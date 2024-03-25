/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class ConvertirStringDouble {
    public static double convertirStringDouble(String valor){
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number=null;
        try {
            number = format.parse(valor);
        } catch (ParseException ex) {
            Logger.getLogger(ConvertirStringDouble.class.getName()).log(Level.SEVERE, null, ex);
        }
        double d = number.doubleValue();
        return d;
    }
}
