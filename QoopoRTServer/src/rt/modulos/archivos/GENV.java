package rt.modulos.archivos;

import comunes.Interfaz;
import java.io.File;
import java.util.LinkedList;
import network.Conexion;
import rt.util.CLRT;

//gestor envio
public class GENV extends Thread {

    private boolean ejecutar = false;
    private int actuales = 0;
    private final LinkedList<CDES> archivos = new LinkedList<CDES>();
    private Interfaz servicio;

    public GENV(Interfaz conex) {
//        this.setName("hilo-gestor-envio-" + UtilRT.getHiloId());
        this.servicio = conex;
    }

    public void detener() {
        ejecutar = false;
        try {
            sleep(300);
        } catch (InterruptedException ex) {

        }
        interrupt();
    }

    public void iniciar() {
        ejecutar = true;
        start();
    }

    public void restarDescarga() {
        actuales--;
    }

    public void agregarEnvio(String rutaArchivo, String rutaADescargarDelCliente, Long offset) {
        synchronized (archivos) {
            archivos.addFirst(new CDES(rutaArchivo, rutaADescargarDelCliente, offset));
        }
    }

    public void agregarEnvio(String rutaArchivo, String rutaADescargarDelCliente, String rutaAomitir, Long offset) {
        synchronized (archivos) {
            archivos.addFirst(new CDES(rutaArchivo, rutaADescargarDelCliente, rutaAomitir, offset));
        }
    }

    public void agregarEnvio(String rutaArchivo, String rutaADescargarDelCliente, Conexion conexion, int bufferSize, Long offset) {
        synchronized (archivos) {
            archivos.addFirst(new CDES(rutaArchivo, rutaADescargarDelCliente, conexion, bufferSize, offset));
        }
    }

    public void agregarEnvio(String rutaArchivo, String rutaADescargarDelCliente, String rutaAomitir, Conexion conexion, int bufferSize, Long offset) {
        synchronized (archivos) {
            archivos.addFirst(new CDES(rutaArchivo, rutaADescargarDelCliente, rutaAomitir, conexion, bufferSize, offset));
        }
    }

    @Override
    public void run() {
        File f;
        while (ejecutar) {
            synchronized (archivos) {
                if (!archivos.isEmpty()) {
                    if (actuales < 3) {
                        CDES cola = archivos.getLast();
                        f = new File(cola.getArchivo());
                        try {
                            if (f.exists()) {
                                if (!f.isDirectory()) {
                                    try {
                                        actuales++;
                                        Interfaz envArchiv = ((Interfaz) new CLRT().loadClass("rt.modulos.archivos.SAR").newInstance());
                                        if (cola.getConexion() == null) {
                                            envArchiv.instanciar(servicio, cola.getArchivo(), cola.getRuta(), cola.getRutaOmitir(), cola.getOffset());
                                        } else {
                                            envArchiv.instanciar(servicio, cola.getArchivo(), cola.getRuta(), cola.getRutaOmitir(), cola.getOffset(), cola.getConexion(), cola.getBufferSize());
                                        }
                                        envArchiv.ejecutar(0);
                                    } catch (Exception ex) {
                                    }
                                } else {
                                    for (File ff : f.listFiles()) {
                                        agregarEnvio(ff.getAbsolutePath(), cola.getRuta(), cola.getRutaOmitir(), 0L);
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                        archivos.removeLast();
                    }
                }
            }
            try {
                Thread.sleep(500);//reduce carga cpu
            } catch (Exception e) {
            }
        }
    }
}
