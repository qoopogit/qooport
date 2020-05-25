/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import comunes.Archivo;
import comunes.Comando;
import comunes.GPSPosicion;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import qooport.asociado.Asociado;
import qooport.avanzado.QoopoRT;
import qooport.utilidades.cifrado.Encriptacion;
import rt.util.IMG;

/**
 *
 * @author alberto
 */
public class Util {

    public static ImageIcon iconoSistemaOperativo(String os) {

        ImageIcon icono = Util.cargarIcono16("/resources/3.gif");
        if (os.contains("Windows")) {
            if (os.toLowerCase().contains("xp")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_windowsxp.png");
                } catch (Exception e) {
                }
            } else if (os.contains(" 8")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_windows8.png");
                } catch (Exception e) {
                }
            } else if (os.contains(" 10")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_windows10.png");
                } catch (Exception e) {
                }
            } else if (os.contains(" 7")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_windows7.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("server")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_windowsserver.png");
                } catch (Exception e) {
                }
            } else {
                try {
                    icono = Util.cargarIcono16("/resources/os_windows.png");
                } catch (Exception e) {
                }
            }

        } else if (os.contains("Linux")) {

            if (os.toLowerCase().contains("kde")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_kde.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("ubuntu")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_ubuntu.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("suse")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_suse.png");
                } catch (Exception e) {
                }

            } else if (os.toLowerCase().contains("debian")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_debian.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("arch")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_arch.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("redhut")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_redhut.png");
                } catch (Exception e) {
                }
            } else if (os.toLowerCase().contains("solaris")) {
                try {
                    icono = Util.cargarIcono16("/resources/os_solaris.png");
                } catch (Exception e) {
                }
            } else {
                try {
                    icono = Util.cargarIcono16("/resources/os_linux.png");
                } catch (Exception e) {
                }
            }

        } else if (os.contains("Mac")) {
            try {
                icono = Util.cargarIcono16("/resources/os_mac.png");
            } catch (Exception e) {
            }
        } else if (os.contains("Android")) {
            try {
                icono = Util.cargarIcono16("/resources/android16.png");
            } catch (Exception e) {
            }
        } else { //desconocido
            try {
                icono = Util.cargarIcono16("/resources/3.gif");
            } catch (Exception e) {
            }
        }
        return icono;
    }

    public static String convertirBytes(double largo) {
        try {
            String unidad = "B";
            if (largo > 1024) {
                largo = largo / 1024;
                unidad = "KB";
            }
            if (largo > 1024) {
                largo = largo / 1024;
                unidad = "MB";
            }
            if (largo > 1024) {
                largo = largo / 1024;
                unidad = "GB";
            }
            BigDecimal a = new BigDecimal("" + largo).setScale(2, BigDecimal.ROUND_HALF_UP);
            return "" + a + " " + unidad;
        } catch (Exception e) {
            return "0.00 B";
        }
    }

    public static String redondear(double number, int decimales) {
        BigDecimal bi = new BigDecimal(number);
        return bi.setScale(decimales, RoundingMode.HALF_UP).toString();
    }

    public static String rellenarEspacios(String cadena, int largo) {
        return String.format("%1$-" + largo + "s", cadena);
    }

    public static String crearTituloString(String cadena, int largo) {
        int largoLateral = (largo - cadena.length()) / 2;

        char[] chars = new char[largoLateral];
        Arrays.fill(chars, '=');

        return new String(chars) + " " + cadena + " " + new String(chars);
    }

    public static GPSPosicion obtenerPosicionGPS(String ipAddress) {
        File file = new File("lib", "GeoLiteCity.dat");
        return getLocation(ipAddress, file);
    }

    private static GPSPosicion getLocation(String ipAddress, File file) {
        GPSPosicion posicion = null;
        try {
            posicion = new GPSPosicion();
            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location ubic = lookup.getLocation(ipAddress);
            posicion.setLatitud(ubic.latitude);
            posicion.setLongitud(ubic.longitude);

//            System.out.println("pais=" + ubic.countryName);
//            System.out.println("ciudad=" + ubic.city) ;
//            System.out.println("latitud=" + ubic.latitude);
//            System.out.println("longitud=" + ubic.longitude);
//            serverLocation.setCountryCode(locationServices.countryCode);
//            serverLocation.setCountryName(locationServices.countryName);
//            serverLocation.setRegion(locationServices.region);
//            serverLocation.setRegionName(regionName.regionNameByCode(
//            locationServices.countryCode, locationServices.region));
//            serverLocation.setCity(locationServices.city);
//            serverLocation.setPostalCode(locationServices.postalCode);
//            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
//            serverLocation.setLongitude(String.valueOf(locationServices.longitude));
        } catch (IOException e) {
        }
        return posicion;
    }

    public static void copyFile(InputStream input, File dst) throws IOException {
        OutputStream output = new FileOutputStream(dst);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        input.close();
        output.close();
    }

    public static byte[] conseguirBytes(InputStream input) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            copyStream(input, bout);
            return bout.toByteArray();
        } catch (Exception e) {

        }
        return null;
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            input.close();
            output.close();
        } catch (Exception ex) {
        }
    }

    public static ImageIcon cargarIcono12(String icono) {
        return cargarIcono(icono, 12, 12);
    }

    public static ImageIcon cargarIcono14(String icono) {
        return cargarIcono(icono, 14, 14);
    }

    public static ImageIcon cargarIcono16(String icono) {
        return cargarIcono(icono, 16, 16);
    }

    public static ImageIcon cargarIcono20(String icono) {
        return cargarIcono(icono, 20, 20);
    }

    public static ImageIcon cargarIcono24(String icono) {
        return cargarIcono(icono, 24, 24);
    }

    public static ImageIcon cargarIcono32(String icono) {
        return cargarIcono(icono, 32, 32);
    }

    public static ImageIcon cargarIcono(String icono, int ancho, int alto) {
        try {
            return new ImageIcon(new ImageIcon(QoopoRT.class.getResource(icono)).getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon cargarIcono(String icono) {
        return new ImageIcon(QoopoRT.class.getResource(icono));
    }

    public static byte[] obtenerBytes(ImageIcon icono) {
        return obtenerBytes(icono.getImage());
    }

    public static byte[] obtenerBytes(Image image) {
        try {
            BufferedImage im = new BufferedImage(image.getWidth(null), image.getHeight(null), 3);
            im.createGraphics().drawImage(image, 0, 0, null);
            ByteArrayOutputStream ara = new ByteArrayOutputStream();
            try {
                ImageIO.write(im, "png", ara);
                ara.close();
            } catch (IOException ex) {
            }
            return ara.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getExternalIp() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
                String ip = in.readLine();
                return ip;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static byte[] comprimirObjeto(Object objeto) {

        byte[] bytes = null;
        ObjectOutputStream objectOut = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(baos);
            objectOut.writeObject(objeto);
            objectOut.close();
            bytes = Compresor.comprimirGZIP(baos.toByteArray());
        } catch (Exception ex) {
        } finally {
            try {
                objectOut.close();
            } catch (Exception ex) {
            }
        }
        return bytes;
    }

    public static Object descomprimirObjeto(byte[] bytes, Asociado serv) {
        ObjectInputStream objectIn = null;
        if (bytes == null) {
            return null;
        }
        if (serv != null) {
            serv.agregarRecibidos(bytes.length);
        }
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Compresor.descomprimirGZIP(bytes));
            objectIn = new ObjectInputStream(bais);
            return (Object) objectIn.readObject();
        } catch (Exception ex) {
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (IOException ex) {
            }
        }
        return null;
    }

    public static byte[] texto(String texto) throws IOException {
        return Compresor.comprimirGZIP(Encriptacion.cifra(texto));
    }

    public static String nombreHora() {
        //SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return sdf.format(new Date());
    }

    public static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

  
    public static byte[] sacarIcono(File f) {
        String nombre = f.getName().toLowerCase();
        byte[] r;
        if ((f.isFile()) && ((nombre.contains(".jpg")) || (nombre.contains(".png"))
                || (nombre.contains(".jpeg")) || (nombre.contains(".gif")) || (nombre.contains(".ico"))
                || (nombre.contains(".bmp")))) {
            r = sacarThumbail(f);
            if (r != null) {
                return r;
            }
        }

        r = sacarIcono1(f);
        if (r == null) {
            r = sacarIcono2(f);
        }
        return r;
    }

    public static byte[] sacarThumbail(File f) {
        try {
            byte[] r;
            BufferedImage m = ImageIO.read(f);
            if (m.getWidth() > m.getHeight()) {
                m = IMG.escalarSuave(m, 100, (100 * m.getHeight() / m.getWidth()));
            } else {
                m = IMG.escalarSuave(m, (100 * m.getWidth() / m.getHeight()), 100);
            }
            ByteArrayOutputStream ara = new ByteArrayOutputStream();
            ImageIO.write(m, "png", ara);
            r = ara.toByteArray();
            ara.close();
            return r;
        } catch (Exception ex) {
            return null;
        }
    }

    public static BufferedImage sacarThumbailBI(File f) {
        try {
            BufferedImage m = ImageIO.read(f);
            if (m.getWidth() > m.getHeight()) {
                m = QoopoIMG.escalarSuave(m, 100, (100 * m.getHeight() / m.getWidth()));
            } else {
                m = QoopoIMG.escalarSuave(m, (100 * m.getWidth() / m.getHeight()), 100);
            }
            return m;
        } catch (Exception ex) {
            return null;
        }
    }

    //saca un icono mas grandeq 16x16
    public static byte[] sacarIcono1(File f) {
        try {
            Image icon = sun.awt.shell.ShellFolder.getShellFolder(f).getIcon(true);
            //ImageIcon mmm = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
            BufferedImage im = new BufferedImage(icon.getWidth(null), icon.getHeight(null), 3);
            im.createGraphics().drawImage(icon, 0, 0, null);
            ByteArrayOutputStream ara = new ByteArrayOutputStream();
            try {
                ImageIO.write(im, "png", ara);
                ara.close();
            } catch (Exception ex) {
            }
            return ara.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    //saca un icono de 16x16
    public static byte[] sacarIcono2(File f) {
        try {
            ImageIcon mmm = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
            BufferedImage im = new BufferedImage(mmm.getIconWidth(), mmm.getIconHeight(), 3);
            im.createGraphics().drawImage(mmm.getImage(), 0, 0, null);
            ByteArrayOutputStream ara = new ByteArrayOutputStream();
            try {
                ImageIO.write(im, "png", ara);
                ara.close();
            } catch (Exception ex) {
            }
            return ara.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static String calcularTiempo(long milisegundos) {
        long difSegundos = milisegundos / 1000 % 60;
        long difMinutos = milisegundos / (1000 * 60) % 60;
        long difHoras = milisegundos / (1000 * 60 * 60) % 24;
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%s:%s:%s", df.format(difHoras), df.format(difMinutos), df.format(difSegundos));
    }

    public static void comprimir(File archivo) throws IOException {
        if (archivo.isDirectory()) {
            comprimirArchivos(obtenerArchivos(archivo), new File(archivo.getParent(), archivo.getName() + ".zip"), true, archivo.getParent());
        } else {
            comprimirArchivo(archivo);
        }
    }

    public static void comprimir(File archivo, boolean mantenerRuta) throws IOException {
        if (archivo.isDirectory()) {
            comprimirArchivos(obtenerArchivos(archivo), new File(archivo.getParent(), archivo.getName() + ".zip"), mantenerRuta, archivo.getParent());
        } else {
            comprimirArchivo(archivo);
        }
    }

    private static List<File> obtenerArchivos(File ruta) {
        List<File> r = new ArrayList<File>();
        if (ruta.isDirectory()) {
            for (File f2 : ruta.listFiles()) {
                if (f2.isDirectory()) {
                    //r.add(f2);
                    r.addAll(obtenerArchivos(f2));
                } else {
                    r.add(f2);
                }
            }
        }
        return r;
    }

    private static void comprimirArchivos(List<File> ficheros, File archivoSalida, boolean mantenerRutas, String rutaAeliminar) throws IOException {
        // PREPARACIÓN DEL FICHERO ZIP.

        File salidaTemp = new File(archivoSalida.getAbsolutePath() + ".tmp");

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(salidaTemp));
        ZipEntry ze;

        rutaAeliminar = rutaAeliminar.replaceAll(Pattern.quote("\\"), "/");

        // INSERCIÓN DE LOS FICHEROS AL FICHERO ZIP.
        for (File archivo : ficheros) {
            String t = archivo.getAbsolutePath();

            t = t.replaceAll(Pattern.quote("\\"), "/");
            // INTRODUCIMOS EL FICHERO VACÍO CON SU NOMBRE Y EXTENSIÓN.
            if (mantenerRutas) {
                try {
                    if (rutaAeliminar.endsWith("/") || rutaAeliminar.endsWith("\\")) {
                        ze = new ZipEntry(t.substring(rutaAeliminar.length()));
                    } else {
                        ze = new ZipEntry(t.substring(rutaAeliminar.length() + 1));
                    }
                } catch (Exception e) {
                    ze = new ZipEntry(t);
                }
            } else {
                ze = new ZipEntry(archivo.getName());
            }
            zos.putNextEntry(ze);

            // INTRODUCIMOS LOS DATOS DEL FICHERO VACIÓ INTRODUCIDO.
            if (!archivo.isDirectory()) {
                byte[] readAllBytesOfFile = Files.readAllBytes(archivo.toPath());
                zos.write(readAllBytesOfFile, 0, readAllBytesOfFile.length);
            }
        }
        // CERRAMOS LOS FLUJOS DE DATOS.
        zos.closeEntry();
        zos.close();

        archivoSalida.delete();
        salidaTemp.renameTo(archivoSalida);

    }

    private static void comprimirArchivo(File file) throws IOException {
        byte[] b = new byte[512];
        File salida = new File(file.getParent(), file.getName() + ".zip");
        File salidaTemp = new File(salida.getAbsolutePath() + ".tmp");
        FileOutputStream out = new FileOutputStream(salidaTemp);
        ZipOutputStream zout = new ZipOutputStream(out);
        InputStream in = new FileInputStream(file);
        ZipEntry e = new ZipEntry(file.getName());
        zout.putNextEntry(e);
        int len = 0;
        while ((len = in.read(b)) != -1) {
            zout.write(b, 0, len);
        }
        zout.closeEntry();
//        print(e);
        zout.close();
        salida.delete();
        salidaTemp.renameTo(salida);
    }

//    private static void print(ZipEntry e) {
//        PrintStream err = System.err;
//        err.print("added " + e.getName());
//        if (e.getMethod() == ZipEntry.DEFLATED) {
//            long size = e.getSize();
//            if (size > 0) {
//                long csize = e.getCompressedSize();
//                long ratio = ((size - csize) * 100) / size;
//                err.println(" (deflated " + ratio + "%)");
//            } else {
//                err.println(" (deflated 0%)");
//            }
//        } else {
//            err.println(" (stored 0%)");
//        }
    public static Object leerParametro(Comando comando, int... parametro) {
        try {

            //si es de tipo arreglo
            if (comando.getObjeto() instanceof Object[]) {
                if (parametro.length > 0) {
                    return ((Object[]) comando.getObjeto())[parametro[0]];
                } else {
                    return ((Object[]) comando.getObjeto())[0];
                }
            } else {
                //si viene el objeto simple
                return comando.getObjeto();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedImage getImagenDeByteArray(int ancho, int alto, byte[] datos, boolean jpg, int tipoImagen) {
        try {
            //metodo de lso datos del buffered image
            if (datos == null) {
                return null;
            }
            if (jpg) {
                return ImageIO.read(new ByteArrayInputStream(datos));
            } else {
                //byte
                BufferedImage img = new BufferedImage(ancho, alto, tipoImagen);
                img.setData(Raster.createRaster(img.getSampleModel(), new DataBufferByte(datos, datos.length), new Point()));
                return img;
//            BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_3BYTE_BGR);
//            byte[] array = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(datos, 0, array, 0, array.length);
//            return image;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getImagenDeIntArray(int ancho, int alto, int[] datos, int tipoImagen) {
        if (datos == null) {
            return null;
        }
        //int
        try {
//            System.out.println("creando imagne de int arrya lrgo=" + datos.length);
//            System.out.println("tipo imagen " + tipoImagen);
            BufferedImage img = new BufferedImage(ancho, alto, tipoImagen);
            img.setData(Raster.createRaster(img.getSampleModel(), new DataBufferInt(datos, datos.length), new Point()));
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String armarNombreUnidad(Archivo archivo) {
        return archivo.getPath() + "--[" + Util.convertirBytes(archivo.getLibre()) + "/" + Util.convertirBytes(archivo.getTamanio()) + "]";
    }

    public static String getNombreUnidad(String texto) {
        try {
            return texto.split("--")[0];
        } catch (Exception e) {

        }
        return texto;
    }

//    public static void escribirLog(String msg, Exception e) {
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/alberto/QoopoRT_cliente.txt", true)));
//            out.print("=================================================");
//            out.println(msg);
//            out.println(e.getMessage());
//            out.println(e.getLocalizedMessage());
//            out.println(e.toString());
//            out.print("--------------------------");
//            e.printStackTrace(out);
//            out.close();
//        } catch (Exception e2) {
//            //exception handling left as an exercise for the reader
//        }
//    }
//
//    public static void escribirLog(String msg) {
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/alberto/QoopoRT_cliente.txt", true)));
//            out.print("=================================================");
//            out.println(msg);
//            out.print("--------------------------");
//            out.close();
//        } catch (Exception e2) {
//            //exception handling left as an exercise for the reader
//        }
//    }
    public static void gc() {
        try {
            System.gc();
        } catch (Exception e) {
        }
    }

    public static void esperar(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e) {
        }
    }

    public static void agregarTexto(File archivo, String texto) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(archivo, true)));
            out.print(texto);
            out.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void guardarTexto(String texto) {
        try {

            JFileChooser cd = new JFileChooser();
            cd.setDialogType(JFileChooser.SAVE_DIALOG);
            cd.setFileFilter(new FileNameExtensionFilter("Archivo de texto", new String[]{"txt"}));
            //cd.setCurrentDirectory(dir);
            //cd.setSelectedFile(new File("servidor"));
//                if (cd.showOpenDialog(this) == 0) {
            if (cd.showSaveDialog(null) == 0) {
                agregarTexto(cd.getSelectedFile(), texto);
            }

        } catch (Exception e2) {

        }
    }

    public static String abrirArchivoTexto() {
        try {
            JFileChooser cd = new JFileChooser();
            cd.setDialogType(JFileChooser.OPEN_DIALOG);
            cd.setFileFilter(new FileNameExtensionFilter("Archivo de texto", new String[]{"txt"}));
            //cd.setCurrentDirectory(dir);
            //cd.setSelectedFile(new File("servidor"));
            if (cd.showOpenDialog(null) == 0) {
                return getArchivoTexto(cd.getSelectedFile().getAbsolutePath());
            }

        } catch (Exception e2) {

        }
        return "";
    }

    public static String getArchivoTexto(String ruta) {
        FileReader fr;
        BufferedReader br = null;
        StringBuilder contenido = new StringBuilder();
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
                contenido.append("\n");
            }
        } catch (Exception e) {
            System.out.println("error al leer el archivo");
            e.printStackTrace();
            contenido = new StringBuilder("n/a");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
            }
        }
        return contenido.toString();
    }

    public static String procesaNombreCarpeta(String ruta, Asociado asociado) {
        try {
            //File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            ruta = ruta.replaceAll("<descargas>", asociado.getdDescargas().getAbsolutePath());
            ruta = ruta.replaceAll("<capturas>", asociado.getdCapturas().getAbsolutePath());
            ruta = ruta.replaceAll("<keylogger>", asociado.getfKeylogger().getAbsolutePath());

            ruta = ruta.replaceAll(Pattern.quote("\\"), "/");
            return ruta;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ruta;
        }
    }
    
    
     //consigue un hashmd5 de un archivo

    private static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
