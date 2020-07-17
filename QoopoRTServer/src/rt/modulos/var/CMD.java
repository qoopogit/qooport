package rt.modulos.var;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import comunes.Interfaz;
import java.util.Map;
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

//                    final Map<String, String> envs = new HashMap<String, String>(builder.environment());
                    final Map<String, String> envs = builder.environment();
//                    final Map<String, String> envs = System.getenv();

                    envs.put("TERM", "xterm");
//                    builder.environment().putAll(envs);
                    builder.redirectErrorStream(true);
                    p = builder.start();
                    a = true;
                    s.ejecutar(3, Protocolo.COMANDO_SHELL, "\n<Consola activada>\n");
                    final InputStream inStream = p.getInputStream();
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                byte[] buf = new byte[1];//voy leyendo de caracter en caracter
                                while ((inStream.read(buf)) > 0 && a) {
                                    //out.write(buf, 0, tamanio);
                                    s.ejecutar(3, Protocolo.COMANDO_SHELL, new String(buf));
                                }
                            } catch (Exception e) {
                            }
                            s.ejecutar(3, Protocolo.COMANDO_SHELL, "\n<Consola desactivada>\n");
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
        s.ejecutar(3, Protocolo.COMANDO_SHELL, "\n<Consola desactivada>\n");
    }

    public void escribirCaracter(char c) {
        if (p != null) {
            try {
                OutputStream outStream = p.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream);
                pWriter.print(c);
                pWriter.flush();
            } catch (Exception e) {

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
