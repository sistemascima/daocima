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
import entidades.Muestreo;
import java.util.ArrayList;
import java.util.List;
import entidades.MuestreoHistorico;
import entidades.Punto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class PuntoJpaController1 implements Serializable {

    public PuntoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Punto punto) {
        if (punto.getMuestreoList() == null) {
            punto.setMuestreoList(new ArrayList<Muestreo>());
        }
        if (punto.getMuestreoHistoricoList() == null) {
            punto.setMuestreoHistoricoList(new ArrayList<MuestreoHistorico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Muestreo> attachedMuestreoList = new ArrayList<Muestreo>();
            for (Muestreo muestreoListMuestreoToAttach : punto.getMuestreoList()) {
                muestreoListMuestreoToAttach = em.getReference(muestreoListMuestreoToAttach.getClass(), muestreoListMuestreoToAttach.getIdMuestreo());
                attachedMuestreoList.add(muestreoListMuestreoToAttach);
            }
            punto.setMuestreoList(attachedMuestreoList);
            List<MuestreoHistorico> attachedMuestreoHistoricoList = new ArrayList<MuestreoHistorico>();
            for (MuestreoHistorico muestreoHistoricoListMuestreoHistoricoToAttach : punto.getMuestreoHistoricoList()) {
                muestreoHistoricoListMuestreoHistoricoToAttach = em.getReference(muestreoHistoricoListMuestreoHistoricoToAttach.getClass(), muestreoHistoricoListMuestreoHistoricoToAttach.getPiMuestreoHistoricoId());
                attachedMuestreoHistoricoList.add(muestreoHistoricoListMuestreoHistoricoToAttach);
            }
            punto.setMuestreoHistoricoList(attachedMuestreoHistoricoList);
            em.persist(punto);
            for (Muestreo muestreoListMuestreo : punto.getMuestreoList()) {
                Punto oldIdPuntoOfMuestreoListMuestreo = muestreoListMuestreo.getIdPunto();
                muestreoListMuestreo.setIdPunto(punto);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
                if (oldIdPuntoOfMuestreoListMuestreo != null) {
                    oldIdPuntoOfMuestreoListMuestreo.getMuestreoList().remove(muestreoListMuestreo);
                    oldIdPuntoOfMuestreoListMuestreo = em.merge(oldIdPuntoOfMuestreoListMuestreo);
                }
            }
            for (MuestreoHistorico muestreoHistoricoListMuestreoHistorico : punto.getMuestreoHistoricoList()) {
                Punto oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListMuestreoHistorico = muestreoHistoricoListMuestreoHistorico.getFiMuestreoHistoricoPunto();
                muestreoHistoricoListMuestreoHistorico.setFiMuestreoHistoricoPunto(punto);
                muestreoHistoricoListMuestreoHistorico = em.merge(muestreoHistoricoListMuestreoHistorico);
                if (oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListMuestreoHistorico != null) {
                    oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListMuestreoHistorico.getMuestreoHistoricoList().remove(muestreoHistoricoListMuestreoHistorico);
                    oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListMuestreoHistorico = em.merge(oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListMuestreoHistorico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Punto punto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Punto persistentPunto = em.find(Punto.class, punto.getIdpunto());
            List<Muestreo> muestreoListOld = persistentPunto.getMuestreoList();
            List<Muestreo> muestreoListNew = punto.getMuestreoList();
            List<MuestreoHistorico> muestreoHistoricoListOld = persistentPunto.getMuestreoHistoricoList();
            List<MuestreoHistorico> muestreoHistoricoListNew = punto.getMuestreoHistoricoList();
            List<String> illegalOrphanMessages = null;
            for (Muestreo muestreoListOldMuestreo : muestreoListOld) {
                if (!muestreoListNew.contains(muestreoListOldMuestreo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestreo " + muestreoListOldMuestreo + " since its idPunto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Muestreo> attachedMuestreoListNew = new ArrayList<Muestreo>();
            for (Muestreo muestreoListNewMuestreoToAttach : muestreoListNew) {
                muestreoListNewMuestreoToAttach = em.getReference(muestreoListNewMuestreoToAttach.getClass(), muestreoListNewMuestreoToAttach.getIdMuestreo());
                attachedMuestreoListNew.add(muestreoListNewMuestreoToAttach);
            }
            muestreoListNew = attachedMuestreoListNew;
            punto.setMuestreoList(muestreoListNew);
            List<MuestreoHistorico> attachedMuestreoHistoricoListNew = new ArrayList<MuestreoHistorico>();
            for (MuestreoHistorico muestreoHistoricoListNewMuestreoHistoricoToAttach : muestreoHistoricoListNew) {
                muestreoHistoricoListNewMuestreoHistoricoToAttach = em.getReference(muestreoHistoricoListNewMuestreoHistoricoToAttach.getClass(), muestreoHistoricoListNewMuestreoHistoricoToAttach.getPiMuestreoHistoricoId());
                attachedMuestreoHistoricoListNew.add(muestreoHistoricoListNewMuestreoHistoricoToAttach);
            }
            muestreoHistoricoListNew = attachedMuestreoHistoricoListNew;
            punto.setMuestreoHistoricoList(muestreoHistoricoListNew);
            punto = em.merge(punto);
            for (Muestreo muestreoListNewMuestreo : muestreoListNew) {
                if (!muestreoListOld.contains(muestreoListNewMuestreo)) {
                    Punto oldIdPuntoOfMuestreoListNewMuestreo = muestreoListNewMuestreo.getIdPunto();
                    muestreoListNewMuestreo.setIdPunto(punto);
                    muestreoListNewMuestreo = em.merge(muestreoListNewMuestreo);
                    if (oldIdPuntoOfMuestreoListNewMuestreo != null && !oldIdPuntoOfMuestreoListNewMuestreo.equals(punto)) {
                        oldIdPuntoOfMuestreoListNewMuestreo.getMuestreoList().remove(muestreoListNewMuestreo);
                        oldIdPuntoOfMuestreoListNewMuestreo = em.merge(oldIdPuntoOfMuestreoListNewMuestreo);
                    }
                }
            }
            for (MuestreoHistorico muestreoHistoricoListOldMuestreoHistorico : muestreoHistoricoListOld) {
                if (!muestreoHistoricoListNew.contains(muestreoHistoricoListOldMuestreoHistorico)) {
                    muestreoHistoricoListOldMuestreoHistorico.setFiMuestreoHistoricoPunto(null);
                    muestreoHistoricoListOldMuestreoHistorico = em.merge(muestreoHistoricoListOldMuestreoHistorico);
                }
            }
            for (MuestreoHistorico muestreoHistoricoListNewMuestreoHistorico : muestreoHistoricoListNew) {
                if (!muestreoHistoricoListOld.contains(muestreoHistoricoListNewMuestreoHistorico)) {
                    Punto oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico = muestreoHistoricoListNewMuestreoHistorico.getFiMuestreoHistoricoPunto();
                    muestreoHistoricoListNewMuestreoHistorico.setFiMuestreoHistoricoPunto(punto);
                    muestreoHistoricoListNewMuestreoHistorico = em.merge(muestreoHistoricoListNewMuestreoHistorico);
                    if (oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico != null && !oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico.equals(punto)) {
                        oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico.getMuestreoHistoricoList().remove(muestreoHistoricoListNewMuestreoHistorico);
                        oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico = em.merge(oldFiMuestreoHistoricoPuntoOfMuestreoHistoricoListNewMuestreoHistorico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = punto.getIdpunto();
                if (findPunto(id) == null) {
                    throw new NonexistentEntityException("The punto with id " + id + " no longer exists.");
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
            Punto punto;
            try {
                punto = em.getReference(Punto.class, id);
                punto.getIdpunto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The punto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestreo> muestreoListOrphanCheck = punto.getMuestreoList();
            for (Muestreo muestreoListOrphanCheckMuestreo : muestreoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Punto (" + punto + ") cannot be destroyed since the Muestreo " + muestreoListOrphanCheckMuestreo + " in its muestreoList field has a non-nullable idPunto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MuestreoHistorico> muestreoHistoricoList = punto.getMuestreoHistoricoList();
            for (MuestreoHistorico muestreoHistoricoListMuestreoHistorico : muestreoHistoricoList) {
                muestreoHistoricoListMuestreoHistorico.setFiMuestreoHistoricoPunto(null);
                muestreoHistoricoListMuestreoHistorico = em.merge(muestreoHistoricoListMuestreoHistorico);
            }
            em.remove(punto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Punto> findPuntoEntities() {
        return findPuntoEntities(true, -1, -1);
    }

    public List<Punto> findPuntoEntities(int maxResults, int firstResult) {
        return findPuntoEntities(false, maxResults, firstResult);
    }

    private List<Punto> findPuntoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Punto.class));
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

    public Punto findPunto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Punto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Punto> rt = cq.from(Punto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
