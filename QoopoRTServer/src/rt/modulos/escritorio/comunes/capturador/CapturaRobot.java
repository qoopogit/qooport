/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes.capturador;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CapturaRobot extends Capturador {

    @Override
    public BufferedImage capturar(Rectangle recuadro) {
        return robot.createScreenCapture(recuadro);
    }
}
