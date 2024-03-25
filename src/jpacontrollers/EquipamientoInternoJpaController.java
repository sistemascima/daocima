/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.EquipamientoInterno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.HojaVida;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class EquipamientoInternoJpaController implements Serializable {

    public EquipamientoInternoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EquipamientoInterno equipamientoInterno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaVida hojaVida = equipamientoInterno.getHojaVida();
            if (hojaVida != null) {
                hojaVida = em.getReference(hojaVida.getClass(), hojaVida.getHojaVidaPK());
                equipamientoInterno.setHojaVida(hojaVida);
            }
            SUsuario fsEquiInterUsuacrea = equipamientoInterno.getFsEquiInterUsuacrea();
            if (fsEquiInterUsuacrea != null) {
                fsEquiInterUsuacrea = em.getReference(fsEquiInterUsuacrea.getClass(), fsEquiInterUsuacrea.getPsUsuarioCodigo());
                equipamientoInterno.setFsEquiInterUsuacrea(fsEquiInterUsuacrea);
            }
            SUsuario fsEquiInterUsuamodi = equipamientoInterno.getFsEquiInterUsuamodi();
            if (fsEquiInterUsuamodi != null) {
                fsEquiInterUsuamodi = em.getReference(fsEquiInterUsuamodi.getClass(), fsEquiInterUsuamodi.getPsUsuarioCodigo());
                equipamientoInterno.setFsEquiInterUsuamodi(fsEquiInterUsuamodi);
            }
            em.persist(equipamientoInterno);
            if (hojaVida != null) {
                hojaVida.getEquipamientoInternoList().add(equipamientoInterno);
                hojaVida = em.merge(hojaVida);
            }
            if (fsEquiInterUsuacrea != null) {
                fsEquiInterUsuacrea.getEquipamientoInternoList().add(equipamientoInterno);
                fsEquiInterUsuacrea = em.merge(fsEquiInterUsuacrea);
            }
            if (fsEquiInterUsuamodi != null) {
                fsEquiInterUsuamodi.getEquipamientoInternoList().add(equipamientoInterno);
                fsEquiInterUsuamodi = em.merge(fsEquiInterUsuamodi);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EquipamientoInterno equipamientoInterno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipamientoInterno persistentEquipamientoInterno = em.find(EquipamientoInterno.class, equipamientoInterno.getPiEquiInterId());
            HojaVida hojaVidaOld = persistentEquipamientoInterno.getHojaVida();
            HojaVida hojaVidaNew = equipamientoInterno.getHojaVida();
            SUsuario fsEquiInterUsuacreaOld = persistentEquipamientoInterno.getFsEquiInterUsuacrea();
            SUsuario fsEquiInterUsuacreaNew = equipamientoInterno.getFsEquiInterUsuacrea();
            SUsuario fsEquiInterUsuamodiOld = persistentEquipamientoInterno.getFsEquiInterUsuamodi();
            SUsuario fsEquiInterUsuamodiNew = equipamientoInterno.getFsEquiInterUsuamodi();
            if (hojaVidaNew != null) {
                hojaVidaNew = em.getReference(hojaVidaNew.getClass(), hojaVidaNew.getHojaVidaPK());
                equipamientoInterno.setHojaVida(hojaVidaNew);
            }
            if (fsEquiInterUsuacreaNew != null) {
                fsEquiInterUsuacreaNew = em.getReference(fsEquiInterUsuacreaNew.getClass(), fsEquiInterUsuacreaNew.getPsUsuarioCodigo());
                equipamientoInterno.setFsEquiInterUsuacrea(fsEquiInterUsuacreaNew);
            }
            if (fsEquiInterUsuamodiNew != null) {
                fsEquiInterUsuamodiNew = em.getReference(fsEquiInterUsuamodiNew.getClass(), fsEquiInterUsuamodiNew.getPsUsuarioCodigo());
                equipamientoInterno.setFsEquiInterUsuamodi(fsEquiInterUsuamodiNew);
            }
            equipamientoInterno = em.merge(equipamientoInterno);
            if (hojaVidaOld != null && !hojaVidaOld.equals(hojaVidaNew)) {
                hojaVidaOld.getEquipamientoInternoList().remove(equipamientoInterno);
                hojaVidaOld = em.merge(hojaVidaOld);
            }
            if (hojaVidaNew != null && !hojaVidaNew.equals(hojaVidaOld)) {
                hojaVidaNew.getEquipamientoInternoList().add(equipamientoInterno);
                hojaVidaNew = em.merge(hojaVidaNew);
            }
            if (fsEquiInterUsuacreaOld != null && !fsEquiInterUsuacreaOld.equals(fsEquiInterUsuacreaNew)) {
                fsEquiInterUsuacreaOld.getEquipamientoInternoList().remove(equipamientoInterno);
                fsEquiInterUsuacreaOld = em.merge(fsEquiInterUsuacreaOld);
            }
            if (fsEquiInterUsuacreaNew != null && !fsEquiInterUsuacreaNew.equals(fsEquiInterUsuacreaOld)) {
                fsEquiInterUsuacreaNew.getEquipamientoInternoList().add(equipamientoInterno);
                fsEquiInterUsuacreaNew = em.merge(fsEquiInterUsuacreaNew);
            }
            if (fsEquiInterUsuamodiOld != null && !fsEquiInterUsuamodiOld.equals(fsEquiInterUsuamodiNew)) {
                fsEquiInterUsuamodiOld.getEquipamientoInternoList().remove(equipamientoInterno);
                fsEquiInterUsuamodiOld = em.merge(fsEquiInterUsuamodiOld);
            }
            if (fsEquiInterUsuamodiNew != null && !fsEquiInterUsuamodiNew.equals(fsEquiInterUsuamodiOld)) {
                fsEquiInterUsuamodiNew.getEquipamientoInternoList().add(equipamientoInterno);
                fsEquiInterUsuamodiNew = em.merge(fsEquiInterUsuamodiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipamientoInterno.getPiEquiInterId();
                if (findEquipamientoInterno(id) == null) {
                    throw new NonexistentEntityException("The equipamientoInterno with id " + id + " no longer exists.");
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
            EquipamientoInterno equipamientoInterno;
            try {
                equipamientoInterno = em.getReference(EquipamientoInterno.class, id);
                equipamientoInterno.getPiEquiInterId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipamientoInterno with id " + id + " no longer exists.", enfe);
            }
            HojaVida hojaVida = equipamientoInterno.getHojaVida();
            if (hojaVida != null) {
                hojaVida.getEquipamientoInternoList().remove(equipamientoInterno);
                hojaVida = em.merge(hojaVida);
            }
            SUsuario fsEquiInterUsuacrea = equipamientoInterno.getFsEquiInterUsuacrea();
            if (fsEquiInterUsuacrea != null) {
                fsEquiInterUsuacrea.getEquipamientoInternoList().remove(equipamientoInterno);
                fsEquiInterUsuacrea = em.merge(fsEquiInterUsuacrea);
            }
            SUsuario fsEquiInterUsuamodi = equipamientoInterno.getFsEquiInterUsuamodi();
            if (fsEquiInterUsuamodi != null) {
                fsEquiInterUsuamodi.getEquipamientoInternoList().remove(equipamientoInterno);
                fsEquiInterUsuamodi = em.merge(fsEquiInterUsuamodi);
            }
            em.remove(equipamientoInterno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EquipamientoInterno> findEquipamientoInternoEntities() {
        return findEquipamientoInternoEntities(true, -1, -1);
    }

    public List<EquipamientoInterno> findEquipamientoInternoEntities(int maxResults, int firstResult) {
        return findEquipamientoInternoEntities(false, maxResults, firstResult);
    }

    private List<EquipamientoInterno> findEquipamientoInternoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EquipamientoInterno.class));
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

    public EquipamientoInterno findEquipamientoInterno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EquipamientoInterno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipamientoInternoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EquipamientoInterno> rt = cq.from(EquipamientoInterno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
