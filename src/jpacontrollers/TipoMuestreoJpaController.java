/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.TipoMuestra;
import entidades.TipoMuestreo;
import jpacontrollers.exceptions.NonexistentEntityException;
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
 * @author TOSHIBA
 */
public class TipoMuestreoJpaController implements Serializable {

    public TipoMuestreoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMuestreo tpMuestra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tpMuestra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMuestreo tpMuestra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tpMuestra = em.merge(tpMuestra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tpMuestra.getIdtpMuestra();
                if (findTpMuestra(id) == null) {
                    throw new NonexistentEntityException("The tpMuestra with id " + id + " no longer exists.");
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
            TipoMuestreo tpMuestra;
            try {
                tpMuestra = em.getReference(TipoMuestreo.class, id);
                tpMuestra.getIdtpMuestra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tpMuestra with id " + id + " no longer exists.", enfe);
            }
            em.remove(tpMuestra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMuestreo> findTpMuestraEntities() {
        return findTpMuestraEntities(true, -1, -1);
    }

    public List<TipoMuestreo> findTpMuestraEntities(int maxResults, int firstResult) {
        return findTpMuestraEntities(false, maxResults, firstResult);
    }

    private List<TipoMuestreo> findTpMuestraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMuestreo.class));
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

    public TipoMuestreo findTpMuestra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMuestreo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTpMuestraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMuestreo> rt = cq.from(TipoMuestreo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Devolver el tipo de muestra pasando el nombre
    public List<TipoMuestreo> getTipoMuestreo(String nombre) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_muestreo where s_tipomuestreo_nombre = ?";
        try {
            Query q = em.createNativeQuery(consulta, TipoMuestreo.class);
            q.setParameter(1, nombre);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<TipoMuestreo> encontrarTipoMuestreo() {
           EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_muestreo"
                + " order by s_tipomuestreo_nombre";
        try {
            Query q = em.createNativeQuery(consulta, TipoMuestreo.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

   
    

  
    
}
