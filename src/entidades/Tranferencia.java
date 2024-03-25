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
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "tranferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tranferencia.findAll", query = "SELECT t FROM Tranferencia t")
    , @NamedQuery(name = "Tranferencia.findByPiTranferencia", query = "SELECT t FROM Tranferencia t WHERE t.piTranferencia = :piTranferencia")
    , @NamedQuery(name = "Tranferencia.findByDTranferenciaFechaInicio", query = "SELECT t FROM Tranferencia t WHERE t.dTranferenciaFechaInicio = :dTranferenciaFechaInicio")
    , @NamedQuery(name = "Tranferencia.findByDTranferenciaFechaFin", query = "SELECT t FROM Tranferencia t WHERE t.dTranferenciaFechaFin = :dTranferenciaFechaFin")
    , @NamedQuery(name = "Tranferencia.findByITranferenciaProyectoId", query = "SELECT t FROM Tranferencia t WHERE t.iTranferenciaProyectoId = :iTranferenciaProyectoId")
    , @NamedQuery(name = "Tranferencia.findBySTranferenciaEstado", query = "SELECT t FROM Tranferencia t WHERE t.sTranferenciaEstado = :sTranferenciaEstado")})
public class Tranferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_tranferencia", nullable = false)
    private Integer piTranferencia;
    @Basic(optional = false)
    @Column(name = "d_tranferencia_fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dTranferenciaFechaInicio;
    @Basic(optional = false)
    @Column(name = "d_tranferencia_fecha_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dTranferenciaFechaFin;
    @Basic(optional = false)
    @Column(name = "i_tranferencia_proyecto_id", nullable = false)
    private int iTranferenciaProyectoId;
    @Basic(optional = false)
    @Column(name = "s_tranferencia_estado", nullable = false, length = 45)
    private String sTranferenciaEstado;
    @JoinColumn(name = "s_tranferencia_objeto_id", referencedColumnName = "pi_objeto", nullable = false)
    @ManyToOne(optional = false)
    private Objeto sTranferenciaObjetoId;
    @JoinColumn(name = "s_tranferencia_responsable_entrega_entrada", referencedColumnName = "usuario_cod", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario sTranferenciaResponsableEntregaEntrada;
    @JoinColumn(name = "s_tranferencia_responsable_entrega_salida", referencedColumnName = "usuario_cod", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario sTranferenciaResponsableEntregaSalida;
    @JoinColumn(name = "s_tranferencia_responsable_entrada_salida", referencedColumnName = "usuario_cod", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario sTranferenciaResponsableEntradaSalida;
    @JoinColumn(name = "s_tranferencia_responsable_recepcion_salida", referencedColumnName = "usuario_cod", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario sTranferenciaResponsableRecepcionSalida;
    @OneToMany(mappedBy = "iObjetoTranferenciaId")
    private List<Objeto> objetoList;

    public Tranferencia() {
    }

    public Tranferencia(Integer piTranferencia) {
        this.piTranferencia = piTranferencia;
    }

    public Tranferencia(Integer piTranferencia, Date dTranferenciaFechaInicio, Date dTranferenciaFechaFin, int iTranferenciaProyectoId, String sTranferenciaEstado) {
        this.piTranferencia = piTranferencia;
        this.dTranferenciaFechaInicio = dTranferenciaFechaInicio;
        this.dTranferenciaFechaFin = dTranferenciaFechaFin;
        this.iTranferenciaProyectoId = iTranferenciaProyectoId;
        this.sTranferenciaEstado = sTranferenciaEstado;
    }

    public Integer getPiTranferencia() {
        return piTranferencia;
    }

    public void setPiTranferencia(Integer piTranferencia) {
        this.piTranferencia = piTranferencia;
    }

    public Date getDTranferenciaFechaInicio() {
        return dTranferenciaFechaInicio;
    }

    public void setDTranferenciaFechaInicio(Date dTranferenciaFechaInicio) {
        this.dTranferenciaFechaInicio = dTranferenciaFechaInicio;
    }

    public Date getDTranferenciaFechaFin() {
        return dTranferenciaFechaFin;
    }

    public void setDTranferenciaFechaFin(Date dTranferenciaFechaFin) {
        this.dTranferenciaFechaFin = dTranferenciaFechaFin;
    }

    public int getITranferenciaProyectoId() {
        return iTranferenciaProyectoId;
    }

    public void setITranferenciaProyectoId(int iTranferenciaProyectoId) {
        this.iTranferenciaProyectoId = iTranferenciaProyectoId;
    }

    public String getSTranferenciaEstado() {
        return sTranferenciaEstado;
    }

    public void setSTranferenciaEstado(String sTranferenciaEstado) {
        this.sTranferenciaEstado = sTranferenciaEstado;
    }

    public Objeto getSTranferenciaObjetoId() {
        return sTranferenciaObjetoId;
    }

    public void setSTranferenciaObjetoId(Objeto sTranferenciaObjetoId) {
        this.sTranferenciaObjetoId = sTranferenciaObjetoId;
    }

    public SUsuario getSTranferenciaResponsableEntregaEntrada() {
        return sTranferenciaResponsableEntregaEntrada;
    }

    public void setSTranferenciaResponsableEntregaEntrada(SUsuario sTranferenciaResponsableEntregaEntrada) {
        this.sTranferenciaResponsableEntregaEntrada = sTranferenciaResponsableEntregaEntrada;
    }

    public SUsuario getSTranferenciaResponsableEntregaSalida() {
        return sTranferenciaResponsableEntregaSalida;
    }

    public void setSTranferenciaResponsableEntregaSalida(SUsuario sTranferenciaResponsableEntregaSalida) {
        this.sTranferenciaResponsableEntregaSalida = sTranferenciaResponsableEntregaSalida;
    }

    public SUsuario getSTranferenciaResponsableEntradaSalida() {
        return sTranferenciaResponsableEntradaSalida;
    }

    public void setSTranferenciaResponsableEntradaSalida(SUsuario sTranferenciaResponsableEntradaSalida) {
        this.sTranferenciaResponsableEntradaSalida = sTranferenciaResponsableEntradaSalida;
    }

    public SUsuario getSTranferenciaResponsableRecepcionSalida() {
        return sTranferenciaResponsableRecepcionSalida;
    }

    public void setSTranferenciaResponsableRecepcionSalida(SUsuario sTranferenciaResponsableRecepcionSalida) {
        this.sTranferenciaResponsableRecepcionSalida = sTranferenciaResponsableRecepcionSalida;
    }

    @XmlTransient
    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public void setObjetoList(List<Objeto> objetoList) {
        this.objetoList = objetoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piTranferencia != null ? piTranferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tranferencia)) {
            return false;
        }
        Tranferencia other = (Tranferencia) object;
        if ((this.piTranferencia == null && other.piTranferencia != null) || (this.piTranferencia != null && !this.piTranferencia.equals(other.piTranferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tranferencia[ piTranferencia=" + piTranferencia + " ]";
    }
    
}
