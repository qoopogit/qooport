package com;

import java.io.Serializable;

public class F implements Serializable {

    private String nombre;

    private byte[] contenido;

    public F() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

}
