package rt.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifra {

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

    public Cifra() {
    }
}
