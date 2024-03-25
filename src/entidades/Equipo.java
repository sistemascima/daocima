/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByFkEquipoHojaDeVidaConsecutivo", query = "SELECT e FROM Equipo e WHERE e.equipoPK.fkEquipoHojaDeVidaConsecutivo = :fkEquipoHojaDeVidaConsecutivo")
    , @NamedQuery(name = "Equipo.findByFkEquipoHojaDeVidaMatriz", query = "SELECT e FROM Equipo e WHERE e.equipoPK.fkEquipoHojaDeVidaMatriz = :fkEquipoHojaDeVidaMatriz")
    , @NamedQuery(name = "Equipo.findByFkEquipoHojaDeVidaFuncion", query = "SELECT e FROM Equipo e WHERE e.equipoPK.fkEquipoHojaDeVidaFuncion = :fkEquipoHojaDeVidaFuncion")
    , @NamedQuery(name = "Equipo.findBySEquipoModelo", query = "SELECT e FROM Equipo e WHERE e.sEquipoModelo = :sEquipoModelo")
    , @NamedQuery(name = "Equipo.findBySEquipoSerial", query = "SELECT e FROM Equipo e WHERE e.sEquipoSerial = :sEquipoSerial")
    , @NamedQuery(name = "Equipo.findByDEquipoPerMantenimiento", query = "SELECT e FROM Equipo e WHERE e.dEquipoPerMantenimiento = :dEquipoPerMantenimiento")
    , @NamedQuery(name = "Equipo.findByDEquipoPerCalibracion", query = "SELECT e FROM Equipo e WHERE e.dEquipoPerCalibracion = :dEquipoPerCalibracion")
    , @NamedQuery(name = "Equipo.findBySEquipoAccesorios", query = "SELECT e FROM Equipo e WHERE e.sEquipoAccesorios = :sEquipoAccesorios")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoPK equipoPK;
    @Basic(optional = false)
    @Column(name = "s_equipo_modelo", nullable = false, length = 45)
    private String sEquipoModelo;
    @Basic(optional = false)
    @Column(name = "s_equipo_serial", nullable = false, length = 45)
    private String sEquipoSerial;
    @Column(name = "d_equipo_per_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date dEquipoPerMantenimiento;
    @Column(name = "d_equipo_per_calibracion")
    @Temporal(TemporalType.DATE)
    private Date dEquipoPerCalibracion;
    @Column(name = "s_equipo_accesorios", length = 45)
    private String sEquipoAccesorios;
    @JoinColumns({
        @JoinColumn(name = "fk_equipo_hoja_de_vida_consecutivo", referencedColumnName = "pi_hoja_vida_consecutivo", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_equipo_hoja_de_vida_matriz", referencedColumnName = "ps_hoja_vida_matriz", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_equipo_hoja_de_vida_funcion", referencedColumnName = "ps_hoja_vida_funcion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private HojaDeVida hojaDeVida;

    public Equipo() {
    }

    public Equipo(EquipoPK equipoPK) {
        this.equipoPK = equipoPK;
    }

    public Equipo(EquipoPK equipoPK, String sEquipoModelo, String sEquipoSerial) {
        this.equipoPK = equipoPK;
        this.sEquipoModelo = sEquipoModelo;
        this.sEquipoSerial = sEquipoSerial;
    }

    public Equipo(int fkEquipoHojaDeVidaConsecutivo, String fkEquipoHojaDeVidaMatriz, String fkEquipoHojaDeVidaFuncion) {
        this.equipoPK = new EquipoPK(fkEquipoHojaDeVidaConsecutivo, fkEquipoHojaDeVidaMatriz, fkEquipoHojaDeVidaFuncion);
    }

    public EquipoPK getEquipoPK() {
        return equipoPK;
    }

    public void setEquipoPK(EquipoPK equipoPK) {
        this.equipoPK = equipoPK;
    }

    public String getSEquipoModelo() {
        return sEquipoModelo;
    }

    public void setSEquipoModelo(String sEquipoModelo) {
        this.sEquipoModelo = sEquipoModelo;
    }

    public String getSEquipoSerial() {
        return sEquipoSerial;
    }

    public void setSEquipoSerial(String sEquipoSerial) {
        this.sEquipoSerial = sEquipoSerial;
    }

    public Date getDEquipoPerMantenimiento() {
        return dEquipoPerMantenimiento;
    }

    public void setDEquipoPerMantenimiento(Date dEquipoPerMantenimiento) {
        this.dEquipoPerMantenimiento = dEquipoPerMantenimiento;
    }

    public Date getDEquipoPerCalibracion() {
        return dEquipoPerCalibracion;
    }

    public void setDEquipoPerCalibracion(Date dEquipoPerCalibracion) {
        this.dEquipoPerCalibracion = dEquipoPerCalibracion;
    }

    public String getSEquipoAccesorios() {
        return sEquipoAccesorios;
    }

    public void setSEquipoAccesorios(String sEquipoAccesorios) {
        this.sEquipoAccesorios = sEquipoAccesorios;
    }

    public HojaDeVida getHojaDeVida() {
        return hojaDeVida;
    }

    public void setHojaDeVida(HojaDeVida hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoPK != null ? equipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.equipoPK == null && other.equipoPK != null) || (this.equipoPK != null && !this.equipoPK.equals(other.equipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Equipo[ equipoPK=" + equipoPK + " ]";
    }
    
}
