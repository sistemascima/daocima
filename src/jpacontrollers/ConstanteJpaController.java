/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Constante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.VariableCalculo;
import entidades.SUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class ConstanteJpaController implements Serializable {

    public ConstanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Constante constante) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableCalculo fiConstanteVariableCalculo = constante.getFiConstanteVariableCalculo();
            if (fiConstanteVariableCalculo != null) {
                fiConstanteVariableCalculo = em.getReference(fiConstanteVariableCalculo.getClass(), fiConstanteVariableCalculo.getPiVariableCalculo());
                constante.setFiConstanteVariableCalculo(fiConstanteVariableCalculo);
            }
            SUsuario fkConstanteUsuarioCreador = constante.getFkConstanteUsuarioCreador();
            if (fkConstanteUsuarioCreador != null) {
                fkConstanteUsuarioCreador = em.getReference(fkConstanteUsuarioCreador.getClass(), fkConstanteUsuarioCreador.getPsUsuarioCodigo());
                constante.setFkConstanteUsuarioCreador(fkConstanteUsuarioCreador);
            }
            SUsuario fkConstanteUsuarioModificador = constante.getFkConstanteUsuarioModificador();
            if (fkConstanteUsuarioModificador != null) {
                fkConstanteUsuarioModificador = em.getReference(fkConstanteUsuarioModificador.getClass(), fkConstanteUsuarioModificador.getPsUsuarioCodigo());
                constante.setFkConstanteUsuarioModificador(fkConstanteUsuarioModificador);
            }
            em.persist(constante);
           /* if (fiConstanteVariableCalculo != null) {
                fiConstanteVariableCalculo.getConstanteList().add(constante);
                fiConstanteVariableCalculo = em.merge(fiConstanteVariableCalculo);
            }
            if (fkConstanteUsuarioCreador != null) {
                fkConstanteUsuarioCreador.getConstanteList().add(constante);
                fkConstanteUsuarioCreador = em.merge(fkConstanteUsuarioCreador);
            }
            if (fkConstanteUsuarioModificador != null) {
                fkConstanteUsuarioModificador.getConstanteList().add(constante);
                fkConstanteUsuarioModificador = em.merge(fkConstanteUsuarioModificador);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConstante(constante.getPiConstanteId()) != null) {
                throw new PreexistingEntityException("Constante " + constante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Constante constante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Constante persistentConstante = em.find(Constante.class, constante.getPiConstanteId());
            VariableCalculo fiConstanteVariableCalculoOld = persistentConstante.getFiConstanteVariableCalculo();
            VariableCalculo fiConstanteVariableCalculoNew = constante.getFiConstanteVariableCalculo();
            SUsuario fkConstanteUsuarioCreadorOld = persistentConstante.getFkConstanteUsuarioCreador();
            SUsuario fkConstanteUsuarioCreadorNew = constante.getFkConstanteUsuarioCreador();
            SUsuario fkConstanteUsuarioModificadorOld = persistentConstante.getFkConstanteUsuarioModificador();
            SUsuario fkConstanteUsuarioModificadorNew = constante.getFkConstanteUsuarioModificador();
            if (fiConstanteVariableCalculoNew != null) {
                fiConstanteVariableCalculoNew = em.getReference(fiConstanteVariableCalculoNew.getClass(), fiConstanteVariableCalculoNew.getPiVariableCalculo());
                constante.setFiConstanteVariableCalculo(fiConstanteVariableCalculoNew);
            }
            if (fkConstanteUsuarioCreadorNew != null) {
                fkConstanteUsuarioCreadorNew = em.getReference(fkConstanteUsuarioCreadorNew.getClass(), fkConstanteUsuarioCreadorNew.getPsUsuarioCodigo());
                constante.setFkConstanteUsuarioCreador(fkConstanteUsuarioCreadorNew);
            }
            if (fkConstanteUsuarioModificadorNew != null) {
                fkConstanteUsuarioModificadorNew = em.getReference(fkConstanteUsuarioModificadorNew.getClass(), fkConstanteUsuarioModificadorNew.getPsUsuarioCodigo());
                constante.setFkConstanteUsuarioModificador(fkConstanteUsuarioModificadorNew);
            }
            constante = em.merge(constante);
         /*   if (fiConstanteVariableCalculoOld != null && !fiConstanteVariableCalculoOld.equals(fiConstanteVariableCalculoNew)) {
                fiConstanteVariableCalculoOld.getConstanteList().remove(constante);
                fiConstanteVariableCalculoOld = em.merge(fiConstanteVariableCalculoOld);
            }
            if (fiConstanteVariableCalculoNew != null && !fiConstanteVariableCalculoNew.equals(fiConstanteVariableCalculoOld)) {
                fiConstanteVariableCalculoNew.getConstanteList().add(constante);
                fiConstanteVariableCalculoNew = em.merge(fiConstanteVariableCalculoNew);
            }
            if (fkConstanteUsuarioCreadorOld != null && !fkConstanteUsuarioCreadorOld.equals(fkConstanteUsuarioCreadorNew)) {
                fkConstanteUsuarioCreadorOld.getConstanteList().remove(constante);
                fkConstanteUsuarioCreadorOld = em.merge(fkConstanteUsuarioCreadorOld);
            }
            if (fkConstanteUsuarioCreadorNew != null && !fkConstanteUsuarioCreadorNew.equals(fkConstanteUsuarioCreadorOld)) {
                fkConstanteUsuarioCreadorNew.getConstanteList().add(constante);
                fkConstanteUsuarioCreadorNew = em.merge(fkConstanteUsuarioCreadorNew);
            }
            if (fkConstanteUsuarioModificadorOld != null && !fkConstanteUsuarioModificadorOld.equals(fkConstanteUsuarioModificadorNew)) {
                fkConstanteUsuarioModificadorOld.getConstanteList().remove(constante);
                fkConstanteUsuarioModificadorOld = em.merge(fkConstanteUsuarioModificadorOld);
            }
            if (fkConstanteUsuarioModificadorNew != null && !fkConstanteUsuarioModificadorNew.equals(fkConstanteUsuarioModificadorOld)) {
                fkConstanteUsuarioModificadorNew.getConstanteList().add(constante);
                fkConstanteUsuarioModificadorNew = em.merge(fkConstanteUsuarioModificadorNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = constante.getPiConstanteId();
                if (findConstante(id) == null) {
                    throw new NonexistentEntityException("The constante with id " + id + " no longer exists.");
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
            Constante constante;
            try {
                constante = em.getReference(Constante.class, id);
                constante.getPiConstanteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The constante with id " + id + " no longer exists.", enfe);
            }
            VariableCalculo fiConstanteVariableCalculo = constante.getFiConstanteVariableCalculo();
            /*if (fiConstanteVariableCalculo != null) {
                fiConstanteVariableCalculo.getConstanteList().remove(constante);
                fiConstanteVariableCalculo = em.merge(fiConstanteVariableCalculo);
            }
            SUsuario fkConstanteUsuarioCreador = constante.getFkConstanteUsuarioCreador();
           /* if (fkConstanteUsuarioCreador != null) {
                fkConstanteUsuarioCreador.getConstanteList().remove(constante);
                fkConstanteUsuarioCreador = em.merge(fkConstanteUsuarioCreador);
            }
            SUsuario fkConstanteUsuarioModificador = constante.getFkConstanteUsuarioModificador();
            if (fkConstanteUsuarioModificador != null) {
                fkConstanteUsuarioModificador.getConstanteList().remove(constante);
                fkConstanteUsuarioModificador = em.merge(fkConstanteUsuarioModificador);
            }*/
            em.remove(constante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Constante> findConstanteEntities() {
        return findConstanteEntities(true, -1, -1);
    }

    public List<Constante> findConstanteEntities(int maxResults, int firstResult) {
        return findConstanteEntities(false, maxResults, firstResult);
    }

    private List<Constante> findConstanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Constante.class));
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

    public Constante findConstante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Constante.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Constante> rt = cq.from(Constante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearConstante(Constante constante) {
         EntityManager em = getEntityManager();
        try {     
               String insert = "INSERT INTO constante (fi_constante_variable_calculo, "
                     + "s_constante_valor, "
                     + "fk_constante_usuario_creador, d_constante_fechcreacion) "
                     + "VALUES (?,?,?,now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, constante.getFiConstanteVariableCalculo().getPiVariableCalculo());
             insercion.setParameter(2, constante.getSConstanteValor());
             insercion.setParameter(3, constante.getFkConstanteUsuarioCreador().getPsUsuarioCodigo());
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public List<Object> encontrarValorConstante(int idConstante) {
        EntityManager em = getEntityManager();
        String consulta = "select s_constante_valor " +
                "from constante " +
                "where fi_constante_variable_calculo = ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idConstante);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarNombreConstante(int idVariableCalculo) {
         EntityManager em = getEntityManager();
        String consulta = "select variable_calculo.s_variable_calculo_nombre"
                + " from constante"
                + " join variable_calculo on variable_calculo.pi_variable_calculo= constante.fi_constante_variable_calculo"
                + " where variable_calculo.pi_variable_calculo=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableCalculo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void reemplazarConstante(int idConstante, String valorConstante, String psUsuarioCodigo) {
     EntityManager em = getEntityManager();
        System.out.println("id constante "+idConstante );
        System.out.println("valorConstante "+valorConstante );
             String insert = " update constante set s_constante_valor = ?, "
                     + "  fk_constante_usuario_modificador = ?, "
                     + "  d_constante_fechmodificacion = now() "
                     + " where pi_constante_id = ? ";                 
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, valorConstante);
        insercion.setParameter(2, psUsuarioCodigo);
        insercion.setParameter(3, idConstante);
        insercion.executeUpdate();
        em.getTransaction().commit();
    }
    
}
