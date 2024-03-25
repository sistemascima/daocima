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
@Table(name = "constante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Constante.findAll", query = "SELECT c FROM Constante c"),
    @NamedQuery(name = "Constante.findByPiConstanteId", query = "SELECT c FROM Constante c WHERE c.piConstanteId = :piConstanteId"),
    @NamedQuery(name = "Constante.findBySConstanteValor", query = "SELECT c FROM Constante c WHERE c.sConstanteValor = :sConstanteValor"),
    @NamedQuery(name = "Constante.findByDConstanteFechcreacion", query = "SELECT c FROM Constante c WHERE c.dConstanteFechcreacion = :dConstanteFechcreacion"),
    @NamedQuery(name = "Constante.findByDConstanteFechmodificacion", query = "SELECT c FROM Constante c WHERE c.dConstanteFechmodificacion = :dConstanteFechmodificacion")})
public class Constante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_constante_id", nullable = false)
    private Integer piConstanteId;
    @Column(name = "s_constante_valor", length = 45)
    private String sConstanteValor;
    @Column(name = "d_constante_fechcreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dConstanteFechcreacion;
    @Column(name = "d_constante_fechmodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dConstanteFechmodificacion;
    @JoinColumn(name = "fi_constante_variable_calculo", referencedColumnName = "pi_variable_calculo")
    @ManyToOne
    private VariableCalculo fiConstanteVariableCalculo;
    @JoinColumn(name = "fk_constante_usuario_creador", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fkConstanteUsuarioCreador;
    @JoinColumn(name = "fk_constante_usuario_modificador", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fkConstanteUsuarioModificador;

    public Constante() {
    }

    public Constante(Integer piConstanteId) {
        this.piConstanteId = piConstanteId;
    }

    public Integer getPiConstanteId() {
        return piConstanteId;
    }

    public void setPiConstanteId(Integer piConstanteId) {
        this.piConstanteId = piConstanteId;
    }

    public String getSConstanteValor() {
        return sConstanteValor;
    }

    public void setSConstanteValor(String sConstanteValor) {
        this.sConstanteValor = sConstanteValor;
    }

    public Date getDConstanteFechcreacion() {
        return dConstanteFechcreacion;
    }

    public void setDConstanteFechcreacion(Date dConstanteFechcreacion) {
        this.dConstanteFechcreacion = dConstanteFechcreacion;
    }

    public Date getDConstanteFechmodificacion() {
        return dConstanteFechmodificacion;
    }

    public void setDConstanteFechmodificacion(Date dConstanteFechmodificacion) {
        this.dConstanteFechmodificacion = dConstanteFechmodificacion;
    }

    public VariableCalculo getFiConstanteVariableCalculo() {
        return fiConstanteVariableCalculo;
    }

    public void setFiConstanteVariableCalculo(VariableCalculo fiConstanteVariableCalculo) {
        this.fiConstanteVariableCalculo = fiConstanteVariableCalculo;
    }

    public SUsuario getFkConstanteUsuarioCreador() {
        return fkConstanteUsuarioCreador;
    }

    public void setFkConstanteUsuarioCreador(SUsuario fkConstanteUsuarioCreador) {
        this.fkConstanteUsuarioCreador = fkConstanteUsuarioCreador;
    }

    public SUsuario getFkConstanteUsuarioModificador() {
        return fkConstanteUsuarioModificador;
    }

    public void setFkConstanteUsuarioModificador(SUsuario fkConstanteUsuarioModificador) {
        this.fkConstanteUsuarioModificador = fkConstanteUsuarioModificador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piConstanteId != null ? piConstanteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Constante)) {
            return false;
        }
        Constante other = (Constante) object;
        if ((this.piConstanteId == null && other.piConstanteId != null) || (this.piConstanteId != null && !this.piConstanteId.equals(other.piConstanteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Constante[ piConstanteId=" + piConstanteId + " ]";
    }
    
}
