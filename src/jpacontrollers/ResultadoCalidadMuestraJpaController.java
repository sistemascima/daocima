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
import entidades.ResultadoCalidadMuestra;
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
public class ResultadoCalidadMuestraJpaController implements Serializable {

    public ResultadoCalidadMuestraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResultadoCalidadMuestra resultadoCalidadMuestra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra fkiRestultadoCalidadMuestraMuestra = resultadoCalidadMuestra.getFkiRestultadoCalidadMuestraMuestra();
            if (fkiRestultadoCalidadMuestraMuestra != null) {
                fkiRestultadoCalidadMuestraMuestra = em.getReference(fkiRestultadoCalidadMuestraMuestra.getClass(), fkiRestultadoCalidadMuestraMuestra.getIdmuestra());
                resultadoCalidadMuestra.setFkiRestultadoCalidadMuestraMuestra(fkiRestultadoCalidadMuestraMuestra);
            }
            if (fkiRestultadoCalidadMuestraMuestra != null) {
                fkiRestultadoCalidadMuestraMuestra.getResultadoCalidadMuestraList().add(resultadoCalidadMuestra);
                fkiRestultadoCalidadMuestraMuestra = em.merge(fkiRestultadoCalidadMuestraMuestra);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResultadoCalidadMuestra resultadoCalidadMuestra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResultadoCalidadMuestra persistentResultadoCalidadMuestra = em.find(ResultadoCalidadMuestra.class, resultadoCalidadMuestra.getPiResultadoCalidadMuestra());
            Muestra fkiRestultadoCalidadMuestraMuestraOld = persistentResultadoCalidadMuestra.getFkiRestultadoCalidadMuestraMuestra();
            Muestra fkiRestultadoCalidadMuestraMuestraNew = resultadoCalidadMuestra.getFkiRestultadoCalidadMuestraMuestra();
           if (fkiRestultadoCalidadMuestraMuestraNew != null) {
                fkiRestultadoCalidadMuestraMuestraNew = em.getReference(fkiRestultadoCalidadMuestraMuestraNew.getClass(), fkiRestultadoCalidadMuestraMuestraNew.getIdmuestra());
                resultadoCalidadMuestra.setFkiRestultadoCalidadMuestraMuestra(fkiRestultadoCalidadMuestraMuestraNew);
            }
            
            resultadoCalidadMuestra = em.merge(resultadoCalidadMuestra);
            if (fkiRestultadoCalidadMuestraMuestraOld != null && !fkiRestultadoCalidadMuestraMuestraOld.equals(fkiRestultadoCalidadMuestraMuestraNew)) {
                fkiRestultadoCalidadMuestraMuestraOld.getResultadoCalidadMuestraList().remove(resultadoCalidadMuestra);
                fkiRestultadoCalidadMuestraMuestraOld = em.merge(fkiRestultadoCalidadMuestraMuestraOld);
            }
            if (fkiRestultadoCalidadMuestraMuestraNew != null && !fkiRestultadoCalidadMuestraMuestraNew.equals(fkiRestultadoCalidadMuestraMuestraOld)) {
                fkiRestultadoCalidadMuestraMuestraNew.getResultadoCalidadMuestraList().add(resultadoCalidadMuestra);
                fkiRestultadoCalidadMuestraMuestraNew = em.merge(fkiRestultadoCalidadMuestraMuestraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoCalidadMuestra.getPiResultadoCalidadMuestra();
                if (findResultadoCalidadMuestra(id) == null) {
                    throw new NonexistentEntityException("The resultadoCalidadMuestra with id " + id + " no longer exists.");
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
            ResultadoCalidadMuestra resultadoCalidadMuestra;
            try {
                resultadoCalidadMuestra = em.getReference(ResultadoCalidadMuestra.class, id);
                resultadoCalidadMuestra.getPiResultadoCalidadMuestra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoCalidadMuestra with id " + id + " no longer exists.", enfe);
            }
            Muestra fkiRestultadoCalidadMuestraMuestra = resultadoCalidadMuestra.getFkiRestultadoCalidadMuestraMuestra();
            if (fkiRestultadoCalidadMuestraMuestra != null) {
                fkiRestultadoCalidadMuestraMuestra.getResultadoCalidadMuestraList().remove(resultadoCalidadMuestra);
                fkiRestultadoCalidadMuestraMuestra = em.merge(fkiRestultadoCalidadMuestraMuestra);
            }
            em.remove(resultadoCalidadMuestra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResultadoCalidadMuestra> findResultadoCalidadMuestraEntities() {
        return findResultadoCalidadMuestraEntities(true, -1, -1);
    }

    public List<ResultadoCalidadMuestra> findResultadoCalidadMuestraEntities(int maxResults, int firstResult) {
        return findResultadoCalidadMuestraEntities(false, maxResults, firstResult);
    }

    private List<ResultadoCalidadMuestra> findResultadoCalidadMuestraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResultadoCalidadMuestra.class));
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

    public ResultadoCalidadMuestra findResultadoCalidadMuestra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResultadoCalidadMuestra.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoCalidadMuestraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResultadoCalidadMuestra> rt = cq.from(ResultadoCalidadMuestra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearResultadoCalidadMuestra(int idResultado, int idMuestra, int idVariable ) {
       EntityManager em = getEntityManager();
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_crear_resultado_calidad");
        em.getTransaction().begin();
        procedimientoAlmacenado.registerStoredProcedureParameter("idResultado", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("idMuestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("idVariable", int.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("idResultado", idResultado);
        procedimientoAlmacenado.setParameter("idMuestra", idMuestra);
        procedimientoAlmacenado.setParameter("idVariable", idVariable);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
        em.close();
    }

    

       
    
     public List<Object> encontrarCantidadAcumuladaMuestras(Integer piVarianalId) {
        EntityManager em = getEntityManager();
        String consulta = "select  distinct(resultado_calidad_muestra.fki_restultado_calidad_muestra_muestra)"
                + " from resultado"
                + " join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo"
                + " join resultado_calidad_muestra on resultado_calidad_muestra.fki_resultado_calidad_muestra_resultado= resultado.pi_resultado_id"
                + " where  MONTH(resultado.d_resultado_fechacreacion) = MONTH(CURRENT_DATE())"
                + " and day(resultado.d_resultado_fechacreacion) = day(current_date())"
                + " and year(resultado.d_resultado_fechacreacion)= year(current_date())"
                + " and variable_calculo.fi_variable_variable_calculo=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }
     }

    
    
    public List<Object> encontrarControlesCalidadMuestra(int muestraInicial, int muestraFinal, int idVarianal) {
        EntityManager em = getEntityManager();
        String consulta = "select distinct "
                + "date(valor.d_valor_fechregi), valor.s_valor_nombre, valor.fs_valor_usuamodi, valor.pi_valor_id "
                + "from resultado_calidad_muestra "
                + "join resultado on resultado.pi_resultado_id= resultado_calidad_muestra.fki_resultado_calidad_muestra_resultado "
                + "join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                + "where fki_restultado_calidad_muestra_muestra between ? and ? "
                + "and fki_resultado_calidad_muestra_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, muestraInicial);
            q.setParameter(2, muestraFinal);
            q.setParameter(3, idVarianal);
            return q.getResultList();
        } finally {
            em.close();
        }
     }

    public void eliminarResultadoPorEspecies(int idVariableCalculo) {
        EntityManager em = getEntityManager();
        String insert = "delete resultado_calidad_muestra "
                + "from resultado_calidad_muestra "
                + "join resultado on resultado.pi_resultado_id= resultado_calidad_muestra.fki_resultado_calidad_muestra_resultado "
                + "where resultado.fki_resultado_dato_hidrobiologia=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idVariableCalculo);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    
    
}
