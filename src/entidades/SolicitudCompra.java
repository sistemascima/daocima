/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "solicitud_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudCompra.findAll", query = "SELECT s FROM SolicitudCompra s"),
    @NamedQuery(name = "SolicitudCompra.findByPiSolicompConsecutivo", query = "SELECT s FROM SolicitudCompra s WHERE s.piSolicompConsecutivo = :piSolicompConsecutivo"),
    @NamedQuery(name = "SolicitudCompra.findByDSolicompFecha", query = "SELECT s FROM SolicitudCompra s WHERE s.dSolicompFecha = :dSolicompFecha"),
    @NamedQuery(name = "SolicitudCompra.findBySSolicompEstado", query = "SELECT s FROM SolicitudCompra s WHERE s.sSolicompEstado = :sSolicompEstado"),
    @NamedQuery(name = "SolicitudCompra.findBySSolicompTipocomp", query = "SELECT s FROM SolicitudCompra s WHERE s.sSolicompTipocomp = :sSolicompTipocomp"),
    @NamedQuery(name = "SolicitudCompra.findByDSolicompCreacion", query = "SELECT s FROM SolicitudCompra s WHERE s.dSolicompCreacion = :dSolicompCreacion"),
    @NamedQuery(name = "SolicitudCompra.findByDSolicompUltimodi", query = "SELECT s FROM SolicitudCompra s WHERE s.dSolicompUltimodi = :dSolicompUltimodi")})
public class SolicitudCompra implements Serializable {

