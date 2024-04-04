package com.switftech.SchedulerService.service;

import com.switftech.SchedulerService.dto.Request.EventRequest;

public interface ScheduleTaskService {
    static void preprocess() {
    }

    static void scheduleTasks() {

    }

    void scheduleEvent(EventRequest eventRequest);
}
