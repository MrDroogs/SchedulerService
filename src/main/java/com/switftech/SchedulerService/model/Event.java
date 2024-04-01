package com.switftech.SchedulerService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    private UUID id;
    private Status status;
    private boolean isScheduled;
    private LocalDateTime createdAt;
    private LocalDateTime dateTime;
}
