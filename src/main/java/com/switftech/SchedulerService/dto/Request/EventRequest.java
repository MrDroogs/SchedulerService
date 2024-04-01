package com.switftech.SchedulerService.dto.Request;

import com.switftech.SchedulerService.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class EventRequest {
    private Status status;
    private boolean isScheduled;
    private LocalDateTime createdAt;
    private LocalDateTime eventDateTime;

}
