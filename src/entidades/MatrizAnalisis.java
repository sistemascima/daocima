/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@Table(name = "matriz_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatrizAnalisis.findAll", query = "SELECT m FROM MatrizAnalisis m"),
    @NamedQuery(name = "MatrizAnalisis.findByPiMatranalId", query = "SELECT m FROM MatrizAnalisis m WHERE m.piMatranalId = :piMatranalId"),
    @NamedQuery(name = "MatrizAnalisis.findBySMatranalDescripcion", query = "SELECT m FROM MatrizAnalisis m WHERE m.sMatranalDescripcion = :sMatranalDescripcion"),
    @NamedQuery(name = "MatrizAnalisis.findByDMatranalCreacion", query = "SELECT m FROM MatrizAnalisis m WHERE m.dMatranalCreacion = :dMatranalCreacion"),
    @NamedQuery(name = "MatrizAnalisis.findByDMatranalUltimodi", query = "SELECT m FROM MatrizAnalisis m WHERE m.dMatranalUltimodi = :dMatranalUltimodi")})
public class MatrizAnalisis implements Serializable {

    @OneToMany(mappedBy = "fiDecimalVarianalMatriz")
    private List<DecimalVarianal> decimalVarianalList;

    @Column(name = "i_matranal_result_autom")
    private Integer iMatranalResultAutom;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_matranal_id")
    private Integer piMatranalId;
    @Basic(optional = false)
    @Column(name = "s_matranal_descripcion")
    private String sMatranalDescripcion;
    @Basic(optional = false)
    @Column(name = "d_matranal_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMatranalCreacion;
    @Column(name = "d_matranal_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dMatranalUltimodi;
     @JoinColumn(name = "fs_matranal_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsMatranalUsuultmod;
    @JoinColumn(name = "fs_matranal_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsMatranalUsuacrea;
    @JoinColumn(name = "fs_matranal_coorlabo", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsMatranalCoorlabo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiVarianalMatriz" )
    private Collection<VariableAnalisis> variableAnalisisCollection;
    
    public MatrizAnalisis() {
    }

    public MatrizAnalisis(Integer piMatranalId) {
        this.piMatranalId = piMatranalId;
    }

    public MatrizAnalisis(Integer piMatranalId, String sMatranalDescripcion, Date dMatranalCreacion) {
        this.piMatranalId = piMatranalId;
        this.sMatranalDescripcion = sMatranalDescripcion;
        this.dMatranalCreacion = dMatranalCreacion;
    }

    public Integer getPiMatranalId() {
        return piMatranalId;
    }

    public void setPiMatranalId(Integer piMatranalId) {
        this.piMatranalId = piMatranalId;
    }

    public String getSMatranalDescripcion() {
        return sMatranalDescripcion;
    }

    public void setSMatranalDescripcion(String sMatranalDescripcion) {
        this.sMatranalDescripcion = sMatranalDescripcion;
    }

    public Date getDMatranalCreacion() {
        return dMatranalCreacion;
    }

    public void setDMatranalCreacion(Date dMatranalCreacion) {
        this.dMatranalCreacion = dMatranalCreacion;
    }

    public Date getDMatranalUltimodi() {
        return dMatranalUltimodi;
    }

    public void setDMatranalUltimodi(Date dMatranalUltimodi) {
        this.dMatranalUltimodi = dMatranalUltimodi;
    }

    public SUsuario getFsMatranalUsuultmod() {
        return fsMatranalUsuultmod;
    }

    public void setFsMatranalUsuultmod(SUsuario fsMatranalUsuultmod) {
        this.fsMatranalUsuultmod = fsMatranalUsuultmod;
    }

    public SUsuario getFsMatranalUsuacrea() {
        return fsMatranalUsuacrea;
    }

    public void setFsMatranalUsuacrea(SUsuario fsMatranalUsuacrea) {
        this.fsMatranalUsuacrea = fsMatranalUsuacrea;
    }

    public Cargo getFsMatranalCoorlabo() {
        return fsMatranalCoorlabo;
    }

    public void setFsMatranalCoorlabo(Cargo fsMatranalCoorlabo) {
        this.fsMatranalCoorlabo = fsMatranalCoorlabo;
    }

    @XmlTransient
    public Collection<VariableAnalisis> getVariableAnalisisCollection() {
        return variableAnalisisCollection;
    }

    public void setVariableAnalisisCollection(Collection<VariableAnalisis> variableAnalisisCollection) {
        this.variableAnalisisCollection = variableAnalisisCollection;
    }

    public int getiMatranalResultAutom() {
        return iMatranalResultAutom;
    }

    public void setiMatranalResultAutom(int iMatranalResultAutom) {
        this.iMatranalResultAutom = iMatranalResultAutom;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piMatranalId != null ? piMatranalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatrizAnalisis)) {
            return false;
        }
        MatrizAnalisis other = (MatrizAnalisis) object;
        if ((this.piMatranalId == null && other.piMatranalId != null) || (this.piMatranalId != null && !this.piMatranalId.equals(other.piMatranalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MatrizAnalisis[ piMatranalId=" + piMatranalId + " ]";
    }

    public Integer getIMatranalResultAutom() {
        return iMatranalResultAutom;
    }

    public void setIMatranalResultAutom(Integer iMatranalResultAutom) {
        this.iMatranalResultAutom = iMatranalResultAutom;
    }

    @XmlTransient
    public List<DecimalVarianal> getDecimalVarianalList() {
        return decimalVarianalList;
    }

    public void setDecimalVarianalList(List<DecimalVarianal> decimalVarianalList) {
        this.decimalVarianalList = decimalVarianalList;
    }
    

}
