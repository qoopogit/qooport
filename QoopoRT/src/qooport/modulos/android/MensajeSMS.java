package qooport.modulos.android;

import comunes.Sms;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static qooport.avanzado.QoopoRT.tipoLetra;
import qooport.utilidades.Util;

public class MensajeSMS extends JPanel {

    private Sms mensaje;
    private JLabel textoRecibido;
    private JLabel fechaRecibido;
    private JLabel iconoRecibido;
    private JLabel textoEnviado;
    private JLabel fechaEnviado;
    private JLabel iconoEnviado;

    public MensajeSMS(Sms sms) {
        initComponents();
        this.mensaje = sms;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (sms.getType() == 1) {
            this.fechaRecibido.setFont(new Font("Arial", 0, 12));
            this.fechaRecibido.setText(sdf.format(new Date(sms.getDate())));
            this.textoRecibido.setFont(new Font("Arial", 2, 16));
            this.textoRecibido.setText(sms.getBody());
            this.iconoRecibido.setIcon(Util.cargarIcono16("/resources/autor.png"));
            setBorder(BorderFactory.createLineBorder(new Color(150, 200, 200), 1, true));
            this.setBackground(new Color(150, 200, 200));
        } else {
            this.fechaEnviado.setFont(new Font(tipoLetra, 0, 12));
            this.fechaEnviado.setText(sdf.format(new Date(sms.getDate())));
            this.textoEnviado.setFont(new Font(tipoLetra, 1, 16));
            this.textoEnviado.setText(sms.getBody());
            this.iconoEnviado.setIcon(Util.cargarIcono16("/resources/autor.png"));
            setBorder(BorderFactory.createLineBorder(new Color(200, 200, 150), 2, true));
            this.setBackground(new Color(200, 200, 150));
        }
    }

    private void initComponents() {
        textoRecibido = new JLabel();
        fechaRecibido = new JLabel();
        iconoRecibido = new JLabel();
        textoEnviado = new JLabel();
        fechaEnviado = new JLabel();
        iconoEnviado = new JLabel();
//        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 102, 51)));
//        setBorder(BorderFactory.createTitledBorder(null, "", 0, 0, new Font(tipoLetra, 1, 12), new Color(0, 102, 51)));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        alinear(layout);
    }

    public void alinear(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(iconoRecibido)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.textoRecibido)
                                        .addGap(5)
                                        .addComponent(this.fechaRecibido)
                        )
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.textoEnviado)
                                        .addGap(5)
                                        .addComponent(this.fechaEnviado)
                        )
                        .addComponent(iconoEnviado)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(iconoRecibido)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(this.textoRecibido)
                                        .addComponent(this.fechaRecibido)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(this.textoEnviado)
                                        .addComponent(this.fechaEnviado)
                        )
                        .addComponent(iconoEnviado)
        );
    }

    public Sms getMensaje() {
        return mensaje;
    }

    public void setMensaje(Sms mensaje) {
        this.mensaje = mensaje;
    }
}
