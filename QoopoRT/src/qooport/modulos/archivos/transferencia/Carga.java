/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.transferencia;

import java.io.File;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import network.Conexion;
import qooport.Global;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

/**
 *
 * @author aigarcia
 */
public class Carga extends Transferencia {

    private int offset = 0;//bytes a descartar del archivo, se usa para reanudar una descarga perdida anteriormente

    public Carga(Asociado asociado, Conexion conexion, File archivo, int offset) {
        this.asociado = asociado;
        this.conexion = conexion;
        this.archivo = archivo;
        this.offset = offset;
        contador = new ContadorBPS();
        contador.start();
    }

    public Carga(Asociado asociado, Conexion conexion, File archivo) {
        this.asociado = asociado;
        this.conexion = conexion;
        this.archivo = archivo;
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
            activo = false;
            sleep(300);
            ok = false;
            interrupt();
        } catch (InterruptedException ex) {

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

    }

    @Override
    public void run() {
        try {
            if (archivo == null || !archivo.exists()) {
                System.out.println("El archivo no existe o no ha sido especificado");
                return;
            }
            agregarAlGestor();
//            if (servidor != null && servidor.isConexionDirecta()) {
//                Socket socket = new Socket(servidor.getHostConexion(), servidor.getPuertoTransferencia());
//                this.bufferSize = socket.getSendBufferSize();
//                enviarObjeto = new ObjectOutputStream(socket.getOutputStream());
//                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//                enviarObjeto.writeInt(Protocolo.subir);
//                enviarObjeto.flush();
//                enviarObjeto.writeObject(archivoASubir);
//                enviarObjeto.flush();
//                enviarObjeto.writeObject(rutaRemota);
//                enviarObjeto.flush();
//                byte[] bytes = (byte[]) in.readObject();
//                Archivo = Asociado.parsearCadena(bytes, false);
//                if (!new File(Archivo).exists()) {
//                    Archivo = Asociado.parsearCadena(bytes, true);
//                }
//            }

            if (asociado != null) {
                asociado.setLimiteFinito();//setea que exista un limite finito de cargas paralelas
            }
            String nombre = archivo.getName();
            conexion.escribirObjeto(Util.texto(nombre));
            try {
                setIcono(new ImageIcon(Util.sacarIcono(archivo)));
            } catch (Exception e) {
            }
            nombreArchivo = nombre;

            //---lectura y carga del archivo
            FileInputStream input = new FileInputStream(archivo);
            byte[] buf = new byte[bufferSize];
            int cantidad;
            actual = 0;
            total = archivo.length();
            Global.transferencias.progresoCarga.agregarTotal(total);
            tInicio = System.currentTimeMillis();
            input.skip(offset);//<ag>25/08/2017. Salta una parte del archivo para poder reanudar en conexiones perdidas
            while ((cantidad = input.read(buf)) !=-1 && activo) {
                conexion.write(buf, 0, cantidad);
                actual += cantidad;
                avance = (int) (actual * 100L / total);
                contador.agregar(cantidad);
                if (accionProgreso != null) {
                    accionProgreso.ejecutar();
                }
                Global.transferencias.progresoCarga.agregarAvance(cantidad);
                QoopoRT.instancia.contadorSubida.agregar(cantidad);
                while (pausado) {
                    sleep(50);
                }
            }
            tFinal = System.currentTimeMillis();
            conexion.flush();

            input.close();
            // despues de terminar de enviar el archivo envio el comando de finalizado
//            conexion.escribirInt(Protocolo.FIN_ARCHIVO);

//            int confirmacion = conexion.leerInt();
//            if (confirmacion != rt.util.Protocolo.FIN_ARCHIVO) {
//                System.out.println("Advertencia al cargar archivo. El comando recibido de fun de archivo no es el correcto [" + confirmacion + "]");
//            } else {
//                System.out.println("se recibio la comprobacion de final de archivo. liberando conexion");
//            }
//            if (asociado != null && asociado.getTiempoVida() > 5000) {
//                //espero el valor de ping del servidor, que corresponde al valor de ida y vuelta del paquete, el doble dle necesario
//                Thread.sleep(asociado.getTiempoVida());
//            } else {
                Thread.sleep(5000);//espero que la ultima parte se haya ido y continuo
//            }
            conexion.cerrar();
            input = null;
            //            conexion.leerInt();//espero confirmacion
            if (accionFinalizar != null) {
                accionFinalizar.ejecutar();
            }
            ok = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            activo = false;
            conexion = null;
            Global.transferencias.progresoCarga.quitarAvance(total);
            Global.transferencias.progresoCarga.quitarTotal(total);
            QoopoRT.ejecutarAccionesCarga();
            if (asociado != null) {
                asociado.restarEnvio();
            }
        }

    }

}
