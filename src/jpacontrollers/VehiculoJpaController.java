/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HojaDeVida;
import entidades.RevisionesVehiculo;
import entidades.Vehiculo;
import entidades.VehiculoPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class VehiculoJpaController implements Serializable {

    public VehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehiculo vehiculo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (vehiculo.getVehiculoPK() == null) {
            vehiculo.setVehiculoPK(new VehiculoPK());
        }
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaMatriz(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaFuncion(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaConsecutivo(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        List<String> illegalOrphanMessages = null;
        HojaDeVida hojaDeVidaOrphanCheck = vehiculo.getHojaDeVida();
        if (hojaDeVidaOrphanCheck != null) {
            Vehiculo oldVehiculoOfHojaDeVida = hojaDeVidaOrphanCheck.getVehiculo();
            if (oldVehiculoOfHojaDeVida != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaOrphanCheck + " already has an item of type Vehiculo whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida hojaDeVida = vehiculo.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida = em.getReference(hojaDeVida.getClass(), hojaDeVida.getHojaDeVidaPK());
                vehiculo.setHojaDeVida(hojaDeVida);
            }
            RevisionesVehiculo revisionesVehiculo = vehiculo.getRevisionesVehiculo();
            if (revisionesVehiculo != null) {
                revisionesVehiculo = em.getReference(revisionesVehiculo.getClass(), revisionesVehiculo.getRevisionesVehiculoPK());
                vehiculo.setRevisionesVehiculo(revisionesVehiculo);
            }
            em.persist(vehiculo);
            if (hojaDeVida != null) {
                hojaDeVida.setVehiculo(vehiculo);
                hojaDeVida = em.merge(hojaDeVida);
            }
            if (revisionesVehiculo != null) {
                Vehiculo oldVehiculoOfRevisionesVehiculo = revisionesVehiculo.getVehiculo();
                if (oldVehiculoOfRevisionesVehiculo != null) {
                    oldVehiculoOfRevisionesVehiculo.setRevisionesVehiculo(null);
                    oldVehiculoOfRevisionesVehiculo = em.merge(oldVehiculoOfRevisionesVehiculo);
                }
                revisionesVehiculo.setVehiculo(vehiculo);
                revisionesVehiculo = em.merge(revisionesVehiculo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVehiculo(vehiculo.getVehiculoPK()) != null) {
                throw new PreexistingEntityException("Vehiculo " + vehiculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehiculo vehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaMatriz(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaFuncion(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        vehiculo.getVehiculoPK().setFkVehiculoHojaDeVidaConsecutivo(vehiculo.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo persistentVehiculo = em.find(Vehiculo.class, vehiculo.getVehiculoPK());
            HojaDeVida hojaDeVidaOld = persistentVehiculo.getHojaDeVida();
            HojaDeVida hojaDeVidaNew = vehiculo.getHojaDeVida();
            RevisionesVehiculo revisionesVehiculoOld = persistentVehiculo.getRevisionesVehiculo();
            RevisionesVehiculo revisionesVehiculoNew = vehiculo.getRevisionesVehiculo();
            List<String> illegalOrphanMessages = null;
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                Vehiculo oldVehiculoOfHojaDeVida = hojaDeVidaNew.getVehiculo();
                if (oldVehiculoOfHojaDeVida != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaNew + " already has an item of type Vehiculo whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
                }
            }
            if (revisionesVehiculoOld != null && !revisionesVehiculoOld.equals(revisionesVehiculoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain RevisionesVehiculo " + revisionesVehiculoOld + " since its vehiculo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hojaDeVidaNew != null) {
                hojaDeVidaNew = em.getReference(hojaDeVidaNew.getClass(), hojaDeVidaNew.getHojaDeVidaPK());
                vehiculo.setHojaDeVida(hojaDeVidaNew);
            }
            if (revisionesVehiculoNew != null) {
                revisionesVehiculoNew = em.getReference(revisionesVehiculoNew.getClass(), revisionesVehiculoNew.getRevisionesVehiculoPK());
                vehiculo.setRevisionesVehiculo(revisionesVehiculoNew);
            }
            vehiculo = em.merge(vehiculo);
            if (hojaDeVidaOld != null && !hojaDeVidaOld.equals(hojaDeVidaNew)) {
                hojaDeVidaOld.setVehiculo(null);
                hojaDeVidaOld = em.merge(hojaDeVidaOld);
            }
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                hojaDeVidaNew.setVehiculo(vehiculo);
                hojaDeVidaNew = em.merge(hojaDeVidaNew);
            }
            if (revisionesVehiculoNew != null && !revisionesVehiculoNew.equals(revisionesVehiculoOld)) {
                Vehiculo oldVehiculoOfRevisionesVehiculo = revisionesVehiculoNew.getVehiculo();
                if (oldVehiculoOfRevisionesVehiculo != null) {
                    oldVehiculoOfRevisionesVehiculo.setRevisionesVehiculo(null);
                    oldVehiculoOfRevisionesVehiculo = em.merge(oldVehiculoOfRevisionesVehiculo);
                }
                revisionesVehiculoNew.setVehiculo(vehiculo);
                revisionesVehiculoNew = em.merge(revisionesVehiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VehiculoPK id = vehiculo.getVehiculoPK();
                if (findVehiculo(id) == null) {
                    throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VehiculoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo vehiculo;
            try {
                vehiculo = em.getReference(Vehiculo.class, id);
                vehiculo.getVehiculoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            RevisionesVehiculo revisionesVehiculoOrphanCheck = vehiculo.getRevisionesVehiculo();
            if (revisionesVehiculoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the RevisionesVehiculo " + revisionesVehiculoOrphanCheck + " in its revisionesVehiculo field has a non-nullable vehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            HojaDeVida hojaDeVida = vehiculo.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida.setVehiculo(null);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.remove(vehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehiculo> findVehiculoEntities() {
        return findVehiculoEntities(true, -1, -1);
    }

    public List<Vehiculo> findVehiculoEntities(int maxResults, int firstResult) {
        return findVehiculoEntities(false, maxResults, firstResult);
    }

    private List<Vehiculo> findVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehiculo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vehiculo findVehiculo(VehiculoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehiculo> rt = cq.from(Vehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
