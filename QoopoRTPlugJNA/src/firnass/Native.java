package firnass;

import static plugin.jna.windows.LoaderService.copyFile;
import java.io.File;
import java.io.InputStream;

public class Native {

    public static String[] nombresLibs = new String[]{
        "libfirnass-x64.dll",
        "libfirnass-x64.so",
        "libfirnass.dll",
        "libfirnass.so"
    };

    public static boolean cargado = false;
    private static String nombreLib;

    public static native int[] getWindowsList();

    public static native String getWindowTitle(int paramInt);

    public static native int[] getWindowRect(int paramInt);

    public static native void bringWindowToTop(int paramInt);

    public static native void captureWindow(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt);

    public static native int getProcID(int paramInt);

    public static native int getParentProcID(int paramInt);

    private static final FamiliaSO so = getFamily();
    private static final String OSArch = System.getProperty("os.arch").toLowerCase();

    private static File carpeta;

    public static void extraer() {

        carpeta = new File("tmp");
        try {
            carpeta.mkdirs();
        } catch (Exception e) {
        }
        for (String item : nombresLibs) {
            try {
                File p = new File(carpeta, item);
                InputStream recurso = Native.class.getResourceAsStream("/extras/firnass/" + item);
                if (!p.exists()) {
                    copyFile(recurso, p);
//                    System.out.println("copiando a " + p.getAbsolutePath());
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }
    }

    static {
        extraer();
        nombreLib = "libfirnass";

        try {
//            if (jdField_e_of_type_Boolean) {
//                b = Native.cargado = 0;
//                return;
//            }
            if (OSArch.contains("64")) {
                nombreLib += "-x64";
            }

            if (so.equals(FamiliaSO.LINUX)) {
                nombreLib += ".so";
            } else if (so.equals(FamiliaSO.WINDOWS)) {
                nombreLib += ".dll";
            }
            cargado = false;
            File f = new File(carpeta, nombreLib);
//            System.out.println("se va a cargar:" + f.getAbsolutePath());
            System.load(f.getAbsolutePath());
            cargado = true;
//            System.out.println("se cargo ok");
            //cv.e(" library loaded: " + jdField_f_of_type_JavaLangString);
            //return;
        } catch (Exception ex) {
            cargado = false;

        }
    }

    private static enum FamiliaSO {
        FREEBSD, OPENBSD, OSX, SOLARIS, LINUX, WINDOWS, UNSUPPORTED;
    }

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
}
