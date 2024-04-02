package com.switftech.SchedulerService.controller;

import com.switftech.SchedulerService.dto.Request.Response.EventResponse;
import com.switftech.SchedulerService.repository.EventRepository;
import com.switftech.SchedulerService.service.ScheduleTaskService;
import com.switftech.SchedulerService.util.RunnableTimeTask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class EventController {
 private final ScheduleTaskService scheduleTaskService;
 private final EventRepository eventRepository;


 @PostMapping("/message")
 public ResponseEntity<EventResponse> schedule(@RequestBody EventResponse eventResponse) throws ParseException {
  return new ResponseEntity<>(scheduleTaskService.schedule((RunnableTimeTask) eventResponse), HttpStatus.OK);
 }
}



