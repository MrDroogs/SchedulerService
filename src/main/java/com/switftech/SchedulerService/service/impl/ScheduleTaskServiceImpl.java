package com.switftech.SchedulerService.service.impl;

import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.service.ScheduleTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@RequiredArgsConstructor


@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    private final String MESSAGE_MANAGEMENT_API_URL = "http://message-management-service-api/messages"; // Replace with the actual URL of the MessageManagementService
    private final WebClient webClient;

    @Autowired
    public ScheduleTaskServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(MESSAGE_MANAGEMENT_API_URL).build();
    }

    // Scheduled to run every hour
    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTasks() {
        webClient.get()
                .retrieve()
                .bodyToMono(Event[].class)
                .subscribe(tasks -> {
                    for (Event task : tasks) {
                        // Handle scheduling logic here
                        System.out.println("Scheduling task: " + task.getId());
                    }
                }, error -> {
                    // Handle API call failure
                    System.err.println("Failed to fetch tasks from the API: " + error.getMessage());
                });
    }
}
