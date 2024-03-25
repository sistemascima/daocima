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
 * @author SISTEMAS
 */
@Entity
@Table(name = "dato_hidrobiologia", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoHidrobiologia.findAll", query = "SELECT d FROM DatoHidrobiologia d")
    , @NamedQuery(name = "DatoHidrobiologia.findByPiDatoHidro", query = "SELECT d FROM DatoHidrobiologia d WHERE d.piDatoHidro = :piDatoHidro")
    , @NamedQuery(name = "DatoHidrobiologia.findBySDatoHidroGenero", query = "SELECT d FROM DatoHidrobiologia d WHERE d.sDatoHidroGenero = :sDatoHidroGenero")
    , @NamedQuery(name = "DatoHidrobiologia.findBySDatoHidroDivisionPhylum", query = "SELECT d FROM DatoHidrobiologia d WHERE d.sDatoHidroDivisionPhylum = :sDatoHidroDivisionPhylum")
    , @NamedQuery(name = "DatoHidrobiologia.findBySDatoHidroClase", query = "SELECT d FROM DatoHidrobiologia d WHERE d.sDatoHidroClase = :sDatoHidroClase")
    , @NamedQuery(name = "DatoHidrobiologia.findBySDatoHidroOrden", query = "SELECT d FROM DatoHidrobiologia d WHERE d.sDatoHidroOrden = :sDatoHidroOrden")
    , @NamedQuery(name = "DatoHidrobiologia.findBySDatoHidroFamilia", query = "SELECT d FROM DatoHidrobiologia d WHERE d.sDatoHidroFamilia = :sDatoHidroFamilia")
    , @NamedQuery(name = "DatoHidrobiologia.findByDDatoHidroFechcreacion", query = "SELECT d FROM DatoHidrobiologia d WHERE d.dDatoHidroFechcreacion = :dDatoHidroFechcreacion")
    , @NamedQuery(name = "DatoHidrobiologia.findByDDatoHidroFechmodi", query = "SELECT d FROM DatoHidrobiologia d WHERE d.dDatoHidroFechmodi = :dDatoHidroFechmodi")})
public class DatoHidrobiologia implements Serializable {

    @Column(name = "i_dato_hidro_version")
    private Integer iDatoHidroVersion;
    @Column(name = "c_dato_hidro_estado")
    private Character cDatoHidroEstado;

    @OneToMany(mappedBy = "fiFotohidroDathidr")
    private List<FotoHidrobiologia> fotoHidrobiologiaList;

