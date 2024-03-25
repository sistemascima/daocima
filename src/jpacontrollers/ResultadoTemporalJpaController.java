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
import entidades.Muestra;
import entidades.ResultadoTemporal;
import entidades.VariableCalculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ResultadoTemporalJpaController implements Serializable {

    public ResultadoTemporalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResultadoTemporal resultadoTemporal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra fiResultadoTemporalMuestra = resultadoTemporal.getFiResultadoTemporalMuestra();
            if (fiResultadoTemporalMuestra != null) {
                fiResultadoTemporalMuestra = em.getReference(fiResultadoTemporalMuestra.getClass(), fiResultadoTemporalMuestra.getIdmuestra());
                resultadoTemporal.setFiResultadoTemporalMuestra(fiResultadoTemporalMuestra);
            }
            VariableCalculo fiResultadoTemporalVarCal = resultadoTemporal.getFiResultadoTemporalVarCal();
            if (fiResultadoTemporalVarCal != null) {
                fiResultadoTemporalVarCal = em.getReference(fiResultadoTemporalVarCal.getClass(), fiResultadoTemporalVarCal.getPiVariableCalculo());
                resultadoTemporal.setFiResultadoTemporalVarCal(fiResultadoTemporalVarCal);
            }
            em.persist(resultadoTemporal);
            /*if (fiResultadoTemporalMuestra != null) {
                fiResultadoTemporalMuestra.getResultadoTemporalList().add(resultadoTemporal);
                fiResultadoTemporalMuestra = em.merge(fiResultadoTemporalMuestra);
            }*/
            if (fiResultadoTemporalVarCal != null) {
                fiResultadoTemporalVarCal.getResultadoTemporalList().add(resultadoTemporal);
                fiResultadoTemporalVarCal = em.merge(fiResultadoTemporalVarCal);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResultadoTemporal resultadoTemporal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResultadoTemporal persistentResultadoTemporal = em.find(ResultadoTemporal.class, resultadoTemporal.getPiResultadoTemporalId());
            Muestra fiResultadoTemporalMuestraOld = persistentResultadoTemporal.getFiResultadoTemporalMuestra();
            Muestra fiResultadoTemporalMuestraNew = resultadoTemporal.getFiResultadoTemporalMuestra();
            VariableCalculo fiResultadoTemporalVarCalOld = persistentResultadoTemporal.getFiResultadoTemporalVarCal();
            VariableCalculo fiResultadoTemporalVarCalNew = resultadoTemporal.getFiResultadoTemporalVarCal();
            if (fiResultadoTemporalMuestraNew != null) {
                fiResultadoTemporalMuestraNew = em.getReference(fiResultadoTemporalMuestraNew.getClass(), fiResultadoTemporalMuestraNew.getIdmuestra());
                resultadoTemporal.setFiResultadoTemporalMuestra(fiResultadoTemporalMuestraNew);
            }
            if (fiResultadoTemporalVarCalNew != null) {
                fiResultadoTemporalVarCalNew = em.getReference(fiResultadoTemporalVarCalNew.getClass(), fiResultadoTemporalVarCalNew.getPiVariableCalculo());
                resultadoTemporal.setFiResultadoTemporalVarCal(fiResultadoTemporalVarCalNew);
            }
            resultadoTemporal = em.merge(resultadoTemporal);
           /* if (fiResultadoTemporalMuestraOld != null && !fiResultadoTemporalMuestraOld.equals(fiResultadoTemporalMuestraNew)) {
                fiResultadoTemporalMuestraOld.getResultadoTemporalList().remove(resultadoTemporal);
                fiResultadoTemporalMuestraOld = em.merge(fiResultadoTemporalMuestraOld);
            }
            if (fiResultadoTemporalMuestraNew != null && !fiResultadoTemporalMuestraNew.equals(fiResultadoTemporalMuestraOld)) {
                fiResultadoTemporalMuestraNew.getResultadoTemporalList().add(resultadoTemporal);
                fiResultadoTemporalMuestraNew = em.merge(fiResultadoTemporalMuestraNew);
            }*/
            if (fiResultadoTemporalVarCalOld != null && !fiResultadoTemporalVarCalOld.equals(fiResultadoTemporalVarCalNew)) {
                fiResultadoTemporalVarCalOld.getResultadoTemporalList().remove(resultadoTemporal);
                fiResultadoTemporalVarCalOld = em.merge(fiResultadoTemporalVarCalOld);
            }
            if (fiResultadoTemporalVarCalNew != null && !fiResultadoTemporalVarCalNew.equals(fiResultadoTemporalVarCalOld)) {
                fiResultadoTemporalVarCalNew.getResultadoTemporalList().add(resultadoTemporal);
                fiResultadoTemporalVarCalNew = em.merge(fiResultadoTemporalVarCalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoTemporal.getPiResultadoTemporalId();
                if (findResultadoTemporal(id) == null) {
                    throw new NonexistentEntityException("The resultadoTemporal with id " + id + " no longer exists.");
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
            ResultadoTemporal resultadoTemporal;
            try {
                resultadoTemporal = em.getReference(ResultadoTemporal.class, id);
                resultadoTemporal.getPiResultadoTemporalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoTemporal with id " + id + " no longer exists.", enfe);
            }
            Muestra fiResultadoTemporalMuestra = resultadoTemporal.getFiResultadoTemporalMuestra();
            /*if (fiResultadoTemporalMuestra != null) {
                fiResultadoTemporalMuestra.getResultadoTemporalList().remove(resultadoTemporal);
                fiResultadoTemporalMuestra = em.merge(fiResultadoTemporalMuestra);
            }*/
            VariableCalculo fiResultadoTemporalVarCal = resultadoTemporal.getFiResultadoTemporalVarCal();
            if (fiResultadoTemporalVarCal != null) {
                fiResultadoTemporalVarCal.getResultadoTemporalList().remove(resultadoTemporal);
                fiResultadoTemporalVarCal = em.merge(fiResultadoTemporalVarCal);
            }
            em.remove(resultadoTemporal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResultadoTemporal> findResultadoTemporalEntities() {
        return findResultadoTemporalEntities(true, -1, -1);
    }

    public List<ResultadoTemporal> findResultadoTemporalEntities(int maxResults, int firstResult) {
        return findResultadoTemporalEntities(false, maxResults, firstResult);
    }

    private List<ResultadoTemporal> findResultadoTemporalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResultadoTemporal.class));
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

    public ResultadoTemporal findResultadoTemporal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResultadoTemporal.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoTemporalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResultadoTemporal> rt = cq.from(ResultadoTemporal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

   /* public void subirDato(int idMuestra) {
      EntityManager em = getEntityManager();
         StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_actualizar_resultado");
         em.getTransaction().begin();  
         procedimientoAlmacenado.registerStoredProcedureParameter("resultado", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_modificacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("id_muestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("variable_calculo", int.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("resultado",resultado);
        procedimientoAlmacenado.setParameter("usuario_modificacion", usuarioModificacion);
        procedimientoAlmacenado.setParameter("id_muestra", idMuestra);
        procedimientoAlmacenado.setParameter("variable_calculo", idVariableCalculo);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
         em.close(); 
    }*/

    public void eliminarDatosTemporales(int idMuestra) {
        System.out.println("elimina muestra "+ idMuestra);
        EntityManager em = getEntityManager();
        String insert = "DELETE FROM resultado_temporal WHERE fi_resultado_temporal_muestra= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idMuestra);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public boolean existeResultadoPrevioGuardado(int idMuestra, int idVariableCalculo) {
       EntityManager em = this.getEntityManager();
         String consulta = "select *from resultado_temporal " +
                            "where fi_resultado_temporal_muestra=? and fi_resultado_temporal_var_cal=?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
             q.setParameter(2, idVariableCalculo);
            if(q.getResultList().isEmpty()){
                return false;
            }
            else{
                return true;
            }
        }
        finally {
            em.close();
        }
    }

    public boolean existenDatosPrevios(int idMuestra , int idVariableCalculo) {
      EntityManager em = this.getEntityManager();
         String consulta = "select *from resultado_temporal " +
                            "where fi_resultado_temporal_muestra=?"
                 + " and fi_resultado_temporal_var_cal= ?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
            q.setParameter(2, idVariableCalculo);
            if(q.getResultList().isEmpty()){
                return false;
            }
            else{
                return true;
            }
        }
        finally {
            em.close();
        }
    }

    public String encontrarResultadoTemporalGuardado(int idVariableCalculo, int idMuestra) {
      EntityManager em = this.getEntityManager();
         String consulta = "select s_resultado_temporal_valor "
                + "from resultado_temporal "
                + "where fi_resultado_temporal_muestra= ? "
                + "and fi_resultado_temporal_var_cal=?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
             q.setParameter(2, idVariableCalculo);
           return (String) q.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    public String encontrarResultadoNmp(String valorPozosPequeños, String valorPozosGrandes) {
      EntityManager em = this.getEntityManager();
         String consulta = "SELECT s_coliformes_resultado "
                + "from coliformes "
                + "where s_coliformes_dato_eje_y=? "
                + "and s_coliformes_dato_eje_x=? ";
        try {
            final Query q = em.createNativeQuery(consulta);
            System.out.println("valorPozosPequeños "+valorPozosPequeños);
            System.out.println("valorPozosGrandes "+valorPozosGrandes);
            
            q.setParameter(1, valorPozosGrandes);
             q.setParameter(2, valorPozosPequeños);
           return (String) q.getSingleResult();
        }
        finally {
            em.close();
        }
    }
    
}
