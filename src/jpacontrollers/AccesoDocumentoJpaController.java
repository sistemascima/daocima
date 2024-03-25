/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AccesoDocumento;
import entidades.AccesoDocumentoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cargo;
import entidades.DocumentoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
public class AccesoDocumentoJpaController implements Serializable {

    public AccesoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccesoDocumento accesoDocumento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accesoDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccesoDocumento(accesoDocumento.getAccesoDocumentoPK()) != null) {
                throw new PreexistingEntityException("AccesoDocumento " + accesoDocumento + " ya existe en la base de datos.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccesoDocumento accesoDocumento) throws NonexistentEntityException, Exception {
        accesoDocumento.getAccesoDocumentoPK().setPfsAccedocuLetrproc(accesoDocumento.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
        accesoDocumento.getAccesoDocumentoPK().setPfsAccedocuCargo(accesoDocumento.getCargo().getPsCargoCodigo());
        accesoDocumento.getAccesoDocumentoPK().setPfsAccedocuVersdocu(accesoDocumento.getDocumento().getDocumentoPK().getPsDocumentVersion());
        accesoDocumento.getAccesoDocumentoPK().setPfsAccedocuConsdocu(accesoDocumento.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
        accesoDocumento.getAccesoDocumentoPK().setPfsAccedocuTipodocu(accesoDocumento.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccesoDocumento persistentAccesoDocumento = em.find(AccesoDocumento.class, accesoDocumento.getAccesoDocumentoPK());
            accesoDocumento = em.merge(accesoDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AccesoDocumentoPK id = accesoDocumento.getAccesoDocumentoPK();
                if (findAccesoDocumento(id) == null) {
                    throw new NonexistentEntityException("The accesoDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AccesoDocumentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccesoDocumento accesoDocumento;
            try {
                accesoDocumento = em.getReference(AccesoDocumento.class, id);
                accesoDocumento.getAccesoDocumentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accesoDocumento with id " + id + " no longer exists.", enfe);
            }
            em.remove(accesoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccesoDocumento> findAccesoDocumentoEntities() {
        return findAccesoDocumentoEntities(true, -1, -1);
    }

    public List<AccesoDocumento> findAccesoDocumentoEntities(int maxResults, int firstResult) {
        return findAccesoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<AccesoDocumento> findAccesoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccesoDocumento.class));
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

    public AccesoDocumento findAccesoDocumento(AccesoDocumentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccesoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccesoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccesoDocumento> rt = cq.from(AccesoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean hasNivelAcceso(String codigoDocumento, String version, String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ad.* "
                + "FROM acceso_documento ad "
                + "INNER JOIN usuario_cargo uc ON uc.pfs_usuacarg_cargo = ad.pfs_accedocu_cargo "
                + "WHERE ad.ps_accedocu_tipoacce = 'NIVACC' AND "
                + "     concat( ad.pfs_accedocu_tipodocu, ad.pfs_accedocu_letrproc, \"-\", ad.pfs_accedocu_consdocu) = ? AND "
                + "	ad.pfs_accedocu_versdocu = ? AND "
                + "	uc.pfs_usuacarg_usuario = ? "
                + "LIMIT 1; ";
        try {
            Query q = em.createNativeQuery(consulta, AccesoDocumento.class);
            q.setParameter(1, codigoDocumento);
            q.setParameter(2, version);
            q.setParameter(3, codigoUsuario);
            AccesoDocumento resu = (AccesoDocumento) q.getSingleResult();
            if (resu != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public void BorrarAcceso(Cargo cargo) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        String consulta = "DELETE FROM acceso_documento WHERE pfs_accedocu_cargo= ? ";
        Query q = em.createNativeQuery(consulta);
        q.setParameter(1, cargo.getPsCargoCodigo());
        int reg = q.executeUpdate();
        System.out.println(reg);
        em.getTransaction().commit();

        em.close();
    }

    public void GoToDescartar(DocumentoPK id) throws Exception {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            String Consulta = "DELETE FROM acceso_documento  "
                    + "WHERE pfs_accedocu_tipodocu LIKE ?  "
                    + "AND pfs_accedocu_letrproc LIKE ?  "
                    + "AND pfs_accedocu_consdocu LIKE ?  "
                    + "AND pfs_accedocu_versdocu LIKE ?  ; ";
            String Consulta3 = "DELETE FROM comentario_documento "
                    + "WHERE pfs_comedocu_tipodocu=? "
                    + " AND pfs_comedocu_letrproc=? "
                    + " AND pfs_comedocu_consedocu=? "
                    + " AND pfs_comedocu_version=? ;";
            String Consulta2 = "DELETE FROM documento "
                    + " WHERE pfs_document_tipodocu= ? "
                    + " AND pfs_document_letrproc= ? AND "
                    + " ps_document_consecutivo= ? AND "
                    + " ps_document_version= ? ;";
            Query q = em.createNativeQuery(Consulta);
            q.setParameter(1, id.getPfsDocumentTipodocu());
            q.setParameter(2, id.getPfsDocumentLetrproc());
            q.setParameter(3, id.getPsDocumentConsecutivo());
            q.setParameter(4, id.getPsDocumentVersion());
            Query q2 = em.createNativeQuery(Consulta2);
            q2.setParameter(1, id.getPfsDocumentTipodocu());
            q2.setParameter(2, id.getPfsDocumentLetrproc());
            q2.setParameter(3, id.getPsDocumentConsecutivo());
            q2.setParameter(4, id.getPsDocumentVersion());
            Query q3=em.createNativeQuery(Consulta3);
            q3.setParameter(1, id.getPfsDocumentTipodocu());
            q3.setParameter(2, id.getPfsDocumentLetrproc());
            q3.setParameter(3, id.getPsDocumentConsecutivo());
            q3.setParameter(4, id.getPsDocumentVersion());
            q3.executeUpdate();
            q.executeUpdate();
            q2.executeUpdate();
            em.getTransaction().commit();
        } catch (java.lang.Exception ex) {

            throw new Exception(ex);
        } finally {
            em.close();
        }
    }
}
