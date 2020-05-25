package qooport.modulos.escritorioremoto;

import comunes.Captura;
import network.Conexion;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class RecibirEscritorio extends Thread {

    private Conexion conexion;
    private EscritorioRemoto ventana;
    private boolean pidiendo;

    // Buffer de la captura. Se utiliza para n enviar bloques que se mantienen, por ejemplo cuano se mueve una ventana
    //es un mapa, donde la clave es el numero del monitor, el valor es otro mapa
    //donde la clave es la ubicacion de la celda y el valor la celda misma
//    private final Map<Integer, Map<String, PantallaBloque>> capturaPrevia = new HashMap();
    public RecibirEscritorio() {

    }

    public RecibirEscritorio(Conexion conexion, EscritorioRemoto ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
    }

    public EscritorioRemoto getVentana() {
        return ventana;
    }

    public void setVentana(EscritorioRemoto ventana) {
        this.ventana = ventana;
    }

    public boolean isPidiendo() {
        return pidiendo;
    }

    public void setPidiendo(boolean pidiendo) {
        this.pidiendo = pidiendo;
    }

//    public void limpiarBuffers() {
//        capturaPrevia.clear();
//    }
    @Override
    public void run() {
        Captura cap;
        byte[] buf;
        while (pidiendo) {
            try {
                //if (pidiendo && conexion != null) {
                if (conexion != null) {
                    if (ventana.getServidor().isAndroid()) {
                        ventana.setTitle("Pantalla Remota [" + ventana.getServidor().getInformacion() + "]");
                        ventana.setTipoEnvio(EscritorioRemoto.ENVIO_COMPLETO);//siempre es envío completo en android
                    } else {
                        ventana.setTitle("Escritorio Remoto [" + ventana.getServidor().getInformacion() + "]");
                    }
                    try {
                        buf = (byte[]) conexion.leerObjeto();
                        if (ventana.getItmComprimir().isSelected()) {
                            cap = (Captura) Util.descomprimirObjeto(buf, ventana.getServidor());//con compresion
                        } else {
                            cap = (Captura) SerializarUtil.leerObjeto(buf); //sin compresion
                            ventana.getServidor().agregarRecibidos(buf.length);//agregamos los bytes recibidos, en el metodo descomprmir ya se agregan
                        }
                        ventana.getReproductor().reproducir(cap);
                        actualizarContadores(cap, buf.length);
                        ventana.barraInferior.setEstado("Conectado");
                    } catch (ClassCastException e) {
                        ventana.barraInferior.setEstado("Esta versión no es compatible");
                    } catch (Exception e) {
                        ventana.barraInferior.setEstado("Error:" + e.getMessage());
                    }
                } else {
                    ventana.barraInferior.setEstado("Esperando conexión...");
                    try {
                        ventana.getContadorFps().getTasaFormated();
                    } catch (Exception e) {
                    }
                    try {
                        ventana.getContadorBps().getTasaFormated();
                    } catch (Exception e) {
                    }
                }
                cap = null;
                buf = null;
                dormir();
            } catch (Exception ex) {
//                Util.escribirLog("Recibir Escritorio", ex);
            }
        }
        //imagen = null;
        conexion = null;
        Util.gc();
    }

    public void dormir() {
        try {
            Thread.sleep(10);
        } catch (Exception ex) {

        }
    }

    public void detener() {
        try {
            pidiendo = false;
            //escribirCapturas();//escribe lo q tenga alamcenado y no haya guardado aun
            Util.esperar(300);
            try {
                //imagen = null;//como es el buffer de las imagenes no la vacio para poder repintar la pantalla
                conexion.cerrar();
            } catch (Exception ex) {
            }
        } catch (Exception e) {
        } finally {
            conexion = null;
        }
        interrupt();
    }

    public void actualizarContadores(final Captura cap, final long largoBuffer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ventana.getContadorFps().agregar(1);
                    ventana.getContadorBps().agregar(largoBuffer);
                    ventana.getContadorTCaptura().resetear();
                    ventana.getContadorTCaptura().agregar(cap.gettCaptura());
                    ventana.getContadorTProceso().resetear();
                    ventana.getContadorTProceso().agregar(cap.gettProceso());
                    ventana.getContadorTEnvio().resetear();
                    ventana.getContadorTEnvio().agregar(cap.gettEnvio());
                    ventana.getContadorBuffer().resetear();
                    ventana.getContadorBuffer().agregar(cap.getTamBuffer());
                    ventana.getContadorCalidad().resetear();
                    if (cap.isJpg()) {
                        ventana.getContadorCalidad().agregar(cap.getCalidad() * 100);
                    } else {
                        ventana.getContadorCalidad().agregar(-1);
                    }
                    ventana.getContadorBits().resetear();
                    ventana.getContadorBits().agregar(cap.getBits());
                    ventana.getContadorBloques().resetear();
                    ventana.getContadorBloques().agregar(cap.getBloques().size());
                    ventana.getContadorPorcentaje().resetear();
                    ventana.getContadorPorcentaje().agregar(cap.getPorcentaje());
                    ventana.getContadorB().resetear();
                    ventana.getContadorB().agregar(largoBuffer);
                    ventana.getContadorSaltadas().resetear();
                    ventana.getContadorSaltadas().agregar(cap.getSaltadas());

                    ventana.getContadorCeldasRC().resetear();
                    ventana.getContadorCeldasRC().agregar(ventana.getReproductor().getTC());
                    ventana.getContadorCeldasRepetidas().resetear();
                    ventana.getContadorCeldasRepetidas().agregar(ventana.getReproductor().getTCA());

                    ventana.getContadorCeldasNuevas().resetear();
                    ventana.getContadorCeldasNuevas().agregar(ventana.getReproductor().getTN());

                } catch (Exception ex) {
                }
            }
        }).start();
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

}
