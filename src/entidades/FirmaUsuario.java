/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "firma_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FirmaUsuario.findAll", query = "SELECT f FROM FirmaUsuario f"),
    @NamedQuery(name = "FirmaUsuario.findByIdFirmasUsuario", query = "SELECT f FROM FirmaUsuario f WHERE f.idFirmasUsuario = :idFirmasUsuario")})
public class FirmaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_firmusua_id", nullable = false)
    private Integer idFirmasUsuario;
    @Lob
    @Column(name = "by_firmusua_firma")
    private byte[] firmaUsuario;
    @JoinColumn(name = "fs_firmusua_usuario", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario usuario;

    public FirmaUsuario() {
    }

    public FirmaUsuario(Integer idFirmasUsuario) {
        this.idFirmasUsuario = idFirmasUsuario;
    }

    public Integer getIdFirmasUsuario() {
        return idFirmasUsuario;
    }

    public void setIdFirmasUsuario(Integer idFirmasUsuario) {
        this.idFirmasUsuario = idFirmasUsuario;
    }

    public byte[] getFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(byte[] firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public SUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(SUsuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFirmasUsuario != null ? idFirmasUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirmaUsuario)) {
            return false;
        }
        FirmaUsuario other = (FirmaUsuario) object;
        if ((this.idFirmasUsuario == null && other.idFirmasUsuario != null) || (this.idFirmasUsuario != null && !this.idFirmasUsuario.equals(other.idFirmasUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FirmasUsuario[ idFirmasUsuario=" + idFirmasUsuario + " ]";
    }
    
}
