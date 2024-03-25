/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByPfsDocumentTipodocu", query = "SELECT d FROM Documento d WHERE d.documentoPK.pfsDocumentTipodocu = :pfsDocumentTipodocu"),
    @NamedQuery(name = "Documento.findByPfsDocumentLetrproc", query = "SELECT d FROM Documento d WHERE d.documentoPK.pfsDocumentLetrproc = :pfsDocumentLetrproc"),
    @NamedQuery(name = "Documento.findByPsDocumentConsecutivo", query = "SELECT d FROM Documento d WHERE d.documentoPK.psDocumentConsecutivo = :psDocumentConsecutivo"),
    @NamedQuery(name = "Documento.findByPsDocumentVersion", query = "SELECT d FROM Documento d WHERE d.documentoPK.psDocumentVersion = :psDocumentVersion"),
    @NamedQuery(name = "Documento.findBySDocumentTitulo", query = "SELECT d FROM Documento d WHERE d.sDocumentTitulo = :sDocumentTitulo"),
    @NamedQuery(name = "Documento.findBySDocumentTipocambio", query = "SELECT d FROM Documento d WHERE d.sDocumentTipocambio = :sDocumentTipocambio"),
    @NamedQuery(name = "Documento.findBySDocumentAlmafisi", query = "SELECT d FROM Documento d WHERE d.sDocumentAlmafisi = :sDocumentAlmafisi"),
    @NamedQuery(name = "Documento.findBySDocumentAlmfisreg", query = "SELECT d FROM Documento d WHERE d.sDocumentAlmfisreg = :sDocumentAlmfisreg"),
    @NamedQuery(name = "Documento.findBySDocumentAlmdigreg", query = "SELECT d FROM Documento d WHERE d.sDocumentAlmdigreg = :sDocumentAlmdigreg"),
    @NamedQuery(name = "Documento.findBySDocumentNombarch", query = "SELECT d FROM Documento d WHERE d.sDocumentNombarch = :sDocumentNombarch"),
    @NamedQuery(name = "Documento.findBySDocumentNomarcvis", query = "SELECT d FROM Documento d WHERE d.sDocumentNomarcvis = :sDocumentNomarcvis"),
    @NamedQuery(name = "Documento.findBySDocumentFrecapar", query = "SELECT d FROM Documento d WHERE d.sDocumentFrecapar = :sDocumentFrecapar"),
    @NamedQuery(name = "Documento.findByIDocumentReteanos", query = "SELECT d FROM Documento d WHERE d.iDocumentReteanos = :iDocumentReteanos"),
    @NamedQuery(name = "Documento.findBySDocumentReteotro", query = "SELECT d FROM Documento d WHERE d.sDocumentReteotro = :sDocumentReteotro"),
    @NamedQuery(name = "Documento.findBySDocumentDisfinfis", query = "SELECT d FROM Documento d WHERE d.sDocumentDisfinfis = :sDocumentDisfinfis"),
    @NamedQuery(name = "Documento.findBySDocumentDisfindig", query = "SELECT d FROM Documento d WHERE d.sDocumentDisfindig = :sDocumentDisfindig"),
    @NamedQuery(name = "Documento.findBySDocumentEstado", query = "SELECT d FROM Documento d WHERE d.sDocumentEstado = :sDocumentEstado"),
    @NamedQuery(name = "Documento.findByDDocumentElaborac", query = "SELECT d FROM Documento d WHERE d.dDocumentElaborac = :dDocumentElaborac"),
    @NamedQuery(name = "Documento.findByDDocumentRevision", query = "SELECT d FROM Documento d WHERE d.dDocumentRevision = :dDocumentRevision"),
    @NamedQuery(name = "Documento.findByDDocumentAprobaci", query = "SELECT d FROM Documento d WHERE d.dDocumentAprobaci = :dDocumentAprobaci"),
    @NamedQuery(name = "Documento.findByFsDocumentUsuacrea", query = "SELECT d FROM Documento d WHERE d.fsDocumentUsuacrea = :fsDocumentUsuacrea"),
    @NamedQuery(name = "Documento.findByDDocumentCreacion", query = "SELECT d FROM Documento d WHERE d.dDocumentCreacion = :dDocumentCreacion"),
    @NamedQuery(name = "Documento.findByFsDocumentUsuultmod", query = "SELECT d FROM Documento d WHERE d.fsDocumentUsuultmod = :fsDocumentUsuultmod"),
    @NamedQuery(name = "Documento.findByDDocumentUltimodi", query = "SELECT d FROM Documento d WHERE d.dDocumentUltimodi = :dDocumentUltimodi")})
