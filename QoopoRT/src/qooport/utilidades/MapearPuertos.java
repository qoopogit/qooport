/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import org.wetorrent.upnp.GatewayDevice;
import org.wetorrent.upnp.GatewayDiscover;
import org.wetorrent.upnp.PortMappingEntry;
import qooport.avanzado.QoopoRT;

/**
 *
 * @author alberto
 */
public class MapearPuertos extends Thread {

    private int PUERTO;
    private int tiempoEspera;

    private String getLocalIp(GatewayDevice d) {
        InetAddress iAddress = null;
        try {
            iAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
        }
        String hostname = null;
        if (iAddress != null) {
            hostname = iAddress.getHostAddress();
        }
        if (hostname != null) {
            return hostname;
        }
        try {
            URL controlUrl = new URL(d.getControlURL());
            Socket socket = new Socket(controlUrl.getHost(), controlUrl.getPort());
            InetAddress localAddress = socket.getLocalAddress();
            socket.close();
            return localAddress.toString();
        } catch (Exception e) {
            if (d != null) {
                return d.getLocalAddress().getHostAddress();
            }
        }
        return "N/A";
    }

    public MapearPuertos(int PUERTO, int tiempoEspera) {
        this.PUERTO = PUERTO;
        this.tiempoEspera = tiempoEspera;
    }

    @Override
    public void run() {
        try {
//            Logger logger = LogUtils.getLogger();
            QoopoRT.instancia.ponerEstado("Iniciando weupnp. Redireccionar puerto " + PUERTO);
            GatewayDiscover discover = new GatewayDiscover();
            QoopoRT.instancia.ponerEstado("Buscando dispositivos Gateway");
            discover.discover();
            GatewayDevice d = discover.getValidGateway();
            if (null != d) {
                QoopoRT.instancia.ponerEstado(String.format("Dispositivo Gateway encontrado .\n{0} ({1})", new Object[]{d.getModelName(), d.getModelDescription()}));
            } else {
                QoopoRT.instancia.ponerEstado("No  se encontro un dispositivo gateway valido.");
                return;
            }
//            InetAddress localAddress = d.getLocalAddress();
            String localAddress = getLocalIp(d);
            QoopoRT.instancia.ponerEstado("Usando ip local:" + localAddress);
            String externalIPAddress = d.getExternalIPAddress();
            QoopoRT.instancia.ponerEstado("Dirección externa : " + externalIPAddress);
            PortMappingEntry portMapping = new PortMappingEntry();
            QoopoRT.instancia.ponerEstado("Intentando mapear el puerto " + PUERTO);
            QoopoRT.instancia.ponerEstado("Preguntando si el puerto ya se encuentra mapeado");
            if (!d.getSpecificPortMappingEntry(PUERTO, "TCP", portMapping)) {
                QoopoRT.instancia.ponerEstado("Enviando Solicitud de mapeo");
//                if (d.addPortMapping(PUERTO, PUERTO, localAddress.getHostAddress(), "TCP", "test")) {
                if (d.addPortMapping(PUERTO, PUERTO, localAddress, "TCP", "test")) {
                    QoopoRT.instancia.ponerEstado(String.format("Mapeo satisfactorio", tiempoEspera));
//                    try {
//                        Thread.sleep(1000 * tiempoEspera);
//                    } catch (Exception ex) {
//                    }
                    //d.deletePortMapping(PUERTO, "TCP");
                    //QoopoRT.instancia.ponerEstado("Port mapping removed");
                    //QoopoRT.instancia.ponerEstado("Test SUCCESSFUL");
                } else {
                    QoopoRT.instancia.ponerEstado(" Mapeo no satisfactorio");
                }
            } else {
                QoopoRT.instancia.ponerEstado("Puerto ya se encuentra mapeado");
            }
            QoopoRT.instancia.ponerEstado("Stopping weupnp");
        } catch (Exception ex) {

        }
    }

}
