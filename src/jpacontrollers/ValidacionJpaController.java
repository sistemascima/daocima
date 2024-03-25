/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Validacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.VariableAnalisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ValidacionJpaController implements Serializable {

    public ValidacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Validacion validacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisis fkValidacionVariable1 = validacion.getFkValidacionVariable1();
            if (fkValidacionVariable1 != null) {
                fkValidacionVariable1 = em.getReference(fkValidacionVariable1.getClass(), fkValidacionVariable1.getPiVarianalId());
                validacion.setFkValidacionVariable1(fkValidacionVariable1);
            }
            VariableAnalisis fkValidacionVariable2 = validacion.getFkValidacionVariable2();
            if (fkValidacionVariable2 != null) {
                fkValidacionVariable2 = em.getReference(fkValidacionVariable2.getClass(), fkValidacionVariable2.getPiVarianalId());
                validacion.setFkValidacionVariable2(fkValidacionVariable2);
            }
            em.persist(validacion);
            if (fkValidacionVariable1 != null) {
                fkValidacionVariable1.getValidacionList().add(validacion);
                fkValidacionVariable1 = em.merge(fkValidacionVariable1);
            }
            if (fkValidacionVariable2 != null) {
                fkValidacionVariable2.getValidacionList().add(validacion);
                fkValidacionVariable2 = em.merge(fkValidacionVariable2);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findValidacion(validacion.getPiValidacionId()) != null) {
                throw new PreexistingEntityException("Validacion " + validacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Validacion validacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Validacion persistentValidacion = em.find(Validacion.class, validacion.getPiValidacionId());
            VariableAnalisis fkValidacionVariable1Old = persistentValidacion.getFkValidacionVariable1();
            VariableAnalisis fkValidacionVariable1New = validacion.getFkValidacionVariable1();
            VariableAnalisis fkValidacionVariable2Old = persistentValidacion.getFkValidacionVariable2();
            VariableAnalisis fkValidacionVariable2New = validacion.getFkValidacionVariable2();
            if (fkValidacionVariable1New != null) {
                fkValidacionVariable1New = em.getReference(fkValidacionVariable1New.getClass(), fkValidacionVariable1New.getPiVarianalId());
                validacion.setFkValidacionVariable1(fkValidacionVariable1New);
            }
            if (fkValidacionVariable2New != null) {
                fkValidacionVariable2New = em.getReference(fkValidacionVariable2New.getClass(), fkValidacionVariable2New.getPiVarianalId());
                validacion.setFkValidacionVariable2(fkValidacionVariable2New);
            }
            validacion = em.merge(validacion);
            if (fkValidacionVariable1Old != null && !fkValidacionVariable1Old.equals(fkValidacionVariable1New)) {
                fkValidacionVariable1Old.getValidacionList().remove(validacion);
                fkValidacionVariable1Old = em.merge(fkValidacionVariable1Old);
            }
            if (fkValidacionVariable1New != null && !fkValidacionVariable1New.equals(fkValidacionVariable1Old)) {
                fkValidacionVariable1New.getValidacionList().add(validacion);
                fkValidacionVariable1New = em.merge(fkValidacionVariable1New);
            }
            if (fkValidacionVariable2Old != null && !fkValidacionVariable2Old.equals(fkValidacionVariable2New)) {
                fkValidacionVariable2Old.getValidacionList().remove(validacion);
                fkValidacionVariable2Old = em.merge(fkValidacionVariable2Old);
            }
            if (fkValidacionVariable2New != null && !fkValidacionVariable2New.equals(fkValidacionVariable2Old)) {
                fkValidacionVariable2New.getValidacionList().add(validacion);
                fkValidacionVariable2New = em.merge(fkValidacionVariable2New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = validacion.getPiValidacionId();
                if (findValidacion(id) == null) {
                    throw new NonexistentEntityException("The validacion with id " + id + " no longer exists.");
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
            Validacion validacion;
            try {
                validacion = em.getReference(Validacion.class, id);
                validacion.getPiValidacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The validacion with id " + id + " no longer exists.", enfe);
            }
            VariableAnalisis fkValidacionVariable1 = validacion.getFkValidacionVariable1();
            if (fkValidacionVariable1 != null) {
                fkValidacionVariable1.getValidacionList().remove(validacion);
                fkValidacionVariable1 = em.merge(fkValidacionVariable1);
            }
            VariableAnalisis fkValidacionVariable2 = validacion.getFkValidacionVariable2();
            if (fkValidacionVariable2 != null) {
                fkValidacionVariable2.getValidacionList().remove(validacion);
                fkValidacionVariable2 = em.merge(fkValidacionVariable2);
            }
            em.remove(validacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Validacion> findValidacionEntities() {
        return findValidacionEntities(true, -1, -1);
    }

    public List<Validacion> findValidacionEntities(int maxResults, int firstResult) {
        return findValidacionEntities(false, maxResults, firstResult);
    }

    private List<Validacion> findValidacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Validacion.class));
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

    public Validacion findValidacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Validacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getValidacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Validacion> rt = cq.from(Validacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Validacion> encontrarValidacion(VariableAnalisis variable) {
         EntityManager em = getEntityManager();
         String consulta = "SELECT * from validacion where fk_validacion_variable1=? ";
         try {
             Query q = em.createNativeQuery(consulta, Validacion.class);
             q.setParameter(1, variable.getPiVarianalId());
             return q.getResultList();
        }
         catch (Exception e) {

        }
         finally {
            em.close();
        }
         return null;
    }
    
}
