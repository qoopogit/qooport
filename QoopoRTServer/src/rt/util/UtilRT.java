package rt.util;

import comunes.Captura;
import comunes.CapturaOpciones;
import comunes.Comando;
import comunes.PantallaBloque;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class UtilRT {

    public static final String OS_NOMBRE;
    public static final String OS_VERSION;
    public static final String OS_ARQUITECTURA;
    public static final String OS_PLATAFORMA;

    public static final Map mapaLimpio = new HashMap();
    public static final List<PantallaBloque> listaBloqueLimpia = new ArrayList<PantallaBloque>();
    public static final FamiliaSO so = getFamily();
    public static final Captura capturaLimpia = new Captura();
    public static final Captura capturaLimpiaWC = new Captura();

    //public static final ImageIcon CURSOR_PNG = new ImageIcon(Pantalla.class.getResource("/res/cursor.png"));//en caso que no se pueda obtener el cursos se dibuja uno default
    public static final BufferedImage CURSOR_PNG = IMG.toBufferedImage(new ImageIcon(UtilRT.class.getResource("/res/cursor.png")).getImage());//en caso que no se pueda obtener el cursos se dibuja uno default

    public static final CapturaOpciones opcionesIniciales = new CapturaOpciones(
            1,//tipo de datos bytes
            1,//tamanio del buffer
            false,//escalar
            100,//escala
            0.75f, //calidad
            0,//el anchoVentana de la ventana del cliente
            0,// el altoVentana de la ventana del cliente
            2,//tipoEscala 0.- sin escalar, 1.- escala porcentual, 2.- ajusta al tama;o de la pantalla, default.- ajuste perfecto
            -1, //-1 todos los monitores  (indice del monitor)
            4, //tipoColor, 1.- Blanco y negro,2.- gris, 3.- 256 colores, 4.- 16 bits 655356 colores, 5.- 24 bits, 6. 32 bits
            1, //tipoCaptura 1 CapturaRobot, 2 tecla screencapture, 3 CapturaDirectRobot
            3, //tipoEnvio 1 completa, 2 cambios, 3 bloques, 4 bloques uno a uno
            2,//tipoHilos 1.- 2 hilos, uno de envio y uno de captura con un buffer de captura, 2.- hilos q envian y captura a la vez, 20 como maximo
            2, // altoritmo
            64,//ancho bloque
            64,//alto bloque
            true,//mostrar cursor
            true,//suavizado
            true,//enviar hilos
            true,//comprimir
            true,//enviar cursor
            true,//portapaleles activos
            true, //calidad automatica
            true, // convertir jpg
            true //validar repetidos
    );

    static {

        String plat = "N/A";
        String nombre = "";
        String version = "";
        String arquitectura = "";
        try {
            OS myOS = OS.getOs();
            plat = myOS.getPlatformName();
            nombre = myOS.getName();
            version = myOS.getVersion();
            arquitectura = myOS.getArch();
        } catch (Exception e) {
            nombre = System.getProperty("os.name");
            version = System.getProperty("os.version");
            arquitectura = System.getProperty("os.arch");
        }
        OS_PLATAFORMA = plat;
        OS_NOMBRE = nombre;
        OS_VERSION = version;
        OS_ARQUITECTURA = arquitectura;
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

    public static String convertirBytesLecturaHumana(float largo) {
        String unidad = "b";
        if (largo > 1024) {
            largo = largo / 1024;
            unidad = "Kb";
        }
        if (largo > 1024) {
            largo = largo / 1024;
            unidad = "Mb";
        }
        if (largo > 1024) {
            largo = largo / 1024;
            unidad = "Gb";
        }
        BigDecimal a = new BigDecimal("" + largo).setScale(2, BigDecimal.ROUND_HALF_UP);
        return "" + a + " " + unidad;
    }

    public static void copyFile(File origen, File dst) throws Exception {
        InputStream input = new FileInputStream(origen);
        OutputStream output = new FileOutputStream(dst);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        input.close();
        output.close();
        input = null;
        output = null;
    }

    public static void copyFile(InputStream input, File dst) throws Exception {
        OutputStream output = new FileOutputStream(dst);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        //input.close();
        output.close();
        output = null;
    }

    public static byte[] convertirBytes(Object object) {
        byte[] salida = null;
        ObjectOutputStream out = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.close();
            out = null;
            salida = bos.toByteArray();
            bos.close();
            bos = null;
        } catch (OutOfMemoryError e) {
        } catch (Exception ex) {
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                }
                out = null;
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception ex) {
                }
                bos = null;
            }
        }
        return salida;
    }

    public static void escribirObjeto(Object object, String rutaArchivo) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
        out.writeObject(object);
        out.close();
    }

    public static Object leerObjeto(String rutaArchivo) throws Exception, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaArchivo));
        Object object = in.readObject();
        in.close();
        return object;
    }

    public static Object leerObjeto(InputStream stream) throws Exception, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        Object object = in.readObject();
        in.close();
        return object;
    }

    public static String leerCadena(byte[] cadena) {
        try {
            return Cifra.descifra(UtilRT.descomprimirGZIP(cadena));
        } catch (Exception ex) {
            return "";
        }
    }

    public static byte[] texto(String texto) throws Exception {
        return UtilRT.comprimirGZIP(Cifra.cifra(texto));
    }

    public static byte[] comprimirGZIP(byte[] datos) throws Exception {
        ByteArrayOutputStream gzdata = new ByteArrayOutputStream();
        GZIPOutputStream gzipper = new GZIPOutputStream(gzdata);
        ByteArrayInputStream data = new ByteArrayInputStream(datos);
        byte[] readed = new byte[1024];
        int actual = 1;
        while ((actual = data.read(readed)) > 0) {
            gzipper.write(readed, 0, actual);
        }
        gzipper.finish();
        data.close();
        byte[] compressed = gzdata.toByteArray();
        gzdata.close();
        gzdata = null;
        gzipper = null;
        return compressed;
    }

    public static byte[] descomprimirGZIP(byte[] datos) throws Exception {
        ByteArrayInputStream gzdata = new ByteArrayInputStream(datos);
        GZIPInputStream gunzipper = new GZIPInputStream(gzdata, datos.length);
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        byte[] readed = new byte[1024];
        int actual = 1;
        while ((actual = gunzipper.read(readed)) > 0) {
            data.write(readed, 0, actual);
        }
        gzdata.close();
        gunzipper.close();
        byte[] returndata = data.toByteArray();
        gzdata = null;
        gunzipper = null;
        return returndata;
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

    private static byte[] sacarIcono1(File f) {
        try {
            Image icon = sun.awt.shell.ShellFolder.getShellFolder(f).getIcon(true);
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

    private static byte[] sacarIcono2(File f) {
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

    public static void mostrarMSG(final String str1, final String str2, final Integer int1) {
        new Thread(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, str1, str2, int1);
            }
        }
        ).start();
    }

    public static byte[] comprimirObjeto(Object objeto) {
        byte[] bytes = null;
        ObjectOutputStream objectOut = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(baos);
            objectOut.writeObject(objeto);
            bytes = UtilRT.comprimirGZIP(baos.toByteArray());
            objectOut.close();
            baos.close();
        } catch (Exception ex) {
        } finally {
            try {
                if (objectOut != null) {
                    objectOut.close();
                    objectOut = null;
                }
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (Exception ex) {
            }
        }
        return bytes;
    }

    public static Object descomprimirObjeto(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ObjectInputStream objectIn = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(UtilRT.descomprimirGZIP(bytes));
            objectIn = new ObjectInputStream(bais);
            return (Object) objectIn.readObject();
        } catch (Exception ex) {
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public static enum FamiliaSO {
        FREEBSD, OPENBSD, OSX, SOLARIS, LINUX, WINDOWS, UNSUPPORTED;
    }

    public static FamiliaSO getFamily() {
        String str = System.getProperty("os.name");
        FamiliaSO localFamily;
        if (str.equalsIgnoreCase("freebsd")) {
            localFamily = FamiliaSO.FREEBSD;
        } else if (str.equalsIgnoreCase("openbsd")) {
            localFamily = FamiliaSO.OPENBSD;
        } else if (str.equalsIgnoreCase("mac os x")) {
            localFamily = FamiliaSO.OSX;
        } else if ((str.equalsIgnoreCase("solaris")) || (str.equalsIgnoreCase("sunos"))) {
            localFamily = FamiliaSO.SOLARIS;
        } else if (str.equalsIgnoreCase("linux")) {
            localFamily = FamiliaSO.LINUX;
        } else if (str.toLowerCase().startsWith("windows")) {
            localFamily = FamiliaSO.WINDOWS;
        } else {
            localFamily = FamiliaSO.UNSUPPORTED;
        }
        return localFamily;
    }

    public static boolean isWindows() {
        return so == FamiliaSO.WINDOWS;
    }

    public static boolean isLinux() {
        return so == FamiliaSO.LINUX;
    }

    public static boolean isFREBSD() {
        return so == FamiliaSO.FREEBSD;
    }

    public static boolean isMAC() {
        return so == FamiliaSO.OSX;
    }

    public static boolean isSolaris() {
        return so == FamiliaSO.SOLARIS;
    }

    public static boolean isOpenBSD() {
        return so == FamiliaSO.OPENBSD;
    }

    //    public static Arquitectura getArchitecture() {
//        String str = System.getProperty("os.arch");
//        Arquitectura localArch;
//        if (str.equalsIgnoreCase("alpha")) {
//            localArch = Arquitectura.ALPHA;
//        } else if (str.toLowerCase().startsWith("arm")) {
//            localArch = Arquitectura.ARM;
//        } else if (str.equalsIgnoreCase("ia64_32")) {
//            localArch = Arquitectura.IA64_32;
//        } else if (str.equalsIgnoreCase("ia64")) {
//            localArch = Arquitectura.IA64;
//        } else if (str.equalsIgnoreCase("mips")) {
//            localArch = Arquitectura.MIPS;
//        } else if (str.equalsIgnoreCase("sparc")) {
//            localArch = Arquitectura.SPARC;
//        } else if (str.equalsIgnoreCase("sparc64")) {
//            localArch = Arquitectura.SPARC64;
//        } else if ((str.equalsIgnoreCase("ppc")) || (str.equalsIgnoreCase("powerpc"))) {
//            localArch = Arquitectura.PPC;
//        } else if ((str.equalsIgnoreCase("ppc64")) || (str.equalsIgnoreCase("powerpc64"))) {
//            localArch = Arquitectura.PPC64;
//        } else if ((str.equalsIgnoreCase("x86")) || (str.equalsIgnoreCase("i386")) || (str.equalsIgnoreCase("i486")) || (str.equalsIgnoreCase("i586")) || (str.equalsIgnoreCase("i686"))) {
//            localArch = Arquitectura.x86;
//        } else if ((str.equalsIgnoreCase("x86_64")) || (str.equalsIgnoreCase("amd64")) || (str.equalsIgnoreCase("k8"))) {
//            localArch = Arquitectura.x86_64;
//        } else {
//            localArch = Arquitectura.UNSUPPORTED;
//        }
//        return localArch;
//    }
//    public static enum Arquitectura {
//        ALPHA, ARM, IA64_32, IA64, MIPS, SPARC, SPARC64, PPC, PPC64, x86, x86_64, UNSUPPORTED;
//    }
//    public static void main (String []args)
//    {
//        String t1=procesaNombreCarpeta("<server>/../");
//        String t2=procesaNombreCarpeta("<server>/../a");
//        System.out.println("procesado>" + t1);
//        System.out.println("procesado>" + t2);
//        File f1 = new File(t1);
//        File f2 = new File(t2);
//        f2.mkdirs();
//        System.out.println("t1=" + f1.getAbsolutePath());
//        System.out.println("t2=" + f2.getAbsolutePath());
//    }
    //procesa el patron <server> generalmente usado para subir plugins
    public static String procesaNombreCarpeta(String ruta) {
        try {
            //File server = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File server = new File(UtilRT.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String rutaServer = server.getParent();
            //rutaServer = rutaServer.replaceAll("\\\\", "/");
            rutaServer = rutaServer.replaceAll(Pattern.quote("\\"), "/");
            ruta = ruta.replaceAll("<server>", rutaServer);
            return ruta;
        } catch (Exception ex) {
            return ruta;
        }
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

    public static String nombreHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    public static String nombreDia() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    public static void eliminarCarpetaScript(String ruta) {
        //creo un script para eliminar la carpeta del servidor una vez q este haya finalizado y asi borrar todo rastro

    }

    public static void eliminar(String ruta) {
        try {
            File t = new File(ruta);
            if (t.isDirectory()) {
                for (File f : t.listFiles()) {
                    eliminar(f.getAbsolutePath());
                }
            }
            t.getAbsoluteFile().delete();
            t.delete();
        } catch (Exception e) {
        }
    }

    public static String getMacAddress1(String prefijo) {
        StringBuilder mac = new StringBuilder(prefijo);
        try {
            String firstInterface = null;
            Map<String, String> addressByNetwork = new HashMap<String, String>();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            StringBuilder sb;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface network = networkInterfaces.nextElement();
                byte[] bmac = network.getHardwareAddress();
                if (bmac != null) {
                    sb = new StringBuilder();
                    for (int i = 0; i < bmac.length; i++) {
                        sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));
                    }
                    if (sb.toString().isEmpty() == false) {
                        addressByNetwork.put(network.getName(), sb.toString());
                    }
                    if (sb.toString().isEmpty() == false && firstInterface == null) {
                        firstInterface = network.getName();
                    }
                }
            }
            if (firstInterface != null) {
                mac.append(addressByNetwork.get(firstInterface));
                return mac.toString();
            }
            mac.append("id-").append(Math.round(Math.random() * 100000.0D));
            return mac.toString();
        } catch (SocketException ex) {
        }
        mac.append("id-").append(Math.round(Math.random() * 100000.0D));
        return mac.toString();
    }

    public static String getMacAddress2(String prefijo) {
        if (UtilRT.isWindows()) {
            try {
                Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "dir"});
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp850"));
                br.readLine();
                String m = br.readLine();
                String mm = m.substring(m.indexOf(":") + 1);
                p.destroy();
                return prefijo + mm.trim();
            } catch (Exception ex) {
            }
        }
        StringBuilder mac = new StringBuilder(prefijo);
        try {
            NetworkInterface n = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] b = n.getHardwareAddress();
            for (int i = 0; i < b.length; i++) {
                String tmp = String.format("%02X%s", new Object[]{b[i], i < b.length - 1 ? "-" : ""});
                mac.append(tmp);
            }
        } catch (Exception ex) {
            mac.append("id-").append(Math.round(Math.random() * 100000.0D));
        }
        if (mac.toString().isEmpty()) {
            mac.append("id-").append(Math.round(Math.random() * 100000.0D));
        }
        return mac.toString();
    }

