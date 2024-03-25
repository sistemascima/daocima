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
@Table(name = "muestra_auditoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestraAuditoria.findAll", query = "SELECT m FROM MuestraAuditoria m"),
    @NamedQuery(name = "MuestraAuditoria.findByPiMuestraAuditoriaId", query = "SELECT m FROM MuestraAuditoria m WHERE m.piMuestraAuditoriaId = :piMuestraAuditoriaId"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaMuestreo", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaMuestreo = :fiMuestraAuditoriaMuestreo"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaTecnico", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaTecnico = :fiMuestraAuditoriaTecnico"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaCliente", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaCliente = :fiMuestraAuditoriaCliente"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaTipomuestreo", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaTipomuestreo = :fiMuestraAuditoriaTipomuestreo"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoraMatriz", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoraMatriz = :fiMuestraAuditoraMatriz"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaUsuresprecep", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaUsuresprecep = :fiMuestraAuditoriaUsuresprecep"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaReporte", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaReporte = :fiMuestraAuditoriaReporte"),
    @NamedQuery(name = "MuestraAuditoria.findByDMuestraAuditoriaFechdesca", query = "SELECT m FROM MuestraAuditoria m WHERE m.dMuestraAuditoriaFechdesca = :dMuestraAuditoriaFechdesca"),
    @NamedQuery(name = "MuestraAuditoria.findBySMuestraAuditoriaObservaciones", query = "SELECT m FROM MuestraAuditoria m WHERE m.sMuestraAuditoriaObservaciones = :sMuestraAuditoriaObservaciones"),
    @NamedQuery(name = "MuestraAuditoria.findByDMuestraAuditoriaFechanal", query = "SELECT m FROM MuestraAuditoria m WHERE m.dMuestraAuditoriaFechanal = :dMuestraAuditoriaFechanal"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaProyecto", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaProyecto = :fiMuestraAuditoriaProyecto"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaCampo", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaCampo = :fiMuestraAuditoriaCampo"),
    @NamedQuery(name = "MuestraAuditoria.findBySMuestraAuditoriaDescripcion", query = "SELECT m FROM MuestraAuditoria m WHERE m.sMuestraAuditoriaDescripcion = :sMuestraAuditoriaDescripcion"),
    @NamedQuery(name = "MuestraAuditoria.findByDMuestraAuditoriaFechtomamuest", query = "SELECT m FROM MuestraAuditoria m WHERE m.dMuestraAuditoriaFechtomamuest = :dMuestraAuditoriaFechtomamuest"),
    @NamedQuery(name = "MuestraAuditoria.findByDMuestraAuditoriaFechcreac", query = "SELECT m FROM MuestraAuditoria m WHERE m.dMuestraAuditoriaFechcreac = :dMuestraAuditoriaFechcreac"),
    @NamedQuery(name = "MuestraAuditoria.findByFsMuestraAuditoriaUsucreac", query = "SELECT m FROM MuestraAuditoria m WHERE m.fsMuestraAuditoriaUsucreac = :fsMuestraAuditoriaUsucreac"),
    @NamedQuery(name = "MuestraAuditoria.findByFsMuestraAuditoriaUsuamodi", query = "SELECT m FROM MuestraAuditoria m WHERE m.fsMuestraAuditoriaUsuamodi = :fsMuestraAuditoriaUsuamodi"),
    @NamedQuery(name = "MuestraAuditoria.findByDMuestraAuditoriaFechmodi", query = "SELECT m FROM MuestraAuditoria m WHERE m.dMuestraAuditoriaFechmodi = :dMuestraAuditoriaFechmodi"),
    @NamedQuery(name = "MuestraAuditoria.findByFiMuestraAuditoriaTipomuest", query = "SELECT m FROM MuestraAuditoria m WHERE m.fiMuestraAuditoriaTipomuest = :fiMuestraAuditoriaTipomuest")})
