/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "objeto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objeto.findAll", query = "SELECT o FROM Objeto o")
    , @NamedQuery(name = "Objeto.findByPiObjeto", query = "SELECT o FROM Objeto o WHERE o.piObjeto = :piObjeto")})
public class Objeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_objeto", nullable = false, length = 45)
    private String piObjeto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sTranferenciaObjetoId")
    private List<Tranferencia> tranferenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sAnexoObjetoId")
    private List<Anexo> anexoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sHojaDeVidaObjetoId")
    private List<HojaDeVida> hojaDeVidaList;
    @JoinColumn(name = "i_objeto_anexo_id", referencedColumnName = "pi_anexo")
    @ManyToOne
    private Anexo iObjetoAnexoId;
    @JoinColumn(name = "i_objeto_mantenimiento_id", referencedColumnName = "pi_mantenimiento")
    @ManyToOne
    private Mantenimiento iObjetoMantenimientoId;
    @JoinColumn(name = "i_objeto_sede_id", referencedColumnName = "pi_sede", nullable = false)
    @ManyToOne(optional = false)
    private Sede iObjetoSedeId;
    @JoinColumn(name = "i_objeto_tranferencia_id", referencedColumnName = "pi_tranferencia")
    @ManyToOne
    private Tranferencia iObjetoTranferenciaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sMantenimientoObjetoId")
    private List<Mantenimiento> mantenimientoList;

    public Objeto() {
    }

    public Objeto(String piObjeto) {
        this.piObjeto = piObjeto;
    }

    public String getPiObjeto() {
        return piObjeto;
    }

    public void setPiObjeto(String piObjeto) {
        this.piObjeto = piObjeto;
    }

    @XmlTransient
    public List<Tranferencia> getTranferenciaList() {
        return tranferenciaList;
    }

    public void setTranferenciaList(List<Tranferencia> tranferenciaList) {
        this.tranferenciaList = tranferenciaList;
    }

    @XmlTransient
    public List<Anexo> getAnexoList() {
        return anexoList;
    }

    public void setAnexoList(List<Anexo> anexoList) {
        this.anexoList = anexoList;
    }

    @XmlTransient
    public List<HojaDeVida> getHojaDeVidaList() {
        return hojaDeVidaList;
    }

    public void setHojaDeVidaList(List<HojaDeVida> hojaDeVidaList) {
        this.hojaDeVidaList = hojaDeVidaList;
    }

    public Anexo getIObjetoAnexoId() {
        return iObjetoAnexoId;
    }

    public void setIObjetoAnexoId(Anexo iObjetoAnexoId) {
        this.iObjetoAnexoId = iObjetoAnexoId;
    }

    public Mantenimiento getIObjetoMantenimientoId() {
        return iObjetoMantenimientoId;
    }

    public void setIObjetoMantenimientoId(Mantenimiento iObjetoMantenimientoId) {
        this.iObjetoMantenimientoId = iObjetoMantenimientoId;
    }

    public Sede getIObjetoSedeId() {
        return iObjetoSedeId;
    }

    public void setIObjetoSedeId(Sede iObjetoSedeId) {
        this.iObjetoSedeId = iObjetoSedeId;
    }

    public Tranferencia getIObjetoTranferenciaId() {
        return iObjetoTranferenciaId;
    }

    public void setIObjetoTranferenciaId(Tranferencia iObjetoTranferenciaId) {
        this.iObjetoTranferenciaId = iObjetoTranferenciaId;
    }

   

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piObjeto != null ? piObjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objeto)) {
            return false;
        }
        Objeto other = (Objeto) object;
        if ((this.piObjeto == null && other.piObjeto != null) || (this.piObjeto != null && !this.piObjeto.equals(other.piObjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Objeto[ piObjeto=" + piObjeto + " ]";
    }
    
}
