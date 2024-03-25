/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto"),
    @NamedQuery(name = "Proyecto.findByNombre", query = "SELECT p FROM Proyecto p WHERE p.nombre = :nombre")})
public class Proyecto implements Serializable {

    @OneToMany(mappedBy = "fiReporteHistoricoProyecto")
    private List<ReporteHistorico> reporteHistoricoList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_proyecto_id", nullable = false)
    private Integer idProyecto;
    @Column(name = "s_proyecto_nombre", length = 225)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private List<Muestreo> muestreoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private List<Reporte> reporteList;
    @OneToMany(mappedBy = "proyecto")
    private List<Muestra> muestraList;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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

    @XmlTransient
    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
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
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Proyecto[ idProyecto=" + idProyecto + " ]";
    }

    @XmlTransient
    public List<ReporteHistorico> getReporteHistoricoList() {
        return reporteHistoricoList;
    }

    public void setReporteHistoricoList(List<ReporteHistorico> reporteHistoricoList) {
        this.reporteHistoricoList = reporteHistoricoList;
    }
    
}
