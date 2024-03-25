/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "tipo_valor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoValor.findAll", query = "SELECT t FROM TipoValor t"),
    @NamedQuery(name = "TipoValor.findByIdtipoValor", query = "SELECT t FROM TipoValor t WHERE t.idtipoValor = :idtipoValor"),
    @NamedQuery(name = "TipoValor.findByNombre", query = "SELECT t FROM TipoValor t WHERE t.nombre = :nombre")})
public class TipoValor implements Serializable {

    @OneToMany(mappedBy = "fiVariableCalculoTipoValor")
    private List<VariableCalculo> variableCalculoList;

   /* @OneToMany(mappedBy = "fiVariableCalculoTipoValor")
    private List<VariableCalculo> variableCalculoList;

    @OneToMany(mappedBy = "fiEspecieMorfoespecieTipoValor")
    private List<EspecieMorfoespecie> especieMorfoespecieList;*/

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_tipoval_id", nullable = false)
    private Integer idtipoValor;
    @Column(name = "s_tipoval_nombre", length = 45)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTpValor")
    private List<Valor> valorList;

    public TipoValor() {
    }

    public TipoValor(Integer idtipoValor) {
        this.idtipoValor = idtipoValor;
    }

    public Integer getIdtipoValor() {
        return idtipoValor;
    }

    public void setIdtipoValor(Integer idtipoValor) {
        this.idtipoValor = idtipoValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Valor> getValorList() {
        return valorList;
    }

    public void setValorList(List<Valor> valorList) {
        this.valorList = valorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoValor != null ? idtipoValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoValor)) {
            return false;
        }
        TipoValor other = (TipoValor) object;
        if ((this.idtipoValor == null && other.idtipoValor != null) || (this.idtipoValor != null && !this.idtipoValor.equals(other.idtipoValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoValor[ idtipoValor=" + idtipoValor + " ]";
    }

   /* @XmlTransient
    public List<EspecieMorfoespecie> getEspecieMorfoespecieList() {
        return especieMorfoespecieList;
    }

    public void setEspecieMorfoespecieList(List<EspecieMorfoespecie> especieMorfoespecieList) {
        this.especieMorfoespecieList = especieMorfoespecieList;
    }

    @XmlTransient
    public List<VariableCalculo> getVariableCalculoList() {
        return variableCalculoList;
    }

    public void setVariableCalculoList(List<VariableCalculo> variableCalculoList) {
        this.variableCalculoList = variableCalculoList;
    }*/

    @XmlTransient
    public List<VariableCalculo> getVariableCalculoList() {
        return variableCalculoList;
    }

    public void setVariableCalculoList(List<VariableCalculo> variableCalculoList) {
        this.variableCalculoList = variableCalculoList;
    }
    
}
