/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MuestraAuditoria;
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
public class MuestraAuditoriaJpaController implements Serializable {

    public MuestraAuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraAuditoria muestraAuditoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(muestraAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraAuditoria muestraAuditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            muestraAuditoria = em.merge(muestraAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraAuditoria.getPiMuestraAuditoriaId();
                if (findMuestraAuditoria(id) == null) {
                    throw new NonexistentEntityException("The muestraAuditoria with id " + id + " no longer exists.");
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
            MuestraAuditoria muestraAuditoria;
            try {
                muestraAuditoria = em.getReference(MuestraAuditoria.class, id);
                muestraAuditoria.getPiMuestraAuditoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraAuditoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(muestraAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraAuditoria> findMuestraAuditoriaEntities() {
        return findMuestraAuditoriaEntities(true, -1, -1);
    }

    public List<MuestraAuditoria> findMuestraAuditoriaEntities(int maxResults, int firstResult) {
        return findMuestraAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<MuestraAuditoria> findMuestraAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraAuditoria.class));
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

    public MuestraAuditoria findMuestraAuditoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraAuditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraAuditoria> rt = cq.from(MuestraAuditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarMuestra(String idMuestra){
        EntityManager em = getEntityManager();
        String consulta = "select fi_muestra_auditoria_muestreo, fi_muestra_auditoria_tecnico,"
                + " fi_muestra_auditoria_cliente, tipo_muestreo.s_tipomuestreo_nombre,"
                + " fi_muestra_auditoria_matriz, fi_muestra_auditoria_reporte, "
                + " s_muestra_auditoria_observaciones, d_muestra_auditoria_fechanal,"
                + " proyecto.s_proyecto_nombre, fi_muestra_auditoria_campo,"
                + " s_muestra_auditoria_descripcion, d_muestra_auditoria_fechtomamuest,"
                + " d_muestra_auditoria_fechcreac, fs_muestra_auditoria_usucreac,"
                + " fs_muestra_auditoria_usuamodi, d_muestra_auditoria_fechmodi,"
                + " tipo_muestra.s_tipomuestra_nombre from muestra_auditoria"
                + " join tipo_muestreo on tipo_muestreo.pi_tipomuestreo_id= fi_muestra_auditoria_tipomuestreo" 
                + " join tipo_muestra on tipo_muestra.pi_tipomuestra_id= fi_muestra_auditoria_tipomuest"
                + " join proyecto on proyecto.pi_proyecto_id=fi_muestra_auditoria_proyecto"
                + " where pi_muestra_auditoria_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1,idMuestra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
