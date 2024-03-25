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
 * @author SISTEMAS
 */
@Entity
@Table(name = "resultado", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultado.findAll", query = "SELECT r FROM Resultado r")
    , @NamedQuery(name = "Resultado.findByPiResultadoId", query = "SELECT r FROM Resultado r WHERE r.piResultadoId = :piResultadoId")
    , @NamedQuery(name = "Resultado.findBySResultadoResultado", query = "SELECT r FROM Resultado r WHERE r.sResultadoResultado = :sResultadoResultado")
    , @NamedQuery(name = "Resultado.findByDResultadoFechacreacion", query = "SELECT r FROM Resultado r WHERE r.dResultadoFechacreacion = :dResultadoFechacreacion")
    , @NamedQuery(name = "Resultado.findByDResultadoFechamodificacion", query = "SELECT r FROM Resultado r WHERE r.dResultadoFechamodificacion = :dResultadoFechamodificacion")})
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_resultado_id", nullable = false)
    private Integer piResultadoId;
    @Column(name = "s_resultado_resultado", length = 200)
    private String sResultadoResultado;
    @Column(name = "d_resultado_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResultadoFechacreacion;
    @Column(name = "d_resultado_fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResultadoFechamodificacion;
    @JoinColumn(name = "fs_resultado_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsResultadoUsuacreacion;
    @JoinColumn(name = "fs_resultado_usuamodificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsResultadoUsuamodificacion;
    @JoinColumn(name = "fi_resultado_valor", referencedColumnName = "pi_valor_id")
    @ManyToOne
    private Valor fiResultadoValor;
    @JoinColumn(name = "fi_resultado_variable_calculo", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiResultadoVariableCalculo;
    @JoinColumn(name = "fki_resultado_dato_hidrobiologia", referencedColumnName = "pi_dato_hidro")
    @ManyToOne
    private DatoHidrobiologia fkiResultadoDatoHidrobiologia;

    public Resultado() {
    }

    public Resultado(Integer piResultadoId) {
        this.piResultadoId = piResultadoId;
    }

    public Integer getPiResultadoId() {
        return piResultadoId;
    }

    public void setPiResultadoId(Integer piResultadoId) {
        this.piResultadoId = piResultadoId;
    }

    public String getSResultadoResultado() {
        return sResultadoResultado;
    }

    public void setSResultadoResultado(String sResultadoResultado) {
        this.sResultadoResultado = sResultadoResultado;
    }

    public Date getDResultadoFechacreacion() {
        return dResultadoFechacreacion;
    }

    public void setDResultadoFechacreacion(Date dResultadoFechacreacion) {
        this.dResultadoFechacreacion = dResultadoFechacreacion;
    }

    public Date getDResultadoFechamodificacion() {
        return dResultadoFechamodificacion;
    }

    public void setDResultadoFechamodificacion(Date dResultadoFechamodificacion) {
        this.dResultadoFechamodificacion = dResultadoFechamodificacion;
    }

    public SUsuario getFsResultadoUsuacreacion() {
        return fsResultadoUsuacreacion;
    }

    public void setFsResultadoUsuacreacion(SUsuario fsResultadoUsuacreacion) {
        this.fsResultadoUsuacreacion = fsResultadoUsuacreacion;
    }

    public SUsuario getFsResultadoUsuamodificacion() {
        return fsResultadoUsuamodificacion;
    }

    public void setFsResultadoUsuamodificacion(SUsuario fsResultadoUsuamodificacion) {
        this.fsResultadoUsuamodificacion = fsResultadoUsuamodificacion;
    }

    public Valor getFiResultadoValor() {
        return fiResultadoValor;
    }

    public void setFiResultadoValor(Valor fiResultadoValor) {
        this.fiResultadoValor = fiResultadoValor;
    }

    public VariableCalculo getFiResultadoVariableCalculo() {
        return fiResultadoVariableCalculo;
    }

    public void setFiResultadoVariableCalculo(VariableCalculo fiResultadoVariableCalculo) {
        this.fiResultadoVariableCalculo = fiResultadoVariableCalculo;
    }

    public DatoHidrobiologia getFkiResultadoDatoHidrobiologia() {
        return fkiResultadoDatoHidrobiologia;
    }

    public void setFkiResultadoDatoHidrobiologia(DatoHidrobiologia fkiResultadoDatoHidrobiologia) {
        this.fkiResultadoDatoHidrobiologia = fkiResultadoDatoHidrobiologia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piResultadoId != null ? piResultadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.piResultadoId == null && other.piResultadoId != null) || (this.piResultadoId != null && !this.piResultadoId.equals(other.piResultadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Resultado[ piResultadoId=" + piResultadoId + " ]";
    }
    
}
