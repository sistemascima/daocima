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
import entidades.Proyecto;
import entidades.Tecnico;
import entidades.Punto;
import entidades.Reporte;
import java.util.ArrayList;
import java.util.List;
import entidades.Muestra;
import entidades.Muestreo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestreoJpaController1 implements Serializable {

    public MuestreoJpaController1(EntityManagerFactory emf) {
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
            Punto idPunto = muestreo.getIdPunto();
            if (idPunto != null) {
                idPunto = em.getReference(idPunto.getClass(), idPunto.getIdpunto());
                muestreo.setIdPunto(idPunto);
            }
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            for (Reporte reporteListReporteToAttach : muestreo.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getPiReporteId());
                attachedReporteList.add(reporteListReporteToAttach);
            }
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
            if (idPunto != null) {
                idPunto.getMuestreoList().add(muestreo);
                idPunto = em.merge(idPunto);
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
            Punto idPuntoOld = persistentMuestreo.getIdPunto();
            Punto idPuntoNew = muestreo.getIdPunto();
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
            if (idPuntoNew != null) {
                idPuntoNew = em.getReference(idPuntoNew.getClass(), idPuntoNew.getIdpunto());
                muestreo.setIdPunto(idPuntoNew);
            }
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getPiReporteId());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }
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
            if (idPuntoOld != null && !idPuntoOld.equals(idPuntoNew)) {
                idPuntoOld.getMuestreoList().remove(muestreo);
                idPuntoOld = em.merge(idPuntoOld);
            }
            if (idPuntoNew != null && !idPuntoNew.equals(idPuntoOld)) {
                idPuntoNew.getMuestreoList().add(muestreo);
                idPuntoNew = em.merge(idPuntoNew);
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
            Punto idPunto = muestreo.getIdPunto();
            if (idPunto != null) {
                idPunto.getMuestreoList().remove(muestreo);
                idPunto = em.merge(idPunto);
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
    
}
