/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Equipo;
import entidades.EquipoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HojaDeVida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipo equipo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (equipo.getEquipoPK() == null) {
            equipo.setEquipoPK(new EquipoPK());
        }
        equipo.getEquipoPK().setFkEquipoHojaDeVidaConsecutivo(equipo.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        equipo.getEquipoPK().setFkEquipoHojaDeVidaFuncion(equipo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        equipo.getEquipoPK().setFkEquipoHojaDeVidaMatriz(equipo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        List<String> illegalOrphanMessages = null;
        HojaDeVida hojaDeVidaOrphanCheck = equipo.getHojaDeVida();
        if (hojaDeVidaOrphanCheck != null) {
            Equipo oldEquipoOfHojaDeVida = hojaDeVidaOrphanCheck.getEquipo();
            if (oldEquipoOfHojaDeVida != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaOrphanCheck + " already has an item of type Equipo whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida hojaDeVida = equipo.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida = em.getReference(hojaDeVida.getClass(), hojaDeVida.getHojaDeVidaPK());
                equipo.setHojaDeVida(hojaDeVida);
            }
            em.persist(equipo);
            if (hojaDeVida != null) {
                hojaDeVida.setEquipo(equipo);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipo(equipo.getEquipoPK()) != null) {
                throw new PreexistingEntityException("Equipo " + equipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        equipo.getEquipoPK().setFkEquipoHojaDeVidaConsecutivo(equipo.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        equipo.getEquipoPK().setFkEquipoHojaDeVidaFuncion(equipo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        equipo.getEquipoPK().setFkEquipoHojaDeVidaMatriz(equipo.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getEquipoPK());
            HojaDeVida hojaDeVidaOld = persistentEquipo.getHojaDeVida();
            HojaDeVida hojaDeVidaNew = equipo.getHojaDeVida();
            List<String> illegalOrphanMessages = null;
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                Equipo oldEquipoOfHojaDeVida = hojaDeVidaNew.getEquipo();
                if (oldEquipoOfHojaDeVida != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaNew + " already has an item of type Equipo whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hojaDeVidaNew != null) {
                hojaDeVidaNew = em.getReference(hojaDeVidaNew.getClass(), hojaDeVidaNew.getHojaDeVidaPK());
                equipo.setHojaDeVida(hojaDeVidaNew);
            }
            equipo = em.merge(equipo);
            if (hojaDeVidaOld != null && !hojaDeVidaOld.equals(hojaDeVidaNew)) {
                hojaDeVidaOld.setEquipo(null);
                hojaDeVidaOld = em.merge(hojaDeVidaOld);
            }
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                hojaDeVidaNew.setEquipo(equipo);
                hojaDeVidaNew = em.merge(hojaDeVidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EquipoPK id = equipo.getEquipoPK();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EquipoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getEquipoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            HojaDeVida hojaDeVida = equipo.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida.setEquipo(null);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(EquipoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
