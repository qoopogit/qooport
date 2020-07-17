/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes.capturador;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CapturaPrintScreen extends Capturador {

    private Clipboard clipboard;//usada para obtener la pantalla en el metodo de envio con printscreen

    @Override
    public BufferedImage capturar(Rectangle recuadro) {
        BufferedImage imageEscritorio = null;
        //captura usando print-screen
        try {
            if (clipboard == null) {
                clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            }
            robot.keyPress(KeyEvent.VK_PRINTSCREEN);
            robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
            robot.delay(10);
            imageEscritorio = (BufferedImage) clipboard.getData(DataFlavor.imageFlavor);
        } catch (Exception ex) {

        }
        return imageEscritorio;
    }
}
