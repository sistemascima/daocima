/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author Jhon
 */
@Entity
@Table(name = "resp_eval_prov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespEvalProv.findAll", query = "SELECT r FROM RespEvalProv r"),
    @NamedQuery(name = "RespEvalProv.findByPfsResevaproTipo", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfsResevaproTipo = :pfsResevaproTipo"),
    @NamedQuery(name = "RespEvalProv.findByPfiResevaproNumeeval", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfiResevaproNumeeval = :pfiResevaproNumeeval"),
    @NamedQuery(name = "RespEvalProv.findByPfsResevaproProveedor", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfsResevaproProveedor = :pfsResevaproProveedor"),
    @NamedQuery(name = "RespEvalProv.findByPfiResevaproFormeval", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfiResevaproFormeval = :pfiResevaproFormeval"),
    @NamedQuery(name = "RespEvalProv.findByPfsResevaproAspecto", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfsResevaproAspecto = :pfsResevaproAspecto"),
    @NamedQuery(name = "RespEvalProv.findByPfiResevaproOrden", query = "SELECT r FROM RespEvalProv r WHERE r.respEvalProvPK.pfiResevaproOrden = :pfiResevaproOrden"),
    @NamedQuery(name = "RespEvalProv.findByDbResevaproPuntaje", query = "SELECT r FROM RespEvalProv r WHERE r.dbResevaproPuntaje = :dbResevaproPuntaje"),
    @NamedQuery(name = "RespEvalProv.findBySResevaproNoaplica", query = "SELECT r FROM RespEvalProv r WHERE r.sResevaproNoaplica = :sResevaproNoaplica"),
    @NamedQuery(name = "RespEvalProv.findByDResevaproCreacion", query = "SELECT r FROM RespEvalProv r WHERE r.dResevaproCreacion = :dResevaproCreacion"),
    @NamedQuery(name = "RespEvalProv.findByDResevaproUltimodi", query = "SELECT r FROM RespEvalProv r WHERE r.dResevaproUltimodi = :dResevaproUltimodi")})
public class RespEvalProv implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespEvalProvPK respEvalProvPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_resevapro_puntaje")
    private BigDecimal dbResevaproPuntaje;
    @Column(name = "s_resevapro_noaplica")
    private Character sResevaproNoaplica;
    @Basic(optional = false)
    @Column(name = "d_resevapro_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResevaproCreacion;
    @Column(name = "d_resevapro_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResevaproUltimodi;
    @JoinColumn(name = "fs_resevapro_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsResevaproUsuultmod;
    @JoinColumn(name = "fs_resevapro_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsResevaproUsuacrea;
    @JoinColumns({
        @JoinColumn(name = "pfi_resevapro_formeval", referencedColumnName = "pfi_pregunta_formeval", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_resevapro_aspecto", referencedColumnName = "pfs_pregunta_aspecto", insertable = false, updatable = false),
        @JoinColumn(name = "pfi_resevapro_orden", referencedColumnName = "pi_pregunta_orden", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @JoinColumns({
        @JoinColumn(name = "pfs_resevapro_tipo", referencedColumnName = "pfs_evalprov_tipo", insertable = false, updatable = false),
        @JoinColumn(name = "pfi_resevapro_numeeval", referencedColumnName = "pi_evalprov_numeeval", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_resevapro_proveedor", referencedColumnName = "pfs_evalprov_proveedor", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EvaluacionProveedor evaluacionProveedor;

    public RespEvalProv() {
    }

    public RespEvalProv(RespEvalProvPK respEvalProvPK) {
        this.respEvalProvPK = respEvalProvPK;
    }

    public RespEvalProv(RespEvalProvPK respEvalProvPK, Date dResevaproCreacion) {
        this.respEvalProvPK = respEvalProvPK;
        this.dResevaproCreacion = dResevaproCreacion;
    }

    public RespEvalProv(String pfsResevaproTipo, int pfiResevaproNumeeval, String pfsResevaproProveedor, int pfiResevaproFormeval, String pfsResevaproAspecto, int pfiResevaproOrden) {
        this.respEvalProvPK = new RespEvalProvPK(pfsResevaproTipo, pfiResevaproNumeeval, pfsResevaproProveedor, pfiResevaproFormeval, pfsResevaproAspecto, pfiResevaproOrden);
    }

    public RespEvalProvPK getRespEvalProvPK() {
        return respEvalProvPK;
    }

    public void setRespEvalProvPK(RespEvalProvPK respEvalProvPK) {
        this.respEvalProvPK = respEvalProvPK;
    }

    public BigDecimal getDbResevaproPuntaje() {
        return dbResevaproPuntaje;
    }

    public void setDbResevaproPuntaje(BigDecimal dbResevaproPuntaje) {
        this.dbResevaproPuntaje = dbResevaproPuntaje;
    }

    public Character getSResevaproNoaplica() {
        return sResevaproNoaplica;
    }

    public void setSResevaproNoaplica(Character sResevaproNoaplica) {
        this.sResevaproNoaplica = sResevaproNoaplica;
    }

    public Date getDResevaproCreacion() {
        return dResevaproCreacion;
    }

    public void setDResevaproCreacion(Date dResevaproCreacion) {
        this.dResevaproCreacion = dResevaproCreacion;
    }

    public Date getDResevaproUltimodi() {
        return dResevaproUltimodi;
    }

    public void setDResevaproUltimodi(Date dResevaproUltimodi) {
        this.dResevaproUltimodi = dResevaproUltimodi;
    }

    public SUsuario getFsResevaproUsuultmod() {
        return fsResevaproUsuultmod;
    }

    public void setFsResevaproUsuultmod(SUsuario fsResevaproUsuultmod) {
        this.fsResevaproUsuultmod = fsResevaproUsuultmod;
    }

    public SUsuario getFsResevaproUsuacrea() {
        return fsResevaproUsuacrea;
    }

    public void setFsResevaproUsuacrea(SUsuario fsResevaproUsuacrea) {
        this.fsResevaproUsuacrea = fsResevaproUsuacrea;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public EvaluacionProveedor getEvaluacionProveedor() {
        return evaluacionProveedor;
    }

    public void setEvaluacionProveedor(EvaluacionProveedor evaluacionProveedor) {
        this.evaluacionProveedor = evaluacionProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respEvalProvPK != null ? respEvalProvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespEvalProv)) {
            return false;
        }
        RespEvalProv other = (RespEvalProv) object;
        if ((this.respEvalProvPK == null && other.respEvalProvPK != null) || (this.respEvalProvPK != null && !this.respEvalProvPK.equals(other.respEvalProvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RespEvalProv[ respEvalProvPK=" + respEvalProvPK + " ]";
    }
    
}
