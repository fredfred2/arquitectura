package com.example.stub;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author tmcginn
 */
public class StubbedWatches {
    // Map: userId, auctionId
    private static ConcurrentHashMap<Integer, Integer> watches;
    private static final StubbedWatches instance =
            new StubbedWatches();

    private StubbedWatches() {
       watches = new ConcurrentHashMap<>();
    }
    
    public static ConcurrentHashMap<Integer, Integer> getWatches() {
        return watches;
    }
}
