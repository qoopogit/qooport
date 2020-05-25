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
public class LoaderService {

    public static String[] nombresLibs = new String[]{
        "ServiceLoaderC.exe",
        "ServiceLoaderC.pdb",
        "ServiceLoaderC.vshost.exe",
        "ServiceLoader.vshost.exe.manifest"
    };

    private File carpeta;

    private void extraer() {

        carpeta = new File("tmp");
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        for (String item : nombresLibs) {
            try {
                File p = new File(carpeta, item);
                InputStream recurso = getClass().getResourceAsStream("/extras/service/" + item);
                if (!p.exists()) {
                    copyFile(recurso, p);
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

    public void lanzarAppEscaparIDSesion0(String ruta) {
        extraer();
        try {
            Process p;
            File lanzador = new File(carpeta, "ServiceLoaderC.exe");
            //metodo 1

//            Util.escribirLog("Voy a lanzarAppEscaparIDSesion0 {" + "\"" + lanzador.getAbsolutePath() + "\" \"" + ruta + "\"" + "}");
//            Process p = Runtime.getRuntime().exec("\"" + lanzador.getAbsolutePath() + "\" \"" + ruta + "\"");
//            p.waitFor();
//            p.destroy();
            //metodo 2
            final ArrayList<String> command = new ArrayList<String>();
            command.add("\"" + lanzador.getAbsolutePath() + "\"");
            command.add("\"" + ruta + "\"");
            //command.add(">> F:/logErrorQoopoRT.txt");

            //Util.escribirLog("Voy a lanzarAppEscaparIDSesion0 {" + command.toString() + "}");
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            p = builder.start();

            InputStream inStream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String salida = scan.nextLine();
                Util.escribirLog("Salida:" + salida);
            }

            Util.escribirLog("Fin ejecucion:");

        } catch (Exception ex) {
            Util.escribirLog("ERRO AL LANZAR EN JNAUTIL ", ex);
        }
        eliminar();

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
