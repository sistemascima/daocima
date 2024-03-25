/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
@Table(name = "comentario_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComentarioDocumento.findAll", query = "SELECT c FROM ComentarioDocumento c"),
    @NamedQuery(name = "ComentarioDocumento.findByPfsComedocuTipodocu", query = "SELECT c FROM ComentarioDocumento c WHERE c.comentarioDocumentoPK.pfsComedocuTipodocu = :pfsComedocuTipodocu"),
    @NamedQuery(name = "ComentarioDocumento.findByPfsComedocuLetrproc", query = "SELECT c FROM ComentarioDocumento c WHERE c.comentarioDocumentoPK.pfsComedocuLetrproc = :pfsComedocuLetrproc"),
    @NamedQuery(name = "ComentarioDocumento.findByPfsComedocuConsedocu", query = "SELECT c FROM ComentarioDocumento c WHERE c.comentarioDocumentoPK.pfsComedocuConsedocu = :pfsComedocuConsedocu"),
    @NamedQuery(name = "ComentarioDocumento.findByPfsComedocuVersion", query = "SELECT c FROM ComentarioDocumento c WHERE c.comentarioDocumentoPK.pfsComedocuVersion = :pfsComedocuVersion"),
    @NamedQuery(name = "ComentarioDocumento.findByPiComedocuConsecutivo", query = "SELECT c FROM ComentarioDocumento c WHERE c.comentarioDocumentoPK.piComedocuConsecutivo = :piComedocuConsecutivo"),
    @NamedQuery(name = "ComentarioDocumento.findBySComedocuAccion", query = "SELECT c FROM ComentarioDocumento c WHERE c.sComedocuAccion = :sComedocuAccion"),
    @NamedQuery(name = "ComentarioDocumento.findByDComedocuCreacion", query = "SELECT c FROM ComentarioDocumento c WHERE c.dComedocuCreacion = :dComedocuCreacion"),
    @NamedQuery(name = "ComentarioDocumento.findByDComedocuUltimodi", query = "SELECT c FROM ComentarioDocumento c WHERE c.dComedocuUltimodi = :dComedocuUltimodi")})
public class ComentarioDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComentarioDocumentoPK comentarioDocumentoPK;
    @Lob
    @Column(name = "s_comedocu_comentario")
    private String sComedocuComentario;
    @Basic(optional = false)
    @Column(name = "s_comedocu_accion")
    private char sComedocuAccion;
    @Basic(optional = false)
    @Column(name = "d_comedocu_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dComedocuCreacion;
    @Column(name = "d_comedocu_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dComedocuUltimodi;
    @JoinColumn(name = "fs_comedocu_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsComedocuUsuultmod;
    @JoinColumn(name = "fs_comedocu_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsComedocuUsuacrea;
    @JoinColumns({
        @JoinColumn(name = "pfs_comedocu_tipodocu", referencedColumnName = "pfs_document_tipodocu", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_comedocu_letrproc", referencedColumnName = "pfs_document_letrproc", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_comedocu_consedocu", referencedColumnName = "ps_document_consecutivo", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_comedocu_version", referencedColumnName = "ps_document_version", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Documento documento;

    public ComentarioDocumento() {
    }

    public ComentarioDocumento(ComentarioDocumentoPK comentarioDocumentoPK) {
        this.comentarioDocumentoPK = comentarioDocumentoPK;
    }

    public ComentarioDocumento(ComentarioDocumentoPK comentarioDocumentoPK, char sComedocuAccion, Date dComedocuCreacion) {
        this.comentarioDocumentoPK = comentarioDocumentoPK;
        this.sComedocuAccion = sComedocuAccion;
        this.dComedocuCreacion = dComedocuCreacion;
    }

    public ComentarioDocumento(char pfsComedocuTipodocu, char pfsComedocuLetrproc, String pfsComedocuConsedocu, String pfsComedocuVersion, int piComedocuConsecutivo) {
        this.comentarioDocumentoPK = new ComentarioDocumentoPK(pfsComedocuTipodocu, pfsComedocuLetrproc, pfsComedocuConsedocu, pfsComedocuVersion, piComedocuConsecutivo);
    }

    public ComentarioDocumentoPK getComentarioDocumentoPK() {
        return comentarioDocumentoPK;
    }

    public void setComentarioDocumentoPK(ComentarioDocumentoPK comentarioDocumentoPK) {
        this.comentarioDocumentoPK = comentarioDocumentoPK;
    }

    public String getSComedocuComentario() {
        return sComedocuComentario;
    }

    public void setSComedocuComentario(String sComedocuComentario) {
        this.sComedocuComentario = sComedocuComentario;
    }

    public char getSComedocuAccion() {
        return sComedocuAccion;
    }

    public void setSComedocuAccion(char sComedocuAccion) {
        this.sComedocuAccion = sComedocuAccion;
    }

    public Date getDComedocuCreacion() {
        return dComedocuCreacion;
    }

    public void setDComedocuCreacion(Date dComedocuCreacion) {
        this.dComedocuCreacion = dComedocuCreacion;
    }

    public Date getDComedocuUltimodi() {
        return dComedocuUltimodi;
    }

    public void setDComedocuUltimodi(Date dComedocuUltimodi) {
        this.dComedocuUltimodi = dComedocuUltimodi;
    }

    public SUsuario getFsComedocuUsuultmod() {
        return fsComedocuUsuultmod;
    }

    public void setFsComedocuUsuultmod(SUsuario fsComedocuUsuultmod) {
        this.fsComedocuUsuultmod = fsComedocuUsuultmod;
    }

    public SUsuario getFsComedocuUsuacrea() {
        return fsComedocuUsuacrea;
    }

    public void setFsComedocuUsuacrea(SUsuario fsComedocuUsuacrea) {
        this.fsComedocuUsuacrea = fsComedocuUsuacrea;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comentarioDocumentoPK != null ? comentarioDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioDocumento)) {
            return false;
        }
        ComentarioDocumento other = (ComentarioDocumento) object;
        if ((this.comentarioDocumentoPK == null && other.comentarioDocumentoPK != null) || (this.comentarioDocumentoPK != null && !this.comentarioDocumentoPK.equals(other.comentarioDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH 'h' mm 'min' ss 's'");
        return "Autor: " + fsComedocuUsuacrea +"\nComentario: " + sComedocuComentario + "\nFecha: " + sdf.format(dComedocuCreacion);
    }
    
}
