/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DetalleServicioInterno;
import entidades.DetalleServicioInternoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.ServicioInterno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class DetalleServicioInternoJpaController implements Serializable {

    public DetalleServicioInternoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleServicioInterno detalleServicioInterno) throws PreexistingEntityException, Exception {
        if (detalleServicioInterno.getDetalleServicioInternoPK() == null) {
            detalleServicioInterno.setDetalleServicioInternoPK(new DetalleServicioInternoPK());
        }
        detalleServicioInterno.getDetalleServicioInternoPK().setPfiDetserintServinte(detalleServicioInterno.getServicioInterno().getPiServinteConsecutivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalleServicioInterno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleServicioInterno(detalleServicioInterno.getDetalleServicioInternoPK()) != null) {
                throw new PreexistingEntityException("DetalleServicioInterno " + detalleServicioInterno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleServicioInterno detalleServicioInterno) throws NonexistentEntityException, Exception {
        detalleServicioInterno.getDetalleServicioInternoPK().setPfiDetserintServinte(detalleServicioInterno.getServicioInterno().getPiServinteConsecutivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalleServicioInterno = em.merge(detalleServicioInterno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleServicioInternoPK id = detalleServicioInterno.getDetalleServicioInternoPK();
                if (findDetalleServicioInterno(id) == null) {
                    throw new NonexistentEntityException("The detalleServicioInterno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleServicioInternoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleServicioInterno detalleServicioInterno;
            try {
                detalleServicioInterno = em.getReference(DetalleServicioInterno.class, id);
                detalleServicioInterno.getDetalleServicioInternoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleServicioInterno with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalleServicioInterno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleServicioInterno> findDetalleServicioInternoEntities() {
        return findDetalleServicioInternoEntities(true, -1, -1);
    }

    public List<DetalleServicioInterno> findDetalleServicioInternoEntities(int maxResults, int firstResult) {
        return findDetalleServicioInternoEntities(false, maxResults, firstResult);
    }

    private List<DetalleServicioInterno> findDetalleServicioInternoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleServicioInterno.class));
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

    public DetalleServicioInterno findDetalleServicioInterno(DetalleServicioInternoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleServicioInterno.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleServicioInternoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleServicioInterno> rt = cq.from(DetalleServicioInterno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<DetalleServicioInterno> detallesServicioInterno(int servicioInterno) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* FROM detalle_servicio_interno d WHERE d.pfi_detserint_servinte = ?; ";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, DetalleServicioInterno.class);
            q.setParameter(1, servicioInterno);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void guardarDetalle(Integer servicioInterno, DetalleServicioInterno detalleServicioInterno, 
            String codigoUsuario)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        Long item = new Long(0);
        String sqlInsertaDetalle =
                "INSERT INTO detalle_servicio_interno "
                + "(pfi_detserint_servinte,	pi_detserint_item,     s_detserint_concepto,"
                + "db_detserint_cantidad,         d_detserint_fechrequ,"
                + "s_detserint_observaciones,   fs_detserint_usuacrea,      d_detserint_creacion)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                       ?,                              NOW()) ; ";
        try {           
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlInsertaDetalle);
            item = calcularConsecutivoItem(servicioInterno);
            q.setParameter(1, servicioInterno);
            q.setParameter(2, item);
            q.setParameter(3, detalleServicioInterno.getSDetserintConcepto());
            q.setParameter(4, detalleServicioInterno.getDbDetserintCantidad());
            q.setParameter(5, detalleServicioInterno.getDDetserintFechrequ());
            q.setParameter(6, detalleServicioInterno.getSDetserintObservaciones());
            q.setParameter(7, codigoUsuario);
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleServicioInterno(new DetalleServicioInternoPK(servicioInterno.intValue(),
                    item.intValue())) != null) {
                throw new PreexistingEntityException("Detalle " + servicioInterno + " - " + item + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long calcularConsecutivoItem(Integer servicioInterno) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(dsi.pi_detserint_item), 0) + 1 "
                + "FROM detalle_servicio_interno dsi "
                + "WHERE dsi.pfi_detserint_servinte = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, servicioInterno);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return new Long(1);
        }
        finally {
            em.close();
        }
    }

    public void eliminarDetalle(DetalleServicioInternoPK detalleServicioInternoPK)
            throws Exception {
        EntityManager em = getEntityManager();
        String sqlBorrarDetalle =
                "DELETE FROM detalle_servicio_interno WHERE pfi_detserint_servinte = ? AND pi_detserint_item = ? ;";
        try {            
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlBorrarDetalle);
            q.setParameter(1, detalleServicioInternoPK.getPfiDetserintServinte());
            q.setParameter(2, detalleServicioInternoPK.getPiDetserintItem());
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
