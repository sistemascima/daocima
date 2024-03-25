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
@Table(name = "vidrieria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vidreria.findAll", query = "SELECT v FROM Vidrieria v")
    , @NamedQuery(name = "Vidreria.findByFkVidreriaHojaDeVidaConsecutivo", query = "SELECT v FROM Vidrieria v WHERE v.vidreriaPK.fkVidreriaHojaDeVidaConsecutivo = :fkVidreriaHojaDeVidaConsecutivo")
    , @NamedQuery(name = "Vidreria.findByFkVidreriaHojaDeVidaMatriz", query = "SELECT v FROM Vidrieria v WHERE v.vidreriaPK.fkVidreriaHojaDeVidaMatriz = :fkVidreriaHojaDeVidaMatriz")
    , @NamedQuery(name = "Vidreria.findByFkVidreriaHojaDeVidaFuncion", query = "SELECT v FROM Vidrieria v WHERE v.vidreriaPK.fkVidreriaHojaDeVidaFuncion = :fkVidreriaHojaDeVidaFuncion")
    , @NamedQuery(name = "Vidreria.findByIVidreriaExactitud", query = "SELECT v FROM Vidrieria v WHERE v.iVidreriaExactitud = :iVidreriaExactitud")
    , @NamedQuery(name = "Vidreria.findBySVidreriaVarMag", query = "SELECT v FROM Vidrieria v WHERE v.sVidreriaVarMag = :sVidreriaVarMag")
    , @NamedQuery(name = "Vidreria.findByDVidreriaPerCar", query = "SELECT v FROM Vidrieria v WHERE v.dVidreriaPerCar = :dVidreriaPerCar")
    , @NamedQuery(name = "Vidreria.findByDVidreriaPerVar", query = "SELECT v FROM Vidrieria v WHERE v.dVidreriaPerVar = :dVidreriaPerVar")})
public class Vidrieria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VidreriaPK vidreriaPK;
    @Basic(optional = false)
    @Column(name = "i_vidrieria_exactitud", nullable = false)
    private int iVidreriaExactitud;
    @Column(name = "s_vidrieria_var_mag")
    private String sVidreriaVarMag;
    @Column(name = "d_vidrieria_per_car")
    @Temporal(TemporalType.DATE)
    private Date dVidreriaPerCar;
    @Column(name = "d_vidrieria_per_var")
    @Temporal(TemporalType.DATE)
    private Date dVidreriaPerVar;
    @JoinColumns({
        @JoinColumn(name = "fk_vidrieria_hoja_de_vida_consecutivo", referencedColumnName = "pi_hoja_vida_consecutivo", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_vidrieria_hoja_de_vida_matriz", referencedColumnName = "ps_hoja_vida_matriz", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_vidrieria_hoja_de_vida_funcion", referencedColumnName = "ps_hoja_vida_funcion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private HojaDeVida hojaDeVida;

    public Vidrieria() {
    }

    public Vidrieria(VidreriaPK vidreriaPK) {
        this.vidreriaPK = vidreriaPK;
    }

    public Vidrieria(VidreriaPK vidreriaPK, int iVidreriaExactitud) {
        this.vidreriaPK = vidreriaPK;
        this.iVidreriaExactitud = iVidreriaExactitud;
    }

    public Vidrieria(int fkVidreriaHojaDeVidaConsecutivo, String fkVidreriaHojaDeVidaMatriz, String fkVidreriaHojaDeVidaFuncion) {
        this.vidreriaPK = new VidreriaPK(fkVidreriaHojaDeVidaConsecutivo, fkVidreriaHojaDeVidaMatriz, fkVidreriaHojaDeVidaFuncion);
    }

    public VidreriaPK getVidreriaPK() {
        return vidreriaPK;
    }

    public void setVidreriaPK(VidreriaPK vidreriaPK) {
        this.vidreriaPK = vidreriaPK;
    }

    public int getIVidreriaExactitud() {
        return iVidreriaExactitud;
    }

    public void setIVidreriaExactitud(int iVidreriaExactitud) {
        this.iVidreriaExactitud = iVidreriaExactitud;
    }

    public String getSVidreriaVarMag() {
        return sVidreriaVarMag;
    }

    public void setSVidreriaVarMag(String sVidreriaVarMag) {
        this.sVidreriaVarMag = sVidreriaVarMag;
    }

    public Date getDVidreriaPerCar() {
        return dVidreriaPerCar;
    }

    public void setDVidreriaPerCar(Date dVidreriaPerCar) {
        this.dVidreriaPerCar = dVidreriaPerCar;
    }

    public Date getDVidreriaPerVar() {
        return dVidreriaPerVar;
    }

    public void setDVidreriaPerVar(Date dVidreriaPerVar) {
        this.dVidreriaPerVar = dVidreriaPerVar;
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
        hash += (vidreriaPK != null ? vidreriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vidrieria)) {
            return false;
        }
        Vidrieria other = (Vidrieria) object;
        if ((this.vidreriaPK == null && other.vidreriaPK != null) || (this.vidreriaPK != null && !this.vidreriaPK.equals(other.vidreriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vidreria[ vidreriaPK=" + vidreriaPK + " ]";
    }
    
}
