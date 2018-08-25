package com.practices.concurrency.part1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;

@WebService(serviceName = "WordService")
@Stateless()
public class WordService {

    @Inject
    private WordBean wordBean;

    @WebMethod(operationName = "check")
    public String check(@WebParam(name = "words") String words) {
        long startTime = System.currentTimeMillis();
        StringBuilder str = new StringBuilder();
        for (String word : words.split("\\ ")) {
            wordBean.register(word);
        }
        str.append(" Took: ");
        str.append(System.currentTimeMillis() - startTime);
        str.append("ms.");
        return str.toString();
    }
}
