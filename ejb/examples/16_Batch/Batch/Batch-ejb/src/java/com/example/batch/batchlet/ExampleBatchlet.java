package com.example.batch.batchlet;

import javax.batch.api.AbstractBatchlet;
import javax.batch.api.BatchProperty;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExampleBatchlet extends AbstractBatchlet {

    @Inject
    @BatchProperty(name = "prefix")
    private String prefix;
    @Inject
    @BatchProperty(name = "times")
    private String timesProp;

    @Override
    public String process() throws Exception {
        int times = Integer.parseInt(timesProp);
        for (int i = 0; i < times; i++) {
            System.out.println(prefix + ": " + i);
        }
        return "COMPLETED";
    }

}
