/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AnalistaParametro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.VariableAnalisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class AnalistaParametroJpaController1 implements Serializable {

    public AnalistaParametroJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnalistaParametro analistaParametro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisis idParametro = analistaParametro.getIdParametro();
            if (idParametro != null) {
                idParametro = em.getReference(idParametro.getClass(), idParametro.getPiVarianalId());
                analistaParametro.setIdParametro(idParametro);
            }
            em.persist(analistaParametro);
            if (idParametro != null) {
                idParametro.getAnalistaParametroList().add(analistaParametro);
                idParametro = em.merge(idParametro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnalistaParametro(analistaParametro.getIdanalistaParametro()) != null) {
                throw new PreexistingEntityException("AnalistaParametro " + analistaParametro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnalistaParametro analistaParametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalistaParametro persistentAnalistaParametro = em.find(AnalistaParametro.class, analistaParametro.getIdanalistaParametro());
            VariableAnalisis idParametroOld = persistentAnalistaParametro.getIdParametro();
            VariableAnalisis idParametroNew = analistaParametro.getIdParametro();
            if (idParametroNew != null) {
                idParametroNew = em.getReference(idParametroNew.getClass(), idParametroNew.getPiVarianalId());
                analistaParametro.setIdParametro(idParametroNew);
            }
            analistaParametro = em.merge(analistaParametro);
            if (idParametroOld != null && !idParametroOld.equals(idParametroNew)) {
                idParametroOld.getAnalistaParametroList().remove(analistaParametro);
                idParametroOld = em.merge(idParametroOld);
            }
            if (idParametroNew != null && !idParametroNew.equals(idParametroOld)) {
                idParametroNew.getAnalistaParametroList().add(analistaParametro);
                idParametroNew = em.merge(idParametroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analistaParametro.getIdanalistaParametro();
                if (findAnalistaParametro(id) == null) {
                    throw new NonexistentEntityException("The analistaParametro with id " + id + " no longer exists.");
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
            AnalistaParametro analistaParametro;
            try {
                analistaParametro = em.getReference(AnalistaParametro.class, id);
                analistaParametro.getIdanalistaParametro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analistaParametro with id " + id + " no longer exists.", enfe);
            }
            VariableAnalisis idParametro = analistaParametro.getIdParametro();
            if (idParametro != null) {
                idParametro.getAnalistaParametroList().remove(analistaParametro);
                idParametro = em.merge(idParametro);
            }
            em.remove(analistaParametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnalistaParametro> findAnalistaParametroEntities() {
        return findAnalistaParametroEntities(true, -1, -1);
    }

    public List<AnalistaParametro> findAnalistaParametroEntities(int maxResults, int firstResult) {
        return findAnalistaParametroEntities(false, maxResults, firstResult);
    }

    private List<AnalistaParametro> findAnalistaParametroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnalistaParametro.class));
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

    public AnalistaParametro findAnalistaParametro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnalistaParametro.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalistaParametroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnalistaParametro> rt = cq.from(AnalistaParametro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
