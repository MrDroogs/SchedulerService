package com.switftech.SchedulerService.controller;

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
    public ResponseEntity<String> scheduleTasks() {
        ScheduleTaskService.scheduleTasks();
        return ResponseEntity.ok("Tasks scheduled successfully");
    }
}

