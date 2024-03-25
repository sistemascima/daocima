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
public class ComponenteCargoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_compcarg_componente")
    private int pfiCompcargComponente;
    @Basic(optional = false)
    @Column(name = "pfs_compcarg_cargo")
    private String pfsCompcargCargo;

    public ComponenteCargoPK() {
    }

    public ComponenteCargoPK(int pfiCompcargComponente, String pfsCompcargCargo) {
        this.pfiCompcargComponente = pfiCompcargComponente;
        this.pfsCompcargCargo = pfsCompcargCargo;
    }

    public int getPfiCompcargComponente() {
        return pfiCompcargComponente;
    }

    public void setPfiCompcargComponente(int pfiCompcargComponente) {
        this.pfiCompcargComponente = pfiCompcargComponente;
    }

    public String getPfsCompcargCargo() {
        return pfsCompcargCargo;
    }

    public void setPfsCompcargCargo(String pfsCompcargCargo) {
        this.pfsCompcargCargo = pfsCompcargCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiCompcargComponente;
        hash += (pfsCompcargCargo != null ? pfsCompcargCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponenteCargoPK)) {
            return false;
        }
        ComponenteCargoPK other = (ComponenteCargoPK) object;
        if (this.pfiCompcargComponente != other.pfiCompcargComponente) {
            return false;
        }
        if ((this.pfsCompcargCargo == null && other.pfsCompcargCargo != null) || (this.pfsCompcargCargo != null && !this.pfsCompcargCargo.equals(other.pfsCompcargCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ComponenteCargoPK[ pfiCompcargComponente=" + pfiCompcargComponente + ", pfsCompcargCargo=" + pfsCompcargCargo + " ]";
    }
    
}
