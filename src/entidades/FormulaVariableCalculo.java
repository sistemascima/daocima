/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "formula_variable_calculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormulaVariableCalculo.findAll", query = "SELECT f FROM FormulaVariableCalculo f"),
    @NamedQuery(name = "FormulaVariableCalculo.findByPiFormulaVariableCalculoId", query = "SELECT f FROM FormulaVariableCalculo f WHERE f.piFormulaVariableCalculoId = :piFormulaVariableCalculoId"),
    @NamedQuery(name = "FormulaVariableCalculo.findByDFormulaVariableCalculoFechacreacion", query = "SELECT f FROM FormulaVariableCalculo f WHERE f.dFormulaVariableCalculoFechacreacion = :dFormulaVariableCalculoFechacreacion"),
    @NamedQuery(name = "FormulaVariableCalculo.findByDFormulaVariableCalculoFechamodificacion", query = "SELECT f FROM FormulaVariableCalculo f WHERE f.dFormulaVariableCalculoFechamodificacion = :dFormulaVariableCalculoFechamodificacion")})
public class FormulaVariableCalculo implements Serializable {

    @OneToMany(mappedBy = "fiCondicionalFormVarCal")
    private List<Condicional> condicionalList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_formula_variable_calculo_id", nullable = false)
    private Integer piFormulaVariableCalculoId;
    @Column(name = "d_formula_variable_calculo_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormulaVariableCalculoFechacreacion;
    @Column(name = "d_formula_variable_calculo_fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormulaVariableCalculoFechamodificacion;
    @JoinColumn(name = "fi_formula_variable_calculo_formula", referencedColumnName = "pi_formula_id")
    @ManyToOne
    private Formula fiFormulaVariableCalculoFormula;
    @JoinColumn(name = "fs_formula_variable_calculo_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsFormulaVariableCalculoUsuacreacion;
    @JoinColumn(name = "fs_formula_variable_calculo_usuamodificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsFormulaVariableCalculoUsuamodificacion;
    @JoinColumn(name = "fi_formula_variable_calculo_variable_calculo", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiFormulaVariableCalculoVariableCalculo;

    public FormulaVariableCalculo() {
    }

    public FormulaVariableCalculo(Integer piFormulaVariableCalculoId) {
        this.piFormulaVariableCalculoId = piFormulaVariableCalculoId;
    }

    public Integer getPiFormulaVariableCalculoId() {
        return piFormulaVariableCalculoId;
    }

    public void setPiFormulaVariableCalculoId(Integer piFormulaVariableCalculoId) {
        this.piFormulaVariableCalculoId = piFormulaVariableCalculoId;
    }

    public Date getDFormulaVariableCalculoFechacreacion() {
        return dFormulaVariableCalculoFechacreacion;
    }

    public void setDFormulaVariableCalculoFechacreacion(Date dFormulaVariableCalculoFechacreacion) {
        this.dFormulaVariableCalculoFechacreacion = dFormulaVariableCalculoFechacreacion;
    }

    public Date getDFormulaVariableCalculoFechamodificacion() {
        return dFormulaVariableCalculoFechamodificacion;
    }

    public void setDFormulaVariableCalculoFechamodificacion(Date dFormulaVariableCalculoFechamodificacion) {
        this.dFormulaVariableCalculoFechamodificacion = dFormulaVariableCalculoFechamodificacion;
    }

    public Formula getFiFormulaVariableCalculoFormula() {
        return fiFormulaVariableCalculoFormula;
    }

    public void setFiFormulaVariableCalculoFormula(Formula fiFormulaVariableCalculoFormula) {
        this.fiFormulaVariableCalculoFormula = fiFormulaVariableCalculoFormula;
    }

    public SUsuario getFsFormulaVariableCalculoUsuacreacion() {
        return fsFormulaVariableCalculoUsuacreacion;
    }

    public void setFsFormulaVariableCalculoUsuacreacion(SUsuario fsFormulaVariableCalculoUsuacreacion) {
        this.fsFormulaVariableCalculoUsuacreacion = fsFormulaVariableCalculoUsuacreacion;
    }

    public SUsuario getFsFormulaVariableCalculoUsuamodificacion() {
        return fsFormulaVariableCalculoUsuamodificacion;
    }

    public void setFsFormulaVariableCalculoUsuamodificacion(SUsuario fsFormulaVariableCalculoUsuamodificacion) {
        this.fsFormulaVariableCalculoUsuamodificacion = fsFormulaVariableCalculoUsuamodificacion;
    }

    public VariableCalculo getFiFormulaVariableCalculoVariableCalculo() {
        return fiFormulaVariableCalculoVariableCalculo;
    }

    public void setFiFormulaVariableCalculoVariableCalculo(VariableCalculo fiFormulaVariableCalculoVariableCalculo) {
        this.fiFormulaVariableCalculoVariableCalculo = fiFormulaVariableCalculoVariableCalculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piFormulaVariableCalculoId != null ? piFormulaVariableCalculoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulaVariableCalculo)) {
            return false;
        }
        FormulaVariableCalculo other = (FormulaVariableCalculo) object;
        if ((this.piFormulaVariableCalculoId == null && other.piFormulaVariableCalculoId != null) || (this.piFormulaVariableCalculoId != null && !this.piFormulaVariableCalculoId.equals(other.piFormulaVariableCalculoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FormulaVariableCalculo[ piFormulaVariableCalculoId=" + piFormulaVariableCalculoId + " ]";
    }

    @XmlTransient
    public List<Condicional> getCondicionalList() {
        return condicionalList;
    }

    public void setCondicionalList(List<Condicional> condicionalList) {
        this.condicionalList = condicionalList;
    }
    
}
