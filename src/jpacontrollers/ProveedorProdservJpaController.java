/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.ProveedorProdserv;
import entidades.ProveedorProdservPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ProveedorProdservJpaController implements Serializable {

    public ProveedorProdservJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProveedorProdserv proveedorProdserv) throws PreexistingEntityException, Exception {
        if (proveedorProdserv.getProveedorProdservPK() == null) {
            proveedorProdserv.setProveedorProdservPK(new ProveedorProdservPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedorProdserv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedorProdserv(proveedorProdserv.getProveedorProdservPK()) != null) {
                throw new PreexistingEntityException("ProveedorProdserv " + proveedorProdserv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProveedorProdserv proveedorProdserv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedorProdserv = em.merge(proveedorProdserv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProveedorProdservPK id = proveedorProdserv.getProveedorProdservPK();
                if (findProveedorProdserv(id) == null) {
                    throw new NonexistentEntityException("The proveedorProdserv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProveedorProdservPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProveedorProdserv proveedorProdserv;
            try {
                proveedorProdserv = em.getReference(ProveedorProdserv.class, id);
                proveedorProdserv.getProveedorProdservPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedorProdserv with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedorProdserv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProveedorProdserv> findProveedorProdservEntities() {
        return findProveedorProdservEntities(true, -1, -1);
    }

    public List<ProveedorProdserv> findProveedorProdservEntities(int maxResults, int firstResult) {
        return findProveedorProdservEntities(false, maxResults, firstResult);
    }

    private List<ProveedorProdserv> findProveedorProdservEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProveedorProdserv.class));
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

    public ProveedorProdserv findProveedorProdserv(ProveedorProdservPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProveedorProdserv.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorProdservCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProveedorProdserv> rt = cq.from(ProveedorProdserv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
