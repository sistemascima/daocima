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
public class RevisionesVehiculoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_revisiones_vehiculo_consecutivo_id", nullable = false)
    private int piRevisionesVehiculoConsecutivoId;
    @Basic(optional = false)
    @Column(name = "ps_revisiones_vehiculo_matriz_id", nullable = false, length = 2)
    private String psRevisionesVehiculoMatrizId;
    @Basic(optional = false)
    @Column(name = "ps_revisiones_vehiculo_funcion_id", nullable = false, length = 2)
    private String psRevisionesVehiculoFuncionId;

    public RevisionesVehiculoPK() {
    }

    public RevisionesVehiculoPK(int piRevisionesVehiculoConsecutivoId, String psRevisionesVehiculoMatrizId, String psRevisionesVehiculoFuncionId) {
        this.piRevisionesVehiculoConsecutivoId = piRevisionesVehiculoConsecutivoId;
        this.psRevisionesVehiculoMatrizId = psRevisionesVehiculoMatrizId;
        this.psRevisionesVehiculoFuncionId = psRevisionesVehiculoFuncionId;
    }

    public int getPiRevisionesVehiculoConsecutivoId() {
        return piRevisionesVehiculoConsecutivoId;
    }

    public void setPiRevisionesVehiculoConsecutivoId(int piRevisionesVehiculoConsecutivoId) {
        this.piRevisionesVehiculoConsecutivoId = piRevisionesVehiculoConsecutivoId;
    }

    public String getPsRevisionesVehiculoMatrizId() {
        return psRevisionesVehiculoMatrizId;
    }

    public void setPsRevisionesVehiculoMatrizId(String psRevisionesVehiculoMatrizId) {
        this.psRevisionesVehiculoMatrizId = psRevisionesVehiculoMatrizId;
    }

    public String getPsRevisionesVehiculoFuncionId() {
        return psRevisionesVehiculoFuncionId;
    }

    public void setPsRevisionesVehiculoFuncionId(String psRevisionesVehiculoFuncionId) {
        this.psRevisionesVehiculoFuncionId = psRevisionesVehiculoFuncionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) piRevisionesVehiculoConsecutivoId;
        hash += (psRevisionesVehiculoMatrizId != null ? psRevisionesVehiculoMatrizId.hashCode() : 0);
        hash += (psRevisionesVehiculoFuncionId != null ? psRevisionesVehiculoFuncionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RevisionesVehiculoPK)) {
            return false;
        }
        RevisionesVehiculoPK other = (RevisionesVehiculoPK) object;
        if (this.piRevisionesVehiculoConsecutivoId != other.piRevisionesVehiculoConsecutivoId) {
            return false;
        }
        if ((this.psRevisionesVehiculoMatrizId == null && other.psRevisionesVehiculoMatrizId != null) || (this.psRevisionesVehiculoMatrizId != null && !this.psRevisionesVehiculoMatrizId.equals(other.psRevisionesVehiculoMatrizId))) {
            return false;
        }
        if ((this.psRevisionesVehiculoFuncionId == null && other.psRevisionesVehiculoFuncionId != null) || (this.psRevisionesVehiculoFuncionId != null && !this.psRevisionesVehiculoFuncionId.equals(other.psRevisionesVehiculoFuncionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RevisionesVehiculoPK[ piRevisionesVehiculoConsecutivoId=" + piRevisionesVehiculoConsecutivoId + ", psRevisionesVehiculoMatrizId=" + psRevisionesVehiculoMatrizId + ", psRevisionesVehiculoFuncionId=" + psRevisionesVehiculoFuncionId + " ]";
    }
    
}
