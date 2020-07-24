/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos;

import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import qooport.asociado.Asociado;

/**
 *
 * @author alberto
 */
public abstract class VentanaReproductor extends JFrame implements KeyListener, WindowListener {

    //public static final int COLOR_BN = 1;
    public static final int COLOR_GRIS = 2;
    public static final int COLOR_8_BITS = 3;
    public static final int COLOR_16_BITS = 4;
    public static final int COLOR_24_BITS = 5;
    public static final int COLOR_32_BITS = 6;

    public static final int ESCALA_ORIGINAL = 0;
    public static final int ESCALA_PORCENTUAL = 1;
    public static final int ESCALA_VENTANA = 2;
    public static final int ESCALA_PERFECTO = 3;

    public abstract void mouseMove(float px, float py, int boton, int pantallaID);

    public abstract void mouseDragged(float px, float py, int boton, int pantallaID);

    public abstract void mousePresionado(float px, float py, int boton, int pantallaID);

    public abstract void mouseLiberado(float px, float py, int boton, int pantallaID);

    public abstract void mouseRueda(float px, float py, int boton, int unidadesScroll, int pantallaID);

    public abstract int getESCALA();

    public abstract Boolean isEscalarRemoto();

    public abstract Boolean isAjustarVentana();

    public abstract Boolean isEscalarSuave();

    public abstract Asociado getAgente();

    public abstract JTextField getTxtColumnas();

    public abstract JComboBox getCmbMonitor();

}
