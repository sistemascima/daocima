/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.VariableAnalisisFormato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class VariableAnalisisFormatoJpaController implements Serializable {

    public VariableAnalisisFormatoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VariableAnalisisFormato variableAnalisisFormato) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(variableAnalisisFormato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VariableAnalisisFormato variableAnalisisFormato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            variableAnalisisFormato = em.merge(variableAnalisisFormato);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = variableAnalisisFormato.getPiVarianalFormatoId();
                if (findVariableAnalisisFormato(id) == null) {
                    throw new NonexistentEntityException("The variableAnalisisFormato with id " + id + " no longer exists.");
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
            VariableAnalisisFormato variableAnalisisFormato;
            try {
                variableAnalisisFormato = em.getReference(VariableAnalisisFormato.class, id);
                variableAnalisisFormato.getPiVarianalFormatoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variableAnalisisFormato with id " + id + " no longer exists.", enfe);
            }
            em.remove(variableAnalisisFormato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VariableAnalisisFormato> findVariableAnalisisFormatoEntities() {
        return findVariableAnalisisFormatoEntities(true, -1, -1);
    }

    public List<VariableAnalisisFormato> findVariableAnalisisFormatoEntities(int maxResults, int firstResult) {
        return findVariableAnalisisFormatoEntities(false, maxResults, firstResult);
    }

    private List<VariableAnalisisFormato> findVariableAnalisisFormatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VariableAnalisisFormato.class));
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

    public VariableAnalisisFormato findVariableAnalisisFormato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VariableAnalisisFormato.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariableAnalisisFormatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VariableAnalisisFormato> rt = cq.from(VariableAnalisisFormato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public String encontrarNombreFormato(int idVariableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "select s_varianal_formato_formato "
                + "from variable_analisis_formato "
                + "where fi_varianal_formato_varianal=? "
                + "and  c_varianal_formato_version='VIGENTE' "
                + "and s_varianal_formato_tipo='PRINCIPAL'";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return (String) q.getSingleResult();
        } 
        catch(Exception e){
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public String encontrarNombreFormatoAnexo(int idVariableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "select s_varianal_formato_formato "
                + "from variable_analisis_formato "
                + "where fi_varianal_formato_varianal=? "
                + "and  c_varianal_formato_version='VIGENTE' "
                + "and s_varianal_formato_tipo='ANEXO'";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return (String) q.getSingleResult();
        } 
        catch(Exception e){
            return null;
        }
        finally {
            em.close();
        }
    }
    
}
