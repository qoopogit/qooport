/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductorCapturas.encoder.mp4;

import comunes.Captura;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.scale.AWTUtil;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class GenerarCapturaMP4 extends JFrame {

    private File archivoOrigen;
    private File archivoDestino;
    private boolean mostrarGui;
    private boolean generando;
    private boolean comprimido;

    private JProgressBar progreso = new JProgressBar(1, 100);

    public GenerarCapturaMP4(File archivoOrigen, File archivoDestino, boolean mostrarGui, boolean comprimido) {
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
        this.setIconImage(Util.cargarIcono16("/resources/mp4.png").getImage());
        this.pack();
        GuiUtil.centrarVentana(this, this.getWidth(), this.getHeight());
    }

    public void generar() {
        generando = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    int total = SerializarUtil.contarObjetos(archivoOrigen.getAbsolutePath());
                    int pos = 0;
                    Captura cap1 = null;
                    SequenceEncoder enc = new SequenceEncoder(archivoDestino);
// GOP size will be supported in 0.2
// enc.getEncoder().setKeyInterval(25);
                    while (generando && pos < total) {
                        //ArchivosOfflineVisor.this.mostrarMiniatura(pos);
                        try {

                            progreso.setValue((int) (pos * 100L / total));
                            cap1 = (Captura) SerializarUtil.leerObjeto(archivoOrigen.getAbsolutePath(), pos, comprimido);
                            ByteArrayInputStream inn = new ByteArrayInputStream(cap1.getBloques().get(0).getDatos());
                            BufferedImage imagenNueva = ImageIO.read(inn);
                            enc.encodeNativeFrame(AWTUtil.fromBufferedImage(imagenNueva));
                            //enc.encodeImage  ( imagenNueva);
                            cap1 = null;
                            Util.gc();
                        } catch (Exception ex) {

                        }
                        pos++;
                    }

                    enc.finish();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
//                    capturasEscritorio.clear();
//                    capturasEscritorio = null;
                dispose();
            }
        }.start();
    }

}
