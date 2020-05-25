/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import qooport.utilidades.SerializarUtil;

/**
 *
 * @author alberto
 */
public class QUDPsocket {

    private DatagramSocket socket;
    private int puerto;
    private InetAddress host;
    private byte[] mensaje_bytes = new byte[1024];
    private DatagramPacket paqueteLectura = new DatagramPacket(mensaje_bytes, mensaje_bytes.length);
    private DatagramPacket paqueteObjeto;

    public QUDPsocket() throws SocketException {
        socket = new DatagramSocket();
    }

    public QUDPsocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public QUDPsocket(int puerto) throws SocketException {
        this.puerto = puerto;
        socket = new DatagramSocket(puerto);
    }

    public QUDPsocket(String host, int puerto) throws SocketException, UnknownHostException {
        this.puerto = puerto;
        this.host = InetAddress.getByName(host);
        socket = new DatagramSocket();
    }

    public void close() {
        socket.close();
    }

    public String readString() throws IOException {
        String valor = null;
        socket.receive(paqueteLectura);
        tomarHost(paqueteLectura);
        byte[] datos = paqueteLectura.getData();
        ByteArrayInputStream bis = new ByteArrayInputStream(datos);
        ObjectInputStream entrada = new ObjectInputStream(bis);
        valor = entrada.readUTF();
        entrada.close();
        bis.close();
        return valor;
    }

    public void escribirString(String valor) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream salida = new ObjectOutputStream(bos);
        salida.writeUTF(valor);
        salida.flush();
        byte[] datos = bos.toByteArray();
        socket.send(new DatagramPacket(datos, datos.length, host, puerto));
        salida.close();
        bos.close();
    }

    public int readInt() throws IOException {
        int valor = -1;
        socket.receive(paqueteLectura);
        tomarHost(paqueteLectura);
        byte[] datos = paqueteLectura.getData();
        ByteArrayInputStream bis = new ByteArrayInputStream(datos);
        ObjectInputStream entrada = new ObjectInputStream(bis);
        valor = entrada.readInt();
        entrada.close();
        bis.close();
        return valor;
    }

    public void escribirInt(int valor) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream salida = new ObjectOutputStream(bos);
        salida.writeInt(valor);
        salida.flush();
        byte[] datos = bos.toByteArray();
        socket.send(new DatagramPacket(datos, datos.length, host, puerto));
        salida.close();
        bos.close();
    }

    public long readLong() throws IOException {
        long valor = -1;
        socket.receive(paqueteLectura);
        tomarHost(paqueteLectura);
        byte[] datos = paqueteLectura.getData();
        ByteArrayInputStream bis = new ByteArrayInputStream(datos);
        ObjectInputStream entrada = new ObjectInputStream(bis);
        valor = entrada.readLong();
        entrada.close();
        bis.close();
        return valor;
    }

    public void escribirLong(long valor) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream salida = new ObjectOutputStream(bos);
        salida.writeLong(valor);
        salida.flush();
        byte[] datos = bos.toByteArray();
        socket.send(new DatagramPacket(datos, datos.length, host, puerto));
        salida.close();
        bos.close();
    }

    public Object leerObjeto() throws IOException, ClassNotFoundException {
        int largo = readInt();
        byte[] buffer = new byte[largo];
        paqueteObjeto = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteObjeto);
        tomarHost(paqueteObjeto);
        byte[] data = paqueteObjeto.getData();
        return SerializarUtil.leerObjeto(new ByteArrayInputStream(data));
    }

    public void escribirObjeto(Object objeto) throws IOException {
        byte[] datos = SerializarUtil.convertirBytes(objeto);
        escribirInt(datos.length);
        socket.send(new DatagramPacket(datos, datos.length, host, puerto));
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }

    public InetAddress getInetAddress() {
        if (host == null) {
            return socket.getInetAddress();
        } else {
            return host;
        }
    }

    private void tomarHost(DatagramPacket paquete) {
        this.host = paquete.getAddress();
        puerto = paquete.getPort();
    }
}
