package comunes;

import java.io.Serializable;

/**
 *
 * @author alberto
 */
public class CapturaOpciones implements Serializable {

    private int tipoDatos = 2;//1 bytes, 2 int
    private int tamBuffer;
    private int escala;
    private float calidad;
    private int ancho;
    private int alto;
    private int tipoEscala;
    private int monitor;
    private int tipoColor;
    private int origenCaptura;
    private int tipoEnvio;
    private int tipoHilos;
    private int algoritmo;
    private int anchoBloque;
    private int altoBloque;
    private boolean escalar;
    private boolean mostrarCursor;
    private boolean suavizado;
    private boolean enviarHilos;
    private boolean comprimir;
    private boolean enviarCursor;
    private boolean portapalesActivos;
    private boolean calidadAutomatica;
    private boolean convertirJpg;
    private boolean validarRepetidos;

    public CapturaOpciones() {
    }

    public CapturaOpciones(int tipoDatos, int tamBuffer, boolean escalar, int escala, float calidad, int ancho, int alto, int tipoEscala, int monitor, int tipoColor, int origenCaptura, int tipoEnvio, int tipoHilos, int algoritmo, int anchoBloque, int altoBloque,
            boolean mostrarCursor, boolean suavizado, boolean enviarHilos, boolean comprimir, boolean enviarCursor, boolean portapalesActivos, boolean calidadAutomatica, boolean convertirJpg, boolean validarRepetidos) {
        this.tipoDatos = tipoDatos;
        this.tamBuffer = tamBuffer;
        this.escalar = escalar;
        this.escala = escala;
        this.calidad = calidad;
        this.ancho = ancho;
        this.alto = alto;
        this.tipoEscala = tipoEscala;
        this.monitor = monitor;
        this.tipoColor = tipoColor;
        this.origenCaptura = origenCaptura;
        this.tipoEnvio = tipoEnvio;
        this.tipoHilos = tipoHilos;
        this.algoritmo = algoritmo;
        this.anchoBloque = anchoBloque;
        this.altoBloque = altoBloque;
        this.mostrarCursor = mostrarCursor;
        this.suavizado = suavizado;
        this.enviarHilos = enviarHilos;
        this.comprimir = comprimir;
        this.enviarCursor = enviarCursor;
        this.portapalesActivos = portapalesActivos;
        this.calidadAutomatica = calidadAutomatica;
        this.convertirJpg = convertirJpg;
        this.validarRepetidos = validarRepetidos;
    }

    public int getEscala() {
        return escala;
    }

    public void setEscala(int escala) {
        this.escala = escala;
    }

    public float getCalidad() {
        return calidad;
    }

    public void setCalidad(float calidad) {
        this.calidad = calidad;
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

    public int getTipoEscala() {
        return tipoEscala;
    }

    public void setTipoEscala(int tipoEscala) {
        this.tipoEscala = tipoEscala;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public int getTipoColor() {
        return tipoColor;
    }

    public void setTipoColor(int tipoColor) {
        this.tipoColor = tipoColor;
    }

    public int getOrigenCaptura() {
        return origenCaptura;
    }

    public void setOrigenCaptura(int origenCaptura) {
        this.origenCaptura = origenCaptura;
    }

    public int getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(int tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public int getTipoHilos() {
        return tipoHilos;
    }

    public void setTipoHilos(int tipoHilos) {
        this.tipoHilos = tipoHilos;
    }

    public int getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(int algoritmo) {
        this.algoritmo = algoritmo;
    }

    public boolean isMostrarCursor() {
        return mostrarCursor;
    }

    public void setMostrarCursor(boolean mostrarCursor) {
        this.mostrarCursor = mostrarCursor;
    }

    public boolean isSuavizado() {
        return suavizado;
    }

    public void setSuavizado(boolean suavizado) {
        this.suavizado = suavizado;
    }

    public boolean isEnviarHilos() {
        return enviarHilos;
    }

    public void setEnviarHilos(boolean enviarHilos) {
        this.enviarHilos = enviarHilos;
    }

    public boolean isComprimir() {
        return comprimir;
    }

    public void setComprimir(boolean comprimir) {
        this.comprimir = comprimir;
    }

    public boolean isEnviarCursor() {
        return enviarCursor;
    }

    public void setEnviarCursor(boolean enviarCursor) {
        this.enviarCursor = enviarCursor;
    }

    public boolean isPortapalesActivos() {
        return portapalesActivos;
    }

    public void setPortapalesActivos(boolean portapalesActivos) {
        this.portapalesActivos = portapalesActivos;
    }

    public boolean isCalidadAutomatica() {
        return calidadAutomatica;
    }

    public void setCalidadAutomatica(boolean calidadAutomatica) {
        this.calidadAutomatica = calidadAutomatica;
    }

    public boolean isConvertirJpg() {
        return convertirJpg;
    }

    public void setConvertirJpg(boolean convertirJpg) {
        this.convertirJpg = convertirJpg;
    }

    public int getAnchoBloque() {
        return anchoBloque;
    }

    public void setAnchoBloque(int anchoBloque) {
        this.anchoBloque = anchoBloque;
    }

    public int getAltoBloque() {
        return altoBloque;
    }

    public void setAltoBloque(int altoBloque) {
        this.altoBloque = altoBloque;
    }

    public int getTamBuffer() {
        return tamBuffer;
    }

    public void setTamBuffer(int tamBuffer) {
        this.tamBuffer = tamBuffer;
    }

    public boolean isEscalar() {
        return escalar;
    }

    public void setEscalar(boolean escalar) {
        this.escalar = escalar;
    }

    public int getTipoDatos() {
        return tipoDatos;
    }

    public void setTipoDatos(int tipoDatos) {
        this.tipoDatos = tipoDatos;
    }

    public boolean isValidarRepetidos() {
        return validarRepetidos;
    }

    public void setValidarRepetidos(boolean validarRepetidos) {
        this.validarRepetidos = validarRepetidos;
    }

    @Override
    public String toString() {
        return "CapturaOpciones{" + "tipoDatos=" + tipoDatos + ", tamBuffer=" + tamBuffer + ", escala=" + escala + ", calidad=" + calidad + ", ancho=" + ancho + ", alto=" + alto + ", tipoEscala=" + tipoEscala + ", monitor=" + monitor + ", tipoColor=" + tipoColor + ", origenCaptura=" + origenCaptura + ", tipoEnvio=" + tipoEnvio + ", tipoHilos=" + tipoHilos + ", algoritmo=" + algoritmo + ", anchoBloque=" + anchoBloque + ", altoBloque=" + altoBloque + ", escalar=" + escalar + ", mostrarCursor=" + mostrarCursor + ", suavizado=" + suavizado + ", enviarHilos=" + enviarHilos + ", comprimir=" + comprimir + ", enviarCursor=" + enviarCursor + ", portapalesActivos=" + portapalesActivos + ", calidadAutomatica=" + calidadAutomatica + ", convertirJpg=" + convertirJpg + ", validaPreExistentes=" + validarRepetidos + '}';
    }

}
