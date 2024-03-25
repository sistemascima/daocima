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
@Table(name = "responsable_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsableDocumento.findAll", query = "SELECT r FROM ResponsableDocumento r"),
    @NamedQuery(name = "ResponsableDocumento.findByPfiRespdocuProceso", query = "SELECT r FROM ResponsableDocumento r WHERE r.responsableDocumentoPK.pfiRespdocuProceso = :pfiRespdocuProceso"),
    @NamedQuery(name = "ResponsableDocumento.findByPfsRespdocuTipodocu", query = "SELECT r FROM ResponsableDocumento r WHERE r.responsableDocumentoPK.pfsRespdocuTipodocu = :pfsRespdocuTipodocu"),
    @NamedQuery(name = "ResponsableDocumento.findByPfsRespdocuCargo", query = "SELECT r FROM ResponsableDocumento r WHERE r.responsableDocumentoPK.pfsRespdocuCargo = :pfsRespdocuCargo"),
    @NamedQuery(name = "ResponsableDocumento.findByPsRespdocuTiporesp", query = "SELECT r FROM ResponsableDocumento r WHERE r.responsableDocumentoPK.psRespdocuTiporesp = :psRespdocuTiporesp"),
    @NamedQuery(name = "ResponsableDocumento.findByDRespdocuCreacion", query = "SELECT r FROM ResponsableDocumento r WHERE r.dRespdocuCreacion = :dRespdocuCreacion"),
    @NamedQuery(name = "ResponsableDocumento.findByDRespdocuUltimodi", query = "SELECT r FROM ResponsableDocumento r WHERE r.dRespdocuUltimodi = :dRespdocuUltimodi")})
public class ResponsableDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResponsableDocumentoPK responsableDocumentoPK;
    @Basic(optional = false)
    @Column(name = "d_respdocu_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dRespdocuCreacion;
    @Column(name = "d_respdocu_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dRespdocuUltimodi;
    @JoinColumn(name = "fs_respdocu_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsRespdocuUsuultmod;
    @JoinColumn(name = "fs_respdocu_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsRespdocuUsuacrea;
    @JoinColumn(name = "pfs_respdocu_tipodocu", referencedColumnName = "ps_tipodocu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "pfi_respdocu_proceso", referencedColumnName = "pi_proceso_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proceso proceso;
    @JoinColumn(name = "pfs_respdocu_cargo", referencedColumnName = "ps_cargo_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;

    public ResponsableDocumento() {
    }

    public ResponsableDocumento(ResponsableDocumentoPK responsableDocumentoPK) {
        this.responsableDocumentoPK = responsableDocumentoPK;
    }

    public ResponsableDocumento(ResponsableDocumentoPK responsableDocumentoPK, Date dRespdocuCreacion) {
        this.responsableDocumentoPK = responsableDocumentoPK;
        this.dRespdocuCreacion = dRespdocuCreacion;
    }

    public ResponsableDocumento(int pfiRespdocuProceso, char pfsRespdocuTipodocu, String pfsRespdocuCargo, char psRespdocuTiporesp) {
        this.responsableDocumentoPK = new ResponsableDocumentoPK(pfiRespdocuProceso, pfsRespdocuTipodocu, pfsRespdocuCargo, psRespdocuTiporesp);
    }

    public ResponsableDocumentoPK getResponsableDocumentoPK() {
        return responsableDocumentoPK;
    }

    public void setResponsableDocumentoPK(ResponsableDocumentoPK responsableDocumentoPK) {
        this.responsableDocumentoPK = responsableDocumentoPK;
    }

    public Date getDRespdocuCreacion() {
        return dRespdocuCreacion;
    }

    public void setDRespdocuCreacion(Date dRespdocuCreacion) {
        this.dRespdocuCreacion = dRespdocuCreacion;
    }

    public Date getDRespdocuUltimodi() {
        return dRespdocuUltimodi;
    }

    public void setDRespdocuUltimodi(Date dRespdocuUltimodi) {
        this.dRespdocuUltimodi = dRespdocuUltimodi;
    }

    public SUsuario getFsRespdocuUsuultmod() {
        return fsRespdocuUsuultmod;
    }

    public void setFsRespdocuUsuultmod(SUsuario fsRespdocuUsuultmod) {
        this.fsRespdocuUsuultmod = fsRespdocuUsuultmod;
    }

    public SUsuario getFsRespdocuUsuacrea() {
        return fsRespdocuUsuacrea;
    }

    public void setFsRespdocuUsuacrea(SUsuario fsRespdocuUsuacrea) {
        this.fsRespdocuUsuacrea = fsRespdocuUsuacrea;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
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
        hash += (responsableDocumentoPK != null ? responsableDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsableDocumento)) {
            return false;
        }
        ResponsableDocumento other = (ResponsableDocumento) object;
        if ((this.responsableDocumentoPK == null && other.responsableDocumentoPK != null) || (this.responsableDocumentoPK != null && !this.responsableDocumentoPK.equals(other.responsableDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResponsableDocumento[ responsableDocumentoPK=" + responsableDocumentoPK + " ]";
    }
    
}
