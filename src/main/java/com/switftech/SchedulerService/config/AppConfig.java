package com.switftech.SchedulerService.config;

import com.switftech.SchedulerService.dto.Request.EventRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EventRequest eventRequest() {
        return new EventRequest();
    }
}
