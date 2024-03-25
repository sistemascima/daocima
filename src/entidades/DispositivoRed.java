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
 * @author TOSHIBA
 */
@Entity
@Table(name = "dispositivo_red", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivoRed.findAll", query = "SELECT d FROM DispositivoRed d"),
    @NamedQuery(name = "DispositivoRed.findByPiDispredId", query = "SELECT d FROM DispositivoRed d WHERE d.piDispredId = :piDispredId"),
    @NamedQuery(name = "DispositivoRed.findBySDispredType", query = "SELECT d FROM DispositivoRed d WHERE d.sDispredType = :sDispredType"),
    @NamedQuery(name = "DispositivoRed.findByDDispredFeching", query = "SELECT d FROM DispositivoRed d WHERE d.dDispredFeching = :dDispredFeching"),
    @NamedQuery(name = "DispositivoRed.findByIDispredUsuaid", query = "SELECT d FROM DispositivoRed d WHERE d.iDispredUsuaid = :iDispredUsuaid"),
    @NamedQuery(name = "DispositivoRed.findBySDispredUbcn", query = "SELECT d FROM DispositivoRed d WHERE d.sDispredUbcn = :sDispredUbcn"),
    @NamedQuery(name = "DispositivoRed.findBySDispredDescr", query = "SELECT d FROM DispositivoRed d WHERE d.sDispredDescr = :sDispredDescr"),
    @NamedQuery(name = "DispositivoRed.findBySDispredIp", query = "SELECT d FROM DispositivoRed d WHERE d.sDispredIp = :sDispredIp"),
    @NamedQuery(name = "DispositivoRed.findByIDispredPasswordip", query = "SELECT d FROM DispositivoRed d WHERE d.iDispredPasswordip = :iDispredPasswordip")})
public class DispositivoRed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_dispred_id", nullable = false)
    private Integer piDispredId;
    @Basic(optional = false)
    @Column(name = "s_dispred_type", nullable = false, length = 30)
    private String sDispredType;
    @Basic(optional = false)
    @Column(name = "d_dispred_feching", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dDispredFeching;
    @Basic(optional = false)
    @Column(name = "i_dispred_usuaid", nullable = false)
    private int iDispredUsuaid;
    @Basic(optional = false)
    @Column(name = "s_dispred_ubcn", nullable = false, length = 30)
    private String sDispredUbcn;
    @Column(name = "s_dispred_descr", length = 255)
    private String sDispredDescr;
    @Column(name = "s_dispred_ip", length = 100)
    private String sDispredIp;
    @Column(name = "i_dispred_passwordip")
    private Integer iDispredPasswordip;

    public DispositivoRed() {
    }

    public DispositivoRed(Integer piDispredId) {
        this.piDispredId = piDispredId;
    }

    public DispositivoRed(Integer piDispredId, String sDispredType, Date dDispredFeching, int iDispredUsuaid, String sDispredUbcn) {
        this.piDispredId = piDispredId;
        this.sDispredType = sDispredType;
        this.dDispredFeching = dDispredFeching;
        this.iDispredUsuaid = iDispredUsuaid;
        this.sDispredUbcn = sDispredUbcn;
    }

    public Integer getPiDispredId() {
        return piDispredId;
    }

    public void setPiDispredId(Integer piDispredId) {
        this.piDispredId = piDispredId;
    }

    public String getSDispredType() {
        return sDispredType;
    }

    public void setSDispredType(String sDispredType) {
        this.sDispredType = sDispredType;
    }

    public Date getDDispredFeching() {
        return dDispredFeching;
    }

    public void setDDispredFeching(Date dDispredFeching) {
        this.dDispredFeching = dDispredFeching;
    }

    public int getIDispredUsuaid() {
        return iDispredUsuaid;
    }

    public void setIDispredUsuaid(int iDispredUsuaid) {
        this.iDispredUsuaid = iDispredUsuaid;
    }

    public String getSDispredUbcn() {
        return sDispredUbcn;
    }

    public void setSDispredUbcn(String sDispredUbcn) {
        this.sDispredUbcn = sDispredUbcn;
    }

    public String getSDispredDescr() {
        return sDispredDescr;
    }

    public void setSDispredDescr(String sDispredDescr) {
        this.sDispredDescr = sDispredDescr;
    }

    public String getSDispredIp() {
        return sDispredIp;
    }

    public void setSDispredIp(String sDispredIp) {
        this.sDispredIp = sDispredIp;
    }

    public Integer getIDispredPasswordip() {
        return iDispredPasswordip;
    }

    public void setIDispredPasswordip(Integer iDispredPasswordip) {
        this.iDispredPasswordip = iDispredPasswordip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piDispredId != null ? piDispredId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivoRed)) {
            return false;
        }
        DispositivoRed other = (DispositivoRed) object;
        if ((this.piDispredId == null && other.piDispredId != null) || (this.piDispredId != null && !this.piDispredId.equals(other.piDispredId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DispositivoRed[ piDispredId=" + piDispredId + " ]";
    }
    
}
