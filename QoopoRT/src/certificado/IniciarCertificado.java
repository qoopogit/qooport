/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package certificado;

import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author aigarcia
 */
public class IniciarCertificado {

    public static SSLContext sc;

    public static void iniciar() {
        //claves privadas
        KeyManagerFactory keyManagerFactory;
        //claves publicas
        TrustManagerFactory trustManagerFactory;
        try {
            // Configuracion del trustManager---------------------
//            System.out.println("voy a cargar el certificado");
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keystoreTrust = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream keystoreStreamTrust = IniciarCertificado.class.getResourceAsStream("/certificado/truststore.jks");
            keystoreTrust.load(keystoreStreamTrust, "qooport".toCharArray());
            trustManagerFactory.init(keystoreTrust);

            // Configuracion del KeyManager---------------------
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keystoreKeyManager = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream keystoreStreamKeyManager = IniciarCertificado.class.getResourceAsStream("/certificado/keystore.jks");
            keystoreKeyManager.load(keystoreStreamKeyManager, "qooport".toCharArray());
            keyManagerFactory.init(keystoreKeyManager, "qooport".toCharArray());

            /// GENERACIOND EL CONTEXTO SSL
//             sc = SSLContext.getInstance("SSL");
            sc = SSLContext.getInstance("TLS");
//            sc = SSLContext.getInstance("TLSv1.2");
            sc.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//            System.out.println("Certificado Cargado");
//        } catch (NoSuchAlgorithmException | IOException | CertificateException | KeyStoreException | KeyManagementException e) {
//            e.printStackTrace();
//            System.out.println("ERROR AL CARGAR EL CERTIFICADO:" + e.getMessage());
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket();
//            String[] protocols = ss.getSupportedProtocols();
//            for (int i = 0; i < protocols.length; i++) {
////                try {
//                if (protocols[i].equals("SSLv2Hello")) {
//                    continue;
//                }
//                SSLContext sslc = SSLContext.getInstance(protocols[i]);
//                SSLSessionContext sslsc = sslc.getServerSessionContext();
//                System.out.println("Protocol: " + protocols[i]);
//                sslsc.setSessionTimeout(Integer.MAX_VALUE);
//                int newtime = sslsc.getSessionTimeout();
//                if (newtime != Integer.MAX_VALUE) {
//                    throw new Exception("Expected timeout: "
//                            + Integer.MAX_VALUE + ", got instead: "
//                            + newtime);
//                }
////                } catch (Exception e) {
////                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("Finished");
//    }
}
