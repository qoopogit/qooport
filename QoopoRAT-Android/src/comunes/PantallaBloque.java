/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.io.Serializable;
import java.util.Arrays;
/**
 *
 * @author alberto
 */
public class PantallaBloque implements Serializable {
    
    private String nombre;
    private int x;
    private int y;
    private byte[] datos;

    public PantallaBloque() {
    }

    public PantallaBloque(String nombre, int x, int y, byte[] datos) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.datos = datos;
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

    public boolean esDiferente(byte[] nuevosDatos) {
        if (datos.length != nuevosDatos.length) {
//            datos=nuevosDatos;
            return true;
        } else {
            // los bloques blanco sy negros tienen el mismo largo,compruebo si el color es le mismo
            return Arrays.hashCode(datos) != Arrays.hashCode(nuevosDatos);
        }
    }
}
