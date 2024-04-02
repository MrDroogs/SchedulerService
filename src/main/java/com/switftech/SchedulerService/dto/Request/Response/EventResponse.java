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
    public static EventResponse accepted(String message)
    {
        EventResponse response = new EventResponse();
        response.setStatus(Status.SCHEDULED);
        response.setMessage(message);
        return response;
    }

    public static EventResponse exception(String message)
    {
        EventResponse response = new EventResponse();
        response.setStatus(Status.PENDING);
        response.setMessage(message);
        return response;
    }
    public static EventResponse completed(String message){
        EventResponse response = new EventResponse();
        response.setStatus(Status.COMPLETED);
        response.setMessage(message);
        return response;
    }


}
