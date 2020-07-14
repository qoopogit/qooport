package qooport.utilidades.cifrado;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

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

    public static byte[] cifrar(String contrasena, byte[] input) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Cipher c = obtieneCipher(contrasena, true);            
            byte[] tmp = c.update(input, 0, input.length);
            out.write(tmp);
            tmp = c.doFinal();
            out.write(tmp);
            out.close();
            return out.toByteArray();
        } catch (Exception ex) {
            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
