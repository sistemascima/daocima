/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Control;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.VarianalControlCantidad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ControlJpaController implements Serializable {

    public ControlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*public void create(Control control) {
        if (control.getVarianalControlCantidadList() == null) {
            control.setVarianalControlCantidadList(new ArrayList<VarianalControlCantidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsControlUsuacreacion = control.getFsControlUsuacreacion();
            if (fsControlUsuacreacion != null) {
                fsControlUsuacreacion = em.getReference(fsControlUsuacreacion.getClass(), fsControlUsuacreacion.getPsUsuarioCodigo());
                control.setFsControlUsuacreacion(fsControlUsuacreacion);
            }
            SUsuario fsControlUsuamodificacion = control.getFsControlUsuamodificacion();
            if (fsControlUsuamodificacion != null) {
                fsControlUsuamodificacion = em.getReference(fsControlUsuamodificacion.getClass(), fsControlUsuamodificacion.getPsUsuarioCodigo());
                control.setFsControlUsuamodificacion(fsControlUsuamodificacion);
            }
            List<VarianalControlCantidad> attachedVarianalControlCantidadList = new ArrayList<VarianalControlCantidad>();
            for (VarianalControlCantidad varianalControlCantidadListVarianalControlCantidadToAttach : control.getVarianalControlCantidadList()) {
                varianalControlCantidadListVarianalControlCantidadToAttach = em.getReference(varianalControlCantidadListVarianalControlCantidadToAttach.getClass(), varianalControlCantidadListVarianalControlCantidadToAttach.getVarianalControlCantidadPK());
                attachedVarianalControlCantidadList.add(varianalControlCantidadListVarianalControlCantidadToAttach);
            }
            control.setVarianalControlCantidadList(attachedVarianalControlCantidadList);
            em.persist(control);
            if (fsControlUsuacreacion != null) {
                fsControlUsuacreacion.getControlList().add(control);
                fsControlUsuacreacion = em.merge(fsControlUsuacreacion);
            }
            if (fsControlUsuamodificacion != null) {
                fsControlUsuamodificacion.getControlList().add(control);
                fsControlUsuamodificacion = em.merge(fsControlUsuamodificacion);
            }
            for (VarianalControlCantidad varianalControlCantidadListVarianalControlCantidad : control.getVarianalControlCantidadList()) {
                Control oldControlOfVarianalControlCantidadListVarianalControlCantidad = varianalControlCantidadListVarianalControlCantidad.getControl();
                varianalControlCantidadListVarianalControlCantidad.setControl(control);
                varianalControlCantidadListVarianalControlCantidad = em.merge(varianalControlCantidadListVarianalControlCantidad);
                if (oldControlOfVarianalControlCantidadListVarianalControlCantidad != null) {
                    oldControlOfVarianalControlCantidadListVarianalControlCantidad.getVarianalControlCantidadList().remove(varianalControlCantidadListVarianalControlCantidad);
                    oldControlOfVarianalControlCantidadListVarianalControlCantidad = em.merge(oldControlOfVarianalControlCantidadListVarianalControlCantidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    /*public void edit(Control control) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Control persistentControl = em.find(Control.class, control.getPiControlId());
            SUsuario fsControlUsuacreacionOld = persistentControl.getFsControlUsuacreacion();
            SUsuario fsControlUsuacreacionNew = control.getFsControlUsuacreacion();
            SUsuario fsControlUsuamodificacionOld = persistentControl.getFsControlUsuamodificacion();
            SUsuario fsControlUsuamodificacionNew = control.getFsControlUsuamodificacion();
            List<VarianalControlCantidad> varianalControlCantidadListOld = persistentControl.getVarianalControlCantidadList();
            List<VarianalControlCantidad> varianalControlCantidadListNew = control.getVarianalControlCantidadList();
            List<String> illegalOrphanMessages = null;
            for (VarianalControlCantidad varianalControlCantidadListOldVarianalControlCantidad : varianalControlCantidadListOld) {
                if (!varianalControlCantidadListNew.contains(varianalControlCantidadListOldVarianalControlCantidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VarianalControlCantidad " + varianalControlCantidadListOldVarianalControlCantidad + " since its control field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fsControlUsuacreacionNew != null) {
                fsControlUsuacreacionNew = em.getReference(fsControlUsuacreacionNew.getClass(), fsControlUsuacreacionNew.getPsUsuarioCodigo());
                control.setFsControlUsuacreacion(fsControlUsuacreacionNew);
            }
            if (fsControlUsuamodificacionNew != null) {
                fsControlUsuamodificacionNew = em.getReference(fsControlUsuamodificacionNew.getClass(), fsControlUsuamodificacionNew.getPsUsuarioCodigo());
                control.setFsControlUsuamodificacion(fsControlUsuamodificacionNew);
            }
            List<VarianalControlCantidad> attachedVarianalControlCantidadListNew = new ArrayList<VarianalControlCantidad>();
            for (VarianalControlCantidad varianalControlCantidadListNewVarianalControlCantidadToAttach : varianalControlCantidadListNew) {
                varianalControlCantidadListNewVarianalControlCantidadToAttach = em.getReference(varianalControlCantidadListNewVarianalControlCantidadToAttach.getClass(), varianalControlCantidadListNewVarianalControlCantidadToAttach.getVarianalControlCantidadPK());
                attachedVarianalControlCantidadListNew.add(varianalControlCantidadListNewVarianalControlCantidadToAttach);
            }
            varianalControlCantidadListNew = attachedVarianalControlCantidadListNew;
            control.setVarianalControlCantidadList(varianalControlCantidadListNew);
            control = em.merge(control);
            if (fsControlUsuacreacionOld != null && !fsControlUsuacreacionOld.equals(fsControlUsuacreacionNew)) {
                fsControlUsuacreacionOld.getControlList().remove(control);
                fsControlUsuacreacionOld = em.merge(fsControlUsuacreacionOld);
            }
            if (fsControlUsuacreacionNew != null && !fsControlUsuacreacionNew.equals(fsControlUsuacreacionOld)) {
                fsControlUsuacreacionNew.getControlList().add(control);
                fsControlUsuacreacionNew = em.merge(fsControlUsuacreacionNew);
            }
            if (fsControlUsuamodificacionOld != null && !fsControlUsuamodificacionOld.equals(fsControlUsuamodificacionNew)) {
                fsControlUsuamodificacionOld.getControlList().remove(control);
                fsControlUsuamodificacionOld = em.merge(fsControlUsuamodificacionOld);
            }
            if (fsControlUsuamodificacionNew != null && !fsControlUsuamodificacionNew.equals(fsControlUsuamodificacionOld)) {
                fsControlUsuamodificacionNew.getControlList().add(control);
                fsControlUsuamodificacionNew = em.merge(fsControlUsuamodificacionNew);
            }
            for (VarianalControlCantidad varianalControlCantidadListNewVarianalControlCantidad : varianalControlCantidadListNew) {
                if (!varianalControlCantidadListOld.contains(varianalControlCantidadListNewVarianalControlCantidad)) {
                    Control oldControlOfVarianalControlCantidadListNewVarianalControlCantidad = varianalControlCantidadListNewVarianalControlCantidad.getControl();
                    varianalControlCantidadListNewVarianalControlCantidad.setControl(control);
                    varianalControlCantidadListNewVarianalControlCantidad = em.merge(varianalControlCantidadListNewVarianalControlCantidad);
                    if (oldControlOfVarianalControlCantidadListNewVarianalControlCantidad != null && !oldControlOfVarianalControlCantidadListNewVarianalControlCantidad.equals(control)) {
                        oldControlOfVarianalControlCantidadListNewVarianalControlCantidad.getVarianalControlCantidadList().remove(varianalControlCantidadListNewVarianalControlCantidad);
                        oldControlOfVarianalControlCantidadListNewVarianalControlCantidad = em.merge(oldControlOfVarianalControlCantidadListNewVarianalControlCantidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = control.getPiControlId();
                if (findControl(id) == null) {
                    throw new NonexistentEntityException("The control with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    /*public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Control control;
            try {
                control = em.getReference(Control.class, id);
                control.getPiControlId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The control with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VarianalControlCantidad> varianalControlCantidadListOrphanCheck = control.getVarianalControlCantidadList();
            for (VarianalControlCantidad varianalControlCantidadListOrphanCheckVarianalControlCantidad : varianalControlCantidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Control (" + control + ") cannot be destroyed since the VarianalControlCantidad " + varianalControlCantidadListOrphanCheckVarianalControlCantidad + " in its varianalControlCantidadList field has a non-nullable control field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SUsuario fsControlUsuacreacion = control.getFsControlUsuacreacion();
            if (fsControlUsuacreacion != null) {
                fsControlUsuacreacion.getControlList().remove(control);
                fsControlUsuacreacion = em.merge(fsControlUsuacreacion);
            }
            SUsuario fsControlUsuamodificacion = control.getFsControlUsuamodificacion();
            if (fsControlUsuamodificacion != null) {
                fsControlUsuamodificacion.getControlList().remove(control);
                fsControlUsuamodificacion = em.merge(fsControlUsuamodificacion);
            }
            em.remove(control);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Control> findControlEntities() {
        return findControlEntities(true, -1, -1);
    }

    public List<Control> findControlEntities(int maxResults, int firstResult) {
        return findControlEntities(false, maxResults, firstResult);
    }

    private List<Control> findControlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Control.class));
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

    public Control findControl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Control.class, id);
        } finally {
            em.close();
        }
    }

    public int getControlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Control> rt = cq.from(Control.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
