package com.switftech.SchedulerService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scheduled_events")
public class Event {

    @Id
    private UUID id;
    private int hour;
    private int minute;
    private int second;
    private Status status;
    private boolean isScheduled;
    private LocalDateTime createdAt;
    private LocalDateTime dateTime;
    private String message;
}
