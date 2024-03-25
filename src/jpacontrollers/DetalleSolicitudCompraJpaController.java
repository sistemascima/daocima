/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Proveedor;
import entidades.Cargo;
import entidades.DetalleOrdenCompra;
import entidades.DetalleSolicitudCompra;
import entidades.DetalleSolicitudCompraPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class DetalleSolicitudCompraJpaController implements Serializable {

    public DetalleSolicitudCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<DetalleSolicitudCompra> findDetalleSolicitudCompraEntities() {
        return findDetalleSolicitudCompraEntities(true, -1, -1);
    }

    public List<DetalleSolicitudCompra> findDetalleSolicitudCompraEntities(int maxResults, int firstResult) {
        return findDetalleSolicitudCompraEntities(false, maxResults, firstResult);
    }

    private List<DetalleSolicitudCompra> findDetalleSolicitudCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleSolicitudCompra.class));
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

    public DetalleSolicitudCompra findDetalleSolicitudCompra(DetalleSolicitudCompraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleSolicitudCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleSolicitudCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleSolicitudCompra> rt = cq.from(DetalleSolicitudCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<DetalleSolicitudCompra> itemsSolicitudCompra(int solicitudCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* FROM detalle_solicitud_compra d WHERE d.pfi_detsolcom_solicomp = ?; ";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, DetalleSolicitudCompra.class);
            q.setParameter(1, solicitudCompra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarDetalleSolicitud(Integer solicitudCompra, Integer item)
            throws Exception {
        EntityManager em = getEntityManager();
        String sqlBorrarItem
                = "DELETE FROM detalle_solicitud_compra WHERE pfi_detsolcom_solicomp = ? AND pi_detsolcom_item = ? ;";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlBorrarItem);
            q.setParameter(1, solicitudCompra);
            q.setParameter(2, item);
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

    public Long calcularConsecutivo(Integer solicitud) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(ds.pi_detsolcom_item) + 1, 1) AS proximoCons "
                + "FROM detalle_solicitud_compra ds "
                + "WHERE ds.pfi_detsolcom_solicomp = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, solicitud);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return new Long(0);
        }
        finally {
            em.close();
        }
    }

    public void insertarDetalle(DetalleSolicitudCompra detalle, String codigoUsuario)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        ProveedorJpaController proveedorJpa = new ProveedorJpaController(emf);
        Integer consecutivo = 0;
        String sqlInsertaDetalle
                = "INSERT INTO detalle_solicitud_compra "
                + "(pfi_detsolcom_solicomp,	pi_detsolcom_item,     s_detsolcom_especific,"
                + "i_detsolcom_cantidad,         fs_detsolcom_usuacrea,  d_detsolcom_creacion)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              NOW()) ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consecutivo = calcularConsecutivo(detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp()).intValue();
            Query q;
            q = em.createNativeQuery(sqlInsertaDetalle);
            q.setParameter(1, detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
            q.setParameter(2, consecutivo);
            q.setParameter(3, detalle.getSDetsolcomEspecific());
            q.setParameter(4, detalle.getIDetsolcomCantidad());
            q.setParameter(5, codigoUsuario);
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleSolicitudCompra(
                    new DetalleSolicitudCompraPK(detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp(),consecutivo)) != null) {
                throw new PreexistingEntityException("Detalle " + detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp() + " - " + consecutivo + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarDetalle(DetalleSolicitudCompra detalle, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud =
                "UPDATE detalle_solicitud_compra "
                + "SET s_detsolcom_especific = ?,"
                + "	i_detsolcom_cantidad = ? ,"
                + "	fs_detsolcom_usuultmod = ? ,"
                + "	d_detsolcom_ultimodi = NOW() "
                + "WHERE pfi_detsolcom_solicomp = ? AND pi_detsolcom_item = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, detalle.getSDetsolcomEspecific());
            q.setParameter(2, detalle.getIDetsolcomCantidad());
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
            q.setParameter(5, detalle.getDetalleSolicitudCompraPK().getPiDetsolcomItem());
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findDetalleSolicitudCompra(new DetalleSolicitudCompraPK(detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp(), 
                        detalle.getDetalleSolicitudCompraPK().getPiDetsolcomItem())) == null) {
                    throw new NonexistentEntityException("El Ítem " + detalle.getDetalleSolicitudCompraPK().getPiDetsolcomItem() + 
                            " de la Solicitud de Compra " + detalle.getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp() + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean detallesIncompletos(Integer solicitud) throws Exception {
        EntityManager em = getEntityManager();
        int registros = 0;
        try {
            em.getTransaction().begin();
            Query validarRespuestas = em.createNativeQuery("SELECT COUNT(1) " +
                    "FROM detalle_solicitud_compra d " +
                    "WHERE d.pfi_detsolcom_solicomp = ? AND " +
                    "	d.i_detsolcom_cantidad <= 0 ; ");
            
            validarRespuestas.setParameter(1, solicitud);
            registros = ((Long)validarRespuestas.getSingleResult()).intValue();
            em.getTransaction().commit();
            return (registros > 0);
        } catch (Exception ex) {
            throw new Exception("Error al validar respuestas. " + ex.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    public List<Object> encontrarDetalleSolicitud(String nitProveedor) {
        EntityManager em = getEntityManager();
         try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery("SELECT count(pfi_detsolcom_solicomp) " +
                                    "from detalle_solicitud_compra where fs_detsolcom_provsele= ? ");
            detalleSolicitud.setParameter(1, nitProveedor);
            return detalleSolicitud.getResultList();
        } 
        finally {
            em.close();
        }
    }

    public List<Object> encontrarEvaluacionesProveedor(String nitProveedor) {
         EntityManager em = getEntityManager();
         try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery("SELECT count(pfs_evalprov_proveedor) " +
                                    "from evaluacion_proveedor where pfs_evalprov_proveedor= ? ");
            detalleSolicitud.setParameter(1, nitProveedor);
            return detalleSolicitud.getResultList();
        } 
        finally {
            em.close();
        }
    
    }
    
    public void guardarRecepcionProductos(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
        Query updateDetalleSolicitudCompra = em.createNativeQuery("UPDATE detalle_solicitud_compra "
                + "SET s_detsolcom_recibido = 'V' "
                + "WHERE pfi_detsolcom_solicomp = ? AND pi_detsolcom_item = ?  ");
        updateDetalleSolicitudCompra.setParameter(1, pfiDetsolcomSolicomp);
        updateDetalleSolicitudCompra.setParameter(2, piDetsolcomItem);
        updateDetalleSolicitudCompra.executeUpdate();
        em.getTransaction().commit();
        }
        finally {    
           em.close();
        }
        }

    

    public int encontrarCantidadTotalItems(int solicitudCompra) {
      EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
        Query query = em.createNativeQuery("select count(pi_detsolcom_item)" 
                +" from detalle_solicitud_compra " 
                +" where pfi_detsolcom_solicomp=?");
        query.setParameter(1, solicitudCompra);
        return (int) Integer.valueOf(query.getSingleResult().toString());
        }
        finally {    
           em.close();
        } 
    }
    
}
