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
import entidades.Insumos;
import entidades.Vidrieria;
import entidades.Equipo;
import entidades.HojaDeVida;
import entidades.HojaDeVidaPK;
import entidades.Objeto;
import entidades.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Juan Felipe Fontecha
 */
public class HojaDeVidaJpaController implements Serializable {

    public HojaDeVidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HojaDeVida hojaDeVida) throws PreexistingEntityException, Exception {
        if (hojaDeVida.getHojaDeVidaPK() == null) {
            hojaDeVida.setHojaDeVidaPK(new HojaDeVidaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumos insumos = hojaDeVida.getInsumos();
            if (insumos != null) {
                insumos = em.getReference(insumos.getClass(), insumos.getInsumosPK());
                hojaDeVida.setInsumos(insumos);
            }
            Vidrieria vidreria = hojaDeVida.getVidreria();
            if (vidreria != null) {
                vidreria = em.getReference(vidreria.getClass(), vidreria.getVidreriaPK());
                hojaDeVida.setVidreria(vidreria);
            }
            Equipo equipo = hojaDeVida.getEquipo();
            if (equipo != null) {
                equipo = em.getReference(equipo.getClass(), equipo.getEquipoPK());
                hojaDeVida.setEquipo(equipo);
            }
            Objeto SHojaDeVidaObjetoId = hojaDeVida.getSHojaDeVidaObjetoId();
            if (SHojaDeVidaObjetoId != null) {
                SHojaDeVidaObjetoId = em.getReference(SHojaDeVidaObjetoId.getClass(), SHojaDeVidaObjetoId.getPiObjeto());
                hojaDeVida.setSHojaDeVidaObjetoId(SHojaDeVidaObjetoId);
            }
            Vehiculo vehiculo = hojaDeVida.getVehiculo();
            if (vehiculo != null) {
                vehiculo = em.getReference(vehiculo.getClass(), vehiculo.getVehiculoPK());
                hojaDeVida.setVehiculo(vehiculo);
            }
            em.persist(hojaDeVida);
            if (insumos != null) {
                HojaDeVida oldHojaDeVidaOfInsumos = insumos.getHojaDeVida();
                if (oldHojaDeVidaOfInsumos != null) {
                    oldHojaDeVidaOfInsumos.setInsumos(null);
                    oldHojaDeVidaOfInsumos = em.merge(oldHojaDeVidaOfInsumos);
                }
                insumos.setHojaDeVida(hojaDeVida);
                insumos = em.merge(insumos);
            }
            if (vidreria != null) {
                HojaDeVida oldHojaDeVidaOfVidreria = vidreria.getHojaDeVida();
                if (oldHojaDeVidaOfVidreria != null) {
                    oldHojaDeVidaOfVidreria.setVidreria(null);
                    oldHojaDeVidaOfVidreria = em.merge(oldHojaDeVidaOfVidreria);
                }
                vidreria.setHojaDeVida(hojaDeVida);
                vidreria = em.merge(vidreria);
            }
            if (equipo != null) {
                HojaDeVida oldHojaDeVidaOfEquipo = equipo.getHojaDeVida();
                if (oldHojaDeVidaOfEquipo != null) {
                    oldHojaDeVidaOfEquipo.setEquipo(null);
                    oldHojaDeVidaOfEquipo = em.merge(oldHojaDeVidaOfEquipo);
                }
                equipo.setHojaDeVida(hojaDeVida);
                equipo = em.merge(equipo);
            }
            if (SHojaDeVidaObjetoId != null) {
                SHojaDeVidaObjetoId.getHojaDeVidaList().add(hojaDeVida);
                SHojaDeVidaObjetoId = em.merge(SHojaDeVidaObjetoId);
            }
            if (vehiculo != null) {
                HojaDeVida oldHojaDeVidaOfVehiculo = vehiculo.getHojaDeVida();
                if (oldHojaDeVidaOfVehiculo != null) {
                    oldHojaDeVidaOfVehiculo.setVehiculo(null);
                    oldHojaDeVidaOfVehiculo = em.merge(oldHojaDeVidaOfVehiculo);
                }
                vehiculo.setHojaDeVida(hojaDeVida);
                vehiculo = em.merge(vehiculo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHojaDeVida(hojaDeVida.getHojaDeVidaPK()) != null) {
                throw new PreexistingEntityException("HojaDeVida " + hojaDeVida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HojaDeVida hojaDeVida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida persistentHojaDeVida = em.find(HojaDeVida.class, hojaDeVida.getHojaDeVidaPK());
            Insumos insumosOld = persistentHojaDeVida.getInsumos();
            Insumos insumosNew = hojaDeVida.getInsumos();
            Vidrieria vidreriaOld = persistentHojaDeVida.getVidreria();
            Vidrieria vidreriaNew = hojaDeVida.getVidreria();
            Equipo equipoOld = persistentHojaDeVida.getEquipo();
            Equipo equipoNew = hojaDeVida.getEquipo();
            Objeto SHojaDeVidaObjetoIdOld = persistentHojaDeVida.getSHojaDeVidaObjetoId();
            Objeto SHojaDeVidaObjetoIdNew = hojaDeVida.getSHojaDeVidaObjetoId();
            Vehiculo vehiculoOld = persistentHojaDeVida.getVehiculo();
            Vehiculo vehiculoNew = hojaDeVida.getVehiculo();
            List<String> illegalOrphanMessages = null;
            if (insumosOld != null && !insumosOld.equals(insumosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Insumos " + insumosOld + " since its hojaDeVida field is not nullable.");
            }
            if (vidreriaOld != null && !vidreriaOld.equals(vidreriaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Vidreria " + vidreriaOld + " since its hojaDeVida field is not nullable.");
            }
            if (equipoOld != null && !equipoOld.equals(equipoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Equipo " + equipoOld + " since its hojaDeVida field is not nullable.");
            }
            if (vehiculoOld != null && !vehiculoOld.equals(vehiculoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Vehiculo " + vehiculoOld + " since its hojaDeVida field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (insumosNew != null) {
                insumosNew = em.getReference(insumosNew.getClass(), insumosNew.getInsumosPK());
                hojaDeVida.setInsumos(insumosNew);
            }
            if (vidreriaNew != null) {
                vidreriaNew = em.getReference(vidreriaNew.getClass(), vidreriaNew.getVidreriaPK());
                hojaDeVida.setVidreria(vidreriaNew);
            }
            if (equipoNew != null) {
                equipoNew = em.getReference(equipoNew.getClass(), equipoNew.getEquipoPK());
                hojaDeVida.setEquipo(equipoNew);
            }
            if (SHojaDeVidaObjetoIdNew != null) {
                SHojaDeVidaObjetoIdNew = em.getReference(SHojaDeVidaObjetoIdNew.getClass(), SHojaDeVidaObjetoIdNew.getPiObjeto());
                hojaDeVida.setSHojaDeVidaObjetoId(SHojaDeVidaObjetoIdNew);
            }
            if (vehiculoNew != null) {
                vehiculoNew = em.getReference(vehiculoNew.getClass(), vehiculoNew.getVehiculoPK());
                hojaDeVida.setVehiculo(vehiculoNew);
            }
            hojaDeVida = em.merge(hojaDeVida);
            if (insumosNew != null && !insumosNew.equals(insumosOld)) {
                HojaDeVida oldHojaDeVidaOfInsumos = insumosNew.getHojaDeVida();
                if (oldHojaDeVidaOfInsumos != null) {
                    oldHojaDeVidaOfInsumos.setInsumos(null);
                    oldHojaDeVidaOfInsumos = em.merge(oldHojaDeVidaOfInsumos);
                }
                insumosNew.setHojaDeVida(hojaDeVida);
                insumosNew = em.merge(insumosNew);
            }
            if (vidreriaNew != null && !vidreriaNew.equals(vidreriaOld)) {
                HojaDeVida oldHojaDeVidaOfVidreria = vidreriaNew.getHojaDeVida();
                if (oldHojaDeVidaOfVidreria != null) {
                    oldHojaDeVidaOfVidreria.setVidreria(null);
                    oldHojaDeVidaOfVidreria = em.merge(oldHojaDeVidaOfVidreria);
                }
                vidreriaNew.setHojaDeVida(hojaDeVida);
                vidreriaNew = em.merge(vidreriaNew);
            }
            if (equipoNew != null && !equipoNew.equals(equipoOld)) {
                HojaDeVida oldHojaDeVidaOfEquipo = equipoNew.getHojaDeVida();
                if (oldHojaDeVidaOfEquipo != null) {
                    oldHojaDeVidaOfEquipo.setEquipo(null);
                    oldHojaDeVidaOfEquipo = em.merge(oldHojaDeVidaOfEquipo);
                }
                equipoNew.setHojaDeVida(hojaDeVida);
                equipoNew = em.merge(equipoNew);
            }
            if (SHojaDeVidaObjetoIdOld != null && !SHojaDeVidaObjetoIdOld.equals(SHojaDeVidaObjetoIdNew)) {
                SHojaDeVidaObjetoIdOld.getHojaDeVidaList().remove(hojaDeVida);
                SHojaDeVidaObjetoIdOld = em.merge(SHojaDeVidaObjetoIdOld);
            }
            if (SHojaDeVidaObjetoIdNew != null && !SHojaDeVidaObjetoIdNew.equals(SHojaDeVidaObjetoIdOld)) {
                SHojaDeVidaObjetoIdNew.getHojaDeVidaList().add(hojaDeVida);
                SHojaDeVidaObjetoIdNew = em.merge(SHojaDeVidaObjetoIdNew);
            }
            if (vehiculoNew != null && !vehiculoNew.equals(vehiculoOld)) {
                HojaDeVida oldHojaDeVidaOfVehiculo = vehiculoNew.getHojaDeVida();
                if (oldHojaDeVidaOfVehiculo != null) {
                    oldHojaDeVidaOfVehiculo.setVehiculo(null);
                    oldHojaDeVidaOfVehiculo = em.merge(oldHojaDeVidaOfVehiculo);
                }
                vehiculoNew.setHojaDeVida(hojaDeVida);
                vehiculoNew = em.merge(vehiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HojaDeVidaPK id = hojaDeVida.getHojaDeVidaPK();
                if (findHojaDeVida(id) == null) {
                    throw new NonexistentEntityException("The hojaDeVida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HojaDeVidaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaDeVida hojaDeVida;
            try {
                hojaDeVida = em.getReference(HojaDeVida.class, id);
                hojaDeVida.getHojaDeVidaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hojaDeVida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Insumos insumosOrphanCheck = hojaDeVida.getInsumos();
            if (insumosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HojaDeVida (" + hojaDeVida + ") cannot be destroyed since the Insumos " + insumosOrphanCheck + " in its insumos field has a non-nullable hojaDeVida field.");
            }
            Vidrieria vidreriaOrphanCheck = hojaDeVida.getVidreria();
            if (vidreriaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HojaDeVida (" + hojaDeVida + ") cannot be destroyed since the Vidreria " + vidreriaOrphanCheck + " in its vidreria field has a non-nullable hojaDeVida field.");
            }
            Equipo equipoOrphanCheck = hojaDeVida.getEquipo();
            if (equipoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HojaDeVida (" + hojaDeVida + ") cannot be destroyed since the Equipo " + equipoOrphanCheck + " in its equipo field has a non-nullable hojaDeVida field.");
            }
            Vehiculo vehiculoOrphanCheck = hojaDeVida.getVehiculo();
            if (vehiculoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HojaDeVida (" + hojaDeVida + ") cannot be destroyed since the Vehiculo " + vehiculoOrphanCheck + " in its vehiculo field has a non-nullable hojaDeVida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Objeto SHojaDeVidaObjetoId = hojaDeVida.getSHojaDeVidaObjetoId();
            if (SHojaDeVidaObjetoId != null) {
                SHojaDeVidaObjetoId.getHojaDeVidaList().remove(hojaDeVida);
                SHojaDeVidaObjetoId = em.merge(SHojaDeVidaObjetoId);
            }
            em.remove(hojaDeVida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HojaDeVida> findHojaDeVidaEntities() {
        return findHojaDeVidaEntities(true, -1, -1);
    }

    public List<HojaDeVida> findHojaDeVidaEntities(int maxResults, int firstResult) {
        return findHojaDeVidaEntities(false, maxResults, firstResult);
    }

    private List<HojaDeVida> findHojaDeVidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HojaDeVida.class));
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

    public HojaDeVida findHojaDeVida(HojaDeVidaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HojaDeVida.class, id);
        } finally {
            em.close();
        }
    }

    public int getHojaDeVidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HojaDeVida> rt = cq.from(HojaDeVida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
