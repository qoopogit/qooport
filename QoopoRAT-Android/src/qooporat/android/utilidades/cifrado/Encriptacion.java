/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.utilidades.cifrado;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author alberto
 */
//@Stateless
//@LocalBean
public class Encriptacion {
    public Encriptacion() {
    }
    /**
     * Crea un hash MD5
     *
     * @param cadena
     * @return
     */
    public static String MD5(String cadena) {
        String resultado = "";
        try {
            MessageDigest dm = MessageDigest.getInstance("MD5");
            try {
                dm.update(cadena.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                dm.update(cadena.getBytes());
            }
            byte messageDigest[] = dm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            resultado = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado.toUpperCase();
    }
    /**
     * Crea un hast SHA
     *
     * @param cadena
     * @return
     */
    public static String SHA(String cadena) {
        String resultado = "";
        try {
            MessageDigest dm = MessageDigest.getInstance("SHA");
            dm.update(cadena.getBytes());
            byte messageDigest[] = dm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            resultado = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado.toUpperCase();
    }
    /**
     * Crea una hast SHA256
     *
     * @param cadena
     * @return
     */
    public static String SHA256(String cadena) {
        String resultado = "";
        try {
            MessageDigest dm = MessageDigest.getInstance("SHA-256");
            dm.update(cadena.getBytes());
            byte messageDigest[] = dm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            resultado = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado.toUpperCase();
    }
    /**
     * Crea un hast 256
     *
     * @param cadena
     * @return
     */
    public String SHA512(String cadena) {
        String resultado = "";
        try {
            MessageDigest dm = MessageDigest.getInstance("SHA-512");
            dm.update(cadena.getBytes());
            byte messageDigest[] = dm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            resultado = hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado.toUpperCase();
    }
    /**
     * Crea un hast de la password del sistema
     *
     * @param cadena
     * @return
     */
    public static String HASH(String cadena) {
        return SHA256(SHA(SHA256(cadena))).toUpperCase();
    }
    public static byte[] cifra(String sinCifrar) {
        try {
            final byte[] bytes = sinCifrar.getBytes("UTF-8");
            final Cipher aes = obtieneCipher(true);
            final byte[] cifrado = aes.doFinal(bytes);
            return cifrado;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Encriptacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return sinCifrar.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return sinCifrar.getBytes();
        }
    }
    public static String descifra(byte[] cifrado) {
        try {
            final Cipher aes = obtieneCipher(false);
//        final byte[] bytes = aes.doFinal(passwordBytes);
            final byte[] bytes = aes.doFinal(cifrado);
            final String sinCifrar = new String(bytes, "UTF-8");
            return sinCifrar;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Encriptacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return new String(cifrado, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return new String(cifrado);
        }
    }
    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "!@3aksjhDlkjas$%6256Ga";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }
        return aes;
    }
}
