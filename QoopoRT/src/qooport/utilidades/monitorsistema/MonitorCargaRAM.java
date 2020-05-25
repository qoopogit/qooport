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
public class MonitorCargaRAM extends Thread {

    private Accion accion;
    private long intervaloMs;
    private boolean activo;

    public MonitorCargaRAM(Accion accion, long intervaloMs) {
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
        double ramLibre;
        double ram;
        while (activo) {
            try {
                Thread.sleep(intervaloMs);
            } catch (InterruptedException ex) {

            }
            ramLibre = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
            ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            if (accion != null) {
                accion.ejecutar(ramLibre, ram);
            }
        }
    }

}
