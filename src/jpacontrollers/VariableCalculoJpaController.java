/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
 
package jpacontrollers;

import entidades.MatrizAnalisis;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.TipoValor;
import entidades.VariableAnalisis;
import entidades.VariableCalculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA*/
 
public class VariableCalculoJpaController implements Serializable {

    public VariableCalculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VariableCalculo variableCalculo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoValor fiVariableCalculoTipoValor = variableCalculo.getFiVariableCalculoTipoValor();
            if (fiVariableCalculoTipoValor != null) {
                fiVariableCalculoTipoValor = em.getReference(fiVariableCalculoTipoValor.getClass(), fiVariableCalculoTipoValor.getIdtipoValor());
                variableCalculo.setFiVariableCalculoTipoValor(fiVariableCalculoTipoValor);
            }
            VariableAnalisis fiVariableVariableCalculo = variableCalculo.getFiVariableVariableCalculo();
            if (fiVariableVariableCalculo != null) {
                fiVariableVariableCalculo = em.getReference(fiVariableVariableCalculo.getClass(), fiVariableVariableCalculo.getPiVarianalId());
                variableCalculo.setFiVariableVariableCalculo(fiVariableVariableCalculo);
            }
            em.persist(variableCalculo);
           /* if (fiVariableCalculoTipoValor != null) {
                fiVariableCalculoTipoValor.getVariableCalculoList().add(variableCalculo);
                fiVariableCalculoTipoValor = em.merge(fiVariableCalculoTipoValor);
            }
            if (fiVariableVariableCalculo != null) {
                fiVariableVariableCalculo.getVariableCalculoList().add(variableCalculo);
                fiVariableVariableCalculo = em.merge(fiVariableVariableCalculo);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VariableCalculo variableCalculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableCalculo persistentVariableCalculo = em.find(VariableCalculo.class, variableCalculo.getPiVariableCalculo());
            TipoValor fiVariableCalculoTipoValorOld = persistentVariableCalculo.getFiVariableCalculoTipoValor();
            TipoValor fiVariableCalculoTipoValorNew = variableCalculo.getFiVariableCalculoTipoValor();
            VariableAnalisis fiVariableVariableCalculoOld = persistentVariableCalculo.getFiVariableVariableCalculo();
            VariableAnalisis fiVariableVariableCalculoNew = variableCalculo.getFiVariableVariableCalculo();
            if (fiVariableCalculoTipoValorNew != null) {
                fiVariableCalculoTipoValorNew = em.getReference(fiVariableCalculoTipoValorNew.getClass(), fiVariableCalculoTipoValorNew.getIdtipoValor());
                variableCalculo.setFiVariableCalculoTipoValor(fiVariableCalculoTipoValorNew);
            }
            if (fiVariableVariableCalculoNew != null) {
                fiVariableVariableCalculoNew = em.getReference(fiVariableVariableCalculoNew.getClass(), fiVariableVariableCalculoNew.getPiVarianalId());
                variableCalculo.setFiVariableVariableCalculo(fiVariableVariableCalculoNew);
            }
            variableCalculo = em.merge(variableCalculo);
            /*if (fiVariableCalculoTipoValorOld != null && !fiVariableCalculoTipoValorOld.equals(fiVariableCalculoTipoValorNew)) {
                fiVariableCalculoTipoValorOld.getVariableCalculoList().remove(variableCalculo);
                fiVariableCalculoTipoValorOld = em.merge(fiVariableCalculoTipoValorOld);
            }
            if (fiVariableCalculoTipoValorNew != null && !fiVariableCalculoTipoValorNew.equals(fiVariableCalculoTipoValorOld)) {
                fiVariableCalculoTipoValorNew.getVariableCalculoList().add(variableCalculo);
                fiVariableCalculoTipoValorNew = em.merge(fiVariableCalculoTipoValorNew);
            }
            if (fiVariableVariableCalculoOld != null && !fiVariableVariableCalculoOld.equals(fiVariableVariableCalculoNew)) {
                fiVariableVariableCalculoOld.getVariableCalculoList().remove(variableCalculo);
                fiVariableVariableCalculoOld = em.merge(fiVariableVariableCalculoOld);
            }
            if (fiVariableVariableCalculoNew != null && !fiVariableVariableCalculoNew.equals(fiVariableVariableCalculoOld)) {
                fiVariableVariableCalculoNew.getVariableCalculoList().add(variableCalculo);
                fiVariableVariableCalculoNew = em.merge(fiVariableVariableCalculoNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = variableCalculo.getPiVariableCalculo();
                if (findVariableCalculo(id) == null) {
                    throw new NonexistentEntityException("The variableCalculo with id " + id + " no longer exists.");
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
            VariableCalculo variableCalculo;
            try {
                variableCalculo = em.getReference(VariableCalculo.class, id);
                variableCalculo.getPiVariableCalculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variableCalculo with id " + id + " no longer exists.", enfe);
            }
            TipoValor fiVariableCalculoTipoValor = variableCalculo.getFiVariableCalculoTipoValor();
            /*if (fiVariableCalculoTipoValor != null) {
                fiVariableCalculoTipoValor.getVariableCalculoList().remove(variableCalculo);
                fiVariableCalculoTipoValor = em.merge(fiVariableCalculoTipoValor);
            }
            VariableAnalisis fiVariableVariableCalculo = variableCalculo.getFiVariableVariableCalculo();
            if (fiVariableVariableCalculo != null) {
                fiVariableVariableCalculo.getVariableCalculoList().remove(variableCalculo);
                fiVariableVariableCalculo = em.merge(fiVariableVariableCalculo);
            }*/
            em.remove(variableCalculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VariableCalculo> findVariableCalculoEntities() {
        return findVariableCalculoEntities(true, -1, -1);
    }

    public List<VariableCalculo> findVariableCalculoEntities(int maxResults, int firstResult) {
        return findVariableCalculoEntities(false, maxResults, firstResult);
    }

    private List<VariableCalculo> findVariableCalculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VariableCalculo.class));
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

    public VariableCalculo findVariableCalculo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VariableCalculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariableCalculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VariableCalculo> rt = cq.from(VariableCalculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<VariableCalculo> encontrarVariableParametro(Integer idVarianal, String nombreParametro) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM  variable_calculo"
                + " where s_variable_calculo_nombre=UPPER(?)"
                + " and fi_variable_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta, VariableCalculo.class);
            q.setParameter(1, nombreParametro);
            q.setParameter(2, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<Object> encontrarVariableParametro(Integer idVarianal) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT s_variable_calculo_nombre FROM  variable_calculo"
                + " where  fi_variable_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
     public List<Integer> encontrarVariableParametroPorIdVariable(Integer idVarianal) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_variable_calculo FROM  variable_calculo"
                + " where  fi_variable_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
      public List<Object> encontrarIdVariableCalculoPorNombre(int idVariableAnalisis, String nombreVariableCalculo) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_variable_calculo FROM  variable_calculo"
                + " where  fi_variable_variable_calculo=? and s_variable_calculo_nombre=UPPER(?)";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, nombreVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
      
       public VariableCalculo encontrarVariableCalculoPorNombre(int idVariableAnalisis, String nombreVariableCalculo) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM  variable_calculo"
                + " where  fi_variable_variable_calculo=? and s_variable_calculo_nombre=UPPER(?)";
        try {
            Query q = em.createNativeQuery(consulta, VariableCalculo.class);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, nombreVariableCalculo);
            return (VariableCalculo) q.getSingleResult();
        } finally {
            em.close();
        }
    }
     
      public List<VariableCalculo> encontrarVariableCalculoPorId(Integer idVarianal) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM  variable_calculo"
                + " where  pi_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta, VariableCalculo.class);
            q.setParameter(1, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
      public List<VariableCalculo> encontrarVariableParametroSalida(Integer idVarianal) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM  variable_calculo"
                + " where  fi_variable_variable_calculo=?"
                + " and i_variable_calculo_input = 0";
        try {
            Query q = em.createNativeQuery(consulta, VariableCalculo.class);
            q.setParameter(1, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
    } 
     
    public void crearVariableCalculo(VariableCalculo variableCalculo) {
        EntityManager em = getEntityManager();
        try {     
               String insert = "INSERT INTO variable_calculo (fi_variable_variable_calculo, "
                     + "s_variable_calculo_nombre, "
                     + "fi_variable_calculo_tipo_valor, i_variable_calculo_input, "
                     + "i_variable_calculo_valor_reporte, fs_variable_calculo_usuacreacion, "
                     + "d_variable_calculo_creacion, fi_variable_calculo_variable_referencia )"
                     + "VALUES (?,UPPER(?),?,?,?,?,now(), ?)";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, variableCalculo.getFiVariableVariableCalculo().getPiVarianalId());
             insercion.setParameter(2, variableCalculo.getSVariableCalculoNombre());
             insercion.setParameter(3, variableCalculo.getFiVariableCalculoTipoValor().getIdtipoValor());
             insercion.setParameter(4, variableCalculo.getIVariableCalculoInput());
             insercion.setParameter(5, variableCalculo.getIVariableCalculoValorReporte());
             insercion.setParameter(6, variableCalculo.getFsVariableCalculoUsuacreacion().getPsUsuarioCodigo());
             insercion.setParameter(7, variableCalculo.getFiVariableCalculoVariableReferencia().getPiVarianalId());
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public List<Object> encontrarVariablesCalculo(String filtros, List<Object> argumentos) {
     EntityManager em = getEntityManager();
         String consulta = "select variable_analisis.s_varianal_descripcion, variable_calculo.s_variable_calculo_nombre, " +
                "tipo_valor.s_tipoval_nombre, variable_calculo.i_variable_calculo_input, variable_calculo.i_variable_calculo_valor_reporte"
                + " from variable_calculo "                 
                + "join variable_analisis on variable_analisis.pi_varianal_id = variable_calculo.fi_variable_variable_calculo "
                + "join matriz_analisis on matriz_analisis.pi_matranal_id = variable_analisis.fi_varianal_matriz "
                + "join tipo_valor on tipo_valor.pi_tipoval_id = variable_calculo.fi_variable_calculo_tipo_valor " +
                "where ";
        consulta+=filtros;
        try {
            Query q = em.createNativeQuery(consulta);
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }   
    
    }

    public void actualizarVariableCalculo(VariableCalculo variableCalculo) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE variable_calculo "
                + "SET fi_variable_variable_calculo=?, "
                + " s_variable_calculo_nombre=?, "
                + " fi_variable_calculo_tipo_valor= ?,"
                + " i_variable_calculo_input = ?,"
                + " i_variable_calculo_valor_reporte = ?,"
                + " fs_variable_calculo_usuultmod = ?, "
                + " d_variable_calculo_ultimodi = now() "
                + "WHERE pi_variable_calculo= ? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, variableCalculo.getFiVariableVariableCalculo().getPiVarianalId());
        insercion.setParameter(2, variableCalculo.getSVariableCalculoNombre().toUpperCase());
        insercion.setParameter(3, variableCalculo.getFiVariableCalculoTipoValor().getIdtipoValor());
        insercion.setParameter(4, variableCalculo.getIVariableCalculoInput());
        insercion.setParameter(5, variableCalculo.getIVariableCalculoValorReporte());
        insercion.setParameter(6, variableCalculo.getFsVariableCalculoUsuultmod().getPsUsuarioCodigo());
        insercion.setParameter(7, variableCalculo.getPiVariableCalculo());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void eliminarVariableCalculo(VariableCalculo variableCalculo) {
        EntityManager em = getEntityManager();
            try {
                String delete = "delete from variable_calculo"
                        + " where pi_variable_calculo = ? ";
                em.getTransaction().begin();
                Query insercion = em.createNativeQuery(delete);
                insercion.setParameter(1, variableCalculo.getPiVariableCalculo());
                insercion.executeUpdate();
                em.getTransaction().commit();
            } catch (Exception ex) {
                throw ex;
            } finally {
                em.close();
            }    
    }

    public List<Object> encontrarVariablesCalculoPorFormula(String filtros, List<Object> argumentos) {
          EntityManager em = getEntityManager();
         String consulta = "select  variable_calculo.s_variable_calculo_nombre " 
                + " from variable_calculo "                 
                + "join variable_analisis on variable_analisis.pi_varianal_id = variable_calculo.fi_variable_variable_calculo "
                + "join matriz_analisis on matriz_analisis.pi_matranal_id = variable_analisis.fi_varianal_matriz "
                + "join tipo_valor on tipo_valor.pi_tipoval_id = variable_calculo.fi_variable_calculo_tipo_valor " +
                "where ";
        consulta+=filtros;
        consulta+="   order by i_variable_calculo_orden";
        try {
            Query q = em.createNativeQuery(consulta);
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }   
    }
    
     public List<Object> encontrarVariablesCalculoFormulaPorVariableAnalisis(int idVariable) {
          EntityManager em = getEntityManager();
         String consulta = "select  variable_calculo.s_variable_calculo_nombre " 
                + " from variable_calculo "                 
                + "join variable_analisis on variable_analisis.pi_varianal_id = variable_calculo.fi_variable_variable_calculo "
                + "join tipo_valor on tipo_valor.pi_tipoval_id = variable_calculo.fi_variable_calculo_tipo_valor " +
                "where variable_analisis.pi_varianal_id=? ";
        consulta+="   order by variable_calculo.i_variable_calculo_input";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariable);
            return q.getResultList();
        } finally {
            em.close();
        }   
    }

    
    public List<Object> encontrarConstantes(int idVariable){
         EntityManager em = getEntityManager();
        String consulta = "select  pi_variable_calculo "
                + "from variable_calculo "
                + "where fi_variable_variable_calculo= ? "
                + "and fi_variable_calculo_tipo_valor= 12 ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariable);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarVariablesEntrada(VariableAnalisis variableAnalisis) {
     EntityManager em = getEntityManager();
        String consulta = "select s_variable_calculo_nombre from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and i_variable_calculo_input=1";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            return q.getResultList();
        } finally {
            em.close();
        }
    
    }
    
    public List<Object> encontrarVariablesSalida(VariableAnalisis variableAnalisis) {
     EntityManager em = getEntityManager();
        String consulta = "select pi_variable_calculo from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and i_variable_calculo_input=0";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<VariableCalculo> encontrarVariableSalida(int idVariableAnalisis){
         EntityManager em = getEntityManager();
        String consulta = "select * from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and i_variable_calculo_input=0";
        try {
            Query q = em.createNativeQuery(consulta, VariableCalculo.class);
            q.setParameter(1, idVariableAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VariableCalculo> encontrarVariableCalculo(VariableCalculo variableCalculo) {     
     EntityManager em = getEntityManager();
        String consulta = "select * from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and s_variable_calculo_nombre=?"
                + " and fi_variable_calculo_tipo_valor = 12";
        try {
            Query q = em.createNativeQuery(consulta,VariableCalculo.class);
            q.setParameter(1, variableCalculo.getFiVariableVariableCalculo().getPiVarianalId());
            q.setParameter(2, variableCalculo.getSVariableCalculoNombre());            
            return q.getResultList();
        } finally {
            em.close();
        } 
    }
    
   public List<VariableCalculo> encontrarVariableCalculoPorNombre(String nombreVariable, VariableAnalisis variable) {     
       EntityManager em = getEntityManager();
        String consulta = "select * from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and s_variable_calculo_nombre=UPPER(?)";
        try {
            Query q = em.createNativeQuery(consulta,VariableCalculo.class);
            q.setParameter(1, variable.getPiVarianalId());
            q.setParameter(2, nombreVariable);            
            return q.getResultList();
        } finally {
            em.close();
        }
   }
   
   
   

    public List<VariableCalculo> encontrarVariableCalculoReferencia(VariableAnalisis variableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "select *from variable_calculo "
                + "where fi_variable_variable_calculo=? "
                + "and fi_variable_calculo_variable_referencia is not null";
        try {
            Query q = em.createNativeQuery(consulta,VariableCalculo.class);
            q.setParameter(1, variableAnalisis.getPiVarianalId());            
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VariableCalculo> encontrarVariableReferencia(VariableAnalisis variableAnalisis) {
     EntityManager em = getEntityManager();
        String consulta = "select *from variable_calculo "
                + " where fi_variable_calculo_variable_referencia=?";
        try {
            Query q = em.createNativeQuery(consulta,VariableCalculo.class);
            q.setParameter(1, variableAnalisis.getPiVarianalId());            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Object> encontrarValoresConstantes(String filtros, List<Object> argumentos){
        EntityManager em = getEntityManager();     
         String consulta = " SELECT variable_analisis.s_varianal_descripcion,"
                 + " variable_calculo.s_variable_calculo_nombre, "
                 + " constante.s_constante_valor"
                 + " from variable_calculo"
                 + " join variable_analisis on variable_analisis.pi_varianal_id= variable_calculo.fi_variable_variable_calculo"
                 + " join constante on constante.fi_constante_variable_calculo= variable_calculo.pi_variable_calculo"
                 + " join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz" 
                 + " where fi_variable_calculo_tipo_valor=12 " ;
        consulta += filtros;
        consulta += " order by variable_analisis.s_varianal_descripcion asc";
         try {  
            Query q = em.createNativeQuery(consulta);
            System.out.println("query"+ q.toString());
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                 q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarIdConstantePorVariableNombreMatriz(String nombreVariable, String matriz, String nombreConstante) {
         EntityManager em = getEntityManager();
        String consulta = "select pi_constante_id "
                + "from constante "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= constante.fi_constante_variable_calculo "
                + "join variable_analisis on variable_analisis.pi_varianal_id= variable_calculo.fi_variable_variable_calculo "
                + "join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz "
                + "where variable_analisis.s_varianal_descripcion=? "
                + "and matriz_analisis.s_matranal_descripcion= ? " +
                "and variable_calculo.s_variable_calculo_nombre= ?" ;
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1,nombreVariable);
            q.setParameter(2,matriz);
            q.setParameter(3,nombreConstante);
            return q.getResultList();
        } finally {
            em.close();
        }
    
    
    }

    public List<Object> encontrarIdVariableCalculoPorNombre(String nombre, int idVariableAnalisis) {
     EntityManager em = getEntityManager();
        String consulta = "select pi_variable_calculo from variable_calculo "
                + " where fi_variable_variable_calculo= ?"
                + " and s_variable_calculo_nombre=UPPER(?)";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, nombre);            
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> esVariableSalida(int idVariableCalculo) {
        EntityManager em = getEntityManager();
        String consulta = "select pi_variable_calculo from variable_calculo "
                + " where pi_variable_calculo= ? "
                + " and i_variable_calculo_valor_reporte=1";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
   
}
