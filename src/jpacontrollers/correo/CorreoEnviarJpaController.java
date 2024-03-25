/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers.correo;

import conexionbd.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.correo.CorreoEnviar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Daniel-SIS
 */
public class CorreoEnviarJpaController implements Serializable {

    public CorreoEnviarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param correoEnviar
     */
    public void insertar(CorreoEnviar correoEnviar)  {
        EntityManager em = null;
        try {
            em = getEntityManager();
            String consulta = "INSERT INTO `correo_enviar`"
                    + " (`s_correnvi_destinatarios`, `s_correnvi_destcopi`, `s_correnvi_subject`, `s_correnvi_body`, `fs_correnvi_usuacrea`, `d_correnvi_creacion`) "
                    + "VALUES (?, ?, ?, ?, ? , NOW() ); ";
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, correoEnviar.getSCorrenviDestinatarios());
            q.setParameter(2, correoEnviar.getSCorrenviDestcopi());
            q.setParameter(3, correoEnviar.getSCorrenviSubject());
            q.setParameter(4, correoEnviar.getSCorrenviBody());
            q.setParameter(5, correoEnviar.getFsCorrenviUsuacrea().getPsUsuarioCodigo());
            q.executeUpdate();
            q.toString();
            em.getTransaction().commit();            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(CorreoEnviar correoEnviar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(correoEnviar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CorreoEnviar correoEnviar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CorreoEnviar persistentCorreoEnviar = em.find(CorreoEnviar.class, correoEnviar.getPiCorrenviConsecutivo());
            correoEnviar = em.merge(correoEnviar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = correoEnviar.getPiCorrenviConsecutivo();
                if (findCorreoEnviar(id) == null) {
                    throw new NonexistentEntityException("The correoEnviar with id " + id + " no longer exists.");
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
            CorreoEnviar correoEnviar;
            try {
                correoEnviar = em.getReference(CorreoEnviar.class, id);
                correoEnviar.getPiCorrenviConsecutivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The correoEnviar with id " + id + " no longer exists.", enfe);
            }
            em.remove(correoEnviar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CorreoEnviar> findCorreoEnviarEntities() {
        return findCorreoEnviarEntities(true, -1, -1);
    }

    public List<CorreoEnviar> findCorreoEnviarEntities(int maxResults, int firstResult) {
        return findCorreoEnviarEntities(false, maxResults, firstResult);
    }

    private List<CorreoEnviar> findCorreoEnviarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CorreoEnviar.class));
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

    public CorreoEnviar findCorreoEnviar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CorreoEnviar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorreoEnviarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CorreoEnviar> rt = cq.from(CorreoEnviar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<CorreoEnviar> findbyBody(String body) {
        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("CorreoEnviar.findByBody")
                .setParameter("sCorrenviBody", body)
                .getResultList();

        return results;
    }

    public void crearcorreos(List correoenviar) {
         EntityManager em = null;
        em = getEntityManager();
        em.getTransaction().begin();
        Query q;
        for (Iterator iterator = correoenviar.iterator(); iterator.hasNext(); q.executeUpdate()) {
            CorreoEnviar correoEnviar = (CorreoEnviar) iterator.next();
            String consulta = "INSERT INTO `correo_enviar` (`s_correnvi_destinatarios`, `s_correnvi_destcopi`, "
                    + "`s_correnvi_subject`, `s_correnvi_body`, `fs_correnvi_usuacrea`, `d_correnvi_cre"
                    + "acion`) VALUES (?, ?, ?, ?, ? , NOW() ); ";
            q = em.createNativeQuery(consulta);
            q.setParameter(1, correoEnviar.getSCorrenviDestinatarios());
            q.setParameter(2, correoEnviar.getSCorrenviDestcopi());
            q.setParameter(3, correoEnviar.getSCorrenviSubject());
            q.setParameter(4, correoEnviar.getSCorrenviBody());
            q.setParameter(5, "SISTEMAS");
        }
        em.getTransaction().commit();
        if (em != null) {
            em.close();
        }
    }

    public Object ContarCorreos(String asunto) {
        EntityManager em;
        em = getEntityManager();
        Long resultados = null;
        Long long2;
        String consulta = "SELECT count(cor.pi_correnvi_consecutivo) FROM correo_enviar cor WHERE s_correnv"
                + "i_subject=?;";
        Query q = em.createNativeQuery(consulta);
        q.setParameter(1, asunto);
        resultados = (Long) q.getSingleResult();
        long2 = resultados;
        em.close();
        return long2;
    }



}
