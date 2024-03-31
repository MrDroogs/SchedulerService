package com.switftech.SchedulerService.service;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;



public interface SchedulingService {
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar);
    public boolean addJob(String jobName);
    public boolean removeJob(String name);
    public String methodToBeExecuted();

}
