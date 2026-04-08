package com.ouz.k8sexample.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GreetingScheduler {

    @Value("${greeting}")
    private String greeting;

    @Scheduled(cron="*/5 * * * * *")
    public void scheduledWelcome(){
        System.out.println(greeting);
    }
}
