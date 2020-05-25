package qooport.listener;

import java.io.IOException;
import network.Conexion;
import network.ConexionServer;
import qooport.asociado.Asociado;
import qooport.asociado.v1.AsociadoV1;
import qooport.asociado.v2.AsociadoV2;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.Protocolo;

/**
 *
 * @author alberto
 */
public class ServListener extends Thread {

    private ConexionServer conexion_servidor;
    private Conexion conexion;
    public boolean CONECTADO = true;
    private boolean version2;
    private boolean ssl;

    public ServListener(int puerto, int tipoConexion, boolean version2, boolean ssl) throws IOException {
        this.conexion_servidor = new ConexionServer(tipoConexion, puerto, ssl);
        this.ssl = ssl;
        this.version2 = version2;
        CONECTADO = true;
    }

    @Override
    public void run() {
        //************************************************************
        //          CONEXION TCP
        //************************************************************
        if (conexion_servidor.getTipo() == ConexionServer.TCP) {
            do {
                try {
                    this.conexion = this.conexion_servidor.aceptar();
                    if (!QoopoRT.instancia.contieneIpBloqueada(conexion.getInetAddress().getHostAddress())) {
                        if (version2) {
                            Asociado servidor = new AsociadoV2(this.conexion, 1, ssl);
                            servidor.start();
                        } else {
                            Asociado servidor = new AsociadoV1(this.conexion, 1, ssl);
                            servidor.start();
                        }
                        QoopoRT.instancia.ponerEstado("Conexión TCP desde :" + conexion.getInetAddress().getHostAddress() + "(" + conexion.getRemoteSocketAddress().toString() + ") en el puerto " + conexion_servidor.getPuerto());
                    } else {
                        QoopoRT.instancia.ponerEstado("Conexión rechazada :" + conexion.getInetAddress().getHostAddress() + "(" + conexion.getRemoteSocketAddress().toString() + ") en el puerto " + conexion_servidor.getPuerto());
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
        if (conexion_servidor.getTipo() == ConexionServer.UDP) {
            try {
                this.conexion = this.conexion_servidor.aceptar();
                do {
                    try {
                        int comando = conexion.leerInt();//este comando siempre llega como entero
                        //instancio un nuevo servidor pasando el
                        if (comando == Protocolo.UDP_INICIAR) {
                            if (!QoopoRT.instancia.contieneIpBloqueada(conexion.getInetAddress().getHostAddress())) {
                                if (version2) {
                                    Asociado servidor = new AsociadoV2(this.conexion, 1, ssl);
                                    servidor.start();
                                } else {
                                    Asociado servidor = new AsociadoV1(this.conexion, 1, ssl);
                                    servidor.start();
                                }
                                CONECTADO = false;
                                QoopoRT.instancia.ponerEstado("Conexión UDP desde:" + conexion.getInetAddress().getHostAddress());// + "(" + conexion.getRemoteSocketAddress().toString() + ")");
                            } else {
                                QoopoRT.instancia.ponerEstado("Conexión rechazada :" + conexion.getInetAddress().getHostAddress());
                            }
                        }
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }
                } while (CONECTADO);
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }

    }

    public void stopPara() {
        CONECTADO = false;
        try {
            this.conexion_servidor.cerrar();
        } catch (Exception ex) {
        }
    }

//    public ServerSocket getP_escucha() {
//        return p_escucha;
//    }
//
//    public void setP_escucha(ServerSocket p_escucha) {
//        this.p_escucha = p_escucha;
//    }
//    public Socket getP_conexion() {
//        return p_conexion;
//    }
//
//    public void setP_conexion(Socket p_conexion) {
//        this.p_conexion = p_conexion;
//    }
}
