/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import jpacontrollers.exceptions.NonexistentEntityException;
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

/**
 *
 * @author HelpDesk
 */
public class PasswordsJpaController implements Serializable {

    public PasswordsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Password passwords) {
/*        if (passwords.getSUsuarioList() == null) {
            passwords.setSUsuarioList(new ArrayList<SUsuario>());
        }*/
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SUsuario> attachedSUsuarioList = new ArrayList<SUsuario>();
           /* for (SUsuario SUsuarioListSUsuarioToAttach : passwords.getSUsuarioList()) {
                SUsuarioListSUsuarioToAttach = em.getReference(SUsuarioListSUsuarioToAttach.getClass(), SUsuarioListSUsuarioToAttach.getPsUsuarioCodigo());
                attachedSUsuarioList.add(SUsuarioListSUsuarioToAttach);
            }*/
            //passwords.setSUsuarioList(attachedSUsuarioList);
            em.persist(passwords);
          /*  for (SUsuario SUsuarioListSUsuario : passwords.getSUsuarioList()) {
                Passwords oldFkPasswordIdOfSUsuarioListSUsuario = SUsuarioListSUsuario.getFkPasswordId();
                SUsuarioListSUsuario.setFkPasswordId(passwords);
                SUsuarioListSUsuario = em.merge(SUsuarioListSUsuario);
                if (oldFkPasswordIdOfSUsuarioListSUsuario != null) {
                    oldFkPasswordIdOfSUsuarioListSUsuario.getSUsuarioList().remove(SUsuarioListSUsuario);
                    oldFkPasswordIdOfSUsuarioListSUsuario = em.merge(oldFkPasswordIdOfSUsuarioListSUsuario);
                }
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Password passwords) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Password persistentPasswords = em.find(Password.class, passwords.getPasswordId());
           // List<SUsuario> SUsuarioListOld = persistentPasswords.getSUsuarioList();
           // List<SUsuario> SUsuarioListNew = passwords.getSUsuarioList();
            List<SUsuario> attachedSUsuarioListNew = new ArrayList<SUsuario>();
            /*for (SUsuario SUsuarioListNewSUsuarioToAttach : SUsuarioListNew) {
                SUsuarioListNewSUsuarioToAttach = em.getReference(SUsuarioListNewSUsuarioToAttach.getClass(), SUsuarioListNewSUsuarioToAttach.getPsUsuarioCodigo());
                attachedSUsuarioListNew.add(SUsuarioListNewSUsuarioToAttach);
            }*/
            /*SUsuarioListNew = attachedSUsuarioListNew;
            passwords.setSUsuarioList(SUsuarioListNew);*/
            passwords = em.merge(passwords);
           /* for (SUsuario SUsuarioListOldSUsuario : SUsuarioListOld) {
                if (!SUsuarioListNew.contains(SUsuarioListOldSUsuario)) {
                    SUsuarioListOldSUsuario.setFkPasswordId(null);
                    SUsuarioListOldSUsuario = em.merge(SUsuarioListOldSUsuario);
                }
            }
            for (SUsuario SUsuarioListNewSUsuario : SUsuarioListNew) {
                if (!SUsuarioListOld.contains(SUsuarioListNewSUsuario)) {
                    Passwords oldFkPasswordIdOfSUsuarioListNewSUsuario = SUsuarioListNewSUsuario.getFkPasswordId();
                    SUsuarioListNewSUsuario.setFkPasswordId(passwords);
                    SUsuarioListNewSUsuario = em.merge(SUsuarioListNewSUsuario);
                    if (oldFkPasswordIdOfSUsuarioListNewSUsuario != null && !oldFkPasswordIdOfSUsuarioListNewSUsuario.equals(passwords)) {
                        oldFkPasswordIdOfSUsuarioListNewSUsuario.getSUsuarioList().remove(SUsuarioListNewSUsuario);
                        oldFkPasswordIdOfSUsuarioListNewSUsuario = em.merge(oldFkPasswordIdOfSUsuarioListNewSUsuario);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = passwords.getPasswordId();
                if (findPasswords(id) == null) {
                    throw new NonexistentEntityException("The passwords with id " + id + " no longer exists.");
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
            Password passwords;
            try {
                passwords = em.getReference(Password.class, id);
                passwords.getPasswordId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The passwords with id " + id + " no longer exists.", enfe);
            }
           // List<SUsuario> SUsuarioList = passwords.getSUsuarioList();
            /*for (SUsuario SUsuarioListSUsuario : SUsuarioList) {
                SUsuarioListSUsuario.setFkPasswordId(null);
                SUsuarioListSUsuario = em.merge(SUsuarioListSUsuario);
            }*/
            em.remove(passwords);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Password> findPasswordsEntities() {
        return findPasswordsEntities(true, -1, -1);
    }

    public List<Password> findPasswordsEntities(int maxResults, int firstResult) {
        return findPasswordsEntities(false, maxResults, firstResult);
    }

    private List<Password> findPasswordsEntities(boolean all, int maxResults, int firstResult) {
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

    public Password findPasswords(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Password.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasswordsCount() {
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
    
    public List<Password> findPasswordsByType(String type) {
        EntityManager em = getEntityManager();
        List results = em.createNamedQuery("Passwords.findByType")
                .setParameter("type", type)
                .getResultList();

        return results;
    }

    public List<String> findTypeFromPasswords() {

        EntityManager em = getEntityManager();

        List results = em.createNamedQuery("Passwords.findTypeDistinct")
                .getResultList();

        return results;
    }

    public String findPasswordsByTypeAndUser(String type, String user) {
        String password = null;
        
        EntityManager em = getEntityManager();
        List results = em.createNamedQuery("Passwords.findPasswordByTypeAndUser")
                .setParameter("type", type)
                .setParameter("user", user)
                .getResultList();

        for (int i = 0; i < results.size(); i++) {

            password = results.get(i).toString();
        }

        return password;
    }
    
}
