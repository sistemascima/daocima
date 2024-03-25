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
import entidades.Muestra;
import entidades.MuestraAnalisis;
import entidades.Proveedor;
import entidades.Valor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class MuestraAnalisisJpaController1 implements Serializable {

    public MuestraAnalisisJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MuestraAnalisis muestraAnalisis) {
        if (muestraAnalisis.getValorList() == null) {
            muestraAnalisis.setValorList(new ArrayList<Valor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra idMuestra = muestraAnalisis.getIdMuestra();
            if (idMuestra != null) {
                idMuestra = em.getReference(idMuestra.getClass(), idMuestra.getIdmuestra());
                muestraAnalisis.setIdMuestra(idMuestra);
            }
            Proveedor fsMuestraanalProveedor = muestraAnalisis.getFsMuestraanalProveedor();
            if (fsMuestraanalProveedor != null) {
                fsMuestraanalProveedor = em.getReference(fsMuestraanalProveedor.getClass(), fsMuestraanalProveedor.getPsProveedorNit());
                muestraAnalisis.setFsMuestraanalProveedor(fsMuestraanalProveedor);
            }
            List<Valor> attachedValorList = new ArrayList<Valor>();
            for (Valor valorListValorToAttach : muestraAnalisis.getValorList()) {
                valorListValorToAttach = em.getReference(valorListValorToAttach.getClass(), valorListValorToAttach.getIdValor());
                attachedValorList.add(valorListValorToAttach);
            }
            muestraAnalisis.setValorList(attachedValorList);
            em.persist(muestraAnalisis);
            if (idMuestra != null) {
                idMuestra.getMuestraAnalisisList().add(muestraAnalisis);
                idMuestra = em.merge(idMuestra);
            }
            if (fsMuestraanalProveedor != null) {
                fsMuestraanalProveedor.getMuestraAnalisisList().add(muestraAnalisis);
                fsMuestraanalProveedor = em.merge(fsMuestraanalProveedor);
            }
            for (Valor valorListValor : muestraAnalisis.getValorList()) {
                MuestraAnalisis oldIdParametroOfValorListValor = valorListValor.getIdParametro();
                valorListValor.setIdParametro(muestraAnalisis);
                valorListValor = em.merge(valorListValor);
                if (oldIdParametroOfValorListValor != null) {
                    oldIdParametroOfValorListValor.getValorList().remove(valorListValor);
                    oldIdParametroOfValorListValor = em.merge(oldIdParametroOfValorListValor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MuestraAnalisis muestraAnalisis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MuestraAnalisis persistentMuestraAnalisis = em.find(MuestraAnalisis.class, muestraAnalisis.getIdmuestraAnalisis());
            Muestra idMuestraOld = persistentMuestraAnalisis.getIdMuestra();
            Muestra idMuestraNew = muestraAnalisis.getIdMuestra();
            Proveedor fsMuestraanalProveedorOld = persistentMuestraAnalisis.getFsMuestraanalProveedor();
            Proveedor fsMuestraanalProveedorNew = muestraAnalisis.getFsMuestraanalProveedor();
            List<Valor> valorListOld = persistentMuestraAnalisis.getValorList();
            List<Valor> valorListNew = muestraAnalisis.getValorList();
            if (idMuestraNew != null) {
                idMuestraNew = em.getReference(idMuestraNew.getClass(), idMuestraNew.getIdmuestra());
                muestraAnalisis.setIdMuestra(idMuestraNew);
            }
            if (fsMuestraanalProveedorNew != null) {
                fsMuestraanalProveedorNew = em.getReference(fsMuestraanalProveedorNew.getClass(), fsMuestraanalProveedorNew.getPsProveedorNit());
                muestraAnalisis.setFsMuestraanalProveedor(fsMuestraanalProveedorNew);
            }
            List<Valor> attachedValorListNew = new ArrayList<Valor>();
            for (Valor valorListNewValorToAttach : valorListNew) {
                valorListNewValorToAttach = em.getReference(valorListNewValorToAttach.getClass(), valorListNewValorToAttach.getIdValor());
                attachedValorListNew.add(valorListNewValorToAttach);
            }
            valorListNew = attachedValorListNew;
            muestraAnalisis.setValorList(valorListNew);
            muestraAnalisis = em.merge(muestraAnalisis);
            if (idMuestraOld != null && !idMuestraOld.equals(idMuestraNew)) {
                idMuestraOld.getMuestraAnalisisList().remove(muestraAnalisis);
                idMuestraOld = em.merge(idMuestraOld);
            }
            if (idMuestraNew != null && !idMuestraNew.equals(idMuestraOld)) {
                idMuestraNew.getMuestraAnalisisList().add(muestraAnalisis);
                idMuestraNew = em.merge(idMuestraNew);
            }
            if (fsMuestraanalProveedorOld != null && !fsMuestraanalProveedorOld.equals(fsMuestraanalProveedorNew)) {
                fsMuestraanalProveedorOld.getMuestraAnalisisList().remove(muestraAnalisis);
                fsMuestraanalProveedorOld = em.merge(fsMuestraanalProveedorOld);
            }
            if (fsMuestraanalProveedorNew != null && !fsMuestraanalProveedorNew.equals(fsMuestraanalProveedorOld)) {
                fsMuestraanalProveedorNew.getMuestraAnalisisList().add(muestraAnalisis);
                fsMuestraanalProveedorNew = em.merge(fsMuestraanalProveedorNew);
            }
            for (Valor valorListOldValor : valorListOld) {
                if (!valorListNew.contains(valorListOldValor)) {
                    valorListOldValor.setIdParametro(null);
                    valorListOldValor = em.merge(valorListOldValor);
                }
            }
            for (Valor valorListNewValor : valorListNew) {
                if (!valorListOld.contains(valorListNewValor)) {
                    MuestraAnalisis oldIdParametroOfValorListNewValor = valorListNewValor.getIdParametro();
                    valorListNewValor.setIdParametro(muestraAnalisis);
                    valorListNewValor = em.merge(valorListNewValor);
                    if (oldIdParametroOfValorListNewValor != null && !oldIdParametroOfValorListNewValor.equals(muestraAnalisis)) {
                        oldIdParametroOfValorListNewValor.getValorList().remove(valorListNewValor);
                        oldIdParametroOfValorListNewValor = em.merge(oldIdParametroOfValorListNewValor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraAnalisis.getIdmuestraAnalisis();
                if (findMuestraAnalisis(id) == null) {
                    throw new NonexistentEntityException("The muestraAnalisis with id " + id + " no longer exists.");
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
            MuestraAnalisis muestraAnalisis;
            try {
                muestraAnalisis = em.getReference(MuestraAnalisis.class, id);
                muestraAnalisis.getIdmuestraAnalisis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraAnalisis with id " + id + " no longer exists.", enfe);
            }
            Muestra idMuestra = muestraAnalisis.getIdMuestra();
            if (idMuestra != null) {
                idMuestra.getMuestraAnalisisList().remove(muestraAnalisis);
                idMuestra = em.merge(idMuestra);
            }
            Proveedor fsMuestraanalProveedor = muestraAnalisis.getFsMuestraanalProveedor();
            if (fsMuestraanalProveedor != null) {
                fsMuestraanalProveedor.getMuestraAnalisisList().remove(muestraAnalisis);
                fsMuestraanalProveedor = em.merge(fsMuestraanalProveedor);
            }
            List<Valor> valorList = muestraAnalisis.getValorList();
            for (Valor valorListValor : valorList) {
                valorListValor.setIdParametro(null);
                valorListValor = em.merge(valorListValor);
            }
            em.remove(muestraAnalisis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MuestraAnalisis> findMuestraAnalisisEntities() {
        return findMuestraAnalisisEntities(true, -1, -1);
    }

    public List<MuestraAnalisis> findMuestraAnalisisEntities(int maxResults, int firstResult) {
        return findMuestraAnalisisEntities(false, maxResults, firstResult);
    }

    private List<MuestraAnalisis> findMuestraAnalisisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MuestraAnalisis.class));
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

    public MuestraAnalisis findMuestraAnalisis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MuestraAnalisis.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraAnalisisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MuestraAnalisis> rt = cq.from(MuestraAnalisis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
