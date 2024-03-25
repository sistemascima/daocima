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
 * @author TOSHIBA
 */
@Embeddable
public class MuestraHistoricoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_muestra_historico_id", nullable = false)
    private int piMuestraHistoricoId;
    @Basic(optional = false)
    @Column(name = "fi_muestra_historico_reporte_version", nullable = false)
    private int fiMuestraHistoricoReporteVersion;

    public MuestraHistoricoPK() {
    }

    public MuestraHistoricoPK(int piMuestraHistoricoId, int fiMuestraHistoricoReporteVersion) {
        this.piMuestraHistoricoId = piMuestraHistoricoId;
        this.fiMuestraHistoricoReporteVersion = fiMuestraHistoricoReporteVersion;
    }

    public int getPiMuestraHistoricoId() {
        return piMuestraHistoricoId;
    }

    public void setPiMuestraHistoricoId(int piMuestraHistoricoId) {
        this.piMuestraHistoricoId = piMuestraHistoricoId;
    }

    public int getFiMuestraHistoricoReporteVersion() {
        return fiMuestraHistoricoReporteVersion;
    }

    public void setFiMuestraHistoricoReporteVersion(int fiMuestraHistoricoReporteVersion) {
        this.fiMuestraHistoricoReporteVersion = fiMuestraHistoricoReporteVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piMuestraHistoricoId;
        hash += (int) fiMuestraHistoricoReporteVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraHistoricoPK)) {
            return false;
        }
        MuestraHistoricoPK other = (MuestraHistoricoPK) object;
        if (this.piMuestraHistoricoId != other.piMuestraHistoricoId) {
            return false;
        }
        if (this.fiMuestraHistoricoReporteVersion != other.fiMuestraHistoricoReporteVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestraHistoricoPK[ piMuestraHistoricoId=" + piMuestraHistoricoId + ", fiMuestraHistoricoReporteVersion=" + fiMuestraHistoricoReporteVersion + " ]";
    }
    
}
