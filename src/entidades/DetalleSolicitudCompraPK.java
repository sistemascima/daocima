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
 * @author Jhon
 */
@Embeddable
public class DetalleSolicitudCompraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_detsolcom_solicomp")
    private int pfiDetsolcomSolicomp;
    @Basic(optional = false)
    @Column(name = "pi_detsolcom_item")
    private int piDetsolcomItem;

    public DetalleSolicitudCompraPK() {
    }

    public DetalleSolicitudCompraPK(int pfiDetsolcomSolicomp, int piDetsolcomItem) {
        this.pfiDetsolcomSolicomp = pfiDetsolcomSolicomp;
        this.piDetsolcomItem = piDetsolcomItem;
    }

    public int getPfiDetsolcomSolicomp() {
        return pfiDetsolcomSolicomp;
    }

    public void setPfiDetsolcomSolicomp(int pfiDetsolcomSolicomp) {
        this.pfiDetsolcomSolicomp = pfiDetsolcomSolicomp;
    }

    public int getPiDetsolcomItem() {
        return piDetsolcomItem;
    }

    public void setPiDetsolcomItem(int piDetsolcomItem) {
        this.piDetsolcomItem = piDetsolcomItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiDetsolcomSolicomp;
        hash += (int) piDetsolcomItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleSolicitudCompraPK)) {
            return false;
        }
        DetalleSolicitudCompraPK other = (DetalleSolicitudCompraPK) object;
        if (this.pfiDetsolcomSolicomp != other.pfiDetsolcomSolicomp) {
            return false;
        }
        if (this.piDetsolcomItem != other.piDetsolcomItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleSolicitudCompraPK[ pfiDetsolcomSolicomp=" + pfiDetsolcomSolicomp + ", piDetsolcomItem=" + piDetsolcomItem + " ]";
    }
    
}
