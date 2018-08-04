package com.example.batch.chunk;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named
public class ChunkReader extends AbstractItemReader {

    private int current = 0;

    @Override
    public Object readItem() throws Exception {
        current++;
        System.out.println("Reading item:" + current);
        if (current >= 100) {
            return null;
        } else {
            return current;
        }
    }

}
