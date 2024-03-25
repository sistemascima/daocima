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
@Table(name = "tipo_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAnalisis.findAll", query = "SELECT t FROM TipoAnalisis t"),
    @NamedQuery(name = "TipoAnalisis.findByPiTipoanalId", query = "SELECT t FROM TipoAnalisis t WHERE t.piTipoanalId = :piTipoanalId"),
    @NamedQuery(name = "TipoAnalisis.findBySTipoanalDescripcion", query = "SELECT t FROM TipoAnalisis t WHERE t.sTipoanalDescripcion = :sTipoanalDescripcion"),
    @NamedQuery(name = "TipoAnalisis.findByDTipoanalCreacion", query = "SELECT t FROM TipoAnalisis t WHERE t.dTipoanalCreacion = :dTipoanalCreacion"),
    @NamedQuery(name = "TipoAnalisis.findByDTipoanalUltimodi", query = "SELECT t FROM TipoAnalisis t WHERE t.dTipoanalUltimodi = :dTipoanalUltimodi"),
    @NamedQuery(name = "TipoAnalisis.findByNomenclatura", query = "SELECT t FROM TipoAnalisis t WHERE t.nomenclatura = :nomenclatura")})
public class TipoAnalisis implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_tipoanal_id", nullable = false)
    private Integer piTipoanalId;
    @Basic(optional = false)
    @Column(name = "s_tipoanal_descripcion", nullable = false, length = 80)
    private String sTipoanalDescripcion;
    @Basic(optional = false)
    @Column(name = "d_tipoanal_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipoanalCreacion;
    @Column(name = "d_tipoanal_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipoanalUltimodi;
    @Column(name = "s_tipoanal_nomenclatura", length = 45)
    private String nomenclatura;
    @JoinColumn(name = "fs_tipoanal_usuacrea", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario fsTipoanalUsuacrea;
    @JoinColumn(name = "fs_tipoanal_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsTipoanalUsuultmod;
    
   
    public TipoAnalisis() {
    }

    public TipoAnalisis(Integer piTipoanalId) {
        this.piTipoanalId = piTipoanalId;
    }

    public TipoAnalisis(Integer piTipoanalId, String sTipoanalDescripcion, Date dTipoanalCreacion) {
        this.piTipoanalId = piTipoanalId;
        this.sTipoanalDescripcion = sTipoanalDescripcion;
        this.dTipoanalCreacion = dTipoanalCreacion;
    }

    public Integer getPiTipoanalId() {
        return piTipoanalId;
    }

    public void setPiTipoanalId(Integer piTipoanalId) {
        this.piTipoanalId = piTipoanalId;
    }

    public String getSTipoanalDescripcion() {
        return sTipoanalDescripcion;
    }

    public void setSTipoanalDescripcion(String sTipoanalDescripcion) {
        this.sTipoanalDescripcion = sTipoanalDescripcion;
    }

    public Date getDTipoanalCreacion() {
        return dTipoanalCreacion;
    }

    public void setDTipoanalCreacion(Date dTipoanalCreacion) {
        this.dTipoanalCreacion = dTipoanalCreacion;
    }

    public Date getDTipoanalUltimodi() {
        return dTipoanalUltimodi;
    }

    public void setDTipoanalUltimodi(Date dTipoanalUltimodi) {
        this.dTipoanalUltimodi = dTipoanalUltimodi;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public SUsuario getFsTipoanalUsuacrea() {
        return fsTipoanalUsuacrea;
    }

    public void setFsTipoanalUsuacrea(SUsuario fsTipoanalUsuacrea) {
        this.fsTipoanalUsuacrea = fsTipoanalUsuacrea;
    }

    public SUsuario getFsTipoanalUsuultmod() {
        return fsTipoanalUsuultmod;
    }

    public void setFsTipoanalUsuultmod(SUsuario fsTipoanalUsuultmod) {
        this.fsTipoanalUsuultmod = fsTipoanalUsuultmod;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piTipoanalId != null ? piTipoanalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAnalisis)) {
            return false;
        }
        TipoAnalisis other = (TipoAnalisis) object;
        if ((this.piTipoanalId == null && other.piTipoanalId != null) || (this.piTipoanalId != null && !this.piTipoanalId.equals(other.piTipoanalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sTipoanalDescripcion;
    }

    
    
}
