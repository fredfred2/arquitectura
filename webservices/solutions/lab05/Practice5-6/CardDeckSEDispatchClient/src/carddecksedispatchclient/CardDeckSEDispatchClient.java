package carddecksedispatchclient;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CardDeckSEDispatchClient {

    public static void main(String[] args) throws SOAPException {

        QName serviceQName = new QName("http://ejbs/", "CardDeckSessionBeanService");
        QName portQName = new QName("http://ejbs/", "EchoServicePort");
        String endpointUrl = "http://localhost:7001/CardDeckSessionBean/CardDeckSessionBeanService";


        Service cardService = Service.create(serviceQName);
        cardService.addPort(portQName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);
        Dispatch<SOAPMessage> dispatch = cardService.createDispatch(portQName, SOAPMessage.class, Service.Mode.MESSAGE);
        MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage request = mf.createMessage();
        SOAPPart part = request.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();

        /*
         <env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
         <env:Header />
         <env:Body>
         <createDeck xmlns="http://ejbs/">
         <arg0 xmlns="">1</arg0>
         </createDeck>
         </env:Body>
         </env:Envelope>
         */
        SOAPElement operation = body.addChildElement("createDeck", "ns1", "http://ejbs/");
        SOAPElement value = operation.addChildElement("arg0");
        value.addTextNode("1");
        SOAPMessage response = dispatch.invoke(request);
        String deckId = response.getSOAPBody().getTextContent();
        System.out.println("Deck ID: " + deckId);

        request = mf.createMessage();
        part = request.getSOAPPart();
        envelope = part.getEnvelope();
        body = envelope.getBody();

        operation = body.addChildElement("getDeck", "ns1", "http://ejbs/");
        value = operation.addChildElement("arg0");
        value.addTextNode(deckId);
        response = dispatch.invoke(request);
        SOAPBody responseBody = response.getSOAPBody();

        /*
         <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
         <S:Body>
         <ns2:getDeckResponse xmlns:ns2="http://ejbs/" xmlns:ns3="urn:dukesdecks">
         <return>
         <ns3:card>
         <ns3:rank>A</ns3:rank>
         <ns3:suit>CLUBS</ns3:suit>
         </ns3:card>
         <ns3:card>
         <ns3:rank>2</ns3:rank>
         <ns3:suit>CLUBS</ns3:suit>
         </ns3:card>
         </return>
         </ns2:getDeckResponse>
         </S:Body>
         </S:Envelope>
         */

        NodeList nodes = responseBody.getChildNodes();
        NodeList cardNodes = nodes.item(0).getChildNodes().item(0).getChildNodes();
        for (int i = 0; i < cardNodes.getLength(); i++) {
            Node card = cardNodes.item(i);
            NodeList cardChildNodes = card.getChildNodes();
            String cardStr = "";
            for (int x = 0; x < cardChildNodes.getLength(); x++) {
                Node cardChild = cardChildNodes.item(x);
                cardStr += cardChild.getTextContent() + " ";
            }
            System.out.println(cardStr.trim());
        }

    }
}