/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
 * @author Jhon
 */
@Entity
@Table(name = "usuario_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioCargo.findAll", query = "SELECT u FROM UsuarioCargo u"),
    @NamedQuery(name = "UsuarioCargo.findByPfsUsuacargUsuario", query = "SELECT u FROM UsuarioCargo u WHERE u.usuarioCargoPK.pfsUsuacargUsuario = :pfsUsuacargUsuario"),
    @NamedQuery(name = "UsuarioCargo.findByPfsUsuacargCargo", query = "SELECT u FROM UsuarioCargo u WHERE u.usuarioCargoPK.pfsUsuacargCargo = :pfsUsuacargCargo"),
    @NamedQuery(name = "UsuarioCargo.findBySUsuacargEstado", query = "SELECT u FROM UsuarioCargo u WHERE u.sUsuacargEstado = :sUsuacargEstado"),
    @NamedQuery(name = "UsuarioCargo.findByDUsuacargCreacion", query = "SELECT u FROM UsuarioCargo u WHERE u.dUsuacargCreacion = :dUsuacargCreacion"),
    @NamedQuery(name = "UsuarioCargo.findByDUsuacargUltimodi", query = "SELECT u FROM UsuarioCargo u WHERE u.dUsuacargUltimodi = :dUsuacargUltimodi")})
public class UsuarioCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioCargoPK usuarioCargoPK;
    @Basic(optional = false)
    @Column(name = "s_usuacarg_estado")
    private char sUsuacargEstado;
    @Basic(optional = false)
    @Column(name = "d_usuacarg_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dUsuacargCreacion;
    @Column(name = "d_usuacarg_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dUsuacargUltimodi;
    @JoinColumn(name = "fs_usuacarg_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsUsuacargUsuultmod;
    @JoinColumn(name = "pfs_usuacarg_usuario", referencedColumnName = "ps_usuario_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SUsuario sUsuario;
    @JoinColumn(name = "fs_usuacarg_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsUsuacargUsuacrea;
    @JoinColumn(name = "pfs_usuacarg_cargo", referencedColumnName = "ps_cargo_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;

    public UsuarioCargo() {
    }

    public UsuarioCargo(UsuarioCargoPK usuarioCargoPK) {
        this.usuarioCargoPK = usuarioCargoPK;
    }

    public UsuarioCargo(UsuarioCargoPK usuarioCargoPK, char sUsuacargEstado, Date dUsuacargCreacion) {
        this.usuarioCargoPK = usuarioCargoPK;
        this.sUsuacargEstado = sUsuacargEstado;
        this.dUsuacargCreacion = dUsuacargCreacion;
    }

    public UsuarioCargo(String pfsUsuacargUsuario, String pfsUsuacargCargo) {
        this.usuarioCargoPK = new UsuarioCargoPK(pfsUsuacargUsuario, pfsUsuacargCargo);
    }

    public UsuarioCargoPK getUsuarioCargoPK() {
        return usuarioCargoPK;
    }

    public void setUsuarioCargoPK(UsuarioCargoPK usuarioCargoPK) {
        this.usuarioCargoPK = usuarioCargoPK;
    }

    public char getSUsuacargEstado() {
        return sUsuacargEstado;
    }

    public void setSUsuacargEstado(char sUsuacargEstado) {
        this.sUsuacargEstado = sUsuacargEstado;
    }

    public Date getDUsuacargCreacion() {
        return dUsuacargCreacion;
    }

    public void setDUsuacargCreacion(Date dUsuacargCreacion) {
        this.dUsuacargCreacion = dUsuacargCreacion;
    }

    public Date getDUsuacargUltimodi() {
        return dUsuacargUltimodi;
    }

    public void setDUsuacargUltimodi(Date dUsuacargUltimodi) {
        this.dUsuacargUltimodi = dUsuacargUltimodi;
    }

    public SUsuario getFsUsuacargUsuultmod() {
        return fsUsuacargUsuultmod;
    }

    public void setFsUsuacargUsuultmod(SUsuario fsUsuacargUsuultmod) {
        this.fsUsuacargUsuultmod = fsUsuacargUsuultmod;
    }

    public SUsuario getSUsuario() {
        return sUsuario;
    }

    public void setSUsuario(SUsuario sUsuario) {
        this.sUsuario = sUsuario;
    }

    public SUsuario getFsUsuacargUsuacrea() {
        return fsUsuacargUsuacrea;
    }

    public void setFsUsuacargUsuacrea(SUsuario fsUsuacargUsuacrea) {
        this.fsUsuacargUsuacrea = fsUsuacargUsuacrea;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioCargoPK != null ? usuarioCargoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCargo)) {
            return false;
        }
        UsuarioCargo other = (UsuarioCargo) object;
        if ((this.usuarioCargoPK == null && other.usuarioCargoPK != null) || (this.usuarioCargoPK != null && !this.usuarioCargoPK.equals(other.usuarioCargoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsuarioCargo[ usuarioCargoPK=" + usuarioCargoPK + " ]";
    }
    
}
