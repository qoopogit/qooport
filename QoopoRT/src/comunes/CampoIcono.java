package comunes;

import java.io.Serializable;

public class CampoIcono implements Serializable {

    private byte[] icono;
    private String nombre;

    public CampoIcono() {
    }

    public CampoIcono(byte[] icono, String nombre) {
        this.icono = icono;
        this.nombre = nombre;
    }

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CampoIcono{" + (icono == null ? "nulo" : icono.length) + "nombre=" + nombre + '}';
    }
}
