package qooport.gui.personalizado;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import qooport.modulos.escritorioremoto.EscritorioRemoto;
import static qooport.modulos.escritorioremoto.EscritorioRemoto.ESCALA_PERFECTO;
import static qooport.modulos.escritorioremoto.EscritorioRemoto.ESCALA_VENTANA;
import qooport.utilidades.ImagenEscritorio;
import qooport.utilidades.QoopoIMG;
import qooport.utilidades.Util;

public class Pantalla extends JLabel implements
        KeyListener,
        MouseListener,
        MouseMotionListener,
        MouseWheelListener {

    private EscritorioRemoto ventana;
    private BufferedImage imagen;
    private int pantallaID;
    private int cx = 0, cy = 0;

    public Pantalla(final EscritorioRemoto ventana, int pantallaID) {
        this.ventana = ventana;
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.pantallaID = pantallaID;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ventana.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ventana.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ventana.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        ventana.pantallaClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ventana.mousePresionado(posicionCursorX(e.getX()), posicionCursorY(e.getY()), getTipButton(e.getButton()), pantallaID);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ventana.mouseLiberado(posicionCursorX(e.getX()), posicionCursorY(e.getY()), getTipButton(e.getButton()), pantallaID);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ventana.mouseDragged(posicionCursorX(e.getX()), posicionCursorY(e.getY()), getTipButton(e.getButton()), pantallaID);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ventana.mouseMove(posicionCursorX(e.getX()), posicionCursorY(e.getY()), getTipButton(e.getButton()), pantallaID);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        e.consume();
        ventana.mouseRueda(posicionCursorX(e.getX()), posicionCursorY(e.getY()), getTipButton(e.getButton()), e.getUnitsToScroll(), pantallaID);
    }

    //------------------------------------------------------------------------------------------------------
    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    private void ajustarVentanaAimagen() {
        Icon icono = new ImageIcon(imagen);
        this.setIcon(icono);
        this.setSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
        this.setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
        this.repaint();
        this.repaint();
        ventana.pack();
    }

    private void ajustarPerfecto() {
        if (ventana.getItmEscalarRemoto().isSelected()) {
            //si fue escalado remotamente solo ajustamos la ventana
            ajustarVentanaAimagen();
        } else {
            ImagenEscritorio tmp = QoopoIMG.ajustarEscritorio(imagen, ventana.getItmEscalarSuave().isSelected(), this.getWidth(), this.getHeight());
            imagen = tmp.getImagen();
            cx = tmp.getCx();
            cy = tmp.getCy();
            tmp = null;
            Icon icono = new ImageIcon(imagen);
            this.setIcon(icono);
        }
    }

    public void pintar() {
        try {
            if (imagen == null) {
                return;
            }
            cx = 0;
            cy = 0;
            Icon icono = null;
            if (ventana.getItmAjustarVentana().isSelected()) {
                //ajustar ventana al tamaño de la imagen
                ajustarVentanaAimagen();
            } else {
                //ajustar imagen al tamaño de la ventana
                try {
                    switch (ventana.getESCALA()) {
                        case ESCALA_VENTANA:
                            //escala la imagen al tamaño de la ventana
                            icono = new ImageIcon(QoopoIMG.escalar(imagen, 2, 0, ventana.getItmEscalarSuave().isSelected(), this.getWidth(), this.getHeight()));
                            this.setIcon(icono);
                            break;
                        case ESCALA_PERFECTO:
                            ajustarPerfecto();
                            break;
                        default: //dibuja tal cual
                            icono = new ImageIcon(imagen);
                            this.setIcon(icono);
                            break;
                    }
                } catch (OutOfMemoryError E) {
                    Util.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            icono = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float posicionCursorX(int x) {
        float px = 0;
        if (cx != 0) {
            px = (x - cx) * 100f / (this.getWidth() - cx * 2);
        } else {
            px = x * 100f / this.getWidth();
        }
        return px;
    }

    private float posicionCursorY(int y) {
        float py = 0;
        if (cy != 0) {
            py = (y - cy) * 100f / (this.getHeight() - cy * 2);
        } else {
            py = y * 100f / this.getHeight();
        }
        return py;
    }

    private int getTipButton(int button) {
        switch (button) {
            case 1:
                return 1024;
            case 2:
                return 2048;
            case 3:
                return 4096;
        }
        return 0;
    }

}
