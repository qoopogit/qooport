package rt.util;

import comunes.Interfaz;

public class PING extends Thread implements Interfaz {

    private Interfaz servicio;
    private boolean ejecutar;

    public void instanciar(Object... parametros) {
        servicio = (Interfaz) parametros[0];
        ejecutar = true;
        start();
    }

    @Override
    public void run() {
        try {
            while (ejecutar && (Boolean) servicio.get(1)) {//mientras este conectado
                try {
                    sleep(10000);//cada 10 segundos
                } catch (Exception ex) {
                }
                try {
                    servicio.ejecutar(13);//ping
                } catch (Exception ex) {
                }
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
        switch (opcion) {
            case 1://detener;
                ejecutar = false;
                try {
                    interrupt();
                } catch (Exception e) {
                }
                break;
        }
    }
}
