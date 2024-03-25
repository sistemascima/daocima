/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByPsProveedorNit", query = "SELECT p FROM Proveedor p WHERE p.psProveedorNit = :psProveedorNit"),
    @NamedQuery(name = "Proveedor.findBySProveedorNombre", query = "SELECT p FROM Proveedor p WHERE p.sProveedorNombre = :sProveedorNombre"),
    @NamedQuery(name = "Proveedor.findBySProveedorEstado", query = "SELECT p FROM Proveedor p WHERE p.sProveedorEstado = :sProveedorEstado"),
    @NamedQuery(name = "Proveedor.findBySProveedorDireccion", query = "SELECT p FROM Proveedor p WHERE p.sProveedorDireccion = :sProveedorDireccion"),
    @NamedQuery(name = "Proveedor.findBySProveedorTelefono", query = "SELECT p FROM Proveedor p WHERE p.sProveedorTelefono = :sProveedorTelefono"),
    @NamedQuery(name = "Proveedor.findBySProveedorCorreo", query = "SELECT p FROM Proveedor p WHERE p.sProveedorCorreo = :sProveedorCorreo"),
    @NamedQuery(name = "Proveedor.findBySProveedorContacto", query = "SELECT p FROM Proveedor p WHERE p.sProveedorContacto = :sProveedorContacto"),
    @NamedQuery(name = "Proveedor.findBySProveedorObservaciones", query = "SELECT p FROM Proveedor p WHERE p.sProveedorObservaciones = :sProveedorObservaciones"),
    @NamedQuery(name = "Proveedor.findBySProveedorCarpeta", query = "SELECT p FROM Proveedor p WHERE p.sProveedorCarpeta = :sProveedorCarpeta"),
    @NamedQuery(name = "Proveedor.findByDProveedorCreacion", query = "SELECT p FROM Proveedor p WHERE p.dProveedorCreacion = :dProveedorCreacion"),
    @NamedQuery(name = "Proveedor.findByDProveedorUltimodi", query = "SELECT p FROM Proveedor p WHERE p.dProveedorUltimodi = :dProveedorUltimodi")})
public class Proveedor implements Serializable {


    @OneToMany(mappedBy = "fsMuestraanalProveedor")
    private List<MuestraAnalisis> muestraAnalisisList;

