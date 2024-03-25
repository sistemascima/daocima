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
@Table(name = "variable_analisis_formato", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariableAnalisisFormato.findAll", query = "SELECT v FROM VariableAnalisisFormato v")
    , @NamedQuery(name = "VariableAnalisisFormato.findByPiVarianalFormatoId", query = "SELECT v FROM VariableAnalisisFormato v WHERE v.piVarianalFormatoId = :piVarianalFormatoId")
    , @NamedQuery(name = "VariableAnalisisFormato.findBySVarianalFormatoFormato", query = "SELECT v FROM VariableAnalisisFormato v WHERE v.sVarianalFormatoFormato = :sVarianalFormatoFormato")
    , @NamedQuery(name = "VariableAnalisisFormato.findByCVarianalFormatoVersion", query = "SELECT v FROM VariableAnalisisFormato v WHERE v.cVarianalFormatoVersion = :cVarianalFormatoVersion")})
public class VariableAnalisisFormato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_varianal_formato_id", nullable = false)
    private Integer piVarianalFormatoId;
    @Column(name = "s_varianal_formato_formato", length = 45)
    private String sVarianalFormatoFormato;
    @Column(name = "c_varianal_formato_version", length = 45)
    private String cVarianalFormatoVersion;
    @JoinColumn(name = "fi_varianal_formato_varianal", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiVarianalFormatoVarianal;

    public VariableAnalisisFormato() {
    }

    public VariableAnalisisFormato(Integer piVarianalFormatoId) {
        this.piVarianalFormatoId = piVarianalFormatoId;
    }

    public Integer getPiVarianalFormatoId() {
        return piVarianalFormatoId;
    }

    public void setPiVarianalFormatoId(Integer piVarianalFormatoId) {
        this.piVarianalFormatoId = piVarianalFormatoId;
    }

    public String getSVarianalFormatoFormato() {
        return sVarianalFormatoFormato;
    }

    public void setSVarianalFormatoFormato(String sVarianalFormatoFormato) {
        this.sVarianalFormatoFormato = sVarianalFormatoFormato;
    }

    public String getCVarianalFormatoVersion() {
        return cVarianalFormatoVersion;
    }

    public void setCVarianalFormatoVersion(String cVarianalFormatoVersion) {
        this.cVarianalFormatoVersion = cVarianalFormatoVersion;
    }

    public VariableAnalisis getFiVarianalFormatoVarianal() {
        return fiVarianalFormatoVarianal;
    }

    public void setFiVarianalFormatoVarianal(VariableAnalisis fiVarianalFormatoVarianal) {
        this.fiVarianalFormatoVarianal = fiVarianalFormatoVarianal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVarianalFormatoId != null ? piVarianalFormatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariableAnalisisFormato)) {
            return false;
        }
        VariableAnalisisFormato other = (VariableAnalisisFormato) object;
        if ((this.piVarianalFormatoId == null && other.piVarianalFormatoId != null) || (this.piVarianalFormatoId != null && !this.piVarianalFormatoId.equals(other.piVarianalFormatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VariableAnalisisFormato[ piVarianalFormatoId=" + piVarianalFormatoId + " ]";
    }
    
}
