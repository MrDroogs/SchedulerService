//package com.switftech.SchedulerService.util;
//
//import lombok.RequiredArgsConstructor;
//import org.quartz.Scheduler;
//import org.quartz.impl.StdSchedulerFactory;
//
//@RequiredArgsConstructor
//public class RescheduleJob {
//    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//    // Start the scheduler if it's not already started
//            if (!scheduler.isStarted()) {
//        scheduler.start();
//    }
//
//    // Define the job and tie it to our HelloJob class
//    JobKey jobKey = new JobKey("myJob", "group1");
//    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//
//            if (jobDetail != null) {
//        // Define the new trigger to fire on the 5th of every month
//        Trigger newTrigger = TriggerBuilder.newTrigger()
//                .withIdentity("newTrigger", "group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 5 * ?")) // Fire at midnight on the 5th of every month
//                .build();
//
//        // Reschedule the job with the new trigger
//        scheduler.rescheduleJob(jobDetail.getKey(), newTrigger);
//        System.out.println("Job rescheduled successfully.");
//    } else {
//        System.out.println("Job not found.");
//    }
//
//    // Shutdown the scheduler
//            scheduler.shutdown();
//
//} catch (SchedulerException se) {
//        se.printStackTrace();
//        }
//                }
//
//}
