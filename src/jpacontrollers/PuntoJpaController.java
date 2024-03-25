/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Muestreo;
import entidades.Punto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class PuntoJpaController implements Serializable {

    public PuntoJpaController(EntityManagerFactory emf) {
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
            em.persist(punto);
            /*for (Muestreo muestreoListMuestreo : punto.getMuestreoList()) {
                Punto oldIdPuntoOfMuestreoListMuestreo = muestreoListMuestreo.getIdPunto();
                muestreoListMuestreo.setIdPunto(punto);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
                if (oldIdPuntoOfMuestreoListMuestreo != null) {
                    oldIdPuntoOfMuestreoListMuestreo.getMuestreoList().remove(muestreoListMuestreo);
                    oldIdPuntoOfMuestreoListMuestreo = em.merge(oldIdPuntoOfMuestreoListMuestreo);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Punto punto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Punto persistentPunto = em.find(Punto.class, punto.getIdpunto());
            List<Muestreo> muestreoListOld = persistentPunto.getMuestreoList();
            List<Muestreo> muestreoListNew = punto.getMuestreoList();
            List<Muestreo> attachedMuestreoListNew = new ArrayList<Muestreo>();
            for (Muestreo muestreoListNewMuestreoToAttach : muestreoListNew) {
                muestreoListNewMuestreoToAttach = em.getReference(muestreoListNewMuestreoToAttach.getClass(), muestreoListNewMuestreoToAttach.getIdMuestreo());
                attachedMuestreoListNew.add(muestreoListNewMuestreoToAttach);
            }
            muestreoListNew = attachedMuestreoListNew;
            punto.setMuestreoList(muestreoListNew);
            punto = em.merge(punto);
            for (Muestreo muestreoListOldMuestreo : muestreoListOld) {
                if (!muestreoListNew.contains(muestreoListOldMuestreo)) {
                    muestreoListOldMuestreo.setIdPunto(null);
                    muestreoListOldMuestreo = em.merge(muestreoListOldMuestreo);
                }
            }
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            List<Muestreo> muestreoList = punto.getMuestreoList();
           for (Muestreo muestreoListMuestreo : muestreoList) {
                muestreoListMuestreo.setIdPunto(null);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
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

  

    public List<Punto> consultarPuntoPorNombre(String nombre) {
        EntityManager em = getEntityManager();

         String consulta = "SELECT punto FROM punto where s_punto_nombre = ? ";
         try {
            Query q = em.createNativeQuery(consulta, Muestreo.class);
            q.setParameter(1,nombre);
           
            return q.getResultList();
        } finally {
            em.close();
        } }

    public void actualizar(String nombrePunto , int idPunto) {   
        EntityManager em = getEntityManager();
        String insert = "UPDATE punto SET s_punto_nombre= ?  WHERE pi_punto_id= ?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, nombrePunto);
        insercion.setParameter(2, idPunto);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public int encontrarUltimoPunto() {
          EntityManager em = getEntityManager();

         String consulta = "select max(pi_punto_id) "
                + "from punto";
         try {
            Query q = em.createNativeQuery(consulta);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
 
}
