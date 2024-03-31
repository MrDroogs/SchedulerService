package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.repository.ConfigRepo;
import com.switftech.SchedulerService.service.CancellableScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class CancellableSchedulerImpl implements CancellableScheduler {

    private final ConfigRepo configRepo;
    private final SchedulingConfigurer configurer;
    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    ScheduledFuture future1;
    ScheduledFuture future2;
    ScheduledFuture future3;
    Map<String, ScheduledFuture> futureMap = new HashMap<>();

    @Override
    public String configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;

        }
        if (taskRegistrar.getScheduler() == null)
            taskRegistrar.setScheduler(poolScheduler());
        return null;
    }
    @Override
    public String cancelAll() {
        return null;
    }

    @Override
    public String scheduleFixed() {
        return null;
    }

    @Override
    public String scheduleCron() {
        return null;
    }

    @Override
    public String activateAll() {
        return null;
    }

    @Override
    public String activateFuture() {
        return null;
    }

    @Override
    public String cancelFuture() {
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
