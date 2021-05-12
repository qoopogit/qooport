package comunes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> parametros;//permite configurar varios parametros sin tener que cambiar la clase

    public Configuracion() {
    }

    public void inicializarParamertros() {
        parametros = new HashMap<String, Object>();
    }

    public boolean estaNuloParametros() {
        return parametros == null;
    }

    public boolean isVistasPrevias() {
        return vistasPrevias;
    }

    public void agregarParametro(String clave, Object valor) {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        parametros.put(clave, valor);
    }

    public Object obtenerParametro(String clave) {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        return parametros.get(clave);
    }

    public void vaciar() {
        if (estaNuloParametros()) {
            inicializarParamertros();
        }
        parametros.clear();
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
