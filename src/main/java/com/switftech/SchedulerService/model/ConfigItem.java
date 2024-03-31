package com.switftech.SchedulerService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Config")
public class ConfigItem {

    @Id
    private Long id;
    private String status;
    private LocalDateTime time;
}
