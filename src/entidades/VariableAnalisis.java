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
@Table(name = "variable_analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariableAnalisis.findAll", query = "SELECT v FROM VariableAnalisis v"),
    @NamedQuery(name = "VariableAnalisis.findByPiVarianalId", query = "SELECT v FROM VariableAnalisis v WHERE v.piVarianalId = :piVarianalId"),
    @NamedQuery(name = "VariableAnalisis.findBySVarianalDescripcion", query = "SELECT v FROM VariableAnalisis v WHERE v.sVarianalDescripcion = :sVarianalDescripcion"),
    @NamedQuery(name = "VariableAnalisis.findByDVarianalCreacion", query = "SELECT v FROM VariableAnalisis v WHERE v.dVarianalCreacion = :dVarianalCreacion"),
    @NamedQuery(name = "VariableAnalisis.findByDVarianalUltimodi", query = "SELECT v FROM VariableAnalisis v WHERE v.dVarianalUltimodi = :dVarianalUltimodi")})
public class VariableAnalisis implements Serializable {

    
  
    @OneToMany(mappedBy = "fiDecimalVarianalVarianal")
    private List<DecimalVarianal> decimalVarianalList;

    @OneToMany(mappedBy = "fiFotohidroVarianal")
    private List<FotoHidrobiologia> fotoHidrobiologiaList;

    @OneToMany(mappedBy = "fiDatoHidroVarAnal")
    private List<DatoHidrobiologia> datoHidrobiologiaList;



    @OneToMany(mappedBy = "fiVarianalControlVariable")
    private List<VariableAnalisisControl> variableAnalisisControlList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variableAnalisis")
    private List<VarianalControlCantidad> varianalControlCantidadList;

 

    @OneToMany(mappedBy = "fiCondicionalVariableAnalisis")
    private List<Condicional> condicionalList;

    @OneToMany(mappedBy = "fiVariableVariableCalculo")
    private List<VariableCalculo> variableCalculoList;

   /* @OneToMany(mappedBy = "fiVariableVariableCalculo")
    private List<VariableCalculo> variableCalculoList;*/

   

