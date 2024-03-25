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
@Table(name = "muestreo_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestreoAuditoria.findAll", query = "SELECT m FROM MuestreoAuditoria m"),
    @NamedQuery(name = "MuestreoAuditoria.findByPiMuestreoAuditoriaId", query = "SELECT m FROM MuestreoAuditoria m WHERE m.piMuestreoAuditoriaId = :piMuestreoAuditoriaId"),
    @NamedQuery(name = "MuestreoAuditoria.findBySMuestreoAuditoriaFechinic", query = "SELECT m FROM MuestreoAuditoria m WHERE m.sMuestreoAuditoriaFechinic = :sMuestreoAuditoriaFechinic"),
    @NamedQuery(name = "MuestreoAuditoria.findByDMuestreoAuditoriaPlanmuestreo", query = "SELECT m FROM MuestreoAuditoria m WHERE m.dMuestreoAuditoriaPlanmuestreo = :dMuestreoAuditoriaPlanmuestreo"),
    @NamedQuery(name = "MuestreoAuditoria.findByFsMuestreoAuditoriaTecnico", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fsMuestreoAuditoriaTecnico = :fsMuestreoAuditoriaTecnico"),
    @NamedQuery(name = "MuestreoAuditoria.findByDMuestreoAuditoriaFecharecepmuestreo", query = "SELECT m FROM MuestreoAuditoria m WHERE m.dMuestreoAuditoriaFecharecepmuestreo = :dMuestreoAuditoriaFecharecepmuestreo"),
    @NamedQuery(name = "MuestreoAuditoria.findByFiMuestreoAuditoriaProyecto", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fiMuestreoAuditoriaProyecto = :fiMuestreoAuditoriaProyecto"),
    @NamedQuery(name = "MuestreoAuditoria.findByFiMuestreoAuditoriaMunicipio", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fiMuestreoAuditoriaMunicipio = :fiMuestreoAuditoriaMunicipio"),
    @NamedQuery(name = "MuestreoAuditoria.findByFiMuestreoAuditoriaCiudad", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fiMuestreoAuditoriaCiudad = :fiMuestreoAuditoriaCiudad"),
    @NamedQuery(name = "MuestreoAuditoria.findByFiMuestreoAuditoriaPunto", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fiMuestreoAuditoriaPunto = :fiMuestreoAuditoriaPunto"),
    @NamedQuery(name = "MuestreoAuditoria.findByFiMuestreoAuditoriaUsuario", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fiMuestreoAuditoriaUsuario = :fiMuestreoAuditoriaUsuario"),
    @NamedQuery(name = "MuestreoAuditoria.findByDMuestreoAuditoriaFecharegistro", query = "SELECT m FROM MuestreoAuditoria m WHERE m.dMuestreoAuditoriaFecharegistro = :dMuestreoAuditoriaFecharegistro"),
    @NamedQuery(name = "MuestreoAuditoria.findByFsMuestreoAuditoriaUsuariomodificacion", query = "SELECT m FROM MuestreoAuditoria m WHERE m.fsMuestreoAuditoriaUsuariomodificacion = :fsMuestreoAuditoriaUsuariomodificacion"),
    @NamedQuery(name = "MuestreoAuditoria.findByDMuestreoAuditoriaFechamodificacion", query = "SELECT m FROM MuestreoAuditoria m WHERE m.dMuestreoAuditoriaFechamodificacion = :dMuestreoAuditoriaFechamodificacion"),
    @NamedQuery(name = "MuestreoAuditoria.findBySMuestreoObservacion", query = "SELECT m FROM MuestreoAuditoria m WHERE m.sMuestreoObservacion = :sMuestreoObservacion"),
    @NamedQuery(name = "MuestreoAuditoria.findByMuestreoAuditoriaPi", query = "SELECT m FROM MuestreoAuditoria m WHERE m.muestreoAuditoriaPi = :muestreoAuditoriaPi")})
public class MuestreoAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pi_muestreo_auditoria_id")
    private Integer piMuestreoAuditoriaId;
    @Column(name = "s_muestreo_auditoria_fechinic")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sMuestreoAuditoriaFechinic;
    @Column(name = "d_muestreo_auditoria_planmuestreo", length = 225)
    private String dMuestreoAuditoriaPlanmuestreo;
    @Column(name = "fs_muestreo_auditoria_tecnico", length = 45)
    private String fsMuestreoAuditoriaTecnico;
    @Column(name = "d_muestreo_auditoria_fecharecepmuestreo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoAuditoriaFecharecepmuestreo;
    @Column(name = "fi_muestreo_auditoria_proyecto")
    private Integer fiMuestreoAuditoriaProyecto;
    @Column(name = "fi_muestreo_auditoria_municipio")
    private Integer fiMuestreoAuditoriaMunicipio;
    @Column(name = "fi_muestreo_auditoria_ciudad")
    private Integer fiMuestreoAuditoriaCiudad;
    @Column(name = "fi_muestreo_auditoria_punto")
    private Integer fiMuestreoAuditoriaPunto;
    @Column(name = "fi_muestreo_auditoria_usuario", length = 22)
    private String fiMuestreoAuditoriaUsuario;
    @Column(name = "d_muestreo_auditoria_fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoAuditoriaFecharegistro;
    @Column(name = "fs_muestreo_auditoria_usuariomodificacion", length = 22)
    private String fsMuestreoAuditoriaUsuariomodificacion;
    @Column(name = "d_muestreo_auditoria_fechamodificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoAuditoriaFechamodificacion;
    @Column(name = "s_muestreo_observacion", length = 100)
    private String sMuestreoObservacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "muestreo_auditoria_pi", nullable = false)
    private Integer muestreoAuditoriaPi;

    public MuestreoAuditoria() {
    }

    public MuestreoAuditoria(Integer muestreoAuditoriaPi) {
        this.muestreoAuditoriaPi = muestreoAuditoriaPi;
    }

    public Integer getPiMuestreoAuditoriaId() {
        return piMuestreoAuditoriaId;
    }

    public void setPiMuestreoAuditoriaId(Integer piMuestreoAuditoriaId) {
        this.piMuestreoAuditoriaId = piMuestreoAuditoriaId;
    }

    public Date getSMuestreoAuditoriaFechinic() {
        return sMuestreoAuditoriaFechinic;
    }

    public void setSMuestreoAuditoriaFechinic(Date sMuestreoAuditoriaFechinic) {
        this.sMuestreoAuditoriaFechinic = sMuestreoAuditoriaFechinic;
    }

    public String getDMuestreoAuditoriaPlanmuestreo() {
        return dMuestreoAuditoriaPlanmuestreo;
    }

    public void setDMuestreoAuditoriaPlanmuestreo(String dMuestreoAuditoriaPlanmuestreo) {
        this.dMuestreoAuditoriaPlanmuestreo = dMuestreoAuditoriaPlanmuestreo;
    }

    public String getFsMuestreoAuditoriaTecnico() {
        return fsMuestreoAuditoriaTecnico;
    }

    public void setFsMuestreoAuditoriaTecnico(String fsMuestreoAuditoriaTecnico) {
        this.fsMuestreoAuditoriaTecnico = fsMuestreoAuditoriaTecnico;
    }

    public Date getDMuestreoAuditoriaFecharecepmuestreo() {
        return dMuestreoAuditoriaFecharecepmuestreo;
    }

    public void setDMuestreoAuditoriaFecharecepmuestreo(Date dMuestreoAuditoriaFecharecepmuestreo) {
        this.dMuestreoAuditoriaFecharecepmuestreo = dMuestreoAuditoriaFecharecepmuestreo;
    }

    public Integer getFiMuestreoAuditoriaProyecto() {
        return fiMuestreoAuditoriaProyecto;
    }

    public void setFiMuestreoAuditoriaProyecto(Integer fiMuestreoAuditoriaProyecto) {
        this.fiMuestreoAuditoriaProyecto = fiMuestreoAuditoriaProyecto;
    }

    public Integer getFiMuestreoAuditoriaMunicipio() {
        return fiMuestreoAuditoriaMunicipio;
    }

    public void setFiMuestreoAuditoriaMunicipio(Integer fiMuestreoAuditoriaMunicipio) {
        this.fiMuestreoAuditoriaMunicipio = fiMuestreoAuditoriaMunicipio;
    }

    public Integer getFiMuestreoAuditoriaCiudad() {
        return fiMuestreoAuditoriaCiudad;
    }

    public void setFiMuestreoAuditoriaCiudad(Integer fiMuestreoAuditoriaCiudad) {
        this.fiMuestreoAuditoriaCiudad = fiMuestreoAuditoriaCiudad;
    }

    public Integer getFiMuestreoAuditoriaPunto() {
        return fiMuestreoAuditoriaPunto;
    }

    public void setFiMuestreoAuditoriaPunto(Integer fiMuestreoAuditoriaPunto) {
        this.fiMuestreoAuditoriaPunto = fiMuestreoAuditoriaPunto;
    }

    public String getFiMuestreoAuditoriaUsuario() {
        return fiMuestreoAuditoriaUsuario;
    }

    public void setFiMuestreoAuditoriaUsuario(String fiMuestreoAuditoriaUsuario) {
        this.fiMuestreoAuditoriaUsuario = fiMuestreoAuditoriaUsuario;
    }

    public Date getDMuestreoAuditoriaFecharegistro() {
        return dMuestreoAuditoriaFecharegistro;
    }

    public void setDMuestreoAuditoriaFecharegistro(Date dMuestreoAuditoriaFecharegistro) {
        this.dMuestreoAuditoriaFecharegistro = dMuestreoAuditoriaFecharegistro;
    }

    public String getFsMuestreoAuditoriaUsuariomodificacion() {
        return fsMuestreoAuditoriaUsuariomodificacion;
    }

    public void setFsMuestreoAuditoriaUsuariomodificacion(String fsMuestreoAuditoriaUsuariomodificacion) {
        this.fsMuestreoAuditoriaUsuariomodificacion = fsMuestreoAuditoriaUsuariomodificacion;
    }

    public Date getDMuestreoAuditoriaFechamodificacion() {
        return dMuestreoAuditoriaFechamodificacion;
    }

    public void setDMuestreoAuditoriaFechamodificacion(Date dMuestreoAuditoriaFechamodificacion) {
        this.dMuestreoAuditoriaFechamodificacion = dMuestreoAuditoriaFechamodificacion;
    }

    public String getSMuestreoObservacion() {
        return sMuestreoObservacion;
    }

    public void setSMuestreoObservacion(String sMuestreoObservacion) {
        this.sMuestreoObservacion = sMuestreoObservacion;
    }

    public Integer getMuestreoAuditoriaPi() {
        return muestreoAuditoriaPi;
    }

    public void setMuestreoAuditoriaPi(Integer muestreoAuditoriaPi) {
        this.muestreoAuditoriaPi = muestreoAuditoriaPi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (muestreoAuditoriaPi != null ? muestreoAuditoriaPi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestreoAuditoria)) {
            return false;
        }
        MuestreoAuditoria other = (MuestreoAuditoria) object;
        if ((this.muestreoAuditoriaPi == null && other.muestreoAuditoriaPi != null) || (this.muestreoAuditoriaPi != null && !this.muestreoAuditoriaPi.equals(other.muestreoAuditoriaPi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexionbd.MuestreoAuditoria[ muestreoAuditoriaPi=" + muestreoAuditoriaPi + " ]";
    }
    
}
