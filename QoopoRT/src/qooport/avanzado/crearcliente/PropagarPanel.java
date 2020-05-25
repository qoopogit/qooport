/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.avanzado.crearcliente;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class PropagarPanel extends javax.swing.JPanel {

    /**
     * Creates new form conexion
     */
    public PropagarPanel() {
        initComponents();
        lblTitulo.setIcon(Util.cargarIcono16("/resources/virus.png"));

        this.txtExcluirUSB.setText("C:\\");
        this.txtExcluirUSB.setEnabled(false);
        
        
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
        jLabel3 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlUSB = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        checkUSB = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        nombreUSB = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtExcluirUSB = new javax.swing.JTextField();
        pnlLAN = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        chkLan = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        txtLanNombre = new javax.swing.JTextField();
        pnlSkype = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        chkSkype = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        skypeMensaje = new javax.swing.JTextArea();
        pnlMail = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        chkSkype1 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mailMensaje = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        mailAsunto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        mailNombre = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTitulo.setText("Propagación");

        jLabel3.setText("Configurar al cliente para que se propague automáticamente");

        jLabel2.setText("USB");

        checkUSB.setText("Copiar en dispositivos USB");
        checkUSB.setEnabled(false);

        jLabel4.setText("Nombre:");

        jLabel13.setText("Excluir:");

        javax.swing.GroupLayout pnlUSBLayout = new javax.swing.GroupLayout(pnlUSB);
        pnlUSB.setLayout(pnlUSBLayout);
        pnlUSBLayout.setHorizontalGroup(
            pnlUSBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUSBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUSBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlUSBLayout.createSequentialGroup()
                        .addComponent(checkUSB)
                        .addGap(0, 158, Short.MAX_VALUE))
                    .addGroup(pnlUSBLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreUSB))
                    .addGroup(pnlUSBLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtExcluirUSB)))
                .addContainerGap())
        );
        pnlUSBLayout.setVerticalGroup(
            pnlUSBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUSBLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkUSB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUSBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombreUSB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUSBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtExcluirUSB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tabs.addTab("USB", pnlUSB);

        jLabel7.setText("LAN");

        chkLan.setText("Copiar en recursos compartidos de la red LAN");
        chkLan.setEnabled(false);

        jLabel8.setText("Nombre:");

        javax.swing.GroupLayout pnlLANLayout = new javax.swing.GroupLayout(pnlLAN);
        pnlLAN.setLayout(pnlLANLayout);
        pnlLANLayout.setHorizontalGroup(
            pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLANLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLANLayout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(pnlLANLayout.createSequentialGroup()
                        .addGroup(pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLANLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLanNombre))
                            .addGroup(pnlLANLayout.createSequentialGroup()
                                .addGroup(pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkLan)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        pnlLANLayout.setVerticalGroup(
            pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLANLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkLan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLanNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("LAN", pnlLAN);

        jLabel5.setText("SKYPE");

        chkSkype.setText("Propapar por Skype");
        chkSkype.setEnabled(false);

        jLabel6.setText("Mensaje:");

        skypeMensaje.setColumns(20);
        skypeMensaje.setRows(3);
        jScrollPane1.setViewportView(skypeMensaje);

        javax.swing.GroupLayout pnlSkypeLayout = new javax.swing.GroupLayout(pnlSkype);
        pnlSkype.setLayout(pnlSkypeLayout);
        pnlSkypeLayout.setHorizontalGroup(
            pnlSkypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSkypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(pnlSkypeLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(pnlSkypeLayout.createSequentialGroup()
                        .addGroup(pnlSkypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(chkSkype))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSkypeLayout.setVerticalGroup(
            pnlSkypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSkype)
                .addGroup(pnlSkypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSkypeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addContainerGap(136, Short.MAX_VALUE))
                    .addGroup(pnlSkypeLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        tabs.addTab("SKYPE", pnlSkype);

        jLabel9.setText("Correo - Outlook");

        chkSkype1.setText("Propagar por Correo");
        chkSkype1.setEnabled(false);

        jLabel10.setText("Mensaje:");

        mailMensaje.setColumns(20);
        mailMensaje.setRows(3);
        jScrollPane2.setViewportView(mailMensaje);

        jLabel11.setText("Asunto:");

        jLabel12.setText("Nombre:");

        javax.swing.GroupLayout pnlMailLayout = new javax.swing.GroupLayout(pnlMail);
        pnlMail.setLayout(pnlMailLayout);
        pnlMailLayout.setHorizontalGroup(
            pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mailAsunto))
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addGroup(pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(chkSkype1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mailNombre)))
                .addContainerGap())
        );
        pnlMailLayout.setVerticalGroup(
            pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSkype1)
                .addGap(18, 18, 18)
                .addGroup(pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(mailAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(mailNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlMailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(56, 56, 56))
                    .addGroup(pnlMailLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        tabs.addTab("OUTLOOK", pnlMail);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(tabs)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkUSB;
    private javax.swing.JCheckBox chkLan;
    private javax.swing.JCheckBox chkSkype;
    private javax.swing.JCheckBox chkSkype1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField mailAsunto;
    private javax.swing.JTextArea mailMensaje;
    private javax.swing.JTextField mailNombre;
    private javax.swing.JTextField nombreUSB;
    private javax.swing.JPanel pnlLAN;
    private javax.swing.JPanel pnlMail;
    private javax.swing.JPanel pnlSkype;
    private javax.swing.JPanel pnlUSB;
    private javax.swing.JTextArea skypeMensaje;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField txtExcluirUSB;
    private javax.swing.JTextField txtLanNombre;
    // End of variables declaration//GEN-END:variables

    public JCheckBox getCheckUSB() {
        return checkUSB;
    }

    public void setCheckUSB(JCheckBox checkUSB) {
        this.checkUSB = checkUSB;
    }

    public JCheckBox getChkLan() {
        return chkLan;
    }

    public void setChkLan(JCheckBox chkLan) {
        this.chkLan = chkLan;
    }

    public JCheckBox getChkSkype() {
        return chkSkype;
    }

    public void setChkSkype(JCheckBox chkSkype) {
        this.chkSkype = chkSkype;
    }

    public JTextField getNombreUSB() {
        return nombreUSB;
    }

    public void setNombreUSB(JTextField nombreUSB) {
        this.nombreUSB = nombreUSB;
    }

    public JTextField getTxtLanNombre() {
        return txtLanNombre;
    }

    public void setTxtLanNombre(JTextField txtLanNombre) {
        this.txtLanNombre = txtLanNombre;
    }

    public JTextArea getTxtSkypeMensaje() {
        return skypeMensaje;
    }

    public void setTxtSkypeMensaje(JTextArea txtSkypeMensaje) {
        this.skypeMensaje = txtSkypeMensaje;
    }

}