    @OneToMany(mappedBy = "fkValidacionVariable1")
    private List<Validacion> validacionList;
    @OneToMany(mappedBy = "fkValidacionVariable2")
    private List<Validacion> validacionList1;

    
    @OneToMany(mappedBy = "idParametro")
    private List<AnalistaParametro> analistaParametroList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_varianal_id")
    private Integer piVarianalId;
    @Basic(optional = false)
    @Column(name = "s_varianal_descripcion")
    private String sVarianalDescripcion;
    @Basic(optional = false)
    @Column(name = "d_varianal_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalCreacion;
    @Column(name = "d_varianal_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dVarianalUltimodi;
    @JoinColumn(name = "fs_varianal_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsVarianalUsuultmod;
    @JoinColumn(name = "fs_varianal_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsVarianalUsuacrea;
    @JoinColumn(name = "fi_varianal_matriz", referencedColumnName = "pi_matranal_id")
    @ManyToOne(optional = false)
    private MatrizAnalisis fiVarianalMatriz;
  
    public VariableAnalisis() {
    }

    public VariableAnalisis(Integer piVarianalId) {
        this.piVarianalId = piVarianalId;
    }

    public VariableAnalisis(Integer piVarianalId, String sVarianalDescripcion, Date dVarianalCreacion) {
        this.piVarianalId = piVarianalId;
        this.sVarianalDescripcion = sVarianalDescripcion;
        this.dVarianalCreacion = dVarianalCreacion;
    }

    public Integer getPiVarianalId() {
        return piVarianalId;
    }

    public void setPiVarianalId(Integer piVarianalId) {
        this.piVarianalId = piVarianalId;
    }

    public String getSVarianalDescripcion() {
        return sVarianalDescripcion;
    }

    public void setSVarianalDescripcion(String sVarianalDescripcion) {
        this.sVarianalDescripcion = sVarianalDescripcion;
    }

    public Date getDVarianalCreacion() {
        return dVarianalCreacion;
    }

    public void setDVarianalCreacion(Date dVarianalCreacion) {
        this.dVarianalCreacion = dVarianalCreacion;
    }

    public Date getDVarianalUltimodi() {
        return dVarianalUltimodi;
    }

    public void setDVarianalUltimodi(Date dVarianalUltimodi) {
        this.dVarianalUltimodi = dVarianalUltimodi;
    }

    public SUsuario getFsVarianalUsuultmod() {
        return fsVarianalUsuultmod;
    }

    public void setFsVarianalUsuultmod(SUsuario fsVarianalUsuultmod) {
        this.fsVarianalUsuultmod = fsVarianalUsuultmod;
    }

    public SUsuario getFsVarianalUsuacrea() {
        return fsVarianalUsuacrea;
    }

    public void setFsVarianalUsuacrea(SUsuario fsVarianalUsuacrea) {
        this.fsVarianalUsuacrea = fsVarianalUsuacrea;
    }

    public MatrizAnalisis getFiVarianalMatriz() {
        return fiVarianalMatriz;
    }

    public void setFiVarianalMatriz(MatrizAnalisis fiVarianalMatriz) {
        this.fiVarianalMatriz = fiVarianalMatriz;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piVarianalId != null ? piVarianalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariableAnalisis)) {
            return false;
        }
        VariableAnalisis other = (VariableAnalisis) object;
        if ((this.piVarianalId == null && other.piVarianalId != null) || (this.piVarianalId != null && !this.piVarianalId.equals(other.piVarianalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.sVarianalDescripcion;
    }

    @XmlTransient
    public List<AnalistaParametro> getAnalistaParametroList() {
        return analistaParametroList;
    }

    public void setAnalistaParametroList(List<AnalistaParametro> analistaParametroList) {
        this.analistaParametroList = analistaParametroList;
    }

    @XmlTransient
    public List<Validacion> getValidacionList() {
        return validacionList;
    }

    public void setValidacionList(List<Validacion> validacionList) {
        this.validacionList = validacionList;
    }

    @XmlTransient
    public List<Validacion> getValidacionList1() {
        return validacionList1;
    }

    public void setValidacionList1(List<Validacion> validacionList1) {
        this.validacionList1 = validacionList1;
    }

   /* @XmlTransient
    public List<VariableCalculo> getVariableCalculoList() {
        return variableCalculoList;
    }

    public void setVariableCalculoList(List<VariableCalculo> variableCalculoList) {
        this.variableCalculoList = variableCalculoList;
    }*/

    @XmlTransient
    public List<VariableCalculo> getVariableCalculoList() {
        return variableCalculoList;
    }

    public void setVariableCalculoList(List<VariableCalculo> variableCalculoList) {
        this.variableCalculoList = variableCalculoList;
    }

    @XmlTransient
    public List<Condicional> getCondicionalList() {
        return condicionalList;
    }

    public void setCondicionalList(List<Condicional> condicionalList) {
        this.condicionalList = condicionalList;
    }

    @XmlTransient
    public List<VariableAnalisisControl> getVariableAnalisisControlList() {
        return variableAnalisisControlList;
    }

    public void setVariableAnalisisControlList(List<VariableAnalisisControl> variableAnalisisControlList) {
        this.variableAnalisisControlList = variableAnalisisControlList;
    }

    @XmlTransient
    public List<VarianalControlCantidad> getVarianalControlCantidadList() {
        return varianalControlCantidadList;
    }

    public void setVarianalControlCantidadList(List<VarianalControlCantidad> varianalControlCantidadList) {
        this.varianalControlCantidadList = varianalControlCantidadList;
    }



    @XmlTransient
    public List<DatoHidrobiologia> getDatoHidrobiologiaList() {
        return datoHidrobiologiaList;
    }

    public void setDatoHidrobiologiaList(List<DatoHidrobiologia> datoHidrobiologiaList) {
        this.datoHidrobiologiaList = datoHidrobiologiaList;
    }

    @XmlTransient
    public List<FotoHidrobiologia> getFotoHidrobiologiaList() {
        return fotoHidrobiologiaList;
    }

    public void setFotoHidrobiologiaList(List<FotoHidrobiologia> fotoHidrobiologiaList) {
        this.fotoHidrobiologiaList = fotoHidrobiologiaList;
    }

    @XmlTransient
    public List<DecimalVarianal> getDecimalVarianalList() {
        return decimalVarianalList;
    }

    public void setDecimalVarianalList(List<DecimalVarianal> decimalVarianalList) {
        this.decimalVarianalList = decimalVarianalList;
    }

 

}
