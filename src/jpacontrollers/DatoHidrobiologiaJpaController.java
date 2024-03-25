/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.DatoHidrobiologia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.VariableAnalisis;
import entidades.SUsuario;
import entidades.Resultado;
import java.util.ArrayList;
import java.util.List;
import entidades.FotoHidrobiologia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.NonexistentEntityException;

/**
 *
 * @author SISTEMAS
 */
public class DatoHidrobiologiaJpaController implements Serializable {

    public DatoHidrobiologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   /* public void create(DatoHidrobiologia datoHidrobiologia) {
        if (datoHidrobiologia.getResultadoList() == null) {
            datoHidrobiologia.setResultadoList(new ArrayList<Resultado>());
        }
        if (datoHidrobiologia.getFotoHidrobiologiaList() == null) {
            datoHidrobiologia.setFotoHidrobiologiaList(new ArrayList<FotoHidrobiologia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VariableAnalisis fiDatoHidroVarAnal = datoHidrobiologia.getFiDatoHidroVarAnal();
            if (fiDatoHidroVarAnal != null) {
                fiDatoHidroVarAnal = em.getReference(fiDatoHidroVarAnal.getClass(), fiDatoHidroVarAnal.getPiVarianalId());
                datoHidrobiologia.setFiDatoHidroVarAnal(fiDatoHidroVarAnal);
            }
            SUsuario SDatoHidroUsuacrea = datoHidrobiologia.getSDatoHidroUsuacrea();
            if (SDatoHidroUsuacrea != null) {
                SDatoHidroUsuacrea = em.getReference(SDatoHidroUsuacrea.getClass(), SDatoHidroUsuacrea.getPsUsuarioCodigo());
                datoHidrobiologia.setSDatoHidroUsuacrea(SDatoHidroUsuacrea);
            }
            SUsuario SDatoHidroUsuamodi = datoHidrobiologia.getSDatoHidroUsuamodi();
            if (SDatoHidroUsuamodi != null) {
                SDatoHidroUsuamodi = em.getReference(SDatoHidroUsuamodi.getClass(), SDatoHidroUsuamodi.getPsUsuarioCodigo());
                datoHidrobiologia.setSDatoHidroUsuamodi(SDatoHidroUsuamodi);
            }
            List<Resultado> attachedResultadoList = new ArrayList<Resultado>();
            for (Resultado resultadoListResultadoToAttach : datoHidrobiologia.getResultadoList()) {
                resultadoListResultadoToAttach = em.getReference(resultadoListResultadoToAttach.getClass(), resultadoListResultadoToAttach.getPiResultadoId());
                attachedResultadoList.add(resultadoListResultadoToAttach);
            }
            datoHidrobiologia.setResultadoList(attachedResultadoList);
            List<FotoHidrobiologia> attachedFotoHidrobiologiaList = new ArrayList<FotoHidrobiologia>();
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologiaToAttach : datoHidrobiologia.getFotoHidrobiologiaList()) {
                fotoHidrobiologiaListFotoHidrobiologiaToAttach = em.getReference(fotoHidrobiologiaListFotoHidrobiologiaToAttach.getClass(), fotoHidrobiologiaListFotoHidrobiologiaToAttach.getPiFotohidroId());
                attachedFotoHidrobiologiaList.add(fotoHidrobiologiaListFotoHidrobiologiaToAttach);
            }
            datoHidrobiologia.setFotoHidrobiologiaList(attachedFotoHidrobiologiaList);
            em.persist(datoHidrobiologia);
            if (fiDatoHidroVarAnal != null) {
                fiDatoHidroVarAnal.getDatoHidrobiologiaList().add(datoHidrobiologia);
                fiDatoHidroVarAnal = em.merge(fiDatoHidroVarAnal);
            }
            if (SDatoHidroUsuacrea != null) {
                SDatoHidroUsuacrea.getDatoHidrobiologiaList().add(datoHidrobiologia);
                SDatoHidroUsuacrea = em.merge(SDatoHidroUsuacrea);
            }
            if (SDatoHidroUsuamodi != null) {
                SDatoHidroUsuamodi.getDatoHidrobiologiaList().add(datoHidrobiologia);
                SDatoHidroUsuamodi = em.merge(SDatoHidroUsuamodi);
            }
            for (Resultado resultadoListResultado : datoHidrobiologia.getResultadoList()) {
                DatoHidrobiologia oldFkiResultadoDatoHidrobiologiaOfResultadoListResultado = resultadoListResultado.getFkiResultadoDatoHidrobiologia();
                resultadoListResultado.setFkiResultadoDatoHidrobiologia(datoHidrobiologia);
                resultadoListResultado = em.merge(resultadoListResultado);
                if (oldFkiResultadoDatoHidrobiologiaOfResultadoListResultado != null) {
                    oldFkiResultadoDatoHidrobiologiaOfResultadoListResultado.getResultadoList().remove(resultadoListResultado);
                    oldFkiResultadoDatoHidrobiologiaOfResultadoListResultado = em.merge(oldFkiResultadoDatoHidrobiologiaOfResultadoListResultado);
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologia : datoHidrobiologia.getFotoHidrobiologiaList()) {
                DatoHidrobiologia oldFiFotohidroDathidrOfFotoHidrobiologiaListFotoHidrobiologia = fotoHidrobiologiaListFotoHidrobiologia.getFiFotohidroDathidr();
                fotoHidrobiologiaListFotoHidrobiologia.setFiFotohidroDathidr(datoHidrobiologia);
                fotoHidrobiologiaListFotoHidrobiologia = em.merge(fotoHidrobiologiaListFotoHidrobiologia);
                if (oldFiFotohidroDathidrOfFotoHidrobiologiaListFotoHidrobiologia != null) {
                    oldFiFotohidroDathidrOfFotoHidrobiologiaListFotoHidrobiologia.getFotoHidrobiologiaList().remove(fotoHidrobiologiaListFotoHidrobiologia);
                    oldFiFotohidroDathidrOfFotoHidrobiologiaListFotoHidrobiologia = em.merge(oldFiFotohidroDathidrOfFotoHidrobiologiaListFotoHidrobiologia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

   /* public void edit(DatoHidrobiologia datoHidrobiologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatoHidrobiologia persistentDatoHidrobiologia = em.find(DatoHidrobiologia.class, datoHidrobiologia.getPiDatoHidro());
            VariableAnalisis fiDatoHidroVarAnalOld = persistentDatoHidrobiologia.getFiDatoHidroVarAnal();
            VariableAnalisis fiDatoHidroVarAnalNew = datoHidrobiologia.getFiDatoHidroVarAnal();
            SUsuario SDatoHidroUsuacreaOld = persistentDatoHidrobiologia.getSDatoHidroUsuacrea();
            SUsuario SDatoHidroUsuacreaNew = datoHidrobiologia.getSDatoHidroUsuacrea();
            SUsuario SDatoHidroUsuamodiOld = persistentDatoHidrobiologia.getSDatoHidroUsuamodi();
            SUsuario SDatoHidroUsuamodiNew = datoHidrobiologia.getSDatoHidroUsuamodi();
            List<Resultado> resultadoListOld = persistentDatoHidrobiologia.getResultadoList();
            List<Resultado> resultadoListNew = datoHidrobiologia.getResultadoList();
            List<FotoHidrobiologia> fotoHidrobiologiaListOld = persistentDatoHidrobiologia.getFotoHidrobiologiaList();
            List<FotoHidrobiologia> fotoHidrobiologiaListNew = datoHidrobiologia.getFotoHidrobiologiaList();
            if (fiDatoHidroVarAnalNew != null) {
                fiDatoHidroVarAnalNew = em.getReference(fiDatoHidroVarAnalNew.getClass(), fiDatoHidroVarAnalNew.getPiVarianalId());
                datoHidrobiologia.setFiDatoHidroVarAnal(fiDatoHidroVarAnalNew);
            }
            if (SDatoHidroUsuacreaNew != null) {
                SDatoHidroUsuacreaNew = em.getReference(SDatoHidroUsuacreaNew.getClass(), SDatoHidroUsuacreaNew.getPsUsuarioCodigo());
                datoHidrobiologia.setSDatoHidroUsuacrea(SDatoHidroUsuacreaNew);
            }
            if (SDatoHidroUsuamodiNew != null) {
                SDatoHidroUsuamodiNew = em.getReference(SDatoHidroUsuamodiNew.getClass(), SDatoHidroUsuamodiNew.getPsUsuarioCodigo());
                datoHidrobiologia.setSDatoHidroUsuamodi(SDatoHidroUsuamodiNew);
            }
            List<Resultado> attachedResultadoListNew = new ArrayList<Resultado>();
            for (Resultado resultadoListNewResultadoToAttach : resultadoListNew) {
                resultadoListNewResultadoToAttach = em.getReference(resultadoListNewResultadoToAttach.getClass(), resultadoListNewResultadoToAttach.getPiResultadoId());
                attachedResultadoListNew.add(resultadoListNewResultadoToAttach);
            }
            resultadoListNew = attachedResultadoListNew;
            datoHidrobiologia.setResultadoList(resultadoListNew);
            List<FotoHidrobiologia> attachedFotoHidrobiologiaListNew = new ArrayList<FotoHidrobiologia>();
            for (FotoHidrobiologia fotoHidrobiologiaListNewFotoHidrobiologiaToAttach : fotoHidrobiologiaListNew) {
                fotoHidrobiologiaListNewFotoHidrobiologiaToAttach = em.getReference(fotoHidrobiologiaListNewFotoHidrobiologiaToAttach.getClass(), fotoHidrobiologiaListNewFotoHidrobiologiaToAttach.getPiFotohidroId());
                attachedFotoHidrobiologiaListNew.add(fotoHidrobiologiaListNewFotoHidrobiologiaToAttach);
            }
            fotoHidrobiologiaListNew = attachedFotoHidrobiologiaListNew;
            datoHidrobiologia.setFotoHidrobiologiaList(fotoHidrobiologiaListNew);
            datoHidrobiologia = em.merge(datoHidrobiologia);
            if (fiDatoHidroVarAnalOld != null && !fiDatoHidroVarAnalOld.equals(fiDatoHidroVarAnalNew)) {
                fiDatoHidroVarAnalOld.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                fiDatoHidroVarAnalOld = em.merge(fiDatoHidroVarAnalOld);
            }
            if (fiDatoHidroVarAnalNew != null && !fiDatoHidroVarAnalNew.equals(fiDatoHidroVarAnalOld)) {
                fiDatoHidroVarAnalNew.getDatoHidrobiologiaList().add(datoHidrobiologia);
                fiDatoHidroVarAnalNew = em.merge(fiDatoHidroVarAnalNew);
            }
            if (SDatoHidroUsuacreaOld != null && !SDatoHidroUsuacreaOld.equals(SDatoHidroUsuacreaNew)) {
                SDatoHidroUsuacreaOld.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                SDatoHidroUsuacreaOld = em.merge(SDatoHidroUsuacreaOld);
            }
            if (SDatoHidroUsuacreaNew != null && !SDatoHidroUsuacreaNew.equals(SDatoHidroUsuacreaOld)) {
                SDatoHidroUsuacreaNew.getDatoHidrobiologiaList().add(datoHidrobiologia);
                SDatoHidroUsuacreaNew = em.merge(SDatoHidroUsuacreaNew);
            }
            if (SDatoHidroUsuamodiOld != null && !SDatoHidroUsuamodiOld.equals(SDatoHidroUsuamodiNew)) {
                SDatoHidroUsuamodiOld.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                SDatoHidroUsuamodiOld = em.merge(SDatoHidroUsuamodiOld);
            }
            if (SDatoHidroUsuamodiNew != null && !SDatoHidroUsuamodiNew.equals(SDatoHidroUsuamodiOld)) {
                SDatoHidroUsuamodiNew.getDatoHidrobiologiaList().add(datoHidrobiologia);
                SDatoHidroUsuamodiNew = em.merge(SDatoHidroUsuamodiNew);
            }
            for (Resultado resultadoListOldResultado : resultadoListOld) {
                if (!resultadoListNew.contains(resultadoListOldResultado)) {
                    resultadoListOldResultado.setFkiResultadoDatoHidrobiologia(null);
                    resultadoListOldResultado = em.merge(resultadoListOldResultado);
                }
            }
            for (Resultado resultadoListNewResultado : resultadoListNew) {
                if (!resultadoListOld.contains(resultadoListNewResultado)) {
                    DatoHidrobiologia oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado = resultadoListNewResultado.getFkiResultadoDatoHidrobiologia();
                    resultadoListNewResultado.setFkiResultadoDatoHidrobiologia(datoHidrobiologia);
                    resultadoListNewResultado = em.merge(resultadoListNewResultado);
                    if (oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado != null && !oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado.equals(datoHidrobiologia)) {
                        oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado.getResultadoList().remove(resultadoListNewResultado);
                        oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado = em.merge(oldFkiResultadoDatoHidrobiologiaOfResultadoListNewResultado);
                    }
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListOldFotoHidrobiologia : fotoHidrobiologiaListOld) {
                if (!fotoHidrobiologiaListNew.contains(fotoHidrobiologiaListOldFotoHidrobiologia)) {
                    fotoHidrobiologiaListOldFotoHidrobiologia.setFiFotohidroDathidr(null);
                    fotoHidrobiologiaListOldFotoHidrobiologia = em.merge(fotoHidrobiologiaListOldFotoHidrobiologia);
                }
            }
            for (FotoHidrobiologia fotoHidrobiologiaListNewFotoHidrobiologia : fotoHidrobiologiaListNew) {
                if (!fotoHidrobiologiaListOld.contains(fotoHidrobiologiaListNewFotoHidrobiologia)) {
                    DatoHidrobiologia oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia = fotoHidrobiologiaListNewFotoHidrobiologia.getFiFotohidroDathidr();
                    fotoHidrobiologiaListNewFotoHidrobiologia.setFiFotohidroDathidr(datoHidrobiologia);
                    fotoHidrobiologiaListNewFotoHidrobiologia = em.merge(fotoHidrobiologiaListNewFotoHidrobiologia);
                    if (oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia != null && !oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia.equals(datoHidrobiologia)) {
                        oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia.getFotoHidrobiologiaList().remove(fotoHidrobiologiaListNewFotoHidrobiologia);
                        oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia = em.merge(oldFiFotohidroDathidrOfFotoHidrobiologiaListNewFotoHidrobiologia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datoHidrobiologia.getPiDatoHidro();
                if (findDatoHidrobiologia(id) == null) {
                    throw new NonexistentEntityException("The datoHidrobiologia with id " + id + " no longer exists.");
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
            DatoHidrobiologia datoHidrobiologia;
            try {
                datoHidrobiologia = em.getReference(DatoHidrobiologia.class, id);
                datoHidrobiologia.getPiDatoHidro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datoHidrobiologia with id " + id + " no longer exists.", enfe);
            }
            VariableAnalisis fiDatoHidroVarAnal = datoHidrobiologia.getFiDatoHidroVarAnal();
            if (fiDatoHidroVarAnal != null) {
                fiDatoHidroVarAnal.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                fiDatoHidroVarAnal = em.merge(fiDatoHidroVarAnal);
            }
            SUsuario SDatoHidroUsuacrea = datoHidrobiologia.getSDatoHidroUsuacrea();
            if (SDatoHidroUsuacrea != null) {
                SDatoHidroUsuacrea.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                SDatoHidroUsuacrea = em.merge(SDatoHidroUsuacrea);
            }
            SUsuario SDatoHidroUsuamodi = datoHidrobiologia.getSDatoHidroUsuamodi();
            if (SDatoHidroUsuamodi != null) {
                SDatoHidroUsuamodi.getDatoHidrobiologiaList().remove(datoHidrobiologia);
                SDatoHidroUsuamodi = em.merge(SDatoHidroUsuamodi);
            }
            List<Resultado> resultadoList = datoHidrobiologia.getResultadoList();
            for (Resultado resultadoListResultado : resultadoList) {
                resultadoListResultado.setFkiResultadoDatoHidrobiologia(null);
                resultadoListResultado = em.merge(resultadoListResultado);
            }
            List<FotoHidrobiologia> fotoHidrobiologiaList = datoHidrobiologia.getFotoHidrobiologiaList();
            for (FotoHidrobiologia fotoHidrobiologiaListFotoHidrobiologia : fotoHidrobiologiaList) {
                fotoHidrobiologiaListFotoHidrobiologia.setFiFotohidroDathidr(null);
                fotoHidrobiologiaListFotoHidrobiologia = em.merge(fotoHidrobiologiaListFotoHidrobiologia);
            }
            em.remove(datoHidrobiologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<DatoHidrobiologia> findDatoHidrobiologiaEntities() {
        return findDatoHidrobiologiaEntities(true, -1, -1);
    }

    public List<DatoHidrobiologia> findDatoHidrobiologiaEntities(int maxResults, int firstResult) {
        return findDatoHidrobiologiaEntities(false, maxResults, firstResult);
    }

    private List<DatoHidrobiologia> findDatoHidrobiologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatoHidrobiologia.class));
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

    public DatoHidrobiologia findDatoHidrobiologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatoHidrobiologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatoHidrobiologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatoHidrobiologia> rt = cq.from(DatoHidrobiologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarGenero(int idVariableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = " select CONCAT(s_dato_hidro_epecie_morfoespecie, \" \", IFNULL(s_dato_hidro_sufijo,'' ))"
        + "from dato_hidrobiologia "
        + "where fi_dato_hidro_var_anal=? "
        + "and c_dato_hidro_estado='V' "
        + "order by s_dato_hidro_epecie_morfoespecie asc ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object> encontrarValorHidrobiologia(String genero, int idVariableAnalisis) {
        System.out.println("genero " + genero);
      EntityManager em = getEntityManager();
        String consulta = "select pi_dato_hidro from dato_hidrobiologia " +
                          "where fi_dato_hidro_var_anal=? "+ 
                          "and c_dato_hidro_estado='V' " +
                          "and CONCAT(s_dato_hidro_epecie_morfoespecie, \" \", IFNULL(s_dato_hidro_sufijo,'' )) = ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, genero);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Object> encontrarValorHidrobiologiaGeneroSufijo(String genero, String sufijo, int idVariableAnalisis) {
      EntityManager em = getEntityManager();
        String consulta = "select pi_dato_hidro from dato_hidrobiologia " +
                          "where fi_dato_hidro_var_anal=? "+ 
                          "and c_dato_hidro_estado='V' " +
                          "and s_dato_hidro_epecie_morfoespecie=? and s_dato_hidro_sufijo= ?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idVariableAnalisis);
            q.setParameter(2, genero);
             q.setParameter(3, sufijo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Integer> encontrarListaHidrobiologia(String idReporte) {
      EntityManager em = getEntityManager();
        String consulta = "select distinct(dato_hidrobiologia.pi_dato_hidro) "
                + "from muestra "
                + "join muestra_analisis on muestra_analisis.fi_muestraanal_muestra= muestra.pi_muestra_id "
                + "join valor on valor.fi_valor_muestraanal= muestra_analisis.pi_muestraanal_id "
                + "join resultado on resultado.fi_resultado_valor= valor.pi_valor_id "
                + "join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= resultado.fki_resultado_dato_hidrobiologia "
                + "join variable_analisis on variable_analisis.pi_varianal_id= muestra_analisis.fi_muestraanal_variable "
                + "where fs_muestra_reporte=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idReporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int encontrarIdDatoHidrobiologia(int idParametro, String genero, String sufijo) {
     EntityManager em = getEntityManager();
        System.out.println("parametro" +idParametro );
        System.out.println("genero" + genero);
        System.out.println("sufijo" + sufijo);
        if(sufijo.isEmpty()){
             String consulta = "select pi_dato_hidro "
                + "from dato_hidrobiologia "
                + "where fi_dato_hidro_var_anal=? and s_dato_hidro_epecie_morfoespecie=? "
                + "and c_dato_hidro_estado='V' ";
            try {
                Query q = em.createNativeQuery(consulta);
                q.setParameter(1, idParametro);
                q.setParameter(2, genero);
                return (int)q.getSingleResult();
            } finally {
                em.close();
            }
        }
        else{
             String consulta = "select pi_dato_hidro "
                + "from dato_hidrobiologia "
                + "where fi_dato_hidro_var_anal=? and s_dato_hidro_epecie_morfoespecie=? "
                + "and s_dato_hidro_sufijo= ? "
                + "and c_dato_hidro_estado='V' ";
             System.out.print(consulta);
            try {
                Query q = em.createNativeQuery(consulta);
                q.setParameter(1, idParametro);
                q.setParameter(2, genero);
                q.setParameter(3, sufijo);
                return (int)q.getSingleResult();
            } finally {
                em.close();
            }
        }
       
    }

    public List<Object> encontrarEspeciesMorfoEspecies(Integer piVarianalId) {
        EntityManager em = getEntityManager();
        String consulta = "select s_dato_hidro_epecie_morfoespecie, s_dato_hidro_sufijo, "
                + " s_dato_hidro_division_phylum, "
                + "s_dato_hidro_clase, s_dato_hidro_orden, s_dato_hidro_familia, s_dato_hidro_nombre_comun "
                + "from dato_hidrobiologia "
                + "where fi_dato_hidro_var_anal=? "
                + "and c_dato_hidro_estado='V' "
                + "order by s_dato_hidro_epecie_morfoespecie asc ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public void crearVariable(String especieMorfoespecie, String sufijo, 
                                           String divisionPhylum, String clase, 
                                           String orden, String familia, 
                                           String nombreComun, int ultimaVersion, 
                                           int idVariableAnalisis, String usuario) {
    EntityManager em = getEntityManager();
        try {     
             String insert = "INSERT INTO dato_hidrobiologia (fi_dato_hidro_var_anal, "
                    + "s_dato_hidro_epecie_morfoespecie,  "
                    + "s_dato_hidro_division_phylum, "
                    + "s_dato_hidro_clase, "
                    + "s_dato_hidro_orden, "
                    + "s_dato_hidro_familia,"
                    + " s_dato_hidro_sufijo ,    "
                    + "s_dato_hidro_usuacrea, i_dato_hidro_version, s_dato_hidro_nombre_comun, c_dato_hidro_estado,  d_dato_hidro_fechcreacion) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?,  'V' , now())";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1,idVariableAnalisis );
             insercion.setParameter(2, especieMorfoespecie);
             insercion.setParameter(3, divisionPhylum);
             insercion.setParameter(4, clase);
             insercion.setParameter(5, orden);
             insercion.setParameter(6, familia);
             insercion.setParameter(7, sufijo);
             insercion.setParameter(8, usuario);
             insercion.setParameter(9, ultimaVersion);
             insercion.setParameter(10, nombreComun);
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      

    }

    public int encontrarUltimaVersionEspecie(Integer piVarianalId) {
    EntityManager em = getEntityManager();
        String consulta = "select DISTINCT i_dato_hidro_version "
                + "from dato_hidrobiologia "
                + "where fi_dato_hidro_var_anal=? "
                + "and c_dato_hidro_estado='V' ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, piVarianalId);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void actualizarEspecieMorfoespecie(String especieMorfoespecie, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_epecie_morfoespecie =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, especieMorfoespecie);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
    
       public void actualizarSufijo(String sufijo, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_sufijo =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, sufijo);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
       
    public void actualizarPhylum(String phylum, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_division_phylum =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, phylum);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
    
    public void actualizarClase(String clase, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_clase =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, clase);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
    
    public void actualizarOrden(String orden, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_orden =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, orden);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
    
      
    public void actualizarFamilia(String orden, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_familia =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, orden);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }

      
    public void actualizarNombreComun(String orden, Integer idDatoHidro) {
      EntityManager em = getEntityManager();
        try {
            Query insercion = null;
            //Validar si la muestra es de aire
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_nombre_comun =? "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, orden);
                insercion.setParameter(2, idDatoHidro);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    
    }
    
    public int encontrarIdEspecieMorfoespecie(String especieMorfoespecie, String sufijo, 
                                              String phylum, String clase, 
                                              String orden, String familia, 
                                              Integer piVarianalId) {
        
     EntityManager em = getEntityManager();
        String consulta = "select pi_dato_hidro "
                + "from dato_hidrobiologia "
                + "where s_dato_hidro_epecie_morfoespecie=? "
                + "and s_dato_hidro_sufijo=? "
                + "and s_dato_hidro_division_phylum=? "
                + "and s_dato_hidro_clase=? "
                + "and s_dato_hidro_orden=? "
                + "and s_dato_hidro_familia=? "
                + "and fi_dato_hidro_var_anal=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, especieMorfoespecie);
            q.setParameter(2, sufijo);
            q.setParameter(3, phylum);
            q.setParameter(4, clase);
            q.setParameter(5, orden);
            q.setParameter(6, familia);
            q.setParameter(7, piVarianalId);
            return (int) q.getSingleResult();
        } finally {
            em.close();
        }
    
    }
    
    public void eliminarDatoHidrobiologia(Integer idVariableCalculo) {
        EntityManager em = getEntityManager();
        String insert = "delete from dato_hidrobiologia "
                + "where pi_dato_hidro=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idVariableCalculo);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarValorEspecieMorfoespeciePorIdVariable(String especie, String sufijo, String phylum, String clase, String orden,
                                                            String familia, String nombreComun, int idEspecieMorfoespecie, String usuario) {
    EntityManager em = getEntityManager();
        try {
            Query insercion = null;
                String insert = "UPDATE dato_hidrobiologia SET s_dato_hidro_epecie_morfoespecie =?, "
                        + " s_dato_hidro_division_phylum= ? , s_dato_hidro_clase=?, s_dato_hidro_orden=?,"
                        + " s_dato_hidro_familia=?, s_dato_hidro_sufijo=?, s_dato_hidro_nombre_comun=?,"
                        + " s_dato_hidro_usuamodi=? , d_dato_hidro_fechmodi=now() "
                        + " WHERE pi_dato_hidro =? ;";
                em.getTransaction().begin();
                insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, especie);
                insercion.setParameter(2, phylum);
                insercion.setParameter(3, clase);
                insercion.setParameter(4, orden);
                insercion.setParameter(5, familia);
                insercion.setParameter(6, sufijo);
                insercion.setParameter(7, nombreComun);
                insercion.setParameter(8, usuario);
                insercion.setParameter(9, idEspecieMorfoespecie);
                insercion.executeUpdate();
                em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }   
    }
    
    
}
