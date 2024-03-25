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
import entidades.Proveedor;
import entidades.SoporteProveedor;
import entidades.SoporteProveedorPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class SoporteProveedorJpaController implements Serializable {

    public SoporteProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SoporteProveedor soporteProveedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(soporteProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSoporteProveedor(soporteProveedor.getSoporteProveedorPK()) != null) {
                throw new PreexistingEntityException("SoporteProveedor " + soporteProveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SoporteProveedor soporteProveedor) throws NonexistentEntityException, Exception {
        soporteProveedor.getSoporteProveedorPK().setPfsSopoprovProveedor(soporteProveedor.getProveedor().getPsProveedorNit());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            soporteProveedor = em.merge(soporteProveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SoporteProveedorPK id = soporteProveedor.getSoporteProveedorPK();
                if (findSoporteProveedor(id) == null) {
                    throw new NonexistentEntityException("The soporteProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SoporteProveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SoporteProveedor soporteProveedor;
            try {
                soporteProveedor = em.getReference(SoporteProveedor.class, id);
                soporteProveedor.getSoporteProveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The soporteProveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(soporteProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
public void eliminarSoporte( String nitProveedor,String consecutivo ) throws Exception
{
String sqlConsultaEliminarSoporte="DELETE FROM soporte_proveedor"
        + " WHERE pi_sopoprov_consecutivo= ? "
        + " AND pfs_sopoprov_proveedor= ? ;";
EntityManager em = getEntityManager();
  try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            q = em.createNativeQuery(sqlConsultaEliminarSoporte);
            q.setParameter(1, consecutivo);
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

    public List<SoporteProveedor> findSoporteProveedorEntities() {
        return findSoporteProveedorEntities(true, -1, -1);
    }

    public List<SoporteProveedor> findSoporteProveedorEntities(int maxResults, int firstResult) {
        return findSoporteProveedorEntities(false, maxResults, firstResult);
    }

    private List<SoporteProveedor> findSoporteProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SoporteProveedor.class));
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

    public SoporteProveedor findSoporteProveedor(SoporteProveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SoporteProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getSoporteProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SoporteProveedor> rt = cq.from(SoporteProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SoporteProveedor> getSoportesProveedorFS001(String nit) {
        EntityManager em = getEntityManager();
        List<SoporteProveedor> retorno;
        String consulta = "SELECT sp.* "
                + " FROM soporte_proveedor sp  "
                + " WHERE sp.pfs_sopoprov_proveedor = ?  "
                + " AND sp.s_sopoprov_descripcion like ?;  ";
        try {
            Query q = em.createNativeQuery(consulta, SoporteProveedor.class);
            q.setParameter(1, nit);
            q.setParameter(2, "FS-001");
            retorno = q.getResultList();
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<SoporteProveedor> getSoportesProveedor(String nit, int ultimaEvaluacion) {
        EntityManager em = getEntityManager();
        List<SoporteProveedor> retorno;
        String consulta = "SELECT sp.* "
                + "FROM soporte_proveedor sp "
                + "WHERE sp.pfs_sopoprov_proveedor = ?  and pfi_sopoprov_resevapro_numeeval=? ; ";
        try {
            Query q = em.createNativeQuery(consulta, SoporteProveedor.class);
            q.setParameter(1, nit);
            q.setParameter(2, ultimaEvaluacion);
            retorno = q.getResultList();
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void guardarSoporte(SoporteProveedor soporteProveedor, boolean actualizarCarpeta, String carpeta, String nombreArchivo, String codigoUsuario, int numeval)
            throws PreexistingEntityException, Exception {
        System.out.println("numero evaluacin  " + numeval);
        EntityManager em = getEntityManager();
        Integer consecutivoAsignado = 0;
        String sqlInsertaSoporte
                = "INSERT INTO soporte_proveedor "
                + "(pi_sopoprov_consecutivo,	pfs_sopoprov_proveedor,	s_sopoprov_descripcion, "
                + "s_sopoprov_nombarch,		fs_sopoprov_usuacrea, pfi_sopoprov_resevapro_numeeval,"
                + "pfs_sopoprov_resevapro_tipo, "
                + "d_sopoprov_creacion "
                + ") "
                + "VALUES "
                + "(?,		?,		?, "
                + "?,		?,		? , 'RP', NOW() ); ";
        String sqlActualizaCarpetaProveedor
                = "UPDATE proveedor "
                + "SET s_proveedor_carpeta = ?, "
                + "fs_proveedor_usuultmod = ?, "
                + "d_proveedor_ultimodi = NOW() "
                + "WHERE ps_proveedor_nit = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consecutivoAsignado = calcularConsecutivo(soporteProveedor.getSoporteProveedorPK().getPfsSopoprovProveedor()).intValue();
            Query q;
            if (actualizarCarpeta) {
                q = em.createNativeQuery(sqlActualizaCarpetaProveedor);
                q.setParameter(1, carpeta);
                q.setParameter(2, codigoUsuario);
                q.setParameter(3, soporteProveedor.getSoporteProveedorPK().getPfsSopoprovProveedor());
                q.executeUpdate();
            }
            q = em.createNativeQuery(sqlInsertaSoporte);
            q.setParameter(1, consecutivoAsignado);
            q.setParameter(2, soporteProveedor.getSoporteProveedorPK().getPfsSopoprovProveedor());
            q.setParameter(3, soporteProveedor.getSSopoprovDescripcion());
            q.setParameter(4, nombreArchivo);
            q.setParameter(5, codigoUsuario);
            q.setParameter(6, numeval);
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSoporteProveedor(new SoporteProveedorPK(consecutivoAsignado, soporteProveedor.getSoporteProveedorPK().getPfsSopoprovProveedor())) != null) {
                throw new PreexistingEntityException("Soporte " + consecutivoAsignado + " - " + soporteProveedor.getSoporteProveedorPK().getPfsSopoprovProveedor() + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long calcularConsecutivo(String nitProveedor) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(s.pi_sopoprov_consecutivo) + 1, "
                + "	1 ) AS proximoCons "
                + "FROM soporte_proveedor s "
                + "WHERE s.pfs_sopoprov_proveedor = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nitProveedor);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Long(0);
        } finally {
            em.close();
        }
    }

}
