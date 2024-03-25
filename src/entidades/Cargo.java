/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByPsCargoCodigo", query = "SELECT c FROM Cargo c WHERE c.psCargoCodigo = :psCargoCodigo"),
    @NamedQuery(name = "Cargo.findBySCargoNombre", query = "SELECT c FROM Cargo c WHERE c.sCargoNombre = :sCargoNombre"),
    @NamedQuery(name = "Cargo.findBySCargoEstado", query = "SELECT c FROM Cargo c WHERE c.sCargoEstado = :sCargoEstado"),
    @NamedQuery(name = "Cargo.findByDCargoCreacion", query = "SELECT c FROM Cargo c WHERE c.dCargoCreacion = :dCargoCreacion"),
    @NamedQuery(name = "Cargo.findByDCargoUltimodi", query = "SELECT c FROM Cargo c WHERE c.dCargoUltimodi = :dCargoUltimodi")})
public class Cargo implements Serializable {
    @OneToMany(mappedBy = "fsMatranalCoorlabo")
    private Collection<MatrizAnalisis> matrizAnalisisCollection;
    @OneToMany(mappedBy = "fsDetsolcomCargreci")
    private Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsServinteCooracep")
    private Collection<ServicioInterno> servicioInternoCollection;
    @OneToMany(mappedBy = "fsSeprsocoCargsele")
    private Collection<SeleProvSoliComp> seleProvSoliCompCollection;
    @OneToMany(mappedBy = "fsOrdecompCargrevi")
    private Collection<OrdenCompra> ordenCompraCollection;
    @OneToMany(mappedBy = "fsOrdecompCargapru")
    private Collection<OrdenCompra> ordenCompraCollection1;
    @OneToMany(mappedBy = "fsEvalprovCargeval")
    private Collection<EvaluacionProveedor> evaluacionProveedorCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_cargo_codigo")
    private String psCargoCodigo;
    @Basic(optional = false)
    @Column(name = "s_cargo_nombre")
    private String sCargoNombre;
    @Basic(optional = false)
    @Column(name = "s_cargo_estado")
    private String sCargoEstado;
    @Basic(optional = false)
    @Column(name = "d_cargo_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCargoCreacion;
    @Column(name = "d_cargo_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCargoUltimodi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<AccesoDocumento> accesoDocumentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<UsuarioCargo> usuarioCargoCollection;
    @OneToMany(mappedBy = "fsDocumentCargrevi")
    private Collection<Documento> documentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsDocumentCargelab")
    private Collection<Documento> documentoCollection1;
    @OneToMany(mappedBy = "fsDocumentCargapru")
    private Collection<Documento> documentoCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargo")
    private Collection<ResponsableDocumento> responsableDocumentoCollection;
    @JoinColumn(name = "fs_cargo_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsCargoUsuultmod;
    @JoinColumn(name = "fs_cargo_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsCargoUsuacrea;
    @OneToMany(mappedBy = "fsCargoSuperior")
    private Collection<Cargo> cargoCollection;
    @JoinColumn(name = "fs_cargo_superior", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsCargoSuperior;
    @JoinColumn(name = "fs_cargo_departamento", referencedColumnName = "ps_departam_codigo")
    @ManyToOne
    private Departamento fsCargoDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsSolicompCargsoli")
    private Collection<SolicitudCompra> solicitudCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsSolicompCargapru")
    private Collection<SolicitudCompra> solicitudCompraCollection1;
    @OneToMany(mappedBy = "cargo")
    private List<Tecnico> tecnicoList;


    public Cargo() {
    }

    public Cargo(String psCargoCodigo) {
        this.psCargoCodigo = psCargoCodigo;
    }

    public Cargo(String psCargoCodigo, String sCargoNombre, String sCargoEstado, Date dCargoCreacion) {
        this.psCargoCodigo = psCargoCodigo;
        this.sCargoNombre = sCargoNombre;
        this.sCargoEstado = sCargoEstado;
        this.dCargoCreacion = dCargoCreacion;
    }

    public Cargo(String psCargoCodigo, String sCargoNombre, String sCargoEstado, Date dCargoCreacion, SUsuario fsCargoUsuacrea, Cargo fsCargoSuperior, Departamento fsCargoDepartamento) {
        this.psCargoCodigo = psCargoCodigo;
        this.sCargoNombre = sCargoNombre;
        this.sCargoEstado = sCargoEstado;
        this.dCargoCreacion = dCargoCreacion;
        this.fsCargoUsuacrea = fsCargoUsuacrea;
        this.fsCargoSuperior = fsCargoSuperior;
        this.fsCargoDepartamento = fsCargoDepartamento;
    }
    

    public String getPsCargoCodigo() {
        return psCargoCodigo;
    }

    public void setPsCargoCodigo(String psCargoCodigo) {
        this.psCargoCodigo = psCargoCodigo;
    }

    public String getSCargoNombre() {
        return sCargoNombre;
    }

    public void setSCargoNombre(String sCargoNombre) {
        this.sCargoNombre = sCargoNombre;
    }

    public String getSCargoEstado() {
        return sCargoEstado;
    }

    public void setSCargoEstado(String sCargoEstado) {
        this.sCargoEstado = sCargoEstado;
    }

    public Date getDCargoCreacion() {
        return dCargoCreacion;
    }

    public void setDCargoCreacion(Date dCargoCreacion) {
        this.dCargoCreacion = dCargoCreacion;
    }

    public Date getDCargoUltimodi() {
        return dCargoUltimodi;
    }

    public void setDCargoUltimodi(Date dCargoUltimodi) {
        this.dCargoUltimodi = dCargoUltimodi;
    }

    @XmlTransient
    public Collection<AccesoDocumento> getAccesoDocumentoCollection() {
        return accesoDocumentoCollection;
    }

    public void setAccesoDocumentoCollection(Collection<AccesoDocumento> accesoDocumentoCollection) {
        this.accesoDocumentoCollection = accesoDocumentoCollection;
    }

    @XmlTransient
    public Collection<UsuarioCargo> getUsuarioCargoCollection() {
        return usuarioCargoCollection;
    }

    public void setUsuarioCargoCollection(Collection<UsuarioCargo> usuarioCargoCollection) {
        this.usuarioCargoCollection = usuarioCargoCollection;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection1() {
        return documentoCollection1;
    }

    public void setDocumentoCollection1(Collection<Documento> documentoCollection1) {
        this.documentoCollection1 = documentoCollection1;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection2() {
        return documentoCollection2;
    }

    public void setDocumentoCollection2(Collection<Documento> documentoCollection2) {
        this.documentoCollection2 = documentoCollection2;
    }

    @XmlTransient
    public Collection<ResponsableDocumento> getResponsableDocumentoCollection() {
        return responsableDocumentoCollection;
    }

    public void setResponsableDocumentoCollection(Collection<ResponsableDocumento> responsableDocumentoCollection) {
        this.responsableDocumentoCollection = responsableDocumentoCollection;
    }

    public SUsuario getFsCargoUsuultmod() {
        return fsCargoUsuultmod;
    }

    public void setFsCargoUsuultmod(SUsuario fsCargoUsuultmod) {
        this.fsCargoUsuultmod = fsCargoUsuultmod;
    }

    public SUsuario getFsCargoUsuacrea() {
        return fsCargoUsuacrea;
    }

    public void setFsCargoUsuacrea(SUsuario fsCargoUsuacrea) {
        this.fsCargoUsuacrea = fsCargoUsuacrea;
    }

    @XmlTransient
    public Collection<Cargo> getCargoCollection() {
        return cargoCollection;
    }

    public void setCargoCollection(Collection<Cargo> cargoCollection) {
        this.cargoCollection = cargoCollection;
    }

    public Cargo getFsCargoSuperior() {
        return fsCargoSuperior;
    }

    public void setFsCargoSuperior(Cargo fsCargoSuperior) {
        this.fsCargoSuperior = fsCargoSuperior;
    }

    public Departamento getFsCargoDepartamento() {
        return fsCargoDepartamento;
    }

    public void setFsCargoDepartamento(Departamento fsCargoDepartamento) {
        this.fsCargoDepartamento = fsCargoDepartamento;
    }

    @XmlTransient
    public Collection<SolicitudCompra> getSolicitudCompraCollection() {
        return solicitudCompraCollection;
    }

    public void setSolicitudCompraCollection(Collection<SolicitudCompra> solicitudCompraCollection) {
        this.solicitudCompraCollection = solicitudCompraCollection;
    }

    @XmlTransient
    public Collection<SolicitudCompra> getSolicitudCompraCollection1() {
        return solicitudCompraCollection1;
    }

    public void setSolicitudCompraCollection1(Collection<SolicitudCompra> solicitudCompraCollection1) {
        this.solicitudCompraCollection1 = solicitudCompraCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psCargoCodigo != null ? psCargoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.psCargoCodigo == null && other.psCargoCodigo != null) || (this.psCargoCodigo != null && !this.psCargoCodigo.equals(other.psCargoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sCargoNombre;
    }

    @XmlTransient
    public Collection<EvaluacionProveedor> getEvaluacionProveedorCollection() {
        return evaluacionProveedorCollection;
    }

    public void setEvaluacionProveedorCollection(Collection<EvaluacionProveedor> evaluacionProveedorCollection) {
        this.evaluacionProveedorCollection = evaluacionProveedorCollection;
    }

    @XmlTransient
    public Collection<OrdenCompra> getOrdenCompraCollection() {
        return ordenCompraCollection;
    }

    public void setOrdenCompraCollection(Collection<OrdenCompra> ordenCompraCollection) {
        this.ordenCompraCollection = ordenCompraCollection;
    }

    @XmlTransient
    public Collection<OrdenCompra> getOrdenCompraCollection1() {
        return ordenCompraCollection1;
    }

    public void setOrdenCompraCollection1(Collection<OrdenCompra> ordenCompraCollection1) {
        this.ordenCompraCollection1 = ordenCompraCollection1;
    }

    @XmlTransient
    public Collection<SeleProvSoliComp> getSeleProvSoliCompCollection() {
        return seleProvSoliCompCollection;
    }

    public void setSeleProvSoliCompCollection(Collection<SeleProvSoliComp> seleProvSoliCompCollection) {
        this.seleProvSoliCompCollection = seleProvSoliCompCollection;
    }

    @XmlTransient
    public Collection<ServicioInterno> getServicioInternoCollection() {
        return servicioInternoCollection;
    }

    public void setServicioInternoCollection(Collection<ServicioInterno> servicioInternoCollection) {
        this.servicioInternoCollection = servicioInternoCollection;
    }

    @XmlTransient
    public Collection<DetalleSolicitudCompra> getDetalleSolicitudCompraCollection() {
        return detalleSolicitudCompraCollection;
    }

    public void setDetalleSolicitudCompraCollection(Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollection) {
        this.detalleSolicitudCompraCollection = detalleSolicitudCompraCollection;
    }

    @XmlTransient
    public Collection<MatrizAnalisis> getMatrizAnalisisCollection() {
        return matrizAnalisisCollection;
    }

    public void setMatrizAnalisisCollection(Collection<MatrizAnalisis> matrizAnalisisCollection) {
        this.matrizAnalisisCollection = matrizAnalisisCollection;
    }
    
    @XmlTransient
    public List<Tecnico> getTecnicoList() {
        return tecnicoList;
    }

    public void setTecnicoList(List<Tecnico> tecnicoList) {
        this.tecnicoList = tecnicoList;
    }
    
}
