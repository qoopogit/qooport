package rt.modulos.archivos;

import comunes.CFG;
import comunes.Interfaz;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import network.Conexion;
import rt.Inicio;
import rt.util.Protocolo;
import rt.util.UtilRT;

//subir archivo (funcion descargar archivo)
public class SAR extends Thread implements Interfaz {

    private File archivo;
    private Interfaz servicio;
    private boolean activo;
    private String rutaDestinoCliente;
    private String rutaAomitir;
    private int bufferSize;
    private Conexion conexion;
    private Long offset = 0L;//bytes a descartar del archivo, se usa para reanudar una descarga perdida anteriormente

    public void instanciar(Object... parametros) {
        //String rutaArchivo, String rutaADescargar, String rutaAomitir, SI conex
        //String rutaArchivo, String rutaADescargar, String rutaAomitir, SI conex, Conexion conexion, int bufferSize
        try {
//            System.out.println("se va a instanciar " + parametros.length);
            activo = true;
            this.servicio = (Interfaz) parametros[0];
            archivo = new File((String) parametros[1]);
            this.rutaDestinoCliente = (String) parametros[2];
            this.rutaAomitir = (String) parametros[3];
            try {
                this.offset = (Long) parametros[4];
            } catch (Exception e) {
                offset = 0L;
            }
            if (parametros.length > 5) {
                this.conexion = (Conexion) parametros[5];
                this.bufferSize = (Integer) parametros[6];
            }
        } catch (Exception e) {

        }
    }

    private void detener() {
        activo = false;
    }

    private void iniciar() {
        start();
    }

//    boolean versionNueva = true;
    @Override
    public void run() {
        try {
            //Paso 0. si es conexion inversa realizar la conexión al puerto de transferencias
            if ((Boolean) servicio.get(5)) {//conexion inversa
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                bufferSize = conexion.getSendBufferSize();
                conexion.escribirInt(Protocolo.ADMIN_ARCHIVOS_DESCARGAR);
            }

            //seteo ruta padre
            if (rutaDestinoCliente == null || rutaDestinoCliente.isEmpty()) {
                rutaDestinoCliente = "";
            }
            if (rutaAomitir == null || rutaAomitir.isEmpty()) {
                rutaAomitir = "";
            }

            rutaDestinoCliente = rutaDestinoCliente.replaceAll(Pattern.quote("\\"), "/");
            rutaAomitir = rutaAomitir.replaceAll(Pattern.quote("\\"), "/");
            String parent = archivo.getParent();
            parent = parent.replaceAll(Pattern.quote("\\"), "/");
            CFG config = new CFG();
            config.agregarParametro("id", Inicio.i);
            config.agregarParametro("icono", UtilRT.sacarIcono(archivo));
            if (!rutaDestinoCliente.isEmpty() && !rutaAomitir.isEmpty()) {
                config.agregarParametro("rutaPadre", rutaDestinoCliente + ";" + parent.substring(parent.indexOf(rutaAomitir) + rutaAomitir.length()));
            } else {
                config.agregarParametro("rutaPadre", rutaDestinoCliente + ";" + parent);
            }
            config.agregarParametro("nombre", archivo.getName());
            config.agregarParametro("largo", archivo.length());
            config.agregarParametro("MD5", UtilRT.getMD5Checksum(archivo.getAbsolutePath()));
            config.agregarParametro("offset", offset);
            config.agregarParametro("original", archivo.getAbsolutePath());
            conexion.escribirObjeto(config);
            //paso 6 Preparar envio de archivo
            FileInputStream input = new FileInputStream(archivo);
            byte[] buf = new byte[bufferSize];
            int i;
            //Paso 7 saltar partes del archivo antes enviada

            input.skip(offset);//<ag>25/08/2017. Salta una parte del archivo para poder reanudar en conexiones perdidas
            //paso 8 Enviar archivo
            while ((i = input.read(buf)) > 0 && activo) {
                conexion.write(buf, 0, i);
            }
            //parte 9 ejecutar acciones finales y librerar conexione y recursos 
            conexion.flush();
            input.close();
            Thread.sleep(5000);//espero que la ultima parte se haya ido y continuo
            conexion.cerrar();
            input = null;
        } catch (Exception ex) {
            servicio.ejecutar(6, "Error al enviar archivo " + ex.getMessage());
        } finally {
            conexion = null;
        }
        servicio.ejecutar(11);//restar envio archivo
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
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
