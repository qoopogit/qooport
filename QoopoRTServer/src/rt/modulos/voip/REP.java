package rt.modulos.voip;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

//Reproductor
public class REP extends Thread {

    private SourceDataLine tD;
    private float SampleRate;
    private int SampleSizeInBits;
    private int channel;

    public REP(float SampleRate, int SampleSizeInBits, int channel) {
        this.SampleRate = SampleRate;
        this.SampleSizeInBits = SampleSizeInBits;
        this.channel = channel;
    }

    public void reproduce(byte[] buffer) {
        try {
            this.tD.write(buffer, 0, buffer.length);
        } catch (Exception e) {
        }
    }

    public void para() {
        try {
            this.tD.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {

//        setName("hilo-REP");
        try {
            AudioFormat formato = new AudioFormat(this.SampleRate, this.SampleSizeInBits, this.channel, true, false);
            DataLine.Info line = new DataLine.Info(SourceDataLine.class, formato);
            this.tD = ((SourceDataLine) AudioSystem.getLine(line));
            this.tD.open(formato);
            this.tD.start();
        } catch (LineUnavailableException ex) {
        }
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