    @OneToMany(mappedBy = "fkiResultadoDatoHidrobiologia")
    private List<Resultado> resultadoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_dato_hidro", nullable = false)
    private Integer piDatoHidro;
    @Column(name = "s_dato_hidro_epecie_morfoespecie", length = 45)
    private String sDatoHidroGenero;
    @Column(name = "s_dato_hidro_division_phylum", length = 45)
    private String sDatoHidroDivisionPhylum;
    @Column(name = "s_dato_hidro_clase", length = 45)
    private String sDatoHidroClase;
    @Column(name = "s_dato_hidro_orden", length = 45)
    private String sDatoHidroOrden;
    @Column(name = "s_dato_hidro_familia", length = 45)
    private String sDatoHidroFamilia;
     @Column(name = "s_dato_hidro_nombre_comun", length = 45)
    private String sDatoHidroNombreComun;
     @Column(name = "s_dato_hidro_sufijo", length = 45)
    private String sDatoHidroSufijo;
    @Column(name = "d_dato_hidro_fechcreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDatoHidroFechcreacion;
    @Column(name = "d_dato_hidro_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDatoHidroFechmodi;
    @JoinColumn(name = "fi_dato_hidro_var_anal", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiDatoHidroVarAnal;
    @JoinColumn(name = "s_dato_hidro_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario sDatoHidroUsuacrea;
    @JoinColumn(name = "s_dato_hidro_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario sDatoHidroUsuamodi;

    public DatoHidrobiologia() {
    }

    public DatoHidrobiologia(Integer piDatoHidro) {
        this.piDatoHidro = piDatoHidro;
    }

    public Integer getPiDatoHidro() {
        return piDatoHidro;
    }

    public void setPiDatoHidro(Integer piDatoHidro) {
        this.piDatoHidro = piDatoHidro;
    }

    public String getSDatoHidroGenero() {
        return sDatoHidroGenero;
    }

    public void setSDatoHidroGenero(String sDatoHidroGenero) {
        this.sDatoHidroGenero = sDatoHidroGenero;
    }

    public String getSDatoHidroDivisionPhylum() {
        return sDatoHidroDivisionPhylum;
    }

    public void setSDatoHidroDivisionPhylum(String sDatoHidroDivisionPhylum) {
        this.sDatoHidroDivisionPhylum = sDatoHidroDivisionPhylum;
    }

    public String getSDatoHidroClase() {
        return sDatoHidroClase;
    }

    public void setSDatoHidroClase(String sDatoHidroClase) {
        this.sDatoHidroClase = sDatoHidroClase;
    }

    public String getSDatoHidroOrden() {
        return sDatoHidroOrden;
    }

    public void setSDatoHidroOrden(String sDatoHidroOrden) {
        this.sDatoHidroOrden = sDatoHidroOrden;
    }

    public String getSDatoHidroFamilia() {
        return sDatoHidroFamilia;
    }

    public void setSDatoHidroFamilia(String sDatoHidroFamilia) {
        this.sDatoHidroFamilia = sDatoHidroFamilia;
    }

    public Date getDDatoHidroFechcreacion() {
        return dDatoHidroFechcreacion;
    }

    public void setDDatoHidroFechcreacion(Date dDatoHidroFechcreacion) {
        this.dDatoHidroFechcreacion = dDatoHidroFechcreacion;
    }

    public Date getDDatoHidroFechmodi() {
        return dDatoHidroFechmodi;
    }

    public void setDDatoHidroFechmodi(Date dDatoHidroFechmodi) {
        this.dDatoHidroFechmodi = dDatoHidroFechmodi;
    }

    public VariableAnalisis getFiDatoHidroVarAnal() {
        return fiDatoHidroVarAnal;
    }

    public void setFiDatoHidroVarAnal(VariableAnalisis fiDatoHidroVarAnal) {
        this.fiDatoHidroVarAnal = fiDatoHidroVarAnal;
    }

    public SUsuario getSDatoHidroUsuacrea() {
        return sDatoHidroUsuacrea;
    }

    public void setSDatoHidroUsuacrea(SUsuario sDatoHidroUsuacrea) {
        this.sDatoHidroUsuacrea = sDatoHidroUsuacrea;
    }

    public SUsuario getSDatoHidroUsuamodi() {
        return sDatoHidroUsuamodi;
    }

    public void setSDatoHidroUsuamodi(SUsuario sDatoHidroUsuamodi) {
        this.sDatoHidroUsuamodi = sDatoHidroUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piDatoHidro != null ? piDatoHidro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoHidrobiologia)) {
            return false;
        }
        DatoHidrobiologia other = (DatoHidrobiologia) object;
        if ((this.piDatoHidro == null && other.piDatoHidro != null) || (this.piDatoHidro != null && !this.piDatoHidro.equals(other.piDatoHidro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DatoHidrobiologia[ piDatoHidro=" + piDatoHidro + " ]";
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @XmlTransient
    public List<FotoHidrobiologia> getFotoHidrobiologiaList() {
        return fotoHidrobiologiaList;
    }

    public void setFotoHidrobiologiaList(List<FotoHidrobiologia> fotoHidrobiologiaList) {
        this.fotoHidrobiologiaList = fotoHidrobiologiaList;
    }

    public String getsDatoHidroNombreComun() {
        return sDatoHidroNombreComun;
    }

    public void setsDatoHidroNombreComun(String sDatoHidroNombreComun) {
        this.sDatoHidroNombreComun = sDatoHidroNombreComun;
    }

    public String getsDatoHidroSufijo() {
        return sDatoHidroSufijo;
    }

    public void setsDatoHidroSufijo(String sDatoHidroSufijo) {
        this.sDatoHidroSufijo = sDatoHidroSufijo;
    }

    public Integer getIDatoHidroVersion() {
        return iDatoHidroVersion;
    }

    public void setIDatoHidroVersion(Integer iDatoHidroVersion) {
        this.iDatoHidroVersion = iDatoHidroVersion;
    }

    public Character getCDatoHidroEstado() {
        return cDatoHidroEstado;
    }

    public void setCDatoHidroEstado(Character cDatoHidroEstado) {
        this.cDatoHidroEstado = cDatoHidroEstado;
    }
    
    
    
}
