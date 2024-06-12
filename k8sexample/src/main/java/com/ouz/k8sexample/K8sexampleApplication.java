package com.ouz.k8sexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class K8sexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(K8sexampleApplication.class, args);
    }

}