public class Documento implements Serializable {

    @OneToMany(mappedBy = "documento")
    private List<DocumentoAplicativo> documentoAplicativoList;

    @OneToMany(mappedBy = "documento")
    private List<OrdenCompra> ordenCompraList;

    @OneToMany(mappedBy = "documento")
    private List<SolicitudCompra> solicitudCompraList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentoPK documentoPK;
    @Basic(optional = false)
    @Column(name = "s_document_titulo")
    private String sDocumentTitulo;
    @Column(name = "s_document_tipocambio")
    private Character sDocumentTipocambio;
    @Column(name = "s_document_almafisi")
    private String sDocumentAlmafisi;
    @Column(name = "s_document_almfisreg")
    private String sDocumentAlmfisreg;
    @Column(name = "s_document_almdigreg")
    private String sDocumentAlmdigreg;
    @Basic(optional = false)
    @Column(name = "s_document_nombarch")
    private String sDocumentNombarch;
    @Column(name = "s_document_nomarcvis")
    private String sDocumentNomarcvis;
    @Column(name = "s_document_frecapar")
    private String sDocumentFrecapar;
    @Column(name = "i_document_reteanos")
    private Integer iDocumentReteanos;
    @Column(name = "s_document_reteotro")
    private String sDocumentReteotro;
    @Column(name = "s_document_disfinfis")
    private String sDocumentDisfinfis;
    @Column(name = "s_document_disfindig")
    private String sDocumentDisfindig;
    @Column(name = "s_document_estado")
    private String sDocumentEstado;
    @Basic(optional = false)
    @Column(name = "d_document_elaborac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDocumentElaborac;
    @Column(name = "d_document_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDocumentRevision;
    @Column(name = "d_document_aprobaci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDocumentAprobaci;
    @Basic(optional = false)
    @Column(name = "fs_document_usuacrea")
    private String fsDocumentUsuacrea;
    @Basic(optional = false)
    @Column(name = "d_document_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDocumentCreacion;
    @Column(name = "fs_document_usuultmod")
    private String fsDocumentUsuultmod;
    @Column(name = "d_document_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDocumentUltimodi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private Collection<AccesoDocumento> accesoDocumentoCollection;
    @JoinColumn(name = "fs_document_usuarevi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private SUsuario fsDocumentUsuarevi;
    @JoinColumn(name = "fs_document_usuaelab", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false, fetch= FetchType.LAZY)
    private SUsuario fsDocumentUsuaelab;
    @JoinColumn(name = "fs_document_usuaapru", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private SUsuario fsDocumentUsuaapru;
    @JoinColumn(name = "pfs_document_tipodocu", referencedColumnName = "ps_tipodocu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch= FetchType.LAZY)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "pfs_document_letrproc", referencedColumnName = "s_proceso_letrdocu", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch= FetchType.LAZY)
    private Proceso proceso;
    @JoinColumn(name = "fs_document_cargrevi", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private Cargo fsDocumentCargrevi;
    @JoinColumn(name = "fs_document_cargelab", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(optional = false, fetch= FetchType.LAZY)
    private Cargo fsDocumentCargelab;
    @JoinColumn(name = "fs_document_cargapru", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne(fetch= FetchType.LAZY)
    private Cargo fsDocumentCargapru;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private Collection<ComentarioDocumento> comentarioDocumentoCollection;

    public Documento() {
    }

    public Documento(DocumentoPK documentoPK) {
        this.documentoPK = documentoPK;
    }

    public Documento(DocumentoPK documentoPK, String sDocumentTitulo, String sDocumentNombarch, Date dDocumentElaborac, String fsDocumentUsuacrea, Date dDocumentCreacion) {
        this.documentoPK = documentoPK;
        this.sDocumentTitulo = sDocumentTitulo;
        this.sDocumentNombarch = sDocumentNombarch;
        this.dDocumentElaborac = dDocumentElaborac;
        this.fsDocumentUsuacrea = fsDocumentUsuacrea;
        this.dDocumentCreacion = dDocumentCreacion;
    }

    public Documento(char pfsDocumentTipodocu, char pfsDocumentLetrproc, String psDocumentConsecutivo, String psDocumentVersion) {
        this.documentoPK = new DocumentoPK(pfsDocumentTipodocu, pfsDocumentLetrproc, psDocumentConsecutivo, psDocumentVersion);
    }

    public DocumentoPK getDocumentoPK() {
        return documentoPK;
    }

    public void setDocumentoPK(DocumentoPK documentoPK) {
        this.documentoPK = documentoPK;
    }

    public String getSDocumentTitulo() {
        return sDocumentTitulo;
    }

    public void setSDocumentTitulo(String sDocumentTitulo) {
        this.sDocumentTitulo = sDocumentTitulo;
    }

    public Character getSDocumentTipocambio() {
        return sDocumentTipocambio;
    }

    public void setSDocumentTipocambio(Character sDocumentTipocambio) {
        this.sDocumentTipocambio = sDocumentTipocambio;
    }

    public String getSDocumentAlmafisi() {
        return sDocumentAlmafisi;
    }

    public void setSDocumentAlmafisi(String sDocumentAlmafisi) {
        this.sDocumentAlmafisi = sDocumentAlmafisi;
    }

    public String getSDocumentAlmfisreg() {
        return sDocumentAlmfisreg;
    }

    public void setSDocumentAlmfisreg(String sDocumentAlmfisreg) {
        this.sDocumentAlmfisreg = sDocumentAlmfisreg;
    }

    public String getSDocumentAlmdigreg() {
        return sDocumentAlmdigreg;
    }

    public void setSDocumentAlmdigreg(String sDocumentAlmdigreg) {
        this.sDocumentAlmdigreg = sDocumentAlmdigreg;
    }

    public String getSDocumentNombarch() {
        return sDocumentNombarch;
    }

    public void setSDocumentNombarch(String sDocumentNombarch) {
        this.sDocumentNombarch = sDocumentNombarch;
    }

    public String getSDocumentNomarcvis() {
        return sDocumentNomarcvis;
    }

    public void setSDocumentNomarcvis(String sDocumentNomarcvis) {
        this.sDocumentNomarcvis = sDocumentNomarcvis;
    }

    public String getSDocumentFrecapar() {
        return sDocumentFrecapar;
    }

    public void setSDocumentFrecapar(String sDocumentFrecapar) {
        this.sDocumentFrecapar = sDocumentFrecapar;
    }

    public Integer getIDocumentReteanos() {
        return iDocumentReteanos;
    }

    public void setIDocumentReteanos(Integer iDocumentReteanos) {
        this.iDocumentReteanos = iDocumentReteanos;
    }

    public String getSDocumentReteotro() {
        return sDocumentReteotro;
    }

    public void setSDocumentReteotro(String sDocumentReteotro) {
        this.sDocumentReteotro = sDocumentReteotro;
    }

    public String getSDocumentDisfinfis() {
        return sDocumentDisfinfis;
    }

    public void setSDocumentDisfinfis(String sDocumentDisfinfis) {
        this.sDocumentDisfinfis = sDocumentDisfinfis;
    }

    public String getSDocumentDisfindig() {
        return sDocumentDisfindig;
    }

    public void setSDocumentDisfindig(String sDocumentDisfindig) {
        this.sDocumentDisfindig = sDocumentDisfindig;
    }

    public String getSDocumentEstado() {
        return sDocumentEstado;
    }

    public void setSDocumentEstado(String sDocumentEstado) {
        this.sDocumentEstado = sDocumentEstado;
    }

    public Date getDDocumentElaborac() {
        return dDocumentElaborac;
    }

    public void setDDocumentElaborac(Date dDocumentElaborac) {
        this.dDocumentElaborac = dDocumentElaborac;
    }

    public Date getDDocumentRevision() {
        return dDocumentRevision;
    }

    public void setDDocumentRevision(Date dDocumentRevision) {
        this.dDocumentRevision = dDocumentRevision;
    }

    public Date getDDocumentAprobaci() {
        return dDocumentAprobaci;
    }

    public void setDDocumentAprobaci(Date dDocumentAprobaci) {
        this.dDocumentAprobaci = dDocumentAprobaci;
    }

    public String getFsDocumentUsuacrea() {
        return fsDocumentUsuacrea;
    }

    public void setFsDocumentUsuacrea(String fsDocumentUsuacrea) {
        this.fsDocumentUsuacrea = fsDocumentUsuacrea;
    }

    public Date getDDocumentCreacion() {
        return dDocumentCreacion;
    }

    public void setDDocumentCreacion(Date dDocumentCreacion) {
        this.dDocumentCreacion = dDocumentCreacion;
    }

    public String getFsDocumentUsuultmod() {
        return fsDocumentUsuultmod;
    }

    public void setFsDocumentUsuultmod(String fsDocumentUsuultmod) {
        this.fsDocumentUsuultmod = fsDocumentUsuultmod;
    }

    public Date getDDocumentUltimodi() {
        return dDocumentUltimodi;
    }

    public void setDDocumentUltimodi(Date dDocumentUltimodi) {
        this.dDocumentUltimodi = dDocumentUltimodi;
    }

    @XmlTransient
    public Collection<AccesoDocumento> getAccesoDocumentoCollection() {
        return accesoDocumentoCollection;
    }

    public void setAccesoDocumentoCollection(Collection<AccesoDocumento> accesoDocumentoCollection) {
        this.accesoDocumentoCollection = accesoDocumentoCollection;
    }

    public SUsuario getFsDocumentUsuarevi() {
        return fsDocumentUsuarevi;
    }

    public void setFsDocumentUsuarevi(SUsuario fsDocumentUsuarevi) {
        this.fsDocumentUsuarevi = fsDocumentUsuarevi;
    }

    public SUsuario getFsDocumentUsuaelab() {
        return fsDocumentUsuaelab;
    }

    public void setFsDocumentUsuaelab(SUsuario fsDocumentUsuaelab) {
        this.fsDocumentUsuaelab = fsDocumentUsuaelab;
    }

    public SUsuario getFsDocumentUsuaapru() {
        return fsDocumentUsuaapru;
    }

    public void setFsDocumentUsuaapru(SUsuario fsDocumentUsuaapru) {
        this.fsDocumentUsuaapru = fsDocumentUsuaapru;
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

    public Cargo getFsDocumentCargrevi() {
        return fsDocumentCargrevi;
    }

    public void setFsDocumentCargrevi(Cargo fsDocumentCargrevi) {
        this.fsDocumentCargrevi = fsDocumentCargrevi;
    }

    public Cargo getFsDocumentCargelab() {
        return fsDocumentCargelab;
    }

    public void setFsDocumentCargelab(Cargo fsDocumentCargelab) {
        this.fsDocumentCargelab = fsDocumentCargelab;
    }

    public Cargo getFsDocumentCargapru() {
        return fsDocumentCargapru;
    }

    public void setFsDocumentCargapru(Cargo fsDocumentCargapru) {
        this.fsDocumentCargapru = fsDocumentCargapru;
    }

    @XmlTransient
    public Collection<ComentarioDocumento> getComentarioDocumentoCollection() {
        return comentarioDocumentoCollection;
    }

    public void setComentarioDocumentoCollection(Collection<ComentarioDocumento> comentarioDocumentoCollection) {
        this.comentarioDocumentoCollection = comentarioDocumentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoPK != null ? documentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.documentoPK == null && other.documentoPK != null) || (this.documentoPK != null && !this.documentoPK.equals(other.documentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documento[ documentoPK=" + documentoPK + " ]";
    }

    @XmlTransient
    public List<SolicitudCompra> getSolicitudCompraList() {
        return solicitudCompraList;
    }

    public void setSolicitudCompraList(List<SolicitudCompra> solicitudCompraList) {
        this.solicitudCompraList = solicitudCompraList;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList() {
        return ordenCompraList;
    }

    public void setOrdenCompraList(List<OrdenCompra> ordenCompraList) {
        this.ordenCompraList = ordenCompraList;
    }

    @XmlTransient
    public List<DocumentoAplicativo> getDocumentoAplicativoList() {
        return documentoAplicativoList;
    }

    public void setDocumentoAplicativoList(List<DocumentoAplicativo> documentoAplicativoList) {
        this.documentoAplicativoList = documentoAplicativoList;
    }
    
}
