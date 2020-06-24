/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.simple;

import comunes.Accion;
import comunes.CFG;
import comunes.Interfaz;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.swing.JFrame;
import network.Conexion;
import network.ConexionServer;
import qooport.Global;
import qooport.Inicio;
import qooport.asociado.Asociado;
import qooport.asociado.v2.AsociadoV2;
import qooport.avanzado.MonitorSistema;
import qooport.gui.Infor;
import qooport.gui.NoIp;
import qooport.gui.personalizado.BarraEstado;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Util;
import rt.util.CLRT;

/**
 *
 * @author alberto
 */
public class ModoSimple extends javax.swing.JFrame {

    private Interfaz servicio;
    private final BarraEstado estado = new BarraEstado();

    //acciones del asociado
    //estas acciones son para poder actualizar la barra de estado en el modo simple
    private Accion accionInfo;//accion a ejecutar cuando se recibe la info
    private Accion accionAutenticar;//accion a ejecutar cuando se esta autenticando
    private Accion accionAbrirEscritorio;//accion a ejecutar cuando se abre el escritorio
    private Accion accionDesconectar;//accion a ejecutar cuando se desconecta
    private Accion accionListo;//accion a ejecutar cuando se termino el proceso de conexion, cuadno se abre el escritorio remoto en el modo simple

    //acciones del servicio
    private Accion accionServicioConectar;

