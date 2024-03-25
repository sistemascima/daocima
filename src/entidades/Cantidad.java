/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "cantidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cantidad.findAll", query = "SELECT c FROM Cantidad c"),
    @NamedQuery(name = "Cantidad.findByPiCantidadId", query = "SELECT c FROM Cantidad c WHERE c.cantidadPK.piCantidadId = :piCantidadId"),
    @NamedQuery(name = "Cantidad.findByPfiCantidadSolicomp", query = "SELECT c FROM Cantidad c WHERE c.cantidadPK.pfiCantidadSolicomp = :pfiCantidadSolicomp"),
    @NamedQuery(name = "Cantidad.findByPfiCantidadItem", query = "SELECT c FROM Cantidad c WHERE c.cantidadPK.pfiCantidadItem = :pfiCantidadItem"),
    @NamedQuery(name = "Cantidad.findByICantidadCantidad", query = "SELECT c FROM Cantidad c WHERE c.iCantidadCantidad = :iCantidadCantidad"),
    @NamedQuery(name = "Cantidad.findByDCantidadFechcreacion", query = "SELECT c FROM Cantidad c WHERE c.dCantidadFechcreacion = :dCantidadFechcreacion"),
    @NamedQuery(name = "Cantidad.findByDCantidadFechmodificacion", query = "SELECT c FROM Cantidad c WHERE c.dCantidadFechmodificacion = :dCantidadFechmodificacion"),
    @NamedQuery(name = "Cantidad.findBySCantidadObservacion", query = "SELECT c FROM Cantidad c WHERE c.sCantidadObservacion = :sCantidadObservacion")})
public class Cantidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CantidadPK cantidadPK;
    @Column(name = "i_cantidad_cantidad")
    private Integer iCantidadCantidad;
    @Column(name = "d_cantidad_fechcreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCantidadFechcreacion;
    @Column(name = "d_cantidad_fechmodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCantidadFechmodificacion;
    @Column(name = "s_cantidad_observacion", length = 45)
    private String sCantidadObservacion;
    @JoinColumns({
        @JoinColumn(name = "pfi_cantidad_solicomp", referencedColumnName = "pfi_detordcom_solicomp", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "pfi_cantidad_item", referencedColumnName = "pfi_detordcom_item", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "pfi_cantidad_ordecomp", referencedColumnName = "pfi_detordcom_ordecomp")})
    @ManyToOne(fetch= FetchType.LAZY)
    private DetalleOrdenCompra detalleOrdenCompra;
    @JoinColumn(name = "fs_cantidad_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private SUsuario fsCantidadUsuacreacion;
    @JoinColumn(name = "fs_cantidad_usuamodificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private SUsuario fsCantidadUsuamodificacion;

    public Cantidad() {
    }

    public Cantidad(CantidadPK cantidadPK) {
        this.cantidadPK = cantidadPK;
    }

    public Cantidad(int piCantidadId, int pfiCantidadSolicomp, int pfiCantidadItem) {
        this.cantidadPK = new CantidadPK(piCantidadId, pfiCantidadSolicomp, pfiCantidadItem);
    }

    public CantidadPK getCantidadPK() {
        return cantidadPK;
    }

    public void setCantidadPK(CantidadPK cantidadPK) {
        this.cantidadPK = cantidadPK;
    }

    public Integer getICantidadCantidad() {
        return iCantidadCantidad;
    }

    public void setICantidadCantidad(Integer iCantidadCantidad) {
        this.iCantidadCantidad = iCantidadCantidad;
    }

    public Date getDCantidadFechcreacion() {
        return dCantidadFechcreacion;
    }

    public void setDCantidadFechcreacion(Date dCantidadFechcreacion) {
        this.dCantidadFechcreacion = dCantidadFechcreacion;
    }

    public Date getDCantidadFechmodificacion() {
        return dCantidadFechmodificacion;
    }

    public void setDCantidadFechmodificacion(Date dCantidadFechmodificacion) {
        this.dCantidadFechmodificacion = dCantidadFechmodificacion;
    }

    public String getSCantidadObservacion() {
        return sCantidadObservacion;
    }

    public void setSCantidadObservacion(String sCantidadObservacion) {
        this.sCantidadObservacion = sCantidadObservacion;
    }

    public DetalleOrdenCompra getDetalleOrdenCompra() {
        return detalleOrdenCompra;
    }

    public void setDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra) {
        this.detalleOrdenCompra = detalleOrdenCompra;
    }

    public SUsuario getFsCantidadUsuacreacion() {
        return fsCantidadUsuacreacion;
    }

    public void setFsCantidadUsuacreacion(SUsuario fsCantidadUsuacreacion) {
        this.fsCantidadUsuacreacion = fsCantidadUsuacreacion;
    }

    public SUsuario getFsCantidadUsuamodificacion() {
        return fsCantidadUsuamodificacion;
    }

    public void setFsCantidadUsuamodificacion(SUsuario fsCantidadUsuamodificacion) {
        this.fsCantidadUsuamodificacion = fsCantidadUsuamodificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cantidadPK != null ? cantidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cantidad)) {
            return false;
        }
        Cantidad other = (Cantidad) object;
        if ((this.cantidadPK == null && other.cantidadPK != null) || (this.cantidadPK != null && !this.cantidadPK.equals(other.cantidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cantidad[ cantidadPK=" + cantidadPK + " ]";
    }
    
}
