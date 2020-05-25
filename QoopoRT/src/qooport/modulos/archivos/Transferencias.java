/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos;

import comunes.Accion;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import qooport.avanzado.QoopoRT;
import qooport.gui.personalizado.BarraEstado;
import qooport.modulos.archivos.botonColumna.BotonColumna;
import qooport.modulos.archivos.botonColumna.ValorBotonColumna;
import qooport.modulos.archivos.transferencia.Descarga;
import qooport.modulos.archivos.transferencia.Gestor;
import qooport.modulos.archivos.transferencia.Transferencia;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.ProgresoGeneral;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class Transferencias extends JPanel {

    private Gestor gestor;
    private JScrollPane jScrollPane;
    private JTable tabla;
    private JToolBar barra;
    private BarraEstado barraEstado;
    private JButton btnListar;
    private JButton btnMatar;
    private boolean actualizando = false;

    public ProgresoGeneral progresoDescargas;
    public ProgresoGeneral progresoCarga;

    public Transferencias() {
        gestor = new Gestor();
        initComponents();

        this.tabla.getColumnModel().getColumn(3).setCellRenderer(new CeldaIcono());
        this.tabla.getColumnModel().getColumn(5).setCellRenderer(new CeldaProgreso());
        this.tabla.getColumnModel().getColumn(6).setCellRenderer(new CeldaTamanio());
        this.tabla.getColumnModel().getColumn(7).setCellRenderer(new CeldaTamanio());

//        this.tabla.getColumnModel().getColumn(8).setCellRenderer(new CeldaAccion(1));
//        this.tabla.getColumnModel().getColumn(9).setCellRenderer(new CeldaAccion(2));
//        this.tabla.getColumnModel().getColumn(10).setCellRenderer(new CeldaAccion(3));
//        this.tabla.getColumnModel().getColumn(11).setCellRenderer(new CeldaAccion(4));
        Action pausarReanudar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                if (t.isPausado()) {
                    t.continuar();
                } else {
                    t.pausar();
                }
            }
        };

        BotonColumna btnPausar = new BotonColumna(tabla, pausarReanudar, 12, "Pausar/Reanudar");
//        btnPausar.setMnemonic(KeyEvent.VK_D);

        Action detener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                t.detener();
            }
        };

        BotonColumna btndetener = new BotonColumna(tabla, detener, 13, "Detener");
//        btndetener.setMnemonic(KeyEvent.VK_D);

        Action abrir = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                try {
                    Desktop.getDesktop().open(t.getArchivo().getAbsoluteFile());
                } catch (IOException ex) {

                }
            }
        };

        BotonColumna btnAbrir = new BotonColumna(tabla, abrir, 14, "Abrir/Ejecutar");
//        btnAbrir.setMnemonic(KeyEvent.VK_D);

        Action abrirCarpeta = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                try {
                    Desktop.getDesktop().open(t.getArchivo().getParentFile().getAbsoluteFile());
                } catch (IOException ex) {

                }
            }
        };

        BotonColumna btnAbrirCarpeta = new BotonColumna(tabla, abrirCarpeta, 15, "Abrir carpeta");

        Action eliminar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                eliminarTransferencia(t);
//                reiniciarLista();
            }
        };

        BotonColumna btnEliminar = new BotonColumna(tabla, eliminar, 16, "Eliminar de la lista");

        Action abriVisor = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                QoopoRT.instancia.getVentana().abrirArchivosOfflineVisor(t.getArchivo());
            }
        };

        BotonColumna btnAbrirVisor = new BotonColumna(tabla, abriVisor, 17, "Abrir visor de capturas");

        Action reanudar = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Transferencia t = ((Transferencia) ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1));
                t.reanudar();
            }
        };

        BotonColumna btnReanudad = new BotonColumna(tabla, reanudar, 18, "Reanudar");

