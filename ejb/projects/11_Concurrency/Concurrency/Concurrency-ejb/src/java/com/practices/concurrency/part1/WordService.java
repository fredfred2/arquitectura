package com.practices.concurrency.part1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "WordService")
@Stateless()
public class WordService {

    @WebMethod(operationName = "check")
    public String check(@WebParam(name = "words") String words) {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        // - Register Words Here -

        // - End Word Registry -
        str.append(" Took: ");
        str.append(System.currentTimeMillis() - startTime);
        str.append("ms.");
        return str.toString();
    }
}
