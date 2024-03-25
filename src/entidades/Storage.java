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
@Table(name = "storage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storages.findAll", query = "SELECT s FROM Storage s"),
    @NamedQuery(name = "Storages.findById", query = "SELECT s FROM Storage s WHERE s.storagesPK.id = :id"),
    @NamedQuery(name = "Storages.findByHardwareId", query = "SELECT s FROM Storage s WHERE s.storagesPK.hardwareId = :hardwareId"),
    @NamedQuery(name = "Storages.findByManufacturer", query = "SELECT s FROM Storage s WHERE s.manufacturer = :manufacturer"),
    @NamedQuery(name = "Storages.findByName", query = "SELECT s FROM Storage s WHERE s.name = :name"),
    @NamedQuery(name = "Storages.findByModel", query = "SELECT s FROM Storage s WHERE s.model = :model"),
    @NamedQuery(name = "Storages.findByDescription", query = "SELECT s FROM Storage s WHERE s.description = :description"),
    @NamedQuery(name = "Storages.findByType", query = "SELECT s FROM Storage s WHERE s.type = :type"),
    @NamedQuery(name = "Storages.findByDisksize", query = "SELECT s FROM Storage s WHERE s.disksize = :disksize"),
    @NamedQuery(name = "Storages.findBySerialnumber", query = "SELECT s FROM Storage s WHERE s.serialnumber = :serialnumber"),
    @NamedQuery(name = "Storages.findByFirmware", query = "SELECT s FROM Storage s WHERE s.firmware = :firmware")})
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoragesPK storagesPK;
    @Column(name = "s_storage_manufacturer", length = 255)
    private String manufacturer;
    @Column(name = "s_storage_name", length = 255)
    private String name;
    @Column(name = "s_storage_model", length = 255)
    private String model;
    @Column(name = "s_storage_description", length = 255)
    private String description;
    @Column(name = "s_storage_type", length = 255)
    private String type;
    @Column(name = "i_storage_disksize")
    private Integer disksize;
    @Column(name = "s_storage_serialnumber", length = 255)
    private String serialnumber;
    @Column(name = "s_storage_firmware", length = 255)
    private String firmware;

    public Storage() {
    }

    public Storage(StoragesPK storagesPK) {
        this.storagesPK = storagesPK;
    }

    public Storage(int id, int hardwareId) {
        this.storagesPK = new StoragesPK(id, hardwareId);
    }

    public StoragesPK getStoragesPK() {
        return storagesPK;
    }

    public void setStoragesPK(StoragesPK storagesPK) {
        this.storagesPK = storagesPK;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Integer getDisksize() {
        return disksize;
    }

    public void setDisksize(Integer disksize) {
        this.disksize = disksize;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storagesPK != null ? storagesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storage)) {
            return false;
        }
        Storage other = (Storage) object;
        if ((this.storagesPK == null && other.storagesPK != null) || (this.storagesPK != null && !this.storagesPK.equals(other.storagesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Storages[ storagesPK=" + storagesPK + " ]";
    }
    
}
