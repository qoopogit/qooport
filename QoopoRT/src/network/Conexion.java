/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import network.tcp.QSocket;
import network.tcp.QTcpSocket;
import network.udp.QUDPsocket;

/**
 *
 * @author alberto
 */
public class Conexion {

    /**
     * 1.- TCP; 2.- UDP
     */
    private int tipo;
    private QSocket tcpSocket; //conexions tcp
    private QUDPsocket udpSocket;
    private ObjectOutputStream salidaStream;
    private ObjectInputStream entradaStream;
    private String host;
    private int puerto;

    public Conexion(int tipo) {
        this.tipo = tipo;
    }

    public boolean isConectado() {
        switch (tipo) {
            case 1://tcp
                return tcpSocket.isConected();
            case 2://udp
//                return udpSocket.readInt();
                return true;
        }
        return false;
    }

    public Conexion(String host, int puerto, int tipo) throws IOException {
        constructor(host, puerto, tipo, false);
    }

    public Conexion(String host, int puerto, int tipo, boolean ssl) throws IOException {
        constructor(host, puerto, tipo, ssl);
    }

    private void constructor(String host, int puerto, int tipo, boolean ssl) throws IOException {
        this.host = host;
        this.puerto = puerto;
        this.tipo = tipo;
        switch (tipo) {
            case 1://tcp
                tcpSocket = new QTcpSocket(host, puerto, ssl);
                salidaStream = new ObjectOutputStream(tcpSocket.getOutputStream());
                entradaStream = new ObjectInputStream(tcpSocket.getInputStream());
                break;
            case 2://udp
                udpSocket = new QUDPsocket(host, puerto);
                break;
        }
    }

    public Conexion(QSocket tcpSocket) throws IOException {
        this.tcpSocket = tcpSocket;
        tipo = 1;
        salidaStream = new ObjectOutputStream(tcpSocket.getOutputStream());
        entradaStream = new ObjectInputStream(tcpSocket.getInputStream());
    }

    public Conexion(QUDPsocket udpSocket) {
        this.udpSocket = udpSocket;
        tipo = 2;
    }

    public int leerInt() throws IOException {
        switch (tipo) {
            case 1://tcp
                return entradaStream.readInt();
            case 2://udp
                return udpSocket.readInt();
        }
        return -1;
    }

    public long leerLong() throws IOException {
        switch (tipo) {
            case 1://tcp
                return entradaStream.readLong();
//                break;
            case 2://udp
                return udpSocket.readLong();
        }
        return -1;
    }

    public String leerString() throws IOException {
        switch (tipo) {
            case 1://tcp
                return entradaStream.readUTF();

            case 2://udp
                return udpSocket.readString();
        }
        return null;
    }

    public void cerrar() throws IOException {
        switch (tipo) {
            case 1://tcp
                tcpSocket.close();
                break;
            case 2://udp
                udpSocket.close();
                break;
        }
    }

    public int read(byte b[]) throws IOException {
        switch (tipo) {
            case 1://tcp
                return entradaStream.read(b);

//            case 2://udp
//                return udpSocket.readInt();
        }
        return -1;
    }

    public void write(byte b[], int off, int len) throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.write(b, off, len);
                break;
//            case 2://udp
//                return udpSocket.readInt();
        }
        flush();
    }

    public Object leerObjeto() throws IOException, ClassNotFoundException, Exception {
        switch (tipo) {
            case 1://tcp
                return entradaStream.readObject();
            case 2://udp
                return udpSocket.leerObjeto();
            default:
                System.out.println("NO ESTA DEFINIDO EL TIPO");
        }
        return null;
    }

    public InetAddress getInetAddress() {
        switch (tipo) {
            case 1://tcp
                return ((QTcpSocket) tcpSocket).getInetAddress();
            case 2://udp
                return udpSocket.getInetAddress();
        }
        return null;
    }

    public InetAddress getLocalAddress() {
        switch (tipo) {
            case 1://tcp
                return ((QTcpSocket) tcpSocket).getLocalAddress();

            case 2://udp
                return udpSocket.getLocalAddress();
        }
        return null;
    }

    public SocketAddress getRemoteSocketAddress() {
        switch (tipo) {
            case 1://tcp
                return ((QTcpSocket) tcpSocket).getRemoteSocketAddress();

            case 2://udp
                return udpSocket.getRemoteSocketAddress();
        }
        return null;
    }

    public void escribirObjeto(Object objeto) throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.writeObject(objeto);
                reset();//va liberando memoria ocupada por el outputstream
                break;
            case 2://udp
                udpSocket.escribirObjeto(objeto);
                break;
        }
        flush();
    }

    public void escribirInt(int valor) throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.writeInt(valor);
                break;
            case 2://udp
                udpSocket.escribirInt(valor);
                break;
        }
        flush();
    }

    public void escribirLong(Long valor) throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.writeLong(valor);
                break;
            case 2://udp
                udpSocket.escribirLong(valor);
                break;
        }
        flush();
    }

    public void escribirUTF(String valor) throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.writeUTF(valor);
                break;
            case 2://udp
                udpSocket.escribirString(valor);
                break;
        }
        flush();
    }

    public void reset() throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.reset();
                break;
//            case 2://udp
//                return udpSocket.readInt();
        }
    }

    public void flush() throws IOException {
        switch (tipo) {
            case 1://tcp
                salidaStream.flush();
                break;
//            case 2://udp
//                return udpSocket.readInt();
        }
    }

    public int getSendBufferSize() throws SocketException {
        switch (tipo) {
            case 1://tcp
                return ((QTcpSocket) tcpSocket).getSendBufferSize();
            case 2://udp
                return 1024;
        }
        return 1024;
    }

    public int getReceiveBufferSize() throws SocketException {
        switch (tipo) {
            case 1://tcp
                return ((QTcpSocket) tcpSocket).getReceiveBufferSize();
            case 2://udp
                return 1024;
        }
        return 1024;
    }

    public ObjectOutputStream getSalidaStream() {
        return salidaStream;
    }

    public void setSalidaStream(ObjectOutputStream salidaStream) {
        this.salidaStream = salidaStream;
    }

    public ObjectInputStream getEntradaStream() {
        return entradaStream;
    }

    public void setEntradaStream(ObjectInputStream entradaStream) {
        this.entradaStream = entradaStream;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getSSlInfo() {
        try {
            return tcpSocket.resumenSSL();
        } catch (Exception e) {

        }
        return "";
    }

}
