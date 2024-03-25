/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "reporte_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteHistorico.findAll", query = "SELECT r FROM ReporteHistorico r"),
    @NamedQuery(name = "ReporteHistorico.findByPiReporteHistoricoId", query = "SELECT r FROM ReporteHistorico r WHERE r.reporteHistoricoPK.piReporteHistoricoId = :piReporteHistoricoId"),
    @NamedQuery(name = "ReporteHistorico.findByIReporteHistoricoAnexos", query = "SELECT r FROM ReporteHistorico r WHERE r.iReporteHistoricoAnexos = :iReporteHistoricoAnexos"),
    @NamedQuery(name = "ReporteHistorico.findByIReporteHistoricoVersion", query = "SELECT r FROM ReporteHistorico r WHERE r.reporteHistoricoPK.iReporteHistoricoVersion = :iReporteHistoricoVersion"),
    @NamedQuery(name = "ReporteHistorico.findByDReporteHistoricoFechacreacion", query = "SELECT r FROM ReporteHistorico r WHERE r.dReporteHistoricoFechacreacion = :dReporteHistoricoFechacreacion"),
    @NamedQuery(name = "ReporteHistorico.findByDReporteHistoricoFechaaprobacion", query = "SELECT r FROM ReporteHistorico r WHERE r.dReporteHistoricoFechaaprobacion = :dReporteHistoricoFechaaprobacion"),
    @NamedQuery(name = "ReporteHistorico.findByDReporteHistoricoFechaentrega", query = "SELECT r FROM ReporteHistorico r WHERE r.dReporteHistoricoFechaentrega = :dReporteHistoricoFechaentrega"),
    @NamedQuery(name = "ReporteHistorico.findByDReporteHistoricoFechamod", query = "SELECT r FROM ReporteHistorico r WHERE r.dReporteHistoricoFechamod = :dReporteHistoricoFechamod")})
