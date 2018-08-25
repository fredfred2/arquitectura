package com.practices.concurrency.part3;

import com.practices.services.concurrency.WordWebService;
import com.practices.services.concurrency.WordWebService_Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

@WebService(serviceName = "WordServiceDistributed")
@Stateless()
public class WordServiceDistributed {

  @Resource(name = "java:comp/env/concurrent/ServiceInvoker")
  private ManagedExecutorService executorService;

  @WebMethod(operationName = "process")
  public String process(@WebParam(name = "words") String words) throws Exception {
    long startTime = System.currentTimeMillis();
    StringBuilder str = new StringBuilder();
    // - invoke external registry service -
    List<Future<String>> results = new ArrayList<>();
    WordWebService service = new WordWebService_Service().getPort(WordWebService.class);
    for (String word : words.split("\\ ")) {
      results.add(invokeRemote(word, service));
    }
    for (Future<String> result : results) {
      str.append(result.get());
      str.append("\n");
    }
    // - end invoke external registry service -
    str.append(" Took: ");
    str.append(System.currentTimeMillis() - startTime);
    str.append("ms.");
    return str.toString();

  }

  private Future<String> invokeRemote(String word, WordWebService service) {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        return service.register(word);
      }
    };
    return executorService.submit(callable);
  }
}
