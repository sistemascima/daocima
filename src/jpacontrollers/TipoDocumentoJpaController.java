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
import entidades.Documento;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ResponsableDocumento;
import entidades.TipoDocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoDocumento(tipoDocumento.getPsTipodocuId()) != null) {
                throw new PreexistingEntityException("TipoDocumento " + tipoDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getPsTipodocuId());
            tipoDocumento = em.merge(tipoDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Character id = tipoDocumento.getPsTipodocuId();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getPsTipodocuId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Character id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TipoDocumento> consultarUsuarioAccion(String codigoUsuario, String accion) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT td.* "
                + "FROM usuario_cargo uc "
                + "INNER JOIN responsable_documento rd ON rd.pfs_respdocu_cargo = uc.pfs_usuacarg_cargo "
                + "INNER JOIN tipo_documento td ON td.ps_tipodocu_id = rd.pfs_respdocu_tipodocu "
                + "WHERE uc.pfs_usuacarg_usuario = ? AND "
                + "	rd.ps_respdocu_tiporesp = ? AND "
                + "	uc.s_usuacarg_estado = 'V' ;";
        try {
            Query q = em.createNativeQuery(consulta, TipoDocumento.class);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, accion);
            return q.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
