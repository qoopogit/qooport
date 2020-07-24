package qooport.modulos.escritorioremoto;

import comunes.CapturaOpciones;
import comunes.Evento;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import network.Conexion;
import qooport.asociado.Asociado;
import qooport.gui.personalizado.BarraEstado;
import qooport.modulos.VentanaReproductor;
import qooport.modulos.escritorioremoto.teclado.Teclado;
import qooport.modulos.reproductor.Contenedor;
import qooport.modulos.reproductor.Reproductor;
import qooport.utilidades.ClipboardUtility;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

public class EscritorioRemoto extends VentanaReproductor implements KeyListener, WindowListener {

    /**
     * 1.- Envio completo, 2.- Envio parcial, 3.- Captura por bloques, 4.-
     * Captura por bloques, recibe bloque a bloque y lo pinta cada uno al
     * recibirlo
     */
    public static final int ENVIO_COMPLETO = 1;
//    public static final int ENVIO_PARCIAL = 2;
    public static final int ENVIO_BLOQUES = 3;
    public static final int DATOS_BYTES = 1;
    public static final int DATOS_INT = 2;
    public static final int CAPTURA_ROBOT = 1;
    public static final int CAPTURA_PRTSC = 2;
    public static final int CAPTURA_DIRECT_ROBOT = 3;
    public static final int CAPTURA_NATIVA_FIRNASS = 4;
    public static final int CAPTURA_NATIVA_WINROBOT = 5;
    public static final int CAPTURA_NATIVA_WINAPI = 6;
    public static final int CAPTURA_ALGORITMO_V1 = 1;
    public static final int CAPTURA_ALGORITMO_V2 = 2;
    public static final int CAPTURA_ALGORITMO_V3 = 3;
    public static String tipoLetra = "Arial";
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private Reproductor reproductor;
    private Informacion informacion;
    private List<Evento> listaEventos = new ArrayList<Evento>();
    private Teclado teclado;
    private ClipboardUtility clipboard;
    private Asociado agente;
    private RecibirEscritorio servicio;
    private JButton btnActivar;
    private JButton btnMinimizar;
    public JMenuBar barra;
    public BarraEstado barraInferior;
    private JMenuItem lblTamaPan;
    private JCheckBoxMenuItem itmConvertirJPG;
    private JMenuItem lblCalidad;
    private JSlider jStamanio;
    private JSlider jScalidad;
    private JCheckBoxMenuItem calidadAutomatica;
    private JComboBox cmbMonitor;
    private JComboBox resolucion;
    public boolean conectado;
    private boolean pidiendo;
    private boolean noOculto;

    private int MONITOR = -1;
    private String strResolucion = "";
    /**
     * 1.- Blanco y negro,2.- gris, 3.- 8 bits 256 colores, 4.- 16 bits 655356
     * colores, 5.- 24 bits, 6. 32 bits
     */
    private int tipoColor = 6;

    private int ESCALA = 3;//3 perfecto
    private int tipoEnvio = 3;

    private int tipoDatos = 2; //1 = bytes, 2 = enteros

    private int tipoAlgoritmo = 2;// 1 viejo, 2 nuevo, 3 varios monitores a la vez
    private int tipoCaptura = 3;//1 robot de java,2 tecla printscreen, 3 DirectRobot
    private ContadorBPS contadorFps;// frames por segundo
    private ContadorBPS contadorBps;// cuantos bytes por segundo
    private ContadorBPS contadorBloques;// bloques de la captura
    private ContadorBPS contadorPorcentaje;// porcentaje de la diferencia
    private ContadorBPS contadorCeldasRC;// bloques repetidos de la captura
    private ContadorBPS contadorCeldasRepetidas;// bloques repetidos de la anterior captura
    private ContadorBPS contadorCeldasNuevas;// las celdas nuevas
    private ContadorBPS contadorB; // tama;o de la captura
    private ContadorBPS contadorTEnvio;
    private ContadorBPS contadorTCaptura;
    private ContadorBPS contadorTProceso;
    private ContadorBPS contadorBuffer;
    private ContadorBPS contadorCalidad;
    private ContadorBPS contadorBits;
    private ContadorBPS contadorSaltadas;
    private DespachadorEventos despachadorEventos;
    private JWindow ventanaControles;
    private JMenu menuAccion;
    private JMenu menuVer;
    private JMenu menuCalidad;
    private JMenu menuEscala;
    private JMenu menuHerramientas;
    private JMenu menuConfig;
    private JMenu menuControl;
    private JMenu menuCaptura;
    private JMenu menuMonitor;
    private JMenu menuTeclado;
    private JMenu menuResolucion;
    private JCheckBoxMenuItem itmGris;
    private JCheckBoxMenuItem itm16;
    private JCheckBoxMenuItem itm8;
    private JCheckBoxMenuItem itm24;
    private JCheckBoxMenuItem itmFullColor;
    private JCheckBoxMenuItem itmEscalaOriginal;
    private JCheckBoxMenuItem itmEscalaProporcional;
    private JCheckBoxMenuItem itmEscalarVentana;
    private JCheckBoxMenuItem itmEscalarPerfecto;
    private JCheckBoxMenuItem itmEscalarRemoto;
    private JCheckBoxMenuItem itmEscalarSuave;
    private JCheckBoxMenuItem itmEscalarFuerte;
    private JCheckBoxMenuItem itmAjustarVentana;
    private JMenuItem itmArchivos;
    private JMenuItem itmArchivosEnviarPortapaleles;
    private JMenuItem itmArchivosRecibirPortapaleles;
    private JMenuItem itmCamara;
    private JMenuItem itmVopIP;
    private JMenuItem itmChat;
    private JMenuItem itmMonitorear;
    private JCheckBoxMenuItem itmMenuVerMouse_dibujoRemoto;
    private JCheckBoxMenuItem itmMenuCambiarCursorLocal;
    private JCheckBoxMenuItem itmHabMouse;
    private JCheckBoxMenuItem itmHabTeclado;
    private JCheckBoxMenuItem itmCapBYTES;
    private JCheckBoxMenuItem itmCapINT;
    private JCheckBoxMenuItem itmCapCompleta;
//    private JCheckBoxMenuItem itmCapPixeles;
    private JCheckBoxMenuItem itmCapBloques;
    private JCheckBoxMenuItem itmCapDefault;//robot java
    private JCheckBoxMenuItem itmCapPrtsc;// tecla print screen
    private JCheckBoxMenuItem itmCapDirectRobot;//DirectRobot java
    private JCheckBoxMenuItem itmCapNativaFirnass;//Captura nativa firnass
    private JCheckBoxMenuItem itmCapNativaWinRobot;//Captura nativa winRobot
    private JCheckBoxMenuItem itmCapNativaWinApi;//Captura nativa winApi
    private JCheckBoxMenuItem itmCapAlgoritmoV1;// algoritmo v1
    private JCheckBoxMenuItem itmCapAlgoritmoV2;// algoritmo v2
    private JCheckBoxMenuItem itmCapAlgoritmoV3;// algoritmo v3
    private JCheckBoxMenuItem itmVerCeldas;
    private JCheckBoxMenuItem itmVerificarRepetidos;
    private JMenuItem itmNegro;
    private JTextField txtCeldaSize;
    private JTextField txtColumnas;
    private JCheckBoxMenuItem itmTransNormal;
    private JCheckBoxMenuItem itmTransHilosTipo1;
    private JCheckBoxMenuItem itmTransHilosTipo2;
    private JCheckBoxMenuItem itmGrabar;
    private JCheckBoxMenuItem itmClipboard;
    private JCheckBoxMenuItem itmTodosMonitores;
    private JCheckBoxMenuItem itmComprimir;
    private boolean pantallaCompleta = false;
    private boolean mostrarExtra = false;
    private JCheckBoxMenuItem itmMostrarEstado;
    private JMenuItem itmPantallaCompleta;
    private JMenuItem itmActualizar;
    private JButton btnIniciarDetener;
    private JButton btnVerPantallaCompleta;
    private JMenuItem itmEnviarCTRLALTSUPR;
    private JMenuItem itmApagarMonitor;
    private JMenuItem itmEncenderMonitor;
    private JMenuItem itmBloquearEquipo;
    private JMenuItem itmFinalizar;
    private JCheckBoxMenuItem[] itmMonitores;
    private JCheckBoxMenuItem[] itmResoluciones;
    private boolean corriendoMostrar = false;
    private int tamanioBarra = 80;
    private Conexion conexion;
    private Font fuenteMenus;
    private boolean corriendoOcultar = false;

    public EscritorioRemoto(Conexion conexion, Asociado servidor) {
        this.agente = servidor;
        pidiendo = false;
        noOculto = false;
        this.conexion = conexion;
        clipboard = new ClipboardUtility(servidor);
        clipboard.removeFlavorListener();
        teclado = new Teclado();
        reproductor = new Reproductor();
        reproductor.setDirectorioEscritorio(servidor.getdEscritorio());
        iniciarHilos();
        initComponents();
        actualizaMenuTecado();
    }

    private void detenerHilos() {
        servicio = null;
        contadorFps = null;
        contadorBps = null;
        contadorBloques = null;
        contadorCeldasRC = null;
        contadorCeldasNuevas = null;
        contadorCeldasRepetidas = null;
        contadorPorcentaje = null;
        contadorB = null;
        contadorTEnvio = null;
        contadorTCaptura = null;
        contadorTProceso = null;
        contadorBuffer = null;
        contadorCalidad = null;
        contadorBits = null;
        if (despachadorEventos != null) {
            despachadorEventos.detener();
        }
        despachadorEventos = null;
    }

    private void iniciarHilos() {
        detenerHilos();
        despachadorEventos = new DespachadorEventos();
        despachadorEventos.start();
        contadorFps = new ContadorBPS("Frames por segundo", "f", "ps", GuiUtil.crearJLabel("", "Frames por segundo", Util.cargarIcono16("/resources/ancho_banda.png")), ContadorBPS.FLOTANTE, false);
        contadorFps.start();
        contadorBps = new ContadorBPS("Ancho de banda", "", "/s", GuiUtil.crearJLabel("", "Ancho de banda", Util.cargarIcono16("/resources/ancho_banda.png")), ContadorBPS.BYTES, false);
        contadorBps.start();
        contadorBloques = new ContadorBPS("Celdas de la captura", "", "", GuiUtil.crearJLabel("", "Celdas de la captura", Util.cargarIcono16("/resources/bloques.png")), ContadorBPS.ENTERO, true);
        contadorBloques.start();
        contadorCeldasRC = new ContadorBPS("Celdas repetidas de la captura", "", "", GuiUtil.crearJLabel("", "Celdas repetidas de la captura", Util.cargarIcono16("/resources/bloques.png")), ContadorBPS.ENTERO, true);
        contadorCeldasRC.start();
        contadorCeldasNuevas = new ContadorBPS("Celdas nuevas", "", "", GuiUtil.crearJLabel("", "Celdas nuevas de la captura", Util.cargarIcono16("/resources/bloques.png")), ContadorBPS.ENTERO, true);
        contadorCeldasNuevas.start();
        contadorCeldasRepetidas = new ContadorBPS("Celdas repetidas de la anterior captura", "", "", GuiUtil.crearJLabel("", "Celdas repetidas de la anterior captura", Util.cargarIcono16("/resources/bloques.png")), ContadorBPS.ENTERO, true);
        contadorCeldasRepetidas.start();
        contadorPorcentaje = new ContadorBPS("Porcentaje de cambios", "%", "", GuiUtil.crearJLabel("", "Porcentaje de cambios", null), ContadorBPS.ENTERO, true);
        contadorPorcentaje.start();
        contadorB = new ContadorBPS("Tamaño captura", "", "", GuiUtil.crearJLabel("", "Tamaño captura", Util.cargarIcono16("/resources/binary.png")), ContadorBPS.BYTES, true);
        contadorB.start();
        contadorTCaptura = new ContadorBPS("Tiempo de captura", "ms", "", GuiUtil.crearJLabel("", "Tiempo de captura de imagen", Util.cargarIcono16("/resources/time.png")), ContadorBPS.FLOTANTE, true);
        contadorTCaptura.start();
        contadorTProceso = new ContadorBPS("Tiempo de Proceso Cambios", "ms", "", GuiUtil.crearJLabel("", "Tiempo de proceso de cambios", Util.cargarIcono16("/resources/time.png")), ContadorBPS.FLOTANTE, true);
        contadorTProceso.start();
        contadorTEnvio = new ContadorBPS("Tiempo de envío", "ms", "", GuiUtil.crearJLabel("", "Calidad de la imagen", Util.cargarIcono16("/resources/time.png")), ContadorBPS.FLOTANTE, true);
        contadorTEnvio.start();
        contadorBuffer = new ContadorBPS("Capturas en el buffer", "", "", GuiUtil.crearJLabel("", "Calidad de la imagen", Util.cargarIcono16("/resources/buffer.png")), ContadorBPS.ENTERO, true);
        contadorBuffer.start();
        contadorSaltadas = new ContadorBPS("Capturas saltadas porque el buffer estaba lleno", "", "", GuiUtil.crearJLabel("", "Capturas saltadas", Util.cargarIcono16("/resources/buffer.png")), ContadorBPS.ENTERO, true);
        contadorSaltadas.start();
        contadorCalidad = new ContadorBPS("Calidad de captura (Si es jpg, si es de tipo RAW es -1)", "%", "", GuiUtil.crearJLabel("", "Calidad de la imagen", Util.cargarIcono16("/resources/screen.png")), ContadorBPS.FLOTANTE, true);
        contadorCalidad.start();
        contadorBits = new ContadorBPS("Bits de la imagen (Profundidad de color)", "bits", "", GuiUtil.crearJLabel("", "Calidad de la imagen", Util.cargarIcono16("/resources/bits.png")), ContadorBPS.FLOTANTE, true);
        contadorBits.start();
    }

