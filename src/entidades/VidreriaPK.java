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
public class VidreriaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "fk_vidrieria_hoja_de_vida_consecutivo", nullable = false)
    private int fkVidreriaHojaDeVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "fk_vidrieria_hoja_de_vida_matriz", nullable = false, length = 2)
    private String fkVidreriaHojaDeVidaMatriz;
    @Basic(optional = false)
    @Column(name = "fk_vidrieria_hoja_de_vida_funcion", nullable = false, length = 2)
    private String fkVidreriaHojaDeVidaFuncion;

    public VidreriaPK() {
    }

    public VidreriaPK(int fkVidreriaHojaDeVidaConsecutivo, String fkVidreriaHojaDeVidaMatriz, String fkVidreriaHojaDeVidaFuncion) {
        this.fkVidreriaHojaDeVidaConsecutivo = fkVidreriaHojaDeVidaConsecutivo;
        this.fkVidreriaHojaDeVidaMatriz = fkVidreriaHojaDeVidaMatriz;
        this.fkVidreriaHojaDeVidaFuncion = fkVidreriaHojaDeVidaFuncion;
    }

    public int getFkVidreriaHojaDeVidaConsecutivo() {
        return fkVidreriaHojaDeVidaConsecutivo;
    }

    public void setFkVidreriaHojaDeVidaConsecutivo(int fkVidreriaHojaDeVidaConsecutivo) {
        this.fkVidreriaHojaDeVidaConsecutivo = fkVidreriaHojaDeVidaConsecutivo;
    }

    public String getFkVidreriaHojaDeVidaMatriz() {
        return fkVidreriaHojaDeVidaMatriz;
    }

    public void setFkVidreriaHojaDeVidaMatriz(String fkVidreriaHojaDeVidaMatriz) {
        this.fkVidreriaHojaDeVidaMatriz = fkVidreriaHojaDeVidaMatriz;
    }

    public String getFkVidreriaHojaDeVidaFuncion() {
        return fkVidreriaHojaDeVidaFuncion;
    }

    public void setFkVidreriaHojaDeVidaFuncion(String fkVidreriaHojaDeVidaFuncion) {
        this.fkVidreriaHojaDeVidaFuncion = fkVidreriaHojaDeVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkVidreriaHojaDeVidaConsecutivo;
        hash += (fkVidreriaHojaDeVidaMatriz != null ? fkVidreriaHojaDeVidaMatriz.hashCode() : 0);
        hash += (fkVidreriaHojaDeVidaFuncion != null ? fkVidreriaHojaDeVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VidreriaPK)) {
            return false;
        }
        VidreriaPK other = (VidreriaPK) object;
        if (this.fkVidreriaHojaDeVidaConsecutivo != other.fkVidreriaHojaDeVidaConsecutivo) {
            return false;
        }
        if ((this.fkVidreriaHojaDeVidaMatriz == null && other.fkVidreriaHojaDeVidaMatriz != null) || (this.fkVidreriaHojaDeVidaMatriz != null && !this.fkVidreriaHojaDeVidaMatriz.equals(other.fkVidreriaHojaDeVidaMatriz))) {
            return false;
        }
        if ((this.fkVidreriaHojaDeVidaFuncion == null && other.fkVidreriaHojaDeVidaFuncion != null) || (this.fkVidreriaHojaDeVidaFuncion != null && !this.fkVidreriaHojaDeVidaFuncion.equals(other.fkVidreriaHojaDeVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VidreriaPK[ fkVidreriaHojaDeVidaConsecutivo=" + fkVidreriaHojaDeVidaConsecutivo + ", fkVidreriaHojaDeVidaMatriz=" + fkVidreriaHojaDeVidaMatriz + ", fkVidreriaHojaDeVidaFuncion=" + fkVidreriaHojaDeVidaFuncion + " ]";
    }
    
}
