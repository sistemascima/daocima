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
public class ResponsableDocumentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_respdocu_proceso")
    private int pfiRespdocuProceso;
    @Basic(optional = false)
    @Column(name = "pfs_respdocu_tipodocu")
    private char pfsRespdocuTipodocu;
    @Basic(optional = false)
    @Column(name = "pfs_respdocu_cargo")
    private String pfsRespdocuCargo;
    @Basic(optional = false)
    @Column(name = "ps_respdocu_tiporesp")
    private char psRespdocuTiporesp;

    public ResponsableDocumentoPK() {
    }

    public ResponsableDocumentoPK(int pfiRespdocuProceso, char pfsRespdocuTipodocu, String pfsRespdocuCargo, char psRespdocuTiporesp) {
        this.pfiRespdocuProceso = pfiRespdocuProceso;
        this.pfsRespdocuTipodocu = pfsRespdocuTipodocu;
        this.pfsRespdocuCargo = pfsRespdocuCargo;
        this.psRespdocuTiporesp = psRespdocuTiporesp;
    }

    public int getPfiRespdocuProceso() {
        return pfiRespdocuProceso;
    }

    public void setPfiRespdocuProceso(int pfiRespdocuProceso) {
        this.pfiRespdocuProceso = pfiRespdocuProceso;
    }

    public char getPfsRespdocuTipodocu() {
        return pfsRespdocuTipodocu;
    }

    public void setPfsRespdocuTipodocu(char pfsRespdocuTipodocu) {
        this.pfsRespdocuTipodocu = pfsRespdocuTipodocu;
    }

    public String getPfsRespdocuCargo() {
        return pfsRespdocuCargo;
    }

    public void setPfsRespdocuCargo(String pfsRespdocuCargo) {
        this.pfsRespdocuCargo = pfsRespdocuCargo;
    }

    public char getPsRespdocuTiporesp() {
        return psRespdocuTiporesp;
    }

    public void setPsRespdocuTiporesp(char psRespdocuTiporesp) {
        this.psRespdocuTiporesp = psRespdocuTiporesp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiRespdocuProceso;
        hash += (int) pfsRespdocuTipodocu;
        hash += (pfsRespdocuCargo != null ? pfsRespdocuCargo.hashCode() : 0);
        hash += (int) psRespdocuTiporesp;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableDocumentoPK)) {
            return false;
        }
        ResponsableDocumentoPK other = (ResponsableDocumentoPK) object;
        if (this.pfiRespdocuProceso != other.pfiRespdocuProceso) {
            return false;
        }
        if (this.pfsRespdocuTipodocu != other.pfsRespdocuTipodocu) {
            return false;
        }
        if ((this.pfsRespdocuCargo == null && other.pfsRespdocuCargo != null) || (this.pfsRespdocuCargo != null && !this.pfsRespdocuCargo.equals(other.pfsRespdocuCargo))) {
            return false;
        }
        if (this.psRespdocuTiporesp != other.psRespdocuTiporesp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResponsableDocumentoPK[ pfiRespdocuProceso=" + pfiRespdocuProceso + ", pfsRespdocuTipodocu=" + pfsRespdocuTipodocu + ", pfsRespdocuCargo=" + pfsRespdocuCargo + ", psRespdocuTiporesp=" + psRespdocuTiporesp + " ]";
    }
    
}
