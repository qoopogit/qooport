/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.proxy;

import java.util.ArrayList;
import java.util.List;
import comunes.Accion;

/**
 *
 * @author aigarcia
 */
public class Conexiones {

    private static Accion accion;

    public static List<ConexionProxy> lista = new ArrayList<ConexionProxy>();

    public static void agregarConexion(ConexionProxy conexion) {
        lista.add(conexion);
        if (accion != null) {
            accion.ejecutar();
        }

    }

    public static void cerrarConexiones() {
        for (ConexionProxy conexion : lista) {
            conexion.cerrar();
        }
        lista.clear();
    }

    public static void setAccion(Accion accion) {
        Conexiones.accion = accion;
    }

    public static void eliminarConexion(ConexionProxy conexion) {
        lista.remove(conexion);
        if (accion != null) {
            accion.ejecutar();
        }
    }
}
