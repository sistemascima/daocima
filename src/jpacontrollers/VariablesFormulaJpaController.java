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
import entidades.VariableCalculo;
import entidades.VariablesFormula;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class VariablesFormulaJpaController implements Serializable {

    public VariablesFormulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VariablesFormula variablesFormula) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableCalculo fiVariableFormulaVariableEntrada = variablesFormula.getFiVariableFormulaVariableEntrada();
            if (fiVariableFormulaVariableEntrada != null) {
                fiVariableFormulaVariableEntrada = em.getReference(fiVariableFormulaVariableEntrada.getClass(), fiVariableFormulaVariableEntrada.getPiVariableCalculo());
                variablesFormula.setFiVariableFormulaVariableEntrada(fiVariableFormulaVariableEntrada);
            }
            VariableCalculo fiVariableFormulaVariableFinal = variablesFormula.getFiVariableFormulaVariableFinal();
            if (fiVariableFormulaVariableFinal != null) {
                fiVariableFormulaVariableFinal = em.getReference(fiVariableFormulaVariableFinal.getClass(), fiVariableFormulaVariableFinal.getPiVariableCalculo());
                variablesFormula.setFiVariableFormulaVariableFinal(fiVariableFormulaVariableFinal);
            }
            em.persist(variablesFormula);
            if (fiVariableFormulaVariableEntrada != null) {
                fiVariableFormulaVariableEntrada.getVariablesFormulaList().add(variablesFormula);
                fiVariableFormulaVariableEntrada = em.merge(fiVariableFormulaVariableEntrada);
            }
            if (fiVariableFormulaVariableFinal != null) {
                fiVariableFormulaVariableFinal.getVariablesFormulaList().add(variablesFormula);
                fiVariableFormulaVariableFinal = em.merge(fiVariableFormulaVariableFinal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVariablesFormula(variablesFormula.getPiVariableFormula()) != null) {
                throw new PreexistingEntityException("VariablesFormula " + variablesFormula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VariablesFormula variablesFormula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariablesFormula persistentVariablesFormula = em.find(VariablesFormula.class, variablesFormula.getPiVariableFormula());
            VariableCalculo fiVariableFormulaVariableEntradaOld = persistentVariablesFormula.getFiVariableFormulaVariableEntrada();
            VariableCalculo fiVariableFormulaVariableEntradaNew = variablesFormula.getFiVariableFormulaVariableEntrada();
            VariableCalculo fiVariableFormulaVariableFinalOld = persistentVariablesFormula.getFiVariableFormulaVariableFinal();
            VariableCalculo fiVariableFormulaVariableFinalNew = variablesFormula.getFiVariableFormulaVariableFinal();
            if (fiVariableFormulaVariableEntradaNew != null) {
                fiVariableFormulaVariableEntradaNew = em.getReference(fiVariableFormulaVariableEntradaNew.getClass(), fiVariableFormulaVariableEntradaNew.getPiVariableCalculo());
                variablesFormula.setFiVariableFormulaVariableEntrada(fiVariableFormulaVariableEntradaNew);
            }
            if (fiVariableFormulaVariableFinalNew != null) {
                fiVariableFormulaVariableFinalNew = em.getReference(fiVariableFormulaVariableFinalNew.getClass(), fiVariableFormulaVariableFinalNew.getPiVariableCalculo());
                variablesFormula.setFiVariableFormulaVariableFinal(fiVariableFormulaVariableFinalNew);
            }
            variablesFormula = em.merge(variablesFormula);
            if (fiVariableFormulaVariableEntradaOld != null && !fiVariableFormulaVariableEntradaOld.equals(fiVariableFormulaVariableEntradaNew)) {
                fiVariableFormulaVariableEntradaOld.getVariablesFormulaList().remove(variablesFormula);
                fiVariableFormulaVariableEntradaOld = em.merge(fiVariableFormulaVariableEntradaOld);
            }
            if (fiVariableFormulaVariableEntradaNew != null && !fiVariableFormulaVariableEntradaNew.equals(fiVariableFormulaVariableEntradaOld)) {
                fiVariableFormulaVariableEntradaNew.getVariablesFormulaList().add(variablesFormula);
                fiVariableFormulaVariableEntradaNew = em.merge(fiVariableFormulaVariableEntradaNew);
            }
            if (fiVariableFormulaVariableFinalOld != null && !fiVariableFormulaVariableFinalOld.equals(fiVariableFormulaVariableFinalNew)) {
                fiVariableFormulaVariableFinalOld.getVariablesFormulaList().remove(variablesFormula);
                fiVariableFormulaVariableFinalOld = em.merge(fiVariableFormulaVariableFinalOld);
            }
            if (fiVariableFormulaVariableFinalNew != null && !fiVariableFormulaVariableFinalNew.equals(fiVariableFormulaVariableFinalOld)) {
                fiVariableFormulaVariableFinalNew.getVariablesFormulaList().add(variablesFormula);
                fiVariableFormulaVariableFinalNew = em.merge(fiVariableFormulaVariableFinalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = variablesFormula.getPiVariableFormula();
                if (findVariablesFormula(id) == null) {
                    throw new NonexistentEntityException("The variablesFormula with id " + id + " no longer exists.");
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
            VariablesFormula variablesFormula;
            try {
                variablesFormula = em.getReference(VariablesFormula.class, id);
                variablesFormula.getPiVariableFormula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variablesFormula with id " + id + " no longer exists.", enfe);
            }
            VariableCalculo fiVariableFormulaVariableEntrada = variablesFormula.getFiVariableFormulaVariableEntrada();
            if (fiVariableFormulaVariableEntrada != null) {
                fiVariableFormulaVariableEntrada.getVariablesFormulaList().remove(variablesFormula);
                fiVariableFormulaVariableEntrada = em.merge(fiVariableFormulaVariableEntrada);
            }
            VariableCalculo fiVariableFormulaVariableFinal = variablesFormula.getFiVariableFormulaVariableFinal();
            if (fiVariableFormulaVariableFinal != null) {
                fiVariableFormulaVariableFinal.getVariablesFormulaList().remove(variablesFormula);
                fiVariableFormulaVariableFinal = em.merge(fiVariableFormulaVariableFinal);
            }
            em.remove(variablesFormula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VariablesFormula> findVariablesFormulaEntities() {
        return findVariablesFormulaEntities(true, -1, -1);
    }

    public List<VariablesFormula> findVariablesFormulaEntities(int maxResults, int firstResult) {
        return findVariablesFormulaEntities(false, maxResults, firstResult);
    }

    private List<VariablesFormula> findVariablesFormulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VariablesFormula.class));
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

    public VariablesFormula findVariablesFormula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VariablesFormula.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariablesFormulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VariablesFormula> rt = cq.from(VariablesFormula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearVariableFormula(VariablesFormula variableFormula) {
        EntityManager em = getEntityManager();
        try {
            String insert = "INSERT INTO variables_formula "
                    + "(fi_variable_formula_variable_final, "
                    + "fi_variable_formula_variable_entrada) "
                    + "VALUES (?, ?)";

            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, variableFormula.getFiVariableFormulaVariableFinal().getPiVariableCalculo());
            insercion.setParameter(2, variableFormula.getFiVariableFormulaVariableEntrada().getPiVariableCalculo());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarVariablesEntradaPorVariableSalida(int idVariableSalida) {
        System.out.println("variable de salida " + idVariableSalida);
        EntityManager em = getEntityManager();
        String consulta =    " select variable_calculo.s_variable_calculo_nombre"
                + " from variables_formula"
                + " join variable_calculo on variable_calculo.pi_variable_calculo= variables_formula.fi_variable_formula_variable_entrada"
                + " where fi_variable_formula_variable_final=?"
                + " order by variable_calculo.i_variable_calculo_orden";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableSalida);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
