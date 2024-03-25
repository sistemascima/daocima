/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.FirmaUsuario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class FirmasUsuarioJpaController implements Serializable {

    public FirmasUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FirmaUsuario firmasUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario usuario = firmasUsuario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getPsUsuarioCodigo());
                firmasUsuario.setUsuario(usuario);
            }
            em.persist(firmasUsuario);
            /* if (usuario != null) {
                usuario.getFirmasUsuarioList().add(firmasUsuario);
                usuario = em.merge(usuario);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FirmaUsuario firmasUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FirmaUsuario persistentFirmasUsuario = em.find(FirmaUsuario.class, firmasUsuario.getIdFirmasUsuario());
            SUsuario usuarioOld = persistentFirmasUsuario.getUsuario();
            SUsuario usuarioNew = firmasUsuario.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getPsUsuarioCodigo());
                firmasUsuario.setUsuario(usuarioNew);
            }
            firmasUsuario = em.merge(firmasUsuario);
            /*   if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getFirmasUsuarioList().remove(firmasUsuario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getFirmasUsuarioList().add(firmasUsuario);
                usuarioNew = em.merge(usuarioNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = firmasUsuario.getIdFirmasUsuario();
                if (findFirmasUsuario(id) == null) {
                    throw new NonexistentEntityException("The firmasUsuario with id " + id + " no longer exists.");
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
            FirmaUsuario firmasUsuario;
            try {
                firmasUsuario = em.getReference(FirmaUsuario.class, id);
                firmasUsuario.getIdFirmasUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The firmasUsuario with id " + id + " no longer exists.", enfe);
            }
            SUsuario usuario = firmasUsuario.getUsuario();
            /* if (usuario != null) {
                usuario.getFirmasUsuarioList().remove(firmasUsuario);
                usuario = em.merge(usuario);
            }*/
            em.remove(firmasUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FirmaUsuario> findFirmasUsuarioEntities() {
        return findFirmasUsuarioEntities(true, -1, -1);
    }

    public List<FirmaUsuario> findFirmasUsuarioEntities(int maxResults, int firstResult) {
        return findFirmasUsuarioEntities(false, maxResults, firstResult);
    }

    private List<FirmaUsuario> findFirmasUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FirmaUsuario.class));
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

    public FirmaUsuario findFirmasUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FirmaUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFirmasUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FirmaUsuario> rt = cq.from(FirmaUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarFirmaCoordinador(String idReporte) {
        EntityManager em = getEntityManager();
        String consulta = "Select distinct reporte.pi_reporte_id,"
                + " (Select distinct firma_usuario.by_firmusua_firma"
                + " from firma_usuario"
                + " join reporte on"
                + " reporte.fs_reporte_usuarevi= firma_usuario.fs_firmusua_usuario"
                + " where reporte.pi_reporte_id=?) as firma"
                + " from reporte"
                + " where pi_reporte_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            q.setParameter(2, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarFirmaAnalista(String idReporte) {
        EntityManager em = getEntityManager();
        String consulta = "Select distinct reporte.pi_reporte_id, "
                + "(select distinct firma_usuario.by_firmusua_firma "
                + "from muestra_analisis "
                + "join muestra on muestra.pi_muestra_id= muestra_analisis.fi_muestraanal_muestra "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join firma_usuario on firma_usuario.fs_firmusua_usuario= valor.fs_valor_usuamodi "
                + "where muestra.fs_muestra_reporte=? "
                + ") as firma "
                + "from reporte "
                + "where pi_reporte_id=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            q.setParameter(2, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarFirmaDirector(String idReporte) {
        EntityManager em = getEntityManager();
        String consulta = "Select reporte.pi_reporte_id,"
                + " (Select distinct firma_usuario.by_firmusua_firma"
                + " from firma_usuario"
                + " join reporte on"
                + " reporte.fs_reporte_usuaaprob= firma_usuario.fs_firmusua_usuario"
                + " where reporte.pi_reporte_id=?) as firma"
                + " from reporte"
                + " where pi_reporte_id=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            q.setParameter(2, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarFirmaAprobacionOrdenCompra(int ordenCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT orden_compra.pi_ordecomp_numero, by_firmusua_firma FROM firma_usuario "
                + "JOIN orden_compra "
                + "ON firma_usuario.fs_firmusua_usuario=orden_compra.fs_ordecomp_usuaapru "
                + "WHERE orden_compra.pi_ordecomp_numero=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, ordenCompra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
        public List<Object> encontrarFirmaRevisionOrdenCompra(int ordenCompra) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT orden_compra.pi_ordecomp_numero, by_firmusua_firma FROM firma_usuario "
                + "JOIN orden_compra "
                + "ON firma_usuario.fs_firmusua_usuario=orden_compra.fs_ordecomp_usuarevi "
                + "WHERE orden_compra.pi_ordecomp_numero=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, ordenCompra);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
