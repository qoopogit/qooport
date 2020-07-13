package rt.modulos.escritorio.comunes.detector;

import comunes.PantallaBloque;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rt.util.IMG;
import rt.util.UtilRT;

/**
 * Clase que se encarga de dividir la pantalla en bloques/celdas y detectar
 * cuando existan cambios de imagen en las mismas.
 *
 * @author alberto
 */
public class Celdas extends DetectorCambios {

    private static final int ALGORITMO = 2;
    private static final int TIPO_COMPARACION = 2;//tipo comparacion . 1 con el hashcode, 2 con el ArraysEquals.

    // mapa de hashcode de los bloques capturados, usada para detectar los cambios
    //se neceista mantener el valor de los bloques porq tienen los datos q indican si son o no copias
    private final Map<String, PantallaBloque> capturaPrevia = new HashMap();
    private int anchoAnterior = 1; //lo uso para verificar si se cambio el tamanio de la ventana (vista bloques)
    private int altoAnterior = 1; //lo uso para verificar si se cambio el tamanio de la ventana (vista bloques)

    //en esta variable se almacen lo q se obtuvo en la pantalla
    //esta variable se debe eliminar despues de usarla para no mantener en memoria.
    // se declara en este lugar para hacer nuevas instancias seguidas que colapsaran la memoria
    // pues el GC demora en eliminarla
    private List<PantallaBloque> pantalla;
    //esta variable tiene una funcion parecida a la anterior y se declara en este lugar por las mismas razones
    private List<PantallaBloque> lista;

    public Celdas() {

    }

    @Override
    public void limpiar() {
        capturaPrevia.clear();
        anchoAnterior = 0;
        altoAnterior = 0;
    }

    private static long generaChecksum(PantallaBloque bloque, int tipo) {
        if (tipo == 1) {
            return UtilRT.generarChecksum(bloque.getDatos());
        } else {
            return UtilRT.generarChecksum(bloque.getPixeles());
        }
    }

