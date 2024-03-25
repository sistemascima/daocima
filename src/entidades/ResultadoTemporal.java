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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "resultado_temporal", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoTemporal.findAll", query = "SELECT r FROM ResultadoTemporal r")
    , @NamedQuery(name = "ResultadoTemporal.findByPiResultadoTemporalId", query = "SELECT r FROM ResultadoTemporal r WHERE r.piResultadoTemporalId = :piResultadoTemporalId")
    , @NamedQuery(name = "ResultadoTemporal.findBySResultadoTemporalValor", query = "SELECT r FROM ResultadoTemporal r WHERE r.sResultadoTemporalValor = :sResultadoTemporalValor")
    , @NamedQuery(name = "ResultadoTemporal.findByDResultadoTemporalFechingresoAnalista", query = "SELECT r FROM ResultadoTemporal r WHERE r.dResultadoTemporalFechingresoAnalista = :dResultadoTemporalFechingresoAnalista")
    , @NamedQuery(name = "ResultadoTemporal.findByDResultadoTemporalFechregistro", query = "SELECT r FROM ResultadoTemporal r WHERE r.dResultadoTemporalFechregistro = :dResultadoTemporalFechregistro")
    , @NamedQuery(name = "ResultadoTemporal.findByFsResultadoTemporalUsuario", query = "SELECT r FROM ResultadoTemporal r WHERE r.fsResultadoTemporalUsuario = :fsResultadoTemporalUsuario")})
public class ResultadoTemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_resultado_temporal_id", nullable = false)
    private Integer piResultadoTemporalId;
    @Column(name = "s_resultado_temporal_valor", length = 225)
    private String sResultadoTemporalValor;
    @Column(name = "d_resultado_temporal_fechingreso_analista")
    @Temporal(TemporalType.DATE)
    private Date dResultadoTemporalFechingresoAnalista;
    @Column(name = "d_resultado_temporal_fechregistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResultadoTemporalFechregistro;
    @Column(name = "fs_resultado_temporal_usuario", length = 225)
    private String fsResultadoTemporalUsuario;
    @JoinColumn(name = "fi_resultado_temporal_muestra", referencedColumnName = "pi_muestra_id")
    @ManyToOne
    private Muestra fiResultadoTemporalMuestra;
    @JoinColumn(name = "fi_resultado_temporal_var_cal", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiResultadoTemporalVarCal;

    public ResultadoTemporal() {
    }

    public ResultadoTemporal(Integer piResultadoTemporalId) {
        this.piResultadoTemporalId = piResultadoTemporalId;
    }

    public Integer getPiResultadoTemporalId() {
        return piResultadoTemporalId;
    }

    public void setPiResultadoTemporalId(Integer piResultadoTemporalId) {
        this.piResultadoTemporalId = piResultadoTemporalId;
    }

    public String getSResultadoTemporalValor() {
        return sResultadoTemporalValor;
    }

    public void setSResultadoTemporalValor(String sResultadoTemporalValor) {
        this.sResultadoTemporalValor = sResultadoTemporalValor;
    }

    public Date getDResultadoTemporalFechingresoAnalista() {
        return dResultadoTemporalFechingresoAnalista;
    }

    public void setDResultadoTemporalFechingresoAnalista(Date dResultadoTemporalFechingresoAnalista) {
        this.dResultadoTemporalFechingresoAnalista = dResultadoTemporalFechingresoAnalista;
    }

    public Date getDResultadoTemporalFechregistro() {
        return dResultadoTemporalFechregistro;
    }

    public void setDResultadoTemporalFechregistro(Date dResultadoTemporalFechregistro) {
        this.dResultadoTemporalFechregistro = dResultadoTemporalFechregistro;
    }

    public String getFsResultadoTemporalUsuario() {
        return fsResultadoTemporalUsuario;
    }

    public void setFsResultadoTemporalUsuario(String fsResultadoTemporalUsuario) {
        this.fsResultadoTemporalUsuario = fsResultadoTemporalUsuario;
    }

    public Muestra getFiResultadoTemporalMuestra() {
        return fiResultadoTemporalMuestra;
    }

    public void setFiResultadoTemporalMuestra(Muestra fiResultadoTemporalMuestra) {
        this.fiResultadoTemporalMuestra = fiResultadoTemporalMuestra;
    }

    public VariableCalculo getFiResultadoTemporalVarCal() {
        return fiResultadoTemporalVarCal;
    }

    public void setFiResultadoTemporalVarCal(VariableCalculo fiResultadoTemporalVarCal) {
        this.fiResultadoTemporalVarCal = fiResultadoTemporalVarCal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piResultadoTemporalId != null ? piResultadoTemporalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoTemporal)) {
            return false;
        }
        ResultadoTemporal other = (ResultadoTemporal) object;
        if ((this.piResultadoTemporalId == null && other.piResultadoTemporalId != null) || (this.piResultadoTemporalId != null && !this.piResultadoTemporalId.equals(other.piResultadoTemporalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResultadoTemporal[ piResultadoTemporalId=" + piResultadoTemporalId + " ]";
    }
    
}
