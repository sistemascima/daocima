/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "detalle_solicitud_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitudCompra.findAll", query = "SELECT d FROM DetalleSolicitudCompra d"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByPfiDetsolcomSolicomp", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.detalleSolicitudCompraPK.pfiDetsolcomSolicomp = :pfiDetsolcomSolicomp"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByPiDetsolcomItem", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.detalleSolicitudCompraPK.piDetsolcomItem = :piDetsolcomItem"),
    @NamedQuery(name = "DetalleSolicitudCompra.findBySDetsolcomEspecific", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.sDetsolcomEspecific = :sDetsolcomEspecific"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByIDetsolcomCantidad", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.iDetsolcomCantidad = :iDetsolcomCantidad"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByDDetsolcomRecepcion", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.dDetsolcomRecepcion = :dDetsolcomRecepcion"),
    @NamedQuery(name = "DetalleSolicitudCompra.findBySDetsolcomRecibido", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.sDetsolcomRecibido = :sDetsolcomRecibido"),
    @NamedQuery(name = "DetalleSolicitudCompra.findBySDetsolcomObsereci", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.sDetsolcomObsereci = :sDetsolcomObsereci"),
    @NamedQuery(name = "DetalleSolicitudCompra.findBySDetsolcomObsesele", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.sDetsolcomObsesele = :sDetsolcomObsesele"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByDDetsolcomCreacion", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.dDetsolcomCreacion = :dDetsolcomCreacion"),
    @NamedQuery(name = "DetalleSolicitudCompra.findByDDetsolcomUltimodi", query = "SELECT d FROM DetalleSolicitudCompra d WHERE d.dDetsolcomUltimodi = :dDetsolcomUltimodi")})
public class DetalleSolicitudCompra implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_detsolcom_valounit")
    private BigDecimal dbDetsolcomValounit;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleSolicitudCompraPK detalleSolicitudCompraPK;
    @Basic(optional = false)
    @Column(name = "s_detsolcom_especific")
    private String sDetsolcomEspecific;
    @Basic(optional = false)
    @Column(name = "i_detsolcom_cantidad")
    private int iDetsolcomCantidad;
    @Column(name = "d_detsolcom_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetsolcomRecepcion;
    @Column(name = "s_detsolcom_recibido")
    private Character sDetsolcomRecibido;
    @Column(name = "s_detsolcom_obsereci")
    private String sDetsolcomObsereci;
    @Column(name = "s_detsolcom_obsesele")
    private String sDetsolcomObsesele;
    @Basic(optional = false)
    @Column(name = "d_detsolcom_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetsolcomCreacion;
    @Column(name = "d_detsolcom_ultimodi")
    private String dDetsolcomUltimodi;
    @JoinColumn(name = "fs_detsolcom_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDetsolcomUsuultmod;
    @JoinColumn(name = "fs_detsolcom_usuareci", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDetsolcomUsuareci;
    @JoinColumn(name = "fs_detsolcom_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsDetsolcomUsuacrea;
    @JoinColumn(name = "pfi_detsolcom_solicomp", referencedColumnName = "pi_solicomp_consecutivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SolicitudCompra solicitudCompra;
    @JoinColumn(name = "fs_detsolcom_provsele", referencedColumnName = "ps_proveedor_nit")
    @ManyToOne
    private Proveedor fsDetsolcomProvsele;
    @JoinColumn(name = "fs_detsolcom_cargreci", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsDetsolcomCargreci;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "detalleSolicitudCompra")
    private DetalleOrdenCompra detalleOrdenCompra;

    public DetalleSolicitudCompra() {
    }

    public DetalleSolicitudCompra(DetalleSolicitudCompraPK detalleSolicitudCompraPK) {
        this.detalleSolicitudCompraPK = detalleSolicitudCompraPK;
    }

    public DetalleSolicitudCompra(DetalleSolicitudCompraPK detalleSolicitudCompraPK, String sDetsolcomEspecific, int iDetsolcomCantidad, Date dDetsolcomCreacion) {
        this.detalleSolicitudCompraPK = detalleSolicitudCompraPK;
        this.sDetsolcomEspecific = sDetsolcomEspecific;
        this.iDetsolcomCantidad = iDetsolcomCantidad;
        this.dDetsolcomCreacion = dDetsolcomCreacion;
    }

    public DetalleSolicitudCompra(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
        this.detalleSolicitudCompraPK = new DetalleSolicitudCompraPK(pfiDetsolcomSolicomp, piDetsolcomItem);
    }

    public DetalleSolicitudCompraPK getDetalleSolicitudCompraPK() {
        return detalleSolicitudCompraPK;
    }

    public void setDetalleSolicitudCompraPK(DetalleSolicitudCompraPK detalleSolicitudCompraPK) {
        this.detalleSolicitudCompraPK = detalleSolicitudCompraPK;
    }

    public String getSDetsolcomEspecific() {
        return sDetsolcomEspecific;
    }

    public void setSDetsolcomEspecific(String sDetsolcomEspecific) {
        this.sDetsolcomEspecific = sDetsolcomEspecific;
    }

    public int getIDetsolcomCantidad() {
        return iDetsolcomCantidad;
    }

    public void setIDetsolcomCantidad(int iDetsolcomCantidad) {
        this.iDetsolcomCantidad = iDetsolcomCantidad;
    }

    public Date getDDetsolcomRecepcion() {
        return dDetsolcomRecepcion;
    }

    public void setDDetsolcomRecepcion(Date dDetsolcomRecepcion) {
        this.dDetsolcomRecepcion = dDetsolcomRecepcion;
    }

    public Character getSDetsolcomRecibido() {
        return sDetsolcomRecibido;
    }

    public void setSDetsolcomRecibido(Character sDetsolcomRecibido) {
        this.sDetsolcomRecibido = sDetsolcomRecibido;
    }

    public String getSDetsolcomObsereci() {
        return sDetsolcomObsereci;
    }

    public void setSDetsolcomObsereci(String sDetsolcomObsereci) {
        this.sDetsolcomObsereci = sDetsolcomObsereci;
    }

    public String getSDetsolcomObsesele() {
        return sDetsolcomObsesele;
    }

    public void setSDetsolcomObsesele(String sDetsolcomObsesele) {
        this.sDetsolcomObsesele = sDetsolcomObsesele;
    }

    public Date getDDetsolcomCreacion() {
        return dDetsolcomCreacion;
    }

    public void setDDetsolcomCreacion(Date dDetsolcomCreacion) {
        this.dDetsolcomCreacion = dDetsolcomCreacion;
    }

    public String getDDetsolcomUltimodi() {
        return dDetsolcomUltimodi;
    }

    public void setDDetsolcomUltimodi(String dDetsolcomUltimodi) {
        this.dDetsolcomUltimodi = dDetsolcomUltimodi;
    }

    public SUsuario getFsDetsolcomUsuultmod() {
        return fsDetsolcomUsuultmod;
    }

    public void setFsDetsolcomUsuultmod(SUsuario fsDetsolcomUsuultmod) {
        this.fsDetsolcomUsuultmod = fsDetsolcomUsuultmod;
    }

    public SUsuario getFsDetsolcomUsuareci() {
        return fsDetsolcomUsuareci;
    }

    public void setFsDetsolcomUsuareci(SUsuario fsDetsolcomUsuareci) {
        this.fsDetsolcomUsuareci = fsDetsolcomUsuareci;
    }

    public SUsuario getFsDetsolcomUsuacrea() {
        return fsDetsolcomUsuacrea;
    }

    public void setFsDetsolcomUsuacrea(SUsuario fsDetsolcomUsuacrea) {
        this.fsDetsolcomUsuacrea = fsDetsolcomUsuacrea;
    }

    public SolicitudCompra getSolicitudCompra() {
        return solicitudCompra;
    }

    public void setSolicitudCompra(SolicitudCompra solicitudCompra) {
        this.solicitudCompra = solicitudCompra;
    }

    public Proveedor getFsDetsolcomProvsele() {
        return fsDetsolcomProvsele;
    }

    public void setFsDetsolcomProvsele(Proveedor fsDetsolcomProvsele) {
        this.fsDetsolcomProvsele = fsDetsolcomProvsele;
    }

    public Cargo getFsDetsolcomCargreci() {
        return fsDetsolcomCargreci;
    }

    public void setFsDetsolcomCargreci(Cargo fsDetsolcomCargreci) {
        this.fsDetsolcomCargreci = fsDetsolcomCargreci;
    }

    public DetalleOrdenCompra getDetalleOrdenCompra() {
        return detalleOrdenCompra;
    }

    public void setDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
        this.detalleOrdenCompra = detalleOrdenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleSolicitudCompraPK != null ? detalleSolicitudCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSolicitudCompra)) {
            return false;
        }
        DetalleSolicitudCompra other = (DetalleSolicitudCompra) object;
        if ((this.detalleSolicitudCompraPK == null && other.detalleSolicitudCompraPK != null) || (this.detalleSolicitudCompraPK != null && !this.detalleSolicitudCompraPK.equals(other.detalleSolicitudCompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleSolicitudCompra[ detalleSolicitudCompraPK=" + detalleSolicitudCompraPK + " ]";
    }

    public BigDecimal getDbDetsolcomValounit() {
        return dbDetsolcomValounit;
    }

    public void setDbDetsolcomValounit(BigDecimal dbDetsolcomValounit) {
        this.dbDetsolcomValounit = dbDetsolcomValounit;
    }
    
}
