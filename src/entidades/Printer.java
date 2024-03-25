/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "printer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Printer.findAll", query = "SELECT p FROM Printer p"),
    @NamedQuery(name = "Printer.findById", query = "SELECT p FROM Printer p WHERE p.id = :id"),
    @NamedQuery(name = "Printer.findByName", query = "SELECT p FROM Printer p WHERE p.name = :name"),
    @NamedQuery(name = "Printer.findByDriver", query = "SELECT p FROM Printer p WHERE p.driver = :driver"),
    @NamedQuery(name = "Printer.findByPort", query = "SELECT p FROM Printer p WHERE p.port = :port"),
    @NamedQuery(name = "Printer.findByDescription", query = "SELECT p FROM Printer p WHERE p.description = :description"),
    @NamedQuery(name = "Printer.findByIp", query = "SELECT p FROM Printer p WHERE p.ip = :ip"),
    @NamedQuery(name = "Printer.findByFechaingreso", query = "SELECT p FROM Printer p WHERE p.fechaingreso = :fechaingreso")})
public class Printer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_printer_id", nullable = false)
    private Integer id;
    @Column(name = "s_printer_name", length = 255)
    private String name;
    @Column(name = "s_printer_driver", length = 255)
    private String driver;
    @Column(name = "s_printer_port", length = 255)
    private String port;
    @Column(name = "s_printer_description", length = 255)
    private String description;
    @Basic(optional = false)
    @Column(name = "s_printer_ip", nullable = false, length = 30)
    private String ip;
    @Basic(optional = false)
    @Column(name = "d_printer_fechingr", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;

    public Printer() {
    }

    public Printer(Integer id) {
        this.id = id;
    }

    public Printer(Integer id, String ip, Date fechaingreso) {
        this.id = id;
        this.ip = ip;
        this.fechaingreso = fechaingreso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Printer)) {
            return false;
        }
        Printer other = (Printer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Printers[ id=" + id + " ]";
    }
    
}
