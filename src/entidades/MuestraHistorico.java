/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "muestra_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestraHistorico.findAll", query = "SELECT m FROM MuestraHistorico m"),
    @NamedQuery(name = "MuestraHistorico.findByPiMuestraHistoricoId", query = "SELECT m FROM MuestraHistorico m WHERE m.muestraHistoricoPK.piMuestraHistoricoId = :piMuestraHistoricoId"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoMuestreo", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoMuestreo = :fiMuestraHistoricoMuestreo"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoTecnico", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoTecnico = :fiMuestraHistoricoTecnico"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoTipomuestreo", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoTipomuestreo = :fiMuestraHistoricoTipomuestreo"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoMatriz", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoMatriz = :fiMuestraHistoricoMatriz"),
    @NamedQuery(name = "MuestraHistorico.findByDMuestraHistoricoFechaanal", query = "SELECT m FROM MuestraHistorico m WHERE m.dMuestraHistoricoFechaanal = :dMuestraHistoricoFechaanal"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoProyecto", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoProyecto = :fiMuestraHistoricoProyecto"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoCampo", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoCampo = :fiMuestraHistoricoCampo"),
    @NamedQuery(name = "MuestraHistorico.findBySMuestraHistoricoDescripcion", query = "SELECT m FROM MuestraHistorico m WHERE m.sMuestraHistoricoDescripcion = :sMuestraHistoricoDescripcion"),
    @NamedQuery(name = "MuestraHistorico.findByDMuestraHistoricoFechtomamuestra", query = "SELECT m FROM MuestraHistorico m WHERE m.dMuestraHistoricoFechtomamuestra = :dMuestraHistoricoFechtomamuestra"),
    @NamedQuery(name = "MuestraHistorico.findByDMuestraHistoricoFechcreacion", query = "SELECT m FROM MuestraHistorico m WHERE m.dMuestraHistoricoFechcreacion = :dMuestraHistoricoFechcreacion"),
    @NamedQuery(name = "MuestraHistorico.findByFsMuestraHistoricoUsucreacion", query = "SELECT m FROM MuestraHistorico m WHERE m.fsMuestraHistoricoUsucreacion = :fsMuestraHistoricoUsucreacion"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoTipomuestra", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoTipomuestra = :fiMuestraHistoricoTipomuestra"),
    @NamedQuery(name = "MuestraHistorico.findBySMuestraHistoricoNombreMuestra", query = "SELECT m FROM MuestraHistorico m WHERE m.sMuestraHistoricoNombreMuestra = :sMuestraHistoricoNombreMuestra"),
    @NamedQuery(name = "MuestraHistorico.findBySMuestraHistoricoNombreMuestreo", query = "SELECT m FROM MuestraHistorico m WHERE m.sMuestraHistoricoNombreMuestreo = :sMuestraHistoricoNombreMuestreo"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoReporteAnexo", query = "SELECT m FROM MuestraHistorico m WHERE m.fiMuestraHistoricoReporteAnexo = :fiMuestraHistoricoReporteAnexo"),
    @NamedQuery(name = "MuestraHistorico.findByFiMuestraHistoricoReporteVersion", query = "SELECT m FROM MuestraHistorico m WHERE m.muestraHistoricoPK.fiMuestraHistoricoReporteVersion = :fiMuestraHistoricoReporteVersion")})
