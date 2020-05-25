/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.reproductorCapturas.encoder.avi;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.media.MediaLocator;

/**
 *
 * @author aigarcia
 */
public class Test {

    public static void makeVideo(String rutaImagenes, String archivoSalida) throws MalformedURLException {

        File[] fileArray = new File(rutaImagenes).listFiles();
        Vector<String> imgLst = new Vector<String>();
        for (int i = 0; i < fileArray.length; i++) {
            imgLst.add(fileArray[i].getAbsolutePath());
        }

        JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
        MediaLocator oml;
        if ((oml = imageToMovie.createMediaLocator(archivoSalida)) == null) {
            System.err.println("Cannot build media locator from: " + archivoSalida);
            return;
        }
        int interval = 50;
        imageToMovie.doIt(640, 480, (1000 / interval), imgLst, oml);

    }

    public static void main(String[] args) {
        try {
            //makeVideo("F:\\Alberto\\GoogleDrive\\desarrollo\\NetBeansProjects\\QoopoRT\\QoopoRT\\equipos\\alberto_alberto-laptop\\descargas\\tmp\\20170119_155740_934", "F:\\Alberto\\GoogleDrive\\desarrollo\\NetBeansProjects\\QoopoRT\\QoopoRT\\equipos\\alberto_alberto-laptop\\descargas\\tmp\\salida.avi");
            makeVideo("F:\\Alberto\\GoogleDrive\\desarrollo\\NetBeansProjects\\QoopoRT\\QoopoRT\\equipos\\aigarcia_sisinq4pdesa14.andinatel.int\\imagenes\\escritorio\\2017-01\\19\\imagenes", "F:\\Alberto\\GoogleDrive\\desarrollo\\NetBeansProjects\\QoopoRT\\QoopoRT\\equipos\\aigarcia_sisinq4pdesa14.andinatel.int\\imagenes\\escritorio\\2017-01\\19\\salida.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
