/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firnass;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Firnass {

    private static BufferedImage bi = null;

    public static BufferedImage getScreenShot(Rectangle cuadro) {
        try {
            if (bi == null || (bi.getWidth() != cuadro.getWidth() && bi.getHeight() != cuadro.getHeight())) {
                bi = new BufferedImage(cuadro.width, cuadro.height, BufferedImage.TYPE_INT_RGB);
            }
            Native.captureWindow(0, cuadro.x, cuadro.y, cuadro.width, cuadro.height, ((DataBufferInt) bi.getRaster().getDataBuffer()).getData());
//            int[] arrayOfInt = ((DataBufferInt) bi.getRaster().getDataBuffer()).getData();
//tomo los datos en el arreglo de tipo entero
//            Native.captureWindow(0, cuadro.x, cuadro.y, cuadro.width, cuadro.height, arrayOfInt);

            //no se necesita volver a poner los datos porq se esta cambiando la direccion de memoria
            //vuelvo a poner los datos en el buffered image
            //bi.setData(Raster.createRaster(bi.getSampleModel(), new DataBufferInt(arrayOfInt, arrayOfInt.length), new Point()));
            return bi;
        } catch (Exception e) {
        }
        return null;
    }
}