public class MuestraHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuestraHistoricoPK muestraHistoricoPK;
    @Column(name = "fi_muestra_historico_muestreo")
    private Integer fiMuestraHistoricoMuestreo;
    @Column(name = "fi_muestra_historico_tecnico", length = 45)
    private String fiMuestraHistoricoTecnico;
    @Column(name = "fi_muestra_historico_tipomuestreo")
    private Integer fiMuestraHistoricoTipomuestreo;
    @Column(name = "fi_muestra_historico_matriz")
    private Integer fiMuestraHistoricoMatriz;
    @Column(name = "d_muestra_historico_fechaanal")
    @Temporal(TemporalType.DATE)
    private Date dMuestraHistoricoFechaanal;
    @Column(name = "fi_muestra_historico_proyecto")
    private Integer fiMuestraHistoricoProyecto;
    @Column(name = "fi_muestra_historico_campo", length = 45)
    private String fiMuestraHistoricoCampo;
    @Column(name = "s_muestra_historico_descripcion", length = 250)
    private String sMuestraHistoricoDescripcion;
    @Column(name = "d_muestra_historico_fechtomamuestra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraHistoricoFechtomamuestra;
    @Column(name = "d_muestra_historico_fechcreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraHistoricoFechcreacion;
    @Column(name = "fs_muestra_historico_usucreacion", length = 45)
    private String fsMuestraHistoricoUsucreacion;
    @Column(name = "fi_muestra_historico_tipomuestra")
    private Integer fiMuestraHistoricoTipomuestra;
    @Column(name = "s_muestra_historico_nombre_muestra", length = 45)
    private String sMuestraHistoricoNombreMuestra;
    @Column(name = "s_muestra_historico_nombre_muestreo", length = 45)
    private String sMuestraHistoricoNombreMuestreo;
    @Column(name = "fi_muestra_historico_reporte_anexo")
    private Integer fiMuestraHistoricoReporteAnexo;
    @JoinColumns({
        @JoinColumn(name = "fs_muestra_historico_reporte", referencedColumnName = "pi_reporte_historico_id"),
        @JoinColumn(name = "fi_muestra_historico_reporte_version", referencedColumnName = "i_reporte_historico_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ReporteHistorico reporteHistorico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "muestraHistorico")
    private List<MuestraAnalisisHistorico> muestraAnalisisHistoricoList;

    public MuestraHistorico() {
    }

    public MuestraHistorico(MuestraHistoricoPK muestraHistoricoPK) {
        this.muestraHistoricoPK = muestraHistoricoPK;
    }

    public MuestraHistorico(int piMuestraHistoricoId, int fiMuestraHistoricoReporteVersion) {
        this.muestraHistoricoPK = new MuestraHistoricoPK(piMuestraHistoricoId, fiMuestraHistoricoReporteVersion);
    }

    public MuestraHistoricoPK getMuestraHistoricoPK() {
        return muestraHistoricoPK;
    }

    public void setMuestraHistoricoPK(MuestraHistoricoPK muestraHistoricoPK) {
        this.muestraHistoricoPK = muestraHistoricoPK;
    }

    public Integer getFiMuestraHistoricoMuestreo() {
        return fiMuestraHistoricoMuestreo;
    }

    public void setFiMuestraHistoricoMuestreo(Integer fiMuestraHistoricoMuestreo) {
        this.fiMuestraHistoricoMuestreo = fiMuestraHistoricoMuestreo;
    }

    public String getFiMuestraHistoricoTecnico() {
        return fiMuestraHistoricoTecnico;
    }

    public void setFiMuestraHistoricoTecnico(String fiMuestraHistoricoTecnico) {
        this.fiMuestraHistoricoTecnico = fiMuestraHistoricoTecnico;
    }

    public Integer getFiMuestraHistoricoTipomuestreo() {
        return fiMuestraHistoricoTipomuestreo;
    }

    public void setFiMuestraHistoricoTipomuestreo(Integer fiMuestraHistoricoTipomuestreo) {
        this.fiMuestraHistoricoTipomuestreo = fiMuestraHistoricoTipomuestreo;
    }

    public Integer getFiMuestraHistoricoMatriz() {
        return fiMuestraHistoricoMatriz;
    }

    public void setFiMuestraHistoricoMatriz(Integer fiMuestraHistoricoMatriz) {
        this.fiMuestraHistoricoMatriz = fiMuestraHistoricoMatriz;
    }

    public Date getDMuestraHistoricoFechaanal() {
        return dMuestraHistoricoFechaanal;
    }

    public void setDMuestraHistoricoFechaanal(Date dMuestraHistoricoFechaanal) {
        this.dMuestraHistoricoFechaanal = dMuestraHistoricoFechaanal;
    }

    public Integer getFiMuestraHistoricoProyecto() {
        return fiMuestraHistoricoProyecto;
    }

    public void setFiMuestraHistoricoProyecto(Integer fiMuestraHistoricoProyecto) {
        this.fiMuestraHistoricoProyecto = fiMuestraHistoricoProyecto;
    }

    public String getFiMuestraHistoricoCampo() {
        return fiMuestraHistoricoCampo;
    }

    public void setFiMuestraHistoricoCampo(String fiMuestraHistoricoCampo) {
        this.fiMuestraHistoricoCampo = fiMuestraHistoricoCampo;
    }

    public String getSMuestraHistoricoDescripcion() {
        return sMuestraHistoricoDescripcion;
    }

    public void setSMuestraHistoricoDescripcion(String sMuestraHistoricoDescripcion) {
        this.sMuestraHistoricoDescripcion = sMuestraHistoricoDescripcion;
    }

    public Date getDMuestraHistoricoFechtomamuestra() {
        return dMuestraHistoricoFechtomamuestra;
    }

    public void setDMuestraHistoricoFechtomamuestra(Date dMuestraHistoricoFechtomamuestra) {
        this.dMuestraHistoricoFechtomamuestra = dMuestraHistoricoFechtomamuestra;
    }

    public Date getDMuestraHistoricoFechcreacion() {
        return dMuestraHistoricoFechcreacion;
    }

    public void setDMuestraHistoricoFechcreacion(Date dMuestraHistoricoFechcreacion) {
        this.dMuestraHistoricoFechcreacion = dMuestraHistoricoFechcreacion;
    }

    public String getFsMuestraHistoricoUsucreacion() {
        return fsMuestraHistoricoUsucreacion;
    }

    public void setFsMuestraHistoricoUsucreacion(String fsMuestraHistoricoUsucreacion) {
        this.fsMuestraHistoricoUsucreacion = fsMuestraHistoricoUsucreacion;
    }

    public Integer getFiMuestraHistoricoTipomuestra() {
        return fiMuestraHistoricoTipomuestra;
    }

    public void setFiMuestraHistoricoTipomuestra(Integer fiMuestraHistoricoTipomuestra) {
        this.fiMuestraHistoricoTipomuestra = fiMuestraHistoricoTipomuestra;
    }

    public String getSMuestraHistoricoNombreMuestra() {
        return sMuestraHistoricoNombreMuestra;
    }

    public void setSMuestraHistoricoNombreMuestra(String sMuestraHistoricoNombreMuestra) {
        this.sMuestraHistoricoNombreMuestra = sMuestraHistoricoNombreMuestra;
    }

    public String getSMuestraHistoricoNombreMuestreo() {
        return sMuestraHistoricoNombreMuestreo;
    }

    public void setSMuestraHistoricoNombreMuestreo(String sMuestraHistoricoNombreMuestreo) {
        this.sMuestraHistoricoNombreMuestreo = sMuestraHistoricoNombreMuestreo;
    }

    public Integer getFiMuestraHistoricoReporteAnexo() {
        return fiMuestraHistoricoReporteAnexo;
    }

    public void setFiMuestraHistoricoReporteAnexo(Integer fiMuestraHistoricoReporteAnexo) {
        this.fiMuestraHistoricoReporteAnexo = fiMuestraHistoricoReporteAnexo;
    }

    public ReporteHistorico getReporteHistorico() {
        return reporteHistorico;
    }

    public void setReporteHistorico(ReporteHistorico reporteHistorico) {
        this.reporteHistorico = reporteHistorico;
    }

    @XmlTransient
    public List<MuestraAnalisisHistorico> getMuestraAnalisisHistoricoList() {
        return muestraAnalisisHistoricoList;
    }

    public void setMuestraAnalisisHistoricoList(List<MuestraAnalisisHistorico> muestraAnalisisHistoricoList) {
        this.muestraAnalisisHistoricoList = muestraAnalisisHistoricoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (muestraHistoricoPK != null ? muestraHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraHistorico)) {
            return false;
        }
        MuestraHistorico other = (MuestraHistorico) object;
        if ((this.muestraHistoricoPK == null && other.muestraHistoricoPK != null) || (this.muestraHistoricoPK != null && !this.muestraHistoricoPK.equals(other.muestraHistoricoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestraHistorico[ muestraHistoricoPK=" + muestraHistoricoPK + " ]";
    }
    
}
