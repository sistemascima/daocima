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
import entidades.TipoDocumento;
import entidades.Proceso;
import entidades.Cargo;
import entidades.AccesoDocumento;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ComentarioDocumento;
import entidades.ComentarioDocumentoPK;
import entidades.Documento;
import entidades.DocumentoPK;
import entidades.SolicitudCompra;
import java.util.List;
import entidades.OrdenCompra;
import java.util.LinkedList;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class DocumentoJpaController implements Serializable {

    public DocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) throws PreexistingEntityException, Exception {
       /* if (documento.getDocumentoPK() == null) {
            documento.setDocumentoPK(new DocumentoPK());
        }
        if (documento.getAccesoDocumentoCollection() == null) {
            documento.setAccesoDocumentoCollection(new ArrayList<AccesoDocumento>());
        }
        if (documento.getComentarioDocumentoCollection() == null) {
            documento.setComentarioDocumentoCollection(new ArrayList<ComentarioDocumento>());
        }
        if (documento.getSolicitudCompraList() == null) {
            documento.setSolicitudCompraList(new ArrayList<SolicitudCompra>());
        }
        if (documento.getOrdenCompraList() == null) {
            documento.setOrdenCompraList(new ArrayList<OrdenCompra>());
        }
        documento.getDocumentoPK().setPfsDocumentTipodocu(documento.getTipoDocumento().getPsTipodocuId());
        documento.getDocumentoPK().setPfsDocumentLetrproc(documento.getProceso().getSProcesoLetrdocu());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsDocumentUsuarevi = documento.getFsDocumentUsuarevi();
            if (fsDocumentUsuarevi != null) {
                fsDocumentUsuarevi = em.getReference(fsDocumentUsuarevi.getClass(), fsDocumentUsuarevi.getPsUsuarioCodigo());
                documento.setFsDocumentUsuarevi(fsDocumentUsuarevi);
            }
            SUsuario fsDocumentUsuaelab = documento.getFsDocumentUsuaelab();
            if (fsDocumentUsuaelab != null) {
                fsDocumentUsuaelab = em.getReference(fsDocumentUsuaelab.getClass(), fsDocumentUsuaelab.getPsUsuarioCodigo());
                documento.setFsDocumentUsuaelab(fsDocumentUsuaelab);
            }
            SUsuario fsDocumentUsuaapru = documento.getFsDocumentUsuaapru();
            if (fsDocumentUsuaapru != null) {
                fsDocumentUsuaapru = em.getReference(fsDocumentUsuaapru.getClass(), fsDocumentUsuaapru.getPsUsuarioCodigo());
                documento.setFsDocumentUsuaapru(fsDocumentUsuaapru);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getPsTipodocuId());
                documento.setTipoDocumento(tipoDocumento);
            }
            Proceso proceso = documento.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getPiProcesoId());
                documento.setProceso(proceso);
            }
            Cargo fsDocumentCargrevi = documento.getFsDocumentCargrevi();
            if (fsDocumentCargrevi != null) {
                fsDocumentCargrevi = em.getReference(fsDocumentCargrevi.getClass(), fsDocumentCargrevi.getPsCargoCodigo());
                documento.setFsDocumentCargrevi(fsDocumentCargrevi);
            }
            Cargo fsDocumentCargelab = documento.getFsDocumentCargelab();
            if (fsDocumentCargelab != null) {
                fsDocumentCargelab = em.getReference(fsDocumentCargelab.getClass(), fsDocumentCargelab.getPsCargoCodigo());
                documento.setFsDocumentCargelab(fsDocumentCargelab);
            }
            Cargo fsDocumentCargapru = documento.getFsDocumentCargapru();
            if (fsDocumentCargapru != null) {
                fsDocumentCargapru = em.getReference(fsDocumentCargapru.getClass(), fsDocumentCargapru.getPsCargoCodigo());
                documento.setFsDocumentCargapru(fsDocumentCargapru);
            }
            Collection<AccesoDocumento> attachedAccesoDocumentoCollection = new ArrayList<AccesoDocumento>();
            for (AccesoDocumento accesoDocumentoCollectionAccesoDocumentoToAttach : documento.getAccesoDocumentoCollection()) {
                accesoDocumentoCollectionAccesoDocumentoToAttach = em.getReference(accesoDocumentoCollectionAccesoDocumentoToAttach.getClass(), accesoDocumentoCollectionAccesoDocumentoToAttach.getAccesoDocumentoPK());
                attachedAccesoDocumentoCollection.add(accesoDocumentoCollectionAccesoDocumentoToAttach);
            }
            documento.setAccesoDocumentoCollection(attachedAccesoDocumentoCollection);
            Collection<ComentarioDocumento> attachedComentarioDocumentoCollection = new ArrayList<ComentarioDocumento>();
            for (ComentarioDocumento comentarioDocumentoCollectionComentarioDocumentoToAttach : documento.getComentarioDocumentoCollection()) {
                comentarioDocumentoCollectionComentarioDocumentoToAttach = em.getReference(comentarioDocumentoCollectionComentarioDocumentoToAttach.getClass(), comentarioDocumentoCollectionComentarioDocumentoToAttach.getComentarioDocumentoPK());
                attachedComentarioDocumentoCollection.add(comentarioDocumentoCollectionComentarioDocumentoToAttach);
            }
            documento.setComentarioDocumentoCollection(attachedComentarioDocumentoCollection);
            List<SolicitudCompra> attachedSolicitudCompraList = new ArrayList<SolicitudCompra>();
            for (SolicitudCompra solicitudCompraListSolicitudCompraToAttach : documento.getSolicitudCompraList()) {
                solicitudCompraListSolicitudCompraToAttach = em.getReference(solicitudCompraListSolicitudCompraToAttach.getClass(), solicitudCompraListSolicitudCompraToAttach.getPiSolicompConsecutivo());
                attachedSolicitudCompraList.add(solicitudCompraListSolicitudCompraToAttach);
            }
            documento.setSolicitudCompraList(attachedSolicitudCompraList);
            List<OrdenCompra> attachedOrdenCompraList = new ArrayList<OrdenCompra>();
            for (OrdenCompra ordenCompraListOrdenCompraToAttach : documento.getOrdenCompraList()) {
                ordenCompraListOrdenCompraToAttach = em.getReference(ordenCompraListOrdenCompraToAttach.getClass(), ordenCompraListOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraList.add(ordenCompraListOrdenCompraToAttach);
            }
            documento.setOrdenCompraList(attachedOrdenCompraList);
            em.persist(documento);
            if (fsDocumentUsuarevi != null) {
                fsDocumentUsuarevi.getDocumentoCollection().add(documento);
                fsDocumentUsuarevi = em.merge(fsDocumentUsuarevi);
            }
            if (fsDocumentUsuaelab != null) {
                fsDocumentUsuaelab.getDocumentoCollection().add(documento);
                fsDocumentUsuaelab = em.merge(fsDocumentUsuaelab);
            }
            if (fsDocumentUsuaapru != null) {
                fsDocumentUsuaapru.getDocumentoCollection().add(documento);
                fsDocumentUsuaapru = em.merge(fsDocumentUsuaapru);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().add(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            if (proceso != null) {
                proceso.getDocumentoCollection().add(documento);
                proceso = em.merge(proceso);
            }
            if (fsDocumentCargrevi != null) {
                fsDocumentCargrevi.getDocumentoCollection().add(documento);
                fsDocumentCargrevi = em.merge(fsDocumentCargrevi);
            }
            if (fsDocumentCargelab != null) {
                fsDocumentCargelab.getDocumentoCollection().add(documento);
                fsDocumentCargelab = em.merge(fsDocumentCargelab);
            }
            if (fsDocumentCargapru != null) {
                fsDocumentCargapru.getDocumentoCollection().add(documento);
                fsDocumentCargapru = em.merge(fsDocumentCargapru);
            }
            for (AccesoDocumento accesoDocumentoCollectionAccesoDocumento : documento.getAccesoDocumentoCollection()) {
                Documento oldDocumentoOfAccesoDocumentoCollectionAccesoDocumento = accesoDocumentoCollectionAccesoDocumento.getDocumento();
                accesoDocumentoCollectionAccesoDocumento.setDocumento(documento);
                accesoDocumentoCollectionAccesoDocumento = em.merge(accesoDocumentoCollectionAccesoDocumento);
                if (oldDocumentoOfAccesoDocumentoCollectionAccesoDocumento != null) {
                    oldDocumentoOfAccesoDocumentoCollectionAccesoDocumento.getAccesoDocumentoCollection().remove(accesoDocumentoCollectionAccesoDocumento);
                    oldDocumentoOfAccesoDocumentoCollectionAccesoDocumento = em.merge(oldDocumentoOfAccesoDocumentoCollectionAccesoDocumento);
                }
            }
            for (ComentarioDocumento comentarioDocumentoCollectionComentarioDocumento : documento.getComentarioDocumentoCollection()) {
                Documento oldDocumentoOfComentarioDocumentoCollectionComentarioDocumento = comentarioDocumentoCollectionComentarioDocumento.getDocumento();
                comentarioDocumentoCollectionComentarioDocumento.setDocumento(documento);
                comentarioDocumentoCollectionComentarioDocumento = em.merge(comentarioDocumentoCollectionComentarioDocumento);
                if (oldDocumentoOfComentarioDocumentoCollectionComentarioDocumento != null) {
                    oldDocumentoOfComentarioDocumentoCollectionComentarioDocumento.getComentarioDocumentoCollection().remove(comentarioDocumentoCollectionComentarioDocumento);
                    oldDocumentoOfComentarioDocumentoCollectionComentarioDocumento = em.merge(oldDocumentoOfComentarioDocumentoCollectionComentarioDocumento);
                }
            }
            for (SolicitudCompra solicitudCompraListSolicitudCompra : documento.getSolicitudCompraList()) {
                Documento oldDocumentoOfSolicitudCompraListSolicitudCompra = solicitudCompraListSolicitudCompra.getDocumento();
                solicitudCompraListSolicitudCompra.setDocumento(documento);
                solicitudCompraListSolicitudCompra = em.merge(solicitudCompraListSolicitudCompra);
                if (oldDocumentoOfSolicitudCompraListSolicitudCompra != null) {
                    oldDocumentoOfSolicitudCompraListSolicitudCompra.getSolicitudCompraList().remove(solicitudCompraListSolicitudCompra);
                    oldDocumentoOfSolicitudCompraListSolicitudCompra = em.merge(oldDocumentoOfSolicitudCompraListSolicitudCompra);
                }
            }
            for (OrdenCompra ordenCompraListOrdenCompra : documento.getOrdenCompraList()) {
                Documento oldDocumentoOfOrdenCompraListOrdenCompra = ordenCompraListOrdenCompra.getDocumento();
                ordenCompraListOrdenCompra.setDocumento(documento);
                ordenCompraListOrdenCompra = em.merge(ordenCompraListOrdenCompra);
                if (oldDocumentoOfOrdenCompraListOrdenCompra != null) {
                    oldDocumentoOfOrdenCompraListOrdenCompra.getOrdenCompraList().remove(ordenCompraListOrdenCompra);
                    oldDocumentoOfOrdenCompraListOrdenCompra = em.merge(oldDocumentoOfOrdenCompraListOrdenCompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocumento(documento.getDocumentoPK()) != null) {
                throw new PreexistingEntityException("Documento " + documento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public void edit(Documento documento) throws IllegalOrphanException, NonexistentEntityException, Exception {
       /* documento.getDocumentoPK().setPfsDocumentTipodocu(documento.getTipoDocumento().getPsTipodocuId());
        documento.getDocumentoPK().setPfsDocumentLetrproc(documento.getProceso().getSProcesoLetrdocu());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getDocumentoPK());
            SUsuario fsDocumentUsuareviOld = persistentDocumento.getFsDocumentUsuarevi();
            SUsuario fsDocumentUsuareviNew = documento.getFsDocumentUsuarevi();
            SUsuario fsDocumentUsuaelabOld = persistentDocumento.getFsDocumentUsuaelab();
            SUsuario fsDocumentUsuaelabNew = documento.getFsDocumentUsuaelab();
            SUsuario fsDocumentUsuaapruOld = persistentDocumento.getFsDocumentUsuaapru();
            SUsuario fsDocumentUsuaapruNew = documento.getFsDocumentUsuaapru();
            TipoDocumento tipoDocumentoOld = persistentDocumento.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = documento.getTipoDocumento();
            Proceso procesoOld = persistentDocumento.getProceso();
            Proceso procesoNew = documento.getProceso();
            Cargo fsDocumentCargreviOld = persistentDocumento.getFsDocumentCargrevi();
            Cargo fsDocumentCargreviNew = documento.getFsDocumentCargrevi();
            Cargo fsDocumentCargelabOld = persistentDocumento.getFsDocumentCargelab();
            Cargo fsDocumentCargelabNew = documento.getFsDocumentCargelab();
            Cargo fsDocumentCargapruOld = persistentDocumento.getFsDocumentCargapru();
            Cargo fsDocumentCargapruNew = documento.getFsDocumentCargapru();
            Collection<AccesoDocumento> accesoDocumentoCollectionOld = persistentDocumento.getAccesoDocumentoCollection();
            Collection<AccesoDocumento> accesoDocumentoCollectionNew = documento.getAccesoDocumentoCollection();
            Collection<ComentarioDocumento> comentarioDocumentoCollectionOld = persistentDocumento.getComentarioDocumentoCollection();
            Collection<ComentarioDocumento> comentarioDocumentoCollectionNew = documento.getComentarioDocumentoCollection();
            List<SolicitudCompra> solicitudCompraListOld = persistentDocumento.getSolicitudCompraList();
            List<SolicitudCompra> solicitudCompraListNew = documento.getSolicitudCompraList();
            List<OrdenCompra> ordenCompraListOld = persistentDocumento.getOrdenCompraList();
            List<OrdenCompra> ordenCompraListNew = documento.getOrdenCompraList();
            List<String> illegalOrphanMessages = null;
            for (AccesoDocumento accesoDocumentoCollectionOldAccesoDocumento : accesoDocumentoCollectionOld) {
                if (!accesoDocumentoCollectionNew.contains(accesoDocumentoCollectionOldAccesoDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AccesoDocumento " + accesoDocumentoCollectionOldAccesoDocumento + " since its documento field is not nullable.");
                }
            }
            for (ComentarioDocumento comentarioDocumentoCollectionOldComentarioDocumento : comentarioDocumentoCollectionOld) {
                if (!comentarioDocumentoCollectionNew.contains(comentarioDocumentoCollectionOldComentarioDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ComentarioDocumento " + comentarioDocumentoCollectionOldComentarioDocumento + " since its documento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fsDocumentUsuareviNew != null) {
                fsDocumentUsuareviNew = em.getReference(fsDocumentUsuareviNew.getClass(), fsDocumentUsuareviNew.getPsUsuarioCodigo());
                documento.setFsDocumentUsuarevi(fsDocumentUsuareviNew);
            }
            if (fsDocumentUsuaelabNew != null) {
                fsDocumentUsuaelabNew = em.getReference(fsDocumentUsuaelabNew.getClass(), fsDocumentUsuaelabNew.getPsUsuarioCodigo());
                documento.setFsDocumentUsuaelab(fsDocumentUsuaelabNew);
            }
            if (fsDocumentUsuaapruNew != null) {
                fsDocumentUsuaapruNew = em.getReference(fsDocumentUsuaapruNew.getClass(), fsDocumentUsuaapruNew.getPsUsuarioCodigo());
                documento.setFsDocumentUsuaapru(fsDocumentUsuaapruNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getPsTipodocuId());
                documento.setTipoDocumento(tipoDocumentoNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getPiProcesoId());
                documento.setProceso(procesoNew);
            }
            if (fsDocumentCargreviNew != null) {
                fsDocumentCargreviNew = em.getReference(fsDocumentCargreviNew.getClass(), fsDocumentCargreviNew.getPsCargoCodigo());
                documento.setFsDocumentCargrevi(fsDocumentCargreviNew);
            }
            if (fsDocumentCargelabNew != null) {
                fsDocumentCargelabNew = em.getReference(fsDocumentCargelabNew.getClass(), fsDocumentCargelabNew.getPsCargoCodigo());
                documento.setFsDocumentCargelab(fsDocumentCargelabNew);
            }
            if (fsDocumentCargapruNew != null) {
                fsDocumentCargapruNew = em.getReference(fsDocumentCargapruNew.getClass(), fsDocumentCargapruNew.getPsCargoCodigo());
                documento.setFsDocumentCargapru(fsDocumentCargapruNew);
            }
            Collection<AccesoDocumento> attachedAccesoDocumentoCollectionNew = new ArrayList<AccesoDocumento>();
            for (AccesoDocumento accesoDocumentoCollectionNewAccesoDocumentoToAttach : accesoDocumentoCollectionNew) {
                accesoDocumentoCollectionNewAccesoDocumentoToAttach = em.getReference(accesoDocumentoCollectionNewAccesoDocumentoToAttach.getClass(), accesoDocumentoCollectionNewAccesoDocumentoToAttach.getAccesoDocumentoPK());
                attachedAccesoDocumentoCollectionNew.add(accesoDocumentoCollectionNewAccesoDocumentoToAttach);
            }
            accesoDocumentoCollectionNew = attachedAccesoDocumentoCollectionNew;
            documento.setAccesoDocumentoCollection(accesoDocumentoCollectionNew);
            Collection<ComentarioDocumento> attachedComentarioDocumentoCollectionNew = new ArrayList<ComentarioDocumento>();
            for (ComentarioDocumento comentarioDocumentoCollectionNewComentarioDocumentoToAttach : comentarioDocumentoCollectionNew) {
                comentarioDocumentoCollectionNewComentarioDocumentoToAttach = em.getReference(comentarioDocumentoCollectionNewComentarioDocumentoToAttach.getClass(), comentarioDocumentoCollectionNewComentarioDocumentoToAttach.getComentarioDocumentoPK());
                attachedComentarioDocumentoCollectionNew.add(comentarioDocumentoCollectionNewComentarioDocumentoToAttach);
            }
            comentarioDocumentoCollectionNew = attachedComentarioDocumentoCollectionNew;
            documento.setComentarioDocumentoCollection(comentarioDocumentoCollectionNew);
            List<SolicitudCompra> attachedSolicitudCompraListNew = new ArrayList<SolicitudCompra>();
            for (SolicitudCompra solicitudCompraListNewSolicitudCompraToAttach : solicitudCompraListNew) {
                solicitudCompraListNewSolicitudCompraToAttach = em.getReference(solicitudCompraListNewSolicitudCompraToAttach.getClass(), solicitudCompraListNewSolicitudCompraToAttach.getPiSolicompConsecutivo());
                attachedSolicitudCompraListNew.add(solicitudCompraListNewSolicitudCompraToAttach);
            }
            solicitudCompraListNew = attachedSolicitudCompraListNew;
            documento.setSolicitudCompraList(solicitudCompraListNew);
            List<OrdenCompra> attachedOrdenCompraListNew = new ArrayList<OrdenCompra>();
            for (OrdenCompra ordenCompraListNewOrdenCompraToAttach : ordenCompraListNew) {
                ordenCompraListNewOrdenCompraToAttach = em.getReference(ordenCompraListNewOrdenCompraToAttach.getClass(), ordenCompraListNewOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraListNew.add(ordenCompraListNewOrdenCompraToAttach);
            }
            ordenCompraListNew = attachedOrdenCompraListNew;
            documento.setOrdenCompraList(ordenCompraListNew);
            documento = em.merge(documento);
            if (fsDocumentUsuareviOld != null && !fsDocumentUsuareviOld.equals(fsDocumentUsuareviNew)) {
                fsDocumentUsuareviOld.getDocumentoCollection().remove(documento);
                fsDocumentUsuareviOld = em.merge(fsDocumentUsuareviOld);
            }
            if (fsDocumentUsuareviNew != null && !fsDocumentUsuareviNew.equals(fsDocumentUsuareviOld)) {
                fsDocumentUsuareviNew.getDocumentoCollection().add(documento);
                fsDocumentUsuareviNew = em.merge(fsDocumentUsuareviNew);
            }
            if (fsDocumentUsuaelabOld != null && !fsDocumentUsuaelabOld.equals(fsDocumentUsuaelabNew)) {
                fsDocumentUsuaelabOld.getDocumentoCollection().remove(documento);
                fsDocumentUsuaelabOld = em.merge(fsDocumentUsuaelabOld);
            }
            if (fsDocumentUsuaelabNew != null && !fsDocumentUsuaelabNew.equals(fsDocumentUsuaelabOld)) {
                fsDocumentUsuaelabNew.getDocumentoCollection().add(documento);
                fsDocumentUsuaelabNew = em.merge(fsDocumentUsuaelabNew);
            }
            if (fsDocumentUsuaapruOld != null && !fsDocumentUsuaapruOld.equals(fsDocumentUsuaapruNew)) {
                fsDocumentUsuaapruOld.getDocumentoCollection().remove(documento);
                fsDocumentUsuaapruOld = em.merge(fsDocumentUsuaapruOld);
            }
            if (fsDocumentUsuaapruNew != null && !fsDocumentUsuaapruNew.equals(fsDocumentUsuaapruOld)) {
                fsDocumentUsuaapruNew.getDocumentoCollection().add(documento);
                fsDocumentUsuaapruNew = em.merge(fsDocumentUsuaapruNew);
            }
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getDocumentoCollection().remove(documento);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getDocumentoCollection().add(documento);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getDocumentoCollection().remove(documento);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getDocumentoCollection().add(documento);
                procesoNew = em.merge(procesoNew);
            }
            if (fsDocumentCargreviOld != null && !fsDocumentCargreviOld.equals(fsDocumentCargreviNew)) {
                fsDocumentCargreviOld.getDocumentoCollection().remove(documento);
                fsDocumentCargreviOld = em.merge(fsDocumentCargreviOld);
            }
            if (fsDocumentCargreviNew != null && !fsDocumentCargreviNew.equals(fsDocumentCargreviOld)) {
                fsDocumentCargreviNew.getDocumentoCollection().add(documento);
                fsDocumentCargreviNew = em.merge(fsDocumentCargreviNew);
            }
            if (fsDocumentCargelabOld != null && !fsDocumentCargelabOld.equals(fsDocumentCargelabNew)) {
                fsDocumentCargelabOld.getDocumentoCollection().remove(documento);
                fsDocumentCargelabOld = em.merge(fsDocumentCargelabOld);
            }
            if (fsDocumentCargelabNew != null && !fsDocumentCargelabNew.equals(fsDocumentCargelabOld)) {
                fsDocumentCargelabNew.getDocumentoCollection().add(documento);
                fsDocumentCargelabNew = em.merge(fsDocumentCargelabNew);
            }
            if (fsDocumentCargapruOld != null && !fsDocumentCargapruOld.equals(fsDocumentCargapruNew)) {
                fsDocumentCargapruOld.getDocumentoCollection().remove(documento);
                fsDocumentCargapruOld = em.merge(fsDocumentCargapruOld);
            }
            if (fsDocumentCargapruNew != null && !fsDocumentCargapruNew.equals(fsDocumentCargapruOld)) {
                fsDocumentCargapruNew.getDocumentoCollection().add(documento);
                fsDocumentCargapruNew = em.merge(fsDocumentCargapruNew);
            }
            for (AccesoDocumento accesoDocumentoCollectionNewAccesoDocumento : accesoDocumentoCollectionNew) {
                if (!accesoDocumentoCollectionOld.contains(accesoDocumentoCollectionNewAccesoDocumento)) {
                    Documento oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento = accesoDocumentoCollectionNewAccesoDocumento.getDocumento();
                    accesoDocumentoCollectionNewAccesoDocumento.setDocumento(documento);
                    accesoDocumentoCollectionNewAccesoDocumento = em.merge(accesoDocumentoCollectionNewAccesoDocumento);
                    if (oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento != null && !oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento.equals(documento)) {
                        oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento.getAccesoDocumentoCollection().remove(accesoDocumentoCollectionNewAccesoDocumento);
                        oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento = em.merge(oldDocumentoOfAccesoDocumentoCollectionNewAccesoDocumento);
                    }
                }
            }
            for (ComentarioDocumento comentarioDocumentoCollectionNewComentarioDocumento : comentarioDocumentoCollectionNew) {
                if (!comentarioDocumentoCollectionOld.contains(comentarioDocumentoCollectionNewComentarioDocumento)) {
                    Documento oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento = comentarioDocumentoCollectionNewComentarioDocumento.getDocumento();
                    comentarioDocumentoCollectionNewComentarioDocumento.setDocumento(documento);
                    comentarioDocumentoCollectionNewComentarioDocumento = em.merge(comentarioDocumentoCollectionNewComentarioDocumento);
                    if (oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento != null && !oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento.equals(documento)) {
                        oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento.getComentarioDocumentoCollection().remove(comentarioDocumentoCollectionNewComentarioDocumento);
                        oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento = em.merge(oldDocumentoOfComentarioDocumentoCollectionNewComentarioDocumento);
                    }
                }
            }
            for (SolicitudCompra solicitudCompraListOldSolicitudCompra : solicitudCompraListOld) {
                if (!solicitudCompraListNew.contains(solicitudCompraListOldSolicitudCompra)) {
                    solicitudCompraListOldSolicitudCompra.setDocumento(null);
                    solicitudCompraListOldSolicitudCompra = em.merge(solicitudCompraListOldSolicitudCompra);
                }
            }
            for (SolicitudCompra solicitudCompraListNewSolicitudCompra : solicitudCompraListNew) {
                if (!solicitudCompraListOld.contains(solicitudCompraListNewSolicitudCompra)) {
                    Documento oldDocumentoOfSolicitudCompraListNewSolicitudCompra = solicitudCompraListNewSolicitudCompra.getDocumento();
                    solicitudCompraListNewSolicitudCompra.setDocumento(documento);
                    solicitudCompraListNewSolicitudCompra = em.merge(solicitudCompraListNewSolicitudCompra);
                    if (oldDocumentoOfSolicitudCompraListNewSolicitudCompra != null && !oldDocumentoOfSolicitudCompraListNewSolicitudCompra.equals(documento)) {
                        oldDocumentoOfSolicitudCompraListNewSolicitudCompra.getSolicitudCompraList().remove(solicitudCompraListNewSolicitudCompra);
                        oldDocumentoOfSolicitudCompraListNewSolicitudCompra = em.merge(oldDocumentoOfSolicitudCompraListNewSolicitudCompra);
                    }
                }
            }
            for (OrdenCompra ordenCompraListOldOrdenCompra : ordenCompraListOld) {
                if (!ordenCompraListNew.contains(ordenCompraListOldOrdenCompra)) {
                    ordenCompraListOldOrdenCompra.setDocumento(null);
                    ordenCompraListOldOrdenCompra = em.merge(ordenCompraListOldOrdenCompra);
                }
            }
            for (OrdenCompra ordenCompraListNewOrdenCompra : ordenCompraListNew) {
                if (!ordenCompraListOld.contains(ordenCompraListNewOrdenCompra)) {
                    Documento oldDocumentoOfOrdenCompraListNewOrdenCompra = ordenCompraListNewOrdenCompra.getDocumento();
                    ordenCompraListNewOrdenCompra.setDocumento(documento);
                    ordenCompraListNewOrdenCompra = em.merge(ordenCompraListNewOrdenCompra);
                    if (oldDocumentoOfOrdenCompraListNewOrdenCompra != null && !oldDocumentoOfOrdenCompraListNewOrdenCompra.equals(documento)) {
                        oldDocumentoOfOrdenCompraListNewOrdenCompra.getOrdenCompraList().remove(ordenCompraListNewOrdenCompra);
                        oldDocumentoOfOrdenCompraListNewOrdenCompra = em.merge(oldDocumentoOfOrdenCompraListNewOrdenCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DocumentoPK id = documento.getDocumentoPK();
                if (findDocumento(id) == null) {
                    throw new NonexistentEntityException("The documento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public void destroy(DocumentoPK id) throws IllegalOrphanException, NonexistentEntityException {
        /*EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento documento;
            try {
                documento = em.getReference(Documento.class, id);
                documento.getDocumentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AccesoDocumento> accesoDocumentoCollectionOrphanCheck = documento.getAccesoDocumentoCollection();
            for (AccesoDocumento accesoDocumentoCollectionOrphanCheckAccesoDocumento : accesoDocumentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documento (" + documento + ") cannot be destroyed since the AccesoDocumento " + accesoDocumentoCollectionOrphanCheckAccesoDocumento + " in its accesoDocumentoCollection field has a non-nullable documento field.");
            }
            Collection<ComentarioDocumento> comentarioDocumentoCollectionOrphanCheck = documento.getComentarioDocumentoCollection();
            for (ComentarioDocumento comentarioDocumentoCollectionOrphanCheckComentarioDocumento : comentarioDocumentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documento (" + documento + ") cannot be destroyed since the ComentarioDocumento " + comentarioDocumentoCollectionOrphanCheckComentarioDocumento + " in its comentarioDocumentoCollection field has a non-nullable documento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SUsuario fsDocumentUsuarevi = documento.getFsDocumentUsuarevi();
            if (fsDocumentUsuarevi != null) {
                fsDocumentUsuarevi.getDocumentoCollection().remove(documento);
                fsDocumentUsuarevi = em.merge(fsDocumentUsuarevi);
            }
            SUsuario fsDocumentUsuaelab = documento.getFsDocumentUsuaelab();
            if (fsDocumentUsuaelab != null) {
                fsDocumentUsuaelab.getDocumentoCollection().remove(documento);
                fsDocumentUsuaelab = em.merge(fsDocumentUsuaelab);
            }
            SUsuario fsDocumentUsuaapru = documento.getFsDocumentUsuaapru();
            if (fsDocumentUsuaapru != null) {
                fsDocumentUsuaapru.getDocumentoCollection().remove(documento);
                fsDocumentUsuaapru = em.merge(fsDocumentUsuaapru);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().remove(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            Proceso proceso = documento.getProceso();
            if (proceso != null) {
                proceso.getDocumentoCollection().remove(documento);
                proceso = em.merge(proceso);
            }
            Cargo fsDocumentCargrevi = documento.getFsDocumentCargrevi();
            if (fsDocumentCargrevi != null) {
                fsDocumentCargrevi.getDocumentoCollection().remove(documento);
                fsDocumentCargrevi = em.merge(fsDocumentCargrevi);
            }
            Cargo fsDocumentCargelab = documento.getFsDocumentCargelab();
            if (fsDocumentCargelab != null) {
                fsDocumentCargelab.getDocumentoCollection().remove(documento);
                fsDocumentCargelab = em.merge(fsDocumentCargelab);
            }
            Cargo fsDocumentCargapru = documento.getFsDocumentCargapru();
            if (fsDocumentCargapru != null) {
                fsDocumentCargapru.getDocumentoCollection().remove(documento);
                fsDocumentCargapru = em.merge(fsDocumentCargapru);
            }
            List<SolicitudCompra> solicitudCompraList = documento.getSolicitudCompraList();
            for (SolicitudCompra solicitudCompraListSolicitudCompra : solicitudCompraList) {
                solicitudCompraListSolicitudCompra.setDocumento(null);
                solicitudCompraListSolicitudCompra = em.merge(solicitudCompraListSolicitudCompra);
            }
            List<OrdenCompra> ordenCompraList = documento.getOrdenCompraList();
            for (OrdenCompra ordenCompraListOrdenCompra : ordenCompraList) {
                ordenCompraListOrdenCompra.setDocumento(null);
                ordenCompraListOrdenCompra = em.merge(ordenCompraListOrdenCompra);
            }
            em.remove(documento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
    }

    public List<Documento> findDocumentoEntities() {
        return findDocumentoEntities(true, -1, -1);
    }

    public List<Documento> findDocumentoEntities(int maxResults, int firstResult) {
        return findDocumentoEntities(false, maxResults, firstResult);
    }

    private List<Documento> findDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
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

    public Documento findDocumento(DocumentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Documento> rt = cq.from(Documento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Documento> consultaDocumentosPorEstados(String estados, String codigo, TipoDocumento tipoDocumento,
            Proceso proceso) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* FROM documento d WHERE d.s_document_estado IN (";
        consulta += estados + ") ";
        if (codigo != null && !codigo.equals("")) {
            consulta += " AND CONCAT(d.pfs_document_tipodocu, d.pfs_document_letrproc, '-', d.ps_document_consecutivo) = ? ";
        }
        if (tipoDocumento != null) {
            consulta += " AND d.pfs_document_tipodocu = ? ";
        }
        if (proceso != null) {
            consulta += " AND d.pfs_document_letrproc = ? ";
        }
        consulta += ";";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            int i = 1;
            if (codigo != null && !codigo.equals("")) {
                q.setParameter(i, codigo);
                i++;
            }
            if (tipoDocumento != null) {
                q.setParameter(i, tipoDocumento.getPsTipodocuId());
                i++;
            }
            if (proceso != null) {
                q.setParameter(i, proceso.getSProcesoLetrdocu());
                i++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public String calcularConsecutivo(char tipoDocumento, char letraProceso) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT max(d.ps_document_consecutivo) + 1 "
                + "FROM documento d "
                + "WHERE d.pfs_document_tipodocu = ? AND "
                + "	d.pfs_document_letrproc = ? ;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, tipoDocumento);
            q.setParameter(2, letraProceso);
            Double resu = (Double) q.getSingleResult();
            if (resu == null) {
                resu = 1.0;
            }
            return String.format("%03d", resu.intValue());
        } finally {
            em.close();
        }
    }

    public List<Cargo> getResponsablesDocumento(char tipoDocumento, char letraProceso,
            String consecutivo, String version, String tipoAcceso) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT c.* "
                + "FROM acceso_documento ad "
                + "INNER JOIN cargo c ON ad.pfs_accedocu_cargo = c.ps_cargo_codigo "
                + "WHERE ad.pfs_accedocu_tipodocu = ? AND "
                + "	ad.pfs_accedocu_letrproc = ? AND "
                + "	ad.pfs_accedocu_consdocu = ? AND "
                + "	ad.pfs_accedocu_versdocu = ? AND "
                + "	ad.ps_accedocu_tipoacce = ? ; ";
        try {
            Query q = em.createNativeQuery(consulta, Cargo.class);
            q.setParameter(1, tipoDocumento);
            q.setParameter(2, letraProceso);
            q.setParameter(3, consecutivo);
            q.setParameter(4, version);
            q.setParameter(5, tipoAcceso);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Documento getDocumentoFS006() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM documento "
                + "WHERE pfs_document_tipodocu='F' "
                + "and pfs_document_letrproc='S' "
                + "and ps_document_consecutivo=006 "
                + "and s_document_estado='VIGENTE'";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            return (Documento) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public Documento getDocumentoFS003() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM documento "
                + "WHERE pfs_document_tipodocu='F' "
                + "and pfs_document_letrproc='S' "
                + "and ps_document_consecutivo=003 "
                + "and s_document_estado='VIGENTE'";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            return (Documento) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
     public Documento getDocumentoFL0024() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM documento "
                + "WHERE pfs_document_tipodocu='F' "
                + "and pfs_document_letrproc='L' "
                + "and ps_document_consecutivo=024 "
                + "and s_document_estado='VIGENTE'";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            return (Documento) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Documento getDocumentoFS005() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM documento "
                + "WHERE pfs_document_tipodocu='F' "
                + "and pfs_document_letrproc='S' "
                + "and ps_document_consecutivo=005 "
                + "and s_document_estado='VIGENTE'";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            return (Documento) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public Cargo getCargoAccionDocumento(char tipoDocumento, Integer idProceso,
            char accion, String codigoUsuario) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT DISTINCT c.* "
                + "FROM responsable_documento rd "
                + "INNER JOIN usuario_cargo uc ON uc.pfs_usuacarg_cargo = rd.pfs_respdocu_cargo "
                + "INNER JOIN cargo c ON c.ps_cargo_codigo = uc.pfs_usuacarg_cargo "
                + "WHERE uc.s_usuacarg_estado = 'V' AND "
                + "	c.s_cargo_estado = 'VIGENTE' AND "
                + "	rd.pfi_respdocu_proceso = ? AND "
                + "	rd.pfs_respdocu_tipodocu = ? AND "
                + "	rd.ps_respdocu_tiporesp = ? AND "
                + "	uc.pfs_usuacarg_usuario = ? "
                + "LIMIT 1 ;";
        try {
            Query q = em.createNativeQuery(consulta, Cargo.class);
            q.setParameter(1, idProceso);
            q.setParameter(2, tipoDocumento);
            q.setParameter(3, accion);
            q.setParameter(4, codigoUsuario);
            return (Cargo) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void guardarDocumento(Documento documento, Integer idProceso, String codigoUsuario,
            List<Cargo> nivelAcceso, List<Cargo> responsableRegistros,
            List<Cargo> responsableArchivos, boolean isNuevo, String cambio)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();

        String sqlInsertaDocumento
                = "INSERT INTO documento "
                + "(pfs_document_tipodocu,	pfs_document_letrproc,		ps_document_consecutivo,"
                + "ps_document_version,		s_document_titulo,		s_document_tipocambio,"
                + "s_document_almafisi,		s_document_almfisreg,		s_document_almdigreg,"
                + "s_document_nombarch,		s_document_nomarcvis,		s_document_frecapar,"
                + "i_document_reteanos,		s_document_reteotro,		s_document_disfinfis,"
                + "s_document_disfindig,	s_document_estado,		fs_document_usuaelab,"
                + "fs_document_cargelab,	d_document_elaborac,		fs_document_usuarevi,"
                + "fs_document_cargrevi,	d_document_revision,		fs_document_usuaapru,"
                + "fs_document_cargapru,	d_document_aprobaci,		fs_document_usuacrea,"
                + "d_document_creacion,		fs_document_usuultmod,		d_document_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL,                        NULL,                           NULL, "
                + "NULL,                        NULL,                           ?, "
                + "NOW(),                       NULL,                           NULL ) ; ";
        String sqlInsertaAccesoDocumento
                = "INSERT INTO acceso_documento "
                + "(pfs_accedocu_tipodocu,	pfs_accedocu_letrproc,	pfs_accedocu_consdocu,"
                + "pfs_accedocu_versdocu,	pfs_accedocu_cargo,	ps_accedocu_tipoacce,"
                + "fs_accedocu_usuacrea,	d_accedocu_creacion,	fs_accedocu_usuultmod,"
                + "d_accedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL ) ; ";

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            String consecutivoAsignado = documento.getDocumentoPK().getPsDocumentConsecutivo();
            if (isNuevo) {
                consecutivoAsignado = calcularConsecutivo(documento.getDocumentoPK().getPfsDocumentTipodocu(),
                        documento.getDocumentoPK().getPfsDocumentLetrproc());
                if (!consecutivoAsignado.equals(documento.getDocumentoPK().getPsDocumentConsecutivo())) {
                    documento.getDocumentoPK().setPsDocumentConsecutivo(consecutivoAsignado);
                }
            } else if (idProceso.equals(new Integer(0)) && documento.getDocumentoPK().getPfsDocumentTipodocu() == 'D') {

                actualizarDocumentosObsoletos(documento.getDocumentoPK(), codigoUsuario);
            }
            Cargo cargoElaboracion = getCargoAccionDocumento(documento.getDocumentoPK().getPfsDocumentTipodocu(), idProceso, 'E', codigoUsuario);
            Query q = em.createNativeQuery(sqlInsertaDocumento);
            q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
            q.setParameter(5, documento.getSDocumentTitulo());
            q.setParameter(6, documento.getSDocumentTipocambio());
            q.setParameter(7, documento.getSDocumentAlmafisi());
            q.setParameter(8, documento.getSDocumentAlmfisreg());
            q.setParameter(9, documento.getSDocumentAlmdigreg());
            q.setParameter(10, documento.getSDocumentNombarch());
            q.setParameter(11, documento.getSDocumentNomarcvis());
            q.setParameter(12, documento.getSDocumentFrecapar());
            q.setParameter(13, documento.getIDocumentReteanos());
            q.setParameter(14, documento.getSDocumentReteotro());
            q.setParameter(15, documento.getSDocumentDisfinfis());
            q.setParameter(16, documento.getSDocumentDisfindig());
            q.setParameter(17, documento.getSDocumentEstado());
            q.setParameter(18, codigoUsuario);
            q.setParameter(19, cargoElaboracion.getPsCargoCodigo());
            q.setParameter(20, codigoUsuario);
            q.executeUpdate();
            if (!isNuevo && cambio != null && !cambio.isEmpty()) {
                String insertarcambio = "INSERT INTO comentario_documento "
                        + "(pfs_comedocu_tipodocu,pfs_comedocu_letrproc,pfs_comedocu_consedocu,"
                        + "pfs_comedocu_version,pi_comedocu_consecutivo,"
                        + "s_comedocu_comentario,s_comedocu_accion,fs_comedocu_usuacrea,"
                        + "d_comedocu_creacion,fs_comedocu_usuultmod,d_comedocu_ultimodi) "
                        + " VALUES (?,?,?,?,?,?,?,?,NOW(),NULL,NULL);";
                q = em.createNativeQuery(insertarcambio);
                q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                q.setParameter(5, Integer.parseInt(documento.getDocumentoPK().getPsDocumentConsecutivo().substring(2)));
                q.setParameter(6, cambio);
                q.setParameter(7, 'C');
                q.setParameter(8, documento.getFsDocumentUsuacrea());
                q.executeUpdate();

            }
            if (nivelAcceso != null && !nivelAcceso.isEmpty()) {
                for (Cargo cargo : nivelAcceso) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "NIVACC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableRegistros != null && !responsableRegistros.isEmpty()) {
                for (Cargo cargo : responsableRegistros) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESREG");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableArchivos != null && !responsableArchivos.isEmpty()) {
                for (Cargo cargo : responsableArchivos) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESARC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocumento(documento.getDocumentoPK()) != null) {
                throw new PreexistingEntityException("Documento " + documento + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
//sin cambio

    public void guardarDocumento(Documento documento, Integer idProceso, String codigoUsuario,
            List<Cargo> nivelAcceso, List<Cargo> responsableRegistros,
            List<Cargo> responsableArchivos, boolean isNuevo)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();

        String sqlInsertaDocumento
                = "INSERT INTO documento "
                + "(pfs_document_tipodocu,	pfs_document_letrproc,		ps_document_consecutivo,"
                + "ps_document_version,		s_document_titulo,		s_document_tipocambio,"
                + "s_document_almafisi,		s_document_almfisreg,		s_document_almdigreg,"
                + "s_document_nombarch,		s_document_nomarcvis,		s_document_frecapar,"
                + "i_document_reteanos,		s_document_reteotro,		s_document_disfinfis,"
                + "s_document_disfindig,	s_document_estado,		fs_document_usuaelab,"
                + "fs_document_cargelab,	d_document_elaborac,		fs_document_usuarevi,"
                + "fs_document_cargrevi,	d_document_revision,		fs_document_usuaapru,"
                + "fs_document_cargapru,	d_document_aprobaci,		fs_document_usuacrea,"
                + "d_document_creacion,		fs_document_usuultmod,		d_document_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL,                        NULL,                           NULL, "
                + "NULL,                        NULL,                           ?, "
                + "NOW(),                       NULL,                           NULL ) ; ";
        String sqlInsertaAccesoDocumento
                = "INSERT INTO acceso_documento "
                + "(pfs_accedocu_tipodocu,	pfs_accedocu_letrproc,	pfs_accedocu_consdocu,"
                + "pfs_accedocu_versdocu,	pfs_accedocu_cargo,	ps_accedocu_tipoacce,"
                + "fs_accedocu_usuacrea,	d_accedocu_creacion,	fs_accedocu_usuultmod,"
                + "d_accedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL ) ; ";

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            String consecutivoAsignado = documento.getDocumentoPK().getPsDocumentConsecutivo();
            if (isNuevo) {
                consecutivoAsignado = calcularConsecutivo(documento.getDocumentoPK().getPfsDocumentTipodocu(),
                        documento.getDocumentoPK().getPfsDocumentLetrproc());
                if (!consecutivoAsignado.equals(documento.getDocumentoPK().getPsDocumentConsecutivo())) {
                    documento.getDocumentoPK().setPsDocumentConsecutivo(consecutivoAsignado);
                }
            } else if (idProceso.equals(new Integer(0)) && documento.getDocumentoPK().getPfsDocumentTipodocu() == 'D') {
                actualizarDocumentosObsoletos(documento.getDocumentoPK(), codigoUsuario);
            }
            Cargo cargoElaboracion = getCargoAccionDocumento(documento.getDocumentoPK().getPfsDocumentTipodocu(), idProceso, 'E', codigoUsuario);
            Query q = em.createNativeQuery(sqlInsertaDocumento);
            q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
            q.setParameter(5, documento.getSDocumentTitulo());
            q.setParameter(6, documento.getSDocumentTipocambio());
            q.setParameter(7, documento.getSDocumentAlmafisi());
            q.setParameter(8, documento.getSDocumentAlmfisreg());
            q.setParameter(9, documento.getSDocumentAlmdigreg());
            q.setParameter(10, documento.getSDocumentNombarch());
            q.setParameter(11, documento.getSDocumentNomarcvis());
            q.setParameter(12, documento.getSDocumentFrecapar());
            q.setParameter(13, documento.getIDocumentReteanos());
            q.setParameter(14, documento.getSDocumentReteotro());
            q.setParameter(15, documento.getSDocumentDisfinfis());
            q.setParameter(16, documento.getSDocumentDisfindig());
            q.setParameter(17, documento.getSDocumentEstado());
            q.setParameter(18, codigoUsuario);
            q.setParameter(19, cargoElaboracion.getPsCargoCodigo());
            q.setParameter(20, codigoUsuario);
            q.executeUpdate();
            if (nivelAcceso != null && !nivelAcceso.isEmpty()) {
                for (Cargo cargo : nivelAcceso) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "NIVACC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableRegistros != null && !responsableRegistros.isEmpty()) {
                for (Cargo cargo : responsableRegistros) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESREG");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableArchivos != null && !responsableArchivos.isEmpty()) {
                for (Cargo cargo : responsableArchivos) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESARC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocumento(documento.getDocumentoPK()) != null) {
                throw new PreexistingEntityException("Documento " + documento + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void documentoRevisado(DocumentoPK documentoPK, Integer idProceso, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoDocumento
                = "UPDATE documento "
                + "SET s_document_estado = 'REVISADO',"
                + "	fs_document_usuarevi = ? ,"
                + "	d_document_revision = NOW(),"
                + "	fs_document_cargrevi = ? ,"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	ps_document_version = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargoRevision = getCargoAccionDocumento(documentoPK.getPfsDocumentTipodocu(), idProceso, 'R', codigoUsuario);
            Query q = em.createNativeQuery(sqlActualizaEstadoDocumento);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoRevision.getPsCargoCodigo());
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(5, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(6, documentoPK.getPsDocumentConsecutivo());
            q.setParameter(7, documentoPK.getPsDocumentVersion());
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findDocumento(documentoPK) == null) {
                    throw new NonexistentEntityException("El Documento " + documentoPK + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void documentoAprobado(DocumentoPK documentoPK, Integer idProceso, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaDocumentoAprobado
                = "UPDATE documento "
                + "SET s_document_estado = 'VIGENTE',"
                + "	fs_document_usuaapru = ? ,"
                + "	d_document_aprobaci = NOW(),"
                + "	fs_document_cargapru = ? ,"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	ps_document_version = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            actualizarDocumentosObsoletos(documentoPK, codigoUsuario);
            Cargo cargoAprobacion = getCargoAccionDocumento(documentoPK.getPfsDocumentTipodocu(), idProceso, 'A', codigoUsuario);
            q = em.createNativeQuery(sqlActualizaDocumentoAprobado);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, cargoAprobacion.getPsCargoCodigo());
            q.setParameter(3, codigoUsuario);
            q.setParameter(4, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(5, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(6, documentoPK.getPsDocumentConsecutivo());
            q.setParameter(7, documentoPK.getPsDocumentVersion());
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findDocumento(documentoPK) == null) {
                    throw new NonexistentEntityException("El Documento " + documentoPK + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarDocumentosObsoletos(DocumentoPK documentoPK, String codigoUsuario)
            throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String filtros = " AND pfs_document_tipodocu = ? AND pfs_document_letrproc = ? AND ps_document_consecutivo = ? ";
        String sqlActualizaDocumentosObsoletos
                = "UPDATE documento "
                + "SET s_document_estado = 'OBSOLETO',"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	s_document_estado = 'VIGENTE' ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q;
            Queue<Object> argumentos = new LinkedList<Object>();
            argumentos.add(documentoPK.getPfsDocumentTipodocu());
            argumentos.add(documentoPK.getPfsDocumentLetrproc());
            argumentos.add(documentoPK.getPsDocumentConsecutivo());
            List<Documento> resu = consultaListaMaestra(filtros, argumentos);
            if (resu != null && !resu.isEmpty()) {
                q = em.createNativeQuery(sqlActualizaDocumentosObsoletos);
                q.setParameter(1, codigoUsuario);
                q.setParameter(2, documentoPK.getPfsDocumentTipodocu());
                q.setParameter(3, documentoPK.getPfsDocumentLetrproc());
                q.setParameter(4, documentoPK.getPsDocumentConsecutivo());
                q.executeUpdate();
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findDocumento(documentoPK) == null) {
                    throw new NonexistentEntityException("El Documento " + documentoPK + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public void calcularConsecutivoComentario(ComentarioDocumentoPK comentarioDocumentoPK) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT max(cd.pi_comedocu_consecutivo) + 1 "
                + "FROM comentario_documento cd "
                + "WHERE cd.pfs_comedocu_tipodocu = ? AND "
                + "	cd.pfs_comedocu_letrproc = ? AND "
                + "	cd.pfs_comedocu_consedocu = ? AND "
                + "	cd.pfs_comedocu_version = ? ;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, comentarioDocumentoPK.getPfsComedocuTipodocu());
            q.setParameter(2, comentarioDocumentoPK.getPfsComedocuLetrproc());
            q.setParameter(3, comentarioDocumentoPK.getPfsComedocuConsedocu());
            q.setParameter(4, comentarioDocumentoPK.getPfsComedocuVersion());
            Long resuLong = (Long) q.getSingleResult();
            Integer resu = (resuLong != null) ? resuLong.intValue() : null;
            if (resu == null) {
                resu = 1;
            }
            comentarioDocumentoPK.setPiComedocuConsecutivo(resu);
        } finally {
            em.close();
        }
    }

    public void devolverDocumento(ComentarioDocumento comentarioDocumento, String codigoUsuario)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        String sqlInsertaComentarioDocumento
                = "INSERT INTO comentario_documento "
                + "(pfs_comedocu_tipodocu,	pfs_comedocu_letrproc,		pfs_comedocu_consedocu,"
                + "pfs_comedocu_version,	pi_comedocu_consecutivo,	s_comedocu_comentario,"
                + "s_comedocu_accion,		fs_comedocu_usuacrea,		d_comedocu_creacion,"
                + "fs_comedocu_usuultmod,	d_comedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              NOW(), "
                + "NULL,                        NULL ) ; ";

        String sqlActualizaDocumentoDevuelto
                = "UPDATE documento "
                + "SET s_document_estado = 'DEVUELTO',"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	ps_document_version = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calcularConsecutivoComentario(comentarioDocumento.getComentarioDocumentoPK());
            Query q = em.createNativeQuery(sqlInsertaComentarioDocumento);
            q.setParameter(1, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuTipodocu());
            q.setParameter(2, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuLetrproc());
            q.setParameter(3, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuConsedocu());
            q.setParameter(4, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuVersion());
            q.setParameter(5, comentarioDocumento.getComentarioDocumentoPK().getPiComedocuConsecutivo());
            q.setParameter(6, comentarioDocumento.getSComedocuComentario());
            q.setParameter(7, comentarioDocumento.getSComedocuAccion());
            q.setParameter(8, codigoUsuario);
            q.executeUpdate();

            q = em.createNativeQuery(sqlActualizaDocumentoDevuelto);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuTipodocu());
            q.setParameter(3, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuLetrproc());
            q.setParameter(4, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuConsedocu());
            q.setParameter(5, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuVersion());
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (new ComentarioDocumentoJpaController(this.emf).findComentarioDocumento(comentarioDocumento.getComentarioDocumentoPK()) != null) {
                throw new PreexistingEntityException("Comentario Documento " + comentarioDocumento + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarDocumentoCorregido(Documento documento, String codigoUsuario,
            List<Cargo> nivelAcceso, List<Cargo> responsableRegistros,
            List<Cargo> responsableArchivos) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaDocumentoCorregido
                = "UPDATE documento "
                + "SET s_document_estado = 'CORREGIDO',"
                + "	s_document_titulo = ? ,"
                + "	s_document_almafisi = ? ,"
                + "	s_document_almfisreg = ? ,"
                + "	s_document_almdigreg = ? ,"
                + "	s_document_nombarch = ? ,"
                + "	s_document_nomarcvis = ? ,"
                + "	s_document_frecapar = ? ,"
                + "	i_document_reteanos = ? ,"
                + "	s_document_reteotro = ? ,"
                + "	s_document_disfinfis = ? ,"
                + "	s_document_disfindig = ? ,"
                + "	d_document_elaborac = NOW() ,"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	ps_document_version = ? ; ";

        String sqlEliminaAccesosDocumento
                = "DELETE FROM acceso_documento "
                + "WHERE pfs_accedocu_tipodocu = ? AND "
                + "	pfs_accedocu_letrproc = ? AND "
                + "	pfs_accedocu_consdocu = ? AND "
                + "	pfs_accedocu_versdocu = ? ; ";

        String sqlInsertaAccesoDocumento
                = "INSERT INTO acceso_documento "
                + "(pfs_accedocu_tipodocu,	pfs_accedocu_letrproc,	pfs_accedocu_consdocu,"
                + "pfs_accedocu_versdocu,	pfs_accedocu_cargo,	ps_accedocu_tipoacce,"
                + "fs_accedocu_usuacrea,	d_accedocu_creacion,	fs_accedocu_usuultmod,"
                + "d_accedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL ) ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaDocumentoCorregido);
            q.setParameter(1, documento.getSDocumentTitulo());
            q.setParameter(2, documento.getSDocumentAlmafisi());
            q.setParameter(3, documento.getSDocumentAlmfisreg());
            q.setParameter(4, documento.getSDocumentAlmdigreg());
            q.setParameter(5, documento.getSDocumentNombarch());
            q.setParameter(6, documento.getSDocumentNomarcvis());
            q.setParameter(7, documento.getSDocumentFrecapar());
            q.setParameter(8, documento.getIDocumentReteanos());
            q.setParameter(9, documento.getSDocumentReteotro());
            q.setParameter(10, documento.getSDocumentDisfinfis());
            q.setParameter(11, documento.getSDocumentDisfindig());
            q.setParameter(12, codigoUsuario);
            q.setParameter(13, documento.getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(14, documento.getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(15, documento.getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(16, documento.getDocumentoPK().getPsDocumentVersion());
            q.executeUpdate();

            q = em.createNativeQuery(sqlEliminaAccesosDocumento);
            q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
            q.executeUpdate();

            if (nivelAcceso != null && !nivelAcceso.isEmpty()) {
                for (Cargo cargo : nivelAcceso) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "NIVACC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableRegistros != null && !responsableRegistros.isEmpty()) {
                for (Cargo cargo : responsableRegistros) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESREG");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            if (responsableArchivos != null && !responsableArchivos.isEmpty()) {
                for (Cargo cargo : responsableArchivos) {
                    q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                    q.setParameter(1, documento.getDocumentoPK().getPfsDocumentTipodocu());
                    q.setParameter(2, documento.getDocumentoPK().getPfsDocumentLetrproc());
                    q.setParameter(3, documento.getDocumentoPK().getPsDocumentConsecutivo());
                    q.setParameter(4, documento.getDocumentoPK().getPsDocumentVersion());
                    q.setParameter(5, cargo.getPsCargoCodigo());
                    q.setParameter(6, "RESARC");
                    q.setParameter(7, codigoUsuario);
                    q.executeUpdate();
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findDocumento(documento.getDocumentoPK()) == null) {
                    throw new NonexistentEntityException("El Documento " + documento.getDocumentoPK() + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Documento versionDocumentoEnProceso(DocumentoPK documentoPK) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* "
                + "FROM documento d "
                + "WHERE d.pfs_document_tipodocu = ? AND "
                + "	d.pfs_document_letrproc = ? AND "
                + "	d.ps_document_consecutivo = ? AND "
                + "	d.ps_document_version > ? AND "
                + "	d.s_document_estado IN ('CREADO', 'CORREGIDO', 'MODIFICADO', 'REVISADO', 'DEVUELTO') "
                + "LIMIT 1;";
        try {
            Query q = em.createNativeQuery(consulta, Documento.class);
            q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
            q.setParameter(4, documentoPK.getPsDocumentVersion());
            return (Documento) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarAccesosDocumento(DocumentoPK documentoPK, String tipoAcceso,
            List<Cargo> cargosAcceso,
            String codigoUsuario) {
        EntityManager em;
        String sqlEliminaAccesosDocumento
                = "DELETE FROM acceso_documento "
                + "WHERE pfs_accedocu_tipodocu = ? AND "
                + "	pfs_accedocu_letrproc = ? AND "
                + "	pfs_accedocu_consdocu = ? AND "
                + "	pfs_accedocu_versdocu = ? AND "
                + "	ps_accedocu_tipoacce = ? ; ";

        String sqlInsertaAccesoDocumento
                = "INSERT INTO acceso_documento "
                + "(pfs_accedocu_tipodocu,	pfs_accedocu_letrproc,	pfs_accedocu_consdocu,"
                + "pfs_accedocu_versdocu,	pfs_accedocu_cargo,	ps_accedocu_tipoacce,"
                + "fs_accedocu_usuacrea,	d_accedocu_creacion,	fs_accedocu_usuultmod,"
                + "d_accedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           NOW(),                          NULL, "
                + "NULL ) ; ";

        em = getEntityManager();
        em.getTransaction().begin();

        Query q = em.createNativeQuery(sqlEliminaAccesosDocumento);
        q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
        q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
        q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
        q.setParameter(4, documentoPK.getPsDocumentVersion());
        q.setParameter(5, tipoAcceso);
        q.executeUpdate();

        if (cargosAcceso != null && !cargosAcceso.isEmpty()) {
            for (Cargo cargo : cargosAcceso) {
                q = em.createNativeQuery(sqlInsertaAccesoDocumento);
                q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
                q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
                q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
                q.setParameter(4, documentoPK.getPsDocumentVersion());
                q.setParameter(5, cargo.getPsCargoCodigo());
                q.setParameter(6, tipoAcceso);
                q.setParameter(7, codigoUsuario);
                q.executeUpdate();
            }
        }
        em.getTransaction().commit();

        if (em != null) {
            em.close();
        }
    }

    public void darDeBajaDocumento(ComentarioDocumento comentarioDocumento, String codigoUsuario)
            throws PreexistingEntityException, Exception {
        EntityManager em = getEntityManager();
        String sqlInsertaComentarioDocumento
                = "INSERT INTO comentario_documento "
                + "(pfs_comedocu_tipodocu,	pfs_comedocu_letrproc,		pfs_comedocu_consedocu,"
                + "pfs_comedocu_version,	pi_comedocu_consecutivo,	s_comedocu_comentario,"
                + "s_comedocu_accion,		fs_comedocu_usuacrea,		d_comedocu_creacion,"
                + "fs_comedocu_usuultmod,	d_comedocu_ultimodi)"
                + "VALUES ( "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              ?, "
                + "?,                           ?,                              NOW(), "
                + "NULL,                        NULL ) ; ";

        String sqlActualizaDocumentoDevuelto
                = "UPDATE documento "
                + "SET s_document_estado = 'BAJA',"
                + "	fs_document_usuultmod = ? ,"
                + "	d_document_ultimodi = NOW() "
                + "WHERE pfs_document_tipodocu = ? AND "
                + "	pfs_document_letrproc = ? AND "
                + "	ps_document_consecutivo = ? AND "
                + "	ps_document_version = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calcularConsecutivoComentario(comentarioDocumento.getComentarioDocumentoPK());
            Query q = em.createNativeQuery(sqlInsertaComentarioDocumento);
            q.setParameter(1, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuTipodocu());
            q.setParameter(2, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuLetrproc());
            q.setParameter(3, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuConsedocu());
            q.setParameter(4, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuVersion());
            q.setParameter(5, comentarioDocumento.getComentarioDocumentoPK().getPiComedocuConsecutivo());
            q.setParameter(6, comentarioDocumento.getSComedocuComentario());
            q.setParameter(7, comentarioDocumento.getSComedocuAccion());
            q.setParameter(8, codigoUsuario);
            q.executeUpdate();

            q = em.createNativeQuery(sqlActualizaDocumentoDevuelto);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuTipodocu());
            q.setParameter(3, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuLetrproc());
            q.setParameter(4, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuConsedocu());
            q.setParameter(5, comentarioDocumento.getComentarioDocumentoPK().getPfsComedocuVersion());
            q.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (new ComentarioDocumentoJpaController(this.emf).findComentarioDocumento(comentarioDocumento.getComentarioDocumentoPK()) != null) {
                throw new PreexistingEntityException("Comentario Documento " + comentarioDocumento + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean tieneVersionesObsoletas(DocumentoPK documentoPK) {
        List resultados = versionesObsoletas(documentoPK);
        return (resultados != null && !resultados.isEmpty());
    }

    public List<Documento> versionesObsoletas(DocumentoPK documentoPK) {
        EntityManager em = getEntityManager();
        List<Documento> resultados = null;
        try {
            String consulta
                    = "SELECT d.* "
                    + "FROM documento d "
                    + "WHERE d.s_document_estado = 'OBSOLETO' AND "
                    + "	d.pfs_document_tipodocu = ? AND "
                    + "	d.pfs_document_letrproc = ? AND "
                    + "	d.ps_document_consecutivo = ? ; ";
            Query q = em.createNativeQuery(consulta, Documento.class);
            q.setParameter(1, documentoPK.getPfsDocumentTipodocu());
            q.setParameter(2, documentoPK.getPfsDocumentLetrproc());
            q.setParameter(3, documentoPK.getPsDocumentConsecutivo());
            resultados = q.getResultList();
            return resultados;
        } catch (Exception nre) {
            return null;
        } finally {
            em.close();
        }
    }

    public List recordarRevision() {
        EntityManager em;
        em = getEntityManager();
        List resultados = null;
        List list1;
        String consulta = "SELECT doc.* FROM documento doc WHERE doc.s_document_estado ='VIGENTE' AND  doc."
                + "d_document_aprobaci<(DATE_SUB(NOW(), INTERVAL 2 YEAR)) AND doc.ps_document_versi"
                + "on=( SELECT MAX(docx.ps_document_version) FROM documento docx  WHERE docx.pfs_do"
                + "cument_tipodocu=doc.pfs_document_tipodocu AND  docx.pfs_document_letrproc=doc.pf"
                + "s_document_letrproc AND docx.ps_document_consecutivo=doc.ps_document_consecutivo"
                + " ); ";
        Query q = em.createNativeQuery(consulta,Documento.class);
        resultados = q.getResultList();
        list1 = resultados;
        em.close();
        return list1;
    }
    
    public List<Documento> consultaListaMaestra(String filtros, Queue<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT d.* FROM documento d WHERE d.s_document_estado = 'VIGENTE' ";
        consulta += filtros + ";";
        System.out.println(consulta);
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, Documento.class);
            if (argumentos != null) {
                while (!argumentos.isEmpty()) {
                    q.setParameter(i, argumentos.remove());
                    i++;
                }
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
      
    
}
