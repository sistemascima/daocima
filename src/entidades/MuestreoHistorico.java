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
 * @author TOSHIBA
 */
@Entity
@Table(name = "muestreo_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuestreoHistorico.findAll", query = "SELECT m FROM MuestreoHistorico m"),
    @NamedQuery(name = "MuestreoHistorico.findByPiMuestreoHistoricoId", query = "SELECT m FROM MuestreoHistorico m WHERE m.piMuestreoHistoricoId = :piMuestreoHistoricoId"),
    @NamedQuery(name = "MuestreoHistorico.findByDMuestreoHistoricoFecharecepcmuest", query = "SELECT m FROM MuestreoHistorico m WHERE m.dMuestreoHistoricoFecharecepcmuest = :dMuestreoHistoricoFecharecepcmuest"),
    @NamedQuery(name = "MuestreoHistorico.findByDMuestreoHistoricoFechainicio", query = "SELECT m FROM MuestreoHistorico m WHERE m.dMuestreoHistoricoFechainicio = :dMuestreoHistoricoFechainicio"),
    @NamedQuery(name = "MuestreoHistorico.findBySMuestreoHistoricoPlanmuestreo", query = "SELECT m FROM MuestreoHistorico m WHERE m.sMuestreoHistoricoPlanmuestreo = :sMuestreoHistoricoPlanmuestreo"),
    @NamedQuery(name = "MuestreoHistorico.findByFsMuestreoHistoricoTecnico", query = "SELECT m FROM MuestreoHistorico m WHERE m.fsMuestreoHistoricoTecnico = :fsMuestreoHistoricoTecnico"),
    @NamedQuery(name = "MuestreoHistorico.findByFiMuestreoHistoricoCiudad", query = "SELECT m FROM MuestreoHistorico m WHERE m.fiMuestreoHistoricoCiudad = :fiMuestreoHistoricoCiudad"),
    @NamedQuery(name = "MuestreoHistorico.findByFiMuestreoHistoricoMunicipio", query = "SELECT m FROM MuestreoHistorico m WHERE m.fiMuestreoHistoricoMunicipio = :fiMuestreoHistoricoMunicipio"),
    @NamedQuery(name = "MuestreoHistorico.findByDMuestreoHistoricoFechamodi", query = "SELECT m FROM MuestreoHistorico m WHERE m.dMuestreoHistoricoFechamodi = :dMuestreoHistoricoFechamodi"),
    @NamedQuery(name = "MuestreoHistorico.findByDMuestreoHistoricoFechacreac", query = "SELECT m FROM MuestreoHistorico m WHERE m.dMuestreoHistoricoFechacreac = :dMuestreoHistoricoFechacreac")})
