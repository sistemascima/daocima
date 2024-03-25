/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Proyecto;
import entidades.Punto;
import entidades.Tecnico;
import entidades.Reporte;
import java.util.ArrayList;
import java.util.List;
import entidades.Muestra;
import entidades.Muestreo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class MuestreoJpaController implements Serializable {

    public MuestreoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestreo muestreo) {
        if (muestreo.getReporteList() == null) {
            muestreo.setReporteList(new ArrayList<Reporte>());
        }
        if (muestreo.getMuestraList() == null) {
            muestreo.setMuestraList(new ArrayList<Muestra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto idProyecto = muestreo.getIdProyecto();
            if (idProyecto != null) {
                idProyecto = em.getReference(idProyecto.getClass(), idProyecto.getIdProyecto());
                muestreo.setIdProyecto(idProyecto);
            }
            
            Tecnico realizado = muestreo.getRealizado();
            if (realizado != null) {
                realizado = em.getReference(realizado.getClass(), realizado.getNombre());
                muestreo.setRealizado(realizado);
            }
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            /*for (Reporte reporteListReporteToAttach : muestreo.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdreporte());
                attachedReporteList.add(reporteListReporteToAttach);
            }*/
            muestreo.setReporteList(attachedReporteList);
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : muestreo.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getIdmuestra());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            muestreo.setMuestraList(attachedMuestraList);
            em.persist(muestreo);
            if (idProyecto != null) {
                idProyecto.getMuestreoList().add(muestreo);
                idProyecto = em.merge(idProyecto);
            }
           
            if (realizado != null) {
                realizado.getMuestreoList().add(muestreo);
                realizado = em.merge(realizado);
            }
            for (Reporte reporteListReporte : muestreo.getReporteList()) {
                Muestreo oldIdMuestreoOfReporteListReporte = reporteListReporte.getIdMuestreo();
                reporteListReporte.setIdMuestreo(muestreo);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldIdMuestreoOfReporteListReporte != null) {
                    oldIdMuestreoOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldIdMuestreoOfReporteListReporte = em.merge(oldIdMuestreoOfReporteListReporte);
                }
            }
            for (Muestra muestraListMuestra : muestreo.getMuestraList()) {
                Muestreo oldIdMuestreoOfMuestraListMuestra = muestraListMuestra.getIdMuestreo();
                muestraListMuestra.setIdMuestreo(muestreo);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldIdMuestreoOfMuestraListMuestra != null) {
                    oldIdMuestreoOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldIdMuestreoOfMuestraListMuestra = em.merge(oldIdMuestreoOfMuestraListMuestra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestreo muestreo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestreo persistentMuestreo = em.find(Muestreo.class, muestreo.getIdMuestreo());
            Proyecto idProyectoOld = persistentMuestreo.getIdProyecto();
            Proyecto idProyectoNew = muestreo.getIdProyecto();
           
            Tecnico realizadoOld = persistentMuestreo.getRealizado();
            Tecnico realizadoNew = muestreo.getRealizado();
            List<Reporte> reporteListOld = persistentMuestreo.getReporteList();
            List<Reporte> reporteListNew = muestreo.getReporteList();
            List<Muestra> muestraListOld = persistentMuestreo.getMuestraList();
            List<Muestra> muestraListNew = muestreo.getMuestraList();
            if (idProyectoNew != null) {
                idProyectoNew = em.getReference(idProyectoNew.getClass(), idProyectoNew.getIdProyecto());
                muestreo.setIdProyecto(idProyectoNew);
            }
            
            if (realizadoNew != null) {
                realizadoNew = em.getReference(realizadoNew.getClass(), realizadoNew.getNombre());
                muestreo.setRealizado(realizadoNew);
            }
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            /*for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdreporte());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }*/
            reporteListNew = attachedReporteListNew;
            muestreo.setReporteList(reporteListNew);
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getIdmuestra());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            muestreo.setMuestraList(muestraListNew);
            muestreo = em.merge(muestreo);
            if (idProyectoOld != null && !idProyectoOld.equals(idProyectoNew)) {
                idProyectoOld.getMuestreoList().remove(muestreo);
                idProyectoOld = em.merge(idProyectoOld);
            }
            if (idProyectoNew != null && !idProyectoNew.equals(idProyectoOld)) {
                idProyectoNew.getMuestreoList().add(muestreo);
                idProyectoNew = em.merge(idProyectoNew);
            }
            if (realizadoOld != null && !realizadoOld.equals(realizadoNew)) {
                realizadoOld.getMuestreoList().remove(muestreo);
                realizadoOld = em.merge(realizadoOld);
            }
            if (realizadoNew != null && !realizadoNew.equals(realizadoOld)) {
                realizadoNew.getMuestreoList().add(muestreo);
                realizadoNew = em.merge(realizadoNew);
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    reporteListOldReporte.setIdMuestreo(null);
                    reporteListOldReporte = em.merge(reporteListOldReporte);
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Muestreo oldIdMuestreoOfReporteListNewReporte = reporteListNewReporte.getIdMuestreo();
                    reporteListNewReporte.setIdMuestreo(muestreo);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldIdMuestreoOfReporteListNewReporte != null && !oldIdMuestreoOfReporteListNewReporte.equals(muestreo)) {
                        oldIdMuestreoOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldIdMuestreoOfReporteListNewReporte = em.merge(oldIdMuestreoOfReporteListNewReporte);
                    }
                }
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    muestraListOldMuestra.setIdMuestreo(null);
                    muestraListOldMuestra = em.merge(muestraListOldMuestra);
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Muestreo oldIdMuestreoOfMuestraListNewMuestra = muestraListNewMuestra.getIdMuestreo();
                    muestraListNewMuestra.setIdMuestreo(muestreo);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldIdMuestreoOfMuestraListNewMuestra != null && !oldIdMuestreoOfMuestraListNewMuestra.equals(muestreo)) {
                        oldIdMuestreoOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldIdMuestreoOfMuestraListNewMuestra = em.merge(oldIdMuestreoOfMuestraListNewMuestra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestreo.getIdMuestreo();
                if (findMuestreo(id) == null) {
                    throw new NonexistentEntityException("The muestreo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestreo muestreo;
            try {
                muestreo = em.getReference(Muestreo.class, id);
                muestreo.getIdMuestreo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestreo with id " + id + " no longer exists.", enfe);
            }
            Proyecto idProyecto = muestreo.getIdProyecto();
            if (idProyecto != null) {
                idProyecto.getMuestreoList().remove(muestreo);
                idProyecto = em.merge(idProyecto);
            }
            Tecnico realizado = muestreo.getRealizado();
            if (realizado != null) {
                realizado.getMuestreoList().remove(muestreo);
                realizado = em.merge(realizado);
            }
            List<Reporte> reporteList = muestreo.getReporteList();
            for (Reporte reporteListReporte : reporteList) {
                reporteListReporte.setIdMuestreo(null);
                reporteListReporte = em.merge(reporteListReporte);
            }
            List<Muestra> muestraList = muestreo.getMuestraList();
            for (Muestra muestraListMuestra : muestraList) {
                muestraListMuestra.setIdMuestreo(null);
                muestraListMuestra = em.merge(muestraListMuestra);
            }
            em.remove(muestreo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestreo> findMuestreoEntities() {
        return findMuestreoEntities(true, -1, -1);
    }

    public List<Muestreo> findMuestreoEntities(int maxResults, int firstResult) {
        return findMuestreoEntities(false, maxResults, firstResult);
    }

    private List<Muestreo> findMuestreoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestreo.class));
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

    public Muestreo findMuestreo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestreo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestreoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestreo> rt = cq.from(Muestreo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Muestreo> getMuestreoProyecto(Proyecto proyecto) {
        List results = getEntityManager().createNamedQuery("Muestreo.findProyecto").setParameter("id_Proyecto", proyecto.getIdProyecto()).getResultList();
        
        return results;
    }

    public void crearMuestreo(Muestreo muestreo) {
      
        EntityManager em = getEntityManager();
        try {
         String insert= "INSERT INTO muestreo (s_muestreo_fechinic, s_muestreo_planmues,"
                 + " fs_muestreo_tecnico, fi_muestreo_municipio,fi_muestreo_ciudad, fi_muestreo_proyecto, d_muestreo_fechrecepmuest, fi_muestreo_usuacrea, d_muestreo_fechregis) "
                    + "VALUES (?, ?, ?, ?, ?, ?,?,?, ?)";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);         
            insercion.setParameter(1, muestreo.getFechaInic());
            insercion.setParameter(2, muestreo.getPlanMuestreo());
            insercion.setParameter(3, muestreo.getRealizado().getNombre());
            insercion.setParameter(4, muestreo.getIdMunicipio().getIdValor());
            insercion.setParameter(5, muestreo.getIdCiudad().getIdValor());
            insercion.setParameter(6, muestreo.getIdProyecto().getIdProyecto());
            insercion.setParameter(7, muestreo.getFechaRecepcMuest());
            insercion.setParameter(8, muestreo.getUsuarioCreador().getPsUsuarioCodigo());
            insercion.setParameter(9, muestreo.getFecha_registro());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    }

    public int encontrarMuestreo() throws ParseException {
         EntityManager em = getEntityManager();
         String consulta = "select max(pi_muestreo_id) " +
                            "from muestreo ";
         try {
             Query q = em.createNativeQuery(consulta);
             return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void actualizarMuestreo(Muestreo muestreo) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE  muestreo SET s_muestreo_fechinic =?,  "
                + "s_muestreo_planmues = ?, fs_muestreo_tecnico = ? ,"
                + " d_muestreo_fechrecepmuest = ?, fi_muestreo_proyecto = ?, "
                + " fi_muestreo_municipio = ?, fi_muestreo_ciudad = ? , fs_muestreo_usuamodi = ? , d_muestreo_fechmodi = ?  WHERE pi_muestreo_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, muestreo.getFechaInic());
        insercion.setParameter(2, muestreo.getPlanMuestreo());
        insercion.setParameter(3, muestreo.getRealizado().getNombre());
        insercion.setParameter(4, muestreo.getFechaRecepcMuest());
        insercion.setParameter(5, muestreo.getIdProyecto().getIdProyecto());
        insercion.setParameter(6, muestreo.getIdMunicipio().getIdValor());
        insercion.setParameter(7, muestreo.getIdCiudad().getIdValor());
        insercion.setParameter(8, muestreo.getUsuarioModificador().getPsUsuarioCodigo());
        insercion.setParameter(9, new Date());
        insercion.setParameter(10, muestreo.getIdMuestreo());
        insercion.executeUpdate();
        em.getTransaction().commit();
          
    
    
    }
    
     public void actualizarMuestreoObservaciones(Muestreo muestreo) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE  muestreo SET s_muestreo_fechinic =?, "
                + "fi_muestreo_proyecto = ?, fs_muestreo_tecnico = ? ,"
                + " d_muestreo_fechrecepmuest = ?, "
                + " fi_muestreo_municipio = ?, fi_muestreo_ciudad = ? , "
                + " fs_muestreo_usuamodi = ? , d_muestreo_fechmodi = ? ,"
                + " s_muestreo_observacion = ?, s_muestreo_planmues= ? "
                + " WHERE pi_muestreo_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, muestreo.getFechaInic());
        insercion.setParameter(2, muestreo.getIdProyecto().getIdProyecto());
        insercion.setParameter(3, muestreo.getRealizado().getNombre());
        insercion.setParameter(4, muestreo.getFechaRecepcMuest());
        insercion.setParameter(5, muestreo.getIdMunicipio().getIdValor());
        insercion.setParameter(6, muestreo.getIdCiudad().getIdValor());
        insercion.setParameter(7, muestreo.getUsuarioModificador().getPsUsuarioCodigo());
        insercion.setParameter(8, new Date());
        insercion.setParameter(9, muestreo.getObservaciones());
        insercion.setParameter(10, muestreo.getPlanMuestreo());
        insercion.setParameter(11, muestreo.getIdMuestreo());
        insercion.executeUpdate();
        em.getTransaction().commit();
        String insertMuestreoHistorico = "UPDATE  muestreo_historico SET d_muestreo_historico_fechainicio =?, "
                + " fs_muestreo_historico_tecnico = ? ,"
                + " d_muestreo_historico_fecharecepcmuest = ?, "
                + " fi_muestreo_historico_municipio = ?, fi_muestreo_historico_ciudad = ? , "
                + " fs_muestreo_historico_usuamodif = ? , d_muestreo_historico_fechamodi = ? ,"
                + " s_muestreo_historico_planmuestreo= ? "
                + " WHERE pi_muestreo_historico_id= ?";
        em.getTransaction().begin();
        Query insercionMuestreoHistorico = em.createNativeQuery(insertMuestreoHistorico);
        insercionMuestreoHistorico.setParameter(1, muestreo.getFechaInic());
        insercionMuestreoHistorico.setParameter(2, muestreo.getRealizado().getNombre());
        insercionMuestreoHistorico.setParameter(3, muestreo.getFechaRecepcMuest());
        insercionMuestreoHistorico.setParameter(4, muestreo.getIdMunicipio().getIdValor());
        insercionMuestreoHistorico.setParameter(5, muestreo.getIdCiudad().getIdValor());
        insercionMuestreoHistorico.setParameter(6, muestreo.getUsuarioModificador().getPsUsuarioCodigo());
        insercionMuestreoHistorico.setParameter(7, new Date());
        insercionMuestreoHistorico.setParameter(8, muestreo.getPlanMuestreo());
        insercionMuestreoHistorico.setParameter(9, muestreo.getIdMuestreo());
        insercionMuestreoHistorico.executeUpdate();
        em.getTransaction().commit();
      
        
    }

    public List<Integer> encontrarUltimoMuestreo() {
    EntityManager em = getEntityManager();

         String consulta = "SELECT MAX(pi_muestreo_id) AS id FROM muestreo ";
         try {
            Query q = em.createNativeQuery(consulta);
          
            return q.getResultList();
        } finally {
            em.close();
        } 
    
    
    }

    public List<Muestreo> encontrarCadenaCustodia(String filtros, List<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM muestreo";
        consulta += " join reporte on reporte.fi_reporte_muestreo= muestreo.pi_muestreo_id";
        consulta += " WHERE ";
        consulta += filtros;
        try {
            Query q = em.createNativeQuery(consulta, Muestreo.class);
            System.out.println("consulta "+ consulta);
            int j = 1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Muestreo encontrarMuestreoPorId(Integer muestreoLLegado) {
        System.out.println("muestreo llegado "+ muestreoLLegado);
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM muestreo where pi_muestreo_id= ?";
        try {
            Query q = em.createNativeQuery(consulta, Muestreo.class);
            System.out.println("consulta "+ consulta);
            q.setParameter(1, muestreoLLegado);
            return (Muestreo) q.getSingleResult();
        } finally {
            em.close();
        }
    }

   
  
  
    
}
