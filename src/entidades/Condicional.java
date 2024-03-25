/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "condicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Condicional.findAll", query = "SELECT c FROM Condicional c"),
    @NamedQuery(name = "Condicional.findByPiCondicional", query = "SELECT c FROM Condicional c WHERE c.piCondicional = :piCondicional"),
    @NamedQuery(name = "Condicional.findBySCondicional", query = "SELECT c FROM Condicional c WHERE c.sCondicional = :sCondicional"),
    @NamedQuery(name = "Condicional.findBySCondicionalValor", query = "SELECT c FROM Condicional c WHERE c.sCondicionalValor = :sCondicionalValor")})
public class Condicional implements Serializable {

    @JoinColumn(name = "fi_condicional_variable_calculo_referencia", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiCondicionalVariableCalculoReferencia;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_condicional", nullable = false)
    private Integer piCondicional;
    @Column(name = "s_condicional", length = 45)
    private String sCondicional;
    @Column(name = "s_condicional_valor", length = 45)
    private String sCondicionalValor;
    @JoinColumn(name = "fi_condicional_form_var_cal", referencedColumnName = "pi_formula_variable_calculo_id")
    @ManyToOne
    private FormulaVariableCalculo fiCondicionalFormVarCal;
    @JoinColumn(name = "fi_condicional_variable_analisis", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiCondicionalVariableAnalisis;
    @JoinColumn(name = "fi_condicional_variable_calculo", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiCondicionalVariableCalculo;

    public Condicional() {
    }

    public Condicional(Integer piCondicional) {
        this.piCondicional = piCondicional;
    }

    public Integer getPiCondicional() {
        return piCondicional;
    }

    public void setPiCondicional(Integer piCondicional) {
        this.piCondicional = piCondicional;
    }

    public String getSCondicional() {
        return sCondicional;
    }

    public void setSCondicional(String sCondicional) {
        this.sCondicional = sCondicional;
    }

    public String getSCondicionalValor() {
        return sCondicionalValor;
    }

    public void setSCondicionalValor(String sCondicionalValor) {
        this.sCondicionalValor = sCondicionalValor;
    }

    public FormulaVariableCalculo getFiCondicionalFormVarCal() {
        return fiCondicionalFormVarCal;
    }

    public void setFiCondicionalFormVarCal(FormulaVariableCalculo fiCondicionalFormVarCal) {
        this.fiCondicionalFormVarCal = fiCondicionalFormVarCal;
    }

    public VariableAnalisis getFiCondicionalVariableAnalisis() {
        return fiCondicionalVariableAnalisis;
    }

    public void setFiCondicionalVariableAnalisis(VariableAnalisis fiCondicionalVariableAnalisis) {
        this.fiCondicionalVariableAnalisis = fiCondicionalVariableAnalisis;
    }

    public VariableCalculo getFiCondicionalVariableCalculo() {
        return fiCondicionalVariableCalculo;
    }

    public void setFiCondicionalVariableCalculo(VariableCalculo fiCondicionalVariableCalculo) {
        this.fiCondicionalVariableCalculo = fiCondicionalVariableCalculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piCondicional != null ? piCondicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicional)) {
            return false;
        }
        Condicional other = (Condicional) object;
        if ((this.piCondicional == null && other.piCondicional != null) || (this.piCondicional != null && !this.piCondicional.equals(other.piCondicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Condicional[ piCondicional=" + piCondicional + " ]";
    }

    public VariableCalculo getFiCondicionalVariableCalculoReferencia() {
        return fiCondicionalVariableCalculoReferencia;
    }

    public void setFiCondicionalVariableCalculoReferencia(VariableCalculo fiCondicionalVariableCalculoReferencia) {
        this.fiCondicionalVariableCalculoReferencia = fiCondicionalVariableCalculoReferencia;
    }
    
}
