/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cliente;
import entidades.SUsuario;
import java.util.List;
import entidades.Proveedor;
import entidades.Reporte;
import entidades.VariableAnalisis;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author TOSHIBA
 */
public class ReporteJpaController implements Serializable {

    public ReporteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*public void create(Reporte reporte) throws PreexistingEntityException, Exception {
       
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = reporte.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente);
                reporte.setIdCliente(idCliente);
            }
            Proyecto idProyecto = reporte.getIdProyecto();
            if (idProyecto != null) {
                idProyecto = em.getReference(idProyecto.getClass(), idProyecto.getIdProyecto());
                reporte.setIdProyecto(idProyecto);
            }
            Muestreo idMuestreo = reporte.getIdMuestreo();
            if (idMuestreo != null) {
                idMuestreo = em.getReference(idMuestreo.getClass(), idMuestreo.getIdMuestreo());
                reporte.setIdMuestreo(idMuestreo);
            }
            SUsuario aprobado = reporte.getAprobado();
            if (aprobado != null) {
                aprobado = em.getReference(aprobado.getClass(), aprobado.getPsUsuarioCodigo());
                reporte.setAprobado(aprobado);
            }
            SUsuario revisado = reporte.getRevisado();
            if (revisado != null) {
                revisado = em.getReference(revisado.getClass(), revisado.getPsUsuarioCodigo());
                reporte.setRevisado(revisado);
            }
            for (Foto fotoListFotoToAttach : reporte.getFotoList()) {
                fotoListFotoToAttach = em.getReference(fotoListFotoToAttach.getClass(), fotoListFotoToAttach.getIdFoto());
                attachedFotoList.add(fotoListFotoToAttach);
            }
            reporte.setFotoList(attachedFotoList);
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : reporte.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getIdmuestra());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            reporte.setMuestraList(attachedMuestraList);
            em.persist(reporte);
            if (idCliente != null) {
                idCliente.getReporteList().add(reporte);
                idCliente = em.merge(idCliente);
            }
            if (idProyecto != null) {
                idProyecto.getReporteList().add(reporte);
                idProyecto = em.merge(idProyecto);
            }
            if (idMuestreo != null) {
                idMuestreo.getReporteList().add(reporte);
                idMuestreo = em.merge(idMuestreo);
            }
            if (aprobado != null) {
                aprobado.getReporteList().add(reporte);
                aprobado = em.merge(aprobado);
            }
            if (revisado != null) {
                revisado.getReporteList().add(reporte);
                revisado = em.merge(revisado);
            }
            /*for (Foto fotoListFoto : reporte.getFotoList()) {
                Reporte oldIdReporteOfFotoListFoto = fotoListFoto.getIdReporte();
                fotoListFoto.setIdReporte(reporte);
                fotoListFoto = em.merge(fotoListFoto);
                if (oldIdReporteOfFotoListFoto != null) {
                    oldIdReporteOfFotoListFoto.getFotoList().remove(fotoListFoto);
                    oldIdReporteOfFotoListFoto = em.merge(oldIdReporteOfFotoListFoto);
                }
            }
            for (Muestra muestraListMuestra : reporte.getMuestraList()) {
                Reporte oldReporteOfMuestraListMuestra = muestraListMuestra.getReporte();
                muestraListMuestra.setReporte(reporte);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldReporteOfMuestraListMuestra != null) {
                    oldReporteOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldReporteOfMuestraListMuestra = em.merge(oldReporteOfMuestraListMuestra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            /*if (findReporte(reporte.getIdreporte()) != null) {
                throw new PreexistingEntityException("Reporte " + reporte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public void edit(Reporte reporte) throws IllegalOrphanException, NonexistentEntityException, Exception {
       /* EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte persistentReporte = em.find(Reporte.class, reporte.getIdreporte());
            Cliente idClienteOld = persistentReporte.getIdCliente();
            Cliente idClienteNew = reporte.getIdCliente();
            Proyecto idProyectoOld = persistentReporte.getIdProyecto();
            Proyecto idProyectoNew = reporte.getIdProyecto();
            Muestreo idMuestreoOld = persistentReporte.getIdMuestreo();
            Muestreo idMuestreoNew = reporte.getIdMuestreo();
            SUsuario aprobadoOld = persistentReporte.getAprobado();
            SUsuario aprobadoNew = reporte.getAprobado();
            SUsuario revisadoOld = persistentReporte.getRevisado();
            SUsuario revisadoNew = reporte.getRevisado();
            List<Foto> fotoListOld = persistentReporte.getFotoList();
            List<Foto> fotoListNew = reporte.getFotoList();
            List<Muestra> muestraListOld = persistentReporte.getMuestraList();
            List<Muestra> muestraListNew = reporte.getMuestraList();
            List<String> illegalOrphanMessages = null;
            for (Foto fotoListOldFoto : fotoListOld) {
                if (!fotoListNew.contains(fotoListOldFoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Foto " + fotoListOldFoto + " since its idReporte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew);
                reporte.setIdCliente(idClienteNew);
            }
            if (idProyectoNew != null) {
                idProyectoNew = em.getReference(idProyectoNew.getClass(), idProyectoNew.getIdProyecto());
                reporte.setIdProyecto(idProyectoNew);
            }
            if (idMuestreoNew != null) {
                idMuestreoNew = em.getReference(idMuestreoNew.getClass(), idMuestreoNew.getIdMuestreo());
                reporte.setIdMuestreo(idMuestreoNew);
            }
            if (aprobadoNew != null) {
                aprobadoNew = em.getReference(aprobadoNew.getClass(), aprobadoNew.getPsUsuarioCodigo());
                reporte.setAprobado(aprobadoNew);
            }
            if (revisadoNew != null) {
                revisadoNew = em.getReference(revisadoNew.getClass(), revisadoNew.getPsUsuarioCodigo());
                reporte.setRevisado(revisadoNew);
            }
            List<Foto> attachedFotoListNew = new ArrayList<Foto>();
            for (Foto fotoListNewFotoToAttach : fotoListNew) {
                fotoListNewFotoToAttach = em.getReference(fotoListNewFotoToAttach.getClass(), fotoListNewFotoToAttach.getIdFoto());
                attachedFotoListNew.add(fotoListNewFotoToAttach);
            }
            fotoListNew = attachedFotoListNew;
            reporte.setFotoList(fotoListNew);
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getIdmuestra());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            reporte.setMuestraList(muestraListNew);
            reporte = em.merge(reporte);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getReporteList().remove(reporte);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getReporteList().add(reporte);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idProyectoOld != null && !idProyectoOld.equals(idProyectoNew)) {
                idProyectoOld.getReporteList().remove(reporte);
                idProyectoOld = em.merge(idProyectoOld);
            }
            if (idProyectoNew != null && !idProyectoNew.equals(idProyectoOld)) {
                idProyectoNew.getReporteList().add(reporte);
                idProyectoNew = em.merge(idProyectoNew);
            }
            if (idMuestreoOld != null && !idMuestreoOld.equals(idMuestreoNew)) {
                idMuestreoOld.getReporteList().remove(reporte);
                idMuestreoOld = em.merge(idMuestreoOld);
            }
            if (idMuestreoNew != null && !idMuestreoNew.equals(idMuestreoOld)) {
                idMuestreoNew.getReporteList().add(reporte);
                idMuestreoNew = em.merge(idMuestreoNew);
            }
            if (aprobadoOld != null && !aprobadoOld.equals(aprobadoNew)) {
                aprobadoOld.getReporteList().remove(reporte);
                aprobadoOld = em.merge(aprobadoOld);
            }
            if (aprobadoNew != null && !aprobadoNew.equals(aprobadoOld)) {
                aprobadoNew.getReporteList().add(reporte);
                aprobadoNew = em.merge(aprobadoNew);
            }
            if (revisadoOld != null && !revisadoOld.equals(revisadoNew)) {
                revisadoOld.getReporteList().remove(reporte);
                revisadoOld = em.merge(revisadoOld);
            }
            if (revisadoNew != null && !revisadoNew.equals(revisadoOld)) {
                revisadoNew.getReporteList().add(reporte);
                revisadoNew = em.merge(revisadoNew);
            }
            for (Foto fotoListNewFoto : fotoListNew) {
                if (!fotoListOld.contains(fotoListNewFoto)) {
                    Reporte oldIdReporteOfFotoListNewFoto = fotoListNewFoto.getIdReporte();
                    fotoListNewFoto.setIdReporte(reporte);
                    fotoListNewFoto = em.merge(fotoListNewFoto);
                    if (oldIdReporteOfFotoListNewFoto != null && !oldIdReporteOfFotoListNewFoto.equals(reporte)) {
                        oldIdReporteOfFotoListNewFoto.getFotoList().remove(fotoListNewFoto);
                        oldIdReporteOfFotoListNewFoto = em.merge(oldIdReporteOfFotoListNewFoto);
                    }
                }
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    muestraListOldMuestra.setReporte(null);
                    muestraListOldMuestra = em.merge(muestraListOldMuestra);
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Reporte oldReporteOfMuestraListNewMuestra = muestraListNewMuestra.getReporte();
                    muestraListNewMuestra.setReporte(reporte);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldReporteOfMuestraListNewMuestra != null && !oldReporteOfMuestraListNewMuestra.equals(reporte)) {
                        oldReporteOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldReporteOfMuestraListNewMuestra = em.merge(oldReporteOfMuestraListNewMuestra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reporte.getIdreporte();
                if (findReporte(id) == null) {
                    throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
     /*   EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte reporte;
            try {
                reporte = em.getReference(Reporte.class, id);
                reporte.getIdreporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Foto> fotoListOrphanCheck = reporte.getFotoList();
            for (Foto fotoListOrphanCheckFoto : fotoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reporte (" + reporte + ") cannot be destroyed since the Foto " + fotoListOrphanCheckFoto + " in its fotoList field has a non-nullable idReporte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idCliente = reporte.getIdCliente();
            if (idCliente != null) {
                idCliente.getReporteList().remove(reporte);
                idCliente = em.merge(idCliente);
            }
            Proyecto idProyecto = reporte.getIdProyecto();
            if (idProyecto != null) {
                idProyecto.getReporteList().remove(reporte);
                idProyecto = em.merge(idProyecto);
            }
            Muestreo idMuestreo = reporte.getIdMuestreo();
            if (idMuestreo != null) {
                idMuestreo.getReporteList().remove(reporte);
                idMuestreo = em.merge(idMuestreo);
            }
            SUsuario aprobado = reporte.getAprobado();
            if (aprobado != null) {
                aprobado.getReporteList().remove(reporte);
                aprobado = em.merge(aprobado);
            }
            SUsuario revisado = reporte.getRevisado();
            if (revisado != null) {
                revisado.getReporteList().remove(reporte);
                revisado = em.merge(revisado);
            }
            List<Muestra> muestraList = reporte.getMuestraList();
            for (Muestra muestraListMuestra : muestraList) {
                muestraListMuestra.setReporte(null);
                muestraListMuestra = em.merge(muestraListMuestra);
            }
            em.remove(reporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public List<Reporte> findReporteEntities() {
        return findReporteEntities(true, -1, -1);
    }

    public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
        return findReporteEntities(false, maxResults, firstResult);
    }

    private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reporte.class));
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

    public Reporte findReporte(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reporte> rt = cq.from(Reporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crear(Date muestreo_fech_inic, String planMuestreo, String tecnico, int idMunicipio,
                        int valorCiudad, int proyecto , Date fechaRecepcionMuestra, String usuarioCreador,
                        String idReporte, String nitReporte, String nitReporteFacturacion, String estadoReporte,
                        int numeroReporte,
                        String versionReporte, String matriz) { 
        
        switch(matriz){
            case "MATRIZ CALIDAD DEL AIRE" :
                 EntityManager em = getEntityManager();
                StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_crear_muestreo_reporte_aire");
                em.getTransaction().begin();
                procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
                procedimientoAlmacenado.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);
                procedimientoAlmacenado.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
                procedimientoAlmacenado.setParameter("muestreo_plan_muestreo", planMuestreo);
                procedimientoAlmacenado.setParameter("muestreo_tecnico", tecnico);
                procedimientoAlmacenado.setParameter("valor_municipio", idMunicipio);
                procedimientoAlmacenado.setParameter("valor_ciudad", valorCiudad);
                procedimientoAlmacenado.setParameter("proyecto", proyecto);
                procedimientoAlmacenado.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
                procedimientoAlmacenado.setParameter("usuario_creador", usuarioCreador);
                procedimientoAlmacenado.setParameter("pi_reporte_id", idReporte);
                procedimientoAlmacenado.setParameter("nit_reporte_cliente", nitReporte);
                procedimientoAlmacenado.setParameter("estado_reporte", estadoReporte);
                procedimientoAlmacenado.setParameter("version_reporte", versionReporte);
                procedimientoAlmacenado.setParameter("cliente_facturacion", nitReporteFacturacion);
                procedimientoAlmacenado.setParameter("numero_reporte", numeroReporte);
                procedimientoAlmacenado.execute();
                em.getTransaction().commit();
            break;

              case "MATRIZ BIOTA":
                  EntityManager emHidro = getEntityManager();
                  StoredProcedureQuery procedimientoAlmacenadoHidro = emHidro.createStoredProcedureQuery("pa_crear_reporte_hidrobiologia");
                  emHidro.getTransaction().begin();
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoHidro.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);

                  procedimientoAlmacenadoHidro.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
                  procedimientoAlmacenadoHidro.setParameter("muestreo_plan_muestreo", planMuestreo);
                  procedimientoAlmacenadoHidro.setParameter("muestreo_tecnico", tecnico);
                  procedimientoAlmacenadoHidro.setParameter("valor_municipio", idMunicipio);
                  procedimientoAlmacenadoHidro.setParameter("valor_ciudad", valorCiudad);
                  procedimientoAlmacenadoHidro.setParameter("proyecto", proyecto);
                  procedimientoAlmacenadoHidro.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
                  procedimientoAlmacenadoHidro.setParameter("usuario_creador", usuarioCreador);
                  procedimientoAlmacenadoHidro.setParameter("pi_reporte_id", idReporte);
                  procedimientoAlmacenadoHidro.setParameter("nit_reporte_cliente", nitReporte);
                  procedimientoAlmacenadoHidro.setParameter("estado_reporte", estadoReporte);
                  procedimientoAlmacenadoHidro.setParameter("version_reporte", versionReporte);
                  procedimientoAlmacenadoHidro.setParameter("cliente_facturacion", nitReporteFacturacion);
                  procedimientoAlmacenadoHidro.setParameter("numero_reporte", numeroReporte);
                  procedimientoAlmacenadoHidro.execute();
                  emHidro.getTransaction().commit();

                break;
                
               
                
                 case "MATRIZ AGUA POTABLE":
                  EntityManager emAguaPotable = getEntityManager();
                  StoredProcedureQuery procedimientoAlmacenadoAguaPotable = emAguaPotable.createStoredProcedureQuery("pa_crear_reporte_agua_potable");
                  emAguaPotable.getTransaction().begin();
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoAguaPotable.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);

                  procedimientoAlmacenadoAguaPotable.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
                  procedimientoAlmacenadoAguaPotable.setParameter("muestreo_plan_muestreo", planMuestreo);
                  procedimientoAlmacenadoAguaPotable.setParameter("muestreo_tecnico", tecnico);
                  procedimientoAlmacenadoAguaPotable.setParameter("valor_municipio", idMunicipio);
                  procedimientoAlmacenadoAguaPotable.setParameter("valor_ciudad", valorCiudad);
                  procedimientoAlmacenadoAguaPotable.setParameter("proyecto", proyecto);
                  procedimientoAlmacenadoAguaPotable.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
                  procedimientoAlmacenadoAguaPotable.setParameter("usuario_creador", usuarioCreador);
                  procedimientoAlmacenadoAguaPotable.setParameter("pi_reporte_id", idReporte);
                  procedimientoAlmacenadoAguaPotable.setParameter("nit_reporte_cliente", nitReporte);
                  procedimientoAlmacenadoAguaPotable.setParameter("estado_reporte", estadoReporte);
                  procedimientoAlmacenadoAguaPotable.setParameter("version_reporte", versionReporte);
                  procedimientoAlmacenadoAguaPotable.setParameter("cliente_facturacion", nitReporteFacturacion);
                  procedimientoAlmacenadoAguaPotable.setParameter("numero_reporte", numeroReporte);
                  procedimientoAlmacenadoAguaPotable.execute();
                  emAguaPotable.getTransaction().commit();

                break;
                
                case "MATRIZ CONCRETO":
                  EntityManager emConcreto = getEntityManager();
                  StoredProcedureQuery procedimientoAlmacenadoConcreto = emConcreto.createStoredProcedureQuery("pa_crear_reporte_agua_potable");
                  emConcreto.getTransaction().begin();
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoConcreto.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);

                  procedimientoAlmacenadoConcreto.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
                  procedimientoAlmacenadoConcreto.setParameter("muestreo_plan_muestreo", planMuestreo);
                  procedimientoAlmacenadoConcreto.setParameter("muestreo_tecnico", tecnico);
                  procedimientoAlmacenadoConcreto.setParameter("valor_municipio", idMunicipio);
                  procedimientoAlmacenadoConcreto.setParameter("valor_ciudad", valorCiudad);
                  procedimientoAlmacenadoConcreto.setParameter("proyecto", proyecto);
                  procedimientoAlmacenadoConcreto.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
                  procedimientoAlmacenadoConcreto.setParameter("usuario_creador", usuarioCreador);
                  procedimientoAlmacenadoConcreto.setParameter("pi_reporte_id", idReporte);
                  procedimientoAlmacenadoConcreto.setParameter("nit_reporte_cliente", nitReporte);
                  procedimientoAlmacenadoConcreto.setParameter("estado_reporte", estadoReporte);
                  procedimientoAlmacenadoConcreto.setParameter("version_reporte", versionReporte);
                  procedimientoAlmacenadoConcreto.setParameter("cliente_facturacion", nitReporteFacturacion);
                  procedimientoAlmacenadoConcreto.setParameter("numero_reporte", numeroReporte);
                  procedimientoAlmacenadoConcreto.execute();
                  emConcreto.getTransaction().commit();

                break;
                
                
                
                 default:
                 EntityManager emMatricesDistintas = getEntityManager();
                  StoredProcedureQuery procedimientoAlmacenadoMatricesDistintas = emMatricesDistintas.createStoredProcedureQuery("pa_crear_reporte_aguas");
                  emMatricesDistintas.getTransaction().begin();
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
                  procedimientoAlmacenadoMatricesDistintas.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);

                  procedimientoAlmacenadoMatricesDistintas.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("muestreo_plan_muestreo", planMuestreo);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("muestreo_tecnico", tecnico);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("valor_municipio", idMunicipio);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("valor_ciudad", valorCiudad);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("proyecto", proyecto);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("usuario_creador", usuarioCreador);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("pi_reporte_id", idReporte);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("nit_reporte_cliente", nitReporte);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("estado_reporte", estadoReporte);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("version_reporte", versionReporte);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("cliente_facturacion", nitReporteFacturacion);
                  procedimientoAlmacenadoMatricesDistintas.setParameter("numero_reporte", numeroReporte);
                  procedimientoAlmacenadoMatricesDistintas.execute();
                  emMatricesDistintas.getTransaction().commit();     
                
            
        }
                
        
       System.out.println("usuario creador " + usuarioCreador);
       
        /*
        try {     
         String insert= "INSERT INTO reporte (pi_reporte_id, "
                 + "fi_reporte_cliente, fi_reporte_muestreo, "
                 + "fi_reporte_proyecto, "
                 + "c_reporte_estado, fs_reporte_usuacread, "
                 + "s_reporte_version, fi_reporte_facturacion, i_reporte_numero, "
                 + "fs_reporte_document_tipodocu, fs_reporte_document_letrproc, "
                 + "fs_reporte_document_consecutivo, fs_reporte_document_version, "
                 + "d_reporte_fechcreaci ) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, report.getPiReporteId());
            insercion.setParameter(2, report.getIdCliente().getClientePK().getPsClienteNit());
            insercion.setParameter(3, report.getIdMuestreo().getIdMuestreo());
            insercion.setParameter(4, report.getIdProyecto().getIdProyecto());
            insercion.setParameter(5, report.getEstado());
            insercion.setParameter(6, report.getUsuarioCreador().getPsUsuarioCodigo());
            insercion.setParameter(7, report.getSReporteVersion());
            insercion.setParameter(8, report.getClienteFacturacion().getClientePK().getPsClienteNit());
            insercion.setParameter(9, report.getI_reporte_numero());
            if(report.getDocumento()==null){
                insercion.setParameter(10, null);
                insercion.setParameter(11, null);
                insercion.setParameter(12, null);
                insercion.setParameter(13, null);
            }
            else{
                insercion.setParameter(10, report.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
                insercion.setParameter(11, report.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
                insercion.setParameter(12, report.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
                insercion.setParameter(13, report.getDocumento().getDocumentoPK().getPsDocumentVersion());

            }
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }*/
    }
    
     public void crearReporteHidrobiologia(Date muestreo_fech_inic, String planMuestreo, String tecnico, int idMunicipio,
                        int valorCiudad, int proyecto , Date fechaRecepcionMuestra, String usuarioCreador,
                        String idReporte, String nitReporte, String nitReporteFacturacion, String estadoReporte,
                        int numeroReporte,
                        String versionReporte) { 
       System.out.println("usuario creador " + usuarioCreador);
        EntityManager em = getEntityManager();
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_crear_reporte_hidrobiologia");
        em.getTransaction().begin();
        procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_fech_inicio", Date.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_plan_muestreo", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("muestreo_tecnico", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("valor_municipio", Integer.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("valor_ciudad", Integer.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("proyecto", Integer.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("fecha_recepcion_muestra", Date.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("usuario_creador", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("nit_reporte_cliente", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("estado_reporte", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("version_reporte", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("cliente_facturacion", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("numero_reporte", Integer.class, ParameterMode.IN);
       
        
        procedimientoAlmacenado.setParameter("muestreo_fech_inicio", muestreo_fech_inic);
        procedimientoAlmacenado.setParameter("muestreo_plan_muestreo", planMuestreo);
        procedimientoAlmacenado.setParameter("muestreo_tecnico", tecnico);
        procedimientoAlmacenado.setParameter("valor_municipio", idMunicipio);
        procedimientoAlmacenado.setParameter("valor_ciudad", valorCiudad);
        procedimientoAlmacenado.setParameter("proyecto", proyecto);
        procedimientoAlmacenado.setParameter("fecha_recepcion_muestra", fechaRecepcionMuestra);
        procedimientoAlmacenado.setParameter("usuario_creador", usuarioCreador);
        procedimientoAlmacenado.setParameter("pi_reporte_id", idReporte);
        procedimientoAlmacenado.setParameter("nit_reporte_cliente", nitReporte);
        procedimientoAlmacenado.setParameter("estado_reporte", estadoReporte);
        procedimientoAlmacenado.setParameter("version_reporte", versionReporte);
        procedimientoAlmacenado.setParameter("cliente_facturacion",nitReporteFacturacion);
        procedimientoAlmacenado.setParameter("numero_reporte", numeroReporte);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();

       
    }

    public List buscarUltimoReporte() {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM reporte "
                + "ORDER BY i_reporte_numero DESC LIMIT 1";
        try {
            Query q = em.createNativeQuery(consulta, Reporte.class);      
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> encontrarCliente(String filtros, List<Object> argumentos) {
         EntityManager em = getEntityManager();
         String consulta = "SELECT fi_reporte_cliente FROM reporte"
                 + " join proyecto on proyecto.pi_proyecto_id= reporte.fi_reporte_proyecto"
                 + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id"
                 + " join cliente on cliente.ps_cliente_nit= reporte.fi_reporte_facturacion"
                 + " WHERE ";
        consulta+=filtros;
        consulta+=" group by reporte.pi_reporte_id order by d_reporte_fechcreaci ";
        try {
            System.out.println("consulta cliente " + consulta);
            Query q = em.createNativeQuery(consulta);
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
    
     public List<String> encontrarClienteFacturacion(String filtros, List<Object> argumentos) {
         EntityManager em = getEntityManager();
         String consulta = "SELECT fi_reporte_facturacion FROM reporte"
                 + " join proyecto on proyecto.pi_proyecto_id= reporte.fi_reporte_proyecto"
                 + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id"
                 + " join cliente on cliente.ps_cliente_nit= reporte.fi_reporte_cliente"
                 + " WHERE ";
        consulta+=filtros;
        consulta+=" group by reporte.pi_reporte_id order by d_reporte_fechcreaci ";
        try {
            Query q = em.createNativeQuery(consulta);
            System.out.println("consulta facturacion " +consulta );
            System.out.println("tamaño de argumentos "+ argumentos.size());
          int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                System.out.println("argumentos "+ argumentos.get(i));
                q.setParameter(j, argumentos.get(i));
                j++;
            }
             return q.getResultList();
        } finally {
            em.close();
        }        
    }
    
     public List<Reporte> encontrarReporte(String filtros, List<Object> argumentos) {
         EntityManager em = getEntityManager();     
         String consulta = "SELECT distinct * FROM reporte"
                 + " join proyecto on proyecto.pi_proyecto_id= reporte.fi_reporte_proyecto"
                 + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id"
                 + " join cliente on (cliente.ps_cliente_nit = reporte.fi_reporte_cliente"
                 + " or cliente.ps_cliente_nit= reporte.fi_reporte_facturacion)"
                 + " WHERE ";
        consulta+=filtros;
        consulta+=" group by reporte.pi_reporte_id order by d_reporte_fechcreaci ";
         System.out.println("conulta " + consulta);
        try {  
            Query q = em.createNativeQuery(consulta,Reporte.class);
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
     
    
    public boolean revisarReporte(String reporteActual, SUsuario usuario) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET c_reporte_estado='R', fs_reporte_usuarevi=?, d_reporte_fecharevi =? WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, usuario.getPsUsuarioCodigo());
        insercion.setParameter(2, new Date());
        insercion.setParameter(3, reporteActual);
        insercion.executeUpdate();
        em.getTransaction().commit();
        return true;
    } 
    
    
    public boolean actualizarReporteObservaciones(String idReporteActual, String observaciones, SUsuario usuario) {   
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET s_reporte_observaciones=?, d_reporte_fechmodi=?, fs_reporte_usuamodi = ?  WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, observaciones);
        insercion.setParameter(2, new Date());
        insercion.setParameter(3, usuario.getPsUsuarioCodigo());
        insercion.setParameter(4, idReporteActual);
        insercion.executeUpdate();
        em.getTransaction().commit();
        return true;
    } 

    public List<Cliente> encontrarCliente(String nit) {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM cliente where ps_cliente_nit = ?";
        try {
            Query q = em.createNativeQuery(consulta, Cliente.class);
          q.setParameter(1, nit);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean aprobar(Reporte reporteActual, SUsuario usuarioActual) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET c_reporte_estado='A', fs_reporte_usuaaprob=?, d_reporte_fechapro=? WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, usuarioActual.getPsUsuarioCodigo());
        insercion.setParameter(2, new Date());
        insercion.setParameter(3, reporteActual.getPiReporteId());
        insercion.executeUpdate();
        em.getTransaction().commit();
        return true;
    }


    public void editarReporte(String idReporte, String idViejo) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET pi_reporte_id= ? WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idReporte);
        insercion.setParameter(2, idViejo);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public void cambiarEstado(String idReporte) {   
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET c_reporte_estado='O', d_reporte_fechaentrega = now()  WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idReporte);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
   
     public void cambiarEstadoParcialmente(String idReporte) {   
        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET c_reporte_estado='P' WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idReporte);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
     

    public void actualizarAnexosReporte(int anexos, String idReporteActual) {
        EntityManager em = getEntityManager();
        String consulta = "UPDATE reporte SET i_reporte_anexos=?  WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(consulta);
        insercion.setParameter(1, anexos);
        insercion.setParameter(2, idReporteActual);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    //Devuelve la cantidad de reportes
    public List<Object> encontrarCantidadReportes() {
     EntityManager em = getEntityManager();
        String consulta = "Select count(pi_reporte_id) from reporte";
        try {
            Query q = em.createNativeQuery(consulta);         
            return q.getResultList();
        } finally {
            em.close();
        }  
    }

    public List<Object> consultarParametrosPropios(String idReporte) {
        System.out.println("llega aca al id reporte"+ idReporte);
        EntityManager em = getEntityManager();
        String consulta = "Select DISTINCT variable_analisis.s_varianal_descripcion," +
                           "proveedor_variable_analisis.s_provarana_metodo, muestra.pi_muestra_id," +
                            "muestra_analisis.pi_muestraanal_id from reporte " +
                            "join muestreo on muestreo.pi_muestreo_id= reporte.fi_reporte_muestreo " +
                            "join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id " +
                            "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id " +
                            "join variable_analisis on variable_analisis.pi_varianal_id = muestra_analisis.fi_muestraanal_variable " +
                            "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable = variable_analisis.pi_varianal_id " +
                            "join tipo_analisis on proveedor_variable_analisis.fi_provarana_tipoanal= tipo_analisis.pi_tipoanal_id " +
                            "where reporte.pi_reporte_id=? and proveedor_variable_analisis.pfs_provarana_proveedor= '9002414398' " +
                            "and tipo_analisis.pi_tipoanal_id= 1 " +
                            "group by s_varianal_descripcion";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }  
    }

    public List<Object> consultarParametrosSubcontratados(String idReporte) {
      EntityManager em = getEntityManager();
        String consulta = "select DISTINCT variable_analisis.s_varianal_descripcion ,"
                + "muestra_analisis.fs_muestraanal_proveedor from reporte "
                + "join muestreo on muestreo.pi_muestreo_id= reporte.fi_reporte_muestreo "
                + "join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id "
                + "join muestra_analisis on muestra.pi_muestra_id = muestra_analisis.fi_muestraanal_muestra "
                + "join variable_analisis on variable_analisis.pi_varianal_id = muestra_analisis.fi_muestraanal_variable "
                + "join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable = variable_analisis.pi_varianal_id "
                + "join tipo_analisis on proveedor_variable_analisis.fi_provarana_tipoanal= tipo_analisis.pi_tipoanal_id "
                + "where reporte.pi_reporte_id=? and muestra_analisis.fs_muestraanal_proveedor != 'CIMA' "
                + "group by s_varianal_descripcion";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }  
      
    
    }

    public List<String> encontrarReportePorMuestra(int idmuestra) {
    EntityManager em = getEntityManager();
        String consulta = " select fs_muestra_reporte"
                + " from muestra"
                + " where muestra.pi_muestra_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idmuestra);
            return q.getResultList();
        } finally {
            em.close();
        }  
          
    
    }

    public List<Object> traerReportesVencidosSubcontratados() {
        EntityManager em = getEntityManager();
        String consulta = "select distinct reporte.pi_reporte_id from reporte JOIN muestreo on reporte.fi_reporte_muestreo=muestreo.pi_muestreo_id"
                + " join muestra on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id"
                + " join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id"
                + " join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable=variable_analisis.pi_varianal_id"
                + " join tipo_analisis on proveedor_variable_analisis.fi_provarana_tipoanal= tipo_analisis.pi_tipoanal_id"
                + " where (reporte.fs_reporte_usuaaprob is null or reporte.fs_reporte_usuarevi is null) and (tipo_analisis.pi_tipoanal_id=3 or tipo_analisis.pi_tipoanal_id=4)"
                + " and (muestra.fi_muestra_matriz=1 or muestra.fi_muestra_matriz=3)" 
                + " order by pi_reporte_id asc";
        try {
            Query q = em.createNativeQuery(consulta);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Reporte> reportesVencidos(String idReporte) {

        EntityManager em = getEntityManager();
        String consulta = "Select * from reporte where pi_reporte_id = ?";
        try {
            Query q = em.createNativeQuery(consulta, Reporte.class);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Reporte> encontrarReporte(String idReporte) {

        EntityManager em = getEntityManager();
        String consulta = "Select * from reporte where pi_reporte_id = ?";
        try {
            Query q = em.createNativeQuery(consulta, Reporte.class);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> traerReportesVencidosCima() {
     EntityManager em = getEntityManager();
        String consulta = "select distinct reporte.pi_reporte_id from reporte JOIN muestreo on reporte.fi_reporte_muestreo=muestreo.pi_muestreo_id"
                + " join muestra on muestra.fi_muestra_muestreo= muestreo.pi_muestreo_id"
                + " join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id"
                + " join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable=variable_analisis.pi_varianal_id"
                + " join tipo_analisis on proveedor_variable_analisis.fi_provarana_tipoanal= tipo_analisis.pi_tipoanal_id"
                + " where (reporte.fs_reporte_usuaaprob is null or reporte.fs_reporte_usuarevi is null) and "
                + " (tipo_analisis.pi_tipoanal_id=1 or tipo_analisis.pi_tipoanal_id=2)"
                + " AND (muestra.fi_muestra_matriz= 1 or muestra.fi_muestra_matriz=3)"
                + " order by pi_reporte_id ASC";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getResultList();
        } finally {
            em.close();
        }   
    }

    public List<Reporte> encontrarReportePorCadenaCustodia(Integer idCadenaCustodia) {
        EntityManager em = getEntityManager();
        String consulta = "Select * from reporte where fi_reporte_muestreo = ?";
        try {
            Query q = em.createNativeQuery(consulta, Reporte.class);
            q.setParameter(1, idCadenaCustodia);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> cargarTablaMuestras(String idreporte, String versionReporte) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT muestra.pi_muestra_id, valor.s_valor_nombre,"
                + " muestra.d_muestra_fechanal, valor.s_valor_valor,"
                + " muestra_analisis_historico.s_muestra_analisis_historico_proveedor_variable_metodo,"
                + " muestra_analisis_historico.s_muestra_analisis_historico_proveedor_varlimcuan,"
                + " muestra_analisis_historico.s_muestra_analisis_historico_proveedor_variable_unidad,"
                + " muestra_analisis.pi_muestraanal_id"
                + " FROM muestra"
                + " join muestra_analisis on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra"
                + " join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id"
                + " join muestreo on muestreo.pi_muestreo_id=muestra.fi_muestra_muestreo"
                + " join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable"
                + " join proveedor_variable_analisis on proveedor_variable_analisis.pfi_provarana_variable= variable_analisis.pi_varianal_id"
                + " join proveedor on proveedor.ps_proveedor_nit= proveedor_variable_analisis.pfs_provarana_proveedor"
                + " join muestra_analisis_historico on muestra_analisis_historico.pi_muestra_analisis_historico_id= muestra_analisis.pi_muestraanal_id"
                + " where fs_muestra_reporte=? and proveedor.s_proveedor_nombre=muestra_analisis.fs_muestraanal_proveedor"
                + " and muestra_analisis_historico.fs_muestra_analisis_historico_reporte_version = ?  "
                + " group by muestra_analisis.pi_muestraanal_id";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idreporte);
            q.setParameter(2, versionReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    
    
  
    public void devolverEstadoReporte(String reporte) {
         EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET c_reporte_estado='P', d_reporte_fechaentrega = now()  WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, reporte);
        insercion.executeUpdate();
        em.getTransaction().commit();
    
    }

    public List<Object> tieneParametrosEurofins(Reporte reporteActual) {
         EntityManager em = getEntityManager();
        String consulta = "select muestra_analisis.pi_muestraanal_id from reporte"
                + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id"
                + " join muestra_analisis on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra"
                + " join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id"
                + " where reporte.pi_reporte_id=? and muestra_analisis.fs_muestraanal_proveedor='EUROFINS'"
                + " and valor.s_valor_valor is null";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporteActual.getPiReporteId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> tieneParametrosSubcontratados(Reporte reporteActual) {
         EntityManager em = getEntityManager();
        String consulta = "select muestra_analisis.pi_muestraanal_id from reporte"
                + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id"
                + " join muestra_analisis on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra"
                + " join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id"
                + " where reporte.pi_reporte_id=? and muestra_analisis.fs_muestraanal_proveedor!='CIMA'";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporteActual.getPiReporteId());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void llenarTablaHistorica(String idReporte, String versionReporte) {
         EntityManager em = getEntityManager();
        StoredProcedureQuery procedimientoAlmacenado = em.createStoredProcedureQuery("pa_llenar_tabla_historica");
        em.getTransaction().begin();
        procedimientoAlmacenado.registerStoredProcedureParameter("pi_reporte_id", String.class, ParameterMode.IN);
        procedimientoAlmacenado.registerStoredProcedureParameter("s_reporte_version", String.class, ParameterMode.IN);
        procedimientoAlmacenado.setParameter("pi_reporte_id", idReporte);
        procedimientoAlmacenado.setParameter("s_reporte_version", versionReporte);
        procedimientoAlmacenado.execute();
        em.getTransaction().commit();
        em.close();
    }

    public List<Object> consultarClientePorReporte(Reporte reporteActual) {
     EntityManager em = getEntityManager();
        String consulta = "select ps_cliente_nit, s_cliente_nombre ,"
                + " s_cliente_direccion," +
                " s_cliente_contacto, s_cliente_telefono, s_cliente_correo, fs_reporte_usuacread"
               +" from cliente" 
                +" join reporte on reporte.fi_reporte_cliente= cliente.ps_cliente_nit" +
                " where reporte.pi_reporte_id=?" +
                " group by s_cliente_nombre ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporteActual.getPiReporteId());
            return q.getResultList();
        } finally {
            em.close();
        }  
    }
    
     public List<Object> consultarClienteFacturacion(Reporte reporteActual) {
     EntityManager em = getEntityManager();
        String consulta = "select ps_cliente_nit, s_cliente_nombre ,"
                + " s_cliente_direccion," +
                " s_cliente_contacto, s_cliente_telefono, s_cliente_correo, fs_reporte_usuacread"
               +" from cliente" 
                +" join reporte on reporte.fi_reporte_facturacion= cliente.ps_cliente_nit" +
                " where reporte.pi_reporte_id=?" +
                " group by s_cliente_nombre ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporteActual.getPiReporteId());
            return q.getResultList();
        } finally {
            em.close();
        }  
    }
    
    public void actualizarReporteProyecto(Reporte reporteActual) {

        EntityManager em = getEntityManager();
        String insert = "UPDATE reporte SET fi_reporte_proyecto=?, d_reporte_fechmodi =NOW()  WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, reporteActual.getIdProyecto().getIdProyecto());
        insercion.setParameter(2, reporteActual.getPiReporteId());
        insercion.executeUpdate();
        em.getTransaction().commit();
    
    }

    public List<Object> encontrarReportesPendientes(VariableAnalisis variableAnalisis, Proveedor proveedor) {
         EntityManager em = getEntityManager();
        String consulta = "SELECT distinct reporte.pi_reporte_id FROM muestra_analisis "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join reporte on reporte.pi_reporte_id= muestra.fs_muestra_reporte "
                + "where fi_muestraanal_variable=? and fs_muestraanal_proveedor=? "
                + "and valor.s_valor_valor is null "
                + "and reporte.c_reporte_estado!='A'";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis.getPiVarianalId());
            q.setParameter(2, proveedor.getSProveedorNombre());
            return q.getResultList();
        } finally {
            em.close();
        
    }
    }

    public List<Object> encontrarReportePorMuestraAnalisis(int idMuestraAnalisis) {
        EntityManager em = getEntityManager();
        String consulta =" select pi_reporte_id from reporte"
                + " join muestra on muestra.fs_muestra_reporte= reporte.pi_reporte_id"
                + " join muestra_analisis on muestra_analisis.fi_muestraanal_muestra = muestra.pi_muestra_id"
                + " where pi_muestraanal_id= ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestraAnalisis);
            return q.getResultList();
        } finally {
            em.close();
    }
    }
    
    public List<Object> encontrarMuestrasPendiente(String idReporte) {
        EntityManager em = getEntityManager();
        String consulta ="select count(*) from muestra"
                + " join muestra_analisis on muestra_analisis.fi_muestraanal_muestra = muestra.pi_muestra_id"
                + " join valor on valor.fi_valor_muestraanal = muestra_analisis.pi_muestraanal_id"
                + " where fs_muestra_reporte =? and valor.s_valor_valor is null";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
    }
    }

    public void actualizarEstadoReporte(String piReporteId, String version, 
                                        String usuarioModificador , boolean completo) {
         EntityManager em = getEntityManager();
         String actualizarReporte = null;
         if(completo){ 
              actualizarReporte = "update reporte set s_reporte_version=?,"
                     + " fs_reporte_usuaaprob=null,"
                     + " fs_reporte_usuarevi=null,"
                     + " c_reporte_estado='O',"
                     + " d_reporte_fechmodi=now(),"
                     + " fs_reporte_usuamodi = ?, "
                     + " d_reporte_fechapro= null,"
                     + " d_reporte_fecharevi= null,"
                     + " d_reporte_fechaentrega= now()"
                     + " where reporte.pi_reporte_id= ? ";
         }
         else{
             actualizarReporte = "update reporte set s_reporte_version=?,"
                     + " fs_reporte_usuaaprob=null,"
                     + " fs_reporte_usuarevi=null,"
                     + " c_reporte_estado='P',"
                     + " d_reporte_fechmodi=now(),"
                     + " fs_reporte_usuamodi = ?, "
                     + " d_reporte_fechapro= null,"
                     + " d_reporte_fecharevi= null,"
                     + " d_reporte_fechaentrega= null"
                     + " where reporte.pi_reporte_id= ? ";
         } 
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(actualizarReporte);
        insercion.setParameter(1, version);
        insercion.setParameter(2, usuarioModificador);
        insercion.setParameter(3, piReporteId);
        insercion.executeUpdate();
        em.getTransaction().commit();
    
     }

    public List<Object> encontrarEstadoReporte(String reporte) {
        EntityManager em = getEntityManager();
        String consulta = "select c_reporte_estado from reporte "
                +" where pi_reporte_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            return q.getResultList();
        } finally {
            em.close();
        }   }

    public List<Object> encontrarVersionReporte(String reporte) {
        EntityManager em = getEntityManager();
        String consulta = "select s_reporte_version from reporte"
                +" where pi_reporte_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            return q.getResultList();
        } finally {
            em.close();
        }  
    }

    public int estaCompletoReporte(String reporte) {
      EntityManager em = getEntityManager();
        String consulta = "select count(valor.pi_valor_id) "
                + "from reporte "
                + "join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "where pi_reporte_id=? "
                + "and valor.s_valor_valor is null";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            Long cantidad= (Long)q.getSingleResult(); 
            return cantidad.intValue();
        } finally {
            em.close();
        }  
    }

    public List<Object> encontrarReportePorProyectoAire(String nombre) {
      EntityManager em = getEntityManager();
        String consulta = "select reporte.pi_reporte_id "
                + "from muestreo "
                + "join proyecto on proyecto.pi_proyecto_id= muestreo.fi_muestreo_proyecto "
                + "join reporte on reporte.fi_reporte_muestreo = muestreo.pi_muestreo_id "
                + "join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id "
                + "where proyecto.s_proyecto_nombre= ? "
                + "and reporte.c_reporte_estado != 'A' and "
                + "muestra.fi_muestra_matriz = 7 "
                + "ORDER BY muestra.pi_muestra_id DESC "
                + "LIMIT 1";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nombre);
            return q.getResultList();
        } finally {
            em.close();
        }  
    }
    
     public List<Object> encontrarReportePorProyectoFuentesFijas(String nombre) {
      EntityManager em = getEntityManager();
        String consulta = "select reporte.pi_reporte_id "
                + "from muestreo "
                + "join proyecto on proyecto.pi_proyecto_id= muestreo.fi_muestreo_proyecto "
                + "join reporte on reporte.fi_reporte_muestreo = muestreo.pi_muestreo_id "
                + "join muestra on muestra.fi_muestra_muestreo = muestreo.pi_muestreo_id "
                + "where proyecto.s_proyecto_nombre= ? "
                + "and reporte.c_reporte_estado != 'A' and "
                + "muestra.fi_muestra_matriz = 8 "
                + "ORDER BY muestra.pi_muestra_id DESC "
                + "LIMIT 1";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, nombre);
            return q.getResultList();
        } finally {
            em.close();
        }  
    }

    public List<Integer> encontrarMuestreosPorReporte(String piReporteId, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "SELECT muestreo.pi_muestreo_id "
                + " FROM reporte "
                + " join proyecto on proyecto.pi_proyecto_id= reporte.fi_reporte_proyecto "
                + " join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id "
                + " join muestreo on muestreo.pi_muestreo_id= muestra.fi_muestra_muestreo"
                + " join cliente on (cliente.ps_cliente_nit = reporte.fi_reporte_cliente "
                + " or cliente.ps_cliente_nit= reporte.fi_reporte_facturacion) "
                + " WHERE pi_reporte_id=? "
                + " and muestra.pi_muestra_id between ? and ? "
                + " group by muestreo.pi_muestreo_id";
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
    
    public void actualizarClientePorReporte(Reporte reporte) {
         EntityManager em = getEntityManager();
        String consulta = "UPDATE reporte SET fi_reporte_cliente=?, "
                + "fi_reporte_facturacion= ? "
                + "WHERE pi_reporte_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(consulta);
        insercion.setParameter(1, reporte.getIdCliente().getClientePK().getPsClienteNit());
        insercion.setParameter(2, reporte.getClienteFacturacion().getClientePK().getPsClienteNit());
        insercion.setParameter(3, reporte.getPiReporteId());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Integer> encontrarMuestreosPorReporteUnico(String piReporteId) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT fi_reporte_muestreo FROM reporte "
                + "                 join proyecto on proyecto.pi_proyecto_id= reporte.fi_reporte_proyecto "
                + "                  join muestra on muestra.fs_muestra_reporte=reporte.pi_reporte_id "
                + "                  join cliente on (cliente.ps_cliente_nit = reporte.fi_reporte_cliente "
                + "                   or cliente.ps_cliente_nit= reporte.fi_reporte_facturacion) "
                + "                  WHERE pi_reporte_id=? "
                + "                  group by reporte.fi_reporte_muestreo order by d_reporte_fechcreaci";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piReporteId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
