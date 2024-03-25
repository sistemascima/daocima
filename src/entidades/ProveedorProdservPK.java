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
public class ProveedorProdservPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_provprse_nitprov")
    private String pfsProvprseNitprov;
    @Basic(optional = false)
    @Column(name = "pfs_provprse_tipproser")
    private char pfsProvprseTipproser;

    public ProveedorProdservPK() {
    }

    public ProveedorProdservPK(String pfsProvprseNitprov, char pfsProvprseTipproser) {
        this.pfsProvprseNitprov = pfsProvprseNitprov;
        this.pfsProvprseTipproser = pfsProvprseTipproser;
    }

    public String getPfsProvprseNitprov() {
        return pfsProvprseNitprov;
    }

    public void setPfsProvprseNitprov(String pfsProvprseNitprov) {
        this.pfsProvprseNitprov = pfsProvprseNitprov;
    }

    public char getPfsProvprseTipproser() {
        return pfsProvprseTipproser;
    }

    public void setPfsProvprseTipproser(char pfsProvprseTipproser) {
        this.pfsProvprseTipproser = pfsProvprseTipproser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsProvprseNitprov != null ? pfsProvprseNitprov.hashCode() : 0);
        hash += (int) pfsProvprseTipproser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorProdservPK)) {
            return false;
        }
        ProveedorProdservPK other = (ProveedorProdservPK) object;
        if ((this.pfsProvprseNitprov == null && other.pfsProvprseNitprov != null) || (this.pfsProvprseNitprov != null && !this.pfsProvprseNitprov.equals(other.pfsProvprseNitprov))) {
            return false;
        }
        if (this.pfsProvprseTipproser != other.pfsProvprseTipproser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProveedorProdservPK[ pfsProvprseNitprov=" + pfsProvprseNitprov + ", pfsProvprseTipproser=" + pfsProvprseTipproser + " ]";
    }
    
}
