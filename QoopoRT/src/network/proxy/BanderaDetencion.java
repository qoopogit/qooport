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
public class BanderaDetencion {

    private boolean detener;

    public BanderaDetencion() {
    }

    public BanderaDetencion(boolean detener) {
        this.detener = detener;
    }

    public boolean isDetener() {
        return detener;
    }

    public void setDetener(boolean detener) {
        this.detener = detener;
    }

}