    private static boolean comparar(PantallaBloque b1, PantallaBloque b2, int tipo) {
        try {
            if (TIPO_COMPARACION == 1) {
                return b1.getChecksum() == b2.getChecksum();
            } else {
                if (tipo == 1) {
                    return Arrays.equals(b1.getDatos(), b2.getDatos());
                } else {
                    return Arrays.equals(b1.getPixeles(), b2.getPixeles());
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    //Divide la Pantalla en celdas y detecta las celdas que han cambiado
    @Override
    public List<PantallaBloque> procesarCambios(BufferedImage imagen) {
        switch (ALGORITMO) {
//            case 1:
//                return ordenar(procesarCambio_v1(imagen));
            case 2:
                return ordenar(procesarCambio_v2(imagen));
        }
        return null;
    }

    //nuevo algoritmo
    /**
     * Divide la pantalla en bloques y luego consulta los bloques que cambiaron
     *
     * @param imagen
     * @return
     */
    private List<PantallaBloque> procesarCambio_v2(BufferedImage imagen) {
        //divide toda la pantalla en bloques
        pantalla = dividirImagen(imagen);
        lista = new ArrayList<PantallaBloque>();

        PantallaBloque previo = null;
        porDiferencia = 0;
        //si cambia de tamaño, limpio el buffer
        if (anchoAnterior != imagen.getWidth() || altoAnterior != imagen.getHeight()) {
            capturaPrevia.clear();
        }
        //el problema del metodo de buscar en la anterior captura es que puede estar tomando no de la captura anterior sino de la actual,
        // por eso se crea un buffer temporal donde se va colocando las celdas de la captura actual
        Map<String, PantallaBloque> capturaPreviaTMP = new HashMap();
        //calcula los bloques que cambiaron
        for (PantallaBloque bloque : pantalla) {
            try {
                previo = capturaPrevia.get(bloque.getNombre());
                if (previo == null || !comparar(previo, bloque, opciones.getTipoDatos())) {
                    //verifica que el bloque no este repetido en la captura anterior
                    if (opciones.isValidarRepetidos()) {
                        bloque = buscarRepetido(bloque);//busca si el bloque esta repetido en la anterior captura
                        if (bloque.getNombreCopia() == null || bloque.getNombreCopia().isEmpty()) {
                            //se verifica si los datos del bloque no estan repetidos en un anterior bloque, de la captura actual
                            bloque = buscarRepetido(bloque, lista);
                        }
                    }
                    lista.add(bloque);
                    //capturaPreviaTMP.put(bloque.getNombre(), bloque.limpiar());
                    //en el buffer solo pongo aquellos que no son copias
                    if (bloque.getCopia() == -1 && bloque.getNombreCopia() == null) {
//                    if (bloque.getNombreCopia() == null) {//solo no se agregan al buffer las copias de la captura anterior
                        capturaPreviaTMP.put(bloque.getNombre(), bloque.limpiar());
                    }
//                    else//si son copias en el buffer pongo el valor original y no al bloque
//                    {
//                        //si es copia de la misma captura
//                        if (bloque.getCopia() != -1) {
//                            capturaPreviaTMP.put(bloque.getNombre(), lista.get(bloque.getCopia()));//ubica en el buffer al bloque original
//                        } else//si es copia de la anterior captura                            
//                        {
//                            capturaPreviaTMP.put(bloque.getNombre(), capturaPrevia.get(bloque.getNombreCopia()));//ubica en el buffer al bloque original
//                        }
//                    }
                    porDiferencia++;
                }
            } catch (Exception e) {
            }
        }

        //actualiza el buffer anterior
        try {
            for (Map.Entry<String, PantallaBloque> entry : capturaPreviaTMP.entrySet()) {
                capturaPrevia.put(entry.getKey(), entry.getValue());
            }
        } catch (Exception ex) {
        }

        //el codigo siguiente es para imprimir lo q esta en el buffer sin embargo soluciona el error de barrido que quedan las imagenes
//        try {
//            //voy a grabar el buffer en un archivo para ver q tenog en memoria solo por test
//            BufferedImage bi = IMG.construirImagen(capturaPrevia.values(),
//                    imagen.getWidth(),
//                    imagen.getHeight(),
//                    imagen.getType(),
//                    opciones.isConvertirJpg());
//
//            ImageIO.write(bi, "jpg", new File("~/salida.jpg"));
//        } catch (Exception ex) {
////            ex.printStackTrace();
//        }
//esperar un tiempo impide que se sobremonten las imagenes
        try {
            Thread.sleep(20);
        } catch (Exception e) {
        }
        porDiferencia = (porDiferencia * 100) / (pantalla.size());
        anchoAnterior = imagen.getWidth();
        altoAnterior = imagen.getHeight();
        capturaPreviaTMP.clear();
        capturaPreviaTMP = null;
        pantalla.clear();
        pantalla = null;
        return lista;
    }

    /**
     * Ordena la lista. Primero deben ir los bloques que son copias de la
     * anterior. Luego deben ir las celdas que no son copias. Ultimo deben ir
     * las que son copias de la misma captura
     *
     * @param lista
     * @return
     */
    private List<PantallaBloque> ordenar(List<PantallaBloque> lista) {
        Collections.sort(lista, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                PantallaBloque pb1 = (PantallaBloque) o1;
                PantallaBloque pb2 = (PantallaBloque) o2;
                boolean b1 = pb1.getCopia() != -1 || pb1.getNombreCopia() != null;
                boolean b2 = pb2.getCopia() != -1 || pb2.getNombreCopia() != null;
                if (b1 && b2) {
                    return 0;
                }
                if (b1 && !b2) {
                    return -1;
                }
                if (!b1 && b2) {
                    return 1;
                }
                return 0;
            }
        });

        return lista;
    }

    /**
     * Divide la pantalla en bloques
     *
     * @param imagen
     * @return
     */
    private List<PantallaBloque> dividirImagen(BufferedImage imagen) {
        pantalla = new ArrayList<PantallaBloque>();
        int anchoCelda = opciones.getAnchoBloque();
        int altoCelda = opciones.getAltoBloque();
        anchoCelda = (anchoCelda <= 0) ? imagen.getWidth() : anchoCelda;
        altoCelda = (altoCelda <= 0) ? imagen.getHeight() : altoCelda;
        int anchoB, altoB;
        PantallaBloque bloque;
        for (int px = 0; px < imagen.getWidth(); px += anchoCelda) {
            for (int py = 0; py < imagen.getHeight(); py += altoCelda) {
                anchoB = Math.min(anchoCelda, imagen.getWidth() - px);
                altoB = Math.min(altoCelda, imagen.getHeight() - py);
                if (anchoB > 0 && altoB >= 0) {
                    try {
                        bloque = getSubImagen(imagen, px, py, anchoB, altoB);
                        pantalla.add(bloque);
                    } catch (OutOfMemoryError e) {
                        UtilRT.gc();
                    } catch (Exception e) {
                    } finally {
                        bloque = null;
                    }
                }
            }
        }
        bloque = null;
        return pantalla;
    }

    /**
     * Busca si el bloque esta repetido en la captura anterior. Por ejemplo
     * cuando se mueve una ventana o el contenido de una ventana
     *
     * @param bloque
     * @param checksum
     * @return
     */
    private PantallaBloque buscarRepetido(PantallaBloque bloque) {
        try {
            for (Map.Entry<String, PantallaBloque> entry : capturaPrevia.entrySet()) {
                //si no es el mismo bloque
//                if (!entry.getKey().equals(bloque.getNombre())) {
                //verificamos q sea el mismo checksum pero q no sea una copia
                //if (entry.getValue().getChecksum() == bloque.getChecksum()
                //<ag> 30052017, ya no se pregunta si es una copia el origen porq ya no voy a almacenar en el buffer a las copais
                if (comparar(entry.getValue(), bloque, opciones.getTipoDatos()) /*&& entry.getValue().getCopia() == -1 
                        && entry.getValue().getNombreCopia() == null*/) {
                    bloque.setDatos(null);
                    bloque.setPixeles(null);
                    bloque.setNombreCopia(entry.getKey());
                    return bloque;
                }
//                }
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return bloque;
    }

    /**
     * Busca si el bloque esta repetido en la captura actual
     *
     * @param bloque
     * @param checksum
     * @param buffer
     * @return
     */
    private PantallaBloque buscarRepetido(PantallaBloque bloque, List<PantallaBloque> buffer) {
        try {
            for (int i = 0; i <= buffer.size(); i++) {
                PantallaBloque cap = buffer.get(i);
//                if (capturaPrevia.get(cap.getNombre()).getChecksum() == bloque.getChecksum()) {
                //if (comparar(capturaPrevia.get(cap.getNombre()), bloque, opciones.getTipoDatos())
                //que el bloque con el que se compara no sea una copia
                if (cap.getNombreCopia() == null && cap.getCopia() <= 0
                        && comparar(cap, bloque, opciones.getTipoDatos())) {
//                if (capturaPrevia.get(cap.getNombre()).getChecksum() == bloque.getChecksum()
//                        && !cap.getNombre().equals(bloque.getNombre())) {
                    bloque.setDatos(null);
                    bloque.setPixeles(null);
                    bloque.setCopia(i);
                    return bloque;
                }
            }
        } catch (Exception ex) {
        }
        return bloque;
    }

    //------------------------------------------------------------------------------------------------------------------------------
//    /**
//     * Divide la pantalla en bloques y va comparando cada bloque si cambio. Solo
//     * mantiene en memoria los bloques que cambiaron
//     *
//     * @param imagen
//     * @return
//     */
//    private List<PantallaBloque> procesarCambio_v1(BufferedImage imagen) {
//        List<PantallaBloque> lstTemp = new ArrayList<PantallaBloque>();
//        long tInicio = System.currentTimeMillis();
//        try {
//            //estos valores pueden cambiar dependiendo de ciertas condiciones
//            int anchoCelda = opciones.getAnchoBloque();
//            int altoCelda = opciones.getAltoBloque();
//
//            porDiferencia = 0;
//            //si cambia de tamaño, limpio el buffer
//            if (anchoAnterior != imagen.getWidth() || altoAnterior != imagen.getHeight()) {
//                capturaPrevia.clear();
//            }
//            anchoCelda = (anchoCelda <= 0) ? imagen.getWidth() : anchoCelda;
//            altoCelda = (altoCelda <= 0) ? imagen.getHeight() : altoCelda;
//
//            int columnas = imagen.getWidth() / anchoCelda;
//            int filas = imagen.getHeight() / altoCelda;
//            int iX, iY, anchoB, altoB;
//
//            //el problema del metodo de buscar en la anterior captura es que puede estar tomando no de la captura anteiro sino de la actual,
//            // por eso se crea un buffer temporal donde se va colocando las celdas de la captura actual
//            Map<String, PantallaBloque> capturaPreviaTMP = new HashMap(); 
//
//            PantallaBloque bloque;
//
//            PantallaBloque previo;
//            for (int px = 0; px <= columnas; px++) {
//                for (int py = 0; py <= filas; py++) {
//                    iX = anchoCelda * px;
//                    iY = altoCelda * py;
//                    anchoB = Math.min(anchoCelda, imagen.getWidth() - iX);
//                    altoB = Math.min(altoCelda, imagen.getHeight() - iY);
//                    if (anchoB > 0 && altoB >= 0) {
//                        try {
//                            bloque = getSubImagen(imagen, iX, iY, anchoB, altoB);
//                            bloque.setChecksum(generaChecksum(bloque, opciones.getTipoDatos()));
//                            previo = capturaPrevia.get(bloque.getNombre());
//                            //if (previo == null || previo.getChecksum() != bloque.getChecksum()) {
//                            if (!(previo != null && previo.getChecksum() == bloque.getChecksum())) {
//                                if (opciones.isValidarRepetidos()) {
//                                    bloque = buscarRepetido(bloque);
//                                    if (bloque.getNombreCopia() == null || bloque.getNombreCopia().isEmpty()) {
//                                        //se verifica si los datos del bloque no estan repetidos en un anterior bloque, de la captura actual
//                                        bloque = buscarRepetido(bloque, lstTemp);
//                                    }
//                                }
//                                lstTemp.add(bloque);
//                                porDiferencia++;
//                                capturaPreviaTMP.put(bloque.getNombre(), bloque.limpiar());//actualiza criterio
//                            }
//                        } catch (Exception e) {
//                        } finally {
//                            bloque = null;
//                        }
//                    }
//                }
//            }
//
//            //actualiza el buffer anterior
//            try {
//                for (Map.Entry<String, PantallaBloque> entry : capturaPreviaTMP.entrySet()) {
//                    capturaPrevia.put(entry.getKey(), entry.getValue());
//                }
//            } catch (Exception ex) {
//            }
//            porDiferencia = (porDiferencia * 100) / (columnas * filas);
//            anchoAnterior = imagen.getWidth();
//            altoAnterior = imagen.getHeight();
//            capturaPreviaTMP.clear();
//            capturaPreviaTMP = null;
//        } catch (Exception e) {
////            e.printStackTrace();
//        }
//        long tFin = System.currentTimeMillis();
//        if (Inicio.DEBUG) {
//            System.out.println("Tiempo cambios celdas=" + (tFin - tInicio) + "ms");
//        }
//        return lstTemp;
//    }
    /**
     * Construye un bloque
     *
     * @param imagen
     * @param px
     * @param py
     * @param ancho
     * @param alto
     * @return
     * @throws Exception
     */
    private PantallaBloque getSubImagen(BufferedImage imagen, int px, int py, int ancho, int alto) throws Exception {
        PantallaBloque bloque = null;
        BufferedImage subImage = imagen.getSubimage(px, py, ancho, alto);
        if (!opciones.isConvertirJpg()) {
            //si no tiene conversion jpg clonamos la subimagen, esto porq la subimagen comparte el mismo arreglo de bytes que la imagen superior
            BufferedImage subImage2 = IMG.clonar(subImage);
            if (opciones.getTipoDatos() == 1) {
                bloque = new PantallaBloque(px + "-" + py, px, py, ancho, alto, obtenerBytes(subImage2, opciones.getCalidad()));
            } else {
                bloque = new PantallaBloque(px + "-" + py, px, py, ancho, alto, obtenerInts(subImage2));
            }
            subImage2 = null;
        } else {
            if (opciones.getTipoDatos() == 1) {
                bloque = new PantallaBloque(px + "-" + py, px, py, ancho, alto, obtenerBytes(subImage, opciones.getCalidad()));
            } else {
                bloque = new PantallaBloque(px + "-" + py, px, py, ancho, alto, obtenerInts(subImage));
            }
        }
        bloque.setChecksum(generaChecksum(bloque, opciones.getTipoDatos()));
        subImage = null;
        return bloque;
    }
}
