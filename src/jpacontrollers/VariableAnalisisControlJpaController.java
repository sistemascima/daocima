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
import entidades.VariableAnalisis;
import entidades.SUsuario;
import entidades.VariableAnalisisControl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class VariableAnalisisControlJpaController implements Serializable {

    public VariableAnalisisControlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   /* public void create(VariableAnalisisControl variableAnalisisControl) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisis fiVarianalControlVariable = variableAnalisisControl.getFiVarianalControlVariable();
            if (fiVarianalControlVariable != null) {
                fiVarianalControlVariable = em.getReference(fiVarianalControlVariable.getClass(), fiVarianalControlVariable.getPiVarianalId());
                variableAnalisisControl.setFiVarianalControlVariable(fiVarianalControlVariable);
            }
            SUsuario fsVarianalControlUsuarioCreacion = variableAnalisisControl.getFsVarianalControlUsuarioCreacion();
            if (fsVarianalControlUsuarioCreacion != null) {
                fsVarianalControlUsuarioCreacion = em.getReference(fsVarianalControlUsuarioCreacion.getClass(), fsVarianalControlUsuarioCreacion.getPsUsuarioCodigo());
                variableAnalisisControl.setFsVarianalControlUsuarioCreacion(fsVarianalControlUsuarioCreacion);
            }
            SUsuario fsVarianalControlUsuarioModificacion = variableAnalisisControl.getFsVarianalControlUsuarioModificacion();
            if (fsVarianalControlUsuarioModificacion != null) {
                fsVarianalControlUsuarioModificacion = em.getReference(fsVarianalControlUsuarioModificacion.getClass(), fsVarianalControlUsuarioModificacion.getPsUsuarioCodigo());
                variableAnalisisControl.setFsVarianalControlUsuarioModificacion(fsVarianalControlUsuarioModificacion);
            }
            em.persist(variableAnalisisControl);
            if (fiVarianalControlVariable != null) {
                fiVarianalControlVariable.getVariableAnalisisControlList().add(variableAnalisisControl);
                fiVarianalControlVariable = em.merge(fiVarianalControlVariable);
            }
            if (fsVarianalControlUsuarioCreacion != null) {
                fsVarianalControlUsuarioCreacion.getVariableAnalisisControlList().add(variableAnalisisControl);
                fsVarianalControlUsuarioCreacion = em.merge(fsVarianalControlUsuarioCreacion);
            }
            if (fsVarianalControlUsuarioModificacion != null) {
                fsVarianalControlUsuarioModificacion.getVariableAnalisisControlList().add(variableAnalisisControl);
                fsVarianalControlUsuarioModificacion = em.merge(fsVarianalControlUsuarioModificacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VariableAnalisisControl variableAnalisisControl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisisControl persistentVariableAnalisisControl = em.find(VariableAnalisisControl.class, variableAnalisisControl.getPiVarianalControlId());
            VariableAnalisis fiVarianalControlVariableOld = persistentVariableAnalisisControl.getFiVarianalControlVariable();
            VariableAnalisis fiVarianalControlVariableNew = variableAnalisisControl.getFiVarianalControlVariable();
            SUsuario fsVarianalControlUsuarioCreacionOld = persistentVariableAnalisisControl.getFsVarianalControlUsuarioCreacion();
            SUsuario fsVarianalControlUsuarioCreacionNew = variableAnalisisControl.getFsVarianalControlUsuarioCreacion();
            SUsuario fsVarianalControlUsuarioModificacionOld = persistentVariableAnalisisControl.getFsVarianalControlUsuarioModificacion();
            SUsuario fsVarianalControlUsuarioModificacionNew = variableAnalisisControl.getFsVarianalControlUsuarioModificacion();
            if (fiVarianalControlVariableNew != null) {
                fiVarianalControlVariableNew = em.getReference(fiVarianalControlVariableNew.getClass(), fiVarianalControlVariableNew.getPiVarianalId());
                variableAnalisisControl.setFiVarianalControlVariable(fiVarianalControlVariableNew);
            }
            if (fsVarianalControlUsuarioCreacionNew != null) {
                fsVarianalControlUsuarioCreacionNew = em.getReference(fsVarianalControlUsuarioCreacionNew.getClass(), fsVarianalControlUsuarioCreacionNew.getPsUsuarioCodigo());
                variableAnalisisControl.setFsVarianalControlUsuarioCreacion(fsVarianalControlUsuarioCreacionNew);
            }
            if (fsVarianalControlUsuarioModificacionNew != null) {
                fsVarianalControlUsuarioModificacionNew = em.getReference(fsVarianalControlUsuarioModificacionNew.getClass(), fsVarianalControlUsuarioModificacionNew.getPsUsuarioCodigo());
                variableAnalisisControl.setFsVarianalControlUsuarioModificacion(fsVarianalControlUsuarioModificacionNew);
            }
            variableAnalisisControl = em.merge(variableAnalisisControl);
            if (fiVarianalControlVariableOld != null && !fiVarianalControlVariableOld.equals(fiVarianalControlVariableNew)) {
                fiVarianalControlVariableOld.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fiVarianalControlVariableOld = em.merge(fiVarianalControlVariableOld);
            }
            if (fiVarianalControlVariableNew != null && !fiVarianalControlVariableNew.equals(fiVarianalControlVariableOld)) {
                fiVarianalControlVariableNew.getVariableAnalisisControlList().add(variableAnalisisControl);
                fiVarianalControlVariableNew = em.merge(fiVarianalControlVariableNew);
            }
            if (fsVarianalControlUsuarioCreacionOld != null && !fsVarianalControlUsuarioCreacionOld.equals(fsVarianalControlUsuarioCreacionNew)) {
                fsVarianalControlUsuarioCreacionOld.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fsVarianalControlUsuarioCreacionOld = em.merge(fsVarianalControlUsuarioCreacionOld);
            }
            if (fsVarianalControlUsuarioCreacionNew != null && !fsVarianalControlUsuarioCreacionNew.equals(fsVarianalControlUsuarioCreacionOld)) {
                fsVarianalControlUsuarioCreacionNew.getVariableAnalisisControlList().add(variableAnalisisControl);
                fsVarianalControlUsuarioCreacionNew = em.merge(fsVarianalControlUsuarioCreacionNew);
            }
            if (fsVarianalControlUsuarioModificacionOld != null && !fsVarianalControlUsuarioModificacionOld.equals(fsVarianalControlUsuarioModificacionNew)) {
                fsVarianalControlUsuarioModificacionOld.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fsVarianalControlUsuarioModificacionOld = em.merge(fsVarianalControlUsuarioModificacionOld);
            }
            if (fsVarianalControlUsuarioModificacionNew != null && !fsVarianalControlUsuarioModificacionNew.equals(fsVarianalControlUsuarioModificacionOld)) {
                fsVarianalControlUsuarioModificacionNew.getVariableAnalisisControlList().add(variableAnalisisControl);
                fsVarianalControlUsuarioModificacionNew = em.merge(fsVarianalControlUsuarioModificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = variableAnalisisControl.getPiVarianalControlId();
                if (findVariableAnalisisControl(id) == null) {
                    throw new NonexistentEntityException("The variableAnalisisControl with id " + id + " no longer exists.");
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
            VariableAnalisisControl variableAnalisisControl;
            try {
                variableAnalisisControl = em.getReference(VariableAnalisisControl.class, id);
                variableAnalisisControl.getPiVarianalControlId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variableAnalisisControl with id " + id + " no longer exists.", enfe);
            }
            VariableAnalisis fiVarianalControlVariable = variableAnalisisControl.getFiVarianalControlVariable();
            if (fiVarianalControlVariable != null) {
                fiVarianalControlVariable.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fiVarianalControlVariable = em.merge(fiVarianalControlVariable);
            }
            SUsuario fsVarianalControlUsuarioCreacion = variableAnalisisControl.getFsVarianalControlUsuarioCreacion();
            if (fsVarianalControlUsuarioCreacion != null) {
                fsVarianalControlUsuarioCreacion.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fsVarianalControlUsuarioCreacion = em.merge(fsVarianalControlUsuarioCreacion);
            }
            SUsuario fsVarianalControlUsuarioModificacion = variableAnalisisControl.getFsVarianalControlUsuarioModificacion();
            if (fsVarianalControlUsuarioModificacion != null) {
                fsVarianalControlUsuarioModificacion.getVariableAnalisisControlList().remove(variableAnalisisControl);
                fsVarianalControlUsuarioModificacion = em.merge(fsVarianalControlUsuarioModificacion);
            }
            em.remove(variableAnalisisControl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<VariableAnalisisControl> findVariableAnalisisControlEntities() {
        return findVariableAnalisisControlEntities(true, -1, -1);
    }

    public List<VariableAnalisisControl> findVariableAnalisisControlEntities(int maxResults, int firstResult) {
        return findVariableAnalisisControlEntities(false, maxResults, firstResult);
    }

    private List<VariableAnalisisControl> findVariableAnalisisControlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VariableAnalisisControl.class));
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

    public VariableAnalisisControl findVariableAnalisisControl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VariableAnalisisControl.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariableAnalisisControlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VariableAnalisisControl> rt = cq.from(VariableAnalisisControl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Long encontrarCantidadControles(int idVariable) {
        EntityManager em = getEntityManager();
        String consulta = "select count(pi_varianal_control_id) " 
                +"from variable_analisis_control " 
                +"where fi_varianal_control_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariable);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    
    }
    
     

    public int encontrarCantidadMuestrasControl(Integer piVarianalId) {
     EntityManager em = getEntityManager();
        String consulta = " 	   select  distinct(valor.pi_valor_id)"
                + "	   from resultado "
                + "	   join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo"
                + "	   join valor on valor.pi_valor_id= resultado.fi_resultado_valor"
                + "	  where  MONTH(resultado.d_resultado_fechacreacion) = MONTH(CURRENT_DATE())"
                + "	   and day(resultado.d_resultado_fechacreacion) = day(current_date())"
                + "	   and year(resultado.d_resultado_fechacreacion)= year(current_date())"
                + "	   and variable_calculo.fi_variable_variable_calculo=?"
                + "        and valor.fi_valor_tpvalor=13 ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return  q.getResultList().size();
        } finally {
            em.close();
        }
    }
    
    

    public Object encontrarTipoControlPorVariable(Integer piVarianalId) {
     EntityManager em = getEntityManager();
        String consulta = " 	   select c_varianal_control_fecha "
                + "from variable_analisis_control "
                + "where fi_varianal_control_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return  q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
     public int encontrarCantidadControlesMuestras(Integer piVarianalId) {
     EntityManager em = getEntityManager();
        String consulta = "SELECT i_varianal_control_muestras "
                 + "from variable_analisis_control "
                 + "where fi_varianal_control_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return  (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
}
