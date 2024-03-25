/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
 
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Formula;
import entidades.FormulaVariableCalculo;
import entidades.VariableCalculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA*/
 
public class FormulaVariableCalculoJpaController implements Serializable {

    public FormulaVariableCalculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormulaVariableCalculo formulaVariableCalculo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formula fiFormulaVariableCalculoFormula = formulaVariableCalculo.getFiFormulaVariableCalculoFormula();
            if (fiFormulaVariableCalculoFormula != null) {
                fiFormulaVariableCalculoFormula = em.getReference(fiFormulaVariableCalculoFormula.getClass(), fiFormulaVariableCalculoFormula.getPiFormulaId());
                formulaVariableCalculo.setFiFormulaVariableCalculoFormula(fiFormulaVariableCalculoFormula);
            }
            VariableCalculo fiFormulaVariableCalculoVariableCalculo = formulaVariableCalculo.getFiFormulaVariableCalculoVariableCalculo();
            if (fiFormulaVariableCalculoVariableCalculo != null) {
                fiFormulaVariableCalculoVariableCalculo = em.getReference(fiFormulaVariableCalculoVariableCalculo.getClass(), fiFormulaVariableCalculoVariableCalculo.getPiVariableCalculo());
                formulaVariableCalculo.setFiFormulaVariableCalculoVariableCalculo(fiFormulaVariableCalculoVariableCalculo);
            }
            em.persist(formulaVariableCalculo);
            if (fiFormulaVariableCalculoFormula != null) {
                fiFormulaVariableCalculoFormula.getFormulaVariableCalculoList().add(formulaVariableCalculo);
                fiFormulaVariableCalculoFormula = em.merge(fiFormulaVariableCalculoFormula);
            }
           /* if (fiFormulaVariableCalculoVariableCalculo != null) {
                fiFormulaVariableCalculoVariableCalculo.getFormulaVariableCalculoList().add(formulaVariableCalculo);
                fiFormulaVariableCalculoVariableCalculo = em.merge(fiFormulaVariableCalculoVariableCalculo);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormulaVariableCalculo formulaVariableCalculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormulaVariableCalculo persistentFormulaVariableCalculo = em.find(FormulaVariableCalculo.class, formulaVariableCalculo.getPiFormulaVariableCalculoId());
            Formula fiFormulaVariableCalculoFormulaOld = persistentFormulaVariableCalculo.getFiFormulaVariableCalculoFormula();
            Formula fiFormulaVariableCalculoFormulaNew = formulaVariableCalculo.getFiFormulaVariableCalculoFormula();
            VariableCalculo fiFormulaVariableCalculoVariableCalculoOld = persistentFormulaVariableCalculo.getFiFormulaVariableCalculoVariableCalculo();
            VariableCalculo fiFormulaVariableCalculoVariableCalculoNew = formulaVariableCalculo.getFiFormulaVariableCalculoVariableCalculo();
            if (fiFormulaVariableCalculoFormulaNew != null) {
                fiFormulaVariableCalculoFormulaNew = em.getReference(fiFormulaVariableCalculoFormulaNew.getClass(), fiFormulaVariableCalculoFormulaNew.getPiFormulaId());
                formulaVariableCalculo.setFiFormulaVariableCalculoFormula(fiFormulaVariableCalculoFormulaNew);
            }
            if (fiFormulaVariableCalculoVariableCalculoNew != null) {
                fiFormulaVariableCalculoVariableCalculoNew = em.getReference(fiFormulaVariableCalculoVariableCalculoNew.getClass(), fiFormulaVariableCalculoVariableCalculoNew.getPiVariableCalculo());
                formulaVariableCalculo.setFiFormulaVariableCalculoVariableCalculo(fiFormulaVariableCalculoVariableCalculoNew);
            }
            formulaVariableCalculo = em.merge(formulaVariableCalculo);
            if (fiFormulaVariableCalculoFormulaOld != null && !fiFormulaVariableCalculoFormulaOld.equals(fiFormulaVariableCalculoFormulaNew)) {
                fiFormulaVariableCalculoFormulaOld.getFormulaVariableCalculoList().remove(formulaVariableCalculo);
                fiFormulaVariableCalculoFormulaOld = em.merge(fiFormulaVariableCalculoFormulaOld);
            }
            if (fiFormulaVariableCalculoFormulaNew != null && !fiFormulaVariableCalculoFormulaNew.equals(fiFormulaVariableCalculoFormulaOld)) {
                fiFormulaVariableCalculoFormulaNew.getFormulaVariableCalculoList().add(formulaVariableCalculo);
                fiFormulaVariableCalculoFormulaNew = em.merge(fiFormulaVariableCalculoFormulaNew);
            }
           /* if (fiFormulaVariableCalculoVariableCalculoOld != null && !fiFormulaVariableCalculoVariableCalculoOld.equals(fiFormulaVariableCalculoVariableCalculoNew)) {
                fiFormulaVariableCalculoVariableCalculoOld.getFormulaVariableCalculoList().remove(formulaVariableCalculo);
                fiFormulaVariableCalculoVariableCalculoOld = em.merge(fiFormulaVariableCalculoVariableCalculoOld);
            }
            if (fiFormulaVariableCalculoVariableCalculoNew != null && !fiFormulaVariableCalculoVariableCalculoNew.equals(fiFormulaVariableCalculoVariableCalculoOld)) {
                fiFormulaVariableCalculoVariableCalculoNew.getFormulaVariableCalculoList().add(formulaVariableCalculo);
                fiFormulaVariableCalculoVariableCalculoNew = em.merge(fiFormulaVariableCalculoVariableCalculoNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formulaVariableCalculo.getPiFormulaVariableCalculoId();
                if (findFormulaVariableCalculo(id) == null) {
                    throw new NonexistentEntityException("The formulaVariableCalculo with id " + id + " no longer exists.");
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
            FormulaVariableCalculo formulaVariableCalculo;
            try {
                formulaVariableCalculo = em.getReference(FormulaVariableCalculo.class, id);
                formulaVariableCalculo.getPiFormulaVariableCalculoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formulaVariableCalculo with id " + id + " no longer exists.", enfe);
            }
            Formula fiFormulaVariableCalculoFormula = formulaVariableCalculo.getFiFormulaVariableCalculoFormula();
            if (fiFormulaVariableCalculoFormula != null) {
                fiFormulaVariableCalculoFormula.getFormulaVariableCalculoList().remove(formulaVariableCalculo);
                fiFormulaVariableCalculoFormula = em.merge(fiFormulaVariableCalculoFormula);
            }
            VariableCalculo fiFormulaVariableCalculoVariableCalculo = formulaVariableCalculo.getFiFormulaVariableCalculoVariableCalculo();
          /*  if (fiFormulaVariableCalculoVariableCalculo != null) {
                fiFormulaVariableCalculoVariableCalculo.getFormulaVariableCalculoList().remove(formulaVariableCalculo);
                fiFormulaVariableCalculoVariableCalculo = em.merge(fiFormulaVariableCalculoVariableCalculo);
            }*/
            em.remove(formulaVariableCalculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormulaVariableCalculo> findFormulaVariableCalculoEntities() {
        return findFormulaVariableCalculoEntities(true, -1, -1);
    }

    public List<FormulaVariableCalculo> findFormulaVariableCalculoEntities(int maxResults, int firstResult) {
        return findFormulaVariableCalculoEntities(false, maxResults, firstResult);
    }

    private List<FormulaVariableCalculo> findFormulaVariableCalculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormulaVariableCalculo.class));
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

    public FormulaVariableCalculo findFormulaVariableCalculo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormulaVariableCalculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulaVariableCalculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormulaVariableCalculo> rt = cq.from(FormulaVariableCalculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearFormulaVariableCalculo(FormulaVariableCalculo formulaVariableCalculo) {
      EntityManager em = getEntityManager();
        try {     
               String insert = "INSERT INTO formula_variable_calculo"
                     + "(fi_formula_variable_calculo_formula, "
                     + "fi_formula_variable_calculo_variable_calculo, "
                     + "fs_formula_variable_calculo_usuacreacion, "
                    +  "d_formula_variable_calculo_fechacreacion"
                    + ") "
                     + "VALUES (?,?,?,now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, formulaVariableCalculo.getFiFormulaVariableCalculoFormula().getPiFormulaId());
             insercion.setParameter(2, formulaVariableCalculo.getFiFormulaVariableCalculoVariableCalculo().getPiVariableCalculo());
             insercion.setParameter(3, formulaVariableCalculo.getFsFormulaVariableCalculoUsuacreacion().getPsUsuarioCodigo());             
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public List<Object> encontrarFormulaVariableCalculo(Integer piVarianalId) {
         EntityManager em = getEntityManager();
        String consulta = "select pi_formula_variable_calculo_id " +
                "from formula_variable_calculo " +
                "join formula on formula.pi_formula_id= formula_variable_calculo.fi_formula_variable_calculo_formula " +
                "join variable_calculo on variable_calculo.pi_variable_calculo = formula_variable_calculo.fi_formula_variable_calculo_variable_calculo " +
                "join variable_analisis on variable_analisis.pi_varianal_id= variable_calculo.fi_variable_variable_calculo " +
                "where variable_analisis.pi_varianal_id = ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<FormulaVariableCalculo> encontrarFormulaVariableCalculoPorIdFormulaVariableCalculo(int idFormulaVariableCalculo) {
        System.out.println("ESTE ES EL ID DE LA VARIABLE CALCULO" +idFormulaVariableCalculo );
     EntityManager em = getEntityManager();
        String consulta = "select * " +
                "from formula_variable_calculo " +
                "where fi_formula_variable_calculo_variable_calculo = ?";
        try {
            Query q = em.createNativeQuery(consulta, FormulaVariableCalculo.class);
            q.setParameter(1, idFormulaVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<FormulaVariableCalculo> encontrarFormulaVariableCalculoPorId(int idFormulaVariableCalculo) {
     EntityManager em = getEntityManager();
        String consulta = "select * " +
                "from formula_variable_calculo " +
                "where pi_formula_variable_calculo_id = ?";
        try {
            Query q = em.createNativeQuery(consulta, FormulaVariableCalculo.class);
            q.setParameter(1, idFormulaVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarIdFormulaVariableCalculo(Integer piVarianalId) {
        EntityManager em = getEntityManager();
        String consulta = " select pi_formula_variable_calculo_id "
                + "from formula_variable_calculo "
                + "join variable_calculo on variable_calculo.pi_variable_calculo= formula_variable_calculo.fi_formula_variable_calculo_variable_calculo "
                + "where variable_calculo.fi_variable_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
