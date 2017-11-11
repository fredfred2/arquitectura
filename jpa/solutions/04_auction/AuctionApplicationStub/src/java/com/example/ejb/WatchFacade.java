package com.example.ejb;

import com.example.model.AuctionUser;
import com.example.stub.StubbedWatches;
import com.example.stub.Watch;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ejb.Stateless;

@Stateless
public class WatchFacade {

    public boolean addWatch(AuctionUser watcher, int auctionId) {
        boolean result = false;
        if (!isWatchedBy(watcher, auctionId)) {
            CopyOnWriteArrayList<Watch> watches = StubbedWatches.getWatches();
            watches.add(new Watch(watcher.getDisplayName(), auctionId));
            result = true;
        }
        return result;
    }

    private boolean isWatchedBy(AuctionUser user, int auctionId) {
        boolean result = false;
        CopyOnWriteArrayList<Watch> watches = StubbedWatches.getWatches();
        for (Watch w : watches) {
            if (w.getWatcherId().equals(user.getDisplayName()) &&
                w.getAuctionId() == auctionId) {
                result = true;
            }
        }
        return result;
    }

    // How many unique userId's are watching this auctionID
    public int numWatches(int auctionId) {
        CopyOnWriteArrayList<Watch> watches = StubbedWatches.getWatches();
        int num = 0;
        for (Watch w : watches) {
            if (w.getAuctionId() == auctionId) {
                num++;
            }
        }
        return num;
    }
}
