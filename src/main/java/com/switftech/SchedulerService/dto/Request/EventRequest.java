package com.switftech.SchedulerService.dto.Request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventRequest {

    private boolean isScheduled;
    private LocalDateTime createdAt;
    private LocalDateTime eventDateTime;
    private TimeZone timeZone;

}
