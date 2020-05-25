/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductorCapturas.dividir;

import comunes.Captura;
import java.awt.BorderLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class DividirCaptura extends JFrame {

    public static final int DIVISION_HORA = 1;
    public static final int DIVISION_DIA = 2;
    public static final int DIVISION_MES = 3;
    private File archivoOrigen;
    private File archivoDestino;
    private boolean mostrarGui;
    private boolean generando;
    private boolean comprimido;
    private int opcion;

    private JProgressBar progreso = new JProgressBar(1, 100);

    public DividirCaptura(File archivoOrigen, File archivoDestino, boolean mostrarGui, boolean comprimido, int opcion) {
        this.archivoOrigen = archivoOrigen;
        this.archivoDestino = archivoDestino;
        this.mostrarGui = mostrarGui;
        this.comprimido = comprimido;
        this.opcion = opcion;
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
        this.setIconImage(Util.cargarIcono16("/resources/dividir.png").getImage());
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

                    archivoDestino.mkdirs();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    switch (opcion) {
                        case DIVISION_HORA:
                            sdf = new SimpleDateFormat("yyyyMMddHH");
                            break;
                        case DIVISION_DIA:
                            sdf = new SimpleDateFormat("yyyyMMdd");
                            break;
                        case DIVISION_MES:
                            sdf = new SimpleDateFormat("yyyyMM");
                            break;
                    }

                    File fTemp;
                    while (generando && pos < total) {
                        //ArchivosOfflineVisor.this.mostrarMiniatura(pos);
                        try {
                            progreso.setValue((int) (pos * 100L / total));
                            cap1 = (Captura) SerializarUtil.leerObjeto(archivoOrigen.getAbsolutePath(), pos, comprimido);
                            fTemp= new File(archivoDestino, sdf.format(cap1.getFecha()) + ".dat");
                            SerializarUtil.agregarObjeto(fTemp.getAbsolutePath(), cap1, true, comprimido);
                            //enc.encodeImage  ( imagenNueva);
                            cap1 = null;
                            fTemp=null;
                            Util.gc();
                        } catch (Exception ex) {

                        }
                        pos++;
                    }

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
