package qooport.modulos.pc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;

public class RedLan extends JFrame {

    private JButton btnListar;
//    private JButton btnMatar;
    private JScrollPane jScrollPane1;
    private JTable tabla;
    private Asociado servidor;
    private JToolBar barra;

    public RedLan(Asociado servidor) {
        initComponents();
        this.tabla.getColumnModel().getColumn(1).setCellRenderer(new CeldaIcono());
        this.servidor = servidor;
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.tabla = new JTable();
        this.btnListar = new JButton();
//        this.btnMatar = new JButton();
        barra = new JToolBar();
//        setClosable(true);
        setTitle("Dispositivos red lan");
//        setFrameIcon(Util.cargarIcono16("/resources/procesos.png")));
        this.tabla.setAutoCreateRowSorter(true);
        tabla.setShowGrid(false);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"Orden llegada", "IP", "Hostname"}));
        this.jScrollPane1.setViewportView(this.tabla);
        this.btnListar.setText("Listar");
        this.btnListar.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RedLan.this.btnListar(evt);
            }
        });
//        this.btnMatar.setText("Matar");
//        btnMatar.setIcon(Util.cargarIcono16("/resources/delete.png"));
//        this.btnMatar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                RedLan.this.btnMatar(evt);
//            }
//        });
        barra.setFloatable(false);
        barra.add(btnListar);
//        barra.add(btnMatar);
        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(jScrollPane1, BorderLayout.CENTER);
        this.setSize(800, 600);
//        this.setResizable(true);
        this.setVisible(true);
        this.setIconImage(Util.cargarIcono16("/resources/redlan.png").getImage());
        pack();
    }

    private void btnListar(ActionEvent evt) {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        if (this.tabla.getRowCount() > 0) {
            for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
                m.removeRow(i);
            }
        }
        servidor.listarEquiposRed();
    }

    public void limpiatabla() {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        if (this.tabla.getRowCount() > 0) {
            for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
                m.removeRow(i);
            }
        }
    }

    public void agregarFila(String valor) {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        m.addRow(valor.split("&&"));
    }

//    private void btnMatar(ActionEvent evt) {
//        String pid = this.tabla.getValueAt(this.tabla.getSelectedRow(), 4).toString();
//        servidor.matarProcesos(pid.replaceAll(" ", ""));
//        btnListar(evt);
//    }
    static class CeldaIcono extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            if (value == null) {
                return;
            }
            setText(value.toString());
            String c = value.toString().toLowerCase();
            if (c.contains("interfa")) {
                setIcon(Util.cargarIcono16("/resources/network_interface.png"));
            } else if (c.contains("inicio")) {
                setIcon(Util.cargarIcono16("/resources/network-ip.png"));
            } else {
                setIcon(Util.cargarIcono16("/resources/screen.png"));
            }
        }
    }

}
