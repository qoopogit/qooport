package plugin.jna;

import com.caoym.WinRobot;
import comunes.Interfaz;
import comunes.Punto;
import firnass.Firnass;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import plugin.jna.linux.LinJnaUtils;
import plugin.jna.windows.WInJnaUtil;
import plugin.jna.windows.WinCargarCursor;

public class JnaUtil implements Interfaz {

    private static final FamiliaSO so = getFamily();

    private static WinRobot robot;
    private static String OSArch = System.getProperty("os.arch").toLowerCase();

    private static FamiliaSO getFamily() {
        String str = System.getProperty("os.name");
        FamiliaSO localFamily;
        if (str.equalsIgnoreCase("freebsd")) {
            localFamily = FamiliaSO.FREEBSD;
        } else if (str.equalsIgnoreCase("openbsd")) {
            localFamily = FamiliaSO.OPENBSD;
        } else if (str.equalsIgnoreCase("mac os x")) {
            localFamily = FamiliaSO.OSX;
        } else if ((str.equalsIgnoreCase("solaris")) || (str.equalsIgnoreCase("sunos"))) {
            localFamily = FamiliaSO.SOLARIS;
        } else if (str.equalsIgnoreCase("linux")) {
            localFamily = FamiliaSO.LINUX;
        } else if (str.toLowerCase().startsWith("windows")) {
            localFamily = FamiliaSO.WINDOWS;
        } else {
            localFamily = FamiliaSO.UNSUPPORTED;
        }
        return localFamily;
    }

    private String getTituloVentanaActual() {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                return WInJnaUtil.getForegroundWindowText();
            }

            if (so.equals(FamiliaSO.LINUX)) {
                return LinJnaUtils.getForegroundWindowText();
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return "";
    }

    private Punto getPosicionCursor() {
        Punto p = null;
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                p = WInJnaUtil.getCursorPosicion();
            }

            if (so.equals(FamiliaSO.LINUX)) {
                p = LinJnaUtils.getCursorPosicion();
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return p;
    }

    private String getTipoCursor() {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                return WInJnaUtil.getCursorType();
            }

            if (so.equals(FamiliaSO.LINUX)) {
                return LinJnaUtils.getCursorType();
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return "";
    }

    private BufferedImage getCursorActual() {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                return WinCargarCursor.getActualCursorImage();
            }

            if (so.equals(FamiliaSO.LINUX)) {
                return LinJnaUtils.getActualCursorImage();
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return null;
    }

    private void bloquearEquipo() {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                WInJnaUtil.bloquearEquipo();
            }

            if (so.equals(FamiliaSO.LINUX)) {
                //LinJnaUtils.getForegroundWindowText();
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }

    }

    private BufferedImage getScreenShotFirnass(Rectangle cuadro) {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                //return WInJnaUtil.getScreenShot(cuadro);//accedo a las apis de windows                
                return Firnass.getScreenShot(cuadro);//uso el api de firnass para la pantalal
            }

            if (so.equals(FamiliaSO.LINUX)) {
                return Firnass.getScreenShot(cuadro);//uso el api de firnass para la pantalal
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return null;
    }

    private BufferedImage getScreenShotWinAPI(Rectangle cuadro) {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                return WInJnaUtil.getScreenShot(cuadro);//accedo a las apis de windows     
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return null;
    }

    private BufferedImage getScreenShotWinRobot(Rectangle cuadro) {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                if (robot == null) {
                    robot = new WinRobot();
                }
                if (robot != null) {
                    return robot.createScreenCapture(cuadro);
                }
            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
        return null;
    }

    private void lanzarAppEscaparIDSesion0(String cmd) {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                WInJnaUtil.lanzarAppEscaparIDSesion0(cmd);
            }

            if (so.equals(FamiliaSO.LINUX)) {

            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
    }

    private void instalarServicio(String cmd, String parameros, String nombreServicio) {
        try {
            if (so.equals(FamiliaSO.WINDOWS)) {
                WInJnaUtil.instalarServicio(cmd, parameros, nombreServicio, OSArch.contains("64"));
            }

            if (so.equals(FamiliaSO.LINUX)) {

            }
        } catch (Error ee) {
        } catch (Exception e) {

        }
    }

    @Override
    public void instanciar(Object... parametros) {

    }

    @Override
    public void set(int opcion, Object valor) {

    }

    @Override
    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                return getTituloVentanaActual();
            case 1:
                return getPosicionCursor();
            case 2:
                return getTipoCursor();
            case 3:
                return getCursorActual();
            case 4://nativo firnass
                return getScreenShotFirnass((Rectangle) parametros[0]);
            case 5: // nativo win robot
                return getScreenShotWinRobot((Rectangle) parametros[0]);
            case 6: // nativo win api
                return getScreenShotWinAPI((Rectangle) parametros[0]);
        }
        return null;
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                bloquearEquipo();
                break;
            case 2:
                lanzarAppEscaparIDSesion0((String) parametros[0]);
                break;
            case 3:
                instalarServicio((String) parametros[0], (String) parametros[1], (String) parametros[2]);
                break;
        }
    }

    private static enum FamiliaSO {
        FREEBSD, OPENBSD, OSX, SOLARIS, LINUX, WINDOWS, UNSUPPORTED;
    }

}
