/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades.monitorsistema;

import comunes.Accion;
import java.lang.management.ManagementFactory;

/**
 *
 * @author aigarcia
 */
public class MonitorCargaCPU extends Thread {

    private Accion accion;
    private long intervaloMs;
    private boolean activo;

    public MonitorCargaCPU(Accion accion, long intervaloMs) {
        this.accion = accion;
        this.intervaloMs = intervaloMs;
    }

    public void iniciar() {
        activo = true;
        start();
    }

    public void detener() {
        activo = false;
    }

    @Override
    public void run() {
        double carCPU;
        double carCPU1;
        while (activo) {
            try {
                Thread.sleep(intervaloMs);
            } catch (InterruptedException ex) {

            }
            carCPU = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuLoad() * 100;
            carCPU1 = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad() * 100;
            if (accion != null) {
                accion.ejecutar(carCPU, carCPU1);
            }
        }
    }

}
