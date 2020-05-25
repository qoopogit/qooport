/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.gui.personalizado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelEspectrograma extends JPanel {

    private Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;
    private List<Double> datos;
    private int w;
    private int h;
    private static int x0 = 15;
    private int y0;
    private int intervalo;
    private int fin;
    private double escalaX;
    private double escalaY;
    private Color gridcolor;
    private Color ejescolor;
    private Color texto;
    private Color espectrogramac;
    private Color background;
    private Color player;
    private boolean dibujargrid;
    private boolean dibujarejes;
    private boolean dibujarespectrograma;
    private String informacion;

    public PanelEspectrograma(final int w, final int h) {
        super(new BorderLayout());
        background = Color.BLACK;
        gridcolor = new Color(120, 176, 145);
        ejescolor = Color.YELLOW;
        texto = Color.YELLOW;
        player = Color.RED;
        espectrogramac = new Color(85, 255, 255);
//        espectrogramac = Color.GREEN;
//        espectrogramac = Color.BLUE;
        area = new Dimension(0, 0);
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(background);
        //drawingPane.addMouseListener(this);
        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(w, h));
        add(scroller, BorderLayout.CENTER);
        datos = null;
        this.w = w;
        this.h = h;
        this.y0 = h / 2;
        intervalo = 1;
        fin = 0;
        escalaX = 1;
        escalaY = 1;
        dibujargrid = true;
        dibujarejes = true;
        dibujarespectrograma = false;
        informacion = null;
    }

    /**
     * The component inside the scroll pane.
     */
    class DrawingPane extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            w = getSize().width;
            h = getSize().height;
            y0 = h / 2;
            if (dibujargrid) {
                graficarGrid(g);
            }
            if (dibujarejes) {
                graficarEjes(g);
            }
            if (dibujarespectrograma) {
                graficarEscpectrograma(g);
            }

            if (informacion != null) {
                g.setColor(texto);
                g.drawString(informacion, 20, 20);
            }
        }
    }

    public void graficarGrid(Graphics g) {
        g.setColor(gridcolor);
        for (int i = 0; i < w; i += 15) {
            for (int j = 0; j < h; j += 40) {
                g.drawLine(i, j, i, h);
                g.drawLine(i, j, w, j);
            }
        }
    }

    public void graficarEjes(Graphics g) {
        g.setColor(ejescolor);
        linea(0, 0, w, 0, g);
        linea(0, y0, 0, -y0, g);
    }

//    public void graficarEscpectrograma2(Graphics g) {
//        //reiniciarDimension();
//        g.setColor(espectrogramac);
//        int length = datos.size();
//        int[] puntoP = new int[2];
//        int i, xi = 0, xf = 0, yi = 0, yf = 0;
//        for (i = 0; i < length - intervalo; i += intervalo) {
//            puntoP[0] = (int) (i * escalaX);
//            puntoP[1] = (int) (datos.get(i) * escalaY);
//            xi = puntoP[0];
//            yi = puntoP[1];
//            puntoP[0] = (int) ((i + intervalo) * escalaX);
//            puntoP[1] = (int) (datos.get(i + intervalo) * escalaY);
//            xf = puntoP[0];
//            yf = puntoP[1];
//            linea(xi, yi, xf, yf, g);
//        }
//        fin = xf;
//        if (xf > w || yf >h) {
//            area.width = xf + 100;
//            area.width = yf + 100;
//            drawingPane.setPreferredSize(area);
//            drawingPane.revalidate();
//        }
//    }
    public void graficarEscpectrograma(Graphics g) {
        //reiniciarDimension();
        g.setColor(espectrogramac);
        int length = datos.size();
        int[] puntoP = new int[2];
        int i, xi = 0, xf = 0, yi = 0, yf = 0;
        double mayorX = length;
        double mayory = 0;
        //primero recoro solo para saber el valor mayor
        for (i = 0; i < length - intervalo; i += intervalo) {
            if (datos.get(i) > mayory) {
                mayory = datos.get(i);
            }
        }
        mayory *= 2;//el eje de las y dibujo en negativo y positivo
        double factorX = 0.95 / mayorX;
        //double factorY=0.35 / mayory;
        double factorY = 0.75 / mayory;
        //tipo 1
        for (i = 0; i < length - intervalo; i += intervalo) {
//            puntoP[0] = (int) (i * this.getWidth() * factorX);
//            puntoP[1] = (int) (datos.get(i) * this.getHeight() * factorY);
//            xi = puntoP[0];
//            yi = puntoP[1];
//            puntoP[0] = (int) ((i + intervalo) * this.getWidth() * factorX);
//            puntoP[1] = (int) (datos.get(i + intervalo) * this.getHeight() * factorY);
//            xf = puntoP[0];
//            yf = puntoP[1];
//            linea(xi, yi, xf, yf, g);
        }

        //tipo2
        for (i = 0; i < length - intervalo; i += intervalo) {
            puntoP[0] = (int) (i * this.getWidth() * factorX);
            puntoP[1] = (int) (datos.get(i) * this.getHeight() * factorY);
            xi = puntoP[0];
            yi = puntoP[1];
            linea(xi, yi, xi, -yi, g);
        }

        fin = xf;
//        if (xf > w) {
//            area.width = xf + 100;
//            drawingPane.setPreferredSize(area);
//            drawingPane.revalidate();
//        }
    }

    public void save(File fichero) {
        BufferedImage imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = imagen.getGraphics();
        drawingPane.paint(g);
        // Escribimos la imagen en el archivo.
        try {
            ImageIO.write(imagen, "jpg", fichero);
        } catch (IOException e) {
        }
    }

    public void ajustarDimension() {
        area.width = 0;
        //area.height=0;
        drawingPane.setPreferredSize(area);
        drawingPane.revalidate();
    }

    public void setDibujargrid(boolean dibujargrid) {
        this.dibujargrid = dibujargrid;
    }

    public void setGridcolor(Color gridcolor) {
        this.gridcolor = gridcolor;
    }

    /**
     * Dibuja una linea desde (x1,y1) hasta (x2,y2).
     *
     * @param x1 Coordenada x del punto de origen.
     * @param y1 Coordenada y del punto de origen.
     * @param x2 Coordenada x del punto de destino.
     * @param y2 Coordenada y del punto de destino.
     */
    public void linea(double x1, double y1, double x2, double y2, Graphics g) {
        g.drawLine((int) Math.round(x1 + x0),
                (int) Math.round(y0 - y1),
                (int) Math.round(x2 + x0),
                (int) Math.round(y0 - y2));
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public void setDibujarejes(boolean dibujarejes) {
        this.dibujarejes = dibujarejes;
    }

    public void setDibujarespectrograma(boolean dibujarespectrograma) {
        this.dibujarespectrograma = dibujarespectrograma;
    }

    public void setDatos(List<Double> datos) {
        this.datos = datos;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public void setEscalaY(double escalaY) {
        this.escalaY = escalaY;
    }

    public void setEscalaX(double escalaX) {
        this.escalaX = escalaX;
    }

    public Color getTexto() {
        return texto;
    }

    public void setTexto(Color texto) {
        this.texto = texto;
    }

    public Color getEspectrogramac() {
        return espectrogramac;
    }

    public void setEspectrogramac(Color espectrogramac) {
        this.espectrogramac = espectrogramac;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getPlayer() {
        return player;
    }

    public void setPlayer(Color player) {
        this.player = player;
    }

}
