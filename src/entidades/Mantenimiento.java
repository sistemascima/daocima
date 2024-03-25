/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByPiMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.piMantenimiento = :piMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByDMantenimientoFecha", query = "SELECT m FROM Mantenimiento m WHERE m.dMantenimientoFecha = :dMantenimientoFecha")
    , @NamedQuery(name = "Mantenimiento.findBySMantenimientoTipo", query = "SELECT m FROM Mantenimiento m WHERE m.sMantenimientoTipo = :sMantenimientoTipo")
    , @NamedQuery(name = "Mantenimiento.findBySMantenimientoProximo", query = "SELECT m FROM Mantenimiento m WHERE m.sMantenimientoProximo = :sMantenimientoProximo")
    , @NamedQuery(name = "Mantenimiento.findByIMantenimientoPresupuesto", query = "SELECT m FROM Mantenimiento m WHERE m.iMantenimientoPresupuesto = :iMantenimientoPresupuesto")
    , @NamedQuery(name = "Mantenimiento.findBySMantenimientoDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.sMantenimientoDescripcion = :sMantenimientoDescripcion")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_mantenimiento", nullable = false)
    private Integer piMantenimiento;
    @Basic(optional = false)
    @Column(name = "d_mantenimiento_fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dMantenimientoFecha;
    @Basic(optional = false)
    @Column(name = "s_mantenimiento_tipo", nullable = false, length = 45)
    private String sMantenimientoTipo;
    @Column(name = "s_mantenimiento_proximo", length = 45)
    private String sMantenimientoProximo;
    @Basic(optional = false)
    @Column(name = "i_mantenimiento_presupuesto", nullable = false)
    private long iMantenimientoPresupuesto;
    @Column(name = "s_mantenimiento_descripcion", length = 45)
    private String sMantenimientoDescripcion;
    @OneToMany(mappedBy = "iObjetoMantenimientoId")
    private List<Objeto> objetoList;
    @JoinColumn(name = "s_mantenimiento_objeto_id", referencedColumnName = "pi_objeto", nullable = false)
    @ManyToOne(optional = false)
    private Objeto sMantenimientoObjetoId;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer piMantenimiento) {
        this.piMantenimiento = piMantenimiento;
    }

    public Mantenimiento(Integer piMantenimiento, Date dMantenimientoFecha, String sMantenimientoTipo, long iMantenimientoPresupuesto) {
        this.piMantenimiento = piMantenimiento;
        this.dMantenimientoFecha = dMantenimientoFecha;
        this.sMantenimientoTipo = sMantenimientoTipo;
        this.iMantenimientoPresupuesto = iMantenimientoPresupuesto;
    }

    public Integer getPiMantenimiento() {
        return piMantenimiento;
    }

    public void setPiMantenimiento(Integer piMantenimiento) {
        this.piMantenimiento = piMantenimiento;
    }

    public Date getDMantenimientoFecha() {
        return dMantenimientoFecha;
    }

    public void setDMantenimientoFecha(Date dMantenimientoFecha) {
        this.dMantenimientoFecha = dMantenimientoFecha;
    }

    public String getSMantenimientoTipo() {
        return sMantenimientoTipo;
    }

    public void setSMantenimientoTipo(String sMantenimientoTipo) {
        this.sMantenimientoTipo = sMantenimientoTipo;
    }

    public String getSMantenimientoProximo() {
        return sMantenimientoProximo;
    }

    public void setSMantenimientoProximo(String sMantenimientoProximo) {
        this.sMantenimientoProximo = sMantenimientoProximo;
    }

    public long getIMantenimientoPresupuesto() {
        return iMantenimientoPresupuesto;
    }

    public void setIMantenimientoPresupuesto(long iMantenimientoPresupuesto) {
        this.iMantenimientoPresupuesto = iMantenimientoPresupuesto;
    }

    public String getSMantenimientoDescripcion() {
        return sMantenimientoDescripcion;
    }

    public void setSMantenimientoDescripcion(String sMantenimientoDescripcion) {
        this.sMantenimientoDescripcion = sMantenimientoDescripcion;
    }

    @XmlTransient
    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public void setObjetoList(List<Objeto> objetoList) {
        this.objetoList = objetoList;
    }

    public Objeto getSMantenimientoObjetoId() {
        return sMantenimientoObjetoId;
    }

    public void setSMantenimientoObjetoId(Objeto sMantenimientoObjetoId) {
        this.sMantenimientoObjetoId = sMantenimientoObjetoId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piMantenimiento != null ? piMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.piMantenimiento == null && other.piMantenimiento != null) || (this.piMantenimiento != null && !this.piMantenimiento.equals(other.piMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mantenimiento[ piMantenimiento=" + piMantenimiento + " ]";
    }
    
}
