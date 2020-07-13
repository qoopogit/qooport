package rt.modulos.escritorio.comunes;

import comunes.Captura;
import comunes.CapturaOpciones;
import comunes.Evento;
import comunes.Interfaz;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import javax.swing.KeyStroke;
import rt.modulos.escritorio.comunes.capturador.CapturaDirectRobot;
import rt.modulos.escritorio.comunes.capturador.CapturaJNAFirnass;
import rt.modulos.escritorio.comunes.capturador.CapturaJNAWinAPI;
import rt.modulos.escritorio.comunes.capturador.CapturaJNAWinRobot;
import rt.modulos.escritorio.comunes.capturador.CapturaPrintScreen;
import rt.modulos.escritorio.comunes.capturador.CapturaRobot;
import rt.modulos.escritorio.comunes.capturador.Capturador;
import rt.modulos.escritorio.comunes.capturador.robot.DirectRobot;
import rt.modulos.escritorio.comunes.detector.Celdas;
import rt.modulos.escritorio.comunes.detector.Completa;
import rt.modulos.escritorio.comunes.detector.DetectorCambios;
import rt.modulos.escritorio.comunes.detector.Pixeles;
import rt.util.IMG;
import rt.util.UtilRT;

/**
 * Representa un monitor o todos los monitores juntos Esta clase se encargara de
 * monitorear si existen cambios en la pantalla En caso de existir cambios
 * agregara una captura con los cambios en el buffer, dependiento del tipo de
 * envio que pueden ser completa, bloques, cambios pixeles.
 *
 * @author alberto
 */
public class Pantalla extends Thread implements Interfaz, Serializable {

    private int monitor;//usada cuando se captura de todos los monitores a la vez
    //si es parte de varias pantallas cada pantalla tendra un monitor unico configurado
    private boolean variasPantallas = false;//se usa para saber si es parte de un arreglo de pantallas, de esta forma solo configura le monitor con las opciones cuano no es parte
    private DetectorCambios detector;
    private Capturador capturador;
    private boolean activo;//usada cuando funcione como detector de cambios V3
    private Robot robot;
    private DirectRobot directRobot;
    private Rectangle recuadro;
    private GraphicsDevice[] monitores;
    private BufferedImage cursor;// el cursor que se va a enviar
    private Interfaz servicio;
    private long tCaptura = 0;// tiempo q toma capturar imagen
    private long tProcesoCambios = 0;// tiempo q toma procesar imagen y armar objeto captura
    private boolean monitorConfigurado = false;//indica si ya se configuro el monitor
    private BufferedImage imagen = null;//variable donde se almacena la imagen que fue tomada
    private Captura captura = null;//variable donde se almacena la captura que se realiza

    //parametros iniciales
    private CapturaOpciones opciones = UtilRT.opcionesIniciales;

    public void Pantalla() {
        cargarMonitores();
        iniciarDetector(opciones);
        iniciarCapturador(opciones);
    }

    private void cargarMonitores() {
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        monitores = g.getScreenDevices();
    }

    private void iniciarDetector(CapturaOpciones opciones) {
        detector = null;
        switch (opciones.getTipoEnvio()) {
            case 1://envia imagen completa
                detector = new Completa();
                break;
            case 2: // cambios de pantalla en pixeles
                detector = new Pixeles();
                break;
            default:
                // cambios de pantalla en bloques
                detector = new Celdas();
                break;
        }
        detector.setOpciones(opciones);
    }

    private void iniciarCapturador(CapturaOpciones opciones) {
        capturador = null;
        switch (opciones.getOrigenCaptura()) {
            case 1:
                capturador = new CapturaRobot();
                break;
            case 2:
                capturador = new CapturaPrintScreen();
                break;
            case 3:
                capturador = new CapturaDirectRobot();
                break;
            case 4://nativo firnass
                capturador = new CapturaJNAFirnass();
                break;
            case 5://nativo winrobot (solo windows)
                capturador = new CapturaJNAWinRobot();
                break;
            case 6://nativo win API (solo windows)
                capturador = new CapturaJNAWinAPI();
                break;
        }
        capturador.setRobot(robot);
        capturador.setDirectRobot(directRobot);
        capturador.setServicio(servicio);
    }

