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
 * @author Jhon
 */
@Embeddable
public class ProveedorVariableAnalisisPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pfs_provarana_proveedor", nullable = false, length = 20)
    private String pfsProvaranaProveedor;
    @Basic(optional = false)
    @Column(name = "pfi_provarana_variable", nullable = false)
    private int pfiProvaranaVariable;


    public ProveedorVariableAnalisisPK() {
    }

    public ProveedorVariableAnalisisPK(String pfsProvaranaProveedor, int pfiProvaranaVariable) {
        this.pfsProvaranaProveedor = pfsProvaranaProveedor;
        this.pfiProvaranaVariable = pfiProvaranaVariable;
    }

    public String getPfsProvaranaProveedor() {
        return pfsProvaranaProveedor;
    }

    public void setPfsProvaranaProveedor(String pfsProvaranaProveedor) {
        this.pfsProvaranaProveedor = pfsProvaranaProveedor;
    }

    public int getPfiProvaranaVariable() {
        return pfiProvaranaVariable;
    }

    public void setPfiProvaranaVariable(int pfiProvaranaVariable) {
        this.pfiProvaranaVariable = pfiProvaranaVariable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsProvaranaProveedor != null ? pfsProvaranaProveedor.hashCode() : 0);
        hash += (int) pfiProvaranaVariable;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorVariableAnalisisPK)) {
            return false;
        }
        ProveedorVariableAnalisisPK other = (ProveedorVariableAnalisisPK) object;
        if ((this.pfsProvaranaProveedor == null && other.pfsProvaranaProveedor != null) || (this.pfsProvaranaProveedor != null && !this.pfsProvaranaProveedor.equals(other.pfsProvaranaProveedor))) {
            return false;
        }
        if (this.pfiProvaranaVariable != other.pfiProvaranaVariable) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProveedorVariableAnalisisPK[ pfsProvaranaProveedor=" + pfsProvaranaProveedor + ", pfiProvaranaVariable=" + pfiProvaranaVariable + " ]";
    }

   
    
}
