/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.EquipoNuevo;
import jpacontrollers.exceptions.NonexistentEntityException;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author HelpDesk
 */
public class EquipoNuevoJpaController implements Serializable {

    public EquipoNuevoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EquipoNuevo equiposnuevos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(equiposnuevos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EquipoNuevo equiposnuevos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            equiposnuevos = em.merge(equiposnuevos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equiposnuevos.getId();
                if (findEquiposnuevos(id) == null) {
                    throw new NonexistentEntityException("The equiposnuevos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipoNuevo equiposnuevos;
            try {
                equiposnuevos = em.getReference(EquipoNuevo.class, id);
                equiposnuevos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equiposnuevos with id " + id + " no longer exists.", enfe);
            }
            em.remove(equiposnuevos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EquipoNuevo> findEquiposnuevosEntities() {
        return findEquiposnuevosEntities(true, -1, -1);
    }

    public List<EquipoNuevo> findEquiposnuevosEntities(int maxResults, int firstResult) {
        return findEquiposnuevosEntities(false, maxResults, firstResult);
    }

    private List<EquipoNuevo> findEquiposnuevosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EquipoNuevo.class));
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

    public EquipoNuevo findEquiposnuevos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EquipoNuevo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquiposnuevosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EquipoNuevo> rt = cq.from(EquipoNuevo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
