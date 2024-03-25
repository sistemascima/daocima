/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AspectoPregunta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Pregunta;
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
public class AspectoPreguntaJpaController implements Serializable {

    public AspectoPreguntaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AspectoPregunta aspectoPregunta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aspectoPregunta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAspectoPregunta(aspectoPregunta.getPsAspepregCodigo()) != null) {
                throw new PreexistingEntityException("AspectoPregunta " + aspectoPregunta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AspectoPregunta aspectoPregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aspectoPregunta = em.merge(aspectoPregunta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Character id = aspectoPregunta.getPsAspepregCodigo();
                if (findAspectoPregunta(id) == null) {
                    throw new NonexistentEntityException("The aspectoPregunta with id " + id + " no longer exists.");
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
            AspectoPregunta aspectoPregunta;
            try {
                aspectoPregunta = em.getReference(AspectoPregunta.class, id);
                aspectoPregunta.getPsAspepregCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aspectoPregunta with id " + id + " no longer exists.", enfe);
            }
            em.remove(aspectoPregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AspectoPregunta> findAspectoPreguntaEntities() {
        return findAspectoPreguntaEntities(true, -1, -1);
    }

    public List<AspectoPregunta> findAspectoPreguntaEntities(int maxResults, int firstResult) {
        return findAspectoPreguntaEntities(false, maxResults, firstResult);
    }

    private List<AspectoPregunta> findAspectoPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AspectoPregunta.class));
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

    public AspectoPregunta findAspectoPregunta(Character id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AspectoPregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAspectoPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AspectoPregunta> rt = cq.from(AspectoPregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
