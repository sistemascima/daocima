/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.MatrizAnalisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author Jhon
 */
public class MatrizAnalisisJpaController implements Serializable {

    public MatrizAnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MatrizAnalisis matrizAnalisis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(matrizAnalisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MatrizAnalisis matrizAnalisis) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MatrizAnalisis persistentMatrizAnalisis = em.find(MatrizAnalisis.class, matrizAnalisis.getPiMatranalId());
            matrizAnalisis = em.merge(matrizAnalisis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = matrizAnalisis.getPiMatranalId();
                if (findMatrizAnalisis(id) == null) {
                    throw new NonexistentEntityException("The matrizAnalisis with id " + id + " no longer exists.");
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
            MatrizAnalisis matrizAnalisis;
            try {
                matrizAnalisis = em.getReference(MatrizAnalisis.class, id);
                matrizAnalisis.getPiMatranalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matrizAnalisis with id " + id + " no longer exists.", enfe);
            }
            em.remove(matrizAnalisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MatrizAnalisis> findMatrizAnalisisEntities() {
        return findMatrizAnalisisEntities(true, -1, -1);
    }

    public List<MatrizAnalisis> findMatrizAnalisisEntities(int maxResults, int firstResult) {
        return findMatrizAnalisisEntities(false, maxResults, firstResult);
    }

    private List<MatrizAnalisis> findMatrizAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MatrizAnalisis.class));
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

    public MatrizAnalisis findMatrizAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MatrizAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatrizAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MatrizAnalisis> rt = cq.from(MatrizAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<MatrizAnalisis> consultaMatricesAnalisisCompra() {
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT DISTINCT ma.* "
                + "FROM matriz_analisis ma " +
                "INNER JOIN variable_analisis va ON va.fi_varianal_matriz = ma.pi_matranal_id " +
                "ORDER BY ma.pi_matranal_id;";
        try {
            Query q = em.createNativeQuery(consulta, MatrizAnalisis.class);
            List<MatrizAnalisis> p = q.getResultList();
            return p;
        } finally {
            em.close();
        }
    }
    
    public List<MatrizAnalisis> getMatricesEmpresa() {
         //Deuvelve la matriz de analisis de la empresa
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM matriz_analisis where pi_matranal_id=1 or pi_matranal_id=2 "
                + "or pi_matranal_id=3 or pi_matranal_id=7 or pi_matranal_id=11"
                + " or pi_matranal_id=12 or pi_matranal_id=5"
                + " or pi_matranal_id=13 or pi_matranal_id=8"
                + " or pi_matranal_id=14 or pi_matranal_id=16 or pi_matranal_id=17";
        try {
            Query q = em.createNativeQuery(consulta, MatrizAnalisis.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

  
    public List<MatrizAnalisis> getMatriz(String nombre) {
        List results = getEntityManager().createNamedQuery("MatrizAnalisis.findBySMatranalDescripcion").setParameter("sMatranalDescripcion", nombre).getResultList();  
        return results;
    }
    
    public List<MatrizAnalisis> encontrarMatrizPorId(int id){
        List results = getEntityManager().createNamedQuery("MatrizAnalisis.findByPiMatranalId").setParameter("piMatranalId", id).getResultList();
        return results;
    }
    
    public List<Object> encontrarMatrizPorReporte(String idReporte){
        //Deuvelve la matriz de analisis de la empresa
         EntityManager em = getEntityManager();
        String consulta = "select distinct matriz_analisis.pi_matranal_id"
                + " from matriz_analisis"
                + " join muestra on muestra.fi_muestra_matriz = matriz_analisis.pi_matranal_id"
                + " join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte"
                + " where reporte.pi_reporte_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarMatrizPorCadena(String idCadenaCustodia) {
        System.out.println("id cadena custodia"+idCadenaCustodia);
        EntityManager em = getEntityManager();
        String consulta = "select distinct muestra.fi_muestra_matriz"
                + " from muestreo"
                + " join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id"
                + " where muestreo.pi_muestreo_id= ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idCadenaCustodia);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
