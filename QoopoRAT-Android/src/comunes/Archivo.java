package comunes;
import java.io.Serializable;
public class Archivo
        implements Serializable {
    private String path;
    private String pathParent;
    private String nombre;
    private long length;
    private boolean carpeta;
    private long fecha;
    private boolean hidden;
    private String tipo;
    private byte[] icono;
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getPathParent() {
        return this.pathParent;
    }
    public void setPathParent(String pathParent) {
        this.pathParent = pathParent;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public long getLength() {
        return this.length;
    }
    public void setLength(long length) {
        this.length = length;
    }
    public boolean isCarpeta() {
        return this.carpeta;
    }
    public void setCarpeta(boolean carpeta) {
        this.carpeta = carpeta;
    }
    public long getFecha() {
        return this.fecha;
    }
    public void setFecha(long fecha) {
        this.fecha = fecha;
    }
    public boolean isHidden() {
        return this.hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public Archivo() {
    }
    public Archivo(String path, String pathParent, String nombre, long length, boolean carpeta, long fecha, boolean hidden) {
        this.path = path;
        this.pathParent = pathParent;
        this.nombre = nombre;
        this.length = length;
        this.carpeta = carpeta;
        this.fecha = fecha;
        this.hidden = hidden;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public byte[] getIcono() {
        return icono;
    }
    public void setIcono(byte[] icono) {
        this.icono = icono;
    }
}
