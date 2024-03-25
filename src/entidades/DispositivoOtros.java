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
@Table(name = "dispositivo_otros", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivoOtros.findAll", query = "SELECT d FROM DispositivoOtros d"),
    @NamedQuery(name = "DispositivoOtros.findByPiDispootrosId", query = "SELECT d FROM DispositivoOtros d WHERE d.piDispootrosId = :piDispootrosId"),
    @NamedQuery(name = "DispositivoOtros.findBySDispootrosType", query = "SELECT d FROM DispositivoOtros d WHERE d.sDispootrosType = :sDispootrosType"),
    @NamedQuery(name = "DispositivoOtros.findByDDispootrosFechingr", query = "SELECT d FROM DispositivoOtros d WHERE d.dDispootrosFechingr = :dDispootrosFechingr"),
    @NamedQuery(name = "DispositivoOtros.findByIDispootrosUsua", query = "SELECT d FROM DispositivoOtros d WHERE d.iDispootrosUsua = :iDispootrosUsua"),
    @NamedQuery(name = "DispositivoOtros.findBySDispootrosUbcn", query = "SELECT d FROM DispositivoOtros d WHERE d.sDispootrosUbcn = :sDispootrosUbcn"),
    @NamedQuery(name = "DispositivoOtros.findBySDispootrosDescripcion", query = "SELECT d FROM DispositivoOtros d WHERE d.sDispootrosDescripcion = :sDispootrosDescripcion")})
public class DispositivoOtros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_dispootros_id", nullable = false)
    private Integer piDispootrosId;
    @Basic(optional = false)
    @Column(name = "s_dispootros_type", nullable = false, length = 255)
    private String sDispootrosType;
    @Basic(optional = false)
    @Column(name = "d_dispootros_fechingr", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dDispootrosFechingr;
    @Basic(optional = false)
    @Column(name = "i_dispootros_usua", nullable = false)
    private int iDispootrosUsua;
    @Basic(optional = false)
    @Column(name = "s_dispootros_ubcn", nullable = false, length = 255)
    private String sDispootrosUbcn;
    @Basic(optional = false)
    @Column(name = "s_dispootros_descripcion", nullable = false, length = 255)
    private String sDispootrosDescripcion;

    public DispositivoOtros() {
    }

    public DispositivoOtros(Integer piDispootrosId) {
        this.piDispootrosId = piDispootrosId;
    }

    public DispositivoOtros(Integer piDispootrosId, String sDispootrosType, Date dDispootrosFechingr, int iDispootrosUsua, String sDispootrosUbcn, String sDispootrosDescripcion) {
        this.piDispootrosId = piDispootrosId;
        this.sDispootrosType = sDispootrosType;
        this.dDispootrosFechingr = dDispootrosFechingr;
        this.iDispootrosUsua = iDispootrosUsua;
        this.sDispootrosUbcn = sDispootrosUbcn;
        this.sDispootrosDescripcion = sDispootrosDescripcion;
    }

    public Integer getPiDispootrosId() {
        return piDispootrosId;
    }

    public void setPiDispootrosId(Integer piDispootrosId) {
        this.piDispootrosId = piDispootrosId;
    }

    public String getSDispootrosType() {
        return sDispootrosType;
    }

    public void setSDispootrosType(String sDispootrosType) {
        this.sDispootrosType = sDispootrosType;
    }

    public Date getDDispootrosFechingr() {
        return dDispootrosFechingr;
    }

    public void setDDispootrosFechingr(Date dDispootrosFechingr) {
        this.dDispootrosFechingr = dDispootrosFechingr;
    }

    public int getIDispootrosUsua() {
        return iDispootrosUsua;
    }

    public void setIDispootrosUsua(int iDispootrosUsua) {
        this.iDispootrosUsua = iDispootrosUsua;
    }

    public String getSDispootrosUbcn() {
        return sDispootrosUbcn;
    }

    public void setSDispootrosUbcn(String sDispootrosUbcn) {
        this.sDispootrosUbcn = sDispootrosUbcn;
    }

    public String getSDispootrosDescripcion() {
        return sDispootrosDescripcion;
    }

    public void setSDispootrosDescripcion(String sDispootrosDescripcion) {
        this.sDispootrosDescripcion = sDispootrosDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piDispootrosId != null ? piDispootrosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivoOtros)) {
            return false;
        }
        DispositivoOtros other = (DispositivoOtros) object;
        if ((this.piDispootrosId == null && other.piDispootrosId != null) || (this.piDispootrosId != null && !this.piDispootrosId.equals(other.piDispootrosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DispositivoOtros[ piDispootrosId=" + piDispootrosId + " ]";
    }
    
}
