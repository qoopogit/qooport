/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//repesenta a las configuraciones que se escriben en el servidor
//este objeto sera serializado y debe ser leido por el servidor
public class CFG implements Serializable {

//    private String prefijo;
//    private String claveClase;
//    private String dns;
//    private String password;
//    private int puerto;
//    private int puerto2;
//    private int delay;
//    private boolean avmw;
//    private boolean avml;
//    private boolean capturarEscritorio;
//    private int capDelayFoto;
//    private int tipoConexion;
//    private boolean ssl;
//    private int protocolo;
//    private boolean uac;
//    private boolean gui;
//    private boolean usb;
//    private String nombreUSB;
//    private boolean instalar;
//    private boolean servicio;
//    private boolean autoinicio;
//    private boolean progtareas;
//    private String regname;
//    private String jarname;
    private Map<String, Object> parametros;//permite configurar varios parametros sin tener que cambiar la clase

    public CFG() {
    }

    public void inicializarParamertros() {
        parametros = new HashMap<String, Object>();
    }

    public boolean estaNuloParametros() {
        return parametros == null;
    }

    public void agregarParametro(String clave, Object valor) {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        parametros.put(clave, valor);
    }

    public Object obtenerParametro(String clave) {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        return parametros.get(clave);
    }

    public void eliminarParametro(String clave) {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        parametros.remove(clave);
    }

    public void vaciar() {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        parametros.clear();
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append("Configuración").append("\n");
        try {
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                salida.append("\t").append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
            }
        } catch (Exception ex) {
        }

        return salida.toString();
    }

}
