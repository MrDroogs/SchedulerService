package com.switftech.SchedulerService.util;

import com.switftech.SchedulerService.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventScheduler implements RunnableTimeTask {

    private TaskScheduler taskScheduler;
    private String message;
    private Trigger trigger;

    private EventRepository eventRepository;

    public EventScheduler(String message, CronTrigger cronTrigger) {
    }

    @Override
    public Trigger getScheduleTime() {
        return trigger;
    }

    @Override
    public Runnable getRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                System.out.println(message);
            }
        };
    }
}
