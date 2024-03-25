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
import javax.persistence.FetchType;
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
@Table(name = "formula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formula.findAll", query = "SELECT f FROM Formula f"),
    @NamedQuery(name = "Formula.findByPiFormulaId", query = "SELECT f FROM Formula f WHERE f.piFormulaId = :piFormulaId"),
    @NamedQuery(name = "Formula.findBySFormulaFormula", query = "SELECT f FROM Formula f WHERE f.sFormulaFormula = :sFormulaFormula"),
    @NamedQuery(name = "Formula.findByDFormulaFechacreacion", query = "SELECT f FROM Formula f WHERE f.dFormulaFechacreacion = :dFormulaFechacreacion"),
    @NamedQuery(name = "Formula.findByDFormulaFechmodificacion", query = "SELECT f FROM Formula f WHERE f.dFormulaFechmodificacion = :dFormulaFechmodificacion")})
public class Formula implements Serializable {

    @OneToMany(mappedBy = "fiFormulaVariableCalculoFormula")
    private List<FormulaVariableCalculo> formulaVariableCalculoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_formula_id", nullable = false)
    private Integer piFormulaId;
    @Column(name = "s_formula_formula", length = 445)
    private String sFormulaFormula;
    @Column(name = "d_formula_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormulaFechacreacion;
    @Column(name = "d_formula_fechmodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormulaFechmodificacion;
    @JoinColumn(name = "fs_formula_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch = FetchType.EAGER)
    private SUsuario fsFormulaUsuacreacion;
    @JoinColumn(name = "fs_formula_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch = FetchType.EAGER)
    private SUsuario fsFormulaUsuamodi;

    public Formula() {
    }

    public Formula(Integer piFormulaId) {
        this.piFormulaId = piFormulaId;
    }

    public Integer getPiFormulaId() {
        return piFormulaId;
    }

    public void setPiFormulaId(Integer piFormulaId) {
        this.piFormulaId = piFormulaId;
    }

    public String getSFormulaFormula() {
        return sFormulaFormula;
    }

    public void setSFormulaFormula(String sFormulaFormula) {
        this.sFormulaFormula = sFormulaFormula;
    }

    public Date getDFormulaFechacreacion() {
        return dFormulaFechacreacion;
    }

    public void setDFormulaFechacreacion(Date dFormulaFechacreacion) {
        this.dFormulaFechacreacion = dFormulaFechacreacion;
    }

    public Date getDFormulaFechmodificacion() {
        return dFormulaFechmodificacion;
    }

    public void setDFormulaFechmodificacion(Date dFormulaFechmodificacion) {
        this.dFormulaFechmodificacion = dFormulaFechmodificacion;
    }

    public SUsuario getFsFormulaUsuacreacion() {
        return fsFormulaUsuacreacion;
    }

    public void setFsFormulaUsuacreacion(SUsuario fsFormulaUsuacreacion) {
        this.fsFormulaUsuacreacion = fsFormulaUsuacreacion;
    }

    public SUsuario getFsFormulaUsuamodi() {
        return fsFormulaUsuamodi;
    }

    public void setFsFormulaUsuamodi(SUsuario fsFormulaUsuamodi) {
        this.fsFormulaUsuamodi = fsFormulaUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piFormulaId != null ? piFormulaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formula)) {
            return false;
        }
        Formula other = (Formula) object;
        if ((this.piFormulaId == null && other.piFormulaId != null) || (this.piFormulaId != null && !this.piFormulaId.equals(other.piFormulaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Formula[ piFormulaId=" + piFormulaId + " ]";
    }

    @XmlTransient
    public List<FormulaVariableCalculo> getFormulaVariableCalculoList() {
        return formulaVariableCalculoList;
    }

    public void setFormulaVariableCalculoList(List<FormulaVariableCalculo> formulaVariableCalculoList) {
        this.formulaVariableCalculoList = formulaVariableCalculoList;
    }
    
}
