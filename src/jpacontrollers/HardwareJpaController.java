/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Bios;
import entidades.Accountinfo;
import entidades.Hardware;
import entidades.SUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HelpDesk
 */
public class HardwareJpaController implements Serializable {

    public HardwareJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hardware hardware) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bios bios = hardware.getBios();
            if (bios != null) {
                bios = em.getReference(bios.getClass(), bios.getHardwareId());
                hardware.setBios(bios);
            }
            Accountinfo accountinfo = hardware.getAccountinfo();
            if (accountinfo != null) {
                accountinfo = em.getReference(accountinfo.getClass(), accountinfo.getHardwareId());
                hardware.setAccountinfo(accountinfo);
            }
            SUsuario usuarioCodigo = hardware.getUsuarioCodigo();
            if (usuarioCodigo != null) {
                usuarioCodigo = em.getReference(usuarioCodigo.getClass(), usuarioCodigo.getPsUsuarioCodigo());
                hardware.setUsuarioCodigo(usuarioCodigo);
            }
            em.persist(hardware);
            if (bios != null) {
                Hardware oldHardwareOfBios = bios.getHardware();
                if (oldHardwareOfBios != null) {
                    oldHardwareOfBios.setBios(null);
                    oldHardwareOfBios = em.merge(oldHardwareOfBios);
                }
                bios.setHardware(hardware);
                bios = em.merge(bios);
            }
            if (accountinfo != null) {
                Hardware oldHardwareOfAccountinfo = accountinfo.getHardware();
                if (oldHardwareOfAccountinfo != null) {
                    oldHardwareOfAccountinfo.setAccountinfo(null);
                    oldHardwareOfAccountinfo = em.merge(oldHardwareOfAccountinfo);
                }
                accountinfo.setHardware(hardware);
                accountinfo = em.merge(accountinfo);
            }
           /* if (usuarioCodigo != null) {
                usuarioCodigo.getHardwareList().add(hardware);
                usuarioCodigo = em.merge(usuarioCodigo);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hardware hardware) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hardware persistentHardware = em.find(Hardware.class, hardware.getId());
            Bios biosOld = persistentHardware.getBios();
            Bios biosNew = hardware.getBios();
            Accountinfo accountinfoOld = persistentHardware.getAccountinfo();
            Accountinfo accountinfoNew = hardware.getAccountinfo();
            SUsuario usuarioCodigoOld = persistentHardware.getUsuarioCodigo();
            SUsuario usuarioCodigoNew = hardware.getUsuarioCodigo();
            List<String> illegalOrphanMessages = null;
            if (biosOld != null && !biosOld.equals(biosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Bios " + biosOld + " since its hardware field is not nullable.");
            }
            if (accountinfoOld != null && !accountinfoOld.equals(accountinfoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Accountinfo " + accountinfoOld + " since its hardware field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (biosNew != null) {
                biosNew = em.getReference(biosNew.getClass(), biosNew.getHardwareId());
                hardware.setBios(biosNew);
            }
            if (accountinfoNew != null) {
                accountinfoNew = em.getReference(accountinfoNew.getClass(), accountinfoNew.getHardwareId());
                hardware.setAccountinfo(accountinfoNew);
            }
            if (usuarioCodigoNew != null) {
                usuarioCodigoNew = em.getReference(usuarioCodigoNew.getClass(), usuarioCodigoNew.getPsUsuarioCodigo());
                hardware.setUsuarioCodigo(usuarioCodigoNew);
            }
            hardware = em.merge(hardware);
            if (biosNew != null && !biosNew.equals(biosOld)) {
                Hardware oldHardwareOfBios = biosNew.getHardware();
                if (oldHardwareOfBios != null) {
                    oldHardwareOfBios.setBios(null);
                    oldHardwareOfBios = em.merge(oldHardwareOfBios);
                }
                biosNew.setHardware(hardware);
                biosNew = em.merge(biosNew);
            }
            if (accountinfoNew != null && !accountinfoNew.equals(accountinfoOld)) {
                Hardware oldHardwareOfAccountinfo = accountinfoNew.getHardware();
                if (oldHardwareOfAccountinfo != null) {
                    oldHardwareOfAccountinfo.setAccountinfo(null);
                    oldHardwareOfAccountinfo = em.merge(oldHardwareOfAccountinfo);
                }
                accountinfoNew.setHardware(hardware);
                accountinfoNew = em.merge(accountinfoNew);
            }
          /*  if (usuarioCodigoOld != null && !usuarioCodigoOld.equals(usuarioCodigoNew)) {
                usuarioCodigoOld.getHardwareList().remove(hardware);
                usuarioCodigoOld = em.merge(usuarioCodigoOld);
            }
            if (usuarioCodigoNew != null && !usuarioCodigoNew.equals(usuarioCodigoOld)) {
                usuarioCodigoNew.getHardwareList().add(hardware);
                usuarioCodigoNew = em.merge(usuarioCodigoNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hardware.getId();
                if (findHardware(id) == null) {
                    throw new NonexistentEntityException("The hardware with id " + id + " no longer exists.");
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
            Hardware hardware;
            try {
                hardware = em.getReference(Hardware.class, id);
                hardware.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hardware with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Bios biosOrphanCheck = hardware.getBios();
            if (biosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hardware (" + hardware + ") cannot be destroyed since the Bios " + biosOrphanCheck + " in its bios field has a non-nullable hardware field.");
            }
            Accountinfo accountinfoOrphanCheck = hardware.getAccountinfo();
            if (accountinfoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hardware (" + hardware + ") cannot be destroyed since the Accountinfo " + accountinfoOrphanCheck + " in its accountinfo field has a non-nullable hardware field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SUsuario usuarioCodigo = hardware.getUsuarioCodigo();
           /* if (usuarioCodigo != null) {
                usuarioCodigo.getHardwareList().remove(hardware);
                usuarioCodigo = em.merge(usuarioCodigo);
            }*/
            em.remove(hardware);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hardware> findHardwareEntities() {
        return findHardwareEntities(true, -1, -1);
    }

    public List<Hardware> findHardwareEntities(int maxResults, int firstResult) {
        return findHardwareEntities(false, maxResults, firstResult);
    }

    private List<Hardware> findHardwareEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hardware.class));
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

    public Hardware findHardware(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hardware.class, id);
        } finally {
            em.close();
        }
    }

    public int getHardwareCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hardware> rt = cq.from(Hardware.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   public List<Hardware> findInformationById(int id) {

        EntityManager em = getEntityManager();
       
        List results = em.createNamedQuery("Hardware.findById")
                .setParameter("id", id)
                .getResultList();

        return results;
    }

    public List<Hardware> findHardwareByUsuario(SUsuario usuario) {
        
                
       EntityManager em = getEntityManager();
       
        List results = em.createNamedQuery("Hardware.findByUsuarioCodigo")
                .setParameter("usuarioCodigo", usuario)
                .getResultList();

        return results;
    }

   
    
}
