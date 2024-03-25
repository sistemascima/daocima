/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "hoja_vida", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaVida.findAll", query = "SELECT h FROM HojaVida h")
    , @NamedQuery(name = "HojaVida.findByPfsHojaVidaMatriz", query = "SELECT h FROM HojaVida h WHERE h.hojaVidaPK.pfsHojaVidaMatriz = :pfsHojaVidaMatriz")
    , @NamedQuery(name = "HojaVida.findByPfsHojaVidaConsecutivo", query = "SELECT h FROM HojaVida h WHERE h.hojaVidaPK.pfsHojaVidaConsecutivo = :pfsHojaVidaConsecutivo")
    , @NamedQuery(name = "HojaVida.findByPfsHojaVidaFuncion", query = "SELECT h FROM HojaVida h WHERE h.hojaVidaPK.pfsHojaVidaFuncion = :pfsHojaVidaFuncion")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaNombre", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaNombre = :sHojaVidaNombre")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaMarca", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaMarca = :sHojaVidaMarca")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaModelo", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaModelo = :sHojaVidaModelo")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaReferencia", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaReferencia = :sHojaVidaReferencia")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaSerial", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaSerial = :sHojaVidaSerial")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaArea", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaArea = :sHojaVidaArea")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaFabricante", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaFabricante = :sHojaVidaFabricante")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaProveedor", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaProveedor = :sHojaVidaProveedor")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaAccesorio", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaAccesorio = :sHojaVidaAccesorio")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaFechComp", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaFechComp = :dHojaVidaFechComp")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaFechPuesServ", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaFechPuesServ = :dHojaVidaFechPuesServ")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaFechPerMant", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaFechPerMant = :dHojaVidaFechPerMant")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaFechPerCalib", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaFechPerCalib = :dHojaVidaFechPerCalib")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaFechVidaUtil", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaFechVidaUtil = :dHojaVidaFechVidaUtil")
    , @NamedQuery(name = "HojaVida.findByBHojaVidaManOperacion", query = "SELECT h FROM HojaVida h WHERE h.bHojaVidaManOperacion = :bHojaVidaManOperacion")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaUbicacion", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaUbicacion = :sHojaVidaUbicacion")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaCodigo", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaCodigo = :sHojaVidaCodigo")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaAreaMatrUso", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaAreaMatrUso = :sHojaVidaAreaMatrUso")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaVariable", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaVariable = :sHojaVidaVariable")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaRango", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaRango = :sHojaVidaRango")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaResolucion", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaResolucion = :sHojaVidaResolucion")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaExactitud", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaExactitud = :sHojaVidaExactitud")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaDescripcion", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaDescripcion = :sHojaVidaDescripcion")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaCondiciones", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaCondiciones = :sHojaVidaCondiciones")
    , @NamedQuery(name = "HojaVida.findBySHojaVidaDescMant", query = "SELECT h FROM HojaVida h WHERE h.sHojaVidaDescMant = :sHojaVidaDescMant")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaCreacion", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaCreacion = :dHojaVidaCreacion")
    , @NamedQuery(name = "HojaVida.findByDHojaVidaUltimodi", query = "SELECT h FROM HojaVida h WHERE h.dHojaVidaUltimodi = :dHojaVidaUltimodi")})
