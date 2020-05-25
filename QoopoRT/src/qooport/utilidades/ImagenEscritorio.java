/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.awt.image.BufferedImage;

/**
 *
 * @author aigarcia
 */
public class ImagenEscritorio {

    private BufferedImage imagen;
    int cy;
    int cx;

    public ImagenEscritorio() {
    }

    public ImagenEscritorio(BufferedImage imagen, int cy, int cx) {
        this.imagen = imagen;
        this.cy = cy;
        this.cx = cx;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

}
