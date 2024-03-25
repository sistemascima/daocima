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
@Table(name = "muestra_analisis_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestraAnalisisAuditoria.findAll", query = "SELECT m FROM MuestraAnalisisAuditoria m"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByPiMuestraanalAuditoriaId", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.piMuestraanalAuditoriaId = :piMuestraanalAuditoriaId"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFiMuestraanalAuditoriaMuestra", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fiMuestraanalAuditoriaMuestra = :fiMuestraanalAuditoriaMuestra"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFiMuestraanalAuditoriaVariable", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fiMuestraanalAuditoriaVariable = :fiMuestraanalAuditoriaVariable"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findBySMuestraanalAuditoriaRealizado", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.sMuestraanalAuditoriaRealizado = :sMuestraanalAuditoriaRealizado"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFsMuestraanalAuditoriaProveedor", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fsMuestraanalAuditoriaProveedor = :fsMuestraanalAuditoriaProveedor"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFsMuestraanalAudioriaUsuacrea", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fsMuestraanalAudioriaUsuacrea = :fsMuestraanalAudioriaUsuacrea"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByDMuestraanalAuditoriaFechingr", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.dMuestraanalAuditoriaFechingr = :dMuestraanalAuditoriaFechingr"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFsMuestraanalAuditoriaUsuamodi", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fsMuestraanalAuditoriaUsuamodi = :fsMuestraanalAuditoriaUsuamodi"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByDMuestraanalAuditoriaFechmodi", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.dMuestraanalAuditoriaFechmodi = :dMuestraanalAuditoriaFechmodi"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findBySMuestraanalAuditoriaObservaciones", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.sMuestraanalAuditoriaObservaciones = :sMuestraanalAuditoriaObservaciones"),
    @NamedQuery(name = "MuestraAnalisisAuditoria.findByFiMuestraanalAuditoriaAnalparam", query = "SELECT m FROM MuestraAnalisisAuditoria m WHERE m.fiMuestraanalAuditoriaAnalparam = :fiMuestraanalAuditoriaAnalparam")})
