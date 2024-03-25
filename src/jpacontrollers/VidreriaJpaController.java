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
import entidades.Vidrieria;
import entidades.VidreriaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class VidreriaJpaController implements Serializable {

    public VidreriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vidrieria vidreria) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (vidreria.getVidreriaPK() == null) {
            vidreria.setVidreriaPK(new VidreriaPK());
        }
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaMatriz(vidreria.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaFuncion(vidreria.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaConsecutivo(vidreria.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        List<String> illegalOrphanMessages = null;
        HojaDeVida hojaDeVidaOrphanCheck = vidreria.getHojaDeVida();
        if (hojaDeVidaOrphanCheck != null) {
            Vidrieria oldVidreriaOfHojaDeVida = hojaDeVidaOrphanCheck.getVidreria();
            if (oldVidreriaOfHojaDeVida != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaOrphanCheck + " already has an item of type Vidreria whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida hojaDeVida = vidreria.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida = em.getReference(hojaDeVida.getClass(), hojaDeVida.getHojaDeVidaPK());
                vidreria.setHojaDeVida(hojaDeVida);
            }
            em.persist(vidreria);
            if (hojaDeVida != null) {
                hojaDeVida.setVidreria(vidreria);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVidreria(vidreria.getVidreriaPK()) != null) {
                throw new PreexistingEntityException("Vidreria " + vidreria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vidrieria vidreria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaMatriz(vidreria.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaMatriz());
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaFuncion(vidreria.getHojaDeVida().getHojaDeVidaPK().getPsHojaVidaFuncion());
        vidreria.getVidreriaPK().setFkVidreriaHojaDeVidaConsecutivo(vidreria.getHojaDeVida().getHojaDeVidaPK().getPiHojaVidaConsecutivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vidrieria persistentVidreria = em.find(Vidrieria.class, vidreria.getVidreriaPK());
            HojaDeVida hojaDeVidaOld = persistentVidreria.getHojaDeVida();
            HojaDeVida hojaDeVidaNew = vidreria.getHojaDeVida();
            List<String> illegalOrphanMessages = null;
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                Vidrieria oldVidreriaOfHojaDeVida = hojaDeVidaNew.getVidreria();
                if (oldVidreriaOfHojaDeVida != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HojaDeVida " + hojaDeVidaNew + " already has an item of type Vidreria whose hojaDeVida column cannot be null. Please make another selection for the hojaDeVida field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (hojaDeVidaNew != null) {
                hojaDeVidaNew = em.getReference(hojaDeVidaNew.getClass(), hojaDeVidaNew.getHojaDeVidaPK());
                vidreria.setHojaDeVida(hojaDeVidaNew);
            }
            vidreria = em.merge(vidreria);
            if (hojaDeVidaOld != null && !hojaDeVidaOld.equals(hojaDeVidaNew)) {
                hojaDeVidaOld.setVidreria(null);
                hojaDeVidaOld = em.merge(hojaDeVidaOld);
            }
            if (hojaDeVidaNew != null && !hojaDeVidaNew.equals(hojaDeVidaOld)) {
                hojaDeVidaNew.setVidreria(vidreria);
                hojaDeVidaNew = em.merge(hojaDeVidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VidreriaPK id = vidreria.getVidreriaPK();
                if (findVidreria(id) == null) {
                    throw new NonexistentEntityException("The vidreria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VidreriaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vidrieria vidreria;
            try {
                vidreria = em.getReference(Vidrieria.class, id);
                vidreria.getVidreriaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vidreria with id " + id + " no longer exists.", enfe);
            }
            HojaDeVida hojaDeVida = vidreria.getHojaDeVida();
            if (hojaDeVida != null) {
                hojaDeVida.setVidreria(null);
                hojaDeVida = em.merge(hojaDeVida);
            }
            em.remove(vidreria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vidrieria> findVidreriaEntities() {
        return findVidreriaEntities(true, -1, -1);
    }

    public List<Vidrieria> findVidreriaEntities(int maxResults, int firstResult) {
        return findVidreriaEntities(false, maxResults, firstResult);
    }

    private List<Vidrieria> findVidreriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vidrieria.class));
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

    public Vidrieria findVidreria(VidreriaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vidrieria.class, id);
        } finally {
            em.close();
        }
    }

    public int getVidreriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vidrieria> rt = cq.from(Vidrieria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
