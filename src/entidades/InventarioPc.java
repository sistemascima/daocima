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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "inventario_pc", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioPc.findAll", query = "SELECT i FROM InventarioPc i"),
    @NamedQuery(name = "InventarioPc.findByIdInv", query = "SELECT i FROM InventarioPc i WHERE i.idInv = :idInv"),
    @NamedQuery(name = "InventarioPc.findByNombre", query = "SELECT i FROM InventarioPc i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InventarioPc.findByUserwin", query = "SELECT i FROM InventarioPc i WHERE i.userwin = :userwin"),
    @NamedQuery(name = "InventarioPc.findByModelo", query = "SELECT i FROM InventarioPc i WHERE i.modelo = :modelo"),
    @NamedQuery(name = "InventarioPc.findByNSerial", query = "SELECT i FROM InventarioPc i WHERE i.nSerial = :nSerial"),
    @NamedQuery(name = "InventarioPc.findByProcesador", query = "SELECT i FROM InventarioPc i WHERE i.procesador = :procesador"),
    @NamedQuery(name = "InventarioPc.findByDisco", query = "SELECT i FROM InventarioPc i WHERE i.disco = :disco"),
    @NamedQuery(name = "InventarioPc.findByMemoria", query = "SELECT i FROM InventarioPc i WHERE i.memoria = :memoria"),
    @NamedQuery(name = "InventarioPc.findBySo", query = "SELECT i FROM InventarioPc i WHERE i.so = :so")})
public class InventarioPc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_inv", nullable = false)
    private Integer idInv;
    @Column(name = "nombre", length = 255)
    private String nombre;
    @Column(name = "userwin", length = 255)
    private String userwin;
    @Column(name = "modelo", length = 255)
    private String modelo;
    @Column(name = "n_serial", length = 255)
    private String nSerial;
    @Column(name = "procesador", length = 255)
    private String procesador;
    @Column(name = "disco")
    private Integer disco;
    @Column(name = "memoria")
    private Integer memoria;
    @Column(name = "so", length = 50)
    private String so;

    public InventarioPc() {
    }

    public InventarioPc(Integer idInv) {
        this.idInv = idInv;
    }

    public Integer getIdInv() {
        return idInv;
    }

    public void setIdInv(Integer idInv) {
        this.idInv = idInv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserwin() {
        return userwin;
    }

    public void setUserwin(String userwin) {
        this.userwin = userwin;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNSerial() {
        return nSerial;
    }

    public void setNSerial(String nSerial) {
        this.nSerial = nSerial;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Integer getDisco() {
        return disco;
    }

    public void setDisco(Integer disco) {
        this.disco = disco;
    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInv != null ? idInv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioPc)) {
            return false;
        }
        InventarioPc other = (InventarioPc) object;
        if ((this.idInv == null && other.idInv != null) || (this.idInv != null && !this.idInv.equals(other.idInv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InventarioPc[ idInv=" + idInv + " ]";
    }
    
}