public class ReporteHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReporteHistoricoPK reporteHistoricoPK;
    @Column(name = "i_reporte_historico_anexos")
    private Integer iReporteHistoricoAnexos;
    @Column(name = "d_reporte_historico_fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteHistoricoFechacreacion;
    @Column(name = "d_reporte_historico_fechaaprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteHistoricoFechaaprobacion;
    @Column(name = "d_reporte_historico_fechaentrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteHistoricoFechaentrega;
    @Column(name = "d_reporte_historico_fechamod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dReporteHistoricoFechamod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporteHistorico")
    private List<MuestraHistorico> muestraHistoricoList;
     @JoinColumns({
        @JoinColumn(name = "ps_reporte_historico_cliente", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name="ps_reporte_historico_cliente", referencedColumnName="s_cliente_nombre")
    })
    @ManyToOne(optional = false)
    private Cliente psReporteHistoricoCliente;  
      @JoinColumns({
        @JoinColumn(name = "fks_reporte_historico_cliente_facturacion", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name = "fks_reporte_historico_cliente_facturacion", referencedColumnName = "s_cliente_nombre")
    })
    @ManyToOne(optional = false)
    private Cliente fksReporteHistoricoClienteFacturacion;
    @JoinColumn(name = "fi_reporte_historico_muestreo", referencedColumnName = "pi_muestreo_historico_id")
    @ManyToOne
    private MuestreoHistorico fiReporteHistoricoMuestreo;
    @JoinColumn(name = "fi_reporte_historico_proyecto", referencedColumnName = "pi_proyecto_id")
    @ManyToOne
    private Proyecto fiReporteHistoricoProyecto;
    @JoinColumn(name = "fs_reporte_historico_usuaaprobacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsReporteHistoricoUsuaaprobacion;
    @JoinColumn(name = "fs_reporte_historico_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsReporteHistoricoUsuacreacion;
    @JoinColumn(name = "fs_reporte_historico_usuariomodificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsReporteHistoricoUsuariomodificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporteHistorico")
    private List<MuestraAnalisisHistorico> muestraAnalisisHistoricoList;

    public ReporteHistorico() {
    }

    public ReporteHistorico(ReporteHistoricoPK reporteHistoricoPK) {
        this.reporteHistoricoPK = reporteHistoricoPK;
    }

    public ReporteHistorico(String piReporteHistoricoId, int iReporteHistoricoVersion) {
        this.reporteHistoricoPK = new ReporteHistoricoPK(piReporteHistoricoId, iReporteHistoricoVersion);
    }

    public ReporteHistoricoPK getReporteHistoricoPK() {
        return reporteHistoricoPK;
    }

    public void setReporteHistoricoPK(ReporteHistoricoPK reporteHistoricoPK) {
        this.reporteHistoricoPK = reporteHistoricoPK;
    }

    public Integer getIReporteHistoricoAnexos() {
        return iReporteHistoricoAnexos;
    }

    public void setIReporteHistoricoAnexos(Integer iReporteHistoricoAnexos) {
        this.iReporteHistoricoAnexos = iReporteHistoricoAnexos;
    }

    public Date getDReporteHistoricoFechacreacion() {
        return dReporteHistoricoFechacreacion;
    }

    public void setDReporteHistoricoFechacreacion(Date dReporteHistoricoFechacreacion) {
        this.dReporteHistoricoFechacreacion = dReporteHistoricoFechacreacion;
    }

    public Date getDReporteHistoricoFechaaprobacion() {
        return dReporteHistoricoFechaaprobacion;
    }

    public void setDReporteHistoricoFechaaprobacion(Date dReporteHistoricoFechaaprobacion) {
        this.dReporteHistoricoFechaaprobacion = dReporteHistoricoFechaaprobacion;
    }

    public Date getDReporteHistoricoFechaentrega() {
        return dReporteHistoricoFechaentrega;
    }

    public void setDReporteHistoricoFechaentrega(Date dReporteHistoricoFechaentrega) {
        this.dReporteHistoricoFechaentrega = dReporteHistoricoFechaentrega;
    }

    public Date getDReporteHistoricoFechamod() {
        return dReporteHistoricoFechamod;
    }

    public void setDReporteHistoricoFechamod(Date dReporteHistoricoFechamod) {
        this.dReporteHistoricoFechamod = dReporteHistoricoFechamod;
    }

    @XmlTransient
    public List<MuestraHistorico> getMuestraHistoricoList() {
        return muestraHistoricoList;
    }

    public void setMuestraHistoricoList(List<MuestraHistorico> muestraHistoricoList) {
        this.muestraHistoricoList = muestraHistoricoList;
    }

    public Cliente getPsReporteHistoricoCliente() {
        return psReporteHistoricoCliente;
    }

    public void setPsReporteHistoricoCliente(Cliente psReporteHistoricoCliente) {
        this.psReporteHistoricoCliente = psReporteHistoricoCliente;
    }

    public Cliente getFksReporteHistoricoClienteFacturacion() {
        return fksReporteHistoricoClienteFacturacion;
    }

    public void setFksReporteHistoricoClienteFacturacion(Cliente fksReporteHistoricoClienteFacturacion) {
        this.fksReporteHistoricoClienteFacturacion = fksReporteHistoricoClienteFacturacion;
    }

    public MuestreoHistorico getFiReporteHistoricoMuestreo() {
        return fiReporteHistoricoMuestreo;
    }

    public void setFiReporteHistoricoMuestreo(MuestreoHistorico fiReporteHistoricoMuestreo) {
        this.fiReporteHistoricoMuestreo = fiReporteHistoricoMuestreo;
    }

    public Proyecto getFiReporteHistoricoProyecto() {
        return fiReporteHistoricoProyecto;
    }

    public void setFiReporteHistoricoProyecto(Proyecto fiReporteHistoricoProyecto) {
        this.fiReporteHistoricoProyecto = fiReporteHistoricoProyecto;
    }

    public SUsuario getFsReporteHistoricoUsuaaprobacion() {
        return fsReporteHistoricoUsuaaprobacion;
    }

    public void setFsReporteHistoricoUsuaaprobacion(SUsuario fsReporteHistoricoUsuaaprobacion) {
        this.fsReporteHistoricoUsuaaprobacion = fsReporteHistoricoUsuaaprobacion;
    }

    public SUsuario getFsReporteHistoricoUsuacreacion() {
        return fsReporteHistoricoUsuacreacion;
    }

    public void setFsReporteHistoricoUsuacreacion(SUsuario fsReporteHistoricoUsuacreacion) {
        this.fsReporteHistoricoUsuacreacion = fsReporteHistoricoUsuacreacion;
    }

    public SUsuario getFsReporteHistoricoUsuariomodificacion() {
        return fsReporteHistoricoUsuariomodificacion;
    }

    public void setFsReporteHistoricoUsuariomodificacion(SUsuario fsReporteHistoricoUsuariomodificacion) {
        this.fsReporteHistoricoUsuariomodificacion = fsReporteHistoricoUsuariomodificacion;
    }

    @XmlTransient
    public List<MuestraAnalisisHistorico> getMuestraAnalisisHistoricoList() {
        return muestraAnalisisHistoricoList;
    }

    public void setMuestraAnalisisHistoricoList(List<MuestraAnalisisHistorico> muestraAnalisisHistoricoList) {
        this.muestraAnalisisHistoricoList = muestraAnalisisHistoricoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporteHistoricoPK != null ? reporteHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteHistorico)) {
            return false;
        }
        ReporteHistorico other = (ReporteHistorico) object;
        if ((this.reporteHistoricoPK == null && other.reporteHistoricoPK != null) || (this.reporteHistoricoPK != null && !this.reporteHistoricoPK.equals(other.reporteHistoricoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReporteHistorico[ reporteHistoricoPK=" + reporteHistoricoPK + " ]";
    }
    
}
