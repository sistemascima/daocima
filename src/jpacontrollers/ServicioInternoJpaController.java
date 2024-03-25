/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.DetalleServicioInterno;
import entidades.ServicioInterno;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ServicioInternoJpaController implements Serializable {

    public ServicioInternoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioInterno servicioInterno) throws PreexistingEntityException, Exception {
        if (servicioInterno.getDetalleServicioInternoCollection() == null) {
            servicioInterno.setDetalleServicioInternoCollection(new ArrayList<DetalleServicioInterno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicioInterno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicioInterno(servicioInterno.getPiServinteConsecutivo()) != null) {
                throw new PreexistingEntityException("ServicioInterno " + servicioInterno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioInterno servicioInterno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicioInterno = em.merge(servicioInterno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicioInterno.getPiServinteConsecutivo();
                if (findServicioInterno(id) == null) {
                    throw new NonexistentEntityException("The servicioInterno with id " + id + " no longer exists.");
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
            ServicioInterno servicioInterno;
            try {
                servicioInterno = em.getReference(ServicioInterno.class, id);
                servicioInterno.getPiServinteConsecutivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioInterno with id " + id + " no longer exists.", enfe);
            }
            em.remove(servicioInterno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioInterno> findServicioInternoEntities() {
        return findServicioInternoEntities(true, -1, -1);
    }

    public List<ServicioInterno> findServicioInternoEntities(int maxResults, int firstResult) {
        return findServicioInternoEntities(false, maxResults, firstResult);
    }

    private List<ServicioInterno> findServicioInternoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioInterno.class));
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

    public ServicioInterno findServicioInterno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioInterno.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioInternoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioInterno> rt = cq.from(ServicioInterno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Long calcularConsecutivo() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT ifnull(max(si.pi_servinte_consecutivo) + 1, "
                + "	EXTRACT(YEAR FROM NOW()) * 10000 + 1) AS proximoCons "
                + "FROM servicio_interno si "
                + "WHERE si.pi_servinte_consecutivo > EXTRACT(YEAR FROM NOW()) * 10000 AND "
                + "	si.pi_servinte_consecutivo < (EXTRACT(YEAR FROM NOW()) + 1)*10000; ";
        try {
            Query q = em.createNativeQuery(consulta);
            Long resu = (Long) q.getSingleResult();
            return resu;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Long(0);
        } finally {
            em.close();
        }
    }

    public ServicioInterno crearServicioInterno(ServicioInterno nuevo)
            throws PreexistingEntityException, Exception {

        System.out.println("Crear Servicio Interno");
        EntityManager em = getEntityManager();
        int registros = 0;
        Integer consecutivoAsignado = 0;
        String sqlInserta = "INSERT INTO servicio_interno "
                + "(pi_servinte_consecutivo,          d_servinte_fecha,               i_servinte_proyecto, "
                + "fs_servinte_cliente,		fs_servinte_usuasoli,           fs_servinte_departamento, "
                + "fs_servinte_ingeproy,		s_servinte_estado,              s_servinte_observaciones, "
                + "fs_servinte_cooracep,		s_servinte_recibido,            fs_servinte_usuacrea, "
                + "d_servinte_creacion,		fs_servinte_usuultmod, fs_servinte_tipodocumento, fs_servinte_letraprocedimiento, fs_servinte_consecutivo, fs_servinte_version) "
                + "VALUES "
                + "(?,		?,			?, "
                + "?,			?,			?, "
                + "?,			?,			?, "
                + "?,			?,			?, "
                + "NOW(),		NULL,		NULL, ?, ?, ?, ?);";

        String sqlInsertaCliente = "INSERT INTO cliente "
                + "(ps_cliente_nit,           s_cliente_nombre,               fs_cliente_usuacrea, "
                + "d_cliente_creacion,        fs_cliente_usuultmod,           d_cliente_ultimodi) "
                + "VALUES "
                + "(?,		?,		?, ?, ?, ?, ? "
                + "NOW(),		NULL,		NULL);";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consecutivoAsignado = calcularConsecutivo().intValue();
            Query clienteExiste = em.createNativeQuery("SELECT COUNT(1) "
                    + "FROM cliente c "
                    + "WHERE c.ps_cliente_nit = ? ; ");

            clienteExiste.setParameter(1, nuevo.getFsServinteCliente().getClientePK().getPsClienteNit());
            registros = ((Long) clienteExiste.getSingleResult()).intValue();

            if (registros == 0) {
                Query ic = em.createNativeQuery(sqlInsertaCliente);
                ic.setParameter(1, nuevo.getFsServinteCliente().getClientePK().getPsClienteNit());
                ic.setParameter(2, nuevo.getFsServinteCliente().getSClienteNombre().toUpperCase());
                ic.setParameter(3, nuevo.getFsServinteUsuacrea().getPsUsuarioCodigo());
                ic.executeUpdate();
            }

            Query q = em.createNativeQuery(sqlInserta);
            q.setParameter(1, consecutivoAsignado);
            q.setParameter(2, nuevo.getDServinteFecha());
            q.setParameter(3, nuevo.getIServinteProyecto());
            q.setParameter(4, nuevo.getFsServinteCliente().getClientePK().getPsClienteNit());
            q.setParameter(5, nuevo.getFsServinteUsuasoli().getPsUsuarioCodigo());
            q.setParameter(6, nuevo.getFsServinteDepartamento().getPsDepartamCodigo());
            q.setParameter(7, nuevo.getFsServinteIngeproy().getPsUsuarioCodigo());
            q.setParameter(8, nuevo.getSServinteEstado());
            q.setParameter(9, nuevo.getSServinteObservaciones());
            q.setParameter(10, nuevo.getFsServinteCooracep().getPsCargoCodigo());
            q.setParameter(11, nuevo.getSServinteRecibido());
            q.setParameter(12, nuevo.getFsServinteUsuacrea().getPsUsuarioCodigo());
           q.setParameter(13, nuevo.getDocumento().getDocumentoPK().getPfsDocumentTipodocu());
            q.setParameter(14, nuevo.getDocumento().getDocumentoPK().getPfsDocumentLetrproc());
            q.setParameter(15, nuevo.getDocumento().getDocumentoPK().getPsDocumentConsecutivo());
            q.setParameter(16, nuevo.getDocumento().getDocumentoPK().getPsDocumentVersion());
            q.executeUpdate();
            em.getTransaction().commit();
            return findServicioInterno(consecutivoAsignado);
        } catch (Exception ex) {
            if (findServicioInterno(consecutivoAsignado) != null) {
                throw new PreexistingEntityException("Servicio Interno No. " + consecutivoAsignado + " ya existe.", ex);
            }
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioInterno> consultaServicios(String filtros, Queue<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT s.* FROM servicio_interno s WHERE ";
        consulta += filtros + ";";
        System.out.println("consulta" + consulta);
        try {
            int i = 1;
            Query q = em.createNativeQuery(consulta, ServicioInterno.class);
            if (argumentos != null) {
                while (!argumentos.isEmpty()) {
                    q.setParameter(i, argumentos.remove());
                    i++;
                }
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void radicar(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'R',"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void darVoBo(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'V',"
                + "	fs_servinte_usuavobo = ? ,"
                + "	d_servinte_vistbuen = NOW() ,"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void rechazar(Integer servicioInterno, String codigoUsuario,
            String razonRechazo) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'X',"
                + "	s_servinte_observaciones = s_servinte_observaciones + ? ,"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            razonRechazo = " [" + razonRechazo + "]";
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, razonRechazo);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void aceptar(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'A',"
                + "	fs_servinte_usuaacep = ? ,"
                + "	d_servinte_aceptacion = NOW() ,"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, codigoUsuario);
            q.setParameter(3, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("La Solicitud de Compra " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void entregar(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'E',"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void recibir(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'B',"
                + "	s_servinte_recibido = '1' ,"
                + "	d_servinte_recepcion = NOW() ,"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void devolver(Integer servicioInterno, String codigoUsuario) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        String sqlActualizaEstadoSolicitud
                = "UPDATE servicio_interno "
                + "SET s_servinte_estado = 'D',"
                + "	s_servinte_recibido = '0' ,"
                + "	fs_servinte_usuultmod = ? ,"
                + "	d_servinte_ultimodi = NOW() "
                + "WHERE pi_servinte_consecutivo = ? ; ";
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query q = em.createNativeQuery(sqlActualizaEstadoSolicitud);
            q.setParameter(1, codigoUsuario);
            q.setParameter(2, servicioInterno);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findServicioInterno(servicioInterno) == null) {
                    throw new NonexistentEntityException("El Servicio Interno " + servicioInterno + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
