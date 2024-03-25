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
public class ValorAuxiliar {
    private Valor valor;
    private double dato;
    private int decimales;
    public ValorAuxiliar() {
    }

    public ValorAuxiliar(Valor valor, double dato, int decimales) {
        this.valor = valor;
        this.dato = dato;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public double getDato() {
        return dato;
    }

    public void setDato(double dato) {
        this.dato = dato;
    }

    public int getDecimales() {
        return decimales;
    }

    public void setDecimales(int decimales) {
        this.decimales = decimales;
    }
     
}
