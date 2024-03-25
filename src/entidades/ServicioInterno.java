/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
 * @author Jhon
 */
@Entity
@Table(name = "servicio_interno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioInterno.findAll", query = "SELECT s FROM ServicioInterno s"),
    @NamedQuery(name = "ServicioInterno.findByPiServinteConsecutivo", query = "SELECT s FROM ServicioInterno s WHERE s.piServinteConsecutivo = :piServinteConsecutivo"),
    @NamedQuery(name = "ServicioInterno.findByDServinteFecha", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteFecha = :dServinteFecha"),
    @NamedQuery(name = "ServicioInterno.findByIServinteProyecto", query = "SELECT s FROM ServicioInterno s WHERE s.iServinteProyecto = :iServinteProyecto"),
    @NamedQuery(name = "ServicioInterno.findBySServinteEstado", query = "SELECT s FROM ServicioInterno s WHERE s.sServinteEstado = :sServinteEstado"),
    @NamedQuery(name = "ServicioInterno.findByDServinteVistbuen", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteVistbuen = :dServinteVistbuen"),
    @NamedQuery(name = "ServicioInterno.findByDServinteAceptacion", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteAceptacion = :dServinteAceptacion"),
    @NamedQuery(name = "ServicioInterno.findBySServinteRecibido", query = "SELECT s FROM ServicioInterno s WHERE s.sServinteRecibido = :sServinteRecibido"),
    @NamedQuery(name = "ServicioInterno.findByDServinteRecepcion", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteRecepcion = :dServinteRecepcion"),
    @NamedQuery(name = "ServicioInterno.findByDServinteCreacion", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteCreacion = :dServinteCreacion"),
    @NamedQuery(name = "ServicioInterno.findByDServinteUltimodi", query = "SELECT s FROM ServicioInterno s WHERE s.dServinteUltimodi = :dServinteUltimodi")})
public class ServicioInterno implements Serializable {
    @Basic(optional = false)
    @Column(name = "s_servinte_estado")
    private Character sServinteEstado;
    @Basic(optional = false)
    @Column(name = "s_servinte_recibido")
    private Character sServinteRecibido;
    @JoinColumn(name = "fs_servinte_cooracep", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(optional = false)
    private Cargo fsServinteCooracep;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_servinte_consecutivo")
    private Integer piServinteConsecutivo;
    @Basic(optional = false)
    @Column(name = "d_servinte_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteFecha;
    @Basic(optional = false)
    @Column(name = "i_servinte_proyecto")
    private int iServinteProyecto;
    @Lob
    @Column(name = "s_servinte_observaciones")
    private String sServinteObservaciones;
    @Column(name = "d_servinte_vistbuen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteVistbuen;
    @Column(name = "d_servinte_aceptacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteAceptacion;
    @Column(name = "d_servinte_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteRecepcion;
    @Basic(optional = false)
    @Column(name = "d_servinte_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteCreacion;
    @Column(name = "d_servinte_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dServinteUltimodi;
    @JoinColumn(name = "fs_servinte_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsServinteUsuultmod;
    @JoinColumn(name = "fs_servinte_usuavobo", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsServinteUsuavobo;
    @JoinColumn(name = "fs_servinte_usuasoli", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsServinteUsuasoli;
    @JoinColumn(name = "fs_servinte_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsServinteUsuacrea;
    @JoinColumn(name = "fs_servinte_usuaacep", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsServinteUsuaacep;
    @JoinColumn(name = "fs_servinte_ingeproy", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsServinteIngeproy;
    @JoinColumn(name = "fs_servinte_departamento", referencedColumnName = "ps_departam_codigo")
    @ManyToOne(optional = false)
    private Departamento fsServinteDepartamento;
    @JoinColumns({
        @JoinColumn(name = "fs_servinte_tipodocumento", referencedColumnName = "pfs_document_tipodocu"),
        @JoinColumn(name = "fs_servinte_letraprocedimiento", referencedColumnName = "pfs_document_letrproc"),
        @JoinColumn(name = "fs_servinte_consecutivo", referencedColumnName = "ps_document_consecutivo"),
        @JoinColumn(name = "fs_servinte_version", referencedColumnName = "ps_document_version")})
    @ManyToOne
    private Documento documento;
     @JoinColumns({
        @JoinColumn(name = "fs_servinte_cliente", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name="fs_servinte_cliente", referencedColumnName="s_cliente_nombre")
    })
    @ManyToOne(optional = false)
    private Cliente fsServinteCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioInterno")
    private Collection<DetalleServicioInterno> detalleServicioInternoCollection;

    public ServicioInterno() {
    }

    public ServicioInterno(Integer piServinteConsecutivo) {
        this.piServinteConsecutivo = piServinteConsecutivo;
    }

    public ServicioInterno(Integer piServinteConsecutivo, Date dServinteFecha, int iServinteProyecto, char sServinteEstado, char sServinteRecibido, Date dServinteCreacion) {
        this.piServinteConsecutivo = piServinteConsecutivo;
        this.dServinteFecha = dServinteFecha;
        this.iServinteProyecto = iServinteProyecto;
        this.sServinteEstado = sServinteEstado;
        this.sServinteRecibido = sServinteRecibido;
        this.dServinteCreacion = dServinteCreacion;
    }

    public Integer getPiServinteConsecutivo() {
        return piServinteConsecutivo;
    }

    public void setPiServinteConsecutivo(Integer piServinteConsecutivo) {
        this.piServinteConsecutivo = piServinteConsecutivo;
    }

    public Date getDServinteFecha() {
        return dServinteFecha;
    }

    public void setDServinteFecha(Date dServinteFecha) {
        this.dServinteFecha = dServinteFecha;
    }

    public int getIServinteProyecto() {
        return iServinteProyecto;
    }

    public void setIServinteProyecto(int iServinteProyecto) {
        this.iServinteProyecto = iServinteProyecto;
    }

    public void setSServinteEstado(char sServinteEstado) {
        this.sServinteEstado = sServinteEstado;
    }

    public String getSServinteObservaciones() {
        return sServinteObservaciones;
    }

    public void setSServinteObservaciones(String sServinteObservaciones) {
        this.sServinteObservaciones = sServinteObservaciones;
    }

    public Date getDServinteVistbuen() {
        return dServinteVistbuen;
    }

    public void setDServinteVistbuen(Date dServinteVistbuen) {
        this.dServinteVistbuen = dServinteVistbuen;
    }

    public Date getDServinteAceptacion() {
        return dServinteAceptacion;
    }

    public void setDServinteAceptacion(Date dServinteAceptacion) {
        this.dServinteAceptacion = dServinteAceptacion;
    }

    public void setSServinteRecibido(char sServinteRecibido) {
        this.sServinteRecibido = sServinteRecibido;
    }

    public Date getDServinteRecepcion() {
        return dServinteRecepcion;
    }

    public void setDServinteRecepcion(Date dServinteRecepcion) {
        this.dServinteRecepcion = dServinteRecepcion;
    }

    public Date getDServinteCreacion() {
        return dServinteCreacion;
    }

    public void setDServinteCreacion(Date dServinteCreacion) {
        this.dServinteCreacion = dServinteCreacion;
    }

    public Date getDServinteUltimodi() {
        return dServinteUltimodi;
    }

    public void setDServinteUltimodi(Date dServinteUltimodi) {
        this.dServinteUltimodi = dServinteUltimodi;
    }

    public SUsuario getFsServinteUsuultmod() {
        return fsServinteUsuultmod;
    }

    public void setFsServinteUsuultmod(SUsuario fsServinteUsuultmod) {
        this.fsServinteUsuultmod = fsServinteUsuultmod;
    }

    public SUsuario getFsServinteUsuavobo() {
        return fsServinteUsuavobo;
    }

    public void setFsServinteUsuavobo(SUsuario fsServinteUsuavobo) {
        this.fsServinteUsuavobo = fsServinteUsuavobo;
    }

    public SUsuario getFsServinteUsuasoli() {
        return fsServinteUsuasoli;
    }

    public void setFsServinteUsuasoli(SUsuario fsServinteUsuasoli) {
        this.fsServinteUsuasoli = fsServinteUsuasoli;
    }

    public SUsuario getFsServinteUsuacrea() {
        return fsServinteUsuacrea;
    }

    public void setFsServinteUsuacrea(SUsuario fsServinteUsuacrea) {
        this.fsServinteUsuacrea = fsServinteUsuacrea;
    }

    public SUsuario getFsServinteUsuaacep() {
        return fsServinteUsuaacep;
    }

    public void setFsServinteUsuaacep(SUsuario fsServinteUsuaacep) {
        this.fsServinteUsuaacep = fsServinteUsuaacep;
    }

    public SUsuario getFsServinteIngeproy() {
        return fsServinteIngeproy;
    }

    public void setFsServinteIngeproy(SUsuario fsServinteIngeproy) {
        this.fsServinteIngeproy = fsServinteIngeproy;
    }

    public Departamento getFsServinteDepartamento() {
        return fsServinteDepartamento;
    }

    public void setFsServinteDepartamento(Departamento fsServinteDepartamento) {
        this.fsServinteDepartamento = fsServinteDepartamento;
    }

    public Cliente getFsServinteCliente() {
        return fsServinteCliente;
    }

    public void setFsServinteCliente(Cliente fsServinteCliente) {
        this.fsServinteCliente = fsServinteCliente;
    }

    @XmlTransient
    public Collection<DetalleServicioInterno> getDetalleServicioInternoCollection() {
        return detalleServicioInternoCollection;
    }

    public void setDetalleServicioInternoCollection(Collection<DetalleServicioInterno> detalleServicioInternoCollection) {
        this.detalleServicioInternoCollection = detalleServicioInternoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piServinteConsecutivo != null ? piServinteConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioInterno)) {
            return false;
        }
        ServicioInterno other = (ServicioInterno) object;
        if ((this.piServinteConsecutivo == null && other.piServinteConsecutivo != null) || (this.piServinteConsecutivo != null && !this.piServinteConsecutivo.equals(other.piServinteConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ServicioInterno[ piServinteConsecutivo=" + piServinteConsecutivo + " ]";
    }

    public Cargo getFsServinteCooracep() {
        return fsServinteCooracep;
    }

    public void setFsServinteCooracep(Cargo fsServinteCooracep) {
        this.fsServinteCooracep = fsServinteCooracep;
    }

    public Character getSServinteEstado() {
        return sServinteEstado;
    }

    public void setSServinteEstado(Character sServinteEstado) {
        this.sServinteEstado = sServinteEstado;
    }

    public Character getSServinteRecibido() {
        return sServinteRecibido;
    }

    public void setSServinteRecibido(Character sServinteRecibido) {
        this.sServinteRecibido = sServinteRecibido;
    }
    
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    public Documento getDocumento() {
        return documento;
    }
    
}
    