package com.switftech.SchedulerService.service;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import com.switftech.SchedulerService.dto.Request.Response.EventResponse;
import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.util.RunnableTimeTask;

import java.util.Optional;

public interface ScheduleTaskService {

    public void ScheduleTaskService();
    public EventResponse schedule (RunnableTimeTask task);

    Event save(Event event);

   Optional<Event> findById(long id);
//   public void scheduleMessage(EventRequest eventRequest);
}
