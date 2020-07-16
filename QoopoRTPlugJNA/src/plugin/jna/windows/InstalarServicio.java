/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.windows;

import comunes.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aigarcia
 */
public class InstalarServicio {

    public static String[] nombresLibs = new String[]{"nssm.exe"};

    private File carpeta;

    public void extraer(boolean ar64) {

        carpeta = new File("tmp");
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        for (String item : nombresLibs) {
            try {
                File p = new File(carpeta, item);
                if (ar64) {
                    InputStream recurso = getClass().getResourceAsStream("/extras/nssm/win64/" + item);
                    if (!p.exists()) {
                        copyFile(recurso, p);
                    }
                } else {
                    InputStream recurso = getClass().getResourceAsStream("/extras/nssm/win32/" + item);
                    if (!p.exists()) {
                        copyFile(recurso, p);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    private void eliminar() {
        for (String item : nombresLibs) {
            try {
                File p = new File(carpeta, item);
                p.delete();
            } catch (Exception ex) {
            }
        }
    }

    public void instalar(String ruta, String parametros, String nombreServicio) {
        try {
            File lanzador = new File(carpeta, "nssm.exe");
            final ArrayList<String> command = new ArrayList<String>();
            command.add("\"" + lanzador.getAbsolutePath() + "\"");
            command.add("install");
            command.add(nombreServicio);
            command.add("\"" + ruta + "\"");
            command.add("\"" + parametros + "\"");
            Util.escribirLog("JNA - Se va instalar");
            Util.escribirLog(command.toString());
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream inStream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String salida = scan.nextLine();
                Util.escribirLog("Salida:" + salida);
            }

            Util.escribirLog("JNA - Fin ejecucion:");
        } catch (Exception ex) {

        }
//        eliminar();
    }

    public void iniciar(String nombreServicio) {
        try {
            File lanzador = new File(carpeta, "nssm.exe");
            final ArrayList<String> command = new ArrayList<String>();
            command.add("\"" + lanzador.getAbsolutePath() + "\"");
            command.add("start");
            command.add(nombreServicio);
            Util.escribirLog("Se va Iniciar");
            Util.escribirLog(command.toString());
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream inStream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String salida = scan.nextLine();
                Util.escribirLog("Salida:" + salida);
            }
            Util.escribirLog("Fin ejecucion:");
            Util.escribirLog("Servicio iniciado");
        } catch (Exception ex) {
            Util.escribirLog("Error al iniciar servicio", ex);
        }
//        eliminar();
    }

    public void desinstalarServicio(String nombreServicio) {
        try {
            File lanzador = new File(carpeta, "nssm.exe");
            final ArrayList<String> command = new ArrayList<String>();
            command.add("\"" + lanzador.getAbsolutePath() + "\"");
            command.add("remove");
            command.add(nombreServicio);
            command.add("confirm"); //sin confirmacion

            Util.escribirLog("Se va desinstalar");
            Util.escribirLog(command.toString());

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream inStream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String salida = scan.nextLine();
                Util.escribirLog("Salida:" + salida);
            }
            Util.escribirLog("Servicio desinstalado");
        } catch (Exception ex) {
            Util.escribirLog("Error al destnstalar servicio", ex);
        }
//        eliminar();
    }

    public void detenerServicio(String nombreServicio) {
        try {
            File lanzador = new File(carpeta, "nssm.exe");
            final ArrayList<String> command = new ArrayList<String>();
            command.add("\"" + lanzador.getAbsolutePath() + "\"");
            command.add("stop");
            command.add(nombreServicio);

            Util.escribirLog("Se va detener");
            Util.escribirLog(command.toString());

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream inStream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String salida = scan.nextLine();
                Util.escribirLog("Salida:" + salida);
            }

            Util.escribirLog("Servicio detenido");
        } catch (Exception ex) {
            Util.escribirLog("Error al detener servicio", ex);
        }
//        eliminar();
    }

    public static void copyFile(InputStream input, File dst) throws IOException {
        OutputStream output = new FileOutputStream(dst);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        input.close();
        output.close();

    }
}
