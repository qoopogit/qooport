package comunes;

import java.io.Serializable;

public class WebCamItem implements Serializable {

    private int codigo;
    private String nombre;

    public WebCamItem(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public WebCamItem() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "";
    }
}
