/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "muestra_analisis_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestraAnalisisHistorico.findAll", query = "SELECT m FROM MuestraAnalisisHistorico m"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByPiMuestraAnalisisHistoricoId", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.muestraAnalisisHistoricoPK.piMuestraAnalisisHistoricoId = :piMuestraAnalisisHistoricoId"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByFsMuestraAnalisisHistoricoUsuacrea", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.fsMuestraAnalisisHistoricoUsuacrea = :fsMuestraAnalisisHistoricoUsuacrea"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByDMuestraAnalisisHistoricoFechaingreso", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.dMuestraAnalisisHistoricoFechaingreso = :dMuestraAnalisisHistoricoFechaingreso"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoNombre", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoNombre = :sMuestraAnalisisHistoricoNombre"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoValor", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoValor = :sMuestraAnalisisHistoricoValor"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoVariableDescripcion", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoVariableDescripcion = :sMuestraAnalisisHistoricoVariableDescripcion"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoProveedorNombre", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoProveedorNombre = :sMuestraAnalisisHistoricoProveedorNombre"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoProveedorVariableMetodo", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoProveedorVariableMetodo = :sMuestraAnalisisHistoricoProveedorVariableMetodo"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoProveedorVarlimcuan", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoProveedorVarlimcuan = :sMuestraAnalisisHistoricoProveedorVarlimcuan"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoProveedorVariableUnidad", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoProveedorVariableUnidad = :sMuestraAnalisisHistoricoProveedorVariableUnidad"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoProveedorNit", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoProveedorNit = :sMuestraAnalisisHistoricoProveedorNit"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByFiMuestraAnalisisHistoricoIdVariable", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.fiMuestraAnalisisHistoricoIdVariable = :fiMuestraAnalisisHistoricoIdVariable"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByFiMuestraAnalisisHistoricoTipoVariable", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.fiMuestraAnalisisHistoricoTipoVariable = :fiMuestraAnalisisHistoricoTipoVariable"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findBySMuestraAnalisisHistoricoNomenclatura", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.sMuestraAnalisisHistoricoNomenclatura = :sMuestraAnalisisHistoricoNomenclatura"),
    @NamedQuery(name = "MuestraAnalisisHistorico.findByFsMuestraAnalisisHistoricoReporteVersion", query = "SELECT m FROM MuestraAnalisisHistorico m WHERE m.muestraAnalisisHistoricoPK.fsMuestraAnalisisHistoricoReporteVersion = :fsMuestraAnalisisHistoricoReporteVersion")})
