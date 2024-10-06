package com.ouz.k8sexample.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class GreetingScheduler {

    @Value("${welcome.greeting}")
    private String greeting;

    @Scheduled(cron="*/5 * * * * *")
    public void scheduledWelcome(){
        System.out.println(greeting);
    }
}
