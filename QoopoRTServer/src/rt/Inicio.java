package rt;

import certificado.IniciarCertificado;
import comunes.CFG;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;
import comunes.Interfaz;
import network.ConexionServer;
import rt.util.CLRT;
import rt.util.UtilRT;

public class Inicio {

    public static CFG config;
    public final static boolean DEBUG = false;
    public static Interfaz in;//instalador
    public static String v = "1.4.9";//version
    public static String i = "";//identificador
    public static String pCCON = "LAOSUISNPASD12378ASDLGASDHGAKD"; //password del archivo de configuracion
    private static File f;
    private static FileChannel channel;
    private static FileLock lock;
    public static int max = 512;
    public static int min = 24;
    public static List<Interfaz> con;//conexiones
    
    //cuenta los conectados
    public static int c() {
        int r = 0;
        try {
            for (Interfaz serv : con) {
                if ((Boolean) serv.get(1)) {
                    r++;
                }
            }
        } catch (Exception e) {
        }
        return r;
    }

    //hosts conectados
    public static String hc() {
        StringBuilder r = new StringBuilder();
        try {
            int c = 0;
            for (Interfaz serv : con) {
                c++;
                if ((Boolean) serv.get(1)) {
                    if (c == con.size()) {
                        r.append((String) serv.get(2));
                    } else {
                        r.append((String) serv.get(2)).append(" - ");
                    }
                }
            }
        } catch (Exception e) {
        }
        return r.toString();
    }

    //hosts
    public static String h() {
        StringBuilder r = new StringBuilder();
        try {
            int c = 0;
            for (Interfaz serv : con) {
                c++;
                if (c == con.size()) {
                    r.append((String) serv.get(2));
                } else {
                    r.append((String) serv.get(2)).append(" - ");
                }
            }
        } catch (Exception e) {
        }
        return r.toString();
    }

    // --service.- iniciar como servicio en windows, se encarga de lanzar otra instancia del servidor pero escapando de id sesion 0, para poder obtener una interfaz grafica
    // --update.- es una actualizacion, espera 8 segundos antes de continuar para dejar tiempo qe la version anterior se desinstale completamente
    public static void main(String[] args) {

        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            
            String rutaAeliminar = null;
            boolean eliminarArchivo = false;
//            String cc="U29mdHdhcmVcTWljcm9zb2Z0XFdpbmRvd3NcQ3VycmVudFZlcnNpb25cUnVu";
//            System.out.println("salida=" + B64.decodeString(cc));
//            UtilRT.escribirLog("argumentos ");
//            UtilRT.escribirLog(Arrays.toString(args));
            IniciarCertificado.iniciar();
            boolean actualizacion = false;
            boolean servicio = false;
            boolean instaladoServicio = false;
            boolean eliminarTmp = false;
            try {
                for (String param : args) {
                    if (param.equals("--update")) {
                        actualizacion = true;
                    } else if (param.equals("--service")) {//solo para windows
                        CLRT cl = new CLRT();
                        Interfaz d = ((Interfaz) cl.loadClass("rt.ServiceLoader").newInstance());
                        d.ejecutar(2);//iniciar aplicacion, escapando de la session 0
                    } else if (param.equals("--background")) {//Se ejcuta normalmente
                        instaladoServicio = true;
                    } else if (param.equals("--delete")) {//Se ejecuta normalmente
                        eliminarTmp = true;
                    } else {
                        //el anterior parametros es para eliminar el server que fue subido en la actualizacion automatica
                        if (eliminarTmp) {
                            rutaAeliminar = param;
                            eliminarArchivo = true;
                        }
                        eliminarTmp = false;
                    }
                }
            } catch (Exception e) {
            }
            //esperamos 8 segundos por si acaso se actualiza el server se instale despues q el anterior se finalice
            if (actualizacion) {
                try {
                    Thread.sleep(8000);
                } catch (Exception e) {
                }

            }
            if (eliminarArchivo) {
                try {
                    UtilRT.eliminar(rutaAeliminar);
                } catch (Exception e) {

                }
            }

            if (!servicio) {
//                UtilRT.escribirLog("Continuo ejecucion");
                new Inicio().inicia(actualizacion, instaladoServicio);
            } else {
                //mantiene ejecutado , sino da error
                while (true) {
                    UtilRT.dormir(1000);
                }
            }
        } catch (Exception ex) {
        }
    }

    //desinstalar
    public static void d() {
        try {
            CLRT cl = new CLRT();
            Interfaz d = ((Interfaz) cl.loadClass("rt.util.DINST").newInstance());
            d.instanciar(Inicio.config.obtenerParametro("jarName"),
                    Inicio.config.obtenerParametro("regName"),
                    Inicio.config.obtenerParametro("autoInicio"),
                    Inicio.config.obtenerParametro("progTareas"));
        } catch (Exception ex) {
        }
    }

    //Detiene el instalador
    public static void dIn() {
        try {
            in.ejecutar(1);
        } catch (Exception e) {
        }
    }
//Cargar Config

    private void cc() {
        try {
            CLRT cl = new CLRT();
            Inicio.config = (CFG) UtilRT.leerObjeto(cl.descifrar("/cfg.dat"));
        } catch (Exception e) {
            //configuracion default
            Inicio.config = new CFG();
            Inicio.config.inicializarParamertros();

            Inicio.config.agregarParametro("dns", "");
            Inicio.config.agregarParametro("claveClase", "");
            Inicio.config.agregarParametro("jarName", "");
            Inicio.config.agregarParametro("nombreUSB", "");
            Inicio.config.agregarParametro("password", "");
            Inicio.config.agregarParametro("prefijo", "");
            Inicio.config.agregarParametro("regName", "");
            Inicio.config.agregarParametro("ssl", Boolean.TRUE);
            Inicio.config.agregarParametro("protocolo", ConexionServer.TCP);
        }
    }

    public void inicia(boolean actualizacion, boolean instalandoServicio) throws IOException {
        try {
            CLRT cl = new CLRT();
            cc();
            rm();
            Interfaz server = ((Interfaz) cl.loadClass("rt.RtServer").newInstance());
            server.instanciar(actualizacion, instalandoServicio);
//            input.close();
//            input = null;
            cl = null;
//            configs = null;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    //revisa el mutex
    private void rm() {
        try {
//            f = new File(System.getProperty("java.io.tmpdir"), "orajupdatev2.lock");
            //servidores diferentes, mutex diferentes
            f = new File(System.getProperty("java.io.tmpdir"), "orajupdatev" + v + ".lock");
            channel = new RandomAccessFile(f, "rw").getChannel();
            lock = channel.tryLock();
            if (lock == null) {
                channel.close();
                System.exit(0);
            }
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        } catch (Exception ex) {
        }
    }

    public static void unlockFile() {
        try {
            if (lock != null) {
                lock.release();
                channel.close();
                f.delete();
            }
        } catch (IOException e) {
        }
    }

    static class ShutdownHook extends Thread {

        @Override
        public void run() {
            unlockFile();
        }
    }
}
