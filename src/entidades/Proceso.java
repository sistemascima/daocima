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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"),
    @NamedQuery(name = "Proceso.findBySProcesoLetrdocu", query = "SELECT p FROM Proceso p WHERE p.sProcesoLetrdocu = :sProcesoLetrdocu"),
    @NamedQuery(name = "Proceso.findByPiProcesoId", query = "SELECT p FROM Proceso p WHERE p.piProcesoId = :piProcesoId"),
    @NamedQuery(name = "Proceso.findBySProcesoNombre", query = "SELECT p FROM Proceso p WHERE p.sProcesoNombre = :sProcesoNombre"),
    @NamedQuery(name = "Proceso.findByDProcesoCreacion", query = "SELECT p FROM Proceso p WHERE p.dProcesoCreacion = :dProcesoCreacion"),
    @NamedQuery(name = "Proceso.findByDProcesoUltimodi", query = "SELECT p FROM Proceso p WHERE p.dProcesoUltimodi = :dProcesoUltimodi")})
public class Proceso implements Serializable {

    @Basic(optional = false)
    @Column(name = "s_proceso_letrdocu")
    private Character sProcesoLetrdocu;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_proceso_id")
    private Integer piProcesoId;
    @Basic(optional = false)
    @Column(name = "s_proceso_nombre")
    private String sProcesoNombre;
    @Basic(optional = false)
    @Column(name = "d_proceso_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProcesoCreacion;
    @Column(name = "d_proceso_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dProcesoUltimodi;
    @JoinColumn(name = "fs_proceso_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsProcesoUsuultmod;
    @JoinColumn(name = "fs_proceso_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsProcesoUsuacrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso")
    private Collection<Documento> documentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso")
    private Collection<ResponsableDocumento> responsableDocumentoCollection;

    public Proceso() {
    }

    public Proceso(Integer piProcesoId) {
        this.piProcesoId = piProcesoId;
    }

    public Proceso(Integer piProcesoId, char sProcesoLetrdocu, String sProcesoNombre, Date dProcesoCreacion) {
        this.piProcesoId = piProcesoId;
        this.sProcesoLetrdocu = sProcesoLetrdocu;
        this.sProcesoNombre = sProcesoNombre;
        this.dProcesoCreacion = dProcesoCreacion;
    }

    public char getSProcesoLetrdocu() {
        return sProcesoLetrdocu;
    }

    public void setSProcesoLetrdocu(char sProcesoLetrdocu) {
        this.sProcesoLetrdocu = sProcesoLetrdocu;
    }

    public Integer getPiProcesoId() {
        return piProcesoId;
    }

    public void setPiProcesoId(Integer piProcesoId) {
        this.piProcesoId = piProcesoId;
    }

    public String getSProcesoNombre() {
        return sProcesoNombre;
    }

    public void setSProcesoNombre(String sProcesoNombre) {
        this.sProcesoNombre = sProcesoNombre;
    }

    public Date getDProcesoCreacion() {
        return dProcesoCreacion;
    }

    public void setDProcesoCreacion(Date dProcesoCreacion) {
        this.dProcesoCreacion = dProcesoCreacion;
    }

    public Date getDProcesoUltimodi() {
        return dProcesoUltimodi;
    }

    public void setDProcesoUltimodi(Date dProcesoUltimodi) {
        this.dProcesoUltimodi = dProcesoUltimodi;
    }

    public SUsuario getFsProcesoUsuultmod() {
        return fsProcesoUsuultmod;
    }

    public void setFsProcesoUsuultmod(SUsuario fsProcesoUsuultmod) {
        this.fsProcesoUsuultmod = fsProcesoUsuultmod;
    }

    public SUsuario getFsProcesoUsuacrea() {
        return fsProcesoUsuacrea;
    }

    public void setFsProcesoUsuacrea(SUsuario fsProcesoUsuacrea) {
        this.fsProcesoUsuacrea = fsProcesoUsuacrea;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    public Collection<ResponsableDocumento> getResponsableDocumentoCollection() {
        return responsableDocumentoCollection;
    }

    public void setResponsableDocumentoCollection(Collection<ResponsableDocumento> responsableDocumentoCollection) {
        this.responsableDocumentoCollection = responsableDocumentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piProcesoId != null ? piProcesoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.piProcesoId == null && other.piProcesoId != null) || (this.piProcesoId != null && !this.piProcesoId.equals(other.piProcesoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + sProcesoLetrdocu + "] " + piProcesoId + ". " + sProcesoNombre;
    }



    public void setSProcesoLetrdocu(Character sProcesoLetrdocu) {
        this.sProcesoLetrdocu = sProcesoLetrdocu;
    }
    
}
