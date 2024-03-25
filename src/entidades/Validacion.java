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
@Table(name = "validacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Validacion.findAll", query = "SELECT v FROM Validacion v"),
    @NamedQuery(name = "Validacion.findByPiValidacionId", query = "SELECT v FROM Validacion v WHERE v.piValidacionId = :piValidacionId")})
public class Validacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_validacion_id", nullable = false)
    private Integer piValidacionId;
    @JoinColumn(name = "fk_validacion_variable1", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fkValidacionVariable1;
    @JoinColumn(name = "fk_validacion_variable2", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fkValidacionVariable2;
    @Column(name = "s_validacion_signo")
    private String s_validacion_signo;
        
    public Validacion() {
    }

    public Validacion(Integer piValidacionId) {
        this.piValidacionId = piValidacionId;
    }

    public Integer getPiValidacionId() {
        return piValidacionId;
    }

    public void setPiValidacionId(Integer piValidacionId) {
        this.piValidacionId = piValidacionId;
    }

    public VariableAnalisis getFkValidacionVariable1() {
        return fkValidacionVariable1;
    }

    public void setFkValidacionVariable1(VariableAnalisis fkValidacionVariable1) {
        this.fkValidacionVariable1 = fkValidacionVariable1;
    }

    public VariableAnalisis getFkValidacionVariable2() {
        return fkValidacionVariable2;
    }

    public void setFkValidacionVariable2(VariableAnalisis fkValidacionVariable2) {
        this.fkValidacionVariable2 = fkValidacionVariable2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piValidacionId != null ? piValidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Validacion)) {
            return false;
        }
        Validacion other = (Validacion) object;
        if ((this.piValidacionId == null && other.piValidacionId != null) || (this.piValidacionId != null && !this.piValidacionId.equals(other.piValidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Validacion[ piValidacionId=" + piValidacionId + " ]";
    }

    public String getS_validacion_signo() {
        return s_validacion_signo;
    }

    public void setS_validacion_signo(String s_validacion_signo) {
        this.s_validacion_signo = s_validacion_signo;
    }
    
}
