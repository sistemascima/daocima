/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author SISTEMAS
 */
public class FotoAuxiliar {
    private int pagina;
    private int posx;
    private int posy;
    private String texto;
    private boolean genero;
    private String sufijo;

    public FotoAuxiliar(int pagina, int posx, int posy, String texto, boolean genero, String sufijo) {
        this.pagina = pagina;
        this.posx = posx;
        this.posy = posy;
        this.texto = texto;
        this.genero = genero;
        this.sufijo= sufijo;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public String getTexto() {
        return texto;

    }
    
    public String getSufijo() {
        if(this.sufijo==null){
           return " ";
        }
        else{
            return sufijo;
        }

    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
    
    
}
