package qooport.modulos.archivos.anterior;

import comunes.Accion;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.BadLocationException;
import qooport.asociado.Asociado;
import qooport.avanzado.ModoAvanzado;
import qooport.avanzado.QoopoRT;
import qooport.modulos.archivos.transferencia.Transferencia;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

public class SubirArchivo extends JPanel implements Runnable {

    public boolean decargado = false;
    private JButton btnCancelar;
    private JButton btnCerrar;
    private JButton btnAbrir;
    private JLabel lblIDServidor;
    private JLabel lblId;
    private JLabel lblNombreArchivo;
    private JLabel lblProgreso;
    private JProgressBar barraProgreso;
    private String ID;

    private JLabel lblIcono;
    private ContadorBPS contadorBps;

    private Asociado asociado;
    private String archivoASubir;
    private String rutaRemota;

    private Transferencia transferencia;

    //public SubirArchivo(Conexion conexion, int bufferSize, String archivo) {
    public SubirArchivo(Transferencia transferencia) {
        initComponents();
//        this.enviarObjeto = in;
        this.transferencia = transferencia;

        contadorBps = new ContadorBPS();
        contadorBps.start();
    }

    /**
     * Para conexion directa
     *
     * @param servidor
     * @param archivoAdescargar
     * @param rutaAdescargar
     */
    public SubirArchivo(Asociado servidor, String archivoAdescargar, String rutaAdescargar) {
        initComponents();

        this.asociado = servidor;
        this.archivoASubir = archivoAdescargar;
        this.rutaRemota = rutaAdescargar;
        contadorBps = new ContadorBPS();
        contadorBps.start();
    }

    private void initComponents() {
        this.lblIcono = new JLabel();
        this.lblIcono.setIcon(Util.cargarIcono16("/resources/up_arrow.png"));
        this.lblIDServidor = new JLabel();
        this.barraProgreso = new JProgressBar();
        this.lblId = new JLabel();
        this.lblNombreArchivo = new JLabel();
        this.lblProgreso = new JLabel();
        this.btnCancelar = new JButton();
        this.btnCerrar = new JButton();
        this.btnAbrir = new JButton();
        //setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 102, 51)));
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        this.lblIDServidor.setFont(new Font("Tahoma", 1, 11));
        this.lblIDServidor.setText("");
//        this.barraProgreso.setForeground(new Color(102, 0, 0));
        this.barraProgreso.setStringPainted(true);
        this.lblId.setText("");
        this.lblNombreArchivo.setText("");
        this.lblProgreso.setText("0"); //cantidad descargada
        this.btnCancelar.setText("Cancelar");
        this.btnCancelar.setIcon(Util.cargarIcono16("/resources/disconnect.png"));
        this.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SubirArchivo.this.btnCancelarActionP(evt);
            }
        });
        this.btnCerrar.setText("Cerrar");
        this.btnCerrar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SubirArchivo.this.btnCerrarActionP(evt);
            }
        });
        this.btnAbrir.setText("Abrir");
        this.btnAbrir.setIcon(Util.cargarIcono16("/resources/folder.png"));
        this.btnAbrir.setEnabled(false);
        this.btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SubirArchivo.this.btnAbrir(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        alinear(layout);
    }

    public void alinear(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblIcono)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(lblNombreArchivo)
                                                        .addGap(20)
                                                        .addComponent(this.lblProgreso)
                                        )
                                        .addComponent(this.barraProgreso)
                                        .addGap(5)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(lblId)
                                                        .addGap(5)
                                                        .addComponent(this.lblIDServidor)
                                                        .addGap(20)
                                                        .addComponent(this.btnAbrir)
                                                        .addGap(5)
                                                        .addComponent(this.btnCerrar)
                                                        .addGap(5)
                                                        .addComponent(this.btnCancelar)
                                        )
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblIcono)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblNombreArchivo)
                                                .addComponent(lblProgreso)
                                        )
                                        .addComponent(this.barraProgreso)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblId)
                                                .addComponent(lblIDServidor)
                                                .addComponent(btnAbrir)
                                                .addComponent(btnCerrar)
                                                .addComponent(btnCancelar)
                                        ))
        );
    }

    public void btnCerrarActionP(ActionEvent evt) {
        transferencia.detener();

        try {
            int nStart = ModoAvanzado.paneTransferencia.viewToModel(new Point(getParent().getX(), getParent().getY()));
            ModoAvanzado.paneTransferencia.getDocument().remove(nStart, 2);
            ModoAvanzado.paneTransferencia.repaint();
        } catch (BadLocationException ex) {
        }
        //QoopoRT.listaSubidas.remove(this);
    }

    private void btnCancelarActionP(ActionEvent evt) {

        transferencia.detener();
    }

    private void btnAbrir(ActionEvent evt) {
        try {
            Desktop.getDesktop().open(new File("downloads/" + this.lblIDServidor.getText() + "/" + this.lblNombreArchivo.getText()));
        } catch (IOException ex) {
            Logger.getLogger(SubirArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            Accion accionProgreso = new Accion() {
                @Override
                public void ejecutar(Object... parametros) {
                    lblProgreso.setText(" [" + Util.convertirBytes(transferencia.getActual())
                            + "/" + Util.convertirBytes(transferencia.getTotal())
                            + "] [Tiempo:" + transferencia.getTiempoTranscurrido() + "]  [Tiempo restante:" + transferencia.getTiempoRestante() + "] [" + Util.convertirBytes((float) transferencia.getContador().getTasa()) + "/s]");

                    barraProgreso.setValue(transferencia.getAvance());

                    lblNombreArchivo.setText(transferencia.getNombreArchivo());
                    try {
                        lblNombreArchivo.setIcon(transferencia.getIcono());
                    } catch (Exception e) {
                    }
                    lblIDServidor.setText(transferencia.getIdInformacion());

                }
            };

            Accion accionFinalizar = new Accion() {
                @Override
                public void ejecutar(Object... parametros) {
                    lblProgreso.setText(" [" + Util.convertirBytes(transferencia.getActual())
                            + "/" + Util.convertirBytes(transferencia.getTotal())
                            + "] [Tiempo:" + transferencia.getTiempoTranscurrido() + "]  [Tiempo restante:" + transferencia.getTiempoRestante() + "] [" + Util.convertirBytes((float) transferencia.getContador().getTasa()) + "/s]");

                    btnCancelar.setEnabled(false);
                    btnAbrir.setEnabled(true);

                    if (QoopoRT.instancia.getVentana().getChkCerrarAlTerminar().isSelected()) {
                        btnCerrarActionP(null);
                    }
                    QoopoRT.ejecutarAccionesCarga();
                }
            };

            transferencia.setAccionProgreso(accionProgreso);
            transferencia.setAccionFinalizar(accionFinalizar);

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {

        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public JButton getBtnAbrir() {
        return btnAbrir;
    }

    public void setBtnAbrir(JButton btnAbrir) {
        this.btnAbrir = btnAbrir;
    }
}
