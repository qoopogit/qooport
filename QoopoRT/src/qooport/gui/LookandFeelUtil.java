/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.gui;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import qooport.Global;
import qooport.Inicio;
import qooport.avanzado.ModoAvanzado;

/**
 *
 * @author alberto
 */
public class LookandFeelUtil {

    public static List<String> getListaTemas() {
        List<String> tmp = new ArrayList<>();

        //tmp.add(UIManager.getSystemLookAndFeelClassName());
        tmp.add("Sistema Operativo");
        tmp.add("javax.swing.plaf.metal.MetalLookAndFeel");
        tmp.add("javax.swing.plaf.nimbus.NimbusLookAndFeel");//ok 2
        tmp.add("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        tmp.add("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        tmp.add("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");//
        tmp.add("com.jtattoo.plaf.acryl.AcrylLookAndFeel"); //ok 5
        tmp.add("com.jtattoo.plaf.aero.AeroLookAndFeel");
        tmp.add("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        tmp.add("com.jtattoo.plaf.fast.FastLookAndFeel");
        tmp.add("com.jtattoo.plaf.graphite.GraphiteLookAndFeel"); //ok 5 -----
        tmp.add("com.jtattoo.plaf.luna.LunaLookAndFeel");
        tmp.add("com.jtattoo.plaf.mint.MintLookAndFeel");
        tmp.add("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        tmp.add("com.jtattoo.plaf.noire.NoireLookAndFeel"); //ok 4
        tmp.add("com.jtattoo.plaf.smart.SmartLookAndFeel");
        tmp.add("com.jtattoo.plaf.texture.TextureLookAndFeel");//ok 5
        tmp.add("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); //ok 5
        return tmp;
    }

    public static void aplicarTemaDefault() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//la del sistema operativo
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void aplicarTema(String tema) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (tema != null && !tema.isEmpty()) {
            if (tema.equals("Sistema Operativo")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                UIManager.setLookAndFeel(tema);
            }
        }
    }

    public static void actualizar() {
        for (Window window : ModoAvanzado.getFrames()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
        for (Window window : Inicio.getFrames()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
//a la ventana de trasnferencias
        SwingUtilities.updateComponentTreeUI(Global.transferencias);

    }

}
