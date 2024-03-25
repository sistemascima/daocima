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
@Table(name = "reporte_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteAuditoria.findAll", query = "SELECT r FROM ReporteAuditoria r"),
    @NamedQuery(name = "ReporteAuditoria.findByPiReporteAuditoriaId", query = "SELECT r FROM ReporteAuditoria r WHERE r.piReporteAuditoriaId = :piReporteAuditoriaId"),
    @NamedQuery(name = "ReporteAuditoria.findByFiReporteAuditoriaCliente", query = "SELECT r FROM ReporteAuditoria r WHERE r.fiReporteAuditoriaCliente = :fiReporteAuditoriaCliente"),
    @NamedQuery(name = "ReporteAuditoria.findBySReporteAuditoriaVersion", query = "SELECT r FROM ReporteAuditoria r WHERE r.sReporteAuditoriaVersion = :sReporteAuditoriaVersion"),
    @NamedQuery(name = "ReporteAuditoria.findByIReporteAuditoriaAnexos", query = "SELECT r FROM ReporteAuditoria r WHERE r.iReporteAuditoriaAnexos = :iReporteAuditoriaAnexos"),
    @NamedQuery(name = "ReporteAuditoria.findByFsReporteAuditoriaUsuaaprob", query = "SELECT r FROM ReporteAuditoria r WHERE r.fsReporteAuditoriaUsuaaprob = :fsReporteAuditoriaUsuaaprob"),
    @NamedQuery(name = "ReporteAuditoria.findByFsReporteAuditoriaUsuarevi", query = "SELECT r FROM ReporteAuditoria r WHERE r.fsReporteAuditoriaUsuarevi = :fsReporteAuditoriaUsuarevi"),
    @NamedQuery(name = "ReporteAuditoria.findByCReporteAuditoriaEstado", query = "SELECT r FROM ReporteAuditoria r WHERE r.cReporteAuditoriaEstado = :cReporteAuditoriaEstado"),
    @NamedQuery(name = "ReporteAuditoria.findByFiReporteAuditoriaMuestreo", query = "SELECT r FROM ReporteAuditoria r WHERE r.fiReporteAuditoriaMuestreo = :fiReporteAuditoriaMuestreo"),
    @NamedQuery(name = "ReporteAuditoria.findByFiReporteAuditoriaProyecto", query = "SELECT r FROM ReporteAuditoria r WHERE r.fiReporteAuditoriaProyecto = :fiReporteAuditoriaProyecto"),
    @NamedQuery(name = "ReporteAuditoria.findByDReporteAuditoriaFechcreaci", query = "SELECT r FROM ReporteAuditoria r WHERE r.dReporteAuditoriaFechcreaci = :dReporteAuditoriaFechcreaci"),
    @NamedQuery(name = "ReporteAuditoria.findByFsReporteAuditoriaClientenomb", query = "SELECT r FROM ReporteAuditoria r WHERE r.fsReporteAuditoriaClientenomb = :fsReporteAuditoriaClientenomb"),
    @NamedQuery(name = "ReporteAuditoria.findByFsReporteAuditoriaUsuacread", query = "SELECT r FROM ReporteAuditoria r WHERE r.fsReporteAuditoriaUsuacread = :fsReporteAuditoriaUsuacread"),
    @NamedQuery(name = "ReporteAuditoria.findBySReporteAuditoriaObservaciones", query = "SELECT r FROM ReporteAuditoria r WHERE r.sReporteAuditoriaObservaciones = :sReporteAuditoriaObservaciones"),
    @NamedQuery(name = "ReporteAuditoria.findByDReporteAuditoriaFechmodi", query = "SELECT r FROM ReporteAuditoria r WHERE r.dReporteAuditoriaFechmodi = :dReporteAuditoriaFechmodi"),
    @NamedQuery(name = "ReporteAuditoria.findByFsReporteAuditoriaUsuamodi", query = "SELECT r FROM ReporteAuditoria r WHERE r.fsReporteAuditoriaUsuamodi = :fsReporteAuditoriaUsuamodi"),
    @NamedQuery(name = "ReporteAuditoria.findByDReporteAuditoriaFechapro", query = "SELECT r FROM ReporteAuditoria r WHERE r.dReporteAuditoriaFechapro = :dReporteAuditoriaFechapro"),
    @NamedQuery(name = "ReporteAuditoria.findByDReporteAuditoriaFecharevi", query = "SELECT r FROM ReporteAuditoria r WHERE r.dReporteAuditoriaFecharevi = :dReporteAuditoriaFecharevi"),
    @NamedQuery(name = "ReporteAuditoria.findByDReporteAuditoriaFechaentrega", query = "SELECT r FROM ReporteAuditoria r WHERE r.dReporteAuditoriaFechaentrega = :dReporteAuditoriaFechaentrega"),
    @NamedQuery(name = "ReporteAuditoria.findByReporteAuditoriaId", query = "SELECT r FROM ReporteAuditoria r WHERE r.reporteAuditoriaId = :reporteAuditoriaId")})
