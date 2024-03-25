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
public class DetalleOrdenCompraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_detordcom_ordecomp")
    private int pfiDetordcomOrdecomp;
    @Basic(optional = false)
    @Column(name = "pfi_detordcom_solicomp")
    private int pfiDetordcomSolicomp;
    @Basic(optional = false)
    @Column(name = "pfi_detordcom_item")
    private int pfiDetordcomItem;

    public DetalleOrdenCompraPK() {
    }

    public DetalleOrdenCompraPK(int pfiDetordcomOrdecomp, int pfiDetordcomSolicomp, int pfiDetordcomItem) {
        this.pfiDetordcomOrdecomp = pfiDetordcomOrdecomp;
        this.pfiDetordcomSolicomp = pfiDetordcomSolicomp;
        this.pfiDetordcomItem = pfiDetordcomItem;
    }

    public int getPfiDetordcomOrdecomp() {
        return pfiDetordcomOrdecomp;
    }

    public void setPfiDetordcomOrdecomp(int pfiDetordcomOrdecomp) {
        this.pfiDetordcomOrdecomp = pfiDetordcomOrdecomp;
    }

    public int getPfiDetordcomSolicomp() {
        return pfiDetordcomSolicomp;
    }

    public void setPfiDetordcomSolicomp(int pfiDetordcomSolicomp) {
        this.pfiDetordcomSolicomp = pfiDetordcomSolicomp;
    }

    public int getPfiDetordcomItem() {
        return pfiDetordcomItem;
    }

    public void setPfiDetordcomItem(int pfiDetordcomItem) {
        this.pfiDetordcomItem = pfiDetordcomItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiDetordcomOrdecomp;
        hash += (int) pfiDetordcomSolicomp;
        hash += (int) pfiDetordcomItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenCompraPK)) {
            return false;
        }
        DetalleOrdenCompraPK other = (DetalleOrdenCompraPK) object;
        if (this.pfiDetordcomOrdecomp != other.pfiDetordcomOrdecomp) {
            return false;
        }
        if (this.pfiDetordcomSolicomp != other.pfiDetordcomSolicomp) {
            return false;
        }
        if (this.pfiDetordcomItem != other.pfiDetordcomItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + pfiDetordcomSolicomp + "-" + pfiDetordcomItem;
    }
    
}
