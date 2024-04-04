package com.switftech.SchedulerService.util;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;

public class EventScheduler implements RunnableTimeTask {

    private String message;
    private Trigger trigger;

    public EventScheduler(String message, CronTrigger cronTrigger) {
    }


    @Override
    public Trigger getScheduleTime() {
        return trigger;
    }

    @Override
    public Runnable getRunnable() {
        return null;
    }
}
