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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelMonitorCarga extends JPanel {

    private Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;
    private List< List<Double>> datos;
    private long maximoTamanio = -1;//al numero maximo de datos q contener en el arreglo
    private int w;
    private int h;
    private static int x0 = 15;
    private int y0 = 0;
    private int intervalo;
    private int fin;
    private double escalaX;
    private double escalaY;
    private Color gridcolor;
    private Color ejescolor;
    private Color texto;
    private Map<Integer, Color> coloresSerie;
    private Color background;
    private Color player;
    private boolean dibujargrid;
    private boolean dibujarejes;
    private boolean dibujarespectrograma;
    private String informacion;

    private double valorMayor = Double.MIN_VALUE;

    public PanelMonitorCarga(final int w, final int h) {
        super(new BorderLayout());
        background = Color.BLACK;
//        gridcolor = new Color(120, 176, 145);
        gridcolor = Color.DARK_GRAY;
        ejescolor = Color.YELLOW;
        texto = Color.YELLOW;
        player = Color.RED;

        coloresSerie = new HashMap<>();

//        colorSerie = Color.GREEN;
//        colorSerie = Color.BLUE;
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
        //this.y0 = h / 2;
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
            //y0 = h / 2;
            if (dibujargrid) {
                graficarGrid(g);
            }
            if (dibujarejes) {
                graficarEjes(g);
            }
            if (dibujarespectrograma) {
                for (int i = 0; i < datos.size(); i++) {
                    graficarDatos(i, g);
                }
            }

            if (informacion != null) {
                g.setColor(texto);
                g.drawString(informacion, 20, 20);
            }
        }
    }

    public void graficarGrid(Graphics g) {
        g.setColor(gridcolor);
        for (int i = 0; i < w; i += 12) {
            for (int j = 0; j < h; j += 12) {
                g.drawLine(i, j, i, h);
                g.drawLine(i, j, w, j);
//                linea(i, j, i, h, g);
//                linea(i, j, w, j, g);
            }
        }
    }

    public void graficarEjes(Graphics g) {
        g.setColor(ejescolor);
        linea(0, h, w, h, g);//horizontal
        linea(0, 0, 0, h, g);//vertical
    }

    //retorna la coordenada x inicial q debe tener, esto es util cuadno comienza a dibujar y debe verse mover el grafico
    public int getDiferencialX(int serie) {
        int length = datos.get(serie).size();
        double mayorX = length;
        if (maximoTamanio > 0) {
            mayorX = maximoTamanio;
        }

        double factorX = 1 / mayorX;

        int dx = (int) (mayorX - length);
        dx = (int) (dx * this.getWidth() * factorX);
        return dx;

    }

    public void graficarDatos(int serie, Graphics g) {
        //reiniciarDimension();

        if (coloresSerie.isEmpty()) {
            //agrega color default si no se especifica
            coloresSerie.put(0, new Color(85, 255, 255));
        }
        if (coloresSerie.containsKey(serie)) {
            g.setColor(coloresSerie.get(serie));
        } else {
            g.setColor(coloresSerie.get(0));
        }
        int length = datos.get(serie).size();
        int[] puntoP = new int[2];
        int i, xi = 0, xf = 0, yi = 0, yf = 0;
        double mayorX = length;

        double mayory = 0;

        if (maximoTamanio > 0) {
            mayorX = maximoTamanio;
        }

        if (valorMayor == Double.MIN_VALUE) {
            //primero recoro solo para saber el valor mayor
            for (i = 0; i < length - intervalo; i += intervalo) {
                if (datos.get(serie).get(i) > mayory) {
                    mayory = datos.get(serie).get(i);
                }
            }
        } else {
            mayory = valorMayor;
        }

        double factorX = 1 / mayorX;
        double factorY = 1 / mayory;
        //tipo 1
        for (i = 0; i < length - intervalo; i += intervalo) {
            puntoP[0] = getDiferencialX(serie) + (int) (i * this.getWidth() * factorX);
            puntoP[1] = (int) ((mayory - datos.get(serie).get(i)) * (this.getHeight() - y0) * factorY);
            xi = puntoP[0];
            yi = puntoP[1];
            puntoP[0] = getDiferencialX(serie) + (int) ((i + intervalo) * this.getWidth() * factorX);
            puntoP[1] = (int) ((mayory - datos.get(serie).get(i + intervalo)) * (this.getHeight() - y0) * factorY);
            xf = puntoP[0];
            yf = puntoP[1];
            linea(xi, yi, xf, yf, g);
        }

        //tipo2
//        for (i = 0; i < length - intervalo; i += intervalo) {
//            puntoP[0] = (int) (i * this.getWidth() * factorX);
//            puntoP[1] = (int) (datos.get(i) * this.getHeight() * factorY);
//            xi = puntoP[0];
//            yi = puntoP[1];
//            linea(xi, yi, xi, -yi, g);
//        }
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
        g.drawLine(
                (int) Math.round(x1 + x0),
                (int) Math.round(y1 - y0),
                (int) Math.round(x2 + x0),
                (int) Math.round(y2 - y0));
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

    public void agregarDato(Double dato) {
        datos.get(0).add(dato);
        if (maximoTamanio > 0) {
            if (datos.size() >= maximoTamanio) {
                datos.remove(0);
            }
        }
    }

    public void agregarDato(int serie, Double dato) {
        datos.get(serie).add(dato);
        if (maximoTamanio > 0) {
            if (datos.size() >= maximoTamanio) {
                datos.remove(0);
            }
        }
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

    public void setColorSerie(int serie, Color colorSerie) {
        coloresSerie.put(serie, colorSerie);
    }

    public void setColorSerie(Color colorSerie) {
        coloresSerie.put(0, colorSerie);
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

    public double getValorMayor() {
        return valorMayor;
    }

    public void setValorMayor(double valorMayor) {
        this.valorMayor = valorMayor;
    }

    public long getMaximoTamanio() {
        return maximoTamanio;
    }

    public void setMaximoTamanio(long maximoTamanio) {
        this.maximoTamanio = maximoTamanio;
    }

    public void iniciarSeries(int series) {
        if (series > 0) {
            datos = new ArrayList<>();
            for (int i = 0; i < series; i++) {
                datos.add(new ArrayList<Double>());
            }
        }
    }
}
