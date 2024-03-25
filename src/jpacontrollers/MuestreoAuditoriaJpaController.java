/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MuestreoAuditoria;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestreoAuditoriaJpaController implements Serializable {

    public MuestreoAuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestreoAuditoria muestreoAuditoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(muestreoAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestreoAuditoria muestreoAuditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            muestreoAuditoria = em.merge(muestreoAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestreoAuditoria.getMuestreoAuditoriaPi();
                if (findMuestreoAuditoria(id) == null) {
                    throw new NonexistentEntityException("The muestreoAuditoria with id " + id + " no longer exists.");
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
            MuestreoAuditoria muestreoAuditoria;
            try {
                muestreoAuditoria = em.getReference(MuestreoAuditoria.class, id);
                muestreoAuditoria.getMuestreoAuditoriaPi();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestreoAuditoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(muestreoAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestreoAuditoria> findMuestreoAuditoriaEntities() {
        return findMuestreoAuditoriaEntities(true, -1, -1);
    }

    public List<MuestreoAuditoria> findMuestreoAuditoriaEntities(int maxResults, int firstResult) {
        return findMuestreoAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<MuestreoAuditoria> findMuestreoAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestreoAuditoria.class));
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

    public MuestreoAuditoria findMuestreoAuditoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestreoAuditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestreoAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestreoAuditoria> rt = cq.from(MuestreoAuditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarCadenaCustodia(String idMuestreo) {
        EntityManager em = getEntityManager();
        String consulta = "Select pi_muestreo_auditoria_id, s_muestreo_auditoria_fechinic,"
                + " d_muestreo_auditoria_planmuestreo, fs_muestreo_auditoria_tecnico,"
                + " d_muestreo_auditoria_fecharecepmuestreo, proyecto.s_proyecto_nombre,"
                + " (select valor.s_valor_nombre from valor where valor.pi_valor_id=muestreo_auditoria.fi_muestreo_auditoria_ciudad), "
                + " (select valor.s_valor_nombre from valor where valor.pi_valor_id=muestreo_auditoria.fi_muestreo_auditoria_municipio), punto.s_punto_nombre,"
                + " d_muestreo_auditoria_fecharegistro, fs_muestreo_auditoria_usuariomodificacion,"
                + " d_muestreo_auditoria_fechamodificacion, s_muestreo_observacion"
                + " from muestreo_auditoria "
                + " join proyecto on proyecto.pi_proyecto_id=fi_muestreo_auditoria_proyecto"
                + " join valor on muestreo_auditoria.fi_muestreo_auditoria_municipio=valor.pi_valor_id"
                + " left join punto on punto.pi_punto_id= muestreo_auditoria.fi_muestreo_auditoria_punto "
                + " where pi_muestreo_auditoria_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestreo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
