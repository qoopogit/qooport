package comunes;

public interface Interfaz {

    public void instanciar(Object... parametros);

    public void set(int opcion, Object valor);

    public Object get(int opcion, Object... parametros);

    public void ejecutar(int opcion, Object... parametros);

}
