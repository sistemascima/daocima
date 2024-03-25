/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author TOSHIBA
 */
@Embeddable
public class CantidadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_cantidad_id", nullable = false)
    private int piCantidadId;
    @Basic(optional = false)
    @Column(name = "pfi_cantidad_solicomp", nullable = false)
    private int pfiCantidadSolicomp;
    @Basic(optional = false)
    @Column(name = "pfi_cantidad_item", nullable = false)
    private int pfiCantidadItem;

    public CantidadPK() {
    }

    public CantidadPK(int piCantidadId, int pfiCantidadSolicomp, int pfiCantidadItem) {
        this.piCantidadId = piCantidadId;
        this.pfiCantidadSolicomp = pfiCantidadSolicomp;
        this.pfiCantidadItem = pfiCantidadItem;
    }

    public int getPiCantidadId() {
        return piCantidadId;
    }

    public void setPiCantidadId(int piCantidadId) {
        this.piCantidadId = piCantidadId;
    }

    public int getPfiCantidadSolicomp() {
        return pfiCantidadSolicomp;
    }

    public void setPfiCantidadSolicomp(int pfiCantidadSolicomp) {
        this.pfiCantidadSolicomp = pfiCantidadSolicomp;
    }

    public int getPfiCantidadItem() {
        return pfiCantidadItem;
    }

    public void setPfiCantidadItem(int pfiCantidadItem) {
        this.pfiCantidadItem = pfiCantidadItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piCantidadId;
        hash += (int) pfiCantidadSolicomp;
        hash += (int) pfiCantidadItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CantidadPK)) {
            return false;
        }
        CantidadPK other = (CantidadPK) object;
        if (this.piCantidadId != other.piCantidadId) {
            return false;
        }
        if (this.pfiCantidadSolicomp != other.pfiCantidadSolicomp) {
            return false;
        }
        if (this.pfiCantidadItem != other.pfiCantidadItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CantidadPK[ piCantidadId=" + piCantidadId + ", pfiCantidadSolicomp=" + pfiCantidadSolicomp + ", pfiCantidadItem=" + pfiCantidadItem + " ]";
    }
    
}
