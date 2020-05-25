/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.simple;

import comunes.Accion;
import java.awt.BorderLayout;
import network.Conexion;
import qooport.gui.personalizado.BarraEstado;
import qooport.utilidades.Util;
import comunes.Interfaz;

/**
 *
 * @author aigarcia
 */
public class PanelConexion extends javax.swing.JFrame {

    private Interfaz servicio;

    //acciones del asociado
    //estas acciones son para poder actualizar la barra de estado en el modo simple
    private Accion accionInfo;//accion a ejecutar cuando se recibe la info
    private Accion accionAutenticar;//accion a ejecutar cuando se esta autenticando
    private Accion accionAbrirEscritorio;//accion a ejecutar cuando se abre el escritorio
    private Accion accionDetieneEscritorio;//accion a ejecutar cuando se detiene el escritorio remoto
    private Accion accionDesconectar;//accion a ejecutar cuando se desconecta
    private Accion accionListo;//accion a ejecutar cuando se termino el proceso de conexion, cuadno se abre el escritorio remoto en el modo simple

    private final BarraEstado estado = new BarraEstado();

    /**
     * Creates new form PanelConexion
     *
     * @param servicio
     */
    public PanelConexion(Interfaz servicio) {
        this.servicio = servicio;
        initComponents();

        this.servicio = servicio;

        accionAutenticar = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Autenticando");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_azul.png"));
                } catch (Exception e) {
                }
            }
        };

        accionInfo = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Enviando información inicial");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_amarillo.png"));
                } catch (Exception e) {
                }
            }
        };

        accionAbrirEscritorio = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Escritorio remoto iniciado");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
                } catch (Exception e) {
                }
            }
        };

        accionDesconectar = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Desconectado");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
                } catch (Exception e) {
                }
            }
        };

        accionListo = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Listo");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
                } catch (Exception e) {
                }
            }
        };

        accionDetieneEscritorio = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Escritorio remoto detenido");
                try {
                    jlLed.setIcon(Util.cargarIcono16("/resources/led_amarillo.png"));
                } catch (Exception e) {
                }
            }
        };

        if (servicio != null) {
            try {
                lblIP.setText(((Conexion) this.servicio.get(14)).getInetAddress().getCanonicalHostName());
                this.servicio.set(16, accionAutenticar);//setea la accion de autenticar
                this.servicio.set(17, accionListo);//setea la accion de listo
                this.servicio.set(18, accionInfo);//setea la accion de enviar info
                this.servicio.set(19, accionAbrirEscritorio);//setea la accion de escritorio remoto
                this.servicio.set(20, accionDetieneEscritorio);//setea la accion de escritorio remoto detener
                this.servicio.set(21, accionDesconectar);//setea la accion de escritorio remoto detener
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        lblTitulo.setIcon(Util.cargarIcono32("/resources/remote.png"));
        jlLed.setText("");
        jlLed.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
        btnCerrar.setIcon(Util.cargarIcono16("/resources/close.png"));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(estado, BorderLayout.SOUTH);

        this.setIconImage(Util.cargarIcono16("/resources/remote.png").getImage());
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        jlLed = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblTitulo.setText("QoopoRT");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("IP :");

        lblIP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblIP.setText("0.0.0.0");

        jlLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/led_gris.png"))); // NOI18N
        jlLed.setText("jLabel7");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jlLed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(jLabel1)
                    .addComponent(lblIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(jlLed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        if (servicio != null) {
            servicio.ejecutar(1);//detener
        }
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlLed;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
