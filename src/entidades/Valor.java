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
@Table(name = "valor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "valor.findAll", query = "SELECT v FROM Valor v"),
    @NamedQuery(name = "valor.findByIdValor", query = "SELECT v FROM Valor v WHERE v.idValor = :idValor"),
    @NamedQuery(name = "valor.findByNombre", query = "SELECT v FROM Valor v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "valor.findByValor", query = "SELECT v FROM Valor v WHERE v.valor = :valor"),
    @NamedQuery(name = "valor.findByIdParametro", query = "SELECT v FROM Valor v WHERE v.idParametro = :idParametro"),
    @NamedQuery(name = "valor.findByObservaciones", query = "SELECT v FROM Valor v WHERE v.observaciones = :observaciones")})
public class Valor implements Serializable {


    @Column(name = "s_valor_tecnica_muestreo", length = 45)
    private String sValorTecnicaMuestreo;
    @Column(name = "s_valor_esfuerzo_muestreo", length = 45)
    private String sValorEsfuerzoMuestreo;
    @Column(name = "s_valor_filtro", length = 45)
    private String sValorFiltro;
    @Column(name = "s_valor_cromatograma", length = 45)
    private String sValorCromatograma;
    @Column(name = "s_valor_metodo", length = 256)
    private String sValorMetodo;
    @Column(name = "s_valor_unidad", length = 45)
    private String sValorUnidad;

  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_valor_id", nullable = false)
    private Integer idValor;
    @Column(name = "s_valor_nombre", length = 45)
    private String nombre;
    @Column(name = "s_valor_valor", length = 22)
    private String valor;
    @Column(name = "s_valor_absorbancia", length = 45)
    private String absorbancia;
    @Column(name = "s_valor_masa", length = 45)
    private String masa;
    @Column(name = "s_valor_observaciones", length = 255)
    private String observaciones;
    @Column(name = "s_valor_valorreal", length = 45)
    private String valor_real;
     @Column(name = "s_valor_limicuan", length = 45)
    private String s_valor_limicuan;
    @Column(name = "d_valor_fechregi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;
    @Column(name = "d_valor_fechingreso_analista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalista;
     @Column(name = "d_valor_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_modificacion;
    @JoinColumn(name = "fi_valor_tpvalor", referencedColumnName = "pi_tipoval_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoValor idTpValor;
    @JoinColumn(name = "fs_valor_usuamodi", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario usuario_modificador;
    @JoinColumn(name = "fi_valor_muestraanal", referencedColumnName = "pi_muestraanal_id")
    @ManyToOne
    private MuestraAnalisis idParametro;
    
    @OneToMany(mappedBy = "idValorPadre")
    private List<Valor> valorList;
    @JoinColumn(name = "fi_valor_valorpadre", referencedColumnName = "pi_valor_id")
    @ManyToOne
    private Valor idValorPadre;

    public Valor() {
    }

    public Valor(Integer idValor) {
        this.idValor = idValor;
    }

    public Integer getIdValor() {
        return idValor;
    }

    public void setIdValor(Integer idValor) {
        this.idValor = idValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoValor getIdTpValor() {
        return idTpValor;
    }

    public void setIdTpValor(TipoValor idTpValor) {
        this.idTpValor = idTpValor;
    }

    public MuestraAnalisis getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(MuestraAnalisis idParametro) {
        this.idParametro = idParametro;
    }

    public String getAbsorbancia() {
        return absorbancia;
    }

    public void setAbsorbancia(String absorbancia) {
        this.absorbancia = absorbancia;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }
    
    
    @XmlTransient
    public List<Valor> getValorList() {
        return valorList;
    }

    public void setValorList(List<Valor> valorList) {
        this.valorList = valorList;
    }

    public Valor getIdValorPadre() {
        return idValorPadre;
    }

    public void setIdValorPadre(Valor idValorPadre) {
        this.idValorPadre = idValorPadre;
    }

    public SUsuario getUsuario_modificador() {
        return usuario_modificador;
    }

    public void setUsuario_modificador(SUsuario usuario_modificador) {
        this.usuario_modificador = usuario_modificador;
    }

    public String getS_valor_limicuan() {
        return s_valor_limicuan;
    }

    public void setS_valor_limicuan(String s_valor_limicuan) {
        this.s_valor_limicuan = s_valor_limicuan;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValor != null ? idValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valor)) {
            return false;
        }
        Valor other = (Valor) object;
        if ((this.idValor == null && other.idValor != null) || (this.idValor != null && !this.idValor.equals(other.idValor))) {
            return false;
        }
        return true;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getValor_real() {
        return valor_real;
    }

    public void setValor_real(String valor_real) {
        this.valor_real = valor_real;
    }
    
    
    
    @Override
    public String toString() {
        return "Parametro:" + nombre ;
    }

    public String getSValorMetodo() {
        return sValorMetodo;
    }

    public void setSValorMetodo(String sValorMetodo) {
        this.sValorMetodo = sValorMetodo;
    }

    public String getSValorUnidad() {
        return sValorUnidad;
    }

    public void setSValorUnidad(String sValorUnidad) {
        this.sValorUnidad = sValorUnidad;
    }

    public Date getFechaAnalista() {
        return fechaAnalista;
    }

    public void setFechaAnalista(Date fechaAnalista) {
        this.fechaAnalista = fechaAnalista;
    }

    public String getSValorTecnicaMuestreo() {
        return sValorTecnicaMuestreo;
    }

    public void setSValorTecnicaMuestreo(String sValorTecnicaMuestreo) {
        this.sValorTecnicaMuestreo = sValorTecnicaMuestreo;
    }

    public String getSValorEsfuerzoMuestreo() {
        return sValorEsfuerzoMuestreo;
    }

    public void setSValorEsfuerzoMuestreo(String sValorEsfuerzoMuestreo) {
        this.sValorEsfuerzoMuestreo = sValorEsfuerzoMuestreo;
    }

    public String getSValorFiltro() {
        return sValorFiltro;
    }

    public void setSValorFiltro(String sValorFiltro) {
        this.sValorFiltro = sValorFiltro;
    }

    public String getSValorCromatograma() {
        return sValorCromatograma;
    }

    public void setSValorCromatograma(String sValorCromatograma) {
        this.sValorCromatograma = sValorCromatograma;
    }




}
