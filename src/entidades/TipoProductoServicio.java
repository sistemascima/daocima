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
@Table(name = "tipo_producto_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProductoServicio.findAll", query = "SELECT t FROM TipoProductoServicio t"),
    @NamedQuery(name = "TipoProductoServicio.findByPsTipproserCodigo", query = "SELECT t FROM TipoProductoServicio t WHERE t.psTipproserCodigo = :psTipproserCodigo"),
    @NamedQuery(name = "TipoProductoServicio.findBySTipproserNombre", query = "SELECT t FROM TipoProductoServicio t WHERE t.sTipproserNombre = :sTipproserNombre"),
    @NamedQuery(name = "TipoProductoServicio.findByDTipproserCreacion", query = "SELECT t FROM TipoProductoServicio t WHERE t.dTipproserCreacion = :dTipproserCreacion"),
    @NamedQuery(name = "TipoProductoServicio.findByDTipproserUltimodi", query = "SELECT t FROM TipoProductoServicio t WHERE t.dTipproserUltimodi = :dTipproserUltimodi")})
public class TipoProductoServicio implements Serializable {
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsSolicompTipproser")
    private Collection<SolicitudCompra> solicitudCompraCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_tipproser_codigo")
    private Character psTipproserCodigo;
    @Basic(optional = false)
    @Column(name = "s_tipproser_nombre")
    private String sTipproserNombre;
    @Basic(optional = false)
    @Column(name = "d_tipproser_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipproserCreacion;
    @Column(name = "d_tipproser_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipproserUltimodi;

    public TipoProductoServicio() {
    }

    public TipoProductoServicio(Character psTipproserCodigo) {
        this.psTipproserCodigo = psTipproserCodigo;
    }

    public TipoProductoServicio(Character psTipproserCodigo, String sTipproserNombre, Date dTipproserCreacion) {
        this.psTipproserCodigo = psTipproserCodigo;
        this.sTipproserNombre = sTipproserNombre;
        this.dTipproserCreacion = dTipproserCreacion;
    }

    public Character getPsTipproserCodigo() {
        return psTipproserCodigo;
    }

    public void setPsTipproserCodigo(Character psTipproserCodigo) {
        this.psTipproserCodigo = psTipproserCodigo;
    }

    public String getSTipproserNombre() {
        return sTipproserNombre;
    }

    public void setSTipproserNombre(String sTipproserNombre) {
        this.sTipproserNombre = sTipproserNombre;
    }

    public Date getDTipproserCreacion() {
        return dTipproserCreacion;
    }

    public void setDTipproserCreacion(Date dTipproserCreacion) {
        this.dTipproserCreacion = dTipproserCreacion;
    }

    public Date getDTipproserUltimodi() {
        return dTipproserUltimodi;
    }

    public void setDTipproserUltimodi(Date dTipproserUltimodi) {
        this.dTipproserUltimodi = dTipproserUltimodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psTipproserCodigo != null ? psTipproserCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProductoServicio)) {
            return false;
        }
        TipoProductoServicio other = (TipoProductoServicio) object;
        if ((this.psTipproserCodigo == null && other.psTipproserCodigo != null) || (this.psTipproserCodigo != null && !this.psTipproserCodigo.equals(other.psTipproserCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return psTipproserCodigo + " - " + sTipproserNombre;
    }


    @XmlTransient
    public Collection<SolicitudCompra> getSolicitudCompraCollection() {
        return solicitudCompraCollection;
    }

    public void setSolicitudCompraCollection(Collection<SolicitudCompra> solicitudCompraCollection) {
        this.solicitudCompraCollection = solicitudCompraCollection;
    }

   
    
}