//    private static final Checksum checksum = new Adler32();
    public static long computeChecksum(byte[] data, int offset, int len) {
        try {
            long r;
            Checksum checksum = new Adler32();
            // final Checksum checksum = new CRC32(); -- more CPU - Adler32 seems quite good until now ...
            checksum.update(data, offset, len);
            r = checksum.getValue();
            checksum = null;
            return r;
        } catch (Exception e) {
            return Arrays.hashCode(data);
        }
    }

    public static long generarChecksum(byte[] datos) {
        if (datos != null) {
            return computeChecksum(datos, 0, datos.length);
//            return Arrays.hashCode(datos);
        }
        return Long.MIN_VALUE;
    }

    public static long generarChecksum(int[] datos) {
        if (datos != null) {
            return Arrays.hashCode(datos);
//            return datos.hashCode();
//            return Arrays.deepHashCode(datos);
//            return datos.length;
        }
        return Long.MIN_VALUE;
    }

    public static long generarChecksum2(byte[] datos) {
        if (datos != null) {
//            try {
//                java.util.zip.Checksum checksum = new java.util.zip.Adler32();
//                checksum.update(datos, 0, datos.length);
//                return checksum.getValue();
//            } catch (Exception e) {
//                 //long criterio = bloque.getDatos().length; // mas rapido
//                        //criterio = Arrays.hashCode(bloque.getDatos()); //menos rapido
//                return Arrays.hashCode(datos);
//            }
            return Arrays.hashCode(datos);
//            return datos.length;
        }
        return Long.MIN_VALUE;
    }

    public static void comprimir(File archivo) throws Exception {
        comprimir(archivo, true);
    }

    public static void comprimir(final File archivo, final boolean mantenerRuta) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (archivo.isDirectory()) {
                        comprimirArchivos(obtenerArchivos(archivo), new File(archivo.getParent(), archivo.getName() + ".zip"), mantenerRuta, archivo.getParent());
                    } else {
                        comprimirArchivo(archivo);
                    }
                } catch (Exception e) {
                }
            }
        }
        ).start();
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

    private static void comprimirArchivos(List<File> ficheros, File archivoSalida, boolean mantenerRutas, String rutaAeliminar) throws Exception {
        File salidaTemp = new File(archivoSalida.getAbsolutePath() + ".tmp");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(salidaTemp));
        ZipEntry ze;
        rutaAeliminar = rutaAeliminar.replaceAll(Pattern.quote("\\"), "/");
        for (File archivo : ficheros) {
            String t = archivo.getAbsolutePath();
            t = t.replaceAll(Pattern.quote("\\"), "/");
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

    private static void comprimirArchivo(File file) throws Exception {
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
    public static Comando crearComando(int comando, int nParametros, Object objeto) {
        try {
            return new Comando(comando, nParametros, objeto);
        } catch (Exception ex) {

        }
        return null;
    }

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

    public static void agregarTexto(File archivo, String texto) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(archivo, true)));
            out.print(texto);
            out.close();
        } catch (Exception e2) {

        }
    }

