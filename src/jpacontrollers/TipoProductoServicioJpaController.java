/*
 * To change this template, choose Tools | Templates
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
import entidades.TipoProductoServicio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jhon
 */
public class TipoProductoServicioJpaController implements Serializable {

    public TipoProductoServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoProductoServicio tipoProductoServicio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoProductoServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoProductoServicio(tipoProductoServicio.getPsTipproserCodigo()) != null) {
                throw new PreexistingEntityException("TipoProductoServicio " + tipoProductoServicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoProductoServicio tipoProductoServicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoProductoServicio = em.merge(tipoProductoServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Character id = tipoProductoServicio.getPsTipproserCodigo();
                if (findTipoProductoServicio(id) == null) {
                    throw new NonexistentEntityException("The tipoProductoServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Character id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoProductoServicio tipoProductoServicio;
            try {
                tipoProductoServicio = em.getReference(TipoProductoServicio.class, id);
                tipoProductoServicio.getPsTipproserCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoProductoServicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoProductoServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoProductoServicio> findTipoProductoServicioEntities() {
        return findTipoProductoServicioEntities(true, -1, -1);
    }

    public List<TipoProductoServicio> findTipoProductoServicioEntities(int maxResults, int firstResult) {
        return findTipoProductoServicioEntities(false, maxResults, firstResult);
    }

    private List<TipoProductoServicio> findTipoProductoServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoProductoServicio.class));
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

    public TipoProductoServicio findTipoProductoServicio(Character id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoProductoServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoProductoServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoProductoServicio> rt = cq.from(TipoProductoServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
