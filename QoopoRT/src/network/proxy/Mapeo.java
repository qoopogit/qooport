/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.proxy;

/**
 *
 * @author alberto
 */
public class Mapeo {

    private String hostLocal;
    private int puertoLocal;
    private String hostRemoto;
    private int puertoRemoto;

    public Mapeo(String hostLocal, int puertoLocal, String hostRemoto, int puertoRemoto) {
        this.hostLocal = hostLocal;
        this.puertoLocal = puertoLocal;
        this.hostRemoto = hostRemoto;
        this.puertoRemoto = puertoRemoto;
    }

    public Mapeo() {
    }

    public String getHostLocal() {
        return hostLocal;
    }

    public void setHostLocal(String hostLocal) {
        this.hostLocal = hostLocal;
    }

    public int getPuertoLocal() {
        return puertoLocal;
    }

    public void setPuertoLocal(int puertoLocal) {
        this.puertoLocal = puertoLocal;
    }

    public String getHostRemoto() {
        return hostRemoto;
    }

    public void setHostRemoto(String hostRemoto) {
        this.hostRemoto = hostRemoto;
    }

    public int getPuertoRemoto() {
        return puertoRemoto;
    }

    public void setPuertoRemoto(int puertoRemoto) {
        this.puertoRemoto = puertoRemoto;
    }

}
