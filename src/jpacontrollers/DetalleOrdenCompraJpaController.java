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
import entidades.DetalleSolicitudCompra;
import entidades.OrdenCompra;
import entidades.SUsuario;
import entidades.Cantidad;
import entidades.DetalleOrdenCompra;
import entidades.DetalleOrdenCompraPK;
import java.math.BigDecimal;
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
public class DetalleOrdenCompraJpaController implements Serializable {

    public DetalleOrdenCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleOrdenCompra detalleOrdenCompra) throws IllegalOrphanException, PreexistingEntityException, Exception {
       /* if (detalleOrdenCompra.getDetalleOrdenCompraPK() == null) {
            detalleOrdenCompra.setDetalleOrdenCompraPK(new DetalleOrdenCompraPK());
        }
        if (detalleOrdenCompra.getCantidadList() == null) {
            detalleOrdenCompra.setCantidadList(new ArrayList<Cantidad>());
        }
        detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomItem(detalleOrdenCompra.getDetalleSolicitudCompra().getDetalleSolicitudCompraPK().getPiDetsolcomItem());
        detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomOrdecomp(detalleOrdenCompra.getOrdenCompra().getPiOrdecompNumero());
        detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomSolicomp(detalleOrdenCompra.getDetalleSolicitudCompra().getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
        List<String> illegalOrphanMessages = null;
        DetalleSolicitudCompra detalleSolicitudCompraOrphanCheck = detalleOrdenCompra.getDetalleSolicitudCompra();
        if (detalleSolicitudCompraOrphanCheck != null) {
            DetalleOrdenCompra oldDetalleOrdenCompraOfDetalleSolicitudCompra = detalleSolicitudCompraOrphanCheck.getDetalleOrdenCompra();
            if (oldDetalleOrdenCompraOfDetalleSolicitudCompra != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The DetalleSolicitudCompra " + detalleSolicitudCompraOrphanCheck + " already has an item of type DetalleOrdenCompra whose detalleSolicitudCompra column cannot be null. Please make another selection for the detalleSolicitudCompra field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleSolicitudCompra detalleSolicitudCompra = detalleOrdenCompra.getDetalleSolicitudCompra();
            if (detalleSolicitudCompra != null) {
                detalleSolicitudCompra = em.getReference(detalleSolicitudCompra.getClass(), detalleSolicitudCompra.getDetalleSolicitudCompraPK());
                detalleOrdenCompra.setDetalleSolicitudCompra(detalleSolicitudCompra);
            }
            OrdenCompra ordenCompra = detalleOrdenCompra.getOrdenCompra();
            if (ordenCompra != null) {
                ordenCompra = em.getReference(ordenCompra.getClass(), ordenCompra.getPiOrdecompNumero());
                detalleOrdenCompra.setOrdenCompra(ordenCompra);
            }
            SUsuario fsDetordcomUsuultmod = detalleOrdenCompra.getFsDetordcomUsuultmod();
            if (fsDetordcomUsuultmod != null) {
                fsDetordcomUsuultmod = em.getReference(fsDetordcomUsuultmod.getClass(), fsDetordcomUsuultmod.getPsUsuarioCodigo());
                detalleOrdenCompra.setFsDetordcomUsuultmod(fsDetordcomUsuultmod);
            }
            SUsuario fsDetordcomUsuacrea = detalleOrdenCompra.getFsDetordcomUsuacrea();
            if (fsDetordcomUsuacrea != null) {
                fsDetordcomUsuacrea = em.getReference(fsDetordcomUsuacrea.getClass(), fsDetordcomUsuacrea.getPsUsuarioCodigo());
                detalleOrdenCompra.setFsDetordcomUsuacrea(fsDetordcomUsuacrea);
            }
            List<Cantidad> attachedCantidadList = new ArrayList<Cantidad>();
            for (Cantidad cantidadListCantidadToAttach : detalleOrdenCompra.getCantidadList()) {
                cantidadListCantidadToAttach = em.getReference(cantidadListCantidadToAttach.getClass(), cantidadListCantidadToAttach.getCantidadPK());
                attachedCantidadList.add(cantidadListCantidadToAttach);
            }
            detalleOrdenCompra.setCantidadList(attachedCantidadList);
            em.persist(detalleOrdenCompra);
            if (detalleSolicitudCompra != null) {
                detalleSolicitudCompra.setDetalleOrdenCompra(detalleOrdenCompra);
                detalleSolicitudCompra = em.merge(detalleSolicitudCompra);
            }
            if (ordenCompra != null) {
                ordenCompra.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                ordenCompra = em.merge(ordenCompra);
            }
            if (fsDetordcomUsuultmod != null) {
                fsDetordcomUsuultmod.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                fsDetordcomUsuultmod = em.merge(fsDetordcomUsuultmod);
            }
            if (fsDetordcomUsuacrea != null) {
                fsDetordcomUsuacrea.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                fsDetordcomUsuacrea = em.merge(fsDetordcomUsuacrea);
            }
            for (Cantidad cantidadListCantidad : detalleOrdenCompra.getCantidadList()) {
                DetalleOrdenCompra oldDetalleOrdenCompraOfCantidadListCantidad = cantidadListCantidad.getDetalleOrdenCompra();
                cantidadListCantidad.setDetalleOrdenCompra(detalleOrdenCompra);
                cantidadListCantidad = em.merge(cantidadListCantidad);
                if (oldDetalleOrdenCompraOfCantidadListCantidad != null) {
                    oldDetalleOrdenCompraOfCantidadListCantidad.getCantidadList().remove(cantidadListCantidad);
                    oldDetalleOrdenCompraOfCantidadListCantidad = em.merge(oldDetalleOrdenCompraOfCantidadListCantidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleOrdenCompra(detalleOrdenCompra.getDetalleOrdenCompraPK()) != null) {
                throw new PreexistingEntityException("DetalleOrdenCompra " + detalleOrdenCompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public void edit(DetalleOrdenCompra detalleOrdenCompra) throws IllegalOrphanException, NonexistentEntityException, Exception {
      /*  detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomItem(detalleOrdenCompra.getDetalleSolicitudCompra().getDetalleSolicitudCompraPK().getPiDetsolcomItem());
        detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomOrdecomp(detalleOrdenCompra.getOrdenCompra().getPiOrdecompNumero());
        detalleOrdenCompra.getDetalleOrdenCompraPK().setPfiDetordcomSolicomp(detalleOrdenCompra.getDetalleSolicitudCompra().getDetalleSolicitudCompraPK().getPfiDetsolcomSolicomp());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleOrdenCompra persistentDetalleOrdenCompra = em.find(DetalleOrdenCompra.class, detalleOrdenCompra.getDetalleOrdenCompraPK());
            DetalleSolicitudCompra detalleSolicitudCompraOld = persistentDetalleOrdenCompra.getDetalleSolicitudCompra();
            DetalleSolicitudCompra detalleSolicitudCompraNew = detalleOrdenCompra.getDetalleSolicitudCompra();
            OrdenCompra ordenCompraOld = persistentDetalleOrdenCompra.getOrdenCompra();
            OrdenCompra ordenCompraNew = detalleOrdenCompra.getOrdenCompra();
            SUsuario fsDetordcomUsuultmodOld = persistentDetalleOrdenCompra.getFsDetordcomUsuultmod();
            SUsuario fsDetordcomUsuultmodNew = detalleOrdenCompra.getFsDetordcomUsuultmod();
            SUsuario fsDetordcomUsuacreaOld = persistentDetalleOrdenCompra.getFsDetordcomUsuacrea();
            SUsuario fsDetordcomUsuacreaNew = detalleOrdenCompra.getFsDetordcomUsuacrea();
            List<Cantidad> cantidadListOld = persistentDetalleOrdenCompra.getCantidadList();
            List<Cantidad> cantidadListNew = detalleOrdenCompra.getCantidadList();
            List<String> illegalOrphanMessages = null;
            if (detalleSolicitudCompraNew != null && !detalleSolicitudCompraNew.equals(detalleSolicitudCompraOld)) {
                DetalleOrdenCompra oldDetalleOrdenCompraOfDetalleSolicitudCompra = detalleSolicitudCompraNew.getDetalleOrdenCompra();
                if (oldDetalleOrdenCompraOfDetalleSolicitudCompra != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The DetalleSolicitudCompra " + detalleSolicitudCompraNew + " already has an item of type DetalleOrdenCompra whose detalleSolicitudCompra column cannot be null. Please make another selection for the detalleSolicitudCompra field.");
                }
            }
            for (Cantidad cantidadListOldCantidad : cantidadListOld) {
                if (!cantidadListNew.contains(cantidadListOldCantidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cantidad " + cantidadListOldCantidad + " since its detalleOrdenCompra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detalleSolicitudCompraNew != null) {
                detalleSolicitudCompraNew = em.getReference(detalleSolicitudCompraNew.getClass(), detalleSolicitudCompraNew.getDetalleSolicitudCompraPK());
                detalleOrdenCompra.setDetalleSolicitudCompra(detalleSolicitudCompraNew);
            }
            if (ordenCompraNew != null) {
                ordenCompraNew = em.getReference(ordenCompraNew.getClass(), ordenCompraNew.getPiOrdecompNumero());
                detalleOrdenCompra.setOrdenCompra(ordenCompraNew);
            }
            if (fsDetordcomUsuultmodNew != null) {
                fsDetordcomUsuultmodNew = em.getReference(fsDetordcomUsuultmodNew.getClass(), fsDetordcomUsuultmodNew.getPsUsuarioCodigo());
                detalleOrdenCompra.setFsDetordcomUsuultmod(fsDetordcomUsuultmodNew);
            }
            if (fsDetordcomUsuacreaNew != null) {
                fsDetordcomUsuacreaNew = em.getReference(fsDetordcomUsuacreaNew.getClass(), fsDetordcomUsuacreaNew.getPsUsuarioCodigo());
                detalleOrdenCompra.setFsDetordcomUsuacrea(fsDetordcomUsuacreaNew);
            }
            List<Cantidad> attachedCantidadListNew = new ArrayList<Cantidad>();
            for (Cantidad cantidadListNewCantidadToAttach : cantidadListNew) {
                cantidadListNewCantidadToAttach = em.getReference(cantidadListNewCantidadToAttach.getClass(), cantidadListNewCantidadToAttach.getCantidadPK());
                attachedCantidadListNew.add(cantidadListNewCantidadToAttach);
            }
            cantidadListNew = attachedCantidadListNew;
            detalleOrdenCompra.setCantidadList(cantidadListNew);
            detalleOrdenCompra = em.merge(detalleOrdenCompra);
            if (detalleSolicitudCompraOld != null && !detalleSolicitudCompraOld.equals(detalleSolicitudCompraNew)) {
                detalleSolicitudCompraOld.setDetalleOrdenCompra(null);
                detalleSolicitudCompraOld = em.merge(detalleSolicitudCompraOld);
            }
            if (detalleSolicitudCompraNew != null && !detalleSolicitudCompraNew.equals(detalleSolicitudCompraOld)) {
                detalleSolicitudCompraNew.setDetalleOrdenCompra(detalleOrdenCompra);
                detalleSolicitudCompraNew = em.merge(detalleSolicitudCompraNew);
            }
            if (ordenCompraOld != null && !ordenCompraOld.equals(ordenCompraNew)) {
                ordenCompraOld.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                ordenCompraOld = em.merge(ordenCompraOld);
            }
            if (ordenCompraNew != null && !ordenCompraNew.equals(ordenCompraOld)) {
                ordenCompraNew.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                ordenCompraNew = em.merge(ordenCompraNew);
            }
            if (fsDetordcomUsuultmodOld != null && !fsDetordcomUsuultmodOld.equals(fsDetordcomUsuultmodNew)) {
                fsDetordcomUsuultmodOld.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                fsDetordcomUsuultmodOld = em.merge(fsDetordcomUsuultmodOld);
            }
            if (fsDetordcomUsuultmodNew != null && !fsDetordcomUsuultmodNew.equals(fsDetordcomUsuultmodOld)) {
                fsDetordcomUsuultmodNew.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                fsDetordcomUsuultmodNew = em.merge(fsDetordcomUsuultmodNew);
            }
            if (fsDetordcomUsuacreaOld != null && !fsDetordcomUsuacreaOld.equals(fsDetordcomUsuacreaNew)) {
                fsDetordcomUsuacreaOld.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                fsDetordcomUsuacreaOld = em.merge(fsDetordcomUsuacreaOld);
            }
            if (fsDetordcomUsuacreaNew != null && !fsDetordcomUsuacreaNew.equals(fsDetordcomUsuacreaOld)) {
                fsDetordcomUsuacreaNew.getDetalleOrdenCompraCollection().add(detalleOrdenCompra);
                fsDetordcomUsuacreaNew = em.merge(fsDetordcomUsuacreaNew);
            }
            for (Cantidad cantidadListNewCantidad : cantidadListNew) {
                if (!cantidadListOld.contains(cantidadListNewCantidad)) {
                    DetalleOrdenCompra oldDetalleOrdenCompraOfCantidadListNewCantidad = cantidadListNewCantidad.getDetalleOrdenCompra();
                    cantidadListNewCantidad.setDetalleOrdenCompra(detalleOrdenCompra);
                    cantidadListNewCantidad = em.merge(cantidadListNewCantidad);
                    if (oldDetalleOrdenCompraOfCantidadListNewCantidad != null && !oldDetalleOrdenCompraOfCantidadListNewCantidad.equals(detalleOrdenCompra)) {
                        oldDetalleOrdenCompraOfCantidadListNewCantidad.getCantidadList().remove(cantidadListNewCantidad);
                        oldDetalleOrdenCompraOfCantidadListNewCantidad = em.merge(oldDetalleOrdenCompraOfCantidadListNewCantidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleOrdenCompraPK id = detalleOrdenCompra.getDetalleOrdenCompraPK();
                if (findDetalleOrdenCompra(id) == null) {
                    throw new NonexistentEntityException("The detalleOrdenCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public void destroy(DetalleOrdenCompraPK id) throws IllegalOrphanException, NonexistentEntityException {
       /* EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleOrdenCompra detalleOrdenCompra;
            try {
                detalleOrdenCompra = em.getReference(DetalleOrdenCompra.class, id);
                detalleOrdenCompra.getDetalleOrdenCompraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleOrdenCompra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cantidad> cantidadListOrphanCheck = detalleOrdenCompra.getCantidadList();
            for (Cantidad cantidadListOrphanCheckCantidad : cantidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DetalleOrdenCompra (" + detalleOrdenCompra + ") cannot be destroyed since the Cantidad " + cantidadListOrphanCheckCantidad + " in its cantidadList field has a non-nullable detalleOrdenCompra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            DetalleSolicitudCompra detalleSolicitudCompra = detalleOrdenCompra.getDetalleSolicitudCompra();
            if (detalleSolicitudCompra != null) {
                detalleSolicitudCompra.setDetalleOrdenCompra(null);
                detalleSolicitudCompra = em.merge(detalleSolicitudCompra);
            }
            OrdenCompra ordenCompra = detalleOrdenCompra.getOrdenCompra();
            if (ordenCompra != null) {
                ordenCompra.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                ordenCompra = em.merge(ordenCompra);
            }
            SUsuario fsDetordcomUsuultmod = detalleOrdenCompra.getFsDetordcomUsuultmod();
            if (fsDetordcomUsuultmod != null) {
                fsDetordcomUsuultmod.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                fsDetordcomUsuultmod = em.merge(fsDetordcomUsuultmod);
            }
            SUsuario fsDetordcomUsuacrea = detalleOrdenCompra.getFsDetordcomUsuacrea();
            if (fsDetordcomUsuacrea != null) {
                fsDetordcomUsuacrea.getDetalleOrdenCompraCollection().remove(detalleOrdenCompra);
                fsDetordcomUsuacrea = em.merge(fsDetordcomUsuacrea);
            }
            em.remove(detalleOrdenCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public List<DetalleOrdenCompra> findDetalleOrdenCompraEntities() {
        return findDetalleOrdenCompraEntities(true, -1, -1);
    }

    public List<DetalleOrdenCompra> findDetalleOrdenCompraEntities(int maxResults, int firstResult) {
        return findDetalleOrdenCompraEntities(false, maxResults, firstResult);
    }

    private List<DetalleOrdenCompra> findDetalleOrdenCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleOrdenCompra.class));
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

    public DetalleOrdenCompra findDetalleOrdenCompra(DetalleOrdenCompraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleOrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleOrdenCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleOrdenCompra> rt = cq.from(DetalleOrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<DetalleOrdenCompra> detallesOrdenCompra(int ordenCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* FROM detalle_orden_compra d WHERE d.pfi_detordcom_ordecomp = ?; ";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, DetalleOrdenCompra.class);
            q.setParameter(1, ordenCompra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean guardarDetalleOrdenCompra(Integer ordenCompra, Integer solicitudCompra, 
            Integer itemSolicitudCompra, Integer cantidad, BigDecimal vrUnitario, String codigoUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query update = em.createNativeQuery("INSERT INTO detalle_orden_compra " +
                    "(pfi_detordcom_ordecomp,	pfi_detordcom_solicomp,	pfi_detordcom_item, " +
                    "i_detordcom_cantidad,      db_detordcom_valounit,  fs_detordcom_usuacrea, " +
                    "d_detordcom_creacion,      fs_detordcom_usuultmod, d_detordcom_ultimodi) " +
                    "VALUES " +
                    "(?,	?,		?, " +
                    "?,         ?,              ?," +
                    "NOW(),     null,           null); ");
            
            update.setParameter(1, ordenCompra);
            update.setParameter(2, solicitudCompra);
            update.setParameter(3, itemSolicitudCompra);
            update.setParameter(4, cantidad);
            update.setParameter(5, vrUnitario);
            update.setParameter(6, codigoUsuario);
            update.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public String nombreArchivoCotizacion(Integer solicitud, String codigoProveedor){
        EntityManager em = getEntityManager();
        String consulta = "SELECT c.s_cotizaci_nombarch " +
                "FROM cotizacion c " +
                "WHERE c.pfi_cotizaci_solicomp = ? AND " +
                "	c.pfs_cotizaci_proveedor = ? " +
                "LIMIT 1 ;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, solicitud);
            q.setParameter(2, codigoProveedor);
            String resu = (String) q.getSingleResult();
            return resu;
        } finally {
            em.close();
        }
    }

    public void eliminarDetalleOrden(Integer ordenCompra, Integer solicitudCompra, Integer itemSolicitudCompra)
            throws Exception {
        EntityManager em = getEntityManager();
        String sqlBorrarCotizacion =
                "DELETE FROM detalle_orden_compra WHERE pfi_detordcom_ordecomp = ? AND pfi_detordcom_solicomp = ? AND pfi_detordcom_item = ? ;";
        try {            
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlBorrarCotizacion);
            q.setParameter(1, ordenCompra);
            q.setParameter(2, solicitudCompra);
            q.setParameter(3, itemSolicitudCompra);
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

    public boolean actualizarValorDetalle(Integer ordenCompra, Integer solicitudCompra,
            Integer detalleSolicitudCompra, BigDecimal valorUnitario, String codigoUsuario, Integer cantidadDetalle) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query update = em.createNativeQuery("UPDATE detalle_orden_compra " +
                    "SET db_detordcom_valounit = ?, " +
                    "	fs_detordcom_usuultmod = ?, " +
                    "	i_detordcom_cantidad = ?, " +
                    "	d_detordcom_ultimodi = NOW() " +
                    "WHERE pfi_detordcom_ordecomp = ? " +
                    "	AND pfi_detordcom_solicomp = ? " +
                    "	AND pfi_detordcom_item = ? ; ");
            update.setParameter(1, valorUnitario);
            update.setParameter(2, codigoUsuario);
            update.setParameter(3, cantidadDetalle);
            update.setParameter(4, ordenCompra);
            update.setParameter(5, solicitudCompra);
            update.setParameter(6, detalleSolicitudCompra);
            update.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    
    

    public List<Object> encontrarNumeroOrdenCompra(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
       EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select pfi_detordcom_ordecomp " +
                    " from detalle_orden_compra" + 
                    " where pfi_detordcom_solicomp=? " +
                    " and pfi_detordcom_item=?" );
            detalleSolicitud.setParameter(1, pfiDetsolcomSolicomp);
            detalleSolicitud.setParameter(2, piDetsolcomItem);
            return detalleSolicitud.getResultList();
        } finally {
            em.close();
         }
    }

    public List<Object> encontrarCantidadPorSolicitudItem(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select i_detordcom_cantidad "
                    + " from detalle_orden_compra"
                    + " where pfi_detordcom_solicomp=? "
                    + " and pfi_detordcom_item=?");
            detalleSolicitud.setParameter(1, pfiDetsolcomSolicomp);
            detalleSolicitud.setParameter(2, piDetsolcomItem);
            return detalleSolicitud.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarCantidadRecibidaDetalleSolicitudItem(int pfiDetsolcomSolicomp, int piDetsolcomItem, int ordenCompra) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleOrdenCompra = em.createNativeQuery(" select i_detorcom_cantidad_recibida"
                    + " from detalle_orden_compra"
                    + " where pfi_detordcom_solicomp=?"
                    + " and pfi_detordcom_item=? "
                    + " and pfi_detordcom_ordecomp=?" );
            detalleOrdenCompra.setParameter(1, pfiDetsolcomSolicomp);
            detalleOrdenCompra.setParameter(2, piDetsolcomItem);
            detalleOrdenCompra.setParameter(3, ordenCompra);
           return detalleOrdenCompra.getResultList();
         } finally {
            em.close();
        }
    }

   

    public List<Object> encontrarCantidadSolicitudes(Integer ordenCompra) {
         EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select count(pfi_detordcom_solicomp)"
                    + " from detalle_orden_compra"
                    + " where pfi_detordcom_ordecomp=?" );
            detalleSolicitud.setParameter(1, ordenCompra);
            return detalleSolicitud.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarCantidadSolicitudesVerificadas(Integer ordenCompra) {
      EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select count(pfi_detordcom_solicomp) " 
                    +" from detalle_orden_compra " 
                    +" join detalle_solicitud_compra on detalle_solicitud_compra.pfi_detsolcom_solicomp= detalle_orden_compra.pfi_detordcom_solicomp " 
                    +" and detalle_orden_compra.pfi_detordcom_item= detalle_solicitud_compra.pi_detsolcom_item " 
                    +" where pfi_detordcom_ordecomp=? "
                    + "and detalle_solicitud_compra.s_detsolcom_recibido='V'  "); 
            detalleSolicitud.setParameter(1, ordenCompra);
            return detalleSolicitud.getResultList();
        } finally {
            em.close();
        }
    }

    public int encontrarCantidadItemsRecibidos(Integer numCompra) {
        EntityManager em = getEntityManager();
        String consulta = "select count(detalle_solicitud_compra.s_detsolcom_recibido) " 
                +" from detalle_orden_compra " 
                +" join detalle_solicitud_compra on detalle_solicitud_compra.pfi_detsolcom_solicomp= detalle_orden_compra.pfi_detordcom_solicomp " 
                +" and detalle_solicitud_compra.pi_detsolcom_item= detalle_orden_compra.pfi_detordcom_item " 
                +" where pfi_detordcom_ordecomp=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, numCompra);
            long numeroCompra = (long) q.getSingleResult();
            return (int) numeroCompra;
        } finally {
            em.close();
        }
    }

    public int encontrarCantidadItemsTotales(Integer numCompra) {
         EntityManager em = getEntityManager();
        String consulta = "select  count(detalle_solicitud_compra.pi_detsolcom_item) "
                + "from detalle_orden_compra "
                + "join detalle_solicitud_compra on detalle_solicitud_compra.pfi_detsolcom_solicomp= detalle_orden_compra.pfi_detordcom_solicomp "
                + "and detalle_solicitud_compra.pi_detsolcom_item= detalle_orden_compra.pfi_detordcom_item "
                + "where pfi_detordcom_ordecomp=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, numCompra);
            long numeroCompra = (long) q.getSingleResult();
            return (int) numeroCompra;
        } finally {
            em.close();
        }
    }
      
    
   
}
