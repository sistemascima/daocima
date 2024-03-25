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
public class RespSeleProvPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_resselpro_numeeval")
    private int pfiResselproNumeeval;
    @Basic(optional = false)
    @Column(name = "pfi_resselpro_formeval")
    private int pfiResselproFormeval;
    @Basic(optional = false)
    @Column(name = "pfs_resselpro_aspecto")
    private String pfsResselproAspecto;
    @Basic(optional = false)
    @Column(name = "pfi_resselpro_orden")
    private int pfiResselproOrden;
    @Basic(optional = false)
    @Column(name = "pfs_resselpro_proveedor")
    private String pfsResselproProveedor;

    public RespSeleProvPK() {
    }

    public RespSeleProvPK(int pfiResselproNumeeval, int pfiResselproFormeval, String pfsResselproAspecto, int pfiResselproOrden, String pfsResselproProveedor) {
        this.pfiResselproNumeeval = pfiResselproNumeeval;
        this.pfiResselproFormeval = pfiResselproFormeval;
        this.pfsResselproAspecto = pfsResselproAspecto;
        this.pfiResselproOrden = pfiResselproOrden;
        this.pfsResselproProveedor = pfsResselproProveedor;
    }

    public int getPfiResselproNumeeval() {
        return pfiResselproNumeeval;
    }

    public void setPfiResselproNumeeval(int pfiResselproNumeeval) {
        this.pfiResselproNumeeval = pfiResselproNumeeval;
    }

    public int getPfiResselproFormeval() {
        return pfiResselproFormeval;
    }

    public void setPfiResselproFormeval(int pfiResselproFormeval) {
        this.pfiResselproFormeval = pfiResselproFormeval;
    }

    public String getPfsResselproAspecto() {
        return pfsResselproAspecto;
    }

    public void setPfsResselproAspecto(String pfsResselproAspecto) {
        this.pfsResselproAspecto = pfsResselproAspecto;
    }

    public int getPfiResselproOrden() {
        return pfiResselproOrden;
    }

    public void setPfiResselproOrden(int pfiResselproOrden) {
        this.pfiResselproOrden = pfiResselproOrden;
    }

    public String getPfsResselproProveedor() {
        return pfsResselproProveedor;
    }

    public void setPfsResselproProveedor(String pfsResselproProveedor) {
        this.pfsResselproProveedor = pfsResselproProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiResselproNumeeval;
        hash += (int) pfiResselproFormeval;
        hash += (pfsResselproAspecto != null ? pfsResselproAspecto.hashCode() : 0);
        hash += (int) pfiResselproOrden;
        hash += (pfsResselproProveedor != null ? pfsResselproProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespSeleProvPK)) {
            return false;
        }
        RespSeleProvPK other = (RespSeleProvPK) object;
        if (this.pfiResselproNumeeval != other.pfiResselproNumeeval) {
            return false;
        }
        if (this.pfiResselproFormeval != other.pfiResselproFormeval) {
            return false;
        }
        if ((this.pfsResselproAspecto == null && other.pfsResselproAspecto != null) || (this.pfsResselproAspecto != null && !this.pfsResselproAspecto.equals(other.pfsResselproAspecto))) {
            return false;
        }
        if (this.pfiResselproOrden != other.pfiResselproOrden) {
            return false;
        }
        if ((this.pfsResselproProveedor == null && other.pfsResselproProveedor != null) || (this.pfsResselproProveedor != null && !this.pfsResselproProveedor.equals(other.pfsResselproProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RespSeleProvPK[ pfiResselproNumeeval=" + pfiResselproNumeeval + ", pfiResselproFormeval=" + pfiResselproFormeval + ", pfsResselproAspecto=" + pfsResselproAspecto + ", pfiResselproOrden=" + pfiResselproOrden + ", pfsResselproProveedor=" + pfsResselproProveedor + " ]";
    }
    
}
