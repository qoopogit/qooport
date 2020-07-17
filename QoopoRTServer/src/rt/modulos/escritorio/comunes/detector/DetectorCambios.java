package rt.modulos.escritorio.comunes.detector;

import comunes.CapturaOpciones;
import comunes.PantallaBloque;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import rt.util.IMG;

/**
 *
 * @author alberto
 */
public abstract class DetectorCambios implements Serializable {

    protected CapturaOpciones opciones;

    protected float porDiferencia = 0; // porcentaje de diferencia para saber si aplico compresion jpg o no(envio  cambios)

    public abstract List<PantallaBloque> procesarCambios(BufferedImage imagen);

    public abstract void limpiar();

    public void liberar() {
        opciones = null;
        limpiar();
    }

    public CapturaOpciones getOpciones() {
        return opciones;
    }

    public void setOpciones(CapturaOpciones opciones) {
        this.opciones = opciones;
    }

    public float getPorDiferencia() {
        return porDiferencia;
    }

    public void setPorDiferencia(float porDiferencia) {
        this.porDiferencia = porDiferencia;
    }

    protected int[] obtenerInts(BufferedImage bi) throws IOException {
        try {
            return IMG.getIntArray(bi);
        } catch (Exception e) {
            return null;
        }
    }

    protected byte[] obtenerBytes(BufferedImage bi, float calidad) throws IOException {
        byte[] arrayByte = null;
        if (opciones.isConvertirJpg()) {
            arrayByte = IMG.saveImageJPGByte(bi, calidad);
        } else {
            arrayByte = IMG.getByteArray(bi);
        }
        return arrayByte;
    }

}