    private void initComponents() {
        fuenteMenus = new Font(tipoLetra, 1, 11);
        barra = new JMenuBar();
        barraInferior = new BarraEstado();
        itmActualizar = new JMenuItem();
        itmPantallaCompleta = new JMenuItem();
        this.lblTamaPan = new JMenuItem();
        this.lblCalidad = new JMenuItem();
        this.calidadAutomatica = new JCheckBoxMenuItem();
        itmConvertirJPG = new JCheckBoxMenuItem();
        this.cmbMonitor = new JComboBox();
        this.resolucion = new JComboBox();
        setResizable(true);
        setTitle("Escritorio Remoto");
        try {
            if (getAgente().isAndroid()) {
                setTitle("Capturar Pantalla [" + getAgente().getInformacion() + "]");
            } else {
                setTitle("Escritorio Remoto [" + getAgente().getInformacion() + "]");
            }
        } catch (Exception e) {
        }

        ActionListener listenerEnvioParametros = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.enviarParametros();
                cambiarCursorRemoto(null);
                reproductor.setVerCeldas(itmVerCeldas.isSelected());
            }
        };

        lblTamaPan.setIcon(Util.cargarIcono16("/resources/application-resize.png"));
        lblTamaPan.setText("Escala:100");
        lblTamaPan.setFont(fuenteMenus);
        lblTamaPan.setToolTipText("Tamaño de la imagen en porcentaje.");
        jStamanio = new JSlider();
        jStamanio.setValue(100);
        jStamanio.setMaximum(100);
        jStamanio.setMinimum(1);
        jStamanio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                EscritorioRemoto.this.scalStateChanged(evt);
            }
        });
        lblCalidad.setIcon(Util.cargarIcono16("/resources/monitor.png"));
        lblCalidad.setText("Calidad: 90");
        lblCalidad.setFont(fuenteMenus);
        lblCalidad.setToolTipText("Calidad de imagen");
        calidadAutomatica.setText("Calidad automática");
        calidadAutomatica.setSelected(true);
        calidadAutomatica.setToolTipText("La calidad se configura automáticamente dependiendo de la red.");
        calidadAutomatica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                verificarHabilitaColor();

                EscritorioRemoto.this.enviarParametros();
            }
        });

        itmConvertirJPG.setText("JPEG");
        itmConvertirJPG.setSelected(false);
        itmConvertirJPG.setToolTipText("Convierte la imagen a JPEG. (Toma más tiempode procesamiento, reduce tiempo de transporte)");
        itmConvertirJPG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jScalidad.setEnabled(itmConvertirJPG.isSelected());
                EscritorioRemoto.this.enviarParametros();
            }
        });
        jScalidad = new JSlider();
        jScalidad.setEnabled(false);
        jScalidad.setValue(90);
        jScalidad.setMaximum(100);
        jScalidad.setMinimum(1);
        jScalidad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                EscritorioRemoto.this.caliStateChanged(evt);
            }
        });
        this.menuVer = new JMenu();
        this.menuVer.setFont(fuenteMenus);
        this.menuVer.setIcon(Util.cargarIcono20("/resources/eye_blue_32.png"));
        this.menuVer.setText("Ver");
        this.itmMenuVerMouse_dibujoRemoto = new JCheckBoxMenuItem();
        this.itmMenuVerMouse_dibujoRemoto.setSelected(false);
        this.itmMenuVerMouse_dibujoRemoto.setIcon(Util.cargarIcono16("/resources/cursor_q.png"));
        this.itmMenuVerMouse_dibujoRemoto.setText("Ver cursor remoto");
        this.itmMenuVerMouse_dibujoRemoto.setVisible(!agente.isAndroid());
        this.itmMenuVerMouse_dibujoRemoto.addActionListener(listenerEnvioParametros);
        this.itmMenuVerMouse_dibujoRemoto.setFont(fuenteMenus);
        this.menuVer.add(itmMenuVerMouse_dibujoRemoto);
        this.itmMenuCambiarCursorLocal = new JCheckBoxMenuItem();
        this.itmMenuCambiarCursorLocal.setSelected(true);
        this.itmMenuCambiarCursorLocal.setIcon(Util.cargarIcono16("/resources/cursor.png"));
        this.itmMenuCambiarCursorLocal.setText("Cambiar cursor local");
        this.itmMenuCambiarCursorLocal.setVisible(!agente.isAndroid());
        this.itmMenuCambiarCursorLocal.addActionListener(listenerEnvioParametros);
        this.itmMenuCambiarCursorLocal.setFont(fuenteMenus);
        this.menuVer.add(this.itmMenuCambiarCursorLocal);
        this.itmMostrarEstado = new JCheckBoxMenuItem();
        this.itmMostrarEstado.setIcon(Util.cargarIcono16("/resources/adv_build.png"));
        this.itmMostrarEstado.setSelected(false);
        this.itmMostrarEstado.setText("Mostrar barra de estado");
        this.itmMostrarEstado.setFont(fuenteMenus);
        this.itmMostrarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnMostrarOcultarConfig(evt);
            }
        });
        //menuConfig.add(btnMostrarConfig);
        this.menuVer.add(itmMostrarEstado);
        this.menuVer.addSeparator();
        this.btnIniciarDetener = new JButton();
        this.btnIniciarDetener.setIcon(Util.cargarIcono("/resources/start.png", 8, 8));
        this.btnIniciarDetener.setSelected(false);
        this.btnIniciarDetener.setPreferredSize(new Dimension(10, 10));
        this.btnIniciarDetener.setToolTipText("Iniciar");
        this.btnIniciarDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnIniciarDetenerAP(evt);
            }
        });
        menuCalidad = new JMenu();
        this.menuCalidad.setIcon(Util.cargarIcono16("/resources/color_256.png"));
        this.menuCalidad.setText("Calidad");
        this.menuCalidad.setFont(fuenteMenus);
        this.menuCalidad.add(calidadAutomatica);
        this.menuCalidad.addSeparator();
        this.itmGris = new JCheckBoxMenuItem();
        this.itmGris.setEnabled(false);
        this.itmGris.setIcon(Util.cargarIcono16("/resources/color_gris.png"));
        this.itmGris.setText("Gris");
        this.itmGris.setFont(fuenteMenus);
        this.itmGris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarColor(EscritorioRemoto.COLOR_GRIS);
            }
        });
        this.menuCalidad.add(this.itmGris);
        this.itm8 = new JCheckBoxMenuItem();
        this.itm8.setEnabled(false);
        this.itm8.setIcon(Util.cargarIcono16("/resources/color_256.png"));
        this.itm8.setText("8 bits (256 Colores)");
        this.itm8.setFont(fuenteMenus);
        this.itm8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarColor(EscritorioRemoto.COLOR_8_BITS);
            }
        });
        this.menuCalidad.add(this.itm8);
        this.itm16 = new JCheckBoxMenuItem();
        this.itm16.setEnabled(false);
        this.itm16.setIcon(Util.cargarIcono16("/resources/color_16.png"));
        this.itm16.setText("16 bits (65536 colores)");
        this.itm16.setFont(fuenteMenus);
        this.itm16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarColor(EscritorioRemoto.COLOR_16_BITS);
            }
        });
        this.menuCalidad.add(this.itm16);
        this.itm24 = new JCheckBoxMenuItem();
        this.itm24.setEnabled(false);
        this.itm24.setIcon(Util.cargarIcono16("/resources/color_24bits.png"));
        this.itm24.setText("24 bits (Color verdadero)");
        this.itm24.setFont(fuenteMenus);
        this.itm24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarColor(EscritorioRemoto.COLOR_24_BITS);
            }
        });
        this.menuCalidad.add(this.itm24);
        this.itmFullColor = new JCheckBoxMenuItem();
        this.itmFullColor.setEnabled(false);
        this.itmFullColor.setIcon(Util.cargarIcono16("/resources/color_hd.png"));
        this.itmFullColor.setText("32 bits (Full color)");
        itmFullColor.setSelected(true);
        itmFullColor.setFont(fuenteMenus);
        this.itmFullColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarColor(EscritorioRemoto.COLOR_32_BITS);
            }
        });
        this.menuCalidad.add(this.itmFullColor);
        this.menuCalidad.addSeparator();
        this.menuCalidad.add(itmConvertirJPG);
        this.menuCalidad.add(lblCalidad);
        this.menuCalidad.add(this.jScalidad);
        this.menuVer.add(this.menuCalidad);
        this.menuTeclado = new JMenu();
        this.menuTeclado.setIcon(Util.cargarIcono16("/resources/keyboard.png"));
        this.menuTeclado.setText("Teclado");
        this.menuTeclado.setFont(fuenteMenus);
        this.menuVer.add(this.menuTeclado);
        this.menuMonitor = new JMenu();
        this.menuMonitor.setIcon(Util.cargarIcono16("/resources/monitor.png"));
        this.menuMonitor.setText("Monitor");
        this.menuMonitor.setFont(fuenteMenus);
        this.itmTodosMonitores = new JCheckBoxMenuItem();
        this.itmTodosMonitores.setSelected(true);
        this.itmTodosMonitores.setText("Todos");
        this.itmTodosMonitores.setFont(fuenteMenus);
        this.itmTodosMonitores.setIcon(Util.cargarIcono16("/resources/monitor.png"));
        this.itmTodosMonitores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.seleccionarMonitor(-1);
            }
        });
        this.menuMonitor.add(itmTodosMonitores);
        this.menuVer.add(this.menuMonitor);
        this.menuResolucion = new JMenu();
        this.menuResolucion.setIcon(Util.cargarIcono16("/resources/resize.png"));
        this.menuResolucion.setText("Resolución");
        this.menuResolucion.setFont(fuenteMenus);
        this.menuVer.add(this.menuResolucion);
        this.menuEscala = new JMenu();
        this.menuEscala.setIcon(Util.cargarIcono16("/resources/application-resize.png"));
        this.menuEscala.setText("Escala");
        this.menuEscala.setFont(fuenteMenus);
        this.itmEscalarRemoto = new JCheckBoxMenuItem();
        this.itmEscalarRemoto.setSelected(false);
        this.itmEscalarRemoto.setText("Escalar Remotamente");
        this.itmEscalarRemoto.setToolTipText("El servidor escala la imagen. Reduce la carga de datos a transferir pero afecta a tiempos de respuesta. Usar en redes lentas");
        this.itmEscalarRemoto.setFont(fuenteMenus);
        this.itmEscalarRemoto.setIcon(Util.cargarIcono16("/resources/resize_1.png"));
        this.itmEscalarRemoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarEscalaRemoto(evt);
            }
        });
        this.menuEscala.add(itmEscalarRemoto);
        this.menuEscala.addSeparator();
        this.itmEscalaOriginal = new JCheckBoxMenuItem();
        this.itmEscalaOriginal.setSelected(false);
        this.itmEscalaOriginal.setIcon(Util.cargarIcono16("/resources/resize_original.png"));
        this.itmEscalaOriginal.setText("Tamaño Original");
        itmEscalaOriginal.setFont(fuenteMenus);
        this.itmEscalaOriginal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.escalaOriginalAP(evt);
            }
        });
        this.menuEscala.add(itmEscalaOriginal);
        this.itmEscalaProporcional = new JCheckBoxMenuItem();
        this.itmEscalaProporcional.setSelected(false);
        this.itmEscalaProporcional.setIcon(Util.cargarIcono16("/resources/monitor-size.png"));
        this.itmEscalaProporcional.setText("Escala porcentual");
        this.itmEscalaProporcional.setFont(fuenteMenus);
        this.itmEscalaProporcional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.escalaFijaAP(evt);
            }
        });
