package com.example.timer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class ProgramaticTimer {

    @Resource
    private TimerService timerService;

    private String value;
    private int num;

    @PostConstruct
    public void init() {
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.second(0).minute("*").hour("*");
        TimerConfig timerConfig = new TimerConfig("Programatic Timer", false);
        timerService.createCalendarTimer(schedule, timerConfig);
    }

    @Timeout
    public void execute() {
        num++;
        value = "Executed: " + num + " times, last run: " + System.currentTimeMillis();
    }

    public String getValue() {
        return value;
    }
}
