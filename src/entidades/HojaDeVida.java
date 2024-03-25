/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "hoja_de_vida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaDeVida.findAll", query = "SELECT h FROM HojaDeVida h")
    , @NamedQuery(name = "HojaDeVida.findByPiHojaVidaConsecutivo", query = "SELECT h FROM HojaDeVida h WHERE h.hojaDeVidaPK.piHojaVidaConsecutivo = :piHojaVidaConsecutivo")
    , @NamedQuery(name = "HojaDeVida.findByPsHojaVidaMatriz", query = "SELECT h FROM HojaDeVida h WHERE h.hojaDeVidaPK.psHojaVidaMatriz = :psHojaVidaMatriz")
    , @NamedQuery(name = "HojaDeVida.findByPsHojaVidaFuncion", query = "SELECT h FROM HojaDeVida h WHERE h.hojaDeVidaPK.psHojaVidaFuncion = :psHojaVidaFuncion")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaNom", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaNom = :sHojaDeVidaNom")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaFabricante", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaFabricante = :sHojaDeVidaFabricante")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaProveedor", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaProveedor = :sHojaDeVidaProveedor")
    , @NamedQuery(name = "HojaDeVida.findByDHojaDeVidaFechAdquicision", query = "SELECT h FROM HojaDeVida h WHERE h.dHojaDeVidaFechAdquicision = :dHojaDeVidaFechAdquicision")
    , @NamedQuery(name = "HojaDeVida.findByIHojaDeVidaVidaUtil", query = "SELECT h FROM HojaDeVida h WHERE h.iHojaDeVidaVidaUtil = :iHojaDeVidaVidaUtil")
    , @NamedQuery(name = "HojaDeVida.findByIHojaDeVidaCantidad", query = "SELECT h FROM HojaDeVida h WHERE h.iHojaDeVidaCantidad = :iHojaDeVidaCantidad")
    , @NamedQuery(name = "HojaDeVida.findByCHojaDeVidaEstado", query = "SELECT h FROM HojaDeVida h WHERE h.cHojaDeVidaEstado = :cHojaDeVidaEstado")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaUbicacion", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaUbicacion = :sHojaDeVidaUbicacion")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaArea", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaArea = :sHojaDeVidaArea")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaDescripcionUso", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaDescripcionUso = :sHojaDeVidaDescripcionUso")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaCondicionesAlmace", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaCondicionesAlmace = :sHojaDeVidaCondicionesAlmace")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaDescripcionMant", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaDescripcionMant = :sHojaDeVidaDescripcionMant")
    , @NamedQuery(name = "HojaDeVida.findBySHojaDeVidaDescripcionCant", query = "SELECT h FROM HojaDeVida h WHERE h.sHojaDeVidaDescripcionCant = :sHojaDeVidaDescripcionCant")})
