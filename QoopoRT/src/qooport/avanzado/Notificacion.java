package qooport.avanzado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JWindow;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

//public class Notificacion extends JDialog
public class Notificacion extends JWindow implements Runnable {

    int anchoVentana = 270;
    int ancho;
    int alto;
    public JLabel lblPais;
    public JLabel lblUsuario;
    public JLabel lblSo;
    public JLabel lblWC;
    private JDesktopPane pnlNotificacion;
    public String usuario;
    public String pais;
    public String so;
    public String bandera;
    public String wc;

    //public Notificacion(Frame parent, boolean modal) {
    public Notificacion() {
        //super(parent, modal);
        initComponents();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.ancho = d.width;
        this.alto = d.height;
        setLocation(this.ancho - 300, this.alto);
        //this.setTitle("Nueva conexión");
    }

    public void iniciar() {
        start();
    }

    public static void mostrar(final String usuario, final String so, final String pais, final String bandera, final String webCam) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Notificacion dialog = new Notificacion(new JFrame(), false);
                Notificacion dialog = new Notificacion();
                dialog.lblUsuario.setText(usuario);
                dialog.lblPais.setText(pais);
                dialog.lblSo.setText(so);
                dialog.lblWC.setText(webCam);
                try {
                    dialog.lblPais.setIcon(Util.cargarIcono16("/banderas/" + bandera + ".gif"));
                } catch (Exception e) {
                }
                if (webCam.contains("SI")) {
                    try {
                        dialog.lblWC.setIcon(Util.cargarIcono16("/resources/camera.png"));
                    } catch (Exception e) {
                    }
                } else if (webCam.contains("NO")) {
                    try {
                        dialog.lblWC.setIcon(Util.cargarIcono16("/resources/no-camera.png"));
                    } catch (Exception e) {
                    }
                } else //no hay plugin
                {
                    try {
                        dialog.lblWC.setIcon(Util.cargarIcono16("/resources/plugin_warning.png"));
                    } catch (Exception e) {
                    }
                }

                dialog.lblSo.setIcon(Util.iconoSistemaOperativo(so));
                //dialog.setVisible(true);
                dialog.iniciar();
            }
        });
    }

    public void start() {
        Thread m = new Thread(this);
        m.start();
    }

    private void initComponents() {
        JLabel titulo = new JLabel();
        this.pnlNotificacion = new JDesktopPane();
        this.lblUsuario = new JLabel();
        this.lblPais = new JLabel();
        this.lblSo = new JLabel();
        this.lblWC = new JLabel();
        //setDefaultCloseOperation(2);
        //setUndecorated(true);
        this.pnlNotificacion.setBackground(new Color(204, 255, 204));
        this.pnlNotificacion.setOpaque(true);
        this.lblUsuario.setFont(new Font(tipoLetra, 1, 10));
        this.lblUsuario.setHorizontalAlignment(0);
        this.lblUsuario.setText(usuario);
        this.lblUsuario.setBounds(5, 30, anchoVentana, 17);
        this.pnlNotificacion.add(this.lblUsuario, JLayeredPane.DEFAULT_LAYER);
        this.lblSo.setFont(new Font(tipoLetra, 1, 10));
        this.lblSo.setHorizontalAlignment(0);
        this.lblSo.setText(so);
        this.lblSo.setBounds(5, 55, anchoVentana, 17);
        this.pnlNotificacion.add(this.lblSo, JLayeredPane.DEFAULT_LAYER);
        this.lblPais.setFont(new Font(tipoLetra, 1, 10));
        this.lblPais.setHorizontalAlignment(0);
        this.lblPais.setText(pais);
//         lblPais.setIcon(Util.cargarIcono16("/banderas/" + bandera + ".gif")));
        this.lblPais.setBounds(5, 80, anchoVentana, 17);
        /// fondo
        this.pnlNotificacion.add(this.lblPais, JLayeredPane.DEFAULT_LAYER);
        this.lblWC.setFont(new Font(tipoLetra, 1, 10));
        this.lblWC.setHorizontalAlignment(0);
        this.lblWC.setText(wc);
        this.lblWC.setBounds(5, 100, anchoVentana, 17);
        this.pnlNotificacion.add(this.lblWC, JLayeredPane.DEFAULT_LAYER);
        titulo.setFont(new Font(tipoLetra, 1, 10));
        titulo.setForeground(new Color(255, 255, 255));
        titulo.setHorizontalAlignment(0);
        titulo.setText("Nueva conexión");
        titulo.setBounds(0, 0, anchoVentana, 20);
        titulo.setBackground(Color.darkGray);
        titulo.setOpaque(true);
        pnlNotificacion.add(titulo, JLayeredPane.DEFAULT_LAYER);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(pnlNotificacion, BorderLayout.CENTER);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.pnlNotificacion, -1, anchoVentana, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.pnlNotificacion, -1, 140, 32767));
        setVisible(true);
        pack();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 140; i += 10) {
                setLocation(this.ancho - anchoVentana, this.alto - i);
                setAlwaysOnTop(true);
                repaint();
                //pack();
                Thread.sleep(100L);
            }
            Thread.sleep(4000L);
            for (int i = 140; i >= 0; i -= 10) {
                setLocation(this.ancho - anchoVentana, this.alto - i);
                setAlwaysOnTop(true);
                repaint();
                Thread.sleep(100L);
            }
            dispose();
        } catch (InterruptedException ex) {
            dispose();
        }
    }
}
