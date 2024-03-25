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
import entidades.Cliente;
import entidades.Proyecto;
import entidades.Muestreo;
import entidades.Muestra;
import java.util.ArrayList;
import java.util.List;
import entidades.FotoHidrobiologia;
import entidades.Reporte;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ReporteJpaController1 implements Serializable {

    public ReporteJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reporte reporte) throws PreexistingEntityException, Exception {
        if (reporte.getMuestraList() == null) {
            reporte.setMuestraList(new ArrayList<Muestra>());
        }
        if (reporte.getFotoHidrobiologiaList() == null) {
            reporte.setFotoHidrobiologiaList(new ArrayList<FotoHidrobiologia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = reporte.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getClientePK());
                reporte.setIdCliente(idCliente);
            }
            Cliente clienteFacturacion = reporte.getClienteFacturacion();
            if (clienteFacturacion != null) {
                clienteFacturacion = em.getReference(clienteFacturacion.getClass(), clienteFacturacion.getClientePK());
                reporte.setClienteFacturacion(clienteFacturacion);
            }
            Proyecto idProyecto = reporte.getIdProyecto();
            if (idProyecto != null) {
                idProyecto = em.getReference(idProyecto.getClass(), idProyecto.getIdProyecto());
                reporte.setIdProyecto(idProyecto);
            }
            Muestreo idMuestreo = reporte.getIdMuestreo();
            if (idMuestreo != null) {
                idMuestreo = em.getReference(idMuestreo.getClass(), idMuestreo.getIdMuestreo());
                reporte.setIdMuestreo(idMuestreo);
            }
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : reporte.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getIdmuestra());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            reporte.setMuestraList(attachedMuestraList);
            List<FotoHidrobiologia> attachedFotoHidrobiologiaList = new ArrayList<FotoHidrobiologia>();
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologiaToAttach : reporte.getFotoHidrobiologiaList()) {
                fotoHidrobiologiaListFotoHidrobiologiaToAttach = em.getReference(fotoHidrobiologiaListFotoHidrobiologiaToAttach.getClass(), fotoHidrobiologiaListFotoHidrobiologiaToAttach.getPiFotohidroId());
                attachedFotoHidrobiologiaList.add(fotoHidrobiologiaListFotoHidrobiologiaToAttach);
            }
            reporte.setFotoHidrobiologiaList(attachedFotoHidrobiologiaList);
            em.persist(reporte);
            if (idCliente != null) {
                idCliente.getReporteList().add(reporte);
                idCliente = em.merge(idCliente);
            }
            if (clienteFacturacion != null) {
                clienteFacturacion.getReporteList().add(reporte);
                clienteFacturacion = em.merge(clienteFacturacion);
            }
            if (idProyecto != null) {
                idProyecto.getReporteList().add(reporte);
                idProyecto = em.merge(idProyecto);
            }
            if (idMuestreo != null) {
                idMuestreo.getReporteList().add(reporte);
                idMuestreo = em.merge(idMuestreo);
            }
            for (Muestra muestraListMuestra : reporte.getMuestraList()) {
                Reporte oldReporteOfMuestraListMuestra = muestraListMuestra.getReporte();
                muestraListMuestra.setReporte(reporte);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldReporteOfMuestraListMuestra != null) {
                    oldReporteOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldReporteOfMuestraListMuestra = em.merge(oldReporteOfMuestraListMuestra);
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologia : reporte.getFotoHidrobiologiaList()) {
                Reporte oldFsFotohidroReporteOfFotoHidrobiologiaListFotoHidrobiologia = fotoHidrobiologiaListFotoHidrobiologia.getFsFotohidroReporte();
                fotoHidrobiologiaListFotoHidrobiologia.setFsFotohidroReporte(reporte);
                fotoHidrobiologiaListFotoHidrobiologia = em.merge(fotoHidrobiologiaListFotoHidrobiologia);
                if (oldFsFotohidroReporteOfFotoHidrobiologiaListFotoHidrobiologia != null) {
                    oldFsFotohidroReporteOfFotoHidrobiologiaListFotoHidrobiologia.getFotoHidrobiologiaList().remove(fotoHidrobiologiaListFotoHidrobiologia);
                    oldFsFotohidroReporteOfFotoHidrobiologiaListFotoHidrobiologia = em.merge(oldFsFotohidroReporteOfFotoHidrobiologiaListFotoHidrobiologia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReporte(reporte.getPiReporteId()) != null) {
                throw new PreexistingEntityException("Reporte " + reporte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reporte reporte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte persistentReporte = em.find(Reporte.class, reporte.getPiReporteId());
            Cliente idClienteOld = persistentReporte.getIdCliente();
            Cliente idClienteNew = reporte.getIdCliente();
            Cliente clienteFacturacionOld = persistentReporte.getClienteFacturacion();
            Cliente clienteFacturacionNew = reporte.getClienteFacturacion();
            Proyecto idProyectoOld = persistentReporte.getIdProyecto();
            Proyecto idProyectoNew = reporte.getIdProyecto();
            Muestreo idMuestreoOld = persistentReporte.getIdMuestreo();
            Muestreo idMuestreoNew = reporte.getIdMuestreo();
            List<Muestra> muestraListOld = persistentReporte.getMuestraList();
            List<Muestra> muestraListNew = reporte.getMuestraList();
            List<FotoHidrobiologia> fotoHidrobiologiaListOld = persistentReporte.getFotoHidrobiologiaList();
            List<FotoHidrobiologia> fotoHidrobiologiaListNew = reporte.getFotoHidrobiologiaList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getClientePK());
                reporte.setIdCliente(idClienteNew);
            }
            if (clienteFacturacionNew != null) {
                clienteFacturacionNew = em.getReference(clienteFacturacionNew.getClass(), clienteFacturacionNew.getClientePK());
                reporte.setClienteFacturacion(clienteFacturacionNew);
            }
            if (idProyectoNew != null) {
                idProyectoNew = em.getReference(idProyectoNew.getClass(), idProyectoNew.getIdProyecto());
                reporte.setIdProyecto(idProyectoNew);
            }
            if (idMuestreoNew != null) {
                idMuestreoNew = em.getReference(idMuestreoNew.getClass(), idMuestreoNew.getIdMuestreo());
                reporte.setIdMuestreo(idMuestreoNew);
            }
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getIdmuestra());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            reporte.setMuestraList(muestraListNew);
            List<FotoHidrobiologia> attachedFotoHidrobiologiaListNew = new ArrayList<FotoHidrobiologia>();
            for (FotoHidrobiologia fotoHidrobiologiaListNewFotoHidrobiologiaToAttach : fotoHidrobiologiaListNew) {
                fotoHidrobiologiaListNewFotoHidrobiologiaToAttach = em.getReference(fotoHidrobiologiaListNewFotoHidrobiologiaToAttach.getClass(), fotoHidrobiologiaListNewFotoHidrobiologiaToAttach.getPiFotohidroId());
                attachedFotoHidrobiologiaListNew.add(fotoHidrobiologiaListNewFotoHidrobiologiaToAttach);
            }
            fotoHidrobiologiaListNew = attachedFotoHidrobiologiaListNew;
            reporte.setFotoHidrobiologiaList(fotoHidrobiologiaListNew);
            reporte = em.merge(reporte);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getReporteList().remove(reporte);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getReporteList().add(reporte);
                idClienteNew = em.merge(idClienteNew);
            }
            if (clienteFacturacionOld != null && !clienteFacturacionOld.equals(clienteFacturacionNew)) {
                clienteFacturacionOld.getReporteList().remove(reporte);
                clienteFacturacionOld = em.merge(clienteFacturacionOld);
            }
            if (clienteFacturacionNew != null && !clienteFacturacionNew.equals(clienteFacturacionOld)) {
                clienteFacturacionNew.getReporteList().add(reporte);
                clienteFacturacionNew = em.merge(clienteFacturacionNew);
            }
            if (idProyectoOld != null && !idProyectoOld.equals(idProyectoNew)) {
                idProyectoOld.getReporteList().remove(reporte);
                idProyectoOld = em.merge(idProyectoOld);
            }
            if (idProyectoNew != null && !idProyectoNew.equals(idProyectoOld)) {
                idProyectoNew.getReporteList().add(reporte);
                idProyectoNew = em.merge(idProyectoNew);
            }
            if (idMuestreoOld != null && !idMuestreoOld.equals(idMuestreoNew)) {
                idMuestreoOld.getReporteList().remove(reporte);
                idMuestreoOld = em.merge(idMuestreoOld);
            }
            if (idMuestreoNew != null && !idMuestreoNew.equals(idMuestreoOld)) {
                idMuestreoNew.getReporteList().add(reporte);
                idMuestreoNew = em.merge(idMuestreoNew);
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    muestraListOldMuestra.setReporte(null);
                    muestraListOldMuestra = em.merge(muestraListOldMuestra);
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Reporte oldReporteOfMuestraListNewMuestra = muestraListNewMuestra.getReporte();
                    muestraListNewMuestra.setReporte(reporte);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldReporteOfMuestraListNewMuestra != null && !oldReporteOfMuestraListNewMuestra.equals(reporte)) {
                        oldReporteOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldReporteOfMuestraListNewMuestra = em.merge(oldReporteOfMuestraListNewMuestra);
                    }
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListOldFotoHidrobiologia : fotoHidrobiologiaListOld) {
                if (!fotoHidrobiologiaListNew.contains(fotoHidrobiologiaListOldFotoHidrobiologia)) {
                    fotoHidrobiologiaListOldFotoHidrobiologia.setFsFotohidroReporte(null);
                    fotoHidrobiologiaListOldFotoHidrobiologia = em.merge(fotoHidrobiologiaListOldFotoHidrobiologia);
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListNewFotoHidrobiologia : fotoHidrobiologiaListNew) {
                if (!fotoHidrobiologiaListOld.contains(fotoHidrobiologiaListNewFotoHidrobiologia)) {
                    Reporte oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia = fotoHidrobiologiaListNewFotoHidrobiologia.getFsFotohidroReporte();
                    fotoHidrobiologiaListNewFotoHidrobiologia.setFsFotohidroReporte(reporte);
                    fotoHidrobiologiaListNewFotoHidrobiologia = em.merge(fotoHidrobiologiaListNewFotoHidrobiologia);
                    if (oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia != null && !oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia.equals(reporte)) {
                        oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia.getFotoHidrobiologiaList().remove(fotoHidrobiologiaListNewFotoHidrobiologia);
                        oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia = em.merge(oldFsFotohidroReporteOfFotoHidrobiologiaListNewFotoHidrobiologia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reporte.getPiReporteId();
                if (findReporte(id) == null) {
                    throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
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
            Reporte reporte;
            try {
                reporte = em.getReference(Reporte.class, id);
                reporte.getPiReporteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = reporte.getIdCliente();
            if (idCliente != null) {
                idCliente.getReporteList().remove(reporte);
                idCliente = em.merge(idCliente);
            }
            Cliente clienteFacturacion = reporte.getClienteFacturacion();
            if (clienteFacturacion != null) {
                clienteFacturacion.getReporteList().remove(reporte);
                clienteFacturacion = em.merge(clienteFacturacion);
            }
            Proyecto idProyecto = reporte.getIdProyecto();
            if (idProyecto != null) {
                idProyecto.getReporteList().remove(reporte);
                idProyecto = em.merge(idProyecto);
            }
            Muestreo idMuestreo = reporte.getIdMuestreo();
            if (idMuestreo != null) {
                idMuestreo.getReporteList().remove(reporte);
                idMuestreo = em.merge(idMuestreo);
            }
            List<Muestra> muestraList = reporte.getMuestraList();
            for (Muestra muestraListMuestra : muestraList) {
                muestraListMuestra.setReporte(null);
                muestraListMuestra = em.merge(muestraListMuestra);
            }
            List<FotoHidrobiologia> fotoHidrobiologiaList = reporte.getFotoHidrobiologiaList();
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologia : fotoHidrobiologiaList) {
                fotoHidrobiologiaListFotoHidrobiologia.setFsFotohidroReporte(null);
                fotoHidrobiologiaListFotoHidrobiologia = em.merge(fotoHidrobiologiaListFotoHidrobiologia);
            }
            em.remove(reporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reporte> findReporteEntities() {
        return findReporteEntities(true, -1, -1);
    }

    public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
        return findReporteEntities(false, maxResults, firstResult);
    }

    private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reporte.class));
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

    public Reporte findReporte(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reporte> rt = cq.from(Reporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
