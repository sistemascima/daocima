/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Pregunta;
import entidades.EvaluacionProveedor;
import entidades.RespEvalProv;
import entidades.RespEvalProvPK;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class RespEvalProvJpaController implements Serializable {

    public RespEvalProvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespEvalProv respEvalProv) throws PreexistingEntityException, Exception {
        if (respEvalProv.getRespEvalProvPK() == null) {
            respEvalProv.setRespEvalProvPK(new RespEvalProvPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(respEvalProv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRespEvalProv(respEvalProv.getRespEvalProvPK()) != null) {
                throw new PreexistingEntityException("RespEvalProv " + respEvalProv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespEvalProv respEvalProv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            respEvalProv = em.merge(respEvalProv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RespEvalProvPK id = respEvalProv.getRespEvalProvPK();
                if (findRespEvalProv(id) == null) {
                    throw new NonexistentEntityException("The respEvalProv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RespEvalProvPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespEvalProv respEvalProv;
            try {
                respEvalProv = em.getReference(RespEvalProv.class, id);
                respEvalProv.getRespEvalProvPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respEvalProv with id " + id + " no longer exists.", enfe);
            }
            em.remove(respEvalProv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespEvalProv> findRespEvalProvEntities() {
        return findRespEvalProvEntities(true, -1, -1);
    }

    public List<RespEvalProv> findRespEvalProvEntities(int maxResults, int firstResult) {
        return findRespEvalProvEntities(false, maxResults, firstResult);
    }

    private List<RespEvalProv> findRespEvalProvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RespEvalProv.class));
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

    public RespEvalProv findRespEvalProv(RespEvalProvPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespEvalProv.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespEvalProvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RespEvalProv> rt = cq.from(RespEvalProv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List traerCriteriosEvaluacionProveedor(String tipoEvaluacion, Integer numeroEvaluacion, String nitProveedor,
            String codigoUsuario) {
        System.out.println("tipo evaluacion"+ tipoEvaluacion);
        System.out.println("numero evaluacion"+ numeroEvaluacion);
        System.out.println("nit proveedor"+ nitProveedor);
        System.out.println("codigo usuario"+ codigoUsuario);
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_tra_res_eva_pro (?, ?, ?, ?) ; ", "pa_tra_res_eva_pro");
            pa.setParameter(1, tipoEvaluacion);
            pa.setParameter(2, numeroEvaluacion);
            pa.setParameter(3, nitProveedor);
            pa.setParameter(4, codigoUsuario);
            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean guardarRespuestaCriterio(String tipo, Integer numeroEvaluacion, Integer formatoEvaluacion,
            String aspecto, Integer orden, String nitProveedor, BigDecimal puntaje,
            String noAplica, String codigoUsuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query update = em.createNativeQuery("UPDATE resp_eval_prov " +
                    "SET db_resevapro_puntaje = ?, " +
                    "	s_resevapro_noaplica = ?, " +
                    "	fs_resevapro_usuultmod = ?, " +
                    "	d_resevapro_ultimodi = NOW() " +
                    "WHERE pfs_resevapro_tipo = ? AND " +
                    "pfi_resevapro_numeeval = ? AND " +
                    "pfs_resevapro_proveedor = ? AND " +
                    "pfi_resevapro_formeval = ? AND " +
                    "pfs_resevapro_aspecto = ? AND " +
                    "pfi_resevapro_orden = ? ; ");
            
            update.setParameter(1, puntaje);
            update.setParameter(2, noAplica);
            update.setParameter(3, codigoUsuario);
            update.setParameter(4, tipo);
            update.setParameter(5, numeroEvaluacion);
            update.setParameter(6, nitProveedor);
            update.setParameter(7, formatoEvaluacion);
            update.setParameter(8, aspecto);
            update.setParameter(9, orden);
            update.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public boolean respuestasCompletas(String tipo, Integer numeroEvaluacion, 
            String nitProveedor) throws Exception {
        EntityManager em = getEntityManager();
        int registros = 0;
        try {
            em.getTransaction().begin();
            Query validarRespuestas = em.createNativeQuery("SELECT COUNT(1) " +
                    "FROM resp_eval_prov r " +
                    "WHERE r.pfs_resevapro_tipo = ? AND " +
                    "	r.pfi_resevapro_numeeval = ? AND " +
                    "	r.pfs_resevapro_proveedor = ? AND " +
                    "	((r.s_resevapro_noaplica IS NULL OR r.s_resevapro_noaplica = '0') AND " +
                    "	r.db_resevapro_puntaje IS NULL) ; ");
            
            validarRespuestas.setParameter(1, tipo);
            validarRespuestas.setParameter(2, numeroEvaluacion);
            validarRespuestas.setParameter(3, nitProveedor);
            registros = ((Long)validarRespuestas.getSingleResult()).intValue();
            em.getTransaction().commit();
            return (registros == 0);
        } catch (Exception ex) {
            throw new Exception("Error al validar respuestas. " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List traerCriteriosEvaluacionPostventa(Integer numeroEvaluacion, String nitProveedor) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            em.getTransaction().begin();
            if(numeroEvaluacion == null){
                Query pa = em.createNativeQuery("SELECT p.pfi_pregunta_formeval,	p.pfs_pregunta_aspecto,		a.s_aspepreg_nombre, " +
                        "	p.pi_pregunta_orden,		p.s_pregunta_enunciado,		p.db_pregunta_ponderaci, " +
                        "	CAST(NULL AS decimal(8,2)),	CAST(NULL AS char(1)) " +
                        "FROM pregunta p " +
                        "INNER JOIN formato_evaluacion fe ON p.pfi_pregunta_formeval = fe.pi_formeval_consecuti " +
                        "INNER JOIN aspecto_pregunta a ON a.ps_aspepreg_codigo = p.pfs_pregunta_aspecto " +
                        "WHERE p.pfi_pregunta_formeval = fe.pi_formeval_consecuti AND " +
                        "	fe.s_formeval_estado = 'V' AND " +
                        "	fe.fs_formeval_tipo = 'PP' " +
                        "ORDER BY p.pfs_pregunta_aspecto DESC, p.pi_pregunta_orden ASC; ", "resultados");
                
                resultado = pa.getResultList();
            }else{
                Query pa = em.createNativeQuery("SELECT " +
                        "	r.pfi_resevapro_formeval,	r.pfs_resevapro_aspecto,	a.s_aspepreg_nombre, " +
                        "	r.pfi_resevapro_orden,		p.s_pregunta_enunciado,		p.db_pregunta_ponderaci, " +
                        "	r.db_resevapro_puntaje,		r.s_resevapro_noaplica " +
                        "FROM resp_eval_prov r " +
                        "INNER JOIN pregunta p ON p.pfi_pregunta_formeval = r.pfi_resevapro_formeval AND " +
                        "			p.pfs_pregunta_aspecto = r.pfs_resevapro_aspecto AND " +
                        "			p.pi_pregunta_orden = r.pfi_resevapro_orden " +
                        "INNER JOIN aspecto_pregunta a ON a.ps_aspepreg_codigo = p.pfs_pregunta_aspecto " +
                        "WHERE  r.pfs_resevapro_tipo = 'PP' AND " +
                        "	r.pfi_resevapro_numeeval = ? AND " +
                        "	r.pfs_resevapro_proveedor = ? " +
                        "ORDER BY r.pfs_resevapro_aspecto ASC, r.pfi_resevapro_orden ASC; ", "resultados");

                pa.setParameter(1, numeroEvaluacion);
                pa.setParameter(2, nitProveedor);
                resultado = pa.getResultList();
            }
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarEvaluacionProveedor(int numeroMaximoEvaluacion, char aspecto) {
         EntityManager em = getEntityManager();
        String consulta = "select pfs_resevapro_aspecto,  "
                + "(count(pfi_resevapro_orden)- count(s_resevapro_noaplica)) as diferencia, "
                + "pfs_resevapro_tipo, "
                + "date(d_resevapro_creacion) "
                + "from resp_eval_prov "
                + "where pfi_resevapro_numeeval=? "
                + "and pfs_resevapro_aspecto=? "
                + "group by pfs_resevapro_aspecto ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, numeroMaximoEvaluacion);
            q.setParameter(2, aspecto);
            return  q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Integer encontrarUltimaEvaluacionProveedor(String proveedor) {
         EntityManager em = getEntityManager();
         int cantidadConsulta= 0;
         String consultaPrimeraVez = "select * "
                + " from resp_eval_prov "
                + "where pfs_resevapro_proveedor=? ";

            Query q = em.createNativeQuery(consultaPrimeraVez);
            q.setParameter(1, proveedor);
            cantidadConsulta= q.getResultList().size();
        if(cantidadConsulta==0){
          return 0;
        }
        else{
              System.out.println("proveedor " + proveedor);
            String consulta = "select max(pfi_resevapro_numeeval) "
                    + "from resp_eval_prov "
                    + "where pfs_resevapro_proveedor=? ";
            try {
                Query q2 = em.createNativeQuery(consulta);
                q2.setParameter(1, proveedor);
                return (Integer) q2.getSingleResult();
            } finally {
                em.close();
            }
        }
         
    }
    
    
}
