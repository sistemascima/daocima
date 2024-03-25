/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "password")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passwords.findAll", query = "SELECT p FROM Password p"),
    @NamedQuery(name = "Passwords.findByUser", query = "SELECT p FROM Password p WHERE p.user = :user"),
    @NamedQuery(name = "Passwords.findByPassword", query = "SELECT p FROM Password p WHERE p.password = :password"),
    @NamedQuery(name = "Passwords.findByType", query = "SELECT p FROM Password p WHERE p.type = :type  ORDER BY p.user ASC"),
    @NamedQuery(name = "Passwords.findTypeDistinct", query = "SELECT DISTINCT c.type FROM Password AS c WHERE c.type NOT LIKE 'EasyInventory'"),
    @NamedQuery(name = "Passwords.findByPasswordId", query = "SELECT p FROM Password p WHERE p.passwordId = :passwordId"),
    @NamedQuery(name = "Passwords.findPasswordByTypeAndUser", query = "SELECT p.password FROM Password p WHERE p.type = :type AND p.user = :user"),
    @NamedQuery(name = "Passwords.findByDispositivootrosId", query = "SELECT p FROM Password p WHERE p.dispositivootrosId = :dispositivootrosId"),
    @NamedQuery(name = "Passwords.findByDispositivoredId", query = "SELECT p FROM Password p WHERE p.dispositivoredId = :dispositivoredId"),
    @NamedQuery(name = "Passwords.findByHardwareId", query = "SELECT p FROM Password p WHERE p.hardwareId = :hardwareId")})
public class Password implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "s_password_user", nullable = false, length = 30)
    private String user;
    @Basic(optional = false)
    @Column(name = "s_password_pass", nullable = false, length = 30)
    private String password;
    @Basic(optional = false)
    @Column(name = "s_password_type", nullable = false, length = 30)
    private String type;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_password_id", nullable = false)
    private Integer passwordId;
    @Column(name = "i_password_dispositotroid")
    private Integer dispositivootrosId;
    @Column(name = "i_password_dispositredid")
    private Integer dispositivoredId;
    @Column(name = "fi_password_hardwareid")
    private Integer hardwareId;


    public Password() {
    }

    public Password(Integer passwordId) {
        this.passwordId = passwordId;
    }

    public Password(Integer passwordId, String user, String password, String type) {
        this.passwordId = passwordId;
        this.user = user;
        this.password = password;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Integer passwordId) {
        this.passwordId = passwordId;
    }

    public Integer getDispositivootrosId() {
        return dispositivootrosId;
    }

    public void setDispositivootrosId(Integer dispositivootrosId) {
        this.dispositivootrosId = dispositivootrosId;
    }

    public Integer getDispositivoredId() {
        return dispositivoredId;
    }

    public void setDispositivoredId(Integer dispositivoredId) {
        this.dispositivoredId = dispositivoredId;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passwordId != null ? passwordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Password)) {
            return false;
        }
        Password other = (Password) object;
        if ((this.passwordId == null && other.passwordId != null) || (this.passwordId != null && !this.passwordId.equals(other.passwordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Passwords[ passwordId=" + passwordId + " ]";
    }
    
}
