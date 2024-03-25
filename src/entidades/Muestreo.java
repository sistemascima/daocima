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
import javax.persistence.FetchType;
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
 * @author TOSHIBA
 */
@Entity
@Table(name = "muestreo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestreo.findAll", query = "SELECT m FROM Muestreo m"),
    @NamedQuery(name = "Muestreo.findByIdMuestreo", query = "SELECT m FROM Muestreo m WHERE m.idMuestreo = :idMuestreo"),
    @NamedQuery(name = "Muestreo.findByFechaInic", query = "SELECT m FROM Muestreo m WHERE m.fechaInic = :fechaInic"),
    @NamedQuery(name = "Muestreo.findByPlanMuestreo", query = "SELECT m FROM Muestreo m WHERE m.planMuestreo = :planMuestreo"),
    @NamedQuery(name = "Muestreo.findByFechaRecepcMuest", query = "SELECT m FROM Muestreo m WHERE m.fechaRecepcMuest = :fechaRecepcMuest"),
    })
public class Muestreo implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_muestreo_id", nullable = false)
    private Integer idMuestreo;
    @Column(name = "s_muestreo_fechinic")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInic;
     @Column(name = "d_muestreo_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "s_muestreo_planmues", length = 225)
    private String planMuestreo;
    @Column(name = "d_muestreo_fechrecepmuest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcMuest;
    @Column(name = "d_muestreo_fechregis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;
   
     @Column(name = "s_muestreo_observacion", length = 100)
    private String observaciones;
    @JoinColumn(name = "fi_muestreo_municipio", referencedColumnName="pi_valor_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Valor idMunicipio;
     @JoinColumn(name = "fi_muestreo_ciudad", referencedColumnName="pi_valor_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Valor idCiudad;
    @JoinColumn(name = "fi_muestreo_punto", referencedColumnName="pi_punto_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Punto idPunto;
     
    
    @JoinColumn(name = "fi_muestreo_proyecto", referencedColumnName = "pi_proyecto_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proyecto idProyecto;
    
    @JoinColumn(name = "fi_muestreo_usuacrea", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SUsuario usuarioCreador;
  
    @JoinColumn(name = "fs_muestreo_usuamodi", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SUsuario usuarioModificador;
    
    @JoinColumn(name = "fs_muestreo_tecnico", referencedColumnName = "ps_tecnico_nombre")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tecnico realizado;
    @OneToMany(mappedBy = "idMuestreo" , fetch = FetchType.LAZY)
    private List<Reporte> reporteList;
    @OneToMany(mappedBy = "idMuestreo", fetch = FetchType.LAZY)
    private List<Muestra> muestraList;

    public Muestreo() {
    }

    public Muestreo(Integer idMuestreo) {
        this.idMuestreo = idMuestreo;
    }

    public Integer getIdMuestreo() {
        return idMuestreo;
    }

    public void setIdMuestreo(Integer idMuestreo) {
        this.idMuestreo = idMuestreo;
    }

    public Date getFechaInic() {
        return fechaInic;
    }

    public void setFechaInic(Date fechaInic) {
        this.fechaInic = fechaInic;
    }

    public String getPlanMuestreo() {
        return planMuestreo;
    }

    public void setPlanMuestreo(String planMuestreo) {
        this.planMuestreo = planMuestreo;
    }

    public Date getFechaRecepcMuest() {
        return fechaRecepcMuest;
    }

    public void setFechaRecepcMuest(Date fechaRecepcMuest) {
        this.fechaRecepcMuest = fechaRecepcMuest;
    }


    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

  

    public Tecnico getRealizado() {
        return realizado;
    }

    public void setRealizado(Tecnico realizado) {
        this.realizado = realizado;
    }

    @XmlTransient
    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    @XmlTransient
    public List<Muestra> getMuestraList() {
        return muestraList;
    }

    public void setMuestraList(List<Muestra> muestraList) {
        this.muestraList = muestraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuestreo != null ? idMuestreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muestreo)) {
            return false;
        }
        Muestreo other = (Muestreo) object;
        if ((this.idMuestreo == null && other.idMuestreo != null) || (this.idMuestreo != null && !this.idMuestreo.equals(other.idMuestreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Muestreo[ idMuestreo=" + idMuestreo + " ]";
    }

  
    

    public Valor getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Valor idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Valor getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Valor idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Punto getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Punto idPunto) {
        this.idPunto = idPunto;
    }

    public SUsuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(SUsuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public SUsuario getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(SUsuario usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }    

 

}
