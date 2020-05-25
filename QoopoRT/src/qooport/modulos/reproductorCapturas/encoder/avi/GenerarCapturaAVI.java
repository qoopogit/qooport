/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductorCapturas.encoder.avi;

import comunes.Captura;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class GenerarCapturaAVI extends JFrame {

    private File archivoOrigen;
    private File archivoDestino;
    private boolean mostrarGui;
    private boolean generando;
    private boolean comprimido;

    private JProgressBar progreso = new JProgressBar(1, 100);

    public GenerarCapturaAVI(File archivoOrigen, File archivoDestino, boolean mostrarGui, boolean comprimido) {
        this.archivoOrigen = archivoOrigen;
        this.archivoDestino = archivoDestino;
        this.mostrarGui = mostrarGui;
        this.comprimido = comprimido;
        initComponents();
    }

    public void initComponents() {
        if (!mostrarGui) {
            this.setVisible(false);
            return;
        }

        this.setLayout(new BorderLayout());
        progreso.setStringPainted(true);
        this.add(progreso);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/avi.png").getImage());
        this.pack();
        GuiUtil.centrarVentana(this, this.getWidth(), this.getHeight());

    }

    public void generar() {
        generando = true;
        new Thread() {
            @Override
            public void run() {

                int total = SerializarUtil.contarObjetos(archivoOrigen.getAbsolutePath());
                int pos = 0;

                Captura cap1 = null;

                long t1 = 0;
                long t2 = 0;
                File carpetaTemporal = new File(archivoDestino.getParent(), Util.nombreHora());
                carpetaTemporal.mkdirs();
                System.out.println("Carpeta temporal imagenes " + carpetaTemporal);
                while (generando && pos < total) {
                    //ArchivosOfflineVisor.this.mostrarMiniatura(pos);
                    try {
                        //estado.setText("Reproduciendo [" + (pos + 1) + "/" + total + "]");
                        progreso.setValue((int) (pos * 100L / total));
                        cap1 = (Captura) SerializarUtil.leerObjeto(archivoOrigen.getAbsolutePath(), pos, comprimido);
                        ByteArrayInputStream inn = new ByteArrayInputStream(cap1.getBloques().get(0).getDatos());
                        BufferedImage imagenNueva = ImageIO.read(inn);
                        ImageIO.write(imagenNueva, "jpeg", new File(carpetaTemporal, Util.nombreHora() + ".jpg"));
                        cap1 = null;
                        Util.gc();
                    } catch (Exception ex) {

                    }
                    pos++;
                }

                System.out.println("Imagenes escritas");
                System.out.println("Se procede a generar archivo avi");

                try {
                    cap1 = (Captura) SerializarUtil.leerObjeto(archivoOrigen.getAbsolutePath(), 0, comprimido);
                    ByteArrayInputStream inn = new ByteArrayInputStream(cap1.getBloques().get(0).getDatos());
                    BufferedImage imagenMuestra = ImageIO.read(inn);
                    JpegImagesToMovie.writeMovie(imagenMuestra.getWidth(), imagenMuestra.getHeight(), 1, carpetaTemporal.getAbsolutePath());
//                    JpegImagesToMovie.writeMovie(320, 240, 2, carpetaTemporal.getAbsolutePath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
//                    capturasEscritorio.clear();
//                    capturasEscritorio = null;
            }
        }.start();
    }

}
