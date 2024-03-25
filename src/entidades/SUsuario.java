/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author SISTEMAS
 */
@Entity
@Table(name = "s_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SUsuario.findAll", query = "SELECT s FROM SUsuario s"),
    @NamedQuery(name = "SUsuario.findByPsUsuarioCodigo", query = "SELECT s FROM SUsuario s WHERE s.psUsuarioCodigo = :psUsuarioCodigo"),
    @NamedQuery(name = "SUsuario.findByDUsuarioCreacion", query = "SELECT s FROM SUsuario s WHERE s.dUsuarioCreacion = :dUsuarioCreacion"),
    @NamedQuery(name = "SUsuario.findByDUsuarioUltcamcon", query = "SELECT s FROM SUsuario s WHERE s.dUsuarioUltcamcon = :dUsuarioUltcamcon"),
    @NamedQuery(name = "SUsuario.findByDUsuarioUltimodi", query = "SELECT s FROM SUsuario s WHERE s.dUsuarioUltimodi = :dUsuarioUltimodi"),
    @NamedQuery(name = "SUsuario.findBySUsuarioApellidos", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioApellidos = :sUsuarioApellidos"),
    @NamedQuery(name = "SUsuario.findBySUsuarioCorreo", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioCorreo = :sUsuarioCorreo"),
    @NamedQuery(name = "SUsuario.findBySUsuarioEstado", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioEstado = :sUsuarioEstado"),
    @NamedQuery(name = "SUsuario.findBySUsuarioNombres", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioNombres = :sUsuarioNombres"),
    @NamedQuery(name = "SUsuario.findBySUsuarioReqcamcon", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioReqcamcon = :sUsuarioReqcamcon"),
    @NamedQuery(name = "SUsuario.findByFsUsuarioUsuultmod", query = "SELECT s FROM SUsuario s WHERE s.fsUsuarioUsuultmod = :fsUsuarioUsuultmod"),
    @NamedQuery(name = "SUsuario.findByFiUsuarioPassword", query = "SELECT s FROM SUsuario s WHERE s.fiUsuarioPassword = :fiUsuarioPassword")})
public class SUsuario implements Serializable {

    @Lob
    @Column(name = "by_usuario_contrasena")
    private byte[] byUsuarioContrasena;
    @OneToMany(mappedBy = "fsVariableCalculoUsuacreacion")
    private List<VariableCalculo> variableCalculoList;
    @OneToMany(mappedBy = "fsVariableCalculoUsuultmod")
    private List<VariableCalculo> variableCalculoList1;
    @OneToMany(mappedBy = "fsEquiInterUsuacrea")
    private List<EquipamientoInterno> equipamientoInternoList;
    @OneToMany(mappedBy = "fsEquiInterUsuamodi")
    private List<EquipamientoInterno> equipamientoInternoList1;
    @OneToMany(mappedBy = "fsHojaVidaUsuacrea")
    private List<HojaVida> hojaVidaList;
    @OneToMany(mappedBy = "fsHojaVidaUsuamodi")
    private List<HojaVida> hojaVidaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsMatranalUsuacrea")
    private List<MatrizAnalisis> matrizAnalisisList;
    @OneToMany(mappedBy = "fsMatranalUsuultmod")
    private List<MatrizAnalisis> matrizAnalisisList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsVarianalUsuacrea")
    private List<VariableAnalisis> variableAnalisisList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsTipoanalUsuacrea")
    private List<TipoAnalisis> tipoAnalisisList;
    @OneToMany(mappedBy = "fsTipoanalUsuultmod")
    private List<TipoAnalisis> tipoAnalisisList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsTipforevaUsuacrea")
    private List<TipoFormatoEvaluacion> tipoFormatoEvaluacionList;
    @OneToMany(mappedBy = "fsTipforevaUsuultmod")
    private List<TipoFormatoEvaluacion> tipoFormatoEvaluacionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsEvalprovUsuacrea")
    private List<EvaluacionProveedor> evaluacionProveedorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsEvalprovUsuaeval")
    private List<EvaluacionProveedor> evaluacionProveedorList1;
    @OneToMany(mappedBy = "fsEvalprovUsuultmod")
    private List<EvaluacionProveedor> evaluacionProveedorList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsDepartamUsuacrea")
    private List<Departamento> departamentoList;
    @OneToMany(mappedBy = "fsDepartamUsuultmod")
    private List<Departamento> departamentoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsProveedorUsuacrea")
    private List<Proveedor> proveedorList;
    @OneToMany(mappedBy = "fsProveedorUsuultmod")
    private List<Proveedor> proveedorList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsCargoUsuacrea")
    private List<Cargo> cargoList;
    @OneToMany(mappedBy = "fsCargoUsuultmod")
    private List<Cargo> cargoList1;
    @OneToMany(mappedBy = "fsEmpresaUsuarioCreacion")
    private List<Empresa> empresaList;
    @OneToMany(mappedBy = "fsEmpresaUsuarioModificacion")
    private List<Empresa> empresaList1;
    @OneToMany(mappedBy = "fsOrdecompUsuaapru")
    private List<OrdenCompra> ordenCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fsOrdecompUsuacrea")
    private List<OrdenCompra> ordenCompraList1;
    @OneToMany(mappedBy = "fsOrdecompUsuarevi")
    private List<OrdenCompra> ordenCompraList2;
    @OneToMany(mappedBy = "fsOrdecompUsuultmod")
    private List<OrdenCompra> ordenCompraList3;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ps_usuario_codigo", nullable = false, length = 255)
    private String psUsuarioCodigo;
    @Column(name = "d_usuario_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dUsuarioCreacion;
    @Column(name = "d_usuario_ultcamcon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dUsuarioUltcamcon;
    @Column(name = "d_usuario_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dUsuarioUltimodi;
    @Column(name = "s_usuario_apellidos", length = 255)
    private String sUsuarioApellidos;
    @Column(name = "s_usuario_correo", length = 255)
    private String sUsuarioCorreo;
    @Column(name = "s_usuario_estado", length = 255)
    private String sUsuarioEstado;
    @Column(name = "s_usuario_nombres", length = 255)
    private String sUsuarioNombres;
    @Column(name = "s_usuario_reqcamcon")
    private Character sUsuarioReqcamcon;
    @Column(name = "fs_usuario_usuultmod", length = 255)
    private String fsUsuarioUsuultmod;
    @Column(name = "fi_usuario_password")
    private Integer fiUsuarioPassword;
    @OneToMany(mappedBy = "fsUsuarioUsuacrea")
    private List<SUsuario> sUsuarioList;
    @JoinColumn(name = "fs_usuario_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsUsuarioUsuacrea;

    public SUsuario() {
    }

    public SUsuario(String psUsuarioCodigo) {
        this.psUsuarioCodigo = psUsuarioCodigo;
    }

    public String getPsUsuarioCodigo() {
        return psUsuarioCodigo;
    }

    public void setPsUsuarioCodigo(String psUsuarioCodigo) {
        this.psUsuarioCodigo = psUsuarioCodigo;
    }

    public byte[] getByUsuarioContrasena() {
        return byUsuarioContrasena;
    }

    public void setByUsuarioContrasena(byte[] byUsuarioContrasena) {
        this.byUsuarioContrasena = byUsuarioContrasena;
    }

    public Date getdUsuarioCreacion() {
        return dUsuarioCreacion;
    }

    public void setDUsuarioCreacion(Date dUsuarioCreacion) {
        this.dUsuarioCreacion = dUsuarioCreacion;
    }

    public Date getdUsuarioUltcamcon() {
        return dUsuarioUltcamcon;
    }

    public void setdUsuarioUltcamcon(Date dUsuarioUltcamcon) {
        this.dUsuarioUltcamcon = dUsuarioUltcamcon;
    }

    public Date getdUsuarioUltimodi() {
        return dUsuarioUltimodi;
    }

    public void setdUsuarioUltimodi(Date dUsuarioUltimodi) {
        this.dUsuarioUltimodi = dUsuarioUltimodi;
    }

    public String getsUsuarioApellidos() {
        return sUsuarioApellidos;
    }

    public void setsUsuarioApellidos(String sUsuarioApellidos) {
        this.sUsuarioApellidos = sUsuarioApellidos;
    }

    public String getsUsuarioCorreo() {
        return sUsuarioCorreo;
    }

    public void setsUsuarioCorreo(String sUsuarioCorreo) {
        this.sUsuarioCorreo = sUsuarioCorreo;
    }

    public String getsUsuarioEstado() {
        return sUsuarioEstado;
    }

    public void setsUsuarioEstado(String sUsuarioEstado) {
        this.sUsuarioEstado = sUsuarioEstado;
    }

    public String getsUsuarioNombres() {
        return sUsuarioNombres;
    }

    public void setsUsuarioNombres(String sUsuarioNombres) {
        this.sUsuarioNombres = sUsuarioNombres;
    }

    public Character getsUsuarioReqcamcon() {
        return sUsuarioReqcamcon;
    }

    public void setsUsuarioReqcamcon(Character sUsuarioReqcamcon) {
        this.sUsuarioReqcamcon = sUsuarioReqcamcon;
    }

    public String getFsUsuarioUsuultmod() {
        return fsUsuarioUsuultmod;
    }

    public void setFsUsuarioUsuultmod(String fsUsuarioUsuultmod) {
        this.fsUsuarioUsuultmod = fsUsuarioUsuultmod;
    }

    public Integer getPasswordId() {
        return fiUsuarioPassword;
    }

    public void setFiUsuarioPassword(Integer fiUsuarioPassword) {
        this.fiUsuarioPassword = fiUsuarioPassword;
    }

    @XmlTransient
    public List<SUsuario> getSUsuarioList() {
        return sUsuarioList;
    }

    public void setSUsuarioList(List<SUsuario> sUsuarioList) {
        this.sUsuarioList = sUsuarioList;
    }

    public SUsuario getFsUsuarioUsuacrea() {
        return fsUsuarioUsuacrea;
    }

    public void setFsUsuarioUsuacrea(SUsuario fsUsuarioUsuacrea) {
        this.fsUsuarioUsuacrea = fsUsuarioUsuacrea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psUsuarioCodigo != null ? psUsuarioCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SUsuario)) {
            return false;
        }
        SUsuario other = (SUsuario) object;
        if ((this.psUsuarioCodigo == null && other.psUsuarioCodigo != null) || (this.psUsuarioCodigo != null && !this.psUsuarioCodigo.equals(other.psUsuarioCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  psUsuarioCodigo ;
    }

    
    @XmlTransient
    public List<TipoFormatoEvaluacion> getTipoFormatoEvaluacionList() {
        return tipoFormatoEvaluacionList;
    }

    public void setTipoFormatoEvaluacionList(List<TipoFormatoEvaluacion> tipoFormatoEvaluacionList) {
        this.tipoFormatoEvaluacionList = tipoFormatoEvaluacionList;
    }

    @XmlTransient
    public List<TipoFormatoEvaluacion> getTipoFormatoEvaluacionList1() {
        return tipoFormatoEvaluacionList1;
    }

    public void setTipoFormatoEvaluacionList1(List<TipoFormatoEvaluacion> tipoFormatoEvaluacionList1) {
        this.tipoFormatoEvaluacionList1 = tipoFormatoEvaluacionList1;
    }

    @XmlTransient
    public List<EvaluacionProveedor> getEvaluacionProveedorList() {
        return evaluacionProveedorList;
    }

    public void setEvaluacionProveedorList(List<EvaluacionProveedor> evaluacionProveedorList) {
        this.evaluacionProveedorList = evaluacionProveedorList;
    }

    @XmlTransient
    public List<EvaluacionProveedor> getEvaluacionProveedorList1() {
        return evaluacionProveedorList1;
    }

    public void setEvaluacionProveedorList1(List<EvaluacionProveedor> evaluacionProveedorList1) {
        this.evaluacionProveedorList1 = evaluacionProveedorList1;
    }

    @XmlTransient
    public List<EvaluacionProveedor> getEvaluacionProveedorList2() {
        return evaluacionProveedorList2;
    }

    public void setEvaluacionProveedorList2(List<EvaluacionProveedor> evaluacionProveedorList2) {
        this.evaluacionProveedorList2 = evaluacionProveedorList2;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList1() {
        return departamentoList1;
    }

    public void setDepartamentoList1(List<Departamento> departamentoList1) {
        this.departamentoList1 = departamentoList1;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList1() {
        return proveedorList1;
    }

    public void setProveedorList1(List<Proveedor> proveedorList1) {
        this.proveedorList1 = proveedorList1;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @XmlTransient
    public List<Cargo> getCargoList1() {
        return cargoList1;
    }

    public void setCargoList1(List<Cargo> cargoList1) {
        this.cargoList1 = cargoList1;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList1() {
        return empresaList1;
    }

    public void setEmpresaList1(List<Empresa> empresaList1) {
        this.empresaList1 = empresaList1;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList() {
        return ordenCompraList;
    }

    public void setOrdenCompraList(List<OrdenCompra> ordenCompraList) {
        this.ordenCompraList = ordenCompraList;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList1() {
        return ordenCompraList1;
    }

    public void setOrdenCompraList1(List<OrdenCompra> ordenCompraList1) {
        this.ordenCompraList1 = ordenCompraList1;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList2() {
        return ordenCompraList2;
    }

    public void setOrdenCompraList2(List<OrdenCompra> ordenCompraList2) {
        this.ordenCompraList2 = ordenCompraList2;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList3() {
        return ordenCompraList3;
    }

    public void setOrdenCompraList3(List<OrdenCompra> ordenCompraList3) {
        this.ordenCompraList3 = ordenCompraList3;
    }

   

    @XmlTransient
    public List<TipoAnalisis> getTipoAnalisisList() {
        return tipoAnalisisList;
    }

    public void setTipoAnalisisList(List<TipoAnalisis> tipoAnalisisList) {
        this.tipoAnalisisList = tipoAnalisisList;
    }

    @XmlTransient
    public List<TipoAnalisis> getTipoAnalisisList1() {
        return tipoAnalisisList1;
    }

    public void setTipoAnalisisList1(List<TipoAnalisis> tipoAnalisisList1) {
        this.tipoAnalisisList1 = tipoAnalisisList1;
    }

   

    @XmlTransient
    public List<MatrizAnalisis> getMatrizAnalisisList() {
        return matrizAnalisisList;
    }

    public void setMatrizAnalisisList(List<MatrizAnalisis> matrizAnalisisList) {
        this.matrizAnalisisList = matrizAnalisisList;
    }

    @XmlTransient
    public List<MatrizAnalisis> getMatrizAnalisisList1() {
        return matrizAnalisisList1;
    }

    public void setMatrizAnalisisList1(List<MatrizAnalisis> matrizAnalisisList1) {
        this.matrizAnalisisList1 = matrizAnalisisList1;
    }

    @XmlTransient
    public List<VariableAnalisis> getVariableAnalisisList() {
        return variableAnalisisList;
    }

    public void setVariableAnalisisList(List<VariableAnalisis> variableAnalisisList) {
        this.variableAnalisisList = variableAnalisisList;
    }

  

    @XmlTransient
    public List<EquipamientoInterno> getEquipamientoInternoList() {
        return equipamientoInternoList;
    }

    public void setEquipamientoInternoList(List<EquipamientoInterno> equipamientoInternoList) {
        this.equipamientoInternoList = equipamientoInternoList;
    }

    @XmlTransient
    public List<EquipamientoInterno> getEquipamientoInternoList1() {
        return equipamientoInternoList1;
    }

    public void setEquipamientoInternoList1(List<EquipamientoInterno> equipamientoInternoList1) {
        this.equipamientoInternoList1 = equipamientoInternoList1;
    }

    @XmlTransient
    public List<HojaVida> getHojaVidaList() {
        return hojaVidaList;
    }

    public void setHojaVidaList(List<HojaVida> hojaVidaList) {
        this.hojaVidaList = hojaVidaList;
    }

    @XmlTransient
    public List<HojaVida> getHojaVidaList1() {
        return hojaVidaList1;
    }

    public void setHojaVidaList1(List<HojaVida> hojaVidaList1) {
        this.hojaVidaList1 = hojaVidaList1;
    }



    @XmlTransient
    public List<VariableCalculo> getVariableCalculoList() {
        return variableCalculoList;
    }

    public void setVariableCalculoList(List<VariableCalculo> variableCalculoList) {
        this.variableCalculoList = variableCalculoList;
    }

    @XmlTransient
    public List<VariableCalculo> getVariableCalculoList1() {
        return variableCalculoList1;
    }

    public void setVariableCalculoList1(List<VariableCalculo> variableCalculoList1) {
        this.variableCalculoList1 = variableCalculoList1;
    }

   

   

  
    
}
