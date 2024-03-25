/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "detalle_orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrdenCompra.findAll", query = "SELECT d FROM DetalleOrdenCompra d"),
    @NamedQuery(name = "DetalleOrdenCompra.findByPfiDetordcomOrdecomp", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.detalleOrdenCompraPK.pfiDetordcomOrdecomp = :pfiDetordcomOrdecomp"),
    @NamedQuery(name = "DetalleOrdenCompra.findByPfiDetordcomSolicomp", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.detalleOrdenCompraPK.pfiDetordcomSolicomp = :pfiDetordcomSolicomp"),
    @NamedQuery(name = "DetalleOrdenCompra.findByPfiDetordcomItem", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.detalleOrdenCompraPK.pfiDetordcomItem = :pfiDetordcomItem"),
    @NamedQuery(name = "DetalleOrdenCompra.findByIDetordcomCantidad", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.iDetordcomCantidad = :iDetordcomCantidad"),
    @NamedQuery(name = "DetalleOrdenCompra.findByDbDetordcomValounit", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.dbDetordcomValounit = :dbDetordcomValounit"),
    @NamedQuery(name = "DetalleOrdenCompra.findByDDetordcomCreacion", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.dDetordcomCreacion = :dDetordcomCreacion"),
    @NamedQuery(name = "DetalleOrdenCompra.findByDDetordcomUltimodi", query = "SELECT d FROM DetalleOrdenCompra d WHERE d.dDetordcomUltimodi = :dDetordcomUltimodi")})
public class DetalleOrdenCompra implements Serializable {

    @Column(name = "i_detorcom_cantidad_recibida")
    private Integer iDetorcomCantidadRecibida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleOrdenCompra")
    private List<Cantidad> cantidadList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleOrdenCompraPK detalleOrdenCompraPK;
    @Basic(optional = false)
    @Column(name = "i_detordcom_cantidad")
    private int iDetordcomCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_detordcom_valounit")
    private BigDecimal dbDetordcomValounit;
    @Basic(optional = false)
    @Column(name = "d_detordcom_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetordcomCreacion;
    @Column(name = "d_detordcom_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetordcomUltimodi;
     @Column(name = "d_detordcom_fecha_prox_recepecion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date d_detordcom_fecha_prox_recepecion;
    @JoinColumns({
        @JoinColumn(name = "pfi_detordcom_solicomp", referencedColumnName = "pfi_detsolcom_solicomp", insertable = false, updatable = false),
        @JoinColumn(name = "pfi_detordcom_item", referencedColumnName = "pi_detsolcom_item", insertable = false, updatable = false)})
    @OneToOne(fetch =  FetchType.LAZY)
    private DetalleSolicitudCompra detalleSolicitudCompra;
    @JoinColumn(name = "pfi_detordcom_ordecomp", referencedColumnName = "pi_ordecomp_numero", insertable = false, updatable = false)
    @ManyToOne(fetch =  FetchType.LAZY)
    private OrdenCompra ordenCompra;
    @JoinColumn(name = "fs_detordcom_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch =  FetchType.LAZY)
    private SUsuario fsDetordcomUsuultmod;
    @JoinColumn(name = "fs_detordcom_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch =  FetchType.LAZY)
    private SUsuario fsDetordcomUsuacrea;

    public DetalleSolicitudCompra getDetalleSolicitudCompra() {
        return detalleSolicitudCompra;
    }

    public void setDetalleSolicitudCompra(DetalleSolicitudCompra detalleSolicitudCompra) {
        this.detalleSolicitudCompra = detalleSolicitudCompra;
    }

    public DetalleOrdenCompra() {
    }

    public DetalleOrdenCompra(DetalleOrdenCompraPK detalleOrdenCompraPK) {
        this.detalleOrdenCompraPK = detalleOrdenCompraPK;
    }

    public DetalleOrdenCompra(DetalleOrdenCompraPK detalleOrdenCompraPK, int iDetordcomCantidad, Date dDetordcomCreacion) {
        this.detalleOrdenCompraPK = detalleOrdenCompraPK;
        this.iDetordcomCantidad = iDetordcomCantidad;
        this.dDetordcomCreacion = dDetordcomCreacion;
    }

    public DetalleOrdenCompra(int pfiDetordcomOrdecomp, int pfiDetordcomSolicomp, int pfiDetordcomItem) {
        this.detalleOrdenCompraPK = new DetalleOrdenCompraPK(pfiDetordcomOrdecomp, pfiDetordcomSolicomp, pfiDetordcomItem);
    }

    public DetalleOrdenCompraPK getDetalleOrdenCompraPK() {
        return detalleOrdenCompraPK;
    }

    public void setDetalleOrdenCompraPK(DetalleOrdenCompraPK detalleOrdenCompraPK) {
        this.detalleOrdenCompraPK = detalleOrdenCompraPK;
    }

    public int getIDetordcomCantidad() {
        return iDetordcomCantidad;
    }

    public void setIDetordcomCantidad(int iDetordcomCantidad) {
        this.iDetordcomCantidad = iDetordcomCantidad;
    }

    public BigDecimal getDbDetordcomValounit() {
        return dbDetordcomValounit;
    }

    public void setDbDetordcomValounit(BigDecimal dbDetordcomValounit) {
        this.dbDetordcomValounit = dbDetordcomValounit;
    }

    public Date getDDetordcomCreacion() {
        return dDetordcomCreacion;
    }

    public void setDDetordcomCreacion(Date dDetordcomCreacion) {
        this.dDetordcomCreacion = dDetordcomCreacion;
    }

    public Date getDDetordcomUltimodi() {
        return dDetordcomUltimodi;
    }

    public void setDDetordcomUltimodi(Date dDetordcomUltimodi) {
        this.dDetordcomUltimodi = dDetordcomUltimodi;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public SUsuario getFsDetordcomUsuultmod() {
        return fsDetordcomUsuultmod;
    }

    public void setFsDetordcomUsuultmod(SUsuario fsDetordcomUsuultmod) {
        this.fsDetordcomUsuultmod = fsDetordcomUsuultmod;
    }

    public SUsuario getFsDetordcomUsuacrea() {
        return fsDetordcomUsuacrea;
    }

    public void setFsDetordcomUsuacrea(SUsuario fsDetordcomUsuacrea) {
        this.fsDetordcomUsuacrea = fsDetordcomUsuacrea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleOrdenCompraPK != null ? detalleOrdenCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenCompra)) {
            return false;
        }
        DetalleOrdenCompra other = (DetalleOrdenCompra) object;
        if ((this.detalleOrdenCompraPK == null && other.detalleOrdenCompraPK != null) || (this.detalleOrdenCompraPK != null && !this.detalleOrdenCompraPK.equals(other.detalleOrdenCompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleOrdenCompra[ detalleOrdenCompraPK=" + detalleOrdenCompraPK + " ]";
    }

    public Integer getIDetorcomCantidadRecibida() {
        return iDetorcomCantidadRecibida;
    }

    public void setIDetorcomCantidadRecibida(Integer iDetorcomCantidadRecibida) {
        this.iDetorcomCantidadRecibida = iDetorcomCantidadRecibida;
    }

    @XmlTransient
    public List<Cantidad> getCantidadList() {
        return cantidadList;
    }

    public void setCantidadList(List<Cantidad> cantidadList) {
        this.cantidadList = cantidadList;
    }

    public Date getD_detordcom_fecha_prox_recepecion() {
        return d_detordcom_fecha_prox_recepecion;
    }

    public void setD_detordcom_fecha_prox_recepecion(Date d_detordcom_fecha_prox_recepecion) {
        this.d_detordcom_fecha_prox_recepecion = d_detordcom_fecha_prox_recepecion;
    }
    
    
    
}
