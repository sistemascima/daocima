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
@Table(name = "aspecto_pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AspectoPregunta.findAll", query = "SELECT a FROM AspectoPregunta a"),
    @NamedQuery(name = "AspectoPregunta.findByPsAspepregCodigo", query = "SELECT a FROM AspectoPregunta a WHERE a.psAspepregCodigo = :psAspepregCodigo"),
    @NamedQuery(name = "AspectoPregunta.findBySAspepregNombre", query = "SELECT a FROM AspectoPregunta a WHERE a.sAspepregNombre = :sAspepregNombre"),
    @NamedQuery(name = "AspectoPregunta.findByDAspepregCreacion", query = "SELECT a FROM AspectoPregunta a WHERE a.dAspepregCreacion = :dAspepregCreacion"),
    @NamedQuery(name = "AspectoPregunta.findByDAspepregUltimodi", query = "SELECT a FROM AspectoPregunta a WHERE a.dAspepregUltimodi = :dAspepregUltimodi")})
public class AspectoPregunta implements Serializable {
    @JoinColumn(name = "fs_aspepreg_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsAspepregUsuultmod;
    @JoinColumn(name = "fs_aspepreg_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsAspepregUsuacrea;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_aspepreg_codigo")
    private Character psAspepregCodigo;
    @Basic(optional = false)
    @Column(name = "s_aspepreg_nombre")
    private String sAspepregNombre;
    @Basic(optional = false)
    @Column(name = "d_aspepreg_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dAspepregCreacion;
    @Column(name = "d_aspepreg_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dAspepregUltimodi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspectoPregunta")
    private Collection<Pregunta> preguntaCollection;

    public AspectoPregunta() {
    }

    public AspectoPregunta(Character psAspepregCodigo) {
        this.psAspepregCodigo = psAspepregCodigo;
    }

    public AspectoPregunta(Character psAspepregCodigo, String sAspepregNombre, Date dAspepregCreacion) {
        this.psAspepregCodigo = psAspepregCodigo;
        this.sAspepregNombre = sAspepregNombre;
        this.dAspepregCreacion = dAspepregCreacion;
    }

    public Character getPsAspepregCodigo() {
        return psAspepregCodigo;
    }

    public void setPsAspepregCodigo(Character psAspepregCodigo) {
        this.psAspepregCodigo = psAspepregCodigo;
    }

    public String getSAspepregNombre() {
        return sAspepregNombre;
    }

    public void setSAspepregNombre(String sAspepregNombre) {
        this.sAspepregNombre = sAspepregNombre;
    }

    public Date getDAspepregCreacion() {
        return dAspepregCreacion;
    }

    public void setDAspepregCreacion(Date dAspepregCreacion) {
        this.dAspepregCreacion = dAspepregCreacion;
    }

    public Date getDAspepregUltimodi() {
        return dAspepregUltimodi;
    }

    public void setDAspepregUltimodi(Date dAspepregUltimodi) {
        this.dAspepregUltimodi = dAspepregUltimodi;
    }

    @XmlTransient
    public Collection<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psAspepregCodigo != null ? psAspepregCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspectoPregunta)) {
            return false;
        }
        AspectoPregunta other = (AspectoPregunta) object;
        if ((this.psAspepregCodigo == null && other.psAspepregCodigo != null) || (this.psAspepregCodigo != null && !this.psAspepregCodigo.equals(other.psAspepregCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AspectoPregunta[ psAspepregCodigo=" + psAspepregCodigo + " ]";
    }

    public SUsuario getFsAspepregUsuultmod() {
        return fsAspepregUsuultmod;
    }

    public void setFsAspepregUsuultmod(SUsuario fsAspepregUsuultmod) {
        this.fsAspepregUsuultmod = fsAspepregUsuultmod;
    }

    public SUsuario getFsAspepregUsuacrea() {
        return fsAspepregUsuacrea;
    }

    public void setFsAspepregUsuacrea(SUsuario fsAspepregUsuacrea) {
        this.fsAspepregUsuacrea = fsAspepregUsuacrea;
    }
    
}
