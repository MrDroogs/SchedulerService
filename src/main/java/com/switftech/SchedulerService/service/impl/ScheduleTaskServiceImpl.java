package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.dto.Request.Response.EventResponse;
import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.model.Status;
import com.switftech.SchedulerService.repository.EventRepository;
import com.switftech.SchedulerService.service.ScheduleTaskService;
import com.switftech.SchedulerService.util.MessageTask;
import com.switftech.SchedulerService.util.RunnableTimeTask;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    private final EventRepository eventRepository;
    private final EventResponse eventResponse;
    private static final int POOL_SIZE = 5;
    private static final String THREAD_NAME_PREFIX = "ThreadPoolTaskScheduler";
    private TaskScheduler taskScheduler;

    @Scheduled(fixedDelay = 5000)
    public void ScheduleTaskService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix(THREAD_NAME_PREFIX);
        scheduler.setPoolSize(POOL_SIZE);
        scheduler.initialize();
        this.taskScheduler = scheduler;
    }

    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        List<Event> scheduledEvents = (List<Event>) eventRepository.findByStatus(Status.SCHEDULED);
        for (Event message : scheduledEvents) {
            schedule(MessageTask.build(message));
        }
    }

    public EventResponse schedule(RunnableTimeTask task) {
        if (task == null || task.getRunnable() == null || task.getScheduleTime() == null)
            return eventResponse.exception("The task is not correct");
        else {
            taskScheduler.schedule(task.getRunnable(), task.getScheduleTime());
            System.out.println("Message scheduled at " + task.getScheduleTime().toString());
            return eventResponse.accepted("task is scheduled");
        }
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    @Scheduled(cron = "0 0 5 * * ?")
    public void scheduleMessagesAtFiveAM() {
        List<Event> messages = eventRepository.findAllByStatusAndScheduledTime(Status.SCHEDULED, LocalTime.of(5, 0));
        for (Event message : messages) {
            schedule(MessageTask.build(message));
        }

    }
}
