/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "documento_aplicativo", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoAplicativo.findAll", query = "SELECT d FROM DocumentoAplicativo d"),
    @NamedQuery(name = "DocumentoAplicativo.findByPiDocumentoAplicativo", query = "SELECT d FROM DocumentoAplicativo d WHERE d.piDocumentoAplicativo = :piDocumentoAplicativo"),
    @NamedQuery(name = "DocumentoAplicativo.findBySDocuAplicArchivo", query = "SELECT d FROM DocumentoAplicativo d WHERE d.sDocuAplicArchivo = :sDocuAplicArchivo")})
public class DocumentoAplicativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_documento_aplicativo", nullable = false)
    private Integer piDocumentoAplicativo;
    @Column(name = "s_docu_aplic_archivo", length = 45)
    private String sDocuAplicArchivo;
    @JoinColumns({
        @JoinColumn(name = "fs_docu_aplic_document_tipodocu", referencedColumnName = "pfs_document_tipodocu"),
        @JoinColumn(name = "fs_docu_aplic_document_letrproc", referencedColumnName = "pfs_document_letrproc"),
        @JoinColumn(name = "fs_docu_aplic_document_consecutivo", referencedColumnName = "ps_document_consecutivo"),
        @JoinColumn(name = "fs_docu_aplic_document_version", referencedColumnName = "ps_document_version")})
    @ManyToOne
    private Documento documento;

    public DocumentoAplicativo() {
    }

    public DocumentoAplicativo(Integer piDocumentoAplicativo) {
        this.piDocumentoAplicativo = piDocumentoAplicativo;
    }

    public Integer getPiDocumentoAplicativo() {
        return piDocumentoAplicativo;
    }

    public void setPiDocumentoAplicativo(Integer piDocumentoAplicativo) {
        this.piDocumentoAplicativo = piDocumentoAplicativo;
    }

    public String getSDocuAplicArchivo() {
        return sDocuAplicArchivo;
    }

    public void setSDocuAplicArchivo(String sDocuAplicArchivo) {
        this.sDocuAplicArchivo = sDocuAplicArchivo;
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
        hash += (piDocumentoAplicativo != null ? piDocumentoAplicativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoAplicativo)) {
            return false;
        }
        DocumentoAplicativo other = (DocumentoAplicativo) object;
        if ((this.piDocumentoAplicativo == null && other.piDocumentoAplicativo != null) || (this.piDocumentoAplicativo != null && !this.piDocumentoAplicativo.equals(other.piDocumentoAplicativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocumentoAplicativo[ piDocumentoAplicativo=" + piDocumentoAplicativo + " ]";
    }
    
}
