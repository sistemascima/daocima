/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.FormatoEvaluacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.TipoFormatoEvaluacion;
import entidades.Pregunta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class FormatoEvaluacionJpaController implements Serializable {

    public FormatoEvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormatoEvaluacion formatoEvaluacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formatoEvaluacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormatoEvaluacion(formatoEvaluacion.getPiFormevalConsecuti()) != null) {
                throw new PreexistingEntityException("FormatoEvaluacion " + formatoEvaluacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormatoEvaluacion formatoEvaluacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formatoEvaluacion = em.merge(formatoEvaluacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formatoEvaluacion.getPiFormevalConsecuti();
                if (findFormatoEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The formatoEvaluacion with id " + id + " no longer exists.");
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
            FormatoEvaluacion formatoEvaluacion;
            try {
                formatoEvaluacion = em.getReference(FormatoEvaluacion.class, id);
                formatoEvaluacion.getPiFormevalConsecuti();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formatoEvaluacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(formatoEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormatoEvaluacion> findFormatoEvaluacionEntities() {
        return findFormatoEvaluacionEntities(true, -1, -1);
    }

    public List<FormatoEvaluacion> findFormatoEvaluacionEntities(int maxResults, int firstResult) {
        return findFormatoEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<FormatoEvaluacion> findFormatoEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormatoEvaluacion.class));
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

    public FormatoEvaluacion findFormatoEvaluacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormatoEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormatoEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormatoEvaluacion> rt = cq.from(FormatoEvaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public FormatoEvaluacion getFormatoVigentePorTipo(String tipo) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT f.* " +
                "FROM formato_evaluacion f " +
                "WHERE f.fs_formeval_tipo = ? AND " +
                "	f.s_formeval_estado = 'V' " +
                "LIMIT 1 ; ";
        try {
            Query q = em.createNativeQuery(consulta, FormatoEvaluacion.class);
            q.setParameter(1, tipo);
            return (FormatoEvaluacion)q.getSingleResult();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    public FormatoEvaluacion getFormatoSeleccionSolicitudCompra(Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT f.* " +
            "FROM formato_evaluacion f " +
            "WHERE (f.fs_formeval_tipo = 'SP' AND " +
            "	f.s_formeval_estado = 'V' AND " +
            "	( " +
            "		SELECT MIN(r.pfi_resselpro_formeval) " +
            "		FROM resp_sele_prov r " +
            "		INNER JOIN sele_prov_soli_comp s ON s.pi_seprsoco_numeeval = r.pfi_resselpro_numeeval " +
            "		WHERE s.fi_seprsoco_solicomp = ? " +
            "		) IS NULL " +
            "	) OR (" +
            "		f.pi_formeval_consecuti IN ( " +
            "			SELECT MIN(r.pfi_resselpro_formeval) " +
            "			FROM resp_sele_prov r " +
            "			INNER JOIN sele_prov_soli_comp s ON s.pi_seprsoco_numeeval = r.pfi_resselpro_numeeval " +
            "			WHERE s.fi_seprsoco_solicomp = ? " +
            "		) " +
            "	) " +
            "LIMIT 1 ; ";
        try {
            Query q = em.createNativeQuery(consulta, FormatoEvaluacion.class);
            q.setParameter(1, solicitudCompra);
            q.setParameter(2, solicitudCompra);
            return (FormatoEvaluacion)q.getSingleResult();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
}
