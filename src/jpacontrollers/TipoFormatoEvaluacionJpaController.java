/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.FormatoEvaluacion;
import entidades.TipoFormatoEvaluacion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class TipoFormatoEvaluacionJpaController implements Serializable {

    public TipoFormatoEvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoFormatoEvaluacion tipoFormatoEvaluacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoFormatoEvaluacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoFormatoEvaluacion(tipoFormatoEvaluacion.getPsTipforevaCodigo()) != null) {
                throw new PreexistingEntityException("TipoFormatoEvaluacion " + tipoFormatoEvaluacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoFormatoEvaluacion tipoFormatoEvaluacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoFormatoEvaluacion = em.merge(tipoFormatoEvaluacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoFormatoEvaluacion.getPsTipforevaCodigo();
                if (findTipoFormatoEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The tipoFormatoEvaluacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFormatoEvaluacion tipoFormatoEvaluacion;
            try {
                tipoFormatoEvaluacion = em.getReference(TipoFormatoEvaluacion.class, id);
                tipoFormatoEvaluacion.getPsTipforevaCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoFormatoEvaluacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoFormatoEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoFormatoEvaluacion> findTipoFormatoEvaluacionEntities() {
        return findTipoFormatoEvaluacionEntities(true, -1, -1);
    }

    public List<TipoFormatoEvaluacion> findTipoFormatoEvaluacionEntities(int maxResults, int firstResult) {
        return findTipoFormatoEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<TipoFormatoEvaluacion> findTipoFormatoEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoFormatoEvaluacion.class));
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

    public TipoFormatoEvaluacion findTipoFormatoEvaluacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoFormatoEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoFormatoEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoFormatoEvaluacion> rt = cq.from(TipoFormatoEvaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
