package qooport.listener;

import java.io.IOException;
import network.ConexionServer;
import qooport.asociado.Asociado;
import qooport.asociado.v2.AsociadoV2;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.Protocolo;

/**
 *
 * @author alberto
 */
public class ServListener extends Listener {

    public ServListener(int puerto, int tipoConexion, boolean ssl) throws IOException {
        this.conexionServidor = new ConexionServer(tipoConexion, puerto, ssl);
        this.ssl = ssl;
        CONECTADO = true;
    }

    @Override
    public void run() {
        //************************************************************
        //          CONEXION TCP
        //************************************************************
        if (conexionServidor.getTipo() == ConexionServer.TCP) {
            do {
                try {
                    this.conexion = this.conexionServidor.aceptar();
                    int comando = conexion.leerInt();
                    if (!QoopoRT.instancia.contieneIpBloqueada(conexion.getInetAddress().getHostAddress())) {
                        procesar(comando, conexion, ssl);
                    } else {
                        QoopoRT.instancia.ponerEstado("Conexión rechazada :" + conexion.getInetAddress().getHostAddress() + "(" + conexion.getRemoteSocketAddress().toString() + ") en el puerto " + conexionServidor.getPuerto());
                    }
                    Thread.sleep(50);
                } catch (Exception ex) {
//                    ex.printStackTrace();
                }
            } while (CONECTADO);
        }
        //************************************************************
        //          CONEXION UDP
        //************************************************************
        if (conexionServidor.getTipo() == ConexionServer.UDP) {
            try {                
                this.conexion = this.conexionServidor.aceptar();
                do {
                    try {
                        int comando = conexion.leerInt();//este comando siempre llega como entero
                        System.out.println("UDP recibido comando " + comando);
                        //instancio un nuevo servidor pasando el
//                        if (comando == Protocolo.UDP_INICIAR) {
                            if (!QoopoRT.instancia.contieneIpBloqueada(conexion.getInetAddress().getHostAddress())) {                                
                                procesar(comando, conexion, ssl);           
                                QoopoRT.instancia.ponerEstado("Conexión UDP desde:" + conexion.getInetAddress().getHostAddress());// + "(" + conexion.getRemoteSocketAddress().toString() + ")");
                            } else {
                                QoopoRT.instancia.ponerEstado("Conexión rechazada :" + conexion.getInetAddress().getHostAddress());
                            }
//                        }
                    } catch (Exception ex) {
                    }
                } while (CONECTADO);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    public void stopPara() {
        CONECTADO = false;
        try {
            this.conexionServidor.cerrar();
        } catch (Exception ex) {
        }
    }
}
