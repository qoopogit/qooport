package qooport.modulos.pc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import qooport.asociado.Asociado;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;

public class TextoSpeak extends JFrame {

    private Asociado servidor;
//    private JTabbedPane tabs;

    private JTextArea texto;

    public TextoSpeak(Asociado servidor) {
        initComponents();
        this.servidor = servidor;
    }

    private void initComponents() {
//        tabs = new JTabbedPane();
        JPanel panel = new JPanel();
        JButton boton = new JButton();

        boton.setIcon(Util.cargarIcono16("/resources/sound.png"));
        boton.setVisible(true);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TextoSpeak.this.btnEnviarComando(evt);
            }
        });

//        salida.setFont(new Font("Lucida Console", 1, 12));
//        salida.setFont(new Font("Monospaced", 0, 11));
//         this.salida.append("\n$>");
        texto = new JTextArea();

//        texto.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                TextoSpeak.this.btnEnviarComando(evt);
//            }
//        });
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
//        panelInferior.add(texto, BorderLayout.CENTER);
        panelInferior.add(boton, BorderLayout.EAST);
        panel.setLayout(new BorderLayout());
        panel.add(texto, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);
        //TABS
//        this.tabs.addTab("TextSpeak", Util.cargarIcono16("/resources/sound.png"), panel);
        this.setLayout(new BorderLayout());
//        this.add(texto, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
//        this.setSize(600, 600);
//        this.setPreferredSize(new Dimension(600, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("TextSpeak");
        this.setIconImage(Util.cargarIcono16("/resources/sound.png").getImage());
        pack();

        GuiUtil.centrarVentana(this, 400, 150);
    }

    private void btnEnviarComando(ActionEvent evt) {
        servidor.enviarComando(Protocolo.SPEAK_TEXTO, texto.getText().replaceAll("\n", " "));
    }

}
