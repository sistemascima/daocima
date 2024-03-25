/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Cotizacion;
import entidades.CotizacionPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Proveedor;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class CotizacionJpaController implements Serializable {

    public CotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotizacion cotizacion) throws PreexistingEntityException, Exception {
        if (cotizacion.getCotizacionPK() == null) {
            cotizacion.setCotizacionPK(new CotizacionPK());
        }
        cotizacion.getCotizacionPK().setPfiCotizaciSolicomp(cotizacion.getSolicitudCompra().getPiSolicompConsecutivo());
        cotizacion.getCotizacionPK().setPfsCotizaciProveedor(cotizacion.getProveedor().getPsProveedorNit());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cotizacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCotizacion(cotizacion.getCotizacionPK()) != null) {
                throw new PreexistingEntityException("Cotizacion " + cotizacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizacion cotizacion) throws NonexistentEntityException, Exception {
        cotizacion.getCotizacionPK().setPfiCotizaciSolicomp(cotizacion.getSolicitudCompra().getPiSolicompConsecutivo());
        cotizacion.getCotizacionPK().setPfsCotizaciProveedor(cotizacion.getProveedor().getPsProveedorNit());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cotizacion = em.merge(cotizacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CotizacionPK id = cotizacion.getCotizacionPK();
                if (findCotizacion(id) == null) {
                    throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CotizacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizacion cotizacion;
            try {
                cotizacion = em.getReference(Cotizacion.class, id);
                cotizacion.getCotizacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(cotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotizacion> findCotizacionEntities() {
        return findCotizacionEntities(true, -1, -1);
    }

    public List<Cotizacion> findCotizacionEntities(int maxResults, int firstResult) {
        return findCotizacionEntities(false, maxResults, firstResult);
    }

    private List<Cotizacion> findCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizacion.class));
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

    public Cotizacion findCotizacion(CotizacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizacion> rt = cq.from(Cotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cotizacion> cotizacionesSolicitudCompra(int solicitudCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT c.* FROM cotizacion c WHERE c.pfi_cotizaci_solicomp = ?; ";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, Cotizacion.class);
            q.setParameter(1, solicitudCompra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void guardarCotizacion(Proveedor proveedor, Integer solicitudCompra,
            Date fecha, String numeroCotización,
            String nombreArchivo, String soporte, String codigoUsuario)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        ProveedorJpaController proveedorJpa = new ProveedorJpaController(emf);
        String sqlInsertaCotizacion
                = "INSERT INTO cotizacion "
                + "(pfi_cotizaci_solicomp,	pfs_cotizaci_proveedor,     s_cotizaci_numero,"
                + "d_cotizaci_fechcoti,         s_cotizaci_nombarch,  s_cotizaci_nombsopo, "
                + "fs_cotizaci_usuacrea,        d_cotizaci_creacion)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW()) ; ";
        String sqlInsertaProveedor
                = "INSERT INTO proveedor "
                + "(ps_proveedor_nit,   	s_proveedor_nombre,	s_proveedor_estado,"
                + "s_proveedor_portafolio,	s_proveedor_direccion,	s_proveedor_telefono,"
                + "s_proveedor_correo,          s_proveedor_contacto,	s_proveedor_observaciones,"
                + "s_proveedor_carpeta,         fs_proveedor_usuacrea,	d_proveedor_creacion,"
                + "fs_proveedor_usuultmod,      d_proveedor_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              NOW(), "
                + "NULL,                        NULL ) ; ";
        try {
            Proveedor p = proveedorJpa.getProveedor(proveedor.getPsProveedorNit());
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            if (p == null) {
                q = em.createNativeQuery(sqlInsertaProveedor);
                q.setParameter(1, proveedor.getPsProveedorNit());
                q.setParameter(2, proveedor.getSProveedorNombre().toUpperCase());
                q.setParameter(3, proveedor.getSProveedorEstado());
                q.setParameter(4, proveedor.getSProveedorPortafolio());
                q.setParameter(5, proveedor.getSProveedorDireccion());
                q.setParameter(6, proveedor.getSProveedorTelefono());
                q.setParameter(7, proveedor.getSProveedorCorreo());
                q.setParameter(8, proveedor.getSProveedorContacto());
                q.setParameter(9, proveedor.getSProveedorObservaciones());
                q.setParameter(10, proveedor.getSProveedorCarpeta());
                q.setParameter(11, codigoUsuario);
                q.executeUpdate();
            }
            q = em.createNativeQuery(sqlInsertaCotizacion);
            q.setParameter(1, solicitudCompra);
            q.setParameter(2, proveedor.getPsProveedorNit());
            q.setParameter(3, numeroCotización);
            q.setParameter(4, fecha);
            q.setParameter(5, nombreArchivo);        
            q.setParameter(6, soporte);
            q.setParameter(7, codigoUsuario);
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCotizacion(new CotizacionPK(solicitudCompra, proveedor.getPsProveedorNit())) != null) {
                throw new PreexistingEntityException("Cotización " + solicitudCompra + " - " + proveedor.getPsProveedorNit() + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarCotizacion(Integer solicitudCompra, String nitProveedor)
            throws Exception {
        EntityManager em = getEntityManager();
         String sqlBorrarCotizacion
                = "DELETE FROM cotizacion WHERE pfi_cotizaci_solicomp = ? AND pfs_cotizaci_proveedor = ? ;";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlBorrarCotizacion);
            q.setParameter(1, solicitudCompra);
            q.setParameter(2, nitProveedor);
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
    
    public List<Object> encontrarCotizacion(int solicitudCompra, String nitProveedor){
        System.out.println("solicitud compra"+ solicitudCompra); 
        System.out.println("nit proveedor"+ nitProveedor); 
        EntityManager em = getEntityManager();
        String consulta = "select s_cotizaci_nombarch from cotizacion "
                + "where pfi_cotizaci_solicomp=? "
                + "and pfs_cotizaci_proveedor=? ";
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, solicitudCompra);
            q.setParameter(2, nitProveedor);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
