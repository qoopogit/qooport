/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.deps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * @author alberto
 */
public class Util {
    
    
    // como usarlo 
    //https://www.exploit-db.com/docs/41897.pdf
    
    
    //http://www.semecayounexploit.com/?sec=bugs-y-exploits&nota=32
    
    public static String crearTituloString(String cadena, int largo) {
        int largoLateral = (largo - cadena.length()) / 2;
        char[] chars = new char[largoLateral];
        Arrays.fill(chars, '=');
        return new String(chars) + " " + cadena + " " + new String(chars);
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
    
    public static String getArchivoTexto(String ruta) {
        FileReader fr;
        BufferedReader br = null;
        StringBuilder contenido = new StringBuilder();
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
                contenido.append("\n");
            }
        } catch (Exception e) {
            System.out.println("error al leer el archivo");
            e.printStackTrace();
            contenido = new StringBuilder("n/a");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
            }
        }
        return contenido.toString();
    }
}
