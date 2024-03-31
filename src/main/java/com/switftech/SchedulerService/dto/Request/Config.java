package com.switftech.SchedulerService.dto.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Data
public class Config {

    private String status;
    private LocalDateTime time;

}
