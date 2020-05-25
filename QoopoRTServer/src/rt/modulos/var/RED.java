package rt.modulos.var;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import comunes.Interfaz;
import rt.util.Protocolo;

//explorar la red
public class RED extends Thread {

    private Interfaz servicio;
    private boolean activo;
    private int timeout = 2000;
    private static int ordenEnvio = 0;

    public RED() {
    }

    public void instanciar(Interfaz servicio) {
        this.servicio = servicio;
        activo = true;
    }

    public void detener() {
        activo = false;
        try {
            interrupt();
        } catch (Exception e) {

        }
    }

    public boolean estaDetenido() {
        return isInterrupted();
    }

    public void iniciar() {
        start();
    }

    private void enviar(String col1, String col2) {
        synchronized (servicio) {
            ordenEnvio++;
            servicio.ejecutar(3, Protocolo.GET_LISTA_EQUIPOS_RED, String.valueOf(ordenEnvio) + "&&" + col1 + "&&" + col2);
        }
    }

    @Override
    public void run() {
//        setName("hilo-RED");
        try {
            ordenEnvio = 0;
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface network = (NetworkInterface) e.nextElement();
                Enumeration ee = network.getInetAddresses();
                if (!network.isLoopback()) {//evito las interfaces loopback
                    enviar("=== Interfaz " + network.getDisplayName(), "Virtual:" + network.isVirtual());
                    while (ee.hasMoreElements()) {
                        ExecutorService lanzador = Executors.newFixedThreadPool(10);         //numero de hilos simultaneos
                        InetAddress ipinet = (InetAddress) ee.nextElement();
                        if (ipinet instanceof Inet4Address) {
                            String ip = ipinet.getHostAddress();
                            String subnet = ip.substring(0, ip.lastIndexOf("."));
                            enviar("==> Inicio red:" + subnet + ".* Ip actual:", ip);
                            for (int i = 0; i < 256; i++) {
                                lanzador.execute(new Hilo(subnet + "." + String.valueOf(i), timeout));
                            }
                        }
                        /*{
                         String ip = ipinet.getHostAddress();
                         enviar("==> Red omitida ", "Ip actual:" + ip);
                         }*/
                        if (!activo) {
                            break;
                        }
                        lanzador.shutdown();
                        lanzador.awaitTermination(10, TimeUnit.MINUTES);
                    }
                }
            }
            //lanzador.shutdown();
            //lanzador.awaitTermination(15, TimeUnit.MINUTES);
            enviar("Proceso", "Terminado");

        } catch (Exception ex) {
            enviar("ERROR", ex.getMessage());
            servicio.ejecutar(6, "Error al explorar red" + ex.getMessage());//enviar mensaje
            //servicio.enviarMensaje("Error al explorar red" + ex.getMessage());
        }
    }

    class Hilo extends Thread {

        private String ip;
        private int timeout;

        public Hilo(String ip, int timeout) {
            this.ip = ip;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                if (InetAddress.getByName(ip).isReachable(timeout)) {
                    enviar(ip, InetAddress.getByName(ip).getCanonicalHostName());
                }
            } catch (Exception ex) {
            }
        }
    }
}
