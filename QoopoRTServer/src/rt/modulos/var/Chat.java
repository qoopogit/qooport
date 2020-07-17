package rt.modulos.var;

import comunes.Interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import rt.util.Protocolo;

//chat
public class Chat extends JFrame {

    private Interfaz servicio;
    public JTextArea salida;
    private JTextField mensaje;

    public Chat(Interfaz servicio) {
        initComponents();
        this.servicio = servicio;
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        JButton boton = new JButton();
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        boton.setText("Enviar");
        boton.setVisible(true);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Chat.this.btnEnviarComando(evt);
            }
        });
        salida = new JTextArea("");
        salida.setBackground(Color.WHITE);
        salida.setForeground(Color.BLUE);
        mensaje = new JTextField();
        mensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Chat.this.btnEnviarComando(evt);
            }
        });
        jScrollPane1.setViewportView(this.salida);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(mensaje, BorderLayout.CENTER);
        panel2.add(boton, BorderLayout.EAST);
        panel.setLayout(new BorderLayout());
        panel.add(jScrollPane1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        } catch (Exception e) {
        }
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        pack();
    }

    private void btnEnviarComando(ActionEvent evt) {
        this.salida.append("Tu:" + mensaje.getText() + "\n");
        servicio.ejecutar(3, Protocolo.CHAT_MENSAJE, servicio.get(11) + ":" + mensaje.getText());
        salida.setCaretPosition(salida.getDocument().getLength());
        mensaje.setText("");
    }
}
