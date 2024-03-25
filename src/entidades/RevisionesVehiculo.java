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
@Table(name = "revisiones_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RevisionesVehiculo.findAll", query = "SELECT r FROM RevisionesVehiculo r")
    , @NamedQuery(name = "RevisionesVehiculo.findByPiRevisionesVehiculoConsecutivoId", query = "SELECT r FROM RevisionesVehiculo r WHERE r.revisionesVehiculoPK.piRevisionesVehiculoConsecutivoId = :piRevisionesVehiculoConsecutivoId")
    , @NamedQuery(name = "RevisionesVehiculo.findByPsRevisionesVehiculoMatrizId", query = "SELECT r FROM RevisionesVehiculo r WHERE r.revisionesVehiculoPK.psRevisionesVehiculoMatrizId = :psRevisionesVehiculoMatrizId")
    , @NamedQuery(name = "RevisionesVehiculo.findByPsRevisionesVehiculoFuncionId", query = "SELECT r FROM RevisionesVehiculo r WHERE r.revisionesVehiculoPK.psRevisionesVehiculoFuncionId = :psRevisionesVehiculoFuncionId")
    , @NamedQuery(name = "RevisionesVehiculo.findBySRevicionesNombre", query = "SELECT r FROM RevisionesVehiculo r WHERE r.sRevicionesNombre = :sRevicionesNombre")
    , @NamedQuery(name = "RevisionesVehiculo.findByDRevisionesFecha", query = "SELECT r FROM RevisionesVehiculo r WHERE r.dRevisionesFecha = :dRevisionesFecha")})
public class RevisionesVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RevisionesVehiculoPK revisionesVehiculoPK;
    @Basic(optional = false)
    @Column(name = "s_reviciones_nombre", nullable = false, length = 45)
    private String sRevicionesNombre;
    @Basic(optional = false)
    @Column(name = "d_revisiones_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dRevisionesFecha;
    @JoinColumns({
        @JoinColumn(name = "pi_revisiones_vehiculo_consecutivo_id", referencedColumnName = "fk_vehiculo_hoja_de_vida_consecutivo", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "ps_revisiones_vehiculo_matriz_id", referencedColumnName = "fk_vehiculo_hoja_de_vida_matriz", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "ps_revisiones_vehiculo_funcion_id", referencedColumnName = "fk_vehiculo_hoja_de_vida_funcion", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Vehiculo vehiculo;

    public RevisionesVehiculo() {
    }

    public RevisionesVehiculo(RevisionesVehiculoPK revisionesVehiculoPK) {
        this.revisionesVehiculoPK = revisionesVehiculoPK;
    }

    public RevisionesVehiculo(RevisionesVehiculoPK revisionesVehiculoPK, String sRevicionesNombre, Date dRevisionesFecha) {
        this.revisionesVehiculoPK = revisionesVehiculoPK;
        this.sRevicionesNombre = sRevicionesNombre;
        this.dRevisionesFecha = dRevisionesFecha;
    }

    public RevisionesVehiculo(int piRevisionesVehiculoConsecutivoId, String psRevisionesVehiculoMatrizId, String psRevisionesVehiculoFuncionId) {
        this.revisionesVehiculoPK = new RevisionesVehiculoPK(piRevisionesVehiculoConsecutivoId, psRevisionesVehiculoMatrizId, psRevisionesVehiculoFuncionId);
    }

    public RevisionesVehiculoPK getRevisionesVehiculoPK() {
        return revisionesVehiculoPK;
    }

    public void setRevisionesVehiculoPK(RevisionesVehiculoPK revisionesVehiculoPK) {
        this.revisionesVehiculoPK = revisionesVehiculoPK;
    }

    public String getSRevicionesNombre() {
        return sRevicionesNombre;
    }

    public void setSRevicionesNombre(String sRevicionesNombre) {
        this.sRevicionesNombre = sRevicionesNombre;
    }

    public Date getDRevisionesFecha() {
        return dRevisionesFecha;
    }

    public void setDRevisionesFecha(Date dRevisionesFecha) {
        this.dRevisionesFecha = dRevisionesFecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revisionesVehiculoPK != null ? revisionesVehiculoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RevisionesVehiculo)) {
            return false;
        }
        RevisionesVehiculo other = (RevisionesVehiculo) object;
        if ((this.revisionesVehiculoPK == null && other.revisionesVehiculoPK != null) || (this.revisionesVehiculoPK != null && !this.revisionesVehiculoPK.equals(other.revisionesVehiculoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RevisionesVehiculo[ revisionesVehiculoPK=" + revisionesVehiculoPK + " ]";
    }
    
}