//        btnAbrirCarpeta.setMnemonic(KeyEvent.VK_D);
//        reiniciarLista();
        Accion accion = new Accion() {
            @Override
            public void ejecutar(Object... parametros) {
                actualizarLista();
            }
        };
        Actualizador actualizador = new Actualizador(accion);
        actualizador.start();
    }

    public void agregarTransferencia(Transferencia transferencia) {
        gestor.agregar(transferencia);
        agregarTransferenciaLista(transferencia);
//        reiniciarLista();
    }

    public void eliminarTransferencia(Transferencia transferencia) {
        gestor.eliminar(transferencia);
        eliminarTransferenciaLista(transferencia);
    }

    private void setAnchoCelda(int celda, int ancho) {
        this.tabla.getColumnModel().getColumn(celda).setMinWidth(ancho);
        this.tabla.getColumnModel().getColumn(celda).setPreferredWidth(ancho);
        this.tabla.getColumnModel().getColumn(celda).setMaxWidth(ancho);
    }

    private void initComponents() {
        this.jScrollPane = new JScrollPane();
        this.tabla = new JTable();
        this.btnListar = new JButton();
        this.btnMatar = new JButton();
        barra = new JToolBar();
        tabla.setShowGrid(false);
        this.tabla.setAutoCreateRowSorter(true);
        this.tabla.setModel(new DefaultTableModel(new Object[0][], new String[]{"id", "--", "Tipo", "", "Archivo", "Avance", "Actual", "Total", "Velocidad", "Tiempo", "Tiempo Restante", "Equipo", "Pausar", "Cancelar", "Abrir", "Carpeta", "Eliminar", "Visor", "Reanudar"}));

        setAnchoCelda(0, 0);
        setAnchoCelda(1, 0);

        setAnchoCelda(2, 75);
        setAnchoCelda(3, 25);

        setAnchoCelda(12, 25);
        setAnchoCelda(13, 25);
        setAnchoCelda(14, 25);
        setAnchoCelda(15, 25);
        setAnchoCelda(16, 25);
        setAnchoCelda(17, 25);
        setAnchoCelda(18, 25);
        this.jScrollPane.setViewportView(this.tabla);
        this.btnListar.setText("Actualizar");
        this.btnListar.setIcon(Util.cargarIcono16("/resources/refresh_blue.png"));
        this.btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                Procesos.this.btnListar(evt);
//                reiniciarLista();
                actualizarLista();
            }
        });
        this.btnMatar.setText("Eliminar Terminados");
        btnMatar.setIcon(Util.cargarIcono16("/resources/delete.png"));
        this.btnMatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
