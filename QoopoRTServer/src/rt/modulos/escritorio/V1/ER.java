package rt.modulos.escritorio.V1;

import comunes.Accion;
import comunes.Captura;
import comunes.CapturaOpciones;
import comunes.Interfaz;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import network.Conexion;
import rt.Inicio;
import rt.modulos.escritorio.comunes.BufferCaptura;
import rt.util.CLRT;
import rt.util.HiloAccion;
import rt.util.Protocolo;
import rt.util.UtilRT;

/**
 * Escritorio Remoto V1. Esta clase se encarga de realizar las cpaturas de
 * pantalla y enviar las capturas. Tiene 3 algoritmos de transmision. -- 1. El
 * mas simple de todos. Primero realiza la captura y luego envia esa captura. --
 * 2. Se crean 2 hilos paralelos donde un hilo se encarga de realizar capturas
 * de la pantalla y el otro se encarga de enviar esas capturas. -- 3. Se crean
 * hilos paralelos donde cada uno realiza el algoritmo 1, osea priemro captura y
 * luego envia esa captura
 *
 * @author alberto
 */
public class ER extends Thread implements Interfaz {

    private Interfaz pantalla;
    private Interfaz servicio;
    private Conexion conexion;
    private boolean activo;
    private int hashCursor = 0;// para detectar el cambio del cursor
    private long tEnvio;
    //--------------------------------------------------------------------------------------------
    //                          CONSTANTES
    private final int LIMITE_CAPTURAS = 20;
    private final int LIMITE_HILOS = 15;
    //--------------------------------------------------------------------------------------------

    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        try {
            pantalla = ((Interfaz) new CLRT().loadClass("rt.modulos.escritorio.comunes.Pantalla").newInstance());
            pantalla.instanciar();
        } catch (Exception ex) {

        }
        pantalla.set(0, servicio);
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
                conexion.cerrar();
                conexion = null;
                pantalla = null;
                servicio = null;
            } catch (Exception ex) {
            }
        } catch (Exception e) {
        }
    }

    private void actualizarPantalla() {
        pantalla.ejecutar(2);
    }

    @Override
    public void run() {
//        setName("hilo-ER-V1");
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
            switch (((CapturaOpciones) pantalla.get(3)).getTipoHilos()) {
                case 0:
                    bucleSimple();
                    break;
                case 1:
                    BufferCaptura.iniciarParametros((String) servicio.get(10), LIMITE_CAPTURAS);//limite de capturas en el buffer
                    bucleTransimisionHilos1();
                    break;
                default:
                    bucleTransimisionHilos2();
                    break;
            }
        } catch (Exception ex) {
            try {
                servicio.ejecutar(6, "Error en captura de pantalla:" + ex.getMessage() + "  LM:" + ex.getLocalizedMessage() + " Causa:" + ex.getCause().toString());
            } catch (Exception e) {
            }
        } finally {
            BufferCaptura.limpiar((String) servicio.get(10));
            try {
                conexion.cerrar();
            } catch (Exception ex) {
            }
        }
    }

    private void bucleSimple() {
        while (activo) {
            enviar((Captura) pantalla.get(1));
            UtilRT.dormir(10); //limite de 100 fps asumiendo que el metodo anterior no demorara nada, lo q no es cierto
        }
    }

    /**
     * Un hilo realiza las capuras y otro hilo va tomando y enviando
     */
    private void bucleTransimisionHilos1() {
        HiloAccion hiloCaptura;
        HiloAccion hiloEnvio;
        // metodo de envio por hilos. Existen 2 hilos:
        // * uno que va capturando las pantallas y las va colocando en un buffer
        // * uno que va tomando las pantallas capturadas y las va enviando por la red
        Accion accionCaptura = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                try {
                    BufferCaptura.agregar((String) servicio.get(10), (Captura) pantalla.get(1));
                } catch (Exception e) {
                }
            }
        };
        hiloCaptura = new HiloAccion(accionCaptura);
        hiloCaptura.iniciar();
        Accion accionEnvio = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                try {
                    enviar(BufferCaptura.getCaptura((String) servicio.get(10)));
                } catch (Exception e) {
                }
            }
        };
        hiloEnvio = new HiloAccion(accionEnvio);
        hiloEnvio.iniciar();
        while (activo) {
            //duerme para la siguiente validacion de fin de ejecucion. Este dormir no afecta a la velocidad
            UtilRT.dormir(1000);//este dormir no afecta la velocidad de captura
        }
        hiloCaptura.detener();
        hiloCaptura = null;
        hiloEnvio.detener();
        hiloEnvio = null;
    }

    /**
     * Se crean hilos paralelos donde van capturando y enviando
     */
    private void bucleTransimisionHilos2() {
        //capturas y envio simultaneos
        Accion accionCaptura = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                try {
                    enviar((Captura) pantalla.get(1));
                } catch (Exception e) {
                }
            }
        };
        List<HiloAccion> lst = new ArrayList<HiloAccion>();
        for (int i = 0; i < LIMITE_HILOS; i++) {
            HiloAccion hiloCaptura = new HiloAccion(accionCaptura);
            hiloCaptura.iniciar();
            lst.add(hiloCaptura);
            UtilRT.dormir(50);//tiempo de espera entre cada hilo q creo
        }

        while (activo) {
            //duerme para la siguiente validacion de fin de ejecucion. Este dormir no afecta a la velocidad
            UtilRT.dormir(1000);//este dormir no afecta la velocidad de captura
        }

        for (HiloAccion h : lst) {
            h.detener();
        }
        lst.clear();
        lst = null;

    }

    private void enviarCursor() {
        if (((CapturaOpciones) pantalla.get(3)).isEnviarCursor()) {
            ImageIcon cursor = (ImageIcon) pantalla.get(5);
            //envia la imagen del cursor solo cuando ha cambiado
            if (cursor != null && cursor.hashCode() != hashCursor) {
                servicio.ejecutar(3, Protocolo.GET_CURSOR, cursor);
                hashCursor = cursor.hashCode();
            }
        }
    }

    private void enviar(Captura captura) {
        long tInicio = System.currentTimeMillis();
        try {
            if (captura == null) {
                return;
            }
            //si no hay bloques no envia nada
            if (captura.getBloques() == null || captura.getBloques().isEmpty()) {
                return;
            }
            captura.settEnvio(tEnvio);
            if (((CapturaOpciones) pantalla.get(3)).isComprimir()) {
                conexion.escribirObjeto(UtilRT.comprimirObjeto(captura));
            } else {
                conexion.escribirObjeto(UtilRT.convertirBytes(captura));
            }
//            conexion.flush();
        } catch (Exception e) {
        }

        long tFin = System.currentTimeMillis();
        tEnvio = (tFin - tInicio);
        enviarCursor();
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
                    pantalla.set(3, (CapturaOpciones) valor);
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
