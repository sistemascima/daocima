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
@Table(name = "accountinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accountinfo.findAll", query = "SELECT a FROM Accountinfo a"),
    @NamedQuery(name = "Accountinfo.findByHardwareId", query = "SELECT a FROM Accountinfo a WHERE a.hardwareId = :hardwareId"),
    @NamedQuery(name = "Accountinfo.findByTag", query = "SELECT a FROM Accountinfo a WHERE a.tag = :tag")})
public class Accountinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pfi_accountin_hardware", nullable = false)
    private Integer hardwareId;
    @Column(name = "s_accountin_tag", length = 255)
    private String tag;
    @JoinColumn(name = "pfi_accountin_hardware", referencedColumnName = "pi_hardware_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Hardware hardware;

    public Accountinfo() {
    }

    public Accountinfo(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        if (!(object instanceof Accountinfo)) {
            return false;
        }
        Accountinfo other = (Accountinfo) object;
        if ((this.hardwareId == null && other.hardwareId != null) || (this.hardwareId != null && !this.hardwareId.equals(other.hardwareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Accountinfo[ hardwareId=" + hardwareId + " ]";
    }
    
}
