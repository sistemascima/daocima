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
public class VehiculoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "fk_vehiculo_hoja_de_vida_consecutivo", nullable = false)
    private int fkVehiculoHojaDeVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "fk_vehiculo_hoja_de_vida_matriz", nullable = false, length = 2)
    private String fkVehiculoHojaDeVidaMatriz;
    @Basic(optional = false)
    @Column(name = "fk_vehiculo_hoja_de_vida_funcion", nullable = false, length = 2)
    private String fkVehiculoHojaDeVidaFuncion;

    public VehiculoPK() {
    }

    public VehiculoPK(int fkVehiculoHojaDeVidaConsecutivo, String fkVehiculoHojaDeVidaMatriz, String fkVehiculoHojaDeVidaFuncion) {
        this.fkVehiculoHojaDeVidaConsecutivo = fkVehiculoHojaDeVidaConsecutivo;
        this.fkVehiculoHojaDeVidaMatriz = fkVehiculoHojaDeVidaMatriz;
        this.fkVehiculoHojaDeVidaFuncion = fkVehiculoHojaDeVidaFuncion;
    }

    public int getFkVehiculoHojaDeVidaConsecutivo() {
        return fkVehiculoHojaDeVidaConsecutivo;
    }

    public void setFkVehiculoHojaDeVidaConsecutivo(int fkVehiculoHojaDeVidaConsecutivo) {
        this.fkVehiculoHojaDeVidaConsecutivo = fkVehiculoHojaDeVidaConsecutivo;
    }

    public String getFkVehiculoHojaDeVidaMatriz() {
        return fkVehiculoHojaDeVidaMatriz;
    }

    public void setFkVehiculoHojaDeVidaMatriz(String fkVehiculoHojaDeVidaMatriz) {
        this.fkVehiculoHojaDeVidaMatriz = fkVehiculoHojaDeVidaMatriz;
    }

    public String getFkVehiculoHojaDeVidaFuncion() {
        return fkVehiculoHojaDeVidaFuncion;
    }

    public void setFkVehiculoHojaDeVidaFuncion(String fkVehiculoHojaDeVidaFuncion) {
        this.fkVehiculoHojaDeVidaFuncion = fkVehiculoHojaDeVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkVehiculoHojaDeVidaConsecutivo;
        hash += (fkVehiculoHojaDeVidaMatriz != null ? fkVehiculoHojaDeVidaMatriz.hashCode() : 0);
        hash += (fkVehiculoHojaDeVidaFuncion != null ? fkVehiculoHojaDeVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculoPK)) {
            return false;
        }
        VehiculoPK other = (VehiculoPK) object;
        if (this.fkVehiculoHojaDeVidaConsecutivo != other.fkVehiculoHojaDeVidaConsecutivo) {
            return false;
        }
        if ((this.fkVehiculoHojaDeVidaMatriz == null && other.fkVehiculoHojaDeVidaMatriz != null) || (this.fkVehiculoHojaDeVidaMatriz != null && !this.fkVehiculoHojaDeVidaMatriz.equals(other.fkVehiculoHojaDeVidaMatriz))) {
            return false;
        }
        if ((this.fkVehiculoHojaDeVidaFuncion == null && other.fkVehiculoHojaDeVidaFuncion != null) || (this.fkVehiculoHojaDeVidaFuncion != null && !this.fkVehiculoHojaDeVidaFuncion.equals(other.fkVehiculoHojaDeVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VehiculoPK[ fkVehiculoHojaDeVidaConsecutivo=" + fkVehiculoHojaDeVidaConsecutivo + ", fkVehiculoHojaDeVidaMatriz=" + fkVehiculoHojaDeVidaMatriz + ", fkVehiculoHojaDeVidaFuncion=" + fkVehiculoHojaDeVidaFuncion + " ]";
    }
    
}
