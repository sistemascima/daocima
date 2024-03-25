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
public class UsuarioCargoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_usuacarg_usuario")
    private String pfsUsuacargUsuario;
    @Basic(optional = false)
    @Column(name = "pfs_usuacarg_cargo")
    private String pfsUsuacargCargo;

    public UsuarioCargoPK() {
    }

    public UsuarioCargoPK(String pfsUsuacargUsuario, String pfsUsuacargCargo) {
        this.pfsUsuacargUsuario = pfsUsuacargUsuario;
        this.pfsUsuacargCargo = pfsUsuacargCargo;
    }

    public String getPfsUsuacargUsuario() {
        return pfsUsuacargUsuario;
    }

    public void setPfsUsuacargUsuario(String pfsUsuacargUsuario) {
        this.pfsUsuacargUsuario = pfsUsuacargUsuario;
    }

    public String getPfsUsuacargCargo() {
        return pfsUsuacargCargo;
    }

    public void setPfsUsuacargCargo(String pfsUsuacargCargo) {
        this.pfsUsuacargCargo = pfsUsuacargCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsUsuacargUsuario != null ? pfsUsuacargUsuario.hashCode() : 0);
        hash += (pfsUsuacargCargo != null ? pfsUsuacargCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCargoPK)) {
            return false;
        }
        UsuarioCargoPK other = (UsuarioCargoPK) object;
        if ((this.pfsUsuacargUsuario == null && other.pfsUsuacargUsuario != null) || (this.pfsUsuacargUsuario != null && !this.pfsUsuacargUsuario.equals(other.pfsUsuacargUsuario))) {
            return false;
        }
        if ((this.pfsUsuacargCargo == null && other.pfsUsuacargCargo != null) || (this.pfsUsuacargCargo != null && !this.pfsUsuacargCargo.equals(other.pfsUsuacargCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsuarioCargoPK[ pfsUsuacargUsuario=" + pfsUsuacargUsuario + ", pfsUsuacargCargo=" + pfsUsuacargCargo + " ]";
    }
    
}
