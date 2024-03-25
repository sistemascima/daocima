/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "version_aplicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VersionAplicacion.findAll", query = "SELECT v FROM VersionAplicacion v"),
    @NamedQuery(name = "VersionAplicacion.findByPiVersapliVersion", query = "SELECT v FROM VersionAplicacion v WHERE v.piVersapliVersion = :piVersapliVersion")})
public class VersionAplicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "pi_versapli_version")
    private BigDecimal piVersapliVersion;

    public VersionAplicacion() {
    }

    public VersionAplicacion(BigDecimal piVersapliVersion) {
        this.piVersapliVersion = piVersapliVersion;
    }

    public BigDecimal getPiVersapliVersion() {
        return piVersapliVersion;
    }

    public void setPiVersapliVersion(BigDecimal piVersapliVersion) {
        this.piVersapliVersion = piVersapliVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVersapliVersion != null ? piVersapliVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionAplicacion)) {
            return false;
        }
        VersionAplicacion other = (VersionAplicacion) object;
        if ((this.piVersapliVersion == null && other.piVersapliVersion != null) || (this.piVersapliVersion != null && !this.piVersapliVersion.equals(other.piVersapliVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VersionAplicacion[ piVersapliVersion=" + piVersapliVersion + " ]";
    }
    
}
