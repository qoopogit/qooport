package qooport.modulos.escritorioremoto;

import comunes.Captura;
import network.Conexion;
import qooport.modulos.reproductor.Reproductor;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class RecibirEscritorio extends Thread {

    private Conexion conexion;
    private EscritorioRemoto ventana;
    private Reproductor reproductor;
    private boolean pidiendo;

    public RecibirEscritorio() {

    }

    public RecibirEscritorio(Conexion conexion, EscritorioRemoto ventana) {
        this.conexion = conexion;
        this.ventana = ventana;
        this.reproductor= ventana.getReproductor();
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

    @Override
    public void run() {
        Captura captura;
        byte[] buffer;
        while (pidiendo) {
            try {
                if (conexion != null) {
                    if (ventana.getServidor().isAndroid()) {
                        ventana.setTitle("Pantalla Remota [" + ventana.getServidor().getInformacion() + "]");
                        ventana.setTipoEnvio(EscritorioRemoto.ENVIO_COMPLETO);//siempre es envío completo en android
                    } else {
                        ventana.setTitle("Escritorio Remoto [" + ventana.getServidor().getInformacion() + "]");
                    }
                    try {
                        buffer = (byte[]) conexion.leerObjeto();
                        if (ventana.getItmComprimir().isSelected()) {
                            captura = (Captura) Util.descomprimirObjeto(buffer, ventana.getServidor());//con compresion
                        } else {
                            captura = (Captura) SerializarUtil.leerObjeto(buffer); //sin compresion
                            ventana.getServidor().agregarRecibidos(buffer.length);//agregamos los bytes recibidos, en el metodo descomprmir ya se agregan
                        }
                        reproductor.reproducir(captura);
                        actualizarContadores(captura, buffer.length);
                        ventana.barraInferior.setEstado("Conectado");
                    } catch (ClassCastException e) {
                        ventana.barraInferior.setEstado("Esta versión no es compatible");
                    } catch (Error e) {
                        ventana.barraInferior.setEstado("Error:" + e.getMessage());
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
                captura = null;
                buffer = null;
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