public class HojaDeVida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HojaDeVidaPK hojaDeVidaPK;
    @Basic(optional = false)
    @Column(name = "s_hoja_de_vida_nom", nullable = false, length = 45)
    private String sHojaDeVidaNom;
    @Column(name = "s_hoja_de_vida_fabricante", length = 45)
    private String sHojaDeVidaFabricante;
    @Column(name = "s_hoja_de_vida_proveedor", length = 45)
    private String sHojaDeVidaProveedor;
    @Basic(optional = false)
    @Column(name = "d_hoja_de_vida_fech_adquicision", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dHojaDeVidaFechAdquicision;
    @Basic(optional = false)
    @Column(name = "i_hoja_de_vida_vida_util", nullable = false)
    private int iHojaDeVidaVidaUtil;
    @Basic(optional = false)
    @Column(name = "i_hoja_de_vida_cantidad", nullable = false)
    private int iHojaDeVidaCantidad;
    @Basic(optional = false)
    @Column(name = "c_hoja_de_vida_estado", nullable = false, length = 30)
    private String cHojaDeVidaEstado;
    @Column(name = "s_hoja_de_vida_ubicacion", length = 45)
    private String sHojaDeVidaUbicacion;
    @Basic(optional = false)
    @Column(name = "s_hoja_de_vida_area", nullable = false, length = 45)
    private String sHojaDeVidaArea;
    @Column(name = "s_hoja_de_vida_descripcion_uso", length = 45)
    private String sHojaDeVidaDescripcionUso;
    @Column(name = "s_hoja_de_vida_condiciones_almace", length = 45)
    private String sHojaDeVidaCondicionesAlmace;
    @Basic(optional = false)
    @Column(name = "s_hoja_de_vida_descripcion_mant", nullable = false, length = 45)
    private String sHojaDeVidaDescripcionMant;
    @Lob
    @Column(name = "b_hoja_de_vida_imagen")
    private byte[] bHojaDeVidaImagen;
    @Basic(optional = false)
    @Column(name = "s_hoja_de_vida_descripcion_cant", nullable = false, length = 45)
    private String sHojaDeVidaDescripcionCant;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hojaDeVida")
    private Insumos insumos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hojaDeVida")
    private Vidrieria vidrieria;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hojaDeVida")
    private Equipo equipo;
    @JoinColumn(name = "s_hoja_de_vida_objeto_id", referencedColumnName = "pi_objeto", nullable = false)
    @ManyToOne(optional = false)
    private Objeto sHojaDeVidaObjetoId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hojaDeVida")
    private Vehiculo vehiculo;

    public HojaDeVida() {
    }

    public HojaDeVida(HojaDeVidaPK hojaDeVidaPK) {
        this.hojaDeVidaPK = hojaDeVidaPK;
    }

    public HojaDeVida(HojaDeVidaPK hojaDeVidaPK, String sHojaDeVidaNom, Date dHojaDeVidaFechAdquicision, int iHojaDeVidaVidaUtil, int iHojaDeVidaCantidad, String cHojaDeVidaEstado, String sHojaDeVidaArea, String sHojaDeVidaDescripcionMant, String sHojaDeVidaDescripcionCant) {
        this.hojaDeVidaPK = hojaDeVidaPK;
        this.sHojaDeVidaNom = sHojaDeVidaNom;
        this.dHojaDeVidaFechAdquicision = dHojaDeVidaFechAdquicision;
        this.iHojaDeVidaVidaUtil = iHojaDeVidaVidaUtil;
        this.iHojaDeVidaCantidad = iHojaDeVidaCantidad;
        this.cHojaDeVidaEstado = cHojaDeVidaEstado;
        this.sHojaDeVidaArea = sHojaDeVidaArea;
        this.sHojaDeVidaDescripcionMant = sHojaDeVidaDescripcionMant;
        this.sHojaDeVidaDescripcionCant = sHojaDeVidaDescripcionCant;
    }

    public HojaDeVida(int piHojaVidaConsecutivo, String psHojaVidaMatriz, String psHojaVidaFuncion) {
        this.hojaDeVidaPK = new HojaDeVidaPK(piHojaVidaConsecutivo, psHojaVidaMatriz, psHojaVidaFuncion);
    }

    public HojaDeVidaPK getHojaDeVidaPK() {
        return hojaDeVidaPK;
    }

    public void setHojaDeVidaPK(HojaDeVidaPK hojaDeVidaPK) {
        this.hojaDeVidaPK = hojaDeVidaPK;
    }

    public String getSHojaDeVidaNom() {
        return sHojaDeVidaNom;
    }

    public void setSHojaDeVidaNom(String sHojaDeVidaNom) {
        this.sHojaDeVidaNom = sHojaDeVidaNom;
    }

    public String getSHojaDeVidaFabricante() {
        return sHojaDeVidaFabricante;
    }

    public void setSHojaDeVidaFabricante(String sHojaDeVidaFabricante) {
        this.sHojaDeVidaFabricante = sHojaDeVidaFabricante;
    }

    public String getSHojaDeVidaProveedor() {
        return sHojaDeVidaProveedor;
    }

    public void setSHojaDeVidaProveedor(String sHojaDeVidaProveedor) {
        this.sHojaDeVidaProveedor = sHojaDeVidaProveedor;
    }

    public Date getDHojaDeVidaFechAdquicision() {
        return dHojaDeVidaFechAdquicision;
    }

    public void setDHojaDeVidaFechAdquicision(Date dHojaDeVidaFechAdquicision) {
        this.dHojaDeVidaFechAdquicision = dHojaDeVidaFechAdquicision;
    }

    public int getIHojaDeVidaVidaUtil() {
        return iHojaDeVidaVidaUtil;
    }

    public void setIHojaDeVidaVidaUtil(int iHojaDeVidaVidaUtil) {
        this.iHojaDeVidaVidaUtil = iHojaDeVidaVidaUtil;
    }

    public int getIHojaDeVidaCantidad() {
        return iHojaDeVidaCantidad;
    }

    public void setIHojaDeVidaCantidad(int iHojaDeVidaCantidad) {
        this.iHojaDeVidaCantidad = iHojaDeVidaCantidad;
    }

    public String getCHojaDeVidaEstado() {
        return cHojaDeVidaEstado;
    }

    public void setCHojaDeVidaEstado(String cHojaDeVidaEstado) {
        this.cHojaDeVidaEstado = cHojaDeVidaEstado;
    }

    public String getSHojaDeVidaUbicacion() {
        return sHojaDeVidaUbicacion;
    }

    public void setSHojaDeVidaUbicacion(String sHojaDeVidaUbicacion) {
        this.sHojaDeVidaUbicacion = sHojaDeVidaUbicacion;
    }

    public String getSHojaDeVidaArea() {
        return sHojaDeVidaArea;
    }

    public void setSHojaDeVidaArea(String sHojaDeVidaArea) {
        this.sHojaDeVidaArea = sHojaDeVidaArea;
    }

    public String getSHojaDeVidaDescripcionUso() {
        return sHojaDeVidaDescripcionUso;
    }

    public void setSHojaDeVidaDescripcionUso(String sHojaDeVidaDescripcionUso) {
        this.sHojaDeVidaDescripcionUso = sHojaDeVidaDescripcionUso;
    }

    public String getSHojaDeVidaCondicionesAlmace() {
        return sHojaDeVidaCondicionesAlmace;
    }

    public void setSHojaDeVidaCondicionesAlmace(String sHojaDeVidaCondicionesAlmace) {
        this.sHojaDeVidaCondicionesAlmace = sHojaDeVidaCondicionesAlmace;
    }

    public String getSHojaDeVidaDescripcionMant() {
        return sHojaDeVidaDescripcionMant;
    }

    public void setSHojaDeVidaDescripcionMant(String sHojaDeVidaDescripcionMant) {
        this.sHojaDeVidaDescripcionMant = sHojaDeVidaDescripcionMant;
    }

    public byte[] getBHojaDeVidaImagen() {
        return bHojaDeVidaImagen;
    }

    public void setBHojaDeVidaImagen(byte[] bHojaDeVidaImagen) {
        this.bHojaDeVidaImagen = bHojaDeVidaImagen;
    }

    public String getSHojaDeVidaDescripcionCant() {
        return sHojaDeVidaDescripcionCant;
    }

    public void setSHojaDeVidaDescripcionCant(String sHojaDeVidaDescripcionCant) {
        this.sHojaDeVidaDescripcionCant = sHojaDeVidaDescripcionCant;
    }

    public Insumos getInsumos() {
        return insumos;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    public Vidrieria getVidreria() {
        return vidrieria;
    }

    public void setVidreria(Vidrieria vidrieria) {
        this.vidrieria = vidrieria;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Objeto getSHojaDeVidaObjetoId() {
        return sHojaDeVidaObjetoId;
    }

    public void setSHojaDeVidaObjetoId(Objeto sHojaDeVidaObjetoId) {
        this.sHojaDeVidaObjetoId = sHojaDeVidaObjetoId;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hojaDeVidaPK != null ? hojaDeVidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaDeVida)) {
            return false;
        }
        HojaDeVida other = (HojaDeVida) object;
        if ((this.hojaDeVidaPK == null && other.hojaDeVidaPK != null) || (this.hojaDeVidaPK != null && !this.hojaDeVidaPK.equals(other.hojaDeVidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HojaDeVida[ hojaDeVidaPK=" + hojaDeVidaPK + " ]";
    }
    
}
