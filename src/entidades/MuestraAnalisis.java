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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author TOSHIBA
 */
@Entity
@Table(name = "muestra_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestraAnalisis.findAll", query = "SELECT m FROM MuestraAnalisis m"),
    @NamedQuery(name = "MuestraAnalisis.findByIdmuestraAnalisis", query = "SELECT m FROM MuestraAnalisis m WHERE m.idmuestraAnalisis = :idmuestraAnalisis"),
    @NamedQuery(name = "MuestraAnalisis.findByRealizadoPor", query = "SELECT m FROM MuestraAnalisis m WHERE m.realizadoPor = :realizadoPor")})
   
public class MuestraAnalisis implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_muestraanal_id", nullable = false)
    private Integer idmuestraAnalisis;
    @Basic(optional = false)
    @Column(name = "s_muestraanal_realizado", nullable = false, length = 45)
    private String realizadoPor;
     @Basic(optional = false)
    @Column(name = "s_muestraanal_observaciones", length = 250)
    private String observaciones;
       @Basic(optional = false)
    @Column(name = "c_muestraanal_estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "d_muestraanal_fechingr")
     @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_ingreso;
    @JoinColumn(name = "fi_muestraanal_variable", referencedColumnName = "pi_varianal_id", nullable = false)
    @ManyToOne(optional = false)
    private VariableAnalisis idAnalisis;
    @Column(name = "d_muestraanal_fechmodi")
     @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_modificacion;
    @Column(name = "d_muestraanal_fecharevision")
     @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @JoinColumn(name = "fi_muestraanal_muestra", referencedColumnName = "pi_muestra_id", nullable = false)
    @ManyToOne(optional = false)
    private Muestra idMuestra;
    @JoinColumn(name = "fs_muestraanal_usuacrea", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario usuarioCreador;
    @JoinColumn(name = "fi_muestraanal_analparam", referencedColumnName = "pi_analparam_id", nullable = false)
    @ManyToOne(optional = false)
    private AnalistaParametro analista;
    @JoinColumn(name = "fs_muestraanal_usuamodi", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario usuarioModificador;
    @OneToMany(mappedBy = "idParametro")
    private List<Valor> valorList;
    @JoinColumn(name = "fs_muestraanal_proveedor", referencedColumnName = "s_proveedor_nombre")
    @ManyToOne
    private Proveedor fsMuestraanalProveedor;   
    @JoinColumn(name = "fs_muestraanal_usuarevision", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioRevision;   
    
    
    public MuestraAnalisis() {
    }

    public MuestraAnalisis(Integer idmuestraAnalisis) {
        this.idmuestraAnalisis = idmuestraAnalisis;
    }

    public MuestraAnalisis(Integer idmuestraAnalisis, String realizadoPor) {
        this.idmuestraAnalisis = idmuestraAnalisis;
        this.realizadoPor = realizadoPor;
    }

    public Integer getIdmuestraAnalisis() {
        return idmuestraAnalisis;
    }

    public void setIdmuestraAnalisis(Integer idmuestraAnalisis) {
        this.idmuestraAnalisis = idmuestraAnalisis;
    }

    public String getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(String realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public VariableAnalisis getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(VariableAnalisis idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Muestra getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(Muestra idMuestra) {
        this.idMuestra = idMuestra;
    }

    @XmlTransient
    public List<Valor> getValorList() {
        return valorList;
    }

    public void setValorList(List<Valor> valorList) {
        this.valorList = valorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmuestraAnalisis != null ? idmuestraAnalisis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraAnalisis)) {
            return false;
        }
        MuestraAnalisis other = (MuestraAnalisis) object;
        if ((this.idmuestraAnalisis == null && other.idmuestraAnalisis != null) || (this.idmuestraAnalisis != null && !this.idmuestraAnalisis.equals(other.idmuestraAnalisis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestraAnalisis[ idmuestraAnalisis=" + idmuestraAnalisis + " ]";
    }
   
    public SUsuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(SUsuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public SUsuario getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(SUsuario usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public AnalistaParametro getAnalista() {
        return analista;
    }

    public void setAnalista(AnalistaParametro analista) {
        this.analista = analista;
    }    

    public Proveedor getFsMuestraanalProveedor() {
        return fsMuestraanalProveedor;
    }

    public void setFsMuestraanalProveedor(Proveedor fsMuestraanalProveedor) {
        this.fsMuestraanalProveedor = fsMuestraanalProveedor;
    }

    public SUsuario getUsuarioRevision() {
        return usuarioRevision;
    }

    public void setUsuarioRevision(SUsuario usuarioRevision) {
        this.usuarioRevision = usuarioRevision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    
    

}
