/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MuestraAnalisisAuditoria;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestraAnalisisAuditoriaJpaController implements Serializable {

    public MuestraAnalisisAuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraAnalisisAuditoria muestraAnalisisAuditoria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(muestraAnalisisAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMuestraAnalisisAuditoria(muestraAnalisisAuditoria.getPiMuestraanalAuditoriaId()) != null) {
                throw new PreexistingEntityException("MuestraAnalisisAuditoria " + muestraAnalisisAuditoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraAnalisisAuditoria muestraAnalisisAuditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            muestraAnalisisAuditoria = em.merge(muestraAnalisisAuditoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraAnalisisAuditoria.getPiMuestraanalAuditoriaId();
                if (findMuestraAnalisisAuditoria(id) == null) {
                    throw new NonexistentEntityException("The muestraAnalisisAuditoria with id " + id + " no longer exists.");
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
            MuestraAnalisisAuditoria muestraAnalisisAuditoria;
            try {
                muestraAnalisisAuditoria = em.getReference(MuestraAnalisisAuditoria.class, id);
                muestraAnalisisAuditoria.getPiMuestraanalAuditoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraAnalisisAuditoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(muestraAnalisisAuditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraAnalisisAuditoria> findMuestraAnalisisAuditoriaEntities() {
        return findMuestraAnalisisAuditoriaEntities(true, -1, -1);
    }

    public List<MuestraAnalisisAuditoria> findMuestraAnalisisAuditoriaEntities(int maxResults, int firstResult) {
        return findMuestraAnalisisAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<MuestraAnalisisAuditoria> findMuestraAnalisisAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraAnalisisAuditoria.class));
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

    public MuestraAnalisisAuditoria findMuestraAnalisisAuditoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraAnalisisAuditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraAnalisisAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraAnalisisAuditoria> rt = cq.from(MuestraAnalisisAuditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Object> getCargarMuestraParametro(String muestra, String parametro) {
        EntityManager em = getEntityManager();
        String consulta = "select fi_muestraanal_auditoria_muestra, s_muestraanal_auditoria_realizado,"
                + " fs_muestraanal_auditoria_proveedor, fs_muestraanal_audioria_usuacrea,"
                + " d_muestraanal_auditoria_fechingr, fs_muestraanal_auditoria_usuamodi,"
                + " d_muestraanal_auditoria_fechmodi, s_muestraanal_auditoria_observaciones,"
                + " analista_parametro.fs_analparam_usutitul,"
                + " analista_parametro.fs_analparam_ususupl, analista_parametro.fs_analparam_usuterc,"
                + " valor_auditoria.s_valor_auditoria_nombre, valor_auditoria.s_valor_auditoria_valor,"
                + " valor_auditoria.s_valor_valorreal,"
                + " valor_auditoria.s_valor_auditoria_observaciones, valor_auditoria.fs_valor_auditoria_usuamodi,"
                + " valor_auditoria.d_valor_auditoria_fechregi, valor_auditoria.d_valor_auditoria_fechmodi"
                + " from muestra_analisis_auditoria"
                + " join analista_parametro on"
                + " analista_parametro.pi_analparam_id= muestra_analisis_auditoria.fi_muestraanal_auditoria_analparam"
                + " join valor_auditoria on valor_auditoria.fi_valor_auditoria_muestraanal= muestra_analisis_auditoria.pi_muestraanal_auditoria_id"
                + " where ";
        
        if(muestra.isEmpty() && parametro!=null){
              consulta += " valor_auditoria.s_valor_auditoria_nombre=?";
              
              Query q = em.createNativeQuery(consulta);
              q.setParameter(1, parametro);
              System.out.println("contulta"+ consulta);
              return q.getResultList();
        }
        else{
            consulta += " fi_muestraanal_auditoria_muestra = ? and s_valor_auditoria_nombre=?";
            System.out.println("contulta"+ consulta);
            Query q= em.createNativeQuery(consulta);
            q.setParameter(1, muestra);
            q.setParameter(2, parametro);
            return q.getResultList();
        }   
    
    }
    
}
