/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.escritorioremoto.teclado;

import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.text.DateFormat;
import java.util.Locale;

/**
 *
 * @author alberto
 */
public class Teclado {

    private int ultimo_keycode;
    private long ultimo_time;
    private long diferencia_maxima_ms = 50;

    public int mapear(int keycode) {
        return keycode;
    }

    /**
     * Verifica que no se presione la tecla muy seguido dentro de mu ypoco
     * tiempo y q no se envie a la vez el metodo keytyped y keypress
     *
     * @param e
     * @return
     */
    public boolean verificarKeyEvent(KeyEvent e) {

        boolean t = true;
        long ct = System.currentTimeMillis();
        if (e.getKeyCode() == ultimo_keycode) {
            if (ct - ultimo_time < diferencia_maxima_ms) {
                t = false;
            }
        }
        ultimo_keycode = e.getKeyCode();
        ultimo_time = ct;
        return t;
    }

    public Locale[] listarConfiguraciones() {
        return DateFormat.getAvailableLocales();
    }

    public void configurarMetodoEntrada(Locale l) {
        InputContext context = InputContext.getInstance();
        context.selectInputMethod(l);
    }

    public Locale getMetodoActual() {
        return InputContext.getInstance().getLocale();
    }

    public static void main(String args[]) {

        Teclado t = new Teclado();

        System.out.println("Locale actual");
        System.out.println(t.getMetodoActual().toString());

        System.out.println("");
        System.out.println("Disponibles");
        System.out.println("--------------------");

        for (Locale local : t.listarConfiguraciones()) {
            System.out.println(local.toString());
        }
    }
}
