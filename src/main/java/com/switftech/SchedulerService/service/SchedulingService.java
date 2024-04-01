package com.switftech.SchedulerService.service;

import com.switftech.SchedulerService.dto.Request.EventRequest;

public interface SchedulingService {
    public String sendMessage(EventRequest eventRequest);


}
