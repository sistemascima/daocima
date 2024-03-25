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
import entidades.Cotizacion;
import entidades.EvaluacionProveedor;
import entidades.Proveedor;
import entidades.TipoProductoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getCotizacionCollection() == null) {
            proveedor.setCotizacionCollection(new ArrayList<Cotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getPsProveedorNit()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedor = em.merge(proveedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proveedor.getPsProveedorNit();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getPsProveedorNit();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public ArrayList<String> busquedaProveedorNit(String cadenaBusqueda) {
        EntityManager em = getEntityManager();
        ArrayList<String> retorno = new ArrayList<String>();
        String consulta = "SELECT CONCAT('[', p.ps_proveedor_nit, '] ', p.s_proveedor_nombre) AS texto "
                + "FROM proveedor p "
                + "WHERE p.ps_proveedor_nit like ? ; ";
        try {
            cadenaBusqueda = "%" + cadenaBusqueda + "%";
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, cadenaBusqueda);
            List<String> aux = q.getResultList();
            retorno.addAll(aux);
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return retorno;
        } finally {
            em.close();
        }
    }

    public ArrayList<String> busquedaProveedorNombre(String cadenaBusqueda) {
        EntityManager em = getEntityManager();
        ArrayList<String> retorno = new ArrayList<String>();
        String consulta = "SELECT CONCAT('[', p.ps_proveedor_nit, '] ', p.s_proveedor_nombre) AS texto "
                + "FROM proveedor p "
                + "WHERE LOWER(p.s_proveedor_nombre) like ? ; ";
        try {
            cadenaBusqueda = "%" + cadenaBusqueda.toLowerCase() + "%";
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, cadenaBusqueda);
            List<String> aux = q.getResultList();
            retorno.addAll(aux);
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return retorno;
        } finally {
            em.close();
        }
    }

    public Proveedor getProveedor(String nit) {
        EntityManager em = getEntityManager();
        ArrayList<String> retorno = new ArrayList<String>();
        String consulta = "SELECT p.* "
                + "FROM proveedor p "
                + "WHERE p.ps_proveedor_nit = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, nit);
            return (Proveedor) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void incluir(String nitProveedor, String codigoUsuario, char estado) throws Exception {
        EntityManager em = getEntityManager();
        String sqlActualizaProveedor
                = "UPDATE proveedor "
                + "SET s_proveedor_estado = ? , "
                + "	d_proveedor_fechingr = NOW(), "
                + "	fs_proveedor_usuultmod = ?, "
                + "	d_proveedor_ultimodi = NOW() "
                + "WHERE ps_proveedor_nit = ? ;";
        String sqlActualizaSolicitudesPendientes
                = "UPDATE solicitud_compra "
                + "SET s_solicomp_estado = 'O', "
                + "	fs_solicomp_usuultmod = ?, "
                + "	d_solicomp_ultimodi = NOW() "
                + "WHERE pi_solicomp_consecutivo IN ( "
                + "		SELECT d1.pfi_detsolcom_solicomp "
                + "		FROM detalle_solicitud_compra d1 "
                + "               INNER JOIN proveedor p ON p.ps_proveedor_nit = d1.fs_detsolcom_provsele "
                + "		WHERE d1.pfi_detsolcom_solicomp IN( SELECT d2.pfi_detsolcom_solicomp  "
                + "                                                   FROM detalle_solicitud_compra d2 "
                + "                                                   WHERE d2.fs_detsolcom_provsele = ?) "
                + "               GROUP BY d1.pfi_detsolcom_solicomp "
                + "               HAVING SUM(if(p.s_proveedor_estado <> 'I', 1, 0)) = 0) AND "
                + "	s_solicomp_estado = 'P';";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaProveedor);
            q.setParameter(1, estado);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, nitProveedor);
            q.executeUpdate();

            if (estado == 'I') {
                q = em.createNativeQuery(sqlActualizaSolicitudesPendientes);
                q.setParameter(1, codigoUsuario);
                q.setParameter(2, nitProveedor);
                q.executeUpdate();
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findProveedor(nitProveedor) == null) {
                    throw new NonexistentEntityException("El Proveedor " + nitProveedor + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EvaluacionProveedor> getEvaluacionesProveedor(String tipo, String nit) {
        EntityManager em = getEntityManager();
        List<EvaluacionProveedor> retorno;
        String consulta = "SELECT ep.* "
                + "FROM evaluacion_proveedor ep "
                + "WHERE ep.pfs_evalprov_tipo = ? AND "
                + "ep.pfs_evalprov_proveedor = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta, EvaluacionProveedor.class);
            q.setParameter(1, tipo);
            q.setParameter(2, nit);
            retorno = q.getResultList();
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public boolean hayReevaluacionesCreadas(String nit) {
        EntityManager em = getEntityManager();
        List<EvaluacionProveedor> retorno;
        String consulta = "SELECT e.* "
                + "FROM evaluacion_proveedor e "
                + "WHERE e.pfs_evalprov_proveedor = ? AND "
                + "	e.pfs_evalprov_tipo = 'RP' AND "
                + "	e.s_evalprov_estado = 'C' ; ";
        try {
            Query q = em.createNativeQuery(consulta, EvaluacionProveedor.class);
            q.setParameter(1, nit);
            retorno = q.getResultList();
            return (retorno != null && !retorno.isEmpty());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public List<Proveedor> consultaProveedores(String nitBusqueda, String nombreBusqueda) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT p.* "
                + "FROM proveedor p "
                + "WHERE p.ps_proveedor_nit like ? AND p.s_proveedor_nombre like ? ; ";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, nitBusqueda);
            q.setParameter(2, nombreBusqueda);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List listaProveedores(String filtro, TipoProductoServicio tipo, String nombre) {
        EntityManager em = getEntityManager();
        List resultado = null;
        String consulta = "select DISTINCT pfs_resevapro_proveedor "
                + "from proveedor p "
                + "join resp_eval_prov on resp_eval_prov.pfs_resevapro_proveedor= p.ps_proveedor_nit "
                + "where pfs_resevapro_aspecto=? ";
                if (filtro != null && !filtro.equals("")) {
            consulta += filtro;
        }
        consulta += " ; ";
        System.out.println("consulta " + consulta);
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery(consulta, "resultados");

            pa.setParameter(1, tipo.getPsTipproserCodigo());
            if (nombre != null && !nombre.equals("")) {
                pa.setParameter(2, nombre.toUpperCase());
            }
            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarProveedor(Proveedor proveedor)
            throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE proveedor "
                    + "SET s_proveedor_direccion = ?, "
                    + "	s_proveedor_telefono = ?, "
                    + "	s_proveedor_nombre = ?, "
                    + "	s_proveedor_correo = ?, "
                    + "	s_proveedor_contacto = ?, "
                    + "	s_proveedor_portafolio = ?, "
                    + "	s_proveedor_observaciones = ?, "
                    + "	fs_proveedor_usuultmod = ?, "
                    + "	d_proveedor_ultimodi = NOW() "
                    + "WHERE ps_proveedor_nit = ? ; ");

            pa.setParameter(1, proveedor.getSProveedorDireccion());
            pa.setParameter(2, proveedor.getSProveedorTelefono());
            pa.setParameter(3, proveedor.getSProveedorNombre());
            pa.setParameter(4, proveedor.getSProveedorCorreo());
            pa.setParameter(5, proveedor.getSProveedorContacto());
            pa.setParameter(6, proveedor.getSProveedorPortafolio());
            pa.setParameter(7, proveedor.getSProveedorObservaciones());
            pa.setParameter(8, proveedor.getFsProveedorUsuultmod().getPsUsuarioCodigo());
            pa.setParameter(9, proveedor.getPsProveedorNit());
            pa.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Proveedor> encontrarProveedorPorNombre(String nombreProveedor) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * "
                + " FROM proveedor"
                + " where s_proveedor_nombre = ?";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, nombreProveedor);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Proveedor> encontrarProveedorPorNit(String nitProveedor) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * "
                + " FROM proveedor"
                + " where ps_proveedor_nit = ?";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, nitProveedor);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void crearProveedor(Proveedor proveedor) {
        EntityManager em = getEntityManager();
        try {

            String insert = "INSERT INTO proveedor (ps_proveedor_nit, "
                    + "s_proveedor_nombre, s_proveedor_direccion, "
                    + "s_proveedor_telefono, s_proveedor_correo, "
                    + "fs_proveedor_usuacrea, "
                    + "d_proveedor_creacion, s_proveedor_estado, s_proveedor_portafolio, s_proveedor_contacto, s_proveedor_observaciones) "
                    + "VALUES (?, ?, ?, ?, ?, ?, now(), 'C', ?, ?, ?)";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, proveedor.getPsProveedorNit());
            insercion.setParameter(2, proveedor.getSProveedorNombre().toUpperCase());
            insercion.setParameter(3, proveedor.getSProveedorDireccion());
            insercion.setParameter(4, proveedor.getSProveedorTelefono());
            insercion.setParameter(5, proveedor.getSProveedorCorreo());
            insercion.setParameter(6, proveedor.getFsProveedorUsuacrea().getPsUsuarioCodigo());
            insercion.setParameter(7, proveedor.getSProveedorPortafolio());
            insercion.setParameter(8, proveedor.getSProveedorContacto());
            insercion.setParameter(9, proveedor.getSProveedorObservaciones());
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarNombreProveedores() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct proveedor.s_proveedor_nombre"
                + " FROM proveedor";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarProveedor(String filtros, List<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = " select distinct proveedor.ps_proveedor_nit, proveedor.s_proveedor_nombre,"
                + " proveedor.s_proveedor_direccion, proveedor.s_proveedor_telefono,"
                + " proveedor.s_proveedor_correo, proveedor.s_proveedor_contacto"
                + " from proveedor"
                + " where ";
        consulta += filtros;
        try {
            Query q = em.createNativeQuery(consulta);
            System.out.println("query" + q.toString());
            int j = 1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }

    }
    
     public List<Proveedor> encontrarListadoProveedor() {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * "
                + " FROM proveedor";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int encontrarMaximoEvaluacion(String nitProveedor, char aspecto) {
         EntityManager em = getEntityManager();
        String consulta = "select max(pfi_resevapro_numeeval) "
                + "from resp_eval_prov "
                + "join evaluacion_proveedor on evaluacion_proveedor.pi_evalprov_numeeval= resp_eval_prov.pfi_resevapro_numeeval "
                + "where pfs_resevapro_proveedor=? "
                + "and evaluacion_proveedor.s_evalprov_estado='T' "
                + "and pfs_resevapro_aspecto=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nitProveedor);
            q.setParameter(2, aspecto);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Proveedor encontrarProveedorNit(String nit) {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * "
                + " FROM proveedor"
                + " where ps_proveedor_nit=? ";
        try {
            Query q = em.createNativeQuery(consulta, Proveedor.class);
            q.setParameter(1, nit);
            return (Proveedor)q.getSingleResult();
        } finally {
            em.close();
        }
    }

    
    

}
