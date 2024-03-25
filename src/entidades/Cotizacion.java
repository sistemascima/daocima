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
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c"),
    @NamedQuery(name = "Cotizacion.findByPfiCotizaciSolicomp", query = "SELECT c FROM Cotizacion c WHERE c.cotizacionPK.pfiCotizaciSolicomp = :pfiCotizaciSolicomp"),
    @NamedQuery(name = "Cotizacion.findByPfsCotizaciProveedor", query = "SELECT c FROM Cotizacion c WHERE c.cotizacionPK.pfsCotizaciProveedor = :pfsCotizaciProveedor"),
    @NamedQuery(name = "Cotizacion.findBySCotizaciNombarch", query = "SELECT c FROM Cotizacion c WHERE c.sCotizaciNombarch = :sCotizaciNombarch"),
    @NamedQuery(name = "Cotizacion.findByDCotizaciCreacion", query = "SELECT c FROM Cotizacion c WHERE c.dCotizaciCreacion = :dCotizaciCreacion")})
public class Cotizacion implements Serializable {
    @Basic(optional = false)
    @Column(name = "s_cotizaci_nombsopo")
    private String sCotizaciNombsopo;
    @Basic(optional = false)
    @Column(name = "s_cotizaci_numero")
    private String sCotizaciNumero;
    @Basic(optional = false)
    @Column(name = "d_cotizaci_fechcoti")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCotizaciFechcoti;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CotizacionPK cotizacionPK;
    @Basic(optional = false)
    @Column(name = "s_cotizaci_nombarch")
    private String sCotizaciNombarch;
    @Basic(optional = false)
    @Column(name = "d_cotizaci_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCotizaciCreacion;
    @JoinColumn(name = "fs_cotizaci_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsCotizaciUsuacrea;
    @JoinColumn(name = "pfi_cotizaci_solicomp", referencedColumnName = "pi_solicomp_consecutivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SolicitudCompra solicitudCompra;
    @JoinColumn(name = "pfs_cotizaci_proveedor", referencedColumnName = "ps_proveedor_nit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public Cotizacion() {
    }

    public Cotizacion(CotizacionPK cotizacionPK) {
        this.cotizacionPK = cotizacionPK;
    }

    public Cotizacion(CotizacionPK cotizacionPK, String sCotizaciNombarch, Date dCotizaciCreacion) {
        this.cotizacionPK = cotizacionPK;
        this.sCotizaciNombarch = sCotizaciNombarch;
        this.dCotizaciCreacion = dCotizaciCreacion;
    }

    public Cotizacion(int pfiCotizaciSolicomp, String pfsCotizaciProveedor) {
        this.cotizacionPK = new CotizacionPK(pfiCotizaciSolicomp, pfsCotizaciProveedor);
    }

    public CotizacionPK getCotizacionPK() {
        return cotizacionPK;
    }

    public void setCotizacionPK(CotizacionPK cotizacionPK) {
        this.cotizacionPK = cotizacionPK;
    }

    public String getSCotizaciNombarch() {
        return sCotizaciNombarch;
    }

    public void setSCotizaciNombarch(String sCotizaciNombarch) {
        this.sCotizaciNombarch = sCotizaciNombarch;
    }

    public Date getDCotizaciCreacion() {
        return dCotizaciCreacion;
    }

    public void setDCotizaciCreacion(Date dCotizaciCreacion) {
        this.dCotizaciCreacion = dCotizaciCreacion;
    }

    public SUsuario getFsCotizaciUsuacrea() {
        return fsCotizaciUsuacrea;
    }

    public void setFsCotizaciUsuacrea(SUsuario fsCotizaciUsuacrea) {
        this.fsCotizaciUsuacrea = fsCotizaciUsuacrea;
    }

    public SolicitudCompra getSolicitudCompra() {
        return solicitudCompra;
    }

    public void setSolicitudCompra(SolicitudCompra solicitudCompra) {
        this.solicitudCompra = solicitudCompra;
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
        hash += (cotizacionPK != null ? cotizacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.cotizacionPK == null && other.cotizacionPK != null) || (this.cotizacionPK != null && !this.cotizacionPK.equals(other.cotizacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotizacion[ cotizacionPK=" + cotizacionPK + " ]";
    }

    public String getSCotizaciNumero() {
        return sCotizaciNumero;
    }

    public void setSCotizaciNumero(String sCotizaciNumero) {
        this.sCotizaciNumero = sCotizaciNumero;
    }

    public Date getDCotizaciFechcoti() {
        return dCotizaciFechcoti;
    }

    public void setDCotizaciFechcoti(Date dCotizaciFechcoti) {
        this.dCotizaciFechcoti = dCotizaciFechcoti;
    }

    public String getSCotizaciNombsopo() {
        return sCotizaciNombsopo;
    }

    public void setSCotizaciNombsopo(String sCotizaciNombsopo) {
        this.sCotizaciNombsopo = sCotizaciNombsopo;
    }
    
}
