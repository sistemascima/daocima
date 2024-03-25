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
 * @author Jhon
 */
@Entity
@Table(name = "acceso_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoDocumento.findAll", query = "SELECT a FROM AccesoDocumento a"),
    @NamedQuery(name = "AccesoDocumento.findByPfsAccedocuTipodocu", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.pfsAccedocuTipodocu = :pfsAccedocuTipodocu"),
    @NamedQuery(name = "AccesoDocumento.findByPfsAccedocuLetrproc", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.pfsAccedocuLetrproc = :pfsAccedocuLetrproc"),
    @NamedQuery(name = "AccesoDocumento.findByPfsAccedocuConsdocu", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.pfsAccedocuConsdocu = :pfsAccedocuConsdocu"),
    @NamedQuery(name = "AccesoDocumento.findByPfsAccedocuVersdocu", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.pfsAccedocuVersdocu = :pfsAccedocuVersdocu"),
    @NamedQuery(name = "AccesoDocumento.findByPfsAccedocuCargo", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.pfsAccedocuCargo = :pfsAccedocuCargo"),
    @NamedQuery(name = "AccesoDocumento.findByPsAccedocuTipoacce", query = "SELECT a FROM AccesoDocumento a WHERE a.accesoDocumentoPK.psAccedocuTipoacce = :psAccedocuTipoacce"),
    @NamedQuery(name = "AccesoDocumento.findByDAccedocuCreacion", query = "SELECT a FROM AccesoDocumento a WHERE a.dAccedocuCreacion = :dAccedocuCreacion"),
    @NamedQuery(name = "AccesoDocumento.findByDAccedocuUltimodi", query = "SELECT a FROM AccesoDocumento a WHERE a.dAccedocuUltimodi = :dAccedocuUltimodi")})
public class AccesoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccesoDocumentoPK accesoDocumentoPK;
    @Basic(optional = false)
    @Column(name = "d_accedocu_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dAccedocuCreacion;
    @Column(name = "d_accedocu_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dAccedocuUltimodi;
    @JoinColumn(name = "fs_accedocu_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsAccedocuUsuultmod;
    @JoinColumn(name = "fs_accedocu_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsAccedocuUsuacrea;
    @JoinColumns({
        @JoinColumn(name = "pfs_accedocu_tipodocu", referencedColumnName = "pfs_document_tipodocu", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_accedocu_letrproc", referencedColumnName = "pfs_document_letrproc", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_accedocu_consdocu", referencedColumnName = "ps_document_consecutivo", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_accedocu_versdocu", referencedColumnName = "ps_document_version", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Documento documento;
    @JoinColumn(name = "pfs_accedocu_cargo", referencedColumnName = "ps_cargo_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;

    public AccesoDocumento() {
    }

    public AccesoDocumento(AccesoDocumentoPK accesoDocumentoPK) {
        this.accesoDocumentoPK = accesoDocumentoPK;
    }

    public AccesoDocumento(AccesoDocumentoPK accesoDocumentoPK, Date dAccedocuCreacion) {
        this.accesoDocumentoPK = accesoDocumentoPK;
        this.dAccedocuCreacion = dAccedocuCreacion;
    }

    public AccesoDocumento(char pfsAccedocuTipodocu, char pfsAccedocuLetrproc, String pfsAccedocuConsdocu, String pfsAccedocuVersdocu, String pfsAccedocuCargo, String psAccedocuTipoacce) {
        this.accesoDocumentoPK = new AccesoDocumentoPK(pfsAccedocuTipodocu, pfsAccedocuLetrproc, pfsAccedocuConsdocu, pfsAccedocuVersdocu, pfsAccedocuCargo, psAccedocuTipoacce);
    }

    public AccesoDocumentoPK getAccesoDocumentoPK() {
        return accesoDocumentoPK;
    }

    public void setAccesoDocumentoPK(AccesoDocumentoPK accesoDocumentoPK) {
        this.accesoDocumentoPK = accesoDocumentoPK;
    }

    public Date getDAccedocuCreacion() {
        return dAccedocuCreacion;
    }

    public void setDAccedocuCreacion(Date dAccedocuCreacion) {
        this.dAccedocuCreacion = dAccedocuCreacion;
    }

    public Date getDAccedocuUltimodi() {
        return dAccedocuUltimodi;
    }

    public void setDAccedocuUltimodi(Date dAccedocuUltimodi) {
        this.dAccedocuUltimodi = dAccedocuUltimodi;
    }

    public SUsuario getFsAccedocuUsuultmod() {
        return fsAccedocuUsuultmod;
    }

    public void setFsAccedocuUsuultmod(SUsuario fsAccedocuUsuultmod) {
        this.fsAccedocuUsuultmod = fsAccedocuUsuultmod;
    }

    public SUsuario getFsAccedocuUsuacrea() {
        return fsAccedocuUsuacrea;
    }

    public void setFsAccedocuUsuacrea(SUsuario fsAccedocuUsuacrea) {
        this.fsAccedocuUsuacrea = fsAccedocuUsuacrea;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        hash += (accesoDocumentoPK != null ? accesoDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoDocumento)) {
            return false;
        }
        AccesoDocumento other = (AccesoDocumento) object;
        if ((this.accesoDocumentoPK == null && other.accesoDocumentoPK != null) || (this.accesoDocumentoPK != null && !this.accesoDocumentoPK.equals(other.accesoDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AccesoDocumento[ accesoDocumentoPK=" + accesoDocumentoPK + " ]";
    }
    
}
