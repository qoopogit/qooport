/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.gui.personalizado;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class PanelEquipos extends JPanel {

    /**
     * 1 tabla, 2 iconos con escritorios como vista,
     */
    private int tipoVista = 1;

    //***********************************************************************************
    // **                       VISTA TIPO TABLA
    //***********************************************************************************
    private JTable tabla;
    private DefaultTableModel modelotabla;

    private JScrollPane scrollEquipos;

    public PanelEquipos() {
    }

    public void eliminarServidor(Asociado serv) {
        try {
            for (int i = 0; i <= modelotabla.getRowCount(); i++) {
                String id = this.modelotabla.getValueAt(i, 2).toString();
                if (id.equals(serv.getIdentificador())) {
                    this.modelotabla.removeRow(i);
                }
            }
        } catch (Exception e) {
        }
    }

    public void iniciar(JPopupMenu ppmnuServer) {
        scrollEquipos = new JScrollPane();
        iniciarTabla(ppmnuServer);
    }

    private void iniciarTabla(JPopupMenu ppmnuServer) {

        tabla = new JTable();
        tabla.setShowGrid(false);
        this.tabla.setAutoCreateRowSorter(true);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"Escritorio", "Camara", "Identificador", "País", "Ping", "IP Externa", "IP Interna", "Usuario PC", "Host name", "Web Cam", "S.O.", "Versión  JRE", "Versión", "Recib", "Envia", "Avatar", "Protocolo", "SSL"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        this.tabla.setComponentPopupMenu(ppmnuServer);
        this.tabla.setDropMode(DropMode.ON);
        this.tabla.setRowHeight(50);
        this.tabla.setSelectionMode(2);
        this.tabla.getTableHeader().setVisible(true);
        this.tabla.getTableHeader().setReorderingAllowed(true);
        this.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                QoopoRT.instancia.getVentana().tablaMouseClicked(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                QoopoRT.instancia.getVentana().tablaMousePressed(evt);
            }
        });

        this.tabla.getColumnModel().getColumn(0).setMinWidth(100);
        this.tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.tabla.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tabla.getColumnModel().getColumn(1).setMinWidth(100);
        this.tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tabla.getColumnModel().getColumn(1).setMaxWidth(100);
        this.tabla.getColumnModel().getColumn(0).setHeaderValue("Escritorio");
        this.tabla.getColumnModel().getColumn(1).setHeaderValue("Camara");
        this.tabla.getColumnModel().getColumn(2).setHeaderValue("Identificador");
        this.tabla.getColumnModel().getColumn(3).setHeaderValue("Pais");
        this.tabla.getColumnModel().getColumn(4).setHeaderValue("Ping");
        this.tabla.getColumnModel().getColumn(5).setHeaderValue("IP Externa");
        this.tabla.getColumnModel().getColumn(6).setHeaderValue("IP Interna");
        this.tabla.getColumnModel().getColumn(7).setHeaderValue("Usuario");
        this.tabla.getColumnModel().getColumn(8).setHeaderValue("Host Name");
        this.tabla.getColumnModel().getColumn(9).setHeaderValue("Cámara");
        this.tabla.getColumnModel().getColumn(10).setHeaderValue("Sistema Operativo");
        this.tabla.getColumnModel().getColumn(11).setHeaderValue("Version jre");
        this.tabla.getColumnModel().getColumn(12).setHeaderValue("Server Version");
        this.tabla.getColumnModel().getColumn(13).setHeaderValue("Recib");
        this.tabla.getColumnModel().getColumn(14).setHeaderValue("Envia");
        this.tabla.getColumnModel().getColumn(15).setHeaderValue("Avatar");
        this.tabla.getColumnModel().getColumn(16).setHeaderValue("Protocolo");
        this.tabla.getColumnModel().getColumn(17).setHeaderValue("SSL");

        this.tabla.getColumnModel().getColumn(0).setCellRenderer(new CeldaEscritorio());
        this.tabla.getColumnModel().getColumn(1).setCellRenderer(new CeldaCamara());
        this.tabla.getColumnModel().getColumn(3).setCellRenderer(new CeldaBandera());
        this.tabla.getColumnModel().getColumn(10).setCellRenderer(new CeldaOS());
        this.tabla.getColumnModel().getColumn(9).setCellRenderer(new CeldaWebCam());
        this.tabla.getColumnModel().getColumn(4).setCellRenderer(new CeldaPing());
        this.tabla.getColumnModel().getColumn(15).setCellRenderer(new CeldaAvatar());
        this.tabla.getColumnModel().getColumn(17).setCellRenderer(new CeldaSSL());
        this.modelotabla = ((DefaultTableModel) this.tabla.getModel());
        tabla.setShowGrid(false);

        this.scrollEquipos.setViewportView(tabla);

        this.setLayout(new GridLayout(1, 1));
        this.add(scrollEquipos);
    }

    public List<String> getSeleccionados() {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < this.tabla.getSelectedRows().length; i++) {
            String seleccionado = this.tabla.getValueAt(this.tabla.getSelectedRows()[i], 2).toString();
            lst.add(seleccionado);
        }
        return lst;
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

    public void agregarServidor(Asociado serv) {
        synchronized (this.tabla) {
            try {
                Object[] datos = new Object[18];

                datos[0] = serv.getVistaPreviaEscritorio();
                datos[1] = serv.getVistaPreviaWC();
                datos[2] = serv.getIdentificador();
                datos[3] = serv.getDatos()[0];
                datos[4] = "" + serv.getTiempoVida();
                datos[5] = "" + serv.getDatos()[2];
                datos[6] = "" + serv.getDatos()[3];
                datos[7] = "" + serv.getUsuario();
                datos[8] = "" + serv.getDatos()[5];
                datos[9] = "" + serv.getDatos()[6];
                datos[10] = "" + serv.getDatos()[7];
                datos[11] = "" + serv.getDatos()[8];
                datos[12] = "" + serv.getDatos()[9];
                datos[13] = "" + Util.convertirBytes(serv.getBytesRecibidos());
                datos[14] = "" + Util.convertirBytes(serv.getBytesEnviados());
                datos[15] = serv.getUsuarioAvatar();
                datos[16] = serv.getTipoConexionSTR();
                datos[17] = serv.isSsl();
                getModelotabla().addRow(datos);
            } catch (Exception e) {

                System.out.println("Datos recibidos ");
                System.out.println(Arrays.toString(serv.getDatos()));
                e.printStackTrace();
            }
        }
    }

    public boolean actualizarDatosServidor(Asociado serv) {
        boolean encontrado = false;
        synchronized (this.tabla) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                String seleccionado = getModelotabla().getValueAt(i, 2).toString();
                if (seleccionado.equals(serv.getIdentificador())) {
                    getModelotabla().setValueAt(serv.getVistaPreviaEscritorio(), i, 0);
                    getModelotabla().setValueAt(serv.getVistaPreviaWC(), i, 1);
                    getModelotabla().setValueAt(serv.getUsuarioAvatar(), i, 15);
                    getModelotabla().setValueAt(serv.getTiempoVida(), i, 4);
                    getModelotabla().setValueAt(Util.convertirBytes(serv.getBytesRecibidos()), i, 13);
                    getModelotabla().setValueAt(Util.convertirBytes(serv.getBytesEnviados()), i, 14);
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    static class CeldaEscritorio extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            setText("");
            if (value != null) {
                try {
                    ImageIcon icono = (ImageIcon) value;
                    try {
                        icono = new ImageIcon(icono.getImage().getScaledInstance(90, 45, Image.SCALE_SMOOTH));
                    } catch (Exception e) {
                    }
                    setIcon(icono);
                } catch (Exception e) {
                    setText("Error ");
                    try {
                        setText("Valor " + value.toString());
                    } catch (Exception ee) {
                    }
                }
            } else {
                setIcon(Util.cargarIcono("/resources/screen.png", 16, 16));
            }
        }
    }

    static class CeldaAvatar extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            setText("");
            if (value != null) {
                try {
                    ImageIcon icono = (ImageIcon) value;
                    try {
                        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                    } catch (Exception e) {
                    }
                    setIcon(icono);
                } catch (Exception e) {
                    setText("Error ");
                    try {
                        setText("Valor " + value.toString());
                    } catch (Exception ee) {
                    }
                }
            } else {
                setIcon(Util.cargarIcono("/resources/nodisponible.png", 16, 16));
            }
        }
    }

    static class CeldaCamara extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            setText("");
            if (value != null) {
                try {
                    ImageIcon icono = (ImageIcon) value;
                    try {
                        icono = new ImageIcon(icono.getImage().getScaledInstance(90, 45, Image.SCALE_SMOOTH));
                    } catch (Exception e) {
                    }
                    setIcon(icono);
                } catch (Exception e) {
                    setText("Error ");
                    try {
                        setText("Valor " + value.toString());
                    } catch (Exception ee) {
                    }
                }
            } else {
//                setIcon(Util.cargarIcono("/resources/desktop1.png", 100, 50));
                setIcon(Util.cargarIcono("/resources/camera.png", 16, 16));
            }
        }
    }

    static class CeldaBandera extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                if (value == null) {
                    setText("N/A");
                } else {
                    String[] m = value.toString().split("#");
                    try {
                        setIcon(Util.cargarIcono16("/banderas/" + m[1] + ".gif"));
                    } catch (Exception e) {
                        setIcon(Util.cargarIcono16("/resources/3.gif"));
                    }
                    setText(m[0]);
                }
            } catch (Exception e) {

            }
        }
    }

    static class CeldaOS extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            if (value == null) {
                setText("");
            } else {
                String m = value.toString();
                setIcon(Util.iconoSistemaOperativo(m));
                setText(m);
            }
        }
    }

    static class CeldaWebCam extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            if (value == null) {
                setText("N/A");
            } else {
                String m = value.toString();
                if (m.contains("SI")) {
                    try {
                        setIcon(Util.cargarIcono16("/resources/camera.png"));
                    } catch (Exception e) {
                    }
                } else if (m.contains("NO")) {
                    try {
                        setIcon(Util.cargarIcono16("/resources/no-camera.png"));
                    } catch (Exception e) {
                    }
                } else //no hay plugin
                {
                    try {
                        setIcon(Util.cargarIcono16("/resources/plugin_warning.png"));
                    } catch (Exception e) {
                    }
                }
//                setIcon(Util.cargarIcono16("/banderas/" + value.toString() + ".gif")));
                setText(m);
            }
        }
    }

    static class CeldaPing extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {

            try {
                if (value == null) {
                    setText("N/A");
                } else {
                    Long m = Long.valueOf(value.toString());

//                m=m/100;
                    if (m < 10) {
                        setIcon(Util.cargarIcono16("/resources/ping0.png"));
                    } else if (m < 100) {
                        setIcon(Util.cargarIcono16("/resources/ping1.png"));
                    } else if (m < 200) {
                        setIcon(Util.cargarIcono16("/resources/ping2.png"));
                    } else if (m < 400) {
                        setIcon(Util.cargarIcono16("/resources/ping3.png"));
                    } else if (m < 600) {
                        setIcon(Util.cargarIcono16("/resources/ping4.png"));
                    } else {
                        setIcon(Util.cargarIcono16("/resources/ping5.png"));
                    }
                    setText(m + " ms");
                }
            } catch (Exception e) {
                setIcon(Util.cargarIcono16("/resources/ping5.png"));
            }
        }
    }

    static class CeldaSSL extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {

            try {
                if (value == null) {
                    setText("");
                } else {
                    Boolean m = Boolean.valueOf(value.toString());

//                m=m/100;
                    if (m) {
                        setIcon(Util.cargarIcono16("/resources/ssl_2.png"));
                    } else {
                        setIcon(null);
//                        setIcon(Util.cargarIcono16("/resources/ping5.png"));
                    }
                    setText("");
                    //setText(m + " ms");
                }
            } catch (Exception e) {
                //setIcon(Util.cargarIcono16("/resources/ping5.png"));
            }
        }
    }

    public String getSeleccionado() {
        return this.tabla.getValueAt(this.tabla.getSelectedRow(), 2).toString();
    }

}
