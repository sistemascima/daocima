/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Monitor;
import entidades.MonitorsPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author HelpDesk
 */
public class MonitorsJpaController implements Serializable {

    public MonitorsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Monitor monitors) throws PreexistingEntityException, Exception {
        if (monitors.getMonitorsPK() == null) {
            monitors.setMonitorsPK(new MonitorsPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMonitors(monitors.getMonitorsPK()) != null) {
                throw new PreexistingEntityException("Monitors " + monitors + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Monitor monitors) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitors = em.merge(monitors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MonitorsPK id = monitors.getMonitorsPK();
                if (findMonitors(id) == null) {
                    throw new NonexistentEntityException("The monitors with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MonitorsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Monitor monitors;
            try {
                monitors = em.getReference(Monitor.class, id);
                monitors.getMonitorsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitors with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitors);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Monitor> findMonitorsEntities() {
        return findMonitorsEntities(true, -1, -1);
    }

    public List<Monitor> findMonitorsEntities(int maxResults, int firstResult) {
        return findMonitorsEntities(false, maxResults, firstResult);
    }

    private List<Monitor> findMonitorsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monitor.class));
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

    public Monitor findMonitors(MonitorsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Monitor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitorsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Monitor> rt = cq.from(Monitor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
