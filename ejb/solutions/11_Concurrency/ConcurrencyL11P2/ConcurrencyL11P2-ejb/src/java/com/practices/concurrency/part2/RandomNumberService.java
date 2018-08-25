package com.practices.concurrency.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;

@WebService(serviceName = "RandomNumberService")
@Stateless()
public class RandomNumberService {

    @Inject
    private SlowRandomGenerator slowBean;

    @WebMethod(operationName = "numbers")
    public String getNumbers(@WebParam int howMany) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        List<Future<Integer>> numbers = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            numbers.add(slowBean.getRandomNumber());
        }
        for (Future<Integer> number : numbers) {
            str.append(number.get());
            str.append(", ");
        }
        str.append(" Took: ");
        str.append(System.currentTimeMillis() - startTime);
        str.append("ms.");
        return str.toString();
    }
}
