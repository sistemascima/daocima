/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "soporte_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoporteProveedor.findAll", query = "SELECT s FROM SoporteProveedor s"),
    @NamedQuery(name = "SoporteProveedor.findByPiSopoprovConsecutivo", query = "SELECT s FROM SoporteProveedor s WHERE s.soporteProveedorPK.piSopoprovConsecutivo = :piSopoprovConsecutivo"),
    @NamedQuery(name = "SoporteProveedor.findByPfsSopoprovProveedor", query = "SELECT s FROM SoporteProveedor s WHERE s.soporteProveedorPK.pfsSopoprovProveedor = :pfsSopoprovProveedor"),
    @NamedQuery(name = "SoporteProveedor.findBySSopoprovDescripcion", query = "SELECT s FROM SoporteProveedor s WHERE s.sSopoprovDescripcion = :sSopoprovDescripcion"),
    @NamedQuery(name = "SoporteProveedor.findBySSopoprovNombarch", query = "SELECT s FROM SoporteProveedor s WHERE s.sSopoprovNombarch = :sSopoprovNombarch"),
    @NamedQuery(name = "SoporteProveedor.findByDSopoprovCreacion", query = "SELECT s FROM SoporteProveedor s WHERE s.dSopoprovCreacion = :dSopoprovCreacion")})
public class SoporteProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoporteProveedorPK soporteProveedorPK;
    @Basic(optional = false)
    @Column(name = "s_sopoprov_descripcion")
    private String sSopoprovDescripcion;
    @Basic(optional = false)
    @Column(name = "s_sopoprov_nombarch")
    private String sSopoprovNombarch;
    @Basic(optional = false)
    @Column(name = "d_sopoprov_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSopoprovCreacion;
    @JoinColumn(name = "fs_sopoprov_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsSopoprovUsuacrea;
    @JoinColumn(name = "pfs_sopoprov_proveedor", referencedColumnName = "ps_proveedor_nit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public SoporteProveedor() {
    }

    public SoporteProveedor(SoporteProveedorPK soporteProveedorPK) {
        this.soporteProveedorPK = soporteProveedorPK;
    }

    public SoporteProveedor(SoporteProveedorPK soporteProveedorPK, String sSopoprovDescripcion, String sSopoprovNombarch, Date dSopoprovCreacion) {
        this.soporteProveedorPK = soporteProveedorPK;
        this.sSopoprovDescripcion = sSopoprovDescripcion;
        this.sSopoprovNombarch = sSopoprovNombarch;
        this.dSopoprovCreacion = dSopoprovCreacion;
    }

    public SoporteProveedor(int piSopoprovConsecutivo, String pfsSopoprovProveedor) {
        this.soporteProveedorPK = new SoporteProveedorPK(piSopoprovConsecutivo, pfsSopoprovProveedor);
    }

    public SoporteProveedorPK getSoporteProveedorPK() {
        return soporteProveedorPK;
    }

    public void setSoporteProveedorPK(SoporteProveedorPK soporteProveedorPK) {
        this.soporteProveedorPK = soporteProveedorPK;
    }

    public String getSSopoprovDescripcion() {
        return sSopoprovDescripcion;
    }

    public void setSSopoprovDescripcion(String sSopoprovDescripcion) {
        this.sSopoprovDescripcion = sSopoprovDescripcion;
    }

    public String getSSopoprovNombarch() {
        return sSopoprovNombarch;
    }

    public void setSSopoprovNombarch(String sSopoprovNombarch) {
        this.sSopoprovNombarch = sSopoprovNombarch;
    }

    public Date getDSopoprovCreacion() {
        return dSopoprovCreacion;
    }

    public void setDSopoprovCreacion(Date dSopoprovCreacion) {
        this.dSopoprovCreacion = dSopoprovCreacion;
    }

    public SUsuario getFsSopoprovUsuacrea() {
        return fsSopoprovUsuacrea;
    }

    public void setFsSopoprovUsuacrea(SUsuario fsSopoprovUsuacrea) {
        this.fsSopoprovUsuacrea = fsSopoprovUsuacrea;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soporteProveedorPK != null ? soporteProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoporteProveedor)) {
            return false;
        }
        SoporteProveedor other = (SoporteProveedor) object;
        if ((this.soporteProveedorPK == null && other.soporteProveedorPK != null) || (this.soporteProveedorPK != null && !this.soporteProveedorPK.equals(other.soporteProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return soporteProveedorPK.getPiSopoprovConsecutivo() + "";
    }
    
}
