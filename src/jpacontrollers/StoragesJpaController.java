/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Storage;
import entidades.StoragesPK;
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
public class StoragesJpaController implements Serializable {

    public StoragesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Storage storages) throws PreexistingEntityException, Exception {
        if (storages.getStoragesPK() == null) {
            storages.setStoragesPK(new StoragesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(storages);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStorages(storages.getStoragesPK()) != null) {
                throw new PreexistingEntityException("Storages " + storages + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Storage storages) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            storages = em.merge(storages);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                StoragesPK id = storages.getStoragesPK();
                if (findStorages(id) == null) {
                    throw new NonexistentEntityException("The storages with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(StoragesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Storage storages;
            try {
                storages = em.getReference(Storage.class, id);
                storages.getStoragesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The storages with id " + id + " no longer exists.", enfe);
            }
            em.remove(storages);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Storage> findStoragesEntities() {
        return findStoragesEntities(true, -1, -1);
    }

    public List<Storage> findStoragesEntities(int maxResults, int firstResult) {
        return findStoragesEntities(false, maxResults, firstResult);
    }

    private List<Storage> findStoragesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Storage.class));
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

    public Storage findStorages(StoragesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Storage.class, id);
        } finally {
            em.close();
        }
    }

    public int getStoragesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Storage> rt = cq.from(Storage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