    public ModoSimple(String title) throws HeadlessException {
        super(title);
        initComponents();
        accionAutenticar = new Accion() {

            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Autenticando");
                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_azul.png"));
                } catch (Exception e) {
                }
            }
        };

        accionInfo = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Recibiendo información");
                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_amarillo.png"));
                } catch (Exception e) {
                }
            }
        };

        accionAbrirEscritorio = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Abriendo escritorio remoto");
                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_verde.png"));
                } catch (Exception e) {
                }

            }
        };

        accionDesconectar = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Desconectado");

                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
                } catch (Exception e) {
                }
            }
        };

        accionListo = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                estado.setEstado("Listo");

                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
                } catch (Exception e) {
                }
            }
        };

        accionServicioConectar = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                PanelConexion ven = new PanelConexion(servicio);
                ven.setVisible(true);
            }
        };
        lblTitulo.setIcon(Util.cargarIcono32("/resources/remote.png"));

        this.setIconImage(Util.cargarIcono16("/resources/remote.png").getImage());
        this.setLocationRelativeTo(null);

        String miIps = getHostName() + "," + getIPLocales();
        txtMiIp.setText(miIps);
        txtMiIp.setToolTipText(miIps);
        txtMiIp.setCaretPosition(0);

        txtHost.setText(getHostName());

        //jTextField1.setText(getHostName() + ", " + getIPLocales());
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(estado, BorderLayout.SOUTH);
        this.jlLed.setText("");
        jlLed.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
        this.jlLed1.setText("");
        jlLed1.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
        btnCambiar.setIcon(Util.cargarIcono16("/resources/switch.png"));

        mnuItmNoIp.setIcon(Util.cargarIcono16("/resources/noip.png"));
        mnuItmOpciones.setIcon(Util.cargarIcono16("/resources/advancedsettings.png"));
        mnuVerTransferencias.setIcon(Util.cargarIcono16("/resources/transfer.png"));
        mnuItmAcercaDe.setIcon(Util.cargarIcono16("/resources/info.png"));

        estado.agregarRamInfo();
        estado.setEstado("Listo");
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
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        btnIniciarServicio = new javax.swing.JButton();
        txtClave = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlLed = new javax.swing.JLabel();
        txtMiIp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnConectar = new javax.swing.JButton();
        jlLed1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnCambiar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        mnuItmOpciones = new javax.swing.JMenuItem();
        mnuItmNoIp = new javax.swing.JMenuItem();
        mnuVerTransferencias = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnuItmAcercaDe = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Permitir control remoto"));

        btnIniciarServicio.setText("Iniciar");
        btnIniciarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarServicioActionPerformed(evt);
            }
        });

        txtClave.setEditable(false);
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        jLabel6.setText("Clave:");

        jLabel1.setText("IP Local");

        jlLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/led_gris.png"))); // NOI18N
        jlLed.setText("jLabel7");

        txtMiIp.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlLed, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(207, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMiIp)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIniciarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMiIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciarServicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlLed, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar ordenador remoto"));

        jLabel2.setText("IP Remoto");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        jLabel4.setText("Para especificar un puerto escriba \":puerto\". ");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        jLabel5.setText("Ejemplo: localhost:4100");

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        jlLed1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/led_gris.png"))); // NOI18N
        jlLed1.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConectar)
                            .addComponent(jlLed1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlLed1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblTitulo.setText("QoopoRT");

        btnCambiar.setText("Cambiar");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        jMenu4.setText("Extras");

        mnuItmOpciones.setText("Opciones");
        mnuItmOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItmOpcionesActionPerformed(evt);
            }
        });
        jMenu4.add(mnuItmOpciones);

        mnuItmNoIp.setText("NoIP");
        mnuItmNoIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItmNoIpActionPerformed(evt);
            }
        });
        jMenu4.add(mnuItmNoIp);

        mnuVerTransferencias.setText("Transferencias");
        mnuVerTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVerTransferenciasActionPerformed(evt);
            }
        });
        jMenu4.add(mnuVerTransferencias);

        jMenuItem1.setText("Monitor del Sistema");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar2.add(jMenu4);

        jMenu5.setText("Ayuda");

        mnuItmAcercaDe.setText("Acerca de");
        mnuItmAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItmAcercaDeActionPerformed(evt);
            }
        });
        jMenu5.add(mnuItmAcercaDe);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCambiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCambiar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed

        //accionServicioConectar.ejecutar();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String destino = "";
                try {
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_amarillo.png"));
                    int puerto = 4100;
                    String[] datos = txtHost.getText().split(":");
                    String host = datos[0];
                    if (datos.length == 2) {
                        puerto = Integer.parseInt(datos[1]);
                    }
                    destino = host + ":" + puerto;
                    Conexion conexion = new Conexion(host, puerto, ConexionServer.TCP);
                    estado.setEstado("Conectando..." + conexion.getRemoteSocketAddress().toString());
                    if (conexion.isConectado()) {
                        Asociado asociado = new AsociadoV2(conexion, 2, true, host, puerto);
                        asociado.setAccionAbrirEscritorio(accionAbrirEscritorio);
                        asociado.setAccionAutenticar(accionAutenticar);
                        asociado.setAccionDesconectar(accionDesconectar);
                        asociado.setAccionInfo(accionInfo);
                        asociado.setAccionListo(accionListo);
                        asociado.setAbrirEscritoriRemotoAlconectar(true);
                        asociado.start();
                    } else {
                        estado.setEstado("No se puede conectar a " + conexion.getRemoteSocketAddress().toString());
                        jlLed1.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
                    }
                } catch (Exception ex) {
                    estado.setEstado("No se puede conectar a " + destino + ". " + ex.getMessage());
                    jlLed1.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
                }
            }
        }).start();

    }//GEN-LAST:event_btnConectarActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    public String generarClave() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        int size = 10;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * letras.length());
            b.append(letras.charAt(index));
        }
        this.txtClave.setText(b.toString());
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

    private String getIPLocales() {
        StringBuilder sb = new StringBuilder("");
        InetAddress inet;
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
                                sb.append(ip).append(", ");
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } catch (SocketException ex) {

        }
        return sb.toString();
    }

    private void iniciar() {
        try {
            System.setProperty("java.net.preferIPv4Stack", "true");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String clave = generarClave();
                    try {
                        CLRT cl = new CLRT();

                        //configuracion default
                        rt.Inicio.config = new CFG();
                        rt.Inicio.config.inicializarParamertros();
                        rt.Inicio.config.agregarParametro("dns", "");
                        rt.Inicio.config.agregarParametro("puerto", "4100");
                        rt.Inicio.config.agregarParametro("claveClase", "");
                        rt.Inicio.config.agregarParametro("jarName", "");
                        rt.Inicio.config.agregarParametro("nombreUSB", "");
                        rt.Inicio.config.agregarParametro("password", "");
                        rt.Inicio.config.agregarParametro("prefijo", "");
                        rt.Inicio.config.agregarParametro("regName", "");
                        rt.Inicio.config.agregarParametro("ssl", Boolean.TRUE);
                        rt.Inicio.config.agregarParametro("protocolo", ConexionServer.TCP);

                        servicio = ((Interfaz) cl.loadClass("rt.Servicio").newInstance());
//                      conexion.iniciar(dnsUnico, puerto, puerto2, password, delay, prefijo, escritorioOffline, capDelayFoto, tipoConexion);
                        System.out.println("se va a inciar el servicio");
                        servicio.instanciar(null, 4100, clave, 3000, "rt_", false, 3000, 2);
                        System.out.println("se iniciar el servicio");
                        servicio.set(15, accionServicioConectar);//setea la accion de conectar
                        servicio.ejecutar(0);//si no hay conexion y se detiene el servicio. no llega a esta linea porq aun esta aceptando conexiones

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
            btnIniciarServicio.setText("Detener");
            jlLed.setIcon(Util.cargarIcono16("/resources/led_naranja.png"));
            estado.setEstado("Esperando conexiones");
        } catch (Exception e) {

        }
    }

    private void detener() {
        try {
            servicio.ejecutar(1);
            servicio = null;
            btnIniciarServicio.setText("Iniciar");
            this.txtClave.setText("");
            jlLed.setIcon(Util.cargarIcono16("/resources/led_gris.png"));
            estado.setEstado("Servicio detenido");
        } catch (Exception e) {

        }
    }

    private void btnIniciarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarServicioActionPerformed
        if (servicio == null) {
            iniciar();
        } else {
            detener();
        }
    }//GEN-LAST:event_btnIniciarServicioActionPerformed

    private void mnuItmAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItmAcercaDeActionPerformed
        Infor.ver();
    }//GEN-LAST:event_mnuItmAcercaDeActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        Inicio.main(null);

        this.dispose();
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void mnuVerTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVerTransferenciasActionPerformed
        // TODO add your handling code here:
        JFrame ventTransferencias = new JFrame();
        ventTransferencias.setTitle("Transferencias");
        ventTransferencias.setIconImage(Util.cargarIcono16("/resources/transfer.png").getImage());
        ventTransferencias.getContentPane().add(Global.transferencias);
        ventTransferencias.pack();
        ventTransferencias.setVisible(true);
        GuiUtil.centrarVentana(ventTransferencias, 800, 300);

    }//GEN-LAST:event_mnuVerTransferenciasActionPerformed

    private void mnuItmOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItmOpcionesActionPerformed
        Config conf = new Config();
        conf.setVisible(true);
    }//GEN-LAST:event_mnuItmOpcionesActionPerformed

    private void mnuItmNoIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItmNoIpActionPerformed
        JFrame ventTransferencias = new JFrame();
        ventTransferencias.setTitle("NoIP");
        ventTransferencias.setIconImage(Util.cargarIcono16("/resources/noip.png").getImage());
        ventTransferencias.getContentPane().add(new NoIp());
        ventTransferencias.pack();
        ventTransferencias.setVisible(true);
//        GuiUtil.centrarVentana(ventTransferencias, 800, 300);
    }//GEN-LAST:event_mnuItmNoIpActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new MonitorSistema().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
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
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnIniciarServicio;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlLed;
    private javax.swing.JLabel jlLed1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JMenuItem mnuItmAcercaDe;
    private javax.swing.JMenuItem mnuItmNoIp;
    private javax.swing.JMenuItem mnuItmOpciones;
    private javax.swing.JMenuItem mnuVerTransferencias;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtMiIp;
    // End of variables declaration//GEN-END:variables
}
