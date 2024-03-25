/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DispositivoOtros;
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
public class DispositivoOtrosJpaController implements Serializable {

    public DispositivoOtrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DispositivoOtros dispositivoOtros) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dispositivoOtros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DispositivoOtros dispositivoOtros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dispositivoOtros = em.merge(dispositivoOtros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dispositivoOtros.getPiDispootrosId();
                if (findDispositivoOtros(id) == null) {
                    throw new NonexistentEntityException("The dispositivoOtros with id " + id + " no longer exists.");
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
            DispositivoOtros dispositivoOtros;
            try {
                dispositivoOtros = em.getReference(DispositivoOtros.class, id);
                dispositivoOtros.getPiDispootrosId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dispositivoOtros with id " + id + " no longer exists.", enfe);
            }
            em.remove(dispositivoOtros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DispositivoOtros> findDispositivoOtrosEntities() {
        return findDispositivoOtrosEntities(true, -1, -1);
    }

    public List<DispositivoOtros> findDispositivoOtrosEntities(int maxResults, int firstResult) {
        return findDispositivoOtrosEntities(false, maxResults, firstResult);
    }

    private List<DispositivoOtros> findDispositivoOtrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DispositivoOtros.class));
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

    public DispositivoOtros findDispositivoOtros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DispositivoOtros.class, id);
        } finally {
            em.close();
        }
    }

    public int getDispositivoOtrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DispositivoOtros> rt = cq.from(DispositivoOtros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
