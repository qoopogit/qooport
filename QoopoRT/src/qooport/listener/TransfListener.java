package qooport.listener;

import java.io.IOException;
import network.ConexionServer;

/**
 *
 * @author alberto
 */
public class TransfListener extends Listener {


    public TransfListener(int puerto, int tipoConexion, boolean version2, boolean ssl) throws IOException {
        this.conexion_servidor = new ConexionServer(tipoConexion, puerto, ssl);
        this.version2 = version2;
        CONECTADO = true;
        this.ssl = ssl;
    }

    @Override
    public void run() {
        do {
            try {
                this.conexion = this.conexion_servidor.aceptar();
                int comando = conexion.leerInt();
                if (conexion_servidor.getTipo() == ConexionServer.UDP) {
                    CONECTADO = false;// obliga a salir del loop, porq estan lelgando los comandos q le corresponden a la conexion
//                    System.out.println("TransListerer>> Se recibio comando " + comando);
                }
                procesar(comando, conexion, ssl);
            } catch (Exception ex) {
            }
        } while (CONECTADO);
    }

    public void stopPara() {
        CONECTADO = false;
        try {
            this.conexion_servidor.cerrar();
        } catch (Exception ex) {
        }
    }

}
