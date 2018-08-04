package com.example.batch.chunk;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class ChunkProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        System.out.println("Processing item: " + item);
        return item.toString();
    }

}
