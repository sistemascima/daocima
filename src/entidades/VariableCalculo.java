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
@Table(name = "variable_calculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariableCalculo.findAll", query = "SELECT v FROM VariableCalculo v"),
    @NamedQuery(name = "VariableCalculo.findByPiVariableCalculo", query = "SELECT v FROM VariableCalculo v WHERE v.piVariableCalculo = :piVariableCalculo"),
    @NamedQuery(name = "VariableCalculo.findBySVariableCalculoNombre", query = "SELECT v FROM VariableCalculo v WHERE v.sVariableCalculoNombre = :sVariableCalculoNombre"),
    @NamedQuery(name = "VariableCalculo.findByIVariableCalculoInput", query = "SELECT v FROM VariableCalculo v WHERE v.iVariableCalculoInput = :iVariableCalculoInput"),
    @NamedQuery(name = "VariableCalculo.findByIVariableCalculoValorReporte", query = "SELECT v FROM VariableCalculo v WHERE v.iVariableCalculoValorReporte = :iVariableCalculoValorReporte"),
    @NamedQuery(name = "VariableCalculo.findByDVariableCalculoCreacion", query = "SELECT v FROM VariableCalculo v WHERE v.dVariableCalculoCreacion = :dVariableCalculoCreacion"),
    @NamedQuery(name = "VariableCalculo.findByDVariableCalculoUltimodi", query = "SELECT v FROM VariableCalculo v WHERE v.dVariableCalculoUltimodi = :dVariableCalculoUltimodi")})
public class VariableCalculo implements Serializable {

    @OneToMany(mappedBy = "fiResultadoTemporalVarCal")
    private List<ResultadoTemporal> resultadoTemporalList;

    @OneToMany(mappedBy = "fiResultadoVariableCalculo")
    private List<Resultado> resultadoList;

    @OneToMany(mappedBy = "fiVariableFormulaVariableEntrada")
    private List<VariablesFormula> variablesFormulaList;
    @OneToMany(mappedBy = "fiVariableFormulaVariableFinal")
    private List<VariablesFormula> variablesFormulaList1;