    @Column(name = "s_solicomp_proyecto", length = 45)
    private String sSolicompProyecto;
    @JoinColumn(name = "fs_solicomp_usua_solicita", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsSolicompUsuaSolicita;
    @Basic(optional = false)
    @Column(name = "s_solicomp_estado")
    private Character sSolicompEstado;
    @Basic(optional = false)
    @Column(name = "s_solicomp_tipocomp")
    private Character sSolicompTipocomp;
    @JoinColumns({
        @JoinColumn(name = "fs_solicomp_tipodocumento", referencedColumnName = "pfs_document_tipodocu"),
        @JoinColumn(name = "fs_solicomp_letraprocedimiento", referencedColumnName = "pfs_document_letrproc"),
        @JoinColumn(name = "fs_solicomp_consecutivo", referencedColumnName = "ps_document_consecutivo"),
        @JoinColumn(name = "fs_solicomp_version", referencedColumnName = "ps_document_version")})
    @ManyToOne
    private Documento documento;
    @Column(name = "s_solicomp_observaciones")
    private String sSolicompObservaciones;
    @Column(name = "fs_solicomp_colaborador")
    private String fsSolicompColaborador;
    @Basic(optional = false)
    @Column(name = "s_solicomp_obseapro")
    private String sSolicompObseapro;
    @Column(name = "d_solicomp_vistbuen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSolicompVistbuen;
    @Column(name = "s_solicomp_vistbuen")
    private Character sSolicompVistbuen;
    @Column(name = "d_solicomp_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSolicompAprobacion;
    @Column(name = "s_solicomp_aprobado")
    private Character sSolicompAprobado;
    @JoinColumn(name = "fs_solicomp_tipproser", referencedColumnName = "ps_tipproser_codigo")
    @ManyToOne(optional = false)
    private TipoProductoServicio fsSolicompTipproser;
    @JoinColumn(name = "fs_solicomp_usuaapru", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsSolicompUsuaapru;
    @JoinColumn(name = "fs_solicomp_carvisbue", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(optional = false)
    private Cargo fsSolicompCarvisbue;
    @JoinColumn(name = "fs_solicomp_usuvisbue", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsSolicompUsuvisbue;
    @JoinColumn(name = "fs_solicomp_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsSolicompUsuultmod;
    @JoinColumn(name = "fs_solicomp_usuasoli", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsSolicompUsuasoli;
    @JoinColumn(name = "fs_solicomp_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsSolicompUsuacrea;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_solicomp_consecutivo")
    private Integer piSolicompConsecutivo;
    @Basic(optional = false)
    @Column(name = "d_solicomp_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSolicompFecha;
    @Basic(optional = false)
    @Column(name = "d_solicomp_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSolicompCreacion;
    @Column(name = "d_solicomp_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSolicompUltimodi;
    @JoinColumn(name = "fs_solicomp_departamento", referencedColumnName = "ps_departam_codigo")
    @ManyToOne(optional = false)
    private Departamento fsSolicompDepartamento;
    @JoinColumn(name = "fs_solicomp_cargsoli", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(optional = false)
    private Cargo fsSolicompCargsoli;
    @JoinColumn(name = "fs_solicomp_cargapru", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(optional = false)
    private Cargo fsSolicompCargapru;
    

    public SolicitudCompra() {
    }

    public SolicitudCompra(Integer piSolicompConsecutivo) {
        this.piSolicompConsecutivo = piSolicompConsecutivo;
    }

    public SolicitudCompra(Integer piSolicompConsecutivo, Date dSolicompFecha, char sSolicompEstado, char sSolicompTipocomp, Date dSolicompCreacion) {
        this.piSolicompConsecutivo = piSolicompConsecutivo;
        this.dSolicompFecha = dSolicompFecha;
        this.sSolicompEstado = sSolicompEstado;
        this.sSolicompTipocomp = sSolicompTipocomp;
        this.dSolicompCreacion = dSolicompCreacion;
    }

    public Integer getPiSolicompConsecutivo() {
        return piSolicompConsecutivo;
    }

    public void setPiSolicompConsecutivo(Integer piSolicompConsecutivo) {
        this.piSolicompConsecutivo = piSolicompConsecutivo;
    }

    public Date getDSolicompFecha() {
        return dSolicompFecha;
    }

    public void setDSolicompFecha(Date dSolicompFecha) {
        this.dSolicompFecha = dSolicompFecha;
    }

    public char getSSolicompEstado() {
        return sSolicompEstado;
    }

    public void setSSolicompEstado(char sSolicompEstado) {
        this.sSolicompEstado = sSolicompEstado;
    }

    public char getSSolicompTipocomp() {
        return sSolicompTipocomp;
    }

    public void setSSolicompTipocomp(char sSolicompTipocomp) {
        this.sSolicompTipocomp = sSolicompTipocomp;
    }

    public Date getDSolicompCreacion() {
        return dSolicompCreacion;
    }

    public void setDSolicompCreacion(Date dSolicompCreacion) {
        this.dSolicompCreacion = dSolicompCreacion;
    }

    public Date getDSolicompUltimodi() {
        return dSolicompUltimodi;
    }

    public void setDSolicompUltimodi(Date dSolicompUltimodi) {
        this.dSolicompUltimodi = dSolicompUltimodi;
    }

    public Departamento getFsSolicompDepartamento() {
        return fsSolicompDepartamento;
    }

    public void setFsSolicompDepartamento(Departamento fsSolicompDepartamento) {
        this.fsSolicompDepartamento = fsSolicompDepartamento;
    }

    public Cargo getFsSolicompCargsoli() {
        return fsSolicompCargsoli;
    }

    public void setFsSolicompCargsoli(Cargo fsSolicompCargsoli) {
        this.fsSolicompCargsoli = fsSolicompCargsoli;
    }

    public Cargo getFsSolicompCargapru() {
        return fsSolicompCargapru;
    }

    public void setFsSolicompCargapru(Cargo fsSolicompCargapru) {
        this.fsSolicompCargapru = fsSolicompCargapru;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piSolicompConsecutivo != null ? piSolicompConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudCompra)) {
            return false;
        }
        SolicitudCompra other = (SolicitudCompra) object;
        if ((this.piSolicompConsecutivo == null && other.piSolicompConsecutivo != null) || (this.piSolicompConsecutivo != null && !this.piSolicompConsecutivo.equals(other.piSolicompConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SolicitudCompra[ piSolicompConsecutivo=" + piSolicompConsecutivo + " ]";
    }

    public SUsuario getFsSolicompUsuultmod() {
        return fsSolicompUsuultmod;
    }

    public void setFsSolicompUsuultmod(SUsuario fsSolicompUsuultmod) {
        this.fsSolicompUsuultmod = fsSolicompUsuultmod;
    }

    public SUsuario getFsSolicompUsuasoli() {
        return fsSolicompUsuasoli;
    }

    public void setFsSolicompUsuasoli(SUsuario fsSolicompUsuasoli) {
        this.fsSolicompUsuasoli = fsSolicompUsuasoli;
    }

    public SUsuario getFsSolicompUsuacrea() {
        return fsSolicompUsuacrea;
    }

    public void setFsSolicompUsuacrea(SUsuario fsSolicompUsuacrea) {
        this.fsSolicompUsuacrea = fsSolicompUsuacrea;
    }

    public Date getDSolicompVistbuen() {
        return dSolicompVistbuen;
    }

    public void setDSolicompVistbuen(Date dSolicompVistbuen) {
        this.dSolicompVistbuen = dSolicompVistbuen;
    }

    public Character getSSolicompVistbuen() {
        return sSolicompVistbuen;
    }

    public void setSSolicompVistbuen(Character sSolicompVistbuen) {
        this.sSolicompVistbuen = sSolicompVistbuen;
    }

    public Date getDSolicompAprobacion() {
        return dSolicompAprobacion;
    }

    public void setDSolicompAprobacion(Date dSolicompAprobacion) {
        this.dSolicompAprobacion = dSolicompAprobacion;
    }

    public Character getSSolicompAprobado() {
        return sSolicompAprobado;
    }

    public void setSSolicompAprobado(Character sSolicompAprobado) {
        this.sSolicompAprobado = sSolicompAprobado;
    }

    public TipoProductoServicio getFsSolicompTipproser() {
        return fsSolicompTipproser;
    }

    public void setFsSolicompTipproser(TipoProductoServicio fsSolicompTipproser) {
        this.fsSolicompTipproser = fsSolicompTipproser;
    }

    public SUsuario getFsSolicompUsuaapru() {
        return fsSolicompUsuaapru;
    }

    public void setFsSolicompUsuaapru(SUsuario fsSolicompUsuaapru) {
        this.fsSolicompUsuaapru = fsSolicompUsuaapru;
    }

    public Cargo getFsSolicompCarvisbue() {
        return fsSolicompCarvisbue;
    }

    public void setFsSolicompCarvisbue(Cargo fsSolicompCarvisbue) {
        this.fsSolicompCarvisbue = fsSolicompCarvisbue;
    }

    public SUsuario getFsSolicompUsuvisbue() {
        return fsSolicompUsuvisbue;
    }

    public void setFsSolicompUsuvisbue(SUsuario fsSolicompUsuvisbue) {
        this.fsSolicompUsuvisbue = fsSolicompUsuvisbue;
    }

    public String getSSolicompObseapro() {
        return sSolicompObseapro;
    }

    public void setSSolicompObseapro(String sSolicompObseapro) {
        this.sSolicompObseapro = sSolicompObseapro;
    }

    public String getFsSolicompColaborador() {
        return fsSolicompColaborador;
    }

    public void setFsSolicompColaborador(String fsSolicompColaborador) {
        this.fsSolicompColaborador = fsSolicompColaborador;
    }

    public String getSSolicompObservaciones() {
        return sSolicompObservaciones;
    }

    public void setSSolicompObservaciones(String sSolicompObservaciones) {
        this.sSolicompObservaciones = sSolicompObservaciones;
    }

    public void setSSolicompTipocomp(Character sSolicompTipocomp) {
        this.sSolicompTipocomp = sSolicompTipocomp;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public SUsuario getFsSolicompUsuaSolicita() {
        return fsSolicompUsuaSolicita;
    }

    public void setFsSolicompUsuaSolicita(SUsuario fsSolicompUsuaSolicita) {
        this.fsSolicompUsuaSolicita = fsSolicompUsuaSolicita;
    }

  

    public String getSSolicompProyecto() {
        return sSolicompProyecto;
    }

    public void setSSolicompProyecto(String sSolicompProyecto) {
        this.sSolicompProyecto = sSolicompProyecto;
    }

    

}
