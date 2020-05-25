/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.utilidades;
/**
 *
 * @author alberto
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
public class NoIpService {
    private String user;
    private String pass;
    public NoIpService(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    public String[] getDNS() {
        String[] DNS = null;
        try {
            HttpURLConnection conexion = (HttpURLConnection) new URL("http://dynupdate.no-ip.com/list-hosts.php?email=" + this.user + "&pass=" + this.pass).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String t = "";
            while (br.ready()) {
                t = br.readLine();
            }
            if (t.contains("=")) {
                return null;
            }
            t = t.replace("#,", "");
            return t.split(Pattern.quote("|"));
        } catch (IOException ex) {
        }
        return DNS;
    }
    public String actualiza(String DNS, String ip) {
        String texto = "Ocurrio un error";
        String URL = "http://dynupdate.no-ip.com/dns?username=" + this.user + "&password=" + this.pass + "&hostname=" + DNS;
        if (!ip.equals("")) {
            URL = URL + "&ip=" + ip;
        }
        try {
            HttpURLConnection conexion = (HttpURLConnection) new URL(URL).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String resultado = br.readLine();
            conexion.disconnect();
            String[] dato = resultado.split(":");
            int valor = Integer.parseInt(dato[1]);
            switch (valor) {
                case 0:
                    texto = "Sin cambios";
                    break;
                case 1:
                    texto = "Actualizado";
            }
        } catch (Exception ex) {
            System.out.println("Error servicio no-ip:" + ex.getMessage());
        }
        return texto;
    }
}
