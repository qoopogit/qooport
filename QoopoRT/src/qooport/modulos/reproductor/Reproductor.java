/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductor;

import comunes.Captura;
import comunes.PantallaBloque;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import qooport.utilidades.QoopoIMG;
import qooport.utilidades.SerializarUtil;
import qooport.utilidades.Util;

/**
 *
 * @author aigarcia
 */
public class Reproductor {

    private int TC;
    private int TCA;
    private int TN;
    private File directorioEscritorio;
    private boolean verCeldas = false;
    private boolean grabar = false;
    private File archivoCaptura;//archivo donde se guarda la captura

    //mapa de las imagenes de cada monitor
    private Map<Integer, BufferedImage> imagen = new HashMap<>();

    // Buffer de la captura. Se utiliza para n enviar bloques que se mantienen, por ejemplo cuando se mueve una ventana
    //es un mapa, donde la clave es el numero del monitor, el valor es otro mapa
    //donde la clave es la ubicacion de la celda y el valor la celda misma
    private final Map<Integer, Map<String, PantallaBloque>> capturaPrevia = new HashMap();

    private Contenedor contenedor;

    /**
     * Devuelve la pantalal donde se va a dibujar los cambios. En caso de no
     * existir se crea una nueva
     *
     * @param ancho
     * @param alto
     * @param tipo
     * @return
     */
    private void prepararPantalla(int monitorID, int ancho, int alto, int tipo) {
        //para probrar los cambios siempre mando una imagen negra
//        return new BufferedImage(ancho, alto, tipo);
//        BufferedImage pantalla;
        // si la imagen mantiene el mismo tamanio se sigue dibujando en la misma
        if (this.imagen.get(monitorID) != null
                && ancho == this.imagen.get(monitorID).getWidth()
                && alto == this.imagen.get(monitorID).getHeight()) {
            //pantalla = this.imagen.get(monitorID);
            return;
//        } else {
//            pantalla = new BufferedImage(ancho, alto, tipo);
        }
        //coloca la nueva imagen
//        imagen.put(monitorID, pantalla);
        imagen.put(monitorID, new BufferedImage(ancho, alto, tipo));

    }

    /**
     * Dibuja el borde del bloque que recibe esto es para depuracion
     *
     * @param monitor
     * @param bloque
     * @param ancho
     * @param alto
     * @param isJpg
     * @param tipoImagen
     */
    public void dibujarRectangulo(int monitor, PantallaBloque bloque, int ancho, int alto, boolean isJpg, int tipoImagen) {
        try {
            if (imagen != null) {
                Graphics g = imagen.get(monitor).getGraphics();
                g.setColor(Color.RED);
                g.drawRect(bloque.getX(), bloque.getY(), ancho - 1, alto - 1);
                new DesDibujarRectangulo(monitor, bloque, isJpg, tipoImagen).start();
            }
        } catch (Exception e) {
        }
    }

    /**
     * Vuelve a dibujar al rectangulo para borrar
     */
    public class DesDibujarRectangulo extends Thread {

        private final PantallaBloque bloque;
        private final boolean isJpg;
        private final int tipoImagen;
        private final int monitor;

        public DesDibujarRectangulo(int monitor, PantallaBloque bloque, boolean isJpg, int tipoImagen) {
            this.bloque = bloque;
            this.isJpg = isJpg;
            this.tipoImagen = tipoImagen;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);//espera medio segundo
                //BufferedImage imagenNueva = Util.getImagenDeByteArray(bloque.getAncho(), bloque.getAlto(), descomprimir(bloque.getDatos()), isJpg, tipoImagen);
                BufferedImage imagenNueva = Util.getImagenDeByteArray(bloque.getAncho(), bloque.getAlto(), bloque.getDatos(), isJpg, tipoImagen);
                imagen.get(monitor).getGraphics().drawImage(imagenNueva, bloque.getX(), bloque.getY(), null);
                //actualizarPantalla(monitor);
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Devuelve la imagen del buffer. Si el monitor no existe se devuelve el
     * monitor compelto -1
     *
     * @param monitor
     * @return
     */
    public BufferedImage getImagen(int monitor) {
        if (monitor == 0) {
            if (!imagen.containsKey(monitor)) {
                monitor = -1;//
            }
        }
        return imagen.get(monitor);
    }

