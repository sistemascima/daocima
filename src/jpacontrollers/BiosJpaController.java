/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Bios;
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
public class BiosJpaController implements Serializable {

    public BiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bios bios) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Hardware hardwareOrphanCheck = bios.getHardware();
        if (hardwareOrphanCheck != null) {
            Bios oldBiosOfHardware = hardwareOrphanCheck.getBios();
            if (oldBiosOfHardware != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Hardware " + hardwareOrphanCheck + " already has an item of type Bios whose hardware column cannot be null. Please make another selection for the hardware field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hardware hardware = bios.getHardware();
            if (hardware != null) {
                hardware = em.getReference(hardware.getClass(), hardware.getId());
                bios.setHardware(hardware);
            }
            em.persist(bios);
            if (hardware != null) {
                hardware.setBios(bios);
                hardware = em.merge(hardware);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBios(bios.getHardwareId()) != null) {
                throw new PreexistingEntityException("Bios " + bios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bios bios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bios persistentBios = em.find(Bios.class, bios.getHardwareId());
            Hardware hardwareOld = persistentBios.getHardware();
            Hardware hardwareNew = bios.getHardware();
            List<String> illegalOrphanMessages = null;
            if (hardwareNew != null && !hardwareNew.equals(hardwareOld)) {
                Bios oldBiosOfHardware = hardwareNew.getBios();
                if (oldBiosOfHardware != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Hardware " + hardwareNew + " already has an item of type Bios whose hardware column cannot be null. Please make another selection for the hardware field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hardwareNew != null) {
                hardwareNew = em.getReference(hardwareNew.getClass(), hardwareNew.getId());
                bios.setHardware(hardwareNew);
            }
            bios = em.merge(bios);
            if (hardwareOld != null && !hardwareOld.equals(hardwareNew)) {
                hardwareOld.setBios(null);
                hardwareOld = em.merge(hardwareOld);
            }
            if (hardwareNew != null && !hardwareNew.equals(hardwareOld)) {
                hardwareNew.setBios(bios);
                hardwareNew = em.merge(hardwareNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bios.getHardwareId();
                if (findBios(id) == null) {
                    throw new NonexistentEntityException("The bios with id " + id + " no longer exists.");
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
            Bios bios;
            try {
                bios = em.getReference(Bios.class, id);
                bios.getHardwareId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bios with id " + id + " no longer exists.", enfe);
            }
            Hardware hardware = bios.getHardware();
            if (hardware != null) {
                hardware.setBios(null);
                hardware = em.merge(hardware);
            }
            em.remove(bios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bios> findBiosEntities() {
        return findBiosEntities(true, -1, -1);
    }

    public List<Bios> findBiosEntities(int maxResults, int firstResult) {
        return findBiosEntities(false, maxResults, firstResult);
    }

    private List<Bios> findBiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bios.class));
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

    public Bios findBios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bios.class, id);
        } finally {
            em.close();
        }
    }

    public int getBiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bios> rt = cq.from(Bios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        
    public List<Bios> findInformationById(int id) {

        EntityManager em = getEntityManager();
       
        List results = em.createNamedQuery("Bios.findByHardwareId")
                .setParameter("hardwareId", id)
                .getResultList();

        return results;
    }
    
}
