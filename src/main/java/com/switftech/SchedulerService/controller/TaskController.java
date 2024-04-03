package com.switftech.SchedulerService.controller;

import com.switftech.SchedulerService.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final ScheduleTaskService scheduleTaskService;

    @Autowired
    public TaskController(ScheduleTaskService scheduleTaskService) {
        this.scheduleTaskService = scheduleTaskService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<String> scheduleTasks() {
        scheduleTaskService.scheduleTasks();
        return ResponseEntity.ok("Tasks scheduled successfully");
    }
}

