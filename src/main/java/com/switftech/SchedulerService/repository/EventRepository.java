package com.switftech.SchedulerService.repository;

import com.switftech.SchedulerService.model.Event;
import com.switftech.SchedulerService.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findDateTime();

    Event findByStatus(Status status);
}
