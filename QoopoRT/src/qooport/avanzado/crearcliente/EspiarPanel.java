/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.avanzado.crearcliente;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class EspiarPanel extends javax.swing.JPanel {

    /**
     * Creates new form conexion
     */
    public EspiarPanel() {
        initComponents();

        lblTitulo.setIcon(Util.cargarIcono16("/resources/spy.png"));

//        this.capturaOfflinePantalla.setIcon(Util.cargarIcono16("/resources/screen.png"));
        lblDelayCaptura.setIcon(Util.cargarIcono16("/resources/timer.png"));
        this.capturaOfflinePantalla.setEnabled(true);
        this.capturaOfflinePantalla.setSelected(false);
        this.delayCapturaPantalla.setFont(new Font(tipoLetra, 1, 10));
        this.delayCapturaPantalla.setMajorTickSpacing(1);
        this.delayCapturaPantalla.setMaximum(60);
        this.delayCapturaPantalla.setMinimum(1);
        this.delayCapturaPantalla.setMinorTickSpacing(1);
        this.delayCapturaPantalla.setPaintLabels(false);
        this.delayCapturaPantalla.setPaintTicks(true);
        this.delayCapturaPantalla.setValue(3);
        this.delayCapturaPantalla.setValueIsAdjusting(true);
        delayCapturaPantalla.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblDelayCaptura.setText("Delay entre capturas " + delayCapturaPantalla.getValue() + " segundos");
            }
        });

        //--------------------------------------------------------
        lblDelayCapWC.setIcon(Util.cargarIcono16("/resources/timer.png"));
        this.capturaOfflineWebCam.setText("Capturas de Cámara");
//        this.capturaOfflinePantalla.setIcon(Util.cargarIcono16("/resources/screen.png"));
        this.capturaOfflineWebCam.setEnabled(true);
        this.capturaOfflineWebCam.setSelected(false);
        this.delayCapturaWebCam.setFont(new Font(tipoLetra, 1, 10));
        this.delayCapturaWebCam.setMajorTickSpacing(1);
        this.delayCapturaWebCam.setMaximum(60);
        this.delayCapturaWebCam.setMinimum(1);
        this.delayCapturaWebCam.setMinorTickSpacing(1);
        this.delayCapturaWebCam.setPaintLabels(false);
        this.delayCapturaWebCam.setPaintTicks(true);
        this.delayCapturaWebCam.setValue(3);
        this.delayCapturaWebCam.setValueIsAdjusting(true);
        delayCapturaWebCam.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblDelayCapWC.setText("Delay entre capturas " + delayCapturaWebCam.getValue() + " segundos");
            }
        });

        //-----------------------------------------------------------
        lblDelayAudio.setIcon(Util.cargarIcono16("/resources/timer.png"));
        this.capturaOfflineAudio.setText("Capturas de Audio del micrófono");
