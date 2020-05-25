/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.pc.terminal.caret;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author alberto
 */
public class testcaret {
    
     public static void main(String args[]) {
        JFrame frame = new JFrame("FancyCaret demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea area = new JTextArea(8, 32);
        area.setCaret(new FancyCaret());
//        area.setCaret(new Caret2());
//area.setCaret(new CornerCaret());
        area.setText("VI\tVirgin Islands \nVA      Virginia\nVT\tVermont");
        frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
