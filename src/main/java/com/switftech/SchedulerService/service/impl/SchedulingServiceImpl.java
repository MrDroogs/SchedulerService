package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.model.Status;
import com.switftech.SchedulerService.repository.EventRepository;
import com.switftech.SchedulerService.service.SchedulingService;
import com.switftech.SchedulerService.util.EventScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {

    private final EventRequest eventRequest;
    private final EventRepository eventRepository;
    private final EventScheduler eventScheduler;
    private ScheduledTaskRegistrar scheduledTaskRegistrar;


    @Scheduled(cron = "0/5 * * * * ?")
    public String sendMessage(EventRequest eventRequest) {
        Event event = eventRepository.findByStatus(Status.SCHEDULED);
        LocalDateTime createdAt = LocalDateTime.now();
        if (LocalDateTime.now()
                .isAfter(event.getCreatedAt())) {
            event.setStatus(Status.PENDING);
            return "Time has already passed";

        } else
            event.setStatus(Status.SCHEDULED);
        event.setCreatedAt(eventRequest.getCreatedAt());
        event.setDateTime(eventRequest.getEventDateTime());
        eventRepository.save(event);
        return "message sent successfully";
    }


}



