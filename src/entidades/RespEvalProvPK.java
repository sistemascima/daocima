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
public class RespEvalProvPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfs_resevapro_tipo")
    private String pfsResevaproTipo;
    @Basic(optional = false)
    @Column(name = "pfi_resevapro_numeeval")
    private int pfiResevaproNumeeval;
    @Basic(optional = false)
    @Column(name = "pfs_resevapro_proveedor")
    private String pfsResevaproProveedor;
    @Basic(optional = false)
    @Column(name = "pfi_resevapro_formeval")
    private int pfiResevaproFormeval;
    @Basic(optional = false)
    @Column(name = "pfs_resevapro_aspecto")
    private String pfsResevaproAspecto;
    @Basic(optional = false)
    @Column(name = "pfi_resevapro_orden")
    private int pfiResevaproOrden;

    public RespEvalProvPK() {
    }

    public RespEvalProvPK(String pfsResevaproTipo, int pfiResevaproNumeeval, String pfsResevaproProveedor, int pfiResevaproFormeval, String pfsResevaproAspecto, int pfiResevaproOrden) {
        this.pfsResevaproTipo = pfsResevaproTipo;
        this.pfiResevaproNumeeval = pfiResevaproNumeeval;
        this.pfsResevaproProveedor = pfsResevaproProveedor;
        this.pfiResevaproFormeval = pfiResevaproFormeval;
        this.pfsResevaproAspecto = pfsResevaproAspecto;
        this.pfiResevaproOrden = pfiResevaproOrden;
    }

    public String getPfsResevaproTipo() {
        return pfsResevaproTipo;
    }

    public void setPfsResevaproTipo(String pfsResevaproTipo) {
        this.pfsResevaproTipo = pfsResevaproTipo;
    }

    public int getPfiResevaproNumeeval() {
        return pfiResevaproNumeeval;
    }

    public void setPfiResevaproNumeeval(int pfiResevaproNumeeval) {
        this.pfiResevaproNumeeval = pfiResevaproNumeeval;
    }

    public String getPfsResevaproProveedor() {
        return pfsResevaproProveedor;
    }

    public void setPfsResevaproProveedor(String pfsResevaproProveedor) {
        this.pfsResevaproProveedor = pfsResevaproProveedor;
    }

    public int getPfiResevaproFormeval() {
        return pfiResevaproFormeval;
    }

    public void setPfiResevaproFormeval(int pfiResevaproFormeval) {
        this.pfiResevaproFormeval = pfiResevaproFormeval;
    }

    public String getPfsResevaproAspecto() {
        return pfsResevaproAspecto;
    }

    public void setPfsResevaproAspecto(String pfsResevaproAspecto) {
        this.pfsResevaproAspecto = pfsResevaproAspecto;
    }

    public int getPfiResevaproOrden() {
        return pfiResevaproOrden;
    }

    public void setPfiResevaproOrden(int pfiResevaproOrden) {
        this.pfiResevaproOrden = pfiResevaproOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsResevaproTipo != null ? pfsResevaproTipo.hashCode() : 0);
        hash += (int) pfiResevaproNumeeval;
        hash += (pfsResevaproProveedor != null ? pfsResevaproProveedor.hashCode() : 0);
        hash += (int) pfiResevaproFormeval;
        hash += (pfsResevaproAspecto != null ? pfsResevaproAspecto.hashCode() : 0);
        hash += (int) pfiResevaproOrden;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespEvalProvPK)) {
            return false;
        }
        RespEvalProvPK other = (RespEvalProvPK) object;
        if ((this.pfsResevaproTipo == null && other.pfsResevaproTipo != null) || (this.pfsResevaproTipo != null && !this.pfsResevaproTipo.equals(other.pfsResevaproTipo))) {
            return false;
        }
        if (this.pfiResevaproNumeeval != other.pfiResevaproNumeeval) {
            return false;
        }
        if ((this.pfsResevaproProveedor == null && other.pfsResevaproProveedor != null) || (this.pfsResevaproProveedor != null && !this.pfsResevaproProveedor.equals(other.pfsResevaproProveedor))) {
            return false;
        }
        if (this.pfiResevaproFormeval != other.pfiResevaproFormeval) {
            return false;
        }
        if ((this.pfsResevaproAspecto == null && other.pfsResevaproAspecto != null) || (this.pfsResevaproAspecto != null && !this.pfsResevaproAspecto.equals(other.pfsResevaproAspecto))) {
            return false;
        }
        if (this.pfiResevaproOrden != other.pfiResevaproOrden) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RespEvalProvPK[ pfsResevaproTipo=" + pfsResevaproTipo + ", pfiResevaproNumeeval=" + pfiResevaproNumeeval + ", pfsResevaproProveedor=" + pfsResevaproProveedor + ", pfiResevaproFormeval=" + pfiResevaproFormeval + ", pfsResevaproAspecto=" + pfsResevaproAspecto + ", pfiResevaproOrden=" + pfiResevaproOrden + " ]";
    }
    
}
