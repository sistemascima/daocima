// 
// Decompiled by Procyon v0.5.36
// 

package jpacontrollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import entidades.ProveedorVariableAnalisis;
import entidades.Reporte;
import entidades.SUsuario;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;
import java.util.Date;
import entidades.MatrizAnalisis;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Expression;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;
import javax.persistence.EntityNotFoundException;
import jpacontrollers.exceptions.NonexistentEntityException;
import entidades.MuestraAnalisis;
import entidades.TipoValor;
import java.util.List;
import java.util.ArrayList;
import entidades.Valor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

public class ValorJpaController implements Serializable
{
    private EntityManagerFactory emf;
    
    public ValorJpaController(final EntityManagerFactory emf) {
        this.emf = null;
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }
    
    public void create(final Valor valor) {
        if (valor.getValorList() == null) {
            valor.setValorList(new ArrayList<Valor>());
        }
        EntityManager em = null;
        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            TipoValor idTpValor = valor.getIdTpValor();
            if (idTpValor != null) {
                idTpValor = (TipoValor)em.getReference((Class)idTpValor.getClass(), (Object)idTpValor.getIdtipoValor());
                valor.setIdTpValor(idTpValor);
            }
            MuestraAnalisis idParametro = valor.getIdParametro();
            if (idParametro != null) {
                idParametro = (MuestraAnalisis)em.getReference((Class)idParametro.getClass(), (Object)idParametro.getIdmuestraAnalisis());
                valor.setIdParametro(idParametro);
            }
            Valor idValorPadre = valor.getIdValorPadre();
            if (idValorPadre != null) {
                idValorPadre = (Valor)em.getReference((Class)idValorPadre.getClass(), (Object)idValorPadre.getIdValor());
                valor.setIdValorPadre(idValorPadre);
            }
            final List<Valor> attachedValorList = new ArrayList<Valor>();
            for (Valor valorListValorToAttach : valor.getValorList()) {
                valorListValorToAttach = (Valor)em.getReference((Class)valorListValorToAttach.getClass(), (Object)valorListValorToAttach.getIdValor());
                attachedValorList.add(valorListValorToAttach);
            }
            valor.setValorList(attachedValorList);
            em.persist((Object)valor);
            if (idTpValor != null) {
                idTpValor.getValorList().add(valor);
                idTpValor = (TipoValor)em.merge((Object)idTpValor);
            }
            if (idParametro != null) {
                idParametro.getValorList().add(valor);
                idParametro = (MuestraAnalisis)em.merge((Object)idParametro);
            }
            if (idValorPadre != null) {
                idValorPadre.getValorList().add(valor);
                idValorPadre = (Valor)em.merge((Object)idValorPadre);
            }
            for (Valor valorListValor : valor.getValorList()) {
                Valor oldIdValorPadreOfValorListValor = valorListValor.getIdValorPadre();
                valorListValor.setIdValorPadre(valor);
                valorListValor = (Valor)em.merge((Object)valorListValor);
                if (oldIdValorPadreOfValorListValor != null) {
                    oldIdValorPadreOfValorListValor.getValorList().remove(valorListValor);
                    oldIdValorPadreOfValorListValor = (Valor)em.merge((Object)oldIdValorPadreOfValorListValor);
                }
            }
            em.getTransaction().commit();
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Valor valor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            final Valor persistentValor = (Valor)em.find((Class)Valor.class, (Object)valor.getIdValor());
            TipoValor idTpValorOld = persistentValor.getIdTpValor();
            TipoValor idTpValorNew = valor.getIdTpValor();
            MuestraAnalisis idParametroOld = persistentValor.getIdParametro();
            MuestraAnalisis idParametroNew = valor.getIdParametro();
            Valor idValorPadreOld = persistentValor.getIdValorPadre();
            Valor idValorPadreNew = valor.getIdValorPadre();
            final List<Valor> valorListOld = persistentValor.getValorList();
            List<Valor> valorListNew = valor.getValorList();
            if (idTpValorNew != null) {
                idTpValorNew = (TipoValor)em.getReference((Class)idTpValorNew.getClass(), (Object)idTpValorNew.getIdtipoValor());
                valor.setIdTpValor(idTpValorNew);
            }
            if (idParametroNew != null) {
                idParametroNew = (MuestraAnalisis)em.getReference((Class)idParametroNew.getClass(), (Object)idParametroNew.getIdmuestraAnalisis());
                valor.setIdParametro(idParametroNew);
            }
            if (idValorPadreNew != null) {
                idValorPadreNew = (Valor)em.getReference((Class)idValorPadreNew.getClass(), (Object)idValorPadreNew.getIdValor());
                valor.setIdValorPadre(idValorPadreNew);
            }
            final List<Valor> attachedValorListNew = new ArrayList<Valor>();
            for (Valor valorListNewValorToAttach : valorListNew) {
                valorListNewValorToAttach = (Valor)em.getReference((Class)valorListNewValorToAttach.getClass(), (Object)valorListNewValorToAttach.getIdValor());
                attachedValorListNew.add(valorListNewValorToAttach);
            }
            valorListNew = attachedValorListNew;
            valor.setValorList(valorListNew);
            valor = (Valor)em.merge((Object)valor);
            if (idTpValorOld != null && !idTpValorOld.equals(idTpValorNew)) {
                idTpValorOld.getValorList().remove(valor);
                idTpValorOld = (TipoValor)em.merge((Object)idTpValorOld);
            }
            if (idTpValorNew != null && !idTpValorNew.equals(idTpValorOld)) {
                idTpValorNew.getValorList().add(valor);
                idTpValorNew = (TipoValor)em.merge((Object)idTpValorNew);
            }
            if (idParametroOld != null && !idParametroOld.equals(idParametroNew)) {
                idParametroOld.getValorList().remove(valor);
                idParametroOld = (MuestraAnalisis)em.merge((Object)idParametroOld);
            }
            if (idParametroNew != null && !idParametroNew.equals(idParametroOld)) {
                idParametroNew.getValorList().add(valor);
                idParametroNew = (MuestraAnalisis)em.merge((Object)idParametroNew);
            }
            if (idValorPadreOld != null && !idValorPadreOld.equals(idValorPadreNew)) {
                idValorPadreOld.getValorList().remove(valor);
                idValorPadreOld = (Valor)em.merge((Object)idValorPadreOld);
            }
            if (idValorPadreNew != null && !idValorPadreNew.equals(idValorPadreOld)) {
                idValorPadreNew.getValorList().add(valor);
                idValorPadreNew = (Valor)em.merge((Object)idValorPadreNew);
            }
            for (Valor valorListOldValor : valorListOld) {
                if (!valorListNew.contains(valorListOldValor)) {
                    valorListOldValor.setIdValorPadre(null);
                    valorListOldValor = (Valor)em.merge((Object)valorListOldValor);
                }
            }
            for (Valor valorListNewValor : valorListNew) {
                if (!valorListOld.contains(valorListNewValor)) {
                    Valor oldIdValorPadreOfValorListNewValor = valorListNewValor.getIdValorPadre();
                    valorListNewValor.setIdValorPadre(valor);
                    valorListNewValor = (Valor)em.merge((Object)valorListNewValor);
                    if (oldIdValorPadreOfValorListNewValor == null || oldIdValorPadreOfValorListNewValor.equals(valor)) {
                        continue;
                    }
                    oldIdValorPadreOfValorListNewValor.getValorList().remove(valorListNewValor);
                    oldIdValorPadreOfValorListNewValor = (Valor)em.merge((Object)oldIdValorPadreOfValorListNewValor);
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception ex) {
            final String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                final Integer id = valor.getIdValor();
                if (this.findValor(id) == null) {
                    throw new NonexistentEntityException("The valor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void destroy(final Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            Valor valor;
            try {
                valor = (Valor)em.getReference((Class)Valor.class, (Object)id);
                valor.getIdValor();
            }
            catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valor with id " + id + " no longer exists.", (Throwable)enfe);
            }
            TipoValor idTpValor = valor.getIdTpValor();
            if (idTpValor != null) {
                idTpValor.getValorList().remove(valor);
                idTpValor = (TipoValor)em.merge((Object)idTpValor);
            }
            MuestraAnalisis idParametro = valor.getIdParametro();
            if (idParametro != null) {
                idParametro.getValorList().remove(valor);
                idParametro = (MuestraAnalisis)em.merge((Object)idParametro);
            }
            Valor idValorPadre = valor.getIdValorPadre();
            if (idValorPadre != null) {
                idValorPadre.getValorList().remove(valor);
                idValorPadre = (Valor)em.merge((Object)idValorPadre);
            }
            final List<Valor> valorList = valor.getValorList();
            for (Valor valorListValor : valorList) {
                valorListValor.setIdValorPadre(null);
                valorListValor = (Valor)em.merge((Object)valorListValor);
            }
            em.remove((Object)valor);
            em.getTransaction().commit();
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Valor> findValorEntities() {
        return this.findValorEntities(true, -1, -1);
    }
    
    public List<Valor> findValorEntities(final int maxResults, final int firstResult) {
        return this.findValorEntities(false, maxResults, firstResult);
    }
    
    private List<Valor> findValorEntities(final boolean all, final int maxResults, final int firstResult) {
        final EntityManager em = this.getEntityManager();
        try {
            final CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select((Selection)cq.from((Class)Valor.class));
            final Query q = (Query)em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public Valor findValor(final Integer id) {
        final EntityManager em = this.getEntityManager();
        try {
            return (Valor)em.find((Class)Valor.class, (Object)id);
        }
        finally {
            em.close();
        }
    }
    
    public int getValorCount() {
        final EntityManager em = this.getEntityManager();
        try {
            final CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            final Root<Valor> rt = (Root<Valor>)cq.from((Class)Valor.class);
            cq.select((Selection)em.getCriteriaBuilder().count((Expression)rt));
            final Query q = (Query)em.createQuery(cq);
            return ((Long)q.getSingleResult()).intValue();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarParametrosAgua() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=4 and s_valor_valor is null";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<String> encontrarParametrosAnalista(final String usuario, final int idMatriz) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT distinct variable_analisis.s_varianal_descripcion FROM valor  join muestra_analisis on valor.fi_valor_muestraanal = muestra_analisis.pi_muestraanal_id  join analista_parametro on muestra_analisis.fi_muestraanal_analparam= analista_parametro.pi_analparam_id join variable_analisis on variable_analisis.pi_varianal_id=muestra_analisis.fi_muestraanal_variable where (analista_parametro.fs_analparam_ususupl =? or analista_parametro.fs_analparam_usutitul =? or fs_analparam_usuterc=?) and  valor.s_valor_valor is null and variable_analisis.fi_varianal_matriz=?  order by s_varianal_descripcion";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)usuario);
            q.setParameter(2, (Object)usuario);
            q.setParameter(3, (Object)usuario);
            q.setParameter(4, (Object)idMatriz);
            return (List<String>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> findValorIdParametro(final int idmuestraAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=4 and fi_valor_muestraanal = ? and (s_valor_absorbancia is null or s_valor_masa is null)";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            q.setParameter(1, (Object)idmuestraAnalisis);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarParametrosAire(final MatrizAnalisis matriz) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=4 and (s_valor_absorbancia is null or s_valor_masa is null)";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarValor(final Valor valor) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=4 and s_valor_nombre= ? and fi_valor_muestraanal=? ";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            q.setParameter(1, (Object)valor.getNombre());
            q.setParameter(2, (Object)valor.getIdParametro().getIdmuestraAnalisis());
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> encontrarValorPorIdMuestraAnalisis(final int idMuestraAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT pi_valor_id FROM valor where fi_valor_tpvalor=4 and fi_valor_muestraanal= ?  ";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idMuestraAnalisis);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarMunicipio() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=3";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
     public List<Valor> encontrarMatricesMantenimiento() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=14";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
     
       public List<Valor> encontrarFuncionesMantenimiento() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=15";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarCiudad() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where fi_valor_tpvalor=2";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void editarValor(final Valor valor) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_absorbancia=?, s_valor_masa=? WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)valor.getAbsorbancia());
        insercion.setParameter(2, (Object)valor.getMasa());
        insercion.setParameter(3, (Object)valor.getIdValor());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<Valor> encontrarMunicipio(final String nombre) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where s_valor_nombre=? and fi_valor_tpvalor=3";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            q.setParameter(1, (Object)nombre);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarCiudad(final String nombre) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * FROM valor where s_valor_nombre=? and fi_valor_tpvalor=2";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            q.setParameter(1, (Object)nombre);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void actualizar(final String valor, final String usuarioModificador, final String valorReal, final int idValor) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_valor=? , fs_valor_usuamodi = ? , d_valor_fechregi = now(), s_valor_valorreal = ? WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)valor);
        insercion.setParameter(2, (Object)usuarioModificador);
        insercion.setParameter(3, (Object)valorReal);
        insercion.setParameter(4, (Object)idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public void actualizarValores(final List<String> listaValores, final List<String> listaValoresReal, final List<Integer> listaMuestra, final String usuarioModificacion, final int idVariableAnalisis, final List<Date> fechaIngreso) {
        System.out.println("viene aqui y actualiza");
        final EntityManager em = this.getEntityManager();
        for (int i = 0; i < listaValores.size(); ++i) {
            final StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_llenar_valores");
            em.getTransaction().begin();
            System.out.println("id muestra "+ (Object)listaMuestra.get(i));
            System.out.println("valores "+ (Object)listaValores.get(i));
            System.out.println("valorReal "+ (Object)listaValoresReal.get(i));
            System.out.println("usuaModificacion "+ (Object)usuarioModificacion);
            System.out.println("variableAnalisis "+ (Object)idVariableAnalisis);
            System.out.println("fechaValor "+ (Object)fechaIngreso.get(i));
            procedimientoAlmacenado.registerStoredProcedureParameter("id_muestra", (Class)Integer.TYPE, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("valor", (Class)String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("valorReal", (Class)String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("usuaModificacion", (Class)String.class, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("variableAnalisis", (Class)Integer.TYPE, ParameterMode.IN);
            procedimientoAlmacenado.registerStoredProcedureParameter("fechaValor", (Class)Date.class, ParameterMode.IN);
            procedimientoAlmacenado.setParameter("id_muestra", (Object)listaMuestra.get(i));
            procedimientoAlmacenado.setParameter("valor", (Object)listaValores.get(i));
            procedimientoAlmacenado.setParameter("valorReal", (Object)listaValoresReal.get(i));
            procedimientoAlmacenado.setParameter("usuaModificacion", (Object)usuarioModificacion);
            procedimientoAlmacenado.setParameter("variableAnalisis", (Object)idVariableAnalisis);
            procedimientoAlmacenado.setParameter("fechaValor", (Object)fechaIngreso.get(i));
            procedimientoAlmacenado.execute();
            em.getTransaction().commit();
            
        }
        em.close();
    }
    
    public int crearValorCalidad(final Valor valor, final String usuario) {
        final EntityManager em = this.getEntityManager();
        int idValor = 0;
        em.getTransaction().begin();
        final StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_llenar_tabla_controles");
        procedimientoAlmacenado.registerStoredProcedureParameter("s_valor_nombre", (Class)String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("s_valor_usua_modi", (Class)String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("id_Valor", (Class)Integer.TYPE, ParameterMode.OUT);
        procedimientoAlmacenado.setParameter("s_valor_nombre", (Object)valor.getNombre());
        procedimientoAlmacenado.setParameter("s_valor_usua_modi", (Object)usuario);
        procedimientoAlmacenado.execute();
        idValor = (int)procedimientoAlmacenado.getOutputParameterValue("id_Valor");
        em.getTransaction().commit();
        em.close();
        return idValor;
    }
    
    public void actualizarValorPorReporte(final Valor valor) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_valor=? , fs_valor_usuamodi = ? , d_valor_fechmodi = now(), s_valor_observaciones = ? WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)valor.getValor());
        insercion.setParameter(2, (Object)valor.getUsuario_modificador().getPsUsuarioCodigo());
        insercion.setParameter(3, (Object)valor.getObservaciones());
        insercion.setParameter(4, (Object)valor.getIdValor());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public void editarValorPorMuestraAnalisis(final String nuevoParametro, final Valor valor, final MuestraAnalisis muestraNueva) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_valor = null, s_valor_nombre=? , s_valor_observaciones = ? , d_valor_fechmodi = ?,  fs_valor_usuamodi = ?,    s_valor_limicuan = (select proveedor_variable_analisis.s_provarana_limicuan  from proveedor_variable_analisis  join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor  where pfi_provarana_variable=?  and proveedor.s_proveedor_nombre=? ), s_valor_metodo = (select proveedor_variable_analisis.s_provarana_metodo  from proveedor_variable_analisis join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor where pfi_provarana_variable=? and proveedor.s_proveedor_nombre=?), s_valor_unidad = (select proveedor_variable_analisis.unidad  from proveedor_variable_analisis join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor where pfi_provarana_variable=? and proveedor.s_proveedor_nombre=?) WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)nuevoParametro);
        insercion.setParameter(2, (Object)valor.getObservaciones());
        insercion.setParameter(3, (Object)new Date());
        insercion.setParameter(4, (Object)valor.getUsuario_modificador().getPsUsuarioCodigo());
        insercion.setParameter(5, (Object)muestraNueva.getIdAnalisis().getPiVarianalId());
        insercion.setParameter(6, (Object)muestraNueva.getFsMuestraanalProveedor().getPsProveedorNit());
        insercion.setParameter(7, (Object)muestraNueva.getIdAnalisis().getPiVarianalId());
        insercion.setParameter(8, (Object)muestraNueva.getFsMuestraanalProveedor().getPsProveedorNit());
        insercion.setParameter(9, (Object)muestraNueva.getIdAnalisis().getPiVarianalId());
        insercion.setParameter(10, (Object)muestraNueva.getFsMuestraanalProveedor().getPsProveedorNit());
        insercion.setParameter(11, (Object)valor.getIdValor());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<Object> encontrarValoresPorMuestra(final String idMuestra, final int idMatriz) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT muestra.pi_muestra_id, variable_analisis.s_varianal_descripcion, muestreo.s_muestreo_fechinic,proveedor_variable_analisis.s_provarana_metodo, proveedor_variable_analisis.s_provarana_limicuan,proveedor_variable_analisis.unidad, tipo_muestra.s_tipomuestra_nombre FROM valor join muestra_analisis on valor.fi_valor_muestraanal = muestra_analisis.pi_muestraanal_id join muestra on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable join analista_parametro on muestra_analisis.fi_muestraanal_analparam= analista_parametro.pi_analparam_id join proveedor on proveedor.s_proveedor_nombre= muestra_analisis.fs_muestraanal_proveedor join proveedor_variable_analisis on (proveedor_variable_analisis.pfs_provarana_proveedor=proveedor.ps_proveedor_nit and proveedor_variable_analisis.pfi_provarana_variable=variable_analisis.pi_varianal_id) join muestreo on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id join tipo_muestra on tipo_muestra.pi_tipomuestra_id= muestra.fi_muestra_tipomuest where (analista_parametro.fs_analparam_ususupl ='N.PENA' or analista_parametro.fs_analparam_usutitul ='N.PENA') and valor.s_valor_valor is null and muestra_analisis.fi_muestraanal_muestra=? and muestra.fi_muestra_matriz=?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idMuestra);
            q.setParameter(2, (Object)idMatriz);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> encontrarValorPorMuestraParametro(final Integer idMuestraAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT s_valor_valorreal FROM valor join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal where fi_valor_muestraanal = ? ";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idMuestraAnalisis);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> esUltimoValor(final String idReporte) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "SELECT * from reporte join muestreo on reporte.fi_reporte_muestreo= muestreo.pi_muestreo_id join muestra on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id where pi_reporte_id=? AND s_valor_valor is null ";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idReporte);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void devolverValor(final Valor valor, final String observacion) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_valor=null , s_valor_observaciones = ? , d_valor_fechmodi = ?, fs_valor_usuamodi = ?   WHERE fi_valor_muestraanal=? and s_valor_nombre=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)observacion);
        insercion.setParameter(2, (Object)new Date());
        insercion.setParameter(3, (Object)valor.getUsuario_modificador().getPsUsuarioCodigo());
        insercion.setParameter(4, (Object)valor.getIdParametro().getIdmuestraAnalisis());
        insercion.setParameter(5, (Object)valor.getNombre());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<Valor> traerDatosAuditoria(final String filtros, final List<Object> argumentos) {
        final EntityManager em = this.getEntityManager();
        String consulta = "Select pi_muestra_id,pi_muestreo_id as 'Cadena Custodia' tipo_muestra.nombre as 'Tipo Muestra', muestra.usuario_creacion as  'Creador Muestra', muestra_analisis.d_muestraanal_fechingr, valor.s_valor_nombre, auditoria.valor, auditoria.d_muestraanal_fechingr, auditoria.usuario,  auditoria.d_muestraanal_fechingr as 'Fecha Ingreso Dato' from valor join auditoria on auditoria.fk_id_valor= valor.id_valor join muestra_analisis on muestra_analisis.pi_muestraanal_id=valor.fi_valor_muestraanal join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra join tipo_muestra on tipo_muestra.pi_tipomuestra_id=muestra.fi_muestra_tipomuestreo ";
        consulta += filtros;
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            int j = 1;
            for (int i = 0; i < argumentos.size(); ++i) {
                q.setParameter(j, argumentos.get(i));
                ++j;
            }
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> cargarValoresPendientes(final String parametroAnalizar, final MatrizAnalisis matriz, final String nombreUsuario) {
        System.out.println("parametro analizar" + parametroAnalizar);
         System.out.println("Matriz" + matriz);
         System.out.println("nombreUsuario" + nombreUsuario);
        final EntityManager em = this.getEntityManager();
        final String consulta = "Select muestra.pi_muestra_id, "
                + "variable_analisis.s_varianal_descripcion, muestreo.s_muestreo_fechinic, "
                + "proveedor_variable_analisis.s_provarana_metodo, "
                + "proveedor_variable_analisis.s_provarana_limicuan, "
                + "proveedor_variable_analisis.unidad, tipo_muestra.s_tipomuestra_nombre from  "
                + "valor join muestra_analisis on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join muestra on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " join analista_parametro on analista_parametro.fi_analparam_varianal= variable_analisis.pi_varianal_id "
                + "join proveedor on proveedor.s_proveedor_nombre= muestra_analisis.fs_muestraanal_proveedor  "
                + "join proveedor_variable_analisis on (proveedor_variable_analisis.pfs_provarana_proveedor=proveedor.ps_proveedor_nit  "
                + "and proveedor_variable_analisis.pfi_provarana_variable=variable_analisis.pi_varianal_id) "
                + "join muestreo on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id join tipo_muestra on tipo_muestra.pi_tipomuestra_id= muestra.fi_muestra_tipomuest where variable_analisis.s_varianal_descripcion= ? "
                + " AND valor.s_valor_valor is null and variable_analisis.fi_varianal_matriz=?   and (analista_parametro.fs_analparam_ususupl= ? "
                + "or analista_parametro.fs_analparam_usutitul= ? or analista_parametro.fs_analparam_usuterc= ?) "
                + "and muestra_analisis.fi_muestraanal_analparam= analista_parametro.pi_analparam_id order by muestra.pi_muestra_id asc";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)parametroAnalizar);
            q.setParameter(2, (Object)matriz.getPiMatranalId());
            q.setParameter(3, (Object)nombreUsuario);
            q.setParameter(4, (Object)nombreUsuario);
            q.setParameter(5, (Object)nombreUsuario);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> encontrarMuestrasPendientesPorSubir(final int idVariableAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "select pi_valor_id from valor join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable where valor.s_valor_valor is null and variable_analisis.pi_varianal_id=?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idVariableAnalisis);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Valor> encontrarValorPorIdValor(final int idMuestraAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "selec pi_valor_id from valor  where fi_valor_muestraanal=?";
        try {
            final Query q = em.createNativeQuery(consulta, (Class)Valor.class);
            q.setParameter(1, (Object)idMuestraAnalisis);
            return (List<Valor>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> reedondearCifra(final String valor, final int decimales) {
        final EntityManager em = this.getEntityManager();
        try {
            final String insert = "SELECT cast( ROUND (?, ?) as char)";
            final Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, (Object)valor);
            insercion.setParameter(2, (Object)decimales);
            return (List<Object>)insercion.getResultList();
        }
        catch (Exception ex) {}
        finally {
            em.close();
        }
        return null;
    }
    
    public void reemplazarPuntoComa(final Integer idValor) {
        final EntityManager em = this.getEntityManager();
        final String insert = "update valor set s_valor_valor=REPLACE(s_valor_valor,'.',','), s_valor_valorreal=REPLACE(s_valor_valorreal,'.',',') WHERE valor.pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public void actualizarValorEurofins(final int idMuestraAnalisis, final SUsuario usuario) {
        final EntityManager em = this.getEntityManager();
        final String insert = "UPDATE valor SET s_valor_valor ='Ver reporte anexo', fs_valor_usuamodi = ? , d_valor_fechregi = ?,  s_valor_valorreal = 'Ver anexo' WHERE fi_valor_muestraanal=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)usuario.getPsUsuarioCodigo());
        insercion.setParameter(2, (Object)new Date());
        insercion.setParameter(3, (Object)idMuestraAnalisis);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public void eliminarValores(final Integer idMuestraAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String insert = "DELETE FROM valor WHERE fi_valor_muestraanal= ?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)idMuestraAnalisis);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Object> verificarDatos(final Reporte reporteActual) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "select count(muestra.pi_muestra_id) from reporte join muestra on muestra.fs_muestra_reporte= reporte.pi_reporte_id join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id where reporte.pi_reporte_id=? AND valor.s_valor_valor is null";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)reporteActual.getPiReporteId());
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void actualizarValor(final int idMuestraAnalisis, final ProveedorVariableAnalisis proveedorVariableAnalisis) {
        final EntityManager em = this.getEntityManager();
        final String insert = "update valor set s_valor_metodo=?, s_valor_limicuan = ?, s_valor_unidad= ? WHERE fi_valor_muestraanal=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)proveedorVariableAnalisis.getSProvaranaMetodo());
        insercion.setParameter(2, (Object)proveedorVariableAnalisis.getSProvaranaLimicuan());
        insercion.setParameter(3, (Object)proveedorVariableAnalisis.getUnidad());
        insercion.setParameter(4, (Object)idMuestraAnalisis);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<Object> encontrarValorPorNombre(final Valor valor) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "select pi_valor_id from valor where fi_valor_tpvalor=13 and s_valor_nombre= ? and fs_valor_usuamodi=? and d_valor_fechregi=?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)valor.getValor());
            q.setParameter(2, (Object)valor.getUsuario_modificador().getPsUsuarioCodigo());
            q.setParameter(3, (Object)valor.getFecha_registro());
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void actualizarMetodoLimiteUnidad(final Valor valor) {
        final EntityManager em = this.getEntityManager();
        final String insert = "update valor set s_valor_metodo=?, s_valor_limicuan = ?, s_valor_unidad= ?, fs_valor_usuamodi= ?, d_valor_fechmodi = now() WHERE fi_valor_muestraanal=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, (Object)valor.getSValorMetodo());
        insercion.setParameter(2, (Object)valor.getS_valor_limicuan());
        insercion.setParameter(3, (Object)valor.getSValorUnidad());
        insercion.setParameter(4, (Object)valor.getUsuario_modificador().getPsUsuarioCodigo());
        insercion.setParameter(5, (Object)valor.getIdParametro().getIdmuestraAnalisis());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<Object> encontrarValorReal(final int idValor) {
        final EntityManager em = this.getEntityManager();
        final String consulta = "select s_valor_valorreal from valor where pi_valor_id= ?";
        try {
            final Query q = em.createNativeQuery(consulta);
            q.setParameter(1, (Object)idValor);
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public List<Object> encontrarFormatos(final String filtros, final List<Object> argumentos) {
        final EntityManager em = this.getEntityManager();
        String consulta = " select date(d_valor_fechregi), muestra_analisis.fi_muestraanal_muestra, valor.fs_valor_usuamodi from valor join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal where  ";
        consulta += filtros;
        consulta += " and valor.s_valor_valor is not null";
        try {
            final Query q = em.createNativeQuery(consulta);
            System.out.println("query" + q.toString());
            int j = 1;
            for (int i = 0; i < argumentos.size(); ++i) {
                q.setParameter(j, argumentos.get(i));
                ++j;
            }
            return (List<Object>)q.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    public void insertarValorCalidad(final String nombreValor, final String usuarioModificador, final String fecha) {
        final EntityManager em = this.getEntityManager();
        try {
            final String insert = "INSERT INTO valor (fi_valor_tpvalor, s_valor_nombre, fs_valor_usuamodi, d_valor_fechregi, d_valor_fechingreso_analista) VALUES (13, ?, ?, now(), ? ) ";
            em.getTransaction().begin();
            final Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, (Object)nombreValor);
            insercion.setParameter(2, (Object)usuarioModificador);
            insercion.setParameter(3, (Object)fecha);
            insercion.executeUpdate();
            em.getTransaction().commit();
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            em.close();
        }
    }
    
    public Object encontrarUltimoValorCalidad() {
        final EntityManager em = this.getEntityManager();
        final String consulta = "select max(valor.pi_valor_id) from valor where fi_valor_tpvalor=13";
        try {
            final Query q = em.createNativeQuery(consulta);
            return q.getSingleResult();
        }
        finally {
            em.close();
        }
    }
    
    public void actualizarValorHidrobiologia(final int idValor, final Date valorSubidoAnalista, final String usuarioModificador, String observaciones) {
        final EntityManager em = this.getEntityManager();
        System.out.println("entra aca !!!");
        System.out.println("valor " + idValor);
        final String insert = "update valor set s_valor_valor='SUBIDO', s_valor_valorreal='SUBIDO',"
                + " d_valor_fechingreso_analista=?,  "
                + "fs_valor_usuamodi=?, s_valor_observaciones=?,  d_valor_fechmodi = now() WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String stringValorSubidoAnalista = dateFormat.format(valorSubidoAnalista);
        insercion.setParameter(1, stringValorSubidoAnalista);
        insercion.setParameter(2, usuarioModificador);
        insercion.setParameter(3, observaciones);
        insercion.setParameter(4, idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
     public void actualizarValorHidrobiologiaPeces(final int idValor, final Date valorSubidoAnalista, final String usuarioModificador, String tecnicaMuestreo, String esfuerzo, String observaciones) {
        final EntityManager em = this.getEntityManager();
        System.out.println("entra aca !!!");
        System.out.println("valor " + idValor);
        final String insert = "update valor set s_valor_valor='SUBIDO', s_valor_valorreal='SUBIDO', "
                + "d_valor_fechingreso_analista=?,  fs_valor_usuamodi=?,  "
                + "d_valor_fechmodi = now(), s_valor_tecnica_muestreo=?, "
                + "s_valor_esfuerzo_muestreo=?, s_valor_observaciones=?   WHERE pi_valor_id=?";
        em.getTransaction().begin();
        final Query insercion = em.createNativeQuery(insert);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String stringValorSubidoAnalista = dateFormat.format(valorSubidoAnalista);
        insercion.setParameter(1, stringValorSubidoAnalista);
        insercion.setParameter(2, usuarioModificador);
        insercion.setParameter(3, tecnicaMuestreo);
        insercion.setParameter(4, esfuerzo);
        insercion.setParameter(5, observaciones);
        insercion.setParameter(6, idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
      public void actualizarValorHidrobiologiaMacrofitas(final int idValor, final Date valorSubidoAnalista, final String usuarioModificador, String s_valor_area_muestreo,  String observaciones) {
        EntityManager em = this.getEntityManager();
        System.out.println("entra aca !!!");
        System.out.println("valor " + idValor);
        String insert = "update valor set s_valor_valor='SUBIDO', s_valor_valorreal='SUBIDO', "
                + "d_valor_fechingreso_analista=?,  fs_valor_usuamodi=?,  "
                + "d_valor_fechmodi = now(), s_valor_area_muestreo=?, "
                + "s_valor_observaciones=?   WHERE pi_valor_id=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String stringValorSubidoAnalista = dateFormat.format(valorSubidoAnalista);
        insercion.setParameter(1, stringValorSubidoAnalista);
        insercion.setParameter(2, usuarioModificador);
        insercion.setParameter(3, s_valor_area_muestreo);
        insercion.setParameter(4, observaciones);
        insercion.setParameter(5, idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void actualizarValorFiltro(String filtro, int idMuestra, int idVariable, String proveedor) {
        EntityManager em = this.getEntityManager();
        String insert = "update valor set s_valor_filtro=? "
                + " WHERE fi_valor_muestraanal=( select pi_muestraanal_id from "
                + " muestra_analisis "
                + " where fi_muestraanal_muestra=? "
                + " and fi_muestraanal_variable=? "
                + " and fs_muestraanal_proveedor=?)";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, filtro);
        insercion.setParameter(2, idMuestra);
        insercion.setParameter(3, idVariable);
        insercion.setParameter(4, proveedor);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarValorCromatograma(String cromatograma, Integer idValor) {
     EntityManager em = this.getEntityManager();
        String insert = "update valor set s_valor_cromatograma=? "
                + "WHERE pi_valor_id=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, cromatograma);
        insercion.setParameter(2, idValor);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
   
    public int encontraridValor(int idMuestra, int idVariableAnalisis) {
     EntityManager em = this.getEntityManager();
        String consulta = "select pi_valor_id from valor "
                + "join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "where muestra.pi_muestra_id= ? and muestra_analisis.fi_muestraanal_variable=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(consulta);
        insercion.setParameter(1, idMuestra);
        insercion.setParameter(2, idVariableAnalisis);
        return (Integer) insercion.getSingleResult();
        
    }
    
    public int encontrarValorPorMuestraAnalisis(int idMuestra, int idVariableAnalisis) {
     EntityManager em = this.getEntityManager();
        String consulta = "select pi_valor_id from valor "
                + "join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "where muestra.pi_muestra_id= ? and muestra_analisis.fi_muestraanal_variable=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(consulta);
        insercion.setParameter(1, idMuestra);
        insercion.setParameter(2, idVariableAnalisis);
        return (Integer) insercion.getSingleResult();
        
    }

    public List<Object> cargarFiltrosAire(String idReporte) {
     EntityManager em = getEntityManager();
        String consulta = "SELECT muestra.pi_muestra_id, muestra.fi_muestra_muestreo, muestra.fs_muestra_reporte, "
                + "muestra.fi_muestra_campo, muestra.s_muestra_descripcion, variable_analisis.s_varianal_descripcion, "
                + "valor.s_valor_filtro "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "where fs_muestra_reporte=? "
                + "order by muestra.pi_muestra_id asc " ;
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1,idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void modificarFiltroAire(Integer idMuestraAnalisis, String valor) {
        EntityManager em = this.getEntityManager();
        String insert = "update valor set s_valor_filtro=?, d_valor_fechmodi=now() "
                + "where fi_valor_muestraanal=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, valor);
        insercion.setParameter(2, idMuestraAnalisis);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void actualizarValorResultado(int idMuestra, String valorIngresar, String valorReal, Integer piVarianalId, Date fechaIngresar, String usuario) {
         EntityManager em = getEntityManager();
         StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_actualizar_valor");
         em.getTransaction().begin();  
         procedimientoAlmacenado.registerStoredProcedureParameter("idMuestra", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("valorIngresar", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("valorReal", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("piVarianalId", int.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("fechaIngresar", Date.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_modificacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("idMuestra",idMuestra);
        procedimientoAlmacenado.setParameter("valorIngresar", valorIngresar);
        procedimientoAlmacenado.setParameter("valorReal", valorReal);
        procedimientoAlmacenado.setParameter("piVarianalId", piVarianalId);
        procedimientoAlmacenado.setParameter("fechaIngresar", fechaIngresar);
        procedimientoAlmacenado.setParameter("usuario_modificacion", usuario);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
         em.close(); 
    
    }

  

 
}