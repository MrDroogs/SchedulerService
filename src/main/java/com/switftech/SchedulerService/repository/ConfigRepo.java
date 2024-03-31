package com.switftech.SchedulerService.repository;

import com.switftech.SchedulerService.model.ConfigItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepo extends JpaRepository<ConfigItem,String> {

}
