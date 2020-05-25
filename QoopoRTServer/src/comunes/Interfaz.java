package comunes;

public interface Interfaz {

    public void instanciar(Object... parametros);

    //getters y setters
    public void set(int opcion, Object valor);

    public Object get(int opcion, Object... parametros);

    public void ejecutar(int opcion, Object... parametros);
    /*
    0 iniciar
    1 detener
    > propios de la clase
     */

    //para generales
    //public void iniciar(String nombre);
    //public void iniciar(SI servicio, int opcion, Object[] parametros);
//    public boolean estaDetenido();
//
//    public void abrir(String tipo);
//
//    public void cerrar();
//
//    //instalador
//    public void detener();
//
//    //capura de pantalla;
//    public void eventoMouse(float x, float y, int evento, int boton, int wheel);
//
//    public void actualizarPantalla();
}
