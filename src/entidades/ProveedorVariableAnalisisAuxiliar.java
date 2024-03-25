/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author TOSHIBA
 */
public class ProveedorVariableAnalisisAuxiliar {
    
    private String metodo;
    private String limite;
    private String unidad;

    public ProveedorVariableAnalisisAuxiliar(String metodo, String limite, String unidad) {
        this.metodo = metodo;
        this.limite = limite;
        this.unidad = unidad;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
}
