/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt;

import java.io.File;
import comunes.Interfaz;
import rt.util.UtilRT;

/**
 * Se encarga de lanzar una ejecución del servicio escapando del id sesion 0 en
 * windows
 *
 * @author aigarcia
 */
public class ServiceLoader implements Interfaz {

    private Interfaz jnaUtil = null;
    private boolean exito;

    private boolean instalar(String nombreServicio) {
        if (UtilRT.isWindows()) {
            try {
                final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                final File currentJar = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                StringBuilder command = new StringBuilder();
                //command.append(javaBin).append(" ");
                command.append("-Xms").append(Inicio.min).append("m").append(" ");
                command.append("-Xmx").append(Inicio.max).append("m").append(" ");
                command.append("-jar").append(" ");
                command.append(currentJar.getPath()).append(" ");
                command.append("--service");
                jnaUtil = ((Interfaz) Class.forName("plugin.jna.JnaUtil").newInstance());
//                UtilRT.escribirLog("server - Se va a instalar servicio [" + nombreServicio + "]   [" + command.toString() + "]");
                jnaUtil.ejecutar(3, javaBin, command.toString(), nombreServicio);
                return true;
            } catch (Exception ex) {
//                UtilRT.escribirLog("ERROR AL INSTALAR", ex);
                jnaUtil = null;
                return false;
            }
        }
        return false;
    }

    private boolean iniciar() {
        if (UtilRT.isWindows()) {
            try {
                final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                final File currentJar = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                StringBuilder command = new StringBuilder();
                command.append(javaBin).append(" ");
                command.append("-Xms").append(Inicio.min).append("m").append(" ");
                command.append("-Xmx").append(Inicio.max).append("m").append(" ");
                command.append("-jar").append(" ");
                command.append(currentJar.getPath()).append(" ");
                command.append("--background");
                jnaUtil = ((Interfaz) Class.forName("plugin.jna.JnaUtil").newInstance());
//                UtilRT.escribirLog("server- se a lanzar el background El comadno q lanzo de parametro es :[" + command.toString() + "]");
                jnaUtil.ejecutar(2, command.toString());
                return true;
            } catch (Exception ex) {
//                UtilRT.escribirLog("ERROR AL LANZAR", ex);
                jnaUtil = null;
                return false;
            }

        }
        return false;
    }

    @Override
    public void instanciar(Object... parametros) {

    }

    @Override
    public void set(int opcion, Object valor) {

    }

    @Override
    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                return exito;
        }
        return null;
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                instalar((String) parametros[0]);
                break;
            case 2:
                exito = iniciar();
                break;
        }
    }
}
