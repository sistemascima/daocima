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
import entidades.RespSeleProv;
import entidades.SeleProvSoliComp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
public class SeleProvSoliCompJpaController implements Serializable {

    public SeleProvSoliCompJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeleProvSoliComp seleProvSoliComp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(seleProvSoliComp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeleProvSoliComp(seleProvSoliComp.getPiSeprsocoNumeeval()) != null) {
                throw new PreexistingEntityException("SeleProvSoliComp " + seleProvSoliComp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeleProvSoliComp seleProvSoliComp) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seleProvSoliComp = em.merge(seleProvSoliComp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seleProvSoliComp.getPiSeprsocoNumeeval();
                if (findSeleProvSoliComp(id) == null) {
                    throw new NonexistentEntityException("The seleProvSoliComp with id " + id + " no longer exists.");
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
            SeleProvSoliComp seleProvSoliComp;
            try {
                seleProvSoliComp = em.getReference(SeleProvSoliComp.class, id);
                seleProvSoliComp.getPiSeprsocoNumeeval();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seleProvSoliComp with id " + id + " no longer exists.", enfe);
            }
            em.remove(seleProvSoliComp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeleProvSoliComp> findSeleProvSoliCompEntities() {
        return findSeleProvSoliCompEntities(true, -1, -1);
    }

    public List<SeleProvSoliComp> findSeleProvSoliCompEntities(int maxResults, int firstResult) {
        return findSeleProvSoliCompEntities(false, maxResults, firstResult);
    }

    private List<SeleProvSoliComp> findSeleProvSoliCompEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeleProvSoliComp.class));
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

    public SeleProvSoliComp findSeleProvSoliComp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeleProvSoliComp.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeleProvSoliCompCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeleProvSoliComp> rt = cq.from(SeleProvSoliComp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public SeleProvSoliComp traerSeleccionSolicitud(Integer solicitudCompra, String codigoUsuario) {
        EntityManager em = getEntityManager();
        SeleProvSoliComp resultado = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_tra_sel_sol_com (?, ?) ; ", SeleProvSoliComp.class);

            pa.setParameter(1, solicitudCompra);
            pa.setParameter(2, codigoUsuario);
            resultado = (SeleProvSoliComp)pa.getSingleResult();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List proveedoresPuntajesSolicitud(Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            System.out.println("entra a hacer el query");
            em.getTransaction().begin();
          
         Query pa = em.createNativeQuery(" SELECT DISTINCT cotizacion.pfs_cotizaci_proveedor, "
                 + "proveedor.s_proveedor_nombre, proveedor.s_proveedor_estado "
                 + "FROM solicitud_compra "
                 + "INNER JOIN cotizacion ON cotizacion.pfi_cotizaci_solicomp = solicitud_compra.pi_solicomp_consecutivo "
                 + "INNER JOIN proveedor ON proveedor.ps_proveedor_nit= cotizacion.pfs_cotizaci_proveedor "
                 + "where pi_solicomp_consecutivo= ? "
                 + "AND s_solicomp_tipocomp ='N' ");
            pa.setParameter(1, solicitudCompra);
            //pa.setParameter(2, solicitudCompra);
            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            System.err.println(""+ex);
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarSeleccion(SeleProvSoliComp seleccion, String codigoUsuario) 
            throws Exception{
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("UPDATE sele_prov_soli_comp " +
                    "SET s_seprsoco_evalserv = ? , " +
                    "s_seprsoco_evalsumi = ? , " +
                    "s_seprsoco_evalequi = ? , " +
                    "s_seprsoco_observaciones = ? , " +
                    "fs_seprsoco_usuultmod = ? , " +
                    "d_seprsoco_ultimodi = NOW() " +
                    "WHERE pi_seprsoco_numeeval = ? ; ");

            pa.setParameter(1, seleccion.getSSeprsocoEvalserv());
            pa.setParameter(2, seleccion.getSSeprsocoEvalsumi());
            pa.setParameter(3, seleccion.getSSeprsocoEvalequi());
            pa.setParameter(4, seleccion.getSSeprsocoObservaciones());
            pa.setParameter(5, codigoUsuario);
            pa.setParameter(6, seleccion.getPiSeprsocoNumeeval());
            pa.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public String seleccionarProveedorSolicitud( SeleProvSoliComp seleccion, String nitProveedorSeleccionado,
            String codigoUsuario, Integer solicitudCompra, Integer item, String obseSeleItem) {
        EntityManager em = getEntityManager();
        String resultado = null;
        try {
            System.out.println("seleccion "+ seleccion);
            System.out.println("nit "+ nitProveedorSeleccionado);
            System.out.println("coidgo usuario "+ codigoUsuario);
            System.out.println("solicitudCompra "+ solicitudCompra);
            System.out.println("item "+ item);
            
            System.out.println("obseSeleitem "+ obseSeleItem);
            
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_seleccionar_proveedor (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ; ");

            pa.setParameter(1, (seleccion == null)? null : seleccion.getPiSeprsocoNumeeval());
            pa.setParameter(2, (seleccion == null)? null : seleccion.getSSeprsocoEvalserv());
            pa.setParameter(3, (seleccion == null)? null : seleccion.getSSeprsocoEvalsumi());
            pa.setParameter(4, (seleccion == null)? null : seleccion.getSSeprsocoEvalequi());
            pa.setParameter(5, (seleccion == null)? null : seleccion.getSSeprsocoObservaciones());
            pa.setParameter(6, nitProveedorSeleccionado);
            pa.setParameter(7, codigoUsuario);
            pa.setParameter(8, solicitudCompra);
            pa.setParameter(9, item);
            pa.setParameter(10, obseSeleItem);
            resultado = (String) (pa.getSingleResult());
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return "Error al ejecutar: " + ex.getMessage();
        } finally {
            em.close();
        }
    }

    public List traerProveedoresSeleccionSolicitud(Integer numeroSolicitud) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("call pa_prov_soli_comp_anal (?) ; ", "pa_prov_soli_comp_anal");

            pa.setParameter(1, numeroSolicitud);
            resultado = pa.getResultList();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public SeleProvSoliComp traerSeleccionSolicitud(Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        SeleProvSoliComp resultado = null;
        try {
            em.getTransaction().begin();
            Query pa = em.createNativeQuery("SELECT s.* " +
                    "FROM sele_prov_soli_comp s " +
                    "WHERE s.fi_seprsoco_solicomp = ?; ", SeleProvSoliComp.class);

            pa.setParameter(1, solicitudCompra);
            resultado = (SeleProvSoliComp)pa.getSingleResult();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public BigDecimal entregarPuntajeProveedor(String nit) {
          EntityManager em = getEntityManager();
        BigDecimal resultado = null;
        try {
            em.getTransaction().begin();
         String query = " SELECT sum(db_resevapro_puntaje) from resp_eval_prov "
                    + "WHERE pfs_resevapro_proveedor= ? and pfi_resevapro_numeeval=( "
                    + "			select max(pfi_resevapro_numeeval) "
                    + "from resp_eval_prov "
                    + "where pfs_resevapro_proveedor=?) "
                    + "group by pfi_resevapro_numeeval ";
         Query pa = em.createNativeQuery(query);
            pa.setParameter(1, nit);
             pa.setParameter(2, nit);
            //pa.setParameter(2, solicitudCompra);
            resultado = (BigDecimal) pa.getSingleResult();
            em.getTransaction().commit();
            return resultado;
        } catch (Exception ex) {
            System.err.println(""+ex);
            return null;
        } finally {
            em.close();
        }
    }

    public List<BigDecimal> encontrarCantidadAspectos(String nit) {
       EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
         String query = " SELECT sum(db_resevapro_puntaje) from resp_eval_prov "
                    + "WHERE pfs_resevapro_proveedor= ? and pfi_resevapro_numeeval=( "
                    + "select max(pfi_resevapro_numeeval) "
                    + "from resp_eval_prov "
                    + "where pfs_resevapro_proveedor=?) "
                    + "group by pfs_resevapro_aspecto ";
         Query pa = em.createNativeQuery(query);
            pa.setParameter(1, nit);
             pa.setParameter(2, nit);
            //pa.setParameter(2, solicitudCompra);
            return (List<BigDecimal>) pa.getResultList();
        } catch (Exception ex) {
            System.err.println(""+ex);
            return null;
        } finally {
            em.close();
        }
    }
    
}
