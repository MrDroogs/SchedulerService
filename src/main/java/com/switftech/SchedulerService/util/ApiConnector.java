package com.switftech.SchedulerService.util;

import com.switftech.SchedulerService.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ApiConnector {
    private final String MESSAGE_MANAGEMENT_API_URL = "http://message-management-service-api/messages";
    private final WebClient webClient;

    @Autowired
    public ApiConnector(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(MESSAGE_MANAGEMENT_API_URL).build();
    }
    @Scheduled(cron = "0 * * * * *")
    public void scheduleTasks() {
        webClient.get()
                .retrieve()
                .bodyToMono(Event[].class)
                .subscribe(tasks -> {
                    for (Event task : tasks) {

                        System.out.println("Scheduling task: " + task.getId());
                    }
                }, error -> {

                    System.err.println("Failed to fetch tasks from the API: " + error.getMessage());
                });
    }
}
