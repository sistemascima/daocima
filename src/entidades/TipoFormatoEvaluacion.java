/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author Jhon
 */
@Entity
@Table(name = "tipo_formato_evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFormatoEvaluacion.findAll", query = "SELECT t FROM TipoFormatoEvaluacion t"),
    @NamedQuery(name = "TipoFormatoEvaluacion.findByPsTipforevaCodigo", query = "SELECT t FROM TipoFormatoEvaluacion t WHERE t.psTipforevaCodigo = :psTipforevaCodigo"),
    @NamedQuery(name = "TipoFormatoEvaluacion.findBySTipforevaNombre", query = "SELECT t FROM TipoFormatoEvaluacion t WHERE t.sTipforevaNombre = :sTipforevaNombre"),
    @NamedQuery(name = "TipoFormatoEvaluacion.findByDTipforevaCreacion", query = "SELECT t FROM TipoFormatoEvaluacion t WHERE t.dTipforevaCreacion = :dTipforevaCreacion"),
    @NamedQuery(name = "TipoFormatoEvaluacion.findByDTipforevaUltimodi", query = "SELECT t FROM TipoFormatoEvaluacion t WHERE t.dTipforevaUltimodi = :dTipforevaUltimodi")})
public class TipoFormatoEvaluacion implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoFormatoEvaluacion")
    private Collection<EvaluacionProveedor> evaluacionProveedorCollection;
    @JoinColumn(name = "fs_tipforeva_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsTipforevaUsuultmod;
    @JoinColumn(name = "fs_tipforeva_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsTipforevaUsuacrea;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_tipforeva_codigo")
    private String psTipforevaCodigo;
    @Basic(optional = false)
    @Column(name = "s_tipforeva_nombre")
    private String sTipforevaNombre;
    @Basic(optional = false)
    @Column(name = "d_tipforeva_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipforevaCreacion;
    @Column(name = "d_tipforeva_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTipforevaUltimodi;
    

    public TipoFormatoEvaluacion() {
    }

    public TipoFormatoEvaluacion(String psTipforevaCodigo) {
        this.psTipforevaCodigo = psTipforevaCodigo;
    }

    public TipoFormatoEvaluacion(String psTipforevaCodigo, String sTipforevaNombre, Date dTipforevaCreacion) {
        this.psTipforevaCodigo = psTipforevaCodigo;
        this.sTipforevaNombre = sTipforevaNombre;
        this.dTipforevaCreacion = dTipforevaCreacion;
    }

    public String getPsTipforevaCodigo() {
        return psTipforevaCodigo;
    }

    public void setPsTipforevaCodigo(String psTipforevaCodigo) {
        this.psTipforevaCodigo = psTipforevaCodigo;
    }

    public String getSTipforevaNombre() {
        return sTipforevaNombre;
    }

    public void setSTipforevaNombre(String sTipforevaNombre) {
        this.sTipforevaNombre = sTipforevaNombre;
    }

    public Date getDTipforevaCreacion() {
        return dTipforevaCreacion;
    }

    public void setDTipforevaCreacion(Date dTipforevaCreacion) {
        this.dTipforevaCreacion = dTipforevaCreacion;
    }

    public Date getDTipforevaUltimodi() {
        return dTipforevaUltimodi;
    }

    public void setDTipforevaUltimodi(Date dTipforevaUltimodi) {
        this.dTipforevaUltimodi = dTipforevaUltimodi;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psTipforevaCodigo != null ? psTipforevaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFormatoEvaluacion)) {
            return false;
        }
        TipoFormatoEvaluacion other = (TipoFormatoEvaluacion) object;
        if ((this.psTipforevaCodigo == null && other.psTipforevaCodigo != null) || (this.psTipforevaCodigo != null && !this.psTipforevaCodigo.equals(other.psTipforevaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoFormatoEvaluacion[ psTipforevaCodigo=" + psTipforevaCodigo + " ]";
    }

    @XmlTransient
    public Collection<EvaluacionProveedor> getEvaluacionProveedorCollection() {
        return evaluacionProveedorCollection;
    }

    public void setEvaluacionProveedorCollection(Collection<EvaluacionProveedor> evaluacionProveedorCollection) {
        this.evaluacionProveedorCollection = evaluacionProveedorCollection;
    }

    public SUsuario getFsTipforevaUsuultmod() {
        return fsTipforevaUsuultmod;
    }

    public void setFsTipforevaUsuultmod(SUsuario fsTipforevaUsuultmod) {
        this.fsTipforevaUsuultmod = fsTipforevaUsuultmod;
    }

    public SUsuario getFsTipforevaUsuacrea() {
        return fsTipforevaUsuacrea;
    }

    public void setFsTipforevaUsuacrea(SUsuario fsTipforevaUsuacrea) {
        this.fsTipforevaUsuacrea = fsTipforevaUsuacrea;
    }
    
}
