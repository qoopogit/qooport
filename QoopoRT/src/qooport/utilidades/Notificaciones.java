/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import static java.lang.Thread.sleep;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import qooport.asociado.Asociado;
import qooport.avanzado.Notificacion;
import qooport.avanzado.QoopoRT;

/**
 *
 * @author alberto
 */
public class Notificaciones {

    public static void sondidoConectar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(QoopoRT.class.getResourceAsStream("/resources/sonidos/conectado.wav")));
                    clip.start();
                    sleep(clip.getMicrosecondLength() * 2);
                    clip.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void mostrarNotificacion(final Asociado servidor) {
        new Thread() {
            @Override
            public void run() {
                Notificacion.mostrar(servidor.getUsuario() + "@" + servidor.getHost(), servidor.getSo(), servidor.getPais(), servidor.getBandera(), servidor.getWebCam());
            }
        }.start();
    }

}
