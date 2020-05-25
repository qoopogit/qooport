package qooport.modulos.reproductorCapturas;

import comunes.Captura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import qooport.gui.personalizado.PantallaMiniatura;
import qooport.modulos.reproductorCapturas.dividir.DividirCaptura;
import qooport.modulos.reproductorCapturas.encoder.avi.GenerarCapturaAVI;
import qooport.modulos.reproductorCapturas.encoder.mp4.GenerarCapturaMP4;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.QoopoIMG;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

public class ReproductorCapturas extends JFrame implements WindowListener {

    private File archivo;
    private boolean cargando = false;
    public JTextArea txtListaOffline;
    private JFileChooser cd = new JFileChooser();
    private JScrollPane scrollVistasPrevias;
    private JPanel panelVistas;
    private JLabel pantalla;
    private JLabel estado;
    private JCheckBox chkComprimido;
    private boolean reproduciendo;
    private int total;
    private int actual;
    private int anterior = -1;
    private JSlider slider;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private boolean detenerApertura=false;

    public static void main(String args[]) {
        new ReproductorCapturas(true);
    }

    public ReproductorCapturas(boolean finalizarAlcerrar) {
        initComponents(finalizarAlcerrar);

    }

    private void initComponents(boolean finalizarAlcerrar) {
//        tabs = new JTabbedPane();
        /////////////////////////////////////////////
//                TAB PARA VISUALIZAR EL ARCHIVO DE CAPTURAS DE ESCRITORIO
        /////////////////////////////////////////////
        JPanel panelCapturasEscritorio = new JPanel();
        JPanel panelCapturasSuperior = new JPanel();
        JToolBar barraSuperior = new JToolBar();
        barraSuperior.setFloatable(false);

        JToolBar barraInferior = new JToolBar();
        barraInferior.setFloatable(false);

        scrollVistasPrevias = new JScrollPane();

        //barraSuperior.addSeparator();
        JButton btnAbrirEscritorio = new JButton();
        btnAbrirEscritorio.setIcon(Util.cargarIcono16("/resources/folder.png"));
        btnAbrirEscritorio.setVisible(true);
        btnAbrirEscritorio.setText("Abrir");
        btnAbrirEscritorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnAbrirEscritorio(evt);
            }
        });
        barraSuperior.add(btnAbrirEscritorio);
        
        
        JButton btnDetenerApertura = new JButton();
        btnDetenerApertura.setIcon(Util.cargarIcono16("/resources/stop_close.png"));
        btnDetenerApertura.setVisible(true);
        btnDetenerApertura.setText("Detener");
        btnDetenerApertura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnDetenerApertura(evt);
            }
        });
        barraSuperior.add(btnDetenerApertura);

        chkComprimido = new JCheckBox("Comprimido?");
        chkComprimido.setSelected(true);
        barraSuperior.add(chkComprimido);
        barraSuperior.addSeparator();

        barraSuperior.add(GuiUtil.crearJLabel("Exportar a:"));

        JButton btnGenerarAVI = new JButton();
        btnGenerarAVI.setIcon(Util.cargarIcono16("/resources/avi.png"));
        btnGenerarAVI.setVisible(true);
        btnGenerarAVI.setText("AVI");
        btnGenerarAVI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnGenerarAvi(evt);
            }
        });
        barraSuperior.add(btnGenerarAVI);

        JButton btnGenerarMp4 = new JButton();
        btnGenerarMp4.setIcon(Util.cargarIcono16("/resources/mp4.png"));
        btnGenerarMp4.setVisible(true);
        btnGenerarMp4.setText("MP4");
        btnGenerarMp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnGenerarMP4(evt);
            }
        });
        barraSuperior.add(btnGenerarMp4);

        barraSuperior.addSeparator();

        barraSuperior.add(GuiUtil.crearJLabel("Dividir a:", Util.cargarIcono16("/resources/dividir.png")));
        JButton btnDividirHora = new JButton();
        btnDividirHora.setVisible(true);
        btnDividirHora.setText("Hora");
        btnDividirHora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnDividirAP(DividirCaptura.DIVISION_HORA);
            }
        });
        barraSuperior.add(btnDividirHora);

        JButton btnDividirDia = new JButton();
        btnDividirDia.setVisible(true);
        btnDividirDia.setText("Día");
        btnDividirDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnDividirAP(DividirCaptura.DIVISION_DIA);
            }
        });
        barraSuperior.add(btnDividirDia);

        JButton btnDividirMes = new JButton();
        btnDividirMes.setVisible(true);
        btnDividirMes.setText("Mes");
        btnDividirMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.btnDividirAP(DividirCaptura.DIVISION_MES);
            }
        });
        barraSuperior.add(btnDividirMes);

        JButton btnReproducir = new JButton();
        btnReproducir.setIcon(Util.cargarIcono16("/resources/start.png"));
        btnReproducir.setVisible(true);
        btnReproducir.setToolTipText("Reproducir");
        btnReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.reproducir();
            }
        });
        barraInferior.add(btnReproducir);

        JButton btnDetener = new JButton();
        btnDetener.setIcon(Util.cargarIcono16("/resources/stop_close.png"));
        btnDetener.setVisible(true);
        btnDetener.setToolTipText("Detener");
        btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReproductorCapturas.this.detener();
            }
        });
        barraInferior.add(btnDetener);

        JButton btnAnterior = new JButton();
        btnAnterior.setIcon(Util.cargarIcono16("/resources/2leftarrow.png"));
        btnAnterior.setVisible(true);
        btnAnterior.setToolTipText("Anterior");
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (actual > 0) {
                    actual--;
                }
                mostrarMiniatura(actual);
            }
        });
        barraInferior.add(btnAnterior);

        JButton btnSiguiente = new JButton();
        btnSiguiente.setIcon(Util.cargarIcono16("/resources/2rightarrow.png"));
        btnSiguiente.setVisible(true);
        btnSiguiente.setToolTipText("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (actual < total) {
                    actual++;
                }
                mostrarMiniatura(actual);
            }
        });
        barraInferior.add(btnSiguiente);

        barraInferior.addSeparator();
        this.slider = new JSlider();

        this.slider.setMajorTickSpacing(1);
        this.slider.setMaximum(20);
        this.slider.setMinimum(1);
        this.slider.setMinorTickSpacing(1);
        this.slider.setPaintLabels(false);
        this.slider.setPaintTicks(false);
        this.slider.setValue(1);
        this.slider.setValueIsAdjusting(true);
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                mostrarMiniatura(slider.getValue());

            }
        });

        barraInferior.add(slider);
        this.pantalla = new JLabel();
        panelVistas = new JPanel();
        estado = new JLabel(":");
        scrollVistasPrevias.setViewportView(this.panelVistas);

        scrollVistasPrevias.setPreferredSize(new Dimension(scrollVistasPrevias.getWidth(), 80));

        panelCapturasSuperior.setLayout(new BorderLayout());
        panelCapturasSuperior.add(scrollVistasPrevias, BorderLayout.CENTER);
        panelCapturasSuperior.add(barraSuperior, BorderLayout.NORTH);
        panelCapturasEscritorio.setLayout(new BorderLayout());
        panelCapturasEscritorio.add(pantalla, BorderLayout.CENTER);
        panelCapturasEscritorio.add(panelCapturasSuperior, BorderLayout.NORTH);
        panelCapturasEscritorio.add(barraInferior, BorderLayout.SOUTH);
