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
@Table(name = "tipo_muestra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMuestra.findAll", query = "SELECT t FROM TipoMuestra t"),
    @NamedQuery(name = "TipoMuestra.findByIdTipoMuestra", query = "SELECT t FROM TipoMuestra t WHERE t.idTipoMuestra = :idTipoMuestra"),
    @NamedQuery(name = "TipoMuestra.findByNombre", query = "SELECT t FROM TipoMuestra t WHERE t.nombre = :nombre"),
    })
public class TipoMuestra implements Serializable {

   


    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_tipomuestra_id", nullable = false)
    private Integer idTipoMuestra;
    @Column(name = "s_tipomuestra_nombre", length = 45)
    private String nombre;
    

    public TipoMuestra() {
    }

    public TipoMuestra(Integer idTipoMuestra) {
        this.idTipoMuestra = idTipoMuestra;
    }

    public Integer getIdTipoMuestra() {
        return idTipoMuestra;
    }

    public void setIdTipoMuestra(Integer idTipoMuestra) {
        this.idTipoMuestra = idTipoMuestra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMuestra != null ? idTipoMuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMuestra)) {
            return false;
        }
        TipoMuestra other = (TipoMuestra) object;
        if ((this.idTipoMuestra == null && other.idTipoMuestra != null) || (this.idTipoMuestra != null && !this.idTipoMuestra.equals(other.idTipoMuestra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoMuestra[ idTipoMuestra=" + idTipoMuestra + " ]";
    }


    

    


    
}
