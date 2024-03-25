/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
 
package jpacontrollers;

import entidades.Formula;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.FormulaVariableCalculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA*/
 
public class FormulaJpaController implements Serializable {

    public FormulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formula formula) {
        if (formula.getFormulaVariableCalculoList() == null) {
            formula.setFormulaVariableCalculoList(new ArrayList<FormulaVariableCalculo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsFormulaUsuacreacion = formula.getFsFormulaUsuacreacion();
            if (fsFormulaUsuacreacion != null) {
                fsFormulaUsuacreacion = em.getReference(fsFormulaUsuacreacion.getClass(), fsFormulaUsuacreacion.getPsUsuarioCodigo());
                formula.setFsFormulaUsuacreacion(fsFormulaUsuacreacion);
            }
            SUsuario fsFormulaUsuamodi = formula.getFsFormulaUsuamodi();
            if (fsFormulaUsuamodi != null) {
                fsFormulaUsuamodi = em.getReference(fsFormulaUsuamodi.getClass(), fsFormulaUsuamodi.getPsUsuarioCodigo());
                formula.setFsFormulaUsuamodi(fsFormulaUsuamodi);
            }
            List<FormulaVariableCalculo> attachedFormulaVariableCalculoList = new ArrayList<FormulaVariableCalculo>();
            for (FormulaVariableCalculo formulaVariableCalculoListFormulaVariableCalculoToAttach : formula.getFormulaVariableCalculoList()) {
                formulaVariableCalculoListFormulaVariableCalculoToAttach = em.getReference(formulaVariableCalculoListFormulaVariableCalculoToAttach.getClass(), formulaVariableCalculoListFormulaVariableCalculoToAttach.getPiFormulaVariableCalculoId());
                attachedFormulaVariableCalculoList.add(formulaVariableCalculoListFormulaVariableCalculoToAttach);
            }
            formula.setFormulaVariableCalculoList(attachedFormulaVariableCalculoList);
            em.persist(formula);
           /* if (fsFormulaUsuacreacion != null) {
                fsFormulaUsuacreacion.getFormulaCollection().add(formula);
                fsFormulaUsuacreacion = em.merge(fsFormulaUsuacreacion);
            }
            if (fsFormulaUsuamodi != null) {
                fsFormulaUsuamodi.getFormulaCollection().add(formula);
                fsFormulaUsuamodi = em.merge(fsFormulaUsuamodi);
            }
            for (FormulaVariableCalculo formulaVariableCalculoListFormulaVariableCalculo : formula.getFormulaVariableCalculoList()) {
                Formula oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListFormulaVariableCalculo = formulaVariableCalculoListFormulaVariableCalculo.getFiFormulaVariableCalculoFormula();
                formulaVariableCalculoListFormulaVariableCalculo.setFiFormulaVariableCalculoFormula(formula);
                formulaVariableCalculoListFormulaVariableCalculo = em.merge(formulaVariableCalculoListFormulaVariableCalculo);
                if (oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListFormulaVariableCalculo != null) {
                    oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListFormulaVariableCalculo.getFormulaVariableCalculoList().remove(formulaVariableCalculoListFormulaVariableCalculo);
                    oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListFormulaVariableCalculo = em.merge(oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListFormulaVariableCalculo);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formula formula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formula persistentFormula = em.find(Formula.class, formula.getPiFormulaId());
            SUsuario fsFormulaUsuacreacionOld = persistentFormula.getFsFormulaUsuacreacion();
            SUsuario fsFormulaUsuacreacionNew = formula.getFsFormulaUsuacreacion();
            SUsuario fsFormulaUsuamodiOld = persistentFormula.getFsFormulaUsuamodi();
            SUsuario fsFormulaUsuamodiNew = formula.getFsFormulaUsuamodi();
            List<FormulaVariableCalculo> formulaVariableCalculoListOld = persistentFormula.getFormulaVariableCalculoList();
            List<FormulaVariableCalculo> formulaVariableCalculoListNew = formula.getFormulaVariableCalculoList();
            if (fsFormulaUsuacreacionNew != null) {
                fsFormulaUsuacreacionNew = em.getReference(fsFormulaUsuacreacionNew.getClass(), fsFormulaUsuacreacionNew.getPsUsuarioCodigo());
                formula.setFsFormulaUsuacreacion(fsFormulaUsuacreacionNew);
            }
            if (fsFormulaUsuamodiNew != null) {
                fsFormulaUsuamodiNew = em.getReference(fsFormulaUsuamodiNew.getClass(), fsFormulaUsuamodiNew.getPsUsuarioCodigo());
                formula.setFsFormulaUsuamodi(fsFormulaUsuamodiNew);
            }
            List<FormulaVariableCalculo> attachedFormulaVariableCalculoListNew = new ArrayList<FormulaVariableCalculo>();
            for (FormulaVariableCalculo formulaVariableCalculoListNewFormulaVariableCalculoToAttach : formulaVariableCalculoListNew) {
                formulaVariableCalculoListNewFormulaVariableCalculoToAttach = em.getReference(formulaVariableCalculoListNewFormulaVariableCalculoToAttach.getClass(), formulaVariableCalculoListNewFormulaVariableCalculoToAttach.getPiFormulaVariableCalculoId());
                attachedFormulaVariableCalculoListNew.add(formulaVariableCalculoListNewFormulaVariableCalculoToAttach);
            }
            formulaVariableCalculoListNew = attachedFormulaVariableCalculoListNew;
            formula.setFormulaVariableCalculoList(formulaVariableCalculoListNew);
            formula = em.merge(formula);
           /* if (fsFormulaUsuacreacionOld != null && !fsFormulaUsuacreacionOld.equals(fsFormulaUsuacreacionNew)) {
                fsFormulaUsuacreacionOld.getFormulaCollection().remove(formula);
                fsFormulaUsuacreacionOld = em.merge(fsFormulaUsuacreacionOld);
            }
            if (fsFormulaUsuacreacionNew != null && !fsFormulaUsuacreacionNew.equals(fsFormulaUsuacreacionOld)) {
                fsFormulaUsuacreacionNew.getFormulaCollection().add(formula);
                fsFormulaUsuacreacionNew = em.merge(fsFormulaUsuacreacionNew);
            }
            if (fsFormulaUsuamodiOld != null && !fsFormulaUsuamodiOld.equals(fsFormulaUsuamodiNew)) {
                fsFormulaUsuamodiOld.getFormulaCollection().remove(formula);
                fsFormulaUsuamodiOld = em.merge(fsFormulaUsuamodiOld);
            }
            if (fsFormulaUsuamodiNew != null && !fsFormulaUsuamodiNew.equals(fsFormulaUsuamodiOld)) {
                fsFormulaUsuamodiNew.getFormulaCollection().add(formula);
                fsFormulaUsuamodiNew = em.merge(fsFormulaUsuamodiNew);
            }*/
            for (FormulaVariableCalculo formulaVariableCalculoListOldFormulaVariableCalculo : formulaVariableCalculoListOld) {
                if (!formulaVariableCalculoListNew.contains(formulaVariableCalculoListOldFormulaVariableCalculo)) {
                    formulaVariableCalculoListOldFormulaVariableCalculo.setFiFormulaVariableCalculoFormula(null);
                    formulaVariableCalculoListOldFormulaVariableCalculo = em.merge(formulaVariableCalculoListOldFormulaVariableCalculo);
                }
            }
            for (FormulaVariableCalculo formulaVariableCalculoListNewFormulaVariableCalculo : formulaVariableCalculoListNew) {
                if (!formulaVariableCalculoListOld.contains(formulaVariableCalculoListNewFormulaVariableCalculo)) {
                    Formula oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo = formulaVariableCalculoListNewFormulaVariableCalculo.getFiFormulaVariableCalculoFormula();
                    formulaVariableCalculoListNewFormulaVariableCalculo.setFiFormulaVariableCalculoFormula(formula);
                    formulaVariableCalculoListNewFormulaVariableCalculo = em.merge(formulaVariableCalculoListNewFormulaVariableCalculo);
                    if (oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo != null && !oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo.equals(formula)) {
                        oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo.getFormulaVariableCalculoList().remove(formulaVariableCalculoListNewFormulaVariableCalculo);
                        oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo = em.merge(oldFiFormulaVariableCalculoFormulaOfFormulaVariableCalculoListNewFormulaVariableCalculo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formula.getPiFormulaId();
                if (findFormula(id) == null) {
                    throw new NonexistentEntityException("The formula with id " + id + " no longer exists.");
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
            Formula formula;
            try {
                formula = em.getReference(Formula.class, id);
                formula.getPiFormulaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formula with id " + id + " no longer exists.", enfe);
            }
            SUsuario fsFormulaUsuacreacion = formula.getFsFormulaUsuacreacion();
          /*  if (fsFormulaUsuacreacion != null) {
                fsFormulaUsuacreacion.getFormulaCollection().remove(formula);
                fsFormulaUsuacreacion = em.merge(fsFormulaUsuacreacion);
            }
            SUsuario fsFormulaUsuamodi = formula.getFsFormulaUsuamodi();
            if (fsFormulaUsuamodi != null) {
                fsFormulaUsuamodi.getFormulaCollection().remove(formula);
                fsFormulaUsuamodi = em.merge(fsFormulaUsuamodi);
            }*/
            List<FormulaVariableCalculo> formulaVariableCalculoList = formula.getFormulaVariableCalculoList();
            for (FormulaVariableCalculo formulaVariableCalculoListFormulaVariableCalculo : formulaVariableCalculoList) {
                formulaVariableCalculoListFormulaVariableCalculo.setFiFormulaVariableCalculoFormula(null);
                formulaVariableCalculoListFormulaVariableCalculo = em.merge(formulaVariableCalculoListFormulaVariableCalculo);
            }
            em.remove(formula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formula> findFormulaEntities() {
        return findFormulaEntities(true, -1, -1);
    }

    public List<Formula> findFormulaEntities(int maxResults, int firstResult) {
        return findFormulaEntities(false, maxResults, firstResult);
    }

    private List<Formula> findFormulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formula.class));
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

    public Formula findFormula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formula.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formula> rt = cq.from(Formula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearFormula(Formula formula) {
      EntityManager em = getEntityManager();
        try {     
               String insert = "INSERT INTO formula (s_formula_formula, "
                     + "fs_formula_usuacreacion, "
                     + "d_formula_fechacreacion )"
                     + "VALUES (?,?,now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, formula.getSFormulaFormula());
             insercion.setParameter(2, formula.getFsFormulaUsuacreacion().getPsUsuarioCodigo());
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public List<Formula> encontrarFormula(String sFormulaFormula) {
       EntityManager em = getEntityManager();
        String consulta = "select * from formula where s_formula_formula = ? ";
        try {
            Query q = em.createNativeQuery(consulta, Formula.class);
            q.setParameter(1, sFormulaFormula);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Object encontrarListaFormula(String parametro, String matriz) {
        System.out.println("parametro"+ parametro);
        System.out.println("matriz"+ matriz);
        
           EntityManager em = getEntityManager();
        String consulta = " select formula.s_formula_formula"
                + " from variable_calculo"
                + " join formula_variable_calculo on formula_variable_calculo.fi_formula_variable_calculo_variable_calculo= variable_calculo.pi_variable_calculo"
                + " join formula on formula.pi_formula_id= formula_variable_calculo.fi_formula_variable_calculo_formula"
                + " join variable_analisis on variable_analisis.pi_varianal_id=variable_calculo.fi_variable_variable_calculo"
                + " join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz"
                + " where variable_analisis.s_varianal_descripcion=? "
                + " and matriz_analisis.s_matranal_descripcion=? "
                + " and i_variable_calculo_input=0 ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, parametro);
            q.setParameter(2, matriz);
            return q.getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
