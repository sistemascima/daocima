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
public class MuestraAnalisisAuxiliar {
 
    private int idMuestra;
    private int idVariable;
    private String s_muestraanal_realizado;
    private String provvedor;
    private String usuarioCreador;
    private int codigoAnalista;

    public MuestraAnalisisAuxiliar(int idMuestra, int idVariable, String s_muestraanal_realizado, String provvedor, String usuarioCreador, int codigoAnalista) {
        this.idMuestra = idMuestra;
        this.idVariable = idVariable;
        this.s_muestraanal_realizado = s_muestraanal_realizado;
        this.provvedor = provvedor;
        this.usuarioCreador = usuarioCreador;
        this.codigoAnalista = codigoAnalista;
    }
    
    
      public MuestraAnalisisAuxiliar(){
          
    }


    public int getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(int idMuestra) {
        this.idMuestra = idMuestra;
    }

    public int getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(int idVariable) {
        this.idVariable = idVariable;
    }

    public String getS_muestraanal_realizado() {
        return s_muestraanal_realizado;
    }

    public void setS_muestraanal_realizado(String s_muestraanal_realizado) {
        this.s_muestraanal_realizado = s_muestraanal_realizado;
    }

    public String getProvvedor() {
        return provvedor;
    }

    public void setProvvedor(String provvedor) {
        this.provvedor = provvedor;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public int getCodigoAnalista() {
        return codigoAnalista;
    }

    public void setCodigoAnalista(int codigoAnalista) {
        this.codigoAnalista = codigoAnalista;
    }
                                              
    
}
