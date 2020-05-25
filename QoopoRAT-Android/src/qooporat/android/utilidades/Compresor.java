/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 *
 * @author alberto
 */
public class Compresor {
    /**
     * Obtiene el array de bytes comprimido a partir de otro array de bytes que
     * se quiere comprimir.
     *
     * @param file los datos descomprimidos
     * @return los datos comprimidos.
     * @throws IOException de vez en cuando
     */
    public static byte[] comprimirGZIP(byte[] file) throws IOException {
        ByteArrayOutputStream gzdata = new ByteArrayOutputStream();
        GZIPOutputStream gzipper = new GZIPOutputStream(gzdata);
        ByteArrayInputStream data = new ByteArrayInputStream(file);
        byte[] readed = new byte[1024];
        int actual = 1;
        while ((actual = data.read(readed)) > 0) {
            gzipper.write(readed, 0, actual);
        }
        gzipper.finish();
        data.close();
        byte[] compressed = gzdata.toByteArray();
        gzdata.close();
        return compressed;
    }
    /**
     * Obtiene el array de bytes descomprimido a partir de otro array de bytes
     * comprimido
     *
     * @param file los datos comprimidos
     * @return los datos descomprimidos.
     * @throws IOException de vez en cuando
     */
    public static byte[] descomprimirGZIP(byte[] file) throws IOException {
        ByteArrayInputStream gzdata = new ByteArrayInputStream(file);
        GZIPInputStream gunzipper = new GZIPInputStream(gzdata, file.length);
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        byte[] readed = new byte[1024];
        int actual = 1;
        while ((actual = gunzipper.read(readed)) > 0) {
            data.write(readed, 0, actual);
        }
        gzdata.close();
        gunzipper.close();
        byte[] returndata = data.toByteArray();
//        csvdata.close();
        return returndata;
    }
}
