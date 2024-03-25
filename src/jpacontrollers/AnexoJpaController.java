/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Anexo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Objeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class AnexoJpaController implements Serializable {

    public AnexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anexo anexo) throws PreexistingEntityException, Exception {
        if (anexo.getObjetoList() == null) {
            anexo.setObjetoList(new ArrayList<Objeto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objeto SAnexoObjetoId = anexo.getSAnexoObjetoId();
            if (SAnexoObjetoId != null) {
                SAnexoObjetoId = em.getReference(SAnexoObjetoId.getClass(), SAnexoObjetoId.getPiObjeto());
                anexo.setSAnexoObjetoId(SAnexoObjetoId);
            }
            List<Objeto> attachedObjetoList = new ArrayList<Objeto>();
            for (Objeto objetoListObjetoToAttach : anexo.getObjetoList()) {
                objetoListObjetoToAttach = em.getReference(objetoListObjetoToAttach.getClass(), objetoListObjetoToAttach.getPiObjeto());
                attachedObjetoList.add(objetoListObjetoToAttach);
            }
            anexo.setObjetoList(attachedObjetoList);
            em.persist(anexo);
            if (SAnexoObjetoId != null) {
                SAnexoObjetoId.getAnexoList().add(anexo);
                SAnexoObjetoId = em.merge(SAnexoObjetoId);
            }
            for (Objeto objetoListObjeto : anexo.getObjetoList()) {
                Anexo oldIObjetoAnexoIdOfObjetoListObjeto = objetoListObjeto.getIObjetoAnexoId();
                objetoListObjeto.setIObjetoAnexoId(anexo);
                objetoListObjeto = em.merge(objetoListObjeto);
                if (oldIObjetoAnexoIdOfObjetoListObjeto != null) {
                    oldIObjetoAnexoIdOfObjetoListObjeto.getObjetoList().remove(objetoListObjeto);
                    oldIObjetoAnexoIdOfObjetoListObjeto = em.merge(oldIObjetoAnexoIdOfObjetoListObjeto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnexo(anexo.getPiAnexo()) != null) {
                throw new PreexistingEntityException("Anexo " + anexo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anexo anexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anexo persistentAnexo = em.find(Anexo.class, anexo.getPiAnexo());
            Objeto SAnexoObjetoIdOld = persistentAnexo.getSAnexoObjetoId();
            Objeto SAnexoObjetoIdNew = anexo.getSAnexoObjetoId();
            List<Objeto> objetoListOld = persistentAnexo.getObjetoList();
            List<Objeto> objetoListNew = anexo.getObjetoList();
            if (SAnexoObjetoIdNew != null) {
                SAnexoObjetoIdNew = em.getReference(SAnexoObjetoIdNew.getClass(), SAnexoObjetoIdNew.getPiObjeto());
                anexo.setSAnexoObjetoId(SAnexoObjetoIdNew);
            }
            List<Objeto> attachedObjetoListNew = new ArrayList<Objeto>();
            for (Objeto objetoListNewObjetoToAttach : objetoListNew) {
                objetoListNewObjetoToAttach = em.getReference(objetoListNewObjetoToAttach.getClass(), objetoListNewObjetoToAttach.getPiObjeto());
                attachedObjetoListNew.add(objetoListNewObjetoToAttach);
            }
            objetoListNew = attachedObjetoListNew;
            anexo.setObjetoList(objetoListNew);
            anexo = em.merge(anexo);
            if (SAnexoObjetoIdOld != null && !SAnexoObjetoIdOld.equals(SAnexoObjetoIdNew)) {
                SAnexoObjetoIdOld.getAnexoList().remove(anexo);
                SAnexoObjetoIdOld = em.merge(SAnexoObjetoIdOld);
            }
            if (SAnexoObjetoIdNew != null && !SAnexoObjetoIdNew.equals(SAnexoObjetoIdOld)) {
                SAnexoObjetoIdNew.getAnexoList().add(anexo);
                SAnexoObjetoIdNew = em.merge(SAnexoObjetoIdNew);
            }
            for (Objeto objetoListOldObjeto : objetoListOld) {
                if (!objetoListNew.contains(objetoListOldObjeto)) {
                    objetoListOldObjeto.setIObjetoAnexoId(null);
                    objetoListOldObjeto = em.merge(objetoListOldObjeto);
                }
            }
            for (Objeto objetoListNewObjeto : objetoListNew) {
                if (!objetoListOld.contains(objetoListNewObjeto)) {
                    Anexo oldIObjetoAnexoIdOfObjetoListNewObjeto = objetoListNewObjeto.getIObjetoAnexoId();
                    objetoListNewObjeto.setIObjetoAnexoId(anexo);
                    objetoListNewObjeto = em.merge(objetoListNewObjeto);
                    if (oldIObjetoAnexoIdOfObjetoListNewObjeto != null && !oldIObjetoAnexoIdOfObjetoListNewObjeto.equals(anexo)) {
                        oldIObjetoAnexoIdOfObjetoListNewObjeto.getObjetoList().remove(objetoListNewObjeto);
                        oldIObjetoAnexoIdOfObjetoListNewObjeto = em.merge(oldIObjetoAnexoIdOfObjetoListNewObjeto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anexo.getPiAnexo();
                if (findAnexo(id) == null) {
                    throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.");
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
            Anexo anexo;
            try {
                anexo = em.getReference(Anexo.class, id);
                anexo.getPiAnexo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.", enfe);
            }
            Objeto SAnexoObjetoId = anexo.getSAnexoObjetoId();
            if (SAnexoObjetoId != null) {
                SAnexoObjetoId.getAnexoList().remove(anexo);
                SAnexoObjetoId = em.merge(SAnexoObjetoId);
            }
            List<Objeto> objetoList = anexo.getObjetoList();
            for (Objeto objetoListObjeto : objetoList) {
                objetoListObjeto.setIObjetoAnexoId(null);
                objetoListObjeto = em.merge(objetoListObjeto);
            }
            em.remove(anexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Anexo> findAnexoEntities() {
        return findAnexoEntities(true, -1, -1);
    }

    public List<Anexo> findAnexoEntities(int maxResults, int firstResult) {
        return findAnexoEntities(false, maxResults, firstResult);
    }

    private List<Anexo> findAnexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anexo.class));
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

    public Anexo findAnexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anexo> rt = cq.from(Anexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
