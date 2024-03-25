/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.TipoMuestra;
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
public class TipoMuestraJpaController implements Serializable {

    public TipoMuestraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMuestra tipoMuestra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoMuestra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoMuestra(tipoMuestra.getIdTipoMuestra()) != null) {
                throw new PreexistingEntityException("TipoMuestra " + tipoMuestra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMuestra tipoMuestra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoMuestra = em.merge(tipoMuestra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoMuestra.getIdTipoMuestra();
                if (findTipoMuestra(id) == null) {
                    throw new NonexistentEntityException("The tipoMuestra with id " + id + " no longer exists.");
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
            TipoMuestra tipoMuestra;
            try {
                tipoMuestra = em.getReference(TipoMuestra.class, id);
                tipoMuestra.getIdTipoMuestra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMuestra with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoMuestra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMuestra> findTipoMuestraEntities() {
        return findTipoMuestraEntities(true, -1, -1);
    }

    public List<TipoMuestra> findTipoMuestraEntities(int maxResults, int firstResult) {
        return findTipoMuestraEntities(false, maxResults, firstResult);
    }

    private List<TipoMuestra> findTipoMuestraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMuestra.class));
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

    public TipoMuestra findTipoMuestra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMuestra.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMuestraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMuestra> rt = cq.from(TipoMuestra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<TipoMuestra> cargarTipoMuestra() {
     EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_muestra";
        try {
            Query q = em.createNativeQuery(consulta, TipoMuestra.class);
            return q.getResultList();
        } finally {
            em.close();
        }  
    }

    public List<TipoMuestra> encontrarTipoMuestraPorDescripcion(String tipoMuestra) {
    EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_muestra where s_tipomuestra_nombre=?";
        try {
            Query q = em.createNativeQuery(consulta, TipoMuestra.class);
            q.setParameter(1, tipoMuestra);
            return q.getResultList();
        } finally {
            em.close();
        }    
    }

    public List<Object> encontrarUltimoTipoMuestra() {
      EntityManager em = getEntityManager();
        String consulta = "SELECT MAX(pi_tipomuestra_id) FROM tipo_muestra";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
