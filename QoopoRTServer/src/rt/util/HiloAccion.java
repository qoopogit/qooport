package rt.util;

import comunes.Accion;

public class HiloAccion extends Thread {

    private boolean activo;
    private Accion accion;

    public HiloAccion(Accion accion) {
        this.accion = accion;
    }

    public void iniciar() {
        activo = true;
        start();
    }

    public void detener() {
        activo = false;
    }

    private void dormir() {
        try {
            Thread.sleep(20);// disminuye el uso de cpu, tamben pone límite de 100 fps
        } catch (Exception ie) {
        }
    }

    @Override
    public void run() {
        while (activo) {
            if (accion != null) {
                accion.ejecutar();
            } else {
                break;
            }
            dormir();
        }
    }
}
