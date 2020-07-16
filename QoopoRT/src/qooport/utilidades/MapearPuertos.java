/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import network.ConexionServer;
import org.bitlet.weupnp.GatewayDevice;
import org.bitlet.weupnp.GatewayDiscover;
import org.bitlet.weupnp.PortMappingEntry;
import qooport.avanzado.QoopoRT;

/**
 *
 * @author alberto
 */
public class MapearPuertos extends Thread {

    private int PUERTO;
    private int tipoConexion = ConexionServer.TCP;

    private GatewayDevice gateWay = null;

    public MapearPuertos(int PUERTO, int tipoConexion) {
        this.PUERTO = PUERTO;
        this.tipoConexion = tipoConexion;
    }

    private String getLocalIp(GatewayDevice d) {
//        InetAddress iAddress = null;
//        try {
//            iAddress = InetAddress.getLocalHost();
//        } catch (UnknownHostException ex) {
//        }
//        String hostname = null;
//        if (iAddress != null) {
//            hostname = iAddress.getHostAddress();
//        }
//        if (hostname != null) {
//            return hostname;
//        }
        try {
            URL controlUrl = new URL(d.getControlURL());
            Socket socket = new Socket(controlUrl.getHost(), controlUrl.getPort());
            InetAddress localAddress = socket.getLocalAddress();
            socket.close();
//            System.out.println("Local-1:" + localAddress.getHostAddress());
//            System.out.println("Local-2:" + localAddress.getHostName());
//            System.out.println("Local-3:" + localAddress.getCanonicalHostName());
//            System.out.println("Local-4:" + localAddress.toString());
            return localAddress.getHostAddress();
        } catch (Exception e) {
            if (d != null) {
                return d.getLocalAddress().getHostAddress();
            }
        }
        return "N/A";
    }

    @Override
    public void run() {
        try {
            String tipoPuerto = "TCP";
            if (tipoConexion == ConexionServer.UDP) {
                tipoPuerto = "UDP";
            }
            QoopoRT.instancia.ponerEstado("  Iniciando weupnp. Redireccionar puerto " + PUERTO);
            GatewayDiscover discover = new GatewayDiscover();
            QoopoRT.instancia.ponerEstado("     Buscando dispositivos Gateway");
            discover.discover();
            gateWay = discover.getValidGateway();
            if (gateWay != null) {
                QoopoRT.instancia.ponerEstado("     " + String.format("Dispositivo Gateway encontrado. [%s] [%s]", new Object[]{gateWay.getModelName(), gateWay.getModelDescription()}));
            } else {
                QoopoRT.instancia.ponerEstado("  <<   No  se encontro un dispositivo gateway valido.   >>");
                return;
            }
            String localAddress = getLocalIp(gateWay);
            String externalIPAddress = gateWay.getExternalIPAddress();
            QoopoRT.instancia.ponerEstado("     IP Local   : " + localAddress);
            QoopoRT.instancia.ponerEstado("     IP Externa : " + externalIPAddress);
            PortMappingEntry portMapping = new PortMappingEntry();
//            QoopoRT.instancia.ponerEstado("     Intentando mapear el puerto " + PUERTO);
//            QoopoRT.instancia.ponerEstado("     Preguntando si el puerto ya se encuentra mapeado");
            if (gateWay.getSpecificPortMappingEntry(PUERTO, tipoPuerto, portMapping)) {
//                QoopoRT.instancia.ponerEstado("     Puerto ya se encuentra mapeado. Se procede a actualizar");
                gateWay.deletePortMapping(PUERTO, tipoPuerto);//se elimina para volver a agregar
            }
//            QoopoRT.instancia.ponerEstado("     Enviando Solicitud de mapeo");
//                if (d.addPortMapping(PUERTO, PUERTO, localAddress.getHostAddress(), "TCP", "test")) {
            if (gateWay.addPortMapping(PUERTO, PUERTO, localAddress, "TCP", "QoopoRT-" + PUERTO)) {
                QoopoRT.instancia.ponerEstado("     Mapeo satisfactorio");
            } else {
                QoopoRT.instancia.ponerEstado("      Mapeo no satisfactorio");
            }
        } catch (Exception ex) {

        }
    }

    public void liberarMap() {
        try {
            String tipoPuerto = "TCP";
            if (tipoConexion == ConexionServer.UDP) {
                tipoPuerto = "UDP";
            }
            if (gateWay != null) {
                gateWay.deletePortMapping(PUERTO, tipoPuerto);
            }
        } catch (Exception e) {

        }
    }
}
