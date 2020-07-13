/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes;

import comunes.CapturaOpciones;
import comunes.Evento;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import comunes.Interfaz;
import rt.util.CLRT;

public class GestorPantalla {

    private List<Interfaz> pantallas = new ArrayList<Interfaz>();
    private GraphicsDevice[] monitores;

    public GestorPantalla() {
        cargarMonitores();
    }

    public int getNumeroPantallas() {
        return monitores.length;
    }

    private void cargarMonitores() {
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        monitores = g.getScreenDevices();
        for (int i = 0; i < monitores.length; i++) {
            agregarPantalla(i);
        }
    }

    public void liberar() {
        monitores = null;
        pantallas.clear();
        pantallas = null;
    }

    public void reiniciarPantallas() {
        for (int i = 0; i < pantallas.size(); i++) {
            pantallas.get(i).ejecutar(1);//se detiene la pantalla
        }
        pantallas.clear();
    }

    public void agregarPantalla(int pantallaID) {
        try {
            Interfaz pantalla = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.comunes.Pantalla").newInstance());
            pantalla.instanciar();
            pantalla.set(6, pantallaID);
            pantallas.add(pantalla);
        } catch (Exception ex) {
        }
    }

    public void removerPantalla(int pantallaID) {
        pantallas.get(pantallaID).ejecutar(1);//se detiene la pantalla
        pantallas.remove(pantallaID);
    }

    public void actualizar() {
        for (Interfaz pantalla : pantallas) {
            pantalla.ejecutar(2);//limpiar
        }
    }

    public void iniciar() {
        for (Interfaz pantalla : pantallas) {
            pantalla.ejecutar(0);//iniciar
        }
    }

    public void setearServicio(Interfaz servicio) {
        for (Interfaz pantalla : pantallas) {
            pantalla.set(0, servicio);//setea el servicio
        }
    }

    public void setearOpciones(CapturaOpciones opciones) {
        for (Interfaz pantalla : pantallas) {
            pantalla.set(3, opciones);//setea las opciones
        }
    }

    public void ejecutarEventos(List<Evento> eventos) {
        for (Interfaz pantalla : pantallas) {
            pantalla.ejecutar(6, eventos);//ejecuta los eventos
        }
    }

    //solo hace que la primera pantalla ejecute esa instrucción
    public void ctrl_alt_supr() {
        try {
            pantallas.get(0).ejecutar(3);
        } catch (Exception e) {

        }
    }

}
