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
import entidades.ReporteHistorico;
import entidades.MuestraAnalisisHistorico;
import entidades.MuestraHistorico;
import entidades.MuestraHistoricoPK;
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
public class MuestraHistoricoJpaController implements Serializable {

    public MuestraHistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraHistorico muestraHistorico) throws PreexistingEntityException, Exception {
        if (muestraHistorico.getMuestraHistoricoPK() == null) {
            muestraHistorico.setMuestraHistoricoPK(new MuestraHistoricoPK());
        }
        if (muestraHistorico.getMuestraAnalisisHistoricoList() == null) {
            muestraHistorico.setMuestraAnalisisHistoricoList(new ArrayList<MuestraAnalisisHistorico>());
        }
        muestraHistorico.getMuestraHistoricoPK().setFiMuestraHistoricoReporteVersion(muestraHistorico.getReporteHistorico().getReporteHistoricoPK().getIReporteHistoricoVersion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReporteHistorico reporteHistorico = muestraHistorico.getReporteHistorico();
            if (reporteHistorico != null) {
                reporteHistorico = em.getReference(reporteHistorico.getClass(), reporteHistorico.getReporteHistoricoPK());
                muestraHistorico.setReporteHistorico(reporteHistorico);
            }
            List<MuestraAnalisisHistorico> attachedMuestraAnalisisHistoricoList = new ArrayList<MuestraAnalisisHistorico>();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach : muestraHistorico.getMuestraAnalisisHistoricoList()) {
                muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach = em.getReference(muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach.getClass(), muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach.getMuestraAnalisisHistoricoPK());
                attachedMuestraAnalisisHistoricoList.add(muestraAnalisisHistoricoListMuestraAnalisisHistoricoToAttach);
            }
            muestraHistorico.setMuestraAnalisisHistoricoList(attachedMuestraAnalisisHistoricoList);
            em.persist(muestraHistorico);
            if (reporteHistorico != null) {
                reporteHistorico.getMuestraHistoricoList().add(muestraHistorico);
                reporteHistorico = em.merge(reporteHistorico);
            }
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListMuestraAnalisisHistorico : muestraHistorico.getMuestraAnalisisHistoricoList()) {
                MuestraHistorico oldMuestraHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico = muestraAnalisisHistoricoListMuestraAnalisisHistorico.getMuestraHistorico();
                muestraAnalisisHistoricoListMuestraAnalisisHistorico.setMuestraHistorico(muestraHistorico);
                muestraAnalisisHistoricoListMuestraAnalisisHistorico = em.merge(muestraAnalisisHistoricoListMuestraAnalisisHistorico);
                if (oldMuestraHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico != null) {
                    oldMuestraHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistoricoListMuestraAnalisisHistorico);
                    oldMuestraHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico = em.merge(oldMuestraHistoricoOfMuestraAnalisisHistoricoListMuestraAnalisisHistorico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMuestraHistorico(muestraHistorico.getMuestraHistoricoPK()) != null) {
                throw new PreexistingEntityException("MuestraHistorico " + muestraHistorico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraHistorico muestraHistorico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        muestraHistorico.getMuestraHistoricoPK().setFiMuestraHistoricoReporteVersion(muestraHistorico.getReporteHistorico().getReporteHistoricoPK().getIReporteHistoricoVersion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraHistorico persistentMuestraHistorico = em.find(MuestraHistorico.class, muestraHistorico.getMuestraHistoricoPK());
            ReporteHistorico reporteHistoricoOld = persistentMuestraHistorico.getReporteHistorico();
            ReporteHistorico reporteHistoricoNew = muestraHistorico.getReporteHistorico();
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListOld = persistentMuestraHistorico.getMuestraAnalisisHistoricoList();
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListNew = muestraHistorico.getMuestraAnalisisHistoricoList();
            List<String> illegalOrphanMessages = null;
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListOldMuestraAnalisisHistorico : muestraAnalisisHistoricoListOld) {
                if (!muestraAnalisisHistoricoListNew.contains(muestraAnalisisHistoricoListOldMuestraAnalisisHistorico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MuestraAnalisisHistorico " + muestraAnalisisHistoricoListOldMuestraAnalisisHistorico + " since its muestraHistorico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (reporteHistoricoNew != null) {
                reporteHistoricoNew = em.getReference(reporteHistoricoNew.getClass(), reporteHistoricoNew.getReporteHistoricoPK());
                muestraHistorico.setReporteHistorico(reporteHistoricoNew);
            }
            List<MuestraAnalisisHistorico> attachedMuestraAnalisisHistoricoListNew = new ArrayList<MuestraAnalisisHistorico>();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach : muestraAnalisisHistoricoListNew) {
                muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach = em.getReference(muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach.getClass(), muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach.getMuestraAnalisisHistoricoPK());
                attachedMuestraAnalisisHistoricoListNew.add(muestraAnalisisHistoricoListNewMuestraAnalisisHistoricoToAttach);
            }
            muestraAnalisisHistoricoListNew = attachedMuestraAnalisisHistoricoListNew;
            muestraHistorico.setMuestraAnalisisHistoricoList(muestraAnalisisHistoricoListNew);
            muestraHistorico = em.merge(muestraHistorico);
            if (reporteHistoricoOld != null && !reporteHistoricoOld.equals(reporteHistoricoNew)) {
                reporteHistoricoOld.getMuestraHistoricoList().remove(muestraHistorico);
                reporteHistoricoOld = em.merge(reporteHistoricoOld);
            }
            if (reporteHistoricoNew != null && !reporteHistoricoNew.equals(reporteHistoricoOld)) {
                reporteHistoricoNew.getMuestraHistoricoList().add(muestraHistorico);
                reporteHistoricoNew = em.merge(reporteHistoricoNew);
            }
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListNewMuestraAnalisisHistorico : muestraAnalisisHistoricoListNew) {
                if (!muestraAnalisisHistoricoListOld.contains(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico)) {
                    MuestraHistorico oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico = muestraAnalisisHistoricoListNewMuestraAnalisisHistorico.getMuestraHistorico();
                    muestraAnalisisHistoricoListNewMuestraAnalisisHistorico.setMuestraHistorico(muestraHistorico);
                    muestraAnalisisHistoricoListNewMuestraAnalisisHistorico = em.merge(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                    if (oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico != null && !oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico.equals(muestraHistorico)) {
                        oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico.getMuestraAnalisisHistoricoList().remove(muestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                        oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico = em.merge(oldMuestraHistoricoOfMuestraAnalisisHistoricoListNewMuestraAnalisisHistorico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MuestraHistoricoPK id = muestraHistorico.getMuestraHistoricoPK();
                if (findMuestraHistorico(id) == null) {
                    throw new NonexistentEntityException("The muestraHistorico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MuestraHistoricoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraHistorico muestraHistorico;
            try {
                muestraHistorico = em.getReference(MuestraHistorico.class, id);
                muestraHistorico.getMuestraHistoricoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraHistorico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MuestraAnalisisHistorico> muestraAnalisisHistoricoListOrphanCheck = muestraHistorico.getMuestraAnalisisHistoricoList();
            for (MuestraAnalisisHistorico muestraAnalisisHistoricoListOrphanCheckMuestraAnalisisHistorico : muestraAnalisisHistoricoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MuestraHistorico (" + muestraHistorico + ") cannot be destroyed since the MuestraAnalisisHistorico " + muestraAnalisisHistoricoListOrphanCheckMuestraAnalisisHistorico + " in its muestraAnalisisHistoricoList field has a non-nullable muestraHistorico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ReporteHistorico reporteHistorico = muestraHistorico.getReporteHistorico();
            if (reporteHistorico != null) {
                reporteHistorico.getMuestraHistoricoList().remove(muestraHistorico);
                reporteHistorico = em.merge(reporteHistorico);
            }
            em.remove(muestraHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraHistorico> findMuestraHistoricoEntities() {
        return findMuestraHistoricoEntities(true, -1, -1);
    }

    public List<MuestraHistorico> findMuestraHistoricoEntities(int maxResults, int firstResult) {
        return findMuestraHistoricoEntities(false, maxResults, firstResult);
    }

    private List<MuestraHistorico> findMuestraHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraHistorico.class));
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

    public MuestraHistorico findMuestraHistorico(MuestraHistoricoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraHistorico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraHistorico> rt = cq.from(MuestraHistorico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
