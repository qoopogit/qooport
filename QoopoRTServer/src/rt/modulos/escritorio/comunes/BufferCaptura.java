package rt.modulos.escritorio.comunes;

import comunes.Captura;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BufferCaptura {

    public static int saltadas = 0;
    private static final Map<String, Integer> LIMITES_CAPTURAS = new HashMap<String, Integer>();
    private static final Map<String, LinkedList<Captura>> BUFFER = new HashMap<String, LinkedList<Captura>>();

    public static void iniciarParametros(String idCliente, int limiteBuffer) {
        try {
            LIMITES_CAPTURAS.put(idCliente, limiteBuffer);
            BUFFER.put(idCliente, new LinkedList<Captura>());
        } catch (Exception e) {
        }
    }

    public static int getSize(String idCliente) {
        int size = 0;
        try {
//            synchronized (BUFFER) {
            size = BUFFER.get(idCliente).size();
//            }
        } catch (Exception e) {
        }
        return size;
    }

    public static void limpiar(String idCliente) {
        try {
//            synchronized (BUFFER) {
            if (BUFFER.containsKey(idCliente)) {
                BUFFER.get(idCliente).clear();
            }
//            }
            saltadas = 0;
        } catch (Exception e) {
        }
    }

    public static void agregar(String idCliente, Captura item) {
        if (item == null) {
            return;
        }
        try {
            if (getSize(idCliente) < LIMITES_CAPTURAS.get(idCliente)) {
                //synchronized (BUFFER) {
                BUFFER.get(idCliente).addLast(item);
                //}
                if (saltadas > 0) {
                    saltadas--;//disminuyo los saltadas
                }
            } else {
                saltadas++;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static Captura getCaptura(String id) {
        try {
            if (getSize(id) > 0) {
//                synchronized (BUFFER) {
                return BUFFER.get(id).removeFirst();
//                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
}