    @OneToMany(mappedBy = "fiCondicionalVariableCalculo")
    private List<Condicional> condicionalList;
    @OneToMany(mappedBy = "fiFormulaVariableCalculoVariableCalculo")
    private List<FormulaVariableCalculo> formulaVariableCalculoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_variable_calculo", nullable = false)
    private Integer piVariableCalculo;
    @Column(name = "s_variable_calculo_nombre", length = 225)
    private String sVariableCalculoNombre;
    @Column(name = "i_variable_calculo_input")
    private Short iVariableCalculoInput;
    @Column(name = "i_variable_calculo_valor_reporte")
    private Short iVariableCalculoValorReporte;
    @Column(name = "d_variable_calculo_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVariableCalculoCreacion;
    @Column(name = "d_variable_calculo_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVariableCalculoUltimodi;
    @JoinColumn(name = "fi_variable_calculo_tipo_valor", referencedColumnName = "pi_tipoval_id")
    @ManyToOne
    private TipoValor fiVariableCalculoTipoValor;
    @JoinColumn(name = "fs_variable_calculo_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVariableCalculoUsuacreacion;
    @JoinColumn(name = "fs_variable_calculo_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVariableCalculoUsuultmod;
    @JoinColumn(name = "fi_variable_variable_calculo", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiVariableVariableCalculo;
    @JoinColumn(name = "fi_variable_calculo_variable_referencia", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiVariableCalculoVariableReferencia;

    public VariableCalculo() {
    }

    public VariableCalculo(Integer piVariableCalculo) {
        this.piVariableCalculo = piVariableCalculo;
    }

    public Integer getPiVariableCalculo() {
        return piVariableCalculo;
    }

    public void setPiVariableCalculo(Integer piVariableCalculo) {
        this.piVariableCalculo = piVariableCalculo;
    }

    public String getSVariableCalculoNombre() {
        return sVariableCalculoNombre;
    }

    public void setSVariableCalculoNombre(String sVariableCalculoNombre) {
        this.sVariableCalculoNombre = sVariableCalculoNombre;
    }

    public Short getIVariableCalculoInput() {
        return iVariableCalculoInput;
    }

    public void setIVariableCalculoInput(Short iVariableCalculoInput) {
        this.iVariableCalculoInput = iVariableCalculoInput;
    }

    public Short getIVariableCalculoValorReporte() {
        return iVariableCalculoValorReporte;
    }

    public void setIVariableCalculoValorReporte(Short iVariableCalculoValorReporte) {
        this.iVariableCalculoValorReporte = iVariableCalculoValorReporte;
    }

    public Date getDVariableCalculoCreacion() {
        return dVariableCalculoCreacion;
    }

    public void setDVariableCalculoCreacion(Date dVariableCalculoCreacion) {
        this.dVariableCalculoCreacion = dVariableCalculoCreacion;
    }

    public Date getDVariableCalculoUltimodi() {
        return dVariableCalculoUltimodi;
    }

    public void setDVariableCalculoUltimodi(Date dVariableCalculoUltimodi) {
        this.dVariableCalculoUltimodi = dVariableCalculoUltimodi;
    }

    public TipoValor getFiVariableCalculoTipoValor() {
        return fiVariableCalculoTipoValor;
    }

    public void setFiVariableCalculoTipoValor(TipoValor fiVariableCalculoTipoValor) {
        this.fiVariableCalculoTipoValor = fiVariableCalculoTipoValor;
    }

    public SUsuario getFsVariableCalculoUsuacreacion() {
        return fsVariableCalculoUsuacreacion;
    }

    public void setFsVariableCalculoUsuacreacion(SUsuario fsVariableCalculoUsuacreacion) {
        this.fsVariableCalculoUsuacreacion = fsVariableCalculoUsuacreacion;
    }

    public SUsuario getFsVariableCalculoUsuultmod() {
        return fsVariableCalculoUsuultmod;
    }

    public void setFsVariableCalculoUsuultmod(SUsuario fsVariableCalculoUsuultmod) {
        this.fsVariableCalculoUsuultmod = fsVariableCalculoUsuultmod;
    }

    public VariableAnalisis getFiVariableVariableCalculo() {
        return fiVariableVariableCalculo;
    }

    public void setFiVariableVariableCalculo(VariableAnalisis fiVariableVariableCalculo) {
        this.fiVariableVariableCalculo = fiVariableVariableCalculo;
    }

    public VariableAnalisis getFiVariableCalculoVariableReferencia() {
        return fiVariableCalculoVariableReferencia;
    }

    public void setFiVariableCalculoVariableReferencia(VariableAnalisis fiVariableCalculoVariableReferencia) {
        this.fiVariableCalculoVariableReferencia = fiVariableCalculoVariableReferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVariableCalculo != null ? piVariableCalculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariableCalculo)) {
            return false;
        }
        VariableCalculo other = (VariableCalculo) object;
        if ((this.piVariableCalculo == null && other.piVariableCalculo != null) || (this.piVariableCalculo != null && !this.piVariableCalculo.equals(other.piVariableCalculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VariableCalculo[ piVariableCalculo=" + piVariableCalculo + " ]";
    }

    @XmlTransient
    public List<Condicional> getCondicionalList() {
        return condicionalList;
    }

    public void setCondicionalList(List<Condicional> condicionalList) {
        this.condicionalList = condicionalList;
    }

    @XmlTransient
    public List<FormulaVariableCalculo> getFormulaVariableCalculoList() {
        return formulaVariableCalculoList;
    }

    public void setFormulaVariableCalculoList(List<FormulaVariableCalculo> formulaVariableCalculoList) {
        this.formulaVariableCalculoList = formulaVariableCalculoList;
    }

    @XmlTransient
    public List<VariablesFormula> getVariablesFormulaList() {
        return variablesFormulaList;
    }

    public void setVariablesFormulaList(List<VariablesFormula> variablesFormulaList) {
        this.variablesFormulaList = variablesFormulaList;
    }

    @XmlTransient
    public List<VariablesFormula> getVariablesFormulaList1() {
        return variablesFormulaList1;
    }

    public void setVariablesFormulaList1(List<VariablesFormula> variablesFormulaList1) {
        this.variablesFormulaList1 = variablesFormulaList1;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @XmlTransient
    public List<ResultadoTemporal> getResultadoTemporalList() {
        return resultadoTemporalList;
    }

    public void setResultadoTemporalList(List<ResultadoTemporal> resultadoTemporalList) {
        this.resultadoTemporalList = resultadoTemporalList;
    }
    
}
