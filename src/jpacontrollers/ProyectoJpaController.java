/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import entidades.Muestreo;
import java.util.ArrayList;
import java.util.List;
import entidades.Reporte;
import entidades.Muestra;
import entidades.Proyecto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) throws PreexistingEntityException, Exception {
        if (proyecto.getMuestreoList() == null) {
            proyecto.setMuestreoList(new ArrayList<Muestreo>());
        }
        if (proyecto.getReporteList() == null) {
            proyecto.setReporteList(new ArrayList<Reporte>());
        }
        if (proyecto.getMuestraList() == null) {
            proyecto.setMuestraList(new ArrayList<Muestra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Muestreo> attachedMuestreoList = new ArrayList<Muestreo>();
            for (Muestreo muestreoListMuestreoToAttach : proyecto.getMuestreoList()) {
                muestreoListMuestreoToAttach = em.getReference(muestreoListMuestreoToAttach.getClass(), muestreoListMuestreoToAttach.getIdMuestreo());
                attachedMuestreoList.add(muestreoListMuestreoToAttach);
            }
            proyecto.setMuestreoList(attachedMuestreoList);
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
           /* for (Reporte reporteListReporteToAttach : proyecto.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdreporte());
                attachedReporteList.add(reporteListReporteToAttach);
            }*/
            proyecto.setReporteList(attachedReporteList);
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : proyecto.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getIdmuestra());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            proyecto.setMuestraList(attachedMuestraList);
            em.persist(proyecto);
            for (Muestreo muestreoListMuestreo : proyecto.getMuestreoList()) {
                Proyecto oldIdProyectoOfMuestreoListMuestreo = muestreoListMuestreo.getIdProyecto();
                muestreoListMuestreo.setIdProyecto(proyecto);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
                if (oldIdProyectoOfMuestreoListMuestreo != null) {
                    oldIdProyectoOfMuestreoListMuestreo.getMuestreoList().remove(muestreoListMuestreo);
                    oldIdProyectoOfMuestreoListMuestreo = em.merge(oldIdProyectoOfMuestreoListMuestreo);
                }
            }
            for (Reporte reporteListReporte : proyecto.getReporteList()) {
                Proyecto oldIdProyectoOfReporteListReporte = reporteListReporte.getIdProyecto();
                reporteListReporte.setIdProyecto(proyecto);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldIdProyectoOfReporteListReporte != null) {
                    oldIdProyectoOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldIdProyectoOfReporteListReporte = em.merge(oldIdProyectoOfReporteListReporte);
                }
            }
            for (Muestra muestraListMuestra : proyecto.getMuestraList()) {
                Proyecto oldProyectoOfMuestraListMuestra = muestraListMuestra.getProyecto();
                muestraListMuestra.setProyecto(proyecto);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldProyectoOfMuestraListMuestra != null) {
                    oldProyectoOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldProyectoOfMuestraListMuestra = em.merge(oldProyectoOfMuestraListMuestra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProyecto(proyecto.getIdProyecto()) != null) {
                throw new PreexistingEntityException("Proyecto " + proyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getIdProyecto());
            List<Muestreo> muestreoListOld = persistentProyecto.getMuestreoList();
            List<Muestreo> muestreoListNew = proyecto.getMuestreoList();
            List<Reporte> reporteListOld = persistentProyecto.getReporteList();
            List<Reporte> reporteListNew = proyecto.getReporteList();
            List<Muestra> muestraListOld = persistentProyecto.getMuestraList();
            List<Muestra> muestraListNew = proyecto.getMuestraList();
            List<String> illegalOrphanMessages = null;
            for (Muestreo muestreoListOldMuestreo : muestreoListOld) {
                if (!muestreoListNew.contains(muestreoListOldMuestreo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestreo " + muestreoListOldMuestreo + " since its idProyecto field is not nullable.");
                }
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its idProyecto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Muestreo> attachedMuestreoListNew = new ArrayList<Muestreo>();
            for (Muestreo muestreoListNewMuestreoToAttach : muestreoListNew) {
                muestreoListNewMuestreoToAttach = em.getReference(muestreoListNewMuestreoToAttach.getClass(), muestreoListNewMuestreoToAttach.getIdMuestreo());
                attachedMuestreoListNew.add(muestreoListNewMuestreoToAttach);
            }
            muestreoListNew = attachedMuestreoListNew;
            proyecto.setMuestreoList(muestreoListNew);
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
          /*  for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdreporte());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }*/
            reporteListNew = attachedReporteListNew;
            proyecto.setReporteList(reporteListNew);
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getIdmuestra());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            proyecto.setMuestraList(muestraListNew);
            proyecto = em.merge(proyecto);
            for (Muestreo muestreoListNewMuestreo : muestreoListNew) {
                if (!muestreoListOld.contains(muestreoListNewMuestreo)) {
                    Proyecto oldIdProyectoOfMuestreoListNewMuestreo = muestreoListNewMuestreo.getIdProyecto();
                    muestreoListNewMuestreo.setIdProyecto(proyecto);
                    muestreoListNewMuestreo = em.merge(muestreoListNewMuestreo);
                    if (oldIdProyectoOfMuestreoListNewMuestreo != null && !oldIdProyectoOfMuestreoListNewMuestreo.equals(proyecto)) {
                        oldIdProyectoOfMuestreoListNewMuestreo.getMuestreoList().remove(muestreoListNewMuestreo);
                        oldIdProyectoOfMuestreoListNewMuestreo = em.merge(oldIdProyectoOfMuestreoListNewMuestreo);
                    }
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Proyecto oldIdProyectoOfReporteListNewReporte = reporteListNewReporte.getIdProyecto();
                    reporteListNewReporte.setIdProyecto(proyecto);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldIdProyectoOfReporteListNewReporte != null && !oldIdProyectoOfReporteListNewReporte.equals(proyecto)) {
                        oldIdProyectoOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldIdProyectoOfReporteListNewReporte = em.merge(oldIdProyectoOfReporteListNewReporte);
                    }
                }
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    muestraListOldMuestra.setProyecto(null);
                    muestraListOldMuestra = em.merge(muestraListOldMuestra);
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Proyecto oldProyectoOfMuestraListNewMuestra = muestraListNewMuestra.getProyecto();
                    muestraListNewMuestra.setProyecto(proyecto);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldProyectoOfMuestraListNewMuestra != null && !oldProyectoOfMuestraListNewMuestra.equals(proyecto)) {
                        oldProyectoOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldProyectoOfMuestraListNewMuestra = em.merge(oldProyectoOfMuestraListNewMuestra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proyecto.getIdProyecto();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getIdProyecto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestreo> muestreoListOrphanCheck = proyecto.getMuestreoList();
            for (Muestreo muestreoListOrphanCheckMuestreo : muestreoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Muestreo " + muestreoListOrphanCheckMuestreo + " in its muestreoList field has a non-nullable idProyecto field.");
            }
            List<Reporte> reporteListOrphanCheck = proyecto.getReporteList();
            for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proyecto (" + proyecto + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable idProyecto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Muestra> muestraList = proyecto.getMuestraList();
            for (Muestra muestraListMuestra : muestraList) {
                muestraListMuestra.setProyecto(null);
                muestraListMuestra = em.merge(muestraListMuestra);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crear(Proyecto proy) {
     EntityManager em = getEntityManager();  
        try {
         String insert= "INSERT INTO proyecto (s_proyecto_nombre) VALUES (?)";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, proy.getNombre());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    }

    public List<Proyecto> encontrarProyecto(String nombre) {
    EntityManager em = getEntityManager();   
        String consulta = "SELECT * FROM proyecto where s_proyecto_nombre = ?";
        try {
            Query q = em.createNativeQuery(consulta, Proyecto.class);
            q.setParameter(1, nombre);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> encontrarProyectoMuestra(int idmuestra) {
       EntityManager em = getEntityManager();
       
        String consulta = "SELECT proyecto.s_proyecto_nombre FROM cima.muestreo join proyecto on proyecto.pi_proyecto_id= muestreo.fi_muestreo_proyecto" +
                          " join muestra on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id where muestra.pi_muestra_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idmuestra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    
}
