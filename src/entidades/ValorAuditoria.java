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
@Table(name = "valor_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorAuditoria.findAll", query = "SELECT v FROM ValorAuditoria v"),
    @NamedQuery(name = "ValorAuditoria.findByPiValorAuditoriaId", query = "SELECT v FROM ValorAuditoria v WHERE v.piValorAuditoriaId = :piValorAuditoriaId"),
    @NamedQuery(name = "ValorAuditoria.findByFiValorAuditoriaTpvalor", query = "SELECT v FROM ValorAuditoria v WHERE v.fiValorAuditoriaTpvalor = :fiValorAuditoriaTpvalor"),
    @NamedQuery(name = "ValorAuditoria.findBySValorAuditoriaNombre", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorAuditoriaNombre = :sValorAuditoriaNombre"),
    @NamedQuery(name = "ValorAuditoria.findBySValorAuditoriaValor", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorAuditoriaValor = :sValorAuditoriaValor"),
    @NamedQuery(name = "ValorAuditoria.findByFiValorAuditoriaMuestraanal", query = "SELECT v FROM ValorAuditoria v WHERE v.fiValorAuditoriaMuestraanal = :fiValorAuditoriaMuestraanal"),
    @NamedQuery(name = "ValorAuditoria.findBySValorAuditoriaObservaciones", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorAuditoriaObservaciones = :sValorAuditoriaObservaciones"),
    @NamedQuery(name = "ValorAuditoria.findByFiValorAuditoriaValorpadre", query = "SELECT v FROM ValorAuditoria v WHERE v.fiValorAuditoriaValorpadre = :fiValorAuditoriaValorpadre"),
    @NamedQuery(name = "ValorAuditoria.findBySValorAuditoriaAbsorbancia", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorAuditoriaAbsorbancia = :sValorAuditoriaAbsorbancia"),
    @NamedQuery(name = "ValorAuditoria.findBySValorAuditoriaMasa", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorAuditoriaMasa = :sValorAuditoriaMasa"),
    @NamedQuery(name = "ValorAuditoria.findByFsValorAuditoriaUsuamodi", query = "SELECT v FROM ValorAuditoria v WHERE v.fsValorAuditoriaUsuamodi = :fsValorAuditoriaUsuamodi"),
    @NamedQuery(name = "ValorAuditoria.findByDValorAuditoriaFechregi", query = "SELECT v FROM ValorAuditoria v WHERE v.dValorAuditoriaFechregi = :dValorAuditoriaFechregi"),
    @NamedQuery(name = "ValorAuditoria.findByDValorAuditoriaFechmodi", query = "SELECT v FROM ValorAuditoria v WHERE v.dValorAuditoriaFechmodi = :dValorAuditoriaFechmodi"),
    @NamedQuery(name = "ValorAuditoria.findBySValorValorreal", query = "SELECT v FROM ValorAuditoria v WHERE v.sValorValorreal = :sValorValorreal"),
    @NamedQuery(name = "ValorAuditoria.findByValorAuditoriaId", query = "SELECT v FROM ValorAuditoria v WHERE v.valorAuditoriaId = :valorAuditoriaId")})
public class ValorAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pi_valor_auditoria_id")
    private Integer piValorAuditoriaId;
    @Column(name = "fi_valor_auditoria_tpvalor")
    private Integer fiValorAuditoriaTpvalor;
    @Column(name = "s_valor_auditoria_nombre", length = 225)
    private String sValorAuditoriaNombre;
    @Column(name = "s_valor_auditoria_valor", length = 22)
    private String sValorAuditoriaValor;
    @Column(name = "fi_valor_auditoria_muestraanal")
    private Integer fiValorAuditoriaMuestraanal;
    @Column(name = "s_valor_auditoria_observaciones", length = 225)
    private String sValorAuditoriaObservaciones;
    @Column(name = "fi_valor_auditoria_valorpadre")
    private Integer fiValorAuditoriaValorpadre;
    @Column(name = "s_valor_auditoria_absorbancia", length = 45)
    private String sValorAuditoriaAbsorbancia;
    @Column(name = "s_valor_auditoria_masa", length = 45)
    private String sValorAuditoriaMasa;
    @Column(name = "fs_valor_auditoria_usuamodi", length = 22)
    private String fsValorAuditoriaUsuamodi;
    @Column(name = "d_valor_auditoria_fechregi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dValorAuditoriaFechregi;
    @Column(name = "d_valor_auditoria_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dValorAuditoriaFechmodi;
    @Column(name = "s_valor_valorreal", length = 45)
    private String sValorValorreal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_auditoria_id", nullable = false)
    private Integer valorAuditoriaId;

    public ValorAuditoria() {
    }

    public ValorAuditoria(Integer valorAuditoriaId) {
        this.valorAuditoriaId = valorAuditoriaId;
    }

    public Integer getPiValorAuditoriaId() {
        return piValorAuditoriaId;
    }

    public void setPiValorAuditoriaId(Integer piValorAuditoriaId) {
        this.piValorAuditoriaId = piValorAuditoriaId;
    }

    public Integer getFiValorAuditoriaTpvalor() {
        return fiValorAuditoriaTpvalor;
    }

    public void setFiValorAuditoriaTpvalor(Integer fiValorAuditoriaTpvalor) {
        this.fiValorAuditoriaTpvalor = fiValorAuditoriaTpvalor;
    }

    public String getSValorAuditoriaNombre() {
        return sValorAuditoriaNombre;
    }

    public void setSValorAuditoriaNombre(String sValorAuditoriaNombre) {
        this.sValorAuditoriaNombre = sValorAuditoriaNombre;
    }

    public String getSValorAuditoriaValor() {
        return sValorAuditoriaValor;
    }

    public void setSValorAuditoriaValor(String sValorAuditoriaValor) {
        this.sValorAuditoriaValor = sValorAuditoriaValor;
    }

    public Integer getFiValorAuditoriaMuestraanal() {
        return fiValorAuditoriaMuestraanal;
    }

    public void setFiValorAuditoriaMuestraanal(Integer fiValorAuditoriaMuestraanal) {
        this.fiValorAuditoriaMuestraanal = fiValorAuditoriaMuestraanal;
    }

    public String getSValorAuditoriaObservaciones() {
        return sValorAuditoriaObservaciones;
    }

    public void setSValorAuditoriaObservaciones(String sValorAuditoriaObservaciones) {
        this.sValorAuditoriaObservaciones = sValorAuditoriaObservaciones;
    }

    public Integer getFiValorAuditoriaValorpadre() {
        return fiValorAuditoriaValorpadre;
    }

    public void setFiValorAuditoriaValorpadre(Integer fiValorAuditoriaValorpadre) {
        this.fiValorAuditoriaValorpadre = fiValorAuditoriaValorpadre;
    }

    public String getSValorAuditoriaAbsorbancia() {
        return sValorAuditoriaAbsorbancia;
    }

    public void setSValorAuditoriaAbsorbancia(String sValorAuditoriaAbsorbancia) {
        this.sValorAuditoriaAbsorbancia = sValorAuditoriaAbsorbancia;
    }

    public String getSValorAuditoriaMasa() {
        return sValorAuditoriaMasa;
    }

    public void setSValorAuditoriaMasa(String sValorAuditoriaMasa) {
        this.sValorAuditoriaMasa = sValorAuditoriaMasa;
    }

    public String getFsValorAuditoriaUsuamodi() {
        return fsValorAuditoriaUsuamodi;
    }

    public void setFsValorAuditoriaUsuamodi(String fsValorAuditoriaUsuamodi) {
        this.fsValorAuditoriaUsuamodi = fsValorAuditoriaUsuamodi;
    }

    public Date getDValorAuditoriaFechregi() {
        return dValorAuditoriaFechregi;
    }

    public void setDValorAuditoriaFechregi(Date dValorAuditoriaFechregi) {
        this.dValorAuditoriaFechregi = dValorAuditoriaFechregi;
    }

    public Date getDValorAuditoriaFechmodi() {
        return dValorAuditoriaFechmodi;
    }

    public void setDValorAuditoriaFechmodi(Date dValorAuditoriaFechmodi) {
        this.dValorAuditoriaFechmodi = dValorAuditoriaFechmodi;
    }

    public String getSValorValorreal() {
        return sValorValorreal;
    }

    public void setSValorValorreal(String sValorValorreal) {
        this.sValorValorreal = sValorValorreal;
    }

    public Integer getValorAuditoriaId() {
        return valorAuditoriaId;
    }

    public void setValorAuditoriaId(Integer valorAuditoriaId) {
        this.valorAuditoriaId = valorAuditoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorAuditoriaId != null ? valorAuditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorAuditoria)) {
            return false;
        }
        ValorAuditoria other = (ValorAuditoria) object;
        if ((this.valorAuditoriaId == null && other.valorAuditoriaId != null) || (this.valorAuditoriaId != null && !this.valorAuditoriaId.equals(other.valorAuditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexionbd.ValorAuditoria[ valorAuditoriaId=" + valorAuditoriaId + " ]";
    }
    
}
