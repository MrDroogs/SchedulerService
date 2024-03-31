package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.repository.ConfigRepo;
import com.switftech.SchedulerService.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {

    private final ConfigRepo configRepo;
    private final SchedulingConfigurer configurer;
    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    Map<String, ScheduledFuture> futureMap = new HashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;

        }
        if (taskRegistrar.getScheduler() == null) {
            taskRegistrar.setScheduler(poolScheduler());
        }
    }

    @Override
    public boolean addJob(String jobName) {
        if (futureMap.containsKey(jobName)) {
            return false;
        }
        ScheduledFuture future = scheduledTaskRegistrar.getScheduler()
                .schedule(() -> methodToBeExecuted(), t -> {
                    Calendar nextExecutionTime = new GregorianCalendar();
                    Date lastActualExecutionTime = t.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                    nextExecutionTime.add(Calendar.SECOND, 5);
                    return nextExecutionTime.getTime()
                            .toInstant();

                });
        configureTasks(scheduledTaskRegistrar);
        futureMap.put(jobName, future);
        return true;
    }

    @Override
    public boolean removeJob(String name) {
        if (futureMap.containsKey(name)) {
            return false;
        }
        ScheduledFuture scheduledFuture = futureMap.get(name);
        scheduledFuture.cancel(true);
        futureMap.remove(name);
        return true;

    }

    @Override
    public String methodToBeExecuted() {

        return null;
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

}



