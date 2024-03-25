/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Password;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
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
public class PasswordJpaController implements Serializable {

    public PasswordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

  /*  public void create(Password password) {
        if (password.getSUsuarioList() == null) {
            password.setSUsuarioList(new ArrayList<SUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SUsuario> attachedSUsuarioList = new ArrayList<SUsuario>();
            for (SUsuario SUsuarioListSUsuarioToAttach : password.getSUsuarioList()) {
                SUsuarioListSUsuarioToAttach = em.getReference(SUsuarioListSUsuarioToAttach.getClass(), SUsuarioListSUsuarioToAttach.getPsUsuarioCodigo());
                attachedSUsuarioList.add(SUsuarioListSUsuarioToAttach);
            }
            password.setSUsuarioList(attachedSUsuarioList);
            em.persist(password);
            for (SUsuario SUsuarioListSUsuario : password.getSUsuarioList()) {
                Password oldPasswordIdOfSUsuarioListSUsuario = SUsuarioListSUsuario.getPasswordId();
                SUsuarioListSUsuario.setPasswordId(password);
                SUsuarioListSUsuario = em.merge(SUsuarioListSUsuario);
                if (oldPasswordIdOfSUsuarioListSUsuario != null) {
                    oldPasswordIdOfSUsuarioListSUsuario.getSUsuarioList().remove(SUsuarioListSUsuario);
                    oldPasswordIdOfSUsuarioListSUsuario = em.merge(oldPasswordIdOfSUsuarioListSUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Password password) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Password persistentPassword = em.find(Password.class, password.getPasswordId());
            List<SUsuario> SUsuarioListOld = persistentPassword.getSUsuarioList();
            List<SUsuario> SUsuarioListNew = password.getSUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (SUsuario SUsuarioListOldSUsuario : SUsuarioListOld) {
                if (!SUsuarioListNew.contains(SUsuarioListOldSUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SUsuario " + SUsuarioListOldSUsuario + " since its passwordId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SUsuario> attachedSUsuarioListNew = new ArrayList<SUsuario>();
            for (SUsuario SUsuarioListNewSUsuarioToAttach : SUsuarioListNew) {
                SUsuarioListNewSUsuarioToAttach = em.getReference(SUsuarioListNewSUsuarioToAttach.getClass(), SUsuarioListNewSUsuarioToAttach.getPsUsuarioCodigo());
                attachedSUsuarioListNew.add(SUsuarioListNewSUsuarioToAttach);
            }
            SUsuarioListNew = attachedSUsuarioListNew;
            password.setSUsuarioList(SUsuarioListNew);
            password = em.merge(password);
            for (SUsuario SUsuarioListNewSUsuario : SUsuarioListNew) {
                if (!SUsuarioListOld.contains(SUsuarioListNewSUsuario)) {
                    Password oldPasswordIdOfSUsuarioListNewSUsuario = SUsuarioListNewSUsuario.getPasswordId();
                    SUsuarioListNewSUsuario.setPasswordId(password);
                    SUsuarioListNewSUsuario = em.merge(SUsuarioListNewSUsuario);
                    if (oldPasswordIdOfSUsuarioListNewSUsuario != null && !oldPasswordIdOfSUsuarioListNewSUsuario.equals(password)) {
                        oldPasswordIdOfSUsuarioListNewSUsuario.getSUsuarioList().remove(SUsuarioListNewSUsuario);
                        oldPasswordIdOfSUsuarioListNewSUsuario = em.merge(oldPasswordIdOfSUsuarioListNewSUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = password.getPasswordId();
                if (findPassword(id) == null) {
                    throw new NonexistentEntityException("The password with id " + id + " no longer exists.");
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
            Password password;
            try {
                password = em.getReference(Password.class, id);
                password.getPasswordId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The password with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SUsuario> SUsuarioListOrphanCheck = password.getSUsuarioList();
            for (SUsuario SUsuarioListOrphanCheckSUsuario : SUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Password (" + password + ") cannot be destroyed since the SUsuario " + SUsuarioListOrphanCheckSUsuario + " in its SUsuarioList field has a non-nullable passwordId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(password);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Password> findPasswordEntities() {
        return findPasswordEntities(true, -1, -1);
    }

    public List<Password> findPasswordEntities(int maxResults, int firstResult) {
        return findPasswordEntities(false, maxResults, firstResult);
    }

    private List<Password> findPasswordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Password.class));
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

    public Password findPassword(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Password.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasswordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Password> rt = cq.from(Password.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
