/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "equipo_nuevo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoNuevo.findAll", query = "SELECT e FROM EquipoNuevo e"),
    @NamedQuery(name = "EquipoNuevo.findByType", query = "SELECT e FROM EquipoNuevo e WHERE e.type = :type"),
    @NamedQuery(name = "EquipoNuevo.findById", query = "SELECT e FROM EquipoNuevo e WHERE e.id = :id"),
    @NamedQuery(name = "EquipoNuevo.findByModelo", query = "SELECT e FROM EquipoNuevo e WHERE e.modelo = :modelo"),
    @NamedQuery(name = "EquipoNuevo.findBySerial", query = "SELECT e FROM EquipoNuevo e WHERE e.serial = :serial"),
    @NamedQuery(name = "EquipoNuevo.findByUbicacion", query = "SELECT e FROM EquipoNuevo e WHERE e.ubicacion = :s_equinuev_ubicacion"),
    @NamedQuery(name = "EquipoNuevo.findByDescripcion", query = "SELECT e FROM EquipoNuevo e WHERE e.descripcion = :s_equinuev_descripcion"),
    @NamedQuery(name = "EquipoNuevo.findByMarca", query = "SELECT e FROM EquipoNuevo e WHERE e.marca = :marca")})
public class EquipoNuevo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "s_equinuev_type", nullable = false, length = 255)
    private String type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_equinuev_id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "s_equinuev_modelo", nullable = false, length = 255)
    private String modelo;
    @Basic(optional = false)
    @Column(name = "s_equinuev_serial", nullable = false, length = 255)
    private String serial;
    @Basic(optional = false)
    @Column(name = "s_equinuev_ubicacion", nullable = false, length = 255)
    private String ubicacion;
    @Basic(optional = false)
    @Column(name = "s_equinuev_descripcion", nullable = false, length = 255)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "s_equinuev_marca", nullable = false, length = 255)
    private String marca;

    public EquipoNuevo() {
    }

    public EquipoNuevo(Integer id) {
        this.id = id;
    }

    public EquipoNuevo(Integer id, String type, String modelo, String serial, String ubicacion, String descripcion, String marca) {
        this.id = id;
        this.type = type;
        this.modelo = modelo;
        this.serial = serial;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.marca = marca;
    }

    public EquipoNuevo(String tipo, String modelo, String serial, String ubicacion, String descripcion, String marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoNuevo)) {
            return false;
        }
        EquipoNuevo other = (EquipoNuevo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Equipos nuevos[ id=" + id + " ]";
    }
    
}
