package qooport.utilidades;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import qooport.utilidades.cifrado.Base64Coder;

public class NoIpService {

    private String usuario;
    private String password;

    public NoIpService(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String[] listarDominios() {
        String[] dominios = null;
        try {
            HttpURLConnection conexion = (HttpURLConnection) new URL("http://dynupdate.no-ip.com/list-hosts.php?email=" + this.usuario + "&pass=" + this.password).openConnection();
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
        } catch (Exception ex) {
        }
        return dominios;
    }

    //public String updateDNS(List<String> domains, String ip) {
    /**
     * Servicio de actualización version 3 Implementado 17/02/2017
     *
     * @param domain El host a actualizar, soporta varios separados por coma (,)
     * @param ip
     * @return
     */
    public String actualiza3(String domain, String ip) {
        String texto = "Ocurrió un error";
        try {
            String loginPassword = this.usuario + ":" + this.password;
            //String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            String encoded = Base64Coder.encodeString(loginPassword);

            int letra;
            String retorno = "";

//            for (String domain : domains) {
            String url1 = "http://@dynupdate.no-ip.com/nic/update?hostname=" + domain;
            if (!ip.equals("")) {
                url1 = url1 + "&myip=" + ip;
            }
            URL url = new URL(url1);

            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            while ((letra = bis.read()) != -1) {
                retorno += (char) letra;
            }
            texto = interpretarResultado(retorno);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return texto;
    }

    /**
     * Servicio de actualización version 2 Implementado 17/02/2017
     *
     * @param dominio El host a actualizar, soporta varios separados por coma
     * (,)
     * @param ip
     * @return
     */
    public String actualiza2(String dominio, String ip) {
        String texto = "Ocurrió un error";
        try {

            Thread.sleep(1000);//espero un segundo para no causar conflicto
            String username = URLEncoder.encode(this.usuario, "UTF-8");
            String password = URLEncoder.encode(this.password, "UTF-8");
            

            String url = "http://" + username + ":" + password + "@dynupdate.no-ip.com/nic/update?hostname=" + dominio;
            if (!ip.equals("")) {
                url = url + "&myip=" + ip;
            }

            System.out.println("url=[" + url + "]");
            HttpURLConnection conexion = (HttpURLConnection) new URL(url).openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String resultado = br.readLine();
            conexion.disconnect();
            texto = interpretarResultado(resultado);
        } catch (Exception ex) {
            System.out.println("Error servicio no-ip:" + ex.getMessage());
            ex.printStackTrace();
        }
        return texto;
    }

    /**
     * Versión original de actualizacion
     *
     * @param dominio
     * @param ip
     * @return
     */
    public String actualiza(String dominio, String ip) {
        String texto = "Ocurrió un error";
        String url = "http://dynupdate.no-ip.com/dns?username=" + this.usuario + "&password=" + this.password + "&hostname=" + dominio;
        if (!ip.equals("")) {
            url = url + "&ip=" + ip;
        }
        try {
            HttpURLConnection conexion = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String resultado = br.readLine();
            conexion.disconnect();
            String[] dato = resultado.split(":");
//            System.out.println("Resultado solicitud " + resultado);
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

    private static String interpretarResultado(String resultado) {
        String texto = resultado;
        texto = texto.replaceAll("good", "Actualizado");
        texto = texto.replaceAll("nochg", "Sin Cambios");
        texto = texto.replaceAll("nohost", "No se ha definido el host");
        texto = texto.replaceAll("badauth", "Error de autenticación");
        texto = texto.replaceAll("badagent", "(badagent) Client disabled. Client should exit and not perform any more updates without user intervention.");
        texto = texto.replaceAll("donator", "An update request was sent including a feature that is not available to that particular user such as offline options.");
        texto = texto.replaceAll("abuse", "Username is blocked due to abuse. Either for not following our update specifications or disabled due to violation of the No-IP terms of service. Our terms of service can be viewed here. Client should stop sending updates.");
        texto = texto.replaceAll("911", "A fatal error on our side such as a database outage. Retry the update no sooner than 30 minutes.");
        return texto;
    }
}