//                Procesos.this.btnMatar(evt);
                eliminarTerminados();
            }
        });
        barra.setFloatable(false);
        barra.add(btnListar);
        barra.add(btnMatar);

        barraEstado = new BarraEstado();

        progresoDescargas = new ProgresoGeneral(GuiUtil.crearJLabel("", Util.cargarIcono16("/resources/down_arrow.png")), 250);
        barraEstado.add(progresoDescargas);
        progresoCarga = new ProgresoGeneral(GuiUtil.crearJLabel("", Util.cargarIcono16("/resources/up_arrow.png")), 250);
        barraEstado.add(progresoCarga);

        this.setLayout(new BorderLayout());
        this.add(barra, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(barraEstado, BorderLayout.SOUTH);

        this.setSize(800, 600);
//        this.setResizable(true);
        this.setVisible(true);
    }

    public void actualizarLista() {
        if (actualizando) {
            return;
        }
        try {
//            List<Transferencia> lstTmp = gestor.getLista();
            List<Transferencia> lstTmp = new ArrayList<>();
            lstTmp.addAll(gestor.getLista());
            actualizando = true;
//            boolean encontrado = true;
            boolean encontradoTmp = false;
//        synchronized (this.tabla) {
            for (Transferencia p : lstTmp) {
                encontradoTmp = false;
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    try {
                        Long seleccionado = (Long) tabla.getModel().getValueAt(i, 0);
                        if (seleccionado.equals(p.getId())) {
                            try {
                                encontradoTmp = true;
                                tabla.getModel().setValueAt(p, i, 1);
                                tabla.getModel().setValueAt(p.getIcono(), i, 3);
                                tabla.getModel().setValueAt(p.getNombreArchivo(), i, 4);
                                tabla.getModel().setValueAt(p.getAvance(), i, 5);
                                tabla.getModel().setValueAt(p.getActual(), i, 6);
                                tabla.getModel().setValueAt(p.getTotal(), i, 7);
                                tabla.getModel().setValueAt(Util.convertirBytes((float) p.getContador().getTasa()) + "/s", i, 8);
                                tabla.getModel().setValueAt(p.getTiempoTranscurrido(), i, 9);
                                tabla.getModel().setValueAt(p.getTiempoRestante(), i, 10);
                                if (p.getAsociado() != null) {
                                    tabla.getModel().setValueAt(p.getAsociado().getInformacion(), i, 11);
                                } else {
                                    tabla.getModel().setValueAt("N/A", i, 11);
                                }
                                //pausa
                                tabla.getModel().setValueAt(new ValorBotonColumna(p.isActivo(), p.isPausado() ? Util.cargarIcono12("/resources/start.png") : Util.cargarIcono12("/resources/stop.png"), null), i, 12);
                                //stop
                                tabla.getModel().setValueAt(new ValorBotonColumna(p.isActivo(), Util.cargarIcono12("/resources/stop_close.png"), null), i, 13);
                                //abrir
                                tabla.getModel().setValueAt(new ValorBotonColumna(p.isOk(), Util.cargarIcono12("/resources/ejecutar.png"), null), i, 14);
                                //abrir visor
                                tabla.getModel().setValueAt(new ValorBotonColumna(!p.isActivo(), Util.cargarIcono12("/resources/monitor.png"), null), i, 17);
                                //reanudar
                                tabla.getModel().setValueAt(new ValorBotonColumna(!p.isActivo() && !p.isOk(), Util.cargarIcono12("/resources/reload.png"), null), i, 18);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {

                    }
                }
                if (!encontradoTmp) {
//                    encontrado = false;
//                    agregarTransferenciaLista(p);
                }
            }

//            //si algun ono fue encontrado se reinicializa la lista
//            if (!encontrado) {
//                //sino exite en la lista la reinicializo
//                reiniciarLista();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        actualizando = false;
    }

    public void eliminarTransferenciaLista(Transferencia p) {
        try {
            for (int i = 0; i <= ((DefaultTableModel) tabla.getModel()).getRowCount(); i++) {
                Long id = (Long) ((DefaultTableModel) tabla.getModel()).getValueAt(i, 0);
                if (id.equals(p.getId())) {
                    ((DefaultTableModel) tabla.getModel()).removeRow(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarTransferenciaLista(Transferencia p) {
        ((DefaultTableModel) tabla.getModel()).addRow(
                new Object[]{
                    p.getId(),//id
                    p,
                    p instanceof Descarga ? "Descarga" : "Carga",
                    p.getIcono(),//icono
                    p.getNombreArchivo(),
                    p.getAvance(),
                    p.getActual(),
                    p.getTotal(),
                    //p.getContador().getTasaBytesFormated(),
                    Util.convertirBytes((float) p.getContador().getTasa()) + "/s",
                    p.getTiempoTranscurrido(),
                    p.getTiempoRestante(),
                    p.getAsociado() != null ? p.getAsociado().getInformacion() : "N/A",
                    p.isPausado() ? Util.cargarIcono12("/resources/start.png") : Util.cargarIcono12("/resources/stop.png"),
                    Util.cargarIcono12("/resources/stop_close.png"),
                    Util.cargarIcono12("/resources/ejecutar.png"),
                    Util.cargarIcono12("/resources/folder.png"),
                    Util.cargarIcono12("/resources/close.png"),
                    Util.cargarIcono12("/resources/monitor.png")
                });
    }
//    public void reiniciarLista() {
//        if (actualizando) {
//            return;
//        }
//        try {
//            actualizando = true;
//            for (int i = tabla.getRowCount() - 1; i > -1; i--) {
//                ((DefaultTableModel) tabla.getModel()).removeRow(i);
//            }
//
//            List<Transferencia> lstTmp = gestor.getLista();
//
//            for (Transferencia p : lstTmp) {
//                ((DefaultTableModel) tabla.getModel()).addRow(
//                        new Object[]{
//                            p.getId(),//id
//                            p,
//                            p instanceof Descarga ? "Descarga" : "Carga",
//                            p.getIcono(),//icono
//                            p.getNombreArchivo(),
//                            p.getAvance(),
//                            p.getActual(),
//                            p.getTotal(),
//                            //p.getContador().getTasaBytesFormated(),
//                            Util.convertirBytes((float) p.getContador().getTasa()) + "/s",
//                            p.getTiempoTranscurrido(),
//                            p.getTiempoRestante(),
//                            p.getAsociado() != null ? p.getAsociado().getInformacion() : "N/A",
//                            p.isPausado() ? Util.cargarIcono12("/resources/start.png") : Util.cargarIcono12("/resources/stop.png"),
//                            Util.cargarIcono12("/resources/stop_close.png"),
//                            Util.cargarIcono12("/resources/ejecutar.png"),
//                            Util.cargarIcono12("/resources/folder.png"),
//                            Util.cargarIcono12("/resources/close.png"),
//                            Util.cargarIcono12("/resources/monitor.png")
//                        });
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        actualizando = false;
//    }

    public void esperarActualizacion() {
        while (actualizando) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {

            }
        }
    }

    public void eliminarTerminados() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                esperarActualizacion();
                actualizando = true;
                try {
                    //List<Transferencia> lstTmp = gestor.getLista();
                    List<Transferencia> lstTmp = new ArrayList<>();
                    lstTmp.addAll(gestor.getLista());

                    for (Transferencia p : lstTmp) {
                        if (!p.isActivo()) {
                            eliminarTransferencia(p);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                actualizando = false;
                //reiniciarLista();
            }

        }).start();

    }

    class Actualizador extends Thread {

        private Accion accion;

        public Actualizador(Accion accion) {
            this.accion = accion;
        }

        public void run() {

            while (true) {
                try {
                    sleep(250);
                    if (accion != null) {
                        accion.ejecutar();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

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

    static class CeldaProgreso extends DefaultTableCellRenderer {

        private final JProgressBar progreso = new JProgressBar(0, 100);

        public CeldaProgreso() {
            super();
            setOpaque(true);
            progreso.setStringPainted(true);
//            progreso.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Integer i = (Integer) value;
            String text = "Completo";
            if (i < 0) {
                text = "Error";
            } else if (i < 100) {
                progreso.setValue(i);
                //text = i + "%";
                return progreso;
            }
            super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
            return this;
        }

//        public void setValue(Object value) {
//            if (value == null) {
//                setText("");
//            } else {
//                long peso = Long.valueOf(value.toString());
//                setText(Util.convertirBytes(peso));
//            }
//        }
    }

    static class CeldaIcono extends DefaultTableCellRenderer {

        @Override
        public void setValue(Object value) {
            try {
                ImageIcon campo = (ImageIcon) value;
                if (campo == null) {
                    setText("");
                } else {
                    try {
                        //setIcon(new ImageIcon(new ImageIcon(campo.getIcono()).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
                        setIcon(new ImageIcon(campo.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
//                            setIcon(campo);
                    } catch (Exception e) {
                        setText("");
                    }

                }
            } catch (Exception e) {
//                setText("ERROR:" + value.toString());
            }
        }
    }

}
