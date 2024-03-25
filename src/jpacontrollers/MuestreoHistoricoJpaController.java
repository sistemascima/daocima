/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MuestreoHistorico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Punto;
import entidades.ReporteHistorico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestreoHistoricoJpaController implements Serializable {

    public MuestreoHistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestreoHistorico muestreoHistorico) throws PreexistingEntityException, Exception {
        if (muestreoHistorico.getReporteHistoricoList() == null) {
            muestreoHistorico.setReporteHistoricoList(new ArrayList<ReporteHistorico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsMuestreoHistoricoUsuacreacion = muestreoHistorico.getFsMuestreoHistoricoUsuacreacion();
            if (fsMuestreoHistoricoUsuacreacion != null) {
                fsMuestreoHistoricoUsuacreacion = em.getReference(fsMuestreoHistoricoUsuacreacion.getClass(), fsMuestreoHistoricoUsuacreacion.getPsUsuarioCodigo());
                muestreoHistorico.setFsMuestreoHistoricoUsuacreacion(fsMuestreoHistoricoUsuacreacion);
            }
            SUsuario fsMuestreoHistoricoUsuamodif = muestreoHistorico.getFsMuestreoHistoricoUsuamodif();
            if (fsMuestreoHistoricoUsuamodif != null) {
                fsMuestreoHistoricoUsuamodif = em.getReference(fsMuestreoHistoricoUsuamodif.getClass(), fsMuestreoHistoricoUsuamodif.getPsUsuarioCodigo());
                muestreoHistorico.setFsMuestreoHistoricoUsuamodif(fsMuestreoHistoricoUsuamodif);
            }
            Punto fiMuestreoHistoricoPunto = muestreoHistorico.getFiMuestreoHistoricoPunto();
            if (fiMuestreoHistoricoPunto != null) {
                fiMuestreoHistoricoPunto = em.getReference(fiMuestreoHistoricoPunto.getClass(), fiMuestreoHistoricoPunto.getIdpunto());
                muestreoHistorico.setFiMuestreoHistoricoPunto(fiMuestreoHistoricoPunto);
            }
            List<ReporteHistorico> attachedReporteHistoricoList = new ArrayList<ReporteHistorico>();
            for (ReporteHistorico reporteHistoricoListReporteHistoricoToAttach : muestreoHistorico.getReporteHistoricoList()) {
                reporteHistoricoListReporteHistoricoToAttach = em.getReference(reporteHistoricoListReporteHistoricoToAttach.getClass(), reporteHistoricoListReporteHistoricoToAttach.getReporteHistoricoPK());
                attachedReporteHistoricoList.add(reporteHistoricoListReporteHistoricoToAttach);
            }
            muestreoHistorico.setReporteHistoricoList(attachedReporteHistoricoList);
            em.persist(muestreoHistorico);
          /*  if (fsMuestreoHistoricoUsuacreacion != null) {
                fsMuestreoHistoricoUsuacreacion.getMuestreoHistoricoList().add(muestreoHistorico);
                fsMuestreoHistoricoUsuacreacion = em.merge(fsMuestreoHistoricoUsuacreacion);
            }
            if (fsMuestreoHistoricoUsuamodif != null) {
                fsMuestreoHistoricoUsuamodif.getMuestreoHistoricoList().add(muestreoHistorico);
                fsMuestreoHistoricoUsuamodif = em.merge(fsMuestreoHistoricoUsuamodif);
            }*/
            if (fiMuestreoHistoricoPunto != null) {
                fiMuestreoHistoricoPunto.getMuestreoHistoricoList().add(muestreoHistorico);
                fiMuestreoHistoricoPunto = em.merge(fiMuestreoHistoricoPunto);
            }
            for (ReporteHistorico reporteHistoricoListReporteHistorico : muestreoHistorico.getReporteHistoricoList()) {
                MuestreoHistorico oldFiReporteHistoricoMuestreoOfReporteHistoricoListReporteHistorico = reporteHistoricoListReporteHistorico.getFiReporteHistoricoMuestreo();
                reporteHistoricoListReporteHistorico.setFiReporteHistoricoMuestreo(muestreoHistorico);
                reporteHistoricoListReporteHistorico = em.merge(reporteHistoricoListReporteHistorico);
                if (oldFiReporteHistoricoMuestreoOfReporteHistoricoListReporteHistorico != null) {
                    oldFiReporteHistoricoMuestreoOfReporteHistoricoListReporteHistorico.getReporteHistoricoList().remove(reporteHistoricoListReporteHistorico);
                    oldFiReporteHistoricoMuestreoOfReporteHistoricoListReporteHistorico = em.merge(oldFiReporteHistoricoMuestreoOfReporteHistoricoListReporteHistorico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMuestreoHistorico(muestreoHistorico.getPiMuestreoHistoricoId()) != null) {
                throw new PreexistingEntityException("MuestreoHistorico " + muestreoHistorico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestreoHistorico muestreoHistorico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestreoHistorico persistentMuestreoHistorico = em.find(MuestreoHistorico.class, muestreoHistorico.getPiMuestreoHistoricoId());
            SUsuario fsMuestreoHistoricoUsuacreacionOld = persistentMuestreoHistorico.getFsMuestreoHistoricoUsuacreacion();
            SUsuario fsMuestreoHistoricoUsuacreacionNew = muestreoHistorico.getFsMuestreoHistoricoUsuacreacion();
            SUsuario fsMuestreoHistoricoUsuamodifOld = persistentMuestreoHistorico.getFsMuestreoHistoricoUsuamodif();
            SUsuario fsMuestreoHistoricoUsuamodifNew = muestreoHistorico.getFsMuestreoHistoricoUsuamodif();
            Punto fiMuestreoHistoricoPuntoOld = persistentMuestreoHistorico.getFiMuestreoHistoricoPunto();
            Punto fiMuestreoHistoricoPuntoNew = muestreoHistorico.getFiMuestreoHistoricoPunto();
            List<ReporteHistorico> reporteHistoricoListOld = persistentMuestreoHistorico.getReporteHistoricoList();
            List<ReporteHistorico> reporteHistoricoListNew = muestreoHistorico.getReporteHistoricoList();
            if (fsMuestreoHistoricoUsuacreacionNew != null) {
                fsMuestreoHistoricoUsuacreacionNew = em.getReference(fsMuestreoHistoricoUsuacreacionNew.getClass(), fsMuestreoHistoricoUsuacreacionNew.getPsUsuarioCodigo());
                muestreoHistorico.setFsMuestreoHistoricoUsuacreacion(fsMuestreoHistoricoUsuacreacionNew);
            }
            if (fsMuestreoHistoricoUsuamodifNew != null) {
                fsMuestreoHistoricoUsuamodifNew = em.getReference(fsMuestreoHistoricoUsuamodifNew.getClass(), fsMuestreoHistoricoUsuamodifNew.getPsUsuarioCodigo());
                muestreoHistorico.setFsMuestreoHistoricoUsuamodif(fsMuestreoHistoricoUsuamodifNew);
            }
            if (fiMuestreoHistoricoPuntoNew != null) {
                fiMuestreoHistoricoPuntoNew = em.getReference(fiMuestreoHistoricoPuntoNew.getClass(), fiMuestreoHistoricoPuntoNew.getIdpunto());
                muestreoHistorico.setFiMuestreoHistoricoPunto(fiMuestreoHistoricoPuntoNew);
            }
            List<ReporteHistorico> attachedReporteHistoricoListNew = new ArrayList<ReporteHistorico>();
            for (ReporteHistorico reporteHistoricoListNewReporteHistoricoToAttach : reporteHistoricoListNew) {
                reporteHistoricoListNewReporteHistoricoToAttach = em.getReference(reporteHistoricoListNewReporteHistoricoToAttach.getClass(), reporteHistoricoListNewReporteHistoricoToAttach.getReporteHistoricoPK());
                attachedReporteHistoricoListNew.add(reporteHistoricoListNewReporteHistoricoToAttach);
            }
            reporteHistoricoListNew = attachedReporteHistoricoListNew;
            muestreoHistorico.setReporteHistoricoList(reporteHistoricoListNew);
            muestreoHistorico = em.merge(muestreoHistorico);
           /* if (fsMuestreoHistoricoUsuacreacionOld != null && !fsMuestreoHistoricoUsuacreacionOld.equals(fsMuestreoHistoricoUsuacreacionNew)) {
                fsMuestreoHistoricoUsuacreacionOld.getMuestreoHistoricoList().remove(muestreoHistorico);
                fsMuestreoHistoricoUsuacreacionOld = em.merge(fsMuestreoHistoricoUsuacreacionOld);
            }*/
            /*if (fsMuestreoHistoricoUsuacreacionNew != null && !fsMuestreoHistoricoUsuacreacionNew.equals(fsMuestreoHistoricoUsuacreacionOld)) {
                fsMuestreoHistoricoUsuacreacionNew.getMuestreoHistoricoList().add(muestreoHistorico);
                fsMuestreoHistoricoUsuacreacionNew = em.merge(fsMuestreoHistoricoUsuacreacionNew);
            }*/
          /*  if (fsMuestreoHistoricoUsuamodifOld != null && !fsMuestreoHistoricoUsuamodifOld.equals(fsMuestreoHistoricoUsuamodifNew)) {
                fsMuestreoHistoricoUsuamodifOld.getMuestreoHistoricoList().remove(muestreoHistorico);
                fsMuestreoHistoricoUsuamodifOld = em.merge(fsMuestreoHistoricoUsuamodifOld);
            }
            if (fsMuestreoHistoricoUsuamodifNew != null && !fsMuestreoHistoricoUsuamodifNew.equals(fsMuestreoHistoricoUsuamodifOld)) {
                fsMuestreoHistoricoUsuamodifNew.getMuestreoHistoricoList().add(muestreoHistorico);
                fsMuestreoHistoricoUsuamodifNew = em.merge(fsMuestreoHistoricoUsuamodifNew);
            }*/
            if (fiMuestreoHistoricoPuntoOld != null && !fiMuestreoHistoricoPuntoOld.equals(fiMuestreoHistoricoPuntoNew)) {
                fiMuestreoHistoricoPuntoOld.getMuestreoHistoricoList().remove(muestreoHistorico);
                fiMuestreoHistoricoPuntoOld = em.merge(fiMuestreoHistoricoPuntoOld);
            }
            if (fiMuestreoHistoricoPuntoNew != null && !fiMuestreoHistoricoPuntoNew.equals(fiMuestreoHistoricoPuntoOld)) {
                fiMuestreoHistoricoPuntoNew.getMuestreoHistoricoList().add(muestreoHistorico);
                fiMuestreoHistoricoPuntoNew = em.merge(fiMuestreoHistoricoPuntoNew);
            }
            for (ReporteHistorico reporteHistoricoListOldReporteHistorico : reporteHistoricoListOld) {
                if (!reporteHistoricoListNew.contains(reporteHistoricoListOldReporteHistorico)) {
                    reporteHistoricoListOldReporteHistorico.setFiReporteHistoricoMuestreo(null);
                    reporteHistoricoListOldReporteHistorico = em.merge(reporteHistoricoListOldReporteHistorico);
                }
            }
            for (ReporteHistorico reporteHistoricoListNewReporteHistorico : reporteHistoricoListNew) {
                if (!reporteHistoricoListOld.contains(reporteHistoricoListNewReporteHistorico)) {
                    MuestreoHistorico oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico = reporteHistoricoListNewReporteHistorico.getFiReporteHistoricoMuestreo();
                    reporteHistoricoListNewReporteHistorico.setFiReporteHistoricoMuestreo(muestreoHistorico);
                    reporteHistoricoListNewReporteHistorico = em.merge(reporteHistoricoListNewReporteHistorico);
                    if (oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico != null && !oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico.equals(muestreoHistorico)) {
                        oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico.getReporteHistoricoList().remove(reporteHistoricoListNewReporteHistorico);
                        oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico = em.merge(oldFiReporteHistoricoMuestreoOfReporteHistoricoListNewReporteHistorico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestreoHistorico.getPiMuestreoHistoricoId();
                if (findMuestreoHistorico(id) == null) {
                    throw new NonexistentEntityException("The muestreoHistorico with id " + id + " no longer exists.");
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
            MuestreoHistorico muestreoHistorico;
            try {
                muestreoHistorico = em.getReference(MuestreoHistorico.class, id);
                muestreoHistorico.getPiMuestreoHistoricoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestreoHistorico with id " + id + " no longer exists.", enfe);
            }
            SUsuario fsMuestreoHistoricoUsuacreacion = muestreoHistorico.getFsMuestreoHistoricoUsuacreacion();
            /*if (fsMuestreoHistoricoUsuacreacion != null) {
                fsMuestreoHistoricoUsuacreacion.getMuestreoHistoricoList().remove(muestreoHistorico);
                fsMuestreoHistoricoUsuacreacion = em.merge(fsMuestreoHistoricoUsuacreacion);
            }*/
            SUsuario fsMuestreoHistoricoUsuamodif = muestreoHistorico.getFsMuestreoHistoricoUsuamodif();
            /*if (fsMuestreoHistoricoUsuamodif != null) {
                fsMuestreoHistoricoUsuamodif.getMuestreoHistoricoList().remove(muestreoHistorico);
                fsMuestreoHistoricoUsuamodif = em.merge(fsMuestreoHistoricoUsuamodif);
            }*/
            Punto fiMuestreoHistoricoPunto = muestreoHistorico.getFiMuestreoHistoricoPunto();
            if (fiMuestreoHistoricoPunto != null) {
                fiMuestreoHistoricoPunto.getMuestreoHistoricoList().remove(muestreoHistorico);
                fiMuestreoHistoricoPunto = em.merge(fiMuestreoHistoricoPunto);
            }
            List<ReporteHistorico> reporteHistoricoList = muestreoHistorico.getReporteHistoricoList();
            for (ReporteHistorico reporteHistoricoListReporteHistorico : reporteHistoricoList) {
                reporteHistoricoListReporteHistorico.setFiReporteHistoricoMuestreo(null);
                reporteHistoricoListReporteHistorico = em.merge(reporteHistoricoListReporteHistorico);
            }
            em.remove(muestreoHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestreoHistorico> findMuestreoHistoricoEntities() {
        return findMuestreoHistoricoEntities(true, -1, -1);
    }

    public List<MuestreoHistorico> findMuestreoHistoricoEntities(int maxResults, int firstResult) {
        return findMuestreoHistoricoEntities(false, maxResults, firstResult);
    }

    private List<MuestreoHistorico> findMuestreoHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestreoHistorico.class));
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

    public MuestreoHistorico findMuestreoHistorico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestreoHistorico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestreoHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestreoHistorico> rt = cq.from(MuestreoHistorico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
