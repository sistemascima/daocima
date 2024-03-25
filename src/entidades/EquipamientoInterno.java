/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "equipamiento_interno", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipamientoInterno.findAll", query = "SELECT e FROM EquipamientoInterno e")
    , @NamedQuery(name = "EquipamientoInterno.findByPiEquiInterId", query = "SELECT e FROM EquipamientoInterno e WHERE e.piEquiInterId = :piEquiInterId")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterEquipo", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterEquipo = :sEquiInterEquipo")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterSerial", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterSerial = :sEquiInterSerial")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterCodigo", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterCodigo = :sEquiInterCodigo")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterUnidades", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterUnidades = :sEquiInterUnidades")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterRango", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterRango = :sEquiInterRango")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterMinimaEscala", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterMinimaEscala = :sEquiInterMinimaEscala")
    , @NamedQuery(name = "EquipamientoInterno.findBySEquiInterIncertidumbre", query = "SELECT e FROM EquipamientoInterno e WHERE e.sEquiInterIncertidumbre = :sEquiInterIncertidumbre")
    , @NamedQuery(name = "EquipamientoInterno.findByDEquiInterCreacion", query = "SELECT e FROM EquipamientoInterno e WHERE e.dEquiInterCreacion = :dEquiInterCreacion")
    , @NamedQuery(name = "EquipamientoInterno.findByDEquiInterUltimodi", query = "SELECT e FROM EquipamientoInterno e WHERE e.dEquiInterUltimodi = :dEquiInterUltimodi")})
public class EquipamientoInterno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_equi_inter_id", nullable = false)
    private Integer piEquiInterId;
    @Column(name = "s_equi_inter_equipo", length = 100)
    private String sEquiInterEquipo;
    @Column(name = "s_equi_inter_serial", length = 100)
    private String sEquiInterSerial;
    @Column(name = "s_equi_inter_codigo", length = 100)
    private String sEquiInterCodigo;
    @Column(name = "s_equi_inter_unidades", length = 100)
    private String sEquiInterUnidades;
    @Column(name = "s_equi_inter_rango", length = 100)
    private String sEquiInterRango;
    @Column(name = "s_equi_inter_minima_escala", length = 45)
    private String sEquiInterMinimaEscala;
    @Column(name = "s_equi_inter_incertidumbre", length = 45)
    private String sEquiInterIncertidumbre;
    @Column(name = "d_equi_inter_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEquiInterCreacion;
    @Column(name = "d_equi_inter_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEquiInterUltimodi;
    @JoinColumns({
        @JoinColumn(name = "fs_equi_inter_matriz", referencedColumnName = "pfs_hoja_vida_matriz")
        , @JoinColumn(name = "fi_equi_inter_consecutivo", referencedColumnName = "pfs_hoja_vida_consecutivo")
        , @JoinColumn(name = "fs_equi_inter_funcion", referencedColumnName = "pfs_hoja_vida_funcion")})
    @ManyToOne
    private HojaVida hojaVida;
    @JoinColumn(name = "fs_equi_inter_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsEquiInterUsuacrea;
    @JoinColumn(name = "fs_equi_inter_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsEquiInterUsuamodi;

    public EquipamientoInterno() {
    }

    public EquipamientoInterno(Integer piEquiInterId) {
        this.piEquiInterId = piEquiInterId;
    }

    public Integer getPiEquiInterId() {
        return piEquiInterId;
    }

    public void setPiEquiInterId(Integer piEquiInterId) {
        this.piEquiInterId = piEquiInterId;
    }

    public String getSEquiInterEquipo() {
        return sEquiInterEquipo;
    }

    public void setSEquiInterEquipo(String sEquiInterEquipo) {
        this.sEquiInterEquipo = sEquiInterEquipo;
    }

    public String getSEquiInterSerial() {
        return sEquiInterSerial;
    }

    public void setSEquiInterSerial(String sEquiInterSerial) {
        this.sEquiInterSerial = sEquiInterSerial;
    }

    public String getSEquiInterCodigo() {
        return sEquiInterCodigo;
    }

    public void setSEquiInterCodigo(String sEquiInterCodigo) {
        this.sEquiInterCodigo = sEquiInterCodigo;
    }

    public String getSEquiInterUnidades() {
        return sEquiInterUnidades;
    }

    public void setSEquiInterUnidades(String sEquiInterUnidades) {
        this.sEquiInterUnidades = sEquiInterUnidades;
    }

    public String getSEquiInterRango() {
        return sEquiInterRango;
    }

    public void setSEquiInterRango(String sEquiInterRango) {
        this.sEquiInterRango = sEquiInterRango;
    }

    public String getSEquiInterMinimaEscala() {
        return sEquiInterMinimaEscala;
    }

    public void setSEquiInterMinimaEscala(String sEquiInterMinimaEscala) {
        this.sEquiInterMinimaEscala = sEquiInterMinimaEscala;
    }

    public String getSEquiInterIncertidumbre() {
        return sEquiInterIncertidumbre;
    }

    public void setSEquiInterIncertidumbre(String sEquiInterIncertidumbre) {
        this.sEquiInterIncertidumbre = sEquiInterIncertidumbre;
    }

    public Date getDEquiInterCreacion() {
        return dEquiInterCreacion;
    }

    public void setDEquiInterCreacion(Date dEquiInterCreacion) {
        this.dEquiInterCreacion = dEquiInterCreacion;
    }

    public Date getDEquiInterUltimodi() {
        return dEquiInterUltimodi;
    }

    public void setDEquiInterUltimodi(Date dEquiInterUltimodi) {
        this.dEquiInterUltimodi = dEquiInterUltimodi;
    }

    public HojaVida getHojaVida() {
        return hojaVida;
    }

    public void setHojaVida(HojaVida hojaVida) {
        this.hojaVida = hojaVida;
    }

    public SUsuario getFsEquiInterUsuacrea() {
        return fsEquiInterUsuacrea;
    }

    public void setFsEquiInterUsuacrea(SUsuario fsEquiInterUsuacrea) {
        this.fsEquiInterUsuacrea = fsEquiInterUsuacrea;
    }

    public SUsuario getFsEquiInterUsuamodi() {
        return fsEquiInterUsuamodi;
    }

    public void setFsEquiInterUsuamodi(SUsuario fsEquiInterUsuamodi) {
        this.fsEquiInterUsuamodi = fsEquiInterUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piEquiInterId != null ? piEquiInterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipamientoInterno)) {
            return false;
        }
        EquipamientoInterno other = (EquipamientoInterno) object;
        if ((this.piEquiInterId == null && other.piEquiInterId != null) || (this.piEquiInterId != null && !this.piEquiInterId.equals(other.piEquiInterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EquipamientoInterno[ piEquiInterId=" + piEquiInterId + " ]";
    }
    
}
