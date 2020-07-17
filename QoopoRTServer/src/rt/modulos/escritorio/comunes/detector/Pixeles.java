package rt.modulos.escritorio.comunes.detector;

import comunes.PantallaBloque;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import rt.util.IMG;

/**
 * Detecta los cambios de pantalla a nivel de pixeles
 *
 * @author alberto
 */
public class Pixeles extends DetectorCambios {

    private BufferedImage anterior = null;//usada para detectar los pixeles que cambiaron, en el metodo de envio cambios

    @Override
    public List<PantallaBloque> procesarCambios(BufferedImage imagen) {
        List<PantallaBloque> lista = null;
        try {
            BufferedImage diferencia = obtenerCambios(anterior, imagen);
            lista = new ArrayList<PantallaBloque>();
            if (opciones.getTipoDatos() == 1) {
                byte[] datos;
                if (porDiferencia > 90) {
                    datos = obtenerBytes(diferencia, opciones.getCalidad());//solo es es la imagen completa comprimo en jpg (primer envio)
                } else {
                    datos = obtenerBytes(diferencia, 1);// le mando con calidad 100% (sincompresion jpg) para q no se ensucie la imagen
                }
                lista.add(new PantallaBloque("", 0, 0, imagen.getWidth(), imagen.getHeight(), datos));
            } else {
                lista.add(new PantallaBloque("", 0, 0, imagen.getWidth(), imagen.getHeight(), obtenerInts(diferencia)));
            }
            //anterior = imagen;
            anterior = IMG.clonar(imagen);
        } catch (Exception ex) {
        }
        return lista;
    }

    private BufferedImage obtenerCambios(BufferedImage img1, BufferedImage img2) {
        if (img1 == null || img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            porDiferencia = 100;
            return img2;
        }
        int i_ancho = img2.getWidth();
        int i_alto = img2.getHeight();
        porDiferencia = 0;
        BufferedImage img = null;
        if (opciones.getTipoDatos() == 1) {
            img = new BufferedImage(i_ancho, i_alto, BufferedImage.TYPE_4BYTE_ABGR); // este formato es para la transparencia
        } else {
            img = new BufferedImage(i_ancho, i_alto, BufferedImage.TYPE_INT_ARGB); // este formato es para la transparencia
        }
        for (int pX = 0; pX < i_ancho; pX++) {
            for (int pY = 0; pY < i_alto; pY++) {
                int rgb1 = img1.getRGB(pX, pY);
                int rgb2 = img2.getRGB(pX, pY);
                if (rgb1 != rgb2) {
                    porDiferencia++;
                    img.setRGB(pX, pY, rgb2);
                }
            }
        }
        porDiferencia = (porDiferencia * 100) / (i_ancho * i_alto);
        return img;
    }

    @Override
    public void limpiar() {
        anterior = null;
    }
}
