/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author USER
 */
public enum MatrizAnalisisEnum {

    DEFAULT("",""),
    AGUAS("MATRIZ AGUAS", "A-"), 
    BIOTA("MATRIZ BIOTA", "H-"),
    SUELOS("MATRIZ SUELOS", "S-"),
    AIRE("MATRIZ CALIDAD DEL AIRE", "CA-"),
    SEDIMENTOS("MATRIZ SEDIMENTOS", "SE-"),
    CONCRETO("MATRIZ CONCRETO", "CO-"),
    FUENTES_FIJAS("MATRIZ FUENTES FIJAS","FF-"),
    RESIDUOS_PELIGROSOS("MATRIZ RESIDUOS PELIGROSOS","RP-"),
    LODO("MATRIZ LODO","L-"),
    TEJIDO_ANIMAL("MATRIZ TEJIDO ANIMAL","TA-"),
    TEJIDO_VEGETAL("MATRIZ TEJIDO VEGETAL","TV-"),
    AGUA_POTABLE("MATRIZ AGUA POTABLE","AP-")
    ;

    //Campos tipo constante   
    private final String nombre; //Nombre de la matriz
    private final String convencionMatriz; //Convencion matriz

    MatrizAnalisisEnum (String nombre, String convencionMatriz) { 

        this.nombre = nombre;
        this.convencionMatriz = convencionMatriz;
    } 

    public String getNombre(){
        return this.nombre;
    }

    public String getConvencionMatriz(){
        return this.convencionMatriz;
    }


} //Cierre del enum