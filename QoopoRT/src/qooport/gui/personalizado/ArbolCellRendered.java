package qooport.gui.personalizado;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import qooport.utilidades.Util;

/**
 *
 * @author alberto
 */
public class ArbolCellRendered extends JLabel implements TreeCellRenderer {

    private ImageIcon[] iconos;
    private boolean seleccionado;

    public ArbolCellRendered() {
        iconos = new ImageIcon[19];
        iconos[0] = Util.cargarIcono16("/resources/server.png");
        iconos[1] = Util.cargarIcono16("/resources/toolbox.png");
        iconos[2] = Util.cargarIcono16("/resources/remoto.png");
        iconos[3] = Util.cargarIcono16("/resources/camera.png");
        iconos[4] = Util.cargarIcono16("/resources/voip.png");
        iconos[5] = Util.cargarIcono16("/resources/clipboard.png");
        iconos[6] = Util.cargarIcono16("/resources/cmd.png");
        iconos[7] = Util.cargarIcono16("/resources/info.png");
        iconos[8] = Util.cargarIcono16("/resources/folder.png");
        iconos[9] = Util.cargarIcono16("/resources/toolbox.png");
        iconos[10] = Util.cargarIcono16("/resources/procesos.png");
        iconos[11] = Util.cargarIcono16("/resources/conexiones.png");
        iconos[12] = Util.cargarIcono16("/resources/plugin.png");
        iconos[13] = Util.cargarIcono16("/resources/android16.png");
        iconos[14] = Util.cargarIcono16("/resources/map.png");
        iconos[15] = Util.cargarIcono16("/resources/sms.png");
        iconos[16] = Util.cargarIcono16("/resources/llamada16.png");
        iconos[17] = Util.cargarIcono16("/resources/contactos.png");
        iconos[18] = Util.cargarIcono16("/resources/chat.png");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
        String texto = (String) nodo.getUserObject();
        this.seleccionado = selected;
        if (!seleccionado) {
            setForeground(Color.black);
        } else {
            setForeground(Color.BLUE);
        }
        switch (texto) {
            case "Información":
                setIcon(iconos[7]);
                break;
            case "Opciones":
                setIcon(iconos[0]);
                break;
            case "Herramientas":
                setIcon(iconos[1]);
                break;
            case "Escritorio Remoto":
                setIcon(iconos[2]);
                break;
            case "Cámara":
                setIcon(iconos[3]);
                break;
            case "VoIP":
                setIcon(iconos[4]);
                break;
            case "Administrar":
                setIcon(iconos[9]);
                break;
            case "Archivos":
                setIcon(iconos[8]);
                break;
            case "Consola":
                setIcon(iconos[6]);
                break;
            case "Portapapeles":
                setIcon(iconos[5]);
                break;
            case "Procesos":
                setIcon(iconos[10]);
                break;
            case "Conexiones":
                setIcon(iconos[11]);
                break;
            case "Plugins":
                setIcon(iconos[12]);
                break;
            case "WebCam":
                setIcon(iconos[12]);
                break;
            case "NirSoft":
                setIcon(iconos[12]);
                break;
            case "Android":
                setIcon(iconos[13]);
                break;
            case "GPS":
                setIcon(iconos[14]);
                break;
            case "SMS":
                setIcon(iconos[15]);
                break;
            case "Llamadas":
                setIcon(iconos[16]);
                break;
            case "Contactos":
                setIcon(iconos[17]);
                break;
            case "Chat":
                setIcon(iconos[18]);
                break;
        }
        setText(texto);
        return (this);
    }
}