public class ReporteAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pi_reporte_auditoria_id", length = 20)
    private String piReporteAuditoriaId;
    @Column(name = "fi_reporte_auditoria_cliente", length = 100)
    private String fiReporteAuditoriaCliente;
    @Column(name = "s_reporte_auditoria_version", length = 20)
    private String sReporteAuditoriaVersion;
    @Column(name = "i_reporte_auditoria_anexos")
    private Integer iReporteAuditoriaAnexos;
    @Column(name = "fs_reporte_auditoria_usuaaprob", length = 20)
    private String fsReporteAuditoriaUsuaaprob;
    @Column(name = "fs_reporte_auditoria_usuarevi", length = 20)
    private String fsReporteAuditoriaUsuarevi;
    @Column(name = "c_reporte_auditoria_estado", length = 1)
    private String cReporteAuditoriaEstado;
    @Column(name = "fi_reporte_auditoria_muestreo")
    private Integer fiReporteAuditoriaMuestreo;
    @Column(name = "fi_reporte_auditoria_proyecto")
    private Integer fiReporteAuditoriaProyecto;
    @Column(name = "d_reporte_auditoria_fechcreaci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteAuditoriaFechcreaci;
    @Column(name = "fs_reporte_auditoria_clientenomb", length = 100)
    private String fsReporteAuditoriaClientenomb;
    @Column(name = "fs_reporte_auditoria_usuacread", length = 22)
    private String fsReporteAuditoriaUsuacread;
    @Column(name = "s_reporte_auditoria_observaciones", length = 225)
    private String sReporteAuditoriaObservaciones;
    @Column(name = "d_reporte_auditoria_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteAuditoriaFechmodi;
    @Column(name = "fs_reporte_auditoria_usuamodi", length = 22)
    private String fsReporteAuditoriaUsuamodi;
    @Column(name = "d_reporte_auditoria_fechapro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteAuditoriaFechapro;
    @Column(name = "d_reporte_auditoria_fecharevi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteAuditoriaFecharevi;
    @Column(name = "d_reporte_auditoria_fechaentrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteAuditoriaFechaentrega;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reporte_auditoria_id", nullable = false)
    private Integer reporteAuditoriaId;

    public ReporteAuditoria() {
    }

    public ReporteAuditoria(Integer reporteAuditoriaId) {
        this.reporteAuditoriaId = reporteAuditoriaId;
    }

    public String getPiReporteAuditoriaId() {
        return piReporteAuditoriaId;
    }

    public void setPiReporteAuditoriaId(String piReporteAuditoriaId) {
        this.piReporteAuditoriaId = piReporteAuditoriaId;
    }

    public String getFiReporteAuditoriaCliente() {
        return fiReporteAuditoriaCliente;
    }

    public void setFiReporteAuditoriaCliente(String fiReporteAuditoriaCliente) {
        this.fiReporteAuditoriaCliente = fiReporteAuditoriaCliente;
    }

    public String getSReporteAuditoriaVersion() {
        return sReporteAuditoriaVersion;
    }

    public void setSReporteAuditoriaVersion(String sReporteAuditoriaVersion) {
        this.sReporteAuditoriaVersion = sReporteAuditoriaVersion;
    }

    public Integer getIReporteAuditoriaAnexos() {
        return iReporteAuditoriaAnexos;
    }

    public void setIReporteAuditoriaAnexos(Integer iReporteAuditoriaAnexos) {
        this.iReporteAuditoriaAnexos = iReporteAuditoriaAnexos;
    }

    public String getFsReporteAuditoriaUsuaaprob() {
        return fsReporteAuditoriaUsuaaprob;
    }

    public void setFsReporteAuditoriaUsuaaprob(String fsReporteAuditoriaUsuaaprob) {
        this.fsReporteAuditoriaUsuaaprob = fsReporteAuditoriaUsuaaprob;
    }

    public String getFsReporteAuditoriaUsuarevi() {
        return fsReporteAuditoriaUsuarevi;
    }

    public void setFsReporteAuditoriaUsuarevi(String fsReporteAuditoriaUsuarevi) {
        this.fsReporteAuditoriaUsuarevi = fsReporteAuditoriaUsuarevi;
    }

    public String getCReporteAuditoriaEstado() {
        return cReporteAuditoriaEstado;
    }

    public void setCReporteAuditoriaEstado(String cReporteAuditoriaEstado) {
        this.cReporteAuditoriaEstado = cReporteAuditoriaEstado;
    }

    public Integer getFiReporteAuditoriaMuestreo() {
        return fiReporteAuditoriaMuestreo;
    }

    public void setFiReporteAuditoriaMuestreo(Integer fiReporteAuditoriaMuestreo) {
        this.fiReporteAuditoriaMuestreo = fiReporteAuditoriaMuestreo;
    }

    public Integer getFiReporteAuditoriaProyecto() {
        return fiReporteAuditoriaProyecto;
    }

    public void setFiReporteAuditoriaProyecto(Integer fiReporteAuditoriaProyecto) {
        this.fiReporteAuditoriaProyecto = fiReporteAuditoriaProyecto;
    }

    public Date getDReporteAuditoriaFechcreaci() {
        return dReporteAuditoriaFechcreaci;
    }

    public void setDReporteAuditoriaFechcreaci(Date dReporteAuditoriaFechcreaci) {
        this.dReporteAuditoriaFechcreaci = dReporteAuditoriaFechcreaci;
    }

    public String getFsReporteAuditoriaClientenomb() {
        return fsReporteAuditoriaClientenomb;
    }

    public void setFsReporteAuditoriaClientenomb(String fsReporteAuditoriaClientenomb) {
        this.fsReporteAuditoriaClientenomb = fsReporteAuditoriaClientenomb;
    }

    public String getFsReporteAuditoriaUsuacread() {
        return fsReporteAuditoriaUsuacread;
    }

    public void setFsReporteAuditoriaUsuacread(String fsReporteAuditoriaUsuacread) {
        this.fsReporteAuditoriaUsuacread = fsReporteAuditoriaUsuacread;
    }

    public String getSReporteAuditoriaObservaciones() {
        return sReporteAuditoriaObservaciones;
    }

    public void setSReporteAuditoriaObservaciones(String sReporteAuditoriaObservaciones) {
        this.sReporteAuditoriaObservaciones = sReporteAuditoriaObservaciones;
    }

    public Date getDReporteAuditoriaFechmodi() {
        return dReporteAuditoriaFechmodi;
    }

    public void setDReporteAuditoriaFechmodi(Date dReporteAuditoriaFechmodi) {
        this.dReporteAuditoriaFechmodi = dReporteAuditoriaFechmodi;
    }

    public String getFsReporteAuditoriaUsuamodi() {
        return fsReporteAuditoriaUsuamodi;
    }

    public void setFsReporteAuditoriaUsuamodi(String fsReporteAuditoriaUsuamodi) {
        this.fsReporteAuditoriaUsuamodi = fsReporteAuditoriaUsuamodi;
    }

    public Date getDReporteAuditoriaFechapro() {
        return dReporteAuditoriaFechapro;
    }

    public void setDReporteAuditoriaFechapro(Date dReporteAuditoriaFechapro) {
        this.dReporteAuditoriaFechapro = dReporteAuditoriaFechapro;
    }

    public Date getDReporteAuditoriaFecharevi() {
        return dReporteAuditoriaFecharevi;
    }

    public void setDReporteAuditoriaFecharevi(Date dReporteAuditoriaFecharevi) {
        this.dReporteAuditoriaFecharevi = dReporteAuditoriaFecharevi;
    }

    public Date getDReporteAuditoriaFechaentrega() {
        return dReporteAuditoriaFechaentrega;
    }

    public void setDReporteAuditoriaFechaentrega(Date dReporteAuditoriaFechaentrega) {
        this.dReporteAuditoriaFechaentrega = dReporteAuditoriaFechaentrega;
    }

    public Integer getReporteAuditoriaId() {
        return reporteAuditoriaId;
    }

    public void setReporteAuditoriaId(Integer reporteAuditoriaId) {
        this.reporteAuditoriaId = reporteAuditoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporteAuditoriaId != null ? reporteAuditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteAuditoria)) {
            return false;
        }
        ReporteAuditoria other = (ReporteAuditoria) object;
        if ((this.reporteAuditoriaId == null && other.reporteAuditoriaId != null) || (this.reporteAuditoriaId != null && !this.reporteAuditoriaId.equals(other.reporteAuditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexionbd.ReporteAuditoria[ reporteAuditoriaId=" + reporteAuditoriaId + " ]";
    }
    
}
