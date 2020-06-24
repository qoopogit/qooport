/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.proxy;

import comunes.Accion;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import network.ConexionServer;
import network.proxy.ConexionProxy;
import network.proxy.Conexiones;
import network.proxy.Redireccion;
import qooport.Inicio;
import qooport.gui.Infor;
import qooport.gui.personalizado.BarraEstado;
import qooport.utilidades.Perfil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class ModoProxy extends javax.swing.JFrame {

    private BarraEstado estado;
    private JTable tblPerfiles;
    private JScrollPane scrollTablaPerfiles;
    private JTable tblConexiones;
    private JScrollPane scrollTablaConexiones;
    private List<Perfil> perfiles;
    private Redireccion proxy;

    static class CeldaIcono extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                setIcon(Util.cargarIcono16("/resources/username.png"));
            } catch (Exception e) {
            }
        }
    }

    private void cargarArchivoPerfiles() {
        List<Perfil> tmp = new ArrayList<Perfil>();
        File archivo = new File("perfiles.dat");
        if (archivo.exists()) {
            try {
                tmp = (List) SerializarUtil.leerObjeto(new FileInputStream(archivo));
            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {
            }
        }
        perfiles = tmp;
    }

    private void actualizarListaPerfiles() {

        cargarArchivoPerfiles();

        for (int i = tblPerfiles.getRowCount() - 1; i > -1; i--) {
            ((DefaultTableModel) tblPerfiles.getModel()).removeRow(i);
        }
        String tipoConexion;
        int i;
        String version2;
        for (Perfil p : perfiles) {
            i = 0;
            tipoConexion = "N/A";
            try {
                i = (Integer) p.obtenerParametro("tipoConexion");
            } catch (Exception e) {
//                i = ConexionServer.TCP;
            }
            if (i == 0) {
                tipoConexion = "TCP";
            } else if (i == 1) {
                tipoConexion = "UDP";
            }

            try {
                version2 = Boolean.valueOf((String) p.obtenerParametro("version_objeto")) ? "SI" : "NO";
            } catch (Exception e) {
                version2 = "NO";
            }

            ((DefaultTableModel) tblPerfiles.getModel()).addRow(
                    new Object[]{
                        null,
                        (String) p.obtenerParametro("nombre"),
                        (String) p.obtenerParametro("puerto"),
                        tipoConexion,
                        version2

                    });
        }

    }

    private void limpiarTablaConexiones() {
        for (int i = tblConexiones.getRowCount() - 1; i > -1; i--) {
            ((DefaultTableModel) tblConexiones.getModel()).removeRow(i);
        }
    }

    private void actualizarConexiones() {
        limpiarTablaConexiones();
        for (ConexionProxy p : Conexiones.lista) {
            try {
                ((DefaultTableModel) tblConexiones.getModel()).addRow(
                        new Object[]{
                            null,
                            p.getConexionOrigen().getRemoteAddress().toString(),
                            p.getConexionOrigen().getRemoteAddress().toString().split(":")[1],
                            p.getConexionDestino().getRemoteAddress().toString(),
                            p.getConexionDestino().getRemoteAddress().toString().split(":")[1]
                        });
            } catch (Exception e) {
            }
        }

    }

    public ModoProxy(String title) throws HeadlessException {
        super(title);
        initComponents();

        //###############################################################################################################3
//                            Perfiles
//###############################################################################################################3
        this.scrollTablaPerfiles = new JScrollPane();
        tblPerfiles = new JTable();
        tblPerfiles.setShowGrid(false);
//        this.tabla.setComponentPopupMenu(this.menu);
        this.tblPerfiles.setAutoCreateRowSorter(true);
//        this.tblPerfiles.setFont(new Font(tipoLetra, 1, 14));
        this.tblPerfiles.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "Nombre", "Puerto", "Tipo", "Versión Objetos"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblPerfiles.getColumnModel().getColumn(0).setMinWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setMaxWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());

        this.scrollTablaPerfiles.setViewportView(this.tblPerfiles);

        this.jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollTablaPerfiles, BorderLayout.CENTER);

