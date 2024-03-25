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
import entidades.Cargo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author owners
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCargo(cargo.getPsCargoCodigo()) != null) {
                throw new PreexistingEntityException("Cargo " + cargo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getPsCargoCodigo());
            cargo = em.merge(cargo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cargo.getPsCargoCodigo();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getPsCargoCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
      
    public List<Cargo> cargosVigentes() {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            String consulta =
                    "SELECT c.* "
                    + "FROM cargo c "
                    + "WHERE c.s_cargo_estado = 'VIGENTE' ; ";
            Query cq = em.createNativeQuery(consulta, Cargo.class);
            resultados = cq.getResultList();
            return resultados;
        } finally {
            em.close();
        }
    }
    
    
    public List<Cargo> busquedaCodigo(String palabraClave) {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            String consulta =
                    "SELECT c.* "
                    + "FROM cargo c "
                    + "WHERE c.ps_cargo_codigo like ? ; ";
            palabraClave = "%" + palabraClave + "%";
            Query cq = em.createNativeQuery(consulta, Cargo.class);
            cq.setParameter(1, palabraClave);
            resultados = cq.getResultList();
            return resultados;
        } finally {
            em.close();
        }
    }
    
    public List<Cargo> cargosCANCELADO() {
        EntityManager em = getEntityManager();
        List<Cargo> resultados = null;
        try {
            String consulta

                    = "SELECT c.* "
                    + "FROM cargo c "
                    + "WHERE c.s_cargo_estado = 'CANCELADO' ; ";
            Query cq = em.createNativeQuery(consulta, Cargo.class);

            resultados = cq.getResultList();
            return resultados;
        } finally {
            em.close();
        }
    }
}
