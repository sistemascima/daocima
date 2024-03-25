/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Felipe Fontecha
 */
@Entity
@Table(name = "anexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexo.findAll", query = "SELECT a FROM Anexo a")
    , @NamedQuery(name = "Anexo.findByPiAnexo", query = "SELECT a FROM Anexo a WHERE a.piAnexo = :piAnexo")
    , @NamedQuery(name = "Anexo.findBySAnexoCertificado", query = "SELECT a FROM Anexo a WHERE a.sAnexoCertificado = :sAnexoCertificado")
    , @NamedQuery(name = "Anexo.findBySAnexoInstructivo", query = "SELECT a FROM Anexo a WHERE a.sAnexoInstructivo = :sAnexoInstructivo")
    , @NamedQuery(name = "Anexo.findBySAnexoOtro", query = "SELECT a FROM Anexo a WHERE a.sAnexoOtro = :sAnexoOtro")})
public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_anexo", nullable = false)
    private Integer piAnexo;
    @Column(name = "s_anexo_certificado", length = 45)
    private String sAnexoCertificado;
    @Column(name = "s_anexo_instructivo", length = 45)
    private String sAnexoInstructivo;
    @Column(name = "s_anexo_otro", length = 45)
    private String sAnexoOtro;
    @JoinColumn(name = "s_anexo_objeto_id", referencedColumnName = "pi_objeto", nullable = false)
    @ManyToOne(optional = false)
    private Objeto sAnexoObjetoId;
    @OneToMany(mappedBy = "iObjetoAnexoId")
    private List<Objeto> objetoList;

    public Anexo() {
    }

    public Anexo(Integer piAnexo) {
        this.piAnexo = piAnexo;
    }

    public Integer getPiAnexo() {
        return piAnexo;
    }

    public void setPiAnexo(Integer piAnexo) {
        this.piAnexo = piAnexo;
    }

    public String getSAnexoCertificado() {
        return sAnexoCertificado;
    }

    public void setSAnexoCertificado(String sAnexoCertificado) {
        this.sAnexoCertificado = sAnexoCertificado;
    }

    public String getSAnexoInstructivo() {
        return sAnexoInstructivo;
    }

    public void setSAnexoInstructivo(String sAnexoInstructivo) {
        this.sAnexoInstructivo = sAnexoInstructivo;
    }

    public String getSAnexoOtro() {
        return sAnexoOtro;
    }

    public void setSAnexoOtro(String sAnexoOtro) {
        this.sAnexoOtro = sAnexoOtro;
    }

    public Objeto getSAnexoObjetoId() {
        return sAnexoObjetoId;
    }

    public void setSAnexoObjetoId(Objeto sAnexoObjetoId) {
        this.sAnexoObjetoId = sAnexoObjetoId;
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
        hash += (piAnexo != null ? piAnexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexo)) {
            return false;
        }
        Anexo other = (Anexo) object;
        if ((this.piAnexo == null && other.piAnexo != null) || (this.piAnexo != null && !this.piAnexo.equals(other.piAnexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Anexo[ piAnexo=" + piAnexo + " ]";
    }
    
}
