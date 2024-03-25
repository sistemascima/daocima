/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "sele_prov_soli_comp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeleProvSoliComp.findAll", query = "SELECT s FROM SeleProvSoliComp s"),
    @NamedQuery(name = "SeleProvSoliComp.findByPiSeprsocoNumeeval", query = "SELECT s FROM SeleProvSoliComp s WHERE s.piSeprsocoNumeeval = :piSeprsocoNumeeval"),
    @NamedQuery(name = "SeleProvSoliComp.findBySSeprsocoEvalserv", query = "SELECT s FROM SeleProvSoliComp s WHERE s.sSeprsocoEvalserv = :sSeprsocoEvalserv"),
    @NamedQuery(name = "SeleProvSoliComp.findBySSeprsocoEvalsumi", query = "SELECT s FROM SeleProvSoliComp s WHERE s.sSeprsocoEvalsumi = :sSeprsocoEvalsumi"),
    @NamedQuery(name = "SeleProvSoliComp.findBySSeprsocoEvalequi", query = "SELECT s FROM SeleProvSoliComp s WHERE s.sSeprsocoEvalequi = :sSeprsocoEvalequi"),
    @NamedQuery(name = "SeleProvSoliComp.findBySSeprsocoEstado", query = "SELECT s FROM SeleProvSoliComp s WHERE s.sSeprsocoEstado = :sSeprsocoEstado"),
    @NamedQuery(name = "SeleProvSoliComp.findBySSeprsocoObservaciones", query = "SELECT s FROM SeleProvSoliComp s WHERE s.sSeprsocoObservaciones = :sSeprsocoObservaciones"),
    @NamedQuery(name = "SeleProvSoliComp.findByFsSeprsocoUsuasele", query = "SELECT s FROM SeleProvSoliComp s WHERE s.fsSeprsocoUsuasele = :fsSeprsocoUsuasele"),
    @NamedQuery(name = "SeleProvSoliComp.findByDSeprsocoSeleccion", query = "SELECT s FROM SeleProvSoliComp s WHERE s.dSeprsocoSeleccion = :dSeprsocoSeleccion"),
    @NamedQuery(name = "SeleProvSoliComp.findByDSeprsocoCreacion", query = "SELECT s FROM SeleProvSoliComp s WHERE s.dSeprsocoCreacion = :dSeprsocoCreacion"),
    @NamedQuery(name = "SeleProvSoliComp.findByDSeprsocoUltimodi", query = "SELECT s FROM SeleProvSoliComp s WHERE s.dSeprsocoUltimodi = :dSeprsocoUltimodi")})
