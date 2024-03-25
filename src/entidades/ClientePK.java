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
public class ClientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ps_cliente_nit", nullable = false, length = 20)
    private String psClienteNit;
    @Basic(optional = false)
    @Column(name = "s_cliente_nombre", nullable = false, length = 100)
    private String sClienteNombre;

    public ClientePK() {
    }

    public ClientePK(String psClienteNit, String sClienteNombre) {
        this.psClienteNit = psClienteNit;
        this.sClienteNombre = sClienteNombre;
    }

    public String getPsClienteNit() {
        return psClienteNit;
    }

    public void setPsClienteNit(String psClienteNit) {
        this.psClienteNit = psClienteNit;
    }

    public String getSClienteNombre() {
        return sClienteNombre;
    }

    public void setSClienteNombre(String sClienteNombre) {
        this.sClienteNombre = sClienteNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psClienteNit != null ? psClienteNit.hashCode() : 0);
        hash += (sClienteNombre != null ? sClienteNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientePK)) {
            return false;
        }
        ClientePK other = (ClientePK) object;
        if ((this.psClienteNit == null && other.psClienteNit != null) || (this.psClienteNit != null && !this.psClienteNit.equals(other.psClienteNit))) {
            return false;
        }
        if ((this.sClienteNombre == null && other.sClienteNombre != null) || (this.sClienteNombre != null && !this.sClienteNombre.equals(other.sClienteNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ClientePK[ psClienteNit=" + psClienteNit + ", sClienteNombre=" + sClienteNombre + " ]";
    }
    
}
