/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Software;
import entidades.SoftwaresPK;
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
public class SoftwaresJpaController implements Serializable {

    public SoftwaresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Software softwares) throws PreexistingEntityException, Exception {
        if (softwares.getSoftwaresPK() == null) {
            softwares.setSoftwaresPK(new SoftwaresPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(softwares);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSoftwares(softwares.getSoftwaresPK()) != null) {
                throw new PreexistingEntityException("Softwares " + softwares + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Software softwares) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            softwares = em.merge(softwares);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SoftwaresPK id = softwares.getSoftwaresPK();
                if (findSoftwares(id) == null) {
                    throw new NonexistentEntityException("The softwares with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SoftwaresPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Software softwares;
            try {
                softwares = em.getReference(Software.class, id);
                softwares.getSoftwaresPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The softwares with id " + id + " no longer exists.", enfe);
            }
            em.remove(softwares);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Software> findSoftwaresEntities() {
        return findSoftwaresEntities(true, -1, -1);
    }

    public List<Software> findSoftwaresEntities(int maxResults, int firstResult) {
        return findSoftwaresEntities(false, maxResults, firstResult);
    }

    private List<Software> findSoftwaresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Software.class));
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

    public Software findSoftwares(SoftwaresPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Software.class, id);
        } finally {
            em.close();
        }
    }

    public int getSoftwaresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Software> rt = cq.from(Software.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
