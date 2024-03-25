/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByPsDepartamCodigo", query = "SELECT d FROM Departamento d WHERE d.psDepartamCodigo = :psDepartamCodigo"),
    @NamedQuery(name = "Departamento.findBySDepartamNombre", query = "SELECT d FROM Departamento d WHERE d.sDepartamNombre = :sDepartamNombre"),
    @NamedQuery(name = "Departamento.findBySDepartamEstado", query = "SELECT d FROM Departamento d WHERE d.sDepartamEstado = :sDepartamEstado"),
    @NamedQuery(name = "Departamento.findByDDepartamCreacion", query = "SELECT d FROM Departamento d WHERE d.dDepartamCreacion = :dDepartamCreacion"),
    @NamedQuery(name = "Departamento.findByDDepartamUltimodi", query = "SELECT d FROM Departamento d WHERE d.dDepartamUltimodi = :dDepartamUltimodi")})
public class Departamento implements Serializable {
    @Basic(optional = false)
    @Column(name = "s_departam_estado")
    private Character sDepartamEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsServinteDepartamento")
    private Collection<ServicioInterno> servicioInternoCollection;
    @JoinColumn(name = "fs_departam_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDepartamUsuultmod;
    @JoinColumn(name = "fs_departam_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsDepartamUsuacrea;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_departam_codigo")
    private String psDepartamCodigo;
    @Basic(optional = false)
    @Column(name = "s_departam_nombre")
    private String sDepartamNombre;
    @Basic(optional = false)
    @Column(name = "d_departam_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDepartamCreacion;
    @Column(name = "d_departam_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDepartamUltimodi;
    @OneToMany(mappedBy = "fsCargoDepartamento")
    private Collection<Cargo> cargoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsSolicompDepartamento")
    private Collection<SolicitudCompra> solicitudCompraCollection;

    public Departamento() {
    }

    public Departamento(String psDepartamCodigo) {
        this.psDepartamCodigo = psDepartamCodigo;
    }

    public Departamento(String psDepartamCodigo, String sDepartamNombre, char sDepartamEstado, Date dDepartamCreacion) {
        this.psDepartamCodigo = psDepartamCodigo;
        this.sDepartamNombre = sDepartamNombre;
        this.sDepartamEstado = sDepartamEstado;
        this.dDepartamCreacion = dDepartamCreacion;
    }

    public String getPsDepartamCodigo() {
        return psDepartamCodigo;
    }

    public void setPsDepartamCodigo(String psDepartamCodigo) {
        this.psDepartamCodigo = psDepartamCodigo;
    }

    public String getSDepartamNombre() {
        return sDepartamNombre;
    }

    public void setSDepartamNombre(String sDepartamNombre) {
        this.sDepartamNombre = sDepartamNombre;
    }

    public char getSDepartamEstado() {
        return sDepartamEstado;
    }

    public void setSDepartamEstado(char sDepartamEstado) {
        this.sDepartamEstado = sDepartamEstado;
    }

    public Date getDDepartamCreacion() {
        return dDepartamCreacion;
    }

    public void setDDepartamCreacion(Date dDepartamCreacion) {
        this.dDepartamCreacion = dDepartamCreacion;
    }

    public Date getDDepartamUltimodi() {
        return dDepartamUltimodi;
    }

    public void setDDepartamUltimodi(Date dDepartamUltimodi) {
        this.dDepartamUltimodi = dDepartamUltimodi;
    }

    @XmlTransient
    public Collection<Cargo> getCargoCollection() {
        return cargoCollection;
    }

    public void setCargoCollection(Collection<Cargo> cargoCollection) {
        this.cargoCollection = cargoCollection;
    }

    @XmlTransient
    public Collection<SolicitudCompra> getSolicitudCompraCollection() {
        return solicitudCompraCollection;
    }

    public void setSolicitudCompraCollection(Collection<SolicitudCompra> solicitudCompraCollection) {
        this.solicitudCompraCollection = solicitudCompraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psDepartamCodigo != null ? psDepartamCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.psDepartamCodigo == null && other.psDepartamCodigo != null) || (this.psDepartamCodigo != null && !this.psDepartamCodigo.equals(other.psDepartamCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sDepartamNombre;
    }

    public SUsuario getFsDepartamUsuultmod() {
        return fsDepartamUsuultmod;
    }

    public void setFsDepartamUsuultmod(SUsuario fsDepartamUsuultmod) {
        this.fsDepartamUsuultmod = fsDepartamUsuultmod;
    }

    public SUsuario getFsDepartamUsuacrea() {
        return fsDepartamUsuacrea;
    }

    public void setFsDepartamUsuacrea(SUsuario fsDepartamUsuacrea) {
        this.fsDepartamUsuacrea = fsDepartamUsuacrea;
    }

    @XmlTransient
    public Collection<ServicioInterno> getServicioInternoCollection() {
        return servicioInternoCollection;
    }

    public void setServicioInternoCollection(Collection<ServicioInterno> servicioInternoCollection) {
        this.servicioInternoCollection = servicioInternoCollection;
    }

    

    public void setSDepartamEstado(Character sDepartamEstado) {
        this.sDepartamEstado = sDepartamEstado;
    }
    
}
