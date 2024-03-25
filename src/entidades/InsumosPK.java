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
public class InsumosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "fk_insumos_hoja_de_vida_consecutivo", nullable = false)
    private int fkInsumosHojaDeVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "fk_insumos_hoja_de_vida_matriz", nullable = false, length = 2)
    private String fkInsumosHojaDeVidaMatriz;
    @Basic(optional = false)
    @Column(name = "fk_insumos_hoja_de_vida_funcion", nullable = false, length = 2)
    private String fkInsumosHojaDeVidaFuncion;

    public InsumosPK() {
    }

    public InsumosPK(int fkInsumosHojaDeVidaConsecutivo, String fkInsumosHojaDeVidaMatriz, String fkInsumosHojaDeVidaFuncion) {
        this.fkInsumosHojaDeVidaConsecutivo = fkInsumosHojaDeVidaConsecutivo;
        this.fkInsumosHojaDeVidaMatriz = fkInsumosHojaDeVidaMatriz;
        this.fkInsumosHojaDeVidaFuncion = fkInsumosHojaDeVidaFuncion;
    }

    public int getFkInsumosHojaDeVidaConsecutivo() {
        return fkInsumosHojaDeVidaConsecutivo;
    }

    public void setFkInsumosHojaDeVidaConsecutivo(int fkInsumosHojaDeVidaConsecutivo) {
        this.fkInsumosHojaDeVidaConsecutivo = fkInsumosHojaDeVidaConsecutivo;
    }

    public String getFkInsumosHojaDeVidaMatriz() {
        return fkInsumosHojaDeVidaMatriz;
    }

    public void setFkInsumosHojaDeVidaMatriz(String fkInsumosHojaDeVidaMatriz) {
        this.fkInsumosHojaDeVidaMatriz = fkInsumosHojaDeVidaMatriz;
    }

    public String getFkInsumosHojaDeVidaFuncion() {
        return fkInsumosHojaDeVidaFuncion;
    }

    public void setFkInsumosHojaDeVidaFuncion(String fkInsumosHojaDeVidaFuncion) {
        this.fkInsumosHojaDeVidaFuncion = fkInsumosHojaDeVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkInsumosHojaDeVidaConsecutivo;
        hash += (fkInsumosHojaDeVidaMatriz != null ? fkInsumosHojaDeVidaMatriz.hashCode() : 0);
        hash += (fkInsumosHojaDeVidaFuncion != null ? fkInsumosHojaDeVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumosPK)) {
            return false;
        }
        InsumosPK other = (InsumosPK) object;
        if (this.fkInsumosHojaDeVidaConsecutivo != other.fkInsumosHojaDeVidaConsecutivo) {
            return false;
        }
        if ((this.fkInsumosHojaDeVidaMatriz == null && other.fkInsumosHojaDeVidaMatriz != null) || (this.fkInsumosHojaDeVidaMatriz != null && !this.fkInsumosHojaDeVidaMatriz.equals(other.fkInsumosHojaDeVidaMatriz))) {
            return false;
        }
        if ((this.fkInsumosHojaDeVidaFuncion == null && other.fkInsumosHojaDeVidaFuncion != null) || (this.fkInsumosHojaDeVidaFuncion != null && !this.fkInsumosHojaDeVidaFuncion.equals(other.fkInsumosHojaDeVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InsumosPK[ fkInsumosHojaDeVidaConsecutivo=" + fkInsumosHojaDeVidaConsecutivo + ", fkInsumosHojaDeVidaMatriz=" + fkInsumosHojaDeVidaMatriz + ", fkInsumosHojaDeVidaFuncion=" + fkInsumosHojaDeVidaFuncion + " ]";
    }
    
}
