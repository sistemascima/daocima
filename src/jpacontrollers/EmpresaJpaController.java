/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Empresa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.OrdenCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*public void create(Empresa empresa) {
        if (empresa.getOrdenCompraList() == null) {
            empresa.setOrdenCompraList(new ArrayList<OrdenCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario fsEmpresaUsuarioCreacion = empresa.getFsEmpresaUsuarioCreacion();
            if (fsEmpresaUsuarioCreacion != null) {
                fsEmpresaUsuarioCreacion = em.getReference(fsEmpresaUsuarioCreacion.getClass(), fsEmpresaUsuarioCreacion.getPsUsuarioCodigo());
                empresa.setFsEmpresaUsuarioCreacion(fsEmpresaUsuarioCreacion);
            }
            SUsuario fsEmpresaUsuarioModificacion = empresa.getFsEmpresaUsuarioModificacion();
            if (fsEmpresaUsuarioModificacion != null) {
                fsEmpresaUsuarioModificacion = em.getReference(fsEmpresaUsuarioModificacion.getClass(), fsEmpresaUsuarioModificacion.getPsUsuarioCodigo());
                empresa.setFsEmpresaUsuarioModificacion(fsEmpresaUsuarioModificacion);
            }
            List<OrdenCompra> attachedOrdenCompraList = new ArrayList<OrdenCompra>();
           /* for (OrdenCompra ordenCompraListOrdenCompraToAttach : empresa.getOrdenCompraList()) {
                ordenCompraListOrdenCompraToAttach = em.getReference(ordenCompraListOrdenCompraToAttach.getClass(), ordenCompraListOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraList.add(ordenCompraListOrdenCompraToAttach);
            }
            empresa.setOrdenCompraList(attachedOrdenCompraList);
            em.persist(empresa);
            if (fsEmpresaUsuarioCreacion != null) {
                fsEmpresaUsuarioCreacion.getEmpresaList().add(empresa);
                fsEmpresaUsuarioCreacion = em.merge(fsEmpresaUsuarioCreacion);
            }
            if (fsEmpresaUsuarioModificacion != null) {
                fsEmpresaUsuarioModificacion.getEmpresaList().add(empresa);
                fsEmpresaUsuarioModificacion = em.merge(fsEmpresaUsuarioModificacion);
            }
           /* for (OrdenCompra ordenCompraListOrdenCompra : empresa.getOrdenCompraList()) {
                Empresa oldFsOrdecompEmpresaOfOrdenCompraListOrdenCompra = ordenCompraListOrdenCompra.getFsOrdecompEmpresa();
                ordenCompraListOrdenCompra.setFsOrdecompEmpresa(empresa);
                ordenCompraListOrdenCompra = em.merge(ordenCompraListOrdenCompra);
                if (oldFsOrdecompEmpresaOfOrdenCompraListOrdenCompra != null) {
                    oldFsOrdecompEmpresaOfOrdenCompraListOrdenCompra.getOrdenCompraList().remove(ordenCompraListOrdenCompra);
                    oldFsOrdecompEmpresaOfOrdenCompraListOrdenCompra = em.merge(oldFsOrdecompEmpresaOfOrdenCompraListOrdenCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getPiEmpresaId());
            SUsuario fsEmpresaUsuarioCreacionOld = persistentEmpresa.getFsEmpresaUsuarioCreacion();
            SUsuario fsEmpresaUsuarioCreacionNew = empresa.getFsEmpresaUsuarioCreacion();
            SUsuario fsEmpresaUsuarioModificacionOld = persistentEmpresa.getFsEmpresaUsuarioModificacion();
            SUsuario fsEmpresaUsuarioModificacionNew = empresa.getFsEmpresaUsuarioModificacion();
            //List<OrdenCompra> ordenCompraListOld = persistentEmpresa.getOrdenCompraList();
            //List<OrdenCompra> ordenCompraListNew = empresa.getOrdenCompraList();
            if (fsEmpresaUsuarioCreacionNew != null) {
                fsEmpresaUsuarioCreacionNew = em.getReference(fsEmpresaUsuarioCreacionNew.getClass(), fsEmpresaUsuarioCreacionNew.getPsUsuarioCodigo());
                empresa.setFsEmpresaUsuarioCreacion(fsEmpresaUsuarioCreacionNew);
            }
            if (fsEmpresaUsuarioModificacionNew != null) {
                fsEmpresaUsuarioModificacionNew = em.getReference(fsEmpresaUsuarioModificacionNew.getClass(), fsEmpresaUsuarioModificacionNew.getPsUsuarioCodigo());
                empresa.setFsEmpresaUsuarioModificacion(fsEmpresaUsuarioModificacionNew);
            }
            List<OrdenCompra> attachedOrdenCompraListNew = new ArrayList<OrdenCompra>();
            for (OrdenCompra ordenCompraListNewOrdenCompraToAttach : ordenCompraListNew) {
                ordenCompraListNewOrdenCompraToAttach = em.getReference(ordenCompraListNewOrdenCompraToAttach.getClass(), ordenCompraListNewOrdenCompraToAttach.getPiOrdecompNumero());
                attachedOrdenCompraListNew.add(ordenCompraListNewOrdenCompraToAttach);
            }
            //ordenCompraListNew = attachedOrdenCompraListNew;
           // empresa.setOrdenCompraList(ordenCompraListNew);
            empresa = em.merge(empresa);
            if (fsEmpresaUsuarioCreacionOld != null && !fsEmpresaUsuarioCreacionOld.equals(fsEmpresaUsuarioCreacionNew)) {
                fsEmpresaUsuarioCreacionOld.getEmpresaList().remove(empresa);
                fsEmpresaUsuarioCreacionOld = em.merge(fsEmpresaUsuarioCreacionOld);
            }
            if (fsEmpresaUsuarioCreacionNew != null && !fsEmpresaUsuarioCreacionNew.equals(fsEmpresaUsuarioCreacionOld)) {
                fsEmpresaUsuarioCreacionNew.getEmpresaList().add(empresa);
                fsEmpresaUsuarioCreacionNew = em.merge(fsEmpresaUsuarioCreacionNew);
            }
            if (fsEmpresaUsuarioModificacionOld != null && !fsEmpresaUsuarioModificacionOld.equals(fsEmpresaUsuarioModificacionNew)) {
                fsEmpresaUsuarioModificacionOld.getEmpresaList().remove(empresa);
                fsEmpresaUsuarioModificacionOld = em.merge(fsEmpresaUsuarioModificacionOld);
            }
            if (fsEmpresaUsuarioModificacionNew != null && !fsEmpresaUsuarioModificacionNew.equals(fsEmpresaUsuarioModificacionOld)) {
                fsEmpresaUsuarioModificacionNew.getEmpresaList().add(empresa);
                fsEmpresaUsuarioModificacionNew = em.merge(fsEmpresaUsuarioModificacionNew);
            }
            /*for (OrdenCompra ordenCompraListOldOrdenCompra : ordenCompraListOld) {
                if (!ordenCompraListNew.contains(ordenCompraListOldOrdenCompra)) {
                    ordenCompraListOldOrdenCompra.setFsOrdecompEmpresa(null);
                    ordenCompraListOldOrdenCompra = em.merge(ordenCompraListOldOrdenCompra);
                }
            }
            for (OrdenCompra ordenCompraListNewOrdenCompra : ordenCompraListNew) {
                if (!ordenCompraListOld.contains(ordenCompraListNewOrdenCompra)) {
                    Empresa oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra = ordenCompraListNewOrdenCompra.getFsOrdecompEmpresa();
                    ordenCompraListNewOrdenCompra.setFsOrdecompEmpresa(empresa);
                    ordenCompraListNewOrdenCompra = em.merge(ordenCompraListNewOrdenCompra);
                    if (oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra != null && !oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra.equals(empresa)) {
                        oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra.getOrdenCompraList().remove(ordenCompraListNewOrdenCompra);
                        oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra = em.merge(oldFsOrdecompEmpresaOfOrdenCompraListNewOrdenCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getPiEmpresaId();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getPiEmpresaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            SUsuario fsEmpresaUsuarioCreacion = empresa.getFsEmpresaUsuarioCreacion();
            if (fsEmpresaUsuarioCreacion != null) {
                fsEmpresaUsuarioCreacion.getEmpresaList().remove(empresa);
                fsEmpresaUsuarioCreacion = em.merge(fsEmpresaUsuarioCreacion);
            }
            SUsuario fsEmpresaUsuarioModificacion = empresa.getFsEmpresaUsuarioModificacion();
            if (fsEmpresaUsuarioModificacion != null) {
                fsEmpresaUsuarioModificacion.getEmpresaList().remove(empresa);
                fsEmpresaUsuarioModificacion = em.merge(fsEmpresaUsuarioModificacion);
            }
            /*List<OrdenCompra> ordenCompraList = empresa.getOrdenCompraList();
            for (OrdenCompra ordenCompraListOrdenCompra : ordenCompraList) {
                ordenCompraListOrdenCompra.setFsOrdecompEmpresa(null);
                ordenCompraListOrdenCompra = em.merge(ordenCompraListOrdenCompra);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Empresa> entregarListaEmpresas() {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM empresa ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta,Empresa.class);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Empresa> entregarEmpresaPorNombre(String nombreEmpresa) {
         EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM empresa where ps_empresa_nombre= ?  ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta,Empresa.class);
            q.setParameter(1, nombreEmpresa);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Empresa> entregarEmpresaPorId(Integer piEmpresaId) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM empresa where pi_empresa_id= ?  ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(consulta,Empresa.class);
            q.setParameter(1, piEmpresaId);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }}
    
}
