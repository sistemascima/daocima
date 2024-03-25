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
import javax.persistence.Lob;
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
@Table(name = "detalle_servicio_interno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleServicioInterno.findAll", query = "SELECT d FROM DetalleServicioInterno d"),
    @NamedQuery(name = "DetalleServicioInterno.findByPfiDetserintServinte", query = "SELECT d FROM DetalleServicioInterno d WHERE d.detalleServicioInternoPK.pfiDetserintServinte = :pfiDetserintServinte"),
    @NamedQuery(name = "DetalleServicioInterno.findByPiDetserintItem", query = "SELECT d FROM DetalleServicioInterno d WHERE d.detalleServicioInternoPK.piDetserintItem = :piDetserintItem"),
    @NamedQuery(name = "DetalleServicioInterno.findBySDetserintConcepto", query = "SELECT d FROM DetalleServicioInterno d WHERE d.sDetserintConcepto = :sDetserintConcepto"),
    @NamedQuery(name = "DetalleServicioInterno.findByDbDetserintCantidad", query = "SELECT d FROM DetalleServicioInterno d WHERE d.dbDetserintCantidad = :dbDetserintCantidad"),
    @NamedQuery(name = "DetalleServicioInterno.findByDDetserintFechrequ", query = "SELECT d FROM DetalleServicioInterno d WHERE d.dDetserintFechrequ = :dDetserintFechrequ"),
    @NamedQuery(name = "DetalleServicioInterno.findByDDetserintCreacion", query = "SELECT d FROM DetalleServicioInterno d WHERE d.dDetserintCreacion = :dDetserintCreacion"),
    @NamedQuery(name = "DetalleServicioInterno.findByDDetserintUltimodi", query = "SELECT d FROM DetalleServicioInterno d WHERE d.dDetserintUltimodi = :dDetserintUltimodi")})
public class DetalleServicioInterno implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleServicioInternoPK detalleServicioInternoPK;
    @Basic(optional = false)
    @Column(name = "s_detserint_concepto")
    private String sDetserintConcepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "db_detserint_cantidad")
    private BigDecimal dbDetserintCantidad;
    @Column(name = "d_detserint_fechrequ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetserintFechrequ;
    @Lob
    @Column(name = "s_detserint_observaciones")
    private String sDetserintObservaciones;
    @Basic(optional = false)
    @Column(name = "d_detserint_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetserintCreacion;
    @Column(name = "d_detserint_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDetserintUltimodi;
    @JoinColumn(name = "fs_detserint_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsDetserintUsuultmod;
    @JoinColumn(name = "fs_detserint_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsDetserintUsuacrea;
    @JoinColumn(name = "pfi_detserint_servinte", referencedColumnName = "pi_servinte_consecutivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServicioInterno servicioInterno;

    public DetalleServicioInterno() {
    }

    public DetalleServicioInterno(DetalleServicioInternoPK detalleServicioInternoPK) {
        this.detalleServicioInternoPK = detalleServicioInternoPK;
    }

    public DetalleServicioInterno(DetalleServicioInternoPK detalleServicioInternoPK, String sDetserintConcepto, BigDecimal dbDetserintCantidad, Date dDetserintCreacion) {
        this.detalleServicioInternoPK = detalleServicioInternoPK;
        this.sDetserintConcepto = sDetserintConcepto;
        this.dbDetserintCantidad = dbDetserintCantidad;
        this.dDetserintCreacion = dDetserintCreacion;
    }

    public DetalleServicioInterno(int pfiDetserintServinte, int piDetserintItem) {
        this.detalleServicioInternoPK = new DetalleServicioInternoPK(pfiDetserintServinte, piDetserintItem);
    }

    public DetalleServicioInternoPK getDetalleServicioInternoPK() {
        return detalleServicioInternoPK;
    }

    public void setDetalleServicioInternoPK(DetalleServicioInternoPK detalleServicioInternoPK) {
        this.detalleServicioInternoPK = detalleServicioInternoPK;
    }

    public String getSDetserintConcepto() {
        return sDetserintConcepto;
    }

    public void setSDetserintConcepto(String sDetserintConcepto) {
        this.sDetserintConcepto = sDetserintConcepto;
    }

    public BigDecimal getDbDetserintCantidad() {
        return dbDetserintCantidad;
    }

    public void setDbDetserintCantidad(BigDecimal dbDetserintCantidad) {
        this.dbDetserintCantidad = dbDetserintCantidad;
    }

    public Date getDDetserintFechrequ() {
        return dDetserintFechrequ;
    }

    public void setDDetserintFechrequ(Date dDetserintFechrequ) {
        this.dDetserintFechrequ = dDetserintFechrequ;
    }

    public String getSDetserintObservaciones() {
        return sDetserintObservaciones;
    }

    public void setSDetserintObservaciones(String sDetserintObservaciones) {
        this.sDetserintObservaciones = sDetserintObservaciones;
    }

    public Date getDDetserintCreacion() {
        return dDetserintCreacion;
    }

    public void setDDetserintCreacion(Date dDetserintCreacion) {
        this.dDetserintCreacion = dDetserintCreacion;
    }

    public Date getDDetserintUltimodi() {
        return dDetserintUltimodi;
    }

    public void setDDetserintUltimodi(Date dDetserintUltimodi) {
        this.dDetserintUltimodi = dDetserintUltimodi;
    }

    public SUsuario getFsDetserintUsuultmod() {
        return fsDetserintUsuultmod;
    }

    public void setFsDetserintUsuultmod(SUsuario fsDetserintUsuultmod) {
        this.fsDetserintUsuultmod = fsDetserintUsuultmod;
    }

    public SUsuario getFsDetserintUsuacrea() {
        return fsDetserintUsuacrea;
    }

    public void setFsDetserintUsuacrea(SUsuario fsDetserintUsuacrea) {
        this.fsDetserintUsuacrea = fsDetserintUsuacrea;
    }

    public ServicioInterno getServicioInterno() {
        return servicioInterno;
    }

    public void setServicioInterno(ServicioInterno servicioInterno) {
        this.servicioInterno = servicioInterno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleServicioInternoPK != null ? detalleServicioInternoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicioInterno)) {
            return false;
        }
        DetalleServicioInterno other = (DetalleServicioInterno) object;
        if ((this.detalleServicioInternoPK == null && other.detalleServicioInternoPK != null) || (this.detalleServicioInternoPK != null && !this.detalleServicioInternoPK.equals(other.detalleServicioInternoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sDetserintConcepto;
    }
    
}
