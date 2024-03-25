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
import entidades.Documento;
import entidades.Proceso;
import java.util.ArrayList;
import java.util.Collection;
import entidades.ResponsableDocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ProcesoJpaController1 implements Serializable {

    public ProcesoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proceso proceso) {
        if (proceso.getDocumentoCollection() == null) {
            proceso.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (proceso.getResponsableDocumentoCollection() == null) {
            proceso.setResponsableDocumentoCollection(new ArrayList<ResponsableDocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : proceso.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getDocumentoPK());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            proceso.setDocumentoCollection(attachedDocumentoCollection);
            Collection<ResponsableDocumento> attachedResponsableDocumentoCollection = new ArrayList<ResponsableDocumento>();
            for (ResponsableDocumento responsableDocumentoCollectionResponsableDocumentoToAttach : proceso.getResponsableDocumentoCollection()) {
                responsableDocumentoCollectionResponsableDocumentoToAttach = em.getReference(responsableDocumentoCollectionResponsableDocumentoToAttach.getClass(), responsableDocumentoCollectionResponsableDocumentoToAttach.getResponsableDocumentoPK());
                attachedResponsableDocumentoCollection.add(responsableDocumentoCollectionResponsableDocumentoToAttach);
            }
            proceso.setResponsableDocumentoCollection(attachedResponsableDocumentoCollection);
            em.persist(proceso);
            for (Documento documentoCollectionDocumento : proceso.getDocumentoCollection()) {
                Proceso oldProcesoOfDocumentoCollectionDocumento = documentoCollectionDocumento.getProceso();
                documentoCollectionDocumento.setProceso(proceso);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldProcesoOfDocumentoCollectionDocumento != null) {
                    oldProcesoOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldProcesoOfDocumentoCollectionDocumento = em.merge(oldProcesoOfDocumentoCollectionDocumento);
                }
            }
            for (ResponsableDocumento responsableDocumentoCollectionResponsableDocumento : proceso.getResponsableDocumentoCollection()) {
                Proceso oldProcesoOfResponsableDocumentoCollectionResponsableDocumento = responsableDocumentoCollectionResponsableDocumento.getProceso();
                responsableDocumentoCollectionResponsableDocumento.setProceso(proceso);
                responsableDocumentoCollectionResponsableDocumento = em.merge(responsableDocumentoCollectionResponsableDocumento);
                if (oldProcesoOfResponsableDocumentoCollectionResponsableDocumento != null) {
                    oldProcesoOfResponsableDocumentoCollectionResponsableDocumento.getResponsableDocumentoCollection().remove(responsableDocumentoCollectionResponsableDocumento);
                    oldProcesoOfResponsableDocumentoCollectionResponsableDocumento = em.merge(oldProcesoOfResponsableDocumentoCollectionResponsableDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getPiProcesoId());
            Collection<Documento> documentoCollectionOld = persistentProceso.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = proceso.getDocumentoCollection();
            Collection<ResponsableDocumento> responsableDocumentoCollectionOld = persistentProceso.getResponsableDocumentoCollection();
            Collection<ResponsableDocumento> responsableDocumentoCollectionNew = proceso.getResponsableDocumentoCollection();
            List<String> illegalOrphanMessages = null;
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documento " + documentoCollectionOldDocumento + " since its proceso field is not nullable.");
                }
            }
            for (ResponsableDocumento responsableDocumentoCollectionOldResponsableDocumento : responsableDocumentoCollectionOld) {
                if (!responsableDocumentoCollectionNew.contains(responsableDocumentoCollectionOldResponsableDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ResponsableDocumento " + responsableDocumentoCollectionOldResponsableDocumento + " since its proceso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getDocumentoPK());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            proceso.setDocumentoCollection(documentoCollectionNew);
            Collection<ResponsableDocumento> attachedResponsableDocumentoCollectionNew = new ArrayList<ResponsableDocumento>();
            for (ResponsableDocumento responsableDocumentoCollectionNewResponsableDocumentoToAttach : responsableDocumentoCollectionNew) {
                responsableDocumentoCollectionNewResponsableDocumentoToAttach = em.getReference(responsableDocumentoCollectionNewResponsableDocumentoToAttach.getClass(), responsableDocumentoCollectionNewResponsableDocumentoToAttach.getResponsableDocumentoPK());
                attachedResponsableDocumentoCollectionNew.add(responsableDocumentoCollectionNewResponsableDocumentoToAttach);
            }
            responsableDocumentoCollectionNew = attachedResponsableDocumentoCollectionNew;
            proceso.setResponsableDocumentoCollection(responsableDocumentoCollectionNew);
            proceso = em.merge(proceso);
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Proceso oldProcesoOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getProceso();
                    documentoCollectionNewDocumento.setProceso(proceso);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldProcesoOfDocumentoCollectionNewDocumento != null && !oldProcesoOfDocumentoCollectionNewDocumento.equals(proceso)) {
                        oldProcesoOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldProcesoOfDocumentoCollectionNewDocumento = em.merge(oldProcesoOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (ResponsableDocumento responsableDocumentoCollectionNewResponsableDocumento : responsableDocumentoCollectionNew) {
                if (!responsableDocumentoCollectionOld.contains(responsableDocumentoCollectionNewResponsableDocumento)) {
                    Proceso oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento = responsableDocumentoCollectionNewResponsableDocumento.getProceso();
                    responsableDocumentoCollectionNewResponsableDocumento.setProceso(proceso);
                    responsableDocumentoCollectionNewResponsableDocumento = em.merge(responsableDocumentoCollectionNewResponsableDocumento);
                    if (oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento != null && !oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento.equals(proceso)) {
                        oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento.getResponsableDocumentoCollection().remove(responsableDocumentoCollectionNewResponsableDocumento);
                        oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento = em.merge(oldProcesoOfResponsableDocumentoCollectionNewResponsableDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proceso.getPiProcesoId();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getPiProcesoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Documento> documentoCollectionOrphanCheck = proceso.getDocumentoCollection();
            for (Documento documentoCollectionOrphanCheckDocumento : documentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Documento " + documentoCollectionOrphanCheckDocumento + " in its documentoCollection field has a non-nullable proceso field.");
            }
            Collection<ResponsableDocumento> responsableDocumentoCollectionOrphanCheck = proceso.getResponsableDocumentoCollection();
            for (ResponsableDocumento responsableDocumentoCollectionOrphanCheckResponsableDocumento : responsableDocumentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the ResponsableDocumento " + responsableDocumentoCollectionOrphanCheckResponsableDocumento + " in its responsableDocumentoCollection field has a non-nullable proceso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
