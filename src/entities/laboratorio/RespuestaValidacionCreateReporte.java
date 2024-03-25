/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.laboratorio;

/**
 *
 * @author USER
 */
public class RespuestaValidacionCreateReporte {
    
    public boolean proyectoDetectado;
    public String idReporte;

    public RespuestaValidacionCreateReporte(boolean proyectoDetectado, String idReporte) {
        this.proyectoDetectado = proyectoDetectado;
        this.idReporte = idReporte;
    }

    
    
    public boolean isProyectoDetectado() {
        return proyectoDetectado;
    }

    public void setProyectoDetectado(boolean proyectoDetectado) {
        this.proyectoDetectado = proyectoDetectado;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }
    
    
}
