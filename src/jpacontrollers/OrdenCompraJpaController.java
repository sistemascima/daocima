/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DetalleOrdenCompra;
import entidades.DetalleSolicitudCompra;
import entidades.DetalleSolicitudCompraPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Proveedor;
import entidades.OrdenCompra;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class OrdenCompraJpaController implements Serializable {

    public OrdenCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenCompra ordenCompra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ordenCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrdenCompra(ordenCompra.getPiOrdecompNumero()) != null) {
                throw new PreexistingEntityException("OrdenCompra " + ordenCompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenCompra ordenCompra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ordenCompra = em.merge(ordenCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordenCompra.getPiOrdecompNumero();
                if (findOrdenCompra(id) == null) {
                    throw new NonexistentEntityException("The ordenCompra with id " + id + " no longer exists.");
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
            OrdenCompra ordenCompra;
            try {
                ordenCompra = em.getReference(OrdenCompra.class, id);
                ordenCompra.getPiOrdecompNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenCompra with id " + id + " no longer exists.", enfe);
            }
            em.remove(ordenCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenCompra> findOrdenCompraEntities() {
        return findOrdenCompraEntities(true, -1, -1);
    }

    public List<OrdenCompra> findOrdenCompraEntities(int maxResults, int firstResult) {
        return findOrdenCompraEntities(false, maxResults, firstResult);
    }

    private List<OrdenCompra> findOrdenCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenCompra.class));
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

    public OrdenCompra findOrdenCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenCompra> rt = cq.from(OrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Proveedor> proveedoresSeleccionados(String tipoCompra, String codigoUsuario, boolean permiteTodos) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT p.* "
                + "FROM solicitud_compra s "
                + "INNER JOIN detalle_solicitud_compra d ON s.pi_solicomp_consecutivo = d.pfi_detsolcom_solicomp "
                + "INNER JOIN proveedor p ON p.ps_proveedor_nit = d.fs_detsolcom_provsele "
                + "WHERE s.s_solicomp_estado = 'O' AND "
                + "	s.s_solicomp_tipocomp = ? AND "
                + "	CONCAT(d.pfi_detsolcom_solicomp,'-',d.pi_detsolcom_item) NOT IN ( "
                + "			SELECT DISTINCT CONCAT(d1.pfi_detordcom_solicomp,'-',d1.pfi_detordcom_item) "
                + "			FROM detalle_orden_compra d1 "
                + "			INNER JOIN orden_compra o ON o.pi_ordecomp_numero = d1.pfi_detordcom_ordecomp "
                + "			WHERE o.s_ordecomp_estado <> 'X' "
                + "		) AND "
                + "	(s.fs_solicomp_colaborador = ? OR "
                + "	true = ? );";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, tipoCompra);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, permiteTodos);
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public Long calcularConsecutivo() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(oc.pi_ordecomp_numero) + 1, "
                + "	EXTRACT(YEAR FROM NOW()) * 10000 + 1) AS proximoCons "
                + "FROM orden_compra oc "
                + "WHERE oc.pi_ordecomp_numero > EXTRACT(YEAR FROM NOW()) * 10000 AND "
                + "	oc.pi_ordecomp_numero < (EXTRACT(YEAR FROM NOW()) + 1)*10000; ";
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

    public OrdenCompra crearOrdenCompra(OrdenCompra nueva)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        Integer consecutivoAsignado = 0;
        if(nueva.getFsOrdecompEmpresa().getPiEmpresaId()==1){
            String sqlInserta = "INSERT INTO orden_compra "
                    + "(pi_ordecomp_numero,	fs_ordecomp_proveedor,	s_ordecomp_estado, "
                    + "s_ordecomp_tipocomp,	s_ordecomp_servicio,	s_ordecomp_suministro, "
                    + "s_ordecomp_equipo,		db_ordecomp_descuento,	db_ordecomp_subtotal, "
                    + "db_ordecomp_total,		s_ordecomp_condcome,	s_ordecomp_revisado, "
                    + "fs_ordecomp_usuarevi,	fs_ordecomp_cargrevi,	d_ordecomp_revision, "
                    + "s_ordecomp_aprobado,	fs_ordecomp_usuaapru,	fs_ordecomp_cargapru, "
                    + "d_ordecomp_aprobacion,	fs_ordecomp_usuacrea,	d_ordecomp_creacion, "
                    + "fs_ordecomp_usuultmod,	d_ordecomp_ultimodi, d_ordecomp_recepcion, "
                    + " fs_ordecomp_tipodocumento, fs_ordecomp_letraprocedimiento, "
                    + " fs_ordecomp_consecutivo, fs_ordecomp_version, fs_ordecomp_empresa) "
                    + "VALUES "
                    + "(?,		?,			?, "
                    + "?,		?,			?, "
                    + "?,		?,			?, "
                    + "?,		?,			null, "
                    + "null,	null,		null, "
                    + "null,	null,		null, "
                    + "null,	?,			NOW(), "
                    + "null,	null, ?, ?, ?, ?, ?, ?);";
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                consecutivoAsignado = calcularConsecutivo().intValue();
                Query q = em.createNativeQuery(sqlInserta);
                q.setParameter(1, consecutivoAsignado);
                q.setParameter(2, nueva.getFsOrdecompProveedor().getPsProveedorNit());
                q.setParameter(3, nueva.getSOrdecompEstado());
                q.setParameter(4, nueva.getSOrdecompTipocomp());
                q.setParameter(5, nueva.getSOrdecompServicio());
                q.setParameter(6, nueva.getSOrdecompSuministro());
                q.setParameter(7, nueva.getSOrdecompEquipo());
                q.setParameter(8, nueva.getDbOrdecompDescuento());
                q.setParameter(9, nueva.getDbOrdecompSubtotal());
                q.setParameter(10, nueva.getDbOrdecompTotal());
                q.setParameter(11, nueva.getSOrdecompCondcome());
                q.setParameter(12, nueva.getFsOrdecompUsuacrea().getPsUsuarioCodigo());
                q.setParameter(13, nueva.getdOrdecompRecepcion());
                q.setParameter(14, nueva.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
                q.setParameter(15, nueva.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
                q.setParameter(16, nueva.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
                q.setParameter(17, nueva.getDocumento().getDocumentoPK().getPsDocumentVersion());
                q.setParameter(18, nueva.getFsOrdecompEmpresa().getPiEmpresaId());
                q.executeUpdate();
                em.getTransaction().commit();
                return findOrdenCompra(consecutivoAsignado);
            } catch (Exception ex) {
                if (findOrdenCompra(consecutivoAsignado) != null) {
                    throw new PreexistingEntityException("Orden de Compra No. " + consecutivoAsignado + " ya existe.", ex);
                }
                throw ex;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        else{
             String sqlInserta = "INSERT INTO orden_compra "
                    + "(pi_ordecomp_numero,	fs_ordecomp_proveedor,	s_ordecomp_estado, "
                    + "s_ordecomp_tipocomp,	s_ordecomp_servicio,	s_ordecomp_suministro, "
                    + "s_ordecomp_equipo,		db_ordecomp_descuento,	db_ordecomp_subtotal, "
                    + "db_ordecomp_total,		s_ordecomp_condcome,	s_ordecomp_revisado, "
                    + "fs_ordecomp_usuarevi,	fs_ordecomp_cargrevi,	d_ordecomp_revision, "
                    + "s_ordecomp_aprobado,	fs_ordecomp_usuaapru,	fs_ordecomp_cargapru, "
                    + "d_ordecomp_aprobacion,	fs_ordecomp_usuacrea,	d_ordecomp_creacion, "
                    + "fs_ordecomp_usuultmod,	d_ordecomp_ultimodi, d_ordecomp_recepcion, "
                    + "fs_ordecomp_empresa) "
                    + "VALUES "
                    + "(?,		?,			?, "
                    + "?,		?,			?, "
                    + "?,		?,			?, "
                    + "?,		?,			null, "
                    + "null,	null,		null, "
                    + "null,	null,		null, "
                    + "null,	?,			NOW(), "
                    + "null,	null, ?, ?);";
              try {
                em = getEntityManager();
                em.getTransaction().begin();
                consecutivoAsignado = calcularConsecutivo().intValue();
                Query q = em.createNativeQuery(sqlInserta);
                q.setParameter(1, consecutivoAsignado);
                q.setParameter(2, nueva.getFsOrdecompProveedor().getPsProveedorNit());
                q.setParameter(3, nueva.getSOrdecompEstado());
                q.setParameter(4, nueva.getSOrdecompTipocomp());
                q.setParameter(5, nueva.getSOrdecompServicio());
                q.setParameter(6, nueva.getSOrdecompSuministro());
                q.setParameter(7, nueva.getSOrdecompEquipo());
                q.setParameter(8, nueva.getDbOrdecompDescuento());
                q.setParameter(9, nueva.getDbOrdecompSubtotal());
                q.setParameter(10, nueva.getDbOrdecompTotal());
                q.setParameter(11, nueva.getSOrdecompCondcome());
                q.setParameter(12, nueva.getFsOrdecompUsuacrea().getPsUsuarioCodigo());
                q.setParameter(13, nueva.getdOrdecompRecepcion());
                q.setParameter(14, nueva.getFsOrdecompEmpresa().getPiEmpresaId());
                q.executeUpdate();
                em.getTransaction().commit();
                return findOrdenCompra(consecutivoAsignado);
            } catch (Exception ex) {
                if (findOrdenCompra(consecutivoAsignado) != null) {
                    throw new PreexistingEntityException("Orden de Compra No. " + consecutivoAsignado + " ya existe.", ex);
                }
                throw ex;
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
        
    }

    public List<OrdenCompra> consultaOrdenes(String filtros, Queue<Object> argumentos) {
        //System.out.println("query"+filtros);
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT o.* FROM orden_compra o";
        consulta += filtros + ";";
        System.out.println("consulta completa" + consulta);
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, OrdenCompra.class);
            if (argumentos != null) {
                while (!argumentos.isEmpty()) {
                    System.out.println("" + argumentos);
                    q.setParameter(i, argumentos.remove());
                    i++;
                }
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void terminar(Integer ordenCompra, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'T',"
                + "	fs_ordecomp_usuultmod = ? ,"
                + "	d_ordecomp_ultimodi = NOW() "
                + " WHERE pi_ordecomp_numero = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, ordenCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findOrdenCompra(ordenCompra) == null) {
                    throw new NonexistentEntityException("La Orden de Compra " + ordenCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private String cargoAccion(String accion, String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT CASE WHEN cc.pfs_compcarg_cargo = 'TODOS' THEN  "
                + "								(SELECT uc2.pfs_usuacarg_cargo  "
                + "								FROM usuario_cargo uc2  "
                + "								WHERE uc2.pfs_usuacarg_usuario = ? AND  "
                + "									uc2.s_usuacarg_estado = 'V' AND uc2.pfs_usuacarg_cargo <> 'ADMISIST' "
                + "								LIMIT 1) "
                + "ELSE cc.pfs_compcarg_cargo "
                + "END "
                + "FROM componente_cargo cc  "
                + "INNER JOIN componente c ON cc.pfi_compcarg_componente = c.pi_componen_id  "
                + "WHERE c.s_componen_nombre = ? AND  "
                + "	(cc.pfs_compcarg_cargo IN (SELECT uc.pfs_usuacarg_cargo  "
                + "								   FROM usuario_cargo uc  "
                + "								   WHERE uc.pfs_usuacarg_usuario = ? AND  "
                + "								   uc.s_usuacarg_estado = 'V') OR "
                + "	cc.pfs_compcarg_cargo = 'TODOS') "
                + "LIMIT 1 ;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, accion);
            q.setParameter(3, codigoUsuario);
            String resu = (String) q.getSingleResult();
            return resu;
        } finally {
            em.close();
        }
    }

    public void revisar(Integer ordenCompra, String codigoUsuario, String tipoCompraDesc) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'R',"
                + "	s_ordecomp_revisado = '1' ,"
                + "	fs_ordecomp_usuarevi = ? ,"
                + "	fs_ordecomp_cargrevi = ? ,"
                + "	d_ordecomp_revision = NOW() ,"
                + "	fs_ordecomp_usuultmod = ? ,"
                + "	d_ordecomp_ultimodi = NOW() "
                + "WHERE pi_ordecomp_numero = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoAccion("Revisar Orden Compra - " + tipoCompraDesc, codigoUsuario));
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, ordenCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findOrdenCompra(ordenCompra) == null) {
                    throw new NonexistentEntityException("La Orden de Compra " + ordenCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void rechazar(Integer ordenCompra, char estadoOrden, String codigoUsuario, String tipoCompraDesc) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoOrden
                = "UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'X',";
        if (estadoOrden == 'T') {
            sqlActualizaEstadoOrden += "	fs_ordecomp_usuarevi = ? ,"
                    + "	fs_ordecomp_cargrevi = ? ,"
                    + "	d_ordecomp_revision = NOW() ,"
                    + "	s_ordecomp_revisado = '0' ,";
        }
        if (estadoOrden == 'R') {
            sqlActualizaEstadoOrden += "	fs_ordecomp_usuaapru = ? ,"
                    + "	fs_ordecomp_cargapru = ? ,"
                    + "	d_ordecomp_aprobacion = NOW() ,"
                    + "	s_ordecomp_aprobado = '0' ,";
        }
        sqlActualizaEstadoOrden += "	fs_ordecomp_usuultmod = ? ,"
                + "	d_ordecomp_ultimodi = NOW() "
                + "WHERE pi_ordecomp_numero = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoOrden);
            q.setParameter(1, codigoUsuario);
            if (estadoOrden == 'T') {
                q.setParameter(2, cargoAccion("Revisar Orden Compra - " + tipoCompraDesc, codigoUsuario));
            }
            if (estadoOrden == 'R') {
                q.setParameter(2, cargoAccion("Aprobar Orden Compra - " + tipoCompraDesc, codigoUsuario));
            }
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, ordenCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findOrdenCompra(ordenCompra) == null) {
                    throw new NonexistentEntityException("La Orden de Compra " + ordenCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void aprobar(Integer ordenCompra, String codigoUsuario, String tipoCompraDesc) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'A',"
                + "	fs_ordecomp_usuaapru = ? ,"
                + "	fs_ordecomp_cargapru = ? ,"
                + "	d_ordecomp_aprobacion = NOW() ,"
                + "	s_ordecomp_aprobado = '1' ,"
                + "	fs_ordecomp_usuultmod = ? ,"
                + "	d_ordecomp_ultimodi = NOW() "
                + "WHERE pi_ordecomp_numero = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoAccion("Aprobar Orden Compra - " + tipoCompraDesc, codigoUsuario));
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, ordenCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findOrdenCompra(ordenCompra) == null) {
                    throw new NonexistentEntityException("La Orden de Compra " + ordenCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void rechazar(Integer ordenCompra, String codigoUsuario, String tipoCompraDesc) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'X',"
                + "	fs_ordecomp_usuaapru = ? ,"
                + "	fs_ordecomp_cargapru = ? ,"
                + "	d_ordecomp_aprobacion = NOW() ,"
                + "	s_ordecomp_aprobado = '1' ,"
                + "	fs_ordecomp_usuultmod = ? ,"
                + "	d_ordecomp_ultimodi = NOW() "
                + "WHERE pi_ordecomp_numero = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoAccion("Aprobar Orden Compra - " + tipoCompraDesc, codigoUsuario));
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, ordenCompra);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findOrdenCompra(ordenCompra) == null) {
                    throw new NonexistentEntityException("La Orden de Compra " + ordenCompra + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenCompra> consultaFechaRecepcion() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM orden_compra WHERE "
                + "d_ordecomp_recepcion<=now() AND s_ordecomp_estado='A';";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta,OrdenCompra.class);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Integer> consultaSolicitudPorOrden(OrdenCompra ordenCompra) {

        EntityManager em = getEntityManager();

        String consulta = "SELECT DISTINCT detalle_orden_compra.pfi_detordcom_solicomp "
                + "FROM detalle_orden_compra join detalle_solicitud_compra "
                + "on detalle_solicitud_compra.pfi_detsolcom_solicomp = detalle_orden_compra.pfi_detordcom_solicomp "
                + "join solicitud_compra on solicitud_compra.pi_solicomp_consecutivo = detalle_solicitud_compra.pfi_detsolcom_solicomp "
                + "where pfi_detordcom_ordecomp=?";

        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, ordenCompra.getPiOrdecompNumero());
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }

    }

    public List<DetalleSolicitudCompra> solicitudesPendientesOC(char tipoCompra, String codigoProveedor, String codigoUsuario,
            boolean permitirTodos) {

        System.out.println("tipocompra: " + tipoCompra + " codigoproveedor: " + codigoProveedor + " codigoUsuario" + codigoUsuario + " permitirTodos" + permitirTodos);
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT d.* "
                + "FROM detalle_solicitud_compra d "
                + "INNER JOIN solicitud_compra s ON s.pi_solicomp_consecutivo = d.pfi_detsolcom_solicomp "
                + "WHERE s.s_solicomp_estado = 'O' AND "
                + "	s.s_solicomp_tipocomp = ? AND "
                + "	CONCAT(d.pfi_detsolcom_solicomp,'-',d.pi_detsolcom_item) NOT IN ( "
                + "			SELECT DISTINCT CONCAT(d1.pfi_detordcom_solicomp,'-',d1.pfi_detordcom_item) "
                + "			FROM detalle_orden_compra d1 "
                + "			INNER JOIN orden_compra o ON o.pi_ordecomp_numero = d1.pfi_detordcom_ordecomp "
                + "			WHERE o.s_ordecomp_estado <> 'X' "
                + "		) AND "
                + "	d.fs_detsolcom_provsele = ? AND "
                + "	(s.fs_solicomp_colaborador = ? OR true = ? );";
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta, DetalleSolicitudCompra.class);
            q.setParameter(1, tipoCompra);
            q.setParameter(2, codigoProveedor);
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, permitirTodos);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarOrdenCompra(OrdenCompra orden)
            throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE orden_compra "
                    + "SET s_ordecomp_servicio = ?, "
                    + "	s_ordecomp_suministro = ?, "
                    + "	s_ordecomp_equipo = ?, "
                    + "	db_ordecomp_descuento = ?, "
                    + "	db_ordecomp_subtotal = ?, "
                    + "	db_ordecomp_total = ?, "
                    + "	s_ordecomp_condcome = ?, "
                    + "	fs_ordecomp_usuultmod = ?, "
                    + "	d_ordecomp_ultimodi = NOW(), "
                    + "	d_ordecomp_recepcion = ? "
                    + "WHERE pi_ordecomp_numero = ? ; ");

            pa.setParameter(1, orden.getSOrdecompServicio());
            pa.setParameter(2, orden.getSOrdecompSuministro());
            pa.setParameter(3, orden.getSOrdecompEquipo());
            pa.setParameter(4, orden.getDbOrdecompDescuento());
            pa.setParameter(5, orden.getDbOrdecompSubtotal());
            pa.setParameter(6, orden.getDbOrdecompTotal());
            pa.setParameter(7, orden.getSOrdecompCondcome());
            pa.setParameter(8, orden.getFsOrdecompUsuultmod().getPsUsuarioCodigo());
            pa.setParameter(9, orden.getdOrdecompRecepcion());
            pa.setParameter(10, orden.getPiOrdecompNumero());
            pa.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public void actualizarEstadoOrdenCompra(OrdenCompra orden)
            throws Exception {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE orden_compra "
                    + "SET s_ordecomp_estado = ? "
                    + "WHERE pi_ordecomp_numero = ? ; ");
            pa.setParameter(1, orden.getSOrdecompEstado());
            pa.setParameter(2, orden.getPiOrdecompNumero());
            pa.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Proveedor> proveedoresPendientesRecepcion(char tipoCompra, String codigoUsuario) {
        System.out.println("tipo compra"+ tipoCompra);
        System.out.println("codigo usuario"+ codigoUsuario);
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT p.* "
                + "FROM orden_compra o "
                + "INNER JOIN detalle_orden_compra d ON o.pi_ordecomp_numero = d.pfi_detordcom_ordecomp "
                + "INNER JOIN detalle_solicitud_compra d2 ON d2.pfi_detsolcom_solicomp = d.pfi_detordcom_solicomp AND "
                + "d2.pi_detsolcom_item = d.pfi_detordcom_item "
                + "INNER JOIN solicitud_compra s ON s.pi_solicomp_consecutivo = d2.pfi_detsolcom_solicomp "
                + "INNER JOIN proveedor p ON p.ps_proveedor_nit = o.fs_ordecomp_proveedor "
                + "WHERE (d2.s_detsolcom_recibido IS NULL OR "
                + "	s_detsolcom_recibido in ('A', 'R')) AND "
                + "	o.s_ordecomp_tipocomp = ? AND "
                + "	(s.fs_solicomp_usuasoli = ? OR "
                + "	s.fs_solicomp_usuvisbue = ? );";
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, tipoCompra);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, codigoUsuario);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public List<Proveedor> proveedoresPendientesCompras(char tipoCompra, String codigoUsuario) {
        System.out.println("tipo compra"+ tipoCompra);
        System.out.println("codigo usuario"+ codigoUsuario);
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT p.* "
                + "FROM orden_compra o "
                + "INNER JOIN detalle_orden_compra d ON o.pi_ordecomp_numero = d.pfi_detordcom_ordecomp "
                + "INNER JOIN detalle_solicitud_compra d2 ON d2.pfi_detsolcom_solicomp = d.pfi_detordcom_solicomp AND "
                + "d2.pi_detsolcom_item = d.pfi_detordcom_item "
                + "INNER JOIN solicitud_compra s ON s.pi_solicomp_consecutivo = d2.pfi_detsolcom_solicomp "
                + "INNER JOIN proveedor p ON p.ps_proveedor_nit = o.fs_ordecomp_proveedor "
                + "WHERE (d2.s_detsolcom_recibido IS NULL OR "
                + "	s_detsolcom_recibido in ('A', 'R')) AND "
                + "	o.s_ordecomp_tipocomp = ? ;";
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, tipoCompra);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object> productosPendientesVerificacion(char tipoCompra, String proveedor, String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = " SELECT DISTINCT d.pfi_detordcom_ordecomp, d.pfi_detordcom_solicomp, "
                + " d.pfi_detordcom_item, d2.s_detsolcom_especific,"
                + " d.i_detordcom_cantidad,d2.s_detsolcom_recibido, "
                + " d2.s_detsolcom_obsesele, (i_detordcom_cantidad- i_detorcom_cantidad_recibida) "
                + " FROM orden_compra o "
                + " INNER JOIN detalle_orden_compra d ON o.pi_ordecomp_numero = d.pfi_detordcom_ordecomp "
                + " INNER JOIN detalle_solicitud_compra d2 ON d2.pfi_detsolcom_solicomp = d.pfi_detordcom_solicomp AND "
                + " d2.pi_detsolcom_item = d.pfi_detordcom_item  "
                + " INNER JOIN solicitud_compra s ON s.pi_solicomp_consecutivo = d2.pfi_detsolcom_solicomp "
                + " INNER JOIN proveedor p ON p.ps_proveedor_nit = o.fs_ordecomp_proveedor " 
                + " WHERE (d2.s_detsolcom_recibido IS NULL OR "
                + " s_detsolcom_recibido in ('A', 'R')) AND "
                + " o.s_ordecomp_tipocomp = ? AND "
                + " o.fs_ordecomp_proveedor = ? AND"
                + " (s.fs_solicomp_usuasoli = ? OR "
                + " s.fs_solicomp_usuvisbue = ? )";	
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, tipoCompra);
            q.setParameter(2, proveedor);
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, codigoUsuario);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Object> productosPendientesCompras(char tipoCompra, String proveedor) {
        EntityManager em = getEntityManager();
        String consulta = " SELECT DISTINCT d.pfi_detordcom_ordecomp, d.pfi_detordcom_solicomp, "
                + " d.pfi_detordcom_item, d2.s_detsolcom_especific,"
                + " d.i_detordcom_cantidad,d2.s_detsolcom_recibido, "
                + " d2.s_detsolcom_obsesele, (i_detordcom_cantidad- i_detorcom_cantidad_recibida) "
                + " FROM orden_compra o "
                + " INNER JOIN detalle_orden_compra d ON o.pi_ordecomp_numero = d.pfi_detordcom_ordecomp "
                + " INNER JOIN detalle_solicitud_compra d2 ON d2.pfi_detsolcom_solicomp = d.pfi_detordcom_solicomp AND "
                + " d2.pi_detsolcom_item = d.pfi_detordcom_item  "
                + " INNER JOIN solicitud_compra s ON s.pi_solicomp_consecutivo = d2.pfi_detsolcom_solicomp "
                + " INNER JOIN proveedor p ON p.ps_proveedor_nit = o.fs_ordecomp_proveedor " 
                + " WHERE (d2.s_detsolcom_recibido IS NULL OR "
                + " s_detsolcom_recibido in ('A', 'R')) AND "
                + " o.s_ordecomp_tipocomp = ? AND "
                + " o.fs_ordecomp_proveedor = ? ;";	
        try {

            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, tipoCompra);
            q.setParameter(2, proveedor);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Long calcularConsecutivoEvaluacion(String tipoEvaluacion) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT IFNULL(MAX(e.pi_evalprov_numeeval), 0) + 1 "
                + "FROM evaluacion_proveedor e "
                + "WHERE e.pfs_evalprov_tipo = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, tipoEvaluacion);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Long(0);
        } finally {
            em.close();
        }
    }

    public boolean guardarRecepcionProductos(char tipoCompra, String tipoCompraDesc,
            char recibido, String observaciones,
            List<Integer> solicitudesRecibir, List<Integer> ordenesRecibir,
            List<DetalleSolicitudCompraPK> detallesRecibir, String codigoUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query updateDetalleSolicitudCompra = em.createNativeQuery("UPDATE detalle_solicitud_compra "
                    + "SET fs_detsolcom_usuareci = ?, "
                    + "fs_detsolcom_cargreci = ?, "
                    + "d_detsolcom_recepcion = NOW(), "
                    + "s_detsolcom_recibido = ?, "
                    + "s_detsolcom_obsereci = ?, "
                    + "fs_detsolcom_usuultmod = ?, "
                    + "d_detsolcom_ultimodi = NOW() "
                    + "WHERE pfi_detsolcom_solicomp = ? AND pi_detsolcom_item = ? ; ");
            String cargo = cargoAccion("Recepción de Productos - " + tipoCompraDesc, codigoUsuario);
            for (DetalleSolicitudCompraPK detalle : detallesRecibir) {
                updateDetalleSolicitudCompra.setParameter(1, codigoUsuario);
                updateDetalleSolicitudCompra.setParameter(2, cargo);
                updateDetalleSolicitudCompra.setParameter(3, recibido);
                updateDetalleSolicitudCompra.setParameter(4, observaciones);
                updateDetalleSolicitudCompra.setParameter(5, codigoUsuario);
                updateDetalleSolicitudCompra.setParameter(6, detalle.getPfiDetsolcomSolicomp());
                updateDetalleSolicitudCompra.setParameter(7, detalle.getPiDetsolcomItem());
                updateDetalleSolicitudCompra.executeUpdate();
            }

            if (recibido == 'V') {
                Query updateSolicitudCompra = em.createNativeQuery("UPDATE solicitud_compra "
                        + "SET s_solicomp_estado = 'T', "
                        + "	fs_solicomp_usuultmod = ?, "
                        + "    d_solicomp_ultimodi = NOW() "
                        + "WHERE pi_solicomp_consecutivo = ? AND "
                        + "	pi_solicomp_consecutivo NOT IN "
                        + "		( "
                        + "			SELECT DISTINCT d.pfi_detsolcom_solicomp "
                        + "			FROM detalle_solicitud_compra d "
                        + "			WHERE d.pfi_detsolcom_solicomp = ? AND "
                        + "				(d.s_detsolcom_recibido IS NULL OR d.s_detsolcom_recibido in ('A', 'R')) "
                        + "        ); ");

                Query updateOrdenCompra = em.createNativeQuery("UPDATE orden_compra "
                        + "SET s_ordecomp_estado = 'B', "
                        + "	fs_ordecomp_usuultmod = ?, "
                        + "    d_ordecomp_ultimodi = NOW() "
                        + "WHERE pi_ordecomp_numero = ? AND "
                        + "	pi_ordecomp_numero NOT IN  "
                        + "		( "
                        + "			SELECT DISTINCT d1.pfi_detordcom_ordecomp "
                        + "			FROM detalle_orden_compra d1 "
                        + "			INNER JOIN detalle_solicitud_compra d ON d1.pfi_detordcom_solicomp = d.pfi_detsolcom_solicomp AND "
                        + "						d1.pfi_detordcom_item = d.pi_detsolcom_item "
                        + "			WHERE d1.pfi_detordcom_ordecomp = ? AND "
                        + "				(d.s_detsolcom_recibido IS NULL OR d.s_detsolcom_recibido in ('A', 'R')) "
                        + "        ); ");

                if (solicitudesRecibir != null && solicitudesRecibir.size() > 0) {
                    for (Integer solicitud : solicitudesRecibir) {
                        updateSolicitudCompra.setParameter(1, codigoUsuario);
                        updateSolicitudCompra.setParameter(2, solicitud);
                        updateSolicitudCompra.setParameter(3, solicitud);
                        updateSolicitudCompra.executeUpdate();
                    }
                }

                if (ordenesRecibir != null && ordenesRecibir.size() > 0) {
                    for (Integer orden : ordenesRecibir) {
                        updateOrdenCompra.setParameter(1, codigoUsuario);
                        updateOrdenCompra.setParameter(2, orden);
                        updateOrdenCompra.setParameter(3, orden);
                        updateOrdenCompra.executeUpdate();
                    }
                }
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public String destinatariosOrdenCompra(Integer ordenCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT GROUP_CONCAT(DISTINCT u.s_usuario_correo SEPARATOR ',') "
                + "FROM detalle_orden_compra doc "
                + "INNER JOIN solicitud_compra sc ON sc.pi_solicomp_consecutivo = doc.pfi_detordcom_solicomp "
                + "INNER JOIN s_usuario u ON u.ps_usuario_codigo = sc.fs_solicomp_usuasoli "
                + "WHERE doc.pfi_detordcom_ordecomp = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, ordenCompra);
            String resu = (String) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        } finally {
            em.close();
        }
    }

    public String destinatariosRecepcionSinVerificar(List<DetalleSolicitudCompraPK> detallesRecibir, String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT GROUP_CONCAT(DISTINCT u.s_usuario_correo SEPARATOR ',') "
                + "FROM s_usuario u "
                + "WHERE u.ps_usuario_codigo IN (SELECT sc.fs_solicomp_usuasoli "
                + "			FROM solicitud_compra sc "
                + "            WHERE sc.pi_solicomp_consecutivo IN () "
                + "            UNION "
                + "            SELECT sc2.fs_solicomp_usuvisbue "
                + "			FROM solicitud_compra sc2 "
                + "            WHERE sc2.pi_solicomp_consecutivo = IN ()) AND "
                + "	u.ps_usuario_codigo <> ? ; ";

        String solicitudes = "";
        boolean primero = true;
        for (DetalleSolicitudCompraPK detalle : detallesRecibir) {
            if (primero) {
                solicitudes += detalle.getPfiDetsolcomSolicomp();
                primero = false;
            } else {
                solicitudes += "," + detalle.getPfiDetsolcomSolicomp();
            }
        }

        consulta = consulta.replaceAll("IN ()", "IN (" + solicitudes + ")");

        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, codigoUsuario);
            String resu = (String) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            return "";
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarOrdenesCompra(String nitProveedor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery("SELECT count(pi_ordecomp_numero) "
                    + "from orden_compra where fs_ordecomp_proveedor= ? ");
            detalleSolicitud.setParameter(1, nitProveedor);
            return detalleSolicitud.getResultList();
        } finally {
            em.close();
        }

    }

    public void actualizarEstadoDetalleSolicitudCompra(DetalleSolicitudCompraPK detalle, 
            String codigoUsuario, String observaciones, String tipoCompraDesc) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query updateDetalleSolicitudCompra = em.createNativeQuery("UPDATE detalle_solicitud_compra "
                + "SET fs_detsolcom_usuareci = ?, "
                + "fs_detsolcom_cargreci = ?, "
                + "d_detsolcom_recepcion = NOW(), "
                + "s_detsolcom_recibido = 'V', "
                + "s_detsolcom_obsereci = ?, "
                + "fs_detsolcom_usuultmod = ?, "
                + "d_detsolcom_ultimodi = NOW() "
                + "WHERE pfi_detsolcom_solicomp = ? AND pi_detsolcom_item = ? ; ");
        String cargo = cargoAccion("Recepción de Productos - " + tipoCompraDesc, codigoUsuario);
        updateDetalleSolicitudCompra.setParameter(1, codigoUsuario);
        updateDetalleSolicitudCompra.setParameter(2, cargo);
        updateDetalleSolicitudCompra.setParameter(3, observaciones);
        updateDetalleSolicitudCompra.setParameter(4, codigoUsuario);
        updateDetalleSolicitudCompra.setParameter(5, detalle.getPfiDetsolcomSolicomp());
        updateDetalleSolicitudCompra.setParameter(6, detalle.getPiDetsolcomItem());
        updateDetalleSolicitudCompra.executeUpdate();
        em.getTransaction().commit();
    }

    

    public List<Object> encontrarSumaSolicitud(DetalleSolicitudCompraPK solicitudCompra) {
         EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query cantidad = em.createNativeQuery(" select sum(i_detsolcom_cantidad) " +
                     " from detalle_solicitud_compra " +
                     " where pfi_detsolcom_solicomp=? " );
            cantidad.setParameter(1, solicitudCompra.getPfiDetsolcomSolicomp());
            return cantidad.getResultList();
        } finally {
            em.close();
        }
    }

    

    public void recibirOrdenCompra(Integer ordenCompra, String usuario) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query updateOrdenCompra = em.createNativeQuery("UPDATE orden_compra "
                + "SET s_ordecomp_estado = 'B', "
                + "	fs_ordecomp_usuultmod = ?, "
                + "    d_ordecomp_ultimodi = NOW() "
                + "WHERE pi_ordecomp_numero = ?");
        updateOrdenCompra.setParameter(1, usuario);
        updateOrdenCompra.setParameter(2, ordenCompra);
        updateOrdenCompra.executeUpdate();
        em.getTransaction().commit();
        em.close();
       
    }

    public List<Object> encontrarInformeContabilidad(String filtros, List<Object> argumentos) {
        EntityManager em = this.getEntityManager();
        String consulta = " select orden_compra.pi_ordecomp_numero, solicitud_compra.pi_solicomp_consecutivo, detalle_solicitud_compra.s_detsolcom_especific, "
                + "solicitud_compra.fs_solicomp_usuasoli, solicitud_compra.s_solicomp_estado, "
                + "detalle_solicitud_compra.pi_detsolcom_item, detalle_orden_compra.i_detordcom_cantidad, "
                + "detalle_orden_compra.db_detordcom_valounit, orden_compra.db_ordecomp_total, "
                + "orden_compra.d_ordecomp_aprobacion, orden_compra.fs_ordecomp_proveedor, "
                + "proveedor.s_proveedor_nombre, solicitud_compra.s_solicomp_tipocomp, orden_compra.s_ordecomp_estado "
                + " from solicitud_compra "
                + "join detalle_solicitud_compra on detalle_solicitud_compra.pfi_detsolcom_solicomp= solicitud_compra.pi_solicomp_consecutivo "
                + "join detalle_orden_compra on detalle_orden_compra.pfi_detordcom_solicomp = solicitud_compra.pi_solicomp_consecutivo and "
                + "detalle_orden_compra.pfi_detordcom_item= detalle_solicitud_compra.pi_detsolcom_item "
                + "join orden_compra on orden_compra.pi_ordecomp_numero= detalle_orden_compra.pfi_detordcom_ordecomp "
                + "join proveedor on proveedor.ps_proveedor_nit= orden_compra.fs_ordecomp_proveedor ";
        consulta += "where " +filtros;
        System.out.println("filtros "+ filtros);
        try {
            final Query q = em.createNativeQuery(consulta);
            System.out.println("query" + q.toString());
            int j = 1;
            for (int i = 0; i < argumentos.size(); ++i) {
                q.setParameter(j, argumentos.get(i));
                ++j;
            }
            return (List<Object>) q.getResultList();
        } finally {
            em.close();
        }
    }
}
