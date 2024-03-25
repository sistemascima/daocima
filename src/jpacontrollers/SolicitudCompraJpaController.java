/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cargo;
import entidades.DetalleServicioInterno;
import entidades.DetalleSolicitudCompra;
import entidades.Proveedor;
import entidades.SeleProvSoliComp;
import entidades.ServicioInterno;
import entidades.SolicitudCompra;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jhon
 */
public class SolicitudCompraJpaController implements Serializable {

    public SolicitudCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudCompra solicitudCompra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(solicitudCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitudCompra(solicitudCompra.getPiSolicompConsecutivo()) != null) {
                throw new PreexistingEntityException("SolicitudCompra " + solicitudCompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudCompra solicitudCompra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            solicitudCompra = em.merge(solicitudCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudCompra.getPiSolicompConsecutivo();
                if (findSolicitudCompra(id) == null) {
                    throw new NonexistentEntityException("The solicitudCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SolicitudCompra solicitudCompra;
            try {
                solicitudCompra = em.getReference(SolicitudCompra.class, id);
                solicitudCompra.getPiSolicompConsecutivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudCompra with id " + id + " no longer exists.", enfe);
            }
            em.remove(solicitudCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudCompra> findSolicitudCompraEntities() {
        return findSolicitudCompraEntities(true, -1, -1);
    }

    public List<SolicitudCompra> findSolicitudCompraEntities(int maxResults, int firstResult) {
        return findSolicitudCompraEntities(false, maxResults, firstResult);
    }

    private List<SolicitudCompra> findSolicitudCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SolicitudCompra.class));
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

    public SolicitudCompra findSolicitudCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SolicitudCompra> rt = cq.from(SolicitudCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Long calcularConsecutivo() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(sc.pi_solicomp_consecutivo) + 1, "
                + "	EXTRACT(YEAR FROM NOW()) * 10000 + 1) AS proximoCons "
                + "FROM solicitud_compra sc "
                + "WHERE sc.pi_solicomp_consecutivo > EXTRACT(YEAR FROM NOW()) * 10000 AND "
                + "	sc.pi_solicomp_consecutivo < (EXTRACT(YEAR FROM NOW()) + 1)*10000; ";
        try {
            Query q = em.createNativeQuery(consulta);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Long(0);
        } finally {
            em.close();
        }
    }

    public List<Cargo> cargosAprobacion(String codigoDepartamento) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT c.* "
                + "FROM cargo c "
                + "WHERE c.fs_cargo_departamento = ? AND c.s_cargo_estado = 'VIGENTE' AND"
                + "	(c.s_cargo_nombre like '%COORDI%' OR c.s_cargo_nombre like '%DIRECT%');";
        try {
            Query q = em.createNativeQuery(consulta, Cargo.class);
            q.setParameter(1, codigoDepartamento);
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public SolicitudCompra crearSolicitudCompra(SolicitudCompra nueva)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        Integer consecutivoAsignado = 0;
        String sqlInserta = "INSERT INTO solicitud_compra "
                + "(pi_solicomp_consecutivo,          fs_solicomp_usuasoli,           d_solicomp_fecha, "
                + "fs_solicomp_cargsoli,		fs_solicomp_departamento,	s_solicomp_estado, "
                + "s_solicomp_tipocomp,		fs_solicomp_tipproser,          s_solicomp_observaciones, "
                + "fs_solicomp_carvisbue,             fs_solicomp_usuacrea,           d_solicomp_creacion, "
                + "fs_solicomp_usuultmod,             d_solicomp_ultimodi,            fs_solicomp_tipodocumento, "
                + "fs_solicomp_letraprocedimiento, fs_solicomp_consecutivo, fs_solicomp_version, fs_solicomp_usua_solicita, "
                + "s_solicomp_proyecto ) "
                + "VALUES "
                + "(?,		?,			?, "
                + "?,			?,			?, "
                + "?,			?,			?, "
                + "?,			?,			NOW(), "
                + "NULL,              NULL, ?, ?, ?, ?, ?, ? );";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consecutivoAsignado = calcularConsecutivo().intValue();
            Query q = em.createNativeQuery(sqlInserta);
            q.setParameter(1, consecutivoAsignado);
            q.setParameter(2, nueva.getFsSolicompUsuasoli().getPsUsuarioCodigo());
            q.setParameter(3, nueva.getDSolicompFecha());
            q.setParameter(4, nueva.getFsSolicompCargsoli().getPsCargoCodigo());
            q.setParameter(5, nueva.getFsSolicompDepartamento().getPsDepartamCodigo());
            q.setParameter(6, nueva.getSSolicompEstado());
            q.setParameter(7, nueva.getSSolicompTipocomp());
            q.setParameter(8, nueva.getFsSolicompTipproser().getPsTipproserCodigo());
            q.setParameter(9, nueva.getSSolicompObservaciones());
            q.setParameter(10, nueva.getFsSolicompCarvisbue().getPsCargoCodigo());
            q.setParameter(11, nueva.getFsSolicompUsuacrea().getPsUsuarioCodigo());
            q.setParameter(12, nueva.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(13, nueva.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(14, nueva.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(15, nueva.getDocumento().getDocumentoPK().getPsDocumentVersion());
            q.setParameter(16, nueva.getFsSolicompUsuaSolicita().getPsUsuarioCodigo());
            q.setParameter(17, nueva.getSSolicompProyecto());
            q.executeUpdate();
            em.getTransaction().commit();
            return findSolicitudCompra(consecutivoAsignado);
        } catch (Exception ex) {
            if (findSolicitudCompra(consecutivoAsignado) != null) {
                throw new PreexistingEntityException("Solicitud de Compra No. " + consecutivoAsignado + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SolicitudCompra> consultaSolicitudes(String filtros, Queue<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT s.* FROM solicitud_compra s WHERE ";
        consulta += filtros + ";";
        System.out.println("query" + consulta);

        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, SolicitudCompra.class);
            if (argumentos != null) {
                while (!argumentos.isEmpty()) {
                    System.out.println("argumentos" + argumentos.toString());
                    q.setParameter(i, argumentos.remove());
                    i++;
                }
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void radicar(Integer solicitudCompra, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'R',"
                + "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, solicitudCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSolicitudCompra(solicitudCompra) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + solicitudCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
     

    public void darVoBo(Integer solicitudCompra, String codigoUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'V',"
                + "	fs_solicomp_usuvisbue = ? ,"
                + "	d_solicomp_vistbuen = NOW() ,"
                + "	s_solicomp_vistbuen = '1' ,"
                + "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";

        String sqlRevisarParametros = "SELECT IF(COUNT(d.pi_detsolcom_item) = SUM(IF(d.fs_detsolcom_provsele is not null, 1, 0)), 1, 0) "
                + "FROM detalle_solicitud_compra d "
                + "WHERE d.pfi_detsolcom_solicomp = ? ; ";

        String sqlDetalle = "SELECT d.* "
                + "FROM detalle_solicitud_compra d "
                + "WHERE d.pfi_detsolcom_solicomp = ? "
                + "LIMIT 1;";

        String sqlActualizaEstadoSeleccion
                = "UPDATE sele_prov_soli_comp "
                + "SET s_seprsoco_estado = 'S' "
                + "WHERE fi_seprsoco_solicomp = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, solicitudCompra);
            q.executeUpdate();

            q = em.createNativeQuery(sqlRevisarParametros);
            q.setParameter(1, solicitudCompra);
            Long parametros = (Long) q.getSingleResult();

            // Si todos los detalles tienen proveedor seleccionado (parámetros)
            if (parametros.compareTo(Long.valueOf(1)) == 0) {
                q = em.createNativeQuery(sqlDetalle, DetalleSolicitudCompra.class);
                q.setParameter(1, solicitudCompra);
                DetalleSolicitudCompra detalle = (DetalleSolicitudCompra) q.getSingleResult();

                Query pa = em.createNativeQuery("call pa_seleccionar_proveedor (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ; ");
                pa.setParameter(1, null);
                pa.setParameter(2, '1'); // Se asume que es una solicitud de Análisis
                pa.setParameter(3, null);
                pa.setParameter(4, null);
                pa.setParameter(5, "EVALUACIÓN AUTOMÁTICA POR SELECCIÓN DE PARÁMETROS LABORATORIO");
                pa.setParameter(6, detalle.getFsDetsolcomProvsele().getPsProveedorNit());
                pa.setParameter(7, codigoUsuario);
                pa.setParameter(8, solicitudCompra);
                pa.setParameter(9, detalle.getDetalleSolicitudCompraPK().getPiDetsolcomItem());
                pa.setParameter(10, detalle.getSDetsolcomObsesele());
                String resultado = (String) (pa.getSingleResult());

                if (!resultado.equals("OKO")) {
                    em.getTransaction().rollback();
                    throw new Exception("Hubo un error al intentar validar selección con parámetros.");
                } else {
                    q = em.createNativeQuery(sqlActualizaEstadoSeleccion);
                    q.setParameter(1, solicitudCompra);
                    q.executeUpdate();
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSolicitudCompra(solicitudCompra) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + solicitudCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void rechazar(Integer solicitudCompra, char estadoSolicitud, String codigoUsuario, String observacion) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'X',";
        if (estadoSolicitud == 'R') {
            sqlActualizaEstadoSolicitud += "	fs_solicomp_usuvisbue = ? ,"
                    + "	d_solicomp_vistbuen = NOW() ,"
                    + "	s_solicomp_vistbuen = '0' ,"
                    + "	s_solicomp_obseapro = ? ,";
        }
        if (estadoSolicitud == 'V' || estadoSolicitud == 'T' || estadoSolicitud == 'A' || estadoSolicitud == 'O' || estadoSolicitud == 'C') {
            sqlActualizaEstadoSolicitud += "	fs_solicomp_usuaapru = ? ,"
                    + "	fs_solicomp_cargapru = ? ,"
                    + "	d_solicomp_aprobacion = NOW() ,"
                    + "	s_solicomp_aprobado = '0' ,"
                    + "	s_solicomp_obseapro = ? ,";
        }
        sqlActualizaEstadoSolicitud += "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            if (estadoSolicitud == 'R') {
                q.setParameter(2, observacion);
                q.setParameter(3, codigoUsuario);

                q.setParameter(4, solicitudCompra);
            }
            if (estadoSolicitud == 'V') {
                q.setParameter(2, cargoAprueba(codigoUsuario));
                q.setParameter(3, observacion);
                q.setParameter(4, codigoUsuario);
                q.setParameter(5, solicitudCompra);
            }
            if (estadoSolicitud == 'T') {
                q.setParameter(2, cargoAprueba(codigoUsuario));
                q.setParameter(3, observacion);
                q.setParameter(4, codigoUsuario);
                q.setParameter(5, solicitudCompra);
            }
            if (estadoSolicitud == 'A') {
                q.setParameter(2, cargoAprueba(codigoUsuario));
                q.setParameter(3, observacion);
                q.setParameter(4, codigoUsuario);
                q.setParameter(5, solicitudCompra);
            }
            if (estadoSolicitud == 'O') {
                q.setParameter(2, cargoAprueba(codigoUsuario));
                q.setParameter(3, observacion);
                q.setParameter(4, codigoUsuario);
                q.setParameter(5, solicitudCompra);
            }
            if (estadoSolicitud == 'C') {
                q.setParameter(2, cargoAprueba(codigoUsuario));
                q.setParameter(3, observacion);
                q.setParameter(4, codigoUsuario);
                q.setParameter(5, solicitudCompra);
            }
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSolicitudCompra(solicitudCompra) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + solicitudCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private String cargoAprueba(String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT cc.pfs_compcarg_cargo "
                + "FROM componente_cargo cc "
                + "INNER JOIN componente c ON cc.pfi_compcarg_componente = c.pi_componen_id "
                + "WHERE c.s_componen_nombre = 'Aprobar Solicitud Compra - No Análisis' AND "
                + "	cc.pfs_compcarg_cargo IN (SELECT uc.pfs_usuacarg_cargo "
                + "                                   FROM usuario_cargo uc "
                + "                                   WHERE uc.pfs_usuacarg_usuario = ? AND "
                + "                               uc.s_usuacarg_estado = 'V') "
                + "LIMIT 1 ;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, codigoUsuario);
            String resu = (String) q.getSingleResult();
            return resu;
        } finally {
            em.close();
        }
    }

    public void aprobar(Integer solicitudCompra, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'A',"
                + "	fs_solicomp_usuaapru = ? ,"
                + "	fs_solicomp_cargapru = ? ,"
                + "	d_solicomp_aprobacion = NOW() ,"
                + "	s_solicomp_aprobado = '1' ,"
                + "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoAprueba(codigoUsuario));
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, solicitudCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSolicitudCompra(solicitudCompra) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + solicitudCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void asignarColaborador(Integer solicitudCompra, String colaborador, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET fs_solicomp_colaborador = ?,"
                + "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, colaborador);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, solicitudCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findSolicitudCompra(solicitudCompra) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + solicitudCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> proveedoresSolicitud(Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        List<Proveedor> resultado = null;
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("SELECT p.* "
                    + "FROM solicitud_compra s "
                    + "LEFT JOIN cotizacion c ON c.pfi_cotizaci_solicomp = s.pi_solicomp_consecutivo "
                    + "LEFT JOIN proveedor p ON p.ps_proveedor_nit = c.pfs_cotizaci_proveedor "
                    + "WHERE s.pi_solicomp_consecutivo = ?; ", Proveedor.class);

            q.setParameter(1, solicitudCompra);
            resultado = q.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Proveedor> proveedoresSeleccioandosSolicitud(Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        List<Proveedor> resultado = null;
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("SELECT DISTINCT p.* "
                    + "FROM detalle_solicitud_compra d "
                    + "LEFT OUTER JOIN proveedor p ON p.ps_proveedor_nit = d.fs_detsolcom_provsele "
                    + "WHERE d.pfi_detsolcom_solicomp = ? ; ", Proveedor.class);

            q.setParameter(1, solicitudCompra);
            resultado = q.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Map<String, List<Integer>> guardarParametrosAnalisis(Integer solicitudCompra, List<DetalleSolicitudCompra> detalles,
            HashMap<String, ServicioInterno> serviciosInternos, String observacionesGenerales, String usuario) throws Exception {

        Map<String, List<Integer>> retorno = new HashMap<String, List<Integer>>();
        List<Integer> consecutivosServiciosInternos = new LinkedList<Integer>();
        int cantidadDetallseSolicitudCompra = 0, registros;
        EntityManager em = getEntityManager();
        DetalleSolicitudCompraJpaController detalleSolicitudCompraJpaController
                = new DetalleSolicitudCompraJpaController(emf);
        ServicioInternoJpaController internoJpaController = new ServicioInternoJpaController(emf);
        Integer consecutivo = 0;
        Long item;

        String sqlInsertaDetalle
                = "INSERT INTO detalle_solicitud_compra "
                + "(pfi_detsolcom_solicomp,	pi_detsolcom_item,     s_detsolcom_especific,"
                + "i_detsolcom_cantidad,	db_detsolcom_valounit, fs_detsolcom_provsele,"
                + "s_detsolcom_obsesele,         fs_detsolcom_usuacrea,  d_detsolcom_creacion)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              NOW()) ; ";

        String sqlInsertaServicio = "INSERT INTO servicio_interno "
                + "(pi_servinte_consecutivo,          d_servinte_fecha,               i_servinte_proyecto, "
                + "fs_servinte_cliente,		fs_servinte_usuasoli,           fs_servinte_departamento, "
                + "fs_servinte_ingeproy,		s_servinte_estado,              s_servinte_observaciones, "
                + "fs_servinte_cooracep,		s_servinte_recibido,            fs_servinte_usuacrea, "
                + "d_servinte_creacion,		fs_servinte_usuultmod,		d_servinte_ultimodi, fs_servinte_tipodocumento, fs_servinte_letraprocedimiento, fs_servinte_consecutivo, fs_servinte_version) "
                + "VALUES "
                + "(?,		?,			?, "
                + "?,			?,			?, "
                + "?,			?,			?, "
                + "?,			?,			?, "
                + "NOW(),		NULL,		NULL, ?, ?, ?, ?);";

        String sqlInsertaCliente = "INSERT INTO cliente "
                + "(ps_cliente_nit,           s_cliente_nombre,               fs_cliente_usuacrea, "
                + "d_cliente_creacion,        fs_cliente_usuultmod,           d_cliente_ultimodi) "
                + "VALUES "
                + "(?,		?,		?, "
                + "NOW(),		NULL,		NULL);";

        String sqlInsertaDetalleServicio
                = "INSERT INTO detalle_servicio_interno "
                + "(pfi_detserint_servinte,	pi_detserint_item,     s_detserint_concepto,"
                + "db_detserint_cantidad,         d_detserint_fechrequ,"
                + "s_detserint_observaciones,   fs_detserint_usuacrea,      d_detserint_creacion)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?, "
                + "?,                       ?,                              NOW()) ; ";

        String sqlConsecutivoDetalleSol = "SELECT ifnull(max(ds.pi_detsolcom_item) + 1, 1) AS proximoCons "
                + "FROM detalle_solicitud_compra ds "
                + "WHERE ds.pfi_detsolcom_solicomp = ? ; ";

        String sqlConsecutivoServicioInterno = "SELECT ifnull(max(si.pi_servinte_consecutivo) + 1, "
                + "	EXTRACT(YEAR FROM NOW()) * 10000 + 1) AS proximoCons "
                + "FROM servicio_interno si "
                + "WHERE si.pi_servinte_consecutivo > EXTRACT(YEAR FROM NOW()) * 10000 AND "
                + "	si.pi_servinte_consecutivo < (EXTRACT(YEAR FROM NOW()) + 1)*10000; ";

        String sqlConsecutivoDetalleServicio = "SELECT ifnull(max(dsi.pi_detserint_item), 0) + 1 "
                + "FROM detalle_servicio_interno dsi "
                + "WHERE dsi.pfi_detserint_servinte = ? ; ";

        String sqlActualizaSolicitud = "UPDATE solicitud_compra "
                + "SET s_solicomp_observaciones = CONCAT(s_solicomp_observaciones, ?), "
                + "	fs_solicomp_usuultmod = ?, "
                + "    d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";

        try {
            em.getTransaction().begin();
            Query q;

            for (DetalleSolicitudCompra detalle : detalles) {
                q = em.createNativeQuery(sqlConsecutivoDetalleSol);
                q.setParameter(1, detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
                consecutivo = ((Long) q.getSingleResult()).intValue();

                q = em.createNativeQuery(sqlInsertaDetalle);
                q.setParameter(1, detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
                q.setParameter(2, consecutivo);
                q.setParameter(3, detalle.getSDetsolcomEspecific());
                q.setParameter(4, detalle.getIDetsolcomCantidad());
                q.setParameter(5, detalle.getDbDetsolcomValounit());
                q.setParameter(6, detalle.getFsDetsolcomProvsele().getPsProveedorNit());
                q.setParameter(7, detalle.getSDetsolcomObsesele());
                q.setParameter(8, usuario);
                q.executeUpdate();
                cantidadDetallseSolicitudCompra++;
            }
            List<Integer> detallesSolicitud = new LinkedList<Integer>();
            detallesSolicitud.add(cantidadDetallseSolicitudCompra);

            retorno.put("detalles", detallesSolicitud);

            if (cantidadDetallseSolicitudCompra > 0) {
                q = em.createNativeQuery(sqlActualizaSolicitud);
                q.setParameter(1, observacionesGenerales);
                q.setParameter(2, usuario);
                q.setParameter(3, solicitudCompra);
                q.executeUpdate();
            }

            for (Map.Entry<String, ServicioInterno> entrySet : serviciosInternos.entrySet()) {
                String coordinador = entrySet.getKey();
                ServicioInterno servicio = entrySet.getValue();
                q = em.createNativeQuery(sqlConsecutivoServicioInterno);
                consecutivo = ((Long) q.getSingleResult()).intValue();
                Query clienteExiste = em.createNativeQuery("SELECT COUNT(1) "
                        + "FROM cliente c "
                        + "WHERE c.ps_cliente_nit = ? ; ");

                clienteExiste.setParameter(1, servicio.getFsServinteCliente().getClientePK().getPsClienteNit());
                registros = ((Long) clienteExiste.getSingleResult()).intValue();

                if (registros == 0) {
                    Query ic = em.createNativeQuery(sqlInsertaCliente);
                    ic.setParameter(1, servicio.getFsServinteCliente().getClientePK().getPsClienteNit());
                    ic.setParameter(2, servicio.getFsServinteCliente().getSClienteNombre().toUpperCase());
                    ic.setParameter(3, servicio.getFsServinteUsuacrea().getPsUsuarioCodigo());
                    ic.executeUpdate();
                }

                q = em.createNativeQuery(sqlInsertaServicio);
                q.setParameter(1, consecutivo);
                q.setParameter(2, servicio.getDServinteFecha());
                q.setParameter(3, servicio.getIServinteProyecto());
                q.setParameter(4, servicio.getFsServinteCliente().getClientePK().getPsClienteNit());
                q.setParameter(5, servicio.getFsServinteUsuasoli().getPsUsuarioCodigo());
                q.setParameter(6, servicio.getFsServinteDepartamento().getPsDepartamCodigo());
                q.setParameter(7, servicio.getFsServinteIngeproy().getPsUsuarioCodigo());
                q.setParameter(8, servicio.getSServinteEstado());
                q.setParameter(9, observacionesGenerales);
                q.setParameter(10, servicio.getFsServinteCooracep().getPsCargoCodigo());
                q.setParameter(11, servicio.getSServinteRecibido());
                q.setParameter(12, servicio.getFsServinteUsuacrea().getPsUsuarioCodigo());
                q.setParameter(13, servicio.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
                q.setParameter(14, servicio.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
                q.setParameter(15, servicio.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
                q.setParameter(16, servicio.getDocumento().getDocumentoPK().getPsDocumentVersion());
                q.executeUpdate();

                for (DetalleServicioInterno detalleServicioInterno : servicio.getDetalleServicioInternoCollection()) {

                    q = em.createNativeQuery(sqlConsecutivoDetalleServicio);
                    q.setParameter(1, consecutivo);
                    item = (Long) q.getSingleResult();

                    q = em.createNativeQuery(sqlInsertaDetalleServicio);
                    q.setParameter(1, consecutivo);
                    q.setParameter(2, item);
                    q.setParameter(3, detalleServicioInterno.getSDetserintConcepto());
                    q.setParameter(4, detalleServicioInterno.getDbDetserintCantidad());
                    q.setParameter(5, detalleServicioInterno.getDDetserintFechrequ());
                    q.setParameter(6, detalleServicioInterno.getSDetserintObservaciones());
                    q.setParameter(7, usuario);
                    q.executeUpdate();
                }

                consecutivosServiciosInternos.add(consecutivo);
            }
            retorno.put("servicios", consecutivosServiciosInternos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(SolicitudCompraJpaController.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            throw ex;
        }
        return retorno;
    }

    public int detallesSeleccionados(Integer solicitudCompra) throws Exception {
        EntityManager em = getEntityManager();
        int registros = 0;
        try {
            em.getTransaction().begin();
            Query validarRespuestas = em.createNativeQuery("SELECT COUNT(1) "
                    + "FROM detalle_solicitud_compra d "
                    + "WHERE d.pfi_detsolcom_solicomp = ? AND "
                    + "	d.fs_detsolcom_provsele IS NOT NULL ; ");

            validarRespuestas.setParameter(1, solicitudCompra);
            registros = ((Long) validarRespuestas.getSingleResult()).intValue();
            em.getTransaction().commit();
            return registros;
        } catch (Exception ex) {
            throw new Exception("Error al validar respuestas. " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public boolean actualizarDescripcionDetalle(Integer solicitudCompra,
            Integer itemSolicitudCompra, String descripcionDetalleSolicitudCompra, String codigoUsuario) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            Query update = em.createNativeQuery("UPDATE detalle_solicitud_compra "
                    + "SET s_detsolcom_especific = ?, "
                    + "fs_detsolcom_usuultmod = ?, "
                    + "d_detsolcom_ultimodi= NOW() "
                    + "WHERE pfi_detsolcom_solicomp = ? "
                    + "	AND pi_detsolcom_item = ? ; ");

            update.setParameter(1, descripcionDetalleSolicitudCompra);
            update.setParameter(2, codigoUsuario);
            update.setParameter(3, solicitudCompra);
            update.setParameter(4, itemSolicitudCompra);

            update.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List listaSolicitudesEnProceso() {
        EntityManager em = getEntityManager();
        List resultado = null;
        String consulta = "SELECT sc.pi_solicomp_consecutivo, "
                + " 	CASE sc.s_solicomp_tipocomp "
                + "		WHEN 'N' THEN 'N-NO ANÁLISIS' "
                + "		WHEN 'A' THEN 'A-ANÁLISIS' "
                + "	END AS tipoCompra,"
                + "(	SELECT COUNT(1)  "
                + "	FROM detalle_solicitud_compra dsc  "
                + "    WHERE dsc.pfi_detsolcom_solicomp = sc.pi_solicomp_consecutivo) as items, "
                + "CONCAT(sc.fs_solicomp_usuacrea, ' - ', u.s_usuario_nombres, ' ', u.s_usuario_apellidos) as usuacrea, "
                + "sc.d_solicomp_creacion, "
                + "sc.d_solicomp_ultimodi, "
                + "CASE sc.s_solicomp_estado "
                + "	WHEN 'C' THEN 'C-CREADA' "
                + "    WHEN 'R' THEN 'R-RADICADA' "
                + "    WHEN 'V' THEN 'V-VISTO BUENO' "
                + "    WHEN 'A' THEN 'A-APROBADA' "
                + "    WHEN 'P' THEN 'P-PENDIENTE PROVEEDOR' "
                + "    WHEN 'O' THEN 'O-COMPLETA' "
                + "END AS estado, "
                + "(	SELECT GROUP_CONCAT(DISTINCT doc.pfi_detordcom_ordecomp SEPARATOR ', ') "
                + "	FROM detalle_orden_compra doc "
                + "	WHERE doc.pfi_detordcom_solicomp = sc.pi_solicomp_consecutivo "
                + "    )as ordenes_compra "
                + "FROM solicitud_compra sc "
                + "INNER JOIN s_usuario u ON sc.fs_solicomp_usuacrea = u.ps_usuario_codigo "
                + "WHERE sc.s_solicomp_estado NOT IN ('C', 'X', 'T');";
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery(consulta, "resultados");

            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
  
    public void terminarSolicitud(Integer solicitudCompra, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'T',"
                + "	fs_solicomp_usuultmod = ? ,"
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, solicitudCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   
}
