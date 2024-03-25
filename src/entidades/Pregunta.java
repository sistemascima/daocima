/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByPfiPreguntaFormeval", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.pfiPreguntaFormeval = :pfiPreguntaFormeval"),
    @NamedQuery(name = "Pregunta.findByPfsPreguntaAspecto", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.pfsPreguntaAspecto = :pfsPreguntaAspecto"),
    @NamedQuery(name = "Pregunta.findByPiPreguntaOrden", query = "SELECT p FROM Pregunta p WHERE p.preguntaPK.piPreguntaOrden = :piPreguntaOrden"),
    @NamedQuery(name = "Pregunta.findBySPreguntaEnunciado", query = "SELECT p FROM Pregunta p WHERE p.sPreguntaEnunciado = :sPreguntaEnunciado"),
    @NamedQuery(name = "Pregunta.findByDbPreguntaPonderaci", query = "SELECT p FROM Pregunta p WHERE p.dbPreguntaPonderaci = :dbPreguntaPonderaci"),
    @NamedQuery(name = "Pregunta.findByDPreguntaCreacion", query = "SELECT p FROM Pregunta p WHERE p.dPreguntaCreacion = :dPreguntaCreacion"),
    @NamedQuery(name = "Pregunta.findByDPreguntaUltimodi", query = "SELECT p FROM Pregunta p WHERE p.dPreguntaUltimodi = :dPreguntaUltimodi")})
public class Pregunta implements Serializable {
    @JoinColumn(name = "fs_pregunta_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsPreguntaUsuultmod;
    @JoinColumn(name = "fs_pregunta_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsPreguntaUsuacrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private Collection<RespEvalProv> respEvalProvCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreguntaPK preguntaPK;
    @Basic(optional = false)
    @Column(name = "s_pregunta_enunciado")
    private String sPreguntaEnunciado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "db_pregunta_ponderaci")
    private BigDecimal dbPreguntaPonderaci;
    @Basic(optional = false)
    @Column(name = "d_pregunta_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dPreguntaCreacion;
    @Column(name = "d_pregunta_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dPreguntaUltimodi;
    @JoinColumn(name = "pfs_pregunta_aspecto", referencedColumnName = "ps_aspepreg_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AspectoPregunta aspectoPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private Collection<RespSeleProv> respSeleProvCollection;

    public Pregunta() {
    }

    public Pregunta(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public Pregunta(PreguntaPK preguntaPK, String sPreguntaEnunciado, BigDecimal dbPreguntaPonderaci, Date dPreguntaCreacion) {
        this.preguntaPK = preguntaPK;
        this.sPreguntaEnunciado = sPreguntaEnunciado;
        this.dbPreguntaPonderaci = dbPreguntaPonderaci;
        this.dPreguntaCreacion = dPreguntaCreacion;
    }

    public Pregunta(int pfiPreguntaFormeval, char pfsPreguntaAspecto, int piPreguntaOrden) {
        this.preguntaPK = new PreguntaPK(pfiPreguntaFormeval, pfsPreguntaAspecto, piPreguntaOrden);
    }

    public PreguntaPK getPreguntaPK() {
        return preguntaPK;
    }

    public void setPreguntaPK(PreguntaPK preguntaPK) {
        this.preguntaPK = preguntaPK;
    }

    public String getSPreguntaEnunciado() {
        return sPreguntaEnunciado;
    }

    public void setSPreguntaEnunciado(String sPreguntaEnunciado) {
        this.sPreguntaEnunciado = sPreguntaEnunciado;
    }

    public BigDecimal getDbPreguntaPonderaci() {
        return dbPreguntaPonderaci;
    }

    public void setDbPreguntaPonderaci(BigDecimal dbPreguntaPonderaci) {
        this.dbPreguntaPonderaci = dbPreguntaPonderaci;
    }

    public Date getDPreguntaCreacion() {
        return dPreguntaCreacion;
    }

    public void setDPreguntaCreacion(Date dPreguntaCreacion) {
        this.dPreguntaCreacion = dPreguntaCreacion;
    }

    public Date getDPreguntaUltimodi() {
        return dPreguntaUltimodi;
    }

    public void setDPreguntaUltimodi(Date dPreguntaUltimodi) {
        this.dPreguntaUltimodi = dPreguntaUltimodi;
    }

    public AspectoPregunta getAspectoPregunta() {
        return aspectoPregunta;
    }

    public void setAspectoPregunta(AspectoPregunta aspectoPregunta) {
        this.aspectoPregunta = aspectoPregunta;
    }

  
    @XmlTransient
    public Collection<RespSeleProv> getRespSeleProvCollection() {
        return respSeleProvCollection;
    }

    public void setRespSeleProvCollection(Collection<RespSeleProv> respSeleProvCollection) {
        this.respSeleProvCollection = respSeleProvCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaPK != null ? preguntaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.preguntaPK == null && other.preguntaPK != null) || (this.preguntaPK != null && !this.preguntaPK.equals(other.preguntaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pregunta[ preguntaPK=" + preguntaPK + " ]";
    }

    public SUsuario getFsPreguntaUsuultmod() {
        return fsPreguntaUsuultmod;
    }

    public void setFsPreguntaUsuultmod(SUsuario fsPreguntaUsuultmod) {
        this.fsPreguntaUsuultmod = fsPreguntaUsuultmod;
    }

    public SUsuario getFsPreguntaUsuacrea() {
        return fsPreguntaUsuacrea;
    }

    public void setFsPreguntaUsuacrea(SUsuario fsPreguntaUsuacrea) {
        this.fsPreguntaUsuacrea = fsPreguntaUsuacrea;
    }

    @XmlTransient
    public Collection<RespEvalProv> getRespEvalProvCollection() {
        return respEvalProvCollection;
    }

    public void setRespEvalProvCollection(Collection<RespEvalProv> respEvalProvCollection) {
        this.respEvalProvCollection = respEvalProvCollection;
    }
    
}
