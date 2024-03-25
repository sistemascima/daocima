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
public class EquipoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "fk_equipo_hoja_de_vida_consecutivo", nullable = false)
    private int fkEquipoHojaDeVidaConsecutivo;
    @Basic(optional = false)
    @Column(name = "fk_equipo_hoja_de_vida_matriz", nullable = false, length = 2)
    private String fkEquipoHojaDeVidaMatriz;
    @Basic(optional = false)
    @Column(name = "fk_equipo_hoja_de_vida_funcion", nullable = false, length = 2)
    private String fkEquipoHojaDeVidaFuncion;

    public EquipoPK() {
    }

    public EquipoPK(int fkEquipoHojaDeVidaConsecutivo, String fkEquipoHojaDeVidaMatriz, String fkEquipoHojaDeVidaFuncion) {
        this.fkEquipoHojaDeVidaConsecutivo = fkEquipoHojaDeVidaConsecutivo;
        this.fkEquipoHojaDeVidaMatriz = fkEquipoHojaDeVidaMatriz;
        this.fkEquipoHojaDeVidaFuncion = fkEquipoHojaDeVidaFuncion;
    }

    public int getFkEquipoHojaDeVidaConsecutivo() {
        return fkEquipoHojaDeVidaConsecutivo;
    }

    public void setFkEquipoHojaDeVidaConsecutivo(int fkEquipoHojaDeVidaConsecutivo) {
        this.fkEquipoHojaDeVidaConsecutivo = fkEquipoHojaDeVidaConsecutivo;
    }

    public String getFkEquipoHojaDeVidaMatriz() {
        return fkEquipoHojaDeVidaMatriz;
    }

    public void setFkEquipoHojaDeVidaMatriz(String fkEquipoHojaDeVidaMatriz) {
        this.fkEquipoHojaDeVidaMatriz = fkEquipoHojaDeVidaMatriz;
    }

    public String getFkEquipoHojaDeVidaFuncion() {
        return fkEquipoHojaDeVidaFuncion;
    }

    public void setFkEquipoHojaDeVidaFuncion(String fkEquipoHojaDeVidaFuncion) {
        this.fkEquipoHojaDeVidaFuncion = fkEquipoHojaDeVidaFuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkEquipoHojaDeVidaConsecutivo;
        hash += (fkEquipoHojaDeVidaMatriz != null ? fkEquipoHojaDeVidaMatriz.hashCode() : 0);
        hash += (fkEquipoHojaDeVidaFuncion != null ? fkEquipoHojaDeVidaFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoPK)) {
            return false;
        }
        EquipoPK other = (EquipoPK) object;
        if (this.fkEquipoHojaDeVidaConsecutivo != other.fkEquipoHojaDeVidaConsecutivo) {
            return false;
        }
        if ((this.fkEquipoHojaDeVidaMatriz == null && other.fkEquipoHojaDeVidaMatriz != null) || (this.fkEquipoHojaDeVidaMatriz != null && !this.fkEquipoHojaDeVidaMatriz.equals(other.fkEquipoHojaDeVidaMatriz))) {
            return false;
        }
        if ((this.fkEquipoHojaDeVidaFuncion == null && other.fkEquipoHojaDeVidaFuncion != null) || (this.fkEquipoHojaDeVidaFuncion != null && !this.fkEquipoHojaDeVidaFuncion.equals(other.fkEquipoHojaDeVidaFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EquipoPK[ fkEquipoHojaDeVidaConsecutivo=" + fkEquipoHojaDeVidaConsecutivo + ", fkEquipoHojaDeVidaMatriz=" + fkEquipoHojaDeVidaMatriz + ", fkEquipoHojaDeVidaFuncion=" + fkEquipoHojaDeVidaFuncion + " ]";
    }
    
}