public class SeleProvSoliComp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_seprsoco_numeeval")
    private Integer piSeprsocoNumeeval;
    @Column(name = "s_seprsoco_evalserv")
    private Character sSeprsocoEvalserv;
    @Column(name = "s_seprsoco_evalsumi")
    private Character sSeprsocoEvalsumi;
    @Column(name = "s_seprsoco_evalequi")
    private Character sSeprsocoEvalequi;
    @Basic(optional = false)
    @Column(name = "s_seprsoco_estado")
    private Character sSeprsocoEstado;
    @Column(name = "s_seprsoco_observaciones")
    private String sSeprsocoObservaciones;
    @Column(name = "fs_seprsoco_usuasele")
    private String fsSeprsocoUsuasele;
    @Column(name = "d_seprsoco_seleccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSeprsocoSeleccion;
    @Basic(optional = false)
    @Column(name = "d_seprsoco_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSeprsocoCreacion;
    @Column(name = "d_seprsoco_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dSeprsocoUltimodi;
    @JoinColumn(name = "fs_seprsoco_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsSeprsocoUsuultmod;
    @JoinColumn(name = "fs_seprsoco_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsSeprsocoUsuacrea;
    @JoinColumn(name = "fi_seprsoco_solicomp", referencedColumnName = "pi_solicomp_consecutivo")
    @OneToOne(optional = false)
    private SolicitudCompra fiSeprsocoSolicomp;
    @JoinColumn(name = "fs_seprsoco_cargsele", referencedColumnName = "ps_cargo_codigo")
    @ManyToOne
    private Cargo fsSeprsocoCargsele;

    public SeleProvSoliComp() {
    }

    public SeleProvSoliComp(Integer piSeprsocoNumeeval) {
        this.piSeprsocoNumeeval = piSeprsocoNumeeval;
    }

    public SeleProvSoliComp(Integer piSeprsocoNumeeval, Character sSeprsocoEstado, Date dSeprsocoCreacion) {
        this.piSeprsocoNumeeval = piSeprsocoNumeeval;
        this.sSeprsocoEstado = sSeprsocoEstado;
        this.dSeprsocoCreacion = dSeprsocoCreacion;
    }

    public Integer getPiSeprsocoNumeeval() {
        return piSeprsocoNumeeval;
    }

    public void setPiSeprsocoNumeeval(Integer piSeprsocoNumeeval) {
        this.piSeprsocoNumeeval = piSeprsocoNumeeval;
    }

    public Character getSSeprsocoEvalserv() {
        return sSeprsocoEvalserv;
    }

    public void setSSeprsocoEvalserv(Character sSeprsocoEvalserv) {
        this.sSeprsocoEvalserv = sSeprsocoEvalserv;
    }

    public Character getSSeprsocoEvalsumi() {
        return sSeprsocoEvalsumi;
    }

    public void setSSeprsocoEvalsumi(Character sSeprsocoEvalsumi) {
        this.sSeprsocoEvalsumi = sSeprsocoEvalsumi;
    }

    public Character getSSeprsocoEvalequi() {
        return sSeprsocoEvalequi;
    }

    public void setSSeprsocoEvalequi(Character sSeprsocoEvalequi) {
        this.sSeprsocoEvalequi = sSeprsocoEvalequi;
    }

    public Character getSSeprsocoEstado() {
        return sSeprsocoEstado;
    }

    public void setSSeprsocoEstado(Character sSeprsocoEstado) {
        this.sSeprsocoEstado = sSeprsocoEstado;
    }

    public String getSSeprsocoObservaciones() {
        return sSeprsocoObservaciones;
    }

    public void setSSeprsocoObservaciones(String sSeprsocoObservaciones) {
        this.sSeprsocoObservaciones = sSeprsocoObservaciones;
    }

    public String getFsSeprsocoUsuasele() {
        return fsSeprsocoUsuasele;
    }

    public void setFsSeprsocoUsuasele(String fsSeprsocoUsuasele) {
        this.fsSeprsocoUsuasele = fsSeprsocoUsuasele;
    }

    public Date getDSeprsocoSeleccion() {
        return dSeprsocoSeleccion;
    }

    public void setDSeprsocoSeleccion(Date dSeprsocoSeleccion) {
        this.dSeprsocoSeleccion = dSeprsocoSeleccion;
    }

    public Date getDSeprsocoCreacion() {
        return dSeprsocoCreacion;
    }

    public void setDSeprsocoCreacion(Date dSeprsocoCreacion) {
        this.dSeprsocoCreacion = dSeprsocoCreacion;
    }

    public Date getDSeprsocoUltimodi() {
        return dSeprsocoUltimodi;
    }

    public void setDSeprsocoUltimodi(Date dSeprsocoUltimodi) {
        this.dSeprsocoUltimodi = dSeprsocoUltimodi;
    }

    public SUsuario getFsSeprsocoUsuultmod() {
        return fsSeprsocoUsuultmod;
    }

    public void setFsSeprsocoUsuultmod(SUsuario fsSeprsocoUsuultmod) {
        this.fsSeprsocoUsuultmod = fsSeprsocoUsuultmod;
    }

    public SUsuario getFsSeprsocoUsuacrea() {
        return fsSeprsocoUsuacrea;
    }

    public void setFsSeprsocoUsuacrea(SUsuario fsSeprsocoUsuacrea) {
        this.fsSeprsocoUsuacrea = fsSeprsocoUsuacrea;
    }

    public SolicitudCompra getFiSeprsocoSolicomp() {
        return fiSeprsocoSolicomp;
    }

    public void setFiSeprsocoSolicomp(SolicitudCompra fiSeprsocoSolicomp) {
        this.fiSeprsocoSolicomp = fiSeprsocoSolicomp;
    }

    public Cargo getFsSeprsocoCargsele() {
        return fsSeprsocoCargsele;
    }

    public void setFsSeprsocoCargsele(Cargo fsSeprsocoCargsele) {
        this.fsSeprsocoCargsele = fsSeprsocoCargsele;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piSeprsocoNumeeval != null ? piSeprsocoNumeeval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeleProvSoliComp)) {
            return false;
        }
        SeleProvSoliComp other = (SeleProvSoliComp) object;
        if ((this.piSeprsocoNumeeval == null && other.piSeprsocoNumeeval != null) || (this.piSeprsocoNumeeval != null && !this.piSeprsocoNumeeval.equals(other.piSeprsocoNumeeval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SeleProvSoliComp[ piSeprsocoNumeeval=" + piSeprsocoNumeeval + " ]";
    }
    
}