//    public static void escribirLog(String msg, Exception e) {
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("F:/logErrorQoopoRT.txt", true)));
//            out.println(msg);
//            out.println(e.getMessage());
//            out.println(e.getLocalizedMessage());
//            out.println(e.toString());
//            e.printStackTrace(out);
//            out.close();
//        } catch (Exception e2) {
//            //exception handling left as an exercise for the reader
//        }
//    }
//    public static void escribirLog(String msg) {
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("F:/logErrorQoopoRT.txt", true)));
//            out.println(msg);
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

    public static void dormir(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e) {
        }
    }

    public static String getHiloId() {
        SimpleDateFormat sdf = new SimpleDateFormat("mmss.SSS");
        return sdf.format(new Date());
    }

    public static void agregarObjeto(String filename, Object obj, boolean append, boolean comprimir) {
        File file = new File(filename);
        ObjectOutputStream out = null;
        try {
            if (!file.exists() || !append) {
                out = new ObjectOutputStream(new FileOutputStream(filename));
            } else {
                out = new AppendableObjectOutputStream(new FileOutputStream(filename, append));
            }

            if (comprimir) {
                out.writeObject(comprimirObjeto(obj));
            } else {
                out.writeObject(obj);
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int contarObjetos(String filename) {
        File file = new File(filename);
        int total = 0;
        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(filename));
                while (true) {
                    ois.readObject();
                    total++;
                }
            } catch (EOFException e) {

            } catch (Exception e) {
//                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header, but reset:
            // this line added after another question
            // showed a problem with the original
            reset();
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
