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
        } catch (Exception ex) {

        }
    }

}
