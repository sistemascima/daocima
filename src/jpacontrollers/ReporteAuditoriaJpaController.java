/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.ReporteAuditoria;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ReporteAuditoriaJpaController implements Serializable {

    public ReporteAuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReporteAuditoria reporteAuditoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reporteAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReporteAuditoria reporteAuditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reporteAuditoria = em.merge(reporteAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reporteAuditoria.getReporteAuditoriaId();
                if (findReporteAuditoria(id) == null) {
                    throw new NonexistentEntityException("The reporteAuditoria with id " + id + " no longer exists.");
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
            ReporteAuditoria reporteAuditoria;
            try {
                reporteAuditoria = em.getReference(ReporteAuditoria.class, id);
                reporteAuditoria.getReporteAuditoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteAuditoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(reporteAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReporteAuditoria> findReporteAuditoriaEntities() {
        return findReporteAuditoriaEntities(true, -1, -1);
    }

    public List<ReporteAuditoria> findReporteAuditoriaEntities(int maxResults, int firstResult) {
        return findReporteAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<ReporteAuditoria> findReporteAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReporteAuditoria.class));
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

    public ReporteAuditoria findReporteAuditoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReporteAuditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReporteAuditoria> rt = cq.from(ReporteAuditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarReportes(String idReporte) {
        EntityManager em = getEntityManager();
        String consulta = "select pi_reporte_auditoria_id, fi_reporte_auditoria_cliente,"
                + " s_reporte_auditoria_version, i_reporte_auditoria_anexos, "
                + " fs_reporte_auditoria_usuaaprob, fs_reporte_auditoria_usuarevi,"
                + " c_reporte_auditoria_estado, fi_reporte_auditoria_muestreo,"
                + " proyecto.s_proyecto_nombre, d_reporte_auditoria_fechcreaci,"
                + " fs_reporte_auditoria_usuacread, s_reporte_auditoria_observaciones,"
                + " d_reporte_auditoria_fechmodi, fs_reporte_auditoria_usuamodi,"
                + " d_reporte_auditoria_fechapro, d_reporte_auditoria_fecharevi,"
                + " d_reporte_auditoria_fechaentrega from reporte_auditoria"
                + " join proyecto on proyecto.pi_proyecto_id= fi_reporte_auditoria_proyecto"
                + " where pi_reporte_auditoria_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