//        this.tabs.addTab("Escritorio", Util.cargarIcono16("/resources/screen.png"), panelCapturasEscritorio        );
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
//        this.add(tabs, BorderLayout.CENTER);
        this.add(panelCapturasEscritorio, BorderLayout.CENTER);
        this.add(estado, BorderLayout.SOUTH);

        this.setTitle("Visor de Capturas");
        this.setIconImage(Util.cargarIcono16("/resources/player.png").getImage());

        this.setSize(600, 600);
        this.setResizable(true);
        this.setVisible(true);

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent evt) {
                anterior = -1;//para q si actualice
                ReproductorCapturas.this.mostrarMiniatura(actual);
            }

            @Override
            public void componentMoved(ComponentEvent evt) {
//                EscritorioRemoto.this.actualizarFormaControles();
            }

            @Override
            public void componentShown(ComponentEvent e) {
//                EscritorioRemoto.this.actualizarFormaControles();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
//                EscritorioRemoto.this.actualizarFormaControles();
            }
        });
        if (finalizarAlcerrar) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        pack();
    }

    public void cargarArchivo(final File archivo) {
        if (archivo == null) {
            return;
        }

        this.setTitle("Visor de Capturas [" + archivo.getName() + " - " + archivo.getAbsolutePath() + "]");

        this.estado.setText("Cargando miniaturas");
        this.archivo = archivo;
        new Thread() {
            @Override
            public void run() {
                try {
                    int c = 0;
                    cargando = true;
                    total = SerializarUtil.contarObjetos(ReproductorCapturas.this.archivo.getAbsolutePath());
                    slider.setMaximum(total);
                    for (int i = 0; i < total; i++) {
                        try {
                            panelVistas.add(crearMiniatura(i));
                            estado.setText("Cargando archivo [" + (c + 1) + " de " + total + "  " + (100 * c / total) + "% ]");
                            //capturasEscritorio.set(i, cap);
                            Util.gc();
                        } catch (OutOfMemoryError ex) {
                            Util.gc();
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            Logger.getLogger(ReproductorCapturas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        c++;
                        repaint();
                        if(detenerApertura)
                            break;
                    }
                    detenerApertura=false;
                    estado.setText("Carga completa");
                    Util.gc();
                    panelVistas.setMinimumSize(new Dimension(100, 100));
                    repaint();
                } catch (Exception e) {
                    estado.setText("Error al cargar");
                    e.printStackTrace();
                }

                cargando = false;
            }
        }.start();
    }

    private void btnGenerarAvi(ActionEvent evt) {
        cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.cd.setDialogType(JFileChooser.SAVE_DIALOG);
        this.cd.setFileFilter(new FileNameExtensionFilter("Archivo de Video", new String[]{"avi"}));
        cd.setCurrentDirectory(new File("servidores"));

        if (this.cd.showSaveDialog(this) == 0) {
            GenerarCapturaAVI gen = new GenerarCapturaAVI(archivo, cd.getSelectedFile(), true, chkComprimido.isSelected());
            gen.generar();
        }
    }

    private void btnGenerarMP4(ActionEvent evt) {
        cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.cd.setDialogType(JFileChooser.SAVE_DIALOG);
        this.cd.setFileFilter(new FileNameExtensionFilter("Archivo de Video MP4", new String[]{"mp4"}));
        cd.setCurrentDirectory(new File("servidores"));

        if (this.cd.showSaveDialog(this) == 0) {
            GenerarCapturaMP4 gen = new GenerarCapturaMP4(archivo, cd.getSelectedFile(), true, chkComprimido.isSelected());
            gen.generar();
        }
    }

    private void btnDividirAP(int tipo) {
        this.cd.setDialogType(JFileChooser.OPEN_DIALOG);
        cd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.cd.setFileFilter(new FileNameExtensionFilter("Carpeta", new String[]{"dat"}));
        cd.setCurrentDirectory(new File("servidores"));

        if (this.cd.showOpenDialog(this) == 0) {
            DividirCaptura gen = new DividirCaptura(archivo, cd.getSelectedFile(), true, chkComprimido.isSelected(), tipo);
            gen.generar();
        }
    }

    private void btnAbrirEscritorio(ActionEvent evt) {
        cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.cd.setDialogType(JFileChooser.OPEN_DIALOG);
        this.cd.setFileFilter(new FileNameExtensionFilter("Archivo de captura", new String[]{"dat"}));
        cd.setCurrentDirectory(new File("servidores"));

        if (this.cd.showOpenDialog(this) == 0) {
            this.cargarArchivo(cd.getSelectedFile());
        }
    }

    
    private void btnDetenerApertura(ActionEvent evt) {
        detenerApertura=true;
    }
    
    private void reproducir() {
        new Thread() {
            @Override
            public void run() {
                reproduciendo = true;
//                int tam = SerializarUtil.contarObjetos(ReproductorCapturas.this.archivo.getAbsolutePath());
                int pos = 0;

                Captura cap1 = null;
                Captura cap2 = null;
                long t1 = 0;
                long t2 = 0;
                while (reproduciendo && pos < total) {
                    ReproductorCapturas.this.mostrarMiniatura(pos);
                    try {
                        estado.setText("Reproduciendo [" + (pos + 1) + "/" + total + "]");
                        t1 = System.currentTimeMillis();
                        cap1 = (Captura) SerializarUtil.leerObjeto(ReproductorCapturas.this.archivo.getAbsolutePath(), pos + 1, chkComprimido.isSelected());
                        cap2 = (Captura) SerializarUtil.leerObjeto(ReproductorCapturas.this.archivo.getAbsolutePath(), pos, chkComprimido.isSelected());
                        t2 = System.currentTimeMillis();
//espero el tiempo de diferencia entre las fechas de las capturas y les resto el tiempo que me tomo cargar las capturas desde el archivo
                        try {
                            sleep(cap1.getFecha().getTime() - cap2.getFecha().getTime() - (t2 - t1));
                        } catch (Exception e) {
                        }
                        cap1 = null;
                        cap2 = null;
                        Util.gc();
                    } catch (Exception ex) {

                    }
                    pos++;
                }
                estado.setText("Reproducción terminada");
            }
        }.start();
    }

    private void detener() {
        reproduciendo = false;
        estado.setText("Reproducción terminada");
    }

    public void seleccionar(int indiceCaptura) {
        try {
            //Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            ((JLabel) panelVistas.getComponent(indiceCaptura)).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void desSeleccionar(int indiceCaptura) {
        try {
            ((JLabel) panelVistas.getComponent(indiceCaptura)).setBorder(null);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void mostrarMiniatura(int indiceCaptura) {
        try {
            actual = indiceCaptura;
            if (actual == anterior) {
                return;
            }
            seleccionar(actual);
            desSeleccionar(anterior);
            Captura captura = (Captura) SerializarUtil.leerObjeto(archivo.getAbsolutePath(), indiceCaptura, chkComprimido.isSelected());
            ByteArrayInputStream inn = new ByteArrayInputStream(captura.getBloques().get(0).getDatos());
            BufferedImage imagenNueva = ImageIO.read(inn);
            imagenNueva = QoopoIMG.ajustarEscritorio(imagenNueva, true, pantalla.getWidth(), pantalla.getHeight()).getImagen();
            pantalla.setIcon(new ImageIcon(imagenNueva));
            anterior = actual;
            slider.setValue(indiceCaptura);
            scrollVistasPrevias.scrollRectToVisible(panelVistas.getComponent(indiceCaptura).getBounds());
            if (!reproduciendo) {
                estado.setText((indiceCaptura + 1) + " de " + total);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public PantallaMiniatura crearMiniatura(int indiceCaptura) {
        PantallaMiniatura miniatura = new PantallaMiniatura(ReproductorCapturas.this, indiceCaptura);
        try {
            Captura captura = (Captura) SerializarUtil.leerObjeto(archivo.getAbsolutePath(), indiceCaptura, chkComprimido.isSelected());
            ByteArrayInputStream inn = new ByteArrayInputStream(captura.getBloques().get(0).getDatos());
            BufferedImage imagenNueva = ImageIO.read(inn);
            //imagenNueva = QoopoIMG.escalarFuerte(imagenNueva, 100, 50);
            imagenNueva = QoopoIMG.escalarSuave(imagenNueva, 100, 50);
            miniatura.setToolTipText(sdf.format(captura.getFecha()));
            miniatura.setMinimumSize(new Dimension(100, 50));
            miniatura.setIcon(new ImageIcon(imagenNueva));
        } catch (Exception e) {
        }
        return miniatura;
    }

//    private List<Captura> cargarArchivoCapturas(File archivoOffline) {
//        System.out.println("Cargando archivo:" + archivoOffline);
//        List<Captura> tmp = new ArrayList<>();
//        try {
//            if (archivoOffline.exists()) {
//                try {
//                    //tmp = (List) (Util.descomprimirObjeto((byte[]) SerializarUtil.leerObjeto(new FileInputStream(archivoOffline)), null));
//                    tmp = (List) SerializarUtil.leerObjetos(archivoOffline.getAbsolutePath());
//                } catch (Exception ex) {
//                }
//            }
//        } catch (Exception e) {
//        }
//        System.out.println("Archivo cargado. Capturas=" + tmp.size());
//        return tmp;
//    }
    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
