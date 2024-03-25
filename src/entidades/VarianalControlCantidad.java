/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "varianal_control_cantidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VarianalControlCantidad.findAll", query = "SELECT v FROM VarianalControlCantidad v"),
    @NamedQuery(name = "VarianalControlCantidad.findByPfiVarianalControlCantidadVariable", query = "SELECT v FROM VarianalControlCantidad v WHERE v.varianalControlCantidadPK.pfiVarianalControlCantidadVariable = :pfiVarianalControlCantidadVariable"),
    @NamedQuery(name = "VarianalControlCantidad.findByPfiVarianalControlCantidadControl", query = "SELECT v FROM VarianalControlCantidad v WHERE v.varianalControlCantidadPK.pfiVarianalControlCantidadControl = :pfiVarianalControlCantidadControl"),
    @NamedQuery(name = "VarianalControlCantidad.findByCVarianalControlCantidadMuestra", query = "SELECT v FROM VarianalControlCantidad v WHERE v.cVarianalControlCantidadMuestra = :cVarianalControlCantidadMuestra"),
    @NamedQuery(name = "VarianalControlCantidad.findByDVarianalControlCantidadFechaCreacion", query = "SELECT v FROM VarianalControlCantidad v WHERE v.dVarianalControlCantidadFechaCreacion = :dVarianalControlCantidadFechaCreacion"),
    @NamedQuery(name = "VarianalControlCantidad.findByDVarianalControlCantidadFechaModificacion", query = "SELECT v FROM VarianalControlCantidad v WHERE v.dVarianalControlCantidadFechaModificacion = :dVarianalControlCantidadFechaModificacion")})
public class VarianalControlCantidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VarianalControlCantidadPK varianalControlCantidadPK;
    @Column(name = "c_varianal_control_cantidad_muestra")
    private Integer cVarianalControlCantidadMuestra;
    @Column(name = "d_varianal_control_cantidad_fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalControlCantidadFechaCreacion;
    @Column(name = "d_varianal_control_cantidad_fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalControlCantidadFechaModificacion;
    @JoinColumn(name = "pfi_varianal_control_cantidad_control", referencedColumnName = "pi_control_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Control control;
    @JoinColumn(name = "pfi_varianal_control_cantidad_variable", referencedColumnName = "pi_varianal_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VariableAnalisis variableAnalisis;
    @JoinColumn(name = "fs_varianal_control_cantidad_usuario_creacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVarianalControlCantidadUsuarioCreacion;
    @JoinColumn(name = "fs_varianal_control_cantidad_usuario_modificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVarianalControlCantidadUsuarioModificacion;

    public VarianalControlCantidad() {
    }

    public VarianalControlCantidad(VarianalControlCantidadPK varianalControlCantidadPK) {
        this.varianalControlCantidadPK = varianalControlCantidadPK;
    }

    public VarianalControlCantidad(int pfiVarianalControlCantidadVariable, int pfiVarianalControlCantidadControl) {
        this.varianalControlCantidadPK = new VarianalControlCantidadPK(pfiVarianalControlCantidadVariable, pfiVarianalControlCantidadControl);
    }

    public VarianalControlCantidadPK getVarianalControlCantidadPK() {
        return varianalControlCantidadPK;
    }

    public void setVarianalControlCantidadPK(VarianalControlCantidadPK varianalControlCantidadPK) {
        this.varianalControlCantidadPK = varianalControlCantidadPK;
    }

    public Integer getCVarianalControlCantidadMuestra() {
        return cVarianalControlCantidadMuestra;
    }

    public void setCVarianalControlCantidadMuestra(Integer cVarianalControlCantidadMuestra) {
        this.cVarianalControlCantidadMuestra = cVarianalControlCantidadMuestra;
    }

    public Date getDVarianalControlCantidadFechaCreacion() {
        return dVarianalControlCantidadFechaCreacion;
    }

    public void setDVarianalControlCantidadFechaCreacion(Date dVarianalControlCantidadFechaCreacion) {
        this.dVarianalControlCantidadFechaCreacion = dVarianalControlCantidadFechaCreacion;
    }

    public Date getDVarianalControlCantidadFechaModificacion() {
        return dVarianalControlCantidadFechaModificacion;
    }

    public void setDVarianalControlCantidadFechaModificacion(Date dVarianalControlCantidadFechaModificacion) {
        this.dVarianalControlCantidadFechaModificacion = dVarianalControlCantidadFechaModificacion;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public VariableAnalisis getVariableAnalisis() {
        return variableAnalisis;
    }

    public void setVariableAnalisis(VariableAnalisis variableAnalisis) {
        this.variableAnalisis = variableAnalisis;
    }

    public SUsuario getFsVarianalControlCantidadUsuarioCreacion() {
        return fsVarianalControlCantidadUsuarioCreacion;
    }

    public void setFsVarianalControlCantidadUsuarioCreacion(SUsuario fsVarianalControlCantidadUsuarioCreacion) {
        this.fsVarianalControlCantidadUsuarioCreacion = fsVarianalControlCantidadUsuarioCreacion;
    }

    public SUsuario getFsVarianalControlCantidadUsuarioModificacion() {
        return fsVarianalControlCantidadUsuarioModificacion;
    }

    public void setFsVarianalControlCantidadUsuarioModificacion(SUsuario fsVarianalControlCantidadUsuarioModificacion) {
        this.fsVarianalControlCantidadUsuarioModificacion = fsVarianalControlCantidadUsuarioModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (varianalControlCantidadPK != null ? varianalControlCantidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VarianalControlCantidad)) {
            return false;
        }
        VarianalControlCantidad other = (VarianalControlCantidad) object;
        if ((this.varianalControlCantidadPK == null && other.varianalControlCantidadPK != null) || (this.varianalControlCantidadPK != null && !this.varianalControlCantidadPK.equals(other.varianalControlCantidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VarianalControlCantidad[ varianalControlCantidadPK=" + varianalControlCantidadPK + " ]";
    }
    
}
