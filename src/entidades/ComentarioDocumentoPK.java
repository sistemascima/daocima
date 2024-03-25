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
public class ComentarioDocumentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_comedocu_tipodocu")
    private char pfsComedocuTipodocu;
    @Basic(optional = false)
    @Column(name = "pfs_comedocu_letrproc")
    private char pfsComedocuLetrproc;
    @Basic(optional = false)
    @Column(name = "pfs_comedocu_consedocu")
    private String pfsComedocuConsedocu;
    @Basic(optional = false)
    @Column(name = "pfs_comedocu_version")
    private String pfsComedocuVersion;
    @Basic(optional = false)
    @Column(name = "pi_comedocu_consecutivo")
    private int piComedocuConsecutivo;

    public ComentarioDocumentoPK() {
    }

    public ComentarioDocumentoPK(char pfsComedocuTipodocu, char pfsComedocuLetrproc, String pfsComedocuConsedocu, String pfsComedocuVersion, int piComedocuConsecutivo) {
        this.pfsComedocuTipodocu = pfsComedocuTipodocu;
        this.pfsComedocuLetrproc = pfsComedocuLetrproc;
        this.pfsComedocuConsedocu = pfsComedocuConsedocu;
        this.pfsComedocuVersion = pfsComedocuVersion;
        this.piComedocuConsecutivo = piComedocuConsecutivo;
    }

    public char getPfsComedocuTipodocu() {
        return pfsComedocuTipodocu;
    }

    public void setPfsComedocuTipodocu(char pfsComedocuTipodocu) {
        this.pfsComedocuTipodocu = pfsComedocuTipodocu;
    }

    public char getPfsComedocuLetrproc() {
        return pfsComedocuLetrproc;
    }

    public void setPfsComedocuLetrproc(char pfsComedocuLetrproc) {
        this.pfsComedocuLetrproc = pfsComedocuLetrproc;
    }

    public String getPfsComedocuConsedocu() {
        return pfsComedocuConsedocu;
    }

    public void setPfsComedocuConsedocu(String pfsComedocuConsedocu) {
        this.pfsComedocuConsedocu = pfsComedocuConsedocu;
    }

    public String getPfsComedocuVersion() {
        return pfsComedocuVersion;
    }

    public void setPfsComedocuVersion(String pfsComedocuVersion) {
        this.pfsComedocuVersion = pfsComedocuVersion;
    }

    public int getPiComedocuConsecutivo() {
        return piComedocuConsecutivo;
    }

    public void setPiComedocuConsecutivo(int piComedocuConsecutivo) {
        this.piComedocuConsecutivo = piComedocuConsecutivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfsComedocuTipodocu;
        hash += (int) pfsComedocuLetrproc;
        hash += (pfsComedocuConsedocu != null ? pfsComedocuConsedocu.hashCode() : 0);
        hash += (pfsComedocuVersion != null ? pfsComedocuVersion.hashCode() : 0);
        hash += (int) piComedocuConsecutivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioDocumentoPK)) {
            return false;
        }
        ComentarioDocumentoPK other = (ComentarioDocumentoPK) object;
        if (this.pfsComedocuTipodocu != other.pfsComedocuTipodocu) {
            return false;
        }
        if (this.pfsComedocuLetrproc != other.pfsComedocuLetrproc) {
            return false;
        }
        if ((this.pfsComedocuConsedocu == null && other.pfsComedocuConsedocu != null) || (this.pfsComedocuConsedocu != null && !this.pfsComedocuConsedocu.equals(other.pfsComedocuConsedocu))) {
            return false;
        }
        if ((this.pfsComedocuVersion == null && other.pfsComedocuVersion != null) || (this.pfsComedocuVersion != null && !this.pfsComedocuVersion.equals(other.pfsComedocuVersion))) {
            return false;
        }
        if (this.piComedocuConsecutivo != other.piComedocuConsecutivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ComentarioDocumentoPK[ pfsComedocuTipodocu=" + pfsComedocuTipodocu + ", pfsComedocuLetrproc=" + pfsComedocuLetrproc + ", pfsComedocuConsedocu=" + pfsComedocuConsedocu + ", pfsComedocuVersion=" + pfsComedocuVersion + ", piComedocuConsecutivo=" + piComedocuConsecutivo + " ]";
    }
    
}
