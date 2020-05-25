package rt.modulos.escritorio.comunes;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class BufferPantalla {

    private static int LIMITE_CAPTURAS = 40;
    public static int saltadas = 0;
    private static final LinkedList<BufferedImage> BUFFER = new LinkedList<BufferedImage>();//buffer usado en el tipo de transmision rapida 1

    public static void iniciarParametros(int limiteBuffer) {
        LIMITE_CAPTURAS = limiteBuffer;
    }

    public static int getSize() {
        int size = 0;
        try {
            synchronized (BUFFER) {
                size = BUFFER.size();
            }
        } catch (Exception e) {
        }
        return size;
    }

    public static void limpiar() {
        try {
            synchronized (BUFFER) {
                BUFFER.clear();
            }
            saltadas = 0;
        } catch (Exception e) {
//            UtilRT.escribirLog("Buffer Limpiar", e);
        }
    }

    public static void agregar(BufferedImage item) {
        if (item == null) {
            return;
        }
        try {
            if (getSize() < LIMITE_CAPTURAS) {
                synchronized (BUFFER) {
                    BUFFER.addLast(item);
                }
                if (saltadas > 0) {
                    saltadas--;//disminuyo los saltadas
                }
            } else {
                saltadas++;
            }
        } catch (Exception e) {
        }
    }

    public static BufferedImage getCaptura() {
        try {
            BufferedImage tmp;
            if (BUFFER.size() > 0) {
                synchronized (BUFFER) {
                    tmp = BUFFER.removeFirst();
                }
                return tmp;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
