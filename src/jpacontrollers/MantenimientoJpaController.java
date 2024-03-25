/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import entidades.Mantenimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Objeto;
import entidades.SUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class MantenimientoJpaController implements Serializable {

    public MantenimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimiento mantenimiento) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (mantenimiento.getObjetoList() == null) {
            mantenimiento.setObjetoList(new ArrayList<Objeto>());
        }
        List<String> illegalOrphanMessages = null;
        Objeto SMantenimientoObjetoIdOrphanCheck = mantenimiento.getSMantenimientoObjetoId();
        if (SMantenimientoObjetoIdOrphanCheck != null) {
            Mantenimiento oldIObjetoMantenimientoIdOfSMantenimientoObjetoId = SMantenimientoObjetoIdOrphanCheck.getIObjetoMantenimientoId();
            if (oldIObjetoMantenimientoIdOfSMantenimientoObjetoId != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Objeto " + SMantenimientoObjetoIdOrphanCheck + " already has an item of type Mantenimiento whose SMantenimientoObjetoId column cannot be null. Please make another selection for the SMantenimientoObjetoId field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objeto SMantenimientoObjetoId = mantenimiento.getSMantenimientoObjetoId();
            if (SMantenimientoObjetoId != null) {
                SMantenimientoObjetoId = em.getReference(SMantenimientoObjetoId.getClass(), SMantenimientoObjetoId.getPiObjeto());
                mantenimiento.setSMantenimientoObjetoId(SMantenimientoObjetoId);
            }
            /*SUsuario SMantenimientoUsuarioId = mantenimiento.getSMantenimientoUsuarioId();
            if (SMantenimientoUsuarioId != null) {
                SMantenimientoUsuarioId = em.getReference(SMantenimientoUsuarioId.getClass(), SMantenimientoUsuarioId.getUsuarioCod());
                mantenimiento.setSMantenimientoUsuarioId(SMantenimientoUsuarioId);
            }*/
            List<Objeto> attachedObjetoList = new ArrayList<Objeto>();
            for (Objeto objetoListObjetoToAttach : mantenimiento.getObjetoList()) {
                objetoListObjetoToAttach = em.getReference(objetoListObjetoToAttach.getClass(), objetoListObjetoToAttach.getPiObjeto());
                attachedObjetoList.add(objetoListObjetoToAttach);
            }
            mantenimiento.setObjetoList(attachedObjetoList);
            em.persist(mantenimiento);
            if (SMantenimientoObjetoId != null) {
                SMantenimientoObjetoId.setIObjetoMantenimientoId(mantenimiento);
                SMantenimientoObjetoId = em.merge(SMantenimientoObjetoId);
            }
            /*if (SMantenimientoUsuarioId != null) {
                SMantenimientoUsuarioId.getMantenimientoList().add(mantenimiento);
                SMantenimientoUsuarioId = em.merge(SMantenimientoUsuarioId);
            }*/
            for (Objeto objetoListObjeto : mantenimiento.getObjetoList()) {
                Mantenimiento oldIObjetoMantenimientoIdOfObjetoListObjeto = objetoListObjeto.getIObjetoMantenimientoId();
                objetoListObjeto.setIObjetoMantenimientoId(mantenimiento);
                objetoListObjeto = em.merge(objetoListObjeto);
                if (oldIObjetoMantenimientoIdOfObjetoListObjeto != null) {
                    oldIObjetoMantenimientoIdOfObjetoListObjeto.getObjetoList().remove(objetoListObjeto);
                    oldIObjetoMantenimientoIdOfObjetoListObjeto = em.merge(oldIObjetoMantenimientoIdOfObjetoListObjeto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimiento(mantenimiento.getPiMantenimiento()) != null) {
                throw new PreexistingEntityException("Mantenimiento " + mantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   /* public void edit(Mantenimiento mantenimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento persistentMantenimiento = em.find(Mantenimiento.class, mantenimiento.getPiMantenimiento());
            Objeto SMantenimientoObjetoIdOld = persistentMantenimiento.getSMantenimientoObjetoId();
            Objeto SMantenimientoObjetoIdNew = mantenimiento.getSMantenimientoObjetoId();
            SUsuario SMantenimientoUsuarioIdOld = persistentMantenimiento.getSMantenimientoUsuarioId();
            SUsuario SMantenimientoUsuarioIdNew = mantenimiento.getSMantenimientoUsuarioId();
            List<Objeto> objetoListOld = persistentMantenimiento.getObjetoList();
            List<Objeto> objetoListNew = mantenimiento.getObjetoList();
            List<String> illegalOrphanMessages = null;
            if (SMantenimientoObjetoIdNew != null && !SMantenimientoObjetoIdNew.equals(SMantenimientoObjetoIdOld)) {
                Mantenimiento oldIObjetoMantenimientoIdOfSMantenimientoObjetoId = SMantenimientoObjetoIdNew.getIObjetoMantenimientoId();
                if (oldIObjetoMantenimientoIdOfSMantenimientoObjetoId != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Objeto " + SMantenimientoObjetoIdNew + " already has an item of type Mantenimiento whose SMantenimientoObjetoId column cannot be null. Please make another selection for the SMantenimientoObjetoId field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (SMantenimientoObjetoIdNew != null) {
                SMantenimientoObjetoIdNew = em.getReference(SMantenimientoObjetoIdNew.getClass(), SMantenimientoObjetoIdNew.getPiObjeto());
                mantenimiento.setSMantenimientoObjetoId(SMantenimientoObjetoIdNew);
            }
           /* if (SMantenimientoUsuarioIdNew != null) {
                SMantenimientoUsuarioIdNew = em.getReference(SMantenimientoUsuarioIdNew.getClass(), SMantenimientoUsuarioIdNew.getUsuarioCod());
                mantenimiento.setSMantenimientoUsuarioId(SMantenimientoUsuarioIdNew);
            }
            List<Objeto> attachedObjetoListNew = new ArrayList<Objeto>();
            for (Objeto objetoListNewObjetoToAttach : objetoListNew) {
                objetoListNewObjetoToAttach = em.getReference(objetoListNewObjetoToAttach.getClass(), objetoListNewObjetoToAttach.getPiObjeto());
                attachedObjetoListNew.add(objetoListNewObjetoToAttach);
            }
            objetoListNew = attachedObjetoListNew;
            mantenimiento.setObjetoList(objetoListNew);
            mantenimiento = em.merge(mantenimiento);
            if (SMantenimientoObjetoIdOld != null && !SMantenimientoObjetoIdOld.equals(SMantenimientoObjetoIdNew)) {
                SMantenimientoObjetoIdOld.setIObjetoMantenimientoId(null);
                SMantenimientoObjetoIdOld = em.merge(SMantenimientoObjetoIdOld);
            }
            if (SMantenimientoObjetoIdNew != null && !SMantenimientoObjetoIdNew.equals(SMantenimientoObjetoIdOld)) {
                SMantenimientoObjetoIdNew.setIObjetoMantenimientoId(mantenimiento);
                SMantenimientoObjetoIdNew = em.merge(SMantenimientoObjetoIdNew);
            }
           /* if (SMantenimientoUsuarioIdOld != null && !SMantenimientoUsuarioIdOld.equals(SMantenimientoUsuarioIdNew)) {
                SMantenimientoUsuarioIdOld.getMantenimientoList().remove(mantenimiento);
                SMantenimientoUsuarioIdOld = em.merge(SMantenimientoUsuarioIdOld);
            }
            if (SMantenimientoUsuarioIdNew != null && !SMantenimientoUsuarioIdNew.equals(SMantenimientoUsuarioIdOld)) {
                SMantenimientoUsuarioIdNew.getMantenimientoList().add(mantenimiento);
                SMantenimientoUsuarioIdNew = em.merge(SMantenimientoUsuarioIdNew);
            }
            for (Objeto objetoListOldObjeto : objetoListOld) {
                if (!objetoListNew.contains(objetoListOldObjeto)) {
                    objetoListOldObjeto.setIObjetoMantenimientoId(null);
                    objetoListOldObjeto = em.merge(objetoListOldObjeto);
                }
            }
            for (Objeto objetoListNewObjeto : objetoListNew) {
                if (!objetoListOld.contains(objetoListNewObjeto)) {
                    Mantenimiento oldIObjetoMantenimientoIdOfObjetoListNewObjeto = objetoListNewObjeto.getIObjetoMantenimientoId();
                    objetoListNewObjeto.setIObjetoMantenimientoId(mantenimiento);
                    objetoListNewObjeto = em.merge(objetoListNewObjeto);
                    if (oldIObjetoMantenimientoIdOfObjetoListNewObjeto != null && !oldIObjetoMantenimientoIdOfObjetoListNewObjeto.equals(mantenimiento)) {
                        oldIObjetoMantenimientoIdOfObjetoListNewObjeto.getObjetoList().remove(objetoListNewObjeto);
                        oldIObjetoMantenimientoIdOfObjetoListNewObjeto = em.merge(oldIObjetoMantenimientoIdOfObjetoListNewObjeto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mantenimiento.getPiMantenimiento();
                if (findMantenimiento(id) == null) {
                    throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento mantenimiento;
            try {
                mantenimiento = em.getReference(Mantenimiento.class, id);
                mantenimiento.getPiMantenimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.", enfe);
            }
            Objeto SMantenimientoObjetoId = mantenimiento.getSMantenimientoObjetoId();
            if (SMantenimientoObjetoId != null) {
                SMantenimientoObjetoId.setIObjetoMantenimientoId(null);
                SMantenimientoObjetoId = em.merge(SMantenimientoObjetoId);
            }
            /*SUsuario SMantenimientoUsuarioId = mantenimiento.getSMantenimientoUsuarioId();
            if (SMantenimientoUsuarioId != null) {
                SMantenimientoUsuarioId.getMantenimientoList().remove(mantenimiento);
                SMantenimientoUsuarioId = em.merge(SMantenimientoUsuarioId);
            }*/
            List<Objeto> objetoList = mantenimiento.getObjetoList();
            for (Objeto objetoListObjeto : objetoList) {
                objetoListObjeto.setIObjetoMantenimientoId(null);
                objetoListObjeto = em.merge(objetoListObjeto);
            }
            em.remove(mantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimiento> findMantenimientoEntities() {
        return findMantenimientoEntities(true, -1, -1);
    }

    public List<Mantenimiento> findMantenimientoEntities(int maxResults, int firstResult) {
        return findMantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimiento> findMantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimiento.class));
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

    public Mantenimiento findMantenimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimiento> rt = cq.from(Mantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
