/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author alberto
 */
public class ProgresoGeneral extends JPanel {

    private long total;
    private long avance;
    private JProgressBar progreso;
    private JLabel lblIcono;

    public ProgresoGeneral(JLabel lblIcono, JProgressBar progreso) {
        this.progreso = progreso;
        this.lblIcono = lblIcono;
        this.add(lblIcono);
        this.add(progreso);
    }

    public ProgresoGeneral() {
        this.progreso = new JProgressBar();
        this.add(lblIcono);
        this.add(progreso);
    }

    public ProgresoGeneral(JLabel lblIcono, int ancho) {
        this.lblIcono = lblIcono;
        this.progreso = new JProgressBar();
        this.add(lblIcono);
        this.add(progreso);
        Dimension d2 = this.getPreferredSize();
        d2.width = ancho;
        this.setPreferredSize(d2);
    }

    public void mostrarAvance() {
        try {
            if (progreso != null) {
                progreso.setValue((int) (avance * 100L / total));
            }
        } catch (Exception e) {
        }
    }

    public void agregarTotal(long monto) {
        total += monto;
        mostrarAvance();
    }

    public void quitarTotal(long monto) {
        total -= monto;
        mostrarAvance();
    }

    public void quitarAvance(long monto) {
        avance -= monto;
        mostrarAvance();
    }

    public void agregarAvance(long monto) {
        avance += monto;
        mostrarAvance();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAvance() {
        return avance;
    }

    public void setAvance(long avance) {
        this.avance = avance;
    }

}
