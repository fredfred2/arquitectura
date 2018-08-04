package com.example.timer;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.ejb.Timer;

@Singleton
public class AutoTimer {

    private String value;
    private int num;

    @Schedules({
        @Schedule(hour = "*", minute = "*", second = "*/7", persistent = false),
        @Schedule(hour = "*", minute = "*", second = "*/13", persistent = false)})
    public void run(Timer timer) {
        num++;
        value = "Executed: " + num + " times, last run: " + System.currentTimeMillis() + " by: " + timer;
    }

    public String getValue() {
        return value;
    }

}
