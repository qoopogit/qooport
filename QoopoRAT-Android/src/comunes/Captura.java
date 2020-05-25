/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author alberto
 */
public class Captura implements Serializable {
    private int ancho;
    private int alto;
    private Date fecha;
    private List<PantallaBloque> bloques = new ArrayList<PantallaBloque>();
    public Captura() {
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
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public List<PantallaBloque> getBloques() {
        return bloques;
    }
    public void setBloques(List<PantallaBloque> bloques) {
        this.bloques = bloques;
    }
}
