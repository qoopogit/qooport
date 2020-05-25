package rt.modulos.archivos;

import comunes.Archivo;
import java.io.File;
import comunes.Interfaz;
import rt.util.Protocolo;
import rt.util.UtilRT;

//buscar archivo
public class BAR extends Thread implements Interfaz {

    private String criterio;
    private String lugar;
    private boolean seguir;
    private Interfaz servicio;

    private void detener() {
        seguir = false;
        try {
            Thread.sleep(300);

        } catch (Exception e) {
        }
    }

    public void instanciar(Object... parametros) {
        //String criterio, String lugar
        this.servicio = (Interfaz) parametros[0];
        this.criterio = dameRegex((String) parametros[1]);
        this.lugar = (String) parametros[2];
        seguir = true;
        start();
    }

    private String dameRegex(String mascara) {
        try {
            //return mascara.replace("?", ".").replace("*", ".*").replace(".", "\\.");
            return mascara.replace("?", ".?").replace("*", ".*?");
        } catch (Exception e) {
            return mascara;
        }
    }

    private void buscar(String ruta) {
        try {
            if (!seguir) {
                return;
            }
            File f = new File(ruta);
            if (f.exists()) {
                if (f.isDirectory()) {
                    if (f.listFiles() != null && f.listFiles().length > 0) {
                        for (File ff : f.listFiles()) {
                            buscar(ff.getAbsolutePath());
                        }
                    }
                    //} else if (Pattern.matches(criterio, f.getName())) {
                } else if (f.getName().matches(criterio)) {
                    Archivo a = new Archivo();
                    a.setLength(f.length());
                    a.setNombre(f.getName());
                    a.setCarpeta(f.isDirectory());
                    a.setPath(f.getAbsolutePath());
                    a.setTipo(UtilRT.getExtension(f.getName()));
                    a.setFecha(f.lastModified());
                    a.setIcono(UtilRT.sacarIcono(f));
                    a.setPathParent(f.getParent().replace("\\", "/"));
                    servicio.ejecutar(3, Protocolo.BUSCAR_ARCHIVO, a);
                }
            }
        } catch (Exception e) {
            servicio.ejecutar(6, "Error al buscar archivo:" + e.getMessage());
            e.printStackTrace();
//            seguir = false;
        }
    }

    @Override
    public void run() {
//        setName("hilo-buscar archivos");
        buscar(lugar);
        servicio.ejecutar(3, Protocolo.BUSCAR_ARCHIVO_DETENER);
    }

//    private boolean estaDetenido() {
//        return !seguir;
//    }
    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return seguir;
    }

    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 1:
                detener();
                break;
        }
    }

}
