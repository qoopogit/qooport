/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.tcp;

import certificado.IniciarCertificado;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author alberto
 */
public class QTcpServerSocket implements QServerSocket {

    private ServerSocket serverSocket;

    public QTcpServerSocket(int port) throws IOException {
        crearSocket(port, false);
    }

    public QTcpServerSocket(int port, boolean ssl) throws IOException {
        crearSocket(port, ssl);
    }

    private void crearSocket(int port, boolean ssl) throws IOException {
        if (ssl) {
//            ServerSocketFactory basicSocketFactory = ServerSocketFactory.getDefault();
//            serverSocket = basicSocketFactory.createServerSocket(port);
// s is a TCP socket
            //SSLServerSocketFactory tlsSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocketFactory tlsSocketFactory = (SSLServerSocketFactory) IniciarCertificado.sc.getServerSocketFactory();
//            System.out.println(Arrays.toString(tlsSocketFactory.getSupportedCipherSuites()));
            serverSocket = tlsSocketFactory.createServerSocket(port);
        } else {
            this.serverSocket = new ServerSocket(port);
        }
    }

    public QSocket accept() throws IOException {
        try {
            Socket socket = this.serverSocket.accept();
            return new QTcpSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

}
