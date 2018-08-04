package com.practices.concurrency.part3;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "WordServiceDistributed")
@Stateless()
public class WordServiceDistributed {

    @WebMethod(operationName = "process")
    public String process(@WebParam(name = "words") String words) {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        // - invoke external registry service -

        // - end invoke external registry service -
        str.append(" Took: ");
        str.append(System.currentTimeMillis() - startTime);
        str.append("ms.");
        return str.toString();

    }
}
