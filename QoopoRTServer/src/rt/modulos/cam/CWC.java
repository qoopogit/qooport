package rt.modulos.cam;

import comunes.Captura;
import comunes.CapturaOpciones;
import comunes.WebCamInterface;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import network.Conexion;
import rt.Inicio;
import comunes.Interfaz;
import java.io.IOException;
import rt.modulos.escritorio.comunes.BufferCaptura;
import rt.modulos.escritorio.comunes.detector.Completa;
import rt.modulos.escritorio.comunes.detector.DetectorCambios;
import rt.util.IMG;
import rt.util.Protocolo;
import rt.util.UtilRT;

//captura webcam
public class CWC extends Thread implements Interfaz {

    private DetectorCambios detector;
    private Interfaz servicio;
    public boolean activo;
    private float calidad = 100;
    private WebCamInterface webC;
    private String nombreCam;
    private boolean abierto;
    private BufferedImage imagen = null;//variable donde se almacena la imagen que fue tomada
    private Captura captura = null;//variable donde se almacena la captura que se realiza
    private Conexion conexion;
    private CapturaOpciones opciones;

    public CWC() {
    }

    @Override
    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        activo = true;
        abierto = false;
        try {
            webC = ((WebCamInterface) Class.forName("comunes.ObtenerWebCam").newInstance());
        } catch (Exception ex) {
            servicio.ejecutar(3, Protocolo.NO_WEB_CAM);
        }
        int tBloque = 64;
        /**
         * 1.- Blanco y negro,2.- gris, 3.- 8 bits 256 colores, 4.- 16 bits
         * 655356 colores, 5.- 24 bits, 6. 32 bits
         */
        int tipoColor = 6;
        opciones = new CapturaOpciones();
        opciones.setTipoDatos(1);//1 bytes (La imagen que da la libreria de la webcam es en arreglo de bytes), 2 enteros
        opciones.setTamBuffer(1);
        opciones.setAnchoBloque(tBloque);
        opciones.setAltoBloque(tBloque);
        opciones.setEscala(1);
        opciones.setCalidad(0.9f);
        opciones.setEscalar(false);
        opciones.setTipoEscala(3);//ajuste perfecto
//        opciones.setMonitor(0);
        opciones.setTipoColor(tipoColor);
        opciones.setMostrarCursor(false);
        opciones.setTipoEnvio(3);
        opciones.setSuavizado(true);
        opciones.setEnviarHilos(false);//ya no se usa
//        opciones.setOrigenCaptura(0);//no se usa
        opciones.setComprimir(true);
//        opciones.setEnviarCursor(false);
//        opciones.setPortapalesActivos(false);
//        opciones.setTipoHilos(0);
        opciones.setCalidadAutomatica(false);
        opciones.setConvertirJpg(true);
//        opciones.setAlgoritmo(1);
//        opciones.setValidarRepetidos(false);//permitir la validacion de repetidos (Como la codificacion CopyRect de VNC) Aun en pruebas
        opciones.setValidarRepetidos(true);//permitir la validacion de repetidos (Como la codificacion CopyRect de VNC) Aun en pruebas
//        opciones.setAncho(0);
//        opciones.setAlto(0);
        //inicia el detector de cambios
        detector = new Completa();
//        detector = new Celdas();
//        detector = new Pixeles();
        detector.setOpciones(opciones);
    }

    private void abrir() {
        try {
            webC.abrir();
            abierto = true;
        } catch (Exception e) {
            servicio.ejecutar(6, "ERROR AL ABRIR " + nombreCam + " = " + e.getMessage());
        }
    }

    private void seleccionar(String webcam) {
        webC.seleccionar(webcam);
        this.nombreCam = webcam;
    }

    private void seleccionarDimesion(String size) {
        try {
            String[] d = size.split("x");
            Dimension dim = new Dimension(Integer.valueOf(d[0]), Integer.valueOf(d[1]));
            webC.seleccionarDimension(dim);
        } catch (Exception e) {

        }
    }

    private void detener() {
        try {
            abierto = false;
            activo = false;
            webC.cerrar();
        } catch (Exception e) {
        }
    }

    private void setCalidad(float calidad) {
        this.calidad = calidad / 100;
        opciones.setCalidad(this.calidad);
    }

    private byte[] getImagen() {
        try {
            //return UtilRT.comprimirGZIP(IMG.saveImageJPGByte(webC.getImagen(), calidad));
            return IMG.saveImageJPGByte(webC.getImagen(), calidad);
        } catch (Exception ex) {
            return null;
        }
    }

    private void bucle() {
        while (activo) {
            try {
                enviar(obtenerCaptura());
            } catch (Exception ex) {
            }
            UtilRT.dormir(10); //limite de 100 fps asumiendo que el metodo anterior no demorara nada, lo q no es cierto
        }
    }

    private void enviar(Captura captura) throws IOException {
        conexion.escribirObjeto(UtilRT.comprimirObjeto(captura));
//        conexion.flush();
    }

    private Captura obtenerCaptura() {
        capturarIMG();
        procesarCambios();
        return captura;
    }

    private void capturarIMG() {
        imagen = webC.getImagen();
    }

    /**
     * Procesa una nueva imagen en busca de cambios
     *
     * @param imagen
     * @return
     */
    private void procesarCambios() {
        captura = armarCaptura(imagen);
        try {
            captura.setBloques(detector.procesarCambios(imagen));
        } catch (Exception e) {
        }
    }

    private Captura armarCaptura(BufferedImage imagen) {
        captura = UtilRT.capturaLimpia;
        try {
            captura.setPantalla(0);
            captura.settCaptura(0); // tiempo de captura
            captura.settProceso(0);//tiempo q se demora en procesar los cambios
            captura.setTamBuffer(BufferCaptura.getSize((String) servicio.get(10)));
            captura.setAncho(imagen.getWidth());
            captura.setAlto(imagen.getHeight());
            captura.setTipo(opciones.getTipoEnvio());
            captura.setCalidad(opciones.getCalidad());
            captura.setJpg(opciones.isConvertirJpg());
            captura.setSaltadas(BufferCaptura.saltadas);
            captura.setPorcentaje(detector.getPorDiferencia());
            captura.setTipoImagen(imagen.getType());//toma el tipo q fue asignado en el filtro
            captura.setBits(32);
//            switch (opciones.getTipoColor()) {
//                case 1:
////                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_BINARY);
//                    captura.setBits(2);//blanco y negro de un 2 bits
//                    break;
//                case 2:
////                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_GRAY);
//                    captura.setBits(4);
//                    break;
//                case 3:
////                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_INDEXED);
//                    captura.setBits(8);
//                    break;
//                case 4:
////                    captura.setTipoImagen(BufferedImage.TYPE_USHORT_555_RGB);
//                    captura.setBits(16);
//                    break;
//                case 5:
////                    captura.setTipoImagen(BufferedImage.TYPE_3BYTE_BGR);
//                    captura.setBits(24);
//                    break;
//                case 6:
////                    captura.setTipoImagen(BufferedImage.TYPE_4BYTE_ABGR);
//                    captura.setBits(32);
//                    break;
//            }            
        } catch (Exception e) {

        }
        return captura;
    }

    @Override
    public void run() {
        conexion = null;
        byte[] data = null;
        try {
            if (abierto) {
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion.escribirInt(Protocolo.CAPTURA_WEB_CAM);
//                conexion.flush();
                conexion.escribirObjeto(UtilRT.texto(Inicio.i));
//                conexion.flush();
                bucle();

////metodo anterior donde se tomaba toda la imagen y se enviaba la misma, sin deteccion de cambios
//                imagen = null;
//                long tmp2 = 0;
//                while (activo) {
//                    imagen = webC.getImagen();
//                    if (imagen != null) {
//                        // imagen = IMG.filtrar(IMG.escalar(imagen, tipoEscalado, escala, suavizado, this.getAncho(), this.getAlto()), tipoColor);
//                        data = IMG.saveImageJPGByte(imagen, calidad);
//                        //tmp2 = imagen.hashCode();
//                        tmp2 = UtilRT.generarChecksum(data);
//                        if (hashAnterior != tmp2) {
//                            conexion.escribirObjeto(UtilRT.comprimirGZIP(data));
//                            conexion.flush();
//                            hashAnterior = tmp2;
//                        }
//                    }
//                    dormir();
//                }
            }
        } catch (Exception ex) {
            servicio.ejecutar(6, "Web cam error: " + nombreCam + " = " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            activo = false;
            try {
                if (conexion != null) {
                    conexion.cerrar();
                }
            } catch (Exception ex) {
            }
        }
    }

//    private boolean estaDetenido() {
//        return isInterrupted();
//    }
    private void iniciar() {
        activo = true;
        start();
    }

    public void set(int opcion, Object valor) {
        try {
            switch (opcion) {
                case 1: //seleccionar
                    seleccionar((String) valor);
                    break;
                case 2: //calidad
                    setCalidad((Float) valor);
                    break;
                case 5: //seleccionar dimesion
                    seleccionarDimesion((String) valor);
                    break;
            }
        } catch (Exception e) {
        }
    }

    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 0: //getImagen
                return getImagen();
        }
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
            case 1:
                detener();
                break;
            case 2:
                abrir();
                break;
        }
    }
}
