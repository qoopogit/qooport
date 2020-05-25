/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.cola;

/**
 *
 * @author alberto
 */
public class OrdenCarga {
    private String archivo;
    private String rutaRemota;

    public OrdenCarga() {
    }

    public OrdenCarga(String archivo, String rutaRemota) {
        this.archivo = archivo;
        this.rutaRemota = rutaRemota;
    }
    
    

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRutaRemota() {
        return rutaRemota;
    }

    public void setRutaRemota(String rutaRemota) {
        this.rutaRemota = rutaRemota;
    }
    
    
}
