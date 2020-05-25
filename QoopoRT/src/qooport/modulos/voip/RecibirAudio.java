package qooport.modulos.voip;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import network.Conexion;
import qooport.utilidades.ReproduceSonido;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class RecibirAudio extends Thread {

    private Conexion conexion;
    private VoIp ventana;
    private boolean pidiendo;
    private ReproduceSonido sonido;
    private AudioFormat formato;
    private final AudioFileFormat.Type formatoAudio = AudioFileFormat.Type.WAVE;

    public RecibirAudio() {
    }

    public RecibirAudio(Conexion conexion, VoIp ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public VoIp getVentana() {
        return ventana;
    }

    public void setVentana(VoIp ventana) {
        this.ventana = ventana;
    }

    public boolean isPidiendo() {
        return pidiendo;
    }

    public void setPidiendo(boolean pidiendo) {
        this.pidiendo = pidiendo;
    }

    public void iniciarSonido() {
        this.sonido = new ReproduceSonido(formato.getSampleRate(), formato.getSampleSizeInBits(), formato.getChannels());
        this.sonido.start();
    }

    public void pararSonido() {
        sonido.para();
    }

    public void escogerFormato() {
        formato = this.configurarFormato();
    }

    private AudioFormat configurarFormato() {
        float sampleRate = 8000;
        int sampleSizeInBits = 8;
        int channels = 1;
        try {
            String[] partes = ventana.armarOrden().split(":");
            sampleRate = Float.valueOf(partes[0]);
            sampleSizeInBits = Integer.valueOf(partes[1]);
            channels = Integer.valueOf(partes[2]);
        } catch (Exception e) {
        }
        boolean signed = true;
        boolean bigEndian = true;
        if (ventana.isAndroid()) {
            bigEndian = false;
        }
//        bigEndian = false;
        return new AudioFormat(sampleRate,
                sampleSizeInBits, channels, signed, bigEndian);
    }

    public AudioFormat getFormato() {
        return formato;
    }

    public void setVolume(float valor) {
        sonido.setVolume(valor);
    }

    public float getMaximo() {
        return sonido.getMaximo();
    }

    public float getMinimo() {
        return sonido.getMinimo();
    }

    public void setFormato(AudioFormat formato) {
        this.formato = formato;
    }

    @Override
    public void run() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
            File carAnioMes = new File(ventana.getServidor().getdAudio(), sdf.format(new Date()));
            File carpedia = new File(carAnioMes, sdf2.format(new Date()));
            carpedia.mkdirs();
            try {
                byte[] buf = null;
                if (ventana.isYaLlego()) {
                    if (pidiendo) {
                        buf = (byte[]) conexion.leerObjeto();
                        this.sonido.reproduce(buf);
                        ventana.getServidor().agregarRecibidos(buf.length);
                        ventana.setYaLlego(true);
                        try {
                            ventana.getContadorBps().agregar(buf.length);
                        } catch (Exception e) {
                        }
                        if (ventana.getBtnGrabar().isSelected()) {
                            AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(buf), formato, buf.length);
                            File ar = new File(carpedia, Util.nombreHora() + ".wav");
                            AudioSystem.write(ais, this.formatoAudio, ar);
                        }
                    } else {
                        buf = null;
                    }
                    try {
                        ventana.graficarBytesRemoto(buf);
                    } catch (Exception e) {
                    }
                } else {
                    ventana.getContadorBps().getTasaFormated();
                }
                Thread.sleep(100);//disminuye el uso de cpu
            } catch (Exception ex) {
            }

        }

    }
}
