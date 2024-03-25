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
 * @author TOSHIBA
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByPiEmpresaId", query = "SELECT e FROM Empresa e WHERE e.piEmpresaId = :piEmpresaId"),
    @NamedQuery(name = "Empresa.findByPsEmpresaNombre", query = "SELECT e FROM Empresa e WHERE e.psEmpresaNombre = :psEmpresaNombre"),
    @NamedQuery(name = "Empresa.findByPsEmpresaNit", query = "SELECT e FROM Empresa e WHERE e.psEmpresaNit = :psEmpresaNit"),
    @NamedQuery(name = "Empresa.findByDEmpresaFechCreacion", query = "SELECT e FROM Empresa e WHERE e.dEmpresaFechCreacion = :dEmpresaFechCreacion"),
    @NamedQuery(name = "Empresa.findByDEmpresaFechModificacion", query = "SELECT e FROM Empresa e WHERE e.dEmpresaFechModificacion = :dEmpresaFechModificacion")})
public class Empresa implements Serializable {

 
  
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_empresa_id", nullable = false)
    private Integer piEmpresaId;
    @Column(name = "ps_empresa_nombre", length = 45)
    private String psEmpresaNombre;
    @Column(name = "ps_empresa_nit", length = 45)
    private String psEmpresaNit;
    @Column(name = "d_empresa_fech_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEmpresaFechCreacion;
    @Column(name = "d_empresa_fech_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dEmpresaFechModificacion;
    @JoinColumn(name = "fs_empresa_usuario_creacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsEmpresaUsuarioCreacion;
    @JoinColumn(name = "fs_empresa_usuario_modificacion", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsEmpresaUsuarioModificacion;

    public Empresa() {
    }

    public Empresa(Integer piEmpresaId) {
        this.piEmpresaId = piEmpresaId;
    }

    public Integer getPiEmpresaId() {
        return piEmpresaId;
    }

    public void setPiEmpresaId(Integer piEmpresaId) {
        this.piEmpresaId = piEmpresaId;
    }

    public String getPsEmpresaNombre() {
        return psEmpresaNombre;
    }

    public void setPsEmpresaNombre(String psEmpresaNombre) {
        this.psEmpresaNombre = psEmpresaNombre;
    }

    public String getPsEmpresaNit() {
        return psEmpresaNit;
    }

    public void setPsEmpresaNit(String psEmpresaNit) {
        this.psEmpresaNit = psEmpresaNit;
    }

    public Date getDEmpresaFechCreacion() {
        return dEmpresaFechCreacion;
    }

    public void setDEmpresaFechCreacion(Date dEmpresaFechCreacion) {
        this.dEmpresaFechCreacion = dEmpresaFechCreacion;
    }

    public Date getDEmpresaFechModificacion() {
        return dEmpresaFechModificacion;
    }

    public void setDEmpresaFechModificacion(Date dEmpresaFechModificacion) {
        this.dEmpresaFechModificacion = dEmpresaFechModificacion;
    }

    public SUsuario getFsEmpresaUsuarioCreacion() {
        return fsEmpresaUsuarioCreacion;
    }

    public void setFsEmpresaUsuarioCreacion(SUsuario fsEmpresaUsuarioCreacion) {
        this.fsEmpresaUsuarioCreacion = fsEmpresaUsuarioCreacion;
    }

    public SUsuario getFsEmpresaUsuarioModificacion() {
        return fsEmpresaUsuarioModificacion;
    }

    public void setFsEmpresaUsuarioModificacion(SUsuario fsEmpresaUsuarioModificacion) {
        this.fsEmpresaUsuarioModificacion = fsEmpresaUsuarioModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piEmpresaId != null ? piEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.piEmpresaId == null && other.piEmpresaId != null) || (this.piEmpresaId != null && !this.piEmpresaId.equals(other.piEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empresa[ piEmpresaId=" + piEmpresaId + " ]";
    }

   
   

    
    
}
