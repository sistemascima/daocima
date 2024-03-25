/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Jhon
 */
@Embeddable
public class CotizacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_cotizaci_solicomp")
    private int pfiCotizaciSolicomp;
    @Basic(optional = false)
    @Column(name = "pfs_cotizaci_proveedor")
    private String pfsCotizaciProveedor;

    public CotizacionPK() {
    }

    public CotizacionPK(int pfiCotizaciSolicomp, String pfsCotizaciProveedor) {
        this.pfiCotizaciSolicomp = pfiCotizaciSolicomp;
        this.pfsCotizaciProveedor = pfsCotizaciProveedor;
    }

    public int getPfiCotizaciSolicomp() {
        return pfiCotizaciSolicomp;
    }

    public void setPfiCotizaciSolicomp(int pfiCotizaciSolicomp) {
        this.pfiCotizaciSolicomp = pfiCotizaciSolicomp;
    }

    public String getPfsCotizaciProveedor() {
        return pfsCotizaciProveedor;
    }

    public void setPfsCotizaciProveedor(String pfsCotizaciProveedor) {
        this.pfsCotizaciProveedor = pfsCotizaciProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiCotizaciSolicomp;
        hash += (pfsCotizaciProveedor != null ? pfsCotizaciProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CotizacionPK)) {
            return false;
        }
        CotizacionPK other = (CotizacionPK) object;
        if (this.pfiCotizaciSolicomp != other.pfiCotizaciSolicomp) {
            return false;
        }
        if ((this.pfsCotizaciProveedor == null && other.pfsCotizaciProveedor != null) || (this.pfsCotizaciProveedor != null && !this.pfsCotizaciProveedor.equals(other.pfsCotizaciProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CotizacionPK[ pfiCotizaciSolicomp=" + pfiCotizaciSolicomp + ", pfsCotizaciProveedor=" + pfsCotizaciProveedor + " ]";
    }
    
}
