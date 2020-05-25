/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.linux;

import comunes.Punto;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class LinJnaUtils {

    private static X.Display display = new X.Display();

    public static String getForegroundWindowText() {
        try {
            return display.getActiveWindow().getTitle();
        } catch (X.X11Exception ex) {
            return "";
        }

    }

    public static boolean verificarVersion() {
        try {
            LibXFixes.XFixesFindDisplayTipo r = LibXFixes.INSTANCE.XFixesFindDisplay(display.getX11Display().getPointer());
            if (r != null) {
//                System.out.println("VERSION");
//                System.out.println("major version=" + r.major_version);
//                System.out.println("minor version=" + r.minor_version);
                if (r.major_version > 2) {
                    return true;
                }
            }

        } catch (Exception e) {

        }
        return false;
    }

    public static String getCursorType() {
        if (verificarVersion()) {
            LibXFixes.XFixesGetCursorNameTipo r2 = LibXFixes.INSTANCE.XFixesGetCursorName(display.getX11Display().getPointer());
            if (r2 != null) {
                System.out.println("diferente de nulo2");
                try {
                    System.out.println("name cursor=" + r2.name);
                    return r2.name;
                } catch (Exception e) {
                }
            } else {
                System.out.println("nulo");
            }
        }
        return "";
    }

    public static Punto getCursorPosicion() {
        Punto p = null;
        try {
            LibXFixes.XFixesGetCursorImageTipo r = LibXFixes.INSTANCE.XFixesGetCursorImage(display.getX11Display().getPointer());
            if (r != null) {
                p = new Punto(r.x, r.y);
//                System.out.println("X=" + r.x);
//                System.out.println("Y=" + r.y);
//                System.out.println("ancho=" + r.width);
//                System.out.println("alto=" + r.height);
//                System.out.println("xHot=" + r.x_hot);
//                System.out.println("yHot=" + r.y_hot);
//                System.out.println("tipo cursor=" + r.cursor_serial);
            }
        } catch (Exception e) {
            Point p2 = MouseInfo.getPointerInfo().getLocation(); //usa el robot de java
            p = new Punto(p2.x, p2.y);
        }
        return p;
    }

    public static BufferedImage getActualCursorImage() {
//        final int width = 32;
//        final int height = 32;
//        try {
//            //display.getX11Display().getPointer()                        
//            //byte[] datos= new byte[32*32];
//            //LibXFixes.XFixesGetCursorImageTipo r = new LibXFixes.XFixesGetCursorImageTipo();
//            //r.cursor_image=datos;
//            LibXFixes.XFixesGetCursorImageTipo r = LibXFixes.INSTANCE.XFixesGetCursorImage(display.getX11Display().getPointer());
//            if (r != null) {
//                System.out.println("diferente de nulo");
//                try {
//                    System.out.println("X=" + r.x);
//                    System.out.println("Y=" + r.y);
//                    System.out.println("ancho=" + r.width);
//                    System.out.println("alto=" + r.height);
//                    System.out.println("tipo cursor=" + r.cursor_serial);
//                    byte[] buf = new byte[2];
//                    int offset = 0;
//                    boolean seguir = true;
//                    System.out.println("comienzo a leer");
//                    
//                    
////                    ByteArrayOutputStream out = new ByteArrayOutputStream();
////                    while (seguir) {
////                        try {
////                            buf = r.cursor_image.getByteArray(offset, 2);
////                            System.out.println("leido parte");
////                            if (buf != null && buf.length > 0) {
////                                out.write(buf, offset, buf.length);
////                                offset += buf.length;
////                            } else {
////                                seguir = false;
////                            }
////                        } catch (Exception e) {
////
////                        }
////                    }
////                    System.out.println("leido todo");
////                    BufferedImage m = ImageIO.read(new ByteArrayInputStream(out.toByteArray()));
////                    out.flush();
////                    out.close();
//                    //System.out.println("tam=" + r.cursor_image.length);
////                    return m;
//                } catch (Exception e) {
//                }
//            } else {
//                System.out.println("cursor tomado es nulo");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;

    }

}
