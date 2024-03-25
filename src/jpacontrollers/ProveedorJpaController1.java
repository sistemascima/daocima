/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.Cotizacion;
import java.util.ArrayList;
import java.util.Collection;
import entidades.SoporteProveedor;
import entidades.EvaluacionProveedor;
import entidades.OrdenCompra;
import entidades.DetalleSolicitudCompra;
import entidades.MuestraAnalisis;
import entidades.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ProveedorJpaController1 implements Serializable {

    public ProveedorJpaController1(EntityManagerFactory emf) {
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
        if (proveedor.getSoporteProveedorCollection() == null) {
            proveedor.setSoporteProveedorCollection(new ArrayList<SoporteProveedor>());
        }
        if (proveedor.getEvaluacionProveedorCollection() == null) {
            proveedor.setEvaluacionProveedorCollection(new ArrayList<EvaluacionProveedor>());
        }
        if (proveedor.getOrdenCompraCollection() == null) {
            proveedor.setOrdenCompraCollection(new ArrayList<OrdenCompra>());
        }
        if (proveedor.getDetalleSolicitudCompraCollection() == null) {
            proveedor.setDetalleSolicitudCompraCollection(new ArrayList<DetalleSolicitudCompra>());
        }
        if (proveedor.getMuestraAnalisisList() == null) {
            proveedor.setMuestraAnalisisList(new ArrayList<MuestraAnalisis>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsProveedorUsuultmod = proveedor.getFsProveedorUsuultmod();
            if (fsProveedorUsuultmod != null) {
                fsProveedorUsuultmod = em.getReference(fsProveedorUsuultmod.getClass(), fsProveedorUsuultmod.getPsUsuarioCodigo());
                proveedor.setFsProveedorUsuultmod(fsProveedorUsuultmod);
            }
            SUsuario fsProveedorUsuacrea = proveedor.getFsProveedorUsuacrea();
            if (fsProveedorUsuacrea != null) {
                fsProveedorUsuacrea = em.getReference(fsProveedorUsuacrea.getClass(), fsProveedorUsuacrea.getPsUsuarioCodigo());
                proveedor.setFsProveedorUsuacrea(fsProveedorUsuacrea);
            }
            Collection<Cotizacion> attachedCotizacionCollection = new ArrayList<Cotizacion>();
            for (Cotizacion cotizacionCollectionCotizacionToAttach : proveedor.getCotizacionCollection()) {
                cotizacionCollectionCotizacionToAttach = em.getReference(cotizacionCollectionCotizacionToAttach.getClass(), cotizacionCollectionCotizacionToAttach.getCotizacionPK());
                attachedCotizacionCollection.add(cotizacionCollectionCotizacionToAttach);
            }
            proveedor.setCotizacionCollection(attachedCotizacionCollection);
            Collection<SoporteProveedor> attachedSoporteProveedorCollection = new ArrayList<SoporteProveedor>();
            for (SoporteProveedor soporteProveedorCollectionSoporteProveedorToAttach : proveedor.getSoporteProveedorCollection()) {
                soporteProveedorCollectionSoporteProveedorToAttach = em.getReference(soporteProveedorCollectionSoporteProveedorToAttach.getClass(), soporteProveedorCollectionSoporteProveedorToAttach.getSoporteProveedorPK());
                attachedSoporteProveedorCollection.add(soporteProveedorCollectionSoporteProveedorToAttach);
            }
            proveedor.setSoporteProveedorCollection(attachedSoporteProveedorCollection);
            Collection<EvaluacionProveedor> attachedEvaluacionProveedorCollection = new ArrayList<EvaluacionProveedor>();
            for (EvaluacionProveedor evaluacionProveedorCollectionEvaluacionProveedorToAttach : proveedor.getEvaluacionProveedorCollection()) {
                evaluacionProveedorCollectionEvaluacionProveedorToAttach = em.getReference(evaluacionProveedorCollectionEvaluacionProveedorToAttach.getClass(), evaluacionProveedorCollectionEvaluacionProveedorToAttach.getEvaluacionProveedorPK());
                attachedEvaluacionProveedorCollection.add(evaluacionProveedorCollectionEvaluacionProveedorToAttach);
            }
            proveedor.setEvaluacionProveedorCollection(attachedEvaluacionProveedorCollection);
            Collection<OrdenCompra> attachedOrdenCompraCollection = new ArrayList<OrdenCompra>();
            for (OrdenCompra ordenCompraCollectionOrdenCompraToAttach : proveedor.getOrdenCompraCollection()) {
                ordenCompraCollectionOrdenCompraToAttach = em.getReference(ordenCompraCollectionOrdenCompraToAttach.getClass(), ordenCompraCollectionOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraCollection.add(ordenCompraCollectionOrdenCompraToAttach);
            }
            proveedor.setOrdenCompraCollection(attachedOrdenCompraCollection);
            Collection<DetalleSolicitudCompra> attachedDetalleSolicitudCompraCollection = new ArrayList<DetalleSolicitudCompra>();
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionDetalleSolicitudCompraToAttach : proveedor.getDetalleSolicitudCompraCollection()) {
                detalleSolicitudCompraCollectionDetalleSolicitudCompraToAttach = em.getReference(detalleSolicitudCompraCollectionDetalleSolicitudCompraToAttach.getClass(), detalleSolicitudCompraCollectionDetalleSolicitudCompraToAttach.getDetalleSolicitudCompraPK());
                attachedDetalleSolicitudCompraCollection.add(detalleSolicitudCompraCollectionDetalleSolicitudCompraToAttach);
            }
            proveedor.setDetalleSolicitudCompraCollection(attachedDetalleSolicitudCompraCollection);
            List<MuestraAnalisis> attachedMuestraAnalisisList = new ArrayList<MuestraAnalisis>();
            for (MuestraAnalisis muestraAnalisisListMuestraAnalisisToAttach : proveedor.getMuestraAnalisisList()) {
                muestraAnalisisListMuestraAnalisisToAttach = em.getReference(muestraAnalisisListMuestraAnalisisToAttach.getClass(), muestraAnalisisListMuestraAnalisisToAttach.getIdmuestraAnalisis());
                attachedMuestraAnalisisList.add(muestraAnalisisListMuestraAnalisisToAttach);
            }
            proveedor.setMuestraAnalisisList(attachedMuestraAnalisisList);
            em.persist(proveedor);
            if (fsProveedorUsuultmod != null) {
                fsProveedorUsuultmod.getProveedorList().add(proveedor);
                fsProveedorUsuultmod = em.merge(fsProveedorUsuultmod);
            }
            if (fsProveedorUsuacrea != null) {
                fsProveedorUsuacrea.getProveedorList().add(proveedor);
                fsProveedorUsuacrea = em.merge(fsProveedorUsuacrea);
            }
            for (Cotizacion cotizacionCollectionCotizacion : proveedor.getCotizacionCollection()) {
                Proveedor oldProveedorOfCotizacionCollectionCotizacion = cotizacionCollectionCotizacion.getProveedor();
                cotizacionCollectionCotizacion.setProveedor(proveedor);
                cotizacionCollectionCotizacion = em.merge(cotizacionCollectionCotizacion);
                if (oldProveedorOfCotizacionCollectionCotizacion != null) {
                    oldProveedorOfCotizacionCollectionCotizacion.getCotizacionCollection().remove(cotizacionCollectionCotizacion);
                    oldProveedorOfCotizacionCollectionCotizacion = em.merge(oldProveedorOfCotizacionCollectionCotizacion);
                }
            }
            for (SoporteProveedor soporteProveedorCollectionSoporteProveedor : proveedor.getSoporteProveedorCollection()) {
                Proveedor oldProveedorOfSoporteProveedorCollectionSoporteProveedor = soporteProveedorCollectionSoporteProveedor.getProveedor();
                soporteProveedorCollectionSoporteProveedor.setProveedor(proveedor);
                soporteProveedorCollectionSoporteProveedor = em.merge(soporteProveedorCollectionSoporteProveedor);
                if (oldProveedorOfSoporteProveedorCollectionSoporteProveedor != null) {
                    oldProveedorOfSoporteProveedorCollectionSoporteProveedor.getSoporteProveedorCollection().remove(soporteProveedorCollectionSoporteProveedor);
                    oldProveedorOfSoporteProveedorCollectionSoporteProveedor = em.merge(oldProveedorOfSoporteProveedorCollectionSoporteProveedor);
                }
            }
            for (EvaluacionProveedor evaluacionProveedorCollectionEvaluacionProveedor : proveedor.getEvaluacionProveedorCollection()) {
                Proveedor oldProveedorOfEvaluacionProveedorCollectionEvaluacionProveedor = evaluacionProveedorCollectionEvaluacionProveedor.getProveedor();
                evaluacionProveedorCollectionEvaluacionProveedor.setProveedor(proveedor);
                evaluacionProveedorCollectionEvaluacionProveedor = em.merge(evaluacionProveedorCollectionEvaluacionProveedor);
                if (oldProveedorOfEvaluacionProveedorCollectionEvaluacionProveedor != null) {
                    oldProveedorOfEvaluacionProveedorCollectionEvaluacionProveedor.getEvaluacionProveedorCollection().remove(evaluacionProveedorCollectionEvaluacionProveedor);
                    oldProveedorOfEvaluacionProveedorCollectionEvaluacionProveedor = em.merge(oldProveedorOfEvaluacionProveedorCollectionEvaluacionProveedor);
                }
            }
            for (OrdenCompra ordenCompraCollectionOrdenCompra : proveedor.getOrdenCompraCollection()) {
                Proveedor oldFsOrdecompProveedorOfOrdenCompraCollectionOrdenCompra = ordenCompraCollectionOrdenCompra.getFsOrdecompProveedor();
                ordenCompraCollectionOrdenCompra.setFsOrdecompProveedor(proveedor);
                ordenCompraCollectionOrdenCompra = em.merge(ordenCompraCollectionOrdenCompra);
                if (oldFsOrdecompProveedorOfOrdenCompraCollectionOrdenCompra != null) {
                    oldFsOrdecompProveedorOfOrdenCompraCollectionOrdenCompra.getOrdenCompraCollection().remove(ordenCompraCollectionOrdenCompra);
                    oldFsOrdecompProveedorOfOrdenCompraCollectionOrdenCompra = em.merge(oldFsOrdecompProveedorOfOrdenCompraCollectionOrdenCompra);
                }
            }
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionDetalleSolicitudCompra : proveedor.getDetalleSolicitudCompraCollection()) {
                Proveedor oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionDetalleSolicitudCompra = detalleSolicitudCompraCollectionDetalleSolicitudCompra.getFsDetsolcomProvsele();
                detalleSolicitudCompraCollectionDetalleSolicitudCompra.setFsDetsolcomProvsele(proveedor);
                detalleSolicitudCompraCollectionDetalleSolicitudCompra = em.merge(detalleSolicitudCompraCollectionDetalleSolicitudCompra);
                if (oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionDetalleSolicitudCompra != null) {
                    oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionDetalleSolicitudCompra.getDetalleSolicitudCompraCollection().remove(detalleSolicitudCompraCollectionDetalleSolicitudCompra);
                    oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionDetalleSolicitudCompra = em.merge(oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionDetalleSolicitudCompra);
                }
            }
            for (MuestraAnalisis muestraAnalisisListMuestraAnalisis : proveedor.getMuestraAnalisisList()) {
                Proveedor oldFsMuestraanalProveedorOfMuestraAnalisisListMuestraAnalisis = muestraAnalisisListMuestraAnalisis.getFsMuestraanalProveedor();
                muestraAnalisisListMuestraAnalisis.setFsMuestraanalProveedor(proveedor);
                muestraAnalisisListMuestraAnalisis = em.merge(muestraAnalisisListMuestraAnalisis);
                if (oldFsMuestraanalProveedorOfMuestraAnalisisListMuestraAnalisis != null) {
                    oldFsMuestraanalProveedorOfMuestraAnalisisListMuestraAnalisis.getMuestraAnalisisList().remove(muestraAnalisisListMuestraAnalisis);
                    oldFsMuestraanalProveedorOfMuestraAnalisisListMuestraAnalisis = em.merge(oldFsMuestraanalProveedorOfMuestraAnalisisListMuestraAnalisis);
                }
            }
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
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getPsProveedorNit());
            SUsuario fsProveedorUsuultmodOld = persistentProveedor.getFsProveedorUsuultmod();
            SUsuario fsProveedorUsuultmodNew = proveedor.getFsProveedorUsuultmod();
            SUsuario fsProveedorUsuacreaOld = persistentProveedor.getFsProveedorUsuacrea();
            SUsuario fsProveedorUsuacreaNew = proveedor.getFsProveedorUsuacrea();
            Collection<Cotizacion> cotizacionCollectionOld = persistentProveedor.getCotizacionCollection();
            Collection<Cotizacion> cotizacionCollectionNew = proveedor.getCotizacionCollection();
            Collection<SoporteProveedor> soporteProveedorCollectionOld = persistentProveedor.getSoporteProveedorCollection();
            Collection<SoporteProveedor> soporteProveedorCollectionNew = proveedor.getSoporteProveedorCollection();
            Collection<EvaluacionProveedor> evaluacionProveedorCollectionOld = persistentProveedor.getEvaluacionProveedorCollection();
            Collection<EvaluacionProveedor> evaluacionProveedorCollectionNew = proveedor.getEvaluacionProveedorCollection();
            Collection<OrdenCompra> ordenCompraCollectionOld = persistentProveedor.getOrdenCompraCollection();
            Collection<OrdenCompra> ordenCompraCollectionNew = proveedor.getOrdenCompraCollection();
            Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollectionOld = persistentProveedor.getDetalleSolicitudCompraCollection();
            Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollectionNew = proveedor.getDetalleSolicitudCompraCollection();
            List<MuestraAnalisis> muestraAnalisisListOld = persistentProveedor.getMuestraAnalisisList();
            List<MuestraAnalisis> muestraAnalisisListNew = proveedor.getMuestraAnalisisList();
            List<String> illegalOrphanMessages = null;
            for (Cotizacion cotizacionCollectionOldCotizacion : cotizacionCollectionOld) {
                if (!cotizacionCollectionNew.contains(cotizacionCollectionOldCotizacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cotizacion " + cotizacionCollectionOldCotizacion + " since its proveedor field is not nullable.");
                }
            }
            for (SoporteProveedor soporteProveedorCollectionOldSoporteProveedor : soporteProveedorCollectionOld) {
                if (!soporteProveedorCollectionNew.contains(soporteProveedorCollectionOldSoporteProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SoporteProveedor " + soporteProveedorCollectionOldSoporteProveedor + " since its proveedor field is not nullable.");
                }
            }
            for (EvaluacionProveedor evaluacionProveedorCollectionOldEvaluacionProveedor : evaluacionProveedorCollectionOld) {
                if (!evaluacionProveedorCollectionNew.contains(evaluacionProveedorCollectionOldEvaluacionProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EvaluacionProveedor " + evaluacionProveedorCollectionOldEvaluacionProveedor + " since its proveedor field is not nullable.");
                }
            }
            for (OrdenCompra ordenCompraCollectionOldOrdenCompra : ordenCompraCollectionOld) {
                if (!ordenCompraCollectionNew.contains(ordenCompraCollectionOldOrdenCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenCompra " + ordenCompraCollectionOldOrdenCompra + " since its fsOrdecompProveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fsProveedorUsuultmodNew != null) {
                fsProveedorUsuultmodNew = em.getReference(fsProveedorUsuultmodNew.getClass(), fsProveedorUsuultmodNew.getPsUsuarioCodigo());
                proveedor.setFsProveedorUsuultmod(fsProveedorUsuultmodNew);
            }
            if (fsProveedorUsuacreaNew != null) {
                fsProveedorUsuacreaNew = em.getReference(fsProveedorUsuacreaNew.getClass(), fsProveedorUsuacreaNew.getPsUsuarioCodigo());
                proveedor.setFsProveedorUsuacrea(fsProveedorUsuacreaNew);
            }
            Collection<Cotizacion> attachedCotizacionCollectionNew = new ArrayList<Cotizacion>();
            for (Cotizacion cotizacionCollectionNewCotizacionToAttach : cotizacionCollectionNew) {
                cotizacionCollectionNewCotizacionToAttach = em.getReference(cotizacionCollectionNewCotizacionToAttach.getClass(), cotizacionCollectionNewCotizacionToAttach.getCotizacionPK());
                attachedCotizacionCollectionNew.add(cotizacionCollectionNewCotizacionToAttach);
            }
            cotizacionCollectionNew = attachedCotizacionCollectionNew;
            proveedor.setCotizacionCollection(cotizacionCollectionNew);
            Collection<SoporteProveedor> attachedSoporteProveedorCollectionNew = new ArrayList<SoporteProveedor>();
            for (SoporteProveedor soporteProveedorCollectionNewSoporteProveedorToAttach : soporteProveedorCollectionNew) {
                soporteProveedorCollectionNewSoporteProveedorToAttach = em.getReference(soporteProveedorCollectionNewSoporteProveedorToAttach.getClass(), soporteProveedorCollectionNewSoporteProveedorToAttach.getSoporteProveedorPK());
                attachedSoporteProveedorCollectionNew.add(soporteProveedorCollectionNewSoporteProveedorToAttach);
            }
            soporteProveedorCollectionNew = attachedSoporteProveedorCollectionNew;
            proveedor.setSoporteProveedorCollection(soporteProveedorCollectionNew);
            Collection<EvaluacionProveedor> attachedEvaluacionProveedorCollectionNew = new ArrayList<EvaluacionProveedor>();
            for (EvaluacionProveedor evaluacionProveedorCollectionNewEvaluacionProveedorToAttach : evaluacionProveedorCollectionNew) {
                evaluacionProveedorCollectionNewEvaluacionProveedorToAttach = em.getReference(evaluacionProveedorCollectionNewEvaluacionProveedorToAttach.getClass(), evaluacionProveedorCollectionNewEvaluacionProveedorToAttach.getEvaluacionProveedorPK());
                attachedEvaluacionProveedorCollectionNew.add(evaluacionProveedorCollectionNewEvaluacionProveedorToAttach);
            }
            evaluacionProveedorCollectionNew = attachedEvaluacionProveedorCollectionNew;
            proveedor.setEvaluacionProveedorCollection(evaluacionProveedorCollectionNew);
            Collection<OrdenCompra> attachedOrdenCompraCollectionNew = new ArrayList<OrdenCompra>();
            for (OrdenCompra ordenCompraCollectionNewOrdenCompraToAttach : ordenCompraCollectionNew) {
                ordenCompraCollectionNewOrdenCompraToAttach = em.getReference(ordenCompraCollectionNewOrdenCompraToAttach.getClass(), ordenCompraCollectionNewOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraCollectionNew.add(ordenCompraCollectionNewOrdenCompraToAttach);
            }
            ordenCompraCollectionNew = attachedOrdenCompraCollectionNew;
            proveedor.setOrdenCompraCollection(ordenCompraCollectionNew);
            Collection<DetalleSolicitudCompra> attachedDetalleSolicitudCompraCollectionNew = new ArrayList<DetalleSolicitudCompra>();
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionNewDetalleSolicitudCompraToAttach : detalleSolicitudCompraCollectionNew) {
                detalleSolicitudCompraCollectionNewDetalleSolicitudCompraToAttach = em.getReference(detalleSolicitudCompraCollectionNewDetalleSolicitudCompraToAttach.getClass(), detalleSolicitudCompraCollectionNewDetalleSolicitudCompraToAttach.getDetalleSolicitudCompraPK());
                attachedDetalleSolicitudCompraCollectionNew.add(detalleSolicitudCompraCollectionNewDetalleSolicitudCompraToAttach);
            }
            detalleSolicitudCompraCollectionNew = attachedDetalleSolicitudCompraCollectionNew;
            proveedor.setDetalleSolicitudCompraCollection(detalleSolicitudCompraCollectionNew);
            List<MuestraAnalisis> attachedMuestraAnalisisListNew = new ArrayList<MuestraAnalisis>();
            for (MuestraAnalisis muestraAnalisisListNewMuestraAnalisisToAttach : muestraAnalisisListNew) {
                muestraAnalisisListNewMuestraAnalisisToAttach = em.getReference(muestraAnalisisListNewMuestraAnalisisToAttach.getClass(), muestraAnalisisListNewMuestraAnalisisToAttach.getIdmuestraAnalisis());
                attachedMuestraAnalisisListNew.add(muestraAnalisisListNewMuestraAnalisisToAttach);
            }
            muestraAnalisisListNew = attachedMuestraAnalisisListNew;
            proveedor.setMuestraAnalisisList(muestraAnalisisListNew);
            proveedor = em.merge(proveedor);
            if (fsProveedorUsuultmodOld != null && !fsProveedorUsuultmodOld.equals(fsProveedorUsuultmodNew)) {
                fsProveedorUsuultmodOld.getProveedorList().remove(proveedor);
                fsProveedorUsuultmodOld = em.merge(fsProveedorUsuultmodOld);
            }
            if (fsProveedorUsuultmodNew != null && !fsProveedorUsuultmodNew.equals(fsProveedorUsuultmodOld)) {
                fsProveedorUsuultmodNew.getProveedorList().add(proveedor);
                fsProveedorUsuultmodNew = em.merge(fsProveedorUsuultmodNew);
            }
            if (fsProveedorUsuacreaOld != null && !fsProveedorUsuacreaOld.equals(fsProveedorUsuacreaNew)) {
                fsProveedorUsuacreaOld.getProveedorList().remove(proveedor);
                fsProveedorUsuacreaOld = em.merge(fsProveedorUsuacreaOld);
            }
            if (fsProveedorUsuacreaNew != null && !fsProveedorUsuacreaNew.equals(fsProveedorUsuacreaOld)) {
                fsProveedorUsuacreaNew.getProveedorList().add(proveedor);
                fsProveedorUsuacreaNew = em.merge(fsProveedorUsuacreaNew);
            }
            for (Cotizacion cotizacionCollectionNewCotizacion : cotizacionCollectionNew) {
                if (!cotizacionCollectionOld.contains(cotizacionCollectionNewCotizacion)) {
                    Proveedor oldProveedorOfCotizacionCollectionNewCotizacion = cotizacionCollectionNewCotizacion.getProveedor();
                    cotizacionCollectionNewCotizacion.setProveedor(proveedor);
                    cotizacionCollectionNewCotizacion = em.merge(cotizacionCollectionNewCotizacion);
                    if (oldProveedorOfCotizacionCollectionNewCotizacion != null && !oldProveedorOfCotizacionCollectionNewCotizacion.equals(proveedor)) {
                        oldProveedorOfCotizacionCollectionNewCotizacion.getCotizacionCollection().remove(cotizacionCollectionNewCotizacion);
                        oldProveedorOfCotizacionCollectionNewCotizacion = em.merge(oldProveedorOfCotizacionCollectionNewCotizacion);
                    }
                }
            }
            for (SoporteProveedor soporteProveedorCollectionNewSoporteProveedor : soporteProveedorCollectionNew) {
                if (!soporteProveedorCollectionOld.contains(soporteProveedorCollectionNewSoporteProveedor)) {
                    Proveedor oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor = soporteProveedorCollectionNewSoporteProveedor.getProveedor();
                    soporteProveedorCollectionNewSoporteProveedor.setProveedor(proveedor);
                    soporteProveedorCollectionNewSoporteProveedor = em.merge(soporteProveedorCollectionNewSoporteProveedor);
                    if (oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor != null && !oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor.equals(proveedor)) {
                        oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor.getSoporteProveedorCollection().remove(soporteProveedorCollectionNewSoporteProveedor);
                        oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor = em.merge(oldProveedorOfSoporteProveedorCollectionNewSoporteProveedor);
                    }
                }
            }
            for (EvaluacionProveedor evaluacionProveedorCollectionNewEvaluacionProveedor : evaluacionProveedorCollectionNew) {
                if (!evaluacionProveedorCollectionOld.contains(evaluacionProveedorCollectionNewEvaluacionProveedor)) {
                    Proveedor oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor = evaluacionProveedorCollectionNewEvaluacionProveedor.getProveedor();
                    evaluacionProveedorCollectionNewEvaluacionProveedor.setProveedor(proveedor);
                    evaluacionProveedorCollectionNewEvaluacionProveedor = em.merge(evaluacionProveedorCollectionNewEvaluacionProveedor);
                    if (oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor != null && !oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor.equals(proveedor)) {
                        oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor.getEvaluacionProveedorCollection().remove(evaluacionProveedorCollectionNewEvaluacionProveedor);
                        oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor = em.merge(oldProveedorOfEvaluacionProveedorCollectionNewEvaluacionProveedor);
                    }
                }
            }
            for (OrdenCompra ordenCompraCollectionNewOrdenCompra : ordenCompraCollectionNew) {
                if (!ordenCompraCollectionOld.contains(ordenCompraCollectionNewOrdenCompra)) {
                    Proveedor oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra = ordenCompraCollectionNewOrdenCompra.getFsOrdecompProveedor();
                    ordenCompraCollectionNewOrdenCompra.setFsOrdecompProveedor(proveedor);
                    ordenCompraCollectionNewOrdenCompra = em.merge(ordenCompraCollectionNewOrdenCompra);
                    if (oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra != null && !oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra.equals(proveedor)) {
                        oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra.getOrdenCompraCollection().remove(ordenCompraCollectionNewOrdenCompra);
                        oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra = em.merge(oldFsOrdecompProveedorOfOrdenCompraCollectionNewOrdenCompra);
                    }
                }
            }
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionOldDetalleSolicitudCompra : detalleSolicitudCompraCollectionOld) {
                if (!detalleSolicitudCompraCollectionNew.contains(detalleSolicitudCompraCollectionOldDetalleSolicitudCompra)) {
                    detalleSolicitudCompraCollectionOldDetalleSolicitudCompra.setFsDetsolcomProvsele(null);
                    detalleSolicitudCompraCollectionOldDetalleSolicitudCompra = em.merge(detalleSolicitudCompraCollectionOldDetalleSolicitudCompra);
                }
            }
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionNewDetalleSolicitudCompra : detalleSolicitudCompraCollectionNew) {
                if (!detalleSolicitudCompraCollectionOld.contains(detalleSolicitudCompraCollectionNewDetalleSolicitudCompra)) {
                    Proveedor oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra = detalleSolicitudCompraCollectionNewDetalleSolicitudCompra.getFsDetsolcomProvsele();
                    detalleSolicitudCompraCollectionNewDetalleSolicitudCompra.setFsDetsolcomProvsele(proveedor);
                    detalleSolicitudCompraCollectionNewDetalleSolicitudCompra = em.merge(detalleSolicitudCompraCollectionNewDetalleSolicitudCompra);
                    if (oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra != null && !oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra.equals(proveedor)) {
                        oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra.getDetalleSolicitudCompraCollection().remove(detalleSolicitudCompraCollectionNewDetalleSolicitudCompra);
                        oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra = em.merge(oldFsDetsolcomProvseleOfDetalleSolicitudCompraCollectionNewDetalleSolicitudCompra);
                    }
                }
            }
            for (MuestraAnalisis muestraAnalisisListOldMuestraAnalisis : muestraAnalisisListOld) {
                if (!muestraAnalisisListNew.contains(muestraAnalisisListOldMuestraAnalisis)) {
                    muestraAnalisisListOldMuestraAnalisis.setFsMuestraanalProveedor(null);
                    muestraAnalisisListOldMuestraAnalisis = em.merge(muestraAnalisisListOldMuestraAnalisis);
                }
            }
            for (MuestraAnalisis muestraAnalisisListNewMuestraAnalisis : muestraAnalisisListNew) {
                if (!muestraAnalisisListOld.contains(muestraAnalisisListNewMuestraAnalisis)) {
                    Proveedor oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis = muestraAnalisisListNewMuestraAnalisis.getFsMuestraanalProveedor();
                    muestraAnalisisListNewMuestraAnalisis.setFsMuestraanalProveedor(proveedor);
                    muestraAnalisisListNewMuestraAnalisis = em.merge(muestraAnalisisListNewMuestraAnalisis);
                    if (oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis != null && !oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis.equals(proveedor)) {
                        oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis.getMuestraAnalisisList().remove(muestraAnalisisListNewMuestraAnalisis);
                        oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis = em.merge(oldFsMuestraanalProveedorOfMuestraAnalisisListNewMuestraAnalisis);
                    }
                }
            }
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
            List<String> illegalOrphanMessages = null;
            Collection<Cotizacion> cotizacionCollectionOrphanCheck = proveedor.getCotizacionCollection();
            for (Cotizacion cotizacionCollectionOrphanCheckCotizacion : cotizacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Cotizacion " + cotizacionCollectionOrphanCheckCotizacion + " in its cotizacionCollection field has a non-nullable proveedor field.");
            }
            Collection<SoporteProveedor> soporteProveedorCollectionOrphanCheck = proveedor.getSoporteProveedorCollection();
            for (SoporteProveedor soporteProveedorCollectionOrphanCheckSoporteProveedor : soporteProveedorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the SoporteProveedor " + soporteProveedorCollectionOrphanCheckSoporteProveedor + " in its soporteProveedorCollection field has a non-nullable proveedor field.");
            }
            Collection<EvaluacionProveedor> evaluacionProveedorCollectionOrphanCheck = proveedor.getEvaluacionProveedorCollection();
            for (EvaluacionProveedor evaluacionProveedorCollectionOrphanCheckEvaluacionProveedor : evaluacionProveedorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the EvaluacionProveedor " + evaluacionProveedorCollectionOrphanCheckEvaluacionProveedor + " in its evaluacionProveedorCollection field has a non-nullable proveedor field.");
            }
            Collection<OrdenCompra> ordenCompraCollectionOrphanCheck = proveedor.getOrdenCompraCollection();
            for (OrdenCompra ordenCompraCollectionOrphanCheckOrdenCompra : ordenCompraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the OrdenCompra " + ordenCompraCollectionOrphanCheckOrdenCompra + " in its ordenCompraCollection field has a non-nullable fsOrdecompProveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SUsuario fsProveedorUsuultmod = proveedor.getFsProveedorUsuultmod();
            if (fsProveedorUsuultmod != null) {
                fsProveedorUsuultmod.getProveedorList().remove(proveedor);
                fsProveedorUsuultmod = em.merge(fsProveedorUsuultmod);
            }
            SUsuario fsProveedorUsuacrea = proveedor.getFsProveedorUsuacrea();
            if (fsProveedorUsuacrea != null) {
                fsProveedorUsuacrea.getProveedorList().remove(proveedor);
                fsProveedorUsuacrea = em.merge(fsProveedorUsuacrea);
            }
            Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollection = proveedor.getDetalleSolicitudCompraCollection();
            for (DetalleSolicitudCompra detalleSolicitudCompraCollectionDetalleSolicitudCompra : detalleSolicitudCompraCollection) {
                detalleSolicitudCompraCollectionDetalleSolicitudCompra.setFsDetsolcomProvsele(null);
                detalleSolicitudCompraCollectionDetalleSolicitudCompra = em.merge(detalleSolicitudCompraCollectionDetalleSolicitudCompra);
            }
            List<MuestraAnalisis> muestraAnalisisList = proveedor.getMuestraAnalisisList();
            for (MuestraAnalisis muestraAnalisisListMuestraAnalisis : muestraAnalisisList) {
                muestraAnalisisListMuestraAnalisis.setFsMuestraanalProveedor(null);
                muestraAnalisisListMuestraAnalisis = em.merge(muestraAnalisisListMuestraAnalisis);
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
    
}
