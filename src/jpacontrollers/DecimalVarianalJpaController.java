/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DecimalVarianal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.MatrizAnalisis;
import entidades.VariableAnalisis;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author SISTEMAS
 */
public class DecimalVarianalJpaController implements Serializable {

    public DecimalVarianalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   /* public void create(DecimalVarianal decimalVarianal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MatrizAnalisis fiDecimalVarianalMatriz = decimalVarianal.getFiDecimalVarianalMatriz();
            if (fiDecimalVarianalMatriz != null) {
                fiDecimalVarianalMatriz = em.getReference(fiDecimalVarianalMatriz.getClass(), fiDecimalVarianalMatriz.getPiMatranalId());
                decimalVarianal.setFiDecimalVarianalMatriz(fiDecimalVarianalMatriz);
            }
            VariableAnalisis fiDecimalVarianalVarianal = decimalVarianal.getFiDecimalVarianalVarianal();
            if (fiDecimalVarianalVarianal != null) {
                fiDecimalVarianalVarianal = em.getReference(fiDecimalVarianalVarianal.getClass(), fiDecimalVarianalVarianal.getPiVarianalId());
                decimalVarianal.setFiDecimalVarianalVarianal(fiDecimalVarianalVarianal);
            }
            SUsuario fsDecimalVarianalUsuacreac = decimalVarianal.getFsDecimalVarianalUsuacreac();
            if (fsDecimalVarianalUsuacreac != null) {
                fsDecimalVarianalUsuacreac = em.getReference(fsDecimalVarianalUsuacreac.getClass(), fsDecimalVarianalUsuacreac.getPsUsuarioCodigo());
                decimalVarianal.setFsDecimalVarianalUsuacreac(fsDecimalVarianalUsuacreac);
            }
            SUsuario fsDecimalVarianalUsuamodi = decimalVarianal.getFsDecimalVarianalUsuamodi();
            if (fsDecimalVarianalUsuamodi != null) {
                fsDecimalVarianalUsuamodi = em.getReference(fsDecimalVarianalUsuamodi.getClass(), fsDecimalVarianalUsuamodi.getPsUsuarioCodigo());
                decimalVarianal.setFsDecimalVarianalUsuamodi(fsDecimalVarianalUsuamodi);
            }
            em.persist(decimalVarianal);
            if (fiDecimalVarianalMatriz != null) {
                fiDecimalVarianalMatriz.getDecimalVarianalList().add(decimalVarianal);
                fiDecimalVarianalMatriz = em.merge(fiDecimalVarianalMatriz);
            }
            if (fiDecimalVarianalVarianal != null) {
                fiDecimalVarianalVarianal.getDecimalVarianalList().add(decimalVarianal);
                fiDecimalVarianalVarianal = em.merge(fiDecimalVarianalVarianal);
            }
            if (fsDecimalVarianalUsuacreac != null) {
                fsDecimalVarianalUsuacreac.getDecimalVarianalList().add(decimalVarianal);
                fsDecimalVarianalUsuacreac = em.merge(fsDecimalVarianalUsuacreac);
            }
            if (fsDecimalVarianalUsuamodi != null) {
                fsDecimalVarianalUsuamodi.getDecimalVarianalList().add(decimalVarianal);
                fsDecimalVarianalUsuamodi = em.merge(fsDecimalVarianalUsuamodi);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DecimalVarianal decimalVarianal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DecimalVarianal persistentDecimalVarianal = em.find(DecimalVarianal.class, decimalVarianal.getPiDecimalVarianalId());
            MatrizAnalisis fiDecimalVarianalMatrizOld = persistentDecimalVarianal.getFiDecimalVarianalMatriz();
            MatrizAnalisis fiDecimalVarianalMatrizNew = decimalVarianal.getFiDecimalVarianalMatriz();
            VariableAnalisis fiDecimalVarianalVarianalOld = persistentDecimalVarianal.getFiDecimalVarianalVarianal();
            VariableAnalisis fiDecimalVarianalVarianalNew = decimalVarianal.getFiDecimalVarianalVarianal();
            SUsuario fsDecimalVarianalUsuacreacOld = persistentDecimalVarianal.getFsDecimalVarianalUsuacreac();
            SUsuario fsDecimalVarianalUsuacreacNew = decimalVarianal.getFsDecimalVarianalUsuacreac();
            SUsuario fsDecimalVarianalUsuamodiOld = persistentDecimalVarianal.getFsDecimalVarianalUsuamodi();
            SUsuario fsDecimalVarianalUsuamodiNew = decimalVarianal.getFsDecimalVarianalUsuamodi();
            if (fiDecimalVarianalMatrizNew != null) {
                fiDecimalVarianalMatrizNew = em.getReference(fiDecimalVarianalMatrizNew.getClass(), fiDecimalVarianalMatrizNew.getPiMatranalId());
                decimalVarianal.setFiDecimalVarianalMatriz(fiDecimalVarianalMatrizNew);
            }
            if (fiDecimalVarianalVarianalNew != null) {
                fiDecimalVarianalVarianalNew = em.getReference(fiDecimalVarianalVarianalNew.getClass(), fiDecimalVarianalVarianalNew.getPiVarianalId());
                decimalVarianal.setFiDecimalVarianalVarianal(fiDecimalVarianalVarianalNew);
            }
            if (fsDecimalVarianalUsuacreacNew != null) {
                fsDecimalVarianalUsuacreacNew = em.getReference(fsDecimalVarianalUsuacreacNew.getClass(), fsDecimalVarianalUsuacreacNew.getPsUsuarioCodigo());
                decimalVarianal.setFsDecimalVarianalUsuacreac(fsDecimalVarianalUsuacreacNew);
            }
            if (fsDecimalVarianalUsuamodiNew != null) {
                fsDecimalVarianalUsuamodiNew = em.getReference(fsDecimalVarianalUsuamodiNew.getClass(), fsDecimalVarianalUsuamodiNew.getPsUsuarioCodigo());
                decimalVarianal.setFsDecimalVarianalUsuamodi(fsDecimalVarianalUsuamodiNew);
            }
            decimalVarianal = em.merge(decimalVarianal);
            if (fiDecimalVarianalMatrizOld != null && !fiDecimalVarianalMatrizOld.equals(fiDecimalVarianalMatrizNew)) {
                fiDecimalVarianalMatrizOld.getDecimalVarianalList().remove(decimalVarianal);
                fiDecimalVarianalMatrizOld = em.merge(fiDecimalVarianalMatrizOld);
            }
            if (fiDecimalVarianalMatrizNew != null && !fiDecimalVarianalMatrizNew.equals(fiDecimalVarianalMatrizOld)) {
                fiDecimalVarianalMatrizNew.getDecimalVarianalList().add(decimalVarianal);
                fiDecimalVarianalMatrizNew = em.merge(fiDecimalVarianalMatrizNew);
            }
            if (fiDecimalVarianalVarianalOld != null && !fiDecimalVarianalVarianalOld.equals(fiDecimalVarianalVarianalNew)) {
                fiDecimalVarianalVarianalOld.getDecimalVarianalList().remove(decimalVarianal);
                fiDecimalVarianalVarianalOld = em.merge(fiDecimalVarianalVarianalOld);
            }
            if (fiDecimalVarianalVarianalNew != null && !fiDecimalVarianalVarianalNew.equals(fiDecimalVarianalVarianalOld)) {
                fiDecimalVarianalVarianalNew.getDecimalVarianalList().add(decimalVarianal);
                fiDecimalVarianalVarianalNew = em.merge(fiDecimalVarianalVarianalNew);
            }
            if (fsDecimalVarianalUsuacreacOld != null && !fsDecimalVarianalUsuacreacOld.equals(fsDecimalVarianalUsuacreacNew)) {
                fsDecimalVarianalUsuacreacOld.getDecimalVarianalList().remove(decimalVarianal);
                fsDecimalVarianalUsuacreacOld = em.merge(fsDecimalVarianalUsuacreacOld);
            }
            if (fsDecimalVarianalUsuacreacNew != null && !fsDecimalVarianalUsuacreacNew.equals(fsDecimalVarianalUsuacreacOld)) {
                fsDecimalVarianalUsuacreacNew.getDecimalVarianalList().add(decimalVarianal);
                fsDecimalVarianalUsuacreacNew = em.merge(fsDecimalVarianalUsuacreacNew);
            }
            if (fsDecimalVarianalUsuamodiOld != null && !fsDecimalVarianalUsuamodiOld.equals(fsDecimalVarianalUsuamodiNew)) {
                fsDecimalVarianalUsuamodiOld.getDecimalVarianalList().remove(decimalVarianal);
                fsDecimalVarianalUsuamodiOld = em.merge(fsDecimalVarianalUsuamodiOld);
            }
            if (fsDecimalVarianalUsuamodiNew != null && !fsDecimalVarianalUsuamodiNew.equals(fsDecimalVarianalUsuamodiOld)) {
                fsDecimalVarianalUsuamodiNew.getDecimalVarianalList().add(decimalVarianal);
                fsDecimalVarianalUsuamodiNew = em.merge(fsDecimalVarianalUsuamodiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = decimalVarianal.getPiDecimalVarianalId();
                if (findDecimalVarianal(id) == null) {
                    throw new NonexistentEntityException("The decimalVarianal with id " + id + " no longer exists.");
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
            DecimalVarianal decimalVarianal;
            try {
                decimalVarianal = em.getReference(DecimalVarianal.class, id);
                decimalVarianal.getPiDecimalVarianalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The decimalVarianal with id " + id + " no longer exists.", enfe);
            }
            MatrizAnalisis fiDecimalVarianalMatriz = decimalVarianal.getFiDecimalVarianalMatriz();
            if (fiDecimalVarianalMatriz != null) {
                fiDecimalVarianalMatriz.getDecimalVarianalList().remove(decimalVarianal);
                fiDecimalVarianalMatriz = em.merge(fiDecimalVarianalMatriz);
            }
            VariableAnalisis fiDecimalVarianalVarianal = decimalVarianal.getFiDecimalVarianalVarianal();
            if (fiDecimalVarianalVarianal != null) {
                fiDecimalVarianalVarianal.getDecimalVarianalList().remove(decimalVarianal);
                fiDecimalVarianalVarianal = em.merge(fiDecimalVarianalVarianal);
            }
            SUsuario fsDecimalVarianalUsuacreac = decimalVarianal.getFsDecimalVarianalUsuacreac();
            if (fsDecimalVarianalUsuacreac != null) {
                fsDecimalVarianalUsuacreac.getDecimalVarianalList().remove(decimalVarianal);
                fsDecimalVarianalUsuacreac = em.merge(fsDecimalVarianalUsuacreac);
            }
            SUsuario fsDecimalVarianalUsuamodi = decimalVarianal.getFsDecimalVarianalUsuamodi();
            if (fsDecimalVarianalUsuamodi != null) {
                fsDecimalVarianalUsuamodi.getDecimalVarianalList().remove(decimalVarianal);
                fsDecimalVarianalUsuamodi = em.merge(fsDecimalVarianalUsuamodi);
            }
            em.remove(decimalVarianal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<DecimalVarianal> findDecimalVarianalEntities() {
        return findDecimalVarianalEntities(true, -1, -1);
    }

    public List<DecimalVarianal> findDecimalVarianalEntities(int maxResults, int firstResult) {
        return findDecimalVarianalEntities(false, maxResults, firstResult);
    }

    private List<DecimalVarianal> findDecimalVarianalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DecimalVarianal.class));
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

    public DecimalVarianal findDecimalVarianal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DecimalVarianal.class, id);
        } finally {
            em.close();
        }
    }

    public int getDecimalVarianalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DecimalVarianal> rt = cq.from(DecimalVarianal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int encontrarDecimales(int variableAnalisis) {
     EntityManager em = getEntityManager();
        System.out.println("variable !!!" + variableAnalisis);
        String consulta = "select i_decimal_varianal_decimal " +
            "from decimal_varianal where fi_decimal_varianal_varianal=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, variableAnalisis);
            System.out.println("tamaño " + q.getResultList().size());
            return  (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    
}
