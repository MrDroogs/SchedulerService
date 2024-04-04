package com.switftech.SchedulerService.dto.Request.Response;

import com.switftech.SchedulerService.model.Status;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private Status status;
    private String message;



}
