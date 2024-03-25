/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MatrizAnalisis;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import entidades.VariableAnalisis;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class VariableAnalisisJpaController implements Serializable {

    public VariableAnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<VariableAnalisis> findVariableAnalisisEntities() {
        return findVariableAnalisisEntities(true, -1, -1);
    }

    public List<VariableAnalisis> findVariableAnalisisEntities(int maxResults, int firstResult) {
        return findVariableAnalisisEntities(false, maxResults, firstResult);
    }

    private List<VariableAnalisis> findVariableAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VariableAnalisis.class));
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

    public VariableAnalisis findVariableAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VariableAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariableAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VariableAnalisis> rt = cq.from(VariableAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

 

    public List<VariableAnalisis> getVariableAnalisisDescripcion(String descripcion, int idMatriz) {
       EntityManager em = getEntityManager();
        System.out.println("descripcion " + descripcion);
        System.out.println("matriz " + idMatriz);
        String consulta = "SELECT * FROM variable_analisis where fi_varianal_matriz = ? "
                + "and s_varianal_descripcion=?";
        try {
            Query q = em.createNativeQuery(consulta, VariableAnalisis.class);
            q.setParameter(1, idMatriz);
            q.setParameter(2, descripcion);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public int getIDVariableAnalisis(String descripcion, int idMatriz) {
       EntityManager em = getEntityManager();
        System.out.println("descripcion " + descripcion);
        System.out.println("matriz " + idMatriz);
        String consulta = "SELECT pi_varianal_id FROM variable_analisis where fi_varianal_matriz = ? "
                + "and s_varianal_descripcion=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMatriz);
            q.setParameter(2, descripcion);
            return Integer.valueOf(q.getSingleResult().toString());
        } finally {
            em.close();
        }
    }
    
    
    public List<Object> getVariableAnalisisPorId(int idVariableAnalisis) {
       EntityManager em = getEntityManager();
        String consulta = "SELECT s_varianal_descripcion FROM variable_analisis where pi_varianal_id = ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public List<VariableAnalisis> encontrarVariableMatriz(MatrizAnalisis matriz) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM variable_analisis where fi_varianal_matriz = ? "
                + " order by s_varianal_descripcion ";
        try {
            Query q = em.createNativeQuery(consulta, VariableAnalisis.class);
            q.setParameter(1, matriz.getPiMatranalId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarVariable() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct s_varianal_descripcion FROM variable_analisis order by s_varianal_descripcion";
        try {
            Query q = em.createNativeQuery(consulta);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Object> encontrarNombreVariables() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct s_varianal_descripcion FROM variable_analisis order by s_varianal_descripcion ASC";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<Object> encontrarNombreVariablesHidro() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct s_varianal_descripcion FROM variable_analisis where fi_varianal_matriz=2 order by s_varianal_descripcion ASC"
                + "  ";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
     public List<VariableAnalisis> encontrarParametroPorNombre(String nombreParametro) {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM variable_analisis "
                + " where s_varianal_descripcion=?";
        try {
            Query q = em.createNativeQuery(consulta, VariableAnalisis.class );
            q.setParameter(1, nombreParametro);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
     

    public void crearVariable(VariableAnalisis variableAnalisis) {
    EntityManager em = getEntityManager();
        try {     
               String insert = "INSERT INTO variable_analisis (fi_varianal_matriz, "
                     + "s_varianal_descripcion, "
                     + "fs_varianal_usuacrea, d_varianal_creacion) "
                     + "VALUES (?, ?, ?, now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, variableAnalisis.getFiVarianalMatriz().getPiMatranalId());
             insercion.setParameter(2, variableAnalisis.getSVarianalDescripcion());
             insercion.setParameter(3, variableAnalisis.getFsVarianalUsuacrea().getPsUsuarioCodigo());
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public void actualizarVariable(VariableAnalisis variable) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE variable_analisis "
                + "SET fi_varianal_matriz=?, "
                + " fs_varianal_usuultmod=?, "
                + " d_varianal_ultimodi= now(),"
                + " s_varianal_descripcion = ? "
                + "WHERE pi_varianal_id= ? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, variable.getFiVarianalMatriz().getPiMatranalId());
        insercion.setParameter(2, variable.getFsVarianalUsuultmod().getPsUsuarioCodigo());
        insercion.setParameter(3, variable.getSVarianalDescripcion());
        insercion.setParameter(4, variable.getPiVarianalId());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void eliminarVariable(VariableAnalisis variableAnalisis) {
        EntityManager em = getEntityManager();
        try {
            String delete = "delete from variable_analisis"
                    + " where pi_varianal_id = ? ";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(delete);
            insercion.setParameter(1, variableAnalisis.getPiVarianalId());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public int encontrarAutomatizacionResultados(int idVariable) {
     EntityManager em = getEntityManager();
        String consulta = "SELECT matriz_analisis.i_matranal_result_autom "
                + "from variable_analisis "
                + "join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz "
                + "where pi_varianal_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariable);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public int encontrarIdVariable(String nombreParametro , int idMatriz) {
     EntityManager em = getEntityManager();
        String consulta = "select pi_varianal_id "
                + "from variable_analisis "
                + "where fi_varianal_matriz=? "
                + "and s_varianal_descripcion=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMatriz);
            q.setParameter(2, nombreParametro);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}
