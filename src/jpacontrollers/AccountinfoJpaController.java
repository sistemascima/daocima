/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Accountinfo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Hardware;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HelpDesk
 */
public class AccountinfoJpaController implements Serializable {

    public AccountinfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accountinfo accountinfo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Hardware hardwareOrphanCheck = accountinfo.getHardware();
        if (hardwareOrphanCheck != null) {
            Accountinfo oldAccountinfoOfHardware = hardwareOrphanCheck.getAccountinfo();
            if (oldAccountinfoOfHardware != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Hardware " + hardwareOrphanCheck + " already has an item of type Accountinfo whose hardware column cannot be null. Please make another selection for the hardware field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hardware hardware = accountinfo.getHardware();
            if (hardware != null) {
                hardware = em.getReference(hardware.getClass(), hardware.getId());
                accountinfo.setHardware(hardware);
            }
            em.persist(accountinfo);
            if (hardware != null) {
                hardware.setAccountinfo(accountinfo);
                hardware = em.merge(hardware);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccountinfo(accountinfo.getHardwareId()) != null) {
                throw new PreexistingEntityException("Accountinfo " + accountinfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accountinfo accountinfo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accountinfo persistentAccountinfo = em.find(Accountinfo.class, accountinfo.getHardwareId());
            Hardware hardwareOld = persistentAccountinfo.getHardware();
            Hardware hardwareNew = accountinfo.getHardware();
            List<String> illegalOrphanMessages = null;
            if (hardwareNew != null && !hardwareNew.equals(hardwareOld)) {
                Accountinfo oldAccountinfoOfHardware = hardwareNew.getAccountinfo();
                if (oldAccountinfoOfHardware != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Hardware " + hardwareNew + " already has an item of type Accountinfo whose hardware column cannot be null. Please make another selection for the hardware field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hardwareNew != null) {
                hardwareNew = em.getReference(hardwareNew.getClass(), hardwareNew.getId());
                accountinfo.setHardware(hardwareNew);
            }
            accountinfo = em.merge(accountinfo);
            if (hardwareOld != null && !hardwareOld.equals(hardwareNew)) {
                hardwareOld.setAccountinfo(null);
                hardwareOld = em.merge(hardwareOld);
            }
            if (hardwareNew != null && !hardwareNew.equals(hardwareOld)) {
                hardwareNew.setAccountinfo(accountinfo);
                hardwareNew = em.merge(hardwareNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accountinfo.getHardwareId();
                if (findAccountinfo(id) == null) {
                    throw new NonexistentEntityException("The accountinfo with id " + id + " no longer exists.");
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
            Accountinfo accountinfo;
            try {
                accountinfo = em.getReference(Accountinfo.class, id);
                accountinfo.getHardwareId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accountinfo with id " + id + " no longer exists.", enfe);
            }
            Hardware hardware = accountinfo.getHardware();
            if (hardware != null) {
                hardware.setAccountinfo(null);
                hardware = em.merge(hardware);
            }
            em.remove(accountinfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accountinfo> findAccountinfoEntities() {
        System.out.println("entra al metodo de find account info entities...");
        return findAccountinfoEntities(true, -1, -1);
    }

    public List<Accountinfo> findAccountinfoEntities(int maxResults, int firstResult) {
        return findAccountinfoEntities(false, maxResults, firstResult);
    }

    private List<Accountinfo> findAccountinfoEntities(boolean all, int maxResults, int firstResult) {
        System.out.println("entra al otro metodo");
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accountinfo.class));
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

    public Accountinfo findAccountinfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accountinfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountinfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accountinfo> rt = cq.from(Accountinfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public List<Accountinfo> findAccountinfoByTag(String tag) {

        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Accountinfo.findByTag")
                .setParameter("tag", tag)
                .getResultList();

        return results;
    }

    public List<Accountinfo> findAccountinfoByHardware(int hardware) {
        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Accountinfo.findByHardwareId")
                .setParameter("hardwareId", hardware)
                .getResultList();

        return results;
    }
    
}
