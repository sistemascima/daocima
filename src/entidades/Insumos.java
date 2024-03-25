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
@Table(name = "insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumos.findAll", query = "SELECT i FROM Insumos i")
    , @NamedQuery(name = "Insumos.findByFkInsumosHojaDeVidaConsecutivo", query = "SELECT i FROM Insumos i WHERE i.insumosPK.fkInsumosHojaDeVidaConsecutivo = :fkInsumosHojaDeVidaConsecutivo")
    , @NamedQuery(name = "Insumos.findByFkInsumosHojaDeVidaMatriz", query = "SELECT i FROM Insumos i WHERE i.insumosPK.fkInsumosHojaDeVidaMatriz = :fkInsumosHojaDeVidaMatriz")
    , @NamedQuery(name = "Insumos.findByFkInsumosHojaDeVidaFuncion", query = "SELECT i FROM Insumos i WHERE i.insumosPK.fkInsumosHojaDeVidaFuncion = :fkInsumosHojaDeVidaFuncion")
    , @NamedQuery(name = "Insumos.findBySInsumosMarca", query = "SELECT i FROM Insumos i WHERE i.sInsumosMarca = :sInsumosMarca")
    , @NamedQuery(name = "Insumos.findBySInsumosVarMag", query = "SELECT i FROM Insumos i WHERE i.sInsumosVarMag = :sInsumosVarMag")
    , @NamedQuery(name = "Insumos.findByDInsumosPerVer", query = "SELECT i FROM Insumos i WHERE i.dInsumosPerVer = :dInsumosPerVer")
    , @NamedQuery(name = "Insumos.findBySInsumosResolucion", query = "SELECT i FROM Insumos i WHERE i.sInsumosResolucion = :sInsumosResolucion")})
public class Insumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsumosPK insumosPK;
    @Basic(optional = false)
    @Column(name = "s_insumos_marca", nullable = false, length = 45)
    private String sInsumosMarca;
    @Column(name = "s_insumos_var_mag", length = 45)
    private String sInsumosVarMag;
    @Column(name = "d_insumos_per_ver")
    @Temporal(TemporalType.DATE)
    private Date dInsumosPerVer;
    @Column(name = "s_insumos_resolucion", length = 45)
    private String sInsumosResolucion;
    @JoinColumns({
        @JoinColumn(name = "fk_insumos_hoja_de_vida_consecutivo", referencedColumnName = "pi_hoja_vida_consecutivo", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_insumos_hoja_de_vida_matriz", referencedColumnName = "ps_hoja_vida_matriz", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "fk_insumos_hoja_de_vida_funcion", referencedColumnName = "ps_hoja_vida_funcion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private HojaDeVida hojaDeVida;

    public Insumos() {
    }

    public Insumos(InsumosPK insumosPK) {
        this.insumosPK = insumosPK;
    }

    public Insumos(InsumosPK insumosPK, String sInsumosMarca) {
        this.insumosPK = insumosPK;
        this.sInsumosMarca = sInsumosMarca;
    }

    public Insumos(int fkInsumosHojaDeVidaConsecutivo, String fkInsumosHojaDeVidaMatriz, String fkInsumosHojaDeVidaFuncion) {
        this.insumosPK = new InsumosPK(fkInsumosHojaDeVidaConsecutivo, fkInsumosHojaDeVidaMatriz, fkInsumosHojaDeVidaFuncion);
    }

    public InsumosPK getInsumosPK() {
        return insumosPK;
    }

    public void setInsumosPK(InsumosPK insumosPK) {
        this.insumosPK = insumosPK;
    }

    public String getSInsumosMarca() {
        return sInsumosMarca;
    }

    public void setSInsumosMarca(String sInsumosMarca) {
        this.sInsumosMarca = sInsumosMarca;
    }

    public String getSInsumosVarMag() {
        return sInsumosVarMag;
    }

    public void setSInsumosVarMag(String sInsumosVarMag) {
        this.sInsumosVarMag = sInsumosVarMag;
    }

    public Date getDInsumosPerVer() {
        return dInsumosPerVer;
    }

    public void setDInsumosPerVer(Date dInsumosPerVer) {
        this.dInsumosPerVer = dInsumosPerVer;
    }

    public String getSInsumosResolucion() {
        return sInsumosResolucion;
    }

    public void setSInsumosResolucion(String sInsumosResolucion) {
        this.sInsumosResolucion = sInsumosResolucion;
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
        hash += (insumosPK != null ? insumosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumos)) {
            return false;
        }
        Insumos other = (Insumos) object;
        if ((this.insumosPK == null && other.insumosPK != null) || (this.insumosPK != null && !this.insumosPK.equals(other.insumosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Insumos[ insumosPK=" + insumosPK + " ]";
    }
    
}
