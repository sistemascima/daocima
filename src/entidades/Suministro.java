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
@Table(name = "suministro", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suministros.findAll", query = "SELECT s FROM Suministro s"),
    @NamedQuery(name = "Suministros.findBySuministroId", query = "SELECT s FROM Suministro s WHERE s.suministroId = :suministroId"),
    @NamedQuery(name = "Suministros.findByEquipoId", query = "SELECT s FROM Suministro s WHERE s.equipoId = :equipoId"),
    @NamedQuery(name = "Suministros.findByCartucho", query = "SELECT s FROM Suministro s WHERE s.cartucho = :cartucho"),
    @NamedQuery(name = "Suministros.findCartuchoByCantidadAndColor", query = "SELECT s FROM Suministro s WHERE s.cartucho = :cartucho AND s.color = :color"),
    @NamedQuery(name = "Suministros.findCartuchosAgotados", query = "SELECT s FROM Suministro s WHERE s.cantidad < 3"),
    @NamedQuery(name = "Suministros.findCartuchoDistinct", query = "SELECT DISTINCT c.cartucho FROM Suministro AS c"),
    @NamedQuery(name = "Suministros.findColorDistinct", query = "SELECT DISTINCT c.color FROM Suministro AS c WHERE c.cartucho = :cartucho"),
    @NamedQuery(name = "Suministros.findByColor", query = "SELECT s FROM Suministro s WHERE s.color = :color"),
    @NamedQuery(name = "Suministros.findByCantidad", query = "SELECT s FROM Suministro s WHERE s.cantidad = :cantidad")})
public class Suministro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_suministro_id", nullable = false)
    private Integer suministroId;
    @Column(name = "i_suministro_equipoid")
    private Integer equipoId;
    @Basic(optional = false)
    @Column(name = "s_suministro_cartucho", nullable = false, length = 255)
    private String cartucho;
    @Basic(optional = false)
    @Column(name = "s_suministro_color", nullable = false, length = 255)
    private String color;
    @Basic(optional = false)
    @Column(name = "i_suministro_cantidad", nullable = false)
    private int cantidad;

    public Suministro() {
    }

    public Suministro(Integer suministroId) {
        this.suministroId = suministroId;
    }

    public Suministro(Integer suministroId, String cartucho, String color, int cantidad) {
        this.suministroId = suministroId;
        this.cartucho = cartucho;
        this.color = color;
        this.cantidad = cantidad;
    }

    public Integer getSuministroId() {
        return suministroId;
    }

    public void setSuministroId(Integer suministroId) {
        this.suministroId = suministroId;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public String getCartucho() {
        return cartucho;
    }

    public void setCartucho(String cartucho) {
        this.cartucho = cartucho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suministroId != null ? suministroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suministro)) {
            return false;
        }
        Suministro other = (Suministro) object;
        if ((this.suministroId == null && other.suministroId != null) || (this.suministroId != null && !this.suministroId.equals(other.suministroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Suministros[ suministroId=" + suministroId + " ]";
    }
    
}
