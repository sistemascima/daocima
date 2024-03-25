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
import entidades.SeleProvSoliComp;
import entidades.Pregunta;
import entidades.RespSeleProv;
import entidades.RespSeleProvPK;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class RespSeleProvJpaController implements Serializable {

    public RespSeleProvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespSeleProv respSeleProv) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(respSeleProv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRespSeleProv(respSeleProv.getRespSeleProvPK()) != null) {
                throw new PreexistingEntityException("RespSeleProv " + respSeleProv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespSeleProv respSeleProv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            respSeleProv = em.merge(respSeleProv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RespSeleProvPK id = respSeleProv.getRespSeleProvPK();
                if (findRespSeleProv(id) == null) {
                    throw new NonexistentEntityException("The respSeleProv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RespSeleProvPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespSeleProv respSeleProv;
            try {
                respSeleProv = em.getReference(RespSeleProv.class, id);
                respSeleProv.getRespSeleProvPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respSeleProv with id " + id + " no longer exists.", enfe);
            }
            em.remove(respSeleProv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespSeleProv> findRespSeleProvEntities() {
        return findRespSeleProvEntities(true, -1, -1);
    }

    public List<RespSeleProv> findRespSeleProvEntities(int maxResults, int firstResult) {
        return findRespSeleProvEntities(false, maxResults, firstResult);
    }

    private List<RespSeleProv> findRespSeleProvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RespSeleProv.class));
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

    public RespSeleProv findRespSeleProv(RespSeleProvPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespSeleProv.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespSeleProvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RespSeleProv> rt = cq.from(RespSeleProv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List traerCriteriosProveedorSeleccion(Integer numeroEvaluacion, String nitProveedor,
            String codigoUsuario) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_tra_res_sel_pro (?, ?, ?) ; ", "pa_tra_res_sel_pro");

            pa.setParameter(1, numeroEvaluacion);
            pa.setParameter(2, nitProveedor);
            pa.setParameter(3, codigoUsuario);
            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean guardarRespuestaCriterio(Integer numeroEvaluacion, Integer formatoEvaluacion,
            String aspecto, Integer orden, String nitProveedor, BigDecimal puntaje,
            String noAplica, String codigoUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query update = em.createNativeQuery("UPDATE resp_sele_prov " +
                    "SET db_resselpro_puntaje = ?, " +
                    "	s_resselpro_noaplica = ?, " +
                    "	fs_resselpro_usuultmod = ?, " +
                    "	d_resselpro_ultimodi = NOW() " +
                    "WHERE pfi_resselpro_numeeval = ? AND " +
                    "pfi_resselpro_formeval = ? AND " +
                    "pfs_resselpro_aspecto = ? AND " +
                    "pfi_resselpro_orden = ? AND " +
                    "pfs_resselpro_proveedor = ? ; ");
            
            update.setParameter(1, puntaje);
            update.setParameter(2, noAplica);
            update.setParameter(3, codigoUsuario);
            update.setParameter(4, numeroEvaluacion);
            update.setParameter(5, formatoEvaluacion);
            update.setParameter(6, aspecto);
            update.setParameter(7, orden);
            update.setParameter(8, nitProveedor);
            update.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
}
