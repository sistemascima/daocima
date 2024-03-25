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
import javax.persistence.EmbeddedId;
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
 * @author Jhon
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByPsClienteNit", query = "SELECT c FROM Cliente c WHERE c.clientePK.psClienteNit = :psClienteNit"),
    @NamedQuery(name = "Cliente.findBySClienteNombre", query = "SELECT c FROM Cliente c WHERE c.sClienteNombre = :sClienteNombre"),
    @NamedQuery(name = "Cliente.findByDClienteCreacion", query = "SELECT c FROM Cliente c WHERE c.dClienteCreacion = :dClienteCreacion"),
    @NamedQuery(name = "Cliente.findByDClienteUltimodi", query = "SELECT c FROM Cliente c WHERE c.dClienteUltimodi = :dClienteUltimodi"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByContacto", query = "SELECT c FROM Cliente c WHERE c.contacto = :contacto"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo")})
public class Cliente implements Serializable {

    @OneToMany(mappedBy = "psReporteHistoricoCliente")
    private List<ReporteHistorico> reporteHistoricoList;
    @OneToMany(mappedBy = "fksReporteHistoricoClienteFacturacion")
    private List<ReporteHistorico> reporteHistoricoList1;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientePK clientePK;
    
    @Basic(optional = false)
    @Column(name = "s_cliente_nombre")
    private String sClienteNombre;
    @Basic(optional = false)
    @Column(name = "d_cliente_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dClienteCreacion;
    @Column(name = "d_cliente_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dClienteUltimodi;
    @JoinColumn(name = "fs_cliente_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsClienteUsuultmod;
    @JoinColumn(name = "fs_cliente_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsClienteUsuacrea;
     @Column(name = "s_cliente_direccion", length = 100)
    private String direccion;
    @Column(name = "s_cliente_contacto", length = 100)
    private String contacto;
    @Column(name = "s_cliente_telefono", length = 100)
    private String telefono;
    @Column(name = "s_cliente_correo", length = 100)
    private String correo;
    @Column(name = "s_cliente_correo_facturacion", length = 100)
    private String correo_facturacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsServinteCliente")
    private Collection<ServicioInterno> servicioInternoCollection;
    @OneToMany(mappedBy = "idCliente")
    private List<Reporte> reporteList;
    @OneToMany(mappedBy = "idCliente")
    private List<Muestra> muestraList;
     public Cliente() {
    }

    public Cliente(ClientePK clientePK) {
        this.clientePK = clientePK;
    }

    public Cliente(ClientePK clientePK, Date dClienteCreacion) {
        this.clientePK = clientePK;
        this.dClienteCreacion = dClienteCreacion;
    }

    public Cliente(String psClienteNit, String sClienteNombre) {
        this.clientePK = new ClientePK(psClienteNit, sClienteNombre);
    }

    public ClientePK getClientePK() {
        return clientePK;
    }

    public void setClientePK(ClientePK clientePK) {
        this.clientePK = clientePK;
    }

    public String getSClienteNombre() {
        return sClienteNombre;
    }

    public void setSClienteNombre(String sClienteNombre) {
        this.sClienteNombre = sClienteNombre;
    }

    public Date getDClienteCreacion() {
        return dClienteCreacion;
    }

    public void setDClienteCreacion(Date dClienteCreacion) {
        this.dClienteCreacion = dClienteCreacion;
    }

    public Date getDClienteUltimodi() {
        return dClienteUltimodi;
    }

    public void setDClienteUltimodi(Date dClienteUltimodi) {
        this.dClienteUltimodi = dClienteUltimodi;
    }

    public SUsuario getFsClienteUsuultmod() {
        return fsClienteUsuultmod;
    }

    public void setFsClienteUsuultmod(SUsuario fsClienteUsuultmod) {
        this.fsClienteUsuultmod = fsClienteUsuultmod;
    }

    public SUsuario getFsClienteUsuacrea() {
        return fsClienteUsuacrea;
    }

    public void setFsClienteUsuacrea(SUsuario fsClienteUsuacrea) {
        this.fsClienteUsuacrea = fsClienteUsuacrea;
    }

    @XmlTransient
    public Collection<ServicioInterno> getServicioInternoCollection() {
        return servicioInternoCollection;
    }

    public void setServicioInternoCollection(Collection<ServicioInterno> servicioInternoCollection) {
        this.servicioInternoCollection = servicioInternoCollection;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientePK != null ? clientePK.hashCode() : 0);
        return hash;
    }

     @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clientePK == null && other.clientePK != null) || (this.clientePK != null && !this.clientePK.equals(other.clientePK))) {
            return false;
        }
        return true;
    }


   
    @Override
    public String toString() {
        return "entidades.Cliente[ clientePK=" + sClienteNombre + " ]";
    }
    public String getsClienteNombre() {
        return sClienteNombre;
    }

    public void setsClienteNombre(String sClienteNombre) {
        this.sClienteNombre = sClienteNombre;
    }

    public Date getdClienteCreacion() {
        return dClienteCreacion;
    }

    public void setdClienteCreacion(Date dClienteCreacion) {
        this.dClienteCreacion = dClienteCreacion;
    }

    public Date getdClienteUltimodi() {
        return dClienteUltimodi;
    }

    public void setdClienteUltimodi(Date dClienteUltimodi) {
        this.dClienteUltimodi = dClienteUltimodi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }

    public String getCorreo_facturacion() {
        return correo_facturacion;
    }

    public void setCorreo_facturacion(String correo_facturacion) {
        this.correo_facturacion = correo_facturacion;
    }

    @XmlTransient
    public List<ReporteHistorico> getReporteHistoricoList() {
        return reporteHistoricoList;
    }

    public void setReporteHistoricoList(List<ReporteHistorico> reporteHistoricoList) {
        this.reporteHistoricoList = reporteHistoricoList;
    }

    @XmlTransient
    public List<ReporteHistorico> getReporteHistoricoList1() {
        return reporteHistoricoList1;
    }

    public void setReporteHistoricoList1(List<ReporteHistorico> reporteHistoricoList1) {
        this.reporteHistoricoList1 = reporteHistoricoList1;
    }
    
    
}