    @OneToMany(mappedBy = "fsDetsolcomProvsele")
    private Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollection;
    @Basic(optional = false)
    @Column(name = "s_proveedor_estado")
    private Character sProveedorEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsOrdecompProveedor")
    private Collection<OrdenCompra> ordenCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<EvaluacionProveedor> evaluacionProveedorCollection;
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<SoporteProveedor> soporteProveedorCollection;
    @Column(name = "d_proveedor_fechingr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProveedorFechingr;
    @Column(name = "d_proveedor_fecultree")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProveedorFecultree;
    @Column(name = "s_proveedor_portafolio")
    private String sProveedorPortafolio;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_proveedor_nit")
    private String psProveedorNit;
    @Basic(optional = false)
    @Column(name = "s_proveedor_nombre")
    private String sProveedorNombre;
    @Column(name = "s_proveedor_direccion")
    private String sProveedorDireccion;
    @Column(name = "s_proveedor_telefono")
    private String sProveedorTelefono;
    @Column(name = "s_proveedor_correo")
    private String sProveedorCorreo;
    @Column(name = "s_proveedor_contacto")
    private String sProveedorContacto;
    @Column(name = "s_proveedor_observaciones")
    private String sProveedorObservaciones;
    @Column(name = "s_proveedor_carpeta")
    private String sProveedorCarpeta;
    @Basic(optional = false)
    @Column(name = "d_proveedor_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProveedorCreacion;
    @Column(name = "d_proveedor_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProveedorUltimodi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<Cotizacion> cotizacionCollection;
    @JoinColumn(name = "fs_proveedor_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsProveedorUsuultmod;
    @JoinColumn(name = "fs_proveedor_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsProveedorUsuacrea;

    public Proveedor() {
    }

    public Proveedor(String psProveedorNit) {
        this.psProveedorNit = psProveedorNit;
    }

    public Proveedor(String psProveedorNit, String sProveedorNombre, char sProveedorEstado, Date dProveedorCreacion) {
        this.psProveedorNit = psProveedorNit;
        this.sProveedorNombre = sProveedorNombre;
        this.sProveedorEstado = sProveedorEstado;
        this.dProveedorCreacion = dProveedorCreacion;
    }

    public String getPsProveedorNit() {
        return psProveedorNit;
    }

    public void setPsProveedorNit(String psProveedorNit) {
        this.psProveedorNit = psProveedorNit;
    }

    public String getSProveedorNombre() {
        return sProveedorNombre;
    }

    public void setSProveedorNombre(String sProveedorNombre) {
        this.sProveedorNombre = sProveedorNombre;
    }

    public char getSProveedorEstado() {
        return sProveedorEstado;
    }

    public void setSProveedorEstado(char sProveedorEstado) {
        this.sProveedorEstado = sProveedorEstado;
    }

    public String getSProveedorDireccion() {
        return sProveedorDireccion;
    }

    public void setSProveedorDireccion(String sProveedorDireccion) {
        this.sProveedorDireccion = sProveedorDireccion;
    }

    public String getSProveedorTelefono() {
        return sProveedorTelefono;
    }

    public void setSProveedorTelefono(String sProveedorTelefono) {
        this.sProveedorTelefono = sProveedorTelefono;
    }

    public String getSProveedorCorreo() {
        return sProveedorCorreo;
    }

    public void setSProveedorCorreo(String sProveedorCorreo) {
        this.sProveedorCorreo = sProveedorCorreo;
    }

    public String getSProveedorContacto() {
        return sProveedorContacto;
    }

    public void setSProveedorContacto(String sProveedorContacto) {
        this.sProveedorContacto = sProveedorContacto;
    }

    public String getSProveedorObservaciones() {
        return sProveedorObservaciones;
    }

    public void setSProveedorObservaciones(String sProveedorObservaciones) {
        this.sProveedorObservaciones = sProveedorObservaciones;
    }

    public String getSProveedorCarpeta() {
        return sProveedorCarpeta;
    }

    public void setSProveedorCarpeta(String sProveedorCarpeta) {
        this.sProveedorCarpeta = sProveedorCarpeta;
    }

    public Date getDProveedorCreacion() {
        return dProveedorCreacion;
    }

    public void setDProveedorCreacion(Date dProveedorCreacion) {
        this.dProveedorCreacion = dProveedorCreacion;
    }

    public Date getDProveedorUltimodi() {
        return dProveedorUltimodi;
    }

    public void setDProveedorUltimodi(Date dProveedorUltimodi) {
        this.dProveedorUltimodi = dProveedorUltimodi;
    }

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
    }

    public SUsuario getFsProveedorUsuultmod() {
        return fsProveedorUsuultmod;
    }

    public void setFsProveedorUsuultmod(SUsuario fsProveedorUsuultmod) {
        this.fsProveedorUsuultmod = fsProveedorUsuultmod;
    }

    public SUsuario getFsProveedorUsuacrea() {
        return fsProveedorUsuacrea;
    }

    public void setFsProveedorUsuacrea(SUsuario fsProveedorUsuacrea) {
        this.fsProveedorUsuacrea = fsProveedorUsuacrea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psProveedorNit != null ? psProveedorNit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.psProveedorNit == null && other.psProveedorNit != null) || (this.psProveedorNit != null && !this.psProveedorNit.equals(other.psProveedorNit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + psProveedorNit + "]  " + sProveedorNombre;
    }

    public String getSProveedorPortafolio() {
        return sProveedorPortafolio;
    }

    public void setSProveedorPortafolio(String sProveedorPortafolio) {
        this.sProveedorPortafolio = sProveedorPortafolio;
    }

    public Date getDProveedorFechingr() {
        return dProveedorFechingr;
    }

    public void setDProveedorFechingr(Date dProveedorFechingr) {
        this.dProveedorFechingr = dProveedorFechingr;
    }

    public Date getDProveedorFecultree() {
        return dProveedorFecultree;
    }

    public void setDProveedorFecultree(Date dProveedorFecultree) {
        this.dProveedorFecultree = dProveedorFecultree;
    }

    @XmlTransient
    public Collection<SoporteProveedor> getSoporteProveedorCollection() {
        return soporteProveedorCollection;
    }

    public void setSoporteProveedorCollection(Collection<SoporteProveedor> soporteProveedorCollection) {
        this.soporteProveedorCollection = soporteProveedorCollection;
    }

    @XmlTransient
    public Collection<EvaluacionProveedor> getEvaluacionProveedorCollection() {
        return evaluacionProveedorCollection;
    }

    public void setEvaluacionProveedorCollection(Collection<EvaluacionProveedor> evaluacionProveedorCollection) {
        this.evaluacionProveedorCollection = evaluacionProveedorCollection;
    }

    @XmlTransient
    public Collection<OrdenCompra> getOrdenCompraCollection() {
        return ordenCompraCollection;
    }

    public void setOrdenCompraCollection(Collection<OrdenCompra> ordenCompraCollection) {
        this.ordenCompraCollection = ordenCompraCollection;
    }

    public void setSProveedorEstado(Character sProveedorEstado) {
        this.sProveedorEstado = sProveedorEstado;
    }

    @XmlTransient
    public Collection<DetalleSolicitudCompra> getDetalleSolicitudCompraCollection() {
        return detalleSolicitudCompraCollection;
    }

    public void setDetalleSolicitudCompraCollection(Collection<DetalleSolicitudCompra> detalleSolicitudCompraCollection) {
        this.detalleSolicitudCompraCollection = detalleSolicitudCompraCollection;
    }

    

    @XmlTransient
    public List<MuestraAnalisis> getMuestraAnalisisList() {
        return muestraAnalisisList;
    }

    public void setMuestraAnalisisList(List<MuestraAnalisis> muestraAnalisisList) {
        this.muestraAnalisisList = muestraAnalisisList;
    }

  

   
}
