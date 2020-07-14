package qooport.utilidades.cifrado;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

    public static byte[] cifrar(String contrasena, byte[] input) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
            DESKeySpec kspec = new DESKeySpec(contrasena.getBytes());
            SecretKey ks = skf.generateSecret(kspec);
            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
            c.init(1, ks);
            byte[] tmp = c.update(input, 0, input.length);
            out.write(tmp);
            tmp = c.doFinal();
            out.write(tmp);
            out.close();
            return out.toByteArray();
        } catch (Exception ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] descifrar(String contrasena, byte[] input) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SecretKeyFactory skf = SecretKeyFactory.getInstance(new String(new char[]{'D', 'E', 'S'}));
            DESKeySpec kspec = new DESKeySpec(contrasena.getBytes());
            SecretKey ks = skf.generateSecret(kspec);
            Cipher c = Cipher.getInstance(new String(new char[]{'D', 'E', 'S'}));
            c.init(2, ks);
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
