/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Muestra;
import entidades.Muestreo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class MuestraJpaController implements Serializable {

    public MuestraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<Muestra> findMuestraEntities() {
        return findMuestraEntities(true, -1, -1);
    }
    public List<Muestra> findMuestraEntities(int maxResults, int firstResult) {
        return findMuestraEntities(false, maxResults, firstResult);
    }
    private List<Muestra> findMuestraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestra.class));
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
    public Muestra findMuestra(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestra.class, id);
        } finally {
            em.close();
        }
    }
    public int getMuestraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestra> rt = cq.from(Muestra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Muestra guardarMuestra(Muestra muestra) {
        EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
            if (muestra.getIdCampo() == null) {
                //Ejecutar el query sin el id del campo
                String insert = "INSERT INTO muestra (fi_muestra_muestreo, fi_muestra_tecnico, fi_muestra_cliente,"
                        + " fi_muestra_tipomuestreo, fi_muestra_matriz, fs_muestra_usuresprecep, fs_muestra_reporte,"
                        + "d_muestra_fechdesca, s_muestra_observaciones, d_muestra_fechanal, fi_muestra_proyecto, "
                        + "d_muestra_fechtomamuest, fs_muestra_usucreac, d_muestra_fechcreac, fi_muestra_tipomuest) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?, ?,? )";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, muestra.getIdMuestreo().getIdMuestreo());
                insercion.setParameter(2, muestra.getIdResponsable().getNombre());
                insercion.setParameter(3, muestra.getIdCliente().getClientePK().getPsClienteNit());
                insercion.setParameter(4, muestra.getTipoMuestreo().getIdtpMuestra());
                insercion.setParameter(5, muestra.getIdMatriz());
                insercion.setParameter(6, muestra.getResponsableRecepcion().getPsUsuarioCodigo());
                insercion.setParameter(7, muestra.getReporte().getPiReporteId());
                insercion.setParameter(8, muestra.getFechaDescarte());
                insercion.setParameter(9, muestra.getObservaciones());
                insercion.setParameter(10, muestra.getFechaAnalisis());
                insercion.setParameter(11, muestra.getProyecto().getIdProyecto());
                insercion.setParameter(12, muestra.getFechaTomaMuestra());
                insercion.setParameter(13, muestra.getUsuario_creacion().getPsUsuarioCodigo());
                insercion.setParameter(14, muestra.getFechaCreacion());
                insercion.setParameter(15, muestra.getTipoMuestra().getIdTipoMuestra());
            } 
            else if(muestra.getFechaAnalisis()!=null || muestra.getFechaTomaMuestra()!=null){
                String insert = "INSERT INTO muestra (fi_muestra_muestreo, fi_muestra_tecnico, fi_muestra_cliente,"
                        + " fi_muestra_tipomuestreo, fi_muestra_matriz, fs_muestra_usuresprecep, fs_muestra_reporte,"
                        + "d_muestra_fechdesca, s_muestra_observaciones, fi_muestra_proyecto, "
                        + "fs_muestra_usucreac, d_muestra_fechcreac, fi_muestra_campo, s_muestra_descripcion, d_muestra_fechtomamuest, d_muestra_fechanal,fi_muestra_tipomuest) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,? )";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, muestra.getIdMuestreo().getIdMuestreo());
                insercion.setParameter(2, muestra.getIdResponsable().getNombre());
                insercion.setParameter(3, muestra.getIdCliente().getClientePK().getPsClienteNit());
                insercion.setParameter(4, muestra.getTipoMuestreo().getIdtpMuestra());
                insercion.setParameter(5, muestra.getIdMatriz());
                insercion.setParameter(6, muestra.getResponsableRecepcion().getPsUsuarioCodigo());
                insercion.setParameter(7, muestra.getReporte().getPiReporteId());
                insercion.setParameter(8, muestra.getFechaDescarte());
                insercion.setParameter(9, muestra.getObservaciones());
                insercion.setParameter(10, muestra.getProyecto().getIdProyecto());
                insercion.setParameter(11, muestra.getUsuario_creacion().getPsUsuarioCodigo());
                insercion.setParameter(12, muestra.getFechaCreacion());        
                insercion.setParameter(13, muestra.getIdCampo());
                insercion.setParameter(14, muestra.getDescripcion());
                insercion.setParameter(15, muestra.getFechaTomaMuestra());
                insercion.setParameter(16, muestra.getFechaAnalisis());
                insercion.setParameter(17, muestra.getTipoMuestra().getIdTipoMuestra());
            }
            else {
                String insert = "INSERT INTO muestra (fi_muestra_muestreo, fi_muestra_tecnico, fi_muestra_cliente,"
                        + " fi_muestra_tipomuestreo, fi_muestra_matriz, fs_muestra_usuresprecep, fs_muestra_reporte,"
                        + "d_muestra_fechdesca, s_muestra_observaciones, d_muestra_fechanal, fi_muestra_proyecto, fi_muestra_campo, "
                        + "s_muestra_descripcion, d_muestra_fechtomamuest, fs_muestra_usucreac, d_muestra_fechcreac,fi_muestra_tipomuest) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?,?,?,?,?,?, ? , ?,?)";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, muestra.getIdMuestreo().getIdMuestreo());
                insercion.setParameter(2, muestra.getIdResponsable().getNombre());
                insercion.setParameter(3,muestra.getIdCliente().getClientePK().getPsClienteNit());
                insercion.setParameter(4, muestra.getTipoMuestreo().getIdtpMuestra());
                insercion.setParameter(5, muestra.getIdMatriz());
                insercion.setParameter(6, muestra.getResponsableRecepcion().getPsUsuarioCodigo());
                insercion.setParameter(7, muestra.getReporte().getPiReporteId());
                insercion.setParameter(8, muestra.getFechaDescarte());
                insercion.setParameter(9, muestra.getObservaciones());
                insercion.setParameter(10, muestra.getFechaAnalisis());
                insercion.setParameter(11, muestra.getProyecto().getIdProyecto());
                insercion.setParameter(12, muestra.getIdCampo());
                insercion.setParameter(13, muestra.getDescripcion());
                insercion.setParameter(14, muestra.getFechaTomaMuestra());
                insercion.setParameter(15, muestra.getUsuario_creacion().getPsUsuarioCodigo());
                insercion.setParameter(16, new Date());
                insercion.setParameter(17, muestra.getTipoMuestra().getIdTipoMuestra());
            }
            insercion.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
        return muestra;
    }
    
    public List<Muestra> encontrarMuestraPorMuestreo(Integer idMuestreo) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct * FROM muestra where fi_muestra_muestreo=? ";
        try {
            Query q = em.createNativeQuery(consulta, Muestra.class);
            q.setParameter(1, idMuestreo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarMuestreoPunto(int idMuestreo , int idPunto) {
    EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE muestreo SET fi_muestreo_punto=? WHERE pi_muestreo_id=?";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, idPunto);
                insercion.setParameter(2, idMuestreo);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }

    public void editarMuestra(Muestra muestra) {
        EntityManager em = getEntityManager();
        try {         
            Query insercion = null;
            String insert = "UPDATE muestra SET fi_muestra_tipomuestreo=?, s_muestra_observaciones =?, d_muestra_fechtomamuest = ?, fs_muestra_usuamodi = ? , d_muestra_fechmodi = ?, s_muestra_descripcion = ?,"
                    + "fi_muestra_tipomuest=?, fi_muestra_campo = ? , s_muestra_descripcion= ?  "
                    + "WHERE pi_muestra_id=?";
            em.getTransaction().begin();
            insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, muestra.getTipoMuestreo().getIdtpMuestra());
            insercion.setParameter(2, muestra.getObservaciones());
            insercion.setParameter(3, muestra.getFechaTomaMuestra());
            insercion.setParameter(4, muestra.getUsuario_modificador().getPsUsuarioCodigo());
            insercion.setParameter(5, new Date());
            insercion.setParameter(6, muestra.getDescripcion());
            insercion.setParameter(7, muestra.getTipoMuestra().getIdTipoMuestra());
            insercion.setParameter(8, muestra.getIdCampo());
            insercion.setParameter(9, muestra.getDescripcion());
            insercion.setParameter(10, muestra.getIdmuestra());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }
    
    
    public List encontrarMuestra(Muestra muestra, String fecha) {
        System.out.println("numero de muestra reporte "+muestra.getReporte().getPiReporteId());
         System.out.println("numero de muestra nombre "+muestra.getIdResponsable().getNombre());
         System.out.println("numero de muestra muestreo "+muestra.getIdMuestreo().getIdMuestreo());
         
          System.out.println("numero de muestra tipo muestra "+muestra.getTipoMuestreo().getIdtpMuestra());
          System.out.println("numero de muestra id matriz "+muestra.getIdMatriz());
           System.out.println("numero de muestra id proyecto "+muestra.getProyecto().getIdProyecto());
           
           System.out.println("numero de muestra fecha "+fecha);
          
        EntityManager em = getEntityManager();
        if(muestra.getFechaTomaMuestra()!=null){
            String consulta = "SELECT * FROM muestra where fs_muestra_reporte=? "
              + "and fi_muestra_tecnico=? and fi_muestra_muestreo=? "
              + "and fi_muestra_tipomuestreo=? and fi_muestra_matriz=? "
              + "and fi_muestra_proyecto = ? "
              + "and date(d_muestra_fechtomamuest)= ?";
            
        try {
            Query q = em.createNativeQuery(consulta, Muestra.class);
            q.setParameter(1, muestra.getReporte().getPiReporteId());
            q.setParameter(2, muestra.getIdResponsable().getNombre());
            q.setParameter(3, muestra.getIdMuestreo().getIdMuestreo());
            q.setParameter(4, muestra.getTipoMuestreo().getIdtpMuestra());
            q.setParameter(5, muestra.getIdMatriz());
            q.setParameter(6, muestra.getProyecto().getIdProyecto());
            q.setParameter(7, fecha );
            return q.getResultList();
        } finally {
            em.close();
        }
        }
        else{
              String consulta = "SELECT * FROM muestra where fs_muestra_reporte=? "
              + "and fi_muestra_tecnico=? and fi_muestra_muestreo=? "
              + "and fi_muestra_tipomuestreo=? and fi_muestra_matriz=? "
              + "and fi_muestra_proyecto = ? and fi_muestra_tipomuest = ? ";
             
        try {  
            Query q = em.createNativeQuery(consulta, Muestra.class);
            q.setParameter(1, muestra.getReporte().getPiReporteId());
            q.setParameter(2, muestra.getIdResponsable().getNombre());
            q.setParameter(3, muestra.getIdMuestreo().getIdMuestreo());
            q.setParameter(4, muestra.getTipoMuestreo().getIdtpMuestra());
            q.setParameter(5, muestra.getIdMatriz());
            q.setParameter(6, muestra.getProyecto().getIdProyecto());
            q.setParameter(7, muestra.getTipoMuestra().getIdTipoMuestra());
            return q.getResultList();
        } finally {
            em.close();
        }
        }  
    }
    
    public List<Muestra> encontrarMuestraPorId(Integer idMuestra){
         EntityManager em = getEntityManager();
            String consulta = "SELECT * FROM muestra where pi_muestra_id=? ";
        try {
            Query q = em.createNativeQuery(consulta, Muestra.class);
            q.setParameter(1, idMuestra);
            return q.getResultList();
        } finally {
            em.close();
        }
      }  

    public List<Object> encontrarMuestraPorReporte(String idReporte) {
          EntityManager em = getEntityManager();
            String consulta = "select distinct muestra.pi_muestra_id from muestra join"
                + " muestra_analisis on muestra_analisis.fi_muestraanal_muestra=muestra.pi_muestra_id"
                + " join variable_analisis on muestra_analisis.fi_muestraanal_variable= variable_analisis.pi_varianal_id"
                + " join valor on valor.fi_valor_muestraanal = muestra_analisis.pi_muestraanal_id"
                + " where fs_muestra_reporte=? and valor.s_valor_valor is null";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarMuestrasPorReporte(String idReporte) {
          EntityManager em = getEntityManager();
            String consulta = "select distinct muestra.pi_muestra_id, fi_muestra_muestreo"
                + " fi_muestra_tecnico, fi_muestra_tipomuestreo, fi_muestra_matriz,"
                + " fs_muestra_reporte, d_muestra_fechanal, fi_muestra_proyecto,"
                + " fi_muestra_campo, s_muestra_descripcion, d_muestra_fechtomamuest,"
                + " fs_muestra_usucreac, fi_muestra_tipomuest"
                + " from muestra join"
                + " reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte"
                + " where reporte.pi_reporte_id=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    } 
    
     public List<Object> encontrarMuestraPorIdReporte(String idReporte) {
          EntityManager em = getEntityManager();
            String consulta = "select muestra.pi_muestra_id"
                + " from muestra join"
                + " reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte"
                + " where reporte.pi_reporte_id=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void actualizarCampoMuestra(Muestra muestra, String idCampo) {
          EntityManager em = getEntityManager();
        try {
            Query insercion = null; 
            String insert = "UPDATE muestra SET fi_muestra_campo=?"
                    + " WHERE pi_muestra_id=?";
            em.getTransaction().begin();
            insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, idCampo); 
            insercion.setParameter(2, muestra.getIdmuestra());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    
    }

    public List<Integer> consultarUltimaMuestraIncrementada() {
         EntityManager em = getEntityManager();
         String consulta = "select MAX(pi_muestra_id) from muestra  ";
         try {
            Query q = em.createNativeQuery(consulta);    
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarFechaIngresoMuestra(int idMuestra) {
         EntityManager em = getEntityManager();
         String consulta = "select d_muestreo_fechregis "
                + "from muestreo "
                + "join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id "
                + "where pi_muestra_id= ?  ";
         try {
            Query q = em.createNativeQuery(consulta); 
            q.setParameter(1, idMuestra);
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    public void eliminarMuestra(Integer idMuestra) {
        EntityManager em = getEntityManager();
        String insert = "DELETE FROM muestra WHERE pi_muestra_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idMuestra);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Object> encontrarGeneroHidrobiologiaPorReporte(String idReporte, int idDatoHidrobiologia) {
            EntityManager em = getEntityManager();
            String consulta = "select distinct (dato_hidrobiologia.s_dato_hidro_epecie_morfoespecie),dato_hidrobiologia.s_dato_hidro_sufijo, "
                    + " variable_analisis.s_varianal_descripcion "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia "
                + "join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable "
                + "where fs_muestra_reporte=? "
                + "and pi_dato_hidro=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
             q.setParameter(2, idDatoHidrobiologia);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

 
    
}
