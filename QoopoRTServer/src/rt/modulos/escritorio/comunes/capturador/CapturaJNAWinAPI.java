/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes.capturador;

import comunes.Interfaz;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CapturaJNAWinAPI extends Capturador {

    @Override
    public BufferedImage capturar(Rectangle recuadro) {
        try {
            return (BufferedImage) ((Interfaz) servicio.get(7)).get(6, recuadro);
        } catch (Exception e) {

        }
        return null;
    }
}
