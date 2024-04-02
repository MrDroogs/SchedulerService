package com.switftech.SchedulerService.util;

import com.switftech.SchedulerService.model.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class MessageTask {

    public static RunnableTimeTask build(Event event){
        EventScheduler eventScheduler = null;
        try {
            CronTrigger cronTrigger = new CronTrigger(
                    String.valueOf(event.getSecond()+ event.getMinute()+ event.getHour()),
                    TimeZone.getTimeZone(TimeZone.getDefault().getID()));
            eventScheduler = new EventScheduler(event.getMessage(),cronTrigger);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     return eventScheduler;
    }

}

