/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.windows;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author aigarcia
 */
public class WinCargarCursor {

    private static void dibujaCursorActual(BufferedImage image, int diFlags) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            User32 user32 = User32.INSTANCE;
            Gdi32 gdi32 = Gdi32.INSTANCE;

            User32.CURSORINFO ci = new User32.CURSORINFO();
            ci.cbSize = new WinDef.DWORD((long) ci.size());
            user32.GetCursorInfo(ci);

            Pointer hIcon = null;
            if (ci.hCursor == null) {
                //la flechita
                hIcon = user32.LoadCursorW(Pointer.NULL, User32.IDC_ARROW);
            } else {
                hIcon = ci.hCursor.getPointer();
            }

            Pointer hdcn;
            Pointer hdc;
            Pointer bitmap;

            //puntero en blanco y  negro
            //        Pointer hdc = gdi32.CreateCompatibleDC(Pointer.NULL);
            //        Pointer bitmap = gdi32.CreateCompatibleBitmap(hdc, width, height);
            // puntero con color -------------------------------
            //obtiene le hdc de la pantalla, esto evita la paleta monocromatica
            WinDef.HDC dc = user32.GetDC(null);
            if (dc != null) {
                hdcn = dc.getPointer();
                hdc = gdi32.CreateCompatibleDC(hdcn);
                bitmap = gdi32.CreateCompatibleBitmap(hdcn, width, height);
            } else {
                hdc = gdi32.CreateCompatibleDC(Pointer.NULL);
                bitmap = gdi32.CreateCompatibleBitmap(hdc, width, height);
            }

            gdi32.SelectObject(hdc, bitmap);

            user32.DrawIconEx(hdc, 0, 0, hIcon, width, height, 0, Pointer.NULL, diFlags);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < width; y++) {
                    int rgb = gdi32.GetPixel(hdc, x, y);
                    image.setRGB(x, y, rgb);
                }
            }

            gdi32.DeleteObject(bitmap);
            gdi32.DeleteDC(hdc);
        } catch (Exception e) {
        }
    }

    public static BufferedImage getActualCursorImage() {
        final int width = 32;
        final int height = 32;
        try {
//            JnaUtil util = new JnaUtil();
//            String curAc = util.getTipoCursor();
            String curAc = WInJnaUtil.getCursorType();
            if (curAc == null || !curAc.equals("0x10005")) {
                //si es el cursor de texto, su mascara es diferente
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                dibujaCursorActual(image, User32.DI_NORMAL | User32.DI_DEFAULTSIZE);
                BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                dibujaCursorActual(mask, User32.DI_MASK | User32.DI_DEFAULTSIZE);
                applyMask(image, mask);
                return image;
            } else {//cursor de edicion de texto, no tiene mascara
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                dibujaCursorActual(image, User32.DI_NORMAL | User32.DI_DEFAULTSIZE);
                image = cambiarBNYhaceTransparenteBlanco(image);
                return image;
            }
        } catch (Exception e) {
            return null;
        }

    }

    private static BufferedImage cambiarBNYhaceTransparenteBlanco(BufferedImage image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage resultado = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            int rbN = Color.BLACK.getRGB();
            int rbB = Color.WHITE.getRGB();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = image.getRGB(x, y);
                    if (color == rbB) {
                        resultado.setRGB(x, y, rbN);
                    }
                }
            }
            return resultado;
        } catch (Exception e) {
            return image;
        }
    }

    private static void applyMask(BufferedImage image, BufferedImage mask) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int masked = mask.getRGB(x, y);
                    if ((masked & 0x00FFFFFF) == 0) {
                        int rgb = image.getRGB(x, y);
                        rgb = 0xFF000000 | rgb;
                        image.setRGB(x, y, rgb);
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
