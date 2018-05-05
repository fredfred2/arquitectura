package javasewebservice;

import javax.xml.ws.Endpoint;

public class JavaSEWebService {

    public static void main(String[] args) {
        Endpoint endPoint =
                Endpoint.publish("http://localhost:8080/Hello", new HelloService());

    }
}