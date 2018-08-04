package com.example.model;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author mheimer
 */
public class VoteSingleton {

    private static final VoteSingleton instance = new VoteSingleton();
    private final ConcurrentMap<String, Integer> votes = new ConcurrentHashMap<>();

    private VoteSingleton() {

    }

    public static VoteSingleton getInstance() {
        return instance;
    }

    public Integer getVotes(String id) {
        return votes.getOrDefault(id, 0);
    }

    public Integer like(String id) {
        Integer total = addVote(id, 1);
		
        return total;
    }

    public Integer dislike(String id) {
        Integer total = addVote(id, -1);
		
        return total;
    }

    private Integer addVote(String id, Integer value) {
        Integer result = votes.putIfAbsent(id, value);
        if (result == null) {
            return value;
        } else {
            while (!votes.replace(id, result, result + value)) {
                result = votes.get(id);
            }
            return result + value;
        }
    }

}
