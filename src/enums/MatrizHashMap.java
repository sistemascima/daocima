/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

import java.util.HashMap;

/**
 *
 * @author USER
 */
public class MatrizHashMap {

    private HashMap<String, String> matrizAnalisis = new HashMap<String, String>();

    public MatrizHashMap() {
        matrizAnalisis.put("MATRIZ AGUAS", "A-");
        matrizAnalisis.put("MATRIZ BIOTA", "H-");
        matrizAnalisis.put("MATRIZ SUELOS", "S-");
        matrizAnalisis.put("MATRIZ CALIDAD DEL AIRE", "CA-");
        matrizAnalisis.put("MATRIZ SEDIMENTOS", "SE-");
        matrizAnalisis.put("MATRIZ CONCRETO", "CO-");
        matrizAnalisis.put("MATRIZ FUENTES FIJAS", "FF-");
        matrizAnalisis.put("MATRIZ RESIDUOS PELIGROSOS", "RP-");
        matrizAnalisis.put("MATRIZ LODO", "L-");
        matrizAnalisis.put("MATRIZ TEJIDO ANIMAL", "TA-");
        matrizAnalisis.put("MATRIZ TEJIDO VEGETAL", "TV-");
        matrizAnalisis.put("MATRIZ AGUA POTABLE", "AP-");
    }

    public HashMap<String, String> getMatrizAnalisis() {
        return matrizAnalisis;
    }

    public void setMatrizAnalisis(HashMap<String, String> matrizAnalisis) {
        this.matrizAnalisis = matrizAnalisis;
    }

}
