package com.switftech.SchedulerService.model;

import com.switftech.SchedulerService.core.base.entity.BaseAuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scheduled_events")
public class Event extends BaseAuditEntity {


    private int hour;
    private int minute;
    private int second;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean isScheduled;
    private LocalDateTime createdAt;
    private LocalDateTime dateTime;
    private TimeZone timeZone;
    private String message;
}
