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
import entidades.SUsuario;
import entidades.EquipamientoInterno;
import entidades.HojaVida;
import entidades.HojaVidaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class HojaVidaJpaController implements Serializable {

    public HojaVidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HojaVida hojaVida) throws PreexistingEntityException, Exception {
        if (hojaVida.getHojaVidaPK() == null) {
            hojaVida.setHojaVidaPK(new HojaVidaPK());
        }
        if (hojaVida.getEquipamientoInternoList() == null) {
            hojaVida.setEquipamientoInternoList(new ArrayList<EquipamientoInterno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsHojaVidaUsuacrea = hojaVida.getFsHojaVidaUsuacrea();
            if (fsHojaVidaUsuacrea != null) {
                fsHojaVidaUsuacrea = em.getReference(fsHojaVidaUsuacrea.getClass(), fsHojaVidaUsuacrea.getPsUsuarioCodigo());
                hojaVida.setFsHojaVidaUsuacrea(fsHojaVidaUsuacrea);
            }
            SUsuario fsHojaVidaUsuamodi = hojaVida.getFsHojaVidaUsuamodi();
            if (fsHojaVidaUsuamodi != null) {
                fsHojaVidaUsuamodi = em.getReference(fsHojaVidaUsuamodi.getClass(), fsHojaVidaUsuamodi.getPsUsuarioCodigo());
                hojaVida.setFsHojaVidaUsuamodi(fsHojaVidaUsuamodi);
            }
            List<EquipamientoInterno> attachedEquipamientoInternoList = new ArrayList<EquipamientoInterno>();
            for (EquipamientoInterno equipamientoInternoListEquipamientoInternoToAttach : hojaVida.getEquipamientoInternoList()) {
                equipamientoInternoListEquipamientoInternoToAttach = em.getReference(equipamientoInternoListEquipamientoInternoToAttach.getClass(), equipamientoInternoListEquipamientoInternoToAttach.getPiEquiInterId());
                attachedEquipamientoInternoList.add(equipamientoInternoListEquipamientoInternoToAttach);
            }
            hojaVida.setEquipamientoInternoList(attachedEquipamientoInternoList);
            em.persist(hojaVida);
            if (fsHojaVidaUsuacrea != null) {
                fsHojaVidaUsuacrea.getHojaVidaList().add(hojaVida);
                fsHojaVidaUsuacrea = em.merge(fsHojaVidaUsuacrea);
            }
            if (fsHojaVidaUsuamodi != null) {
                fsHojaVidaUsuamodi.getHojaVidaList().add(hojaVida);
                fsHojaVidaUsuamodi = em.merge(fsHojaVidaUsuamodi);
            }
            for (EquipamientoInterno equipamientoInternoListEquipamientoInterno : hojaVida.getEquipamientoInternoList()) {
                HojaVida oldHojaVidaOfEquipamientoInternoListEquipamientoInterno = equipamientoInternoListEquipamientoInterno.getHojaVida();
                equipamientoInternoListEquipamientoInterno.setHojaVida(hojaVida);
                equipamientoInternoListEquipamientoInterno = em.merge(equipamientoInternoListEquipamientoInterno);
                if (oldHojaVidaOfEquipamientoInternoListEquipamientoInterno != null) {
                    oldHojaVidaOfEquipamientoInternoListEquipamientoInterno.getEquipamientoInternoList().remove(equipamientoInternoListEquipamientoInterno);
                    oldHojaVidaOfEquipamientoInternoListEquipamientoInterno = em.merge(oldHojaVidaOfEquipamientoInternoListEquipamientoInterno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHojaVida(hojaVida.getHojaVidaPK()) != null) {
                throw new PreexistingEntityException("HojaVida " + hojaVida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HojaVida hojaVida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaVida persistentHojaVida = em.find(HojaVida.class, hojaVida.getHojaVidaPK());
            SUsuario fsHojaVidaUsuacreaOld = persistentHojaVida.getFsHojaVidaUsuacrea();
            SUsuario fsHojaVidaUsuacreaNew = hojaVida.getFsHojaVidaUsuacrea();
            SUsuario fsHojaVidaUsuamodiOld = persistentHojaVida.getFsHojaVidaUsuamodi();
            SUsuario fsHojaVidaUsuamodiNew = hojaVida.getFsHojaVidaUsuamodi();
            List<EquipamientoInterno> equipamientoInternoListOld = persistentHojaVida.getEquipamientoInternoList();
            List<EquipamientoInterno> equipamientoInternoListNew = hojaVida.getEquipamientoInternoList();
            if (fsHojaVidaUsuacreaNew != null) {
                fsHojaVidaUsuacreaNew = em.getReference(fsHojaVidaUsuacreaNew.getClass(), fsHojaVidaUsuacreaNew.getPsUsuarioCodigo());
                hojaVida.setFsHojaVidaUsuacrea(fsHojaVidaUsuacreaNew);
            }
            if (fsHojaVidaUsuamodiNew != null) {
                fsHojaVidaUsuamodiNew = em.getReference(fsHojaVidaUsuamodiNew.getClass(), fsHojaVidaUsuamodiNew.getPsUsuarioCodigo());
                hojaVida.setFsHojaVidaUsuamodi(fsHojaVidaUsuamodiNew);
            }
            List<EquipamientoInterno> attachedEquipamientoInternoListNew = new ArrayList<EquipamientoInterno>();
            for (EquipamientoInterno equipamientoInternoListNewEquipamientoInternoToAttach : equipamientoInternoListNew) {
                equipamientoInternoListNewEquipamientoInternoToAttach = em.getReference(equipamientoInternoListNewEquipamientoInternoToAttach.getClass(), equipamientoInternoListNewEquipamientoInternoToAttach.getPiEquiInterId());
                attachedEquipamientoInternoListNew.add(equipamientoInternoListNewEquipamientoInternoToAttach);
            }
            equipamientoInternoListNew = attachedEquipamientoInternoListNew;
            hojaVida.setEquipamientoInternoList(equipamientoInternoListNew);
            hojaVida = em.merge(hojaVida);
            if (fsHojaVidaUsuacreaOld != null && !fsHojaVidaUsuacreaOld.equals(fsHojaVidaUsuacreaNew)) {
                fsHojaVidaUsuacreaOld.getHojaVidaList().remove(hojaVida);
                fsHojaVidaUsuacreaOld = em.merge(fsHojaVidaUsuacreaOld);
            }
            if (fsHojaVidaUsuacreaNew != null && !fsHojaVidaUsuacreaNew.equals(fsHojaVidaUsuacreaOld)) {
                fsHojaVidaUsuacreaNew.getHojaVidaList().add(hojaVida);
                fsHojaVidaUsuacreaNew = em.merge(fsHojaVidaUsuacreaNew);
            }
            if (fsHojaVidaUsuamodiOld != null && !fsHojaVidaUsuamodiOld.equals(fsHojaVidaUsuamodiNew)) {
                fsHojaVidaUsuamodiOld.getHojaVidaList().remove(hojaVida);
                fsHojaVidaUsuamodiOld = em.merge(fsHojaVidaUsuamodiOld);
            }
            if (fsHojaVidaUsuamodiNew != null && !fsHojaVidaUsuamodiNew.equals(fsHojaVidaUsuamodiOld)) {
                fsHojaVidaUsuamodiNew.getHojaVidaList().add(hojaVida);
                fsHojaVidaUsuamodiNew = em.merge(fsHojaVidaUsuamodiNew);
            }
            for (EquipamientoInterno equipamientoInternoListOldEquipamientoInterno : equipamientoInternoListOld) {
                if (!equipamientoInternoListNew.contains(equipamientoInternoListOldEquipamientoInterno)) {
                    equipamientoInternoListOldEquipamientoInterno.setHojaVida(null);
                    equipamientoInternoListOldEquipamientoInterno = em.merge(equipamientoInternoListOldEquipamientoInterno);
                }
            }
            for (EquipamientoInterno equipamientoInternoListNewEquipamientoInterno : equipamientoInternoListNew) {
                if (!equipamientoInternoListOld.contains(equipamientoInternoListNewEquipamientoInterno)) {
                    HojaVida oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno = equipamientoInternoListNewEquipamientoInterno.getHojaVida();
                    equipamientoInternoListNewEquipamientoInterno.setHojaVida(hojaVida);
                    equipamientoInternoListNewEquipamientoInterno = em.merge(equipamientoInternoListNewEquipamientoInterno);
                    if (oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno != null && !oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno.equals(hojaVida)) {
                        oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno.getEquipamientoInternoList().remove(equipamientoInternoListNewEquipamientoInterno);
                        oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno = em.merge(oldHojaVidaOfEquipamientoInternoListNewEquipamientoInterno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HojaVidaPK id = hojaVida.getHojaVidaPK();
                if (findHojaVida(id) == null) {
                    throw new NonexistentEntityException("The hojaVida with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HojaVidaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HojaVida hojaVida;
            try {
                hojaVida = em.getReference(HojaVida.class, id);
                hojaVida.getHojaVidaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hojaVida with id " + id + " no longer exists.", enfe);
            }
            SUsuario fsHojaVidaUsuacrea = hojaVida.getFsHojaVidaUsuacrea();
            if (fsHojaVidaUsuacrea != null) {
                fsHojaVidaUsuacrea.getHojaVidaList().remove(hojaVida);
                fsHojaVidaUsuacrea = em.merge(fsHojaVidaUsuacrea);
            }
            SUsuario fsHojaVidaUsuamodi = hojaVida.getFsHojaVidaUsuamodi();
            if (fsHojaVidaUsuamodi != null) {
                fsHojaVidaUsuamodi.getHojaVidaList().remove(hojaVida);
                fsHojaVidaUsuamodi = em.merge(fsHojaVidaUsuamodi);
            }
            List<EquipamientoInterno> equipamientoInternoList = hojaVida.getEquipamientoInternoList();
            for (EquipamientoInterno equipamientoInternoListEquipamientoInterno : equipamientoInternoList) {
                equipamientoInternoListEquipamientoInterno.setHojaVida(null);
                equipamientoInternoListEquipamientoInterno = em.merge(equipamientoInternoListEquipamientoInterno);
            }
            em.remove(hojaVida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HojaVida> findHojaVidaEntities() {
        return findHojaVidaEntities(true, -1, -1);
    }

    public List<HojaVida> findHojaVidaEntities(int maxResults, int firstResult) {
        return findHojaVidaEntities(false, maxResults, firstResult);
    }

    private List<HojaVida> findHojaVidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HojaVida.class));
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

    public HojaVida findHojaVida(HojaVidaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HojaVida.class, id);
        } finally {
            em.close();
        }
    }

    public int getHojaVidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HojaVida> rt = cq.from(HojaVida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
