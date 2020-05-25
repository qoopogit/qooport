package qooport.gui.personalizado;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import qooport.modulos.reproductorCapturas.ReproductorCapturas;

/**
 * Clase usada en el visor de capturas offline
 *
 * @author alberto
 */
public class PantallaMiniatura extends JLabel implements MouseListener {

    private ReproductorCapturas ventana;
    private int indice;

    public PantallaMiniatura(ReproductorCapturas ventana, int indice) {
        this.addMouseListener(this);
        this.ventana = ventana;
        this.indice = indice;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        ventana.pantallaClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ventana.mostrarMiniatura(indice);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
