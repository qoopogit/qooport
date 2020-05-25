package comunes;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public abstract interface WebCamInterface {

    public abstract void ponerDefault();

    public abstract WebCamItem[] listar();

    public abstract void abrir();

    public abstract void cerrar();

    public abstract BufferedImage getImagen();

    public abstract void seleccionar(String nombreCam);

    public abstract Dimension[] listarResoluciones();

    public abstract void seleccionarDimension(Dimension size);

}
