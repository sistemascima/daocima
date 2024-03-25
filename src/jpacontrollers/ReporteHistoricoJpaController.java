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
import entidades.Cliente;
import entidades.MuestreoHistorico;
import entidades.Proyecto;
import entidades.SUsuario;
import entidades.MuestraHistorico;
import java.util.ArrayList;
import java.util.List;
import entidades.MuestraAnalisisHistorico;
import entidades.ReporteHistorico;
import entidades.ReporteHistoricoPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ReporteHistoricoJpaController implements Serializable {

    public ReporteHistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReporteHistorico reporteHistorico) throws PreexistingEntityException, Exception {
        if (reporteHistorico.getReporteHistoricoPK() == null) {
            reporteHistorico.setReporteHistoricoPK(new ReporteHistoricoPK());
        }
        if (reporteHistorico.getMuestraHistoricoList() == null) {
            reporteHistorico.setMuestraHistoricoList(new ArrayList<MuestraHistorico>());
        }
        if (reporteHistorico.getMuestraAnalisisHistoricoList() == null) {
            reporteHistorico.setMuestraAnalisisHistoricoList(new ArrayList<MuestraAnalisisHistorico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente psReporteHistoricoCliente = reporteHistorico.getPsReporteHistoricoCliente();
            if (psReporteHistoricoCliente != null) {
                psReporteHistoricoCliente = em.getReference(psReporteHistoricoCliente.getClass(), psReporteHistoricoCliente.getClientePK());
                reporteHistorico.setPsReporteHistoricoCliente(psReporteHistoricoCliente);
            }
            Cliente fksReporteHistoricoClienteFacturacion = reporteHistorico.getFksReporteHistoricoClienteFacturacion();
            if (fksReporteHistoricoClienteFacturacion != null) {
                fksReporteHistoricoClienteFacturacion = em.getReference(fksReporteHistoricoClienteFacturacion.getClass(), fksReporteHistoricoClienteFacturacion.getClientePK());
                reporteHistorico.setFksReporteHistoricoClienteFacturacion(fksReporteHistoricoClienteFacturacion);
            }
            MuestreoHistorico fiReporteHistoricoMuestreo = reporteHistorico.getFiReporteHistoricoMuestreo();
            if (fiReporteHistoricoMuestreo != null) {
                fiReporteHistoricoMuestreo = em.getReference(fiReporteHistoricoMuestreo.getClass(), fiReporteHistoricoMuestreo.getPiMuestreoHistoricoId());
                reporteHistorico.setFiReporteHistoricoMuestreo(fiReporteHistoricoMuestreo);
            }
            Proyecto fiReporteHistoricoProyecto = reporteHistorico.getFiReporteHistoricoProyecto();
            if (fiReporteHistoricoProyecto != null) {
                fiReporteHistoricoProyecto = em.getReference(fiReporteHistoricoProyecto.getClass(), fiReporteHistoricoProyecto.getIdProyecto());
                reporteHistorico.setFiReporteHistoricoProyecto(fiReporteHistoricoProyecto);
            }
            SUsuario fsReporteHistoricoUsuaaprobacion = reporteHistorico.getFsReporteHistoricoUsuaaprobacion();
            if (fsReporteHistoricoUsuaaprobacion != null) {
                fsReporteHistoricoUsuaaprobacion = em.getReference(fsReporteHistoricoUsuaaprobacion.getClass(), fsReporteHistoricoUsuaaprobacion.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuaaprobacion(fsReporteHistoricoUsuaaprobacion);
            }
            SUsuario fsReporteHistoricoUsuacreacion = reporteHistorico.getFsReporteHistoricoUsuacreacion();
            if (fsReporteHistoricoUsuacreacion != null) {
                fsReporteHistoricoUsuacreacion = em.getReference(fsReporteHistoricoUsuacreacion.getClass(), fsReporteHistoricoUsuacreacion.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuacreacion(fsReporteHistoricoUsuacreacion);
            }
            SUsuario fsReporteHistoricoUsuariomodificacion = reporteHistorico.getFsReporteHistoricoUsuariomodificacion();
            if (fsReporteHistoricoUsuariomodificacion != null) {
                fsReporteHistoricoUsuariomodificacion = em.getReference(fsReporteHistoricoUsuariomodificacion.getClass(), fsReporteHistoricoUsuariomodificacion.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuariomodificacion(fsReporteHistoricoUsuariomodificacion);
            }
            List<MuestraHistorico> attachedMuestraHistoricoList = new ArrayList<MuestraHistorico>();
            for (MuestraHistorico muestraHistoricoListMuestraHistoricoToAttach : reporteHistorico.getMuestraHistoricoList()) {
                muestraHistoricoListMuestraHistoricoToAttach = em.getReference(muestraHistoricoListMuestraHistoricoToAttach.getClass(), muestraHistoricoListMuestraHistoricoToAttach.getMuestraHistoricoPK());
                attachedMuestraHistoricoList.add(muestraHistoricoListMuestraHistoricoToAttach);
            }
            reporteHistorico.setMuestraHistoricoList(attachedMuestraHistoricoList);
            List<MuestraAnalisisHistorico> attachedMuestraAnalisisHistoricoList = new ArrayList<MuestraAnalisisHistorico>();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach : reporteHistorico.getMuestraAnalisisHistoricoList()) {
                muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach = em.getReference(muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach.getClass(), muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach.getMuestraAnalisisHistoricoPK());
                attachedMuestraAnalisisHistoricoList.add(muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach);
            }
            reporteHistorico.setMuestraAnalisisHistoricoList(attachedMuestraAnalisisHistoricoList);
            em.persist(reporteHistorico);
            if (psReporteHistoricoCliente != null) {
                psReporteHistoricoCliente.getReporteHistoricoList().add(reporteHistorico);
                psReporteHistoricoCliente = em.merge(psReporteHistoricoCliente);
            }
            if (fksReporteHistoricoClienteFacturacion != null) {
                fksReporteHistoricoClienteFacturacion.getReporteHistoricoList().add(reporteHistorico);
                fksReporteHistoricoClienteFacturacion = em.merge(fksReporteHistoricoClienteFacturacion);
            }
            if (fiReporteHistoricoMuestreo != null) {
                fiReporteHistoricoMuestreo.getReporteHistoricoList().add(reporteHistorico);
                fiReporteHistoricoMuestreo = em.merge(fiReporteHistoricoMuestreo);
            }
            if (fiReporteHistoricoProyecto != null) {
                fiReporteHistoricoProyecto.getReporteHistoricoList().add(reporteHistorico);
                fiReporteHistoricoProyecto = em.merge(fiReporteHistoricoProyecto);
            }
          /*  if (fsReporteHistoricoUsuaaprobacion != null) {
                fsReporteHistoricoUsuaaprobacion.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuaaprobacion = em.merge(fsReporteHistoricoUsuaaprobacion);
            }
            if (fsReporteHistoricoUsuacreacion != null) {
                fsReporteHistoricoUsuacreacion.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuacreacion = em.merge(fsReporteHistoricoUsuacreacion);
            }
            if (fsReporteHistoricoUsuariomodificacion != null) {
                fsReporteHistoricoUsuariomodificacion.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuariomodificacion = em.merge(fsReporteHistoricoUsuariomodificacion);
            }*/
            for (MuestraHistorico muestraHistoricoListMuestraHistorico : reporteHistorico.getMuestraHistoricoList()) {
                ReporteHistorico oldReporteHistoricoOfMuestraHistoricoListMuestraHistorico = muestraHistoricoListMuestraHistorico.getReporteHistorico();
                muestraHistoricoListMuestraHistorico.setReporteHistorico(reporteHistorico);
                muestraHistoricoListMuestraHistorico = em.merge(muestraHistoricoListMuestraHistorico);
                if (oldReporteHistoricoOfMuestraHistoricoListMuestraHistorico != null) {
                    oldReporteHistoricoOfMuestraHistoricoListMuestraHistorico.getMuestraHistoricoList().remove(muestraHistoricoListMuestraHistorico);
                    oldReporteHistoricoOfMuestraHistoricoListMuestraHistorico = em.merge(oldReporteHistoricoOfMuestraHistoricoListMuestraHistorico);
                }
            }
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListMuestraAnalisisHistorico : reporteHistorico.getMuestraAnalisisHistoricoList()) {
                ReporteHistorico oldReporteHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico = muestraAnalisisHistoricoListMuestraAnalisisHistorico.getReporteHistorico();
                muestraAnalisisHistoricoListMuestraAnalisisHistorico.setReporteHistorico(reporteHistorico);
                muestraAnalisisHistoricoListMuestraAnalisisHistorico = em.merge(muestraAnalisisHistoricoListMuestraAnalisisHistorico);
                if (oldReporteHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico != null) {
                    oldReporteHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistoricoListMuestraAnalisisHistorico);
                    oldReporteHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico = em.merge(oldReporteHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReporteHistorico(reporteHistorico.getReporteHistoricoPK()) != null) {
                throw new PreexistingEntityException("ReporteHistorico " + reporteHistorico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReporteHistorico reporteHistorico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteHistorico persistentReporteHistorico = em.find(ReporteHistorico.class, reporteHistorico.getReporteHistoricoPK());
            Cliente psReporteHistoricoClienteOld = persistentReporteHistorico.getPsReporteHistoricoCliente();
            Cliente psReporteHistoricoClienteNew = reporteHistorico.getPsReporteHistoricoCliente();
            Cliente fksReporteHistoricoClienteFacturacionOld = persistentReporteHistorico.getFksReporteHistoricoClienteFacturacion();
            Cliente fksReporteHistoricoClienteFacturacionNew = reporteHistorico.getFksReporteHistoricoClienteFacturacion();
            MuestreoHistorico fiReporteHistoricoMuestreoOld = persistentReporteHistorico.getFiReporteHistoricoMuestreo();
            MuestreoHistorico fiReporteHistoricoMuestreoNew = reporteHistorico.getFiReporteHistoricoMuestreo();
            Proyecto fiReporteHistoricoProyectoOld = persistentReporteHistorico.getFiReporteHistoricoProyecto();
            Proyecto fiReporteHistoricoProyectoNew = reporteHistorico.getFiReporteHistoricoProyecto();
            SUsuario fsReporteHistoricoUsuaaprobacionOld = persistentReporteHistorico.getFsReporteHistoricoUsuaaprobacion();
            SUsuario fsReporteHistoricoUsuaaprobacionNew = reporteHistorico.getFsReporteHistoricoUsuaaprobacion();
            SUsuario fsReporteHistoricoUsuacreacionOld = persistentReporteHistorico.getFsReporteHistoricoUsuacreacion();
            SUsuario fsReporteHistoricoUsuacreacionNew = reporteHistorico.getFsReporteHistoricoUsuacreacion();
            SUsuario fsReporteHistoricoUsuariomodificacionOld = persistentReporteHistorico.getFsReporteHistoricoUsuariomodificacion();
            SUsuario fsReporteHistoricoUsuariomodificacionNew = reporteHistorico.getFsReporteHistoricoUsuariomodificacion();
            List<MuestraHistorico> muestraHistoricoListOld = persistentReporteHistorico.getMuestraHistoricoList();
            List<MuestraHistorico> muestraHistoricoListNew = reporteHistorico.getMuestraHistoricoList();
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListOld = persistentReporteHistorico.getMuestraAnalisisHistoricoList();
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListNew = reporteHistorico.getMuestraAnalisisHistoricoList();
            List<String> illegalOrphanMessages = null;
            for (MuestraHistorico muestraHistoricoListOldMuestraHistorico : muestraHistoricoListOld) {
                if (!muestraHistoricoListNew.contains(muestraHistoricoListOldMuestraHistorico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MuestraHistorico " + muestraHistoricoListOldMuestraHistorico + " since its reporteHistorico field is not nullable.");
                }
            }
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListOldMuestraAnalisisHistorico : muestraAnalisisHistoricoListOld) {
                if (!muestraAnalisisHistoricoListNew.contains(muestraAnalisisHistoricoListOldMuestraAnalisisHistorico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MuestraAnalisisHistorico " + muestraAnalisisHistoricoListOldMuestraAnalisisHistorico + " since its reporteHistorico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (psReporteHistoricoClienteNew != null) {
                psReporteHistoricoClienteNew = em.getReference(psReporteHistoricoClienteNew.getClass(), psReporteHistoricoClienteNew.getClientePK());
                reporteHistorico.setPsReporteHistoricoCliente(psReporteHistoricoClienteNew);
            }
            if (fksReporteHistoricoClienteFacturacionNew != null) {
                fksReporteHistoricoClienteFacturacionNew = em.getReference(fksReporteHistoricoClienteFacturacionNew.getClass(), fksReporteHistoricoClienteFacturacionNew.getClientePK());
                reporteHistorico.setFksReporteHistoricoClienteFacturacion(fksReporteHistoricoClienteFacturacionNew);
            }
            if (fiReporteHistoricoMuestreoNew != null) {
                fiReporteHistoricoMuestreoNew = em.getReference(fiReporteHistoricoMuestreoNew.getClass(), fiReporteHistoricoMuestreoNew.getPiMuestreoHistoricoId());
                reporteHistorico.setFiReporteHistoricoMuestreo(fiReporteHistoricoMuestreoNew);
            }
            if (fiReporteHistoricoProyectoNew != null) {
                fiReporteHistoricoProyectoNew = em.getReference(fiReporteHistoricoProyectoNew.getClass(), fiReporteHistoricoProyectoNew.getIdProyecto());
                reporteHistorico.setFiReporteHistoricoProyecto(fiReporteHistoricoProyectoNew);
            }
            if (fsReporteHistoricoUsuaaprobacionNew != null) {
                fsReporteHistoricoUsuaaprobacionNew = em.getReference(fsReporteHistoricoUsuaaprobacionNew.getClass(), fsReporteHistoricoUsuaaprobacionNew.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuaaprobacion(fsReporteHistoricoUsuaaprobacionNew);
            }
            if (fsReporteHistoricoUsuacreacionNew != null) {
                fsReporteHistoricoUsuacreacionNew = em.getReference(fsReporteHistoricoUsuacreacionNew.getClass(), fsReporteHistoricoUsuacreacionNew.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuacreacion(fsReporteHistoricoUsuacreacionNew);
            }
            if (fsReporteHistoricoUsuariomodificacionNew != null) {
                fsReporteHistoricoUsuariomodificacionNew = em.getReference(fsReporteHistoricoUsuariomodificacionNew.getClass(), fsReporteHistoricoUsuariomodificacionNew.getPsUsuarioCodigo());
                reporteHistorico.setFsReporteHistoricoUsuariomodificacion(fsReporteHistoricoUsuariomodificacionNew);
            }
            List<MuestraHistorico> attachedMuestraHistoricoListNew = new ArrayList<MuestraHistorico>();
            for (MuestraHistorico muestraHistoricoListNewMuestraHistoricoToAttach : muestraHistoricoListNew) {
                muestraHistoricoListNewMuestraHistoricoToAttach = em.getReference(muestraHistoricoListNewMuestraHistoricoToAttach.getClass(), muestraHistoricoListNewMuestraHistoricoToAttach.getMuestraHistoricoPK());
                attachedMuestraHistoricoListNew.add(muestraHistoricoListNewMuestraHistoricoToAttach);
            }
            muestraHistoricoListNew = attachedMuestraHistoricoListNew;
            reporteHistorico.setMuestraHistoricoList(muestraHistoricoListNew);
            List<MuestraAnalisisHistorico> attachedMuestraAnalisisHistoricoListNew = new ArrayList<MuestraAnalisisHistorico>();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach : muestraAnalisisHistoricoListNew) {
                muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach = em.getReference(muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach.getClass(), muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach.getMuestraAnalisisHistoricoPK());
                attachedMuestraAnalisisHistoricoListNew.add(muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach);
            }
            muestraAnalisisHistoricoListNew = attachedMuestraAnalisisHistoricoListNew;
            reporteHistorico.setMuestraAnalisisHistoricoList(muestraAnalisisHistoricoListNew);
            reporteHistorico = em.merge(reporteHistorico);
            if (psReporteHistoricoClienteOld != null && !psReporteHistoricoClienteOld.equals(psReporteHistoricoClienteNew)) {
                psReporteHistoricoClienteOld.getReporteHistoricoList().remove(reporteHistorico);
                psReporteHistoricoClienteOld = em.merge(psReporteHistoricoClienteOld);
            }
            if (psReporteHistoricoClienteNew != null && !psReporteHistoricoClienteNew.equals(psReporteHistoricoClienteOld)) {
                psReporteHistoricoClienteNew.getReporteHistoricoList().add(reporteHistorico);
                psReporteHistoricoClienteNew = em.merge(psReporteHistoricoClienteNew);
            }
            if (fksReporteHistoricoClienteFacturacionOld != null && !fksReporteHistoricoClienteFacturacionOld.equals(fksReporteHistoricoClienteFacturacionNew)) {
                fksReporteHistoricoClienteFacturacionOld.getReporteHistoricoList().remove(reporteHistorico);
                fksReporteHistoricoClienteFacturacionOld = em.merge(fksReporteHistoricoClienteFacturacionOld);
            }
            if (fksReporteHistoricoClienteFacturacionNew != null && !fksReporteHistoricoClienteFacturacionNew.equals(fksReporteHistoricoClienteFacturacionOld)) {
                fksReporteHistoricoClienteFacturacionNew.getReporteHistoricoList().add(reporteHistorico);
                fksReporteHistoricoClienteFacturacionNew = em.merge(fksReporteHistoricoClienteFacturacionNew);
            }
            if (fiReporteHistoricoMuestreoOld != null && !fiReporteHistoricoMuestreoOld.equals(fiReporteHistoricoMuestreoNew)) {
                fiReporteHistoricoMuestreoOld.getReporteHistoricoList().remove(reporteHistorico);
                fiReporteHistoricoMuestreoOld = em.merge(fiReporteHistoricoMuestreoOld);
            }
            if (fiReporteHistoricoMuestreoNew != null && !fiReporteHistoricoMuestreoNew.equals(fiReporteHistoricoMuestreoOld)) {
                fiReporteHistoricoMuestreoNew.getReporteHistoricoList().add(reporteHistorico);
                fiReporteHistoricoMuestreoNew = em.merge(fiReporteHistoricoMuestreoNew);
            }
            if (fiReporteHistoricoProyectoOld != null && !fiReporteHistoricoProyectoOld.equals(fiReporteHistoricoProyectoNew)) {
                fiReporteHistoricoProyectoOld.getReporteHistoricoList().remove(reporteHistorico);
                fiReporteHistoricoProyectoOld = em.merge(fiReporteHistoricoProyectoOld);
            }
            if (fiReporteHistoricoProyectoNew != null && !fiReporteHistoricoProyectoNew.equals(fiReporteHistoricoProyectoOld)) {
                fiReporteHistoricoProyectoNew.getReporteHistoricoList().add(reporteHistorico);
                fiReporteHistoricoProyectoNew = em.merge(fiReporteHistoricoProyectoNew);
            }
         /*   if (fsReporteHistoricoUsuaaprobacionOld != null && !fsReporteHistoricoUsuaaprobacionOld.equals(fsReporteHistoricoUsuaaprobacionNew)) {
                fsReporteHistoricoUsuaaprobacionOld.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuaaprobacionOld = em.merge(fsReporteHistoricoUsuaaprobacionOld);
            }
            if (fsReporteHistoricoUsuaaprobacionNew != null && !fsReporteHistoricoUsuaaprobacionNew.equals(fsReporteHistoricoUsuaaprobacionOld)) {
                fsReporteHistoricoUsuaaprobacionNew.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuaaprobacionNew = em.merge(fsReporteHistoricoUsuaaprobacionNew);
            }
            if (fsReporteHistoricoUsuacreacionOld != null && !fsReporteHistoricoUsuacreacionOld.equals(fsReporteHistoricoUsuacreacionNew)) {
                fsReporteHistoricoUsuacreacionOld.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuacreacionOld = em.merge(fsReporteHistoricoUsuacreacionOld);
            }
            if (fsReporteHistoricoUsuacreacionNew != null && !fsReporteHistoricoUsuacreacionNew.equals(fsReporteHistoricoUsuacreacionOld)) {
                fsReporteHistoricoUsuacreacionNew.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuacreacionNew = em.merge(fsReporteHistoricoUsuacreacionNew);
            }
            if (fsReporteHistoricoUsuariomodificacionOld != null && !fsReporteHistoricoUsuariomodificacionOld.equals(fsReporteHistoricoUsuariomodificacionNew)) {
                fsReporteHistoricoUsuariomodificacionOld.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuariomodificacionOld = em.merge(fsReporteHistoricoUsuariomodificacionOld);
            }
            if (fsReporteHistoricoUsuariomodificacionNew != null && !fsReporteHistoricoUsuariomodificacionNew.equals(fsReporteHistoricoUsuariomodificacionOld)) {
                fsReporteHistoricoUsuariomodificacionNew.getReporteHistoricoList().add(reporteHistorico);
                fsReporteHistoricoUsuariomodificacionNew = em.merge(fsReporteHistoricoUsuariomodificacionNew);
            }*/
            for (MuestraHistorico muestraHistoricoListNewMuestraHistorico : muestraHistoricoListNew) {
                if (!muestraHistoricoListOld.contains(muestraHistoricoListNewMuestraHistorico)) {
                    ReporteHistorico oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico = muestraHistoricoListNewMuestraHistorico.getReporteHistorico();
                    muestraHistoricoListNewMuestraHistorico.setReporteHistorico(reporteHistorico);
                    muestraHistoricoListNewMuestraHistorico = em.merge(muestraHistoricoListNewMuestraHistorico);
                    if (oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico != null && !oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico.equals(reporteHistorico)) {
                        oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico.getMuestraHistoricoList().remove(muestraHistoricoListNewMuestraHistorico);
                        oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico = em.merge(oldReporteHistoricoOfMuestraHistoricoListNewMuestraHistorico);
                    }
                }
            }
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListNewMuestraAnalisisHistorico : muestraAnalisisHistoricoListNew) {
                if (!muestraAnalisisHistoricoListOld.contains(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico)) {
                    ReporteHistorico oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico = muestraAnalisisHistoricoListNewMuestraAnalisisHistorico.getReporteHistorico();
                    muestraAnalisisHistoricoListNewMuestraAnalisisHistorico.setReporteHistorico(reporteHistorico);
                    muestraAnalisisHistoricoListNewMuestraAnalisisHistorico = em.merge(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                    if (oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico != null && !oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico.equals(reporteHistorico)) {
                        oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                        oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico = em.merge(oldReporteHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReporteHistoricoPK id = reporteHistorico.getReporteHistoricoPK();
                if (findReporteHistorico(id) == null) {
                    throw new NonexistentEntityException("The reporteHistorico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReporteHistoricoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteHistorico reporteHistorico;
            try {
                reporteHistorico = em.getReference(ReporteHistorico.class, id);
                reporteHistorico.getReporteHistoricoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteHistorico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MuestraHistorico> muestraHistoricoListOrphanCheck = reporteHistorico.getMuestraHistoricoList();
            for (MuestraHistorico muestraHistoricoListOrphanCheckMuestraHistorico : muestraHistoricoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReporteHistorico (" + reporteHistorico + ") cannot be destroyed since the MuestraHistorico " + muestraHistoricoListOrphanCheckMuestraHistorico + " in its muestraHistoricoList field has a non-nullable reporteHistorico field.");
            }
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListOrphanCheck = reporteHistorico.getMuestraAnalisisHistoricoList();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListOrphanCheckMuestraAnalisisHistorico : muestraAnalisisHistoricoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReporteHistorico (" + reporteHistorico + ") cannot be destroyed since the MuestraAnalisisHistorico " + muestraAnalisisHistoricoListOrphanCheckMuestraAnalisisHistorico + " in its muestraAnalisisHistoricoList field has a non-nullable reporteHistorico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente psReporteHistoricoCliente = reporteHistorico.getPsReporteHistoricoCliente();
            if (psReporteHistoricoCliente != null) {
                psReporteHistoricoCliente.getReporteHistoricoList().remove(reporteHistorico);
                psReporteHistoricoCliente = em.merge(psReporteHistoricoCliente);
            }
            Cliente fksReporteHistoricoClienteFacturacion = reporteHistorico.getFksReporteHistoricoClienteFacturacion();
            if (fksReporteHistoricoClienteFacturacion != null) {
                fksReporteHistoricoClienteFacturacion.getReporteHistoricoList().remove(reporteHistorico);
                fksReporteHistoricoClienteFacturacion = em.merge(fksReporteHistoricoClienteFacturacion);
            }
            MuestreoHistorico fiReporteHistoricoMuestreo = reporteHistorico.getFiReporteHistoricoMuestreo();
            if (fiReporteHistoricoMuestreo != null) {
                fiReporteHistoricoMuestreo.getReporteHistoricoList().remove(reporteHistorico);
                fiReporteHistoricoMuestreo = em.merge(fiReporteHistoricoMuestreo);
            }
            Proyecto fiReporteHistoricoProyecto = reporteHistorico.getFiReporteHistoricoProyecto();
            if (fiReporteHistoricoProyecto != null) {
                fiReporteHistoricoProyecto.getReporteHistoricoList().remove(reporteHistorico);
                fiReporteHistoricoProyecto = em.merge(fiReporteHistoricoProyecto);
            }
            SUsuario fsReporteHistoricoUsuaaprobacion = reporteHistorico.getFsReporteHistoricoUsuaaprobacion();
            /*if (fsReporteHistoricoUsuaaprobacion != null) {
                fsReporteHistoricoUsuaaprobacion.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuaaprobacion = em.merge(fsReporteHistoricoUsuaaprobacion);
            }
            SUsuario fsReporteHistoricoUsuacreacion = reporteHistorico.getFsReporteHistoricoUsuacreacion();
            if (fsReporteHistoricoUsuacreacion != null) {
                fsReporteHistoricoUsuacreacion.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuacreacion = em.merge(fsReporteHistoricoUsuacreacion);
            }
            SUsuario fsReporteHistoricoUsuariomodificacion = reporteHistorico.getFsReporteHistoricoUsuariomodificacion();
            if (fsReporteHistoricoUsuariomodificacion != null) {
                fsReporteHistoricoUsuariomodificacion.getReporteHistoricoList().remove(reporteHistorico);
                fsReporteHistoricoUsuariomodificacion = em.merge(fsReporteHistoricoUsuariomodificacion);
            }*/
            em.remove(reporteHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReporteHistorico> findReporteHistoricoEntities() {
        return findReporteHistoricoEntities(true, -1, -1);
    }

    public List<ReporteHistorico> findReporteHistoricoEntities(int maxResults, int firstResult) {
        return findReporteHistoricoEntities(false, maxResults, firstResult);
    }

    private List<ReporteHistorico> findReporteHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReporteHistorico.class));
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

    public ReporteHistorico findReporteHistorico(ReporteHistoricoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReporteHistorico.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReporteHistorico> rt = cq.from(ReporteHistorico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Integer> encontrarVersionesReporte(String idReporte) {
     EntityManager em = getEntityManager();
            String consulta = "select i_reporte_historico_version from reporte_historico " +
        "where pi_reporte_historico_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
