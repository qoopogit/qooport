package comunes;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

public class ObtenerWebCam implements WebCamInterface {

    private Webcam webcam;

    public ObtenerWebCam() {
        webcam = Webcam.getDefault();
    }

    @Override
    public void ponerDefault() {
        webcam = Webcam.getDefault();
    }

    @Override
    public WebCamItem[] listar() {
        List<Webcam> tmp = Webcam.getWebcams();
        WebCamItem[] retorno = new WebCamItem[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            retorno[i] = new WebCamItem(i, tmp.get(i).getName());
        }
        return retorno;
    }

    @Override
    public Dimension[] listarResoluciones() {
        return webcam.getViewSizes();
    }

    @Override
    public void seleccionarDimension(Dimension size) {
        webcam.setViewSize(size);
    }

    @Override
    public void seleccionar(String nombreCam) {
        List<Webcam> tmp = Webcam.getWebcams();
        for (Webcam w : tmp) {
            if (w.getName().equals(nombreCam)) {
                webcam = w;
            }
        }
    }

    @Override
    public void abrir() {
        webcam.open();
    }

    @Override
    public void cerrar() {
        webcam.close();
    }

    @Override
    public BufferedImage getImagen() {
        return webcam.getImage();
    }

}
