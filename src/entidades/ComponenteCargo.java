/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "componente_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteCargo.findAll", query = "SELECT c FROM ComponenteCargo c"),
    @NamedQuery(name = "ComponenteCargo.findByPfiCompcargComponente", query = "SELECT c FROM ComponenteCargo c WHERE c.componenteCargoPK.pfiCompcargComponente = :pfiCompcargComponente"),
    @NamedQuery(name = "ComponenteCargo.findByPfsCompcargCargo", query = "SELECT c FROM ComponenteCargo c WHERE c.componenteCargoPK.pfsCompcargCargo = :pfsCompcargCargo"),
    @NamedQuery(name = "ComponenteCargo.findBySCompcargEjecutar", query = "SELECT c FROM ComponenteCargo c WHERE c.sCompcargEjecutar = :sCompcargEjecutar"),
    @NamedQuery(name = "ComponenteCargo.findBySCompcargBuscar", query = "SELECT c FROM ComponenteCargo c WHERE c.sCompcargBuscar = :sCompcargBuscar"),
    @NamedQuery(name = "ComponenteCargo.findBySCompcargCrear", query = "SELECT c FROM ComponenteCargo c WHERE c.sCompcargCrear = :sCompcargCrear"),
    @NamedQuery(name = "ComponenteCargo.findBySCompcargActualizar", query = "SELECT c FROM ComponenteCargo c WHERE c.sCompcargActualizar = :sCompcargActualizar"),
    @NamedQuery(name = "ComponenteCargo.findBySCompcargBorrar", query = "SELECT c FROM ComponenteCargo c WHERE c.sCompcargBorrar = :sCompcargBorrar"),
    @NamedQuery(name = "ComponenteCargo.findByDCompcargCreacion", query = "SELECT c FROM ComponenteCargo c WHERE c.dCompcargCreacion = :dCompcargCreacion"),
    @NamedQuery(name = "ComponenteCargo.findByDCompcargUltimodi", query = "SELECT c FROM ComponenteCargo c WHERE c.dCompcargUltimodi = :dCompcargUltimodi")})
public class ComponenteCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComponenteCargoPK componenteCargoPK;
    @Basic(optional = false)
    @Column(name = "s_compcarg_ejecutar")
    private char sCompcargEjecutar;
    @Basic(optional = false)
    @Column(name = "s_compcarg_buscar")
    private char sCompcargBuscar;
    @Basic(optional = false)
    @Column(name = "s_compcarg_crear")
    private char sCompcargCrear;
    @Basic(optional = false)
    @Column(name = "s_compcarg_actualizar")
    private char sCompcargActualizar;
    @Basic(optional = false)
    @Column(name = "s_compcarg_borrar")
    private char sCompcargBorrar;
    @Basic(optional = false)
    @Column(name = "d_compcarg_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCompcargCreacion;
    @Column(name = "d_compcarg_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCompcargUltimodi;
    @JoinColumn(name = "pfi_compcarg_componente", referencedColumnName = "pi_componen_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componente componente;

    public ComponenteCargo() {
    }

    public ComponenteCargo(ComponenteCargoPK componenteCargoPK) {
        this.componenteCargoPK = componenteCargoPK;
    }

    public ComponenteCargo(ComponenteCargoPK componenteCargoPK, char sCompcargEjecutar, char sCompcargBuscar, char sCompcargCrear, char sCompcargActualizar, char sCompcargBorrar, Date dCompcargCreacion) {
        this.componenteCargoPK = componenteCargoPK;
        this.sCompcargEjecutar = sCompcargEjecutar;
        this.sCompcargBuscar = sCompcargBuscar;
        this.sCompcargCrear = sCompcargCrear;
        this.sCompcargActualizar = sCompcargActualizar;
        this.sCompcargBorrar = sCompcargBorrar;
        this.dCompcargCreacion = dCompcargCreacion;
    }

    public ComponenteCargo(int pfiCompcargComponente, String pfsCompcargCargo) {
        this.componenteCargoPK = new ComponenteCargoPK(pfiCompcargComponente, pfsCompcargCargo);
    }

    public ComponenteCargoPK getComponenteCargoPK() {
        return componenteCargoPK;
    }

    public void setComponenteCargoPK(ComponenteCargoPK componenteCargoPK) {
        this.componenteCargoPK = componenteCargoPK;
    }

    public char getSCompcargEjecutar() {
        return sCompcargEjecutar;
    }

    public void setSCompcargEjecutar(char sCompcargEjecutar) {
        this.sCompcargEjecutar = sCompcargEjecutar;
    }

    public char getSCompcargBuscar() {
        return sCompcargBuscar;
    }

    public void setSCompcargBuscar(char sCompcargBuscar) {
        this.sCompcargBuscar = sCompcargBuscar;
    }

    public char getSCompcargCrear() {
        return sCompcargCrear;
    }

    public void setSCompcargCrear(char sCompcargCrear) {
        this.sCompcargCrear = sCompcargCrear;
    }

    public char getSCompcargActualizar() {
        return sCompcargActualizar;
    }

    public void setSCompcargActualizar(char sCompcargActualizar) {
        this.sCompcargActualizar = sCompcargActualizar;
    }

    public char getSCompcargBorrar() {
        return sCompcargBorrar;
    }

    public void setSCompcargBorrar(char sCompcargBorrar) {
        this.sCompcargBorrar = sCompcargBorrar;
    }

    public Date getDCompcargCreacion() {
        return dCompcargCreacion;
    }

    public void setDCompcargCreacion(Date dCompcargCreacion) {
        this.dCompcargCreacion = dCompcargCreacion;
    }

    public Date getDCompcargUltimodi() {
        return dCompcargUltimodi;
    }

    public void setDCompcargUltimodi(Date dCompcargUltimodi) {
        this.dCompcargUltimodi = dCompcargUltimodi;
    }


    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (componenteCargoPK != null ? componenteCargoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponenteCargo)) {
            return false;
        }
        ComponenteCargo other = (ComponenteCargo) object;
        if ((this.componenteCargoPK == null && other.componenteCargoPK != null) || (this.componenteCargoPK != null && !this.componenteCargoPK.equals(other.componenteCargoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ComponenteCargo[ componenteCargoPK=" + componenteCargoPK + " ]";
    }
    
}
