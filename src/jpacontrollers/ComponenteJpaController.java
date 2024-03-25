/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Componente;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ComponenteCargo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author Jhon
 */
public class ComponenteJpaController implements Serializable {

    public ComponenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Componente componente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(componente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Componente componente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            componente = em.merge(componente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = componente.getPiComponenId();
                if (findComponente(id) == null) {
                    throw new NonexistentEntityException("The componente with id " + id + " no longer exists.");
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
            Componente componente;
            try {
                componente = em.getReference(Componente.class, id);
                componente.getPiComponenId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componente with id " + id + " no longer exists.", enfe);
            }
            em.remove(componente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Componente> findComponenteEntities() {
        return findComponenteEntities(true, -1, -1);
    }

    public List<Componente> findComponenteEntities(int maxResults, int firstResult) {
        return findComponenteEntities(false, maxResults, firstResult);
    }

    private List<Componente> findComponenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Componente.class));
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

    public Componente findComponente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Componente.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Componente> rt = cq.from(Componente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List permisosVentana(String codigoUsuario, String nombreComponente) {
        EntityManager em = getEntityManager();
        List notificaciones = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_hallar_permisos_componente (?, ?) ; ", "permisoComponente");

            pa.setParameter(1, codigoUsuario);
            pa.setParameter(2, nombreComponente);
            notificaciones = pa.getResultList();
            em.getTransaction().commit();
            return notificaciones;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List permisosTodasVentanas(String codigoUsuario) {
        EntityManager em = getEntityManager();
        List notificaciones = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_hallar_permisos_ventanas (?) ; ", "permisoComponente");

            pa.setParameter(1, codigoUsuario);
            notificaciones = pa.getResultList();
            em.getTransaction().commit();
            return notificaciones;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
