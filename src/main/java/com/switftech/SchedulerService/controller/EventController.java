package com.switftech.SchedulerService.controller;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.repository.EventRepository;
import com.switftech.SchedulerService.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EventController {
 private final SchedulingService schedulingService;
 private final EventRepository eventRepository;

 public ResponseEntity<String>sendMessage(@RequestBody EventRequest eventRequest){
  return new ResponseEntity<>(schedulingService.sendMessage(eventRequest), HttpStatus.OK);
 }
}
