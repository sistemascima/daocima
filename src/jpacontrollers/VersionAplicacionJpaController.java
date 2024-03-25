/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.VersionAplicacion;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Jhon
 */
public class VersionAplicacionJpaController implements Serializable {

    public VersionAplicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VersionAplicacion versionAplicacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(versionAplicacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVersionAplicacion(versionAplicacion.getPiVersapliVersion()) != null) {
                throw new PreexistingEntityException("VersionAplicacion " + versionAplicacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VersionAplicacion versionAplicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            versionAplicacion = em.merge(versionAplicacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = versionAplicacion.getPiVersapliVersion();
                if (findVersionAplicacion(id) == null) {
                    throw new NonexistentEntityException("The versionAplicacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VersionAplicacion versionAplicacion;
            try {
                versionAplicacion = em.getReference(VersionAplicacion.class, id);
                versionAplicacion.getPiVersapliVersion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The versionAplicacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(versionAplicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VersionAplicacion> findVersionAplicacionEntities() {
        return findVersionAplicacionEntities(true, -1, -1);
    }

    public List<VersionAplicacion> findVersionAplicacionEntities(int maxResults, int firstResult) {
        return findVersionAplicacionEntities(false, maxResults, firstResult);
    }

    private List<VersionAplicacion> findVersionAplicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VersionAplicacion.class));
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

    public VersionAplicacion findVersionAplicacion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VersionAplicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getVersionAplicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VersionAplicacion> rt = cq.from(VersionAplicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public BigDecimal getVersionActual() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT max(pi_versapli_version)"
                + "FROM version_aplicacion; ";
        try {
            Query q = em.createNativeQuery(consulta);
            BigDecimal resu = (BigDecimal) q.getSingleResult();
            return resu;
        } finally {
            em.close();
        }
    }
    
}
