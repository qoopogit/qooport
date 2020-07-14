package rt.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifra {

    public Cifra() {
    }

//    public static String MD5(String cadena) {
//        String resultado = "";
//        try {
//            MessageDigest dm = MessageDigest.getInstance("MD5");
//            try {
//                dm.update(cadena.getBytes("UTF-8"));
//            } catch (UnsupportedEncodingException ex) {
//                dm.update(cadena.getBytes());
//            }
//            byte messageDigest[] = dm.digest();
//            StringBuffer hexString = new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++) {
//                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
//            }
//            resultado = hexString.toString();
//        } catch (NoSuchAlgorithmException ex) {
//        }
//        return resultado.toUpperCase();
//    }
    public static byte[] cifra(String sinCifrar) {
        try {
            final byte[] bytes = sinCifrar.getBytes("UTF-8");
            final Cipher aes = obtieneCipher("!@3aksjhDlkjas$%6256Ga", true);
            final byte[] cifrado = aes.doFinal(bytes);
            return cifrado;
        } catch (Exception ex) {
        }
        try {
            return sinCifrar.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return sinCifrar.getBytes();
        }
    }

    public static String descifra(byte[] cifrado) {
        try {
            final Cipher aes = obtieneCipher("!@3aksjhDlkjas$%6256Ga", false);
            final byte[] bytes = aes.doFinal(cifrado);
            final String sinCifrar = new String(bytes, "UTF-8");
            return sinCifrar;
        } catch (Exception ex) {
        }
        try {
            return new String(cifrado, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return new String(cifrado);
        }
    }

//    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
//        final String frase = "!@3aksjhDlkjas$%6256Ga";
//        final MessageDigest digest = MessageDigest.getInstance("SHA");
//        digest.update(frase.getBytes("UTF-8"));
//        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
//        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        if (paraCifrar) {
//            aes.init(Cipher.ENCRYPT_MODE, key);
//        } else {
//            aes.init(Cipher.DECRYPT_MODE, key);
//        }
//        return aes;
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

//    public static byte[] cif(String pass, byte[] input) {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            DESKeySpec kspec = new DESKeySpec(pass.getBytes());
//            SecretKey ks = skf.generateSecret(kspec);
//            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            c.init(1, ks);
//            byte[] tmp = c.update(input, 0, input.length);
//            out.write(tmp);
//            tmp = c.doFinal();
//            out.write(tmp);
//            out.close();
//            return out.toByteArray();
//        } catch (Exception ex) {
//        }
//        return null;
//    }
//
//    public static byte[] des(String pass, byte[] input) {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            DESKeySpec kspec = new DESKeySpec(pass.getBytes());
//            SecretKey ks = skf.generateSecret(kspec);
//            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
//            c.init(2, ks);
//            byte[] tmp = c.update(input, 0, input.length);
//            out.write(tmp);
//            tmp = c.doFinal();
//            out.write(tmp);
//            out.close();
//            return out.toByteArray();
//        } catch (Exception ex) {
//        }
//        return null;
//    }
//    public static byte[] cif(String contrasena, byte[] input) {
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
    public static byte[] des(String contrasena, byte[] input) {
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
