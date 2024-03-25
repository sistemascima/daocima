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
public class ReporteHistoricoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_reporte_historico_id", nullable = false, length = 20)
    private String piReporteHistoricoId;
    @Basic(optional = false)
    @Column(name = "i_reporte_historico_version", nullable = false)
    private int iReporteHistoricoVersion;

    public ReporteHistoricoPK() {
    }

    public ReporteHistoricoPK(String piReporteHistoricoId, int iReporteHistoricoVersion) {
        this.piReporteHistoricoId = piReporteHistoricoId;
        this.iReporteHistoricoVersion = iReporteHistoricoVersion;
    }

    public String getPiReporteHistoricoId() {
        return piReporteHistoricoId;
    }

    public void setPiReporteHistoricoId(String piReporteHistoricoId) {
        this.piReporteHistoricoId = piReporteHistoricoId;
    }

    public int getIReporteHistoricoVersion() {
        return iReporteHistoricoVersion;
    }

    public void setIReporteHistoricoVersion(int iReporteHistoricoVersion) {
        this.iReporteHistoricoVersion = iReporteHistoricoVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piReporteHistoricoId != null ? piReporteHistoricoId.hashCode() : 0);
        hash += (int) iReporteHistoricoVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteHistoricoPK)) {
            return false;
        }
        ReporteHistoricoPK other = (ReporteHistoricoPK) object;
        if ((this.piReporteHistoricoId == null && other.piReporteHistoricoId != null) || (this.piReporteHistoricoId != null && !this.piReporteHistoricoId.equals(other.piReporteHistoricoId))) {
            return false;
        }
        if (this.iReporteHistoricoVersion != other.iReporteHistoricoVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReporteHistoricoPK[ piReporteHistoricoId=" + piReporteHistoricoId + ", iReporteHistoricoVersion=" + iReporteHistoricoVersion + " ]";
    }
    
}