public class MuestreoHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_muestreo_historico_id", nullable = false)
    private Integer piMuestreoHistoricoId;
    @Column(name = "d_muestreo_historico_fecharecepcmuest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoHistoricoFecharecepcmuest;
    @Column(name = "d_muestreo_historico_fechainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoHistoricoFechainicio;
    @Column(name = "s_muestreo_historico_planmuestreo", length = 225)
    private String sMuestreoHistoricoPlanmuestreo;
    @Column(name = "fs_muestreo_historico_tecnico", length = 45)
    private String fsMuestreoHistoricoTecnico;
    @Column(name = "fi_muestreo_historico_ciudad")
    private Integer fiMuestreoHistoricoCiudad;
    @Column(name = "fi_muestreo_historico_municipio")
    private Integer fiMuestreoHistoricoMunicipio;
    @Column(name = "d_muestreo_historico_fechamodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoHistoricoFechamodi;
    @Column(name = "d_muestreo_historico_fechacreac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMuestreoHistoricoFechacreac;
    @JoinColumn(name = "fs_muestreo_historico_usuacreacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsMuestreoHistoricoUsuacreacion;
    @JoinColumn(name = "fs_muestreo_historico_usuamodif", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsMuestreoHistoricoUsuamodif;
    @JoinColumn(name = "fi_muestreo_historico_punto", referencedColumnName = "pi_punto_id")
    @ManyToOne
    private Punto fiMuestreoHistoricoPunto;
    @OneToMany(mappedBy = "fiReporteHistoricoMuestreo")
    private List<ReporteHistorico> reporteHistoricoList;

    public MuestreoHistorico() {
    }

    public MuestreoHistorico(Integer piMuestreoHistoricoId) {
        this.piMuestreoHistoricoId = piMuestreoHistoricoId;
    }

    public Integer getPiMuestreoHistoricoId() {
        return piMuestreoHistoricoId;
    }

    public void setPiMuestreoHistoricoId(Integer piMuestreoHistoricoId) {
        this.piMuestreoHistoricoId = piMuestreoHistoricoId;
    }

    public Date getDMuestreoHistoricoFecharecepcmuest() {
        return dMuestreoHistoricoFecharecepcmuest;
    }

    public void setDMuestreoHistoricoFecharecepcmuest(Date dMuestreoHistoricoFecharecepcmuest) {
        this.dMuestreoHistoricoFecharecepcmuest = dMuestreoHistoricoFecharecepcmuest;
    }

    public Date getDMuestreoHistoricoFechainicio() {
        return dMuestreoHistoricoFechainicio;
    }

    public void setDMuestreoHistoricoFechainicio(Date dMuestreoHistoricoFechainicio) {
        this.dMuestreoHistoricoFechainicio = dMuestreoHistoricoFechainicio;
    }

    public String getSMuestreoHistoricoPlanmuestreo() {
        return sMuestreoHistoricoPlanmuestreo;
    }

    public void setSMuestreoHistoricoPlanmuestreo(String sMuestreoHistoricoPlanmuestreo) {
        this.sMuestreoHistoricoPlanmuestreo = sMuestreoHistoricoPlanmuestreo;
    }

    public String getFsMuestreoHistoricoTecnico() {
        return fsMuestreoHistoricoTecnico;
    }

    public void setFsMuestreoHistoricoTecnico(String fsMuestreoHistoricoTecnico) {
        this.fsMuestreoHistoricoTecnico = fsMuestreoHistoricoTecnico;
    }

    public Integer getFiMuestreoHistoricoCiudad() {
        return fiMuestreoHistoricoCiudad;
    }

    public void setFiMuestreoHistoricoCiudad(Integer fiMuestreoHistoricoCiudad) {
        this.fiMuestreoHistoricoCiudad = fiMuestreoHistoricoCiudad;
    }

    public Integer getFiMuestreoHistoricoMunicipio() {
        return fiMuestreoHistoricoMunicipio;
    }

    public void setFiMuestreoHistoricoMunicipio(Integer fiMuestreoHistoricoMunicipio) {
        this.fiMuestreoHistoricoMunicipio = fiMuestreoHistoricoMunicipio;
    }

    public Date getDMuestreoHistoricoFechamodi() {
        return dMuestreoHistoricoFechamodi;
    }

    public void setDMuestreoHistoricoFechamodi(Date dMuestreoHistoricoFechamodi) {
        this.dMuestreoHistoricoFechamodi = dMuestreoHistoricoFechamodi;
    }

    public Date getDMuestreoHistoricoFechacreac() {
        return dMuestreoHistoricoFechacreac;
    }

    public void setDMuestreoHistoricoFechacreac(Date dMuestreoHistoricoFechacreac) {
        this.dMuestreoHistoricoFechacreac = dMuestreoHistoricoFechacreac;
    }

    public SUsuario getFsMuestreoHistoricoUsuacreacion() {
        return fsMuestreoHistoricoUsuacreacion;
    }

    public void setFsMuestreoHistoricoUsuacreacion(SUsuario fsMuestreoHistoricoUsuacreacion) {
        this.fsMuestreoHistoricoUsuacreacion = fsMuestreoHistoricoUsuacreacion;
    }

    public SUsuario getFsMuestreoHistoricoUsuamodif() {
        return fsMuestreoHistoricoUsuamodif;
    }

    public void setFsMuestreoHistoricoUsuamodif(SUsuario fsMuestreoHistoricoUsuamodif) {
        this.fsMuestreoHistoricoUsuamodif = fsMuestreoHistoricoUsuamodif;
    }

    public Punto getFiMuestreoHistoricoPunto() {
        return fiMuestreoHistoricoPunto;
    }

    public void setFiMuestreoHistoricoPunto(Punto fiMuestreoHistoricoPunto) {
        this.fiMuestreoHistoricoPunto = fiMuestreoHistoricoPunto;
    }

    @XmlTransient
    public List<ReporteHistorico> getReporteHistoricoList() {
        return reporteHistoricoList;
    }

    public void setReporteHistoricoList(List<ReporteHistorico> reporteHistoricoList) {
        this.reporteHistoricoList = reporteHistoricoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piMuestreoHistoricoId != null ? piMuestreoHistoricoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuestreoHistorico)) {
            return false;
        }
        MuestreoHistorico other = (MuestreoHistorico) object;
        if ((this.piMuestreoHistoricoId == null && other.piMuestreoHistoricoId != null) || (this.piMuestreoHistoricoId != null && !this.piMuestreoHistoricoId.equals(other.piMuestreoHistoricoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MuestreoHistorico[ piMuestreoHistoricoId=" + piMuestreoHistoricoId + " ]";
    }
    
}
