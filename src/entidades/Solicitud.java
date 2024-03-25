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
 * @author HelpDesk
 */
@Entity
@Table(name = "solicitud", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByHardwareId", query = "SELECT s FROM Solicitud s WHERE s.hardwareId = :hardwareId"),
    @NamedQuery(name = "Solicitud.findByDispositivootrosId", query = "SELECT s FROM Solicitud s WHERE s.dispositivootrosId = :dispositivootrosId"),
    @NamedQuery(name = "Solicitud.findBySolicitudId", query = "SELECT s FROM Solicitud s WHERE s.solicitudId = :solicitudId"),
    @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitud.findByDescription", query = "SELECT s FROM Solicitud s WHERE s.description = :description"),
    @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitud.findSolicitudByUserId", query = "SELECT s FROM Solicitud s WHERE s.usuarioId = :usuarioId"),
    @NamedQuery(name = "Solicitud.findSolicitudByHardware", query = "SELECT s FROM Solicitud s WHERE s.hardwareId = :hardwareId"),
    @NamedQuery(name = "Solicitud.findByObservaciones", query = "SELECT s FROM Solicitud s WHERE s.observacion = :observaciones"),
    @NamedQuery(name = "Solicitud.findByPrioridad", query = "SELECT s FROM Solicitud s WHERE s.prioridad = :prioridad"),
    @NamedQuery(name = "Solicitud.findByDispositivoredId", query = "SELECT s FROM Solicitud s WHERE s.dispositivoredId = :dispositivoredId")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "fi_solicitud_hardware")
    private Integer hardwareId;
    @Column(name = "i_solicitud_disposiotrosid")
    private Integer dispositivootrosId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_solicitud_id", nullable = false)
    private Integer solicitudId;
    @Column(name = "s_solicitud_fecha", length = 255)
    private String fecha;
    @Column(name = "s_solicitud_description", length = 255)
    private String description;
    @Column(name = "s_solicitud_estado", length = 255)
    private String estado;
    @Column(name = "s_solicitud_observacion", length = 255)
    private String observacion;
    @Column(name = "s_solicitud_prioridad", length = 255)
    private String prioridad;
    @Column(name = "i_solicitud_dispredid")
    private Integer dispositivoredId;
    @JoinColumn(name = "fs_solicitud_usuario", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioId;

    public Solicitud() {
    }

    public Solicitud(Integer solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Integer getDispositivootrosId() {
        return dispositivootrosId;
    }

    public void setDispositivootrosId(Integer dispositivootrosId) {
        this.dispositivootrosId = dispositivootrosId;
    }

    public Integer getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Integer solicitudId) {
        this.solicitudId = solicitudId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observacion;
    }

    public void setObservaciones(String observaciones) {
        this.observacion = observaciones;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getDispositivoredId() {
        return dispositivoredId;
    }

    public void setDispositivoredId(Integer dispositivoredId) {
        this.dispositivoredId = dispositivoredId;
    }

    public SUsuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(SUsuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudId != null ? solicitudId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solicitudId == null && other.solicitudId != null) || (this.solicitudId != null && !this.solicitudId.equals(other.solicitudId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Solicitud[ solicitudId=" + solicitudId + " ]";
    }
    
}
