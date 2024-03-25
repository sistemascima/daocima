/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o"),
    @NamedQuery(name = "OrdenCompra.findByPiOrdecompNumero", query = "SELECT o FROM OrdenCompra o WHERE o.piOrdecompNumero = :piOrdecompNumero"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompEstado", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompEstado = :sOrdecompEstado"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompTipocomp", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompTipocomp = :sOrdecompTipocomp"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompServicio", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompServicio = :sOrdecompServicio"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompSuministro", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompSuministro = :sOrdecompSuministro"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompEquipo", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompEquipo = :sOrdecompEquipo"),
    @NamedQuery(name = "OrdenCompra.findByDbOrdecompDescuento", query = "SELECT o FROM OrdenCompra o WHERE o.dbOrdecompDescuento = :dbOrdecompDescuento"),
    @NamedQuery(name = "OrdenCompra.findByDbOrdecompSubtotal", query = "SELECT o FROM OrdenCompra o WHERE o.dbOrdecompSubtotal = :dbOrdecompSubtotal"),
    @NamedQuery(name = "OrdenCompra.findByDbOrdecompTotal", query = "SELECT o FROM OrdenCompra o WHERE o.dbOrdecompTotal = :dbOrdecompTotal"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompCondcome", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompCondcome = :sOrdecompCondcome"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompRevisado", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompRevisado = :sOrdecompRevisado"),
    @NamedQuery(name = "OrdenCompra.findByDOrdecompRevision", query = "SELECT o FROM OrdenCompra o WHERE o.dOrdecompRevision = :dOrdecompRevision"),
    @NamedQuery(name = "OrdenCompra.findBySOrdecompAprobado", query = "SELECT o FROM OrdenCompra o WHERE o.sOrdecompAprobado = :sOrdecompAprobado"),
    @NamedQuery(name = "OrdenCompra.findByDOrdecompAprobacion", query = "SELECT o FROM OrdenCompra o WHERE o.dOrdecompAprobacion = :dOrdecompAprobacion"),
    @NamedQuery(name = "OrdenCompra.findByDOrdecompCreacion", query = "SELECT o FROM OrdenCompra o WHERE o.dOrdecompCreacion = :dOrdecompCreacion"),
    @NamedQuery(name = "OrdenCompra.findByDOrdecompUltimodi", query = "SELECT o FROM OrdenCompra o WHERE o.dOrdecompUltimodi = :dOrdecompUltimodi"),
    @NamedQuery(name = "OrdenCompra.findByDOrdecompRecepcion", query = "SELECT o FROM OrdenCompra o WHERE o.dOrdecompRecepcion = :dOrdecompRecepcion")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_ordecomp_numero", nullable = false)
    private Integer piOrdecompNumero;
    @Basic(optional = false)
    @Column(name = "s_ordecomp_estado", nullable = false)
    private Character sOrdecompEstado;
    @Basic(optional = false)
    @Column(name = "s_ordecomp_tipocomp", nullable = false)
    private Character sOrdecompTipocomp;
    @Basic(optional = false)
    @Column(name = "s_ordecomp_servicio", nullable = false)
    private Character sOrdecompServicio;
    @Basic(optional = false)
    @Column(name = "s_ordecomp_suministro", nullable = false)
    private Character sOrdecompSuministro;
    @Basic(optional = false)
    @Column(name = "s_ordecomp_equipo", nullable = false)
    private Character sOrdecompEquipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_ordecomp_descuento", precision = 18, scale = 2)
    private BigDecimal dbOrdecompDescuento;
    @Column(name = "db_ordecomp_subtotal", precision = 18, scale = 2)
    private BigDecimal dbOrdecompSubtotal;
    @Column(name = "db_ordecomp_total", precision = 18, scale = 2)
    private BigDecimal dbOrdecompTotal;
    @Column(name = "s_ordecomp_condcome", length = 600)
    private String sOrdecompCondcome;
    @Column(name = "s_ordecomp_revisado")
    private Character sOrdecompRevisado;
    @Column(name = "d_ordecomp_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dOrdecompRevision;
    @Column(name = "s_ordecomp_aprobado")
    private Character sOrdecompAprobado;
    @Column(name = "d_ordecomp_aprobacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dOrdecompAprobacion;
    @Basic(optional = false)
    @Column(name = "d_ordecomp_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dOrdecompCreacion;
    @Column(name = "d_ordecomp_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dOrdecompUltimodi;
    @Column(name = "d_ordecomp_recepcion")
    @Temporal(TemporalType.DATE)
    private Date dOrdecompRecepcion;
    @JoinColumns({
        @JoinColumn(name = "fs_ordecomp_tipodocumento", referencedColumnName = "pfs_document_tipodocu"),
        @JoinColumn(name = "fs_ordecomp_letraprocedimiento", referencedColumnName = "pfs_document_letrproc"),
        @JoinColumn(name = "fs_ordecomp_consecutivo", referencedColumnName = "ps_document_consecutivo"),
        @JoinColumn(name = "fs_ordecomp_version", referencedColumnName = "ps_document_version")})
    @ManyToOne
    private Documento documento;
    @JoinColumn(name = "fs_ordecomp_cargapru", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsOrdecompCargapru;
    @JoinColumn(name = "fs_ordecomp_cargrevi", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsOrdecompCargrevi;
    @JoinColumn(name = "fs_ordecomp_empresa", referencedColumnName = "pi_empresa_id")
    @ManyToOne
    private Empresa fsOrdecompEmpresa;

    @JoinColumn(name = "fs_ordecomp_proveedor", referencedColumnName = "ps_proveedor_nit", nullable = false)
    @ManyToOne(optional = false)
    private Proveedor fsOrdecompProveedor;
    @JoinColumn(name = "fs_ordecomp_usuaapru", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsOrdecompUsuaapru;
    @JoinColumn(name = "fs_ordecomp_usuacrea", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario fsOrdecompUsuacrea;
    @JoinColumn(name = "fs_ordecomp_usuarevi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsOrdecompUsuarevi;
    @JoinColumn(name = "fs_ordecomp_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsOrdecompUsuultmod;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer piOrdecompNumero) {
        this.piOrdecompNumero = piOrdecompNumero;
    }

    public OrdenCompra(Integer piOrdecompNumero, Character sOrdecompEstado, Character sOrdecompTipocomp, Character sOrdecompServicio, Character sOrdecompSuministro, Character sOrdecompEquipo, Date dOrdecompCreacion) {
        this.piOrdecompNumero = piOrdecompNumero;
        this.sOrdecompEstado = sOrdecompEstado;
        this.sOrdecompTipocomp = sOrdecompTipocomp;
        this.sOrdecompServicio = sOrdecompServicio;
        this.sOrdecompSuministro = sOrdecompSuministro;
        this.sOrdecompEquipo = sOrdecompEquipo;
        this.dOrdecompCreacion = dOrdecompCreacion;
    }

    public Integer getPiOrdecompNumero() {
        return piOrdecompNumero;
    }

    public void setPiOrdecompNumero(Integer piOrdecompNumero) {
        this.piOrdecompNumero = piOrdecompNumero;
    }

    public Character getSOrdecompEstado() {
        return sOrdecompEstado;
    }

    public void setSOrdecompEstado(Character sOrdecompEstado) {
        this.sOrdecompEstado = sOrdecompEstado;
    }

    public Character getSOrdecompTipocomp() {
        return sOrdecompTipocomp;
    }

    public void setSOrdecompTipocomp(Character sOrdecompTipocomp) {
        this.sOrdecompTipocomp = sOrdecompTipocomp;
    }

    public Character getSOrdecompServicio() {
        return sOrdecompServicio;
    }

    public void setSOrdecompServicio(Character sOrdecompServicio) {
        this.sOrdecompServicio = sOrdecompServicio;
    }

    public Character getSOrdecompSuministro() {
        return sOrdecompSuministro;
    }

    public void setSOrdecompSuministro(Character sOrdecompSuministro) {
        this.sOrdecompSuministro = sOrdecompSuministro;
    }

    public Character getSOrdecompEquipo() {
        return sOrdecompEquipo;
    }

    public void setSOrdecompEquipo(Character sOrdecompEquipo) {
        this.sOrdecompEquipo = sOrdecompEquipo;
    }

    public BigDecimal getDbOrdecompDescuento() {
        return dbOrdecompDescuento;
    }

    public void setDbOrdecompDescuento(BigDecimal dbOrdecompDescuento) {
        this.dbOrdecompDescuento = dbOrdecompDescuento;
    }

    public BigDecimal getDbOrdecompSubtotal() {
        return dbOrdecompSubtotal;
    }

    public void setDbOrdecompSubtotal(BigDecimal dbOrdecompSubtotal) {
        this.dbOrdecompSubtotal = dbOrdecompSubtotal;
    }

    public BigDecimal getDbOrdecompTotal() {
        return dbOrdecompTotal;
    }

    public void setDbOrdecompTotal(BigDecimal dbOrdecompTotal) {
        this.dbOrdecompTotal = dbOrdecompTotal;
    }

    public String getSOrdecompCondcome() {
        return sOrdecompCondcome;
    }

    public void setSOrdecompCondcome(String sOrdecompCondcome) {
        this.sOrdecompCondcome = sOrdecompCondcome;
    }

    public Character getSOrdecompRevisado() {
        return sOrdecompRevisado;
    }

    public void setSOrdecompRevisado(Character sOrdecompRevisado) {
        this.sOrdecompRevisado = sOrdecompRevisado;
    }

    public Date getDOrdecompRevision() {
        return dOrdecompRevision;
    }

    public void setDOrdecompRevision(Date dOrdecompRevision) {
        this.dOrdecompRevision = dOrdecompRevision;
    }

    public Character getSOrdecompAprobado() {
        return sOrdecompAprobado;
    }

    public void setSOrdecompAprobado(Character sOrdecompAprobado) {
        this.sOrdecompAprobado = sOrdecompAprobado;
    }

    public Date getDOrdecompAprobacion() {
        return dOrdecompAprobacion;
    }

    public void setDOrdecompAprobacion(Date dOrdecompAprobacion) {
        this.dOrdecompAprobacion = dOrdecompAprobacion;
    }

    public Date getDOrdecompCreacion() {
        return dOrdecompCreacion;
    }

    public void setDOrdecompCreacion(Date dOrdecompCreacion) {
        this.dOrdecompCreacion = dOrdecompCreacion;
    }

    public Date getDOrdecompUltimodi() {
        return dOrdecompUltimodi;
    }

    public void setDOrdecompUltimodi(Date dOrdecompUltimodi) {
        this.dOrdecompUltimodi = dOrdecompUltimodi;
    }

    public Date getdOrdecompRecepcion() {
        return dOrdecompRecepcion;
    }

    public void setdOrdecompRecepcion(Date dOrdecompRecepcion) {
        this.dOrdecompRecepcion = dOrdecompRecepcion;
    }


    public Cargo getFsOrdecompCargapru() {
        return fsOrdecompCargapru;
    }

    public void setFsOrdecompCargapru(Cargo fsOrdecompCargapru) {
        this.fsOrdecompCargapru = fsOrdecompCargapru;
    }

    public Cargo getFsOrdecompCargrevi() {
        return fsOrdecompCargrevi;
    }

    public void setFsOrdecompCargrevi(Cargo fsOrdecompCargrevi) {
        this.fsOrdecompCargrevi = fsOrdecompCargrevi;
    }

    public Empresa getFsOrdecompEmpresa() {
        return fsOrdecompEmpresa;
    }

    public void setFsOrdecompEmpresa(Empresa fsOrdecompEmpresa) {
        this.fsOrdecompEmpresa = fsOrdecompEmpresa;
    }

  
    public Proveedor getFsOrdecompProveedor() {
        return fsOrdecompProveedor;
    }

    public void setFsOrdecompProveedor(Proveedor fsOrdecompProveedor) {
        this.fsOrdecompProveedor = fsOrdecompProveedor;
    }

    public SUsuario getFsOrdecompUsuaapru() {
        return fsOrdecompUsuaapru;
    }

    public void setFsOrdecompUsuaapru(SUsuario fsOrdecompUsuaapru) {
        this.fsOrdecompUsuaapru = fsOrdecompUsuaapru;
    }

    public SUsuario getFsOrdecompUsuacrea() {
        return fsOrdecompUsuacrea;
    }

    public void setFsOrdecompUsuacrea(SUsuario fsOrdecompUsuacrea) {
        this.fsOrdecompUsuacrea = fsOrdecompUsuacrea;
    }

    public SUsuario getFsOrdecompUsuarevi() {
        return fsOrdecompUsuarevi;
    }

    public void setFsOrdecompUsuarevi(SUsuario fsOrdecompUsuarevi) {
        this.fsOrdecompUsuarevi = fsOrdecompUsuarevi;
    }

    public SUsuario getFsOrdecompUsuultmod() {
        return fsOrdecompUsuultmod;
    }

    public void setFsOrdecompUsuultmod(SUsuario fsOrdecompUsuultmod) {
        this.fsOrdecompUsuultmod = fsOrdecompUsuultmod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piOrdecompNumero != null ? piOrdecompNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.piOrdecompNumero == null && other.piOrdecompNumero != null) || (this.piOrdecompNumero != null && !this.piOrdecompNumero.equals(other.piOrdecompNumero))) {
            return false;
        }
        return true;
    }
    
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
     public Documento getDocumento() {
       return this.documento;
    }
    
    @Override
    public String toString() {
        return "entidades.OrdenCompra[ piOrdecompNumero=" + piOrdecompNumero + " ]";
    }
    
    
}
