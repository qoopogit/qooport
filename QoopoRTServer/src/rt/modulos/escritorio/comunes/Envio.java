package rt.modulos.escritorio.comunes;

import comunes.Captura;
import comunes.Interfaz;
import network.Conexion;
import rt.util.UtilRT;

public class Envio extends Thread implements Interfaz {

    private Interfaz escritorioRemoto;
    private String idServicio;
    private Conexion conexion;
    private boolean activo;
    private long tEnvio = 0;
    private boolean comprimir = false;

    public Envio() {

    }

    public void instanciar(Object... parametros) {

    }

    private void iniciar() {
        activo = true;
        start();
    }

    private void detener() {
        activo = false;
        BufferCaptura.limpiar(idServicio);
        interrupt();
    }

    @Override
    public void run() {
        while (activo) {
            try {
                enviar(BufferCaptura.getCaptura(idServicio));
                UtilRT.dormir(5);// disminuye el uso de cpu, tamben pone límite de 100 fps
            } catch (OutOfMemoryError e) {
                UtilRT.gc();
            } catch (Exception e) {
            }
        }
    }

    private void enviar(Captura captura) {
        long tInicio = System.currentTimeMillis();
        try {
            if (captura == null) {
                return;
            }
            //si no hay bloques no envia nada
            if (captura.getBloques() == null || captura.getBloques().isEmpty()) {
                captura = null;
                return;
            }
            captura.settEnvio(tEnvio);//envia el tiempo de envio anterior
            if (comprimir) {
                conexion.escribirObjeto(UtilRT.comprimirObjeto(captura));//con compresion
            } else {
                conexion.escribirObjeto(UtilRT.convertirBytes(captura));//sin compresion
            }
        } catch (Exception e) {
        }
        long tFin = System.currentTimeMillis();
        tEnvio = (tFin - tInicio);
        captura = null;
    }

    public void set(int opcion, Object valor) {
        switch (opcion) {
            case 0:
                this.conexion = (Conexion) valor;
                break;
            case 7:
                this.escritorioRemoto = (Interfaz) valor;
                break;
            case 1:
                idServicio = (String) valor;
                break;
            case 2:
                comprimir = (Boolean) valor;
                break;
        }
    }

    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                return this.conexion;
            case 7:
                return escritorioRemoto;
            case 1:
                return idServicio;
            case 2:
                return comprimir;
        }
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
            case 1:
                detener();
                break;
        }
    }
}
