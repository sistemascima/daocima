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
public class MatrizHashMapNumberId {
     private HashMap<String, Integer> matrizAnalisis = new HashMap<String, Integer>();
     
     public MatrizHashMapNumberId() {
        matrizAnalisis.put("A", 1);
        matrizAnalisis.put("H", 2);
        matrizAnalisis.put("S", 3);
        matrizAnalisis.put("RP", 5);
        matrizAnalisis.put("R", 6);
        matrizAnalisis.put("CA", 7);
        matrizAnalisis.put("FF", 8);
        matrizAnalisis.put("AP", 11);
        matrizAnalisis.put("SE", 12);
        matrizAnalisis.put("CO", 13);
        matrizAnalisis.put("L", 14);
        matrizAnalisis.put("TV", 16);
        matrizAnalisis.put("TA", 17);
    }
     
      public HashMap<String, Integer> getMatrizAnalisis() {
        return matrizAnalisis;
    }

    public void setMatrizAnalisis(HashMap<String, Integer> matrizAnalisis) {
        this.matrizAnalisis = matrizAnalisis;
    }
}
