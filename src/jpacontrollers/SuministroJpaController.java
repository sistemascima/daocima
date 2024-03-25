/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import entidades.Suministro;
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
public class SuministroJpaController implements Serializable {

    public SuministroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suministro suministros) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(suministros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suministro suministros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            suministros = em.merge(suministros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = suministros.getSuministroId();
                if (findSuministros(id) == null) {
                    throw new NonexistentEntityException("The suministros with id " + id + " no longer exists.");
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
            Suministro suministros;
            try {
                suministros = em.getReference(Suministro.class, id);
                suministros.getSuministroId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suministros with id " + id + " no longer exists.", enfe);
            }
            em.remove(suministros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suministro> findSuministrosEntities() {
        return findSuministrosEntities(true, -1, -1);
    }

    public List<Suministro> findSuministrosEntities(int maxResults, int firstResult) {
        return findSuministrosEntities(false, maxResults, firstResult);
    }

    private List<Suministro> findSuministrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suministro.class));
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

    public Suministro findSuministros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suministro.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuministrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suministro> rt = cq.from(Suministro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        
    public List<String> findCartuchoFromSuministros() {

        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Suministros.findCartuchoDistinct")
                .getResultList();

        return results;
    }
    
    public List<String> findColorFromSuministros(String cartucho) {

        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Suministros.findColorDistinct")
                .setParameter("cartucho", cartucho)
                .getResultList();

        return results;
    }
    
    public Suministro findSuministroByCartuchoAndColor(String cartucho , String color) {

        EntityManager em = getEntityManager();
        Suministro suministro = null;
        List results = em.createNamedQuery("Suministros.findCartuchoByCantidadAndColor")
                 .setParameter("cartucho", cartucho)
                 .setParameter("color", color)
                .getResultList();

        for (int i=0; i<results.size();i++) {
        suministro= (Suministro) results.get(i);
        }
        
        
        return suministro;
    }

    public List<Suministro> getSuministrosAgostados() {
    
        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Suministros.findCartuchosAgotados")
        
                .getResultList();

        return results;
        
    }
    
}
