package rt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import rt.Inicio;

//Class Loader
public class CLRT extends ClassLoader {

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        byte[] tmp = null;
        InputStream m = getResourceAsStream(name.replace(".", "/").concat(new String(new char[]{'.', 'c', 'l', 'a'})));
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[1024];
            int i;
            while ((i = m.read(buf)) > -1) {
                b.write(buf, 0, i);
            }
            b.close();
            tmp = UtilRT.descomprimirGZIP(Cifra.des((String) Inicio.config.obtenerParametro("claveClase"), b.toByteArray()));
            return tmp;
        } catch (Exception ex) {
        }
        return tmp;
    }

    public InputStream descifrar(String name) {
        byte[] tmp = null;
        try {
            InputStream m = Inicio.class.getResourceAsStream(name);
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
                tmp = UtilRT.descomprimirGZIP(Cifra.des(Inicio.pCCON, b.toByteArray()));
                return new ByteArrayInputStream(tmp);
            } catch (Exception e) {
                return Inicio.class.getResourceAsStream(name);
            }
        } catch (Exception ex) {
            return Inicio.class.getResourceAsStream(name);
        }
    }
}