//        menuEscala.add(itmEscalaProporcional);//deshabilitado
        this.itmEscalarVentana = new JCheckBoxMenuItem();
        this.itmEscalarVentana.setSelected(false);
        this.itmEscalarVentana.setText("Ajustar a la ventana");
        this.itmEscalarVentana.setFont(fuenteMenus);
        this.itmEscalarVentana.setIcon(Util.cargarIcono16("/resources/application-resize.png"));
        this.itmEscalarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.escalaTamanioAP(evt);
            }
        });
        this.menuEscala.add(itmEscalarVentana);
        this.itmEscalarPerfecto = new JCheckBoxMenuItem();
        this.itmEscalarPerfecto.setSelected(true);
        this.itmEscalarPerfecto.setText("Ajuste Perfecto");
        this.itmEscalarPerfecto.setFont(fuenteMenus);
        this.itmEscalarPerfecto.setToolTipText("Redimensiona a la ventana manteniendo la proporción");
        this.itmEscalarPerfecto.setIcon(Util.cargarIcono16("/resources/application-resize.png"));
        this.itmEscalarPerfecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.escalaPerfectoAP(evt);
            }
        });
        this.menuEscala.add(itmEscalarPerfecto);
        this.menuEscala.addSeparator();
        this.menuEscala.add("Algoritmo");
        this.itmEscalarSuave = new JCheckBoxMenuItem();
        this.itmEscalarSuave.setSelected(false);
        this.itmEscalarSuave.setText("Suave");
        this.itmEscalarSuave.setToolTipText("Escalado suave. Más lento que el fuerte");
        this.itmEscalarSuave.setFont(fuenteMenus);
        this.itmEscalarSuave.setIcon(Util.cargarIcono16("/resources/resize_1.png"));
        this.itmEscalarSuave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarEscalaSuave(evt);
            }
        });
        this.menuEscala.add(itmEscalarSuave);
        this.itmEscalarFuerte = new JCheckBoxMenuItem();
        this.itmEscalarFuerte.setSelected(true);
        this.itmEscalarFuerte.setText("Fuerte");
        this.itmEscalarFuerte.setToolTipText("Escalado suave. Más rápido que el suave");
        this.itmEscalarFuerte.setFont(fuenteMenus);
        this.itmEscalarFuerte.setIcon(Util.cargarIcono16("/resources/resize_1.png"));
        this.itmEscalarFuerte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarEscalaFuerte(evt);
            }
        });
        this.menuEscala.add(itmEscalarFuerte);
        this.itmAjustarVentana = new JCheckBoxMenuItem();
        this.itmAjustarVentana.setIcon(Util.cargarIcono16("/resources/application-resize-full.png"));
//        itmAjustarImagen.setSelected(agente.isAndroid());
        this.itmAjustarVentana.setSelected(true);
        this.itmAjustarVentana.setText("Ajustar ventana al tamaño de la imagen");
        this.itmAjustarVentana.setFont(fuenteMenus);
        this.menuEscala.add(itmAjustarVentana);
        this.menuEscala.addSeparator();
        this.menuEscala.add(lblTamaPan);
        this.menuEscala.add(this.jStamanio);
        this.menuVer.add(this.menuEscala);
        this.itmPantallaCompleta.setIcon(Util.cargarIcono16("/resources/full_screen.png"));
        this.itmPantallaCompleta.setSelected(false);
        this.itmPantallaCompleta.setText("Pantalla Completa");
        this.itmPantallaCompleta.setFont(fuenteMenus);
        this.itmPantallaCompleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnPantallaCompleta(evt);
            }
        });
        this.menuVer.add(this.itmPantallaCompleta);
        this.itmActualizar.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.itmActualizar.setSelected(false);
        this.itmActualizar.setText("Actualizar Pantalla");
        this.itmActualizar.setFont(fuenteMenus);
        this.itmActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnActualizarPantalla(evt);
            }
        });
        this.menuVer.addSeparator();
        this.menuVer.add(this.itmActualizar);
        this.btnVerPantallaCompleta = new JButton();
        this.btnVerPantallaCompleta.setIcon(Util.cargarIcono("/resources/full_screen.png", 8, 8));
        this.btnVerPantallaCompleta.setSelected(false);
        this.btnVerPantallaCompleta.setPreferredSize(new Dimension(10, 10));
        this.btnVerPantallaCompleta.setToolTipText("Ver pantalla completa");
        this.btnVerPantallaCompleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnPantallaCompleta(evt);
            }
        });
        this.menuHerramientas = new JMenu();
        this.menuHerramientas.setIcon(Util.cargarIcono20("/resources/advancedsettings.png"));
        this.menuHerramientas.setText("Herramientas");
        this.menuHerramientas.setFont(fuenteMenus);
        this.itmMonitorear = new JMenuItem();
        this.itmMonitorear.setIcon(Util.cargarIcono16("/resources/spy.png"));
        this.itmMonitorear.setText("Monitorear (Espiar)");
        this.itmMonitorear.setToolTipText("Solo monitorea,activa la grabación y desactiva el control.");
        this.itmMonitorear.setFont(fuenteMenus);
        this.itmMonitorear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnMOnitorearAP(evt);
            }
        });
        this.menuHerramientas.add(this.itmMonitorear);
        this.itmGrabar = new JCheckBoxMenuItem();
        this.itmGrabar.setIcon(Util.cargarIcono16("/resources/record_small.png"));
        this.itmGrabar.setSelected(false);
        this.itmGrabar.setText("Grabar");
        this.itmGrabar.setFont(fuenteMenus);
        this.itmGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.reproductor.setGrabar(itmGrabar.isSelected());
            }
        });
        this.menuHerramientas.addSeparator();
        this.menuHerramientas.add(this.itmGrabar);

        this.itmClipboard = new JCheckBoxMenuItem();
        this.itmClipboard.setIcon(Util.cargarIcono16("/resources/clipboard.png"));
        this.itmClipboard.setSelected(true);
        this.itmClipboard.setText("Transferencia Portapales");
        this.itmClipboard.setFont(fuenteMenus);
        this.menuHerramientas.add(this.itmClipboard);
        JMenuItem itmVerInfo = new JMenuItem();
        itmVerInfo.setIcon(Util.cargarIcono16("/resources/controlpanel.png"));
        itmVerInfo.setText("Información Conexión");
        itmVerInfo.setFont(fuenteMenus);
        itmVerInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (informacion == null) {
                    informacion = new Informacion(EscritorioRemoto.this);
                } else {
                    informacion.setVentana(EscritorioRemoto.this);
                }
            }
        });
        this.menuHerramientas.add(itmVerInfo);
        this.menuHerramientas.addSeparator();
        this.menuHerramientas.add("Archivos");
        this.itmArchivos = new JMenuItem();
        this.itmArchivos.setIcon(Util.cargarIcono16("/resources/folder.png"));
        this.itmArchivos.setText("Administrador de archivos");
        this.itmArchivos.setFont(fuenteMenus);
        this.itmArchivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnArchivosAP(evt);
            }
        });
        this.menuHerramientas.add(this.itmArchivos);
        this.itmArchivosEnviarPortapaleles = new JMenuItem();
        this.itmArchivosEnviarPortapaleles.setIcon(Util.cargarIcono16("/resources/up_arrow.png"));
        this.itmArchivosEnviarPortapaleles.setText("Enviar del portapapeles");
        this.itmArchivosEnviarPortapaleles.setFont(fuenteMenus);
        this.itmArchivosEnviarPortapaleles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnEnviarPortapalelesAP(evt);
            }
        });
        this.itmArchivosRecibirPortapaleles = new JMenuItem();
        this.itmArchivosRecibirPortapaleles.setIcon(Util.cargarIcono16("/resources/down_arrow.png"));
        this.itmArchivosRecibirPortapaleles.setText("Descargar del portapapeles");
        this.itmArchivosRecibirPortapaleles.setFont(fuenteMenus);
        this.itmArchivosRecibirPortapaleles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnrecibirPortapapelesAP(evt);
            }
        });
        this.menuHerramientas.addSeparator();
        this.itmCamara = new JMenuItem();
        this.itmCamara.setIcon(Util.cargarIcono16("/resources/camera.png"));
        this.itmCamara.setText("Cámara");
        itmCamara.setFont(fuenteMenus);
        this.itmCamara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnCamaraAP(evt);
            }
        });
        this.menuHerramientas.add(this.itmCamara);
        itmVopIP = new JMenuItem();
        this.itmVopIP.setIcon(Util.cargarIcono16("/resources/voip.png"));
        this.itmVopIP.setText("VoIP");
        itmVopIP.setFont(fuenteMenus);
        this.itmVopIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnMicrofonoAP(evt);
            }
        });
        this.menuHerramientas.add(this.itmVopIP);
        itmChat = new JMenuItem();
        this.itmChat.setIcon(Util.cargarIcono16("/resources/chat.png"));
        itmChat.setText("Chat");
        itmChat.setFont(fuenteMenus);
        this.itmChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnChatAP(evt);
            }
        });
        this.menuHerramientas.add(this.itmChat);

        menuConfig = new JMenu();
        this.menuConfig.setIcon(Util.cargarIcono16("/resources/puerto.png"));
        this.menuConfig.setText("Config");
        menuConfig.setFont(fuenteMenus);
        menuControl = new JMenu();
        this.menuControl.setIcon(Util.cargarIcono20("/resources/mouse_blue.png"));
        this.menuControl.setText("Control");
        menuControl.setFont(fuenteMenus);
        itmHabMouse = new JCheckBoxMenuItem();
        itmHabMouse.setIcon(Util.cargarIcono16("/resources/mouse_blue.png"));
        itmHabMouse.setSelected(true);
        itmHabMouse.setVisible(!agente.isAndroid());
        itmHabMouse.setText("Habilitar Mouse");
        itmHabMouse.setFont(fuenteMenus);
        menuControl.add(itmHabMouse);
        itmHabTeclado = new JCheckBoxMenuItem();
        itmHabTeclado.setIcon(Util.cargarIcono16("/resources/keyboard_basic_blue.png"));
        itmHabTeclado.setSelected(true);
        itmHabTeclado.setText("Habilitar teclado");
        itmHabTeclado.setFont(fuenteMenus);
        itmHabTeclado.setVisible(!agente.isAndroid());
        menuControl.add(itmHabTeclado);
        menuConfig.add(menuControl);
        menuCaptura = new JMenu();

        this.menuCaptura.setText("Captura");

        menuCaptura.setFont(fuenteMenus);
        this.menuCaptura.setIcon(Util.cargarIcono16("/resources/project.png"));

        JMenu mnuTipoDatos = new JMenu("Tipo de Datos");
        mnuTipoDatos.setIcon(Util.cargarIcono16("/resources/binary.png"));
        menuCaptura.add(mnuTipoDatos);
        itmCapBYTES = new JCheckBoxMenuItem();
        itmCapBYTES.setIcon(Util.cargarIcono16("/resources/binary.png"));
        itmCapBYTES.setText("Arreglo Byte");
        itmCapBYTES.setFont(fuenteMenus);
        itmCapBYTES.setToolTipText("Imagenes con tipo de datos de arreglo de bytes");
        this.itmCapBYTES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoDatos(EscritorioRemoto.DATOS_BYTES);
                verificarHabilitaColor();
            }
        });
        //menuCaptura.add(itmCapBYTES);
        mnuTipoDatos.add(itmCapBYTES);

        itmCapINT = new JCheckBoxMenuItem();
        itmCapINT.setIcon(Util.cargarIcono16("/resources/binary.png"));

        itmCapINT.setText("Arreglo Int");
        itmCapINT.setFont(fuenteMenus);
        itmCapINT.setSelected(true);
        itmCapINT.setToolTipText("Imagenes con tipo de datos de arreglo de entero");
        this.itmCapINT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoDatos(EscritorioRemoto.DATOS_INT);
                verificarHabilitaColor();
            }
        });
        //menuCaptura.add(itmCapINT);
        mnuTipoDatos.add(itmCapINT);

        JMenu mnuOrigenCaptura = new JMenu("Origen de la Captura");
        menuCaptura.add(mnuOrigenCaptura);

        itmCapDefault = new JCheckBoxMenuItem();
        itmCapDefault.setIcon(Util.cargarIcono16("/resources/java.png"));
        itmCapDefault.setVisible(!agente.isAndroid());
        itmCapDefault.setText("Robot");
        itmCapDefault.setFont(fuenteMenus);
        itmCapDefault.setToolTipText("Método de captura de pantalla default (robot java)");
        itmCapDefault.setSelected(false);
        this.itmCapDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_ROBOT);
            }
        });

        mnuOrigenCaptura.add(itmCapDefault);

        itmCapDirectRobot = new JCheckBoxMenuItem();
        itmCapDirectRobot.setIcon(Util.cargarIcono16("/resources/java.png"));
        itmCapDirectRobot.setVisible(!agente.isAndroid());
        itmCapDirectRobot.setText("DirectRobot");
        itmCapDirectRobot.setFont(fuenteMenus);
        itmCapDirectRobot.setToolTipText("Método de captura de pantalla con DirectRobot");
        itmCapDirectRobot.setSelected(true);
        this.itmCapDirectRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_DIRECT_ROBOT);
            }
        });

        mnuOrigenCaptura.add(itmCapDirectRobot);

        //nativo firnass
        itmCapNativaFirnass = new JCheckBoxMenuItem();
        itmCapNativaFirnass.setIcon(Util.cargarIcono16("/resources/os.png"));
        itmCapNativaFirnass.setVisible(!agente.isAndroid());
        itmCapNativaFirnass.setText("Nativa Firnass");
        itmCapNativaFirnass.setFont(fuenteMenus);
        itmCapNativaFirnass.setToolTipText("Método de captura nativa del SO. Requiere plugin JNA.");
        itmCapNativaFirnass.setSelected(false);
        this.itmCapNativaFirnass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_NATIVA_FIRNASS);
            }
        });

        mnuOrigenCaptura.add(itmCapNativaFirnass);

        //nativo winrobot
        itmCapNativaWinRobot = new JCheckBoxMenuItem();
        itmCapNativaWinRobot.setIcon(Util.cargarIcono16("/resources/os.png"));
        itmCapNativaWinRobot.setVisible(!agente.isAndroid());
        itmCapNativaWinRobot.setText("Nativa WinRobot");
        itmCapNativaWinRobot.setFont(fuenteMenus);
        itmCapNativaWinRobot.setToolTipText("Método de captura nativa del SO. Sólo Windows. Requiere plugin JNA.");
        itmCapNativaWinRobot.setSelected(false);
        this.itmCapNativaWinRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_NATIVA_WINROBOT);
            }
        });

        mnuOrigenCaptura.add(itmCapNativaWinRobot);
        //nativo winapi
        itmCapNativaWinApi = new JCheckBoxMenuItem();
        itmCapNativaWinApi.setIcon(Util.cargarIcono16("/resources/os.png"));
        itmCapNativaWinApi.setVisible(!agente.isAndroid());
        itmCapNativaWinApi.setText("Nativa WinAPI");
        itmCapNativaWinApi.setFont(fuenteMenus);
        itmCapNativaWinApi.setToolTipText("Método de captura nativa usando API de windows. Sólo Windows. Requiere plugin JNA.");
        itmCapNativaWinApi.setSelected(false);
        this.itmCapNativaWinApi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_NATIVA_WINAPI);
            }
        });

        mnuOrigenCaptura.add(itmCapNativaWinApi);

        itmCapPrtsc = new JCheckBoxMenuItem();
        itmCapPrtsc.setIcon(Util.cargarIcono16("/resources/tecla.png"));
        itmCapPrtsc.setVisible(!agente.isAndroid());
        itmCapPrtsc.setText("PRTSC");
        itmCapPrtsc.setFont(fuenteMenus);
        itmCapPrtsc.setToolTipText("Usa la tecla PRTSC (PrintScreen) y lee del portapapeles");
        itmCapPrtsc.setSelected(false);
        this.itmCapPrtsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoCaptura(EscritorioRemoto.CAPTURA_PRTSC);
            }
        });
        mnuOrigenCaptura.add(itmCapPrtsc);

        JMenu mnuTipoEnvio = new JMenu("Detector de Cambios");
        menuCaptura.add(mnuTipoEnvio);
        itmCapCompleta = new JCheckBoxMenuItem();
        itmCapCompleta.setIcon(Util.cargarIcono16("/resources/screen-completa.png"));
        itmCapCompleta.setVisible(!agente.isAndroid());
        itmCapCompleta.setText("Completa");
        itmCapCompleta.setFont(fuenteMenus);
        itmCapCompleta.setToolTipText("El servidor envía la imagen completa del escritorio");
        this.itmCapCompleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoEnvio(EscritorioRemoto.ENVIO_COMPLETO);
            }
        });
        mnuTipoEnvio.add(itmCapCompleta);
