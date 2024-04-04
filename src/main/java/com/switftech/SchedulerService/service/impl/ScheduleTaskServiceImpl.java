package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.service.ScheduleTaskService;
import com.switftech.SchedulerService.util.EventScheduler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private static Logger logger = LoggerFactory
            .getLogger(EventScheduler.class);

    private boolean enabled = true;

    //every 5 seconds
     // @Scheduled(cron = "0/5 * * * * ?")
    public void preprocess() {
        if (!enabled) {
            return;
        }

        try {
            ScheduleTaskService.preprocess();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    // every 10 seconds
    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMessages() {
        if (!enabled) {
            return;
        }

        logger.debug("start");

        try {
            ScheduleTaskService.scheduleTasks();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        logger.debug("end");
    }
    @Value("${notification.enabled}")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}


