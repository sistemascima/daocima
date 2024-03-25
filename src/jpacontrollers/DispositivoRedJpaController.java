/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DispositivoRed;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class DispositivoRedJpaController implements Serializable {

    public DispositivoRedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DispositivoRed dispositivoRed) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dispositivoRed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DispositivoRed dispositivoRed) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dispositivoRed = em.merge(dispositivoRed);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dispositivoRed.getPiDispredId();
                if (findDispositivoRed(id) == null) {
                    throw new NonexistentEntityException("The dispositivoRed with id " + id + " no longer exists.");
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
            DispositivoRed dispositivoRed;
            try {
                dispositivoRed = em.getReference(DispositivoRed.class, id);
                dispositivoRed.getPiDispredId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dispositivoRed with id " + id + " no longer exists.", enfe);
            }
            em.remove(dispositivoRed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DispositivoRed> findDispositivoRedEntities() {
        return findDispositivoRedEntities(true, -1, -1);
    }

    public List<DispositivoRed> findDispositivoRedEntities(int maxResults, int firstResult) {
        return findDispositivoRedEntities(false, maxResults, firstResult);
    }

    private List<DispositivoRed> findDispositivoRedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DispositivoRed.class));
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

    public DispositivoRed findDispositivoRed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DispositivoRed.class, id);
        } finally {
            em.close();
        }
    }

    public int getDispositivoRedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DispositivoRed> rt = cq.from(DispositivoRed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
