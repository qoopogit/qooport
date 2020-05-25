package qooport.modulos.pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;

public class Chat extends JFrame {

    private Asociado servidor;
//    private JTabbedPane tabs;
    public JTextArea salida;
    private JTextField mensaje;
    private JToolBar barra;

    public Chat(Asociado servidor) {
        initComponents();
        this.servidor = servidor;
    }

    private void initComponents() {
//        tabs = new JTabbedPane();
        JPanel panel = new JPanel();
        JButton boton = new JButton();
        JButton botonAbrir = new JButton();
        JButton botonCerrar = new JButton();
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        boton.setIcon(Util.cargarIcono16("/resources/chat.png"));
        boton.setVisible(true);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Chat.this.btnEnviarComando(evt);
            }
        });
        botonAbrir.setIcon(Util.cargarIcono16("/resources/chat.png"));
        botonAbrir.setVisible(true);
        botonAbrir.setText("Abrir");
        botonAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Chat.this.btnAbrirChat(evt);
            }
        });
        botonCerrar.setIcon(Util.cargarIcono16("/resources/chat.png"));
        botonCerrar.setVisible(true);
        botonCerrar.setText("Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Chat.this.btnCerrarChat(evt);
            }
        });
        barra = new JToolBar();
        barra.setFloatable(false);
        barra.add(botonAbrir);
        barra.add(botonCerrar);
        salida = new JTextArea("");
        salida.setBackground(Color.WHITE);
        salida.setForeground(Color.BLUE);
//        salida.setFont(new Font("Lucida Console", 1, 12));
//        salida.setFont(new Font("Monospaced", 0, 11));
//         this.salida.append("\n$>");
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
        panel.add(barra, BorderLayout.NORTH);
        panel.add(jScrollPane1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
        //TABS
//        this.tabs.addTab("Chat", Util.cargarIcono16("/resources/chat.png"), panel        );
        this.setLayout(new BorderLayout());
//        this.add(tabs, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("Chat");
        this.setIconImage(Util.cargarIcono16("/resources/chat.png").getImage());
        pack();
    }

    private void btnEnviarComando(ActionEvent evt) {
        this.salida.setForeground(Color.BLUE);
        this.salida.append("Tu:" + mensaje.getText() + "\n");
        servidor.enviarMensajeChat(mensaje.getText());
        salida.setCaretPosition(salida.getDocument().getLength());
        mensaje.setText("");
    }

    private void btnAbrirChat(ActionEvent evt) {
        this.salida.append("<Ventana de chat abierta>\n");
        servidor.abrirChat();
        salida.setCaretPosition(salida.getDocument().getLength());
        mensaje.setText("");
    }

    private void btnCerrarChat(ActionEvent evt) {
        this.salida.append("<Ventana de chat cerrada>\n");
        servidor.cerarChat();
        salida.setCaretPosition(salida.getDocument().getLength());
        mensaje.setText("");
    }
}
