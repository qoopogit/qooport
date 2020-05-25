/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;

/**
 *
 * @author alberto
 */
public class Evento implements Serializable {

    private int pantallaID;//identifica la pantalla donde se debe ejecutar el evento, en caso de movimientos del mouse

    private int tipo;//1 de tecaldo, 2 del mouse
    /**
     * si es teclado. 1 keypress 2 keyrelease 3 keytyped
     *
     *
     * si es mouse 1 presionado 2 liberado 4 wheel 5 dragged
     */
    private int evento;

    private float x;
    private float y;
    private int boton;
    private int wheel;

    private int keycode;
    private char chartecla;

    public Evento() {
    }

    public Evento(int pantallaID, int evento, float x, float y, int boton, int wheel) {
        this.pantallaID = pantallaID;
        this.evento = evento;
        this.x = x;
        this.y = y;
        this.boton = boton;
        this.wheel = wheel;
        tipo = 2;
    }

    public Evento(int pantallaID, int evento, int keycode, char chartecla) {
        this.pantallaID = pantallaID;
        this.evento = evento;
        this.keycode = keycode;
        this.chartecla = chartecla;
        tipo = 1;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getBoton() {
        return boton;
    }

    public void setBoton(int boton) {
        this.boton = boton;
    }

    public int getWheel() {
        return wheel;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public int getKeycode() {
        return keycode;
    }

    public void setKeycode(int keycode) {
        this.keycode = keycode;
    }

    public char getChartecla() {
        return chartecla;
    }

    public void setChartecla(char chartecla) {
        this.chartecla = chartecla;
    }

    public int getPantallaID() {
        return pantallaID;
    }

    public void setPantallaID(int pantallaID) {
        this.pantallaID = pantallaID;
    }

}
