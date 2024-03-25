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
 * @author TOSHIBA
 */
@Entity
@Table(name = "analista_parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalistaParametro.findAll", query = "SELECT a FROM AnalistaParametro a"),
    @NamedQuery(name = "AnalistaParametro.findByIdanalistaParametro", query = "SELECT a FROM AnalistaParametro a WHERE a.idanalistaParametro = :idanalistaParametro")})
public class AnalistaParametro implements Serializable {


  
  
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pi_analparam_id", nullable = false)
    private Integer idanalistaParametro;
    @JoinColumn(name = "fs_analparam_usutitul", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioTitular;
    @JoinColumn(name = "fi_analparam_varianal", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis idParametro;
    @JoinColumn(name = "fs_analparam_ususupl", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuarioSuplente;
    @JoinColumn(name = "fs_analparam_usuterc", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario segundo_usuario_suplente;

    public AnalistaParametro() {
    }

    public AnalistaParametro(Integer idanalistaParametro) {
        this.idanalistaParametro = idanalistaParametro;
    }

    public Integer getIdanalistaParametro() {
        return idanalistaParametro;
    }

    public void setIdanalistaParametro(Integer idanalistaParametro) {
        this.idanalistaParametro = idanalistaParametro;
    }

    public SUsuario getUsuarioTitular() {
        return usuarioTitular;
    }

    public void setUsuarioTitular(SUsuario usuarioTitular) {
        this.usuarioTitular = usuarioTitular;
    }

    public VariableAnalisis getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(VariableAnalisis idParametro) {
        this.idParametro = idParametro;
    }

    public SUsuario getUsuarioSuplente() {
        return usuarioSuplente;
    }

    public void setUsuarioSuplente(SUsuario usuarioSuplente) {
        this.usuarioSuplente = usuarioSuplente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanalistaParametro != null ? idanalistaParametro.hashCode() : 0);
        return hash;
    }

    public SUsuario getSegundo_usuario_suplente() {
        return segundo_usuario_suplente;
    }

    public void setSegundo_usuario_suplente(SUsuario segundo_usuario_suplente) {
        this.segundo_usuario_suplente = segundo_usuario_suplente;
    }

    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnalistaParametro)) {
            return false;
        }
        AnalistaParametro other = (AnalistaParametro) object;
        if ((this.idanalistaParametro == null && other.idanalistaParametro != null) || (this.idanalistaParametro != null && !this.idanalistaParametro.equals(other.idanalistaParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AnalistaParametro[ idanalistaParametro=" + idanalistaParametro + " ]";
    }

}
