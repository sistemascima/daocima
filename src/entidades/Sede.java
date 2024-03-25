/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findByPiSede", query = "SELECT s FROM Sede s WHERE s.piSede = :piSede")
    , @NamedQuery(name = "Sede.findBySSedeNombre", query = "SELECT s FROM Sede s WHERE s.sSedeNombre = :sSedeNombre")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_sede", nullable = false)
    private Integer piSede;
    @Basic(optional = false)
    @Column(name = "s_sede_nombre", nullable = false, length = 45)
    private String sSedeNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iObjetoSedeId")
    private List<Objeto> objetoList;

    public Sede() {
    }

    public Sede(Integer piSede) {
        this.piSede = piSede;
    }

    public Sede(Integer piSede, String sSedeNombre) {
        this.piSede = piSede;
        this.sSedeNombre = sSedeNombre;
    }

    public Integer getPiSede() {
        return piSede;
    }

    public void setPiSede(Integer piSede) {
        this.piSede = piSede;
    }

    public String getSSedeNombre() {
        return sSedeNombre;
    }

    public void setSSedeNombre(String sSedeNombre) {
        this.sSedeNombre = sSedeNombre;
    }

    @XmlTransient
    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public void setObjetoList(List<Objeto> objetoList) {
        this.objetoList = objetoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piSede != null ? piSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.piSede == null && other.piSede != null) || (this.piSede != null && !this.piSede.equals(other.piSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Sede[ piSede=" + piSede + " ]";
    }
    
}