    private void grabarJPG(int monitorID) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
            File carAnioMes = new File(directorioEscritorio, sdf.format(new Date()));
            File carpedia = new File(carAnioMes, sdf2.format(new Date()));
            File carImg = new File(carpedia, "imagenes");
            carImg.mkdirs();
            ImageIO.write(getImagen(monitorID), "jpg", new File(carImg, Util.nombreHora() + ".jpg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Crea un objeto Captura nuevo con la imagen completa para agregar al
     * archivo
     *
     * @param monitor
     */
    private void grabarDAT(int monitor) {
        try {
            if (archivoCaptura == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                File carAnioMes = new File(directorioEscritorio, sdf.format(new Date()));
                File carpedia = new File(carAnioMes, sdf2.format(new Date()));
                carpedia.mkdirs();
                archivoCaptura = new File(carpedia, "sesion.dat");
            }
            //genera archivo captura
            Captura captura = new Captura();
            captura.setBloques(new ArrayList<PantallaBloque>());
            PantallaBloque bloque = new PantallaBloque();
            bloque.setDatos(QoopoIMG.saveImageJPGByte(getImagen(monitor)));
            captura.getBloques().add(bloque);
            captura.setFecha(new Date());
            agregarCaptura(captura);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega una captura al archivo .dat
     *
     * @param captura
     */
    private void agregarCaptura(Captura captura) {
        SerializarUtil.agregarObjeto(archivoCaptura.getAbsolutePath(), captura, true, true);
    }

    /**
     * Pinta la imagen
     *
     * @param monitorID
     */
    private void actualizarPantalla(int monitorID) {
        contenedor.getPantalla(monitorID).setImagen(imagen.get(monitorID));
        contenedor.getPantalla(monitorID).pintar();
    }

    public void reproducir(Captura captura) {
//        reproducir_tipo1(captura);
        reproducir_tipo2(captura);
//        try {
//            //voy a grabar el buffer en un archivo para ver q tenog en memoria solo por test
//            BufferedImage bi = QoopoIMG.construirImagen(capturaPrevia.get(captura.getPantalla()).values(),
//                    captura.getAncho(),
//                    captura.getAlto(),
//                    captura.getTipoImagen(),
//                    captura.isJpg());
//
//            ImageIO.write(bi, "jpg", new File("F:/salida2.jpg"));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

    }

    //----------------------------------------------------------------------------------------------
    /**
     * Método que dibuja todo de nuevo
     *
     * @param captura
     */
    public void reproducir_tipo1(Captura captura) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        try {
            if (captura == null) {
                return;
            }

            // prepara la imagen donde se va a dibujar
            //en formato RGB no hay problema al guardar jpg
            prepararPantalla(captura.getPantalla(), captura.getAncho(), captura.getAlto(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imgCelda;
            TC = 0;
            TCA = 0;
            TN = 0;

            //si no hay un buffer de celdas para esta pantalla creamos uno
            if (!capturaPrevia.containsKey(captura.getPantalla())) {
                capturaPrevia.put(captura.getPantalla(), new HashMap<String, PantallaBloque>());
            }

            System.out.println("actualizando buffer");
//1.- actualizo el bufer
            for (PantallaBloque celda : captura.getBloques()) {

                try {
                    //si el bloque ya existe en la anterior captura
                    if (celda.getNombreCopia() != null && !celda.getNombreCopia().isEmpty()) {
//                        System.out.println("debe ir inicio (obligatorio)");
                        //la anterior captura contiene la supuesta copia
                        if (capturaPrevia.get(captura.getPantalla()).containsKey(celda.getNombreCopia())) {
                            PantallaBloque bloque2 = capturaPrevia.get(captura.getPantalla()).get(celda.getNombreCopia());
                            celda.setDatos(bloque2.getDatos());
                            celda.setPixeles(bloque2.getPixeles());
                            celda.setTipo(bloque2.getTipo());
                            bloque2 = null;
                            TCA++;
                        } else {
                            System.out.println("SE SUPONE ES UNA COPIA PERO NO EXISTE EN ESTE BUFFER. DEBEMOS MANDAR A ACTUALIZAR LA PANTALLA");
                        }
                    } else {
                        //si es una copia de la captura actual
                        if (celda.getCopia() != -1) {
//                            System.out.println("debe ir final (opcional)");
//                                System.out.println("es copia de " + bloque.getCopia());
                            PantallaBloque bloque2 = captura.getBloques().get(celda.getCopia());
                            //tomamos los datos de la anterior captura
                            celda.setDatos(bloque2.getDatos());
                            celda.setPixeles(bloque2.getPixeles());
                            celda.setTipo(bloque2.getTipo());
                            bloque2 = null;
                            TC++;
                        } else {
//                            System.out.println("debe ir al medio (opcional)");
//                    capturaPrevia.get(captura.getPantalla()).put(celda.getNombre(), celda);
                            TN++;
                        }
                    }
                    //actualiza el buffer con los datos e la celda
                    capturaPrevia.get(captura.getPantalla()).put(celda.getNombre(), celda);

                } catch (Exception ex) {
                    System.out.println("Error al dibujar celda");
                    System.out.println("pantalla=" + captura.getPantalla());
                    System.out.println("error = " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            System.out.println("bloques del buffer =" + capturaPrevia.get(captura.getPantalla()).size());
            System.out.println("actualizando imagen desde el buffer");
            //2.- ahora dibujo todo el buffer en la imagen
            for (PantallaBloque celda : capturaPrevia.get(captura.getPantalla()).values()) {
                //obtiene la imagen de la celda
                if (celda.getTipo() == 1) {
                    if (celda.getDatos() == null) {
                        System.out.println("LOS DATOS (BYTES) SON NULOS !!!!!");
                        continue;
                    }
                    //imgBloque = Util.getImagenDeByteArray(bloque.getAncho(), bloque.getAlto(), descomprimir(bloque.getDatos()), captura.isJpg(), captura.getTipoImagen());
                    imgCelda = Util.getImagenDeByteArray(celda.getAncho(), celda.getAlto(), celda.getDatos(), captura.isJpg(), captura.getTipoImagen());
                } else {
                    if (celda.getPixeles() == null) {
                        System.out.println("LOS DATOS (PIXELES) SON NULOS !!!!!");
                        continue;
                    }
                    imgCelda = Util.getImagenDeIntArray(celda.getAncho(), celda.getAlto(), celda.getPixeles(), captura.getTipoImagen());
                }

                //dibuja la celda en la imagen
                if (imgCelda != null) {
                    if (imagen.get(captura.getPantalla()) != null) {
                        imagen.get(captura.getPantalla()).getGraphics().drawImage(imgCelda, celda.getX(), celda.getY(), null);
                        if (verCeldas) {
                            dibujarRectangulo(captura.getPantalla(), celda, imgCelda.getWidth(), imgCelda.getHeight(), captura.isJpg(), captura.getTipoImagen());
                        }
                    } else {
                        System.out.println("ALERTA !!! El buffer de la imagen donde vamos a dibujar es nulo !!!!  pantalla=" + captura.getPantalla());
                        System.out.println("tamanio buffer=" + imagen.size() + " keyset=" + imagen.keySet());
                    }
                } else {
                    System.out.println("LA IMAGEN DEL BLOQUE ES NULO !!! NO DEBERIA SER NUNCA NULO !!!!");
                }
                imgCelda = null;
            }
//                    System.out.println("total nuevos:" + tn);
//                    System.out.println("total copias:" + tc);
//                    System.out.println("total copias Anterior:" + tcA);
//                    System.out.println("total=" + (tn + tc + tcA));
            
            actualizarPantalla(captura.getPantalla());

            if (grabar) {
                grabarJPG(captura.getPantalla()); //graba archivos jpg por separado
                grabarDAT(captura.getPantalla());//graba el archivo .dat
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
//            }
//        }).start();
    }

    /**
     * Método que solo dibuja las celdas que cambiaron
     *
     * @param captura
     */
    public void reproducir_tipo2(Captura captura) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        try {
            if (captura == null) {
                return;
            }

            // prepara la imagen donde se va a dibujar
            //en formato RGB no hay problema al guardar jpg
            prepararPantalla(captura.getPantalla(), captura.getAncho(), captura.getAlto(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imgCelda;
            TC = 0;
            TCA = 0;
            TN = 0;

            //si no hay un buffer de celdas para esta pantalla creamos uno
            if (!capturaPrevia.containsKey(captura.getPantalla())) {
                capturaPrevia.put(captura.getPantalla(), new HashMap<String, PantallaBloque>());
            }

//            System.out.println("-----------------------------------------------------------------------------------");
//            PantallaBloque celda;
            boolean mandarActualizarPantalla = false;

            for (PantallaBloque celda : captura.getBloques()) {
                try {
                    //si el bloque ya existe en la anterior captura
                    if (celda.getNombreCopia() != null && !celda.getNombreCopia().isEmpty()) {
//                        System.out.println("debe ir inicio (obligatorio)");
                        //la anterior captura contiene la supuesta copia
                        if (capturaPrevia.get(captura.getPantalla()).containsKey(celda.getNombreCopia())) {
                            PantallaBloque bloque2 = capturaPrevia.get(captura.getPantalla()).get(celda.getNombreCopia());
                            celda.setDatos(bloque2.getDatos());
                            celda.setPixeles(bloque2.getPixeles());
                            celda.setTipo(bloque2.getTipo());
                            bloque2 = null;
                            TCA++;
                        } else {
                            System.out.println("SE SUPONE ES UNA COPIA PERO NO EXISTE EN ESTE BUFFER. DEBEMOS MANDAR A ACTUALIZAR LA PANTALLA");
                            mandarActualizarPantalla = true;
                        }
                    } else {
                        //si es una copia de la captura actual
                        if (celda.getCopia() != -1) {

                            PantallaBloque bloque2 = captura.getBloques().get(celda.getCopia());
                            //tomamos los datos de la anterior captura
                            celda.setDatos(bloque2.getDatos());
                            celda.setPixeles(bloque2.getPixeles());
                            celda.setTipo(bloque2.getTipo());
                            bloque2 = null;
                            TC++;
                        } else {
//                            System.out.println("debe ir al medio (opcional)");
                            capturaPrevia.get(captura.getPantalla()).put(celda.getNombre(), celda);
                            //capturaPrevia.put(bloque.getNombre(), bloque);
                            TN++;
                        }
                    }

                    //obtiene la imagen de la celda
                    if (celda.getTipo() == 1) {
                        if (celda.getDatos() == null) {
                            System.out.println("LOS DATOS (BYTES) SON NULOS !!!!!");
                            mandarActualizarPantalla = true;
                            continue;
                        }
                        //imgBloque = Util.getImagenDeByteArray(bloque.getAncho(), bloque.getAlto(), descomprimir(bloque.getDatos()), captura.isJpg(), captura.getTipoImagen());
                        imgCelda = Util.getImagenDeByteArray(celda.getAncho(), celda.getAlto(), celda.getDatos(), captura.isJpg(), captura.getTipoImagen());
                    } else {
                        if (celda.getPixeles() == null) {
                            System.out.println("LOS DATOS (PIXELES) SON NULOS !!!!!");
                            mandarActualizarPantalla = true;
                            continue;
                        }
                        imgCelda = Util.getImagenDeIntArray(celda.getAncho(), celda.getAlto(), celda.getPixeles(), captura.getTipoImagen());
                    }

                    //dibuja la celda en la imagen
                    if (imgCelda != null) {
                        if (imagen.get(captura.getPantalla()) != null) {
                            imagen.get(captura.getPantalla()).getGraphics().drawImage(imgCelda, celda.getX(), celda.getY(), null);
                            if (verCeldas) {
                                dibujarRectangulo(captura.getPantalla(), celda, imgCelda.getWidth(), imgCelda.getHeight(), captura.isJpg(), captura.getTipoImagen());
                            }
                        } else {
                            System.out.println("ALERTA !!! El buffer de la imagen donde vamos a dibujar es nulo !!!!  pantalla=" + captura.getPantalla());
                            System.out.println("tamanio buffer=" + imagen.size() + " keyset=" + imagen.keySet());
                        }
                    } else {
                        System.out.println("LA IMAGEN DEL BLOQUE ES NULO !!! NO DEBERIA SER NUNCA NULO !!!!");
                        mandarActualizarPantalla = true;
                    }
                    imgCelda = null;
                } catch (Exception ex) {
                    System.out.println("Error al dibujar celda");
                    System.out.println("pantalla=" + captura.getPantalla());
                    System.out.println("error = " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            //se pide al cliente que actualice toda la pantalla
            if (mandarActualizarPantalla) {
                contenedor.getVentanaER().getServidor().actualizarPantalla();
            }

//                    System.out.println("total nuevos:" + tn);
//                    System.out.println("total copias:" + tc);
//                    System.out.println("total copias Anterior:" + tcA);
//                    System.out.println("total=" + (tn + tc + tcA));
            if (grabar) {                
                grabarJPG(captura.getPantalla()); //graba archivos jpg por separado
                grabarDAT(captura.getPantalla());//graba el archivo .dat
            }

            actualizarPantalla(captura.getPantalla());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
//            }
//        }).start();
    }

    public void modoVariasPantallas() {
        contenedor.modoVariasPantallas();
    }

    public void modoScroll() {
        contenedor.modoScroll();
    }

    public void modoUnico() {
        contenedor.modoUnico();
    }

    public void testVaciarPantalla() {
        for (Map.Entry<Integer, BufferedImage> entry : imagen.entrySet()) {
            imagen.put(entry.getKey(), new BufferedImage(entry.getValue().getWidth(), entry.getValue().getHeight(), entry.getValue().getType()));
        }
    }

    public void limpiarBuffers() {
        capturaPrevia.clear();
    }

    //----------------------------------------------------------------------------------------------------------------
    public boolean isVerCeldas() {
        return verCeldas;
    }

    public void setVerCeldas(boolean verCeldas) {
        this.verCeldas = verCeldas;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public int getTC() {
        return TC;
    }

    public void setTC(int TC) {
        this.TC = TC;
    }

    public int getTCA() {
        return TCA;
    }

    public void setTCA(int TCA) {
        this.TCA = TCA;
    }

    public int getTN() {
        return TN;
    }

    public void setTN(int TN) {
        this.TN = TN;
    }

    public File getDirectorioEscritorio() {
        return directorioEscritorio;
    }

    public void setDirectorioEscritorio(File directorioEscritorio) {
        this.directorioEscritorio = directorioEscritorio;
    }

    public boolean isGrabar() {
        return grabar;
    }

    public void setGrabar(boolean grabar) {
        this.grabar = grabar;
    }

    public File getArchivoCaptura() {
        return archivoCaptura;
    }

    public void setArchivoCaptura(File archivoCaptura) {
        this.archivoCaptura = archivoCaptura;
    }

    public Map<Integer, BufferedImage> getImagen() {
        return imagen;
    }

    public void setImagen(Map<Integer, BufferedImage> imagen) {
        this.imagen = imagen;
    }

}
