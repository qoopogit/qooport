package rt.modulos.escritorio.comunes.detector;

import comunes.PantallaBloque;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import rt.util.IMG;

public class Completa extends DetectorCambios {

    private int hashAnterior;
    private int hashCode;

    @Override
    public List<PantallaBloque> procesarCambios(BufferedImage imagen) {
        List<PantallaBloque> lista = null;
        try {
            hashCode = IMG.hashCode(imagen, opciones.getTipoDatos());
            if (hashAnterior != hashCode) {
                lista = new ArrayList<PantallaBloque>();
                if (opciones.getTipoDatos() == 1) {
                    lista.add(new PantallaBloque("", 0, 0, imagen.getWidth(), imagen.getHeight(), obtenerBytes(imagen, opciones.getCalidad())));
                } else {
                    lista.add(new PantallaBloque("", 0, 0, imagen.getWidth(), imagen.getHeight(), obtenerInts(imagen)));
                }
                hashAnterior = hashCode;
            }
        } catch (Exception ex) {
        }
        return lista;
    }

    @Override
    public void limpiar() {
        hashAnterior = 0;
    }

}
