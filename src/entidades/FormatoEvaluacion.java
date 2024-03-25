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
@Table(name = "formato_evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormatoEvaluacion.findAll", query = "SELECT f FROM FormatoEvaluacion f"),
    @NamedQuery(name = "FormatoEvaluacion.findByPiFormevalConsecuti", query = "SELECT f FROM FormatoEvaluacion f WHERE f.piFormevalConsecuti = :piFormevalConsecuti"),
    @NamedQuery(name = "FormatoEvaluacion.findBySFormevalCodiform", query = "SELECT f FROM FormatoEvaluacion f WHERE f.sFormevalCodiform = :sFormevalCodiform"),
    @NamedQuery(name = "FormatoEvaluacion.findBySFormevalVersform", query = "SELECT f FROM FormatoEvaluacion f WHERE f.sFormevalVersform = :sFormevalVersform"),
    @NamedQuery(name = "FormatoEvaluacion.findBySFormevalEstado", query = "SELECT f FROM FormatoEvaluacion f WHERE f.sFormevalEstado = :sFormevalEstado"),
    @NamedQuery(name = "FormatoEvaluacion.findByDFormevalInicvige", query = "SELECT f FROM FormatoEvaluacion f WHERE f.dFormevalInicvige = :dFormevalInicvige"),
    @NamedQuery(name = "FormatoEvaluacion.findByDFormevalFinavige", query = "SELECT f FROM FormatoEvaluacion f WHERE f.dFormevalFinavige = :dFormevalFinavige"),
    @NamedQuery(name = "FormatoEvaluacion.findByDFormevalCreacion", query = "SELECT f FROM FormatoEvaluacion f WHERE f.dFormevalCreacion = :dFormevalCreacion"),
    @NamedQuery(name = "FormatoEvaluacion.findByDFormevalUltimodi", query = "SELECT f FROM FormatoEvaluacion f WHERE f.dFormevalUltimodi = :dFormevalUltimodi")})
public class FormatoEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_formeval_consecuti")
    private Integer piFormevalConsecuti;
    @Basic(optional = false)
    @Column(name = "s_formeval_codiform")
    private String sFormevalCodiform;
    @Basic(optional = false)
    @Column(name = "s_formeval_versform")
    private String sFormevalVersform;
    @Basic(optional = false)
    @Column(name = "s_formeval_estado")
    private char sFormevalEstado;
    @Basic(optional = false)
    @Column(name = "d_formeval_inicvige")
    @Temporal(TemporalType.DATE)
    private Date dFormevalInicvige;
    @Column(name = "d_formeval_finavige")
    @Temporal(TemporalType.DATE)
    private Date dFormevalFinavige;
    @Basic(optional = false)
    @Column(name = "d_formeval_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormevalCreacion;
    @Column(name = "d_formeval_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFormevalUltimodi;
    @JoinColumn(name = "fs_formeval_tipo", referencedColumnName = "ps_tipforeva_codigo")
    @ManyToOne(optional = false)
    private TipoFormatoEvaluacion fsFormevalTipo;

    public FormatoEvaluacion() {
    }

    public FormatoEvaluacion(Integer piFormevalConsecuti) {
        this.piFormevalConsecuti = piFormevalConsecuti;
    }

    public FormatoEvaluacion(Integer piFormevalConsecuti, String sFormevalCodiform, String sFormevalVersform, char sFormevalEstado, Date dFormevalInicvige, Date dFormevalCreacion) {
        this.piFormevalConsecuti = piFormevalConsecuti;
        this.sFormevalCodiform = sFormevalCodiform;
        this.sFormevalVersform = sFormevalVersform;
        this.sFormevalEstado = sFormevalEstado;
        this.dFormevalInicvige = dFormevalInicvige;
        this.dFormevalCreacion = dFormevalCreacion;
    }

    public Integer getPiFormevalConsecuti() {
        return piFormevalConsecuti;
    }

    public void setPiFormevalConsecuti(Integer piFormevalConsecuti) {
        this.piFormevalConsecuti = piFormevalConsecuti;
    }

    public String getSFormevalCodiform() {
        return sFormevalCodiform;
    }

    public void setSFormevalCodiform(String sFormevalCodiform) {
        this.sFormevalCodiform = sFormevalCodiform;
    }

    public String getSFormevalVersform() {
        return sFormevalVersform;
    }

    public void setSFormevalVersform(String sFormevalVersform) {
        this.sFormevalVersform = sFormevalVersform;
    }

    public char getSFormevalEstado() {
        return sFormevalEstado;
    }

    public void setSFormevalEstado(char sFormevalEstado) {
        this.sFormevalEstado = sFormevalEstado;
    }

    public Date getDFormevalInicvige() {
        return dFormevalInicvige;
    }

    public void setDFormevalInicvige(Date dFormevalInicvige) {
        this.dFormevalInicvige = dFormevalInicvige;
    }

    public Date getDFormevalFinavige() {
        return dFormevalFinavige;
    }

    public void setDFormevalFinavige(Date dFormevalFinavige) {
        this.dFormevalFinavige = dFormevalFinavige;
    }

    public Date getDFormevalCreacion() {
        return dFormevalCreacion;
    }

    public void setDFormevalCreacion(Date dFormevalCreacion) {
        this.dFormevalCreacion = dFormevalCreacion;
    }

    public Date getDFormevalUltimodi() {
        return dFormevalUltimodi;
    }

    public void setDFormevalUltimodi(Date dFormevalUltimodi) {
        this.dFormevalUltimodi = dFormevalUltimodi;
    }

    public TipoFormatoEvaluacion getFsFormevalTipo() {
        return fsFormevalTipo;
    }

    public void setFsFormevalTipo(TipoFormatoEvaluacion fsFormevalTipo) {
        this.fsFormevalTipo = fsFormevalTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piFormevalConsecuti != null ? piFormevalConsecuti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormatoEvaluacion)) {
            return false;
        }
        FormatoEvaluacion other = (FormatoEvaluacion) object;
        if ((this.piFormevalConsecuti == null && other.piFormevalConsecuti != null) || (this.piFormevalConsecuti != null && !this.piFormevalConsecuti.equals(other.piFormevalConsecuti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FormatoEvaluacion[ piFormevalConsecuti=" + piFormevalConsecuti + " ]";
    }


    
}
