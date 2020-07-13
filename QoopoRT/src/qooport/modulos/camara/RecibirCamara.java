/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.camara;

import comunes.Captura;
import java.awt.image.BufferedImage;
import network.Conexion;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class RecibirCamara extends Thread {

    private Camara ventana;
    private boolean pidiendo;
    private BufferedImage imagen = null;
    private Conexion conexion;

    public RecibirCamara() {
    }

    public RecibirCamara(Conexion conexion, Camara ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public Camara getVentana() {
        return ventana;
    }

    public void setVentana(Camara ventana) {
        this.ventana = ventana;
    }

    public boolean isPidiendo() {
        return pidiendo;
    }

    public void setPidiendo(boolean pidiendo) {
        this.pidiendo = pidiendo;
    }

    @Override
    public void run() {
        byte[] buffer;
        while (pidiendo) {
            if (conexion != null) {
                try {
                    buffer = (byte[]) conexion.leerObjeto();
//                        recibirWebCam(buf);
                    if (!ventana.isVisible()) {
                        ventana.setVisible(true);
                    }
                    ventana.setTitle("Cámara Remota [" + ventana.getServidor().getInformacion() + "]");
                    Captura cap = (Captura) Util.descomprimirObjeto(buffer, ventana.getServidor());//con compresion
                    ventana.getReproductor().setGrabar(ventana.getItmGrabar().isSelected());
                    ventana.getReproductor().reproducir(cap);
                    actualizarContadores(cap, buffer.length);
                    ventana.barraInferior.setEstado("Conectado");
//                        ventana.getServidor().agregarRecibidos(buf.length);// no agregamos los bytes recibidos para ocntar en el servidor porq el metodo descomprimir ya lo agrega
                } catch (ClassCastException e) {
                    ventana.barraInferior.setEstado("Esta versión no es compatible");
                } catch (Exception e) {
                    ventana.barraInferior.setEstado("Error:" + e.getMessage());
                }
            } else {
                ventana.barraInferior.setEstado("Esperando conexión...");
                try {
                    ventana.getContadorFps().getTasaFormated();
                } catch (Exception e) {
                }
                try {
                    ventana.getContadorBps().getTasaFormated();
                } catch (Exception e) {
                }
                buffer = null;
                dormir();
            }
        }
    }

    public void dormir() {
        try {
            Thread.sleep(10);
        } catch (Exception ex) {

        }
    }

//    public void recibirWebCam(final byte[] bytes) {
//        try {
//            if (!ventana.isVisible()) {
//                ventana.setVisible(true);
//            }
//            ventana.setTitle("Cámara Remota [" + ventana.getServidor().getInformacion() + "]");
//            //-
////            imagen = ImageIO.read(new ByteArrayInputStream(Compresor.descomprimirGZIP(bytes)));
////            ventana.pintar();
//            Captura cap = (Captura) Util.descomprimirObjeto(bytes, ventana.getServidor());//con compresion
//            ventana.getReproductor().setGrabar(ventana.getItmGrabar().isSelected());
//            ventana.getReproductor().reproducir(cap);            
////            ventana.getContadorTamanio().resetearValores();
////            ventana.getContadorTamanio().agregar(bytes.length);
//            actualizarContadores(cap, bytes.length);
//            ventana.registrarLlegada();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//        }
//    }
    public void actualizarContadores(final Captura cap, final long largoBuffer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ventana.getContadorFps().agregar(1);
                    ventana.getContadorBps().agregar(largoBuffer);
                    ventana.getContadorTamanio().resetear();
                    ventana.getContadorTamanio().agregar(largoBuffer);
                } catch (Exception ex) {
                }
            }
        }).start();
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public void detener() {
        try {
            pidiendo = false;
            //escribirCapturas();//escribe lo q tenga alamcenado y no haya guardado aun
            Util.esperar(300);
            try {
                //imagen = null;//como es el buffer de las imagenes no la vacio para poder repintar la pantalla
                conexion.cerrar();
            } catch (Exception ex) {
            }
        } catch (Exception e) {
        } finally {
            conexion = null;
        }
        interrupt();
    }

}
