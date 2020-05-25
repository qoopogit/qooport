/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.proxy;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class Redireccion {

    private List<Mapeo> lista = new ArrayList<>();
    private List<ProxyWrapper> listaProxy = new ArrayList<>();
    private boolean verbose = false;

    public void agregar(String hostLocal, int puertoLocal, String hostRemoto, int puertoRemoto) {
        System.out.println("se agrego puerto " + puertoLocal);
        lista.add(new Mapeo(hostLocal, puertoLocal, hostRemoto, puertoRemoto));
    }

    public void limpiar() {
        lista.clear();
    }

    public void detener() {
        System.out.println("DETENIENDO PROXY " + listaProxy.size());
        for (ProxyWrapper p : listaProxy) {
            p.detener();
        }
    }

    public void iniciar() {

        listaProxy.clear();
        /*
        Proxy [-help] [-local <local address>] [-local_port <port>] "
                + "[-remote <remote address>] [-remote_port <port>] [-verbose] "
                + "[-file <mapping file>] [-debug]
         */
        System.out.println("INICIANDO PROXY -- " + lista.size());
        for (final Mapeo map : lista) {
            try {
                Proxy p = new Proxy(
                        map.getHostLocal(),
                        map.getPuertoLocal(),
                        map.getHostRemoto(),
                        map.getPuertoRemoto(),
                        verbose, false, null);
                ProxyWrapper pp = new ProxyWrapper(p);
                listaProxy.add(pp);
                pp.start();
                Thread.sleep(500);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Redireccion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Redireccion.class.getName()).log(Level.SEVERE, null, ex);
            }

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Proxy p = new Proxy(map.getHostLocal(), map.getPuertoLocal(),
//                                map.getHostRemoto(), map.getPuertoRemoto(), verbose, false, null);
//                        ProxyWrapper pp = new ProxyWrapper(p);
//                        listaProxy.add(pp);
//                        pp.start();
//                    } catch (UnknownHostException ex) {
//                        Logger.getLogger(Redireccion.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Redireccion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }).start();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if (verbose) {
//                        Proxy.main("-local", map.getHostLocal(), "-local_port", String.valueOf(map.getPuertoLocal()),
//                                "-remote", map.getHostRemoto(), "-remote_port", String.valueOf(map.getPuertoRemoto()),
//                                "-verbose");
//                    } else {
//                        Proxy.main("-local", map.getHostLocal(), "-local_port", String.valueOf(map.getPuertoLocal()),
//                                "-remote", map.getHostRemoto(), "-remote_port", String.valueOf(map.getPuertoRemoto())
//                        );
//                    }
//                }
//            }).iniciar();
        }
    }

    public void test() {
        verbose = false;
        limpiar();
//        lista.add(new Mapeo("192.168.100.91", 5000, "172.18.36.64", 5000));
//        lista.add(new Mapeo("192.168.100.91", 5001, "172.18.36.64", 5001));

        String ipDestino = "172.18.36.83";
        lista.add(new Mapeo("192.168.100.91", 4000, ipDestino, 4000));
        lista.add(new Mapeo("192.168.100.91", 4001, ipDestino, 4001));
        lista.add(new Mapeo("192.168.100.91", 4002, ipDestino, 4002));
        lista.add(new Mapeo("192.168.100.91", 4003, ipDestino, 4003));
        lista.add(new Mapeo("192.168.100.91", 4004, ipDestino, 4004));
        lista.add(new Mapeo("192.168.100.91", 4005, ipDestino, 4005));
    }

    public static void main(String[] args) {
        Redireccion red = new Redireccion();
        red.test();
        red.iniciar();

    }

    class ProxyWrapper extends Thread {

        private Proxy proxy;

        public ProxyWrapper(Proxy proxy) {
            this.proxy = proxy;
        }

        public void detener() {
            proxy.detener();
        }

        public void run() {
            try {
                proxy.iniciar();
            } catch (Exception ex) {
                Logger.getLogger(Redireccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