//        itmCapPixeles = new JCheckBoxMenuItem();
//        itmCapPixeles.setIcon(Util.cargarIcono16("/resources/screen-cambios.png"));
//        itmCapPixeles.setVisible(!agente.isAndroid());
//        itmCapPixeles.setText("Pixeles");
//        itmCapPixeles.setFont(fuenteMenus);
//        itmCapPixeles.setToolTipText("El servidor envía solo los cambios en el escritorio. (El servidor debe procesar los cambios pixel por pixel)");
//        itmCapPixeles.setSelected(false);
//        this.itmCapPixeles.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                EscritorioRemoto.this.cambiarTipoEnvio(EscritorioRemoto.ENVIO_PARCIAL);
//            }
//        });
//        mnuTipoEnvio.add(itmCapPixeles);
        itmCapBloques = new JCheckBoxMenuItem();
        itmCapBloques.setIcon(Util.cargarIcono16("/resources/grid16.png"));
        itmCapBloques.setVisible(!agente.isAndroid());
        itmCapBloques.setText("Celdas");
        itmCapBloques.setFont(fuenteMenus);
        itmCapBloques.setToolTipText("Celdas. El servidor divide la pantalla en celdas (bloques) y envía las celdas que cambiaron");
        itmCapBloques.setSelected(true);
        this.itmCapBloques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoEnvio(EscritorioRemoto.ENVIO_BLOQUES);
            }
        });
        mnuTipoEnvio.add(itmCapBloques);
        JMenu mnuOpcionesCelda = new JMenu("Opciones de Celda");
        mnuOpcionesCelda.setIcon(Util.cargarIcono16("/resources/celdas.png"));
        menuCaptura.add(mnuOpcionesCelda);
        itmVerificarRepetidos = new JCheckBoxMenuItem();
        itmVerificarRepetidos.setIcon(Util.cargarIcono16("/resources/grid.png"));
        itmVerificarRepetidos.setSelected(false);
        itmVerificarRepetidos.setText("Validar celdas repetidas");
        itmVerificarRepetidos.setFont(fuenteMenus);
        itmVerificarRepetidos.setToolTipText("Valida que si existen celdas repetidas en la misma captura o captura anterior. Como la codificación CopyRect de VNC");
        itmVerificarRepetidos.setVisible(!agente.isAndroid());
        this.itmVerificarRepetidos.addActionListener(listenerEnvioParametros);
        mnuOpcionesCelda.add(itmVerificarRepetidos);
        mnuOpcionesCelda.add("Tamaño celda");
        txtCeldaSize = new JTextField("64");
        mnuOpcionesCelda.add(txtCeldaSize);
        JMenu mnuOpcionesDebug = new JMenu("Opciones de Debug");
        mnuOpcionesDebug.setIcon(Util.cargarIcono16("/resources/test.png"));
        menuCaptura.add(mnuOpcionesDebug);
        itmVerCeldas = new JCheckBoxMenuItem();
        itmVerCeldas.setIcon(Util.cargarIcono16("/resources/grid.png"));
        itmVerCeldas.setSelected(false);
        itmVerCeldas.setText("Mostrar cambios (test)");
        itmVerCeldas.setFont(fuenteMenus);
        itmVerCeldas.setToolTipText("Muestra u Oculta la cuadricula de cambios (para depuración)");
        itmVerCeldas.setVisible(!agente.isAndroid());
        this.itmVerCeldas.addActionListener(listenerEnvioParametros);
        mnuOpcionesDebug.add(itmVerCeldas);
        itmNegro = new JMenuItem();
        itmNegro.setIcon(Util.cargarIcono16("/resources/grid.png"));
        itmNegro.setText("Vaciar pantalla (test)");
        itmNegro.setFont(fuenteMenus);
        itmNegro.setToolTipText("Pinta la pantalla de negro para ver los cambios");
        itmNegro.setVisible(!agente.isAndroid());
        this.itmNegro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproductor.testVaciarPantalla();
            }
        });
        mnuOpcionesDebug.add(itmNegro);
        JMenu mnuAlgoritmo = new JMenu("Algoritmo");
        mnuAlgoritmo.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        menuCaptura.add(mnuAlgoritmo);

        itmCapAlgoritmoV1 = new JCheckBoxMenuItem();
        itmCapAlgoritmoV1.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        itmCapAlgoritmoV1.setVisible(!agente.isAndroid());
        itmCapAlgoritmoV1.setSelected(false);
        itmCapAlgoritmoV1.setText("Algoritmo V1");
        itmCapAlgoritmoV1.setFont(fuenteMenus);
        itmCapAlgoritmoV1.setToolTipText("Usa el algoritmo V1 de captura");
        this.itmCapAlgoritmoV1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoAlgoritmo(EscritorioRemoto.CAPTURA_ALGORITMO_V1);
            }
        });
        mnuAlgoritmo.add(itmCapAlgoritmoV1);
        itmCapAlgoritmoV2 = new JCheckBoxMenuItem();
        itmCapAlgoritmoV2.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        itmCapAlgoritmoV2.setVisible(!agente.isAndroid());
        itmCapAlgoritmoV2.setText("Algoritmo V2");
        itmCapAlgoritmoV2.setFont(fuenteMenus);
        itmCapAlgoritmoV2.setToolTipText("Usa el algoritmo V2 de captura");
        itmCapAlgoritmoV2.setSelected(true);
        this.itmCapAlgoritmoV2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoAlgoritmo(EscritorioRemoto.CAPTURA_ALGORITMO_V2);
            }
        });
        mnuAlgoritmo.add(itmCapAlgoritmoV2);
        itmCapAlgoritmoV3 = new JCheckBoxMenuItem();
        itmCapAlgoritmoV3.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        itmCapAlgoritmoV3.setVisible(!agente.isAndroid());
        itmCapAlgoritmoV3.setText("Algoritmo V3");
        itmCapAlgoritmoV3.setFont(fuenteMenus);
        itmCapAlgoritmoV3.setToolTipText("Evolución del algoritmo V2 que permite transmitir varios monitores a la vez");
        itmCapAlgoritmoV3.setSelected(false);
        this.itmCapAlgoritmoV3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.cambiarTipoAlgoritmo(EscritorioRemoto.CAPTURA_ALGORITMO_V3);
            }
        });
        mnuAlgoritmo.add(itmCapAlgoritmoV3);

        JMenu mnuVariosMonitores = new JMenu("Varios Monitores");
        mnuVariosMonitores.setIcon(Util.cargarIcono16("/resources/monitor.png"));
        menuCaptura.add(mnuVariosMonitores);
        mnuVariosMonitores.add(" Columnas:");
        txtColumnas = new JTextField("2");
        txtColumnas.setToolTipText("Indica el número de columnas cuando se presenta varios monitores a la vez (Con algoritmo V3)");
        mnuVariosMonitores.add(txtColumnas);
        JMenu mnuTransmision = new JMenu("Transmisión");
        mnuTransmision.setIcon(Util.cargarIcono16("/resources/images-stack.png"));
        menuCaptura.add(mnuTransmision);
        itmTransNormal = new JCheckBoxMenuItem();
        this.itmTransNormal.setSelected(true);
        itmTransNormal.setEnabled(false);
        this.itmTransNormal.setText("Transmisión normal");
        itmTransNormal.setFont(fuenteMenus);
        this.itmTransNormal.setToolTipText("Transmisión en secuencia. Consume menos recursos");
        this.itmTransNormal.setIcon(Util.cargarIcono16("/resources/images-stack.png"));
        itmTransNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itmTransHilosTipo1.setSelected(false);
                itmTransHilosTipo2.setSelected(false);
            }
        });
