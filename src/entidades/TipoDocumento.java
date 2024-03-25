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
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByPsTipodocuId", query = "SELECT t FROM TipoDocumento t WHERE t.psTipodocuId = :psTipodocuId"),
    @NamedQuery(name = "TipoDocumento.findBySTipodocuNombre", query = "SELECT t FROM TipoDocumento t WHERE t.sTipodocuNombre = :sTipodocuNombre"),
    @NamedQuery(name = "TipoDocumento.findBySTipodocuCarpeta", query = "SELECT t FROM TipoDocumento t WHERE t.sTipodocuCarpeta = :sTipodocuCarpeta"),
    @NamedQuery(name = "TipoDocumento.findByDTipodocuCreacion", query = "SELECT t FROM TipoDocumento t WHERE t.dTipodocuCreacion = :dTipodocuCreacion"),
    @NamedQuery(name = "TipoDocumento.findByDTipodocuUltimodi", query = "SELECT t FROM TipoDocumento t WHERE t.dTipodocuUltimodi = :dTipodocuUltimodi")})
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_tipodocu_id")
    private Character psTipodocuId;
    @Basic(optional = false)
    @Column(name = "s_tipodocu_nombre")
    private String sTipodocuNombre;
    @Column(name = "s_tipodocu_carpeta")
    private String sTipodocuCarpeta;
    @Basic(optional = false)
    @Column(name = "d_tipodocu_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipodocuCreacion;
    @Column(name = "d_tipodocu_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipodocuUltimodi;
    @JoinColumn(name = "fs_tipodocu_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsTipodocuUsuultmod;
    @JoinColumn(name = "fs_tipodocu_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsTipodocuUsuacrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<Documento> documentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private Collection<ResponsableDocumento> responsableDocumentoCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(Character psTipodocuId) {
        this.psTipodocuId = psTipodocuId;
    }

    public TipoDocumento(Character psTipodocuId, String sTipodocuNombre, Date dTipodocuCreacion) {
        this.psTipodocuId = psTipodocuId;
        this.sTipodocuNombre = sTipodocuNombre;
        this.dTipodocuCreacion = dTipodocuCreacion;
    }

    public Character getPsTipodocuId() {
        return psTipodocuId;
    }

    public void setPsTipodocuId(Character psTipodocuId) {
        this.psTipodocuId = psTipodocuId;
    }

    public String getSTipodocuNombre() {
        return sTipodocuNombre;
    }

    public void setSTipodocuNombre(String sTipodocuNombre) {
        this.sTipodocuNombre = sTipodocuNombre;
    }

    public String getSTipodocuCarpeta() {
        return sTipodocuCarpeta;
    }

    public void setSTipodocuCarpeta(String sTipodocuCarpeta) {
        this.sTipodocuCarpeta = sTipodocuCarpeta;
    }

    public Date getDTipodocuCreacion() {
        return dTipodocuCreacion;
    }

    public void setDTipodocuCreacion(Date dTipodocuCreacion) {
        this.dTipodocuCreacion = dTipodocuCreacion;
    }

    public Date getDTipodocuUltimodi() {
        return dTipodocuUltimodi;
    }

    public void setDTipodocuUltimodi(Date dTipodocuUltimodi) {
        this.dTipodocuUltimodi = dTipodocuUltimodi;
    }

    public SUsuario getFsTipodocuUsuultmod() {
        return fsTipodocuUsuultmod;
    }

    public void setFsTipodocuUsuultmod(SUsuario fsTipodocuUsuultmod) {
        this.fsTipodocuUsuultmod = fsTipodocuUsuultmod;
    }

    public SUsuario getFsTipodocuUsuacrea() {
        return fsTipodocuUsuacrea;
    }

    public void setFsTipodocuUsuacrea(SUsuario fsTipodocuUsuacrea) {
        this.fsTipodocuUsuacrea = fsTipodocuUsuacrea;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    public Collection<ResponsableDocumento> getResponsableDocumentoCollection() {
        return responsableDocumentoCollection;
    }

    public void setResponsableDocumentoCollection(Collection<ResponsableDocumento> responsableDocumentoCollection) {
        this.responsableDocumentoCollection = responsableDocumentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psTipodocuId != null ? psTipodocuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.psTipodocuId == null && other.psTipodocuId != null) || (this.psTipodocuId != null && !this.psTipodocuId.equals(other.psTipodocuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return psTipodocuId + " - " + sTipodocuNombre;
    }
    
}
