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
import javax.persistence.CascadeType;
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
@Table(name = "control", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Control.findAll", query = "SELECT c FROM Control c"),
    @NamedQuery(name = "Control.findByPiControlId", query = "SELECT c FROM Control c WHERE c.piControlId = :piControlId"),
    @NamedQuery(name = "Control.findBySControlNombre", query = "SELECT c FROM Control c WHERE c.sControlNombre = :sControlNombre"),
    @NamedQuery(name = "Control.findByDControlFechacreacion", query = "SELECT c FROM Control c WHERE c.dControlFechacreacion = :dControlFechacreacion"),
    @NamedQuery(name = "Control.findByDControlFechamodificacion", query = "SELECT c FROM Control c WHERE c.dControlFechamodificacion = :dControlFechamodificacion")})
public class Control implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_control_id", nullable = false)
    private Integer piControlId;
    @Column(name = "s_control_nombre", length = 100)
    private String sControlNombre;
    @Column(name = "d_control_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dControlFechacreacion;
    @Column(name = "d_control_fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dControlFechamodificacion;
    @JoinColumn(name = "fs_control_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsControlUsuacreacion;
    @JoinColumn(name = "fs_control_usuamodificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsControlUsuamodificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "control")
    private List<VarianalControlCantidad> varianalControlCantidadList;

    public Control() {
    }

    public Control(Integer piControlId) {
        this.piControlId = piControlId;
    }

    public Integer getPiControlId() {
        return piControlId;
    }

    public void setPiControlId(Integer piControlId) {
        this.piControlId = piControlId;
    }

    public String getSControlNombre() {
        return sControlNombre;
    }

    public void setSControlNombre(String sControlNombre) {
        this.sControlNombre = sControlNombre;
    }

    public Date getDControlFechacreacion() {
        return dControlFechacreacion;
    }

    public void setDControlFechacreacion(Date dControlFechacreacion) {
        this.dControlFechacreacion = dControlFechacreacion;
    }

    public Date getDControlFechamodificacion() {
        return dControlFechamodificacion;
    }

    public void setDControlFechamodificacion(Date dControlFechamodificacion) {
        this.dControlFechamodificacion = dControlFechamodificacion;
    }

    public SUsuario getFsControlUsuacreacion() {
        return fsControlUsuacreacion;
    }

    public void setFsControlUsuacreacion(SUsuario fsControlUsuacreacion) {
        this.fsControlUsuacreacion = fsControlUsuacreacion;
    }

    public SUsuario getFsControlUsuamodificacion() {
        return fsControlUsuamodificacion;
    }

    public void setFsControlUsuamodificacion(SUsuario fsControlUsuamodificacion) {
        this.fsControlUsuamodificacion = fsControlUsuamodificacion;
    }

    @XmlTransient
    public List<VarianalControlCantidad> getVarianalControlCantidadList() {
        return varianalControlCantidadList;
    }

    public void setVarianalControlCantidadList(List<VarianalControlCantidad> varianalControlCantidadList) {
        this.varianalControlCantidadList = varianalControlCantidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piControlId != null ? piControlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Control)) {
            return false;
        }
        Control other = (Control) object;
        if ((this.piControlId == null && other.piControlId != null) || (this.piControlId != null && !this.piControlId.equals(other.piControlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Control[ piControlId=" + piControlId + " ]";
    }
    
}
