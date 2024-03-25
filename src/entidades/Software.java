/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "software", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Softwares.findAll", query = "SELECT s FROM Software s"),
    @NamedQuery(name = "Softwares.findById", query = "SELECT s FROM Software s WHERE s.softwaresPK.id = :id"),
    @NamedQuery(name = "Softwares.findByHardwareId", query = "SELECT s FROM Software s WHERE s.softwaresPK.hardwareId = :hardwareId"),
    @NamedQuery(name = "Softwares.findByPublisher", query = "SELECT s FROM Software s WHERE s.publisher = :publisher"),
    @NamedQuery(name = "Softwares.findByName", query = "SELECT s FROM Software s WHERE s.name = :name"),
    @NamedQuery(name = "Softwares.findByVersion", query = "SELECT s FROM Software s WHERE s.version = :version"),
    @NamedQuery(name = "Softwares.findByFilename", query = "SELECT s FROM Software s WHERE s.filename = :filename"),
    @NamedQuery(name = "Softwares.findByFilesize", query = "SELECT s FROM Software s WHERE s.filesize = :filesize"),
    @NamedQuery(name = "Softwares.findBySource", query = "SELECT s FROM Software s WHERE s.source = :source"),
    @NamedQuery(name = "Softwares.findByFrom", query = "SELECT s FROM Software s WHERE s.from = :from")})
public class Software implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoftwaresPK softwaresPK;
    @Column(name = "s_software_publisher", length = 255)
    private String publisher;
    @Column(name = "s_software_name", length = 255)
    private String name;
    @Column(name = "s_software_version", length = 255)
    private String version;
    @Lob
    @Column(name = "s_software_folder", length = 65535)
    private String folder;
    @Lob
    @Column(name = "s_software_comment", length = 65535)
    private String comment;
    @Column(name = "s_software_filename", length = 255)
    private String filename;
    @Column(name = "i_software_filesize")
    private Integer filesize;
    @Column(name = "i_software_source")
    private Integer source;
    @Basic(optional = false)
    @Column(name = "s_software_from", nullable = false, length = 64)
    private String from;

    public Software() {
    }

    public Software(SoftwaresPK softwaresPK) {
        this.softwaresPK = softwaresPK;
    }

    public Software(SoftwaresPK softwaresPK, String from) {
        this.softwaresPK = softwaresPK;
        this.from = from;
    }

    public Software(int id, int hardwareId) {
        this.softwaresPK = new SoftwaresPK(id, hardwareId);
    }

    public SoftwaresPK getSoftwaresPK() {
        return softwaresPK;
    }

    public void setSoftwaresPK(SoftwaresPK softwaresPK) {
        this.softwaresPK = softwaresPK;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getComments() {
        return comment;
    }

    public void setComments(String comments) {
        this.comment = comments;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (softwaresPK != null ? softwaresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Software)) {
            return false;
        }
        Software other = (Software) object;
        if ((this.softwaresPK == null && other.softwaresPK != null) || (this.softwaresPK != null && !this.softwaresPK.equals(other.softwaresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Softwares[ softwaresPK=" + softwaresPK + " ]";
    }
    
}
