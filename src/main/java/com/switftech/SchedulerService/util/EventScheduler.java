package com.switftech.SchedulerService.util;

import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class EventScheduler implements SchedulingConfigurer {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        LocalDateTime eventDateTime = fetchEventDateTimeFromDatabase();
        if (eventDateTime != null) {
            long initialDelay = calculateInitialDelay(eventDateTime);
            taskRegistrar.addFixedDelayTask(() -> triggerEvent(), initialDelay);
        } else {
            System.out.println("No event date time found in the database.");
        }
    }

    private LocalDateTime fetchEventDateTimeFromDatabase() {
        Event event = eventRepository.findDateTime();
        return event != null ? event.getDateTime() : null;
    }

    private long calculateInitialDelay(LocalDateTime eventDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        long initialDelay = ChronoUnit.MILLIS.between(currentDateTime, eventDateTime);
        return initialDelay > 0 ? initialDelay : 0;
    }

    private void triggerEvent() {

        System.out.println("Event triggered at specific date and time!");
    }
}
