/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.InventarioPc;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class InventarioPcJpaController implements Serializable {

    public InventarioPcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InventarioPc inventarioPc) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inventarioPc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInventarioPc(inventarioPc.getIdInv()) != null) {
                throw new PreexistingEntityException("InventarioPc " + inventarioPc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InventarioPc inventarioPc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inventarioPc = em.merge(inventarioPc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventarioPc.getIdInv();
                if (findInventarioPc(id) == null) {
                    throw new NonexistentEntityException("The inventarioPc with id " + id + " no longer exists.");
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
            InventarioPc inventarioPc;
            try {
                inventarioPc = em.getReference(InventarioPc.class, id);
                inventarioPc.getIdInv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventarioPc with id " + id + " no longer exists.", enfe);
            }
            em.remove(inventarioPc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InventarioPc> findInventarioPcEntities() {
        return findInventarioPcEntities(true, -1, -1);
    }

    public List<InventarioPc> findInventarioPcEntities(int maxResults, int firstResult) {
        return findInventarioPcEntities(false, maxResults, firstResult);
    }

    private List<InventarioPc> findInventarioPcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InventarioPc.class));
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

    public InventarioPc findInventarioPc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InventarioPc.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioPcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InventarioPc> rt = cq.from(InventarioPc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
