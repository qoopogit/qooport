package rt;

import comunes.CapturaOpciones;
import comunes.Comando;
import comunes.Interfaz;
import network.Conexion;
import network.ConexionServer;
import rt.util.Protocolo;
import rt.util.UtilRT;

//conexion archivos
public class CONAR extends Thread implements Interfaz {

    private ConexionServer conexion_servidor;
    private Conexion conexion;
    private Interfaz servicio;
    public boolean CONECTADO = true;

    @Override
    public void instanciar(Object... parametros) {
        //int puerto, int cola, SI servicio
        try {
            this.servicio = (Interfaz) parametros[0];
            this.conexion_servidor = new ConexionServer(ConexionServer.TCP, (Integer) parametros[1], (Boolean) Inicio.config.obtenerParametro("protocolo"));
            CONECTADO = true;
            start();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        Comando comando = null;
        do {
            try {
                this.conexion = this.conexion_servidor.aceptar();
                comando = (Comando) UtilRT.descomprimirObjeto((byte[]) conexion.leerObjeto());
                switch (comando.getComando()) {
                    case Protocolo.ADMIN_ARCHIVOS_DESCARGAR:
                        String archivoADescargar = (String) UtilRT.leerParametro(comando, 0);
                        String rutaDescargar = (String) UtilRT.leerParametro(comando, 1);
                        servicio.ejecutar(9, archivoADescargar, rutaDescargar, conexion, conexion.getSendBufferSize());//descargar archivo
                        break;
                    case Protocolo.ADMIN_ARCHIVOS_SUBIR:
                        String archivoAsubir = (String) UtilRT.leerParametro(comando, 0);
                        String rutaAsubir = (String) UtilRT.leerParametro(comando, 1);
                        servicio.ejecutar(2, rutaAsubir, archivoAsubir, conexion, conexion.getReceiveBufferSize());//subir archivo
                        break;
                    case Protocolo.AUDIO:
                        break;
                    case Protocolo.ESCRITORIO_REMOTO:
                        CapturaOpciones opciones = (CapturaOpciones) UtilRT.leerParametro(comando);
                        servicio.ejecutar(10, opciones, conexion);//iniciar escritorio remoto
                        break;
                    case Protocolo.CAPTURA_WEB_CAM:
                        break;

                }
            } catch (Exception ex) {

            }
        } while (CONECTADO);
    }

    private void detener() {
        CONECTADO = false;
        try {
            this.conexion_servidor.cerrar();
        } catch (Exception ex) {
        }
    }

    public boolean estaDetenido() {
        return CONECTADO;
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                detener();
                break;
        }
    }
}