//        menuCaptura.add(itmTransNormal);
        mnuTransmision.add(itmTransNormal);

        itmTransHilosTipo1 = new JCheckBoxMenuItem();
        this.itmTransHilosTipo1.setSelected(false);
        itmTransHilosTipo1.setEnabled(false);
        this.itmTransHilosTipo1.setText("Transmisión rápida tipo 1");
        itmTransHilosTipo1.setFont(fuenteMenus);
        this.itmTransHilosTipo1.setToolTipText("Transmisión rápida usando buffer de captura (Usa hilos y requiere más recursos de parte del servidor)");
        this.itmTransHilosTipo1.setIcon(Util.cargarIcono16("/resources/images-stack.png"));
        itmTransHilosTipo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itmTransNormal.setSelected(false);
                itmTransHilosTipo2.setSelected(false);
            }
        });
//        menuCaptura.add(itmTransHilosTipo1);
        mnuTransmision.add(itmTransHilosTipo1);

        itmTransHilosTipo2 = new JCheckBoxMenuItem();
        this.itmTransHilosTipo2.setSelected(false);
        itmTransHilosTipo2.setEnabled(false);
        this.itmTransHilosTipo2.setText("Transmisión rápida tipo 2");
        itmTransHilosTipo2.setFont(fuenteMenus);
        this.itmTransHilosTipo2.setToolTipText("Transmisión rápida con procesos paralelos (Usa hilos y requiere más recursos de parte del servidor)");
        this.itmTransHilosTipo2.setIcon(Util.cargarIcono16("/resources/images-stack.png"));

        itmTransHilosTipo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itmTransNormal.setSelected(false);
                itmTransHilosTipo1.setSelected(false);
            }
        });

//        menuCaptura.add(itmTransHilosTipo2);
        mnuTransmision.add(itmTransHilosTipo2);

        itmComprimir = new JCheckBoxMenuItem();
        this.itmComprimir.setSelected(true);
        this.itmComprimir.setText("Comprimir");
        itmComprimir.setFont(fuenteMenus);
        this.itmComprimir.setToolTipText("Comprime los datos a enviar");
        this.itmComprimir.setIcon(Util.cargarIcono16("/resources/compress.png"));
        itmComprimir.addActionListener(listenerEnvioParametros);
        menuCaptura.addSeparator();
        menuCaptura.add(itmComprimir);

        menuConfig.add(menuCaptura);

        menuAccion = new JMenu();
        this.menuAccion.setIcon(Util.cargarIcono20("/resources/rayo_azul.png"));
        this.menuAccion.setText("Acciones");
        menuAccion.setFont(fuenteMenus);
        this.itmEnviarCTRLALTSUPR = new JMenuItem();
        itmEnviarCTRLALTSUPR.setIcon(Util.cargarIcono16("/resources/key_ctrl.png"));
        itmEnviarCTRLALTSUPR.setText("Ctrl+Alt+Supr");
        itmEnviarCTRLALTSUPR.setFont(fuenteMenus);
        this.itmEnviarCTRLALTSUPR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnEnviarCTRLALTSUPR(evt);
            }
        });
        this.menuAccion.add(this.itmEnviarCTRLALTSUPR);

        this.itmBloquearEquipo = new JMenuItem();
        itmBloquearEquipo.setIcon(Util.cargarIcono16("/resources/lock3.png"));
        itmBloquearEquipo.setText("Bloquear Equipo");
        itmBloquearEquipo.setFont(fuenteMenus);
        this.itmBloquearEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnBloquearEquipo(evt);
            }
        });
        this.menuAccion.add(this.itmBloquearEquipo);

        this.itmApagarMonitor = new JMenuItem();
        itmApagarMonitor.setIcon(Util.cargarIcono16("/resources/monitor_off.png"));
        itmApagarMonitor.setText("Apagar Monitor");
        itmApagarMonitor.setFont(fuenteMenus);
        this.itmApagarMonitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnApagarMonitor(evt);
            }
        });
        this.menuAccion.add(this.itmApagarMonitor);

        this.itmEncenderMonitor = new JMenuItem();
        itmEncenderMonitor.setIcon(Util.cargarIcono16("/resources/monitor_on.png"));
        itmEncenderMonitor.setText("Encender Monitor");
        itmEncenderMonitor.setFont(fuenteMenus);
        this.itmEncenderMonitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.btnEncenderMonitor(evt);
            }
        });
        this.menuAccion.add(this.itmEncenderMonitor);

        this.itmFinalizar = new JMenuItem();
        itmFinalizar.setIcon(Util.cargarIcono16("/resources/terminate.png"));
        itmFinalizar.setText("Finalizar");
        itmFinalizar.setFont(fuenteMenus);
        this.itmFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnFinalizar(evt);
            }
        });
        this.menuAccion.addSeparator();
        this.menuAccion.add(this.itmFinalizar);
        this.lblTamaPan.setVisible(false);
        this.jStamanio.setVisible(false);

//        barra.add(Box.createHorizontalGlue());
        barra.add(menuAccion);
        barra.add(menuVer);
        barra.add(menuHerramientas);
        barra.add(menuConfig);
        reproductor.setContenedor(new Contenedor(this));

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

        btnMinimizar = new JButton();
        btnMinimizar.setIcon(Util.cargarIcono("/resources/minimizar.png", 8, 8));
        btnMinimizar.setSize(10, 10);
        btnMinimizar.setPreferredSize(new Dimension(10, 10));
        btnMinimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EscritorioRemoto.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(reproductor.getContenedor(), BorderLayout.CENTER);
        this.actualizarFormaControles();
        this.setResizable(true);
        this.setVisible(true);
        this.agregarListenerTeclado();
        this.addKeyListener(this);

        this.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent evt) {
                EscritorioRemoto.this.enviarParametros();
                EscritorioRemoto.this.actualizarFormaControles();
            }

            @Override
            public void componentMoved(ComponentEvent evt) {
                EscritorioRemoto.this.actualizarFormaControles();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                EscritorioRemoto.this.actualizarFormaControles();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                EscritorioRemoto.this.actualizarFormaControles();
            }
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(this);
        this.setMinimumSize(new Dimension(300, 250));
        this.setIconImage(Util.cargarIcono16("/resources/remoto.png").getImage());
        if (agente.isAndroid()) {
            tipoEnvio = EscritorioRemoto.ENVIO_COMPLETO;
        }
        pack();
