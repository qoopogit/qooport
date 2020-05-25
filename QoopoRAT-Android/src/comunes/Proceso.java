package comunes;
import java.io.Serializable;
public class Proceso
        implements Serializable {
    private String nombre;
    private String pid;
    private String comando;
    private String usuario;
    
    private String[] datos;
    
    public Proceso() {
    }

    public Proceso(String[] datos) {
        this.datos = datos;
    }
    
    public Proceso(String nombre, String pid, String comando, String usuario) {
        this.nombre = nombre;
        this.pid = pid;
        this.comando = comando;
        this.usuario = usuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getComando() {
        return comando;
    }
    public void setComando(String comando) {
        this.comando = comando;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }


}
