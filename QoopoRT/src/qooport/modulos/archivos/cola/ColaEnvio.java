/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.cola;

import java.io.File;
import java.util.LinkedList;
import qooport.asociado.Asociado;

/**
 *
 * @author alberto
 */
public class ColaEnvio extends Thread {

    private int maximo=Integer.MAX_VALUE;
    private long tam;
    private long actual;
    private boolean ejecutar = false;
    private int actuales = 0;
    private final LinkedList<OrdenCarga> archivos = new LinkedList<OrdenCarga>();
    private Asociado asociado;

    public ColaEnvio(Asociado asociado) {
//        this.setName("hilo-gestor-envio-" + UtilRT.getHiloId());
        this.asociado = asociado;
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

    public void restarEnvio() {
        actuales--;
    }

    public void agregarEnvio(String rutaArchivo, String rutaADescargar) {
        synchronized (archivos) {
            archivos.addFirst(new OrdenCarga(rutaArchivo, rutaADescargar));
        }
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }
    
    

    @Override
    public void run() {
        File f;
        while (ejecutar) {
            synchronized (archivos) {
                if (!archivos.isEmpty()) {
                    if (actuales < maximo) {
                        OrdenCarga cola = archivos.getLast();
                        f = new File(cola.getArchivo());
                        try {
                            if (f.exists()) {
                                if (!f.isDirectory()) {
                                    try {
                                        actuales++;
                                        asociado.enviarArchivo(cola.getArchivo(), cola.getRutaRemota());
                                    } catch (Exception ex) {
                                    }
                                } else {
                                    asociado.crearCarpeta(f.getName(), cola.getRutaRemota());
                                    for (File ff : f.listFiles()) {
                                        agregarEnvio(ff.getAbsolutePath(), cola.getRutaRemota() + "/" + f.getName());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
