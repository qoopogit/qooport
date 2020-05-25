package comunes;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Prueba {

    private static String OSArch = System.getProperty("os.arch").toLowerCase();

    public static void main(String[] args) {
        try {
            JLabel icon = new JLabel();

            System.out.println("Arquitectura:" + OSArch);
            JFrame frame = new JFrame("Prueba Cursor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(icon);
            frame.setSize(200, 200);

            frame.setPreferredSize(new Dimension(200, 200));
            frame.pack();
            frame.setVisible(true);

            Interfaz util = ((Interfaz) Class.forName("plugin.jna.JnaUtil").newInstance());
            while (true) {
//            System.out.println("Texto ventana fondo:" + util.getTituloVentanaActual());
//            System.out.println("Cursor type=" + util.getTipoCursor());
                Punto p = (Punto) util.get(1);
                if (p != null) {
                    System.out.println("X=" + p.x + " Y=" + p.y);
                }
            //dibuja el cursor
                try {
                    icon.setIcon(new ImageIcon((Image) util.get(3)));
                    icon.repaint();
                } catch (Exception e) {

                }
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//            }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
