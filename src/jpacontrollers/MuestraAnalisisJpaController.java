/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AnalistaParametro;
import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.VariableAnalisis;
import entidades.Muestra;
import entidades.MuestraAnalisis;
import entidades.Muestreo;
import entidades.Valor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class MuestraAnalisisJpaController implements Serializable {

    public MuestraAnalisisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraAnalisis muestraAnalisis) {
        if (muestraAnalisis.getValorList() == null) {
            muestraAnalisis.setValorList(new ArrayList<Valor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisis idAnalisis = muestraAnalisis.getIdAnalisis();
            if (idAnalisis != null) {
                idAnalisis = em.getReference(idAnalisis.getClass(), idAnalisis.getPiVarianalId());
                muestraAnalisis.setIdAnalisis(idAnalisis);
            }
            Muestra idMuestra = muestraAnalisis.getIdMuestra();
            if (idMuestra != null) {
                idMuestra = em.getReference(idMuestra.getClass(), idMuestra.getIdmuestra());
                muestraAnalisis.setIdMuestra(idMuestra);
            }
            List<Valor> attachedValorList = new ArrayList<Valor>();
            for (Valor valorListValorToAttach : muestraAnalisis.getValorList()) {
//                valorListValorToAttach = em.getReference(valorListValorToAttach.getClass(), valorListValorToAttach.getFiValorValorpadre());
                attachedValorList.add(valorListValorToAttach);
            }
            muestraAnalisis.setValorList(attachedValorList);
            em.persist(muestraAnalisis);
          /*  if (idAnalisis != null) {
                idAnalisis.getMuestraAnalisisList().add(muestraAnalisis);
                idAnalisis = em.merge(idAnalisis);
            }*/
            if (idMuestra != null) {
                idMuestra.getMuestraAnalisisList().add(muestraAnalisis);
                idMuestra = em.merge(idMuestra);
            }
         /*   for (Valor valorListValor : muestraAnalisis.getValorList()) {
                MuestraAnalisis oldIdParametroOfValorListValor = valorListValor.getIdParametro();
                valorListValor.setIdParametro(muestraAnalisis);
                valorListValor = em.merge(valorListValor);
                if (oldIdParametroOfValorListValor != null) {
                    oldIdParametroOfValorListValor.getValorList().remove(valorListValor);
                    oldIdParametroOfValorListValor = em.merge(oldIdParametroOfValorListValor);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraAnalisis muestraAnalisis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraAnalisis persistentMuestraAnalisis = em.find(MuestraAnalisis.class, muestraAnalisis.getIdmuestraAnalisis());
            VariableAnalisis idAnalisisOld = persistentMuestraAnalisis.getIdAnalisis();
            VariableAnalisis idAnalisisNew = muestraAnalisis.getIdAnalisis();
            Muestra idMuestraOld = persistentMuestraAnalisis.getIdMuestra();
            Muestra idMuestraNew = muestraAnalisis.getIdMuestra();
            List<Valor> valorListOld = persistentMuestraAnalisis.getValorList();
            List<Valor> valorListNew = muestraAnalisis.getValorList();
            if (idAnalisisNew != null) {
                idAnalisisNew = em.getReference(idAnalisisNew.getClass(), idAnalisisNew.getPiVarianalId());
                muestraAnalisis.setIdAnalisis(idAnalisisNew);
            }
            if (idMuestraNew != null) {
                idMuestraNew = em.getReference(idMuestraNew.getClass(), idMuestraNew.getIdmuestra());
                muestraAnalisis.setIdMuestra(idMuestraNew);
            }
            List<Valor> attachedValorListNew = new ArrayList<Valor>();
            for (Valor valorListNewValorToAttach : valorListNew) {
               // valorListNewValorToAttach = em.getReference(valorListNewValorToAttach.getClass(), valorListNewValorToAttach.getIdValor());
                attachedValorListNew.add(valorListNewValorToAttach);
            }
            valorListNew = attachedValorListNew;
            muestraAnalisis.setValorList(valorListNew);
            muestraAnalisis = em.merge(muestraAnalisis);
            /*if (idAnalisisOld != null && !idAnalisisOld.equals(idAnalisisNew)) {
                idAnalisisOld.getMuestraAnalisisList().remove(muestraAnalisis);
                idAnalisisOld = em.merge(idAnalisisOld);
            }*/
           /* if (idAnalisisNew != null && !idAnalisisNew.equals(idAnalisisOld)) {
                idAnalisisNew.getMuestraAnalisisList().add(muestraAnalisis);
                idAnalisisNew = em.merge(idAnalisisNew);
            }*/
            if (idMuestraOld != null && !idMuestraOld.equals(idMuestraNew)) {
                idMuestraOld.getMuestraAnalisisList().remove(muestraAnalisis);
                idMuestraOld = em.merge(idMuestraOld);
            }
            if (idMuestraNew != null && !idMuestraNew.equals(idMuestraOld)) {
                idMuestraNew.getMuestraAnalisisList().add(muestraAnalisis);
                idMuestraNew = em.merge(idMuestraNew);
            }
            /*for (Valor valorListOldValor : valorListOld) {
                if (!valorListNew.contains(valorListOldValor)) {
                    valorListOldValor.setIdParametro(null);
                    valorListOldValor = em.merge(valorListOldValor);
                }
            }
            for (Valor valorListNewValor : valorListNew) {
                if (!valorListOld.contains(valorListNewValor)) {
                    MuestraAnalisis oldIdParametroOfValorListNewValor = valorListNewValor.getIdParametro();
                    valorListNewValor.setIdParametro(muestraAnalisis);
                    valorListNewValor = em.merge(valorListNewValor);
                    if (oldIdParametroOfValorListNewValor != null && !oldIdParametroOfValorListNewValor.equals(muestraAnalisis)) {
                        oldIdParametroOfValorListNewValor.getValorList().remove(valorListNewValor);
                        oldIdParametroOfValorListNewValor = em.merge(oldIdParametroOfValorListNewValor);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraAnalisis.getIdmuestraAnalisis();
                if (findMuestraAnalisis(id) == null) {
                    throw new NonexistentEntityException("The muestraAnalisis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraAnalisis muestraAnalisis;
            try {
                muestraAnalisis = em.getReference(MuestraAnalisis.class, id);
                muestraAnalisis.getIdmuestraAnalisis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraAnalisis with id " + id + " no longer exists.", enfe);
            }
            VariableAnalisis idAnalisis = muestraAnalisis.getIdAnalisis();
            /*if (idAnalisis != null) {
                idAnalisis.getMuestraAnalisisList().remove(muestraAnalisis);
                idAnalisis = em.merge(idAnalisis);
            }*/
            Muestra idMuestra = muestraAnalisis.getIdMuestra();
            if (idMuestra != null) {
                idMuestra.getMuestraAnalisisList().remove(muestraAnalisis);
                idMuestra = em.merge(idMuestra);
            }
            List<Valor> valorList = muestraAnalisis.getValorList();
           /* for (Valor valorListValor : valorList) {
                valorListValor.setIdParametro(null);
                valorListValor = em.merge(valorListValor);
            }*/
            em.remove(muestraAnalisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraAnalisis> findMuestraAnalisisEntities() {
        return findMuestraAnalisisEntities(true, -1, -1);
    }

    public List<MuestraAnalisis> findMuestraAnalisisEntities(int maxResults, int firstResult) {
        return findMuestraAnalisisEntities(false, maxResults, firstResult);
    }

    private List<MuestraAnalisis> findMuestraAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraAnalisis.class));
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

    public MuestraAnalisis findMuestraAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraAnalisis> rt = cq.from(MuestraAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public boolean guardarMuestraAnalisis(int idMuestra, int idVariable, String realizadoPor, 
                                          String proveedor, String usuarioCreador, int codigoAnalista) {
         EntityManager em = getEntityManager();
         System.out.println("muestra "+ idMuestra);
          System.out.println("variable "+ idVariable);
           System.out.println("realizado  "+ realizadoPor);
          System.out.println("proveedor "+ proveedor);
            System.out.println("usuarioCreador  "+ usuarioCreador);
          System.out.println("codigo Analista "+ codigoAnalista);
         String insert = null;
         Query insercion = null;
         em.getTransaction().begin();
         try {
             //Validar si llega la matriz de analisis con un analista
             //if (muestraAnalisis.getAnalista() == null) {
             if (codigoAnalista==0) {
                 insert = "INSERT INTO muestra_analisis (fi_muestraanal_muestra, fi_muestraanal_variable,"
                         + " s_muestraanal_realizado, fs_muestraanal_proveedor, fs_muestraanal_usuacrea, "
                         + " d_muestraanal_fechingr) "
                         + "VALUES (?, ?, ?, ? , ?, now())";
                 insercion = em.createNativeQuery(insert);
                 insercion.setParameter(1, idMuestra);
                 insercion.setParameter(2, idVariable);
                 insercion.setParameter(3, realizadoPor);
                 insercion.setParameter(4, proveedor);
                 insercion.setParameter(5, usuarioCreador);
             } 
             else {
                 System.out.println("entramos aca !!!");
                 insert = "INSERT INTO muestra_analisis (fi_muestraanal_muestra, fi_muestraanal_variable,"
                         + " s_muestraanal_realizado, fs_muestraanal_proveedor, fs_muestraanal_usuacrea, "
                         + " fi_muestraanal_analparam, d_muestraanal_fechingr ) "
                         + "VALUES (?, ?, ?, ? , ?, ? , now() )";
                 insercion = em.createNativeQuery(insert);
                 insercion.setParameter(1, idMuestra);
                 insercion.setParameter(2, idVariable);
                 insercion.setParameter(3, realizadoPor);
                 insercion.setParameter(4, proveedor);
                 insercion.setParameter(5, usuarioCreador);
                 insercion.setParameter(6, codigoAnalista);
             }
             
             insercion.executeUpdate();
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {

             throw ex;

         } finally {
             em.close();
         }
    }

    
    //Devolver la muestra analisis pasando la variable analisis
    public List<Object> encontrarMuestraAnalisis(VariableAnalisis variableAnalisis, String subcontratado) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis where fi_muestraanal_variable = ?"
                + " and fs_muestraanal_proveedor = ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            q.setParameter(2, subcontratado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<MuestraAnalisis> getMuestraAnalisisPorVariable(VariableAnalisis variableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM muestra_analisis where fi_muestraanal_variable = ?";
        try {
            Query q = em.createNativeQuery(consulta, MuestraAnalisis.class);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<MuestraAnalisis> encontrarMuestraAnalisisPorIdMuestraAnalisis(int idMuestraAnalisis){
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM muestra_analisis where pi_muestraanal_id = ?";
        try {
            Query q = em.createNativeQuery(consulta, MuestraAnalisis.class);
            q.setParameter(1, idMuestraAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<Object> getMuestraAnalisisPorVariableAprobados(VariableAnalisis variableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis "
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                 + "join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                 + "where fi_muestraanal_variable =? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
   

    public List<MuestraAnalisis> getMuestraAnalisisIdMuestra(int idMuestra, String descripcion) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis"
                + " join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " where fi_muestraanal_muestra= ? and variable_analisis.s_varianal_descripcion= ?";
        try {
            Query q = em.createNativeQuery(consulta, MuestraAnalisis.class);
            q.setParameter(1, idMuestra);
            q.setParameter(2, descripcion);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarMuestraAnalisis(MuestraAnalisis muestra) {
        EntityManager em = getEntityManager();
        String insert = "DELETE FROM muestra_analisis WHERE pi_muestraanal_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, muestra.getIdmuestraAnalisis());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

     public void eliminarMuestraAnalisisPorIdMuestraAnalisis(List<Object> listaMuestraAnalisis) {
        EntityManager em = getEntityManager();
         for (int i = 0; i < listaMuestraAnalisis.size(); i++) {
             Integer idMuestraAnalisis = (Integer) listaMuestraAnalisis.get(i);
             String insert = "DELETE FROM muestra_analisis WHERE pi_muestraanal_id= ?";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, idMuestraAnalisis);
             insercion.executeUpdate();
             em.getTransaction().commit();
         }
    }  
    
    public List<MuestraAnalisis> encontrarMuestraAnalisis(int idMuestra, int variableAnalisis, String subcontratado) {
        EntityManager em = getEntityManager();
       String consulta = "SELECT * FROM muestra_analisis"
                + " where fi_muestraanal_muestra=? and fi_muestraanal_variable=? and fs_muestraanal_proveedor=? ";
        try {
            Query q = em.createNativeQuery(consulta, MuestraAnalisis.class);
            q.setParameter(1, idMuestra);
            q.setParameter(2, variableAnalisis);
            q.setParameter(3, subcontratado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<Object> getIdMuestraAnalisis(VariableAnalisis variableAnalisis, String subcontratado) {
     EntityManager em = getEntityManager();
          String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis "
                 + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                 + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                 + "join reporte on reporte.pi_reporte_id=muestra.fs_muestra_reporte "
                 + "where fi_muestraanal_variable=? and fs_muestraanal_proveedor=? "
                 + "and reporte.c_reporte_estado!='A'";
        try {
            Query q = em.createNativeQuery(consulta);
             q.setParameter(1, variableAnalisis.getPiVarianalId());
             q.setParameter(2, subcontratado);
            return q.getResultList();
        } finally {
            em.close();
      }
    }

    public void editarMuestra( MuestraAnalisis muestraAnalisisNueva) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE muestra_analisis SET fi_muestraanal_variable= ? , "
                + "fs_muestraanal_proveedor = ?, d_muestraanal_fechmodi = ? ,"
                + " fs_muestraanal_usuamodi= ? , s_muestraanal_observaciones = ?, "
                + " fi_muestraanal_analparam= ?"
                + " WHERE pi_muestraanal_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, muestraAnalisisNueva.getIdAnalisis().getPiVarianalId());
        insercion.setParameter(2, muestraAnalisisNueva.getFsMuestraanalProveedor().getSProveedorNombre());
        insercion.setParameter(3, new Date());
        insercion.setParameter(4, muestraAnalisisNueva.getUsuarioModificador().getPsUsuarioCodigo());
        insercion.setParameter(5, muestraAnalisisNueva.getObservaciones());
        insercion.setParameter(6, muestraAnalisisNueva.getAnalista().getIdanalistaParametro());
        insercion.setParameter(7, muestraAnalisisNueva.getIdmuestraAnalisis());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    
      public List<Object> encontrarMuestras(String filtros, List<Object> argumentos) {
     EntityManager em = getEntityManager();
         String consulta = "SELECT DISTINCT muestra.pi_muestra_id, variable_analisis.s_varianal_descripcion," 
                 +" reporte.pi_reporte_id, proyecto.s_proyecto_nombre," 
                 +" (select ps_usuario_codigo from s_usuario" 
                 +" where analista_parametro.fs_analparam_usutitul=s_usuario.ps_usuario_codigo"
                 +" ) as 'Usuario Titular', " 
                 +" (select ps_usuario_codigo from s_usuario" 
                 +" where analista_parametro.fs_analparam_ususupl=s_usuario.ps_usuario_codigo " 
                 +" ) as 'Usuario Titular', valor.s_valor_valor, muestra.s_muestra_descripcion," 
                 +" muestreo.s_muestreo_fechinic, muestra_analisis.d_muestraanal_fechingr   " 
                 +" FROM muestra_analisis" 
                 +" join muestra on muestra.pi_muestra_id = muestra_analisis.fi_muestraanal_muestra " 
                 +" join muestreo on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id " 
                 +" join reporte on reporte.fi_reporte_muestreo = muestreo.pi_muestreo_id " 
                 +" join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable " 
                 +" join proyecto on proyecto.pi_proyecto_id = reporte.fi_reporte_proyecto " 
                 +" join cliente on (cliente.ps_cliente_nit = reporte.fi_reporte_cliente"
                 + " or cliente.ps_cliente_nit= reporte.fi_reporte_facturacion) " 
                 +" join analista_parametro on muestra_analisis.fi_muestraanal_analparam=analista_parametro.pi_analparam_id "
                 +" join s_usuario on analista_parametro.fs_analparam_ususupl= s_usuario.ps_usuario_codigo" 
                 +" join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id" 
                 +" WHERE ";
        consulta+=filtros;
        consulta+=" order by muestra.pi_muestra_id";
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

    public List<Integer> encontrarIdMuestraAnalisis(int piVarianalId, int idmuestra) {
         EntityManager em = getEntityManager();
          String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis" +
                        " where fi_muestraanal_muestra=? and fi_muestraanal_variable=? ";
        try {
            Query q = em.createNativeQuery(consulta);
             q.setParameter(1, idmuestra);
             q.setParameter(2, piVarianalId);    
            return q.getResultList();
        } finally {
            em.close();
        }
    
    }
    
    public List<Integer> encontrarIdMuestraAnalisisPorMuestra(int idMuestra, String parametro) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT pi_muestraanal_id FROM muestra_analisis"
                + " join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " where fi_muestraanal_muestra=?"
                + " and variable_analisis.s_varianal_descripcion = ? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
            q.setParameter(2, parametro);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> cargarListaMuestras(Muestreo muestreoActual) {
        EntityManager em = getEntityManager();
        String consulta = "Select muestra.pi_muestra_id, tipo_muestra.s_tipomuestra_nombre, muestra.s_muestra_observaciones," 
                +" muestra.d_muestra_fechtomamuest, muestra.fi_muestra_campo, muestra.s_muestra_descripcion, valor.s_valor_nombre, muestra_analisis.fs_muestraanal_proveedor,"
                +" muestra.fi_muestra_matriz, "
                +" reporte.pi_reporte_id, reporte.s_reporte_version, "
                +" tipo_muestreo.s_tipomuestreo_nombre" 
                +" from muestra" 
                +" join muestreo on muestreo.pi_muestreo_id=muestra.fi_muestra_muestreo" 
                +" join tipo_muestra on tipo_muestra.pi_tipomuestra_id= muestra.fi_muestra_tipomuest"  
                +" join muestra_analisis on muestra_analisis.fi_muestraanal_muestra=muestra.pi_muestra_id" 
                +" join valor on valor.fi_valor_muestraanal=muestra_analisis.pi_muestraanal_id"
                +" join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte"
                +" join tipo_muestreo on tipo_muestreo.pi_tipomuestreo_id= muestra.fi_muestra_tipomuestreo"
                +" where muestreo.pi_muestreo_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1,muestreoActual.getIdMuestreo());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarCantidadParametrosPorMuestra(Integer idMuestra) {
         EntityManager em = getEntityManager();
        String consulta = "select count(pi_muestraanal_id)"
                + " from muestra_analisis"
                + " where fi_muestraanal_muestra=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestra);
             return q.getResultList();
        } finally {
            em.close();
        }
    }
    
       public List<MuestraAnalisis> encontrarMuestraAnalisis(Integer idMuestra){
            EntityManager em = getEntityManager();
           String consulta = "select *"
                   + " from muestra_analisis"
                   + " where fi_muestraanal_muestra=?";
           try {
               Query q = em.createNativeQuery(consulta, MuestraAnalisis.class);
               q.setParameter(1, idMuestra);
               return q.getResultList();
           } finally {
               em.close();
           }
       }
       
       
       public List<Object> encontrarMuestraAnalisisPorId(int idMuestraAnalisis){
          EntityManager em = getEntityManager();
           String consulta = "select proveedor.s_proveedor_nombre"
                   + " from muestra_analisis"
                   + " join proveedor on proveedor.s_proveedor_nombre= muestra_analisis.fs_muestraanal_proveedor"
                   + " where pi_muestraanal_id=?";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, idMuestraAnalisis);
               return q.getResultList();
           } finally {
               em.close();
           }
       }
       
       public int existeMuestra(int numeroMuestra, String nombreParametro){
          EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                   + "from muestra_analisis "
                   + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                   + "where muestra_analisis.fi_muestraanal_muestra=? "
                   + "and valor.s_valor_nombre=? "
                   + "and valor.s_valor_valor is null ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, numeroMuestra);
                q.setParameter(2, nombreParametro);
               return q.getResultList().size();
           } finally {
               em.close();
           }
       }

    public List<Object> encontrarMuestrasPendientes(Integer piVarianalId) {
         EntityManager em = getEntityManager();
           String consulta = "select muestra.pi_muestra_id, valor.s_valor_nombre,  muestreo.s_muestreo_fechinic, tipo_muestra.s_tipomuestra_nombre "
                + "from muestra_analisis "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo "
                + "join tipo_muestra on tipo_muestra.pi_tipomuestra_id= muestra.fi_muestra_tipomuest "
                + "where fi_muestraanal_variable=? "
                + "and valor.s_valor_valor is null ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, piVarianalId);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }

    public int esMuestra(String numeroMuestra) {
    EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id  "
                + "from muestra_analisis "
                + "where fi_muestraanal_muestra=? ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, numeroMuestra);
               return q.getResultList().size();
           } finally {
               em.close();
           }  
    }

    public int esMuestraPermitida(String numeroMuestra) {
      EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "join valor on valor.fi_valor_muestraanal=muestra_analisis.pi_muestraanal_id "
                + "where fi_muestraanal_muestra=? "
                + "and valor.s_valor_valor is null";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, numeroMuestra);
               return q.getResultList().size();
           } finally {
               em.close();
           }  
    }

    public List<Object> encontrarCamposRevisionDatos(Integer piVarianalId) {
     EntityManager em = getEntityManager();
           String consulta = "select muestra.pi_muestra_id, valor.s_valor_nombre, "
                + "valor.s_valor_valorreal, reporte.pi_reporte_id, "
                + "valor.d_valor_fechmodi, valor.fs_valor_usuamodi "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join reporte on pi_reporte_id= muestra.fs_muestra_reporte "
                + "where (reporte.c_reporte_estado='O' or reporte.c_reporte_estado='P') and "
                + " muestra_analisis.fi_muestraanal_variable= ? ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, piVarianalId);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }

    public int contieneFitoplancton(int muestra) {
        EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=216";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }
    
     public int contieneZooplancton(int muestra) {
        EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=215";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }

    public List<Integer> encontrarParametrosPorReporte(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
       EntityManager em = getEntityManager();
           String consulta = "select distinct fi_fotohidro_varianal "
                + "FROM foto_hidrobiologia"
                + " join muestra on muestra.fs_muestra_reporte= foto_hidrobiologia.fs_fotohidro_reporte "
                + "where fs_fotohidro_reporte=? "
                + " and muestra.pi_muestra_id between ? and ? ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, piReporteId);
               q.setParameter(2, idMuestraInicial);
                q.setParameter(3, idMuestraFinal);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }

     public List<Integer> encontrarParametrosPorReporte(String piReporteId, int idMuestreo) {
       EntityManager em = getEntityManager();
           String consulta = "select distinct(fi_muestraanal_variable) "
                 + "from muestra_analisis "
                 + "join muestra on muestra.pi_muestra_id=muestra_analisis.fi_muestraanal_muestra "
                 + "join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                 + "where reporte.pi_reporte_id=? "
                 + "and muestra.fi_muestra_muestreo=? ";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, piReporteId);
               q.setParameter(2, idMuestreo);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }
     
    public int contieneMacroInvertebradosBentonicos(int muestra) {
       EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=217";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }

    public int contienePerifiton(int muestra) {
         EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=214";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }

    public int contienePeces(int muestra) {
       EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=219";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }

    public int contieneMacrofitas(int muestra) {
     EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=218";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }
    
    public int contieneMercurio(int muestra) {
     EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=2165";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }
    
    public int contieneMetilMercurio(int muestra) {
     EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=2163";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }

    public int contieneIctioplancton(int muestra) {
     EntityManager em = getEntityManager();
           String consulta = "select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where muestra_analisis.fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=1023";
           try {
               Query q = em.createNativeQuery(consulta);
               q.setParameter(1, muestra);
               return q.getResultList().size();
           } finally {
               em.close();
           } 
    }
    
    
    public int encontrarIdMuestraAnalisisPorMuestra(int idMuestra, Integer piVarianalId, String sProveedorNombre) {
       EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select pi_muestraanal_id "
                + "from muestra_analisis "
                + "where fi_muestraanal_muestra=? "
                + "and fi_muestraanal_variable=? "
                + "and fs_muestraanal_proveedor=? ");
               q.setParameter(1, idMuestra);
               q.setParameter(2, piVarianalId);
               q.setParameter(3, sProveedorNombre);
               return (int) q.getSingleResult();
           } finally {
               em.close();
           } 
    }
    
    public String encontrarProveedorMuestraParametro(int idVariableAnalisis, String idReporte, int idMuestreo) {
        
        EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select distinct fs_muestraanal_proveedor"
                       + " from muestra_analisis"
                       + " join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra"
                       + " join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte"
                       + " where fi_muestraanal_variable=?"
                       + " and reporte.pi_reporte_id=?"
                       + " and muestra.fi_muestra_muestreo=?");
               q.setParameter(1, idVariableAnalisis);
               q.setParameter(2, idReporte);
                q.setParameter(3, idMuestreo);
               return (String) q.getSingleResult();
           } finally {
               em.close();
           } 
    }

    public int encontrarParametrosSubcontratados(String reportePrincipal) {
         EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select pi_muestraanal_id "
                       + "from muestra_analisis "
                       + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                       + "where muestra.fs_muestra_reporte=? "
                       + "and muestra_analisis.fs_muestraanal_proveedor!='CIMA'");
               q.setParameter(1, reportePrincipal);
               System.out.println("reporte "+ reportePrincipal);
               System.out.println("aqui viene " + q.getResultList().size());
               return  q.getResultList().size();
           } finally {
               em.close();
           } 
    }
    
    
    public List<Integer> encontrarVariableAnalisisPorReporte(String reporte) {
      EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select distinct fi_muestraanal_variable "
                       + "from muestra_analisis "
                       + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                       + "where muestra.fs_muestra_reporte=? ");
               q.setParameter(1, reporte);
               return q.getResultList();
           } finally {
               em.close();
           } 
    }

    public int encontrarMuestraInicial(Integer piVarianalId) {
      EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select min(muestra_analisis.fi_muestraanal_muestra) from muestra_analisis " +
                                            "where fi_muestraanal_variable=?");
               q.setParameter(1, piVarianalId);
               return (Integer) q.getSingleResult();
           } finally {
               em.close();
           } 
    }
    
     public int encontrarMuestraFinal(Integer piVarianalId) {
      EntityManager em = getEntityManager();
           try {
               em.getTransaction().begin();
               Query q = em.createNativeQuery("select max(muestra_analisis.fi_muestraanal_muestra) from muestra_analisis " +
                                            "where fi_muestraanal_variable=?");
               q.setParameter(1, piVarianalId);
               return (Integer) q.getSingleResult();
           } finally {
               em.close();
           } 
    }
    

    
}

   

   

