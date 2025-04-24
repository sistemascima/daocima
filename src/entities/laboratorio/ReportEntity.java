/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.laboratorio;

import java.util.Date;

/**
 *
 * @author USER
 */
public class ReportEntity {
    
    private Date fechaInicio;
    private String planMuestreo;
    private String nombreTecnico;
    private int idMunicipio;
    private int idCiudad;
    private int idProyecto;
    private Date fechaRecepcionMuestras;
    private String usuario;
    private String idReporte;
    private String nitCliente;
    private String nitClienteFacturacion;
    private String estado;
    private int numeroReporte;
    private int version;
    private String direccion;
    private String telefono;
    private String correo;
    private String contacto;
    

    public ReportEntity(Date fechaInicio, 
                        String planMuestreo, 
                        String nombreTecnico, 
                        int idMunicipio, 
                        int idCiudad,
                        int idProyecto, 
                        Date fechaRecepcionMuestras, 
                        String usuario, 
                        String idReporte, 
                        String nitCliente, 
                        String nitClienteFacturacion, 
                        String estado, 
                        int numeroReporte, 
                        int version,
                        String direccion,
                        String telefono,
                        String correo,
                        String contacto) {
        
        this.fechaInicio = fechaInicio;
        this.planMuestreo = planMuestreo;
        this.nombreTecnico = nombreTecnico;
        this.idMunicipio = idMunicipio;
        this.idCiudad = idCiudad;
        this.idProyecto = idProyecto;
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
        this.usuario = usuario;
        this.idReporte = idReporte;
        this.nitCliente = nitCliente;
        this.nitClienteFacturacion = nitClienteFacturacion;
        this.estado = estado;
        this.numeroReporte = numeroReporte;
        this.version= version;
        this.direccion= direccion;
        this.telefono= telefono;
        this.correo= correo;
        this.contacto= contacto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getPlanMuestreo() {
        return planMuestreo;
    }

    public void setPlanMuestreo(String planMuestreo) {
        this.planMuestreo = planMuestreo;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaRecepcionMuestras() {
        return fechaRecepcionMuestras;
    }

    public void setFechaRecepcionMuestras(Date fechaRecepcionMuestras) {
        this.fechaRecepcionMuestras = fechaRecepcionMuestras;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNitClienteFacturacion() {
        return nitClienteFacturacion;
    }

    public void setNitClienteFacturacion(String nitClienteFacturacion) {
        this.nitClienteFacturacion = nitClienteFacturacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroReporte() {
        return numeroReporte;
    }

    public void setNumeroReporte(int numeroReporte) {
        this.numeroReporte = numeroReporte;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    
    
    
}
