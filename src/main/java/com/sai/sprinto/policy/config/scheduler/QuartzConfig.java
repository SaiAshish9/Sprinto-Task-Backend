package com.sai.sprinto.policy.config.scheduler;

import com.sai.sprinto.policy.scheduler.job.QuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(QuartzJob.class)
                .withIdentity("jobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger triggerJobAfterEvery30Days() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 1/30 * ? *");

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("jobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

