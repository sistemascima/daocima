/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "evaluacion_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluacionProveedor.findAll", query = "SELECT e FROM EvaluacionProveedor e"),
    @NamedQuery(name = "EvaluacionProveedor.findByPfsEvalprovTipo", query = "SELECT e FROM EvaluacionProveedor e WHERE e.evaluacionProveedorPK.pfsEvalprovTipo = :pfsEvalprovTipo"),
    @NamedQuery(name = "EvaluacionProveedor.findByPiEvalprovNumeeval", query = "SELECT e FROM EvaluacionProveedor e WHERE e.evaluacionProveedorPK.piEvalprovNumeeval = :piEvalprovNumeeval"),
    @NamedQuery(name = "EvaluacionProveedor.findByPfsEvalprovProveedor", query = "SELECT e FROM EvaluacionProveedor e WHERE e.evaluacionProveedorPK.pfsEvalprovProveedor = :pfsEvalprovProveedor"),
    @NamedQuery(name = "EvaluacionProveedor.findBySEvalprovEstado", query = "SELECT e FROM EvaluacionProveedor e WHERE e.sEvalprovEstado = :sEvalprovEstado"),
    @NamedQuery(name = "EvaluacionProveedor.findBySEvalprovDesporeva", query = "SELECT e FROM EvaluacionProveedor e WHERE e.sEvalprovDesporeva = :sEvalprovDesporeva"),
    @NamedQuery(name = "EvaluacionProveedor.findByDEvalprovFecheval", query = "SELECT e FROM EvaluacionProveedor e WHERE e.dEvalprovFecheval = :dEvalprovFecheval"),
    @NamedQuery(name = "EvaluacionProveedor.findBySEvalprovObservaci", query = "SELECT e FROM EvaluacionProveedor e WHERE e.sEvalprovObservaci = :sEvalprovObservaci"),
    @NamedQuery(name = "EvaluacionProveedor.findByDEvalprovCreacion", query = "SELECT e FROM EvaluacionProveedor e WHERE e.dEvalprovCreacion = :dEvalprovCreacion"),
    @NamedQuery(name = "EvaluacionProveedor.findByDEvalprovUltimodi", query = "SELECT e FROM EvaluacionProveedor e WHERE e.dEvalprovUltimodi = :dEvalprovUltimodi")})
public class EvaluacionProveedor implements Serializable {

