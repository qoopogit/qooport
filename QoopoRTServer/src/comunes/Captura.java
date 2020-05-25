package comunes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Captura implements Serializable {

    private int pantalla;
    private int tipo;
    private int ancho;
    private int alto;
    private Date fecha;
    private List<PantallaBloque> bloques = new ArrayList<PantallaBloque>();
    private long tEnvio = 0;
    private long tCaptura = 0;
    private long tProceso = 0;
    private int tamBuffer;
    private float calidad;
    private boolean jpg;
    private int tipoImagen;
    private int bits;//bits de la imagen
    private int saltadas;
    private float porcentaje;

    public Captura() {
    }

    public int getPantalla() {
        return pantalla;
    }

    public void setPantalla(int pantalla) {
        this.pantalla = pantalla;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<PantallaBloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<PantallaBloque> bloques) {
        this.bloques = bloques;
    }

    public long gettEnvio() {
        return tEnvio;
    }

    public void settEnvio(long tEnvio) {
        this.tEnvio = tEnvio;
    }

    public long gettCaptura() {
        return tCaptura;
    }

    public void settCaptura(long tCaptura) {
        this.tCaptura = tCaptura;
    }

    public int getTamBuffer() {
        return tamBuffer;
    }

    public void setTamBuffer(int tamBuffer) {
        this.tamBuffer = tamBuffer;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getCalidad() {
        return calidad;
    }

    public void setCalidad(float calidad) {
        this.calidad = calidad;
    }

    public boolean isJpg() {
        return jpg;
    }

    public void setJpg(boolean jpg) {
        this.jpg = jpg;
    }

    public int getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(int tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getSaltadas() {
        return saltadas;
    }

    public void setSaltadas(int saltadas) {
        this.saltadas = saltadas;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public long gettProceso() {
        return tProceso;
    }

    public void settProceso(long tProceso) {
        this.tProceso = tProceso;
    }

}
