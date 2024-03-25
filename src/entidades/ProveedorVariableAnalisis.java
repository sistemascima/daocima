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
 * @author SISTEMAS
 */
@Entity
@Table(name = "proveedor_variable_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorVariableAnalisis.findAll", query = "SELECT p FROM ProveedorVariableAnalisis p"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByPfsProvaranaProveedor", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.proveedorVariableAnalisisPK.pfsProvaranaProveedor = :pfsProvaranaProveedor"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByPfiProvaranaVariable", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.proveedorVariableAnalisisPK.pfiProvaranaVariable = :pfiProvaranaVariable"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findBySProvaranaMetodo", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.sProvaranaMetodo = :sProvaranaMetodo"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findBySProvaranaLimicuan", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.sProvaranaLimicuan = :sProvaranaLimicuan"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByDbProvaranaCosto", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.dbProvaranaCosto = :dbProvaranaCosto"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByDProvaranaCreacion", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.dProvaranaCreacion = :dProvaranaCreacion"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByDProvaranaUltimodi", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.dProvaranaUltimodi = :dProvaranaUltimodi"),
    @NamedQuery(name = "ProveedorVariableAnalisis.findByUnidad", query = "SELECT p FROM ProveedorVariableAnalisis p WHERE p.unidad = :unidad")})
public class ProveedorVariableAnalisis implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProveedorVariableAnalisisPK proveedorVariableAnalisisPK;
    @Column(name = "s_provarana_metodo", length = 256)
    private String sProvaranaMetodo;
    @Column(name = "s_provarana_limicuan", length = 620)
    private String sProvaranaLimicuan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "db_provarana_costo", nullable = false, precision = 18, scale = 2)
    private BigDecimal dbProvaranaCosto;
    @Basic(optional = false)
    @Column(name = "d_provarana_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProvaranaCreacion;
    @Column(name = "d_provarana_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProvaranaUltimodi;
    @Column(name = "unidad", length = 45)
    private String unidad;
    @JoinColumn(name = "pfs_provarana_proveedor", referencedColumnName = "ps_proveedor_nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @JoinColumn(name = "fi_provarana_tipoanal", referencedColumnName = "pi_tipoanal_id")
    @ManyToOne
    private TipoAnalisis fiProvaranaTipoanal;
    @JoinColumn(name = "fs_provarana_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsProvaranaUsuacrea;
    @JoinColumn(name = "fs_provarana_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsProvaranaUsuultmod;
    @JoinColumn(name = "pfi_provarana_variable", referencedColumnName = "pi_varianal_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VariableAnalisis variableAnalisis;

    public ProveedorVariableAnalisis() {
    }

    public ProveedorVariableAnalisis(ProveedorVariableAnalisisPK proveedorVariableAnalisisPK) {
        this.proveedorVariableAnalisisPK = proveedorVariableAnalisisPK;
    }

    public ProveedorVariableAnalisis(ProveedorVariableAnalisisPK proveedorVariableAnalisisPK, BigDecimal dbProvaranaCosto, Date dProvaranaCreacion) {
        this.proveedorVariableAnalisisPK = proveedorVariableAnalisisPK;
        this.dbProvaranaCosto = dbProvaranaCosto;
        this.dProvaranaCreacion = dProvaranaCreacion;
    }

    public ProveedorVariableAnalisis(String pfsProvaranaProveedor, int pfiProvaranaVariable) {
        this.proveedorVariableAnalisisPK = new ProveedorVariableAnalisisPK(pfsProvaranaProveedor, pfiProvaranaVariable);
    }

    public ProveedorVariableAnalisisPK getProveedorVariableAnalisisPK() {
        return proveedorVariableAnalisisPK;
    }

    public void setProveedorVariableAnalisisPK(ProveedorVariableAnalisisPK proveedorVariableAnalisisPK) {
        this.proveedorVariableAnalisisPK = proveedorVariableAnalisisPK;
    }

    public String getSProvaranaMetodo() {
        return sProvaranaMetodo;
    }

    public void setSProvaranaMetodo(String sProvaranaMetodo) {
        this.sProvaranaMetodo = sProvaranaMetodo;
    }

    public String getSProvaranaLimicuan() {
        return sProvaranaLimicuan;
    }

    public void setSProvaranaLimicuan(String sProvaranaLimicuan) {
        this.sProvaranaLimicuan = sProvaranaLimicuan;
    }

    public BigDecimal getDbProvaranaCosto() {
        return dbProvaranaCosto;
    }

    public void setDbProvaranaCosto(BigDecimal dbProvaranaCosto) {
        this.dbProvaranaCosto = dbProvaranaCosto;
    }

    public Date getDProvaranaCreacion() {
        return dProvaranaCreacion;
    }

    public void setDProvaranaCreacion(Date dProvaranaCreacion) {
        this.dProvaranaCreacion = dProvaranaCreacion;
    }

    public Date getDProvaranaUltimodi() {
        return dProvaranaUltimodi;
    }

    public void setDProvaranaUltimodi(Date dProvaranaUltimodi) {
        this.dProvaranaUltimodi = dProvaranaUltimodi;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public TipoAnalisis getFiProvaranaTipoanal() {
        return fiProvaranaTipoanal;
    }

    public void setFiProvaranaTipoanal(TipoAnalisis fiProvaranaTipoanal) {
        this.fiProvaranaTipoanal = fiProvaranaTipoanal;
    }

    public SUsuario getFsProvaranaUsuacrea() {
        return fsProvaranaUsuacrea;
    }

    public void setFsProvaranaUsuacrea(SUsuario fsProvaranaUsuacrea) {
        this.fsProvaranaUsuacrea = fsProvaranaUsuacrea;
    }

    public SUsuario getFsProvaranaUsuultmod() {
        return fsProvaranaUsuultmod;
    }

    public void setFsProvaranaUsuultmod(SUsuario fsProvaranaUsuultmod) {
        this.fsProvaranaUsuultmod = fsProvaranaUsuultmod;
    }

    public VariableAnalisis getVariableAnalisis() {
        return variableAnalisis;
    }

    public void setVariableAnalisis(VariableAnalisis variableAnalisis) {
        this.variableAnalisis = variableAnalisis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorVariableAnalisisPK != null ? proveedorVariableAnalisisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorVariableAnalisis)) {
            return false;
        }
        ProveedorVariableAnalisis other = (ProveedorVariableAnalisis) object;
        if ((this.proveedorVariableAnalisisPK == null && other.proveedorVariableAnalisisPK != null) || (this.proveedorVariableAnalisisPK != null && !this.proveedorVariableAnalisisPK.equals(other.proveedorVariableAnalisisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   proveedor.getSProveedorNombre();
    }
    
}
