/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.TipoValor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Valor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author TOSHIBA
 */
public class TipoValorJpaController implements Serializable {

    public TipoValorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoValor tipoValor) {
        if (tipoValor.getValorList() == null) {
            tipoValor.setValorList(new ArrayList<Valor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Valor> attachedValorList = new ArrayList<Valor>();
           /* for (Valor valorListValorToAttach : tipoValor.getValorList()) {
                valorListValorToAttach = em.getReference(valorListValorToAttach.getClass(), valorListValorToAttach.getIdValor());
                attachedValorList.add(valorListValorToAttach);
            }*/
            tipoValor.setValorList(attachedValorList);
            em.persist(tipoValor);
            /*for (Valor valorListValor : tipoValor.getValorList()) {
                TipoValor oldIdTpValorOfValorListValor = valorListValor.getIdTpValor();
                valorListValor.setIdTpValor(tipoValor);
                valorListValor = em.merge(valorListValor);
                if (oldIdTpValorOfValorListValor != null) {
                    oldIdTpValorOfValorListValor.getValorList().remove(valorListValor);
                    oldIdTpValorOfValorListValor = em.merge(oldIdTpValorOfValorListValor);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoValor tipoValor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoValor persistentTipoValor = em.find(TipoValor.class, tipoValor.getIdtipoValor());
            List<Valor> valorListOld = persistentTipoValor.getValorList();
            List<Valor> valorListNew = tipoValor.getValorList();
            List<String> illegalOrphanMessages = null;
            for (Valor valorListOldValor : valorListOld) {
                if (!valorListNew.contains(valorListOldValor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Valor " + valorListOldValor + " since its idTpValor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Valor> attachedValorListNew = new ArrayList<Valor>();
           /* for (Valor valorListNewValorToAttach : valorListNew) {
                valorListNewValorToAttach = em.getReference(valorListNewValorToAttach.getClass(), valorListNewValorToAttach.getIdValor());
                attachedValorListNew.add(valorListNewValorToAttach);
            }*/
            valorListNew = attachedValorListNew;
            tipoValor.setValorList(valorListNew);
            tipoValor = em.merge(tipoValor);
         /*   for (Valor valorListNewValor : valorListNew) {
                if (!valorListOld.contains(valorListNewValor)) {
                    TipoValor oldIdTpValorOfValorListNewValor = valorListNewValor.getIdTpValor();
                    valorListNewValor.setIdTpValor(tipoValor);
                    valorListNewValor = em.merge(valorListNewValor);
                    if (oldIdTpValorOfValorListNewValor != null && !oldIdTpValorOfValorListNewValor.equals(tipoValor)) {
                        oldIdTpValorOfValorListNewValor.getValorList().remove(valorListNewValor);
                        oldIdTpValorOfValorListNewValor = em.merge(oldIdTpValorOfValorListNewValor);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoValor.getIdtipoValor();
                if (findTipoValor(id) == null) {
                    throw new NonexistentEntityException("The tipoValor with id " + id + " no longer exists.");
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
            TipoValor tipoValor;
            try {
                tipoValor = em.getReference(TipoValor.class, id);
                tipoValor.getIdtipoValor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoValor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Valor> valorListOrphanCheck = tipoValor.getValorList();
            for (Valor valorListOrphanCheckValor : valorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoValor (" + tipoValor + ") cannot be destroyed since the Valor " + valorListOrphanCheckValor + " in its valorList field has a non-nullable idTpValor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoValor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoValor> findTipoValorEntities() {
        return findTipoValorEntities(true, -1, -1);
    }

    public List<TipoValor> findTipoValorEntities(int maxResults, int firstResult) {
        return findTipoValorEntities(false, maxResults, firstResult);
    }

    private List<TipoValor> findTipoValorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoValor.class));
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

    public TipoValor findTipoValor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoValor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoValorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoValor> rt = cq.from(TipoValor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<TipoValor> encontrarTipoValorPorNombre(String nombreTipoValor){
          EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_valor"
                + " where s_tipoval_nombre=?";
        try {
            Query q = em.createNativeQuery(consulta, TipoValor.class);
            q.setParameter(1, nombreTipoValor);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<TipoValor> encontrarValoresParametrosVariables(){
          EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM tipo_valor"
                + " where pi_tipoval_id=11"
                + " or pi_tipoval_id=12";
        try {
            Query q = em.createNativeQuery(consulta, TipoValor.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
