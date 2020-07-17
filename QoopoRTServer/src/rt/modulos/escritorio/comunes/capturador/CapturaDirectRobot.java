/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes.capturador;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;

public class CapturaDirectRobot extends Capturador {

//    private ColorModel cm16 = new DirectColorModel(16, 0x7C00, 0x03E0, 0x001F);
//    private ColorModel cm24 = new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff, 0x0);
    private static final ColorModel CM32 = new DirectColorModel(32, 0xff0000, 0xff00, 0xff);// opaco
    private int[] pixels;
    private BufferedImage image;

    @Override
    public BufferedImage capturar(Rectangle recuadro) {
        try {
            if (directRobot != null) {
                pixels = directRobot.getRGBPixels(recuadro);
//              pixels = directRobot.getRGBPixels2(recuadro);
                image = new BufferedImage(CM32, Raster.createWritableRaster(CM32.createCompatibleSampleModel(recuadro.width, recuadro.height), new DataBufferInt(pixels, recuadro.width * recuadro.height), null), false, null);
                pixels = null;
                return image;
            }
        } catch (Exception e) {

        }
        return null;
    }

}