public class HojaVida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HojaVidaPK hojaVidaPK;
    @Column(name = "s_hoja_vida_nombre", length = 100)
    private String sHojaVidaNombre;
    @Column(name = "s_hoja_vida_marca", length = 100)
    private String sHojaVidaMarca;
    @Column(name = "s_hoja_vida_modelo", length = 100)
    private String sHojaVidaModelo;
    @Column(name = "s_hoja_vida_referencia", length = 100)
    private String sHojaVidaReferencia;
    @Column(name = "s_hoja_vida_serial", length = 100)
    private String sHojaVidaSerial;
    @Column(name = "s_hoja_vida_area", length = 100)
    private String sHojaVidaArea;
    @Column(name = "s_hoja_vida_fabricante", length = 100)
    private String sHojaVidaFabricante;
    @Column(name = "s_hoja_vida_proveedor", length = 100)
    private String sHojaVidaProveedor;
    @Column(name = "s_hoja_vida_accesorio", length = 200)
    private String sHojaVidaAccesorio;
    @Column(name = "d_hoja_vida_fech_comp")
    @Temporal(TemporalType.DATE)
    private Date dHojaVidaFechComp;
    @Column(name = "d_hoja_vida_fech_pues_serv")
    @Temporal(TemporalType.DATE)
    private Date dHojaVidaFechPuesServ;
    @Column(name = "d_hoja_vida_fech_per_mant", length = 100)
    private String dHojaVidaFechPerMant;
    @Column(name = "d_hoja_vida_fech_per_calib", length = 100)
    private String dHojaVidaFechPerCalib;
    @Column(name = "d_hoja_vida_fech_vida_util", length = 100)
    private String dHojaVidaFechVidaUtil;
    @Column(name = "b_hoja_vida_man_operacion")
    private Short bHojaVidaManOperacion;
    @Column(name = "s_hoja_vida_ubicacion", length = 100)
    private String sHojaVidaUbicacion;
    @Column(name = "s_hoja_vida_codigo", length = 100)
    private String sHojaVidaCodigo;
    @Column(name = "s_hoja_vida_area_matr_uso", length = 100)
    private String sHojaVidaAreaMatrUso;
    @Column(name = "s_hoja_vida_variable", length = 100)
    private String sHojaVidaVariable;
    @Column(name = "s_hoja_vida_rango", length = 100)
    private String sHojaVidaRango;
    @Column(name = "s_hoja_vida_resolucion", length = 100)
    private String sHojaVidaResolucion;
    @Column(name = "s_hoja_vida_exactitud", length = 100)
    private String sHojaVidaExactitud;
    @Column(name = "s_hoja_vida_descripcion", length = 1000)
    private String sHojaVidaDescripcion;
    @Column(name = "s_hoja_vida_condiciones", length = 1000)
    private String sHojaVidaCondiciones;
    @Column(name = "s_hoja_vida_desc_mant", length = 1000)
    private String sHojaVidaDescMant;
    @Column(name = "d_hoja_vida_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dHojaVidaCreacion;
    @Column(name = "d_hoja_vida_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dHojaVidaUltimodi;
    @OneToMany(mappedBy = "hojaVida")
    private List<EquipamientoInterno> equipamientoInternoList;
    @JoinColumn(name = "fs_hoja_vida_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsHojaVidaUsuacrea;
    @JoinColumn(name = "fs_hoja_vida_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsHojaVidaUsuamodi;

    public HojaVida() {
    }

    public HojaVida(HojaVidaPK hojaVidaPK) {
        this.hojaVidaPK = hojaVidaPK;
    }

    public HojaVida(String pfsHojaVidaMatriz, int pfsHojaVidaConsecutivo, String pfsHojaVidaFuncion) {
        this.hojaVidaPK = new HojaVidaPK(pfsHojaVidaMatriz, pfsHojaVidaConsecutivo, pfsHojaVidaFuncion);
    }

    public HojaVidaPK getHojaVidaPK() {
        return hojaVidaPK;
    }

    public void setHojaVidaPK(HojaVidaPK hojaVidaPK) {
        this.hojaVidaPK = hojaVidaPK;
    }

    public String getSHojaVidaNombre() {
        return sHojaVidaNombre;
    }

    public void setSHojaVidaNombre(String sHojaVidaNombre) {
        this.sHojaVidaNombre = sHojaVidaNombre;
    }

    public String getSHojaVidaMarca() {
        return sHojaVidaMarca;
    }

    public void setSHojaVidaMarca(String sHojaVidaMarca) {
        this.sHojaVidaMarca = sHojaVidaMarca;
    }

    public String getSHojaVidaModelo() {
        return sHojaVidaModelo;
    }

    public void setSHojaVidaModelo(String sHojaVidaModelo) {
        this.sHojaVidaModelo = sHojaVidaModelo;
    }

    public String getSHojaVidaReferencia() {
        return sHojaVidaReferencia;
    }

    public void setSHojaVidaReferencia(String sHojaVidaReferencia) {
        this.sHojaVidaReferencia = sHojaVidaReferencia;
    }

    public String getSHojaVidaSerial() {
        return sHojaVidaSerial;
    }

    public void setSHojaVidaSerial(String sHojaVidaSerial) {
        this.sHojaVidaSerial = sHojaVidaSerial;
    }

    public String getSHojaVidaArea() {
        return sHojaVidaArea;
    }

    public void setSHojaVidaArea(String sHojaVidaArea) {
        this.sHojaVidaArea = sHojaVidaArea;
    }

    public String getSHojaVidaFabricante() {
        return sHojaVidaFabricante;
    }

    public void setSHojaVidaFabricante(String sHojaVidaFabricante) {
        this.sHojaVidaFabricante = sHojaVidaFabricante;
    }

    public String getSHojaVidaProveedor() {
        return sHojaVidaProveedor;
    }

    public void setSHojaVidaProveedor(String sHojaVidaProveedor) {
        this.sHojaVidaProveedor = sHojaVidaProveedor;
    }

    public String getSHojaVidaAccesorio() {
        return sHojaVidaAccesorio;
    }

    public void setSHojaVidaAccesorio(String sHojaVidaAccesorio) {
        this.sHojaVidaAccesorio = sHojaVidaAccesorio;
    }

    public Date getDHojaVidaFechComp() {
        return dHojaVidaFechComp;
    }

    public void setDHojaVidaFechComp(Date dHojaVidaFechComp) {
        this.dHojaVidaFechComp = dHojaVidaFechComp;
    }

    public Date getDHojaVidaFechPuesServ() {
        return dHojaVidaFechPuesServ;
    }

    public void setDHojaVidaFechPuesServ(Date dHojaVidaFechPuesServ) {
        this.dHojaVidaFechPuesServ = dHojaVidaFechPuesServ;
    }

    public String getDHojaVidaFechPerMant() {
        return dHojaVidaFechPerMant;
    }

    public void setDHojaVidaFechPerMant(String dHojaVidaFechPerMant) {
        this.dHojaVidaFechPerMant = dHojaVidaFechPerMant;
    }

    public String getDHojaVidaFechPerCalib() {
        return dHojaVidaFechPerCalib;
    }

    public void setDHojaVidaFechPerCalib(String dHojaVidaFechPerCalib) {
        this.dHojaVidaFechPerCalib = dHojaVidaFechPerCalib;
    }

    public String getDHojaVidaFechVidaUtil() {
        return dHojaVidaFechVidaUtil;
    }

    public void setDHojaVidaFechVidaUtil(String dHojaVidaFechVidaUtil) {
        this.dHojaVidaFechVidaUtil = dHojaVidaFechVidaUtil;
    }

    public Short getBHojaVidaManOperacion() {
        return bHojaVidaManOperacion;
    }

    public void setBHojaVidaManOperacion(Short bHojaVidaManOperacion) {
        this.bHojaVidaManOperacion = bHojaVidaManOperacion;
    }

    public String getSHojaVidaUbicacion() {
        return sHojaVidaUbicacion;
    }

    public void setSHojaVidaUbicacion(String sHojaVidaUbicacion) {
        this.sHojaVidaUbicacion = sHojaVidaUbicacion;
    }

    public String getSHojaVidaCodigo() {
        return sHojaVidaCodigo;
    }

    public void setSHojaVidaCodigo(String sHojaVidaCodigo) {
        this.sHojaVidaCodigo = sHojaVidaCodigo;
    }

    public String getSHojaVidaAreaMatrUso() {
        return sHojaVidaAreaMatrUso;
    }

    public void setSHojaVidaAreaMatrUso(String sHojaVidaAreaMatrUso) {
        this.sHojaVidaAreaMatrUso = sHojaVidaAreaMatrUso;
    }

    public String getSHojaVidaVariable() {
        return sHojaVidaVariable;
    }

    public void setSHojaVidaVariable(String sHojaVidaVariable) {
        this.sHojaVidaVariable = sHojaVidaVariable;
    }

    public String getSHojaVidaRango() {
        return sHojaVidaRango;
    }

    public void setSHojaVidaRango(String sHojaVidaRango) {
        this.sHojaVidaRango = sHojaVidaRango;
    }

    public String getSHojaVidaResolucion() {
        return sHojaVidaResolucion;
    }

    public void setSHojaVidaResolucion(String sHojaVidaResolucion) {
        this.sHojaVidaResolucion = sHojaVidaResolucion;
    }

    public String getSHojaVidaExactitud() {
        return sHojaVidaExactitud;
    }

    public void setSHojaVidaExactitud(String sHojaVidaExactitud) {
        this.sHojaVidaExactitud = sHojaVidaExactitud;
    }

    public String getSHojaVidaDescripcion() {
        return sHojaVidaDescripcion;
    }

    public void setSHojaVidaDescripcion(String sHojaVidaDescripcion) {
        this.sHojaVidaDescripcion = sHojaVidaDescripcion;
    }

    public String getSHojaVidaCondiciones() {
        return sHojaVidaCondiciones;
    }

    public void setSHojaVidaCondiciones(String sHojaVidaCondiciones) {
        this.sHojaVidaCondiciones = sHojaVidaCondiciones;
    }

    public String getSHojaVidaDescMant() {
        return sHojaVidaDescMant;
    }

    public void setSHojaVidaDescMant(String sHojaVidaDescMant) {
        this.sHojaVidaDescMant = sHojaVidaDescMant;
    }

    public Date getDHojaVidaCreacion() {
        return dHojaVidaCreacion;
    }

    public void setDHojaVidaCreacion(Date dHojaVidaCreacion) {
        this.dHojaVidaCreacion = dHojaVidaCreacion;
    }

    public Date getDHojaVidaUltimodi() {
        return dHojaVidaUltimodi;
    }

    public void setDHojaVidaUltimodi(Date dHojaVidaUltimodi) {
        this.dHojaVidaUltimodi = dHojaVidaUltimodi;
    }

    @XmlTransient
    public List<EquipamientoInterno> getEquipamientoInternoList() {
        return equipamientoInternoList;
    }

    public void setEquipamientoInternoList(List<EquipamientoInterno> equipamientoInternoList) {
        this.equipamientoInternoList = equipamientoInternoList;
    }

    public SUsuario getFsHojaVidaUsuacrea() {
        return fsHojaVidaUsuacrea;
    }

    public void setFsHojaVidaUsuacrea(SUsuario fsHojaVidaUsuacrea) {
        this.fsHojaVidaUsuacrea = fsHojaVidaUsuacrea;
    }

    public SUsuario getFsHojaVidaUsuamodi() {
        return fsHojaVidaUsuamodi;
    }

    public void setFsHojaVidaUsuamodi(SUsuario fsHojaVidaUsuamodi) {
        this.fsHojaVidaUsuamodi = fsHojaVidaUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hojaVidaPK != null ? hojaVidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaVida)) {
            return false;
        }
        HojaVida other = (HojaVida) object;
        if ((this.hojaVidaPK == null && other.hojaVidaPK != null) || (this.hojaVidaPK != null && !this.hojaVidaPK.equals(other.hojaVidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HojaVida[ hojaVidaPK=" + hojaVidaPK + " ]";
    }
    
}
