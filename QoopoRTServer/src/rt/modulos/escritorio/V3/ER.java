package rt.modulos.escritorio.V3;

import comunes.CapturaOpciones;
import comunes.Evento;
import comunes.Interfaz;
import java.util.List;
import network.Conexion;
import rt.Inicio;
import rt.modulos.escritorio.comunes.BufferCaptura;
import rt.modulos.escritorio.comunes.GestorPantalla;
import rt.util.CLRT;
import rt.util.Protocolo;
import rt.util.UtilRT;

//escritorio remoto v3
/**
 * Escritorio Remoto V3. Esta clase se encarga de utilizar la clase
 * GestorPantalla, la cual administra y crea instancias para cada pantalla en el
 * cliente. Cada una de estas pantallas detecta los cambios ocurridos en la
 * imagen y realiza una captura que agregan al buffer de Captura. luego hay una
 * clase que se encarga de enviar cada captura que se almacena en este buffer de
 * capturas
 *
 * @author alberto
 */
public class ER extends Thread implements Interfaz {

    private Interfaz servicio;
    private Conexion conexion;
    private Interfaz hiloEnvio;
    private GestorPantalla pantallas;
    private boolean activo;

    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        try {
            hiloEnvio = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.comunes.Envio").newInstance());
        } catch (Exception ex) {

        }
        pantallas = new GestorPantalla();
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
                pantallas.liberar();
                hiloEnvio.ejecutar(1);//detener
                conexion.cerrar();
                conexion = null;
                pantallas = null;
                hiloEnvio = null;
                servicio = null;
            } catch (Exception ex) {
            }
            interrupt();
        } catch (Exception e) {
        }
    }

    private void actualizarPantalla() {
        BufferCaptura.limpiar((String) servicio.get(10));
        pantallas.actualizar();
    }

    @Override
    public void run() {
        try {
            if ((Boolean) servicio.get(5)) {
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion.escribirInt(Protocolo.ESCRITORIO_REMOTO);
                conexion.escribirObjeto(UtilRT.texto(Inicio.i));
            }
            //limpia el buffer capturado previamente , en caso de haber detenido y volver a lanzar la captura
            BufferCaptura.limpiar((String) servicio.get(10));
            //la profundidad del buffer es el numero de pantallas para permitir capturas simultaneas (inicialmente 1)
            BufferCaptura.iniciarParametros((String) servicio.get(10), pantallas.getNumeroPantallas());
            pantallas.setearServicio(servicio);
            pantallas.iniciar();
            hiloEnvio.set(0, conexion);
            hiloEnvio.set(1, (String) servicio.get(10));
            hiloEnvio.set(7, this);
            hiloEnvio.ejecutar(0);//iniciar
            activo = true;
//            while (activo) {
//                enviarCursor();
//                UtilRT.dormir(50);//solo afecta a la velocidad de envio de cursor
//            }
        } catch (Exception ex) {
            try {
                servicio.ejecutar(6, "Error en captura de pantalla:" + ex.getMessage() + "  LM:" + ex.getLocalizedMessage() + " Causa:" + ex.getCause().toString());
            } catch (Exception e) {
            }
        } finally {
            activo = false;
        }
    }

//    private void enviarCursor() {
//        if (((CapturaOpciones) pantalla.get(3)).isEnviarCursor()) {
//            ImageIcon cursor = (ImageIcon) pantalla.get(5);
////            Double px = pantalla.getCursorPosicion().getX();
////            Double py = pantalla.getCursorPosicion().getY();
//            //envia la imagen del cursor solo cuando ha cambiado
//            if (cursor != null && cursor.hashCode() != hashCursor) {
//                servicio.ejecutar(3, Protocolo.GET_CURSOR, cursor);
//                hashCursor = cursor.hashCode();
//            }
//        }
//    }
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
                    pantallas.setearOpciones((CapturaOpciones) valor);
                    //pantalla.set(3, valor);
                    //tomo la propiedad de compresion
                    hiloEnvio.set(2, ((CapturaOpciones) valor).isComprimir());
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
            case 15:
                return getConexion();
            case 16:
                return activo;
            case 17:
                return estaDetenido();
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
            case 3:
                actualizarPantalla();
                break;
            case 5:
                pantallas.ctrl_alt_supr();
                break;
            case 6:
                pantallas.ejecutarEventos((List<Evento>) parametros[0]);
                break;
        }
    }
}
