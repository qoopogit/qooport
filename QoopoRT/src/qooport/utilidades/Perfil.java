/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alberto
 */
public class Perfil implements Serializable {

    private Map<String, Object> parametros;//permite configurar varios parametros sin tener que cambiar la clase

    public Perfil() {
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

}
