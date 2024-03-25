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
import entidades.SUsuario;
import entidades.TipoFormatoEvaluacion;
import entidades.Proveedor;
import entidades.Cargo;
import entidades.EvaluacionProveedor;
import entidades.EvaluacionProveedorPK;
import entidades.RespEvalProv;
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
public class EvaluacionProveedorJpaController implements Serializable {

    public EvaluacionProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EvaluacionProveedor evaluacionProveedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(evaluacionProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEvaluacionProveedor(evaluacionProveedor.getEvaluacionProveedorPK()) != null) {
                throw new PreexistingEntityException("EvaluacionProveedor " + evaluacionProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EvaluacionProveedor evaluacionProveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            evaluacionProveedor = em.merge(evaluacionProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EvaluacionProveedorPK id = evaluacionProveedor.getEvaluacionProveedorPK();
                if (findEvaluacionProveedor(id) == null) {
                    throw new NonexistentEntityException("The evaluacionProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EvaluacionProveedorPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EvaluacionProveedor evaluacionProveedor;
            try {
                evaluacionProveedor = em.getReference(EvaluacionProveedor.class, id);
                evaluacionProveedor.getEvaluacionProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacionProveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(evaluacionProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EvaluacionProveedor> findEvaluacionProveedorEntities() {
        return findEvaluacionProveedorEntities(true, -1, -1);
    }

    public List<EvaluacionProveedor> findEvaluacionProveedorEntities(int maxResults, int firstResult) {
        return findEvaluacionProveedorEntities(false, maxResults, firstResult);
    }

    private List<EvaluacionProveedor> findEvaluacionProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EvaluacionProveedor.class));
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

    public EvaluacionProveedor findEvaluacionProveedor(EvaluacionProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EvaluacionProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EvaluacionProveedor> rt = cq.from(EvaluacionProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void actualizarEvaluacion(EvaluacionProveedor evaluacion, String codigoUsuario) 
            throws Exception{
        System.out.println("entra aqui a actualizar evaluacion");
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE evaluacion_proveedor " +
                    "SET s_evalprov_desporeva = ? , " +
                    "d_evalprov_fecheval = ? , " +
                    "s_evalprov_observaci = ? , " +
                    "fs_evalprov_usuultmod = ? , " +
                    "d_evalprov_ultimodi = NOW() " +
                    "WHERE pfs_evalprov_tipo = ? AND "
                    + "pi_evalprov_numeeval = ? AND "
                    + "pfs_evalprov_proveedor = ? ; ");

            pa.setParameter(1, evaluacion.getSEvalprovDesporeva());
            pa.setParameter(2, evaluacion.getDEvalprovFecheval());
            pa.setParameter(3, evaluacion.getSEvalprovObservaci());
            pa.setParameter(4, codigoUsuario);
            pa.setParameter(5, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovTipo());
            pa.setParameter(6, evaluacion.getEvaluacionProveedorPK().getPiEvalprovNumeeval());
            pa.setParameter(7, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovProveedor());
            pa.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public void terminarEvaluacion(EvaluacionProveedor evaluacion, String codigoUsuario) 
            throws Exception{
        System.out.println("termina evaluacion");
        
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE evaluacion_proveedor " +
                    "SET s_evalprov_estado = 'T' , " +
                    "fs_evalprov_usuultmod = ? , " +
                    "d_evalprov_ultimodi = NOW() " +
                    "WHERE pfs_evalprov_tipo = ? AND "
                    + "pi_evalprov_numeeval = ? AND "
                    + "pfs_evalprov_proveedor = ? ; ");
            Query actualizarProveedor = em.createNativeQuery("UPDATE proveedor SET d_proveedor_fecultree = (SELECT d_evalprov_fecheval "
                    + " FROM evaluacion_proveedor WHERE pfs_evalprov_tipo = ? AND "
                    + " pi_evalprov_numeeval = ? AND "
                    + " pfs_evalprov_proveedor = ?), "
                    + " fs_proveedor_usuultmod = ?, "
                    + "d_proveedor_ultimodi = NOW() "
                    + " WHERE ps_proveedor_nit = ? ");
            pa.setParameter(1, codigoUsuario);
            pa.setParameter(2, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovTipo());
            pa.setParameter(3, evaluacion.getEvaluacionProveedorPK().getPiEvalprovNumeeval());
            pa.setParameter(4, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovProveedor());
            actualizarProveedor.setParameter(1, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovTipo());
            actualizarProveedor.setParameter(2, evaluacion.getEvaluacionProveedorPK().getPiEvalprovNumeeval());
            actualizarProveedor.setParameter(3, evaluacion.getEvaluacionProveedorPK().getPfsEvalprovProveedor());
            actualizarProveedor.setParameter(4, codigoUsuario);
            actualizarProveedor.setParameter(5, evaluacion.getProveedor().getPsProveedorNit());
            pa.executeUpdate();
            actualizarProveedor.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
        
    }
    
}
