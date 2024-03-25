/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.ComentarioDocumento;
import entidades.ComentarioDocumentoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.DocumentoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
public class ComentarioDocumentoJpaController implements Serializable {

    public ComentarioDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComentarioDocumento comentarioDocumento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comentarioDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComentarioDocumento(comentarioDocumento.getComentarioDocumentoPK()) != null) {
                throw new PreexistingEntityException("ComentarioDocumento " + comentarioDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void Actualizar(ComentarioDocumento comentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        String consulta = "UPDATE comentario_documento "
                + "SET s_comedocu_comentario= ? ,"
                + "d_comedocu_ultimodi=NOW() ,"
                + " fs_comedocu_usuultmod=? "
                + "WHERE pfs_comedocu_tipodocu= ?  AND "
                + " pfs_comedocu_letrproc= ?  AND "
                + " pfs_comedocu_consedocu=? AND "
                + " pfs_comedocu_version=? AND "
                + " s_comedocu_accion=? ;";
                
        Query q = em.createNativeQuery(consulta);
        q.setParameter(1, comentario.getSComedocuComentario());
        q.setParameter(2, comentario.getFsComedocuUsuacrea().getPsUsuarioCodigo());
        q.setParameter(3, comentario.getComentarioDocumentoPK().getPfsComedocuTipodocu());
        q.setParameter(4, comentario.getComentarioDocumentoPK().getPfsComedocuLetrproc());
        q.setParameter(5, comentario.getComentarioDocumentoPK().getPfsComedocuConsedocu());
        q.setParameter(6, comentario.getComentarioDocumentoPK().getPfsComedocuVersion());
        q.setParameter(7, comentario.getSComedocuAccion());
       
        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void edit(ComentarioDocumento comentarioDocumento) throws NonexistentEntityException, Exception {
        comentarioDocumento.getComentarioDocumentoPK().setPfsComedocuVersion(comentarioDocumento.getDocumento().getDocumentoPK().getPsDocumentVersion());
        comentarioDocumento.getComentarioDocumentoPK().setPfsComedocuConsedocu(comentarioDocumento.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
        comentarioDocumento.getComentarioDocumentoPK().setPfsComedocuTipodocu(comentarioDocumento.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
        comentarioDocumento.getComentarioDocumentoPK().setPfsComedocuLetrproc(comentarioDocumento.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComentarioDocumento persistentComentarioDocumento = em.find(ComentarioDocumento.class, comentarioDocumento.getComentarioDocumentoPK());
            comentarioDocumento = em.merge(comentarioDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComentarioDocumentoPK id = comentarioDocumento.getComentarioDocumentoPK();
                if (findComentarioDocumento(id) == null) {
                    throw new NonexistentEntityException("The comentarioDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComentarioDocumentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComentarioDocumento comentarioDocumento;
            try {
                comentarioDocumento = em.getReference(ComentarioDocumento.class, id);
                comentarioDocumento.getComentarioDocumentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentarioDocumento with id " + id + " no longer exists.", enfe);
            }
            em.remove(comentarioDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComentarioDocumento> findComentarioDocumentoEntities() {
        return findComentarioDocumentoEntities(true, -1, -1);
    }

    public List<ComentarioDocumento> findComentarioDocumentoEntities(int maxResults, int firstResult) {
        return findComentarioDocumentoEntities(false, maxResults, firstResult);
    }

    private List<ComentarioDocumento> findComentarioDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComentarioDocumento.class));
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

    public ComentarioDocumento findComentarioDocumento(ComentarioDocumentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComentarioDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComentarioDocumento> rt = cq.from(ComentarioDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ComentarioDocumento> consultarComentariosDocumento(DocumentoPK documentoPK) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT cd.* "
                + "FROM comentario_documento cd "
                + "WHERE cd.pfs_comedocu_tipodocu = ? AND "
                + "	cd.pfs_comedocu_letrproc = ? AND "
                + "	cd.pfs_comedocu_consedocu = ? AND "
                + "	cd.pfs_comedocu_version = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta, ComentarioDocumento.class);
            q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
            q.setParameter(4, documentoPK.getPsDocumentVersion());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ComentarioDocumento> consultarCambios(DocumentoPK documentoPK) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT cd.* "
                + "FROM comentario_documento cd "
                + "WHERE cd.pfs_comedocu_tipodocu = ? AND "
                + "	cd.pfs_comedocu_letrproc = ? AND "
                + "	cd.pfs_comedocu_consedocu = ? AND"
                + "      cd.s_comedocu_accion= 'C'";
        try {
            Query q = em.createNativeQuery(consulta, ComentarioDocumento.class);
            q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(3, documentoPK.getPsDocumentConsecutivo());

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<ComentarioDocumento> consultarRegistroDeCambioDocumento(DocumentoPK documentoPK) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT cd.* "
                + "FROM comentario_documento cd "
                + "WHERE cd.pfs_comedocu_tipodocu = ? AND "
                + "	cd.pfs_comedocu_letrproc = ? AND "
                + "	cd.pfs_comedocu_consedocu = ? AND "
                + "	cd.pfs_comedocu_version = ? AND"
                + "     cd.s_comedocu_accion='C'; ";
        try {
            Query q = em.createNativeQuery(consulta, ComentarioDocumento.class);
            q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
            q.setParameter(4, documentoPK.getPsDocumentVersion());
            return q.getResultList();
        } finally {
            em.close();
        }
    }


}