//###############################################################################################################3
//                            Conexiones
//###############################################################################################################3
        this.scrollTablaConexiones = new JScrollPane();
        tblConexiones = new JTable();
        tblConexiones.setShowGrid(false);
//        this.tabla.setComponentPopupMenu(this.menu);
        this.tblConexiones.setAutoCreateRowSorter(true);
//        this.tblPerfiles.setFont(new Font(tipoLetra, 1, 14));
        this.tblConexiones.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "Origen", "Puerto", "Destino", "Puerto"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblConexiones.getColumnModel().getColumn(0).setMinWidth(20);
        this.tblConexiones.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tblConexiones.getColumnModel().getColumn(0).setMaxWidth(20);
        this.tblConexiones.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());

        this.scrollTablaConexiones.setViewportView(this.tblConexiones);

        this.jPanel2.setLayout(new BorderLayout());
        jPanel2.add(scrollTablaConexiones, BorderLayout.CENTER);

//###############################################################################################################3
        this.btnIniciar.setIcon(Util.cargarIcono16("/resources/start.png"));
        this.btnDetener.setIcon(Util.cargarIcono16("/resources/stop_close.png"));

        lblTitulo.setIcon(Util.cargarIcono32("/resources/remote.png"));
        estado = new BarraEstado();
        estado.setEstado("Listo");
        this.setIconImage(Util.cargarIcono16("/resources/remote.png").getImage());
        this.setLocationRelativeTo(null);
//        txtMiIp.setText(getHostName() + "," + getIPLocales());
        //jTextField1.setText(getHostName() + ", " + getIPLocales());
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(estado, BorderLayout.SOUTH);
        this.jLed.setText("");
        jLed.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
        btnCambiar.setIcon(Util.cargarIcono16("/resources/switch.png"));
        cargarListaIPs();
        actualizarListaPerfiles();
        actualizarConexiones();

        Accion accion = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                actualizarConexiones();
            }
        };
        Conexiones.setAccion(accion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLed = new javax.swing.JLabel();
        txtLocalIP = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnCambiar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        itmAcercaDe = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 0));

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblTitulo.setText("QoopoRT - Proxy");

        jLabel1.setText("IP/HOST Destino:");

        jLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/led_gris.png"))); // NOI18N
        jLed.setText("jLabel2");

        txtLocalIP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Ip Local:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Conexiones"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Conexiones", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Puertos"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Puertos", jPanel1);
        jPanel1.getAccessibleContext().setAccessibleName("Redirección de puertos");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnCambiar.setText("Cambiar");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnDetener.setText("Detener");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetener, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCambiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDetener)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu5.setText("Ayuda");

        itmAcercaDe.setText("Acerca de");
        itmAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAcercaDeActionPerformed(evt);
            }
        });
        jMenu5.add(itmAcercaDe);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtLocalIP, 0, 250, Short.MAX_VALUE)
                                            .addComponent(txtHost)))
                                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1)
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String generarClave() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        int size = 6;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * letras.length());
            b.append(letras.charAt(index));
        }
