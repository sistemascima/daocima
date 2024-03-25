/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import entidades.TipoAnalisis;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class TipoAnalisisJpaController implements Serializable {

    public TipoAnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<TipoAnalisis> findTipoAnalisisEntities() {
        return findTipoAnalisisEntities(true, -1, -1);
    }

    public List<TipoAnalisis> findTipoAnalisisEntities(int maxResults, int firstResult) {
        return findTipoAnalisisEntities(false, maxResults, firstResult);
    }

    private List<TipoAnalisis> findTipoAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoAnalisis.class));
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

    public TipoAnalisis findTipoAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoAnalisis> rt = cq.from(TipoAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<TipoAnalisis> getTipoAnalisis() {
         //Deuvelve la matriz de analisis de la empresa
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_analisis ";
        try {
            Query q = em.createNativeQuery(consulta, TipoAnalisis.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
      public List<TipoAnalisis> getTipoAnalisisPorNombre(String nombreTipoAnalisis) {
         //Deuvelve la matriz de analisis de la empresa
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_analisis"
                + " where s_tipoanal_descripcion=? ";
        try {
            Query q = em.createNativeQuery(consulta, TipoAnalisis.class);
            q.setParameter(1, nombreTipoAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
