/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.botonColumna;

import javax.swing.ImageIcon;

/**
 * Esta clase permite definir varios valores que indican al boton como
 * comportarse
 *
 * @author aigarcia
 */
public class ValorBotonColumna {

    private boolean activo;
    private ImageIcon icono;
    private String texto;

    public ValorBotonColumna() {
    }

    public ValorBotonColumna(boolean activo, ImageIcon icono, String texto) {
        this.activo = activo;
        this.icono = icono;
        this.texto = texto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