//        this.escalaFijaAP(null);//setea q se esale fija
        //escalaTamanioAP(null);//setea q se ajuste a la ventana
        escalaPerfectoAP(null);//setea que sea ajuste perfecto
    }

    private void agregarListenerTeclado() {
        KeyListener kl = new KeyListener() {
            public boolean isFocusable() {
                return true;
            }

            @Override
            public void keyTyped(KeyEvent e) {
                EscritorioRemoto.this.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                EscritorioRemoto.this.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                EscritorioRemoto.this.keyReleased(e);
            }
        };
        this.setFocusTraversalKeysEnabled(false);
        jStamanio.setFocusTraversalKeysEnabled(false);
        jScalidad.setFocusTraversalKeysEnabled(false);
        jStamanio.addKeyListener(kl);
        jScalidad.addKeyListener(kl);
        reproductor.getContenedor().agregarKeyListener(kl);
    }

    private void enviarParametros() {
        if (pidiendo) {
            CapturaOpciones opciones = new CapturaOpciones();
            opciones.setTamBuffer(1);
            int tBloque = 64;
            try {
                tBloque = Integer.valueOf(txtCeldaSize.getText());
            } catch (Exception e) {

            }
            try {
                opciones.setTipoDatos(tipoDatos);
            } catch (Exception e) {
            }
            opciones.setAnchoBloque(tBloque);
            opciones.setAltoBloque(tBloque);
            opciones.setEscala(this.jStamanio.getValue());
            opciones.setCalidad(((float) this.jScalidad.getValue()) / 100);
            opciones.setEscalar(itmEscalarRemoto.isSelected());
            opciones.setTipoEscala(ESCALA);
            opciones.setMonitor(MONITOR);
            opciones.setTipoColor(tipoColor);
            opciones.setMostrarCursor(itmMenuVerMouse_dibujoRemoto.isSelected());
            opciones.setTipoEnvio(tipoEnvio);
            opciones.setSuavizado(this.itmEscalarSuave.isSelected());
            opciones.setEnviarHilos(false);//ya no se usa
            opciones.setOrigenCaptura(tipoCaptura);
            opciones.setComprimir(this.itmComprimir.isSelected());
            opciones.setEnviarCursor(itmMenuCambiarCursorLocal.isSelected());
            opciones.setPortapalesActivos(itmClipboard.isSelected());
            opciones.setTipoHilos(this.itmTransNormal.isSelected() ? 0 : (this.itmTransHilosTipo1.isSelected() ? 1 : 2));
            opciones.setCalidadAutomatica(calidadAutomatica.isSelected());
            opciones.setConvertirJpg(itmConvertirJPG.isSelected());
            opciones.setAlgoritmo(tipoAlgoritmo);
            opciones.setValidarRepetidos(itmVerificarRepetidos.isSelected());//permitir la validacion de repetidos (Como la codificacion CopyRect de VNC) Aun en pruebas
            opciones.setAncho(reproductor.getContenedor().getAncho());
            opciones.setAlto(reproductor.getContenedor().getAlto());
            agente.enviarEscritorioRemotoOpciones(opciones);
        }
    }

    private void cambiarTipoDatos(int tipoDatos) {
        this.tipoDatos = tipoDatos;
        this.itmCapBYTES.setSelected(tipoDatos == 1);
        this.itmCapINT.setSelected(tipoDatos == 2);
        this.enviarParametros();
    }

    private void cambiarTipoEnvio(int tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
        this.itmCapCompleta.setSelected(tipoEnvio == EscritorioRemoto.ENVIO_COMPLETO);
//        this.itmCapPixeles.setSelected(tipoEnvio == 2);
        this.itmCapBloques.setSelected(tipoEnvio == EscritorioRemoto.ENVIO_BLOQUES);
        this.enviarParametros();
    }

    private void cambiarTipoCaptura(int tipoCaptura) {
        this.tipoCaptura = tipoCaptura;
        this.itmCapDefault.setSelected(tipoCaptura == 1);
        this.itmCapPrtsc.setSelected(tipoCaptura == 2);
        itmCapDirectRobot.setSelected(tipoCaptura == 3);
        itmCapNativaFirnass.setSelected(tipoCaptura == 4);
        this.enviarParametros();
    }

    private void cambiarTipoAlgoritmo(int tipoAlgoritmo) {
        this.tipoAlgoritmo = tipoAlgoritmo;
        this.itmCapAlgoritmoV1.setSelected(tipoAlgoritmo == EscritorioRemoto.CAPTURA_ALGORITMO_V1);
        this.itmCapAlgoritmoV2.setSelected(tipoAlgoritmo == EscritorioRemoto.CAPTURA_ALGORITMO_V2);
        this.itmCapAlgoritmoV3.setSelected(tipoAlgoritmo == EscritorioRemoto.CAPTURA_ALGORITMO_V3);

        //cambio la forma del contenedor
        try {
            if (tipoAlgoritmo == EscritorioRemoto.CAPTURA_ALGORITMO_V3) {
                reproductor.modoVariasPantallas();
            } else {
                //si es escala o ajuste se configura uno u otro
                if (ESCALA == ESCALA_ORIGINAL || ESCALA == ESCALA_PORCENTUAL) {
                    reproductor.modoScroll();
                } else {
                    reproductor.modoUnico();
                }
            }
            this.pack();
        } catch (Exception e) {
        }
        if (tipoAlgoritmo == EscritorioRemoto.CAPTURA_ALGORITMO_V1) {
            itmTransNormal.setEnabled(true);
            itmTransHilosTipo1.setEnabled(true);
            itmTransHilosTipo2.setEnabled(true);
        } else {
            itmTransNormal.setEnabled(false);
            itmTransHilosTipo1.setEnabled(false);
            itmTransHilosTipo2.setEnabled(false);

            itmTransNormal.setSelected(true);
            itmTransHilosTipo1.setSelected(false);
            itmTransHilosTipo2.setSelected(false);
        }

        this.enviarParametros();
    }

    private void verificarHabilitaColor() {
        //  jScalidad.setEnabled(!calidadAutomatica.isSelected());
//                itmBN.setEnabled(!calidadAutomatica.isSelected());
        itmGris.setEnabled(!calidadAutomatica.isSelected() && itmCapBYTES.isSelected());
        itm8.setEnabled(!calidadAutomatica.isSelected() && itmCapBYTES.isSelected());
        itm24.setEnabled(!calidadAutomatica.isSelected() && itmCapBYTES.isSelected());
        itm16.setEnabled(!calidadAutomatica.isSelected() && itmCapBYTES.isSelected());
        itmFullColor.setEnabled(!calidadAutomatica.isSelected());
    }

    private void cambiarColor(int tipoColor) {
        this.tipoColor = tipoColor;
//        this.itmBN.setSelected(tipoColor == 1);
        this.itmGris.setSelected(tipoColor == 2);
        this.itm8.setSelected(tipoColor == 3);
        this.itm16.setSelected(tipoColor == 4);
        this.itm24.setSelected(tipoColor == 5);
        this.itmFullColor.setSelected(tipoColor == 6);
        this.enviarParametros();
    }

    private void btnActualizarPantalla(ActionEvent evt) {
        reproductor.limpiarBuffers();
        agente.actualizarPantalla();
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
        ventanaControles.setVisible(true);
        ventanaControles.pack();
        this.actualizarFormaControles();
        if (noOculto) {
            ocultarControles();
        }

    }

    private void btnMostrarOcultarConfig(ActionEvent evt) {
        mostrarExtra = !mostrarExtra;
        if (mostrarExtra) {
            this.add(barraInferior, BorderLayout.SOUTH);
        } else {
            this.remove(barraInferior);
        }
        this.revalidate();
        this.enviarParametros();
        this.repaint();
    }

    public void btnFinalizar(ActionEvent evt) {
        if (pidiendo) {
            //detiene
            btnIniciarDetenerAP(evt);
        }
        if (informacion != null) {
            informacion.dispose();
        }
        informacion = null;
        detenerTodo();
        agente.cerrarEscritorioRemotoDesdeVentana();
    }

    private void btnIniciarDetenerAP(ActionEvent evt) {
        this.pidiendo = !this.pidiendo;
        if (this.pidiendo) {

            //envio la configiracion del teclado
            agente.enviarConfiguracionEntrada(teclado.getMetodoActual());

            servicio = null;
            servicio = new RecibirEscritorio(conexion, this);
            servicio.start();
            servicio.setPidiendo(pidiendo);

            this.reproductor.setGrabar(itmGrabar.isSelected());

            //jlPantalla.setOpaque(false);
            btnIniciarDetener.setIcon(Util.cargarIcono("/resources/stop_close.png", 8, 8));
            this.enviarParametros();

            this.cmbMonitor.setEnabled(false);
            btnIniciarDetener.setToolTipText("Detener");

            if (itmClipboard.isSelected()) {
                clipboard.addFlavorListener();
            }

        } else {
            this.cmbMonitor.setEnabled(true);
            btnIniciarDetener.setIcon(Util.cargarIcono("/resources/start.png", 8, 8));
            btnIniciarDetener.setToolTipText("Iniciar");
            try {
                this.agente.detenerPantalla();
            } catch (Exception e) {
            }

            servicio.detener();

            Util.gc();
            clipboard.removeFlavorListener();

            try {
                conexion.cerrar();
            } catch (Exception e) {

            }
            conexion = null;
            barraInferior.setEstado("Detenido");
        }
//        servicio.setPidiendo(pidiendo);
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

    private void btnEnviarCTRLALTSUPR(ActionEvent evt) {
        agente.enviarCTRL_ALT_SUPR();
    }

    private void btnApagarMonitor(ActionEvent evt) {
        agente.apagarMonitor();
    }

    private void btnEncenderMonitor(ActionEvent evt) {
        agente.encenderMonitor();
    }

    private void btnBloquearEquipo(ActionEvent evt) {

        agente.enviarBloquearEquipo();
    }

    private void btnMOnitorearAP(ActionEvent evt) {
        itmHabMouse.setSelected(false);
        itmHabTeclado.setSelected(false);
        itmGrabar.setSelected(true);
        itmClipboard.setSelected(false);
        itmMenuCambiarCursorLocal.setSelected(false);
        itmMenuVerMouse_dibujoRemoto.setSelected(true);
    }

    private void btnArchivosAP(ActionEvent evt) {
        agente.abrirAdminArchivos();
    }

    private void btnEnviarPortapalelesAP(ActionEvent evt) {
        agente.enviarArchivosPortapapeles();
    }

    private void btnrecibirPortapapelesAP(ActionEvent evt) {
        agente.recibirArchivosPortapapeles();
    }

    private void btnChatAP(ActionEvent evt) {
        agente.abrirVentanaChat();
    }

    private void btnCamaraAP(ActionEvent evt) {
        agente.abrirVentanaCamara();
    }

    private void btnMicrofonoAP(ActionEvent evt) {
        agente.abrirVoIP();
    }

    private void escalaOriginalAP(ActionEvent evt) {
        this.itmEscalarPerfecto.setSelected(false);
        this.itmEscalarVentana.setSelected(false);
        this.itmEscalaProporcional.setSelected(false);
        this.itmEscalaOriginal.setSelected(true);
        ESCALA = ESCALA_ORIGINAL;
        this.lblTamaPan.setVisible(false);
        this.jStamanio.setVisible(false);
        this.itmAjustarVentana.setVisible(true);
        //this.itmAjustarVentana.setSelected(true);//ajusta la ventana al tamaño de la imagen
        try {
            if (tipoAlgoritmo == CAPTURA_ALGORITMO_V3) {
                reproductor.modoVariasPantallas();
            } else {
                reproductor.modoScroll();
            }
            this.pack();
        } catch (Exception e) {

        }
        this.enviarParametros();
    }

    private void escalaFijaAP(ActionEvent evt) {
        this.itmEscalarPerfecto.setSelected(false);
        this.itmEscalarVentana.setSelected(false);
        this.itmEscalaOriginal.setSelected(false);
//        this.escalaSuavizado.setSelected(false);
        this.itmEscalaProporcional.setSelected(true);
        ESCALA = ESCALA_PORCENTUAL;
        this.lblTamaPan.setVisible(true);
        this.jStamanio.setVisible(true);
        this.itmAjustarVentana.setVisible(true);
        this.itmAjustarVentana.setSelected(true);
        try {
            if (tipoAlgoritmo == CAPTURA_ALGORITMO_V3) {
                reproductor.modoVariasPantallas();
            } else {
                reproductor.modoScroll();
            }
            this.pack();
        } catch (Exception e) {

        }
        this.enviarParametros();
    }

    private void escalaTamanioAP(ActionEvent evt) {
        this.itmEscalarPerfecto.setSelected(false);
        this.itmEscalaProporcional.setSelected(false);
        this.itmEscalaOriginal.setSelected(false);
//        this.escalaSuavizado.setSelected(false);
        this.itmEscalarVentana.setSelected(true);
        ESCALA = ESCALA_VENTANA;
        this.lblTamaPan.setVisible(false);
        this.jStamanio.setVisible(false);
        this.itmAjustarVentana.setVisible(false);
        this.itmAjustarVentana.setSelected(false);
        try {
            if (tipoAlgoritmo == CAPTURA_ALGORITMO_V3) {
                reproductor.modoVariasPantallas();
            } else {
                reproductor.modoUnico();
            }
            this.pack();
        } catch (Exception e) {

        }
        this.enviarParametros();
    }

    private void escalaPerfectoAP(ActionEvent evt) {
        this.itmEscalaProporcional.setSelected(false);
        this.itmEscalarVentana.setSelected(false);
        this.itmEscalaOriginal.setSelected(false);
        this.itmEscalarPerfecto.setSelected(true);
        ESCALA = ESCALA_PERFECTO;
        this.lblTamaPan.setVisible(false);
        this.jStamanio.setVisible(false);
        this.itmAjustarVentana.setVisible(false);
        this.itmAjustarVentana.setSelected(false);
        try {
//            contenedorPrincipal.removeAll();
//            contenedorPrincipal.add(pantalla);
            if (tipoAlgoritmo == CAPTURA_ALGORITMO_V3) {
                reproductor.modoVariasPantallas();
            } else {
                reproductor.modoUnico();
            }
            this.pack();
        } catch (Exception e) {

        }
        this.enviarParametros();
    }

    private void cambiarEscalaRemoto(ActionEvent evt) {
//        if (itmEscalarRemoto.isSelected()) {
//            this.itmEscalarSuave.setEnabled(true);
//            this.itmEscalarFuerte.setEnabled(true);
//        } else {
//            this.itmEscalarSuave.setEnabled(false);
//            this.itmEscalarFuerte.setEnabled(false);
//        }

        this.enviarParametros();
    }

    private void cambiarEscalaSuave(ActionEvent evt) {
        this.itmEscalarSuave.setSelected(true);
        this.itmEscalarFuerte.setSelected(false);
        this.enviarParametros();
    }

    private void cambiarEscalaFuerte(ActionEvent evt) {
        this.itmEscalarSuave.setSelected(false);
        this.itmEscalarFuerte.setSelected(true);
        this.enviarParametros();
    }

    public void mouseMove(float px, float py, int boton, int pantallaID) {
        if (pidiendo) {
            if (this.itmHabMouse.isSelected()) {
                agregarEventoMouse(pantallaID, 0, px, py, boton, 0);
            }
        }
    }

    public void mouseDragged(float px, float py, int boton, int pantallaID) {
        if (pidiendo) {
            if (this.itmHabMouse.isSelected()) {
                agregarEventoMouse(pantallaID, 5, px, py, boton, 0);
            }
        }
    }

    public void mousePresionado(float px, float py, int boton, int pantallaID) {
        if (pidiendo) {
            if (itmHabMouse.isSelected()) {
                agregarEventoMouse(pantallaID, 1, px, py, boton, 0);
            }
        }
    }

    public void mouseLiberado(float px, float py, int boton, int pantallaID) {
        if (pidiendo) {
            if (itmHabMouse.isSelected()) {
                agregarEventoMouse(pantallaID, 2, px, py, boton, 0);
            }
        }
    }

    public void mouseRueda(float px, float py, int boton, int unidadesScroll, int pantallaID) {
        if (pidiendo) {
            if (itmHabMouse.isSelected()) {
                agregarEventoMouse(pantallaID, 4, px, py, boton, unidadesScroll);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
        if (itmHabTeclado.isSelected()) {
            if (teclado.verificarKeyEvent(e)) {
                //servidor.enviarTecla(e.getKeyChar(), 3);
//                agregarEventoTeclado(0, 3, e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.consume();
        if (pidiendo) {
            if (itmHabTeclado.isSelected()) {

                if (teclado.verificarKeyEvent(e)) {
                    //servidor.enviarTecla(teclado.mapear(e.getKeyCode()), 1);
                    agregarEventoTeclado(0, 1, e);

                }
//                agente.enviarTecla(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
        if (pidiendo) {
            if (itmHabTeclado.isSelected()) {
                if (teclado.verificarKeyEvent(e)) {
                    //servidor.enviarTecla(teclado.mapear(e.getKeyCode()), 2);
                    agregarEventoTeclado(0, 2, e);
                }
//                agente.enviarTecla(e);
            }
        }
    }

    private void scalStateChanged(ChangeEvent evt) {
        this.lblTamaPan.setText("Escala:" + jStamanio.getValue());
        this.enviarParametros();
    }

    private void caliStateChanged(ChangeEvent evt) {
        this.lblCalidad.setText("Calidad: " + jScalidad.getValue() + "");
        this.enviarParametros();
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

//    public void registrarLlegada() {
//        if (contadorFps != null) {
//            contadorFps.agregar(1);
//        }
//    }
    public JComboBox getCmbMonitor() {
        return cmbMonitor;
    }

    public void setCmbMonitor(JComboBox cmbMonitor) {
        this.cmbMonitor = cmbMonitor;
    }

    public JComboBox getResolucion() {
        return resolucion;
    }

    public void setResolucion(JComboBox resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        actualizarFormaControles();
    }

    public void detenerTodo() {
        if (pidiendo) {
            this.btnIniciarDetenerAP(null);
            this.btnIniciarDetener.setSelected(pidiendo);
        }
        detenerHilos();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        detenerTodo();
        agente.cerrarEscritorioRemoto();
        if (informacion != null) {
            informacion.dispose();
        }
        informacion = null;
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
//        try {
//            frameControles.setVisible(false);
//            frameControles.dispose();
//            frameControles = null;
//            System.out.println("framecontroles eliminado");
//        } catch (Exception ex) {
//            System.out.println("error al eliminar framecontroles");
//            ex.printStackTrace();
//        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        EscritorioRemoto.this.frameControles.setAlwaysOnTop(false);
//        EscritorioRemoto.this.frameControles.setVisible(false);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        EscritorioRemoto.this.frameControles.setAlwaysOnTop(true);
//        EscritorioRemoto.this.frameControles.setVisible(true);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        EscritorioRemoto.this.actualizarFormaControles();
//        EscritorioRemoto.this.frameControles.setAlwaysOnTop(true);
//        EscritorioRemoto.this.frameControles.setVisible(true);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        EscritorioRemoto.this.actualizarFormaControles();
//        EscritorioRemoto.this.frameControles.setAlwaysOnTop(false);
//        EscritorioRemoto.this.frameControles.setVisible(false);
    }

    private synchronized void actualizarFormaControles() {
        try {
            if (ventanaControles == null) {
                ventanaControles = new JWindow(this);
                ventanaControles.setLayout(new BorderLayout());
                ventanaControles.setType(Type.UTILITY);
                ventanaControles.setVisible(true);
                btnActivar.setBorderPainted(false);
                btnMinimizar.setBorderPainted(false);
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
                panelSur.add(btnMinimizar);
                panelSur.add(btnVerPantallaCompleta);
                panelSur.add(btnIniciarDetener);
                ventanaControles.add(barra, BorderLayout.NORTH);
                ventanaControles.add(panelSur, BorderLayout.SOUTH);
                ventanaControles.pack();
                tamanioBarra = ventanaControles.getHeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ventanaControles == null) {
            System.out.println("ERROR VENTAA CONTROLES ES NULA !!!!!!!!!!!!!!!");
        }
        btnMinimizar.setVisible(pantallaCompleta);
        int y = 0;
        try {
            int ancho = 370;
            GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            int ajuste = 9;
            int anchoPestania = 60;
            int altoControles = ventanaControles.getHeight();
            if (altoControles < 0) {
                altoControles = 0;
            }
            int altoPestania = btnActivar.getHeight();
            ancho = this.getWidth() * 80 / 100;

            if (ancho > 370) {
                ancho = 370;
            }
            if (ancho > 320) {
                ponerTextoMenus();
            } else {
                ancho = 150;
                quitarTextoMenus();
            }

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

            ventanaControles.setShape(path);
            ventanaControles.setSize(ancho, altoControles);

            try {
                y = (int) reproductor.getContenedor().getLocationOnScreen().getY();
            } catch (Exception e) {

            }

        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            ventanaControles.setLocation((int) this.getBounds().getX() + (int) this.getWidth() / 2 - ventanaControles.getWidth() / 2, y);
        }
    }

    public void ocultarControles() {
        if (corriendoOcultar) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                corriendoOcultar = true;
                for (int i = ventanaControles.getHeight(); i > btnActivar.getHeight() + 9; i -= 10) {
                    ventanaControles.setSize(ventanaControles.getWidth(), i);
                    actualizarFormaControles();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                }
                ventanaControles.setSize(ventanaControles.getWidth(), btnActivar.getHeight() + 9);
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
                try {
                    corriendoMostrar = true;
                    for (int i = btnActivar.getHeight(); i < tamanioBarra; i += 10) {
                        ventanaControles.setSize(ventanaControles.getWidth(), i);
                        actualizarFormaControles();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                        }
                    }
                    corriendoMostrar = false;
                    actualizarFormaControles();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    public void seleccionarMonitor(int indice) {
        try {
            MONITOR = -1;
            itmTodosMonitores.setSelected(true);
            for (int i = 0; i < cmbMonitor.getItemCount(); i++) {
                itmMonitores[i].setSelected(false);
            }
            String[] tmp = itmMonitores[indice].getText().split(" ");
            MONITOR = Integer.parseInt(tmp[1]);
            itmMonitores[indice].setSelected(true);
            itmTodosMonitores.setSelected(false);
        } catch (Exception e) {
        }

        if (MONITOR != -1) {
            MONITOR--;//le resta 1 porq estoy mostrando desde el 1 en adelante y no desde el 0
        }
        enviarParametros();
        agente.pedirResolucionPantalla();
    }

    public void seleccionarResolucion(int indice) {
        try {
            itmTodosMonitores.setSelected(true);
            for (int i = 0; i < resolucion.getItemCount(); i++) {
                itmResoluciones[i].setSelected(false);
            }
            EscritorioRemoto.this.strResolucion = itmResoluciones[indice].getText();
            itmResoluciones[indice].setSelected(true);
            agente.cambiarResolucion(strResolucion);
        } catch (Exception e) {
        }
    }

    public void actualizaMenuTecado() {
        menuTeclado.removeAll();

        JMenuItem itm2 = new JMenuItem();

        itm2.setText("Pedir configuracion remota");
        itm2.setFont(fuenteMenus);
        itm2.setIcon(Util.cargarIcono16("/resources/keyboard.png"));
        itm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {

                    EscritorioRemoto.this.agente.pedirConfiguracionEntrada();
                } catch (Exception e) {

                }
            }
        });

        menuTeclado.add(itm2);

        for (final Locale locale : teclado.listarConfiguraciones()) {
            JMenuItem itm = new JMenuItem();
            itm.setText(locale.getDisplayLanguage() + " " + locale.getDisplayCountry() + " (" + locale.toString() + ")");
            itm.setFont(fuenteMenus);
            itm.setIcon(Util.cargarIcono16("/resources/keyboard.png"));
            itm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        teclado.configurarMetodoEntrada(locale);
                        EscritorioRemoto.this.agente.enviarConfiguracionEntrada(locale);
                    } catch (Exception e) {

                    }
                }
            });

            menuTeclado.add(itm);
        }
    }

    public void actualizarMenuMonitor() {
        menuMonitor.removeAll();
        menuMonitor.add(itmTodosMonitores);
//        menuMonitor.add(btnListarMonitores);
        itmMonitores = new JCheckBoxMenuItem[cmbMonitor.getItemCount()];
        for (int i = 0; i < cmbMonitor.getItemCount(); i++) {
            final String item = (String) cmbMonitor.getItemAt(i);
            final int indice = i;
            itmMonitores[i] = new JCheckBoxMenuItem();
            itmMonitores[i].setIcon(Util.cargarIcono16("/resources/monitor.png"));
            itmMonitores[i].setText(item);
            itmMonitores[i].setFont(fuenteMenus);
            itmMonitores[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        EscritorioRemoto.this.seleccionarMonitor(indice);
                    } catch (Exception e) {
                        EscritorioRemoto.this.MONITOR = -1;
                    }
                }
            });
            menuMonitor.add(itmMonitores[i]);
        }
    }

    public void actualizarMenuResolucion() {
        menuResolucion.removeAll();
        itmResoluciones = new JCheckBoxMenuItem[resolucion.getItemCount()];
        for (int i = 0; i < resolucion.getItemCount(); i++) {
            final String item = (String) resolucion.getItemAt(i);
            final int indice = i;
            itmResoluciones[i] = new JCheckBoxMenuItem();
            itmResoluciones[i].setText(item);
            itmResoluciones[i].setFont(fuenteMenus);
            itmResoluciones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        EscritorioRemoto.this.seleccionarResolucion(indice);
                    } catch (Exception e) {
                        EscritorioRemoto.this.strResolucion = (String) resolucion.getItemAt(0);
                        EscritorioRemoto.this.seleccionarResolucion(0);
                    }
                }
            });
            menuResolucion.add(itmResoluciones[i]);
        }
    }

    public Asociado getAgente() {
        return agente;
    }

    public void setAgente(Asociado agente) {
        this.agente = agente;
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

    public int getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(int tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public JCheckBoxMenuItem getItmVerCeldas() {
        return itmVerCeldas;
    }

    public void setItmVerCeldas(JCheckBoxMenuItem itmVerCeldas) {
        this.itmVerCeldas = itmVerCeldas;
    }

    public BarraEstado getBarraInferior() {
        return barraInferior;
    }

    public void setBarraInferior(BarraEstado barraInferior) {
        this.barraInferior = barraInferior;
    }

    public JCheckBoxMenuItem getItmComprimir() {
        return itmComprimir;
    }

    public void setItmComprimir(JCheckBoxMenuItem itmComprimir) {
        this.itmComprimir = itmComprimir;
    }

//    @Override
//    public void mouseWheelMoved(MouseWheelEvent e) {
//        mouseRueda(e, 0);
//    }
    public void ponerTextoMenus() {
        menuAccion.setText("Acciones");
        menuVer.setText("Ver");
        menuHerramientas.setText("Herramientas");
        menuConfig.setText("Configuración");
    }

    public void quitarTextoMenus() {
        menuAccion.setText("");
        menuVer.setText("");
        menuHerramientas.setText("");
        menuConfig.setText("");
    }

    public boolean isPantallaCompleta() {
        return pantallaCompleta;
    }

    public void setPantallaCompleta(boolean pantallaCompleta) {
        this.pantallaCompleta = pantallaCompleta;
    }

    public void setConexion(Conexion conexion) {
        servicio.setConexion(conexion);
        this.conexion = conexion;
    }

    public JCheckBoxMenuItem getItmMenuVerMouse() {
        return itmMenuVerMouse_dibujoRemoto;
    }

    public void setItmMenuVerMouse(JCheckBoxMenuItem itmMenuVerMouse) {
        this.itmMenuVerMouse_dibujoRemoto = itmMenuVerMouse;
    }

    public JCheckBoxMenuItem getItmMenuCambiarCursorLocal() {
        return itmMenuCambiarCursorLocal;
    }

    public void setItmMenuCambiarCursorLocal(JCheckBoxMenuItem itmMenuCambiarCursorLocal) {
        this.itmMenuCambiarCursorLocal = itmMenuCambiarCursorLocal;
    }

    public void recibirCursorRemoto(ImageIcon cursor, Double px, Double py) {
        cambiarCursorRemoto(cursor);
        pintarCursorRemoto(px, py);
    }

    public void cambiarCursorRemoto(ImageIcon cursor) {
//        if (this.getItmMenuCambiarCursorLocal().isSelected()) {
//            if (cursor != null) {
//                Image image = cursor.getImage();
//                Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(contenedorPrincipal.getPantalla().getX(), contenedorPrincipal.getPantalla().getY()), "img");
//                contenedorPrincipal.getPantalla().setCursor(c);
//                this.cursorRemoto = c;
//            } else if (cursorRemoto != null) {
//                contenedorPrincipal.getPantalla().setCursor(this.cursorRemoto);
//            } else {
//                Cursor c = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//                contenedorPrincipal.getPantalla().setCursor(c);
//            }
//        } else {
//            try {
//                Cursor c = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
//                contenedorPrincipal.getPantalla().setCursor(c);
//            } catch (Exception e) {
//
//            }
//        }
    }

    public void pintarCursorRemoto(Double px, Double py) {
//        this.px = px;
//        this.py = py;
    }

    public RecibirEscritorio getServicio() {
        return servicio;
    }

    public void setServicio(RecibirEscritorio servicio) {
        this.servicio = servicio;
    }

    public JCheckBoxMenuItem getItmClipboard() {
        return itmClipboard;
    }

    public void setItmClipboard(JCheckBoxMenuItem itmClipboard) {
        this.itmClipboard = itmClipboard;
    }

    public JTextField getTxtColumnas() {
        return txtColumnas;
    }

    public void setTxtColumnas(JTextField txtColumnas) {
        this.txtColumnas = txtColumnas;
    }

    public ClipboardUtility getClipboard() {
        return clipboard;
    }

    public void setClipboard(ClipboardUtility clipboard) {
        this.clipboard = clipboard;
    }

    public ContadorBPS getContadorTEnvio() {
        return contadorTEnvio;
    }

    public void setContadorTEnvio(ContadorBPS contadorTEnvio) {
        this.contadorTEnvio = contadorTEnvio;
    }

    public ContadorBPS getContadorTCaptura() {
        return contadorTCaptura;
    }

    public void setContadorTCaptura(ContadorBPS contadorTCaptura) {
        this.contadorTCaptura = contadorTCaptura;
    }

    public ContadorBPS getContadorBuffer() {
        return contadorBuffer;
    }

    public void setContadorBuffer(ContadorBPS contadorBuffer) {
        this.contadorBuffer = contadorBuffer;
    }

    public ContadorBPS getContadorCalidad() {
        return contadorCalidad;
    }

    public void setContadorCalidad(ContadorBPS contadorCalidad) {
        this.contadorCalidad = contadorCalidad;
    }

    public JSlider getjScalidad() {
        return jScalidad;
    }

    public void setjScalidad(JSlider jScalidad) {
        this.jScalidad = jScalidad;
    }

    public ContadorBPS getContadorBits() {
        return contadorBits;
    }

    public void setContadorBits(ContadorBPS contadorBits) {
        this.contadorBits = contadorBits;
    }

    public ContadorBPS getContadorBloques() {
        return contadorBloques;
    }

    public void setContadorBloques(ContadorBPS contadorBloques) {
        this.contadorBloques = contadorBloques;
    }

    public ContadorBPS getContadorCeldasRC() {
        return contadorCeldasRC;
    }

    public void setContadorCeldasRC(ContadorBPS contadorCeldasRC) {
        this.contadorCeldasRC = contadorCeldasRC;
    }

    public ContadorBPS getContadorCeldasNuevas() {
        return contadorCeldasNuevas;
    }

    public void setContadorCeldasNuevas(ContadorBPS contadorCeldasNuevas) {
        this.contadorCeldasNuevas = contadorCeldasNuevas;
    }

    public ContadorBPS getContadorCeldasRepetidas() {
        return contadorCeldasRepetidas;
    }

    public void setContadorCeldasRepetidas(ContadorBPS contadorCeldasRepetidas) {
        this.contadorCeldasRepetidas = contadorCeldasRepetidas;
    }

    public ContadorBPS getContadorPorcentaje() {
        return contadorPorcentaje;
    }

    public void setContadorPorcentaje(ContadorBPS contadorPorcentaje) {
        this.contadorPorcentaje = contadorPorcentaje;
    }

    public ContadorBPS getContadorB() {
        return contadorB;
    }

    public void setContadorB(ContadorBPS contadorB) {
        this.contadorB = contadorB;
    }

    public ContadorBPS getContadorSaltadas() {
        return contadorSaltadas;
    }

    public void setContadorSaltadas(ContadorBPS contadorSaltadas) {
        this.contadorSaltadas = contadorSaltadas;
    }

    public ContadorBPS getContadorTProceso() {
        return contadorTProceso;
    }

    public void setContadorTProceso(ContadorBPS contadorTProceso) {
        this.contadorTProceso = contadorTProceso;
    }

    public JCheckBoxMenuItem getItmGrabar() {
        return itmGrabar;
    }

    public void setItmGrabar(JCheckBoxMenuItem itmGrabar) {
        this.itmGrabar = itmGrabar;
    }

//    public Contenedor getContenedorPrincipal() {
//        return contenedorPrincipal;
//    }
//
//    public void setContenedorPrincipal(Contenedor contenedorPrincipal) {
//        this.contenedorPrincipal = contenedorPrincipal;
//    }
    public JCheckBoxMenuItem getItmAjustarVentana() {
        return itmAjustarVentana;
    }

    public void setItmAjustarVentana(JCheckBoxMenuItem itmAjustarVentana) {
        this.itmAjustarVentana = itmAjustarVentana;
    }

    public JCheckBoxMenuItem getItmEscalaOriginal() {
        return itmEscalaOriginal;
    }

    public void setItmEscalaOriginal(JCheckBoxMenuItem itmEscalaOriginal) {
        this.itmEscalaOriginal = itmEscalaOriginal;
    }

    public JCheckBoxMenuItem getItmEscalaProporcional() {
        return itmEscalaProporcional;
    }

    public void setItmEscalaProporcional(JCheckBoxMenuItem itmEscalaProporcional) {
        this.itmEscalaProporcional = itmEscalaProporcional;
    }

    public JCheckBoxMenuItem getItmEscalarVentana() {
        return itmEscalarVentana;
    }

    public void setItmEscalarVentana(JCheckBoxMenuItem itmEscalarVentana) {
        this.itmEscalarVentana = itmEscalarVentana;
    }

    public JCheckBoxMenuItem getItmEscalarPerfecto() {
        return itmEscalarPerfecto;
    }

    public void setItmEscalarPerfecto(JCheckBoxMenuItem itmEscalarPerfecto) {
        this.itmEscalarPerfecto = itmEscalarPerfecto;
    }

    public JCheckBoxMenuItem getItmEscalarSuave() {
        return itmEscalarSuave;
    }

    public void setItmEscalarSuave(JCheckBoxMenuItem itmEscalarSuave) {
        this.itmEscalarSuave = itmEscalarSuave;
    }

    public JCheckBoxMenuItem getItmEscalarFuerte() {
        return itmEscalarFuerte;
    }

    public void setItmEscalarFuerte(JCheckBoxMenuItem itmEscalarFuerte) {
        this.itmEscalarFuerte = itmEscalarFuerte;
    }

    public JCheckBoxMenuItem getItmEscalarRemoto() {
        return itmEscalarRemoto;
    }

    public void setItmEscalarRemoto(JCheckBoxMenuItem itmEscalarRemoto) {
        this.itmEscalarRemoto = itmEscalarRemoto;
    }

    public int getESCALA() {
        return ESCALA;
    }

    public void setESCALA(int ESCALA) {
        this.ESCALA = ESCALA;
    }

    public Reproductor getReproductor() {
        return reproductor;
    }

    public void setReproductor(Reproductor reproductor) {
        this.reproductor = reproductor;
    }

//    private float posicionCursorX(int x) {
//        float px = 0;
//        if (cx != 0) {
//            px = (x - cx) * 100f / (contenedorPrincipal.getPantalla(0).getWidth() - cx * 2);
//        } else {
//            px = x * 100f / contenedorPrincipal.getPantalla(0).getWidth();
//        }
//        return px;
//    }
//
//    private float posicionCursorY(int y) {
//        float py = 0;
//        if (cy != 0) {
//            py = (y - cy) * 100f / (contenedorPrincipal.getPantalla(0).getHeight() - cy * 2);
//        } else {
//            py = y * 100f / contenedorPrincipal.getPantalla(0).getHeight();
//        }
//        return py;
//    }
    public void agregarEventoTeclado(int pantallaID, int tipo, KeyEvent e) {
        if (pidiendo) {
            if (itmHabTeclado.isSelected()) {
                synchronized (listaEventos) {
                    listaEventos.add(new Evento(pantallaID, tipo, teclado.mapear(e.getKeyCode()), e.getKeyChar()));
                }
            }
        }
    }

    public void agregarEventoMouse(int pantallaID, int tipo, float x, float y, int boton, int wheel) {
        if (pidiendo) {
            if (itmHabTeclado.isSelected()) {
                synchronized (listaEventos) {
                    listaEventos.add(new Evento(pantallaID, tipo, x, y, boton, wheel));
                }
            }
        }
    }

    public List<Evento> getEventos() {
        List<Evento> _eventos = new ArrayList<Evento>();
        synchronized (listaEventos) {
            _eventos = listaEventos;
            listaEventos = new ArrayList<Evento>();
        }
        return _eventos;
    }

    @Override
    public Boolean isEscalarRemoto() {
        return getItmEscalarRemoto().isSelected();
    }

    @Override
    public Boolean isAjustarVentana() {
        return getItmAjustarVentana().isSelected();
    }

    @Override
    public Boolean isEscalarSuave() {
        return getItmEscalarSuave().isSelected();
    }

    class DespachadorEventos extends Thread {

        private boolean activo;

        public void detener() {
            activo = false;
            try {
                Thread.sleep(300);
            } catch (Exception ex) {

            }
            interrupt();
        }

        public void run() {
            activo = true;
            while (activo) {
                try {
                    if (pidiendo) {
                        agente.enviarEventos(getEventos());
                    }
                    Thread.sleep(30);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
