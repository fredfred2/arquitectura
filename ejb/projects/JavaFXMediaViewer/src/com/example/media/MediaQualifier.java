package com.example.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediaQualifier {
    
    private final List<MediaType> types = new ArrayList<>();
    private MediaOrder sortOrder = MediaOrder.TITLE_ASC;
    
    public List<MediaType> getTypes() {
        return types;
    }

    public MediaQualifier setTypes(MediaType... typeArray) {
        types.clear();
        types.addAll(Arrays.asList(typeArray));
        return this;
    }

    public MediaOrder getSortOrder() {
        return sortOrder;
    }

    public MediaQualifier setSortOrder(MediaOrder sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
       
    @Override
    public String toString() {
        return types.toString() + ":" + sortOrder;
    }
}