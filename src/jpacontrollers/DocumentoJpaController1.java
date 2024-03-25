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
import entidades.TipoDocumento;
import entidades.Proceso;
import entidades.Cargo;
import entidades.AccesoDocumento;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ComentarioDocumento;
import entidades.Documento;
import entidades.SolicitudCompra;
import java.util.List;
import entidades.OrdenCompra;
import entidades.DocumentoAplicativo;
import entidades.DocumentoPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class DocumentoJpaController1 implements Serializable {

    public DocumentoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) throws PreexistingEntityException, Exception {
        if (documento.getDocumentoPK() == null) {
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
        if (documento.getDocumentoAplicativoList() == null) {
            documento.setDocumentoAplicativoList(new ArrayList<DocumentoAplicativo>());
        }
        documento.getDocumentoPK().setPfsDocumentTipodocu(documento.getTipoDocumento().getPsTipodocuId());
        documento.getDocumentoPK().setPfsDocumentLetrproc(documento.getProceso().getSProcesoLetrdocu());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            List<DocumentoAplicativo> attachedDocumentoAplicativoList = new ArrayList<DocumentoAplicativo>();
            for (DocumentoAplicativo documentoAplicativoListDocumentoAplicativoToAttach : documento.getDocumentoAplicativoList()) {
                documentoAplicativoListDocumentoAplicativoToAttach = em.getReference(documentoAplicativoListDocumentoAplicativoToAttach.getClass(), documentoAplicativoListDocumentoAplicativoToAttach.getPiDocumentoAplicativo());
                attachedDocumentoAplicativoList.add(documentoAplicativoListDocumentoAplicativoToAttach);
            }
            documento.setDocumentoAplicativoList(attachedDocumentoAplicativoList);
            em.persist(documento);
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
            for (DocumentoAplicativo documentoAplicativoListDocumentoAplicativo : documento.getDocumentoAplicativoList()) {
                Documento oldDocumentoOfDocumentoAplicativoListDocumentoAplicativo = documentoAplicativoListDocumentoAplicativo.getDocumento();
                documentoAplicativoListDocumentoAplicativo.setDocumento(documento);
                documentoAplicativoListDocumentoAplicativo = em.merge(documentoAplicativoListDocumentoAplicativo);
                if (oldDocumentoOfDocumentoAplicativoListDocumentoAplicativo != null) {
                    oldDocumentoOfDocumentoAplicativoListDocumentoAplicativo.getDocumentoAplicativoList().remove(documentoAplicativoListDocumentoAplicativo);
                    oldDocumentoOfDocumentoAplicativoListDocumentoAplicativo = em.merge(oldDocumentoOfDocumentoAplicativoListDocumentoAplicativo);
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
        }
    }

    public void edit(Documento documento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        documento.getDocumentoPK().setPfsDocumentTipodocu(documento.getTipoDocumento().getPsTipodocuId());
        documento.getDocumentoPK().setPfsDocumentLetrproc(documento.getProceso().getSProcesoLetrdocu());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getDocumentoPK());
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
            List<DocumentoAplicativo> documentoAplicativoListOld = persistentDocumento.getDocumentoAplicativoList();
            List<DocumentoAplicativo> documentoAplicativoListNew = documento.getDocumentoAplicativoList();
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
            List<DocumentoAplicativo> attachedDocumentoAplicativoListNew = new ArrayList<DocumentoAplicativo>();
            for (DocumentoAplicativo documentoAplicativoListNewDocumentoAplicativoToAttach : documentoAplicativoListNew) {
                documentoAplicativoListNewDocumentoAplicativoToAttach = em.getReference(documentoAplicativoListNewDocumentoAplicativoToAttach.getClass(), documentoAplicativoListNewDocumentoAplicativoToAttach.getPiDocumentoAplicativo());
                attachedDocumentoAplicativoListNew.add(documentoAplicativoListNewDocumentoAplicativoToAttach);
            }
            documentoAplicativoListNew = attachedDocumentoAplicativoListNew;
            documento.setDocumentoAplicativoList(documentoAplicativoListNew);
            documento = em.merge(documento);
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
            for (DocumentoAplicativo documentoAplicativoListOldDocumentoAplicativo : documentoAplicativoListOld) {
                if (!documentoAplicativoListNew.contains(documentoAplicativoListOldDocumentoAplicativo)) {
                    documentoAplicativoListOldDocumentoAplicativo.setDocumento(null);
                    documentoAplicativoListOldDocumentoAplicativo = em.merge(documentoAplicativoListOldDocumentoAplicativo);
                }
            }
            for (DocumentoAplicativo documentoAplicativoListNewDocumentoAplicativo : documentoAplicativoListNew) {
                if (!documentoAplicativoListOld.contains(documentoAplicativoListNewDocumentoAplicativo)) {
                    Documento oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo = documentoAplicativoListNewDocumentoAplicativo.getDocumento();
                    documentoAplicativoListNewDocumentoAplicativo.setDocumento(documento);
                    documentoAplicativoListNewDocumentoAplicativo = em.merge(documentoAplicativoListNewDocumentoAplicativo);
                    if (oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo != null && !oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo.equals(documento)) {
                        oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo.getDocumentoAplicativoList().remove(documentoAplicativoListNewDocumentoAplicativo);
                        oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo = em.merge(oldDocumentoOfDocumentoAplicativoListNewDocumentoAplicativo);
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
        }
    }

    public void destroy(DocumentoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
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
            List<DocumentoAplicativo> documentoAplicativoList = documento.getDocumentoAplicativoList();
            for (DocumentoAplicativo documentoAplicativoListDocumentoAplicativo : documentoAplicativoList) {
                documentoAplicativoListDocumentoAplicativo.setDocumento(null);
                documentoAplicativoListDocumentoAplicativo = em.merge(documentoAplicativoListDocumentoAplicativo);
            }
            em.remove(documento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
    
}
