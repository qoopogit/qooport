/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.io.Serializable;
/**
 *
 * @author alberto
 */
public class GPSPosicion implements Serializable {
    private double latitud;
    private double longitud;
    private double altitud;
    private float velocidad;
    private float acurrancy;
    private long time;
    private String proveedor;
    public GPSPosicion() {
    }
    public GPSPosicion(double latitud, double longitud, double altitud, float velocidad, float acurrancy, long time, String proveedor) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.velocidad = velocidad;
        this.acurrancy = acurrancy;
        this.time = time;
        this.proveedor = proveedor;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getAltitud() {
        return altitud;
    }
    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }
    public float getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
    public float getAcurrancy() {
        return acurrancy;
    }
    public void setAcurrancy(float acurrancy) {
        this.acurrancy = acurrancy;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    @Override
    public String toString() {
        return "GPSPosicion{" + "latitud=" + latitud + ", longitud=" + longitud + ", altitud=" + altitud + ", velocidad=" + velocidad + ", acurrancy=" + acurrancy + ", time=" + time + ", proveedor=" + proveedor + '}';
    }
}
