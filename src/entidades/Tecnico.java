/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnico.findAll", query = "SELECT t FROM Tecnico t"),
    @NamedQuery(name = "Tecnico.findByNombre", query = "SELECT t FROM Tecnico t WHERE t.nombre = :nombre")})
public class Tecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_tecnico_nombre", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "realizado")
    private List<Muestreo> muestreoList;
    @JoinColumn(name = "fs_tecnico_cargo", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo cargo;
    @OneToMany(mappedBy = "idResponsable")
    private List<Muestra> muestraList;

    public Tecnico() {
    }

    public Tecnico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Muestreo> getMuestreoList() {
        return muestreoList;
    }

    public void setMuestreoList(List<Muestreo> muestreoList) {
        this.muestreoList = muestreoList;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    @XmlTransient
    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnico)) {
            return false;
        }
        Tecnico other = (Tecnico) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tecnico[ nombre=" + nombre + " ]";
    }
    
}
