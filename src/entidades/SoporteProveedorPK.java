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
public class SoporteProveedorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pi_sopoprov_consecutivo")
    private int piSopoprovConsecutivo;
    @Basic(optional = false)
    @Column(name = "pfs_sopoprov_proveedor")
    private String pfsSopoprovProveedor;

    public SoporteProveedorPK() {
    }

    public SoporteProveedorPK(int piSopoprovConsecutivo, String pfsSopoprovProveedor) {
        this.piSopoprovConsecutivo = piSopoprovConsecutivo;
        this.pfsSopoprovProveedor = pfsSopoprovProveedor;
    }

    public int getPiSopoprovConsecutivo() {
        return piSopoprovConsecutivo;
    }

    public void setPiSopoprovConsecutivo(int piSopoprovConsecutivo) {
        this.piSopoprovConsecutivo = piSopoprovConsecutivo;
    }

    public String getPfsSopoprovProveedor() {
        return pfsSopoprovProveedor;
    }

    public void setPfsSopoprovProveedor(String pfsSopoprovProveedor) {
        this.pfsSopoprovProveedor = pfsSopoprovProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piSopoprovConsecutivo;
        hash += (pfsSopoprovProveedor != null ? pfsSopoprovProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoporteProveedorPK)) {
            return false;
        }
        SoporteProveedorPK other = (SoporteProveedorPK) object;
        if (this.piSopoprovConsecutivo != other.piSopoprovConsecutivo) {
            return false;
        }
        if ((this.pfsSopoprovProveedor == null && other.pfsSopoprovProveedor != null) || (this.pfsSopoprovProveedor != null && !this.pfsSopoprovProveedor.equals(other.pfsSopoprovProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SoporteProveedorPK[ piSopoprovConsecutivo=" + piSopoprovConsecutivo + ", pfsSopoprovProveedor=" + pfsSopoprovProveedor + " ]";
    }
    
}
