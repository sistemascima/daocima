/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.FotoHidrobiologia;
import java.io.InputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author SISTEMAS
 */
public class FotoHidrobiologiaJpaController implements Serializable {

    public FotoHidrobiologiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

  /*  public void create(FotoHidrobiologia fotoHidrobiologia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatoHidrobiologia fiFotohidroDathidr = fotoHidrobiologia.getFiFotohidroDathidr();
            if (fiFotohidroDathidr != null) {
                fiFotohidroDathidr = em.getReference(fiFotohidroDathidr.getClass(), fiFotohidroDathidr.getPiDatoHidro());
                fotoHidrobiologia.setFiFotohidroDathidr(fiFotohidroDathidr);
            }
            VariableAnalisis fiFotohidroVarianal = fotoHidrobiologia.getFiFotohidroVarianal();
            if (fiFotohidroVarianal != null) {
                fiFotohidroVarianal = em.getReference(fiFotohidroVarianal.getClass(), fiFotohidroVarianal.getPiVarianalId());
                fotoHidrobiologia.setFiFotohidroVarianal(fiFotohidroVarianal);
            }
            Reporte fsFotohidroReporte = fotoHidrobiologia.getFsFotohidroReporte();
            if (fsFotohidroReporte != null) {
                fsFotohidroReporte = em.getReference(fsFotohidroReporte.getClass(), fsFotohidroReporte.getPiReporteId());
                fotoHidrobiologia.setFsFotohidroReporte(fsFotohidroReporte);
            }
            SUsuario fsFotohidroUsuacrea = fotoHidrobiologia.getFsFotohidroUsuacrea();
            if (fsFotohidroUsuacrea != null) {
                fsFotohidroUsuacrea = em.getReference(fsFotohidroUsuacrea.getClass(), fsFotohidroUsuacrea.getPsUsuarioCodigo());
                fotoHidrobiologia.setFsFotohidroUsuacrea(fsFotohidroUsuacrea);
            }
            SUsuario fsFotohidroUsuamodi = fotoHidrobiologia.getFsFotohidroUsuamodi();
            if (fsFotohidroUsuamodi != null) {
                fsFotohidroUsuamodi = em.getReference(fsFotohidroUsuamodi.getClass(), fsFotohidroUsuamodi.getPsUsuarioCodigo());
                fotoHidrobiologia.setFsFotohidroUsuamodi(fsFotohidroUsuamodi);
            }
            em.persist(fotoHidrobiologia);
            if (fiFotohidroDathidr != null) {
                fiFotohidroDathidr.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fiFotohidroDathidr = em.merge(fiFotohidroDathidr);
            }
            if (fiFotohidroVarianal != null) {
                fiFotohidroVarianal.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fiFotohidroVarianal = em.merge(fiFotohidroVarianal);
            }
            if (fsFotohidroReporte != null) {
                fsFotohidroReporte.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroReporte = em.merge(fsFotohidroReporte);
            }
            if (fsFotohidroUsuacrea != null) {
                fsFotohidroUsuacrea.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroUsuacrea = em.merge(fsFotohidroUsuacrea);
            }
            if (fsFotohidroUsuamodi != null) {
                fsFotohidroUsuamodi.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroUsuamodi = em.merge(fsFotohidroUsuamodi);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FotoHidrobiologia fotoHidrobiologia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FotoHidrobiologia persistentFotoHidrobiologia = em.find(FotoHidrobiologia.class, fotoHidrobiologia.getPiFotohidroId());
            DatoHidrobiologia fiFotohidroDathidrOld = persistentFotoHidrobiologia.getFiFotohidroDathidr();
            DatoHidrobiologia fiFotohidroDathidrNew = fotoHidrobiologia.getFiFotohidroDathidr();
            VariableAnalisis fiFotohidroVarianalOld = persistentFotoHidrobiologia.getFiFotohidroVarianal();
            VariableAnalisis fiFotohidroVarianalNew = fotoHidrobiologia.getFiFotohidroVarianal();
            Reporte fsFotohidroReporteOld = persistentFotoHidrobiologia.getFsFotohidroReporte();
            Reporte fsFotohidroReporteNew = fotoHidrobiologia.getFsFotohidroReporte();
            SUsuario fsFotohidroUsuacreaOld = persistentFotoHidrobiologia.getFsFotohidroUsuacrea();
            SUsuario fsFotohidroUsuacreaNew = fotoHidrobiologia.getFsFotohidroUsuacrea();
            SUsuario fsFotohidroUsuamodiOld = persistentFotoHidrobiologia.getFsFotohidroUsuamodi();
            SUsuario fsFotohidroUsuamodiNew = fotoHidrobiologia.getFsFotohidroUsuamodi();
            if (fiFotohidroDathidrNew != null) {
                fiFotohidroDathidrNew = em.getReference(fiFotohidroDathidrNew.getClass(), fiFotohidroDathidrNew.getPiDatoHidro());
                fotoHidrobiologia.setFiFotohidroDathidr(fiFotohidroDathidrNew);
            }
            if (fiFotohidroVarianalNew != null) {
                fiFotohidroVarianalNew = em.getReference(fiFotohidroVarianalNew.getClass(), fiFotohidroVarianalNew.getPiVarianalId());
                fotoHidrobiologia.setFiFotohidroVarianal(fiFotohidroVarianalNew);
            }
            if (fsFotohidroReporteNew != null) {
                fsFotohidroReporteNew = em.getReference(fsFotohidroReporteNew.getClass(), fsFotohidroReporteNew.getPiReporteId());
                fotoHidrobiologia.setFsFotohidroReporte(fsFotohidroReporteNew);
            }
            if (fsFotohidroUsuacreaNew != null) {
                fsFotohidroUsuacreaNew = em.getReference(fsFotohidroUsuacreaNew.getClass(), fsFotohidroUsuacreaNew.getPsUsuarioCodigo());
                fotoHidrobiologia.setFsFotohidroUsuacrea(fsFotohidroUsuacreaNew);
            }
            if (fsFotohidroUsuamodiNew != null) {
                fsFotohidroUsuamodiNew = em.getReference(fsFotohidroUsuamodiNew.getClass(), fsFotohidroUsuamodiNew.getPsUsuarioCodigo());
                fotoHidrobiologia.setFsFotohidroUsuamodi(fsFotohidroUsuamodiNew);
            }
            fotoHidrobiologia = em.merge(fotoHidrobiologia);
            if (fiFotohidroDathidrOld != null && !fiFotohidroDathidrOld.equals(fiFotohidroDathidrNew)) {
                fiFotohidroDathidrOld.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fiFotohidroDathidrOld = em.merge(fiFotohidroDathidrOld);
            }
            if (fiFotohidroDathidrNew != null && !fiFotohidroDathidrNew.equals(fiFotohidroDathidrOld)) {
                fiFotohidroDathidrNew.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fiFotohidroDathidrNew = em.merge(fiFotohidroDathidrNew);
            }
            if (fiFotohidroVarianalOld != null && !fiFotohidroVarianalOld.equals(fiFotohidroVarianalNew)) {
                fiFotohidroVarianalOld.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fiFotohidroVarianalOld = em.merge(fiFotohidroVarianalOld);
            }
            if (fiFotohidroVarianalNew != null && !fiFotohidroVarianalNew.equals(fiFotohidroVarianalOld)) {
                fiFotohidroVarianalNew.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fiFotohidroVarianalNew = em.merge(fiFotohidroVarianalNew);
            }
            if (fsFotohidroReporteOld != null && !fsFotohidroReporteOld.equals(fsFotohidroReporteNew)) {
                fsFotohidroReporteOld.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroReporteOld = em.merge(fsFotohidroReporteOld);
            }
            if (fsFotohidroReporteNew != null && !fsFotohidroReporteNew.equals(fsFotohidroReporteOld)) {
                fsFotohidroReporteNew.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroReporteNew = em.merge(fsFotohidroReporteNew);
            }
            if (fsFotohidroUsuacreaOld != null && !fsFotohidroUsuacreaOld.equals(fsFotohidroUsuacreaNew)) {
                fsFotohidroUsuacreaOld.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroUsuacreaOld = em.merge(fsFotohidroUsuacreaOld);
            }
            if (fsFotohidroUsuacreaNew != null && !fsFotohidroUsuacreaNew.equals(fsFotohidroUsuacreaOld)) {
                fsFotohidroUsuacreaNew.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroUsuacreaNew = em.merge(fsFotohidroUsuacreaNew);
            }
            if (fsFotohidroUsuamodiOld != null && !fsFotohidroUsuamodiOld.equals(fsFotohidroUsuamodiNew)) {
                fsFotohidroUsuamodiOld.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroUsuamodiOld = em.merge(fsFotohidroUsuamodiOld);
            }
            if (fsFotohidroUsuamodiNew != null && !fsFotohidroUsuamodiNew.equals(fsFotohidroUsuamodiOld)) {
                fsFotohidroUsuamodiNew.getFotoHidrobiologiaList().add(fotoHidrobiologia);
                fsFotohidroUsuamodiNew = em.merge(fsFotohidroUsuamodiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fotoHidrobiologia.getPiFotohidroId();
                if (findFotoHidrobiologia(id) == null) {
                    throw new NonexistentEntityException("The fotoHidrobiologia with id " + id + " no longer exists.");
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
            FotoHidrobiologia fotoHidrobiologia;
            try {
                fotoHidrobiologia = em.getReference(FotoHidrobiologia.class, id);
                fotoHidrobiologia.getPiFotohidroId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotoHidrobiologia with id " + id + " no longer exists.", enfe);
            }
            DatoHidrobiologia fiFotohidroDathidr = fotoHidrobiologia.getFiFotohidroDathidr();
            if (fiFotohidroDathidr != null) {
                fiFotohidroDathidr.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fiFotohidroDathidr = em.merge(fiFotohidroDathidr);
            }
            VariableAnalisis fiFotohidroVarianal = fotoHidrobiologia.getFiFotohidroVarianal();
            if (fiFotohidroVarianal != null) {
                fiFotohidroVarianal.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fiFotohidroVarianal = em.merge(fiFotohidroVarianal);
            }
            Reporte fsFotohidroReporte = fotoHidrobiologia.getFsFotohidroReporte();
            if (fsFotohidroReporte != null) {
                fsFotohidroReporte.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroReporte = em.merge(fsFotohidroReporte);
            }
            SUsuario fsFotohidroUsuacrea = fotoHidrobiologia.getFsFotohidroUsuacrea();
            if (fsFotohidroUsuacrea != null) {
                fsFotohidroUsuacrea.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroUsuacrea = em.merge(fsFotohidroUsuacrea);
            }
            SUsuario fsFotohidroUsuamodi = fotoHidrobiologia.getFsFotohidroUsuamodi();
            if (fsFotohidroUsuamodi != null) {
                fsFotohidroUsuamodi.getFotoHidrobiologiaList().remove(fotoHidrobiologia);
                fsFotohidroUsuamodi = em.merge(fsFotohidroUsuamodi);
            }
            em.remove(fotoHidrobiologia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<FotoHidrobiologia> findFotoHidrobiologiaEntities() {
        return findFotoHidrobiologiaEntities(true, -1, -1);
    }

    public List<FotoHidrobiologia> findFotoHidrobiologiaEntities(int maxResults, int firstResult) {
        return findFotoHidrobiologiaEntities(false, maxResults, firstResult);
    }

    private List<FotoHidrobiologia> findFotoHidrobiologiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FotoHidrobiologia.class));
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

    public FotoHidrobiologia findFotoHidrobiologia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FotoHidrobiologia.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotoHidrobiologiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FotoHidrobiologia> rt = cq.from(FotoHidrobiologia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int encontrarCantidadFotoHidrobiologia(String reporte, int idDatoHidrobiologia) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT count(pi_fotohidro_id) FROM foto_hidrobiologia "
                + "where fs_fotohidro_reporte=? "
                + "and fi_fotohidro_dathidr=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            q.setParameter(2, idDatoHidrobiologia);
            Long cantidad= (Long) q.getSingleResult();
            return (int) cantidad.intValue();
        } finally {
            em.close();
        }
    }
    
    
     public int encontrarCantidadFotoHidrobiologiaPorParametro(String reporte, int idVariableAnalisis) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT count(pi_fotohidro_id) FROM foto_hidrobiologia "
                + "where fs_fotohidro_reporte=? "
                + "and fi_fotohidro_varianal=?";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            q.setParameter(2, idVariableAnalisis);
            Long cantidad= (Long) q.getSingleResult();
            return (int) cantidad.intValue();
        } finally {
            em.close();
        }
    }

    public void insertarFotoHidrobiologia(int datoHidrobiologia, String reporte, InputStream in, String usuarioCreacion, int idVariableCalculo) {
       EntityManager em = getEntityManager();
        try {     
               String insert =" INSERT INTO foto_hidrobiologia "
                       + "(fi_fotohidro_dathidr, fs_fotohidro_reporte, by_fotohidro_imagen, "
                       + "fs_fotohidro_usuacrea, d_fotohidro_fechacreac, fi_fotohidro_varianal) "
                       + "VALUES (?, ?, ?, ?, now(), ?);";
             em.getTransaction().begin();
             Query insercion = em.createNativeQuery(insert);
             insercion.setParameter(1, datoHidrobiologia);
             insercion.setParameter(2, reporte);
             insercion.setParameter(3, in);
             insercion.setParameter(4, usuarioCreacion);
              insercion.setParameter(5, idVariableCalculo);
             
             insercion.executeUpdate();
             em.getTransaction().commit();  
        } catch (Exception ex) {
           throw ex;
        } finally {
            em.close();
        }      
    }

    public List<Object> encontrarFotoHidrobiologia(String reporte, int idVariableCalculo, int idMuestraInicial, int idMuestraFinal) {
     EntityManager em = getEntityManager();
        String consulta = "select  by_fotohidro_imagen, dato_hidrobiologia.s_dato_hidro_epecie_morfoespecie, dato_hidrobiologia.s_dato_hidro_sufijo "
                + "from resultado "
                + "join foto_hidrobiologia on foto_hidrobiologia.fi_fotohidro_dathidr= resultado.fki_resultado_dato_hidrobiologia "
                + "join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= foto_hidrobiologia.fi_fotohidro_dathidr "
                + "join valor on valor.pi_valor_id= resultado.fi_resultado_valor "
                + "join muestra_analisis on muestra_analisis.pi_muestraanal_id= valor.fi_valor_muestraanal "
                + "join muestra on muestra.pi_muestra_id=muestra_analisis.fi_muestraanal_muestra "
                + " join variable_calculo on variable_calculo.pi_variable_calculo= resultado.fi_resultado_variable_calculo "
                + "where variable_calculo.i_variable_calculo_valor_reporte=1 "
                + "AND muestra.pi_muestra_id between ? and ? "
                + "and muestra_analisis.fi_muestraanal_variable=? "
                + "and foto_hidrobiologia.fs_fotohidro_reporte=? "
                + "group by fki_resultado_dato_hidrobiologia;";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, idMuestraInicial);
            q.setParameter(2, idMuestraFinal);
            q.setParameter(3, idVariableCalculo);
             q.setParameter(4, reporte);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
     public List<Object> encontrarFotoHidrobiologiaPorReporteEspecie(String reporte, String especie, String sufijo, int idVariableAnalisis) {
         System.out.println("reporte " + reporte);
           System.out.println("especie  " + especie);
            System.out.println("variable analisis  " + idVariableAnalisis);
                 System.out.println("sufijo  " + sufijo);
         EntityManager em = getEntityManager();
        String consulta = "SELECT by_fotohidro_imagen "
                 + " from foto_hidrobiologia "
                 + " join dato_hidrobiologia on dato_hidrobiologia.pi_dato_hidro= foto_hidrobiologia.fi_fotohidro_dathidr "
                 + " where fs_fotohidro_reporte=? "
                 + " and fi_fotohidro_varianal=? "
                 + " and s_dato_hidro_epecie_morfoespecie=? "
                + " and s_dato_hidro_sufijo=? ";
        try {
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, reporte);
            q.setParameter(2, idVariableAnalisis);
            q.setParameter(3, especie);
            q.setParameter(4, sufijo);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarFotoHidrobiologiaPorEspecie(int idVariableCalculo) {
        EntityManager em = getEntityManager();
        String insert = "delete from foto_hidrobiologia "
                + "where fi_fotohidro_dathidr=? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, idVariableCalculo);
        insercion.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    
     public int encontraridEspecieHidrobiologia(String reporte, String parametro, String especie, String sufijo) {
        EntityManager em = getEntityManager();
        String insert =  "	select foto_hidrobiologia.fi_fotohidro_dathidr "
                + "	from foto_hidrobiologia, dato_hidrobiologia, variable_analisis "
                + "	where fs_fotohidro_reporte=? "
                + "	and dato_hidrobiologia.pi_dato_hidro= foto_hidrobiologia.fi_fotohidro_dathidr "
                + "	and variable_analisis.pi_varianal_id= foto_hidrobiologia.fi_fotohidro_varianal "
                + "	and variable_analisis.s_varianal_descripcion=? "
                + "	and dato_hidrobiologia.s_dato_hidro_epecie_morfoespecie=? "
                + "	and dato_hidrobiologia.s_dato_hidro_sufijo=?  ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, reporte);
        insercion.setParameter(2, parametro);
        insercion.setParameter(3, especie);
        insercion.setParameter(4, sufijo);
        return (Integer) insercion.getSingleResult();
    }
     
   
   

  
    
}
