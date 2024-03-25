/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.ValorAuditoria;
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
public class ValorAuditoriaJpaController implements Serializable {

    public ValorAuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValorAuditoria valorAuditoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valorAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValorAuditoria valorAuditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valorAuditoria = em.merge(valorAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = valorAuditoria.getValorAuditoriaId();
                if (findValorAuditoria(id) == null) {
                    throw new NonexistentEntityException("The valorAuditoria with id " + id + " no longer exists.");
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
            ValorAuditoria valorAuditoria;
            try {
                valorAuditoria = em.getReference(ValorAuditoria.class, id);
                valorAuditoria.getValorAuditoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorAuditoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(valorAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValorAuditoria> findValorAuditoriaEntities() {
        return findValorAuditoriaEntities(true, -1, -1);
    }

    public List<ValorAuditoria> findValorAuditoriaEntities(int maxResults, int firstResult) {
        return findValorAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<ValorAuditoria> findValorAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValorAuditoria.class));
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

    public ValorAuditoria findValorAuditoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValorAuditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValorAuditoria> rt = cq.from(ValorAuditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
