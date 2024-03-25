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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "muestra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestra.findAll", query = "SELECT m FROM Muestra m"),
    @NamedQuery(name = "Muestra.findByIdmuestra", query = "SELECT m FROM Muestra m WHERE m.idmuestra = :idmuestra"),
    @NamedQuery(name = "Muestra.findByIdMatriz", query = "SELECT m FROM Muestra m WHERE m.idMatriz = :idMatriz"),
    @NamedQuery(name = "Muestra.findByFechaDescarte", query = "SELECT m FROM Muestra m WHERE m.fechaDescarte = :fechaDescarte"),
    @NamedQuery(name = "Muestra.findByObservaciones", query = "SELECT m FROM Muestra m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "Muestra.findByFechaAnalisis", query = "SELECT m FROM Muestra m WHERE m.fechaAnalisis = :fechaAnalisis"),
    @NamedQuery(name = "Muestra.findByDescripcion", query = "SELECT m FROM Muestra m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Muestra.findByFechaTomaMuestra", query = "SELECT m FROM Muestra m WHERE m.fechaTomaMuestra = :fechaTomaMuestra")})
public class Muestra implements Serializable {





    @OneToMany(mappedBy = "fkiRestultadoCalidadMuestraMuestra")
    private List<ResultadoCalidadMuestra> resultadoCalidadMuestraList;
    @OneToMany(mappedBy = "fkResultadoCalidadMuestraFinal")
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_muestra_id", nullable = false, length = 22)
    private int idmuestra;
    @Column(name = "fi_muestra_matriz")
    private Integer idMatriz;
    @Column(name = "d_muestra_fechdesca")
    @Temporal(TemporalType.DATE)
    private Date fechaDescarte;
    @Column(name = "s_muestra_observaciones", length = 45)
    private String observaciones;
    @Column(name = "d_muestra_fechanal")
    @Temporal(TemporalType.DATE)
    private Date fechaAnalisis;
     @Column(name = "d_muestra_fechcreac")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
       @Column(name = "d_muestra_fechmodi")
    @Temporal(TemporalType.DATE)
    private Date fecha_modificacion;
    @Column(name = "s_muestra_descripcion", length = 45)
    private String descripcion;
    @Column(name = "fi_muestra_campo", length = 45)
    private String idCampo;
    @Column(name = "d_muestra_fechtomamuest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTomaMuestra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMuestra")
    private List<MuestraAnalisis> muestraAnalisisList;
    
    @JoinColumns({
        @JoinColumn(name = "fi_muestra_cliente", referencedColumnName = "ps_cliente_nit"),
        @JoinColumn(name="fi_muestra_cliente", referencedColumnName="s_cliente_nombre")
    })
     @ManyToOne
    private Cliente idCliente;
    
    @JoinColumn(name = "fi_muestra_tecnico", referencedColumnName = "ps_tecnico_nombre")
    @ManyToOne
    private Tecnico idResponsable;
    @JoinColumn(name = "fs_muestra_usuresprecep", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario responsableRecepcion;
    @JoinColumn(name = "fi_muestra_tipomuestreo", referencedColumnName = "pi_tipomuestreo_id")
    @ManyToOne
    private TipoMuestreo idTpMuestra;
    @JoinColumn(name = "fi_muestra_muestreo", referencedColumnName = "pi_muestreo_id")
    @ManyToOne
    private Muestreo idMuestreo;
    @JoinColumn(name = "fi_muestra_proyecto", referencedColumnName = "pi_proyecto_id")
    @ManyToOne
    private Proyecto proyecto;
    @JoinColumn(name = "fs_muestra_reporte", referencedColumnName = "pi_reporte_id")
    @ManyToOne
    private Reporte reporte;
    @JoinColumn(name = "fs_muestra_usucreac", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuario_creacion;
    @JoinColumn(name = "fs_muestra_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuario_modificador;
    @JoinColumn(name = "fi_muestra_tipomuest", referencedColumnName = "pi_tipomuestra_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoMuestra tipoMuestra;
    
    
    public Muestra() {
    }

    public Muestra(int idmuestra) {
        this.idmuestra = idmuestra;
    }

    public int getIdmuestra() {
        return idmuestra;
    }

    public void setIdmuestra(int idmuestra) {
        this.idmuestra = idmuestra;
    }


    public Integer getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(Integer idMatriz) {
        this.idMatriz = idMatriz;
    }

    public Date getFechaDescarte() {
        return fechaDescarte;
    }

    public void setFechaDescarte(Date fechaDescarte) {
        this.fechaDescarte = fechaDescarte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaTomaMuestra() {
        return fechaTomaMuestra;
    }

    public void setFechaTomaMuestra(Date fechaTomaMuestra) {
        this.fechaTomaMuestra = fechaTomaMuestra;
    }

    @XmlTransient
    public List<MuestraAnalisis> getMuestraAnalisisList() {
        return muestraAnalisisList;
    }

    public void setMuestraAnalisisList(List<MuestraAnalisis> muestraAnalisisList) {
        this.muestraAnalisisList = muestraAnalisisList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Tecnico getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Tecnico idResponsable) {
        this.idResponsable = idResponsable;
    }

    public SUsuario getResponsableRecepcion() {
        return responsableRecepcion;
    }

    public void setResponsableRecepcion(SUsuario responsableRecepcion) {
        this.responsableRecepcion = responsableRecepcion;
    }

    public TipoMuestreo getTipoMuestreo() {
        return idTpMuestra;
    }

    public void setTipoMuestreo(TipoMuestreo idTpMuestra) {
        this.idTpMuestra = idTpMuestra;
    }

    public Muestreo getIdMuestreo() {
        return idMuestreo;
    }

    public void setIdMuestreo(Muestreo idMuestreo) {
        this.idMuestreo = idMuestreo;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

  

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        //hash += (idmuestra != null ? idmuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muestra)) {
            return false;
        }
        Muestra other = (Muestra) object;
       /* if ((this.idmuestra == null && other.idmuestra != null) || (this.idmuestra != null && !this.idmuestra.equals(other.idmuestra))) {
            return false;
        }*/
        return true;
    }

    @Override
    public String toString() {
        return "Números de muestra " + idmuestra ;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
     public SUsuario getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(SUsuario usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public SUsuario getUsuario_modificador() {
        return usuario_modificador;
    }

    public void setUsuario_modificador(SUsuario usuario_modificador) {
        this.usuario_modificador = usuario_modificador;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public TipoMuestra getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(TipoMuestra tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(String idCampo) {
        this.idCampo = idCampo;
    }

    public TipoMuestreo getIdTpMuestra() {
        return idTpMuestra;
    }

    public void setIdTpMuestra(TipoMuestreo idTpMuestra) {
        this.idTpMuestra = idTpMuestra;
    } 

    @XmlTransient
    public List<ResultadoCalidadMuestra> getResultadoCalidadMuestraList() {
        return resultadoCalidadMuestraList;
    }

    public void setResultadoCalidadMuestraList(List<ResultadoCalidadMuestra> resultadoCalidadMuestraList) {
        this.resultadoCalidadMuestraList = resultadoCalidadMuestraList;
    }

   
}
