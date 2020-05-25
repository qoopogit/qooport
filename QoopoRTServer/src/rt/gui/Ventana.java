package rt.gui;

import comunes.Interfaz;

import rt.util.CLRT;

public class Ventana extends javax.swing.JFrame implements Interfaz {

    public Ventana() {
        initComponents();
        this.setTitle("Qoopo RT Servidor");
    }

    private boolean corriendo = false;

    private Interfaz conexion;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInicia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPuerto1 = new javax.swing.JTextField();
        txtPuerto2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInicia.setText("Iniciar");
        btnInicia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciaActionPerformed(evt);
            }
        });

        jLabel1.setText("IIP/DNS:");

        jLabel2.setText("Puerto 1:");

        jLabel3.setText("Puerto 2:");

        jLabel4.setText("Estado:");

        lblEstado.setText("Desconectado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInicia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIp)
                            .addComponent(txtPuerto1)
                            .addComponent(txtPuerto2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPuerto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPuerto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInicia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciaActionPerformed

        corriendo = !corriendo;
        if (corriendo) {

            try {
                CLRT cl = new CLRT();
                conexion = ((Interfaz) cl.loadClass("rt.Servicio").newInstance());
                conexion.instanciar(Ventana.this.txtIp.getText(),
                        Integer.valueOf(Ventana.this.txtPuerto1.getText()),
                        Integer.valueOf(Ventana.this.txtPuerto2.getText()), null, 3000,
                        "Gui_",
                        false, 3000, 1);
                conexion.ejecutar(0);
                lblEstado.setText("Conectando...");
                this.repaint();
                Thread.sleep(2500);
                btnInicia.setText("Detener");
                if ((Boolean) conexion.get(1)) {
                    lblEstado.setText("Conectado");
                } else {
                    lblEstado.setText("Desconectado");
                    btnInicia.setText("Iniciar");
                }
            } catch (Exception ex) {
            }
        } else {
            conexion.ejecutar(1);
            conexion = null;
            btnInicia.setText("Iniciar");
            lblEstado.setText("Desconectado");
        }

    }//GEN-LAST:event_btnIniciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPuerto1;
    private javax.swing.JTextField txtPuerto2;
    // End of variables declaration//GEN-END:variables

//    public void iniciar(String host, int puerto, int puertoTransferencia, String password, int delay, String prefijo, boolean capturaOffline, int delayEntreFoto, int tipoConexion) {
//        this.setVisible(true);
//        this.txtIp.setText(host);
//        this.txtPuerto1.setText(String.valueOf(puerto));
//        this.txtPuerto2.setText(String.valueOf(puertoTransferencia));
//    }
    public void instanciar(Object... parametros) {
        //String host, int puerto, int puertoTransferencia, String password, int delay, String prefijo, boolean capturaOffline, int delayEntreFoto, int tipoConexion
        this.setVisible(true);
        this.txtIp.setText((String) parametros[0]);
        this.txtPuerto1.setText((String) parametros[1]);
        this.txtPuerto2.setText((String) parametros[2]);
    }

    public void set(int opcion, Object valor) {

    }

    public Object get(int opcion, Object... parametros) {
        return null;
    }

    public void ejecutar(int opcion, Object... parametros) {

    }
}
