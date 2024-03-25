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
import entidades.Control;
import entidades.VariableAnalisis;
import entidades.SUsuario;
import entidades.VarianalControlCantidad;
import entidades.VarianalControlCantidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class VarianalControlCantidadJpaController implements Serializable {

    public VarianalControlCantidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   /* public void create(VarianalControlCantidad varianalControlCantidad) throws PreexistingEntityException, Exception {
        if (varianalControlCantidad.getVarianalControlCantidadPK() == null) {
            varianalControlCantidad.setVarianalControlCantidadPK(new VarianalControlCantidadPK());
        }
        varianalControlCantidad.getVarianalControlCantidadPK().setPfiVarianalControlCantidadControl(varianalControlCantidad.getControl().getPiControlId());
        varianalControlCantidad.getVarianalControlCantidadPK().setPfiVarianalControlCantidadVariable(varianalControlCantidad.getVariableAnalisis().getPiVarianalId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Control control = varianalControlCantidad.getControl();
            if (control != null) {
                control = em.getReference(control.getClass(), control.getPiControlId());
                varianalControlCantidad.setControl(control);
            }
            VariableAnalisis variableAnalisis = varianalControlCantidad.getVariableAnalisis();
            if (variableAnalisis != null) {
                variableAnalisis = em.getReference(variableAnalisis.getClass(), variableAnalisis.getPiVarianalId());
                varianalControlCantidad.setVariableAnalisis(variableAnalisis);
            }
            SUsuario fsVarianalControlCantidadUsuarioCreacion = varianalControlCantidad.getFsVarianalControlCantidadUsuarioCreacion();
            if (fsVarianalControlCantidadUsuarioCreacion != null) {
                fsVarianalControlCantidadUsuarioCreacion = em.getReference(fsVarianalControlCantidadUsuarioCreacion.getClass(), fsVarianalControlCantidadUsuarioCreacion.getPsUsuarioCodigo());
                varianalControlCantidad.setFsVarianalControlCantidadUsuarioCreacion(fsVarianalControlCantidadUsuarioCreacion);
            }
            SUsuario fsVarianalControlCantidadUsuarioModificacion = varianalControlCantidad.getFsVarianalControlCantidadUsuarioModificacion();
            if (fsVarianalControlCantidadUsuarioModificacion != null) {
                fsVarianalControlCantidadUsuarioModificacion = em.getReference(fsVarianalControlCantidadUsuarioModificacion.getClass(), fsVarianalControlCantidadUsuarioModificacion.getPsUsuarioCodigo());
                varianalControlCantidad.setFsVarianalControlCantidadUsuarioModificacion(fsVarianalControlCantidadUsuarioModificacion);
            }
            em.persist(varianalControlCantidad);
            if (control != null) {
                control.getVarianalControlCantidadList().add(varianalControlCantidad);
                control = em.merge(control);
            }
            if (variableAnalisis != null) {
                variableAnalisis.getVarianalControlCantidadList().add(varianalControlCantidad);
                variableAnalisis = em.merge(variableAnalisis);
            }
            if (fsVarianalControlCantidadUsuarioCreacion != null) {
                fsVarianalControlCantidadUsuarioCreacion.getVarianalControlCantidadList().add(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioCreacion = em.merge(fsVarianalControlCantidadUsuarioCreacion);
            }
            if (fsVarianalControlCantidadUsuarioModificacion != null) {
                fsVarianalControlCantidadUsuarioModificacion.getVarianalControlCantidadList().add(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioModificacion = em.merge(fsVarianalControlCantidadUsuarioModificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVarianalControlCantidad(varianalControlCantidad.getVarianalControlCantidadPK()) != null) {
                throw new PreexistingEntityException("VarianalControlCantidad " + varianalControlCantidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VarianalControlCantidad varianalControlCantidad) throws NonexistentEntityException, Exception {
        varianalControlCantidad.getVarianalControlCantidadPK().setPfiVarianalControlCantidadControl(varianalControlCantidad.getControl().getPiControlId());
        varianalControlCantidad.getVarianalControlCantidadPK().setPfiVarianalControlCantidadVariable(varianalControlCantidad.getVariableAnalisis().getPiVarianalId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VarianalControlCantidad persistentVarianalControlCantidad = em.find(VarianalControlCantidad.class, varianalControlCantidad.getVarianalControlCantidadPK());
            Control controlOld = persistentVarianalControlCantidad.getControl();
            Control controlNew = varianalControlCantidad.getControl();
            VariableAnalisis variableAnalisisOld = persistentVarianalControlCantidad.getVariableAnalisis();
            VariableAnalisis variableAnalisisNew = varianalControlCantidad.getVariableAnalisis();
            SUsuario fsVarianalControlCantidadUsuarioCreacionOld = persistentVarianalControlCantidad.getFsVarianalControlCantidadUsuarioCreacion();
            SUsuario fsVarianalControlCantidadUsuarioCreacionNew = varianalControlCantidad.getFsVarianalControlCantidadUsuarioCreacion();
            SUsuario fsVarianalControlCantidadUsuarioModificacionOld = persistentVarianalControlCantidad.getFsVarianalControlCantidadUsuarioModificacion();
            SUsuario fsVarianalControlCantidadUsuarioModificacionNew = varianalControlCantidad.getFsVarianalControlCantidadUsuarioModificacion();
            if (controlNew != null) {
                controlNew = em.getReference(controlNew.getClass(), controlNew.getPiControlId());
                varianalControlCantidad.setControl(controlNew);
            }
            if (variableAnalisisNew != null) {
                variableAnalisisNew = em.getReference(variableAnalisisNew.getClass(), variableAnalisisNew.getPiVarianalId());
                varianalControlCantidad.setVariableAnalisis(variableAnalisisNew);
            }
            if (fsVarianalControlCantidadUsuarioCreacionNew != null) {
                fsVarianalControlCantidadUsuarioCreacionNew = em.getReference(fsVarianalControlCantidadUsuarioCreacionNew.getClass(), fsVarianalControlCantidadUsuarioCreacionNew.getPsUsuarioCodigo());
                varianalControlCantidad.setFsVarianalControlCantidadUsuarioCreacion(fsVarianalControlCantidadUsuarioCreacionNew);
            }
            if (fsVarianalControlCantidadUsuarioModificacionNew != null) {
                fsVarianalControlCantidadUsuarioModificacionNew = em.getReference(fsVarianalControlCantidadUsuarioModificacionNew.getClass(), fsVarianalControlCantidadUsuarioModificacionNew.getPsUsuarioCodigo());
                varianalControlCantidad.setFsVarianalControlCantidadUsuarioModificacion(fsVarianalControlCantidadUsuarioModificacionNew);
            }
            varianalControlCantidad = em.merge(varianalControlCantidad);
            if (controlOld != null && !controlOld.equals(controlNew)) {
                controlOld.getVarianalControlCantidadList().remove(varianalControlCantidad);
                controlOld = em.merge(controlOld);
            }
            if (controlNew != null && !controlNew.equals(controlOld)) {
                controlNew.getVarianalControlCantidadList().add(varianalControlCantidad);
                controlNew = em.merge(controlNew);
            }
            if (variableAnalisisOld != null && !variableAnalisisOld.equals(variableAnalisisNew)) {
                variableAnalisisOld.getVarianalControlCantidadList().remove(varianalControlCantidad);
                variableAnalisisOld = em.merge(variableAnalisisOld);
            }
            if (variableAnalisisNew != null && !variableAnalisisNew.equals(variableAnalisisOld)) {
                variableAnalisisNew.getVarianalControlCantidadList().add(varianalControlCantidad);
                variableAnalisisNew = em.merge(variableAnalisisNew);
            }
            if (fsVarianalControlCantidadUsuarioCreacionOld != null && !fsVarianalControlCantidadUsuarioCreacionOld.equals(fsVarianalControlCantidadUsuarioCreacionNew)) {
                fsVarianalControlCantidadUsuarioCreacionOld.getVarianalControlCantidadList().remove(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioCreacionOld = em.merge(fsVarianalControlCantidadUsuarioCreacionOld);
            }
            if (fsVarianalControlCantidadUsuarioCreacionNew != null && !fsVarianalControlCantidadUsuarioCreacionNew.equals(fsVarianalControlCantidadUsuarioCreacionOld)) {
                fsVarianalControlCantidadUsuarioCreacionNew.getVarianalControlCantidadList().add(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioCreacionNew = em.merge(fsVarianalControlCantidadUsuarioCreacionNew);
            }
            if (fsVarianalControlCantidadUsuarioModificacionOld != null && !fsVarianalControlCantidadUsuarioModificacionOld.equals(fsVarianalControlCantidadUsuarioModificacionNew)) {
                fsVarianalControlCantidadUsuarioModificacionOld.getVarianalControlCantidadList().remove(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioModificacionOld = em.merge(fsVarianalControlCantidadUsuarioModificacionOld);
            }
            if (fsVarianalControlCantidadUsuarioModificacionNew != null && !fsVarianalControlCantidadUsuarioModificacionNew.equals(fsVarianalControlCantidadUsuarioModificacionOld)) {
                fsVarianalControlCantidadUsuarioModificacionNew.getVarianalControlCantidadList().add(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioModificacionNew = em.merge(fsVarianalControlCantidadUsuarioModificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VarianalControlCantidadPK id = varianalControlCantidad.getVarianalControlCantidadPK();
                if (findVarianalControlCantidad(id) == null) {
                    throw new NonexistentEntityException("The varianalControlCantidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VarianalControlCantidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VarianalControlCantidad varianalControlCantidad;
            try {
                varianalControlCantidad = em.getReference(VarianalControlCantidad.class, id);
                varianalControlCantidad.getVarianalControlCantidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The varianalControlCantidad with id " + id + " no longer exists.", enfe);
            }
            Control control = varianalControlCantidad.getControl();
            if (control != null) {
                control.getVarianalControlCantidadList().remove(varianalControlCantidad);
                control = em.merge(control);
            }
            VariableAnalisis variableAnalisis = varianalControlCantidad.getVariableAnalisis();
            if (variableAnalisis != null) {
                variableAnalisis.getVarianalControlCantidadList().remove(varianalControlCantidad);
                variableAnalisis = em.merge(variableAnalisis);
            }
            SUsuario fsVarianalControlCantidadUsuarioCreacion = varianalControlCantidad.getFsVarianalControlCantidadUsuarioCreacion();
            if (fsVarianalControlCantidadUsuarioCreacion != null) {
                fsVarianalControlCantidadUsuarioCreacion.getVarianalControlCantidadList().remove(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioCreacion = em.merge(fsVarianalControlCantidadUsuarioCreacion);
            }
            SUsuario fsVarianalControlCantidadUsuarioModificacion = varianalControlCantidad.getFsVarianalControlCantidadUsuarioModificacion();
            if (fsVarianalControlCantidadUsuarioModificacion != null) {
                fsVarianalControlCantidadUsuarioModificacion.getVarianalControlCantidadList().remove(varianalControlCantidad);
                fsVarianalControlCantidadUsuarioModificacion = em.merge(fsVarianalControlCantidadUsuarioModificacion);
            }
            em.remove(varianalControlCantidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<VarianalControlCantidad> findVarianalControlCantidadEntities() {
        return findVarianalControlCantidadEntities(true, -1, -1);
    }

    public List<VarianalControlCantidad> findVarianalControlCantidadEntities(int maxResults, int firstResult) {
        return findVarianalControlCantidadEntities(false, maxResults, firstResult);
    }

    private List<VarianalControlCantidad> findVarianalControlCantidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VarianalControlCantidad.class));
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

    public VarianalControlCantidad findVarianalControlCantidad(VarianalControlCantidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VarianalControlCantidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getVarianalControlCantidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VarianalControlCantidad> rt = cq.from(VarianalControlCantidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
    
    
    public List<Object> cargarControlesConMuestra(Integer piVarianalId) {
      EntityManager em = getEntityManager();
      String consulta = "select control.s_control_nombre, variable_analisis.s_varianal_descripcion, date(now()), "
                + "proveedor_variable_analisis.s_provarana_metodo, proveedor_variable_analisis.s_provarana_limicuan, "
                + "proveedor_variable_analisis.unidad "
                + "from varianal_control_cantidad "
                + "join control on control.pi_control_id= varianal_control_cantidad.pfi_varianal_control_cantidad_control "
                + "join variable_analisis on variable_analisis.pi_varianal_id= varianal_control_cantidad.pfi_varianal_control_cantidad_variable "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= variable_analisis.pi_varianal_id  "
                + "where pfi_varianal_control_cantidad_variable=? "
                + "and proveedor_variable_analisis.pfs_provarana_proveedor='9002414398'";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }  
    
    }

    public List<Object> encontrarCantidadControlesMuestras(int variableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "select count(pfi_varianal_control_cantidad_variable) "
                + "from varianal_control_cantidad "
                + "join variable_analisis_control on variable_analisis_control.fi_varianal_control_variable= varianal_control_cantidad.pfi_varianal_control_cantidad_variable "
                + "where pfi_varianal_control_cantidad_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }   
    }

    public List<Object> encontrarControl(Integer piVarianalId, String nombreControl) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT  varianal_control_cantidad.c_varianal_control_cantidad_muestra "
                + "FROM varianal_control_cantidad "
                + "join control on control.pi_control_id= varianal_control_cantidad.pfi_varianal_control_cantidad_control "
                + "where pfi_varianal_control_cantidad_variable=? "
                + "and control.s_control_nombre=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            q.setParameter(2, nombreControl);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarNombreControles(Integer piVarianalId) {
       EntityManager em = getEntityManager();
        String consulta = "select control.s_control_nombre "
                + "from varianal_control_cantidad "
                + "join control on control.pi_control_id= varianal_control_cantidad.pfi_varianal_control_cantidad_control "
                + "where pfi_varianal_control_cantidad_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        } 
    }
    
    public Long encontrarCantidadControlesVariableAnalisis(int idVariableAnalisis){
         EntityManager em = getEntityManager();
        String consulta = "SELECT count(pfi_varianal_control_cantidad_control) "
                + "from varianal_control_cantidad "
                + "where pfi_varianal_control_cantidad_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        } 
    }

    public Object encontrarTieneMuestra(String controlCalidad, Integer idVariableAnalisis) {
       EntityManager em = getEntityManager();
        String consulta = "select varianal_control_cantidad.c_varianal_control_cantidad_muestra "
                + "from varianal_control_cantidad "
                + "join control on control.pi_control_id= varianal_control_cantidad.pfi_varianal_control_cantidad_control "
                + "where varianal_control_cantidad.pfi_varianal_control_cantidad_variable=? "
                + "and control.s_control_nombre=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, controlCalidad);
            return (Object) q.getSingleResult();
        } finally {
            em.close();
        } 
    }

   
    
}
