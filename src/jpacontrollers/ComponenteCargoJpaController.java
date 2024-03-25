/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Cargo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Componente;
import entidades.ComponenteCargo;
import entidades.ComponenteCargoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ComponenteCargoJpaController implements Serializable {

    public ComponenteCargoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComponenteCargo componenteCargo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(componenteCargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComponenteCargo(componenteCargo.getComponenteCargoPK()) != null) {
                throw new PreexistingEntityException("ComponenteCargo " + componenteCargo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComponenteCargo componenteCargo) throws NonexistentEntityException, Exception {
        componenteCargo.getComponenteCargoPK().setPfiCompcargComponente(componenteCargo.getComponente().getPiComponenId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            componenteCargo = em.merge(componenteCargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComponenteCargoPK id = componenteCargo.getComponenteCargoPK();
                if (findComponenteCargo(id) == null) {
                    throw new NonexistentEntityException("The componenteCargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComponenteCargoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComponenteCargo componenteCargo;
            try {
                componenteCargo = em.getReference(ComponenteCargo.class, id);
                componenteCargo.getComponenteCargoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componenteCargo with id " + id + " no longer exists.", enfe);
            }
            em.remove(componenteCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComponenteCargo> findComponenteCargoEntities() {
        return findComponenteCargoEntities(true, -1, -1);
    }

    public List<ComponenteCargo> findComponenteCargoEntities(int maxResults, int firstResult) {
        return findComponenteCargoEntities(false, maxResults, firstResult);
    }

    private List<ComponenteCargo> findComponenteCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComponenteCargo.class));
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

    public ComponenteCargo findComponenteCargo(ComponenteCargoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComponenteCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponenteCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComponenteCargo> rt = cq.from(ComponenteCargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cargo> cargosComponenteEjecutarDepartamento(String componente, String departamento) {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            em.getTransaction().begin();
            String consulta =
                    "SELECT ca.* " +
                    "FROM componente_cargo cc " +
                    "INNER JOIN componente c ON c.pi_componen_id = cc.pfi_compcarg_componente " +
                    "INNER JOIN cargo ca ON ca.ps_cargo_codigo = cc.pfs_compcarg_cargo " +
                    "WHERE c.s_componen_nombre = ? " +
                    "	AND ca.fs_cargo_departamento = ? " +
                    "	AND ca.s_cargo_estado = 'VIGENTE' " +
                    "	AND c.s_componen_ejecutar = '1' " +
                    "	AND c.s_componen_estado = 'A'; ";
            Query cq = em.createNativeQuery(consulta, Cargo.class);
            cq.setParameter(1, componente);
            cq.setParameter(2, departamento);
            resultados = cq.getResultList();
            em.getTransaction().commit();
            return resultados;
        } catch (NoResultException noRes) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
