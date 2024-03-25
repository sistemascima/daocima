/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "monitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitors.findAll", query = "SELECT m FROM Monitor m"),
    @NamedQuery(name = "Monitors.findById", query = "SELECT m FROM Monitor m WHERE m.monitorsPK.id = :id"),
    @NamedQuery(name = "Monitors.findByHardwareId", query = "SELECT m FROM Monitor m WHERE m.monitorsPK.hardwareId = :hardwareId"),
    @NamedQuery(name = "Monitors.findByManufacturer", query = "SELECT m FROM Monitor m WHERE m.manufacturer = :manufacturer"),
    @NamedQuery(name = "Monitors.findByCaption", query = "SELECT m FROM Monitor m WHERE m.caption = :caption"),
    @NamedQuery(name = "Monitors.findByDescription", query = "SELECT m FROM Monitor m WHERE m.description = :description"),
    @NamedQuery(name = "Monitors.findByType", query = "SELECT m FROM Monitor m WHERE m.type = :type"),
    @NamedQuery(name = "Monitors.findBySerial", query = "SELECT m FROM Monitor m WHERE m.serial = :serial")})
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonitorsPK monitorsPK;
    @Column(name = "s_monitor_manufacturer", length = 255)
    private String manufacturer;
    @Column(name = "s_monitor_caption", length = 255)
    private String caption;
    @Column(name = "s_monitor_description", length = 255)
    private String description;
    @Column(name = "s_monitor_type", length = 255)
    private String type;
    @Column(name = "s_monitor_serial", length = 255)
    private String serial;

    public Monitor() {
    }

    public Monitor(MonitorsPK monitorsPK) {
        this.monitorsPK = monitorsPK;
    }

    public Monitor(int id, int hardwareId) {
        this.monitorsPK = new MonitorsPK(id, hardwareId);
    }

    public MonitorsPK getMonitorsPK() {
        return monitorsPK;
    }

    public void setMonitorsPK(MonitorsPK monitorsPK) {
        this.monitorsPK = monitorsPK;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monitorsPK != null ? monitorsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.monitorsPK == null && other.monitorsPK != null) || (this.monitorsPK != null && !this.monitorsPK.equals(other.monitorsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Monitors[ monitorsPK=" + monitorsPK + " ]";
    }
    
}
