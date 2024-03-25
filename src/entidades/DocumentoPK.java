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
public class DocumentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_document_tipodocu")
    private char pfsDocumentTipodocu;
    @Basic(optional = false)
    @Column(name = "pfs_document_letrproc")
    private char pfsDocumentLetrproc;
    @Basic(optional = false)
    @Column(name = "ps_document_consecutivo")
    private String psDocumentConsecutivo;
    @Basic(optional = false)
    @Column(name = "ps_document_version")
    private String psDocumentVersion;

    public DocumentoPK() {
    }

    public DocumentoPK(char pfsDocumentTipodocu, char pfsDocumentLetrproc, String psDocumentConsecutivo, String psDocumentVersion) {
        this.pfsDocumentTipodocu = pfsDocumentTipodocu;
        this.pfsDocumentLetrproc = pfsDocumentLetrproc;
        this.psDocumentConsecutivo = psDocumentConsecutivo;
        this.psDocumentVersion = psDocumentVersion;
    }

    public char getPfsDocumentTipodocu() {
        return pfsDocumentTipodocu;
    }

    public void setPfsDocumentTipodocu(char pfsDocumentTipodocu) {
        this.pfsDocumentTipodocu = pfsDocumentTipodocu;
    }

    public char getPfsDocumentLetrproc() {
        return pfsDocumentLetrproc;
    }

    public void setPfsDocumentLetrproc(char pfsDocumentLetrproc) {
        this.pfsDocumentLetrproc = pfsDocumentLetrproc;
    }

    public String getPsDocumentConsecutivo() {
        return psDocumentConsecutivo;
    }

    public void setPsDocumentConsecutivo(String psDocumentConsecutivo) {
        this.psDocumentConsecutivo = psDocumentConsecutivo;
    }

    public String getPsDocumentVersion() {
        return psDocumentVersion;
    }

    public void setPsDocumentVersion(String psDocumentVersion) {
        this.psDocumentVersion = psDocumentVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfsDocumentTipodocu;
        hash += (int) pfsDocumentLetrproc;
        hash += (psDocumentConsecutivo != null ? psDocumentConsecutivo.hashCode() : 0);
        hash += (psDocumentVersion != null ? psDocumentVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoPK)) {
            return false;
        }
        DocumentoPK other = (DocumentoPK) object;
        if (this.pfsDocumentTipodocu != other.pfsDocumentTipodocu) {
            return false;
        }
        if (this.pfsDocumentLetrproc != other.pfsDocumentLetrproc) {
            return false;
        }
        if ((this.psDocumentConsecutivo == null && other.psDocumentConsecutivo != null) || (this.psDocumentConsecutivo != null && !this.psDocumentConsecutivo.equals(other.psDocumentConsecutivo))) {
            return false;
        }
        if ((this.psDocumentVersion == null && other.psDocumentVersion != null) || (this.psDocumentVersion != null && !this.psDocumentVersion.equals(other.psDocumentVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocumentoPK[ pfsDocumentTipodocu=" + pfsDocumentTipodocu + ", pfsDocumentLetrproc=" + pfsDocumentLetrproc + ", psDocumentConsecutivo=" + psDocumentConsecutivo + ", psDocumentVersion=" + psDocumentVersion + " ]";
    }
    
}
