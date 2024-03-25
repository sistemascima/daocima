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
import entidades.DocumentoAplicativo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author SISTEMAS
 */
public class DocumentoAplicativoJpaController implements Serializable {

    public DocumentoAplicativoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoAplicativo documentoAplicativo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento documento = documentoAplicativo.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getDocumentoPK());
                documentoAplicativo.setDocumento(documento);
            }
            em.persist(documentoAplicativo);
            if (documento != null) {
                documento.getDocumentoAplicativoList().add(documentoAplicativo);
                documento = em.merge(documento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoAplicativo documentoAplicativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoAplicativo persistentDocumentoAplicativo = em.find(DocumentoAplicativo.class, documentoAplicativo.getPiDocumentoAplicativo());
            Documento documentoOld = persistentDocumentoAplicativo.getDocumento();
            Documento documentoNew = documentoAplicativo.getDocumento();
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getDocumentoPK());
                documentoAplicativo.setDocumento(documentoNew);
            }
            documentoAplicativo = em.merge(documentoAplicativo);
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getDocumentoAplicativoList().remove(documentoAplicativo);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getDocumentoAplicativoList().add(documentoAplicativo);
                documentoNew = em.merge(documentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documentoAplicativo.getPiDocumentoAplicativo();
                if (findDocumentoAplicativo(id) == null) {
                    throw new NonexistentEntityException("The documentoAplicativo with id " + id + " no longer exists.");
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
            DocumentoAplicativo documentoAplicativo;
            try {
                documentoAplicativo = em.getReference(DocumentoAplicativo.class, id);
                documentoAplicativo.getPiDocumentoAplicativo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoAplicativo with id " + id + " no longer exists.", enfe);
            }
            Documento documento = documentoAplicativo.getDocumento();
            if (documento != null) {
                documento.getDocumentoAplicativoList().remove(documentoAplicativo);
                documento = em.merge(documento);
            }
            em.remove(documentoAplicativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoAplicativo> findDocumentoAplicativoEntities() {
        return findDocumentoAplicativoEntities(true, -1, -1);
    }

    public List<DocumentoAplicativo> findDocumentoAplicativoEntities(int maxResults, int firstResult) {
        return findDocumentoAplicativoEntities(false, maxResults, firstResult);
    }

    private List<DocumentoAplicativo> findDocumentoAplicativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentoAplicativo.class));
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

    public DocumentoAplicativo findDocumentoAplicativo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoAplicativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoAplicativoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentoAplicativo> rt = cq.from(DocumentoAplicativo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public String encontrarPathDocumentoHidrobiologia(String consecutivo, String tipo) {
        System.out.println("consecutivo" + consecutivo);
        System.out.println("tipo" + tipo);
        EntityManager em = getEntityManager();
        String consulta = " 	   select s_docu_aplic_archivo "
                + "from documento_aplicativo "
                + "where fs_docu_aplic_document_tipodocu='F' "
                + "and fs_docu_aplic_document_letrproc='L' "
                + "and fs_docu_aplic_document_consecutivo=024 "
                + "and fs_docu_aplic_tipo_docu_tipo='PRINCIPAL' "
                + "and fs_docu_aplic_document_version=? "
                + "and s_docu_aplic_extension=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, consecutivo);
            q.setParameter(2, tipo);
            return (String) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
    public String encontrarPathDocumentoAguas(String consecutivo, String tipo) {
        System.out.println("consecutivo" + consecutivo);
        System.out.println("tipo" + tipo);
        EntityManager em = getEntityManager();
        String consulta = " 	   select s_docu_aplic_archivo "
                + "from documento_aplicativo "
                + "where fs_docu_aplic_document_tipodocu='F' "
                + "and fs_docu_aplic_document_letrproc='L' "
                + "and fs_docu_aplic_document_consecutivo=001 "
                + "and fs_docu_aplic_tipo_docu_tipo='PRINCIPAL' "
                + "and fs_docu_aplic_document_version=? "
                + "and s_docu_aplic_extension=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, consecutivo);
            q.setParameter(2, tipo);
            return (String) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public String encontrarPathDocumentoAguasAnexoA(String consecutivo, String tipo) {
        System.out.println("consecutivo" + consecutivo);
        System.out.println("tipo" + tipo);
        EntityManager em = getEntityManager();
        String consulta = " 	   select s_docu_aplic_archivo "
                + "from documento_aplicativo "
                + "where fs_docu_aplic_document_tipodocu='F' "
                + "and fs_docu_aplic_document_letrproc='L' "
                + "and fs_docu_aplic_document_consecutivo=001 "
                + "and fs_docu_aplic_tipo_docu_tipo='ANEXOA' "
                + "and fs_docu_aplic_document_version=? "
                + "and s_docu_aplic_extension=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, consecutivo);
            q.setParameter(2, tipo);
            return (String) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
     public String encontrarPathDocumentoAguasAnexoB(String consecutivo, String tipo) {
        System.out.println("consecutivo" + consecutivo);
        System.out.println("tipo" + tipo);
        EntityManager em = getEntityManager();
        String consulta = " 	   select s_docu_aplic_archivo "
                + "from documento_aplicativo "
                + "where fs_docu_aplic_document_tipodocu='F' "
                + "and fs_docu_aplic_document_letrproc='L' "
                + "and fs_docu_aplic_document_consecutivo=001 "
                + "and fs_docu_aplic_tipo_docu_tipo='ANEXOB' "
                + "and fs_docu_aplic_document_version=? "
                + "and s_docu_aplic_extension=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, consecutivo);
            q.setParameter(2, tipo);
            return (String) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    
      public String encontrarPathDocumentoMetodoHidrobiologia(String consecutivo) {
        EntityManager em = getEntityManager();
        String consulta = " 	   select s_docu_aplic_archivo "
                + "from documento_aplicativo "
                + "where fs_docu_aplic_document_tipodocu='F' "
                + "and fs_docu_aplic_document_letrproc='L' "
                + "and fs_docu_aplic_document_consecutivo=024 "
                + "and fs_docu_aplic_tipo_docu_tipo='METODO' "
                + "and fs_docu_aplic_document_version=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, consecutivo);
            return (String) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
