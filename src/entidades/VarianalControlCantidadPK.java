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
public class VarianalControlCantidadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pfi_varianal_control_cantidad_variable", nullable = false)
    private int pfiVarianalControlCantidadVariable;
    @Basic(optional = false)
    @Column(name = "pfi_varianal_control_cantidad_control", nullable = false)
    private int pfiVarianalControlCantidadControl;

    public VarianalControlCantidadPK() {
    }

    public VarianalControlCantidadPK(int pfiVarianalControlCantidadVariable, int pfiVarianalControlCantidadControl) {
        this.pfiVarianalControlCantidadVariable = pfiVarianalControlCantidadVariable;
        this.pfiVarianalControlCantidadControl = pfiVarianalControlCantidadControl;
    }

    public int getPfiVarianalControlCantidadVariable() {
        return pfiVarianalControlCantidadVariable;
    }

    public void setPfiVarianalControlCantidadVariable(int pfiVarianalControlCantidadVariable) {
        this.pfiVarianalControlCantidadVariable = pfiVarianalControlCantidadVariable;
    }

    public int getPfiVarianalControlCantidadControl() {
        return pfiVarianalControlCantidadControl;
    }

    public void setPfiVarianalControlCantidadControl(int pfiVarianalControlCantidadControl) {
        this.pfiVarianalControlCantidadControl = pfiVarianalControlCantidadControl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiVarianalControlCantidadVariable;
        hash += (int) pfiVarianalControlCantidadControl;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VarianalControlCantidadPK)) {
            return false;
        }
        VarianalControlCantidadPK other = (VarianalControlCantidadPK) object;
        if (this.pfiVarianalControlCantidadVariable != other.pfiVarianalControlCantidadVariable) {
            return false;
        }
        if (this.pfiVarianalControlCantidadControl != other.pfiVarianalControlCantidadControl) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VarianalControlCantidadPK[ pfiVarianalControlCantidadVariable=" + pfiVarianalControlCantidadVariable + ", pfiVarianalControlCantidadControl=" + pfiVarianalControlCantidadControl + " ]";
    }
    
}
