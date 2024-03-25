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
public class MuestraAnalisisHistoricoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_muestra_analisis_historico_id", nullable = false)
    private int piMuestraAnalisisHistoricoId;
    @Basic(optional = false)
    @Column(name = "fs_muestra_analisis_historico_reporte_version", nullable = false)
    private int fsMuestraAnalisisHistoricoReporteVersion;

    public MuestraAnalisisHistoricoPK() {
    }

    public MuestraAnalisisHistoricoPK(int piMuestraAnalisisHistoricoId, int fsMuestraAnalisisHistoricoReporteVersion) {
        this.piMuestraAnalisisHistoricoId = piMuestraAnalisisHistoricoId;
        this.fsMuestraAnalisisHistoricoReporteVersion = fsMuestraAnalisisHistoricoReporteVersion;
    }

    public int getPiMuestraAnalisisHistoricoId() {
        return piMuestraAnalisisHistoricoId;
    }

    public void setPiMuestraAnalisisHistoricoId(int piMuestraAnalisisHistoricoId) {
        this.piMuestraAnalisisHistoricoId = piMuestraAnalisisHistoricoId;
    }

    public int getFsMuestraAnalisisHistoricoReporteVersion() {
        return fsMuestraAnalisisHistoricoReporteVersion;
    }

    public void setFsMuestraAnalisisHistoricoReporteVersion(int fsMuestraAnalisisHistoricoReporteVersion) {
        this.fsMuestraAnalisisHistoricoReporteVersion = fsMuestraAnalisisHistoricoReporteVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piMuestraAnalisisHistoricoId;
        hash += (int) fsMuestraAnalisisHistoricoReporteVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraAnalisisHistoricoPK)) {
            return false;
        }
        MuestraAnalisisHistoricoPK other = (MuestraAnalisisHistoricoPK) object;
        if (this.piMuestraAnalisisHistoricoId != other.piMuestraAnalisisHistoricoId) {
            return false;
        }
        if (this.fsMuestraAnalisisHistoricoReporteVersion != other.fsMuestraAnalisisHistoricoReporteVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestraAnalisisHistoricoPK[ piMuestraAnalisisHistoricoId=" + piMuestraAnalisisHistoricoId + ", fsMuestraAnalisisHistoricoReporteVersion=" + fsMuestraAnalisisHistoricoReporteVersion + " ]";
    }
    
}
