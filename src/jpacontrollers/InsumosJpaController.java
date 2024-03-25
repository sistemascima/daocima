/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HojaDeVida;
import entidades.Insumos;
import entidades.InsumosPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class InsumosJpaController implements Serializable {

    public InsumosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insumos insumos) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (insumos.getInsumosPK() == null) {
            insumos.setInsumosPK(new InsumosPK());
        }
        insumos.getInsumosPK().setFkInsumosHojaDeVidaMatriz(insumos.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        insumos.getInsumosPK().setFkInsumosHojaDeVidaConsecutivo(insumos.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        insumos.getInsumosPK().setFkInsumosHojaDeVidaFuncion(insumos.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        List<String> illegalOrphanMessages = null;
        HojaDeVida hojaDeVidaOrphanCheck = insumos.getHojaDeVida();
        if (hojaDeVidaOrphanCheck != null) {
            Insumos oldInsumosOfHojaDeVida = hojaDeVidaOrphanCheck.getInsumos();
            if (oldInsumosOfHojaDeVida != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaOrphanCheck + " already has an item of type Insumos whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida hojaDeVida = insumos.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida = em.getReference(hojaDeVida.getClass(), hojaDeVida.getHojaDeVidaPK());
                insumos.setHojaDeVida(hojaDeVida);
            }
            em.persist(insumos);
            if (hojaDeVida != null) {
                hojaDeVida.setInsumos(insumos);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInsumos(insumos.getInsumosPK()) != null) {
                throw new PreexistingEntityException("Insumos " + insumos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insumos insumos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        insumos.getInsumosPK().setFkInsumosHojaDeVidaMatriz(insumos.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        insumos.getInsumosPK().setFkInsumosHojaDeVidaConsecutivo(insumos.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        insumos.getInsumosPK().setFkInsumosHojaDeVidaFuncion(insumos.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumos persistentInsumos = em.find(Insumos.class, insumos.getInsumosPK());
            HojaDeVida hojaDeVidaOld = persistentInsumos.getHojaDeVida();
            HojaDeVida hojaDeVidaNew = insumos.getHojaDeVida();
            List<String> illegalOrphanMessages = null;
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                Insumos oldInsumosOfHojaDeVida = hojaDeVidaNew.getInsumos();
                if (oldInsumosOfHojaDeVida != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaNew + " already has an item of type Insumos whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hojaDeVidaNew != null) {
                hojaDeVidaNew = em.getReference(hojaDeVidaNew.getClass(), hojaDeVidaNew.getHojaDeVidaPK());
                insumos.setHojaDeVida(hojaDeVidaNew);
            }
            insumos = em.merge(insumos);
            if (hojaDeVidaOld != null && !hojaDeVidaOld.equals(hojaDeVidaNew)) {
                hojaDeVidaOld.setInsumos(null);
                hojaDeVidaOld = em.merge(hojaDeVidaOld);
            }
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                hojaDeVidaNew.setInsumos(insumos);
                hojaDeVidaNew = em.merge(hojaDeVidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InsumosPK id = insumos.getInsumosPK();
                if (findInsumos(id) == null) {
                    throw new NonexistentEntityException("The insumos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InsumosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumos insumos;
            try {
                insumos = em.getReference(Insumos.class, id);
                insumos.getInsumosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumos with id " + id + " no longer exists.", enfe);
            }
            HojaDeVida hojaDeVida = insumos.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida.setInsumos(null);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.remove(insumos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insumos> findInsumosEntities() {
        return findInsumosEntities(true, -1, -1);
    }

    public List<Insumos> findInsumosEntities(int maxResults, int firstResult) {
        return findInsumosEntities(false, maxResults, firstResult);
    }

    private List<Insumos> findInsumosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insumos.class));
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

    public Insumos findInsumos(InsumosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insumos.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insumos> rt = cq.from(Insumos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
