package qooport.utilidades;

public class GC extends Thread {

    private static GC instancia;

    public static void iniciar() {
        try {
            if (instancia == null) {
                instancia = new GC();
                instancia.start();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    //sleep(120000);//cada 2 minutos
                    sleep(60000);//cada  minuto
                } catch (Exception ex) {
                }
                Util.gc();
            }
        } catch (Exception e) {

        }
    }

}
