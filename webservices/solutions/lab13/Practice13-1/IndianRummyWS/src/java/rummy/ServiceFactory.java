package rummy;

import cards.CardGameService;
import cards.CardGameService_Service;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import weblogic.security.SSL.TrustManager;
import weblogic.wsee.security.bst.ClientBSTCredentialProvider;
import weblogic.wsee.security.unt.ClientUNTCredentialProvider;
import weblogic.wsee.security.util.CertUtils;
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.xml.crypto.wss.provider.CredentialProvider;

public class ServiceFactory {

    private static final Logger logger = Logger.getLogger("rummy");

    public static CardGameService getGameService() {
        CardGameService_Service service = new CardGameService_Service();
        CardGameService port = service.getCardGameServicePort();
        String serverCertFile = "D:/labs/student/keystores/server.pem";
        String clientKeyStore = "D:/labs/student/keystores/client.jks";
        String clientKeyStorePass = "welcome1";
        String clientKeyAlias = "client";
        String clientKeyPass = "welcome1";

        List credProviders = new ArrayList();

        ClientUNTCredentialProvider unt =
                new ClientUNTCredentialProvider("weblogic".getBytes(), "welcome1".getBytes());
        credProviders.add(unt);

        try {
            final X509Certificate serverCert =
                    (X509Certificate) CertUtils.getCertificate(serverCertFile);
            serverCert.checkValidity();

            CredentialProvider cp =
                    new ClientBSTCredentialProvider(clientKeyStore, clientKeyStorePass,
                    clientKeyAlias, clientKeyPass,
                    "JKS", serverCert);
            credProviders.add(cp);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to load binary security token", ex);
        }

        Map<String, Object> requestContext =
                ((BindingProvider) port).getRequestContext();

        requestContext.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST,
                credProviders);

        requestContext.put(WSSecurityContext.TRUST_MANAGER,
                new TrustManager() {
                    @Override
                    public boolean certificateCallback(X509Certificate[] chain, int validateErr) {
                        return true;
                    }
                });

        return port;
    }
}