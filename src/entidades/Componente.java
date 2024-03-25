/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Jhon
 */
@Entity
@Table(name = "componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componente.findAll", query = "SELECT c FROM Componente c"),
    @NamedQuery(name = "Componente.findByPiComponenId", query = "SELECT c FROM Componente c WHERE c.piComponenId = :piComponenId"),
    @NamedQuery(name = "Componente.findBySComponenNombre", query = "SELECT c FROM Componente c WHERE c.sComponenNombre = :sComponenNombre"),
    @NamedQuery(name = "Componente.findBySComponenVentana", query = "SELECT c FROM Componente c WHERE c.sComponenVentana = :sComponenVentana"),
    @NamedQuery(name = "Componente.findBySComponenTipo", query = "SELECT c FROM Componente c WHERE c.sComponenTipo = :sComponenTipo"),
    @NamedQuery(name = "Componente.findBySComponenEstado", query = "SELECT c FROM Componente c WHERE c.sComponenEstado = :sComponenEstado"),
    @NamedQuery(name = "Componente.findBySComponenEjecutar", query = "SELECT c FROM Componente c WHERE c.sComponenEjecutar = :sComponenEjecutar"),
    @NamedQuery(name = "Componente.findBySComponenBuscar", query = "SELECT c FROM Componente c WHERE c.sComponenBuscar = :sComponenBuscar"),
    @NamedQuery(name = "Componente.findBySComponenCrear", query = "SELECT c FROM Componente c WHERE c.sComponenCrear = :sComponenCrear"),
    @NamedQuery(name = "Componente.findBySComponenActualizar", query = "SELECT c FROM Componente c WHERE c.sComponenActualizar = :sComponenActualizar"),
    @NamedQuery(name = "Componente.findBySComponenBorrar", query = "SELECT c FROM Componente c WHERE c.sComponenBorrar = :sComponenBorrar"),
    @NamedQuery(name = "Componente.findByDComponenCreacion", query = "SELECT c FROM Componente c WHERE c.dComponenCreacion = :dComponenCreacion"),
    @NamedQuery(name = "Componente.findByDComponenUltimodi", query = "SELECT c FROM Componente c WHERE c.dComponenUltimodi = :dComponenUltimodi")})
public class Componente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_componen_id")
    private Integer piComponenId;
    @Basic(optional = false)
    @Column(name = "s_componen_nombre")
    private String sComponenNombre;
    @Column(name = "s_componen_ventana")
    private String sComponenVentana;
    @Basic(optional = false)
    @Column(name = "s_componen_tipo")
    private char sComponenTipo;
    @Basic(optional = false)
    @Column(name = "s_componen_estado")
    private char sComponenEstado;
    @Column(name = "s_componen_ejecutar")
    private Character sComponenEjecutar;
    @Column(name = "s_componen_buscar")
    private Character sComponenBuscar;
    @Column(name = "s_componen_crear")
    private Character sComponenCrear;
    @Column(name = "s_componen_actualizar")
    private Character sComponenActualizar;
    @Column(name = "s_componen_borrar")
    private Character sComponenBorrar;
    @Basic(optional = false)
    @Column(name = "d_componen_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dComponenCreacion;
    @Column(name = "d_componen_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dComponenUltimodi;
    @OneToMany(mappedBy = "fiComponenPadre")
    private Collection<Componente> componenteCollection;
    @JoinColumn(name = "fi_componen_padre", referencedColumnName = "pi_componen_id")
    @ManyToOne
    private Componente fiComponenPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componente")
    private Collection<ComponenteCargo> componenteCargoCollection;

    public Componente() {
    }

    public Componente(Integer piComponenId) {
        this.piComponenId = piComponenId;
    }

    public Componente(Integer piComponenId, String sComponenNombre, char sComponenTipo, char sComponenEstado, Date dComponenCreacion) {
        this.piComponenId = piComponenId;
        this.sComponenNombre = sComponenNombre;
        this.sComponenTipo = sComponenTipo;
        this.sComponenEstado = sComponenEstado;
        this.dComponenCreacion = dComponenCreacion;
    }

    public Integer getPiComponenId() {
        return piComponenId;
    }

    public void setPiComponenId(Integer piComponenId) {
        this.piComponenId = piComponenId;
    }

    public String getSComponenNombre() {
        return sComponenNombre;
    }

    public void setSComponenNombre(String sComponenNombre) {
        this.sComponenNombre = sComponenNombre;
    }

    public String getSComponenVentana() {
        return sComponenVentana;
    }

    public void setSComponenVentana(String sComponenVentana) {
        this.sComponenVentana = sComponenVentana;
    }

    public char getSComponenTipo() {
        return sComponenTipo;
    }

    public void setSComponenTipo(char sComponenTipo) {
        this.sComponenTipo = sComponenTipo;
    }

    public char getSComponenEstado() {
        return sComponenEstado;
    }

    public void setSComponenEstado(char sComponenEstado) {
        this.sComponenEstado = sComponenEstado;
    }

    public Character getSComponenEjecutar() {
        return sComponenEjecutar;
    }

    public void setSComponenEjecutar(Character sComponenEjecutar) {
        this.sComponenEjecutar = sComponenEjecutar;
    }

    public Character getSComponenBuscar() {
        return sComponenBuscar;
    }

    public void setSComponenBuscar(Character sComponenBuscar) {
        this.sComponenBuscar = sComponenBuscar;
    }

    public Character getSComponenCrear() {
        return sComponenCrear;
    }

    public void setSComponenCrear(Character sComponenCrear) {
        this.sComponenCrear = sComponenCrear;
    }

    public Character getSComponenActualizar() {
        return sComponenActualizar;
    }

    public void setSComponenActualizar(Character sComponenActualizar) {
        this.sComponenActualizar = sComponenActualizar;
    }

    public Character getSComponenBorrar() {
        return sComponenBorrar;
    }

    public void setSComponenBorrar(Character sComponenBorrar) {
        this.sComponenBorrar = sComponenBorrar;
    }

    public Date getDComponenCreacion() {
        return dComponenCreacion;
    }

    public void setDComponenCreacion(Date dComponenCreacion) {
        this.dComponenCreacion = dComponenCreacion;
    }

    public Date getDComponenUltimodi() {
        return dComponenUltimodi;
    }

    public void setDComponenUltimodi(Date dComponenUltimodi) {
        this.dComponenUltimodi = dComponenUltimodi;
    }

    @XmlTransient
    public Collection<Componente> getComponenteCollection() {
        return componenteCollection;
    }

    public void setComponenteCollection(Collection<Componente> componenteCollection) {
        this.componenteCollection = componenteCollection;
    }

    public Componente getFiComponenPadre() {
        return fiComponenPadre;
    }

    public void setFiComponenPadre(Componente fiComponenPadre) {
        this.fiComponenPadre = fiComponenPadre;
    }

    @XmlTransient
    public Collection<ComponenteCargo> getComponenteCargoCollection() {
        return componenteCargoCollection;
    }

    public void setComponenteCargoCollection(Collection<ComponenteCargo> componenteCargoCollection) {
        this.componenteCargoCollection = componenteCargoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piComponenId != null ? piComponenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.piComponenId == null && other.piComponenId != null) || (this.piComponenId != null && !this.piComponenId.equals(other.piComponenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Componente[ piComponenId=" + piComponenId + " ]";
    }
    
}
