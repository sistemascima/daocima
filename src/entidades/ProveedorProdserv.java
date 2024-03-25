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
@Table(name = "proveedor_prodserv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorProdserv.findAll", query = "SELECT p FROM ProveedorProdserv p"),
    @NamedQuery(name = "ProveedorProdserv.findByPfsProvprseNitprov", query = "SELECT p FROM ProveedorProdserv p WHERE p.proveedorProdservPK.pfsProvprseNitprov = :pfsProvprseNitprov"),
    @NamedQuery(name = "ProveedorProdserv.findByPfsProvprseTipproser", query = "SELECT p FROM ProveedorProdserv p WHERE p.proveedorProdservPK.pfsProvprseTipproser = :pfsProvprseTipproser"),
    @NamedQuery(name = "ProveedorProdserv.findByDProvprseCreacion", query = "SELECT p FROM ProveedorProdserv p WHERE p.dProvprseCreacion = :dProvprseCreacion"),
    @NamedQuery(name = "ProveedorProdserv.findByDProvprseUltimodi", query = "SELECT p FROM ProveedorProdserv p WHERE p.dProvprseUltimodi = :dProvprseUltimodi")})
public class ProveedorProdserv implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProveedorProdservPK proveedorProdservPK;
    @Basic(optional = false)
    @Column(name = "d_provprse_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProvprseCreacion;
    @Column(name = "d_provprse_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProvprseUltimodi;
    @JoinColumn(name = "fs_provprse_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsProvprseUsuultmod;
    @JoinColumn(name = "fs_provprse_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsProvprseUsuacrea;

    public ProveedorProdserv() {
    }

    public ProveedorProdserv(ProveedorProdservPK proveedorProdservPK) {
        this.proveedorProdservPK = proveedorProdservPK;
    }

    public ProveedorProdserv(ProveedorProdservPK proveedorProdservPK, Date dProvprseCreacion) {
        this.proveedorProdservPK = proveedorProdservPK;
        this.dProvprseCreacion = dProvprseCreacion;
    }

    public ProveedorProdserv(String pfsProvprseNitprov, char pfsProvprseTipproser) {
        this.proveedorProdservPK = new ProveedorProdservPK(pfsProvprseNitprov, pfsProvprseTipproser);
    }

    public ProveedorProdservPK getProveedorProdservPK() {
        return proveedorProdservPK;
    }

    public void setProveedorProdservPK(ProveedorProdservPK proveedorProdservPK) {
        this.proveedorProdservPK = proveedorProdservPK;
    }

    public Date getDProvprseCreacion() {
        return dProvprseCreacion;
    }

    public void setDProvprseCreacion(Date dProvprseCreacion) {
        this.dProvprseCreacion = dProvprseCreacion;
    }

    public Date getDProvprseUltimodi() {
        return dProvprseUltimodi;
    }

    public void setDProvprseUltimodi(Date dProvprseUltimodi) {
        this.dProvprseUltimodi = dProvprseUltimodi;
    }

    public SUsuario getFsProvprseUsuultmod() {
        return fsProvprseUsuultmod;
    }

    public void setFsProvprseUsuultmod(SUsuario fsProvprseUsuultmod) {
        this.fsProvprseUsuultmod = fsProvprseUsuultmod;
    }

    public SUsuario getFsProvprseUsuacrea() {
        return fsProvprseUsuacrea;
    }

    public void setFsProvprseUsuacrea(SUsuario fsProvprseUsuacrea) {
        this.fsProvprseUsuacrea = fsProvprseUsuacrea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorProdservPK != null ? proveedorProdservPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorProdserv)) {
            return false;
        }
        ProveedorProdserv other = (ProveedorProdserv) object;
        if ((this.proveedorProdservPK == null && other.proveedorProdservPK != null) || (this.proveedorProdservPK != null && !this.proveedorProdservPK.equals(other.proveedorProdservPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProveedorProdserv[ proveedorProdservPK=" + proveedorProdservPK + " ]";
    }
    
}