public class MuestraAnalisisHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuestraAnalisisHistoricoPK muestraAnalisisHistoricoPK;
    @Column(name = "fs_muestra_analisis_historico_usuacrea", length = 22)
    private String fsMuestraAnalisisHistoricoUsuacrea;
    @Column(name = "d_muestra_analisis_historico_fechaingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestraAnalisisHistoricoFechaingreso;
    @Column(name = "s_muestra_analisis_historico_nombre", length = 225)
    private String sMuestraAnalisisHistoricoNombre;
    @Column(name = "s_muestra_analisis_historico_valor", length = 225)
    private String sMuestraAnalisisHistoricoValor;
    @Column(name = "s_muestra_analisis_historico_variable_descripcion", length = 512)
    private String sMuestraAnalisisHistoricoVariableDescripcion;
    @Column(name = "s_muestra_analisis_historico_proveedor_nombre", length = 512)
    private String sMuestraAnalisisHistoricoProveedorNombre;
    @Column(name = "s_muestra_analisis_historico_proveedor_variable_metodo", length = 256)
    private String sMuestraAnalisisHistoricoProveedorVariableMetodo;
    @Column(name = "s_muestra_analisis_historico_proveedor_varlimcuan", length = 620)
    private String sMuestraAnalisisHistoricoProveedorVarlimcuan;
    @Column(name = "s_muestra_analisis_historico_proveedor_variable_unidad", length = 45)
    private String sMuestraAnalisisHistoricoProveedorVariableUnidad;
    @Column(name = "s_muestra_analisis_historico_proveedor_nit", length = 512)
    private String sMuestraAnalisisHistoricoProveedorNit;
    @Column(name = "fi_muestra_analisis_historico_id_variable")
    private Integer fiMuestraAnalisisHistoricoIdVariable;
    @Column(name = "fi_muestra_analisis_historico_tipo_variable")
    private Integer fiMuestraAnalisisHistoricoTipoVariable;
    @Column(name = "s_muestra_analisis_historico_nomenclatura", length = 45)
    private String sMuestraAnalisisHistoricoNomenclatura;
    @JoinColumns({
        @JoinColumn(name = "fi_muestra_analisis_historico_muestra", referencedColumnName = "pi_muestra_historico_id"),
        @JoinColumn(name = "fs_muestra_analisis_historico_reporte_version", referencedColumnName = "fi_muestra_historico_reporte_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private MuestraHistorico muestraHistorico;
    @JoinColumns({
        @JoinColumn(name = "fs_muestra_analisis_historico_reporte", referencedColumnName = "pi_reporte_historico_id"),
        @JoinColumn(name = "fs_muestra_analisis_historico_reporte_version", referencedColumnName = "i_reporte_historico_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ReporteHistorico reporteHistorico;

    public MuestraAnalisisHistorico() {
    }

    public MuestraAnalisisHistorico(MuestraAnalisisHistoricoPK muestraAnalisisHistoricoPK) {
        this.muestraAnalisisHistoricoPK = muestraAnalisisHistoricoPK;
    }

    public MuestraAnalisisHistorico(int piMuestraAnalisisHistoricoId, int fsMuestraAnalisisHistoricoReporteVersion) {
        this.muestraAnalisisHistoricoPK = new MuestraAnalisisHistoricoPK(piMuestraAnalisisHistoricoId, fsMuestraAnalisisHistoricoReporteVersion);
    }

    public MuestraAnalisisHistoricoPK getMuestraAnalisisHistoricoPK() {
        return muestraAnalisisHistoricoPK;
    }

    public void setMuestraAnalisisHistoricoPK(MuestraAnalisisHistoricoPK muestraAnalisisHistoricoPK) {
        this.muestraAnalisisHistoricoPK = muestraAnalisisHistoricoPK;
    }

    public String getFsMuestraAnalisisHistoricoUsuacrea() {
        return fsMuestraAnalisisHistoricoUsuacrea;
    }

    public void setFsMuestraAnalisisHistoricoUsuacrea(String fsMuestraAnalisisHistoricoUsuacrea) {
        this.fsMuestraAnalisisHistoricoUsuacrea = fsMuestraAnalisisHistoricoUsuacrea;
    }

    public Date getDMuestraAnalisisHistoricoFechaingreso() {
        return dMuestraAnalisisHistoricoFechaingreso;
    }

    public void setDMuestraAnalisisHistoricoFechaingreso(Date dMuestraAnalisisHistoricoFechaingreso) {
        this.dMuestraAnalisisHistoricoFechaingreso = dMuestraAnalisisHistoricoFechaingreso;
    }

    public String getSMuestraAnalisisHistoricoNombre() {
        return sMuestraAnalisisHistoricoNombre;
    }

    public void setSMuestraAnalisisHistoricoNombre(String sMuestraAnalisisHistoricoNombre) {
        this.sMuestraAnalisisHistoricoNombre = sMuestraAnalisisHistoricoNombre;
    }

    public String getSMuestraAnalisisHistoricoValor() {
        return sMuestraAnalisisHistoricoValor;
    }

    public void setSMuestraAnalisisHistoricoValor(String sMuestraAnalisisHistoricoValor) {
        this.sMuestraAnalisisHistoricoValor = sMuestraAnalisisHistoricoValor;
    }

    public String getSMuestraAnalisisHistoricoVariableDescripcion() {
        return sMuestraAnalisisHistoricoVariableDescripcion;
    }

    public void setSMuestraAnalisisHistoricoVariableDescripcion(String sMuestraAnalisisHistoricoVariableDescripcion) {
        this.sMuestraAnalisisHistoricoVariableDescripcion = sMuestraAnalisisHistoricoVariableDescripcion;
    }

    public String getSMuestraAnalisisHistoricoProveedorNombre() {
        return sMuestraAnalisisHistoricoProveedorNombre;
    }

    public void setSMuestraAnalisisHistoricoProveedorNombre(String sMuestraAnalisisHistoricoProveedorNombre) {
        this.sMuestraAnalisisHistoricoProveedorNombre = sMuestraAnalisisHistoricoProveedorNombre;
    }

    public String getSMuestraAnalisisHistoricoProveedorVariableMetodo() {
        return sMuestraAnalisisHistoricoProveedorVariableMetodo;
    }

    public void setSMuestraAnalisisHistoricoProveedorVariableMetodo(String sMuestraAnalisisHistoricoProveedorVariableMetodo) {
        this.sMuestraAnalisisHistoricoProveedorVariableMetodo = sMuestraAnalisisHistoricoProveedorVariableMetodo;
    }

    public String getSMuestraAnalisisHistoricoProveedorVarlimcuan() {
        return sMuestraAnalisisHistoricoProveedorVarlimcuan;
    }

    public void setSMuestraAnalisisHistoricoProveedorVarlimcuan(String sMuestraAnalisisHistoricoProveedorVarlimcuan) {
        this.sMuestraAnalisisHistoricoProveedorVarlimcuan = sMuestraAnalisisHistoricoProveedorVarlimcuan;
    }

    public String getSMuestraAnalisisHistoricoProveedorVariableUnidad() {
        return sMuestraAnalisisHistoricoProveedorVariableUnidad;
    }

    public void setSMuestraAnalisisHistoricoProveedorVariableUnidad(String sMuestraAnalisisHistoricoProveedorVariableUnidad) {
        this.sMuestraAnalisisHistoricoProveedorVariableUnidad = sMuestraAnalisisHistoricoProveedorVariableUnidad;
    }

    public String getSMuestraAnalisisHistoricoProveedorNit() {
        return sMuestraAnalisisHistoricoProveedorNit;
    }

    public void setSMuestraAnalisisHistoricoProveedorNit(String sMuestraAnalisisHistoricoProveedorNit) {
        this.sMuestraAnalisisHistoricoProveedorNit = sMuestraAnalisisHistoricoProveedorNit;
    }

    public Integer getFiMuestraAnalisisHistoricoIdVariable() {
        return fiMuestraAnalisisHistoricoIdVariable;
    }

    public void setFiMuestraAnalisisHistoricoIdVariable(Integer fiMuestraAnalisisHistoricoIdVariable) {
        this.fiMuestraAnalisisHistoricoIdVariable = fiMuestraAnalisisHistoricoIdVariable;
    }

    public Integer getFiMuestraAnalisisHistoricoTipoVariable() {
        return fiMuestraAnalisisHistoricoTipoVariable;
    }

    public void setFiMuestraAnalisisHistoricoTipoVariable(Integer fiMuestraAnalisisHistoricoTipoVariable) {
        this.fiMuestraAnalisisHistoricoTipoVariable = fiMuestraAnalisisHistoricoTipoVariable;
    }

    public String getSMuestraAnalisisHistoricoNomenclatura() {
        return sMuestraAnalisisHistoricoNomenclatura;
    }

    public void setSMuestraAnalisisHistoricoNomenclatura(String sMuestraAnalisisHistoricoNomenclatura) {
        this.sMuestraAnalisisHistoricoNomenclatura = sMuestraAnalisisHistoricoNomenclatura;
    }

    public MuestraHistorico getMuestraHistorico() {
        return muestraHistorico;
    }

    public void setMuestraHistorico(MuestraHistorico muestraHistorico) {
        this.muestraHistorico = muestraHistorico;
    }

    public ReporteHistorico getReporteHistorico() {
        return reporteHistorico;
    }

    public void setReporteHistorico(ReporteHistorico reporteHistorico) {
        this.reporteHistorico = reporteHistorico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (muestraAnalisisHistoricoPK != null ? muestraAnalisisHistoricoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestraAnalisisHistorico)) {
            return false;
        }
        MuestraAnalisisHistorico other = (MuestraAnalisisHistorico) object;
        if ((this.muestraAnalisisHistoricoPK == null && other.muestraAnalisisHistoricoPK != null) || (this.muestraAnalisisHistoricoPK != null && !this.muestraAnalisisHistoricoPK.equals(other.muestraAnalisisHistoricoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestraAnalisisHistorico[ muestraAnalisisHistoricoPK=" + muestraAnalisisHistoricoPK + " ]";
    }
    
}
