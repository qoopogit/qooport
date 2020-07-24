/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import qooport.gui.personalizado.Pantalla;
import qooport.modulos.VentanaReproductor;

/**
 *
 * @author alberto
 *
 */
public class Contenedor extends JPanel {

    // 1 pantalla unica
    // 2 scroll
    // 3 varias pantallas a la vez
    private int tipoVista = 1;
    private Pantalla pantalla;
    private JScrollPane scrollPantalla;
    private List<Pantalla> pantallas = new ArrayList<Pantalla>();
    private VentanaReproductor ventana;

    public Contenedor(VentanaReproductor ventana) {
        this.ventana = ventana;
        iniciar();
    }

    public void iniciar() {
        //pongo el fondo negro
        this.setBackground(Color.BLACK);
        this.scrollPantalla = new JScrollPane();
        this.scrollPantalla.setBackground(new Color(255, 102, 51));
        this.scrollPantalla.setForeground(new Color(255, 204, 51));
        this.scrollPantalla.setDoubleBuffered(true);
        this.scrollPantalla.setWheelScrollingEnabled(false);//desactiva que capture los eventos scroll  DEL MOUSE
        this.pantalla = new Pantalla(ventana, 0);
        this.pantalla.setBackground(new Color(0, 0, 0));
        this.pantalla.setOpaque(true);
        this.pantalla.setVerticalAlignment(1);
        this.pantalla.setDoubleBuffered(true);
        this.scrollPantalla.setViewportView(this.pantalla);
        this.pantalla.setFocusTraversalKeysEnabled(false);
        this.scrollPantalla.setFocusTraversalKeysEnabled(false);
        modoUnico();
    }

    public void agregarKeyListener(KeyListener kl) {
        this.pantalla.addKeyListener(kl);
        scrollPantalla.addKeyListener(kl);
    }

    public void modoScroll() {
        this.setLayout(new GridLayout(1, 1));
        this.scrollPantalla.setViewportView(this.pantalla);
        this.removeAll();
        this.add(scrollPantalla);
        tipoVista = 2;
    }

    public void modoUnico() {
        this.setLayout(new GridLayout(1, 1));
        this.removeAll();
        this.add(pantalla);
        tipoVista = 1;
    }

    public void modoVariasPantallas() {
        int columnas = 2;
        try {
            columnas = Integer.parseInt(ventana.getTxtColumnas().getText());
        } catch (Exception e) {

        }
        int filas = Math.round((float) ventana.getCmbMonitor().getItemCount() / (float) columnas);
        this.setLayout(new GridLayout(filas, columnas));
        tipoVista = 3;
        pantallas.clear();
        this.removeAll();
        for (int i = 0; i < ventana.getCmbMonitor().getItemCount(); i++) {
            Pantalla p = new Pantalla(ventana, i);
            p.setBackground(new Color(0, 0, 0));
            p.setOpaque(true);
            p.setVerticalAlignment(1);
            p.setDoubleBuffered(true);
            p.setFocusTraversalKeysEnabled(false);
            //p.setBorder(BorderFactory.createTitledBorder("Pantalla -" + (i + 1)));
            pantallas.add(p);
        }

        for (Pantalla p : pantallas) {
            this.add(p);
        }

    }

    public int getAncho() {
        switch (tipoVista) {
            case 1://pantalla unica
                return pantalla.getWidth();
            case 2:
                return scrollPantalla.getWidth();
        }
        return this.getWidth();
    }

    public int getAlto() {
        switch (tipoVista) {
            case 1://pantalla unica
                return pantalla.getHeight();
            case 2:
                return scrollPantalla.getHeight();
        }
        return this.getHeight();
    }

    public Pantalla getPantalla(int monitor) {
        if (tipoVista == 3 && pantallas.size() > 0) {
            return pantallas.get(monitor);
        } else {
            return pantalla;
        }
    }

    public void setPantalla(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public JScrollPane getScrollPantalla() {
        return scrollPantalla;
    }

    public void setScrollPantalla(JScrollPane scrollPantalla) {
        this.scrollPantalla = scrollPantalla;
    }

    public void setIcono(ImageIcon icono) {
        pantalla.setIcon(icono);
    }

    public VentanaReproductor getVentana() {
        return ventana;
    }

    public void setVentana(VentanaReproductor ventana) {
        this.ventana = ventana;
    }

}
