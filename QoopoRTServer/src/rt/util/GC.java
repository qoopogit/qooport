package rt.util;

import comunes.Interfaz;

public class GC extends Thread implements Interfaz {

    public void instanciar(Object... parametros) {
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    sleep(10000);//cada 10 segundos
                } catch (Exception ex) {
                }
                UtilRT.gc();
            }
        } catch (Exception e) {

        }
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;

    }

    public void ejecutar(int opcion, Object... parametros) {

    }

}
