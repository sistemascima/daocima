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
import entidades.SUsuario;
import entidades.Valor;
import entidades.VariableCalculo;
import entidades.DatoHidrobiologia;
import entidades.Resultado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author SISTEMAS
 */
public class ResultadoJpaController implements Serializable {

    public ResultadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*public void create(Resultado resultado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsResultadoUsuacreacion = resultado.getFsResultadoUsuacreacion();
            if (fsResultadoUsuacreacion != null) {
                fsResultadoUsuacreacion = em.getReference(fsResultadoUsuacreacion.getClass(), fsResultadoUsuacreacion.getPsUsuarioCodigo());
                resultado.setFsResultadoUsuacreacion(fsResultadoUsuacreacion);
            }
            SUsuario fsResultadoUsuamodificacion = resultado.getFsResultadoUsuamodificacion();
            if (fsResultadoUsuamodificacion != null) {
                fsResultadoUsuamodificacion = em.getReference(fsResultadoUsuamodificacion.getClass(), fsResultadoUsuamodificacion.getPsUsuarioCodigo());
                resultado.setFsResultadoUsuamodificacion(fsResultadoUsuamodificacion);
            }
            Valor fiResultadoValor = resultado.getFiResultadoValor();
           /* if (fiResultadoValor != null) {
                fiResultadoValor = em.getReference(fiResultadoValor.getClass(), fiResultadoValor.getIdValor());
                resultado.setFiResultadoValor(fiResultadoValor);
            }
            VariableCalculo fiResultadoVariableCalculo = resultado.getFiResultadoVariableCalculo();
            if (fiResultadoVariableCalculo != null) {
                fiResultadoVariableCalculo = em.getReference(fiResultadoVariableCalculo.getClass(), fiResultadoVariableCalculo.getPiVariableCalculo());
                resultado.setFiResultadoVariableCalculo(fiResultadoVariableCalculo);
            }
            DatoHidrobiologia fkiResultadoDatoHidrobiologia = resultado.getFkiResultadoDatoHidrobiologia();
            if (fkiResultadoDatoHidrobiologia != null) {
                fkiResultadoDatoHidrobiologia = em.getReference(fkiResultadoDatoHidrobiologia.getClass(), fkiResultadoDatoHidrobiologia.getPiDatoHidro());
                resultado.setFkiResultadoDatoHidrobiologia(fkiResultadoDatoHidrobiologia);
            }
            em.persist(resultado);
            if (fsResultadoUsuacreacion != null) {
                fsResultadoUsuacreacion.getResultadoList().add(resultado);
                fsResultadoUsuacreacion = em.merge(fsResultadoUsuacreacion);
            }
            if (fsResultadoUsuamodificacion != null) {
                fsResultadoUsuamodificacion.getResultadoList().add(resultado);
                fsResultadoUsuamodificacion = em.merge(fsResultadoUsuamodificacion);
            }
            /*if (fiResultadoValor != null) {
                fiResultadoValor.getResultadoList().add(resultado);
                fiResultadoValor = em.merge(fiResultadoValor);
            }
            if (fiResultadoVariableCalculo != null) {
                fiResultadoVariableCalculo.getResultadoList().add(resultado);
                fiResultadoVariableCalculo = em.merge(fiResultadoVariableCalculo);
            }
            if (fkiResultadoDatoHidrobiologia != null) {
                fkiResultadoDatoHidrobiologia.getResultadoList().add(resultado);
                fkiResultadoDatoHidrobiologia = em.merge(fkiResultadoDatoHidrobiologia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultado resultado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultado persistentResultado = em.find(Resultado.class, resultado.getPiResultadoId());
            SUsuario fsResultadoUsuacreacionOld = persistentResultado.getFsResultadoUsuacreacion();
            SUsuario fsResultadoUsuacreacionNew = resultado.getFsResultadoUsuacreacion();
            SUsuario fsResultadoUsuamodificacionOld = persistentResultado.getFsResultadoUsuamodificacion();
            SUsuario fsResultadoUsuamodificacionNew = resultado.getFsResultadoUsuamodificacion();
            Valor fiResultadoValorOld = persistentResultado.getFiResultadoValor();
            Valor fiResultadoValorNew = resultado.getFiResultadoValor();
            VariableCalculo fiResultadoVariableCalculoOld = persistentResultado.getFiResultadoVariableCalculo();
            VariableCalculo fiResultadoVariableCalculoNew = resultado.getFiResultadoVariableCalculo();
            DatoHidrobiologia fkiResultadoDatoHidrobiologiaOld = persistentResultado.getFkiResultadoDatoHidrobiologia();
            DatoHidrobiologia fkiResultadoDatoHidrobiologiaNew = resultado.getFkiResultadoDatoHidrobiologia();
            if (fsResultadoUsuacreacionNew != null) {
                fsResultadoUsuacreacionNew = em.getReference(fsResultadoUsuacreacionNew.getClass(), fsResultadoUsuacreacionNew.getPsUsuarioCodigo());
                resultado.setFsResultadoUsuacreacion(fsResultadoUsuacreacionNew);
            }
            if (fsResultadoUsuamodificacionNew != null) {
                fsResultadoUsuamodificacionNew = em.getReference(fsResultadoUsuamodificacionNew.getClass(), fsResultadoUsuamodificacionNew.getPsUsuarioCodigo());
                resultado.setFsResultadoUsuamodificacion(fsResultadoUsuamodificacionNew);
            }
            if (fiResultadoValorNew != null) {
                fiResultadoValorNew = em.getReference(fiResultadoValorNew.getClass(), fiResultadoValorNew.getIdValor());
                resultado.setFiResultadoValor(fiResultadoValorNew);
            }
            if (fiResultadoVariableCalculoNew != null) {
                fiResultadoVariableCalculoNew = em.getReference(fiResultadoVariableCalculoNew.getClass(), fiResultadoVariableCalculoNew.getPiVariableCalculo());
                resultado.setFiResultadoVariableCalculo(fiResultadoVariableCalculoNew);
            }
            if (fkiResultadoDatoHidrobiologiaNew != null) {
                fkiResultadoDatoHidrobiologiaNew = em.getReference(fkiResultadoDatoHidrobiologiaNew.getClass(), fkiResultadoDatoHidrobiologiaNew.getPiDatoHidro());
                resultado.setFkiResultadoDatoHidrobiologia(fkiResultadoDatoHidrobiologiaNew);
            }
            resultado = em.merge(resultado);
            if (fsResultadoUsuacreacionOld != null && !fsResultadoUsuacreacionOld.equals(fsResultadoUsuacreacionNew)) {
                fsResultadoUsuacreacionOld.getResultadoList().remove(resultado);
                fsResultadoUsuacreacionOld = em.merge(fsResultadoUsuacreacionOld);
            }
            if (fsResultadoUsuacreacionNew != null && !fsResultadoUsuacreacionNew.equals(fsResultadoUsuacreacionOld)) {
                fsResultadoUsuacreacionNew.getResultadoList().add(resultado);
                fsResultadoUsuacreacionNew = em.merge(fsResultadoUsuacreacionNew);
            }
            if (fsResultadoUsuamodificacionOld != null && !fsResultadoUsuamodificacionOld.equals(fsResultadoUsuamodificacionNew)) {
                fsResultadoUsuamodificacionOld.getResultadoList().remove(resultado);
                fsResultadoUsuamodificacionOld = em.merge(fsResultadoUsuamodificacionOld);
            }
            if (fsResultadoUsuamodificacionNew != null && !fsResultadoUsuamodificacionNew.equals(fsResultadoUsuamodificacionOld)) {
                fsResultadoUsuamodificacionNew.getResultadoList().add(resultado);
                fsResultadoUsuamodificacionNew = em.merge(fsResultadoUsuamodificacionNew);
            }
            /*if (fiResultadoValorOld != null && !fiResultadoValorOld.equals(fiResultadoValorNew)) {
                fiResultadoValorOld.getResultadoList().remove(resultado);
                fiResultadoValorOld = em.merge(fiResultadoValorOld);
            }
            if (fiResultadoValorNew != null && !fiResultadoValorNew.equals(fiResultadoValorOld)) {
                fiResultadoValorNew.getResultadoList().add(resultado);
                fiResultadoValorNew = em.merge(fiResultadoValorNew);
            }
            if (fiResultadoVariableCalculoOld != null && !fiResultadoVariableCalculoOld.equals(fiResultadoVariableCalculoNew)) {
                fiResultadoVariableCalculoOld.getResultadoList().remove(resultado);
                fiResultadoVariableCalculoOld = em.merge(fiResultadoVariableCalculoOld);
            }
            if (fiResultadoVariableCalculoNew != null && !fiResultadoVariableCalculoNew.equals(fiResultadoVariableCalculoOld)) {
                fiResultadoVariableCalculoNew.getResultadoList().add(resultado);
                fiResultadoVariableCalculoNew = em.merge(fiResultadoVariableCalculoNew);
            }
            if (fkiResultadoDatoHidrobiologiaOld != null && !fkiResultadoDatoHidrobiologiaOld.equals(fkiResultadoDatoHidrobiologiaNew)) {
                fkiResultadoDatoHidrobiologiaOld.getResultadoList().remove(resultado);
                fkiResultadoDatoHidrobiologiaOld = em.merge(fkiResultadoDatoHidrobiologiaOld);
            }
            if (fkiResultadoDatoHidrobiologiaNew != null && !fkiResultadoDatoHidrobiologiaNew.equals(fkiResultadoDatoHidrobiologiaOld)) {
                fkiResultadoDatoHidrobiologiaNew.getResultadoList().add(resultado);
                fkiResultadoDatoHidrobiologiaNew = em.merge(fkiResultadoDatoHidrobiologiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultado.getPiResultadoId();
                if (findResultado(id) == null) {
                    throw new NonexistentEntityException("The resultado with id " + id + " no longer exists.");
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
            Resultado resultado;
            try {
                resultado = em.getReference(Resultado.class, id);
                resultado.getPiResultadoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultado with id " + id + " no longer exists.", enfe);
            }
            SUsuario fsResultadoUsuacreacion = resultado.getFsResultadoUsuacreacion();
            if (fsResultadoUsuacreacion != null) {
                fsResultadoUsuacreacion.getResultadoList().remove(resultado);
                fsResultadoUsuacreacion = em.merge(fsResultadoUsuacreacion);
            }
            SUsuario fsResultadoUsuamodificacion = resultado.getFsResultadoUsuamodificacion();
            if (fsResultadoUsuamodificacion != null) {
                fsResultadoUsuamodificacion.getResultadoList().remove(resultado);
                fsResultadoUsuamodificacion = em.merge(fsResultadoUsuamodificacion);
            }
            Valor fiResultadoValor = resultado.getFiResultadoValor();
            /*if (fiResultadoValor != null) {
                fiResultadoValor.getResultadoList().remove(resultado);
                fiResultadoValor = em.merge(fiResultadoValor);
            }
            VariableCalculo fiResultadoVariableCalculo = resultado.getFiResultadoVariableCalculo();
            if (fiResultadoVariableCalculo != null) {
                fiResultadoVariableCalculo.getResultadoList().remove(resultado);
                fiResultadoVariableCalculo = em.merge(fiResultadoVariableCalculo);
            }
            DatoHidrobiologia fkiResultadoDatoHidrobiologia = resultado.getFkiResultadoDatoHidrobiologia();
            if (fkiResultadoDatoHidrobiologia != null) {
                fkiResultadoDatoHidrobiologia.getResultadoList().remove(resultado);
                fkiResultadoDatoHidrobiologia = em.merge(fkiResultadoDatoHidrobiologia);
            }
            em.remove(resultado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Resultado> findResultadoEntities() {
        return findResultadoEntities(true, -1, -1);
    }

    public List<Resultado> findResultadoEntities(int maxResults, int firstResult) {
        return findResultadoEntities(false, maxResults, firstResult);
    }

    private List<Resultado> findResultadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resultado.class));
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

    public Resultado findResultado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultado.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resultado> rt = cq.from(Resultado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Resultado> encontrarResultadoPorIdValor(Integer idValor) {
       EntityManager em = getEntityManager();
        String consulta = "select * from resultado "
                + " where fi_resultado_valor= ?";
        try {
            Query q = em.createNativeQuery(consulta,Resultado.class);
            q.setParameter(1, idValor);           
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    public void eliminarResultado(Integer idResultado) {
        EntityManager em = getEntityManager();
        String insert = "DELETE FROM resultado WHERE pi_resultado_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idResultado);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void dejarNuloLosCampos(Integer piResultadoId, String usuarioCambio) {
          EntityManager em = getEntityManager();
         String insert = "update resultado "
                + "set s_resultado_resultado=null, fs_resultado_usuamodificacion = ?, d_resultado_fechamodificacion= now() WHERE "
                + "pi_resultado_id=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, usuarioCambio);
        insercion.setParameter(2, piResultadoId);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void editarResultado(String resultado, int idVariableCalculo, 
                               int idMuestra, String usuarioModificacion, int variableAnalisis) {
        System.out.println("resultado " + resultado);
         System.out.println("idVariableCalculo " + idVariableCalculo);
          System.out.println("usuarioModificacion " + usuarioModificacion);
         System.out.println("variableAnalisis " + variableAnalisis);
         EntityManager em = getEntityManager();
         StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_actualizar_resultado");
         em.getTransaction().begin();  
         procedimientoAlmacenado.registerStoredProcedureParameter("resultado", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_modificacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("id_muestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_calculo", int.class, ParameterMode.IN);
      //  procedimientoAlmacenado.registerStoredProcedureParameter("variable_analisis", int.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("resultado",resultado);
        procedimientoAlmacenado.setParameter("usuario_modificacion", usuarioModificacion);
        procedimientoAlmacenado.setParameter("id_muestra", idMuestra);
       procedimientoAlmacenado.setParameter("variable_calculo", idVariableCalculo);
       // procedimientoAlmacenado.setParameter("variable_analisis", variableAnalisis);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
         em.close(); 
    }
    
    
    
    public int crearResultado(int idValor, int idVariableCalculo, String usuarioCodigo, String resultado) {
        System.out.println("creo el resultado "+ idValor);
        EntityManager em = getEntityManager();
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_crear_resultado");
        em.getTransaction().begin();
        procedimientoAlmacenado.registerStoredProcedureParameter("idValor", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("idVariableCalculo", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuarioCodigo", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("resultado", String.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("idValor", idValor);
        procedimientoAlmacenado.setParameter("idVariableCalculo", idVariableCalculo);
        procedimientoAlmacenado.setParameter("usuarioCodigo", usuarioCodigo);
        procedimientoAlmacenado.setParameter("resultado", resultado);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
        em.close();
        int idResultado = encontrarUltimoResultado();
        return idResultado;
    }
    
    public void actualizarResultadoTemporal(String resultado, int idVariableCalculo, 
                               int idMuestra, String usuarioModificacion, int variableAnalisis) {
         EntityManager em = getEntityManager();
         StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_actualizar_resultado_temporal");
         em.getTransaction().begin();  
         procedimientoAlmacenado.registerStoredProcedureParameter("resultado", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_modificacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("id_muestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_calculo", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_analisis", int.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("resultado",resultado);
        procedimientoAlmacenado.setParameter("usuario_modificacion", usuarioModificacion);
        procedimientoAlmacenado.setParameter("id_muestra", idMuestra);
        procedimientoAlmacenado.setParameter("variable_calculo", idVariableCalculo);
        procedimientoAlmacenado.setParameter("variable_analisis", variableAnalisis);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
         em.close(); 
    }
    
     
    public List<Integer> crearResultadoHidrobiologia(int idValor, int idVariableCalculo, String usuarioCodigo, List<String> listaResultado, List<Integer> listaHidrobiologia) {
        EntityManager em = getEntityManager();
       List<Integer> listaIdResultados= new ArrayList<Integer>();
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_insertar_resultado_hidro");
        em.getTransaction().begin();
        
        for (int i = 0; i < listaResultado.size(); i++) {
            System.out.println("lista hidrobiologa "+ listaHidrobiologia.get(i));
            System.out.println("valor "+idValor);
            System.out.println("id variable calculo "+idVariableCalculo);
            System.out.println("resultado "+listaResultado.get(i));
            System.out.println("USUARIO CODIGO"+usuarioCodigo);
            System.out.println("resultado hidrobiologa"+listaHidrobiologia.get(i));
            
            procedimientoAlmacenado.registerStoredProcedureParameter("fi_resultado_valor", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("fi_resultado_variable_calculo", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("s_resultado_resultado", String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("fs_resultado_usuacreacion", String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("fki_resultado_dato_hidrobiologia", int.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("ultimo_id", int.class, ParameterMode.OUT);
            procedimientoAlmacenado.setParameter("fi_resultado_valor", idValor);
            procedimientoAlmacenado.setParameter("fi_resultado_variable_calculo", idVariableCalculo);
            procedimientoAlmacenado.setParameter("s_resultado_resultado", listaResultado.get(i));
            procedimientoAlmacenado.setParameter("fs_resultado_usuacreacion", usuarioCodigo);
            procedimientoAlmacenado.setParameter("fki_resultado_dato_hidrobiologia", listaHidrobiologia.get(i));
            procedimientoAlmacenado.execute();
            int ultimoId= (Integer) procedimientoAlmacenado.getOutputParameterValue("ultimo_id");
            listaIdResultados.add(ultimoId);
        }
        em.getTransaction().commit();
        em.close();
        return listaIdResultados;
        
      
    }
    
     public List<Object> encontrarIdResultado(int idValor, int idVariableCalculo) {
       EntityManager em = getEntityManager();
        String consulta = "select pi_resultado_id from resultado "
                + " where fi_resultado_valor= ? and fi_resultado_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idValor);
            q.setParameter(2, idVariableCalculo);        
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    private int encontrarUltimoResultado() {
          EntityManager em = getEntityManager();
        String consulta = "select max(pi_resultado_id) " +
                "from resultado";
        try {
            Query q = em.createNativeQuery(consulta);  
            return (int) q.getSingleResult();
        } finally {
            em.close();
        } 
     }

    public List<Object> encontrarValorResultado(Integer numeroMuestra,  int idVariableCalculo,  String nombreVariableCalculo) {
        EntityManager em = getEntityManager();
        
        String consulta = "select resultado.s_resultado_resultado "
                + "from resultado "
                + "join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                + "join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where muestra_analisis.fi_muestraanal_muestra= ? "
                + "and muestra_analisis.fi_muestraanal_variable=? "
                + "and variable_calculo.s_variable_calculo_nombre=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, numeroMuestra);
            q.setParameter(2, idVariableCalculo);
            q.setParameter(3, nombreVariableCalculo);        
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarResultadoCalidad(Integer idValor, String nombreVariableCalculo) {
      EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from resultado "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fi_resultado_valor=? "
                + "and variable_calculo.s_variable_calculo_nombre=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idValor);
            q.setParameter(2, nombreVariableCalculo);     
            return  q.getResultList();
        } finally {
            em.close();
        } 
    
    }	

    public List<String> encontrarDatosGenero(int idValor) {
     EntityManager em = getEntityManager();
        ArrayList<String> datosHidrobiologia = new ArrayList<String>();
        String consulta = " select CONCAT(s_dato_hidro_epecie_morfoespecie, \" \", IFNULL(s_dato_hidro_sufijo,'' ))"
                + " from resultado"
                + " join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia"
                + " where fi_resultado_valor=?"
                + " group by CONCAT(s_dato_hidro_epecie_morfoespecie, IFNULL(s_dato_hidro_sufijo,'' ))";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idValor);
            List<String> aux = q.getResultList();
            datosHidrobiologia.addAll(aux);
            return datosHidrobiologia;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return datosHidrobiologia;
        } finally {
            em.close();
        }
    }

    public String encontrarValorResultadoHidrobiologia(int idValor, int idVariableCalculo, int idValorHidrobiologia) {
        EntityManager em = getEntityManager();
        String resultado = null;
        String consulta = " select resultado.s_resultado_resultado "
                + "from resultado "
                + "where fi_resultado_valor=? "
                + "and fi_resultado_variable_calculo=? "
                + "and fki_resultado_dato_hidrobiologia=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idValor);
            q.setParameter(2, idVariableCalculo);
            q.setParameter(3, idValorHidrobiologia);
            resultado = (String) q.getSingleResult();
            return resultado;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return resultado;
        } finally {
            em.close();
        }
    }

    public void eliminarResultadoHidrobiologia(int idValor) {
      EntityManager em = getEntityManager();
        String insert = "DELETE FROM resultado WHERE fi_resultado_valor= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarResultadoPorVariableCalculo(int idVariableCalculo) {
      EntityManager em = getEntityManager();
        String insert = "delete "
                + "from resultado "
                + "where fki_resultado_dato_hidrobiologia=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idVariableCalculo);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }    
    
    public void eliminarResultadoPorId(int idResultado) {
      EntityManager em = getEntityManager();
        String insert = "delete from resultado "
                + "where pi_resultado_id=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idResultado);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }    
    
    public int encontrarCantidadResultadosSubidosHidrobiologia(int idValor) {
       EntityManager em = getEntityManager();
        String consulta = " select pi_resultado_id from resultado "
                + "where fi_resultado_valor=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idValor);
            return q.getResultList().size();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        } finally {
            em.close();
        }
    }

    public String crearResultadoTemporal(String dato, int idVariableCalculo, int muestra, String psUsuarioCodigo) {
       String query = "INSERT INTO resultado_temporal` "
               + "(`fi_resultado_tmp_valor`, "
               + "`fi_resultado_tmp_variable_calculo`,"
               + " `s_resultado_tmp_resultado`, "
               + "`fs_resultado_tmp_usuacreacion`, "
               + "`d_resultado_tmp_fechacreacion`) "
               + "VALUES ('2546', '12', 'ds', 'D.LINARES', '2021-01-13 13:25:00')'";
       return query;
    }

    public List<Object> encontrarResultadosDioxidoAzufre(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "punto.s_punto_nombre, "
                + "muestra.s_muestra_descripcion, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "resultado.s_resultado_resultado, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=478 "
                + "and variable_calculo.pi_variable_calculo=1021 "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
            q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    
    }
    
    
     public List<Object> encontrarResultadosHidrocarburosTotales(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                 + "muestra.fi_muestra_campo, "
                 + "punto.s_punto_nombre, "
                 + "muestra.s_muestra_descripcion, "
                 + "date(muestra.d_muestra_fechtomamuest), "
                 + "valor.s_valor_valor, "
                 + "tipo_analisis.s_tipoanal_nomenclatura "
                 + "from muestra_analisis "
                 + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                 + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                 + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                 + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                 + "where muestra.fs_muestra_reporte=? "
                 + "and muestra_analisis.fi_muestraanal_variable=481 "
                 + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    
    }
    
     public List<Object> encontrarValorioxidoAzufre(String piReporteId,  int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select valor.s_valor_valor from muestra "
                 + "JOIN reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                 + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id " 
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "where reporte.pi_reporte_id=? "
                 + "and muestra_analisis.fi_muestraanal_variable=478"
                + " group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    
    }

    public List<Object> encontrarResultadosMaterialParticuladoPM10(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
        EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "punto.s_punto_nombre, "
                + "muestra.s_muestra_descripcion, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_filtro, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal  "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=482 "
                + "and muestra.pi_muestra_id between ? and ?  ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
            q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
    public List<Object> encontrarResultadosParticulasSuspendidasTotales(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
        EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "punto.s_punto_nombre, "
                + "muestra.s_muestra_descripcion, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_filtro, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal  "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=486 "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
            q.setParameter(3, idMuestraFinal);
            
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarResultadosPesoFinalMaterialParticuladoPM10(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 482 "
                + "and variable_calculo.pi_variable_calculo=1009 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarResultadosPesoInicialMaterialParticuladoPM10(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 482 "
                + "and variable_calculo.pi_variable_calculo=1008 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
             q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
     public List<Object> encontrarResultadosMasaMaterialParticuladoPM10(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 482 "
                + "and variable_calculo.pi_variable_calculo=1010 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
             q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
     public List<Object> encontrarResultadosPesoFinalTsp(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 486 "
                + "and variable_calculo.pi_variable_calculo=1026 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
             q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
     
     
     public List<Object> encontrarResultadoTolueno(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 2000 "
                + "and variable_calculo.pi_variable_calculo=1064 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
     
       public List<Object> encontrarResultadoEtilbenceno(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 2000 "
                + "and variable_calculo.pi_variable_calculo=1065 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
       
           public List<Object> encontrarResultadoXileno(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 2000 "
                + "and variable_calculo.pi_variable_calculo=1066 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarResultadosPesoInicialTsp(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 486 "
                + "and variable_calculo.pi_variable_calculo=1027 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
             q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
     public List<Object> encontrarResultadoMasaTsp(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select resultado.s_resultado_resultado "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable= 486 "
                + "and variable_calculo.pi_variable_calculo=1028 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
             q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarCompuestosOrganicos(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "punto.s_punto_nombre, "
                + "muestra.s_muestra_descripcion, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_cromatograma, "
                + "valor.s_valor_valor, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal  "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=477 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
              q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
     public List<Object> encontrarFormaldheido(String piReporteId,  int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                 + "muestra.fi_muestra_campo, "
                 + "muestra.s_muestra_descripcion, "
                 + "punto.s_punto_nombre, "
                 + "date(muestra.d_muestra_fechtomamuest), "
                 + "resultado.s_resultado_resultado, "
                 + "valor.s_valor_valor, "
                 + "tipo_analisis.s_tipoanal_nomenclatura "
                 + "from muestra_analisis "
                 + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                 + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                 + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                 + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                 + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                 + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                 + "where muestra.fs_muestra_reporte=? "
                 + "and muestra_analisis.fi_muestraanal_variable=480 "
                 + "and variable_calculo.pi_variable_calculo=1031 "
                 + "group by muestra.pi_muestra_id "
                 + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
              q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarOzono(String piReporteId,  int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "muestra.s_muestra_descripcion, "
                + "punto.s_punto_nombre, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_valor, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=485 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
    public List<Object> encontrarSulfuroHidrogeno(String piReporteId,  int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "muestra.s_muestra_descripcion, "
                + "punto.s_punto_nombre, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_valor, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=489 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
    
     public List<Object> encontrarPm25(String piReporteId,  int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                + "muestra.fi_muestra_campo, "
                + "muestra.s_muestra_descripcion, "
                + "punto.s_punto_nombre, "
                + "date(muestra.d_muestra_fechtomamuest), "
                + "valor.s_valor_valor, "
                + "tipo_analisis.s_tipoanal_nomenclatura "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                + "where muestra.fs_muestra_reporte=? "
                + "and muestra_analisis.fi_muestraanal_variable=499 "
                + "group by muestra.pi_muestra_id "
                + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }
     
     public List<Object> encontrarBtex(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
      EntityManager em = getEntityManager();
        String consulta = "select muestra.pi_muestra_id, "
                 + "muestra.fi_muestra_campo, "
                 + "muestra.s_muestra_descripcion, "
                 + "punto.s_punto_nombre, "
                 + "date(muestra.d_muestra_fechtomamuest), "
                 + "resultado.s_resultado_resultado, "
                 + "tipo_analisis.s_tipoanal_nomenclatura "
                 + "from muestra_analisis "
                 + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                 + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                 + "join punto on punto.pi_punto_id= muestreo.fi_muestreo_punto "
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= muestra_analisis.fi_muestraanal_variable "
                 + "join tipo_analisis on tipo_analisis.pi_tipoanal_id = proveedor_variable_analisis.fi_provarana_tipoanal "
                 + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                 + "join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                 + "where muestra.fs_muestra_reporte=? "
                 + "and muestra_analisis.fi_muestraanal_variable=2000 "
                 + "and variable_calculo.pi_variable_calculo=1063 "
                 + "group by muestra.pi_muestra_id "
                 + "and muestra.pi_muestra_id between ? and ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            q.setParameter(2, idMuestraInicial);
             q.setParameter(3, idMuestraFinal);
            return  q.getResultList();
        } finally {
            em.close();
        } 
    }


    
      public List<Object> encontrarResultadosEspeciesHidro(Integer idMuestra, int piVarianalId) {
     EntityManager em = getEntityManager();
           String consulta = "select muestra_analisis.fi_muestraanal_muestra, variable_analisis.s_varianal_descripcion ,"
                             +" dato_hidrobiologia.s_dato_hidro_epecie_morfoespecie, dato_hidrobiologia.s_dato_hidro_sufijo, "
                             + "reporte.pi_reporte_id, resultado.d_resultado_fechacreacion, "
                             + "resultado.fs_resultado_usuacreacion "
                             + "from muestra_analisis "
                             + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                             + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                             + "join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia "
                             + "join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable "
                             + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                             + "join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                             + "where fi_muestraanal_muestra=? "
                             + "and fi_muestraanal_variable=? "
                             + "group by s_dato_hidro_epecie_morfoespecie ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, idMuestra);
               q.setParameter(2, piVarianalId);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }

    public String encontrarValorResultadoEspecie(Integer idMuestra, int idVariableAnalisis, String columnName, String especie, String sufijo) {
         System.out.println("muestra "+idMuestra );
        System.out.println("idVariableAnalisis "+idVariableAnalisis );
         System.out.println("columnName "+columnName );
        System.out.println("especie "+especie );
        System.out.println("sufijo "+sufijo );
        
        EntityManager em = getEntityManager();
        
        if(sufijo !=null){
            String consulta =" select resultado.s_resultado_resultado"
                  + " from resultado "
                  + " join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                  + " join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                  + " join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                  + " join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia"
                  + " where muestra_analisis.fi_muestraanal_muestra= ?"
                  + " and muestra_analisis.fi_muestraanal_variable=? "
                  + " and variable_calculo.s_variable_calculo_nombre=? "
                  + " and s_dato_hidro_epecie_morfoespecie=?"
                  + " and s_dato_hidro_sufijo=?"
                  + " and dato_hidrobiologia.c_dato_hidro_estado='V'";
          System.out.println( consulta);
        try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, idMuestra);
               q.setParameter(2, idVariableAnalisis);
               q.setParameter(3, columnName);
               q.setParameter(4, especie);
               q.setParameter(5, sufijo);
               
               return (String) q.getSingleResult();
           } finally { 
               em.close();
           } 
        }
        else{ 
             String consulta =" select resultado.s_resultado_resultado"
                  + " from resultado "
                  + " join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                  + " join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                  + " join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                  + " join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia"
                  + " where muestra_analisis.fi_muestraanal_muestra= ?"
                  + " and muestra_analisis.fi_muestraanal_variable=? "
                  + " and variable_calculo.s_variable_calculo_nombre=? "
                  + " and s_dato_hidro_epecie_morfoespecie=?"
                  + " and dato_hidrobiologia.c_dato_hidro_estado='V'";
          System.out.println( consulta);
        try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, idMuestra);
               q.setParameter(2, idVariableAnalisis);
               q.setParameter(3, columnName);
               q.setParameter(4, especie);
               
               return (String) q.getSingleResult();
           } finally { 
               em.close();
           }  
        }
          
        
        
    }

    public List<Object> cantidadFotosReporte(String reporte, Integer idVariableAnalisis) {
    EntityManager em = getEntityManager();
          String consulta = " select distinct(s_dato_hidro_epecie_morfoespecie),s_dato_hidro_sufijo  "
                + " from resultado "
                + " join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                + " join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + " join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra"
                + " join reporte on reporte.pi_reporte_id=muestra.fs_muestra_reporte "
                + " join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + " join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia"
                + " where pi_reporte_id=? "
                + " AND muestra_analisis.fi_muestraanal_variable=? "
                + " and s_dato_hidro_epecie_morfoespecie != '-' ";  
        try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, reporte);
               q.setParameter(2, idVariableAnalisis);
               return q.getResultList();
           } finally { 
               em.close();
           } 
    
    }

    public List<Integer> encontrarResultadosPorVariableCalculo(int idVariableCalculo) {
      EntityManager em = getEntityManager();
        System.out.println("id variable calculo"+ idVariableCalculo);
          String consulta = " select pi_resultado_id "
                + "from resultado "
                + "where fki_resultado_dato_hidrobiologia=? ";
        try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, idVariableCalculo);
               return q.getResultList();
           } finally { 
               em.close();
           } 
    
    }

    public void insertarResultadoTemporal(String dato, int idVariableCalculo, Integer muestra, String psUsuarioCodigo, Integer piVarianalId) {
       EntityManager em = getEntityManager();
         StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_insertar_resultado_temporal");
         em.getTransaction().begin();  
         procedimientoAlmacenado.registerStoredProcedureParameter("resultado", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_modificacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("id_muestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_calculo", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_analisis", int.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("resultado",dato);
        procedimientoAlmacenado.setParameter("usuario_modificacion", psUsuarioCodigo);
        procedimientoAlmacenado.setParameter("id_muestra", muestra);
        procedimientoAlmacenado.setParameter("variable_calculo", idVariableCalculo);
        procedimientoAlmacenado.setParameter("variable_analisis", piVarianalId);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
         em.close(); 
    }

    public List<Integer> encontrarIdDatoHidrobiologia(int idMuestra, int idVariableAnalisis, String especieMorfoespecie, String sufijo) {
        EntityManager em = getEntityManager();
        String consulta = " select resultado.pi_resultado_id "
                + "from resultado, valor, muestra_analisis, dato_hidrobiologia "
                + "where resultado.fi_resultado_valor= valor.pi_valor_id "
                + "and muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + "and dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia "
                + "and muestra_analisis.fi_muestraanal_muestra=? "
                + "and muestra_analisis.fi_muestraanal_variable=? "
                + "and dato_hidrobiologia.s_dato_hidro_epecie_morfoespecie=? "
                + "and dato_hidrobiologia.s_dato_hidro_sufijo=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
            q.setParameter(2, idVariableAnalisis);
            q.setParameter(3, especieMorfoespecie);
            q.setParameter(4, sufijo);
            return q.getResultList();
        } finally {
            em.close();
        }

    
    }
    
     public void eliminarResultadoHidrobiologiaPorId(int idResultado) {
         //Actualizar el valor del dato en nulo
         final EntityManager em = this.getEntityManager();
         final String actualizacion = "update valor "
                 + "set s_valor_valor = null "
                 + "where pi_valor_id= (select resultado.fi_resultado_valor "
                 + "from resultado "
                 + "where pi_resultado_id=? )";
        em.getTransaction().begin();
         Query insercion = em.createNativeQuery(actualizacion);
        insercion.setParameter(1, idResultado);
        insercion.executeUpdate();
        em.getTransaction().commit();
         
         String delete = "DELETE FROM resultado WHERE pi_resultado_id= ?";
        em.getTransaction().begin();
         Query eliminacion = em.createNativeQuery(delete);
        
         eliminacion.setParameter(1, idResultado);
        eliminacion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

  
    
    
    
}
