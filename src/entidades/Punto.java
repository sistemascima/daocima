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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "punto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Punto.findAll", query = "SELECT p FROM Punto p"),
    @NamedQuery(name = "Punto.findByIdpunto", query = "SELECT p FROM Punto p WHERE p.idpunto = :idpunto"),
    @NamedQuery(name = "Punto.findByNombre", query = "SELECT p FROM Punto p WHERE p.nombre = :nombre")})
  
public class Punto implements Serializable {

    @OneToMany(mappedBy = "fiMuestreoHistoricoPunto")
    private List<MuestreoHistorico> muestreoHistoricoList;

    @EmbeddedId
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_punto_id", nullable = false)
    private Integer idpunto;
    @Column(name = "s_punto_nombre", length = 45)
    private String nombre;
   
    @OneToMany(mappedBy = "idPunto")
    private List<Muestreo> muestreoList;

    public Punto() {
    }

    public Punto(Integer idpunto) {
        this.idpunto = idpunto;
    }

    public Integer getIdpunto() {
        return idpunto;
    }

    public void setIdpunto(Integer idpunto) {
        this.idpunto = idpunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    @XmlTransient
    public List<Muestreo> getMuestreoList() {
        return muestreoList;
    }

    public void setMuestreoList(List<Muestreo> muestreoList) {
        this.muestreoList = muestreoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpunto != null ? idpunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Punto)) {
            return false;
        }
        Punto other = (Punto) object;
        if ((this.idpunto == null && other.idpunto != null) || (this.idpunto != null && !this.idpunto.equals(other.idpunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Punto[ idpunto=" + idpunto + " ]";
    }

    @XmlTransient
    public List<MuestreoHistorico> getMuestreoHistoricoList() {
        return muestreoHistoricoList;
    }

    public void setMuestreoHistoricoList(List<MuestreoHistorico> muestreoHistoricoList) {
        this.muestreoHistoricoList = muestreoHistoricoList;
    }


    
}
