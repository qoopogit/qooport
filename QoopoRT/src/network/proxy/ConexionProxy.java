/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase representa una conexion del proxy. Consta de 2 conexiones. -- --
 * Origen.- Es la conexion origne, la que solicito una conexion al proxy --
 * Destino.- Es la conexion destino, la que abre el proxy para redirigir la
 * conexión
 *
 * @author aigarcia
 */
public class ConexionProxy {

    private SocketChannel conexionOrigen;
    private SocketChannel conexionDestino;

    public ConexionProxy() {
    }

    public ConexionProxy(SocketChannel conexionOrigen, SocketChannel conexionDestino) {
        this.conexionOrigen = conexionOrigen;
        this.conexionDestino = conexionDestino;
    }

    public SocketChannel getConexionOrigen() {
        return conexionOrigen;
    }

    public void setConexionOrigen(SocketChannel conexionOrigen) {
        this.conexionOrigen = conexionOrigen;
    }

    public SocketChannel getConexionDestino() {
        return conexionDestino;
    }

    public void setConexionDestino(SocketChannel conexionDestino) {
        this.conexionDestino = conexionDestino;
    }

    public void cerrar() {
        try {
            conexionOrigen.close();
        } catch (Exception e) {

        }

        try {
            conexionDestino.close();
        } catch (Exception e) {

        }
    }

    @Override
    public String toString() {
        try {
            return "ConexionProxy{" + "conexionOrigen=" + conexionOrigen.getRemoteAddress().toString() + ", conexionDestino=" + conexionDestino.getRemoteAddress().toString() + "}";
        } catch (IOException ex) {
            return "ConexionProxy{" + "conexionOrigen=" + conexionOrigen + ", conexionDestino=" + conexionDestino + "}";
        }
    }

}
