package comunes;

import java.io.Serializable;
/**
 * Clase que representa una celda o bloque de la pantalla. 
 * Para la captura de pantalla se divide la pantalla en bloques y se realiza un senso de estas divisiones para detectar si hay cambios. Cuando hay un cambio, se envia solo las celdas cambiadas.
 * @author alberto
 */
public class PantallaBloque implements Serializable {

    private String nombre;
    private int x, y, ancho, alto;
    private byte[] datos;
    private int[] pixeles;
    private int tipo;//1 byte, 2 int
    // si es -1 no es copia de otro rectangulo sino q contiene datos, 
    //si es diferente de -1 este numero indica que ya existe una celda con el mismo valor en la posicion dada
    private int copia = -1;
    //si el bloque es el mismo de un bloque de la captura anterior
    private String nombreCopia = null;
    private long checksum;

    public PantallaBloque() {
    }

    public PantallaBloque(String nombre, int x, int y, int ancho, int alto, byte[] datos) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.datos = datos;
        tipo = 1;
        copia = -1;
        nombreCopia = null;
    }

    public PantallaBloque(String nombre, int x, int y, int ancho, int alto, int[] pixeles) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.pixeles = pixeles;
        tipo = 2;
        copia = -1;
        nombreCopia = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
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

    public int[] getPixeles() {
        return pixeles;
    }

    public void setPixeles(int[] pixeles) {
        this.pixeles = pixeles;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCopia() {
        return copia;
    }

    public void setCopia(int copia) {
        this.copia = copia;
    }

    public String getNombreCopia() {
        return nombreCopia;
    }

    public void setNombreCopia(String nombreCopia) {
        this.nombreCopia = nombreCopia;
    }

    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    public PantallaBloque limpiar() {
        PantallaBloque tmp;
        tmp = new PantallaBloque();
        tmp.setX(x);
        tmp.setY(y);
        tmp.setTipo(tipo);
        tmp.setNombre(nombre);
        tmp.setAncho(ancho);
        tmp.setAlto(alto);
//        tmp.setPixeles(null);
//        tmp.setDatos(null);
        tmp.setPixeles(pixeles);
        tmp.setDatos(datos);
        tmp.setChecksum(checksum);
        tmp.setCopia(copia);
        tmp.setNombreCopia(nombreCopia);
        return tmp;
    }

}
