/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import comunes.Accion;

/**
 *
 * @author aigarcia
 */
public class HiloAccion extends Thread {

    private boolean activo;
    private Accion accion;
    private long intervalo;

    public HiloAccion(Accion accion, long intervalo) {
        this.accion = accion;
        this.intervalo = intervalo;
    }

    public void iniciar() {
        activo = true;
        start();
    }

    public void detener() {
        activo = false;
    }

    private void dormir() {
        try {
            Thread.sleep(intervalo);// disminuye el uso de cpu, tamben pone límite de 100 fps
        } catch (Exception ie) {
        }
    }

    @Override
    public void run() {
        while (activo) {
            if (accion != null) {
                accion.ejecutar();
            } else {
                break;
            }
            dormir();
        }
    }
}
