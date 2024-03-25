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
public class DetalleServicioInternoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_detserint_servinte")
    private int pfiDetserintServinte;
    @Basic(optional = false)
    @Column(name = "pi_detserint_item")
    private int piDetserintItem;

    public DetalleServicioInternoPK() {
    }

    public DetalleServicioInternoPK(int pfiDetserintServinte, int piDetserintItem) {
        this.pfiDetserintServinte = pfiDetserintServinte;
        this.piDetserintItem = piDetserintItem;
    }

    public int getPfiDetserintServinte() {
        return pfiDetserintServinte;
    }

    public void setPfiDetserintServinte(int pfiDetserintServinte) {
        this.pfiDetserintServinte = pfiDetserintServinte;
    }

    public int getPiDetserintItem() {
        return piDetserintItem;
    }

    public void setPiDetserintItem(int piDetserintItem) {
        this.piDetserintItem = piDetserintItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiDetserintServinte;
        hash += (int) piDetserintItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicioInternoPK)) {
            return false;
        }
        DetalleServicioInternoPK other = (DetalleServicioInternoPK) object;
        if (this.pfiDetserintServinte != other.pfiDetserintServinte) {
            return false;
        }
        if (this.piDetserintItem != other.piDetserintItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleServicioInternoPK[ pfiDetserintServinte=" + pfiDetserintServinte + ", piDetserintItem=" + piDetserintItem + " ]";
    }
    
}
