/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Hardware;
import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HelpDesk
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario usuarioId = solicitud.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getPsUsuarioCodigo());
                solicitud.setUsuarioId(usuarioId);
            }
            em.persist(solicitud);
            /*if (usuarioId != null) {
                usuarioId.getSolicitudList().add(solicitud);
                usuarioId = em.merge(usuarioId);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getSolicitudId());
            SUsuario usuarioIdOld = persistentSolicitud.getUsuarioId();
            SUsuario usuarioIdNew = solicitud.getUsuarioId();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getPsUsuarioCodigo());
                solicitud.setUsuarioId(usuarioIdNew);
            }
            solicitud = em.merge(solicitud);
           /* if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getSolicitudList().remove(solicitud);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getSolicitudList().add(solicitud);
                usuarioIdNew = em.merge(usuarioIdNew);
        }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getSolicitudId();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getSolicitudId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            SUsuario usuarioId = solicitud.getUsuarioId();
          /*  if (usuarioId != null) {
                usuarioId.getSolicitudList().remove(solicitud);
                usuarioId = em.merge(usuarioId);
            }*/
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public List<Solicitud> findSolicitudByUsuario(SUsuario usuario) {

        System.out.println("usuario llega" + usuario.getPsUsuarioCodigo());
        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Solicitud.findSolicitudByUserId")
                .setParameter("usuarioId", usuario)
                .getResultList();

        return results;
    }

    public List<Solicitud> findSolicitudbyHardware(Hardware hardware) {
    
    
        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Solicitud.findSolicitudByHardware")
                .setParameter("hardwareId", hardware.getId())
                .getResultList();

        return results;
    
    }
    
}
