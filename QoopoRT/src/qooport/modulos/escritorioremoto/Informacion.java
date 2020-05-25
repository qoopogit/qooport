package qooport.modulos.escritorioremoto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import qooport.utilidades.GuiUtil;
import qooport.utilidades.Util;

public class Informacion extends JFrame {

    private EscritorioRemoto ventana;

    public Informacion(EscritorioRemoto escritorio) {
        this.ventana = escritorio;
        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(15, 2));

        panel.add(GuiUtil.crearJLabel("FPS:"));
        panel.add(ventana.getContadorFps().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Velocidad (Bps):"));
        panel.add(ventana.getContadorBps().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Tamaño captura:"));
        panel.add(ventana.getContadorB().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Calidad JPG:"));
        panel.add(ventana.getContadorCalidad().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Tiempo Captura:"));
        panel.add(ventana.getContadorTCaptura().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Tiempo Proceso:"));
        panel.add(ventana.getContadorTProceso().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Tiempo Envío:"));
        panel.add(ventana.getContadorTEnvio().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Tamaño Buffer:"));
        panel.add(ventana.getContadorBuffer().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Capturas saltadas:"));
        panel.add(ventana.getContadorSaltadas().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Color:"));
        panel.add(ventana.getContadorBits().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Cambios celdas:"));
        panel.add(ventana.getContadorBloques().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Cambios %:"));
        panel.add(ventana.getContadorPorcentaje().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Celdas nuevas:"));
        panel.add(ventana.getContadorCeldasNuevas().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Celdas repetidas:"));
        panel.add(ventana.getContadorCeldasRepetidas().getLblEstado());

        panel.add(GuiUtil.crearJLabel("Celdas repetidas captura:"));
        panel.add(ventana.getContadorCeldasRC().getLblEstado());

        //TABS
        this.setLayout(new BorderLayout());
//        this.add(barra, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setIconImage(Util.cargarIcono16("/resources/controlpanel.png").getImage());
        this.setSize(600, 450);
        this.setResizable(true);
        this.setVisible(true);
        pack();
    }

}
