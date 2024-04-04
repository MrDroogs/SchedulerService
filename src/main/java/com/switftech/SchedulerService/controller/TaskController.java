package com.switftech.SchedulerService.controller;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.service.ScheduleTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ScheduleTaskService scheduleTaskService;


    @GetMapping("/schedule")
    public ResponseEntity<String> scheduleEvents(EventRequest eventRequest) {
        scheduleTaskService.scheduleEvent(eventRequest);
        return ResponseEntity.ok("Tasks scheduled successfully");
    }
}

