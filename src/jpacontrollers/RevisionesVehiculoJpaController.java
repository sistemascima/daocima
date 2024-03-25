/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.RevisionesVehiculo;
import entidades.RevisionesVehiculoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class RevisionesVehiculoJpaController implements Serializable {

    public RevisionesVehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RevisionesVehiculo revisionesVehiculo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (revisionesVehiculo.getRevisionesVehiculoPK() == null) {
            revisionesVehiculo.setRevisionesVehiculoPK(new RevisionesVehiculoPK());
        }
        revisionesVehiculo.getRevisionesVehiculoPK().setPsRevisionesVehiculoMatrizId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaMatriz());
        revisionesVehiculo.getRevisionesVehiculoPK().setPsRevisionesVehiculoFuncionId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaFuncion());
        revisionesVehiculo.getRevisionesVehiculoPK().setPiRevisionesVehiculoConsecutivoId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaConsecutivo());
        List<String> illegalOrphanMessages = null;
        Vehiculo vehiculoOrphanCheck = revisionesVehiculo.getVehiculo();
        if (vehiculoOrphanCheck != null) {
            RevisionesVehiculo oldRevisionesVehiculoOfVehiculo = vehiculoOrphanCheck.getRevisionesVehiculo();
            if (oldRevisionesVehiculoOfVehiculo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Vehiculo " + vehiculoOrphanCheck + " already has an item of type RevisionesVehiculo whose vehiculo column cannot be null. Please make another selection for the vehiculo field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo vehiculo = revisionesVehiculo.getVehiculo();
            if (vehiculo != null) {
                vehiculo = em.getReference(vehiculo.getClass(), vehiculo.getVehiculoPK());
                revisionesVehiculo.setVehiculo(vehiculo);
            }
            em.persist(revisionesVehiculo);
            if (vehiculo != null) {
                vehiculo.setRevisionesVehiculo(revisionesVehiculo);
                vehiculo = em.merge(vehiculo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRevisionesVehiculo(revisionesVehiculo.getRevisionesVehiculoPK()) != null) {
                throw new PreexistingEntityException("RevisionesVehiculo " + revisionesVehiculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RevisionesVehiculo revisionesVehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        revisionesVehiculo.getRevisionesVehiculoPK().setPsRevisionesVehiculoMatrizId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaMatriz());
        revisionesVehiculo.getRevisionesVehiculoPK().setPsRevisionesVehiculoFuncionId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaFuncion());
        revisionesVehiculo.getRevisionesVehiculoPK().setPiRevisionesVehiculoConsecutivoId(revisionesVehiculo.getVehiculo().getVehiculoPK().getFkVehiculoHojaDeVidaConsecutivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RevisionesVehiculo persistentRevisionesVehiculo = em.find(RevisionesVehiculo.class, revisionesVehiculo.getRevisionesVehiculoPK());
            Vehiculo vehiculoOld = persistentRevisionesVehiculo.getVehiculo();
            Vehiculo vehiculoNew = revisionesVehiculo.getVehiculo();
            List<String> illegalOrphanMessages = null;
            if (vehiculoNew != null && !vehiculoNew.equals(vehiculoOld)) {
                RevisionesVehiculo oldRevisionesVehiculoOfVehiculo = vehiculoNew.getRevisionesVehiculo();
                if (oldRevisionesVehiculoOfVehiculo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Vehiculo " + vehiculoNew + " already has an item of type RevisionesVehiculo whose vehiculo column cannot be null. Please make another selection for the vehiculo field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (vehiculoNew != null) {
                vehiculoNew = em.getReference(vehiculoNew.getClass(), vehiculoNew.getVehiculoPK());
                revisionesVehiculo.setVehiculo(vehiculoNew);
            }
            revisionesVehiculo = em.merge(revisionesVehiculo);
            if (vehiculoOld != null && !vehiculoOld.equals(vehiculoNew)) {
                vehiculoOld.setRevisionesVehiculo(null);
                vehiculoOld = em.merge(vehiculoOld);
            }
            if (vehiculoNew != null && !vehiculoNew.equals(vehiculoOld)) {
                vehiculoNew.setRevisionesVehiculo(revisionesVehiculo);
                vehiculoNew = em.merge(vehiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RevisionesVehiculoPK id = revisionesVehiculo.getRevisionesVehiculoPK();
                if (findRevisionesVehiculo(id) == null) {
                    throw new NonexistentEntityException("The revisionesVehiculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RevisionesVehiculoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RevisionesVehiculo revisionesVehiculo;
            try {
                revisionesVehiculo = em.getReference(RevisionesVehiculo.class, id);
                revisionesVehiculo.getRevisionesVehiculoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionesVehiculo with id " + id + " no longer exists.", enfe);
            }
            Vehiculo vehiculo = revisionesVehiculo.getVehiculo();
            if (vehiculo != null) {
                vehiculo.setRevisionesVehiculo(null);
                vehiculo = em.merge(vehiculo);
            }
            em.remove(revisionesVehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RevisionesVehiculo> findRevisionesVehiculoEntities() {
        return findRevisionesVehiculoEntities(true, -1, -1);
    }

    public List<RevisionesVehiculo> findRevisionesVehiculoEntities(int maxResults, int firstResult) {
        return findRevisionesVehiculoEntities(false, maxResults, firstResult);
    }

    private List<RevisionesVehiculo> findRevisionesVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RevisionesVehiculo.class));
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

    public RevisionesVehiculo findRevisionesVehiculo(RevisionesVehiculoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RevisionesVehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionesVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RevisionesVehiculo> rt = cq.from(RevisionesVehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
