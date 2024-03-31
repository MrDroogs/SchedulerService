package com.switftech.SchedulerService.service;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public interface CancellableScheduler {
    public String configureTasks(ScheduledTaskRegistrar taskRegistrar);
    public String cancelAll();
    public String scheduleFixed();
    public String scheduleCron();

    public String activateAll();

    public String activateFuture();
    public String cancelFuture();



}