public class MuestraAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_muestra_auditoria_id", nullable = false)
    private Integer piMuestraAuditoriaId;
    @Column(name = "fi_muestra_auditoria_muestreo")
    private Integer fiMuestraAuditoriaMuestreo;
    @Column(name = "fi_muestra_auditoria_tecnico", length = 45)
    private String fiMuestraAuditoriaTecnico;
    @Column(name = "fi_muestra_auditoria_cliente", length = 20)
    private String fiMuestraAuditoriaCliente;
    @Column(name = "fi_muestra_auditoria_tipomuestreo")
    private Integer fiMuestraAuditoriaTipomuestreo;
    @Column(name = "fi_muestra_auditora_matriz")
    private Integer fiMuestraAuditoraMatriz;
    @Column(name = "fi_muestra_auditoria_usuresprecep", length = 45)
    private String fiMuestraAuditoriaUsuresprecep;
    @Column(name = "fi_muestra_auditoria_reporte", length = 20)
    private String fiMuestraAuditoriaReporte;
    @Column(name = "d_muestra_auditoria_fechdesca")
    @Temporal(TemporalType.DATE)
    private Date dMuestraAuditoriaFechdesca;
    @Column(name = "s_muestra_auditoria_observaciones", length = 45)
    private String sMuestraAuditoriaObservaciones;
    @Column(name = "d_muestra_auditoria_fechanal")
    @Temporal(TemporalType.DATE)
    private Date dMuestraAuditoriaFechanal;
    @Column(name = "fi_muestra_auditoria_proyecto")
    private Integer fiMuestraAuditoriaProyecto;
    @Column(name = "fi_muestra_auditoria_campo", length = 45)
    private String fiMuestraAuditoriaCampo;
    @Column(name = "s_muestra_auditoria_descripcion", length = 250)
    private String sMuestraAuditoriaDescripcion;
    @Column(name = "d_muestra_auditoria_fechtomamuest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraAuditoriaFechtomamuest;
    @Column(name = "d_muestra_auditoria_fechcreac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraAuditoriaFechcreac;
    @Column(name = "fs_muestra_auditoria_usucreac", length = 45)
    private String fsMuestraAuditoriaUsucreac;
    @Column(name = "fs_muestra_auditoria_usuamodi", length = 22)
    private String fsMuestraAuditoriaUsuamodi;
    @Column(name = "d_muestra_auditoria_fechmodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraAuditoriaFechmodi;
    @Column(name = "fi_muestra_auditoria_tipomuest")
    private Integer fiMuestraAuditoriaTipomuest;

    public MuestraAuditoria() {
    }

    public MuestraAuditoria(Integer piMuestraAuditoriaId) {
        this.piMuestraAuditoriaId = piMuestraAuditoriaId;
    }

    public Integer getPiMuestraAuditoriaId() {
        return piMuestraAuditoriaId;
    }

    public void setPiMuestraAuditoriaId(Integer piMuestraAuditoriaId) {
        this.piMuestraAuditoriaId = piMuestraAuditoriaId;
    }

    public Integer getFiMuestraAuditoriaMuestreo() {
        return fiMuestraAuditoriaMuestreo;
    }

    public void setFiMuestraAuditoriaMuestreo(Integer fiMuestraAuditoriaMuestreo) {
        this.fiMuestraAuditoriaMuestreo = fiMuestraAuditoriaMuestreo;
    }

    public String getFiMuestraAuditoriaTecnico() {
        return fiMuestraAuditoriaTecnico;
    }

    public void setFiMuestraAuditoriaTecnico(String fiMuestraAuditoriaTecnico) {
        this.fiMuestraAuditoriaTecnico = fiMuestraAuditoriaTecnico;
    }

    public String getFiMuestraAuditoriaCliente() {
        return fiMuestraAuditoriaCliente;
    }

    public void setFiMuestraAuditoriaCliente(String fiMuestraAuditoriaCliente) {
        this.fiMuestraAuditoriaCliente = fiMuestraAuditoriaCliente;
    }

    public Integer getFiMuestraAuditoriaTipomuestreo() {
        return fiMuestraAuditoriaTipomuestreo;
    }

    public void setFiMuestraAuditoriaTipomuestreo(Integer fiMuestraAuditoriaTipomuestreo) {
        this.fiMuestraAuditoriaTipomuestreo = fiMuestraAuditoriaTipomuestreo;
    }

    public Integer getFiMuestraAuditoraMatriz() {
        return fiMuestraAuditoraMatriz;
    }

    public void setFiMuestraAuditoraMatriz(Integer fiMuestraAuditoraMatriz) {
        this.fiMuestraAuditoraMatriz = fiMuestraAuditoraMatriz;
    }

    public String getFiMuestraAuditoriaUsuresprecep() {
        return fiMuestraAuditoriaUsuresprecep;
    }

    public void setFiMuestraAuditoriaUsuresprecep(String fiMuestraAuditoriaUsuresprecep) {
        this.fiMuestraAuditoriaUsuresprecep = fiMuestraAuditoriaUsuresprecep;
    }

    public String getFiMuestraAuditoriaReporte() {
        return fiMuestraAuditoriaReporte;
    }

    public void setFiMuestraAuditoriaReporte(String fiMuestraAuditoriaReporte) {
        this.fiMuestraAuditoriaReporte = fiMuestraAuditoriaReporte;
    }

    public Date getDMuestraAuditoriaFechdesca() {
        return dMuestraAuditoriaFechdesca;
    }

    public void setDMuestraAuditoriaFechdesca(Date dMuestraAuditoriaFechdesca) {
        this.dMuestraAuditoriaFechdesca = dMuestraAuditoriaFechdesca;
    }

    public String getSMuestraAuditoriaObservaciones() {
        return sMuestraAuditoriaObservaciones;
    }

    public void setSMuestraAuditoriaObservaciones(String sMuestraAuditoriaObservaciones) {
        this.sMuestraAuditoriaObservaciones = sMuestraAuditoriaObservaciones;
    }

    public Date getDMuestraAuditoriaFechanal() {
        return dMuestraAuditoriaFechanal;
    }

    public void setDMuestraAuditoriaFechanal(Date dMuestraAuditoriaFechanal) {
        this.dMuestraAuditoriaFechanal = dMuestraAuditoriaFechanal;
    }

    public Integer getFiMuestraAuditoriaProyecto() {
        return fiMuestraAuditoriaProyecto;
    }

    public void setFiMuestraAuditoriaProyecto(Integer fiMuestraAuditoriaProyecto) {
        this.fiMuestraAuditoriaProyecto = fiMuestraAuditoriaProyecto;
    }

    public String getFiMuestraAuditoriaCampo() {
        return fiMuestraAuditoriaCampo;
    }

    public void setFiMuestraAuditoriaCampo(String fiMuestraAuditoriaCampo) {
        this.fiMuestraAuditoriaCampo = fiMuestraAuditoriaCampo;
    }

    public String getSMuestraAuditoriaDescripcion() {
        return sMuestraAuditoriaDescripcion;
    }

    public void setSMuestraAuditoriaDescripcion(String sMuestraAuditoriaDescripcion) {
        this.sMuestraAuditoriaDescripcion = sMuestraAuditoriaDescripcion;
    }

    public Date getDMuestraAuditoriaFechtomamuest() {
        return dMuestraAuditoriaFechtomamuest;
    }

    public void setDMuestraAuditoriaFechtomamuest(Date dMuestraAuditoriaFechtomamuest) {
        this.dMuestraAuditoriaFechtomamuest = dMuestraAuditoriaFechtomamuest;
    }

    public Date getDMuestraAuditoriaFechcreac() {
        return dMuestraAuditoriaFechcreac;
    }

    public void setDMuestraAuditoriaFechcreac(Date dMuestraAuditoriaFechcreac) {
        this.dMuestraAuditoriaFechcreac = dMuestraAuditoriaFechcreac;
    }

    public String getFsMuestraAuditoriaUsucreac() {
        return fsMuestraAuditoriaUsucreac;
    }

    public void setFsMuestraAuditoriaUsucreac(String fsMuestraAuditoriaUsucreac) {
        this.fsMuestraAuditoriaUsucreac = fsMuestraAuditoriaUsucreac;
    }

    public String getFsMuestraAuditoriaUsuamodi() {
        return fsMuestraAuditoriaUsuamodi;
    }

    public void setFsMuestraAuditoriaUsuamodi(String fsMuestraAuditoriaUsuamodi) {
        this.fsMuestraAuditoriaUsuamodi = fsMuestraAuditoriaUsuamodi;
    }

    public Date getDMuestraAuditoriaFechmodi() {
        return dMuestraAuditoriaFechmodi;
    }

    public void setDMuestraAuditoriaFechmodi(Date dMuestraAuditoriaFechmodi) {
        this.dMuestraAuditoriaFechmodi = dMuestraAuditoriaFechmodi;
    }

    public Integer getFiMuestraAuditoriaTipomuest() {
        return fiMuestraAuditoriaTipomuest;
    }

    public void setFiMuestraAuditoriaTipomuest(Integer fiMuestraAuditoriaTipomuest) {
        this.fiMuestraAuditoriaTipomuest = fiMuestraAuditoriaTipomuest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piMuestraAuditoriaId != null ? piMuestraAuditoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraAuditoria)) {
            return false;
        }
        MuestraAuditoria other = (MuestraAuditoria) object;
        if ((this.piMuestraAuditoriaId == null && other.piMuestraAuditoriaId != null) || (this.piMuestraAuditoriaId != null && !this.piMuestraAuditoriaId.equals(other.piMuestraAuditoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexionbd.MuestraAuditoria[ piMuestraAuditoriaId=" + piMuestraAuditoriaId + " ]";
    }
    
}
