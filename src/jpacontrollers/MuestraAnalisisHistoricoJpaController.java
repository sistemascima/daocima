/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MuestraAnalisisHistorico;
import entidades.MuestraAnalisisHistoricoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.MuestraHistorico;
import entidades.ReporteHistorico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestraAnalisisHistoricoJpaController implements Serializable {

    public MuestraAnalisisHistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraAnalisisHistorico muestraAnalisisHistorico) throws PreexistingEntityException, Exception {
        if (muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK() == null) {
            muestraAnalisisHistorico.setMuestraAnalisisHistoricoPK(new MuestraAnalisisHistoricoPK());
        }
        muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK().setFsMuestraAnalisisHistoricoReporteVersion(muestraAnalisisHistorico.getReporteHistorico().getReporteHistoricoPK().getIReporteHistoricoVersion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraHistorico muestraHistorico = muestraAnalisisHistorico.getMuestraHistorico();
            if (muestraHistorico != null) {
                muestraHistorico = em.getReference(muestraHistorico.getClass(), muestraHistorico.getMuestraHistoricoPK());
                muestraAnalisisHistorico.setMuestraHistorico(muestraHistorico);
            }
            ReporteHistorico reporteHistorico = muestraAnalisisHistorico.getReporteHistorico();
            if (reporteHistorico != null) {
                reporteHistorico = em.getReference(reporteHistorico.getClass(), reporteHistorico.getReporteHistoricoPK());
                muestraAnalisisHistorico.setReporteHistorico(reporteHistorico);
            }
            em.persist(muestraAnalisisHistorico);
            if (muestraHistorico != null) {
                muestraHistorico.getMuestraAnalisisHistoricoList().add(muestraAnalisisHistorico);
                muestraHistorico = em.merge(muestraHistorico);
            }
            if (reporteHistorico != null) {
                reporteHistorico.getMuestraAnalisisHistoricoList().add(muestraAnalisisHistorico);
                reporteHistorico = em.merge(reporteHistorico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMuestraAnalisisHistorico(muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK()) != null) {
                throw new PreexistingEntityException("MuestraAnalisisHistorico " + muestraAnalisisHistorico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraAnalisisHistorico muestraAnalisisHistorico) throws NonexistentEntityException, Exception {
        muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK().setFsMuestraAnalisisHistoricoReporteVersion(muestraAnalisisHistorico.getReporteHistorico().getReporteHistoricoPK().getIReporteHistoricoVersion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraAnalisisHistorico persistentMuestraAnalisisHistorico = em.find(MuestraAnalisisHistorico.class, muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK());
            MuestraHistorico muestraHistoricoOld = persistentMuestraAnalisisHistorico.getMuestraHistorico();
            MuestraHistorico muestraHistoricoNew = muestraAnalisisHistorico.getMuestraHistorico();
            ReporteHistorico reporteHistoricoOld = persistentMuestraAnalisisHistorico.getReporteHistorico();
            ReporteHistorico reporteHistoricoNew = muestraAnalisisHistorico.getReporteHistorico();
            if (muestraHistoricoNew != null) {
                muestraHistoricoNew = em.getReference(muestraHistoricoNew.getClass(), muestraHistoricoNew.getMuestraHistoricoPK());
                muestraAnalisisHistorico.setMuestraHistorico(muestraHistoricoNew);
            }
            if (reporteHistoricoNew != null) {
                reporteHistoricoNew = em.getReference(reporteHistoricoNew.getClass(), reporteHistoricoNew.getReporteHistoricoPK());
                muestraAnalisisHistorico.setReporteHistorico(reporteHistoricoNew);
            }
            muestraAnalisisHistorico = em.merge(muestraAnalisisHistorico);
            if (muestraHistoricoOld != null && !muestraHistoricoOld.equals(muestraHistoricoNew)) {
                muestraHistoricoOld.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistorico);
                muestraHistoricoOld = em.merge(muestraHistoricoOld);
            }
            if (muestraHistoricoNew != null && !muestraHistoricoNew.equals(muestraHistoricoOld)) {
                muestraHistoricoNew.getMuestraAnalisisHistoricoList().add(muestraAnalisisHistorico);
                muestraHistoricoNew = em.merge(muestraHistoricoNew);
            }
            if (reporteHistoricoOld != null && !reporteHistoricoOld.equals(reporteHistoricoNew)) {
                reporteHistoricoOld.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistorico);
                reporteHistoricoOld = em.merge(reporteHistoricoOld);
            }
            if (reporteHistoricoNew != null && !reporteHistoricoNew.equals(reporteHistoricoOld)) {
                reporteHistoricoNew.getMuestraAnalisisHistoricoList().add(muestraAnalisisHistorico);
                reporteHistoricoNew = em.merge(reporteHistoricoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MuestraAnalisisHistoricoPK id = muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK();
                if (findMuestraAnalisisHistorico(id) == null) {
                    throw new NonexistentEntityException("The muestraAnalisisHistorico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MuestraAnalisisHistoricoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraAnalisisHistorico muestraAnalisisHistorico;
            try {
                muestraAnalisisHistorico = em.getReference(MuestraAnalisisHistorico.class, id);
                muestraAnalisisHistorico.getMuestraAnalisisHistoricoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraAnalisisHistorico with id " + id + " no longer exists.", enfe);
            }
            MuestraHistorico muestraHistorico = muestraAnalisisHistorico.getMuestraHistorico();
            if (muestraHistorico != null) {
                muestraHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistorico);
                muestraHistorico = em.merge(muestraHistorico);
            }
            ReporteHistorico reporteHistorico = muestraAnalisisHistorico.getReporteHistorico();
            if (reporteHistorico != null) {
                reporteHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistorico);
                reporteHistorico = em.merge(reporteHistorico);
            }
            em.remove(muestraAnalisisHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraAnalisisHistorico> findMuestraAnalisisHistoricoEntities() {
        return findMuestraAnalisisHistoricoEntities(true, -1, -1);
    }

    public List<MuestraAnalisisHistorico> findMuestraAnalisisHistoricoEntities(int maxResults, int firstResult) {
        return findMuestraAnalisisHistoricoEntities(false, maxResults, firstResult);
    }

    private List<MuestraAnalisisHistorico> findMuestraAnalisisHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraAnalisisHistorico.class));
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

    public MuestraAnalisisHistorico findMuestraAnalisisHistorico(MuestraAnalisisHistoricoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraAnalisisHistorico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraAnalisisHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraAnalisisHistorico> rt = cq.from(MuestraAnalisisHistorico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
