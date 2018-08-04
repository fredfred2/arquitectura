package com.example.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.inject.Inject;

@WebService(serviceName = "ConcurrentWebService")
@Stateless()
public class ConcurrentWebService {

    @Inject
    private AsyncEjb asyncEjb;
    @Inject
    private ReturnEjb returnEjb;
    @Inject
    private ConcurrentUtilEjb concurrentUtilEjb;

    @WebMethod(operationName = "invokeEJB")
    public String invokeEJB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            asyncEjb.process();
        }
        return totalTime(start) + "Async EJB launched, see Server console.";
    }

    @WebMethod(operationName = "invokeEJBWithReturn")
    public String invokeEJBWithReturn() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder values = new StringBuilder();
        values.append('\n');
        for (int i = 0; i < 5; i++) {
            values.append(returnEjb.process());
            values.append('\n');
        }
        return totalTime(start) + "Async EJB launched, return values " + values.toString();
    }

    @WebMethod(operationName = "invokeConcurrent")
    public String invokeConcurrent() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder values = new StringBuilder();
        values.append('\n');
        for (int i = 0; i < 5; i++) {
            values.append(concurrentUtilEjb.process());
            values.append('\n');
        }
        return totalTime(start) + "Async methods launched with return values:" + values.toString();
    }

    @WebMethod(operationName = "async-invokeEJB")
    public String invokeAsyncEJB() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            asyncEjb.processAsync();
        }
        return totalTime(start) + "Async EJB launched, see Server console.";
    }

    @WebMethod(operationName = "async-invokeEJBWithReturn")
    public String invokeAsyncEJBWithReturn() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder values = new StringBuilder();
        values.append('\n');
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            results.add(returnEjb.processAsync());
        }
        for (Future<String> result : results) {
            values.append(result.get());
            values.append('\n');
        }
        return totalTime(start) + "Async EJB launched, return values " + values.toString();
    }

    @WebMethod(operationName = "async-invokeConcurrent")
    public String invokeAsyncConcurrent() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder values = new StringBuilder();
        values.append('\n');
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            results.add(concurrentUtilEjb.processAsync());
        }
        for (Future<String> result : results) {
            values.append(result.get());
            values.append('\n');
        }
        return totalTime(start) + "Async methods launched with return values:" + values.toString();
    }

    private String totalTime(long start) {
        return "DONE IN: " + (System.currentTimeMillis() - start) + "ms. ";
    }
}
