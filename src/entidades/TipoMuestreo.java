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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "tipo_muestreo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMuestreo.findAll", query = "SELECT t FROM TipoMuestreo t"),
    @NamedQuery(name = "TipoMuestreo.findByIdtpMuestra", query = "SELECT t FROM TipoMuestreo t WHERE t.idtpMuestra = :idtpMuestra"),
    @NamedQuery(name = "TipoMuestreo.findByNombre", query = "SELECT t FROM TipoMuestreo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMuestreo.findByDescripcion", query = "SELECT t FROM TipoMuestreo t WHERE t.descripcion = :descripcion")})
public class TipoMuestreo implements Serializable {


   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_tipomuestreo_id", nullable = false)
    private Integer idtpMuestra;
    @Column(name="s_tipomuestreo_nombre", length = 45)
    private String nombre;
    @Column(name="s_tipomuestreo_descripcion", length = 225)
    private String descripcion;

    public TipoMuestreo() {
    }

    public TipoMuestreo(Integer idtpMuestra) {
        this.idtpMuestra = idtpMuestra;
    }

    public Integer getIdtpMuestra() {
        return idtpMuestra;
    }

    public void setIdtpMuestra(Integer idtpMuestra) {
        this.idtpMuestra = idtpMuestra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtpMuestra != null ? idtpMuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMuestreo)) {
            return false;
        }
        TipoMuestreo other = (TipoMuestreo) object;
        if ((this.idtpMuestra == null && other.idtpMuestra != null) || (this.idtpMuestra != null && !this.idtpMuestra.equals(other.idtpMuestra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TpMuestra[ idtpMuestra=" + idtpMuestra + " ]";
    }



   


    

}
