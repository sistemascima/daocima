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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "decimal_varianal", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DecimalVarianal.findAll", query = "SELECT d FROM DecimalVarianal d")
    , @NamedQuery(name = "DecimalVarianal.findByPiDecimalVarianalId", query = "SELECT d FROM DecimalVarianal d WHERE d.piDecimalVarianalId = :piDecimalVarianalId")
    , @NamedQuery(name = "DecimalVarianal.findByIDecimalVarianalDecimal", query = "SELECT d FROM DecimalVarianal d WHERE d.iDecimalVarianalDecimal = :iDecimalVarianalDecimal")
    , @NamedQuery(name = "DecimalVarianal.findByDDecimalVarianalFechacreaci", query = "SELECT d FROM DecimalVarianal d WHERE d.dDecimalVarianalFechacreaci = :dDecimalVarianalFechacreaci")
    , @NamedQuery(name = "DecimalVarianal.findByDDecimalVarianalFechamodi", query = "SELECT d FROM DecimalVarianal d WHERE d.dDecimalVarianalFechamodi = :dDecimalVarianalFechamodi")})
public class DecimalVarianal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_decimal_varianal_id", nullable = false)
    private Integer piDecimalVarianalId;
    @Column(name = "i_decimal_varianal_decimal")
    private Integer iDecimalVarianalDecimal;
    @Column(name = "d_decimal_varianal_fechacreaci")
    @Temporal(TemporalType.DATE)
    private Date dDecimalVarianalFechacreaci;
    @Column(name = "d_decimal_varianal_fechamodi")
    @Temporal(TemporalType.DATE)
    private Date dDecimalVarianalFechamodi;
    @JoinColumn(name = "fi_decimal_varianal_matriz", referencedColumnName = "pi_matranal_id")
    @ManyToOne
    private MatrizAnalisis fiDecimalVarianalMatriz;
    @JoinColumn(name = "fi_decimal_varianal_varianal", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiDecimalVarianalVarianal;
    @JoinColumn(name = "fs_decimal_varianal_usuacreac", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDecimalVarianalUsuacreac;
    @JoinColumn(name = "fs_decimal_varianal_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDecimalVarianalUsuamodi;

    public DecimalVarianal() {
    }

    public DecimalVarianal(Integer piDecimalVarianalId) {
        this.piDecimalVarianalId = piDecimalVarianalId;
    }

    public Integer getPiDecimalVarianalId() {
        return piDecimalVarianalId;
    }

    public void setPiDecimalVarianalId(Integer piDecimalVarianalId) {
        this.piDecimalVarianalId = piDecimalVarianalId;
    }

    public Integer getIDecimalVarianalDecimal() {
        return iDecimalVarianalDecimal;
    }

    public void setIDecimalVarianalDecimal(Integer iDecimalVarianalDecimal) {
        this.iDecimalVarianalDecimal = iDecimalVarianalDecimal;
    }

    public Date getDDecimalVarianalFechacreaci() {
        return dDecimalVarianalFechacreaci;
    }

    public void setDDecimalVarianalFechacreaci(Date dDecimalVarianalFechacreaci) {
        this.dDecimalVarianalFechacreaci = dDecimalVarianalFechacreaci;
    }

    public Date getDDecimalVarianalFechamodi() {
        return dDecimalVarianalFechamodi;
    }

    public void setDDecimalVarianalFechamodi(Date dDecimalVarianalFechamodi) {
        this.dDecimalVarianalFechamodi = dDecimalVarianalFechamodi;
    }

    public MatrizAnalisis getFiDecimalVarianalMatriz() {
        return fiDecimalVarianalMatriz;
    }

    public void setFiDecimalVarianalMatriz(MatrizAnalisis fiDecimalVarianalMatriz) {
        this.fiDecimalVarianalMatriz = fiDecimalVarianalMatriz;
    }

    public VariableAnalisis getFiDecimalVarianalVarianal() {
        return fiDecimalVarianalVarianal;
    }

    public void setFiDecimalVarianalVarianal(VariableAnalisis fiDecimalVarianalVarianal) {
        this.fiDecimalVarianalVarianal = fiDecimalVarianalVarianal;
    }

    public SUsuario getFsDecimalVarianalUsuacreac() {
        return fsDecimalVarianalUsuacreac;
    }

    public void setFsDecimalVarianalUsuacreac(SUsuario fsDecimalVarianalUsuacreac) {
        this.fsDecimalVarianalUsuacreac = fsDecimalVarianalUsuacreac;
    }

    public SUsuario getFsDecimalVarianalUsuamodi() {
        return fsDecimalVarianalUsuamodi;
    }

    public void setFsDecimalVarianalUsuamodi(SUsuario fsDecimalVarianalUsuamodi) {
        this.fsDecimalVarianalUsuamodi = fsDecimalVarianalUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piDecimalVarianalId != null ? piDecimalVarianalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DecimalVarianal)) {
            return false;
        }
        DecimalVarianal other = (DecimalVarianal) object;
        if ((this.piDecimalVarianalId == null && other.piDecimalVarianalId != null) || (this.piDecimalVarianalId != null && !this.piDecimalVarianalId.equals(other.piDecimalVarianalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DecimalVarianal[ piDecimalVarianalId=" + piDecimalVarianalId + " ]";
    }
    
}
