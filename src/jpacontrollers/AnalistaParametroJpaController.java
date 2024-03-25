/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.AnalistaParametro;
import entidades.MatrizAnalisis;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.SUsuario;
import entidades.VariableAnalisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author TOSHIBA
 */
public class AnalistaParametroJpaController implements Serializable {

    public AnalistaParametroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*public void create(AnalistaParametro analistaParametro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuario usuarioTitular = analistaParametro.getUsuarioTitular();
            if (usuarioTitular != null) {
                usuarioTitular = em.getReference(usuarioTitular.getClass(), usuarioTitular.getPsUsuarioCodigo());
                analistaParametro.setUsuarioTitular(usuarioTitular);
            }
            VariableAnalisis idParametro = analistaParametro.getIdParametro();
            if (idParametro != null) {
                idParametro = em.getReference(idParametro.getClass(), idParametro.getPiVarianalId());
                analistaParametro.setIdParametro(idParametro);
            }
            SUsuario usuarioSuplente = analistaParametro.getUsuarioSuplente();
            if (usuarioSuplente != null) {
                usuarioSuplente = em.getReference(usuarioSuplente.getClass(), usuarioSuplente.getPsUsuarioCodigo());
                analistaParametro.setUsuarioSuplente(usuarioSuplente);
            }
            em.persist(analistaParametro);
            if (usuarioTitular != null) {
                usuarioTitular.getAnalistaParametroList().add(analistaParametro);
                usuarioTitular = em.merge(usuarioTitular);
            }
            if (idParametro != null) {
                idParametro.getAnalistaParametroList().add(analistaParametro);
                idParametro = em.merge(idParametro);
            }
            if (usuarioSuplente != null) {
                usuarioSuplente.getAnalistaParametroList().add(analistaParametro);
                usuarioSuplente = em.merge(usuarioSuplente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnalistaParametro(analistaParametro.getIdanalistaParametro()) != null) {
                throw new PreexistingEntityException("AnalistaParametro " + analistaParametro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    /*public void edit(AnalistaParametro analistaParametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalistaParametro persistentAnalistaParametro = em.find(AnalistaParametro.class, analistaParametro.getIdanalistaParametro());
            SUsuario usuarioTitularOld = persistentAnalistaParametro.getUsuarioTitular();
            SUsuario usuarioTitularNew = analistaParametro.getUsuarioTitular();
            VariableAnalisis idParametroOld = persistentAnalistaParametro.getIdParametro();
            VariableAnalisis idParametroNew = analistaParametro.getIdParametro();
            SUsuario usuarioSuplenteOld = persistentAnalistaParametro.getUsuarioSuplente();
            SUsuario usuarioSuplenteNew = analistaParametro.getUsuarioSuplente();
            if (usuarioTitularNew != null) {
                usuarioTitularNew = em.getReference(usuarioTitularNew.getClass(), usuarioTitularNew.getPsUsuarioCodigo());
                analistaParametro.setUsuarioTitular(usuarioTitularNew);
            }
            if (idParametroNew != null) {
                idParametroNew = em.getReference(idParametroNew.getClass(), idParametroNew.getPiVarianalId());
                analistaParametro.setIdParametro(idParametroNew);
            }
            if (usuarioSuplenteNew != null) {
                usuarioSuplenteNew = em.getReference(usuarioSuplenteNew.getClass(), usuarioSuplenteNew.getPsUsuarioCodigo());
                analistaParametro.setUsuarioSuplente(usuarioSuplenteNew);
            }
            analistaParametro = em.merge(analistaParametro);
            if (usuarioTitularOld != null && !usuarioTitularOld.equals(usuarioTitularNew)) {
                usuarioTitularOld.getAnalistaParametroList().remove(analistaParametro);
                usuarioTitularOld = em.merge(usuarioTitularOld);
            }
            if (usuarioTitularNew != null && !usuarioTitularNew.equals(usuarioTitularOld)) {
                usuarioTitularNew.getAnalistaParametroList().add(analistaParametro);
                usuarioTitularNew = em.merge(usuarioTitularNew);
            }
            if (idParametroOld != null && !idParametroOld.equals(idParametroNew)) {
                idParametroOld.getAnalistaParametroList().remove(analistaParametro);
                idParametroOld = em.merge(idParametroOld);
            }
            if (idParametroNew != null && !idParametroNew.equals(idParametroOld)) {
                idParametroNew.getAnalistaParametroList().add(analistaParametro);
                idParametroNew = em.merge(idParametroNew);
            }
            if (usuarioSuplenteOld != null && !usuarioSuplenteOld.equals(usuarioSuplenteNew)) {
                usuarioSuplenteOld.getAnalistaParametroList().remove(analistaParametro);
                usuarioSuplenteOld = em.merge(usuarioSuplenteOld);
            }
            if (usuarioSuplenteNew != null && !usuarioSuplenteNew.equals(usuarioSuplenteOld)) {
                usuarioSuplenteNew.getAnalistaParametroList().add(analistaParametro);
                usuarioSuplenteNew = em.merge(usuarioSuplenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analistaParametro.getIdanalistaParametro();
                if (findAnalistaParametro(id) == null) {
                    throw new NonexistentEntityException("The analistaParametro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    /*public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnalistaParametro analistaParametro;
            try {
                analistaParametro = em.getReference(AnalistaParametro.class, id);
                analistaParametro.getIdanalistaParametro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analistaParametro with id " + id + " no longer exists.", enfe);
            }
            SUsuario usuarioTitular = analistaParametro.getUsuarioTitular();
            if (usuarioTitular != null) {
                usuarioTitular.getAnalistaParametroList().remove(analistaParametro);
                usuarioTitular = em.merge(usuarioTitular);
            }
            VariableAnalisis idParametro = analistaParametro.getIdParametro();
            if (idParametro != null) {
                idParametro.getAnalistaParametroList().remove(analistaParametro);
                idParametro = em.merge(idParametro);
            }
            SUsuario usuarioSuplente = analistaParametro.getUsuarioSuplente();
            if (usuarioSuplente != null) {
                usuarioSuplente.getAnalistaParametroList().remove(analistaParametro);
                usuarioSuplente = em.merge(usuarioSuplente);
            }
            em.remove(analistaParametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<AnalistaParametro> findAnalistaParametroEntities() {
        return findAnalistaParametroEntities(true, -1, -1);
    }

    public List<AnalistaParametro> findAnalistaParametroEntities(int maxResults, int firstResult) {
        return findAnalistaParametroEntities(false, maxResults, firstResult);
    }

    private List<AnalistaParametro> findAnalistaParametroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnalistaParametro.class));
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

    public AnalistaParametro findAnalistaParametro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnalistaParametro.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalistaParametroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnalistaParametro> rt = cq.from(AnalistaParametro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<AnalistaParametro> encontrarAnalistaParametro(int variable) {
     EntityManager em = getEntityManager();   
        String consulta = "SELECT * FROM analista_parametro where fi_analparam_varianal = ?";
        try {
            Query q = em.createNativeQuery(consulta, AnalistaParametro.class);
            q.setParameter(1, variable);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarVariableParametro(Integer piVarianalId) {
         EntityManager em = getEntityManager();
        try {     
             String delete = "delete from analista_parametro"
                     + " where fi_analparam_varianal = ? ";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(delete);
             insercion.setParameter(1, piVarianalId);
             insercion.executeUpdate();
             em.getTransaction().commit();        
        }catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }

    }

    public List<Object> encontrarAnalistaParametro(String filtros, List<Object> argumentos) {
      EntityManager em = getEntityManager();   
        String consulta = "select matriz_analisis.s_matranal_descripcion, variable_analisis.s_varianal_descripcion, " 
                +"analista_parametro.fs_analparam_usutitul, analista_parametro.fs_analparam_ususupl, "
                + "analista_parametro.fs_analparam_usuterc "
                + "from analista_parametro "
                + "join variable_analisis on variable_analisis.pi_varianal_id= analista_parametro.fi_analparam_varianal "
                + "join matriz_analisis on matriz_analisis.pi_matranal_id= variable_analisis.fi_varianal_matriz "
                + "where  ";
        consulta+=filtros;
        consulta+= " order by variable_analisis.s_varianal_descripcion ASC";
        System.out.println("consulta"+consulta);
        try {  
            Query q = em.createNativeQuery(consulta);
           int j=1;
            for (int i = 0; i < argumentos.size(); i++) {
                System.out.println("argumentos"+argumentos.get(i));
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }    
    }
    
    public Object encontrarUltimoIdAnalistaParametro(){
        EntityManager em = getEntityManager();   
        String consulta = "select max(pi_analparam_id) from analista_parametro";
        try {
            Query q = em.createNativeQuery(consulta);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void adicionarAnalistaParametro(int idAnalistaParametro, VariableAnalisis variableAnalisis, String usuarioPrincipal, String usuarioSuplente, String tercerUsuario) {
        EntityManager em = getEntityManager();
        try {
            String insert = "INSERT INTO analista_parametro (pi_analparam_id, fi_analparam_varianal, "
                    + "fs_analparam_usutitul, fs_analparam_ususupl, "
                    + "fs_analparam_usuterc) "
                    + "VALUES (?, ?, ?, ?, ?)";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(insert);
            insercion.setParameter(1, idAnalistaParametro);
            insercion.setParameter(2, variableAnalisis.getPiVarianalId());
            insercion.setParameter(3, usuarioPrincipal);
            insercion.setParameter(4, usuarioSuplente);
            insercion.setParameter(5, tercerUsuario);        
            insercion.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public void actualizarUsuarioAnalistaParametro(VariableAnalisis variable, String usuarioPrincipal, String usuarioSuplente, String tercerUsuario) {
         EntityManager em = getEntityManager();
         String update = "UPDATE analista_parametro"
                 + " SET fs_analparam_usutitul=?,"
                 + " fs_analparam_ususupl=?,"
                 + " fs_analparam_usuterc=?"
                 + " WHERE fi_analparam_varianal=?";
            em.getTransaction().begin();
            Query insercion = em.createNativeQuery(update);
            insercion.setParameter(1, usuarioPrincipal);
            insercion.setParameter(2, usuarioSuplente);
            insercion.setParameter(3, tercerUsuario);
            insercion.setParameter(4, variable.getPiVarianalId());
            insercion.executeUpdate();
            em.getTransaction().commit();  
    }
    
}
