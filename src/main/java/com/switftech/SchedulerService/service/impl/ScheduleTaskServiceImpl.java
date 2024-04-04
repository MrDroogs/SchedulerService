package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.model.Status;
import com.switftech.SchedulerService.service.ScheduleTaskService;
import com.switftech.SchedulerService.util.EventScheduler;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);

    private boolean enabled = true;

    private final Scheduler scheduler;

    @Value("${notification.enabled}")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduleTasks() {
        try {
            // Your existing logic here to fetch events

            // Schedule event handling job
            Event eventRequest = new Event();
            eventRequest.setMessage("Sample Event");
            eventRequest.setStatus(Status.SCHEDULED);
            // Set other properties as needed

            scheduleEvent(eventRequest);
        } catch (Exception ex) {
            logger.error("Failed to schedule tasks", ex);
        }
    }

    public void scheduleEvent(Event eventRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(eventRequest.getDateTime(), eventRequest.getTimeZone().toZoneId());
            if (dateTime.isBefore(ZonedDateTime.now())) {
                logger.error("dateTime must be after current time");
                return;
            }

            JobDetail jobDetail = buildJobDetail(eventRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            logger.info("Event Scheduled Successfully!");
        } catch (SchedulerException ex) {
            logger.error("Error scheduling event", ex);
        }
    }

    private JobDetail buildJobDetail(EventRequest eventRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("", eventRequest.getEventDateTime());
        return JobBuilder.newJob((Class<? extends Job>) EventScheduler.class) // Assuming EventJob implements the Job interface
                .withIdentity(UUID.randomUUID().toString(), "event-jobs")
                .withDescription("Handle Event Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }


    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "event-triggers")
                .withDescription("Event Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
