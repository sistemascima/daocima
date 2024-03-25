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
public class AccesoDocumentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_accedocu_tipodocu")
    private char pfsAccedocuTipodocu;
    @Basic(optional = false)
    @Column(name = "pfs_accedocu_letrproc")
    private char pfsAccedocuLetrproc;
    @Basic(optional = false)
    @Column(name = "pfs_accedocu_consdocu")
    private String pfsAccedocuConsdocu;
    @Basic(optional = false)
    @Column(name = "pfs_accedocu_versdocu")
    private String pfsAccedocuVersdocu;
    @Basic(optional = false)
    @Column(name = "pfs_accedocu_cargo")
    private String pfsAccedocuCargo;
    @Basic(optional = false)
    @Column(name = "ps_accedocu_tipoacce")
    private String psAccedocuTipoacce;

    public AccesoDocumentoPK() {
    }

    public AccesoDocumentoPK(char pfsAccedocuTipodocu, char pfsAccedocuLetrproc, String pfsAccedocuConsdocu, String pfsAccedocuVersdocu, String pfsAccedocuCargo, String psAccedocuTipoacce) {
        this.pfsAccedocuTipodocu = pfsAccedocuTipodocu;
        this.pfsAccedocuLetrproc = pfsAccedocuLetrproc;
        this.pfsAccedocuConsdocu = pfsAccedocuConsdocu;
        this.pfsAccedocuVersdocu = pfsAccedocuVersdocu;
        this.pfsAccedocuCargo = pfsAccedocuCargo;
        this.psAccedocuTipoacce = psAccedocuTipoacce;
    }

    public char getPfsAccedocuTipodocu() {
        return pfsAccedocuTipodocu;
    }

    public void setPfsAccedocuTipodocu(char pfsAccedocuTipodocu) {
        this.pfsAccedocuTipodocu = pfsAccedocuTipodocu;
    }

    public char getPfsAccedocuLetrproc() {
        return pfsAccedocuLetrproc;
    }

    public void setPfsAccedocuLetrproc(char pfsAccedocuLetrproc) {
        this.pfsAccedocuLetrproc = pfsAccedocuLetrproc;
    }

    public String getPfsAccedocuConsdocu() {
        return pfsAccedocuConsdocu;
    }

    public void setPfsAccedocuConsdocu(String pfsAccedocuConsdocu) {
        this.pfsAccedocuConsdocu = pfsAccedocuConsdocu;
    }

    public String getPfsAccedocuVersdocu() {
        return pfsAccedocuVersdocu;
    }

    public void setPfsAccedocuVersdocu(String pfsAccedocuVersdocu) {
        this.pfsAccedocuVersdocu = pfsAccedocuVersdocu;
    }

    public String getPfsAccedocuCargo() {
        return pfsAccedocuCargo;
    }

    public void setPfsAccedocuCargo(String pfsAccedocuCargo) {
        this.pfsAccedocuCargo = pfsAccedocuCargo;
    }

    public String getPsAccedocuTipoacce() {
        return psAccedocuTipoacce;
    }

    public void setPsAccedocuTipoacce(String psAccedocuTipoacce) {
        this.psAccedocuTipoacce = psAccedocuTipoacce;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfsAccedocuTipodocu;
        hash += (int) pfsAccedocuLetrproc;
        hash += (pfsAccedocuConsdocu != null ? pfsAccedocuConsdocu.hashCode() : 0);
        hash += (pfsAccedocuVersdocu != null ? pfsAccedocuVersdocu.hashCode() : 0);
        hash += (pfsAccedocuCargo != null ? pfsAccedocuCargo.hashCode() : 0);
        hash += (psAccedocuTipoacce != null ? psAccedocuTipoacce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoDocumentoPK)) {
            return false;
        }
        AccesoDocumentoPK other = (AccesoDocumentoPK) object;
        if (this.pfsAccedocuTipodocu != other.pfsAccedocuTipodocu) {
            return false;
        }
        if (this.pfsAccedocuLetrproc != other.pfsAccedocuLetrproc) {
            return false;
        }
        if ((this.pfsAccedocuConsdocu == null && other.pfsAccedocuConsdocu != null) || (this.pfsAccedocuConsdocu != null && !this.pfsAccedocuConsdocu.equals(other.pfsAccedocuConsdocu))) {
            return false;
        }
        if ((this.pfsAccedocuVersdocu == null && other.pfsAccedocuVersdocu != null) || (this.pfsAccedocuVersdocu != null && !this.pfsAccedocuVersdocu.equals(other.pfsAccedocuVersdocu))) {
            return false;
        }
        if ((this.pfsAccedocuCargo == null && other.pfsAccedocuCargo != null) || (this.pfsAccedocuCargo != null && !this.pfsAccedocuCargo.equals(other.pfsAccedocuCargo))) {
            return false;
        }
        if ((this.psAccedocuTipoacce == null && other.psAccedocuTipoacce != null) || (this.psAccedocuTipoacce != null && !this.psAccedocuTipoacce.equals(other.psAccedocuTipoacce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AccesoDocumentoPK[ pfsAccedocuTipodocu=" + pfsAccedocuTipodocu + ", pfsAccedocuLetrproc=" + pfsAccedocuLetrproc + ", pfsAccedocuConsdocu=" + pfsAccedocuConsdocu + ", pfsAccedocuVersdocu=" + pfsAccedocuVersdocu + ", pfsAccedocuCargo=" + pfsAccedocuCargo + ", psAccedocuTipoacce=" + psAccedocuTipoacce + " ]";
    }
    
}
