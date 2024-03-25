/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import entidades.Impresora;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jhon
 */
public class ImpresoraJpaController implements Serializable {

    public ImpresoraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Impresora impresora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(impresora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Impresora impresora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            impresora = em.merge(impresora);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = impresora.getPiImpresoraId();
                if (findImpresora(id) == null) {
                    throw new NonexistentEntityException("The impresora with id " + id + " no longer exists.");
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
            Impresora impresora;
            try {
                impresora = em.getReference(Impresora.class, id);
                impresora.getPiImpresoraId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The impresora with id " + id + " no longer exists.", enfe);
            }
            em.remove(impresora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Impresora> findImpresoraEntities() {
        return findImpresoraEntities(true, -1, -1);
    }

    public List<Impresora> findImpresoraEntities(int maxResults, int firstResult) {
        return findImpresoraEntities(false, maxResults, firstResult);
    }

    private List<Impresora> findImpresoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Impresora.class));
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

    public Impresora findImpresora(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Impresora.class, id);
        } finally {
            em.close();
        }
    }

    public int getImpresoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Impresora> rt = cq.from(Impresora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Impresora buscarImpresora(String nombreImpresora) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT i.* "
                + "FROM impresora i "
                + "WHERE i.s_impresora_nombre = ? ;";
        try {
            Query q = em.createNativeQuery(consulta, Impresora.class);
            q.setParameter(1, nombreImpresora);
            return (Impresora)q.getSingleResult();
        }catch(NoResultException ex){
            return null;
        } 
        finally {
            em.close();
        }
    }
    
}
