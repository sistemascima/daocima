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
@Table(name = "resp_sele_prov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespSeleProv.findAll", query = "SELECT r FROM RespSeleProv r"),
    @NamedQuery(name = "RespSeleProv.findByPfiResselproNumeeval", query = "SELECT r FROM RespSeleProv r WHERE r.respSeleProvPK.pfiResselproNumeeval = :pfiResselproNumeeval"),
    @NamedQuery(name = "RespSeleProv.findByPfiResselproFormeval", query = "SELECT r FROM RespSeleProv r WHERE r.respSeleProvPK.pfiResselproFormeval = :pfiResselproFormeval"),
    @NamedQuery(name = "RespSeleProv.findByPfsResselproAspecto", query = "SELECT r FROM RespSeleProv r WHERE r.respSeleProvPK.pfsResselproAspecto = :pfsResselproAspecto"),
    @NamedQuery(name = "RespSeleProv.findByPfiResselproOrden", query = "SELECT r FROM RespSeleProv r WHERE r.respSeleProvPK.pfiResselproOrden = :pfiResselproOrden"),
    @NamedQuery(name = "RespSeleProv.findByPfsResselproProveedor", query = "SELECT r FROM RespSeleProv r WHERE r.respSeleProvPK.pfsResselproProveedor = :pfsResselproProveedor"),
    @NamedQuery(name = "RespSeleProv.findByDbResselproPuntaje", query = "SELECT r FROM RespSeleProv r WHERE r.dbResselproPuntaje = :dbResselproPuntaje"),
    @NamedQuery(name = "RespSeleProv.findBySResselproNoaplica", query = "SELECT r FROM RespSeleProv r WHERE r.sResselproNoaplica = :sResselproNoaplica"),
    @NamedQuery(name = "RespSeleProv.findByDResselproCreacion", query = "SELECT r FROM RespSeleProv r WHERE r.dResselproCreacion = :dResselproCreacion"),
    @NamedQuery(name = "RespSeleProv.findByDResselproUltimodi", query = "SELECT r FROM RespSeleProv r WHERE r.dResselproUltimodi = :dResselproUltimodi")})
public class RespSeleProv implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespSeleProvPK respSeleProvPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_resselpro_puntaje")
    private BigDecimal dbResselproPuntaje;
    @Column(name = "s_resselpro_noaplica")
    private Character sResselproNoaplica;
    @Basic(optional = false)
    @Column(name = "d_resselpro_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResselproCreacion;
    @Column(name = "d_resselpro_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dResselproUltimodi;
    @JoinColumn(name = "pfi_resselpro_numeeval", referencedColumnName = "pi_seprsoco_numeeval", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SeleProvSoliComp seleProvSoliComp;
    @JoinColumns({
        @JoinColumn(name = "pfi_resselpro_formeval", referencedColumnName = "pfi_pregunta_formeval", insertable = false, updatable = false),
        @JoinColumn(name = "pfs_resselpro_aspecto", referencedColumnName = "pfs_pregunta_aspecto", insertable = false, updatable = false),
        @JoinColumn(name = "pfi_resselpro_orden", referencedColumnName = "pi_pregunta_orden", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public RespSeleProv() {
    }

    public RespSeleProv(RespSeleProvPK respSeleProvPK) {
        this.respSeleProvPK = respSeleProvPK;
    }

    public RespSeleProv(RespSeleProvPK respSeleProvPK, Date dResselproCreacion) {
        this.respSeleProvPK = respSeleProvPK;
        this.dResselproCreacion = dResselproCreacion;
    }

    public RespSeleProv(int pfiResselproNumeeval, int pfiResselproFormeval, String pfsResselproAspecto, int pfiResselproOrden, String pfsResselproProveedor) {
        this.respSeleProvPK = new RespSeleProvPK(pfiResselproNumeeval, pfiResselproFormeval, pfsResselproAspecto, pfiResselproOrden, pfsResselproProveedor);
    }

    public RespSeleProvPK getRespSeleProvPK() {
        return respSeleProvPK;
    }

    public void setRespSeleProvPK(RespSeleProvPK respSeleProvPK) {
        this.respSeleProvPK = respSeleProvPK;
    }

    public BigDecimal getDbResselproPuntaje() {
        return dbResselproPuntaje;
    }

    public void setDbResselproPuntaje(BigDecimal dbResselproPuntaje) {
        this.dbResselproPuntaje = dbResselproPuntaje;
    }

    public Character getSResselproNoaplica() {
        return sResselproNoaplica;
    }

    public void setSResselproNoaplica(Character sResselproNoaplica) {
        this.sResselproNoaplica = sResselproNoaplica;
    }

    public Date getDResselproCreacion() {
        return dResselproCreacion;
    }

    public void setDResselproCreacion(Date dResselproCreacion) {
        this.dResselproCreacion = dResselproCreacion;
    }

    public Date getDResselproUltimodi() {
        return dResselproUltimodi;
    }

    public void setDResselproUltimodi(Date dResselproUltimodi) {
        this.dResselproUltimodi = dResselproUltimodi;
    }

    public SeleProvSoliComp getSeleProvSoliComp() {
        return seleProvSoliComp;
    }

    public void setSeleProvSoliComp(SeleProvSoliComp seleProvSoliComp) {
        this.seleProvSoliComp = seleProvSoliComp;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respSeleProvPK != null ? respSeleProvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespSeleProv)) {
            return false;
        }
        RespSeleProv other = (RespSeleProv) object;
        if ((this.respSeleProvPK == null && other.respSeleProvPK != null) || (this.respSeleProvPK != null && !this.respSeleProvPK.equals(other.respSeleProvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RespSeleProv[ respSeleProvPK=" + respSeleProvPK + " ]";
    }
    
}