    //metodo usado en la segunda version
    //lleno el buffer con las diferencias
    @Override
    public void run() {
//        this.setName("hilo-Pantalla-" + UtilRT.getHiloId());
        limpiar();
        try {
            while (activo) {
                obtenerCaptura();
                if (captura != null) {
                    BufferCaptura.agregar((String) servicio.get(10), captura);
                }
                captura = null;
                //el delay ayuda a liberar uso cpu
                //limite teorico de captura 66.66 fps, un monitor refresca hasta 60 fps por lo q no se necesita un delay menor
                UtilRT.dormir(10);
            }
        } catch (Exception e) {
            detener();
        }
    }
//-------------------------------------------------------------------------------
//---                   METODOS DE EJECUCION
//-------------------------------------------------------------------------------

    private void iniciar() {
        activo = true;
        iniciarDetector(opciones);
        iniciarCapturador(opciones);
        start();
    }

    private void detener() {
        try {
            activo = false;
            if (detector != null) {
                detector.liberar();
            }
            detector = null;
            capturador = null;
            monitores = null;
            robot = null;
            directRobot = null;
            recuadro = null;
            cursor = null;
            servicio = null;
            imagen = null;
            captura = null;
            interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Captura obtenerCaptura() {
        capturarIMG();
        procesarCambios();
        return captura;
    }

    //ajusta los parametros automaticamente de acuerdo al tiempo de conexion
    // y a la velocidad de red
    private void ajustarParametros() {
        try {
            //verifico velocidad de red  para ajustar calidad
            if (opciones.isCalidadAutomatica()) {
                //tipo de color de imagen
                //private int tipoColor = 4; //1.- Blanco y negro,2.- gris, 3.- 256 colores, 4.- 16 bits 655356 colores, 5.- 24 bits, 6. 32 bits
                long t = ((Long) servicio.get(9));
//                System.out.println("tiempo de ping " + t);
                if (t > 2000) {
                    opciones.setTipoColor(2);
                    opciones.setCalidad(0.25f);
                    //opciones.setConvertirJpg(false);
                } else if (t > 1500) {
                    opciones.setTipoColor(2);
                    opciones.setCalidad(0.3f);
//                    opciones.setConvertirJpg(false);
                } else if (t > 1000) {
                    opciones.setTipoColor(2);
                    opciones.setCalidad(0.4f);
//                    opciones.setConvertirJpg(false);
                } else if (t > 800) {
                    opciones.setTipoColor(3);
                    opciones.setCalidad(0.5f);
//                    opciones.setConvertirJpg(false);
                } else if (t > 700) {
                    opciones.setTipoColor(3);
                    opciones.setCalidad(0.6f);
//                    opciones.setConvertirJpg(false);
                } else if (t > 600) {
                    opciones.setTipoColor(4);
                    opciones.setCalidad(0.65f);
//                    opciones.setConvertirJpg(true);
                } else if (t > 500) {
                    opciones.setTipoColor(4);
                    opciones.setCalidad(0.7f);
//                    opciones.setConvertirJpg(true);
                } else if (t > 250) {
                    opciones.setTipoColor(5);
                    opciones.setCalidad(0.75f);
//                    opciones.setConvertirJpg(true);
                } else if (t > 150) {
                    opciones.setTipoColor(5);
                    opciones.setCalidad(0.8f);
//                    opciones.setConvertirJpg(true);
                } else if (t > 100) {
                    opciones.setTipoColor(6);
                    opciones.setCalidad(0.85f);
//                    opciones.setConvertirJpg(true);
                } else {
//                    if (t > 40) {
                    opciones.setTipoColor(6);
                    opciones.setCalidad(0.95f);
//                    opciones.setConvertirJpg(true);
//                } else {
//                    tipoColor = 6;
//                    calidad = 1f;
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private Point getCursorPosicion() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        p.x = p.x - recuadro.x;
        p.y = p.y - recuadro.y;
        return p;
    }

    /**
     * Pinta el cursor y lo prepara para ser enviado
     *
     * @param imageEscritorio
     * @return
     */
    private void pintarCursor(BufferedImage imageEscritorio) {
        //si hay q dibujar o enviar el cursor
        if (opciones.isEnviarCursor() || opciones.isMostrarCursor()) {
            cursor = null;
            try {
                if (servicio.get(7) != null) {
                    cursor = (BufferedImage) ((Interfaz) servicio.get(7)).get(3);
                }
                if (cursor == null) {
                    cursor = UtilRT.CURSOR_PNG;
                }
            } catch (Exception e) {
                cursor = UtilRT.CURSOR_PNG;
            }
            if (opciones.isMostrarCursor()) {
                Point p = getCursorPosicion();
                imageEscritorio.getGraphics().drawImage(cursor, p.x, p.y, null);
                p = null;
            }
            if (!opciones.isEnviarCursor()) {
                //si no se envia el cursor liberamos memoria
                cursor = null;
            }
        }
    }

    //PROCESAR IMAGEN (FILTRO DE COLOR Y ESCALA)
    // SI SE PROCESEA SE REDUCE TIEMPO DE ENVIO PERO SE PENA TIEMPO DE PROCESAMIENTO
    // EN CASO DE TENER TIEMPOS DE ENVIO MUY CORTOS (RED LAN), SE DEBERIA EVITAR EL PROCESAMIENTO DE LA IMAGEN
    private BufferedImage procesarImagen(BufferedImage imagen) {
        //BufferedImage salida;
        //si el servidor debe escalar (de este lado)
        if (opciones.isEscalar()) {
            imagen = IMG.escalar(
                    imagen,
                    opciones.getTipoEscala(),
                    opciones.getEscala(),
                    opciones.isSuavizado(),
                    opciones.getAncho(),
                    opciones.getAlto()
            );
        }

        imagen = IMG.filtrar(imagen, opciones.getTipoColor(), opciones.getTipoDatos());
        return imagen;
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    //obtiene la imagen actual del monitor
    private void capturarIMG() {
        long tInicio = System.currentTimeMillis();
        ajustarParametros();//ajusta parametros automaticamente
        imagen = null;
        try {
            imagen = capturador.capturar(recuadro);
            if (imagen != null) {
                imagen.setAccelerationPriority(1.0F);
                pintarCursor(imagen);
                //imageEscritorio = pintarCursor(imageEscritorio);
                imagen = procesarImagen(imagen);
            }
        } catch (OutOfMemoryError ex) {
            imagen = null;
            UtilRT.gc();

        } catch (Exception ex) {
            imagen = null;
        }
        long tFin = System.currentTimeMillis();
        tCaptura = (tFin - tInicio);       
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    //                                              PROCESAR LOS CAMBIOS EXISTENTES
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Procesa una nueva imagen en busca de cambios
     *
     * @param imagen
     * @return
     */
    private void procesarCambios() {
        long tInicio = System.currentTimeMillis();
        captura = armarCaptura(imagen);
        try {
            captura.setBloques(detector.procesarCambios(imagen));
        } catch (Exception e) {
//            e.printStackTrace();
        }
//        if (captura.getBloques() == null || captura.getBloques().isEmpty()) {
//            captura = null;
//        }

        long tFin = System.currentTimeMillis();
        tProcesoCambios = (tFin - tInicio);
        //return captura;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    //                                              CONSTRUIR LA CAPTURA
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    private Captura armarCaptura(BufferedImage imagen) {
//        long tInicio = System.currentTimeMillis();
//        if (captura == null) {
        //captura = new Captura();
        captura = UtilRT.capturaLimpia;
//        }
        try {
            captura.setPantalla(monitor);
            captura.settCaptura(tCaptura); // tiempo de captura
            captura.settProceso(tProcesoCambios);//tiempo q se demora en procesar los cambios
            captura.setTamBuffer(BufferCaptura.getSize((String) servicio.get(10)));
            captura.setAncho(imagen.getWidth());
            captura.setAlto(imagen.getHeight());
            captura.setTipo(opciones.getTipoEnvio());
            captura.setCalidad(opciones.getCalidad());
            captura.setJpg(opciones.isConvertirJpg());
            captura.setSaltadas(BufferCaptura.saltadas);
            captura.setPorcentaje(detector.getPorDiferencia());
            captura.setTipoImagen(imagen.getType());//toma el tipo q fue asignado en el filtro
            switch (opciones.getTipoColor()) {
                case 1:
//                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_BINARY);
                    captura.setBits(2);//blanco y negro de un 2 bits
                    break;
                case 2:
//                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_GRAY);
                    captura.setBits(4);
                    break;
                case 3:
//                    captura.setTipoImagen(BufferedImage.TYPE_BYTE_INDEXED);
                    captura.setBits(8);
                    break;
                case 4:
//                    captura.setTipoImagen(BufferedImage.TYPE_USHORT_555_RGB);
                    captura.setBits(16);
                    break;
                case 5:
//                    captura.setTipoImagen(BufferedImage.TYPE_3BYTE_BGR);
                    captura.setBits(24);
                    break;
                case 6:
//                    captura.setTipoImagen(BufferedImage.TYPE_4BYTE_ABGR);
                    captura.setBits(32);
                    break;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
//        long tFin = System.currentTimeMillis();
//        if (DEBUG) {
//            System.out.println("Tiempo cabecera=" + (tFin - tInicio) + "ms");
//        }
        return captura;
    }

    //configura el monitor seleccionado
    private void configurarMonitor(int monitor) {
        this.monitor = monitor;//esta variable es usada para enviar en la captura
        if (monitor == opciones.getMonitor() && monitorConfigurado) {
            return;
        }
        try {

            robot = null;
            directRobot = null;
            recuadro = null;
            if (monitor == -1) {
                //todos los monitores
                robot = new Robot();
                directRobot = new DirectRobot();
                //recuadro = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                //toma la dimension de todos los monitores
                recuadro = new Rectangle();

                //calculo las coordenadas menores xy y las mayres luego armo el cuadro en base a estas coordenadas
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                for (GraphicsDevice screen : monitores) {
                    Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
                    x1 = Math.min(x1, screenBounds.x);
                    y1 = Math.min(y1, screenBounds.y);
                    x2 = Math.max(x2, screenBounds.width + screenBounds.x);
                    y2 = Math.max(y2, screenBounds.height + screenBounds.y);
                }

                recuadro.x = x1;
                recuadro.y = y1;
                recuadro.width = x2 - x1;
                recuadro.height = y2 - y1;
            } else {
                if (monitores == null || monitores.length == 0) {
                    cargarMonitores();
                }

                //un monitor en particular
                robot = new Robot(monitores[monitor]);
                directRobot = new DirectRobot(monitores[monitor]);
                recuadro = monitores[monitor].getDefaultConfiguration().getBounds();
            }
            robot.setAutoDelay(0);
            robot.setAutoWaitForIdle(false);

        } catch (Exception e) {
            recuadro = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        }

        if (capturador != null) {
            capturador.setRobot(robot);
            capturador.setDirectRobot(directRobot);
        }
        monitorConfigurado = true;
    }

    private void configurarBuffer(int tamBuffer) {
        if (tamBuffer == opciones.getTamBuffer()) {
            return;
        }
        BufferCaptura.iniciarParametros((String) servicio.get(10), tamBuffer);
    }

    private void limpiar() {
        synchronized (this) {
            if (detector != null) {
                detector.limpiar();
            }
            BufferCaptura.limpiar((String) servicio.get(10));
        }
    }

    private void CTR_ALT_SUPR() {
        try {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(150);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.delay(150);
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_DELETE);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.delay(150);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (Exception e) {

        }
//                                try {
//                                    CapturaRobot.keyPress(KeyEvent.VK_CONTROL);
//                                    CapturaRobot.delay(150);
//                                    CapturaRobot.keyPress(KeyEvent.VK_ALT);
//                                    CapturaRobot.delay(150);
//                                    CapturaRobot.keyPress(KeyEvent.VK_ESCAPE);
//                                    CapturaRobot.delay(150);
//                                    CapturaRobot.keyRelease(KeyEvent.VK_ESCAPE);
//                                    CapturaRobot.delay(150);
//                                    CapturaRobot.keyRelease(KeyEvent.VK_ALT);
//                                    CapturaRobot.delay(150);
//                                    CapturaRobot.keyRelease(KeyEvent.VK_CONTROL);
//                                } catch (Exception e) {
//                                }
//                                try {
//                                    KeyEvent keyEvent = new KeyEvent(null, 0, 0L, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_DELETE, (char) 127);
//                                    CapturaRobot.keyPress(KeyEvent.VK_CONTROL);
//                                    CapturaRobot.keyPress(KeyEvent.VK_ALT);
//                                    CapturaRobot.keyPress(keyEvent.getKeyCode());
//                                    CapturaRobot.keyRelease(KeyEvent.VK_ALT);
//                                    CapturaRobot.keyRelease(KeyEvent.VK_CONTROL);
//                                    CapturaRobot.keyRelease(keyEvent.getKeyCode());
//                                } catch (Exception e) {
//
//                                }
    }

    private void eventoTecla(int tipoEvento, int tecla) {
        switch (tipoEvento) {
            case 1: //presionado
                if (opciones.getOrigenCaptura() == 3) {
                    directRobot.keyPress(tecla);
                } else {
                    robot.keyPress(tecla);
                }
//                System.out.println("kp " + tecla);
                break;
            case 2: //liberado
                if (opciones.getOrigenCaptura() == 3) {
                    directRobot.keyRelease(tecla);
                } else {
                    robot.keyRelease(tecla);
                }
//                System.out.println("kr " + tecla);
                break;
            case 3://tecla presionadoa, viene el keychar y no e keycode
                try {
                char c = (char) tecla;
//                    System.out.println("llega keychar " + c);

                KeyStroke ks = KeyStroke.getKeyStroke(c);
                if (opciones.getOrigenCaptura() == 3) {
                    directRobot.keyPress(ks.getKeyCode());
                    directRobot.keyRelease(ks.getKeyCode());
                } else {
                    robot.keyPress(ks.getKeyCode());
                    robot.keyRelease(ks.getKeyCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
    }

    private void eventoMouse(float x, float y, int evento, int boton, int wheel) {
        try {
            int px = (int) (x * recuadro.width / 100) + recuadro.x;
            int py = (int) (y * recuadro.height / 100) + recuadro.y;

            if (opciones.getOrigenCaptura() == 1) {
                robot.mouseMove(px, py);
            } else {
                directRobot.mouseMove(px, py);
            }
            switch (evento) {
                case 5:// evento dragged, puede tener un boton presionado
                    if (opciones.getOrigenCaptura() == 1) {
                        robot.mousePress(boton);
                    } else {
                        directRobot.mousePress(boton);
                    }
                    break;
                case 1: //presionado
                    if (opciones.getOrigenCaptura() == 1) {
                        robot.mousePress(boton);
                    } else {
                        directRobot.mousePress(boton);
                    }
                    break;
                case 2: //liberado
                    if (opciones.getOrigenCaptura() == 1) {
                        robot.mouseRelease(boton);
                    } else {
                        directRobot.mouseRelease(boton);
                    }
                    break;
                case 4://wheel
                    //System.out.println("evento wheel " + wheel);
                    if (opciones.getOrigenCaptura() == 1) {
                        robot.mouseWheel(wheel);
                    } else {
                        directRobot.mouseWheel(wheel);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//-------------------------------- GUETTERS Y SETTERS----------------------------------------------------------
    public void setOpciones(CapturaOpciones opciones) {
        //solo configura el monitor si no es parte de varias pantallas
        if (!variasPantallas) {
            configurarMonitor(opciones.getMonitor());
        }
        configurarBuffer(opciones.getTamBuffer());
        if (detector != null) {
            detector.setOpciones(opciones);
        } else {
            iniciarDetector(opciones);
        }
        this.opciones = opciones;
    }

    @Override
    public void instanciar(Object... parametros) {
        cargarMonitores();
    }

    @Override
    public void set(int opcion, Object valor) {
        try {
            switch (opcion) {
                case 0:
                    this.servicio = (Interfaz) valor;
                    if (capturador != null) {
                        capturador.setServicio(servicio);
                    }
                    break;
                case 3:
                    setOpciones((CapturaOpciones) valor);
                    break;
                case 6:
                    monitor = (Integer) valor;
                    variasPantallas = true;
                    configurarMonitor(monitor);
                    break;

            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Override
    public Object get(int opcion, Object... parametros) {
        try {
            switch (opcion) {
                case 0:
                    return servicio;
                case 1:
                    return obtenerCaptura();
                case 2:
                    return getCursorPosicion();
                case 3:
                    return opciones;
                case 4:
                    return tCaptura;
                case 5:
                    return cursor;
                case 6:
                    return monitor;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    private void ejecutarEventos(List<Evento> lista) {
        for (Evento evento : lista) {
//            System.out.println("ejecutando evento");
            //si es parte de varias pantallas y no es la pantalla del evento ps lo ignoramos
            if (variasPantallas && evento.getPantallaID() != monitor) {
                continue;
            }
//            System.out.println("si se ejecuta el evento");
            switch (evento.getTipo()) {
                case 1://teclado
                    if (evento.getEvento() != 3) {
                        eventoTecla(evento.getEvento(), evento.getKeycode());
                    } else {
                        eventoTecla(evento.getEvento(), evento.getChartecla());
                    }
                    break;
                case 2://mouse
                    eventoMouse(evento.getX(), evento.getY(), evento.getEvento(), evento.getBoton(), evento.getWheel());
                    break;
            }
        }
    }

    @Override
    public void ejecutar(int opcion, Object... parametros) {
        try {
            switch (opcion) {
                case 0:
                    iniciar();
                    break;
                case 1:
                    detener();
                    break;
                case 2:
                    limpiar();
                    break;
                case 3:
                    CTR_ALT_SUPR();
                    break;
                case 4:
                    eventoTecla((Integer) parametros[0], (Integer) parametros[1]);
                    break;
                case 5:
                    eventoMouse((Float) parametros[0], (Float) parametros[1], (Integer) parametros[2], (Integer) parametros[3], (Integer) parametros[4]);
                    break;
                case 6:
//                    System.out.println("tam parametros " + parametros.length);
                    ejecutarEventos((List<Evento>) parametros[0]);
                    break;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
