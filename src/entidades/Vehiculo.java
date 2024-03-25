/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByFkVehiculoHojaDeVidaConsecutivo", query = "SELECT v FROM Vehiculo v WHERE v.vehiculoPK.fkVehiculoHojaDeVidaConsecutivo = :fkVehiculoHojaDeVidaConsecutivo")
    , @NamedQuery(name = "Vehiculo.findByFkVehiculoHojaDeVidaMatriz", query = "SELECT v FROM Vehiculo v WHERE v.vehiculoPK.fkVehiculoHojaDeVidaMatriz = :fkVehiculoHojaDeVidaMatriz")
    , @NamedQuery(name = "Vehiculo.findByFkVehiculoHojaDeVidaFuncion", query = "SELECT v FROM Vehiculo v WHERE v.vehiculoPK.fkVehiculoHojaDeVidaFuncion = :fkVehiculoHojaDeVidaFuncion")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VehiculoPK vehiculoPK;
    @JoinColumns({
        @JoinColumn(name = "fk_vehiculo_hoja_de_vida_consecutivo", referencedColumnName = "pi_hoja_vida_consecutivo", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_vehiculo_hoja_de_vida_matriz", referencedColumnName = "ps_hoja_vida_matriz", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_vehiculo_hoja_de_vida_funcion", referencedColumnName = "ps_hoja_vida_funcion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private HojaDeVida hojaDeVida;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private RevisionesVehiculo revisionesVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public Vehiculo(int fkVehiculoHojaDeVidaConsecutivo, String fkVehiculoHojaDeVidaMatriz, String fkVehiculoHojaDeVidaFuncion) {
        this.vehiculoPK = new VehiculoPK(fkVehiculoHojaDeVidaConsecutivo, fkVehiculoHojaDeVidaMatriz, fkVehiculoHojaDeVidaFuncion);
    }

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public HojaDeVida getHojaDeVida() {
        return hojaDeVida;
    }

    public void setHojaDeVida(HojaDeVida hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }

    public RevisionesVehiculo getRevisionesVehiculo() {
        return revisionesVehiculo;
    }

    public void setRevisionesVehiculo(RevisionesVehiculo revisionesVehiculo) {
        this.revisionesVehiculo = revisionesVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehiculoPK != null ? vehiculoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.vehiculoPK == null && other.vehiculoPK != null) || (this.vehiculoPK != null && !this.vehiculoPK.equals(other.vehiculoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vehiculo[ vehiculoPK=" + vehiculoPK + " ]";
    }
    
}
