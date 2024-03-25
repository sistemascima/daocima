/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Documento;
import entidades.Proceso;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ResponsableDocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author owners
 */
public class ProcesoJpaController implements Serializable {

    public ProcesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proceso proceso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getPiProcesoId());
            proceso = em.merge(proceso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proceso.getPiProcesoId();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getPiProcesoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            em.remove(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Proceso> consultarProcesosAccionDocumento(String codigoUsuario, String accion, String tipoDocumento) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT p.* "
                + "FROM usuario_cargo uc "
                + "INNER JOIN responsable_documento rd ON rd.pfs_respdocu_cargo = uc.pfs_usuacarg_cargo "
                + "INNER JOIN proceso p ON p.pi_proceso_id = rd.pfi_respdocu_proceso "
                + "WHERE uc.pfs_usuacarg_usuario = ? AND "
                + "	rd.ps_respdocu_tiporesp = ? AND "
                + "	rd.pfs_respdocu_tipodocu = ? AND "
                + "	uc.s_usuacarg_estado = 'V' ;";
        try {
            Query q = em.createNativeQuery(consulta, Proceso.class);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, accion);
            q.setParameter(3, tipoDocumento);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
