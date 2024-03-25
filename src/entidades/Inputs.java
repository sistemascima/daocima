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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "inputs", catalog = "cima", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inputs.findAll", query = "SELECT i FROM Inputs i"),
    @NamedQuery(name = "Inputs.findById", query = "SELECT i FROM Inputs i WHERE i.id = :id"),
    @NamedQuery(name = "Inputs.findByHardwareId", query = "SELECT i FROM Inputs i WHERE i.hardwareId = :hardwareId"),
    @NamedQuery(name = "Inputs.findByMarcateclado", query = "SELECT i FROM Inputs i WHERE i.marcateclado = :marcateclado"),
    @NamedQuery(name = "Inputs.findByMarcamouse", query = "SELECT i FROM Inputs i WHERE i.marcamouse = :marcamouse"),
    @NamedQuery(name = "Inputs.findBySerialteclado", query = "SELECT i FROM Inputs i WHERE i.serialteclado = :serialteclado"),
    @NamedQuery(name = "Inputs.findBySerialmouse", query = "SELECT i FROM Inputs i WHERE i.serialmouse = :serialmouse"),
    @NamedQuery(name = "Inputs.findByObservacion", query = "SELECT i FROM Inputs i WHERE i.observacion = :observacion")})
public class Inputs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_inputs_id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "i_inputs_hardid", nullable = false)
    private int hardwareId;
    @Column(name = "s_inputs_marctecl", length = 255)
    private String marcateclado;
    @Column(name = "s_inptus_marcmous", length = 255)
    private String marcamouse;
    @Column(name = "s_inputs_seritecl", length = 255)
    private String serialteclado;
    @Column(name = "s_inputs_serimous", length = 255)
    private String serialmouse;
    @Column(name = "s_inputs_obse", length = 255)
    private String observacion;

    public Inputs() {
    }

    public Inputs(Integer id) {
        this.id = id;
    }

    public Inputs(Integer id, int hardwareId) {
        this.id = id;
        this.hardwareId = hardwareId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getMarcateclado() {
        return marcateclado;
    }

    public void setMarcateclado(String marcateclado) {
        this.marcateclado = marcateclado;
    }

    public String getMarcamouse() {
        return marcamouse;
    }

    public void setMarcamouse(String marcamouse) {
        this.marcamouse = marcamouse;
    }

    public String getSerialteclado() {
        return serialteclado;
    }

    public void setSerialteclado(String serialteclado) {
        this.serialteclado = serialteclado;
    }

    public String getSerialmouse() {
        return serialmouse;
    }

    public void setSerialmouse(String serialmouse) {
        this.serialmouse = serialmouse;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inputs)) {
            return false;
        }
        Inputs other = (Inputs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Inputs[ id=" + id + " ]";
    }
    
}