//        this.capturaOfflinePantalla.setIcon(Util.cargarIcono16("/resources/screen.png"));
        this.capturaOfflineAudio.setEnabled(true);
        this.capturaOfflineAudio.setSelected(false);
        this.delayCapturaAudio.setFont(new Font(tipoLetra, 1, 10));
        this.delayCapturaAudio.setMajorTickSpacing(1);
        this.delayCapturaAudio.setMaximum(60);
        this.delayCapturaAudio.setMinimum(1);
        this.delayCapturaAudio.setMinorTickSpacing(1);
        this.delayCapturaAudio.setPaintLabels(false);
        this.delayCapturaAudio.setPaintTicks(true);
        this.delayCapturaAudio.setValue(3);
        this.delayCapturaAudio.setValueIsAdjusting(true);
        delayCapturaAudio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblDelayAudio.setText("Delay entre capturas " + delayCapturaAudio.getValue() + " segundos");
            }
        });

        jTabbedPane1.removeAll();

        jTabbedPane1.addTab("Pantalla", Util.cargarIcono16("/resources/monitor.png"), pnlPantalla);
        jTabbedPane1.addTab("Cámara", Util.cargarIcono16("/resources/camera.png"), pnlCamara);
        jTabbedPane1.addTab("Micrófono", Util.cargarIcono16("/resources/microphone.png"), pnlMicrofono);
        jTabbedPane1.addTab("Keylogger", Util.cargarIcono16("/resources/key_ctrl.png"), pnlKeylogger);

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlPantalla = new javax.swing.JPanel();
        capturaOfflinePantalla = new javax.swing.JCheckBox();
        lblDelayCaptura = new javax.swing.JLabel();
        delayCapturaPantalla = new javax.swing.JSlider();
        pnlCamara = new javax.swing.JPanel();
        capturaOfflineWebCam = new javax.swing.JCheckBox();
        lblDelayCapWC = new javax.swing.JLabel();
        delayCapturaWebCam = new javax.swing.JSlider();
        pnlMicrofono = new javax.swing.JPanel();
        capturaOfflineAudio = new javax.swing.JCheckBox();
        lblDelayAudio = new javax.swing.JLabel();
        delayCapturaAudio = new javax.swing.JSlider();
        pnlKeylogger = new javax.swing.JPanel();
        capturaOfflineKeylogger = new javax.swing.JCheckBox();

        jLabel1.setText("jLabel1");

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTitulo.setText("Espiar");

        jLabel3.setText("Permite capturar información mientras está desconectado");

        capturaOfflinePantalla.setText("Capturas de Pantalla");

        lblDelayCaptura.setText("Delay entra capturas:");

        delayCapturaPantalla.setMaximum(60);

        javax.swing.GroupLayout pnlPantallaLayout = new javax.swing.GroupLayout(pnlPantalla);
        pnlPantalla.setLayout(pnlPantallaLayout);
        pnlPantallaLayout.setHorizontalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delayCapturaPantalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPantallaLayout.createSequentialGroup()
                        .addComponent(capturaOfflinePantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 205, Short.MAX_VALUE))
                    .addComponent(lblDelayCaptura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPantallaLayout.setVerticalGroup(
            pnlPantallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPantallaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(capturaOfflinePantalla)
                .addGap(18, 18, 18)
                .addComponent(lblDelayCaptura)
                .addGap(18, 18, 18)
                .addComponent(delayCapturaPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pantalla", pnlPantalla);

        capturaOfflineWebCam.setText("Capturas de Cámara");

        lblDelayCapWC.setText("Delay entra capturas:");

        delayCapturaWebCam.setMaximum(60);

        javax.swing.GroupLayout pnlCamaraLayout = new javax.swing.GroupLayout(pnlCamara);
        pnlCamara.setLayout(pnlCamaraLayout);
        pnlCamaraLayout.setHorizontalGroup(
            pnlCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamaraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delayCapturaWebCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCamaraLayout.createSequentialGroup()
                        .addComponent(capturaOfflineWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 205, Short.MAX_VALUE))
                    .addComponent(lblDelayCapWC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCamaraLayout.setVerticalGroup(
            pnlCamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamaraLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(capturaOfflineWebCam)
                .addGap(18, 18, 18)
                .addComponent(lblDelayCapWC)
                .addGap(18, 18, 18)
                .addComponent(delayCapturaWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cámara", pnlCamara);

        capturaOfflineAudio.setText("Capturas de Audio del Micrófono");

        lblDelayAudio.setText("Delay entra capturas:");

        delayCapturaAudio.setMaximum(60);

        javax.swing.GroupLayout pnlMicrofonoLayout = new javax.swing.GroupLayout(pnlMicrofono);
        pnlMicrofono.setLayout(pnlMicrofonoLayout);
        pnlMicrofonoLayout.setHorizontalGroup(
            pnlMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMicrofonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delayCapturaAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMicrofonoLayout.createSequentialGroup()
                        .addComponent(capturaOfflineAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 205, Short.MAX_VALUE))
                    .addComponent(lblDelayAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMicrofonoLayout.setVerticalGroup(
            pnlMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMicrofonoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(capturaOfflineAudio)
                .addGap(18, 18, 18)
                .addComponent(lblDelayAudio)
                .addGap(18, 18, 18)
                .addComponent(delayCapturaAudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Micrófono", pnlMicrofono);

        capturaOfflineKeylogger.setText("Keylogger offline");

        javax.swing.GroupLayout pnlKeyloggerLayout = new javax.swing.GroupLayout(pnlKeylogger);
        pnlKeylogger.setLayout(pnlKeyloggerLayout);
        pnlKeyloggerLayout.setHorizontalGroup(
            pnlKeyloggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeyloggerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(capturaOfflineKeylogger, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        pnlKeyloggerLayout.setVerticalGroup(
            pnlKeyloggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeyloggerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(capturaOfflineKeylogger)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("KeyLogger", pnlKeylogger);

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
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
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
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox capturaOfflineAudio;
    private javax.swing.JCheckBox capturaOfflineKeylogger;
    private javax.swing.JCheckBox capturaOfflinePantalla;
    private javax.swing.JCheckBox capturaOfflineWebCam;
    private javax.swing.JSlider delayCapturaAudio;
    private javax.swing.JSlider delayCapturaPantalla;
    private javax.swing.JSlider delayCapturaWebCam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDelayAudio;
    private javax.swing.JLabel lblDelayCapWC;
    private javax.swing.JLabel lblDelayCaptura;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCamara;
    private javax.swing.JPanel pnlKeylogger;
    private javax.swing.JPanel pnlMicrofono;
    private javax.swing.JPanel pnlPantalla;
    // End of variables declaration//GEN-END:variables

    public JCheckBox getCapturaOfflineAudio() {
        return capturaOfflineAudio;
    }

    public void setCapturaOfflineAudio(JCheckBox capturaOfflineAudio) {
        this.capturaOfflineAudio = capturaOfflineAudio;
    }

    public JCheckBox getCapturaOfflineKeylogger() {
        return capturaOfflineKeylogger;
    }

    public void setCapturaOfflineKeylogger(JCheckBox capturaOfflineKeylogger) {
        this.capturaOfflineKeylogger = capturaOfflineKeylogger;
    }

    public JCheckBox getCapturaOfflinePantalla() {
        return capturaOfflinePantalla;
    }

    public void setCapturaOfflinePantalla(JCheckBox capturaOfflinePantalla) {
        this.capturaOfflinePantalla = capturaOfflinePantalla;
    }

    public JCheckBox getCapturaOfflineWebCam() {
        return capturaOfflineWebCam;
    }

    public void setCapturaOfflineWebCam(JCheckBox capturaOfflineWebCam) {
        this.capturaOfflineWebCam = capturaOfflineWebCam;
    }

    public JSlider getDelayCapturaAudio() {
        return delayCapturaAudio;
    }

    public void setDelayCapturaAudio(JSlider delayCapturaAudio) {
        this.delayCapturaAudio = delayCapturaAudio;
    }

    public JSlider getDelayCapturaPantalla() {
        return delayCapturaPantalla;
    }

    public void setDelayCapturaPantalla(JSlider delayCapturaPantalla) {
        this.delayCapturaPantalla = delayCapturaPantalla;
    }

    public JSlider getDelayCapturaWebCam() {
        return delayCapturaWebCam;
    }

    public void setDelayCapturaWebCam(JSlider delayCapturaWebCam) {
        this.delayCapturaWebCam = delayCapturaWebCam;
    }

}
