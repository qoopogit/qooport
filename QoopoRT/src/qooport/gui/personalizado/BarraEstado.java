package qooport.gui.personalizado;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import qooport.utilidades.SystemUtilities;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

/**
 *
 * @author alberto
 */
public class BarraEstado extends JPanel {

    private final JLabel estado = new JLabel();

    public BarraEstado() {
        setLayout(new BoxLayout(this, 2));
        setBorder(new EtchedBorder() {
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.translate(x, y);
                g.setColor(this.etchType == 1 ? getShadowColor(c) : getHighlightColor(c));
                g.drawLine(0, 0, width, 0);
                g.setColor(this.etchType == 1 ? getHighlightColor(c) : getShadowColor(c));
                g.drawLine(1, 1, width, 1);
                g.translate(-x, -y);
            }
        });
        add(Box.createHorizontalStrut(5));
        add(this.estado);
        add(Box.createHorizontalGlue());
    }

    public void limpiarEstado() {
        this.estado.setText(null);
    }

    public void setEstado(String message) {
        this.estado.setText(message);
    }

    public void agregar(Component componente) {
        agregarSeparador();
        add(componente);
    }

    public void agregarContador(ContadorBPS contador) {
        JLabel lbl = new JLabel("");
        if (contador.getLblEstado() != null) {
            lbl = contador.getLblEstado();
        }
        lbl.setHorizontalAlignment(0);
//        lbl.setSize(new Dimension(width, 10));
//        lbl.setPreferredSize(new Dimension(width, 10));
        lbl.setToolTipText(contador.getDescripcion());
        contador.setLblEstado(lbl);
        agregarSeparador();
        add(lbl);
    }

    public void agregarRamInfo() {
        final JLabel lbl = new JLabel();
        lbl.setIcon(Util.cargarIcono16("/resources/procesos.png"));
        lbl.setHorizontalAlignment(0);
        lbl.setToolTipText("Memoria ocupada / Memoria asignada [Maxima a ocupar]");
//        lbl.setSize(new Dimension(300, 10));
//        lbl.setPreferredSize(new Dimension(300, 10));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    lbl.setText(SystemUtilities.getRamInfo());
                }
            }
        }).start();
        agregarSeparador();
        add(lbl);
    }

    public void agregarSeparador() {
        JToolBar.Separator separator = new JToolBar.Separator();
        separator.setOrientation(1);
        add(separator);
    }
}
