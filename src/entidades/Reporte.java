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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findByNoAnexos", query = "SELECT r FROM Reporte r WHERE r.noAnexos = :noAnexos"),
    @NamedQuery(name = "Reporte.findByEstado", query = "SELECT r FROM Reporte r WHERE r.estado = :estado"),
    @NamedQuery(name = "Reporte.findByFechaEntrega", query = "SELECT r FROM Reporte r WHERE r.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Reporte.findByFechaCreacion", query = "SELECT r FROM Reporte r WHERE r.fechaCreacion = :fechaCreacion")})
public class Reporte implements Serializable {

   
    @JoinColumns({
        @JoinColumn(name = "fs_reporte_document_tipodocu", referencedColumnName = "pfs_document_tipodocu"),
        @JoinColumn(name = "fs_reporte_document_letrproc", referencedColumnName = "pfs_document_letrproc"),
        @JoinColumn(name = "fs_reporte_document_consecutivo", referencedColumnName = "ps_document_consecutivo"),
        @JoinColumn(name = "fs_reporte_document_version", referencedColumnName = "ps_document_version")})
    @ManyToOne
    private Documento documento;
     
    @OneToMany(mappedBy = "fsFotohidroReporte")
    private List<FotoHidrobiologia> fotoHidrobiologiaList;

   
    @Id
    @Basic(optional = false)
    @Column(name = "pi_reporte_id", nullable = false, length = 20)
    private String piReporteId;
    @Column(name = "s_reporte_version", length = 25)
    private String sReporteVersion;
    @Column(name = "fs_reporte_clientenomb", length = 100)
    private String fsReporteClientenomb;
    private static final long serialVersionUID = 1L;
    @Column(name = "i_reporte_anexos")
    private Integer noAnexos;
    @Column(name = "i_reporte_numero")
    private Integer i_reporte_numero;
    @Column(name = "c_reporte_estado", length = 1)
    private String estado;
     @Column(name = "s_reporte_observaciones", length = 225)
    private String observaciones;
    @Column(name = "d_reporte_fechaentrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Column(name = "d_reporte_fechcreaci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "d_reporte_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
     @Column(name = "d_reporte_fechapro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
     @Column(name = "d_reporte_fecharevi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @JoinColumns({
        @JoinColumn(name = "fi_reporte_cliente", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name="fi_reporte_cliente", referencedColumnName="s_cliente_nombre")
    })
    @ManyToOne(optional = false)
    private Cliente idCliente;
     @JoinColumns({
        @JoinColumn(name = "fi_reporte_facturacion", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name="fi_reporte_facturacion", referencedColumnName="s_cliente_nombre")
    })
    @ManyToOne(optional = false)
    private Cliente clienteFacturacion;
    @JoinColumn(name = "fi_reporte_proyecto", referencedColumnName = "pi_proyecto_id", nullable = false)
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @JoinColumn(name = "fi_reporte_muestreo", referencedColumnName = "pi_muestreo_id")
    @ManyToOne
    private Muestreo idMuestreo;
    @JoinColumn(name = "fs_reporte_usuaaprob", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario aprobado;
     @JoinColumn(name = "fs_reporte_usuacread", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioCreador;
      @JoinColumn(name = "fs_reporte_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioModificador;
    @JoinColumn(name = "fs_reporte_usuarevi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario revisado;

    @OneToMany(mappedBy = "reporte")
    private List<Muestra> muestraList;



    public Integer getNoAnexos() {
        return noAnexos;
    }

    public void setNoAnexos(Integer noAnexos) {
        this.noAnexos = noAnexos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getClienteFacturacion() {
        return clienteFacturacion;
    }

    public void setClienteFacturacion(Cliente clienteFacturacion) {
        this.clienteFacturacion = clienteFacturacion;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Muestreo getIdMuestreo() {
        return idMuestreo;
    }

    public void setIdMuestreo(Muestreo idMuestreo) {
        this.idMuestreo = idMuestreo;
    }

    public SUsuario getAprobado() {
        return aprobado;
    }

    public void setAprobado(SUsuario aprobado) {
        this.aprobado = aprobado;
    }

    public SUsuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(SUsuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public SUsuario getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(SUsuario usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    public SUsuario getRevisado() {
        return revisado;
    }

    public void setRevisado(SUsuario revisado) {
        this.revisado = revisado;
    }



    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }

    public Reporte() {
    }

    public Reporte(String piReporteId) {
        this.piReporteId = piReporteId;
    }

    public String getPiReporteId() {
        return piReporteId;
    }

    public void setPiReporteId(String piReporteId) {
        this.piReporteId = piReporteId;
    }

    public String getSReporteVersion() {
        return sReporteVersion;
    }

    public void setSReporteVersion(String sReporteVersion) {
        this.sReporteVersion = sReporteVersion;
    }

    public String getFsReporteClientenomb() {
        return fsReporteClientenomb;
    }

    public void setFsReporteClientenomb(String fsReporteClientenomb) {
        this.fsReporteClientenomb = fsReporteClientenomb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piReporteId != null ? piReporteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.piReporteId == null && other.piReporteId != null) || (this.piReporteId != null && !this.piReporteId.equals(other.piReporteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Reporte[ piReporteId=" + piReporteId + " ]";
    }

   

    

    @XmlTransient
    public List<FotoHidrobiologia> getFotoHidrobiologiaList() {
        return fotoHidrobiologiaList;
    }

    public void setFotoHidrobiologiaList(List<FotoHidrobiologia> fotoHidrobiologiaList) {
        this.fotoHidrobiologiaList = fotoHidrobiologiaList;
    }

    public String getsReporteVersion() {
        return sReporteVersion;
    }

    public void setsReporteVersion(String sReporteVersion) {
        this.sReporteVersion = sReporteVersion;
    }

    public Integer getI_reporte_numero() {
        return i_reporte_numero;
    }

    public void setI_reporte_numero(Integer i_reporte_numero) {
        this.i_reporte_numero = i_reporte_numero;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

   
   

 
}