    @Basic(optional = false)
    @Column(name = "s_evalprov_estado")
    private Character sEvalprovEstado;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluacionProveedorPK evaluacionProveedorPK;
    @Column(name = "s_evalprov_desporeva")
    private String sEvalprovDesporeva;
    @Column(name = "d_evalprov_fecheval")
    @Temporal(TemporalType.DATE)
    private Date dEvalprovFecheval;
    @Column(name = "s_evalprov_observaci")
    private String sEvalprovObservaci;
    @Basic(optional = false)
    @Column(name = "d_evalprov_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEvalprovCreacion;
    @Column(name = "d_evalprov_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEvalprovUltimodi;
    @JoinColumn(name = "fs_evalprov_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsEvalprovUsuultmod;
    @JoinColumn(name = "fs_evalprov_usuaeval", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsEvalprovUsuaeval;
    @JoinColumn(name = "fs_evalprov_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsEvalprovUsuacrea;
    @JoinColumn(name = "pfs_evalprov_tipo", referencedColumnName = "ps_tipforeva_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoFormatoEvaluacion tipoFormatoEvaluacion;
    @JoinColumn(name = "pfs_evalprov_proveedor", referencedColumnName = "ps_proveedor_nit", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @JoinColumn(name = "fs_evalprov_cargeval", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsEvalprovCargeval;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluacionProveedor")
    private Collection<RespEvalProv> respEvalProvCollection;

    public EvaluacionProveedor() {
    }

    public EvaluacionProveedor(EvaluacionProveedorPK evaluacionProveedorPK) {
        this.evaluacionProveedorPK = evaluacionProveedorPK;
    }

    public EvaluacionProveedor(EvaluacionProveedorPK evaluacionProveedorPK, char sEvalprovEstado, Date dEvalprovCreacion) {
        this.evaluacionProveedorPK = evaluacionProveedorPK;
        this.sEvalprovEstado = sEvalprovEstado;
        this.dEvalprovCreacion = dEvalprovCreacion;
    }

    public EvaluacionProveedor(String pfsEvalprovTipo, int piEvalprovNumeeval, String pfsEvalprovProveedor) {
        this.evaluacionProveedorPK = new EvaluacionProveedorPK(pfsEvalprovTipo, piEvalprovNumeeval, pfsEvalprovProveedor);
    }

    public EvaluacionProveedorPK getEvaluacionProveedorPK() {
        return evaluacionProveedorPK;
    }

    public void setEvaluacionProveedorPK(EvaluacionProveedorPK evaluacionProveedorPK) {
        this.evaluacionProveedorPK = evaluacionProveedorPK;
    }

    public char getSEvalprovEstado() {
        return sEvalprovEstado;
    }

    public void setSEvalprovEstado(char sEvalprovEstado) {
        this.sEvalprovEstado = sEvalprovEstado;
    }

    public String getSEvalprovDesporeva() {
        return sEvalprovDesporeva;
    }

    public void setSEvalprovDesporeva(String sEvalprovDesporeva) {
        this.sEvalprovDesporeva = sEvalprovDesporeva;
    }

    public Date getDEvalprovFecheval() {
        return dEvalprovFecheval;
    }

    public void setDEvalprovFecheval(Date dEvalprovFecheval) {
        this.dEvalprovFecheval = dEvalprovFecheval;
    }

    public String getSEvalprovObservaci() {
        return sEvalprovObservaci;
    }

    public void setSEvalprovObservaci(String sEvalprovObservaci) {
        this.sEvalprovObservaci = sEvalprovObservaci;
    }

    public Date getDEvalprovCreacion() {
        return dEvalprovCreacion;
    }

    public void setDEvalprovCreacion(Date dEvalprovCreacion) {
        this.dEvalprovCreacion = dEvalprovCreacion;
    }

    public Date getDEvalprovUltimodi() {
        return dEvalprovUltimodi;
    }

    public void setDEvalprovUltimodi(Date dEvalprovUltimodi) {
        this.dEvalprovUltimodi = dEvalprovUltimodi;
    }

    public SUsuario getFsEvalprovUsuultmod() {
        return fsEvalprovUsuultmod;
    }

    public void setFsEvalprovUsuultmod(SUsuario fsEvalprovUsuultmod) {
        this.fsEvalprovUsuultmod = fsEvalprovUsuultmod;
    }

    public SUsuario getFsEvalprovUsuaeval() {
        return fsEvalprovUsuaeval;
    }

    public void setFsEvalprovUsuaeval(SUsuario fsEvalprovUsuaeval) {
        this.fsEvalprovUsuaeval = fsEvalprovUsuaeval;
    }

    public SUsuario getFsEvalprovUsuacrea() {
        return fsEvalprovUsuacrea;
    }

    public void setFsEvalprovUsuacrea(SUsuario fsEvalprovUsuacrea) {
        this.fsEvalprovUsuacrea = fsEvalprovUsuacrea;
    }

    public TipoFormatoEvaluacion getTipoFormatoEvaluacion() {
        return tipoFormatoEvaluacion;
    }

    public void setTipoFormatoEvaluacion(TipoFormatoEvaluacion tipoFormatoEvaluacion) {
        this.tipoFormatoEvaluacion = tipoFormatoEvaluacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Cargo getFsEvalprovCargeval() {
        return fsEvalprovCargeval;
    }

    public void setFsEvalprovCargeval(Cargo fsEvalprovCargeval) {
        this.fsEvalprovCargeval = fsEvalprovCargeval;
    }

    @XmlTransient
    public Collection<RespEvalProv> getRespEvalProvCollection() {
        return respEvalProvCollection;
    }

    public void setRespEvalProvCollection(Collection<RespEvalProv> respEvalProvCollection) {
        this.respEvalProvCollection = respEvalProvCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionProveedorPK != null ? evaluacionProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionProveedor)) {
            return false;
        }
        EvaluacionProveedor other = (EvaluacionProveedor) object;
        if ((this.evaluacionProveedorPK == null && other.evaluacionProveedorPK != null) || (this.evaluacionProveedorPK != null && !this.evaluacionProveedorPK.equals(other.evaluacionProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return evaluacionProveedorPK + "";
    }



    

    public void setSEvalprovEstado(Character sEvalprovEstado) {
        this.sEvalprovEstado = sEvalprovEstado;
    }
}
