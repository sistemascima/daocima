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
public class HojaVidaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pfs_hoja_vida_matriz", nullable = false, length = 2)
    private String pfsHojaVidaMatriz;
    @Basic(optional = false)
    @Column(name = "pfs_hoja_vida_consecutivo", nullable = false)
    private int pfsHojaVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "pfs_hoja_vida_funcion", nullable = false, length = 2)
    private String pfsHojaVidaFuncion;

    public HojaVidaPK() {
    }

    public HojaVidaPK(String pfsHojaVidaMatriz, int pfsHojaVidaConsecutivo, String pfsHojaVidaFuncion) {
        this.pfsHojaVidaMatriz = pfsHojaVidaMatriz;
        this.pfsHojaVidaConsecutivo = pfsHojaVidaConsecutivo;
        this.pfsHojaVidaFuncion = pfsHojaVidaFuncion;
    }

    public String getPfsHojaVidaMatriz() {
        return pfsHojaVidaMatriz;
    }

    public void setPfsHojaVidaMatriz(String pfsHojaVidaMatriz) {
        this.pfsHojaVidaMatriz = pfsHojaVidaMatriz;
    }

    public int getPfsHojaVidaConsecutivo() {
        return pfsHojaVidaConsecutivo;
    }

    public void setPfsHojaVidaConsecutivo(int pfsHojaVidaConsecutivo) {
        this.pfsHojaVidaConsecutivo = pfsHojaVidaConsecutivo;
    }

    public String getPfsHojaVidaFuncion() {
        return pfsHojaVidaFuncion;
    }

    public void setPfsHojaVidaFuncion(String pfsHojaVidaFuncion) {
        this.pfsHojaVidaFuncion = pfsHojaVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfsHojaVidaMatriz != null ? pfsHojaVidaMatriz.hashCode() : 0);
        hash += (int) pfsHojaVidaConsecutivo;
        hash += (pfsHojaVidaFuncion != null ? pfsHojaVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaVidaPK)) {
            return false;
        }
        HojaVidaPK other = (HojaVidaPK) object;
        if ((this.pfsHojaVidaMatriz == null && other.pfsHojaVidaMatriz != null) || (this.pfsHojaVidaMatriz != null && !this.pfsHojaVidaMatriz.equals(other.pfsHojaVidaMatriz))) {
            return false;
        }
        if (this.pfsHojaVidaConsecutivo != other.pfsHojaVidaConsecutivo) {
            return false;
        }
        if ((this.pfsHojaVidaFuncion == null && other.pfsHojaVidaFuncion != null) || (this.pfsHojaVidaFuncion != null && !this.pfsHojaVidaFuncion.equals(other.pfsHojaVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HojaVidaPK[ pfsHojaVidaMatriz=" + pfsHojaVidaMatriz + ", pfsHojaVidaConsecutivo=" + pfsHojaVidaConsecutivo + ", pfsHojaVidaFuncion=" + pfsHojaVidaFuncion + " ]";
    }
    
}
