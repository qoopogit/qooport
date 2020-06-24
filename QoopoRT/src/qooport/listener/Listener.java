/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.listener;

import java.io.File;
import network.Conexion;
import network.ConexionServer;
import qooport.asociado.Asociado;
import qooport.asociado.v2.AsociadoV2;
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
public abstract class Listener extends Thread {

    protected ConexionServer conexion_servidor;
    protected Conexion conexion;
    public boolean CONECTADO = true;
    protected boolean ssl;
    protected boolean version2;

    protected void procesar(int comando, Conexion conexion, boolean ssl) {
        try {
            byte[] bytesId;
            switch (comando) {

                case Protocolo.TPC_INICIAR: {
//                    if (version2) {
//                        Asociado servidor = new AsociadoV2(this.conexion, 1, ssl);
//                        servidor.start();
//                    } else {
//                        Asociado servidor = new AsociadoV1(this.conexion, 1, ssl);
//                        servidor.start();
//                    }
                    Asociado servidor = new AsociadoV2(conexion, 1, ssl);
                    servidor.start();
//                    QoopoRT.instancia.ponerEstado("Conexión TCP desde :" + conexion.getInetAddress().getHostAddress() + "(" + conexion.getRemoteSocketAddress().toString() + ") en el puerto " + conexion_servidor.getPuerto());
                    QoopoRT.instancia.ponerEstado("Conexión TCP desde :" + conexion.getInetAddress().getHostAddress() + "(" + conexion.getRemoteSocketAddress().toString() + ") en el puerto " + conexion.getPuerto());

                    break;
                }
                case Protocolo.ADMIN_ARCHIVOS_DESCARGAR: {
                    Transferencia tDescarga = new Descarga(conexion);
                    tDescarga.setBufferSize(conexion.getReceiveBufferSize());
                    tDescarga.iniciar();
                    break;
                }
                case Protocolo.ADMIN_ARCHIVOS_SUBIR: {
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
                    break;
                }
                case Protocolo.AUDIO: {
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
                }
                case Protocolo.AUDIO_ANDROID: {
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
                }
                case Protocolo.ESCRITORIO_REMOTO: {
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
                }
                case Protocolo.CAPTURA_WEB_CAM: {
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
                }
                case Protocolo.ANDROID_FOTO: {
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
            }

        } catch (Exception e) {

        }
    }

}
