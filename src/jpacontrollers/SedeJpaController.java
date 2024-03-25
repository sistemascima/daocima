/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Objeto;
import entidades.Sede;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sede sede) throws PreexistingEntityException, Exception {
        if (sede.getObjetoList() == null) {
            sede.setObjetoList(new ArrayList<Objeto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Objeto> attachedObjetoList = new ArrayList<Objeto>();
            for (Objeto objetoListObjetoToAttach : sede.getObjetoList()) {
                objetoListObjetoToAttach = em.getReference(objetoListObjetoToAttach.getClass(), objetoListObjetoToAttach.getPiObjeto());
                attachedObjetoList.add(objetoListObjetoToAttach);
            }
            sede.setObjetoList(attachedObjetoList);
            em.persist(sede);
            for (Objeto objetoListObjeto : sede.getObjetoList()) {
                Sede oldIObjetoSedeIdOfObjetoListObjeto = objetoListObjeto.getIObjetoSedeId();
                objetoListObjeto.setIObjetoSedeId(sede);
                objetoListObjeto = em.merge(objetoListObjeto);
                if (oldIObjetoSedeIdOfObjetoListObjeto != null) {
                    oldIObjetoSedeIdOfObjetoListObjeto.getObjetoList().remove(objetoListObjeto);
                    oldIObjetoSedeIdOfObjetoListObjeto = em.merge(oldIObjetoSedeIdOfObjetoListObjeto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSede(sede.getPiSede()) != null) {
                throw new PreexistingEntityException("Sede " + sede + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getPiSede());
            List<Objeto> objetoListOld = persistentSede.getObjetoList();
            List<Objeto> objetoListNew = sede.getObjetoList();
            List<String> illegalOrphanMessages = null;
            for (Objeto objetoListOldObjeto : objetoListOld) {
                if (!objetoListNew.contains(objetoListOldObjeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objeto " + objetoListOldObjeto + " since its IObjetoSedeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Objeto> attachedObjetoListNew = new ArrayList<Objeto>();
            for (Objeto objetoListNewObjetoToAttach : objetoListNew) {
                objetoListNewObjetoToAttach = em.getReference(objetoListNewObjetoToAttach.getClass(), objetoListNewObjetoToAttach.getPiObjeto());
                attachedObjetoListNew.add(objetoListNewObjetoToAttach);
            }
            objetoListNew = attachedObjetoListNew;
            sede.setObjetoList(objetoListNew);
            sede = em.merge(sede);
            for (Objeto objetoListNewObjeto : objetoListNew) {
                if (!objetoListOld.contains(objetoListNewObjeto)) {
                    Sede oldIObjetoSedeIdOfObjetoListNewObjeto = objetoListNewObjeto.getIObjetoSedeId();
                    objetoListNewObjeto.setIObjetoSedeId(sede);
                    objetoListNewObjeto = em.merge(objetoListNewObjeto);
                    if (oldIObjetoSedeIdOfObjetoListNewObjeto != null && !oldIObjetoSedeIdOfObjetoListNewObjeto.equals(sede)) {
                        oldIObjetoSedeIdOfObjetoListNewObjeto.getObjetoList().remove(objetoListNewObjeto);
                        oldIObjetoSedeIdOfObjetoListNewObjeto = em.merge(oldIObjetoSedeIdOfObjetoListNewObjeto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getPiSede();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getPiSede();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objeto> objetoListOrphanCheck = sede.getObjetoList();
            for (Objeto objetoListOrphanCheckObjeto : objetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Objeto " + objetoListOrphanCheckObjeto + " in its objetoList field has a non-nullable IObjetoSedeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Sede getSedeNom(String nom){
        List<Sede> res = new ArrayList<Sede>();
        res = getEntityManager().createNamedQuery("Sede.findBySSedeNombre").setParameter( "sSedeNombre", nom).getResultList();
        Sede sede = null;
        for(Sede s:res){
            sede =s;
        }
        return sede;
    }
}
