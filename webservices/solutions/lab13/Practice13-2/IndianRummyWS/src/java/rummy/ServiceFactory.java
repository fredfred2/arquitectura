package rummy;

import cards.CardGameService;
import cards.CardGameService_Service;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import users.UserManagementService;
import users.UserManagementType;
import weblogic.security.SSL.TrustManager;
import weblogic.wsee.security.bst.ClientBSTCredentialProvider;
import weblogic.wsee.security.unt.ClientUNTCredentialProvider;
import weblogic.wsee.security.util.CertUtils;
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.xml.crypto.wss.provider.CredentialProvider;

public class ServiceFactory {

    private static final Logger logger = Logger.getLogger("rummy");
    private static CardGameService_Service gameService;
    private static final ThreadLocal<CardGameService> localGamePort = new ThreadLocal<>();
    private static UserManagementService userService;
    private static final ThreadLocal<UserManagementType> localUserPort = new ThreadLocal<>();

    static {
        final String wsdlPath = "../../wsdl/localhost_7001/GenericCardGameWS/CardGameService.wsdl";
        try {
            URL url = ServiceFactory.class.getResource("");
            url = new URL(url, wsdlPath);
            gameService = new CardGameService_Service(url);
        } catch (Exception e) {
            logger.warning("Failed to create URL for the wsdl Location: " + wsdlPath + ", retrying without local file");
            logger.warning(e.getMessage());
            gameService = new CardGameService_Service();
        }
    }

    static {
        final String wsdlPath = "../../wsdl/localhost_7001/UserManager/user-management-service.wsdl";
        try {
            URL url = ServiceFactory.class.getResource("");
            url = new URL(url, wsdlPath);
            userService = new UserManagementService(url);
        } catch (Exception e) {
            logger.warning("Failed to create URL for the wsdl Location: " + wsdlPath + ", retrying without local file");
            logger.warning(e.getMessage());
            userService = new UserManagementService();
        }
    }

    public static CardGameService getGameService() {
        CardGameService port = localGamePort.get();
        if (port != null) {
            return port;
        } else {
            port = gameService.getCardGameServicePort();
            localGamePort.set(port);

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

    public static UserManagementType getUserService() {
        UserManagementType port = localUserPort.get();
        if (port != null) {
            return port;
        } else {
            port = userService.getUserManagementPort();
            localUserPort.set(port);
            return port;
        }
    }
}