//        this.txtClave.setText(b.toString());
        return b.toString();
    }

    public String getHostName() {
        InetAddress iAddress = null;
        try {
            iAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
        }
        String hostname = null;
//        if (iAddress != null) {
//            hostname = iAddress.getHostName();
//        }
//        if (hostname != null) {
//            return hostname;
//        }
        if (iAddress != null) {
            hostname = iAddress.getCanonicalHostName();
        }
        if (hostname != null) {
            return hostname;
        }
        hostname = System.getenv("COMPUTERNAME");
        if (hostname != null) {
            return hostname;
        }
        hostname = System.getenv("HOSTNAME");
        if (hostname != null) {
            return hostname;
        }
        return "N/A";
//        return this.conexion.getLocalAddress().getHostName();
    }

    public String getLocalIp() {
        InetAddress iAddress = null;
        try {
            iAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
        }
        String hostname = null;

        if (iAddress != null) {
            hostname = iAddress.getHostAddress();
        }
        if (hostname != null) {
            return hostname;
        }

        return "N/A";

    }

    private void cargarListaIPs() {
        //StringBuilder sb = new StringBuilder("");
        txtLocalIP.removeAllItems();
        //txtLocalIP.addItem(getHostName());
        InetAddress inet;
        txtLocalIP.addItem("0.0.0.0");
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                try {
                    NetworkInterface network = (NetworkInterface) e.nextElement();
                    Enumeration ee = network.getInetAddresses();
                    if (!network.isLoopback()) {//evito las interfaces loopback
                        while (ee.hasMoreElements()) {
                            InetAddress ipinet = (InetAddress) ee.nextElement();
                            if (ipinet instanceof Inet4Address) {
                                String ip = ipinet.getHostAddress();
                                txtLocalIP.addItem(ip);
                                //sb.append(ip).append(", ");
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } catch (SocketException ex) {

        }

    }

    private void itmAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAcercaDeActionPerformed
        Infor.ver();
    }//GEN-LAST:event_itmAcercaDeActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        Inicio.main(null);
        this.dispose();
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {

            System.out.println("inicia ejecucion");
            List<String> puertosUsados = new ArrayList<>();
            proxy = new Redireccion();
            proxy.limpiar();
            int tipoConexion = 0;

            if (perfiles != null && !perfiles.isEmpty()) {
                for (Perfil p : this.perfiles) {
                    boolean version2 = false;
                    try {
                        version2 = Boolean.valueOf((String) p.obtenerParametro("version_objeto"));
                    } catch (Exception e) {
                        version2 = false;
                    }
                    try {
                        int tipo = (int) p.obtenerParametro("tipoConexion");
                        if (tipo == 0) {
                            tipoConexion = ConexionServer.TCP;
                        } else if (tipo == 1) {
                            tipoConexion = ConexionServer.UDP;

                        }
                    } catch (Exception e) {
                        tipoConexion = ConexionServer.TCP;
                    }

                    if (tipoConexion == ConexionServer.TCP) {
                        if (Boolean.valueOf((String) p.obtenerParametro("inversa"))) {
                            try {
                                if (!puertosUsados.contains((String) p.obtenerParametro("puerto") + "|" + tipoConexion)) {
                                    proxy.agregar(
                                            txtLocalIP.getSelectedItem().toString(),
                                            Integer.valueOf((String) p.obtenerParametro("puerto")),
                                            txtHost.getText(),
                                            Integer.valueOf((String) p.obtenerParametro("puerto"))
                                    );
                                    puertosUsados.add((String) p.obtenerParametro("puerto") + "|" + tipoConexion);
                                }
                            } catch (Exception ex) {

                            }
//                            try {
//                                if (!puertosUsados.contains((String) p.obtenerParametro("puerto2") + "|" + tipoConexion)) {
//                                    proxy.agregar(
//                                            txtLocalIP.getSelectedItem().toString(),
//                                            Integer.valueOf((String) p.obtenerParametro("puerto2")),
//                                            txtHost.getText(),
//                                            Integer.valueOf((String) p.obtenerParametro("puerto2"))
//                                    );
//                                    puertosUsados.add((String) p.obtenerParametro("puerto2") + "|" + tipoConexion);
//
//                                }
//                            } catch (Exception ex) {
//
//                            }
                        }
                    }
                }

                proxy.iniciar();
                estado.setEstado("Proxy iniciado");
                jLed.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
                txtLocalIP.setEnabled(false);
                txtHost.setEnabled(false);
                btnIniciar.setEnabled(false);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        if (proxy != null) {
            proxy.detener();
            Conexiones.cerrarConexiones();
            actualizarConexiones();
            jLed.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
            estado.setEstado("Proxy detenido");
            txtHost.setEnabled(true);
            txtLocalIP.setEnabled(true);
            btnIniciar.setEnabled(true);
        }
    }//GEN-LAST:event_btnDetenerActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ModoSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModoSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModoSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModoSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModoSimple().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnDetener;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JMenuItem itmAcercaDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLed;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtHost;
    private javax.swing.JComboBox<String> txtLocalIP;
    // End of variables declaration//GEN-END:variables

}