public class MuestraAnalisisAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_muestraanal_auditoria_id", nullable = false)
    private Integer piMuestraanalAuditoriaId;
    @Column(name = "fi_muestraanal_auditoria_muestra")
    private Integer fiMuestraanalAuditoriaMuestra;
    @Column(name = "fi_muestraanal_auditoria_variable")
    private Integer fiMuestraanalAuditoriaVariable;
    @Column(name = "s_muestraanal_auditoria_realizado", length = 45)
    private String sMuestraanalAuditoriaRealizado;
    @Column(name = "fs_muestraanal_auditoria_proveedor", length = 100)
    private String fsMuestraanalAuditoriaProveedor;
    @Column(name = "fs_muestraanal_audioria_usuacrea", length = 22)
    private String fsMuestraanalAudioriaUsuacrea;
    @Column(name = "d_muestraanal_auditoria_fechingr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraanalAuditoriaFechingr;
    @Column(name = "fs_muestraanal_auditoria_usuamodi", length = 22)
    private String fsMuestraanalAuditoriaUsuamodi;
    @Column(name = "d_muestraanal_auditoria_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraanalAuditoriaFechmodi;
    @Column(name = "s_muestraanal_auditoria_observaciones", length = 250)
    private String sMuestraanalAuditoriaObservaciones;
    @Column(name = "fi_muestraanal_auditoria_analparam")
    private Integer fiMuestraanalAuditoriaAnalparam;

    public MuestraAnalisisAuditoria() {
    }

    public MuestraAnalisisAuditoria(Integer piMuestraanalAuditoriaId) {
        this.piMuestraanalAuditoriaId = piMuestraanalAuditoriaId;
    }

    public Integer getPiMuestraanalAuditoriaId() {
        return piMuestraanalAuditoriaId;
    }

    public void setPiMuestraanalAuditoriaId(Integer piMuestraanalAuditoriaId) {
        this.piMuestraanalAuditoriaId = piMuestraanalAuditoriaId;
    }

    public Integer getFiMuestraanalAuditoriaMuestra() {
        return fiMuestraanalAuditoriaMuestra;
    }

    public void setFiMuestraanalAuditoriaMuestra(Integer fiMuestraanalAuditoriaMuestra) {
        this.fiMuestraanalAuditoriaMuestra = fiMuestraanalAuditoriaMuestra;
    }

    public Integer getFiMuestraanalAuditoriaVariable() {
        return fiMuestraanalAuditoriaVariable;
    }

    public void setFiMuestraanalAuditoriaVariable(Integer fiMuestraanalAuditoriaVariable) {
        this.fiMuestraanalAuditoriaVariable = fiMuestraanalAuditoriaVariable;
    }

    public String getSMuestraanalAuditoriaRealizado() {
        return sMuestraanalAuditoriaRealizado;
    }

    public void setSMuestraanalAuditoriaRealizado(String sMuestraanalAuditoriaRealizado) {
        this.sMuestraanalAuditoriaRealizado = sMuestraanalAuditoriaRealizado;
    }

    public String getFsMuestraanalAuditoriaProveedor() {
        return fsMuestraanalAuditoriaProveedor;
    }

    public void setFsMuestraanalAuditoriaProveedor(String fsMuestraanalAuditoriaProveedor) {
        this.fsMuestraanalAuditoriaProveedor = fsMuestraanalAuditoriaProveedor;
    }

    public String getFsMuestraanalAudioriaUsuacrea() {
        return fsMuestraanalAudioriaUsuacrea;
    }

    public void setFsMuestraanalAudioriaUsuacrea(String fsMuestraanalAudioriaUsuacrea) {
        this.fsMuestraanalAudioriaUsuacrea = fsMuestraanalAudioriaUsuacrea;
    }

    public Date getDMuestraanalAuditoriaFechingr() {
        return dMuestraanalAuditoriaFechingr;
    }

    public void setDMuestraanalAuditoriaFechingr(Date dMuestraanalAuditoriaFechingr) {
        this.dMuestraanalAuditoriaFechingr = dMuestraanalAuditoriaFechingr;
    }

    public String getFsMuestraanalAuditoriaUsuamodi() {
        return fsMuestraanalAuditoriaUsuamodi;
    }

    public void setFsMuestraanalAuditoriaUsuamodi(String fsMuestraanalAuditoriaUsuamodi) {
        this.fsMuestraanalAuditoriaUsuamodi = fsMuestraanalAuditoriaUsuamodi;
    }

    public Date getDMuestraanalAuditoriaFechmodi() {
        return dMuestraanalAuditoriaFechmodi;
    }

    public void setDMuestraanalAuditoriaFechmodi(Date dMuestraanalAuditoriaFechmodi) {
        this.dMuestraanalAuditoriaFechmodi = dMuestraanalAuditoriaFechmodi;
    }

    public String getSMuestraanalAuditoriaObservaciones() {
        return sMuestraanalAuditoriaObservaciones;
    }

    public void setSMuestraanalAuditoriaObservaciones(String sMuestraanalAuditoriaObservaciones) {
        this.sMuestraanalAuditoriaObservaciones = sMuestraanalAuditoriaObservaciones;
    }

    public Integer getFiMuestraanalAuditoriaAnalparam() {
        return fiMuestraanalAuditoriaAnalparam;
    }

    public void setFiMuestraanalAuditoriaAnalparam(Integer fiMuestraanalAuditoriaAnalparam) {
        this.fiMuestraanalAuditoriaAnalparam = fiMuestraanalAuditoriaAnalparam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piMuestraanalAuditoriaId != null ? piMuestraanalAuditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraAnalisisAuditoria)) {
            return false;
        }
        MuestraAnalisisAuditoria other = (MuestraAnalisisAuditoria) object;
        if ((this.piMuestraanalAuditoriaId == null && other.piMuestraanalAuditoriaId != null) || (this.piMuestraanalAuditoriaId != null && !this.piMuestraanalAuditoriaId.equals(other.piMuestraanalAuditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexionbd.MuestraAnalisisAuditoria[ piMuestraanalAuditoriaId=" + piMuestraanalAuditoriaId + " ]";
    }
    
}
