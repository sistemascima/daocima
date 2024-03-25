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
 * @author TOSHIBA
 */
@Entity
@Table(name = "variable_analisis_control")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariableAnalisisControl.findAll", query = "SELECT v FROM VariableAnalisisControl v"),
    @NamedQuery(name = "VariableAnalisisControl.findByPiVarianalControlId", query = "SELECT v FROM VariableAnalisisControl v WHERE v.piVarianalControlId = :piVarianalControlId"),
    @NamedQuery(name = "VariableAnalisisControl.findByCVarianalControlFecha", query = "SELECT v FROM VariableAnalisisControl v WHERE v.cVarianalControlFecha = :cVarianalControlFecha"),
    @NamedQuery(name = "VariableAnalisisControl.findByIVarianalControlMuestras", query = "SELECT v FROM VariableAnalisisControl v WHERE v.iVarianalControlMuestras = :iVarianalControlMuestras"),
    @NamedQuery(name = "VariableAnalisisControl.findByDVarianalControlFechacreacion", query = "SELECT v FROM VariableAnalisisControl v WHERE v.dVarianalControlFechacreacion = :dVarianalControlFechacreacion"),
    @NamedQuery(name = "VariableAnalisisControl.findByDVarianalControlFechamodificacion", query = "SELECT v FROM VariableAnalisisControl v WHERE v.dVarianalControlFechamodificacion = :dVarianalControlFechamodificacion")})
public class VariableAnalisisControl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_varianal_control_id", nullable = false)
    private Integer piVarianalControlId;
    @Column(name = "c_varianal_control_fecha")
    private Character cVarianalControlFecha;
    @Column(name = "i_varianal_control_muestras")
    private Integer iVarianalControlMuestras;
    @Column(name = "d_varianal_control_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalControlFechacreacion;
    @Column(name = "d_varianal_control_fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalControlFechamodificacion;
    @JoinColumn(name = "fi_varianal_control_variable", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiVarianalControlVariable;
    @JoinColumn(name = "fs_varianal_control_usuario_creacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVarianalControlUsuarioCreacion;
    @JoinColumn(name = "fs_varianal_control_usuario_modificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVarianalControlUsuarioModificacion;

    public VariableAnalisisControl() {
    }

    public VariableAnalisisControl(Integer piVarianalControlId) {
        this.piVarianalControlId = piVarianalControlId;
    }

    public Integer getPiVarianalControlId() {
        return piVarianalControlId;
    }

    public void setPiVarianalControlId(Integer piVarianalControlId) {
        this.piVarianalControlId = piVarianalControlId;
    }

    public Character getCVarianalControlFecha() {
        return cVarianalControlFecha;
    }

    public void setCVarianalControlFecha(Character cVarianalControlFecha) {
        this.cVarianalControlFecha = cVarianalControlFecha;
    }

    public Integer getIVarianalControlMuestras() {
        return iVarianalControlMuestras;
    }

    public void setIVarianalControlMuestras(Integer iVarianalControlMuestras) {
        this.iVarianalControlMuestras = iVarianalControlMuestras;
    }

    public Date getDVarianalControlFechacreacion() {
        return dVarianalControlFechacreacion;
    }

    public void setDVarianalControlFechacreacion(Date dVarianalControlFechacreacion) {
        this.dVarianalControlFechacreacion = dVarianalControlFechacreacion;
    }

    public Date getDVarianalControlFechamodificacion() {
        return dVarianalControlFechamodificacion;
    }

    public void setDVarianalControlFechamodificacion(Date dVarianalControlFechamodificacion) {
        this.dVarianalControlFechamodificacion = dVarianalControlFechamodificacion;
    }

    public VariableAnalisis getFiVarianalControlVariable() {
        return fiVarianalControlVariable;
    }

    public void setFiVarianalControlVariable(VariableAnalisis fiVarianalControlVariable) {
        this.fiVarianalControlVariable = fiVarianalControlVariable;
    }

    public SUsuario getFsVarianalControlUsuarioCreacion() {
        return fsVarianalControlUsuarioCreacion;
    }

    public void setFsVarianalControlUsuarioCreacion(SUsuario fsVarianalControlUsuarioCreacion) {
        this.fsVarianalControlUsuarioCreacion = fsVarianalControlUsuarioCreacion;
    }

    public SUsuario getFsVarianalControlUsuarioModificacion() {
        return fsVarianalControlUsuarioModificacion;
    }

    public void setFsVarianalControlUsuarioModificacion(SUsuario fsVarianalControlUsuarioModificacion) {
        this.fsVarianalControlUsuarioModificacion = fsVarianalControlUsuarioModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVarianalControlId != null ? piVarianalControlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariableAnalisisControl)) {
            return false;
        }
        VariableAnalisisControl other = (VariableAnalisisControl) object;
        if ((this.piVarianalControlId == null && other.piVarianalControlId != null) || (this.piVarianalControlId != null && !this.piVarianalControlId.equals(other.piVarianalControlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VariableAnalisisControl[ piVarianalControlId=" + piVarianalControlId + " ]";
    }
    
}
