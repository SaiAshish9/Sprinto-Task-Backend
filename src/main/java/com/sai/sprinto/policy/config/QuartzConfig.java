package com.sai.sprinto.policy.config;

import com.sai.sprinto.policy.QuartzScheduler.QuartzJob;
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
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("jobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

