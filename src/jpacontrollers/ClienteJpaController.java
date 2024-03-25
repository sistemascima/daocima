/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontrollers;

import entidades.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.ServicioInterno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpacontrollers.exceptions.IllegalOrphanException;
import jpacontrollers.exceptions.NonexistentEntityException;
import jpacontrollers.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getServicioInternoCollection() == null) {
            cliente.setServicioInternoCollection(new ArrayList<ServicioInterno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getClientePK().getPsClienteNit()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getClientePK().getPsClienteNit();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getClientePK().getPsClienteNit();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public ArrayList<String> busquedaClienteNit(String cadenaBusqueda) {
        EntityManager em = getEntityManager();
        ArrayList<String> retorno = new ArrayList<String>();
        String consulta = "SELECT CONCAT('[', c.ps_cliente_nit, '] ', c.s_cliente_nombre) AS texto "
                + "FROM cliente c "
                + "WHERE c.ps_cliente_nit like ? order by  c.s_cliente_nombre asc ; ";

        try {
            cadenaBusqueda = "%" + cadenaBusqueda + "%";
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, cadenaBusqueda);
            List<String> aux = q.getResultList();
            retorno.addAll(aux);
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return retorno;
        } finally {
            em.close();
        }
    }

    public ArrayList<String> busquedaClienteNombre(String cadenaBusqueda) {
        EntityManager em = getEntityManager();
        ArrayList<String> retorno = new ArrayList<String>();
        String consulta = "SELECT CONCAT('[', c.ps_cliente_nit, '] ', c.s_cliente_nombre) AS texto "
                + "FROM cliente c "
                + "WHERE LOWER(c.s_cliente_nombre) like ?; ";
        try {
            cadenaBusqueda = "%" + cadenaBusqueda + "%";
            Query q = em.createNativeQuery(consulta);
            q.setParameter(1, cadenaBusqueda);
            List<String> aux = q.getResultList();
            retorno.addAll(aux);
            return retorno;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return retorno;
        } finally {
            em.close();
        }
    }

    public Cliente encontrarClientePorNombre(String nombre) {
        List<Cliente> results = new ArrayList<Cliente>();
        results = getEntityManager().createNamedQuery("Cliente.findBySClienteNombre").setParameter("sClienteNombre", nombre).getResultList();
        Cliente client = null;
        for (Cliente cliente : results) {
            client = cliente;
        }
        return client;
    }

    public void crearCliente(Cliente cliente) {

        EntityManager em = getEntityManager();
        try {
            if (cliente.getClientePK() != null) {
                String insert = "INSERT INTO cliente (s_cliente_nombre, s_cliente_direccion, s_cliente_contacto, "
                        + "s_cliente_telefono, s_cliente_correo, fs_cliente_usuacrea, "
                        + "d_cliente_creacion, ps_cliente_nit) "
                        + "VALUES (?, ?, ?, ?, ?, ?, now(), ?)";
                em.getTransaction().begin();
                Query insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, cliente.getSClienteNombre());
                insercion.setParameter(2, cliente.getDireccion());
                insercion.setParameter(3, cliente.getContacto());
                insercion.setParameter(4, cliente.getTelefono());
                insercion.setParameter(5, cliente.getCorreo());
                insercion.setParameter(6, "SISTEMAS");
                insercion.setParameter(7, cliente.getClientePK().getPsClienteNit());
                insercion.executeUpdate();
                em.getTransaction().commit();
            } else {
                String insert = "INSERT INTO cliente (s_cliente_nombre,"
                        + "s_cliente_direccion, s_cliente_contacto, "
                        + "s_cliente_telefono, s_cliente_correo, fs_cliente_usuacrea, "
                        + "d_cliente_creacion) "
                        + "VALUES (?, ?, ?, ?, ?, ?, now())";
                em.getTransaction().begin();
                Query insercion = em.createNativeQuery(insert);
                insercion.setParameter(1, cliente.getSClienteNombre());
                insercion.setParameter(2, cliente.getDireccion());
                insercion.setParameter(3, cliente.getContacto());
                insercion.setParameter(4, cliente.getTelefono());
                insercion.setParameter(5, cliente.getCorreo());
                insercion.setParameter(6, "SISTEMAS");
                insercion.executeUpdate();
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public Cliente actualizarCliente(Cliente cliente) {
       
         
        EntityManager em = getEntityManager();
        String insert = "UPDATE cliente SET s_cliente_nombre= ?, "
                + " s_cliente_direccion = ?, s_cliente_telefono=?,"
                + " s_cliente_contacto= ?,"
                + " s_cliente_correo=?,"
                + " d_cliente_ultimodi= now(),"
                + " fs_cliente_usuultmod = ?"
                + " WHERE ps_cliente_nit= ? ";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, cliente.getSClienteNombre());
        insercion.setParameter(2, cliente.getDireccion());
        insercion.setParameter(3, cliente.getTelefono());
        insercion.setParameter(4, cliente.getContacto());
        insercion.setParameter(5, cliente.getCorreo());
        insercion.setParameter(6, cliente.getFsClienteUsuultmod().getPsUsuarioCodigo());
        insercion.setParameter(7, cliente.getClientePK().getPsClienteNit());
        insercion.executeUpdate();
        em.getTransaction().commit();
        return cliente;
    }

    public List<Cliente> encontrarCliente(String nit) {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM cliente where ps_cliente_nit = ?";
        try {
            Query q = em.createNativeQuery(consulta, Cliente.class);
            q.setParameter(1, nit);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cliente> encontrarTodosClientes() {
        EntityManager em = getEntityManager();
        String consulta = "SELECT * FROM cliente"
                + " order by s_cliente_nombre";
        try {
            Query q = em.createNativeQuery(consulta, Cliente.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarClienteCorreoFacturacion(Cliente cliente) {
        EntityManager em = getEntityManager();
        String insert = "UPDATE cliente SET s_cliente_correo_facturacion= ?  WHERE s_cliente_nombre= ?"
                + " and ps_cliente_nit=?";
        em.getTransaction().begin();
        Query insercion = em.createNativeQuery(insert);
        insercion.setParameter(1, cliente.getCorreo_facturacion());
        insercion.setParameter(2, cliente.getSClienteNombre());
        insercion.setParameter(3, cliente.getClientePK().getPsClienteNit());
        insercion.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Cliente> encontrarCliente(String filtros, List<Object> argumentos) {
        EntityManager em = getEntityManager();
        String consulta = " select * "
                + "from cliente WHERE";
        consulta += filtros;
        try {
            Query q = em.createNativeQuery(consulta, Cliente.class);
            int j = 1;
            for (int i = 0; i < argumentos.size(); i++) {
                q.setParameter(j, argumentos.get(i));
                j++;
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
