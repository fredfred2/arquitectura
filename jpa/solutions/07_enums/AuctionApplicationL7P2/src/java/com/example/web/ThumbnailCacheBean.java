package com.example.web;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;


@Singleton
@LocalBean
public class ThumbnailCacheBean {

    private Map<String,byte[]> thumbNails = new HashMap<>();
    
    @Lock(LockType.READ)
    public byte[] getCachedThumbnail(String id) {
        return thumbNails.get(id);
    }
    
    @Lock(LockType.WRITE)
    public void setCachedThumbnail(String id, byte[] bytes) {
        thumbNails.put(id, bytes);
    }
    
}
