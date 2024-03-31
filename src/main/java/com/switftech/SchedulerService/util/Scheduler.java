package com.switftech.SchedulerService.util;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    @Scheduled
    public void scheduledTask(){

    }

}
