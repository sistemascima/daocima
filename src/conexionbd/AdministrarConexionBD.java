/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jhon
 */
public class AdministrarConexionBD {
    public static final EntityManagerFactory conexionCima = Persistence.createEntityManagerFactory("DAOCimaPU");
          
}
