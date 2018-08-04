package com.example.batch.chunk;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

@Named
public class ChunkWriter extends AbstractItemWriter {

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            System.out.println("Chunk Write: " + item);
        }
    }

}
