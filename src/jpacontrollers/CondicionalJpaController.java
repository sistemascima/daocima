/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Condicional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.FormulaVariableCalculo;
import entidades.VariableAnalisis;
import entidades.VariableCalculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class CondicionalJpaController implements Serializable {

    public CondicionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Condicional condicional) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormulaVariableCalculo fiCondicionalFormVarCal = condicional.getFiCondicionalFormVarCal();
            if (fiCondicionalFormVarCal != null) {
                fiCondicionalFormVarCal = em.getReference(fiCondicionalFormVarCal.getClass(), fiCondicionalFormVarCal.getPiFormulaVariableCalculoId());
                condicional.setFiCondicionalFormVarCal(fiCondicionalFormVarCal);
            }
            VariableAnalisis fiCondicionalVariableAnalisis = condicional.getFiCondicionalVariableAnalisis();
            if (fiCondicionalVariableAnalisis != null) {
                fiCondicionalVariableAnalisis = em.getReference(fiCondicionalVariableAnalisis.getClass(), fiCondicionalVariableAnalisis.getPiVarianalId());
                condicional.setFiCondicionalVariableAnalisis(fiCondicionalVariableAnalisis);
            }
            VariableCalculo fiCondicionalVariableCalculo = condicional.getFiCondicionalVariableCalculo();
            if (fiCondicionalVariableCalculo != null) {
                fiCondicionalVariableCalculo = em.getReference(fiCondicionalVariableCalculo.getClass(), fiCondicionalVariableCalculo.getPiVariableCalculo());
                condicional.setFiCondicionalVariableCalculo(fiCondicionalVariableCalculo);
            }
            em.persist(condicional);
            if (fiCondicionalFormVarCal != null) {
                fiCondicionalFormVarCal.getCondicionalList().add(condicional);
                fiCondicionalFormVarCal = em.merge(fiCondicionalFormVarCal);
            }
            if (fiCondicionalVariableAnalisis != null) {
                fiCondicionalVariableAnalisis.getCondicionalList().add(condicional);
                fiCondicionalVariableAnalisis = em.merge(fiCondicionalVariableAnalisis);
            }
            if (fiCondicionalVariableCalculo != null) {
                fiCondicionalVariableCalculo.getCondicionalList().add(condicional);
                fiCondicionalVariableCalculo = em.merge(fiCondicionalVariableCalculo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Condicional condicional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicional persistentCondicional = em.find(Condicional.class, condicional.getPiCondicional());
            FormulaVariableCalculo fiCondicionalFormVarCalOld = persistentCondicional.getFiCondicionalFormVarCal();
            FormulaVariableCalculo fiCondicionalFormVarCalNew = condicional.getFiCondicionalFormVarCal();
            VariableAnalisis fiCondicionalVariableAnalisisOld = persistentCondicional.getFiCondicionalVariableAnalisis();
            VariableAnalisis fiCondicionalVariableAnalisisNew = condicional.getFiCondicionalVariableAnalisis();
            VariableCalculo fiCondicionalVariableCalculoOld = persistentCondicional.getFiCondicionalVariableCalculo();
            VariableCalculo fiCondicionalVariableCalculoNew = condicional.getFiCondicionalVariableCalculo();
            if (fiCondicionalFormVarCalNew != null) {
                fiCondicionalFormVarCalNew = em.getReference(fiCondicionalFormVarCalNew.getClass(), fiCondicionalFormVarCalNew.getPiFormulaVariableCalculoId());
                condicional.setFiCondicionalFormVarCal(fiCondicionalFormVarCalNew);
            }
            if (fiCondicionalVariableAnalisisNew != null) {
                fiCondicionalVariableAnalisisNew = em.getReference(fiCondicionalVariableAnalisisNew.getClass(), fiCondicionalVariableAnalisisNew.getPiVarianalId());
                condicional.setFiCondicionalVariableAnalisis(fiCondicionalVariableAnalisisNew);
            }
            if (fiCondicionalVariableCalculoNew != null) {
                fiCondicionalVariableCalculoNew = em.getReference(fiCondicionalVariableCalculoNew.getClass(), fiCondicionalVariableCalculoNew.getPiVariableCalculo());
                condicional.setFiCondicionalVariableCalculo(fiCondicionalVariableCalculoNew);
            }
            condicional = em.merge(condicional);
            if (fiCondicionalFormVarCalOld != null && !fiCondicionalFormVarCalOld.equals(fiCondicionalFormVarCalNew)) {
                fiCondicionalFormVarCalOld.getCondicionalList().remove(condicional);
                fiCondicionalFormVarCalOld = em.merge(fiCondicionalFormVarCalOld);
            }
            if (fiCondicionalFormVarCalNew != null && !fiCondicionalFormVarCalNew.equals(fiCondicionalFormVarCalOld)) {
                fiCondicionalFormVarCalNew.getCondicionalList().add(condicional);
                fiCondicionalFormVarCalNew = em.merge(fiCondicionalFormVarCalNew);
            }
            if (fiCondicionalVariableAnalisisOld != null && !fiCondicionalVariableAnalisisOld.equals(fiCondicionalVariableAnalisisNew)) {
                fiCondicionalVariableAnalisisOld.getCondicionalList().remove(condicional);
                fiCondicionalVariableAnalisisOld = em.merge(fiCondicionalVariableAnalisisOld);
            }
            if (fiCondicionalVariableAnalisisNew != null && !fiCondicionalVariableAnalisisNew.equals(fiCondicionalVariableAnalisisOld)) {
                fiCondicionalVariableAnalisisNew.getCondicionalList().add(condicional);
                fiCondicionalVariableAnalisisNew = em.merge(fiCondicionalVariableAnalisisNew);
            }
            if (fiCondicionalVariableCalculoOld != null && !fiCondicionalVariableCalculoOld.equals(fiCondicionalVariableCalculoNew)) {
                fiCondicionalVariableCalculoOld.getCondicionalList().remove(condicional);
                fiCondicionalVariableCalculoOld = em.merge(fiCondicionalVariableCalculoOld);
            }
            if (fiCondicionalVariableCalculoNew != null && !fiCondicionalVariableCalculoNew.equals(fiCondicionalVariableCalculoOld)) {
                fiCondicionalVariableCalculoNew.getCondicionalList().add(condicional);
                fiCondicionalVariableCalculoNew = em.merge(fiCondicionalVariableCalculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = condicional.getPiCondicional();
                if (findCondicional(id) == null) {
                    throw new NonexistentEntityException("The condicional with id " + id + " no longer exists.");
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
            Condicional condicional;
            try {
                condicional = em.getReference(Condicional.class, id);
                condicional.getPiCondicional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The condicional with id " + id + " no longer exists.", enfe);
            }
            FormulaVariableCalculo fiCondicionalFormVarCal = condicional.getFiCondicionalFormVarCal();
            if (fiCondicionalFormVarCal != null) {
                fiCondicionalFormVarCal.getCondicionalList().remove(condicional);
                fiCondicionalFormVarCal = em.merge(fiCondicionalFormVarCal);
            }
            VariableAnalisis fiCondicionalVariableAnalisis = condicional.getFiCondicionalVariableAnalisis();
            if (fiCondicionalVariableAnalisis != null) {
                fiCondicionalVariableAnalisis.getCondicionalList().remove(condicional);
                fiCondicionalVariableAnalisis = em.merge(fiCondicionalVariableAnalisis);
            }
            VariableCalculo fiCondicionalVariableCalculo = condicional.getFiCondicionalVariableCalculo();
            if (fiCondicionalVariableCalculo != null) {
                fiCondicionalVariableCalculo.getCondicionalList().remove(condicional);
                fiCondicionalVariableCalculo = em.merge(fiCondicionalVariableCalculo);
            }
            em.remove(condicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Condicional> findCondicionalEntities() {
        return findCondicionalEntities(true, -1, -1);
    }

    public List<Condicional> findCondicionalEntities(int maxResults, int firstResult) {
        return findCondicionalEntities(false, maxResults, firstResult);
    }

    private List<Condicional> findCondicionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Condicional.class));
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

    public Condicional findCondicional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Condicional.class, id);
        } finally {
            em.close();
        }
    }

    public int getCondicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Condicional> rt = cq.from(Condicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public void crearCondicional(Condicional condicional) {
        EntityManager em = getEntityManager();
        System.out.println("condicional"+condicional.getFiCondicionalVariableCalculoReferencia() );
        if(condicional.getFiCondicionalVariableCalculoReferencia()==null){
               String insert = "INSERT INTO condicional "
                       + "(fi_condicional_variable_analisis, "
                       + "fi_condicional_variable_calculo, "
                       + "s_condicional, s_condicional_valor, "
                       + "fi_condicional_form_var_cal)"
                       + "VALUES (?,?,?,?,?)";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, condicional.getFiCondicionalVariableAnalisis().getPiVarianalId());
             insercion.setParameter(2, condicional.getFiCondicionalVariableCalculo().getPiVariableCalculo());
             insercion.setParameter(3, condicional.getSCondicional());
             insercion.setParameter(4, condicional.getSCondicionalValor());
             insercion.setParameter(5, condicional.getFiCondicionalFormVarCal().getPiFormulaVariableCalculoId());
             insercion.executeUpdate();
             em.getTransaction().commit();    
             }
        else{
            String insert = "INSERT INTO condicional "
                       + "(fi_condicional_variable_analisis, "
                       + "fi_condicional_variable_calculo, "
                       + "s_condicional, s_condicional_valor, "
                       + "fi_condicional_form_var_cal,"
                       + "fi_condicional_variable_calculo_referencia) "
                       + "VALUES (?,?,?,?,?,?)";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, condicional.getFiCondicionalVariableAnalisis().getPiVarianalId());
             insercion.setParameter(2, condicional.getFiCondicionalVariableCalculo().getPiVariableCalculo());
             insercion.setParameter(3, condicional.getSCondicional());
             insercion.setParameter(4, condicional.getSCondicionalValor());
             insercion.setParameter(5, condicional.getFiCondicionalFormVarCal().getPiFormulaVariableCalculoId());
             insercion.setParameter(6, condicional.getFiCondicionalVariableCalculoReferencia().getPiVariableCalculo());           
             insercion.executeUpdate();
             em.getTransaction().commit();  
        
        }      
    }

    public List<Condicional> encontrarCondicionales(Integer piVarianalId) {
       EntityManager em = getEntityManager();
        String consulta = "select * from condicional where fi_condicional_variable_analisis = ? ";
        try {
            Query q = em.createNativeQuery(consulta, Condicional.class);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarListaCondicionalesPorVariableCalculoId(int idVariableCalculo){
        EntityManager em = getEntityManager();
        String consulta = "select pi_condicional from condicional "
                + "join formula_variable_calculo on formula_variable_calculo.pi_formula_variable_calculo_id= condicional.fi_condicional_form_var_cal "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= formula_variable_calculo.fi_formula_variable_calculo_variable_calculo "
                + "where variable_calculo.pi_variable_calculo=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Condicional> encontrarCondicionalPorVariableCondicion(int idCondicional){
        EntityManager em = getEntityManager();
        String consulta = "select * from condicional where pi_condicional = ? ";
        try {
            Query q = em.createNativeQuery(consulta, Condicional.class);
            q.setParameter(1, idCondicional);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
