package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.service.ScheduleTaskService;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);



    private final Scheduler scheduler;


    public void scheduleEvent(EventRequest eventRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(eventRequest.getEventDateTime(), eventRequest.getTimeZone().toZoneId());
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
        return JobBuilder.newJob()
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
