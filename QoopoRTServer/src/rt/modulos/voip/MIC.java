package rt.modulos.voip;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;
import network.Conexion;
import rt.Inicio;
import comunes.Interfaz;
import rt.util.Protocolo;
import rt.util.UtilRT;

//microfono
public class MIC extends Thread implements Interfaz {

    private Interfaz servicio;
    private TargetDataLine line;
    private Line.Info info;
    private AudioFormat format;
    private int bufferSize;
    public boolean activo;

    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
    }

//    private boolean estaDetenido() {
//        return isInterrupted();
//    }
    private void iniciar() {
        start();
    }

    private void abrir(String tipo) {
        try {
            String[] re = tipo.split("&&");
            format = REP.configurarFormato(re[0]);
            //captura del microfono
            if (re.length == 1 || re[1].equalsIgnoreCase("0")) {//default
                info = new DataLine.Info(TargetDataLine.class, format);
            } else if (re[1].equalsIgnoreCase("1")) { // es de salida pero probaremos
                info = Port.Info.SPEAKER;
            } else if (re[1].equalsIgnoreCase("2")) {//line
                info = Port.Info.LINE_IN;
            } else if (re[1].equalsIgnoreCase("3")) {//microfono implicito
                info = Port.Info.MICROPHONE;
            } else if (re[1].equalsIgnoreCase("4")) {//disco compacto
                info = Port.Info.COMPACT_DISC;
            }

//            bufferSize = (int) format.getSampleRate() * format.getFrameSize();
            bufferSize = (int) (format.getSampleRate() * format.getFrameSize()) / 8;// se demora la mitad de tiempo en tomar wl AUDIO y enviarlo
//            System.out.println("buffersize=" + bufferSize);
            activo = true;
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (Exception ex) {
            servicio.ejecutar(6, "ERROR AL ABRIR EL CANAL :" + ex.getMessage());
        }
    }

    private void cerrar() {
        try {
            line.stop();
            line.close();
            activo = false;
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {

//        setName("hilo-MIC");
        Conexion conexion = null;
        try {

//            System.out.println("conectando a "+(String) servicio.get(2)+" "+(Integer) servicio.get(4));
            conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
            conexion.escribirInt(Protocolo.AUDIO);
            conexion.flush();
            conexion.escribirObjeto(UtilRT.texto(Inicio.i));
            conexion.flush();
            byte buffer[];
            int c = 0;
            while (activo) {
                try {
                    buffer = new byte[bufferSize];
                    c = line.read(buffer, 0, buffer.length);
                    if (c > 0) {
                        conexion.escribirObjeto(buffer);
                        conexion.flush();
                    }
                } catch (Exception ex) {
                    activo = false;
                }
            }
            conexion.flush();
            conexion.cerrar();
            cerrar();
        } catch (Exception ex) {
        }
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;

    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
            case 1:
                cerrar();
                break;
            case 2:
                abrir((String) parametros[0]);
                break;
        }
    }
}
