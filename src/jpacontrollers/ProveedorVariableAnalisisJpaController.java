/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.MatrizAnalisis;
import entidades.Proveedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.ProveedorVariableAnalisis;
import entidades.ProveedorVariableAnalisisPK;
import entidades.VariableAnalisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ProveedorVariableAnalisisJpaController implements Serializable {

    public ProveedorVariableAnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProveedorVariableAnalisis proveedorVariableAnalisis) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedorVariableAnalisis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedorVariableAnalisis(proveedorVariableAnalisis.getProveedorVariableAnalisisPK()) != null) {
                throw new PreexistingEntityException("ProveedorVariableAnalisis " + proveedorVariableAnalisis + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProveedorVariableAnalisis proveedorVariableAnalisis) throws NonexistentEntityException, Exception {
        proveedorVariableAnalisis.getProveedorVariableAnalisisPK().setPfsProvaranaProveedor(proveedorVariableAnalisis.getProveedor().getPsProveedorNit());
        proveedorVariableAnalisis.getProveedorVariableAnalisisPK().setPfiProvaranaVariable(proveedorVariableAnalisis.getVariableAnalisis().getPiVarianalId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProveedorVariableAnalisis persistentProveedorVariableAnalisis = em.find(ProveedorVariableAnalisis.class, proveedorVariableAnalisis.getProveedorVariableAnalisisPK());
            proveedorVariableAnalisis = em.merge(proveedorVariableAnalisis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProveedorVariableAnalisisPK id = proveedorVariableAnalisis.getProveedorVariableAnalisisPK();
                if (findProveedorVariableAnalisis(id) == null) {
                    throw new NonexistentEntityException("The proveedorVariableAnalisis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProveedorVariableAnalisisPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProveedorVariableAnalisis proveedorVariableAnalisis;
            try {
                proveedorVariableAnalisis = em.getReference(ProveedorVariableAnalisis.class, id);
                proveedorVariableAnalisis.getProveedorVariableAnalisisPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedorVariableAnalisis with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedorVariableAnalisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProveedorVariableAnalisis> findProveedorVariableAnalisisEntities() {
        return findProveedorVariableAnalisisEntities(true, -1, -1);
    }

    public List<ProveedorVariableAnalisis> findProveedorVariableAnalisisEntities(int maxResults, int firstResult) {
        return findProveedorVariableAnalisisEntities(false, maxResults, firstResult);
    }

    private List<ProveedorVariableAnalisis> findProveedorVariableAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProveedorVariableAnalisis.class));
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

    public ProveedorVariableAnalisis findProveedorVariableAnalisis(ProveedorVariableAnalisisPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProveedorVariableAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorVariableAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProveedorVariableAnalisis> rt = cq.from(ProveedorVariableAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<ProveedorVariableAnalisis> consultaProveedoresVariable(Integer idVariableAnalisis, Integer solicitudCompra) {
        EntityManager em = getEntityManager();
        ProveedorVariableAnalisis[] resultados = null;
        
        String consulta = "SELECT DISTINCT pva.* "
                + "FROM proveedor_variable_analisis pva "
                + "WHERE pva.pfi_provarana_variable = ? AND pva.pfs_provarana_proveedor IN "
                + "     (SELECT c.pfs_cotizaci_proveedor " +
                "       FROM cotizacion c " +
                "       WHERE c.pfi_cotizaci_solicomp = ?);";
        try {
            Query q = em.createNativeQuery(consulta, ProveedorVariableAnalisis.class);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, solicitudCompra);
            return q.getResultList();
        }catch(Exception e){
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public  List<VariableAnalisis> getVariableAnalisisPorMatriz(int matriz) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM variable_analisis "
                + "where variable_analisis.fi_varianal_matriz=? "
                + "order by s_varianal_descripcion ASC"; 
        try {
            Query q = em.createNativeQuery(consulta, VariableAnalisis.class);
            q.setParameter(1, matriz);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> cargarListaProveedores(int matriz) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct proveedor.s_proveedor_nombre  FROM proveedor_variable_analisis"
                + "                join variable_analisis"
                + "                 on variable_analisis.pi_varianal_id= proveedor_variable_analisis.pfi_provarana_variable"
                + "                join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor"
                + "				where variable_analisis.fi_varianal_matriz=?"
                + "                order by proveedor.s_proveedor_nombre ASC";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, matriz);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ProveedorVariableAnalisis> encontrarParametroPorProveedor(int parametro, String proveedor) {
        EntityManager em = getEntityManager();
        String consulta =  " SELECT * FROM proveedor_variable_analisis "
                + "join proveedor on proveedor.ps_proveedor_nit = proveedor_variable_analisis.pfs_provarana_proveedor "
                + "join variable_analisis on variable_analisis.pi_varianal_id = proveedor_variable_analisis.pfi_provarana_variable"
                + " where proveedor.s_proveedor_nombre =? and variable_analisis.pi_varianal_id=?";
        try {
            Query q = em.createNativeQuery(consulta, ProveedorVariableAnalisis.class);
            q.setParameter(1, proveedor);
            q.setParameter(2, parametro);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

        public String consultarTipoAnalisisLaboratorio(int parametro, String proveedor) {
        EntityManager em = getEntityManager();
        String consulta =  " SELECT s_tipoanal_nomenclatura FROM proveedor_variable_analisis " +
                           " join proveedor on proveedor.ps_proveedor_nit = proveedor_variable_analisis.pfs_provarana_proveedor " +
                           " join variable_analisis on variable_analisis.pi_varianal_id = proveedor_variable_analisis.pfi_provarana_variable " +
                           " join tipo_analisis on proveedor_variable_analisis.fi_provarana_tipoanal= tipo_analisis.pi_tipoanal_id "
                + " where proveedor.s_proveedor_nombre =? and variable_analisis.pi_varianal_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, proveedor);
            q.setParameter(2, parametro);
            return q.getSingleResult().toString();
        } finally {
            em.close();
        }
    }
        
        
        public String consultarTipoAnalisisLaboratorioAuxiliar(int parametro, String reporte, int idMuestreo) {
        EntityManager em = getEntityManager();
            System.out.println("parametro " + parametro);
            System.out.println("reporte "+ reporte);
            System.out.println("idMuestreo "+ idMuestreo);
        String consulta =  " select distinct tipo_analisis.s_tipoanal_nomenclatura "
                    + "	from muestra_analisis "
                    + "	join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                    + "    join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                    + "    join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                    + "    join proveedor on proveedor.s_proveedor_nombre= muestra_analisis.fs_muestraanal_proveedor "
                    + "    join proveedor_variable_analisis on (proveedor_variable_analisis.pfs_provarana_proveedor= proveedor.ps_proveedor_nit "
                    + "    and proveedor_variable_analisis.pfi_provarana_variable=muestra_analisis.fi_muestraanal_variable) "
                    + "    join tipo_analisis on tipo_analisis.pi_tipoanal_id= proveedor_variable_analisis.fi_provarana_tipoanal "
                    + "    where reporte.pi_reporte_id= ? "
                    + "    and muestra_analisis.fi_muestraanal_variable=? "
                    + "    and muestreo.pi_muestreo_id=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            q.setParameter(2, parametro);
            q.setParameter(3, idMuestreo);
            
            return q.getSingleResult().toString();
        } finally {
            em.close();
        }
    }
    
     public List<Object> encontrarProveedor() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT distinct (proveedor.s_proveedor_nombre) FROM proveedor_variable_analisis " +
                          "join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarParametroProveedor(String filtros, List<Object> argumentos) {
        EntityManager em = getEntityManager();     
         String consulta = "Select matriz_analisis.s_matranal_descripcion, "
                 + " variable_analisis.s_varianal_descripcion"
                 + " from variable_analisis"
                 + " join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz"
                 + " WHERE ";
        consulta += filtros;
        consulta += " order by variable_analisis.s_varianal_descripcion asc";
         try {  
            Query q = em.createNativeQuery(consulta);
            System.out.println("query"+ q.toString());
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarVariableProveedor(VariableAnalisis variableNueva, Proveedor proveedorNuevo, VariableAnalisis variableViejo, Proveedor proveedorViejo) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE proveedor_variable_analisis "
                + "SET pfi_provarana_variable=?, fs_provarana_usuacrea= ?,"
                + " d_provarana_ultimodi= now() ,"
                + " pfs_provarana_proveedor=? "
                + "WHERE pfi_provarana_variable= ? and pfs_provarana_proveedor=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, variableNueva.getPiVarianalId());
        insercion.setParameter(2, variableNueva.getFsVarianalUsuacrea().getPsUsuarioCodigo());
        insercion.setParameter(3, proveedorNuevo.getPsProveedorNit());
        insercion.setParameter(4, variableViejo.getPiVarianalId());
        insercion.setParameter(5, proveedorViejo.getPsProveedorNit());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
     public void actualizarVariableProveedor(ProveedorVariableAnalisis proveedorVariableAnalisis) {
        EntityManager em = getEntityManager();
         System.out.println("unidad que llega"+proveedorVariableAnalisis.getUnidad());
        String insert = "UPDATE proveedor_variable_analisis "
                + "SET s_provarana_limicuan=?, s_provarana_metodo= ?,"
                + " db_provarana_costo=? ,"
                + " fi_provarana_tipoanal=?,"
                + " fs_provarana_usuultmod=?, d_provarana_ultimodi= now() "
                + "WHERE pfi_provarana_variable= ? and pfs_provarana_proveedor=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, proveedorVariableAnalisis.getSProvaranaLimicuan());
        insercion.setParameter(2, proveedorVariableAnalisis.getSProvaranaMetodo());
        insercion.setParameter(3, proveedorVariableAnalisis.getDbProvaranaCosto());
        insercion.setParameter(4, proveedorVariableAnalisis.getFiProvaranaTipoanal().getPiTipoanalId());
        insercion.setParameter(5, proveedorVariableAnalisis.getFsProvaranaUsuultmod().getPsUsuarioCodigo());
        insercion.setParameter(6, proveedorVariableAnalisis.getVariableAnalisis().getPiVarianalId());
        insercion.setParameter(7, proveedorVariableAnalisis.getProveedor().getPsProveedorNit());
        insercion.executeUpdate();
        em.getTransaction().commit();    
    }
     
     public void actualizarVariableUnidad(ProveedorVariableAnalisis proveedorVariableAnalisis) {
        EntityManager em = getEntityManager();
         System.out.println("unidad que llega"+proveedorVariableAnalisis.getUnidad());
         String insert = "UPDATE proveedor_variable_analisis "
                + "SET unidad=?, "
                + " fs_provarana_usuultmod=?, d_provarana_ultimodi= now() "
                + "WHERE pfi_provarana_variable= ? and pfs_provarana_proveedor=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, proveedorVariableAnalisis.getUnidad());
        insercion.setParameter(2, proveedorVariableAnalisis.getFsProvaranaUsuultmod().getPsUsuarioCodigo());
        insercion.setParameter(3, proveedorVariableAnalisis.getVariableAnalisis().getPiVarianalId());
        insercion.setParameter(4, proveedorVariableAnalisis.getProveedor().getPsProveedorNit());
        insercion.executeUpdate();
        em.getTransaction().commit();    
    }


    public List<ProveedorVariableAnalisis> encontrarVariableAnalisisProveedor(VariableAnalisis variable, Proveedor proveedor) {
        
        EntityManager em = getEntityManager();
         String consulta = "SELECT * FROM proveedor_variable_analisis " +
                          "where pfi_provarana_variable=? "
                         +" and pfs_provarana_proveedor=?";
        try {
            Query q = em.createNativeQuery(consulta,ProveedorVariableAnalisis.class);
            q.setParameter(1, variable.getPiVarianalId());
            q.setParameter(2, proveedor.getPsProveedorNit());
            return q.getResultList();
        } finally {
            em.close();
        }
    
    }

   
    public void crearProveedorVariableAnalisis(ProveedorVariableAnalisis proveedorVariableAnalisis) {
        EntityManager em = getEntityManager();
        try {     
         
             String insert = "INSERT INTO proveedor_variable_analisis (pfs_provarana_proveedor, "
                     + "pfi_provarana_variable, s_provarana_metodo, "
                     + "s_provarana_limicuan, fi_provarana_tipoanal, "
                     + "db_provarana_costo, unidad, fs_provarana_usuacrea, d_provarana_creacion) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, proveedorVariableAnalisis.getProveedorVariableAnalisisPK().getPfsProvaranaProveedor());
             insercion.setParameter(2, proveedorVariableAnalisis.getProveedorVariableAnalisisPK().getPfiProvaranaVariable());
             insercion.setParameter(3, proveedorVariableAnalisis.getSProvaranaMetodo());
             insercion.setParameter(4, proveedorVariableAnalisis.getSProvaranaLimicuan());
             insercion.setParameter(5, proveedorVariableAnalisis.getFiProvaranaTipoanal().getPiTipoanalId());
             insercion.setParameter(6, proveedorVariableAnalisis.getDbProvaranaCosto());
             insercion.setParameter(7, proveedorVariableAnalisis.getUnidad());
             insercion.setParameter(8, proveedorVariableAnalisis.getFsProvaranaUsuacrea().getPsUsuarioCodigo());
             insercion.executeUpdate();
             em.getTransaction().commit();        
        }catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    }

    public void eliminarProveedorVariableAnalisis(VariableAnalisis variableAnalisis) {
      EntityManager em = getEntityManager();
        try {     
             String delete = "delete from proveedor_variable_analisis"
                     + " where pfi_provarana_variable = ? ";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(delete);
             insercion.setParameter(1, variableAnalisis.getPiVarianalId());
             insercion.executeUpdate();
             em.getTransaction().commit();        
        }catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    
    }

    public void eliminarProveedorVariableAnalisis(ProveedorVariableAnalisis proveedorVariableAnalisis) {
      EntityManager em = getEntityManager();
        try {     
             String delete = "delete from proveedor_variable_analisis"
                     + " where pfi_provarana_variable = ? "
                     + " and pfs_provarana_proveedor = ?";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(delete);
             insercion.setParameter(1, proveedorVariableAnalisis.getVariableAnalisis().getPiVarianalId());
             insercion.setParameter(2, proveedorVariableAnalisis.getProveedor().getPsProveedorNit());
             insercion.executeUpdate();
             em.getTransaction().commit();        
        }catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    
    }
    
    public List<Object> encontrarVariableAnalisis(String nitProveedor) {
         EntityManager em = getEntityManager();
         try {
            em.getTransaction().begin();
            Query detalleSolicitud = em.createNativeQuery("select count(pfs_provarana_proveedor) " +
                                    "from proveedor_variable_analisis where pfs_provarana_proveedor=? ");
            detalleSolicitud.setParameter(1, nitProveedor);
            return detalleSolicitud.getResultList();
        } 
        finally {
            em.close();
        }
    
    }

    public List<Object> encontrarNitLaboratorio() {
        EntityManager em = getEntityManager();
        String consulta = "select distinct proveedor.ps_proveedor_nit "
                + "from proveedor ";
       try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarNombreLaboratorio() {
         EntityManager em = getEntityManager();
        String consulta = "select distinct proveedor.s_proveedor_nombre "
                + "from proveedor ";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

    public List<Object> encontrarProveedorVariableAnalisis(String filtros, List<Object> argumentos) {
        EntityManager em = getEntityManager();     
         String consulta = "select s_matranal_descripcion, "
                 + "variable_analisis.s_varianal_descripcion, proveedor_variable_analisis.pfs_provarana_proveedor, "
                 + "proveedor.s_proveedor_nombre, proveedor_variable_analisis.s_provarana_metodo, "
                 + "proveedor_variable_analisis.s_provarana_limicuan, tipo_analisis.s_tipoanal_descripcion , "
                 + "proveedor_variable_analisis.db_provarana_costo, proveedor_variable_analisis.unidad "
                 + "from matriz_analisis "
                 + "join variable_analisis on variable_analisis.fi_varianal_matriz= matriz_analisis.pi_matranal_id "
                 + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= variable_analisis.pi_varianal_id "
                 + "join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor "
                 + "join tipo_analisis on tipo_analisis.pi_tipoanal_id= proveedor_variable_analisis.fi_provarana_tipoanal "
                 + "WHERE ";
        consulta+=filtros;
        try {  
            Query q = em.createNativeQuery(consulta);
            System.out.println("query"+ q.toString());
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }    
    }
    
    public List<Object> encontrarLimiteCuantificacion(int idVariableAnalisis){
         EntityManager em = getEntityManager();
        String consulta = "SELECT s_provarana_limicuan"
                + " from proveedor_variable_analisis"
                + " where pfi_provarana_variable=?"
                + " and pfs_provarana_proveedor='9002414398'";
       try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        } 
    }

   

}
