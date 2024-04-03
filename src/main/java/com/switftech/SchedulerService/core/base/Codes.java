package com.switftech.SchedulerService.core.base;


import com.switftech.SchedulerService.core.records.CodeRecord;
import org.springframework.stereotype.Component;

@FunctionalInterface
@Component
public interface Codes {

    CodeRecord pick(String code);
}
