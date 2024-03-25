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
import entidades.ResponsableDocumento;
import entidades.ResponsableDocumentoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
public class ResponsableDocumentoJpaController implements Serializable {

    public ResponsableDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResponsableDocumento responsableDocumento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(responsableDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsableDocumento(responsableDocumento.getResponsableDocumentoPK()) != null) {
                throw new PreexistingEntityException("ResponsableDocumento " + responsableDocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResponsableDocumento responsableDocumento) throws NonexistentEntityException, Exception {
        responsableDocumento.getResponsableDocumentoPK().setPfsRespdocuTipodocu(responsableDocumento.getTipoDocumento().getPsTipodocuId());
        responsableDocumento.getResponsableDocumentoPK().setPfiRespdocuProceso(responsableDocumento.getProceso().getPiProcesoId());
        responsableDocumento.getResponsableDocumentoPK().setPfsRespdocuCargo(responsableDocumento.getCargo().getPsCargoCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResponsableDocumento persistentResponsableDocumento = em.find(ResponsableDocumento.class, responsableDocumento.getResponsableDocumentoPK());
            responsableDocumento = em.merge(responsableDocumento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ResponsableDocumentoPK id = responsableDocumento.getResponsableDocumentoPK();
                if (findResponsableDocumento(id) == null) {
                    throw new NonexistentEntityException("The responsableDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ResponsableDocumentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResponsableDocumento responsableDocumento;
            try {
                responsableDocumento = em.getReference(ResponsableDocumento.class, id);
                responsableDocumento.getResponsableDocumentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El responsable de documento " + id + " ya no existe.", enfe);
            }
            em.remove(responsableDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResponsableDocumento> findResponsableDocumentoEntities() {
        return findResponsableDocumentoEntities(true, -1, -1);
    }

    public List<ResponsableDocumento> findResponsableDocumentoEntities(int maxResults, int firstResult) {
        return findResponsableDocumentoEntities(false, maxResults, firstResult);
    }

    private List<ResponsableDocumento> findResponsableDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResponsableDocumento.class));
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

    public ResponsableDocumento findResponsableDocumento(ResponsableDocumentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResponsableDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsableDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResponsableDocumento> rt = cq.from(ResponsableDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ResponsableDocumento> consultaResponsables(Integer proceso, String tipoDocumento,
            String codigoCargo, String responsabilidad, String filtros) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "SELECT rd.* FROM responsable_documento rd ";
        consulta += filtros + ";";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, ResponsableDocumento.class);
            if (proceso != null) {
                q.setParameter(i, proceso);
                i++;
            }
            if (tipoDocumento != null) {
                q.setParameter(i, tipoDocumento);
                i++;
            }
            if (codigoCargo != null) {
                q.setParameter(i, codigoCargo);
                i++;
            }
            if (responsabilidad != null) {
                q.setParameter(i, responsabilidad);
                i++;
            }
            return q.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
public int eliminarresponsabilidad(String cargo)
{  EntityManager em = getEntityManager();
        String consulta = "DELETE FROM responsable_documento "
                + "WHERE pfs_respdocu_cargo = ? ";
                
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, cargo);
           
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }}
    public int eliminar(ResponsableDocumentoPK id) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "DELETE FROM responsable_documento "
                + "WHERE pfi_respdocu_proceso = ? "
                + "AND pfs_respdocu_tipodocu = ? "
                + "AND pfs_respdocu_cargo = ? "
                + "AND ps_respdocu_tiporesp = ? ; ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, id.getPfiRespdocuProceso());
            q.setParameter(2, id.getPfsRespdocuTipodocu());
            q.setParameter(3, id.getPfsRespdocuCargo());
            q.setParameter(4, id.getPsRespdocuTiporesp());
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public int insertar(ResponsableDocumento nuevo, String codigoUsuario) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "INSERT INTO responsable_documento "
                + "(pfi_respdocu_proceso,	pfs_respdocu_tipodocu,	pfs_respdocu_cargo, "
                + "ps_respdocu_tiporesp,	fs_respdocu_usuacrea,	d_respdocu_creacion, "
                + "fs_respdocu_usuultmod,	d_respdocu_ultimodi) "
                + "VALUES "
                + "(?,		?,		?, "
                + "?,		?,		NOW(), "
                + "NULL,	NULL); ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getResponsableDocumentoPK().getPfiRespdocuProceso());
            q.setParameter(2, nuevo.getResponsableDocumentoPK().getPfsRespdocuTipodocu());
            q.setParameter(3, nuevo.getResponsableDocumentoPK().getPfsRespdocuCargo());
            q.setParameter(4, nuevo.getResponsableDocumentoPK().getPsRespdocuTiporesp());
            q.setParameter(5, codigoUsuario);
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public int actualizar(ResponsableDocumento nuevo, String codigoUsuario, ResponsableDocumentoPK anterior) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "UPDATE responsable_documento "
                + "SET pfi_respdocu_proceso = ?, "
                + "	pfs_respdocu_tipodocu = ?, "
                + "	pfs_respdocu_cargo = ?, "
                + "	ps_respdocu_tiporesp = ?, "
                + "	fs_respdocu_usuultmod = ?, "
                + "	d_respdocu_ultimodi = NOW() "
                + "WHERE pfi_respdocu_proceso = ? AND "
                + "	pfs_respdocu_tipodocu = ? AND "
                + "	pfs_respdocu_cargo = ? AND "
                + "	ps_respdocu_tiporesp = ? ; ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getResponsableDocumentoPK().getPfiRespdocuProceso());
            q.setParameter(2, nuevo.getResponsableDocumentoPK().getPfsRespdocuTipodocu());
            q.setParameter(3, nuevo.getResponsableDocumentoPK().getPfsRespdocuCargo());
            q.setParameter(4, nuevo.getResponsableDocumentoPK().getPsRespdocuTiporesp());
            q.setParameter(5, codigoUsuario);
            q.setParameter(6, anterior.getPfiRespdocuProceso());
            q.setParameter(7, anterior.getPfsRespdocuTipodocu());
            q.setParameter(8, anterior.getPfsRespdocuCargo());
            q.setParameter(9, anterior.getPsRespdocuTiporesp());
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
}
