/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author HelpDesk
 */
@Embeddable
public class SoftwaresPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pi_software_id", nullable = false)
    private int id;
    @Basic(optional = false)
    @Column(name = "fi_software_hardware", nullable = false)
    private int hardwareId;

    public SoftwaresPK() {
    }

    public SoftwaresPK(int id, int hardwareId) {
        this.id = id;
        this.hardwareId = hardwareId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) hardwareId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoftwaresPK)) {
            return false;
        }
        SoftwaresPK other = (SoftwaresPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.hardwareId != other.hardwareId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.SoftwaresPK[ id=" + id + ", hardwareId=" + hardwareId + " ]";
    }
    
}
