/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.tcp;

import certificado.IniciarCertificado;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author alberto
 */
public class QTcpSocket implements QSocket {

    private Socket socket;

    public QTcpSocket(Socket socket) {
        this.socket = socket;
    }

    public QTcpSocket(String host, int port) throws UnknownHostException, IOException {
        conexion(host, port, false);
    }

    public QTcpSocket(String host, int port, boolean ssl) throws IOException {
        conexion(host, port, ssl);
    }

    private void conexion(String host, int port, boolean ssl) throws IOException {
        if (ssl) {
//            SocketFactory basicSocketFactory = SocketFactory.getDefault();
//            socket = basicSocketFactory.createSocket(host, port);
// s is a TCP socket
            //SSLSocketFactory tlsSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocketFactory tlsSocketFactory = (SSLSocketFactory) IniciarCertificado.sc.getSocketFactory();
            //modo 1
            socket = tlsSocketFactory.createSocket(host, port);
            ((SSLSocket) socket).startHandshake();
        } else {
            this.socket = new Socket(host, port);
        }
    }

    public void close() throws IOException {
        this.socket.close();
    }

    public InetAddress getInetAddress() {
        return socket.getInetAddress();
    }

    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    public int getSendBufferSize() throws SocketException {
        return socket.getSendBufferSize();
    }

    public int getReceiveBufferSize() throws SocketException {
        return socket.getReceiveBufferSize();
    }

    @Override
    public boolean isConected() {
        return socket.isConnected();
    }

    @Override
    public String resumenSSL() {
        try {

            try {

                SSLSession session = ((SSLSocket) socket).getSession();
                java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

                String tmp = "";
                for (Certificate cert : servercerts) {

//                    mylist.add(servercerts[i]);
//                    System.out.println("Informacion certificado:");
////
//                    System.out.println("Tipo=" + cert.getType());
//                    System.out.println("Algoritmo=" + cert.getPublicKey().getAlgorithm());
//                    System.out.println("Formato=" + cert.getPublicKey().getFormat());
////
//                    System.out.println("Resumen:" + cert.getPublicKey().toString().split("modulus")[0]);
//                    System.out.println("to string:" + cert.getPublicKey().toString());
                    tmp = cert.getPublicKey().toString().split("modulus")[0] + " (" + cert.getType() + ")";
                    return tmp.replaceAll("\n", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
        return "";
    }

}
