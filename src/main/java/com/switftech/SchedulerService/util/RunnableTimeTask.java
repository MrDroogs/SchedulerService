package com.switftech.SchedulerService.util;

import org.springframework.scheduling.Trigger;

public interface RunnableTimeTask {
    public Trigger getScheduleTime();
    public Runnable getRunnable();
}
