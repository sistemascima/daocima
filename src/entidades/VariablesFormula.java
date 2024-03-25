/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.VariableCalculo;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "variables_formula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariablesFormula.findAll", query = "SELECT v FROM VariablesFormula v"),
    @NamedQuery(name = "VariablesFormula.findByPiVariableFormula", query = "SELECT v FROM VariablesFormula v WHERE v.piVariableFormula = :piVariableFormula")})
public class VariablesFormula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_variable_formula", nullable = false)
    private Integer piVariableFormula;
    @JoinColumn(name = "fi_variable_formula_variable_entrada", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiVariableFormulaVariableEntrada;
    @JoinColumn(name = "fi_variable_formula_variable_final", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiVariableFormulaVariableFinal;

    public VariablesFormula() {
    }

    public VariablesFormula(Integer piVariableFormula) {
        this.piVariableFormula = piVariableFormula;
    }

    public Integer getPiVariableFormula() {
        return piVariableFormula;
    }

    public void setPiVariableFormula(Integer piVariableFormula) {
        this.piVariableFormula = piVariableFormula;
    }

    public VariableCalculo getFiVariableFormulaVariableEntrada() {
        return fiVariableFormulaVariableEntrada;
    }

    public void setFiVariableFormulaVariableEntrada(VariableCalculo fiVariableFormulaVariableEntrada) {
        this.fiVariableFormulaVariableEntrada = fiVariableFormulaVariableEntrada;
    }

    public VariableCalculo getFiVariableFormulaVariableFinal() {
        return fiVariableFormulaVariableFinal;
    }

    public void setFiVariableFormulaVariableFinal(VariableCalculo fiVariableFormulaVariableFinal) {
        this.fiVariableFormulaVariableFinal = fiVariableFormulaVariableFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVariableFormula != null ? piVariableFormula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariablesFormula)) {
            return false;
        }
        VariablesFormula other = (VariablesFormula) object;
        if ((this.piVariableFormula == null && other.piVariableFormula != null) || (this.piVariableFormula != null && !this.piVariableFormula.equals(other.piVariableFormula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "common.VariablesFormula[ piVariableFormula=" + piVariableFormula + " ]";
    }
    
}
