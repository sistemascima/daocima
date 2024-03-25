/*
 * To change this template, choose Tools | Templates
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
 * @author Jhon
 */
@Entity
@Table(name = "impresora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impresora.findAll", query = "SELECT i FROM Impresora i"),
    @NamedQuery(name = "Impresora.findByPiImpresoraId", query = "SELECT i FROM Impresora i WHERE i.piImpresoraId = :piImpresoraId"),
    @NamedQuery(name = "Impresora.findBySImpresoraNombre", query = "SELECT i FROM Impresora i WHERE i.sImpresoraNombre = :sImpresoraNombre"),
    @NamedQuery(name = "Impresora.findBySImpresoraDescripcion", query = "SELECT i FROM Impresora i WHERE i.sImpresoraDescripcion = :sImpresoraDescripcion"),
    @NamedQuery(name = "Impresora.findByDImpresoraCreacion", query = "SELECT i FROM Impresora i WHERE i.dImpresoraCreacion = :dImpresoraCreacion"),
    @NamedQuery(name = "Impresora.findByDImpresoraUltimodi", query = "SELECT i FROM Impresora i WHERE i.dImpresoraUltimodi = :dImpresoraUltimodi")})
public class Impresora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_impresora_id")
    private Integer piImpresoraId;
    @Basic(optional = false)
    @Column(name = "s_impresora_nombre")
    private String sImpresoraNombre;
    @Column(name = "s_impresora_descripcion")
    private String sImpresoraDescripcion;
    @Basic(optional = false)
    @Column(name = "d_impresora_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dImpresoraCreacion;
    @Column(name = "d_impresora_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dImpresoraUltimodi;

    public Impresora() {
    }

    public Impresora(Integer piImpresoraId) {
        this.piImpresoraId = piImpresoraId;
    }

    public Impresora(Integer piImpresoraId, String sImpresoraNombre, Date dImpresoraCreacion) {
        this.piImpresoraId = piImpresoraId;
        this.sImpresoraNombre = sImpresoraNombre;
        this.dImpresoraCreacion = dImpresoraCreacion;
    }

    public Integer getPiImpresoraId() {
        return piImpresoraId;
    }

    public void setPiImpresoraId(Integer piImpresoraId) {
        this.piImpresoraId = piImpresoraId;
    }

    public String getSImpresoraNombre() {
        return sImpresoraNombre;
    }

    public void setSImpresoraNombre(String sImpresoraNombre) {
        this.sImpresoraNombre = sImpresoraNombre;
    }

    public String getSImpresoraDescripcion() {
        return sImpresoraDescripcion;
    }

    public void setSImpresoraDescripcion(String sImpresoraDescripcion) {
        this.sImpresoraDescripcion = sImpresoraDescripcion;
    }

    public Date getDImpresoraCreacion() {
        return dImpresoraCreacion;
    }

    public void setDImpresoraCreacion(Date dImpresoraCreacion) {
        this.dImpresoraCreacion = dImpresoraCreacion;
    }

    public Date getDImpresoraUltimodi() {
        return dImpresoraUltimodi;
    }

    public void setDImpresoraUltimodi(Date dImpresoraUltimodi) {
        this.dImpresoraUltimodi = dImpresoraUltimodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piImpresoraId != null ? piImpresoraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impresora)) {
            return false;
        }
        Impresora other = (Impresora) object;
        if ((this.piImpresoraId == null && other.piImpresoraId != null) || (this.piImpresoraId != null && !this.piImpresoraId.equals(other.piImpresoraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sImpresoraNombre + " (" + sImpresoraDescripcion + ")";
    }
    
}
