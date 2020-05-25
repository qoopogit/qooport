/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author alberto
 */
public class ArchivoUtil {

    public static byte[] bytesArchivo(File f) {

        byte[] bytes = null;
        try {
//            return Files.readAllBytes(Paths.get(f.getAbsolutePath()));
            FileInputStream fis = new FileInputStream(f);
            bytes = getBytes(new FileInputStream(f));
            fis.close();
            fis = null;
        } catch (IOException ex) {
            return null;
        }
        return bytes;
    }

    public static void crerArchivo(byte[] datos, String archivo) {
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf;
        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1) {
                bos.write(buf, 0, len);
            }
            buf = bos.toByteArray();
        }
        return buf;
    }

    public static List<File> textoaListaArchivo(String data) {
        List<File> list = new ArrayList(1);
        StringTokenizer st = new StringTokenizer(data, "\r\n");
        while (st.hasMoreTokens()) {
            try {
                list.add(new File(st.nextToken()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
