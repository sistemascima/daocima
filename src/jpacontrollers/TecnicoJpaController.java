/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cargo;
import entidades.Cliente;
import entidades.Muestreo;
import java.util.ArrayList;
import java.util.List;
import entidades.Muestra;
import entidades.Tecnico;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TOSHIBA
 */
public class TecnicoJpaController implements Serializable {

    public TecnicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnico tecnico) throws PreexistingEntityException, Exception {
        if (tecnico.getMuestreoList() == null) {
            tecnico.setMuestreoList(new ArrayList<Muestreo>());
        }
        if (tecnico.getMuestraList() == null) {
            tecnico.setMuestraList(new ArrayList<Muestra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo = tecnico.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getPsCargoCodigo());
                tecnico.setCargo(cargo);
            }
            List<Muestreo> attachedMuestreoList = new ArrayList<Muestreo>();
            for (Muestreo muestreoListMuestreoToAttach : tecnico.getMuestreoList()) {
                muestreoListMuestreoToAttach = em.getReference(muestreoListMuestreoToAttach.getClass(), muestreoListMuestreoToAttach.getIdMuestreo());
                attachedMuestreoList.add(muestreoListMuestreoToAttach);
            }
            tecnico.setMuestreoList(attachedMuestreoList);
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : tecnico.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getIdmuestra());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            tecnico.setMuestraList(attachedMuestraList);
            em.persist(tecnico);
            if (cargo != null) {
                cargo.getTecnicoList().add(tecnico);
                cargo = em.merge(cargo);
            }
            for (Muestreo muestreoListMuestreo : tecnico.getMuestreoList()) {
                Tecnico oldRealizadoOfMuestreoListMuestreo = muestreoListMuestreo.getRealizado();
                muestreoListMuestreo.setRealizado(tecnico);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
                if (oldRealizadoOfMuestreoListMuestreo != null) {
                    oldRealizadoOfMuestreoListMuestreo.getMuestreoList().remove(muestreoListMuestreo);
                    oldRealizadoOfMuestreoListMuestreo = em.merge(oldRealizadoOfMuestreoListMuestreo);
                }
            }
            for (Muestra muestraListMuestra : tecnico.getMuestraList()) {
                Tecnico oldIdResponsableOfMuestraListMuestra = muestraListMuestra.getIdResponsable();
                muestraListMuestra.setIdResponsable(tecnico);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldIdResponsableOfMuestraListMuestra != null) {
                    oldIdResponsableOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldIdResponsableOfMuestraListMuestra = em.merge(oldIdResponsableOfMuestraListMuestra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTecnico(tecnico.getNombre()) != null) {
                throw new PreexistingEntityException("Tecnico " + tecnico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnico tecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico persistentTecnico = em.find(Tecnico.class, tecnico.getNombre());
            Cargo cargoOld = persistentTecnico.getCargo();
            Cargo cargoNew = tecnico.getCargo();
            List<Muestreo> muestreoListOld = persistentTecnico.getMuestreoList();
            List<Muestreo> muestreoListNew = tecnico.getMuestreoList();
            List<Muestra> muestraListOld = persistentTecnico.getMuestraList();
            List<Muestra> muestraListNew = tecnico.getMuestraList();
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getPsCargoCodigo());
                tecnico.setCargo(cargoNew);
            }
            List<Muestreo> attachedMuestreoListNew = new ArrayList<Muestreo>();
            for (Muestreo muestreoListNewMuestreoToAttach : muestreoListNew) {
                muestreoListNewMuestreoToAttach = em.getReference(muestreoListNewMuestreoToAttach.getClass(), muestreoListNewMuestreoToAttach.getIdMuestreo());
                attachedMuestreoListNew.add(muestreoListNewMuestreoToAttach);
            }
            muestreoListNew = attachedMuestreoListNew;
            tecnico.setMuestreoList(muestreoListNew);
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getIdmuestra());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            tecnico.setMuestraList(muestraListNew);
            tecnico = em.merge(tecnico);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getTecnicoList().remove(tecnico);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getTecnicoList().add(tecnico);
                cargoNew = em.merge(cargoNew);
            }
            for (Muestreo muestreoListOldMuestreo : muestreoListOld) {
                if (!muestreoListNew.contains(muestreoListOldMuestreo)) {
                    muestreoListOldMuestreo.setRealizado(null);
                    muestreoListOldMuestreo = em.merge(muestreoListOldMuestreo);
                }
            }
            for (Muestreo muestreoListNewMuestreo : muestreoListNew) {
                if (!muestreoListOld.contains(muestreoListNewMuestreo)) {
                    Tecnico oldRealizadoOfMuestreoListNewMuestreo = muestreoListNewMuestreo.getRealizado();
                    muestreoListNewMuestreo.setRealizado(tecnico);
                    muestreoListNewMuestreo = em.merge(muestreoListNewMuestreo);
                    if (oldRealizadoOfMuestreoListNewMuestreo != null && !oldRealizadoOfMuestreoListNewMuestreo.equals(tecnico)) {
                        oldRealizadoOfMuestreoListNewMuestreo.getMuestreoList().remove(muestreoListNewMuestreo);
                        oldRealizadoOfMuestreoListNewMuestreo = em.merge(oldRealizadoOfMuestreoListNewMuestreo);
                    }
                }
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    muestraListOldMuestra.setIdResponsable(null);
                    muestraListOldMuestra = em.merge(muestraListOldMuestra);
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Tecnico oldIdResponsableOfMuestraListNewMuestra = muestraListNewMuestra.getIdResponsable();
                    muestraListNewMuestra.setIdResponsable(tecnico);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldIdResponsableOfMuestraListNewMuestra != null && !oldIdResponsableOfMuestraListNewMuestra.equals(tecnico)) {
                        oldIdResponsableOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldIdResponsableOfMuestraListNewMuestra = em.merge(oldIdResponsableOfMuestraListNewMuestra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tecnico.getNombre();
                if (findTecnico(id) == null) {
                    throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico tecnico;
            try {
                tecnico = em.getReference(Tecnico.class, id);
                tecnico.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.", enfe);
            }
            Cargo cargo = tecnico.getCargo();
            if (cargo != null) {
                cargo.getTecnicoList().remove(tecnico);
                cargo = em.merge(cargo);
            }
            List<Muestreo> muestreoList = tecnico.getMuestreoList();
            for (Muestreo muestreoListMuestreo : muestreoList) {
                muestreoListMuestreo.setRealizado(null);
                muestreoListMuestreo = em.merge(muestreoListMuestreo);
            }
            List<Muestra> muestraList = tecnico.getMuestraList();
            for (Muestra muestraListMuestra : muestraList) {
                muestraListMuestra.setIdResponsable(null);
                muestraListMuestra = em.merge(muestraListMuestra);
            }
            em.remove(tecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   public List<Tecnico> findTecnicoEntities() {
        return findTecnicoEntities(true, -1, -1);
    }

    public List<Tecnico> findTecnicoEntities(int maxResults, int firstResult) {
        return findTecnicoEntities(false, maxResults, firstResult);
    }

    private List<Tecnico> findTecnicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnico.class));
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

    public Tecnico findTecnico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnico> rt = cq.from(Tecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     //Devuelve el tecnico con el parametro del nombre 
    public List getTecnico(String nombre) {
        
         EntityManager em = getEntityManager();
         String consulta = "SELECT * FROM tecnico where ps_tecnico_nombre=?";
         try {
            Query q = em.createNativeQuery(consulta, Tecnico.class);
            q.setParameter(1, nombre);
          
            return q.getResultList();
        } finally {
            em.close();
        }
       
    }

    public List<Tecnico> getTecnicos() {
         EntityManager em = getEntityManager();
         String consulta = "SELECT * FROM tecnico";
         try {
            Query q = em.createNativeQuery(consulta, Tecnico.class);      
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void crearTecnico(Tecnico tecnico) {
        EntityManager em = getEntityManager();
        try {
         String insert= "INSERT INTO tecnico (ps_tecnico_nombre, fs_tecnico_cargo) "
                    + "VALUES (?, ?)";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, tecnico.getNombre());
            insercion.setParameter(2, tecnico.getCargo());      
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }
    }
    
    
}
