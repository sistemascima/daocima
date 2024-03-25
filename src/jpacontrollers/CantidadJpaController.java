/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Cantidad;
import entidades.CantidadPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.DetalleOrdenCompra;
import entidades.SUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class CantidadJpaController implements Serializable {

    public CantidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   /* public void create(Cantidad cantidad) throws PreexistingEntityException, Exception {
        if (cantidad.getCantidadPK() == null) {
            cantidad.setCantidadPK(new CantidadPK());
        }
        cantidad.getCantidadPK().setPfiCantidadSolicomp(cantidad.getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomSolicomp());
        cantidad.getCantidadPK().setPfiCantidadItem(cantidad.getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleOrdenCompra detalleOrdenCompra = cantidad.getDetalleOrdenCompra();
            if (detalleOrdenCompra != null) {
                detalleOrdenCompra = em.getReference(detalleOrdenCompra.getClass(), detalleOrdenCompra.getDetalleOrdenCompraPK());
                cantidad.setDetalleOrdenCompra(detalleOrdenCompra);
            }
            SUsuario fsCantidadUsuacreacion = cantidad.getFsCantidadUsuacreacion();
            if (fsCantidadUsuacreacion != null) {
                fsCantidadUsuacreacion = em.getReference(fsCantidadUsuacreacion.getClass(), fsCantidadUsuacreacion.getPsUsuarioCodigo());
                cantidad.setFsCantidadUsuacreacion(fsCantidadUsuacreacion);
            }
            SUsuario fsCantidadUsuamodificacion = cantidad.getFsCantidadUsuamodificacion();
            if (fsCantidadUsuamodificacion != null) {
                fsCantidadUsuamodificacion = em.getReference(fsCantidadUsuamodificacion.getClass(), fsCantidadUsuamodificacion.getPsUsuarioCodigo());
                cantidad.setFsCantidadUsuamodificacion(fsCantidadUsuamodificacion);
            }
            em.persist(cantidad);
            if (detalleOrdenCompra != null) {
                detalleOrdenCompra.getCantidadList().add(cantidad);
                detalleOrdenCompra = em.merge(detalleOrdenCompra);
            }
            if (fsCantidadUsuacreacion != null) {
                fsCantidadUsuacreacion.getCantidadList().add(cantidad);
                fsCantidadUsuacreacion = em.merge(fsCantidadUsuacreacion);
            }
            if (fsCantidadUsuamodificacion != null) {
                fsCantidadUsuamodificacion.getCantidadList().add(cantidad);
                fsCantidadUsuamodificacion = em.merge(fsCantidadUsuamodificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCantidad(cantidad.getCantidadPK()) != null) {
                throw new PreexistingEntityException("Cantidad " + cantidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

   /* public void edit(Cantidad cantidad) throws NonexistentEntityException, Exception {
        cantidad.getCantidadPK().setPfiCantidadSolicomp(cantidad.getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomSolicomp());
        cantidad.getCantidadPK().setPfiCantidadItem(cantidad.getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomItem());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cantidad persistentCantidad = em.find(Cantidad.class, cantidad.getCantidadPK());
            DetalleOrdenCompra detalleOrdenCompraOld = persistentCantidad.getDetalleOrdenCompra();
            DetalleOrdenCompra detalleOrdenCompraNew = cantidad.getDetalleOrdenCompra();
            SUsuario fsCantidadUsuacreacionOld = persistentCantidad.getFsCantidadUsuacreacion();
            SUsuario fsCantidadUsuacreacionNew = cantidad.getFsCantidadUsuacreacion();
            SUsuario fsCantidadUsuamodificacionOld = persistentCantidad.getFsCantidadUsuamodificacion();
            SUsuario fsCantidadUsuamodificacionNew = cantidad.getFsCantidadUsuamodificacion();
            if (detalleOrdenCompraNew != null) {
                detalleOrdenCompraNew = em.getReference(detalleOrdenCompraNew.getClass(), detalleOrdenCompraNew.getDetalleOrdenCompraPK());
                cantidad.setDetalleOrdenCompra(detalleOrdenCompraNew);
            }
            if (fsCantidadUsuacreacionNew != null) {
                fsCantidadUsuacreacionNew = em.getReference(fsCantidadUsuacreacionNew.getClass(), fsCantidadUsuacreacionNew.getPsUsuarioCodigo());
                cantidad.setFsCantidadUsuacreacion(fsCantidadUsuacreacionNew);
            }
            if (fsCantidadUsuamodificacionNew != null) {
                fsCantidadUsuamodificacionNew = em.getReference(fsCantidadUsuamodificacionNew.getClass(), fsCantidadUsuamodificacionNew.getPsUsuarioCodigo());
                cantidad.setFsCantidadUsuamodificacion(fsCantidadUsuamodificacionNew);
            }
            cantidad = em.merge(cantidad);
            if (detalleOrdenCompraOld != null && !detalleOrdenCompraOld.equals(detalleOrdenCompraNew)) {
                detalleOrdenCompraOld.getCantidadList().remove(cantidad);
                detalleOrdenCompraOld = em.merge(detalleOrdenCompraOld);
            }
            if (detalleOrdenCompraNew != null && !detalleOrdenCompraNew.equals(detalleOrdenCompraOld)) {
                detalleOrdenCompraNew.getCantidadList().add(cantidad);
                detalleOrdenCompraNew = em.merge(detalleOrdenCompraNew);
            }
            if (fsCantidadUsuacreacionOld != null && !fsCantidadUsuacreacionOld.equals(fsCantidadUsuacreacionNew)) {
                fsCantidadUsuacreacionOld.getCantidadList().remove(cantidad);
                fsCantidadUsuacreacionOld = em.merge(fsCantidadUsuacreacionOld);
            }
            if (fsCantidadUsuacreacionNew != null && !fsCantidadUsuacreacionNew.equals(fsCantidadUsuacreacionOld)) {
                fsCantidadUsuacreacionNew.getCantidadList().add(cantidad);
                fsCantidadUsuacreacionNew = em.merge(fsCantidadUsuacreacionNew);
            }
            if (fsCantidadUsuamodificacionOld != null && !fsCantidadUsuamodificacionOld.equals(fsCantidadUsuamodificacionNew)) {
                fsCantidadUsuamodificacionOld.getCantidadList().remove(cantidad);
                fsCantidadUsuamodificacionOld = em.merge(fsCantidadUsuamodificacionOld);
            }
            if (fsCantidadUsuamodificacionNew != null && !fsCantidadUsuamodificacionNew.equals(fsCantidadUsuamodificacionOld)) {
                fsCantidadUsuamodificacionNew.getCantidadList().add(cantidad);
                fsCantidadUsuamodificacionNew = em.merge(fsCantidadUsuamodificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CantidadPK id = cantidad.getCantidadPK();
                if (findCantidad(id) == null) {
                    throw new NonexistentEntityException("The cantidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

   /* public void destroy(CantidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cantidad cantidad;
            try {
                cantidad = em.getReference(Cantidad.class, id);
                cantidad.getCantidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cantidad with id " + id + " no longer exists.", enfe);
            }
            DetalleOrdenCompra detalleOrdenCompra = cantidad.getDetalleOrdenCompra();
            if (detalleOrdenCompra != null) {
                detalleOrdenCompra.getCantidadList().remove(cantidad);
                detalleOrdenCompra = em.merge(detalleOrdenCompra);
            }
            SUsuario fsCantidadUsuacreacion = cantidad.getFsCantidadUsuacreacion();
            if (fsCantidadUsuacreacion != null) {
                fsCantidadUsuacreacion.getCantidadList().remove(cantidad);
                fsCantidadUsuacreacion = em.merge(fsCantidadUsuacreacion);
            }
            SUsuario fsCantidadUsuamodificacion = cantidad.getFsCantidadUsuamodificacion();
            if (fsCantidadUsuamodificacion != null) {
                fsCantidadUsuamodificacion.getCantidadList().remove(cantidad);
                fsCantidadUsuamodificacion = em.merge(fsCantidadUsuamodificacion);
            }
            em.remove(cantidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Cantidad> findCantidadEntities() {
        return findCantidadEntities(true, -1, -1);
    }

    public List<Cantidad> findCantidadEntities(int maxResults, int firstResult) {
        return findCantidadEntities(false, maxResults, firstResult);
    }

    private List<Cantidad> findCantidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cantidad.class));
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

    public Cantidad findCantidad(CantidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cantidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCantidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cantidad> rt = cq.from(Cantidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int encontrarIdCantidad(int pfiDetsolcomSolicomp, int piDetsolcomItem, int ordenCompra) {
        EntityManager em = getEntityManager();
        int idCantidad=0;
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select max(pi_cantidad_id)"
                    + " from cantidad"
                    + " where pfi_cantidad_solicomp=?"
                    + " and pfi_cantidad_item=?"
                    + " and pfi_cantidad_ordecomp=?"  );
            detalleSolicitud.setParameter(1, pfiDetsolcomSolicomp);
            detalleSolicitud.setParameter(2, piDetsolcomItem);
            detalleSolicitud.setParameter(3, ordenCompra);
            if(detalleSolicitud.getSingleResult()!=null){
                   idCantidad=  Integer.valueOf(detalleSolicitud.getSingleResult().toString());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return idCantidad;
    }

    public List<Boolean> insertarCantidad(List<Cantidad> listaCantidad, String tipoCompraDesc, String observaciones) {
         EntityManager em = getEntityManager();
         List<Boolean> lista = new ArrayList<Boolean>();
         for (int i = 0; i < listaCantidad.size(); i++) {
            String cargo = cargoAccion("Recepción de Productos - " + tipoCompraDesc, listaCantidad.get(i).getFsCantidadUsuacreacion().getPsUsuarioCodigo());
            StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_recepcion_parcial");
            em.getTransaction().begin();
            procedimientoAlmacenado.registerStoredProcedureParameter("id_solicitud", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("id_item", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("id_ordecomp", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("cantidad", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("usuario", String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("cargo", String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("observaciones", String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("insertado", boolean.class, ParameterMode.OUT);
            procedimientoAlmacenado.setParameter("id_solicitud", listaCantidad.get(i).getCantidadPK().getPfiCantidadSolicomp());
            procedimientoAlmacenado.setParameter("id_item", listaCantidad.get(i).getCantidadPK().getPfiCantidadItem());
            procedimientoAlmacenado.setParameter("id_ordecomp", listaCantidad.get(i).getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomOrdecomp());
            procedimientoAlmacenado.setParameter("cantidad", listaCantidad.get(i).getICantidadCantidad());
            procedimientoAlmacenado.setParameter("usuario", listaCantidad.get(i).getFsCantidadUsuacreacion().getPsUsuarioCodigo());
            procedimientoAlmacenado.setParameter("cargo", cargo);
            procedimientoAlmacenado.setParameter("observaciones", observaciones);
            procedimientoAlmacenado.execute();
            lista.add((boolean) procedimientoAlmacenado.getOutputParameterValue("insertado"));
            em.getTransaction().commit();
        }
        em.close();
        return lista;
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

    
    public int encontrarSumaPorCantidad(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
       EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery(" select COALESCE(sum(i_cantidad_cantidad),0)"
                    + " from cantidad "
                    +" where pfi_cantidad_solicomp=? " 
                    +" and pfi_cantidad_item=?"  );
            detalleSolicitud.setParameter(1, pfiDetsolcomSolicomp);
            detalleSolicitud.setParameter(2, piDetsolcomItem);
            BigDecimal cantidad= (BigDecimal) detalleSolicitud.getSingleResult();
            em.getTransaction().commit();
            return cantidad.intValue();
        } finally {
            em.close();
        }
    }

    public List<Long> encontrarCantidadFalta(List<Cantidad> listaCantidad) {
         EntityManager em = getEntityManager();
         List<Long> lista = new ArrayList<Long>();
         for (int i = 0; i < listaCantidad.size(); i++) {
            StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_traer_items_sobrantes");
            em.getTransaction().begin();
            procedimientoAlmacenado.registerStoredProcedureParameter("id_solicitud", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("id_item", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("id_ordecomp", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("cantidad", int.class, ParameterMode.OUT);
            procedimientoAlmacenado.setParameter("id_solicitud", listaCantidad.get(i).getCantidadPK().getPfiCantidadSolicomp());
            procedimientoAlmacenado.setParameter("id_item", listaCantidad.get(i).getCantidadPK().getPfiCantidadItem());
            procedimientoAlmacenado.setParameter("id_ordecomp", listaCantidad.get(i).getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomOrdecomp()); 
            procedimientoAlmacenado.execute();
            lista.add((Long) procedimientoAlmacenado.getOutputParameterValue("cantidad_sobra"));
            em.getTransaction().commit();
        }
        em.close();
        return lista;
    }

    public void actualizarFechasRecepcion(List<Cantidad> listaCantidad) {
          EntityManager em = getEntityManager();
          for (int i = 0; i < listaCantidad.size(); i++) {
              StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_actualizar_fechas_recepcion");
              em.getTransaction().begin();
              procedimientoAlmacenado.registerStoredProcedureParameter("id_solicitud", int.class, ParameterMode.IN);
              procedimientoAlmacenado.registerStoredProcedureParameter("id_item", int.class, ParameterMode.IN);
              procedimientoAlmacenado.registerStoredProcedureParameter("id_ordecomp", int.class, ParameterMode.IN);
              procedimientoAlmacenado.registerStoredProcedureParameter("fecha_recepcion", Date.class, ParameterMode.IN);   
              procedimientoAlmacenado.setParameter("id_solicitud", listaCantidad.get(i).getCantidadPK().getPfiCantidadSolicomp());
              procedimientoAlmacenado.setParameter("id_item", listaCantidad.get(i).getCantidadPK().getPfiCantidadItem());
              procedimientoAlmacenado.setParameter("id_ordecomp", listaCantidad.get(i).getDetalleOrdenCompra().getDetalleOrdenCompraPK().getPfiDetordcomOrdecomp());
              procedimientoAlmacenado.setParameter("fecha_recepcion", listaCantidad.get(i).getDetalleOrdenCompra().getD_detordcom_fecha_prox_recepecion());      
              procedimientoAlmacenado.execute();
              em.getTransaction().commit();
        }
           em.close();
      
    }
}
