/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import network.tcp.QServerSocket;
import network.tcp.QSocket;
import network.tcp.QTcpServerSocket;
import network.udp.QUDPsocket;

/**
 *
 * @author alberto
 */
public class ConexionServer {

    public static final int TCP = 1;
    public static final int UDP = 2;
    /**
     * 1.- TCP; 2.- UDP
     */
    private int tipo;
    private QServerSocket tcpSocket; //conexions tcp
    private QUDPsocket udpSocket;

    private int puerto;

    public ConexionServer(int tipo, int puerto) throws IOException {
        constructor(tipo, puerto, false);
    }

    public ConexionServer(int tipo, int puerto, boolean ssl) throws IOException {
        constructor(tipo, puerto, ssl);
    }

    public void constructor(int tipo, int puerto, boolean ssl) throws IOException {
        this.tipo = tipo;
        this.puerto = puerto;
        switch (tipo) {
            case 1://tcp
                tcpSocket = new QTcpServerSocket(puerto, ssl);
                break;
            case 2://udp
                udpSocket = new QUDPsocket(puerto);
                break;
        }
    }

    public ConexionServer(QServerSocket tcpSocket) {
        this.tcpSocket = tcpSocket;
        tipo = 1;
    }

    public ConexionServer(QUDPsocket udpSocket) {
        this.udpSocket = udpSocket;
        tipo = 2;
    }

    public void cerrar() {
        try {
            switch (tipo) {
                case 1://tcp
                    ((QTcpServerSocket) tcpSocket).close();
                    break;
                case 2://udp
                    udpSocket.close();
                    break;
            }
        } catch (IOException ex) {

        }
    }

    public Conexion aceptar() {
        Conexion con = null;
        try {
            switch (tipo) {
                case 1://tcp
                    QSocket socket = tcpSocket.accept();
                    con = new Conexion(socket);
                    break;
                case 2://udp
                    con = new Conexion(udpSocket);
                    break;
            }

        } catch (IOException ex) {

        }
        return con;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

}
