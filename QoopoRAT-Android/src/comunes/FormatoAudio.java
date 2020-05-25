/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.io.Serializable;
/**
 *
 * @author alberto
 */
public class FormatoAudio implements Serializable {
    private float sampleRate;
    private int sampleSizeInBits;
    private int channels;
    private boolean signed;
    private boolean bigEndian;
    public FormatoAudio() {
    }
    public FormatoAudio(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        this.sampleRate = sampleRate;
        this.sampleSizeInBits = sampleSizeInBits;
        this.channels = channels;
        this.signed = signed;
        this.bigEndian = bigEndian;
    }
    public float getSampleRate() {
        return sampleRate;
    }
    public void setSampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
    }
    public int getSampleSizeInBits() {
        return sampleSizeInBits;
    }
    public void setSampleSizeInBits(int sampleSizeInBits) {
        this.sampleSizeInBits = sampleSizeInBits;
    }
    public int getChannels() {
        return channels;
    }
    public void setChannels(int channels) {
        this.channels = channels;
    }
    public boolean isSigned() {
        return signed;
    }
    public void setSigned(boolean signed) {
        this.signed = signed;
    }
    public boolean isBigEndian() {
        return bigEndian;
    }
    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }
    @Override
    public String toString() {
        return "FormatoAudio{" + "sampleRate=" + sampleRate + ", sampleSizeInBits=" + sampleSizeInBits + ", channels=" + channels + ", signed=" + signed + ", bigEndian=" + bigEndian + '}';
    }
}
