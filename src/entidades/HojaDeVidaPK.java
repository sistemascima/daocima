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
 * @author Juan Felipe Fontecha
 */
@Embeddable
public class HojaDeVidaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_hoja_vida_consecutivo", nullable = false)
    private int piHojaVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "ps_hoja_vida_matriz", nullable = false, length = 2)
    private String psHojaVidaMatriz;
    @Basic(optional = false)
    @Column(name = "ps_hoja_vida_funcion", nullable = false, length = 2)
    private String psHojaVidaFuncion;

    public HojaDeVidaPK() {
    }

    public HojaDeVidaPK(int piHojaVidaConsecutivo, String psHojaVidaMatriz, String psHojaVidaFuncion) {
        this.piHojaVidaConsecutivo = piHojaVidaConsecutivo;
        this.psHojaVidaMatriz = psHojaVidaMatriz;
        this.psHojaVidaFuncion = psHojaVidaFuncion;
    }

    public int getPiHojaVidaConsecutivo() {
        return piHojaVidaConsecutivo;
    }

    public void setPiHojaVidaConsecutivo(int piHojaVidaConsecutivo) {
        this.piHojaVidaConsecutivo = piHojaVidaConsecutivo;
    }

    public String getPsHojaVidaMatriz() {
        return psHojaVidaMatriz;
    }

    public void setPsHojaVidaMatriz(String psHojaVidaMatriz) {
        this.psHojaVidaMatriz = psHojaVidaMatriz;
    }

    public String getPsHojaVidaFuncion() {
        return psHojaVidaFuncion;
    }

    public void setPsHojaVidaFuncion(String psHojaVidaFuncion) {
        this.psHojaVidaFuncion = psHojaVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piHojaVidaConsecutivo;
        hash += (psHojaVidaMatriz != null ? psHojaVidaMatriz.hashCode() : 0);
        hash += (psHojaVidaFuncion != null ? psHojaVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaDeVidaPK)) {
            return false;
        }
        HojaDeVidaPK other = (HojaDeVidaPK) object;
        if (this.piHojaVidaConsecutivo != other.piHojaVidaConsecutivo) {
            return false;
        }
        if ((this.psHojaVidaMatriz == null && other.psHojaVidaMatriz != null) || (this.psHojaVidaMatriz != null && !this.psHojaVidaMatriz.equals(other.psHojaVidaMatriz))) {
            return false;
        }
        if ((this.psHojaVidaFuncion == null && other.psHojaVidaFuncion != null) || (this.psHojaVidaFuncion != null && !this.psHojaVidaFuncion.equals(other.psHojaVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HojaDeVidaPK[ piHojaVidaConsecutivo=" + piHojaVidaConsecutivo + ", psHojaVidaMatriz=" + psHojaVidaMatriz + ", psHojaVidaFuncion=" + psHojaVidaFuncion + " ]";
    }
    
}
