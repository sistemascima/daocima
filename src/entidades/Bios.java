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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "bios", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bios.findAll", query = "SELECT b FROM Bios b"),
    @NamedQuery(name = "Bios.findByHardwareId", query = "SELECT b FROM Bios b WHERE b.hardwareId = :hardwareId"),
    @NamedQuery(name = "Bios.findBySmanufacturer", query = "SELECT b FROM Bios b WHERE b.smanufacturer = :smanufacturer"),
    @NamedQuery(name = "Bios.findBySmodel", query = "SELECT b FROM Bios b WHERE b.smodel = :smodel"),
    @NamedQuery(name = "Bios.findBySsn", query = "SELECT b FROM Bios b WHERE b.ssn = :ssn"),
    @NamedQuery(name = "Bios.findByType", query = "SELECT b FROM Bios b WHERE b.type = :type"),
    @NamedQuery(name = "Bios.findByBmanufacturer", query = "SELECT b FROM Bios b WHERE b.bmanufacturer = :bmanufacturer"),
    @NamedQuery(name = "Bios.findByBversion", query = "SELECT b FROM Bios b WHERE b.bversion = :bversion"),
    @NamedQuery(name = "Bios.findByBdate", query = "SELECT b FROM Bios b WHERE b.bdate = :bdate"),
    @NamedQuery(name = "Bios.findByAssettag", query = "SELECT b FROM Bios b WHERE b.assettag = :assettag")})
public class Bios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pfi_bios_hardware", nullable = false)
    private Integer hardwareId;
    @Column(name = "s_bios_smanufacturer", length = 255)
    private String smanufacturer;
    @Column(name = "s_bios_smodel", length = 255)
    private String smodel;
    @Column(name = "s_bios_ssn", length = 255)
    private String ssn;
    @Column(name = "s_bios_type", length = 255)
    private String type;
    @Column(name = "s_bios_bmanufacturer", length = 255)
    private String bmanufacturer;
    @Column(name = "s_bios_bversion", length = 255)
    private String bversion;
    @Column(name = "s_bios_bdate", length = 255)
    private String bdate;
    @Column(name = "s_bios_assettag", length = 255)
    private String assettag;
    @JoinColumn(name = "pfi_bios_hardware", referencedColumnName = "pi_hardware_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Hardware hardware;

    public Bios() {
    }

    public Bios(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getSmanufacturer() {
        return smanufacturer;
    }

    public void setSmanufacturer(String smanufacturer) {
        this.smanufacturer = smanufacturer;
    }

    public String getSmodel() {
        return smodel;
    }

    public void setSmodel(String smodel) {
        this.smodel = smodel;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBmanufacturer() {
        return bmanufacturer;
    }

    public void setBmanufacturer(String bmanufacturer) {
        this.bmanufacturer = bmanufacturer;
    }

    public String getBversion() {
        return bversion;
    }

    public void setBversion(String bversion) {
        this.bversion = bversion;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getAssettag() {
        return assettag;
    }

    public void setAssettag(String assettag) {
        this.assettag = assettag;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hardwareId != null ? hardwareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bios)) {
            return false;
        }
        Bios other = (Bios) object;
        if ((this.hardwareId == null && other.hardwareId != null) || (this.hardwareId != null && !this.hardwareId.equals(other.hardwareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Bios[ hardwareId=" + hardwareId + " ]";
    }
    
}
