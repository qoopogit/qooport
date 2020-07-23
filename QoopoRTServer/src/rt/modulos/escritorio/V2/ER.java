package rt.modulos.escritorio.V2;

import comunes.CapturaOpciones;
import comunes.Interfaz;
import java.io.Serializable;
import javax.swing.ImageIcon;
import network.Conexion;
import rt.Inicio;
import rt.modulos.escritorio.comunes.BufferCaptura;
import rt.util.CLRT;
import rt.util.Protocolo;
import rt.util.UtilRT;

/**
 * Escritorio Remoto V2. Esta clase se encarga de realizar la captura de
 * pantalla y de enviar las cpaturas en hilso separados. Un hilo se encarga de
 * ir capturando los cambios de la pantalla, y otro hilo se encarga de ir
 * enviando esas capturas
 *
 * @author alberto
 */
public class ER extends Thread implements Interfaz, Serializable {

    private Interfaz servicio;
    private Interfaz hiloEnvio;
    private Interfaz pantalla;
    private Conexion conexion;
    private boolean activo;
    private int hashCursor = 0;// para detectar el cambio del cursor

    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        try {
            hiloEnvio = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.comunes.Envio").newInstance());
        } catch (Exception ex) {

        }
        try {
            pantalla = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.comunes.Pantalla").newInstance());
            pantalla.instanciar();
        } catch (Exception ex) {

        }
    }

    private boolean estaDetenido() {
        return isInterrupted();
    }

    private void iniciar() {
        activo = true;
        start();
    }

    private void detener() {
        try {
            activo = false;
            UtilRT.dormir(300);
            try {
                BufferCaptura.limpiar((String) servicio.get(10));
                pantalla.ejecutar(1);//detener
                hiloEnvio.ejecutar(1);//detener
                pantalla = null;
                hiloEnvio = null;
                conexion.cerrar();
                conexion = null;
                servicio = null;
            } catch (Exception ex) {
            }
            interrupt();
        } catch (Exception e) {

        }
    }

    private void actualizarPantalla() {
        BufferCaptura.limpiar((String) servicio.get(10));
        pantalla.ejecutar(2);
    }

    @Override
    public void run() {
        try {
            if ((Boolean) servicio.get(5)) {
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(3), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion.escribirInt(Protocolo.ESCRITORIO_REMOTO);
                conexion.escribirObjeto(UtilRT.texto(Inicio.i));
            }
            //limpia el buffer capturado previamente , en caso de haber detenido y volver a lanzar la captura
            BufferCaptura.limpiar((String) servicio.get(10));
            BufferCaptura.iniciarParametros((String) servicio.get(10), 1);//solo tengo una captura en el buffer hasta q lelgue el parametro de buffers a usar
            pantalla.set(0, servicio);
            pantalla.ejecutar(0);
            hiloEnvio.set(0, conexion);
            hiloEnvio.set(1, (String) servicio.get(10));
            hiloEnvio.set(7, this);
            hiloEnvio.ejecutar(0);//iniciar
            activo = true;
            while (activo) {
                enviarCursor();
                UtilRT.dormir(50);//solo afecta a la velocidad de envio de cursor
            }
        } catch (Exception ex) {
            try {
                servicio.ejecutar(6, "Error en captura de pantalla:" + ex.getMessage() + "  LM:" + ex.getLocalizedMessage() + " Causa:" + ex.getCause().toString());
            } catch (Exception e) {
            }
        } finally {
            activo = false;
            UtilRT.gc();
        }
    }

    private void enviarCursor() {
        if (((CapturaOpciones) pantalla.get(3)).isEnviarCursor()) {
            ImageIcon cursor = (ImageIcon) pantalla.get(5);
//            Double px = pantalla.getCursorPosicion().getX();
//            Double py = pantalla.getCursorPosicion().getY();
            //envia la imagen del cursor solo cuando ha cambiado
            if (cursor != null && cursor.hashCode() != hashCursor) {
                servicio.ejecutar(3, Protocolo.GET_CURSOR, cursor);
                hashCursor = cursor.hashCode();
                cursor = null;
            }
        }
    }

    private Conexion getConexion() {
        return conexion;
    }

    private void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void set(int opcion, Object valor) {
        try {
            switch (opcion) {
                case 1:
                    pantalla.set(3, valor);
                    //tomo la propiedad de compresion
                    hiloEnvio.set(2, ((CapturaOpciones) pantalla.get(3)).isComprimir());
                    break;
                case 15:
                    setConexion((Conexion) valor);
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                return pantalla.get(3);
            case 6:
                return ((CapturaOpciones) pantalla.get(3)).getMonitor();
            case 15:
                return getConexion();
            case 16:
                return activo;
            case 17:
                return estaDetenido();
            case 50://tiempo de captura
                return pantalla.get(4);
        }
        return null;
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
            case 1:
                detener();
                break;
            case 2:
                pantalla.ejecutar(5, parametros);
                break;
            case 3:
                actualizarPantalla();
                break;
            case 4:
                pantalla.ejecutar(4, parametros);
                break;
            case 5:
                pantalla.ejecutar(3);
                break;
            case 6:
                pantalla.ejecutar(6, parametros);
                break;
        }
    }
}
