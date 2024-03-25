

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Documento;
import entidades.ResponsableDocumento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "notificacionesUsuario", columns = {
        @ColumnResult(name = "notificacionesUsuario")})
})
public class SUsuarioJpaController implements Serializable {

    public SUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SUsuario SUsuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(SUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSUsuario(SUsuario.getPsUsuarioCodigo()) != null) {
                throw new PreexistingEntityException("SUsuario " + SUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SUsuario SUsuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario persistentSUsuario = em.find(SUsuario.class, SUsuario.getPsUsuarioCodigo());
            SUsuario = em.merge(SUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = SUsuario.getPsUsuarioCodigo();
                if (findSUsuario(id) == null) {
                    throw new NonexistentEntityException("The sUsuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario SUsuario;
            try {
                SUsuario = em.getReference(SUsuario.class, id);
                SUsuario.getPsUsuarioCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SUsuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(SUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SUsuario> findSUsuarioEntities() {
        return findSUsuarioEntities(true, -1, -1);
    }

    public List<SUsuario> findSUsuarioEntities(int maxResults, int firstResult) {
        return findSUsuarioEntities(false, maxResults, firstResult);
    }

    private List<SUsuario> findSUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SUsuario.class));
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

    public SUsuario findSUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getSUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SUsuario> rt = cq.from(SUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public SUsuario autenticarUsuario(String codigoUsuario, String contrasena) {
        EntityManager em = getEntityManager();
        SUsuario usuario = null;
        try {
            em.getTransaction().begin();
            String consulta = "SELECT s.* FROM s_usuario s WHERE s.ps_usuario_codigo = ? "
                    + "AND AES_ENCRYPT(?, 'C1m4bienta1ES') = s.by_usuario_contrasena; ";
            Query cq = em.createNativeQuery(consulta, SUsuario.class);
            cq.setParameter(1, codigoUsuario);
            cq.setParameter(2, contrasena);
            em.getTransaction().commit();
            usuario = (SUsuario)cq.getSingleResult();
            return usuario;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }
    }

    public List notificacionesUsuario(String codigoUsuario) {
        EntityManager em = getEntityManager();
        List notificaciones = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_notificaciones (?) ; ", "notificacionesUsuario");
            pa.setParameter(1, codigoUsuario);
            notificaciones = pa.getResultList();
            em.getTransaction().commit();
            return notificaciones;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean requiereCambioContrasena(String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT u.* "
                + "FROM s_usuario u "
                + "WHERE u.ps_usuario_codigo = ? AND "
                + "	( u.d_usuario_ultcamcon < (NOW() - INTERVAL 3 MONTH) OR"
                + "	u.s_usuario_reqcamcon = '1' );";
        try {
            Query q = em.createNativeQuery(consulta, SUsuario.class);
            q.setParameter(1, codigoUsuario);
            SUsuario resu = (SUsuario) q.getSingleResult();
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

    public void actualizarContrasena(String codigoUsuario, String contrasenaNueva, 
            String usuarioCambia, char requiereCambiarContrasena) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaContrasena =
                "UPDATE s_usuario "
                + "SET by_usuario_contrasena = AES_ENCRYPT(?, 'C1m4bienta1ES'), "
                + "	s_usuario_reqcamcon = ?, "
                + "	d_usuario_ultcamcon = NOW(), "
                + "	fs_usuario_usuultmod = ? , "
                + "	d_usuario_ultimodi = NOW() "
                + "WHERE ps_usuario_codigo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaContrasena);
            q.setParameter(1, contrasenaNueva);
            q.setParameter(2, requiereCambiarContrasena);
            q.setParameter(3, usuarioCambia);
            q.setParameter(4, codigoUsuario);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSUsuario(codigoUsuario) == null) {
                    throw new NonexistentEntityException("El Usuario " + codigoUsuario + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean isUsuarioEjecutaAccionDocumento(String codigoUsuario) {
        EntityManager em = getEntityManager();
        List<ResponsableDocumento> resultados = null;
        try {
            String consulta =
                    "SELECT rd.* "
                    + "FROM s_usuario u  "
                    + "INNER JOIN usuario_cargo uc ON u.ps_usuario_codigo = uc.pfs_usuacarg_usuario "
                    + "INNER JOIN cargo c ON c.ps_cargo_codigo = uc.pfs_usuacarg_cargo "
                    + "INNER JOIN responsable_documento rd ON rd.pfs_respdocu_cargo = c.ps_cargo_codigo "
                    + "WHERE u.s_usuario_estado = 'VIGENTE' AND "
                    + "	uc.s_usuacarg_estado = 'V' AND "
                    + "	c.s_cargo_estado = 'VIGENTE' AND "
                    + "	u.ps_usuario_codigo = ? ; ";
            Query cq = em.createNativeQuery(consulta, ResponsableDocumento.class);
            cq.setParameter(1, codigoUsuario);
            resultados = cq.getResultList();
            return (resultados != null && !resultados.isEmpty());
        } catch (Exception noRes) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<SUsuario> usuariosVigentes() {
        EntityManager em = getEntityManager();
        List<SUsuario> resultados = null;
        try {
            String consulta =
                    "SELECT u.* "
                    + "FROM s_usuario u "
                    + "WHERE u.s_usuario_estado = 'VIGENTE' ; ";
            Query cq = em.createNativeQuery(consulta, SUsuario.class);
            resultados = cq.getResultList();
            return resultados;
        } finally {
            em.close();
        }
    }
    
    public List<SUsuario> usuariosVigentesSinElActual(String usuario) {
        EntityManager em = getEntityManager();
        List<SUsuario> resultados = null;
        try {
            String consulta =
                    "SELECT u.* "
                    + "FROM s_usuario u "
                    + "WHERE u.s_usuario_estado = 'VIGENTE'  "
                    + "and u.ps_usuario_codigo!= ? ;";
            Query cq = em.createNativeQuery(consulta, SUsuario.class);
            cq.setParameter(1, usuario);
            resultados = cq.getResultList();
            return resultados;
        } finally {
            em.close();
        }
    }


    
    public int insertar(SUsuario nuevo, String codigoUsuario) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "INSERT INTO s_usuario "
                + "(ps_usuario_codigo,	s_usuario_nombres,	s_usuario_apellidos, "
                + "by_usuario_contrasena,	s_usuario_estado,	s_usuario_reqcamcon, "
                + "d_usuario_ultcamcon,         fs_usuario_usuacrea,	d_usuario_creacion, "
                + "fs_usuario_usuultmod,        d_usuario_ultimodi , s_usuario_correo) "
                + "VALUES "
                + "(?,		?,		?, "
                + "AES_ENCRYPT('sistemas', 'C1m4bienta1ES'),		?,		'1', "
                + "NULL,	?,		NOW(), "
                + "NULL,        NULL,   ?); ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getPsUsuarioCodigo());
            q.setParameter(2, nuevo.getsUsuarioNombres());
            q.setParameter(3, nuevo.getsUsuarioApellidos());
            q.setParameter(4, nuevo.getsUsuarioEstado());
            q.setParameter(5, codigoUsuario);
            q.setParameter(6, nuevo.getsUsuarioCorreo());
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public int actualizar(SUsuario nuevo, String codigoUsuario, String anterior) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "UPDATE s_usuario "
                + "SET s_usuario_nombres = ?, "
                + "	s_usuario_apellidos = ?, "
                + "	s_usuario_estado = ?, "
                + "	s_usuario_reqcamcon = ?, "
                + "	fs_usuario_usuultmod = ?, "
                + "	d_usuario_ultimodi = NOW() ,"
                + "     s_usuario_correo = ? "
                + "WHERE ps_usuario_codigo = ? ; ";
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nuevo.getsUsuarioNombres());
            q.setParameter(2, nuevo.getsUsuarioApellidos());
            q.setParameter(3, nuevo.getsUsuarioEstado());
            q.setParameter(4, nuevo.getsUsuarioReqcamcon());
            q.setParameter(5, codigoUsuario);
            q.setParameter(6, nuevo.getsUsuarioCorreo());
            q.setParameter(7, anterior);
            int reg = q.executeUpdate();
            em.getTransaction().commit();
            return reg;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public List<SUsuario> consultaUsuarios(String codigo, String nombres, 
            String apellidos, String estado, String filtros) throws Exception {
        EntityManager em = getEntityManager();
        String consulta = "SELECT u.* FROM s_usuario u ";
        consulta += filtros + ";";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, SUsuario.class);
            if (codigo != null) {
                q.setParameter(i, codigo);
                i++;
            }
            if (nombres != null) {
                q.setParameter(i, nombres);
                i++;
            }
            if (apellidos != null) {
                q.setParameter(i, apellidos);
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

    public SUsuario findUsuarioByNombre(String usuario) {       
       EntityManager em = getEntityManager();
       SUsuario susuario = new SUsuario(); 
        List<SUsuario> results = em.createNamedQuery("SUsuario.findByPsUsuarioCodigo")
                .setParameter("psUsuarioCodigo", usuario)
                .getResultList();
        for (int i=0; i<results.size(); i++) {
            susuario=results.get(i);
        }
        return susuario;
   }

    public List<SUsuario> traerUsuarios(Documento doc) {
             EntityManager em;
        em = getEntityManager();
        String consulta = "SELECT u.* FROM  s_usuario u , usuario_cargo us  INNER JOIN responsable_document"
                + "o  res ON res.pfs_respdocu_cargo=us.pfs_usuacarg_cargo  INNER JOIN proceso pr ON"
                + " pr.s_proceso_letrdocu= ?  WHERE us.s_usuacarg_estado='V'  AND res.ps_respdocu_t"
                + "iporesp='E'  AND res.pfs_respdocu_tipodocu= ?  AND res.pfi_respdocu_proceso=pr.p"
                + "i_proceso_id  AND us.pfs_usuacarg_usuario=u.ps_usuario_codigo  AND u.s_usuario_e"
                + "stado='VIGENTE'  GROUP BY(u.ps_usuario_codigo) ";
        Query q = em.createNativeQuery(consulta, SUsuario.class);
        q.setParameter(1, doc.getDocumentoPK().getPfsDocumentLetrproc());
        q.setParameter(2, doc.getDocumentoPK().getPfsDocumentTipodocu());
        return q.getResultList();
    }

   
    
    
}
