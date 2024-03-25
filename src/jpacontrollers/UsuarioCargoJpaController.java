/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AccesoDocumento;
import entidades.Cargo;
import entidades.DocumentoPK;
import entidades.SUsuario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UsuarioCargo;
import entidades.UsuarioCargoPK;
import java.util.Collection;
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
public class UsuarioCargoJpaController implements Serializable {

    public UsuarioCargoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioCargo usuarioCargo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarioCargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioCargo(usuarioCargo.getUsuarioCargoPK()) != null) {
                throw new PreexistingEntityException("UsuarioCargo " + usuarioCargo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioCargo usuarioCargo) throws NonexistentEntityException, Exception {
        usuarioCargo.getUsuarioCargoPK().setPfsUsuacargUsuario(usuarioCargo.getSUsuario().getPsUsuarioCodigo());
        usuarioCargo.getUsuarioCargoPK().setPfsUsuacargCargo(usuarioCargo.getCargo().getPsCargoCodigo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioCargo persistentUsuarioCargo = em.find(UsuarioCargo.class, usuarioCargo.getUsuarioCargoPK());
            usuarioCargo = em.merge(usuarioCargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioCargoPK id = usuarioCargo.getUsuarioCargoPK();
                if (findUsuarioCargo(id) == null) {
                    throw new NonexistentEntityException("The usuarioCargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioCargoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioCargo usuarioCargo;
            try {
                usuarioCargo = em.getReference(UsuarioCargo.class, id);
                usuarioCargo.getUsuarioCargoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioCargo with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarioCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioCargo> findUsuarioCargoEntities() {
        return findUsuarioCargoEntities(true, -1, -1);
    }

    public List<UsuarioCargo> findUsuarioCargoEntities(int maxResults, int firstResult) {
        return findUsuarioCargoEntities(false, maxResults, firstResult);
    }

    private List<UsuarioCargo> findUsuarioCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioCargo.class));
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

    public UsuarioCargo findUsuarioCargo(UsuarioCargoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioCargo> rt = cq.from(UsuarioCargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean isCargoEjecutaAccionDocumento(String tipoDocumento, int proceso, String codigoUsuario,
            String tipoAccion) {
        System.out.println("tipo documento"+ tipoDocumento);
        EntityManager em = getEntityManager();
        List resultados = null;
        try {
            em.getTransaction().begin();
            String consulta
                    = "SELECT uc.* "
                    + "FROM responsable_documento r "
                    + "INNER JOIN usuario_cargo uc on uc.pfs_usuacarg_cargo = r.pfs_respdocu_cargo and "
                    + "uc.s_usuacarg_estado = 'V' "
                    + "WHERE r.pfs_respdocu_tipodocu = ? AND "
                    + "r.pfi_respdocu_proceso = ? AND "
                    + "uc.pfs_usuacarg_usuario = ? AND "
                    + "r.ps_respdocu_tiporesp = ?; ";
            Query cq = em.createNativeQuery(consulta, UsuarioCargo.class);
            resultados = cq.setParameter(1, tipoDocumento).setParameter(2, proceso)
                    .setParameter(3, codigoUsuario).setParameter(4, tipoAccion).getResultList();
            em.getTransaction().commit();
            return (resultados != null && !resultados.isEmpty());
        } catch (NoResultException noRes) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<Cargo> cargosUsuario(String codigoUsuario) {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            em.getTransaction().begin();
            String consulta
                    = "SELECT c.* "
                    + "FROM s_usuario u  "
                    + "LEFT OUTER JOIN usuario_cargo uc ON u.ps_usuario_codigo = uc.pfs_usuacarg_usuario "
                    + "LEFT OUTER JOIN cargo c ON c.ps_cargo_codigo = uc.pfs_usuacarg_cargo "
                    + "WHERE u.s_usuario_estado = 'VIGENTE' AND "
                    + "	uc.s_usuacarg_estado = 'V' AND "
                    + "	c.s_cargo_estado = 'VIGENTE' AND "
                    + "	u.ps_usuario_codigo = ? ; ";
            Query cq = em.createNativeQuery(consulta, Cargo.class);
            resultados = cq.setParameter(1, codigoUsuario).getResultList();
            em.getTransaction().commit();
            return resultados;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<UsuarioCargo> ObtenerPendientes(DocumentoPK Documento) {

        EntityManager em = getEntityManager();
        List<UsuarioCargo> lista = null;

        try {
            em.getTransaction().begin();
            String Consulta = "SELECT uc.* FROM usuario_cargo uc "
                    + "INNER JOIN acceso_documento acc ON uc.pfs_usuacarg_cargo=acc.pfs_accedocu_cargo "
                    + "WHERE uc.s_usuacarg_estado='V' "
                    + "AND acc.pfs_accedocu_tipodocu= ? "
                    + "AND acc.pfs_accedocu_letrproc= ? "
                    + "AND acc.pfs_accedocu_consdocu= ? "
                    + "AND acc.pfs_accedocu_versdocu= ? "
                    + "GROUP BY(uc.pfs_usuacarg_usuario);";
            Query cq = em.createNativeQuery(Consulta, UsuarioCargo.class);
            cq.setParameter(1, Documento.getPfsDocumentTipodocu());
             cq.setParameter(2, Documento.getPfsDocumentLetrproc());
             cq.setParameter(3, Documento.getPsDocumentConsecutivo());
             cq.setParameter(4, Documento.getPsDocumentVersion());
            lista = cq.getResultList();
            em.getTransaction().commit();
            return lista;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }

    }

    public int insertar(UsuarioCargo nuevo, String codigoUsuario) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "INSERT INTO usuario_cargo "
                + "(pfs_usuacarg_usuario,	pfs_usuacarg_cargo,	s_usuacarg_estado, "
                + "fs_usuacarg_usuacrea,	d_usuacarg_creacion,	fs_usuacarg_usuultmod, "
                + "d_usuacarg_ultimodi) "
                + "VALUES "
                + "(?,		?,		?, "
                + "?,		NOW(),          NULL, "
                + "NULL); ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getUsuarioCargoPK().getPfsUsuacargUsuario());
            q.setParameter(2, nuevo.getUsuarioCargoPK().getPfsUsuacargCargo());
            q.setParameter(3, nuevo.getSUsuacargEstado());
            q.setParameter(4, codigoUsuario);
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public int actualizar(UsuarioCargo nuevo, String codigoUsuario, UsuarioCargoPK anterior) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "UPDATE usuario_cargo "
                + "SET pfs_usuacarg_usuario = ?, "
                + "	pfs_usuacarg_cargo = ?, "
                + "	s_usuacarg_estado = ?, "
                + "	fs_usuacarg_usuultmod = ?, "
                + "	d_usuacarg_ultimodi = NOW() "
                + "WHERE pfs_usuacarg_usuario = ? AND "
                + "	pfs_usuacarg_cargo = ? ; ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getUsuarioCargoPK().getPfsUsuacargUsuario());
            q.setParameter(2, nuevo.getUsuarioCargoPK().getPfsUsuacargCargo());
            q.setParameter(3, nuevo.getSUsuacargEstado());
            q.setParameter(4, codigoUsuario);
            q.setParameter(5, anterior.getPfsUsuacargUsuario());
            q.setParameter(6, anterior.getPfsUsuacargCargo());
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public List<UsuarioCargo> consultaUsuarioCargos(String codigoUsuario, String codigoCargo,
            String estado, String filtros) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "SELECT uc.* FROM usuario_cargo uc ";
        consulta += filtros + ";";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, UsuarioCargo.class);
            if (codigoUsuario != null) {
                q.setParameter(i, codigoUsuario);
                i++;
            }
            if (codigoCargo != null) {
                q.setParameter(i, codigoCargo);
                i++;
            }
            if (estado != null) {
                q.setParameter(i, estado);
                i++;
            }
            return q.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public int eliminar(UsuarioCargoPK id) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "DELETE FROM usuario_cargo "
                + "WHERE pfs_usuacarg_usuario = ? "
                + "AND pfs_usuacarg_cargo = ? ; ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, id.getPfsUsuacargUsuario());
            q.setParameter(2, id.getPfsUsuacargCargo());
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Cargo> cargosUsuarioComponenteCrear(String codigoUsuario, String componente) {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            em.getTransaction().begin();
            String consulta
                    = "SELECT ca.* "
                    + "FROM s_usuario u "
                    + "INNER JOIN usuario_cargo uc ON u.ps_usuario_codigo = uc.pfs_usuacarg_usuario "
                    + "INNER JOIN componente_cargo cc ON cc.pfs_compcarg_cargo = uc.pfs_usuacarg_cargo "
                    + "INNER JOIN componente c ON c.pi_componen_id = cc.pfi_compcarg_componente "
                    + "INNER JOIN cargo ca ON ca.ps_cargo_codigo = cc.pfs_compcarg_cargo "
                    + "WHERE u.ps_usuario_codigo = ? "
                    + "	AND c.s_componen_nombre = ? "
                    + "	AND c.s_componen_crear = '1' "
                    + "	AND u.s_usuario_estado = 'VIGENTE' "
                    + "	AND uc.s_usuacarg_estado = 'V' "
                    + "	AND c.s_componen_estado = 'A'; ";
            Query cq = em.createNativeQuery(consulta, Cargo.class);
            cq.setParameter(1, codigoUsuario);
            cq.setParameter(2, componente);
            resultados = cq.getResultList();
            em.getTransaction().commit();
            return resultados;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<SUsuario> usuariosCargo(String codigoCargo) {
        EntityManager em = getEntityManager();
        List<SUsuario> resultados = null;
        try {
            em.getTransaction().begin();
            String consulta
                    = "SELECT u.* "
                    + "FROM s_usuario u  "
                    + "LEFT OUTER JOIN usuario_cargo uc ON u.ps_usuario_codigo = uc.pfs_usuacarg_usuario "
                    + "LEFT OUTER JOIN cargo c ON c.ps_cargo_codigo = uc.pfs_usuacarg_cargo "
                    + "WHERE u.s_usuario_estado = 'VIGENTE' AND "
                    + "	uc.s_usuacarg_estado = 'V' AND "
                    + "	c.s_cargo_estado = 'VIGENTE' AND "
                    + "	c.ps_cargo_codigo = ? ; ";
            Query cq = em.createNativeQuery(consulta, SUsuario.class);
            resultados = cq.setParameter(1, codigoCargo).getResultList();
            em.getTransaction().commit();
            return resultados;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
  
    
    
}
