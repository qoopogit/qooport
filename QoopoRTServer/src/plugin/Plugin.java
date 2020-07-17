/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin;

import java.io.Serializable;
import java.util.List;

public class Plugin implements Serializable {

    protected String nombre;
    protected String version;
    protected String descripcion;
    protected String plataformas;
    protected String id;
    protected List<String> listaArchivo;//el string esta armado por 2 tokens separados por |nombre y el segundo 

    public Plugin() {
    }

    public Plugin(String id, String nombre, String version, String descripcion, String plataformas, List<String> listaArchivo) {
        this.id = id;
        this.nombre = nombre;
        this.version = version;
        this.descripcion = descripcion;
        this.plataformas = plataformas;
        this.listaArchivo = listaArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }

    public List<String> getListaArchivo() {
        return listaArchivo;
    }

    public void setListaArchivo(List<String> listaArchivo) {
        this.listaArchivo = listaArchivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
