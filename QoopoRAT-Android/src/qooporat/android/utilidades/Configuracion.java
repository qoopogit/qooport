/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.utilidades;
import java.io.Serializable;

/**
 *
 * @author alberto
 */
public class Configuracion implements Serializable {

    private boolean vistasPrevias;
    private String noIpUsuario;
    private String noIpPassword;
    private int segundos;
    private String rutaArchivo;
    private String version;
    private boolean actualizarserver;
    private boolean pedirArchivoOffline;

    public Configuracion() {
    }

    public boolean isVistasPrevias() {
        return vistasPrevias;
    }

    public void setVistasPrevias(boolean vistasPrevias) {
        this.vistasPrevias = vistasPrevias;
    }

    public String getNoIpUsuario() {
        return noIpUsuario;
    }

    public void setNoIpUsuario(String noIpUsuario) {
        this.noIpUsuario = noIpUsuario;
    }

    public String getNoIpPassword() {
        return noIpPassword;
    }

    public void setNoIpPassword(String noIpPassword) {
        this.noIpPassword = noIpPassword;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isActualizarserver() {
        return actualizarserver;
    }

    public void setActualizarserver(boolean actualizarserver) {
        this.actualizarserver = actualizarserver;
    }

    public boolean isPedirArchivoOffline() {
        return pedirArchivoOffline;
    }

    public void setPedirArchivoOffline(boolean pedirArchivoOffline) {
        this.pedirArchivoOffline = pedirArchivoOffline;
    }
}
