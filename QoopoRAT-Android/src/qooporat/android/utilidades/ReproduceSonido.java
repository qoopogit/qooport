package qooporat.android.utilidades;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
/**
 *
 * @author alberto
 */
public class ReproduceSonido extends Thread {
//    private SourceDataLine tD;
    private float SampleRate;
    private int SampleSizeInBits;
    private int channel;
    public ReproduceSonido(float SampleRate, int SampleSizeInBits, int channel) {
        this.SampleRate = SampleRate;
        this.SampleSizeInBits = SampleSizeInBits;
        this.channel = channel;
    }
    public void Reproduce(byte[] buffer) {
        try {
//            this.tD.write(buffer, 0, buffer.length);
        } catch (Exception e) {
        }
    }
    public void para() {
        try {
//            this.tD.close();
        } catch (Exception e) {
        }
    }
    @Override
    public void run() {
        try {
//            AudioFormat formato = new AudioFormat(this.SampleRate, this.SampleSizeInBits, this.channel, true, false);
//            DataLine.Info line = new DataLine.Info(SourceDataLine.class, formato);
//            this.tD = ((SourceDataLine) AudioSystem.getLine(line));
//            this.tD.open(formato);
//            this.tD.start();
        } catch (Exception ex) {
        }
    }
}
