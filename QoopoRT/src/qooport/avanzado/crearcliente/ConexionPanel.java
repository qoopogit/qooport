/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.avanzado.crearcliente;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class ConexionPanel extends javax.swing.JPanel {

    /**
     * Creates new form conexion
     */
    public ConexionPanel() {
        initComponents();

        lblTitulo.setIcon(Util.cargarIcono16("/resources/connect.png"));
        conexionInversa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIpDNS.setEnabled(conexionInversa.isSelected());
            }
        });

        this.delay.setFont(new Font(tipoLetra, 1, 10));
        this.delay.setMajorTickSpacing(1);
        this.delay.setMaximum(20);
        this.delay.setMinimum(1);
        this.delay.setMinorTickSpacing(1);
        this.delay.setPaintLabels(true);
        this.delay.setPaintTicks(true);
        this.delay.setValue(3);
        this.delay.setValueIsAdjusting(true);
        this.prefijo.setFont(new Font(tipoLetra, 1, 11));
        this.prefijo.setText("serv_");
        this.jLabel1.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel2.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel10.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel3.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel4.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel5.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel7.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel8.setFont(new Font(tipoLetra, 1, 11));
        this.jLabel9.setFont(new Font(tipoLetra, 1, 11));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        comboProtocolo = new javax.swing.JComboBox<>();
        chkSSL = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        conexionInversa = new javax.swing.JRadioButton();
        conexionDirecta = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtIpDNS = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        puerto = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        btnVerPass = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        prefijo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        delay = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTitulo.setText("Conexión");

        jLabel2.setText("Protocolo:");

        comboProtocolo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TCP" }));

        chkSSL.setText("SSL");

        jLabel3.setText("Tipo conexión:");

        conexionInversa.setText("Conexión Inversa");

        conexionDirecta.setText("Conexión Directa");

        jLabel4.setText("IP/Host:");

        txtIpDNS.setToolTipText("Puede registrar varios host separados por ;");

        jLabel5.setText("Puerto:");

        jLabel7.setText("Constraseña:");

        password.setText("jPasswordField1");

        btnVerPass.setText("Ver");
        btnVerPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPassActionPerformed(evt);
            }
        });

        jLabel8.setText("Prefijo Cliente:");

        jLabel9.setText("Delay intento conexión");
        jLabel9.setToolTipText("Tiempo de espera para reconectar en segundos");

        delay.setMaximum(60);
        delay.setValue(3);

        jLabel10.setText("Conexión segura:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(305, 305, 305))
                    .addComponent(delay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIpDNS)
                            .addComponent(puerto))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(password)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerPass))
                            .addComponent(prefijo))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboProtocolo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(conexionInversa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(conexionDirecta))
                                    .addComponent(chkSSL))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSSL)
                    .addComponent(jLabel10))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(conexionInversa)
                    .addComponent(conexionDirecta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIpDNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(prefijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPassActionPerformed
        JOptionPane.showMessageDialog(null, new String(this.password.getPassword()));
    }//GEN-LAST:event_btnVerPassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerPass;
    private javax.swing.JCheckBox chkSSL;
    private javax.swing.JComboBox<String> comboProtocolo;
    private javax.swing.JRadioButton conexionDirecta;
    private javax.swing.JRadioButton conexionInversa;
    private javax.swing.JSlider delay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField prefijo;
    private javax.swing.JSpinner puerto;
    private javax.swing.JTextField txtIpDNS;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnVerPass() {
        return btnVerPass;
    }

    public void setBtnVerPass(JButton btnVerPass) {
        this.btnVerPass = btnVerPass;
    }

    public JCheckBox getChkSSL() {
        return chkSSL;
    }

    public void setChkSSL(JCheckBox chkSSL) {
        this.chkSSL = chkSSL;
    }

    public JComboBox<String> getComboProtocolo() {
        return comboProtocolo;
    }

    public void setComboProtocolo(JComboBox<String> comboProtocolo) {
        this.comboProtocolo = comboProtocolo;
    }

    public JRadioButton getConexionDirecta() {
        return conexionDirecta;
    }

    public void setConexionDirecta(JRadioButton conexionDirecta) {
        this.conexionDirecta = conexionDirecta;
    }

    public JRadioButton getConexionInversa() {
        return conexionInversa;
    }

    public void setConexionInversa(JRadioButton conexionInversa) {
        this.conexionInversa = conexionInversa;
    }

    public JSlider getDelay() {
        return delay;
    }

    public void setDelay(JSlider delay) {
        this.delay = delay;
    }

    public JTextField getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(JTextField prefijo) {
        this.prefijo = prefijo;
    }

    public JSpinner getPuerto() {
        return puerto;
    }

    public void setPuerto(JSpinner puerto) {
        this.puerto = puerto;
    }

//    public JSpinner getPuerto2() {
//        return puerto2;
//    }
//
//    public void setPuerto2(JSpinner puerto2) {
//        this.puerto2 = puerto2;
//    }
    public JTextField getTxtIpDNS() {
        return txtIpDNS;
    }

    public void setTxtIpDNS(JTextField txtIpDNS) {
        this.txtIpDNS = txtIpDNS;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

}