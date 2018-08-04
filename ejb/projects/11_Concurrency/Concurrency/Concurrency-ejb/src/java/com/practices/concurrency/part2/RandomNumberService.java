package com.practices.concurrency.part2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "RandomNumberService")
@Stateless()
public class RandomNumberService {

    @WebMethod(operationName = "numbers")
    public String getNumbers(@WebParam(name = "howMany") int howMany) {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        // - Generate Random Numbers -

        // - End Random Number Generation -
        str.append(" Took: ");
        str.append(System.currentTimeMillis() - startTime);
        str.append("ms.");
        return str.toString();

    }
}
