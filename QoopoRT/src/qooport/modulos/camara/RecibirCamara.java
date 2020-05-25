/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.camara;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import network.Conexion;
import qooport.utilidades.Compresor;
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
        byte[] buf;
        while (pidiendo) {
            if (pidiendo) {
                if (conexion != null) {
                    ventana.barraInferior.setEstado("Conectado");
                    try {
                        buf = (byte[]) conexion.leerObjeto();
                        recibirWebCam(buf);
                        ventana.getServidor().agregarRecibidos(buf.length);
                        ventana.getContadorBps().agregar(buf.length);                        
                    } catch (Exception e) {
                        ventana.barraInferior.setEstado("Error:" + e.getMessage());
                        System.out.println("Error:" + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Util.esperar(100);
                    ventana.barraInferior.setEstado("Esperando conexión...");
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
                buf = null;
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

    public void recibirWebCam(final byte[] bytes) {
//        System.out.println("recibiendo imagen");
//        new Thread(new Runnable() {
//            public void run() {
        try {
            if (!ventana.isVisible()) {
                ventana.setVisible(true);
            }
            ventana.setTitle("Cámara Remota [" + ventana.getServidor().getInformacion() + "]");
            imagen = ImageIO.read(new ByteArrayInputStream(Compresor.descomprimirGZIP(bytes)));
            ventana.pintar();
            ventana.registrarLlegada();
            if (ventana.getItmGrabar().isSelected()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                File carAnioMes = new File(ventana.getServidor().getdWebCam(), sdf.format(new Date()));
                File carpedia = new File(carAnioMes, sdf2.format(new Date()));
                carpedia.mkdirs();
                ImageIO.write(imagen, "jpg", new File(carpedia, Util.nombreHora() + ".jpg"));
            }
            ventana.getContadorTamanio().resetearValores();
            ventana.getContadorTamanio().agregar(bytes.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
//            }
//        }
//        ).start();
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
