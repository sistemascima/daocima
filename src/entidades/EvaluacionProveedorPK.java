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
public class EvaluacionProveedorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_evalprov_tipo")
    private String pfsEvalprovTipo;
    @Basic(optional = false)
    @Column(name = "pi_evalprov_numeeval")
    private int piEvalprovNumeeval;
    @Basic(optional = false)
    @Column(name = "pfs_evalprov_proveedor")
    private String pfsEvalprovProveedor;

    public EvaluacionProveedorPK() {
    }

    public EvaluacionProveedorPK(String pfsEvalprovTipo, int piEvalprovNumeeval, String pfsEvalprovProveedor) {
        this.pfsEvalprovTipo = pfsEvalprovTipo;
        this.piEvalprovNumeeval = piEvalprovNumeeval;
        this.pfsEvalprovProveedor = pfsEvalprovProveedor;
    }

    public String getPfsEvalprovTipo() {
        return pfsEvalprovTipo;
    }

    public void setPfsEvalprovTipo(String pfsEvalprovTipo) {
        this.pfsEvalprovTipo = pfsEvalprovTipo;
    }

    public int getPiEvalprovNumeeval() {
        return piEvalprovNumeeval;
    }

    public void setPiEvalprovNumeeval(int piEvalprovNumeeval) {
        this.piEvalprovNumeeval = piEvalprovNumeeval;
    }

    public String getPfsEvalprovProveedor() {
        return pfsEvalprovProveedor;
    }

    public void setPfsEvalprovProveedor(String pfsEvalprovProveedor) {
        this.pfsEvalprovProveedor = pfsEvalprovProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsEvalprovTipo != null ? pfsEvalprovTipo.hashCode() : 0);
        hash += (int) piEvalprovNumeeval;
        hash += (pfsEvalprovProveedor != null ? pfsEvalprovProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionProveedorPK)) {
            return false;
        }
        EvaluacionProveedorPK other = (EvaluacionProveedorPK) object;
        if ((this.pfsEvalprovTipo == null && other.pfsEvalprovTipo != null) || (this.pfsEvalprovTipo != null && !this.pfsEvalprovTipo.equals(other.pfsEvalprovTipo))) {
            return false;
        }
        if (this.piEvalprovNumeeval != other.piEvalprovNumeeval) {
            return false;
        }
        if ((this.pfsEvalprovProveedor == null && other.pfsEvalprovProveedor != null) || (this.pfsEvalprovProveedor != null && !this.pfsEvalprovProveedor.equals(other.pfsEvalprovProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return piEvalprovNumeeval + "";
    }
    
}
